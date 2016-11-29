package com.anew.note.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.anew.note.R;
import com.anew.note.utils.RegularUtils;
import com.anew.note.utils.SPUtils;
import com.anew.note.views.InputDialog;

public class CheckActivity extends AppCompatActivity {
    private boolean isFirst = true;
    private TextView text_input;
    private Button button_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_check);
        text_input = (TextView) findViewById(R.id.text_input);
        button_input = (Button) findViewById(R.id.button_sec);
        if (SPUtils.getInstance(getApplicationContext()).getStringValue("isFist") == null) {
            text_input.setHint("欢迎来到私密空间，请输入4位数字“1.2.3”密码");
            button_input.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String a = text_input.getText().toString();
                    if (a.length()==4&& RegularUtils.isNumber(a)){
                        SPUtils.getInstance(getApplicationContext()).putStringValue("note", a);
                        SPUtils.getInstance(getApplicationContext()).putStringValue("isFist", "哈哈");
                        Intent intent = new Intent(getApplicationContext(),ScrollingActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"请输入4位数字",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            });
        }
        else {
            text_input.setHint("请输入4位数字“1.2.3”密码");
            button_input.setText("确定");
            button_input.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String a = text_input.getText().toString();
                    if (a.equals(SPUtils.getInstance(getApplicationContext())
                            .getStringValue("note")) ) {
                        Intent intent = new Intent(CheckActivity.this, ScrollingActivity.class);
                        startActivity(intent);
                    }
                    if (!a.equals(SPUtils.getInstance(getApplicationContext())
                            .getStringValue("note"))) {
                      Toast.makeText(getApplicationContext(),"密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
