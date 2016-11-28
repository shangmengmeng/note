package com.anew.note.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.anew.note.R;
import com.anew.note.adapter.MyRecyclerViewAdapter;
import com.anew.note.model.TipModel;
import com.anew.note.model.TipsModel;
import com.anew.note.utils.Constant;
import com.anew.note.utils.MySqliteHelper;
import com.anew.note.utils.SPUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ShowActivity extends AppCompatActivity {
    private RecyclerView rc_list;
    private ArrayList<TipModel>mlist;
    private MyRecyclerViewAdapter adapter;
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
            public void OnLongItemClick(View v, int position) {
                Toast.makeText(getApplicationContext(),"看看长按点击",Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void getData() {
        ArrayList<TipModel> model22= (ArrayList<TipModel>) SPUtils.getInstance(getApplicationContext()).getObject("list");
        Log.e("----------++++++---",model22.toString());
        mlist = model22;
    }
}
