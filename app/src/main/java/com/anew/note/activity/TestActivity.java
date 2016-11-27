package com.anew.note.activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.anew.note.R;
import com.anew.note.utils.DbManager;
import com.anew.note.utils.MySqliteHelper;

public class TestActivity extends AppCompatActivity {
    private MySqliteHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //获得数据库帮助类对象
        helper= DbManager.getInstance(this);

    }
    //点击按钮创建数据库
    public void creatDb(View view){
        SQLiteDatabase db = helper.getWritableDatabase();
    }
}
