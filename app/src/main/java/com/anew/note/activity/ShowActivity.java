package com.anew.note.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.anew.note.R;
import com.anew.note.adapter.MyRecyclerViewAdapter;
import com.anew.note.model.TipModel;

import java.util.ArrayList;

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
       //jia shu ju
        mlist = new ArrayList<>();
        TipModel t1 = new TipModel();
        t1.setContent("哈哈");
        t1.setDate("2016-12-6");
        TipModel t2 = new TipModel();
        t2.setContent("哈哈");
        t2.setDate("2016-12-6");
        mlist.add(t1);
        mlist.add(t2);
    }
}
