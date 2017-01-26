package com.example.zhangjiawen.education.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.zhangjiawen.education.R;

/**
 * Created by zhangjiawen on 2017/1/17.
 */
public class CourseActivity extends AppCompatActivity implements View.OnClickListener {

    private SlidingMenu mLeftMenu;//左边侧滑栏
    private Button back;//标题栏上的返回键
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_layout);
        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);
//        back = (Button) findViewById(R.id.back);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
