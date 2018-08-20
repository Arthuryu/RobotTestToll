package android.hardware;

import android.app.Activity;
import android.content.SharedPreferences;
import android.hardware.adapter.RecyclerViewAdapter;
import android.hardware.tools.SpaceItemDecoration;
import android.os.Bundle;
import android.os.Environment;
import android.robottesttoll.R;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.util.ResourceUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static String TAG = MainActivity.class.getSimpleName();
    //讯飞语音start---------
    private SharedPreferences mSharedPreferences;
    // 引擎类型
    private String mEngineType = SpeechConstant.TYPE_CLOUD;
    SpeechSynthesizer mTts ;
    // 默认云端发音人
    public static String voicerCloud = "xiaoyan";
    //讯飞语音end---------
    private Toast mToast;
    RecyclerViewAdapter recyclerViewAdapter;
    private String fuyang[] = {"俯", "仰"};

    private WheelsManager wheelsManager;
    private NeckManager neckManager;

    private Spinner fuyangSpinner ,angleSpinner;
//    private RecyclerView recyclerView;
    private EditText angle_move,speed_move,distance_move,angle_x,angle_y,times,angle_per_sec,abs_angle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = getSharedPreferences("com.youer.leddrivetest", Activity.MODE_PRIVATE);
        initData();
        initView();

        mTts = SpeechSynthesizer.createSynthesizer(this, mTtsInitListener);
       // setXunFeiParam();
    }


    public void initData(){
        wheelsManager = (WheelsManager)getSystemService("yoyo_wheels");
        neckManager = (NeckManager)getSystemService("yoyo_neck");
    }

    public void initView() {



        fuyangSpinner = findViewById(R.id.fuyangSpinner);
        angleSpinner = findViewById(R.id.angleSpinner);
        angle_move = findViewById(R.id.angle_move);
        speed_move = findViewById(R.id.speed_move);
        distance_move = findViewById(R.id.distance_move);
        angle_x = findViewById(R.id.angle_x);
        angle_y = findViewById(R.id.angle_y);
        times = findViewById(R.id.times);
        angle_per_sec = findViewById(R.id.angle_per_sec);
        abs_angle = findViewById(R.id.abs_angle);
        findViewById(R.id.open_wheels).setOnClickListener(this);
        findViewById(R.id.close_wheels).setOnClickListener(this);

        findViewById(R.id.forward).setOnClickListener(this);
        findViewById(R.id.backward).setOnClickListener(this);
        findViewById(R.id.left).setOnClickListener(this);
        findViewById(R.id.right).setOnClickListener(this);
        findViewById(R.id.turn).setOnClickListener(this);
        findViewById(R.id.halt).setOnClickListener(this);
        findViewById(R.id.turn_left).setOnClickListener(this);
        findViewById(R.id.turn_right).setOnClickListener(this);
        findViewById(R.id.move_async).setOnClickListener(this);
        findViewById(R.id.turn_async).setOnClickListener(this);
        findViewById(R.id.control_async_lr).setOnClickListener(this);
        findViewById(R.id.control_async_ud).setOnClickListener(this);
        findViewById(R.id.control_rock_lr).setOnClickListener(this);
        findViewById(R.id.reset_lr).setOnClickListener(this);
        findViewById(R.id.control_rock_ud).setOnClickListener(this);
        findViewById(R.id.reset_ud).setOnClickListener(this);
        findViewById(R.id.control_lr).setOnClickListener(this);
        findViewById(R.id.control_ud).setOnClickListener(this);

       // speed_move.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL |InputType.TYPE_NUMBER_VARIATION_NORMAL);
        distance_move.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        ArrayAdapter<String> fuyangAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                fuyang);

        fuyangSpinner.setAdapter(fuyangAdapter);
        fuyangSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(manager);
