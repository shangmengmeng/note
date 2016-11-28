package com.anew.note.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.anew.note.R;
import com.anew.note.adapter.MyRecyclerViewAdapter;
import com.anew.note.model.TipModel;
import com.anew.note.utils.SPUtils;

import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView rc_list;
    private ArrayList<TipModel>mlist;
    private MyRecyclerViewAdapter adapter;
    TextView btnOK;
    private int Tag =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        getData();
        initView();
    }

    private void initView() {
        rc_list = (RecyclerView) findViewById(R.id.rc_list);
        adapter =new MyRecyclerViewAdapter(this,mlist);
        rc_list.setAdapter(adapter);

        LinearLayoutManager lm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rc_list.setLayoutManager(lm);
        adapter.setmOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {

            @Override
            public void OnItemClick(View v, int position) {

            }

            @Override
            public void OnLongItemClick(View v, final int position) {
                //这里长按定义的是删除
                AlertDialog.Builder dialog = new AlertDialog.Builder(NoteListActivity.this);
                dialog.setTitle("是否删除");
                dialog.setMessage("确定吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.removeData(position);
                    }
                } );

                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });



    }

    public void getData() {
        ArrayList<TipModel> model22= (ArrayList<TipModel>) SPUtils.getInstance(getApplicationContext()).getObject("list");
        Log.e("----------++++++---",model22.toString());
        mlist = model22;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           default:
               break;
        }
    }
}
