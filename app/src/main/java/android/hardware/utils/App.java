package android.hardware.utils;

import android.app.Application;
import android.content.Context;
import android.robottesttoll.R;

import com.iflytek.cloud.SpeechUtility;

/*** ==============================================================================
 *
 * 版 权 : ****
 *
 * @author  : 王 建 宇
 *
 * @version  : 1.0
 *
 * 创建日期 : 2018/6/7 17:41
 *
 * 描 述 :
 *
 *
 *
 * 修订历史 :
 *
 * ==============================================================================*/
public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
       SpeechUtility.createUtility(this, "appid=" + getString(R.string.app_id));
    }

    public static Context getContext() {
        return context;
    }
}
