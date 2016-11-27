package com.anew.note.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.anew.note.R;
import com.anew.note.model.TipModel;
import java.util.ArrayList;

/**
 * Created by pig on 2016/11/26.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private ArrayList<TipModel>list;

    public MyRecyclerViewAdapter(Context context, ArrayList<TipModel> list) {
        this.context = context;
        this.list = list;
    }
    //定义一个接口，
    public interface OnItemClickListener{
        //没有方法体
        void OnItemClick(View v ,int position);
        void OnLongItemClick(View v ,int position);
    }
    //定义一个接口变量
    private OnItemClickListener mOnItemClickListener;
    //定义一个方法供外部调用
    public void setmOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_show,parent,false);
        MyViewHolder  viewHolder = new MyViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.text_content.setText(list.get(position).getContent());
        holder.text_date_copy.setText(list.get(position).getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener!=null){
                    mOnItemClickListener.OnItemClick(v,position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener!=null){
                    mOnItemClickListener.OnLongItemClick(v,position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{
    protected TextView text_content,text_date_copy;
    public MyViewHolder(View itemView) {
        super(itemView);
        text_content = (TextView) itemView.findViewById(R.id.text_content);
        text_date_copy = (TextView) itemView.findViewById(R.id.text_date_copy);
    }
}
