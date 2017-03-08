package com.anew.note.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.gesture.GestureUtils;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.anew.note.R;
import com.anew.note.views.SplashLayout;

public class SplashActivity extends AppCompatActivity {
    private   long timeExit =0;
    private Handler handler =  new Handler();
    private ImageView iv_welcome;
    private int runCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

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


        layout.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View v, MotionEvent event) {
              if (event.getAction() == MotionEvent.ACTION_DOWN){

                  if (System.currentTimeMillis()-timeExit>2000) {
                      timeExit = System.currentTimeMillis();

                  }
                  else {
                      Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                      startActivity(intent);
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
                startActivity(intent);
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
}
