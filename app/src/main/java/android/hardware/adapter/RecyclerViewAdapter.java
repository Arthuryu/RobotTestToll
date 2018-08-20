package android.hardware.adapter;

import android.content.Context;
import android.robottesttoll.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;




/*** ==============================================================================
 *
 * 版 权 : ****
 *
 * @author  : 王 建 宇
 *
 * @version  : 1.0
 *
 * 创建日期 : 2018/6/6 14:41
 *
 * 描 述 :
 *
 *
 *
 * 修订历史 :
 *
 * ==============================================================================*/
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerHolder>{
    @NonNull
    private Context mContext;
    private String[] itemString = {"头部","说话","轮子","按键","屏幕","摄像头","灯"};

    public RecyclerViewAdapter(@NonNull Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerViewAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.id_rv_item_layout, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RecyclerHolder holder, int position) {
        holder.textView.setText(itemString[position]);

    }

    @Override
    public int getItemCount() {
        return itemString.length;
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView textView;

        private RecyclerHolder(View itemView) {
            super(itemView);
            textView =  itemView.findViewById(R.id.tv__id_item_layout);
        }
    }
}
