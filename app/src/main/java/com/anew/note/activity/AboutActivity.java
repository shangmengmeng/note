package com.anew.note.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.anew.note.R;
import com.anew.note.utils.AppUtils;

public class AboutActivity extends AppCompatActivity {
    private TextView text_about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        text_about = (TextView) findViewById(R.id.text_about);
        String a = AppUtils.getVersionName(this);
        text_about.setText("Version Code："+ a);


    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
