package com.anew.note.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.anew.note.R;
import com.anew.note.model.TipModel;
import com.anew.note.utils.TimeUtils;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edit_add;
    private TextView text_number,text_date;
    private TipModel mData = new TipModel();
    private Button button_summit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        setData();
        edit_add.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Editable editable = edit_add.getText();
                int len = editable.length();
                text_number.setText(len+"");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void initView() {
        text_date = (TextView) findViewById(R.id.text_date);
        text_number = (TextView) findViewById(R.id.text_number);
        edit_add = (EditText) findViewById(R.id.edit_add);
        button_summit = (Button) findViewById(R.id.button_summit);
        button_summit.setOnClickListener(this);


    }
    private void setData() {
        text_date.setText(TimeUtils.getTodayDate());
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_summit:
                if (TextUtils.isEmpty(edit_add.getText())){
                    Toast.makeText(getApplicationContext(),"baocun",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    long time = TimeUtils.getUnixStamp();
                    mData.setNumber(Integer.parseInt(text_number.getText().toString()));
                    mData.setContent(edit_add.getText().toString());
                    mData.setDate(text_date.getText().toString());
                    mData.setTip((int) time);
                    Toast.makeText(getApplicationContext(),"baocun",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("AddActivity",mData);
                    Log.e("-----------",mData.toString());
                    startActivity(intent);
                    finish();
                }

        }
    }
}
