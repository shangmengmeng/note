package com.anew.note.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.anew.note.R;
import com.anew.note.model.SecModel;
import com.anew.note.model.TipModel;
import com.anew.note.utils.JudgeUtils;
import com.anew.note.utils.SPUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
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
        //侧滑插入布局
        View headerView = navigationView.inflateHeaderView(R.layout.navigation_header);
        //修改图片

        CircleImageView profile_image = (CircleImageView)headerView.findViewById(R.id.profile_image);
        if (SPUtils.getInstance(this).getStringValue("image")!=null){
            String path =SPUtils.getInstance(this).getStringValue("image");
            Glide.with(this)
                    .load(path)
                    .into(profile_image);
        }



        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);

        //标题栏的设置
        //设置toolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("小签");
        setSupportActionBar(toolbar);
        //设置返回键
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置点击拉出侧滑
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,
                R.string.drawer_close);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        //设置伸缩栏
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.ctool);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.Cbbc9a));
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);



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
                    //查看判断是否输入密码
                    case R.id.slide_item1:
                        if (JudgeUtils.getIntence(getApplicationContext()).isPass()){
                            Intent intent = new Intent(getApplicationContext(), ScrollingActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), CheckActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        break;
                    //添加
                    case R.id.slide_item2:
                        Intent intent1 = new Intent(getApplicationContext(),AddSecretActivity.class);
                        startActivity(intent1);
                        break;
                    //我的
                    case R.id.slide_item3:
                        Intent intent3 = new Intent(getApplicationContext(), UserCenterActivity.class);
                        startActivity(intent3);
                        finish();
                        break;
                    //关于
                    case R.id.slide_item4:
                        Intent intent2 = new Intent(getApplicationContext(),AboutActivity.class);
                        startActivity(intent2);
                        break;
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


        if (SPUtils.getInstance(this).getObject("list")==null){
            ArrayList<TipModel> model = new ArrayList<TipModel>();
            SPUtils.getInstance(this).putObject("list",model);
        }
        if (SPUtils.getInstance(this).getObject("slist")==null){
            ArrayList<SecModel> smodel = new ArrayList<>();
            SPUtils.getInstance(this).putObject("slist",smodel);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_text:
                Intent intent = new Intent(this, AddActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.check_text:
                Intent intent1 = new Intent(this, NoteListActivity.class);
                startActivity(intent1);
            default:
                break;
        }
    }

    private long timeExit=0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis()-timeExit>2000){
                timeExit=System.currentTimeMillis();
                Toast.makeText(getApplicationContext(),"再按一下退出", Toast.LENGTH_SHORT).show();
            }
            else {
                System.exit(0);
            }
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
