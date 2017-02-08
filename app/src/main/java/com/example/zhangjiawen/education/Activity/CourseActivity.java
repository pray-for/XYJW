package com.example.zhangjiawen.education.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.zhangjiawen.education.R;
import com.example.zhangjiawen.education.util.JsoupService;

/**
 * Created by zhangjiawen on 2017/1/17.
 * 课表查询界面
 */
public class CourseActivity extends AppCompatActivity implements View.OnClickListener {

    private SlidingMenu mLeftMenu;//左边侧滑栏
    private Button back;//标题栏上的返回键
    private Toolbar toolbar;

    private int[][] btn_id = {{R.id.course_1_1, R.id.course_1_2, R.id.course_1_3, R.id.course_1_4, R.id.course_1_5, R.id.course_1_6, R.id.course_1_7},
            {R.id.course_2_1, R.id.course_2_2, R.id.course_2_3, R.id.course_2_4, R.id.course_2_5, R.id.course_2_6, R.id.course_2_7},
            {R.id.course_3_1, R.id.course_3_2, R.id.course_3_3, R.id.course_3_4, R.id.course_3_5, R.id.course_3_6, R.id.course_3_7},
            {R.id.course_4_1, R.id.course_4_2, R.id.course_4_3, R.id.course_4_4, R.id.course_4_5, R.id.course_4_6, R.id.course_4_7},
            {R.id.course_5_1, R.id.course_5_2, R.id.course_5_3, R.id.course_5_4, R.id.course_5_5, R.id.course_5_6, R.id.course_5_7},
            {R.id.course_6_1, R.id.course_6_2, R.id.course_6_3, R.id.course_6_4, R.id.course_6_5, R.id.course_6_6, R.id.course_6_7}};
    /**
     * 课程背景
     */
    private int[] course_bg_id = {
            R.drawable.course1,R.drawable.course2,R.drawable.course3,R.drawable.course1,R.drawable.course2,R.drawable.course3,R.drawable.course1,
    };
    /**
     * 课程背景随机数
     */
    int course_id_num = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_layout);
        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);


        Intent intent = getIntent();
        String response = intent.getStringExtra("content");
        String[][] course = JsoupService.getCourse(response);
        for (int i = 0; i < course.length; i++) {
            for (int j = 0; j < course[i].length; j++) {
                Button button = (Button) findViewById(btn_id[i][j]);
                button.setText(course[i][j]);
                if (button.getText().length() > 2) {
                    button.setBackgroundResource(course_bg_id[course_id_num]);
                    //如果等于最大值，就从0从新开始，否则递增
                    course_id_num = (course_id_num < course_bg_id.length - 1) ? course_id_num + 1 : 0;
                }

            }
        }

    }



//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                this.finish();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
