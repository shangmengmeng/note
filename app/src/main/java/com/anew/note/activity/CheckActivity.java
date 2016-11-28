package com.anew.note.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;

import com.anew.note.R;
import com.anew.note.utils.RegularUtils;
import com.anew.note.utils.SPUtils;
import com.anew.note.views.InputDialog;

public class CheckActivity extends AppCompatActivity {
    private String a = "";
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_check);
        beforView();
    }

    private void beforView() {

        if (SPUtils.getInstance(getApplicationContext()).getStringValue("isFist") == null) {
            InputDialog inputDialog = new InputDialog.Builder(this).setTitle("欢迎来到秘密空间")
                    .setInputHint("请设定密码，4位数字就可以")
                    .setInputMaxWords(4)
                    .setPositiveButton("确定", new InputDialog.ButtonActionListener() {
                        @Override
                        public void onClick(CharSequence inputText) {
                            a = (String) inputText;
                            if (a.length()==4&& RegularUtils.isNumber(a)){
                                SPUtils.getInstance(getApplicationContext()).putStringValue("note", a);
                                SPUtils.getInstance(getApplicationContext()).putStringValue("isFist", "哈哈");
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"请输入4位数字",Toast.LENGTH_SHORT).show();
                               finish();
                            }

                        }
                    }).show();
        } else {
            InputDialog inputDialog = new InputDialog.Builder(this).setTitle("请输入密码")
                    .setInputHint("4位数字就可以...")
                    .setInputMaxWords(4)
                    .setPositiveButton("确定", new InputDialog.ButtonActionListener() {
                        @Override
                        public void onClick(CharSequence inputText) {


                        }
                    })
                    .setNegativeButton("取消", new InputDialog.ButtonActionListener() {
                        @Override
                        public void onClick(CharSequence inputText) {
                            finish();

                        }
                    })
                    .interceptButtonAction(new InputDialog.ButtonActionIntercepter() {
                        @Override
                        public boolean onInterceptButtonAction(int whichButton, CharSequence inputText) {
                            if (((String) inputText).equals(SPUtils.getInstance(getApplicationContext())
                                    .getStringValue("note")) && whichButton == DialogInterface.BUTTON_POSITIVE) {
                                Intent intent = new Intent(CheckActivity.this, ScrollingActivity.class);
                                startActivity(intent);
                                return true;
                            }
                            if (!((String) inputText).equals(SPUtils.getInstance(getApplicationContext())
                                    .getStringValue("note")
                            )) {
                                finish();
                            }
                            return false;
                        }
                    })
                    .show();
        }


    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
