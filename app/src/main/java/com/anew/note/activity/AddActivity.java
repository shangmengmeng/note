package com.anew.note.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.anew.note.R;
import com.anew.note.model.TipModel;

public class AddActivity extends AppCompatActivity {
    private EditText edit_add;
    private TextView text_number;
    private TipModel mData = new TipModel();
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
        text_number = (TextView) findViewById(R.id.text_number);
        edit_add = (EditText) findViewById(R.id.edit_add);

    }
    private void setData() {
        mData.setContent(edit_add.getText().toString());

        mData.setDate("");
    }

}
