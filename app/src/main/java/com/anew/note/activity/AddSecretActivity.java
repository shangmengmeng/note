package com.anew.note.activity;

import android.content.Intent;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anew.note.R;
import com.anew.note.model.SecModel;
import com.anew.note.utils.SPUtils;
import com.anew.note.utils.TimeUtils;

import java.util.ArrayList;

public class AddSecretActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_title,edit_content;
    private Button btn_sumit;
    private TextView text_date;
    private SecModel mData = new SecModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_secrit);
        initView();
        setData();
    }

    private void initView() {
        edit_title = (EditText) findViewById(R.id.edit_title);
        edit_content = (EditText) findViewById(R.id.edit_content);

        text_date = (TextView) findViewById(R.id.text_date_sec);
        btn_sumit = (Button) findViewById(R.id.btn_sumit);
        btn_sumit.setOnClickListener(this);
    }
    private void setData() {
        text_date.setText(TimeUtils.getTodayDate());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sumit:
                if (TextUtils.isEmpty(edit_title.getText())){
                    Toast.makeText(this,"请输入标题",Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(edit_content.getText())){
                    Toast.makeText(this,"请输入内容",Toast.LENGTH_SHORT).show();
                }
                else {
                    mData.setDate(text_date.getText().toString());
                    mData.setContent(edit_content.getText().toString());
                    mData.setTitle(edit_title.getText().toString());
                    ArrayList<SecModel> mlist = new ArrayList<>();
                    mlist.add(mData);
                    SPUtils.getInstance(this).putObject("slist",mlist);
                    Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this,MainActivity.class);
                    startActivity(intent);
                }
        }
    }
}