//        recyclerViewAdapter = new RecyclerViewAdapter(this);
//        recyclerView.addItemDecoration(new SpaceItemDecoration(50));
//        recyclerView.setAdapter(recyclerViewAdapter);

    }

    int angle_moveInt = 0,timesInt = 0;
    double speed_moveDou = 0,distance_moveDou = 0,abs_angle_xDou = 0,abs_angle_yDou = 0 ,angle_per_secDou = 0,abs_angleDou = 0;

    @Override
    public void onClick(View v) {
        int ret = -1;
        String angle_moveStr = angle_move.getText().toString().trim();
        String speed_moveStr = speed_move.getText().toString().trim();
        String distance_moveStr = distance_move.getText().toString().trim();

        String angle_xStr = angle_x.getText().toString().trim();
        String angle_yStr = angle_y.getText().toString().trim();
        String timesStr = times.getText().toString().trim();
        String angle_per_secStr = angle_per_sec.getText().toString().trim();
        String abs_angleStr = abs_angle.getText().toString().trim();


        if (angle_moveStr != null && !angle_moveStr.equals("")){
            angle_moveInt = Integer.valueOf(angle_moveStr);
        }
        if (speed_moveStr != null && !speed_moveStr.equals("")){
            speed_moveDou = Double.valueOf(speed_moveStr);
        }
        if (distance_moveStr != null && !distance_moveStr.equals("")){
            distance_moveDou = Double.valueOf(distance_moveStr);
        }
        if (angle_xStr != null && !angle_xStr.equals("")){
            abs_angle_xDou = Double.valueOf(angle_xStr);
        }
        if (angle_yStr != null && !angle_yStr.equals("")){
            abs_angle_yDou = Double.valueOf(angle_yStr);
        }
        if (timesStr != null && !timesStr.equals("")){
            timesInt = Integer.valueOf(timesStr);
        }
        if (angle_per_secStr != null && !angle_per_secStr.equals("")){
            angle_per_secDou = Double.valueOf(angle_per_secStr);
        }
        if (abs_angleStr != null && !abs_angleStr.equals("")){
            abs_angleDou = Double.valueOf(abs_angleStr);
        }


        switch (v.getId()){
            case R.id.open_wheels:
               ret = wheelsManager.open();
               ToastShow("open_wheels:"+ret);
                break;
            case R.id.close_wheels:
                ret = wheelsManager.close();
                ToastShow("close_wheels:"+ret);
                break;

            case R.id.forward:
                ret = wheelsManager.move_forward(speed_moveDou,distance_moveDou);
                ToastShow("forward:"+ret);
                break;
            case R.id.backward:
                ret = wheelsManager.move_backward(speed_moveDou,distance_moveDou);
                ToastShow("backward:"+ret);
                break;
            case R.id.left:
                ret = wheelsManager.move_left(speed_moveDou,distance_moveDou);
                ToastShow("left:"+ret);
                break;
            case R.id.right:
                ret = wheelsManager.move_right(speed_moveDou,distance_moveDou);
                ToastShow("right:"+ret);
                break;
            case R.id.turn:
                ret = wheelsManager.turn(speed_moveDou,angle_moveInt);
                ToastShow("turn:"+ret);
                break;
            case R.id.halt:
                ret = wheelsManager.halt();
                neckManager.halt_lr();
                neckManager.halt_ud();
                ToastShow("halt:"+ret);
                break;
            case R.id.turn_left:
                ret = wheelsManager.turn_left(speed_moveDou,angle_moveInt);
                ToastShow("turn_left:"+ret);
                break;
            case R.id.turn_right:
                ret = wheelsManager.turn_right(speed_moveDou,angle_moveInt);
                ToastShow("turn_right:"+ret);
                break;
            case R.id.move_async:
                ret = wheelsManager.move_async(angle_moveInt,speed_moveDou);
                ToastShow("move_async:"+ret);
                break;
            case R.id.turn_async:
                ret = wheelsManager.turn_async(speed_moveDou);
                ToastShow("turn_async:"+ret);
                break;
            case R.id.control_async_lr:
                ret = neckManager.control_async_lr(speed_moveDou);
                ToastShow("control_async_lr:"+ret);
                break;
            case R.id.control_async_ud:
                ret = neckManager.control_async_ud(speed_moveDou);
                ToastShow("control_async_ud:"+ret);
                break;
            case R.id.control_rock_lr:
                ret = neckManager.control_rock_lr(abs_angle_xDou,abs_angle_yDou,timesInt,angle_per_secDou);
                ToastShow("control_rock_lr:"+ret);
                break;
            case R.id.reset_lr:
                ret = neckManager.reset_lr(angle_per_secDou);
                ToastShow("reset_lr:"+ret);
                break;
            case R.id.control_rock_ud:
                ret = neckManager.control_rock_ud(abs_angle_xDou,abs_angle_yDou,timesInt,angle_per_secDou);
                ToastShow("control_rock_ud:"+ret);
                break;
            case R.id.reset_ud:
                ret = neckManager.reset_ud(angle_per_secDou);
                ToastShow("reset_ud:"+ret);
                break;
            case R.id.control_lr:
                ret = neckManager.control_lr(abs_angleDou,angle_per_secDou);
                ToastShow("control_lr:"+ret);
                break;
            case R.id.control_ud:
                ret = neckManager.control_ud(abs_angleDou,angle_per_secDou);
                ToastShow("control_ud:"+ret);
                break;


                default:
        }

    }

    public void ToastShow(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }


    //科大讯飞语音合成

    /**
     * 参数设置
     *
     * @param
     * @return
     */
    private void setXunFeiParam() {


        mTts.setParameter(SpeechConstant.PARAMS, null);
        //设置合成
        if (mEngineType.equals(SpeechConstant.TYPE_CLOUD)) {
            //设置使用云端引擎
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
            //设置发音人
            mTts.setParameter(SpeechConstant.VOICE_NAME, voicerCloud);
        } else {
            //设置使用本地引擎
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_LOCAL);
            //设置发音人资源路径
            mTts.setParameter(ResourceUtil.TTS_RES_PATH, getResourcePath());
            //设置发音人
            mTts.setParameter(SpeechConstant.VOICE_NAME, voicerCloud);
        }
        //设置合成语速
        mTts.setParameter(SpeechConstant.SPEED, mSharedPreferences.getString("speed_preference", "50"));
        //设置合成音调
        mTts.setParameter(SpeechConstant.PITCH, mSharedPreferences.getString("pitch_preference", "50"));
        //设置合成音量
        mTts.setParameter(SpeechConstant.VOLUME, mSharedPreferences.getString("volume_preference", "50"));
        //设置播放器音频流类型
        mTts.setParameter(SpeechConstant.STREAM_TYPE, mSharedPreferences.getString("stream_preference", "3"));

        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/tts.wav");


    }

    /**
     * 初始化监听。
     */
    private InitListener mTtsInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            Log.d(TAG, "InitListener init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败,错误码："+code);
            } else {
                // 初始化成功，之后可以调用startSpeaking方法
                // 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
                // 正确的做法是将onCreate中的startSpeaking调用移至这里
            }
        }
    };



    //获取发音人资源路径
    private String getResourcePath() {
        StringBuffer tempBuffer = new StringBuffer();
        //合成通用资源
        tempBuffer.append(ResourceUtil.generateResourcePath(this, ResourceUtil.RESOURCE_TYPE.assets, "tts/common.jet"));
        tempBuffer.append(";");
        //发音人资源
        tempBuffer.append(ResourceUtil.generateResourcePath(this, ResourceUtil.RESOURCE_TYPE.assets, "tts/henry.jet"));
        return tempBuffer.toString();
    }

    /**
     * 合成回调监听。
     */
    private SynthesizerListener mTtsListener = new SynthesizerListener() {

        @Override
        public void onSpeakBegin() {
            showTip("开始播放");
        }

        @Override
        public void onSpeakPaused() {
            showTip("暂停播放");
        }

        @Override
        public void onSpeakResumed() {
            showTip("继续播放");
        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos,
                                     String info) {

        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
            // 播放进度

        }

        @Override
        public void onCompleted(SpeechError error) {
            if (error == null) {
                showTip("播放完成");

            } else if (error != null) {

            }
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		Log.d(TAG, "session id =" + sid);
            //	}
        }
    };

    private void showTip(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mToast.setText(str);
                mToast.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (null != mTts) {
            mTts.stopSpeaking();
            // 退出时释放连接
            mTts.destroy();
        }
    }



}
