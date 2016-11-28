package com.anew.note.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.anew.note.R;
import com.anew.note.model.SecrModel;
import com.anew.note.utils.SPUtils;

public class AddSecretActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_title,edit_content;
    private Button btn_sumit;
    private TextView text_date;
    private SecrModel mData = new SecrModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_secrit);
        initView();
    }

    private void initView() {
        edit_title = (EditText) findViewById(R.id.edit_title);
        edit_content = (EditText) findViewById(R.id.edit_content);
        btn_sumit = (Button) findViewById(R.id.btn_sumit);
        text_date = (TextView) findViewById(R.id.text_date_sec);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sumit:
                mData.setDate(text_date.getText().toString());
                mData.setContent(edit_content.getText().toString());
                mData.setTitle(edit_title.getText().toString());
                SPUtils.getInstance(this).putObject("screit",mData);

        }
    }
}
