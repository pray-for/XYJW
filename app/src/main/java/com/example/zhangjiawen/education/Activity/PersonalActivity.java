package com.example.zhangjiawen.education.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zhangjiawen.education.Adapter.PersonalAdapter;
import com.example.zhangjiawen.education.R;
import com.example.zhangjiawen.education.util.JsoupService;
import com.example.zhangjiawen.education.util.OkHttpUtil;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhangjiawen on 2017/1/23.
 * 个人信息界面
 */
public class PersonalActivity extends AppCompatActivity implements View.OnClickListener {

    private SlidingMenu mLeftMenu;//左边侧滑栏
    private Button back;//标题栏上的返回键
    private static final String TAG = "PersonalActivity";
    private Toolbar toolbar;
    private ListView listView;
    private Map<String, String> map;
    private PersonalAdapter personalAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_layout);
        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);

        this.toolbar = (Toolbar) findViewById(R.id.main_toolbar_back);
        toolbar.setTitle("西邮教务");
        toolbar.setSubtitle("个人信息");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initView();
        personalAdapter = new PersonalAdapter(this);
        initData();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.personal_listView);
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

    /**
     * 初始化数据
     */
    private void initData() {
        //获取Request对象
        Intent intent = getIntent();
        String name1 = intent.getStringExtra("name1");
        String number1 = intent.getStringExtra("number1");
        Log.d("传参姓名+学号" , name1 + "---" + number1);
//        final Request request = OkHttpUtil.getRequest(OkHttpUtil.getREFERER() + JsoupService.getLinkMap().get("个人信息"));
        Request request = OkHttpUtil.getRequest(OkHttpUtil.getREFERER() + "xsgrxx.aspx?xh=" + number1 + "&xm=" + name1 + "&gnmkdm=N121501");
//        Log.d("学生信息测试" , OkHttpUtil.getREFERER() + JsoupService.getLinkMap().get("个人信息"));
        Log.d("学生信息测试" , OkHttpUtil.getREFERER() + JsoupService.getLinkMap());
        OkHttpUtil.getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PersonalActivity.this, "获取失败，请检查网络", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String content = new String(response.body().bytes(), "gb2312");
//                Log.d("AAAAAAAAAA" , response+"");
//                Log.d("content测试---Personal" , content);
                map = JsoupService.parsePersonInfo(content);
                //将数据传递到适配器
                personalAdapter.setMap(map);
                //更新UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(personalAdapter);
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
               finish();
        }
    }

}
