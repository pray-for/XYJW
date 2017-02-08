package com.example.zhangjiawen.education.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.zhangjiawen.education.Adapter.ScoreRecyclerAdapter;
import com.example.zhangjiawen.education.Info.CourseInfo;
import com.example.zhangjiawen.education.R;

import java.util.ArrayList;

/**
 * Created by zhangjiawen on 2017/1/23.
 * 成绩查询界面
 */
public class ScoreActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private Context context;
    /**
     * 显示学年学期
     */
    private TextView textView_score_header;
    private RecyclerView recyclerView_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout);

        initView();

        Intent intent = getIntent();
        //获取intent传递过来的List
        ArrayList<CourseInfo> courseInfoArrayList = (ArrayList<CourseInfo>) intent.getSerializableExtra("score");
        String year = intent.getStringExtra("year");
        String semester = intent.getStringExtra("semester");
        textView_score_header.setText(year + "第" + semester + "学期");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView_score.setLayoutManager(linearLayoutManager);
        ScoreRecyclerAdapter scoreRecyclerAdapter = new ScoreRecyclerAdapter(this, courseInfoArrayList);
        recyclerView_score.setAdapter(scoreRecyclerAdapter);

    }

    private void initView() {
        this.toolbar = (Toolbar) findViewById(R.id.main_toolbar_back);
        this.textView_score_header = (TextView) findViewById(R.id.textView_score_header);
        this.recyclerView_score = (RecyclerView) findViewById(R.id.recyclerView_score);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

}
