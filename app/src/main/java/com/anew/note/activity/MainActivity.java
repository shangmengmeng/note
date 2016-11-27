package com.anew.note.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anew.note.R;
import com.anew.note.model.TipModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView imageView;
    private AppBarLayout appBarLayout;
    private TextView text_blakborad, text_show, text_add, text_check;
    private NavigationView navigationView;
    private TipModel nData;
    private int from;
    private final static int FROM_ADD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
        initView();
        setData();
    }



    public void getData() {
        if (getIntent().getSerializableExtra("AddActivity") != null) {
            nData = (TipModel) getIntent().getSerializableExtra("AddActivity");
            from = FROM_ADD;
        }
    }

    private void initView() {
        navigationView = (NavigationView) findViewById(R.id.slide_menu);
        imageView = (ImageView) findViewById(R.id.image_title);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.ctool);
        text_add = (TextView) findViewById(R.id.add_text);
        text_add.setOnClickListener(this);
        text_check = (TextView) findViewById(R.id.check_text);
        text_check.setOnClickListener(this);
        text_blakborad = (TextView) findViewById(R.id.text_blackboard);
        text_show = (TextView) findViewById(R.id.text_show);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        View headerView = navigationView.inflateHeaderView(R.layout.navigation_header);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //到底部时会触发
                if (verticalOffset == 0) {
                    text_show.setVisibility(View.VISIBLE);
                    text_blakborad.setVisibility(View.GONE);
                }
                if (verticalOffset > 100) {
                    text_show.setVisibility(View.GONE);
                }
                //到顶部位置附近会触发
                if (Math.abs(verticalOffset) >= (appBarLayout.getTotalScrollRange() - 500)) {
                    Log.e("--------------", "hbbbbbbbbbb");
                    text_show.setVisibility(View.GONE);
                    text_blakborad.setVisibility(View.VISIBLE);
                }
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.slide_item1:
                        Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                        startActivity(intent);
                    default:
                        break;
                }
                return true;
            }
        });
    }
    private void setData() {
        if (from ==FROM_ADD){
            text_blakborad.setText(nData.getContent());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_text:
                Intent intent = new Intent(this, AddActivity.class);
                startActivity(intent);
                break;
            case R.id.check_text:
                Intent intent1 = new Intent(this, ShowActivity.class);
                startActivity(intent1);
            default:
                break;
        }
    }


}
