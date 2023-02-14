package com.anew.note.activity;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import com.anew.note.R;
import com.anew.note.app.MyApplycation;
import com.anew.note.views.SplashLayout;
import com.yanzhenjie.permission.AndPermission;

public class SplashActivity extends AppCompatActivity {
    private   long timeExit =0;
    private Handler handler =  new Handler();
    private ImageView iv_welcome;
    private int runCount;
    public  String cityText  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //地图开启----------------------------------------------------
        AndPermission.with(this)
                .requestCode(100)
                .permission(Manifest.permission.WRITE_CONTACTS,
                        Manifest.permission.READ_SMS,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_PHONE_STATE)
                .send();

//---------------------------------------------------------------------------------------
        final SplashLayout layout = (SplashLayout) findViewById(R.id.splashLayout);
        iv_welcome = (ImageView) findViewById(R.id.iv_welcome);
        final Handler handler = new Handler();
        runCount = 0;// 全局变量，用于判断是否是第一次执行
        Runnable runnable = new Runnable(){

            @Override
            public void run() {
                // TODO Auto-generated method stub
                if(runCount == 10){// 第一次执行则关闭定时执行操作
                    // 在此处添加执行的代码
                    layout.initData();
                    layout.initData();
                    layout.initData();
                    layout.initData();
                    handler.removeCallbacks(this);
                }
                handler.postDelayed(this, 10);
                runCount++;
            }

        };
        handler.postDelayed(runnable, 10);// 打开定时器，执行操作


        iv_welcome.setVisibility(View.VISIBLE);
        BounceInterpolator i = new BounceInterpolator();
        ObjectAnimator transX = ObjectAnimator.ofFloat(iv_welcome,"translationX",-500,300);
        transX.setInterpolator(i);
        transX.setDuration(1200);
        ObjectAnimator scalY = ObjectAnimator.ofFloat(iv_welcome,"scaleY",1f,1.5f,1.5f,1f,1f,1.5f,1.5f,1f,1f,1.5f,1.5f,1f);
        scalY.setDuration(1200);
        scalY.setInterpolator(i);
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(transX,scalY);
        set.start();

        //双击进入主页面
        layout.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View v, MotionEvent event) {
              if (event.getAction() == MotionEvent.ACTION_DOWN){

                  if (System.currentTimeMillis()-timeExit>1000) {
                      timeExit = System.currentTimeMillis();

                  }
                  else {
                      Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                      intent.putExtra("city",cityText);
                      startActivity(intent);
                      layout.stop();
                      finish();
                  }
                  return true;
              }
              return false;
          }
      });
      handler .postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                intent.putExtra("city",cityText);
                startActivity(intent);
                layout.stop();
                finish();
            }
        },10000);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        return;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    //写入文件

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                MyApplycation.getInstance().initLocalFile();
        }
    }
}
