package com.example.zhangjiawen.education.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangjiawen.education.util.JsoupService;
import com.example.zhangjiawen.education.util.OkHttpUtil;
import com.example.zhangjiawen.education.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private SlidingMenu mLeftMenu;//左边侧滑栏

    private Button logon;//登录
    private Button course;//课表查询
    private Button grade;//成绩查询
    private Button train;//培养计划查询
    private Button attend;//考勤查询
    private Button back;//返回
    private Button personal;//个人信息

    private EditText username;//用户名
    private EditText password;//密码
    private EditText code;//验证码

    //验证码图片
    private ImageView imageView_code;
    private TextView textView_code;

    private Spinner spinner;
    private Handler handler;

    //是否正在请求验证码
    private boolean isGetCheckCode;
    //再按一次退出的初始时间
    private long exitTime = 0;
    //判断是否已经登录
    public int isLogin = 0;

    public String name_quanju = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLeftMenu = (SlidingMenu) findViewById(R.id.id_menu);

        initView();//对控件进行初始化
        initEvent();//对点击事件进行初始化
        getCheckCode();//获取验证码

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.obj != null && msg.obj instanceof Bitmap) {
                    Bitmap bitmap = (Bitmap) msg.obj;
                    imageView_code.setImageBitmap(bitmap);
                } else if (msg.obj != null && msg.obj instanceof String) {
                    Toast.makeText(MainActivity.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
                }
            }
        };

    }

    /**
     * 判断菜单的开关情况，toolbar里的菜单按钮
     */
    public void toggleMenu(View view){
        mLeftMenu.toggle();
    }

    /**
     * 对控件进行初始化
     */
    private void initView(){
        logon = (Button) findViewById(R.id.logon);
        course = (Button) findViewById(R.id.course);
        grade = (Button) findViewById(R.id.grade);
        train = (Button) findViewById(R.id.train);
        attend = (Button) findViewById(R.id.attend);
        back = (Button) findViewById(R.id.back);
        personal = (Button) findViewById(R.id.personal);
        username = (EditText) findViewById(R.id.editText_user);
        password = (EditText) findViewById(R.id.editText_password);
        code = (EditText) findViewById(R.id.editText_code);
        imageView_code = (ImageView) findViewById(R.id.imageView_code);
        textView_code = (TextView) findViewById(R.id.textView_code);

    }

    /**
     * 初始化点击事件
     */
    private void initEvent(){
        logon.setOnClickListener(this);
        course.setOnClickListener(this);
        grade.setOnClickListener(this);
        train.setOnClickListener(this);
        attend.setOnClickListener(this);
        back.setOnClickListener(this);
        personal.setOnClickListener(this);
        imageView_code.setOnClickListener(this);
        textView_code.setOnClickListener(this);
    }


    /**
     * 对点击事件进行处理
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.logon:
                Toast.makeText(this , "登录" , Toast.LENGTH_SHORT).show();
                login();
                break;
            case R.id.course:
                if (isLogin == 0){
                    Toast.makeText(this , "还没有登录\n请先登录" , Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this , "课表查询" , Toast.LENGTH_SHORT).show();
                    Intent intentCourse = new Intent(MainActivity.this , CourseActivity.class);
                    startActivity(intentCourse);
                }
                break;
            case R.id.grade:
                if (isLogin == 0){
                    Toast.makeText(this , "还没有登录\n请先登录" , Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this , "成绩查询" , Toast.LENGTH_SHORT).show();
                    Intent intentScore = new Intent(MainActivity.this , ScoreActivity.class);
                    startActivity(intentScore);
                }
                break;
            case R.id.train:
                if (isLogin == 0){
                    Toast.makeText(this , "还没有登录\n请先登录" , Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this , "培养计划查询" , Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.attend:
                Toast.makeText(this , "考勤查询" , Toast.LENGTH_SHORT).show();
                Intent intentAttend = new Intent(Intent.ACTION_VIEW);
                intentAttend.setData(Uri.parse("http://jwkq.xupt.edu.cn:8080/User/Query"));
                startActivity(intentAttend);
                break;
            case R.id.back:
                if (isLogin == 0){
                    Toast.makeText(this , "还没有登录\n请先登录" , Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this , "退出" , Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent();
                    intent1.setClass(MainActivity.this, MainActivity.class);
                    startActivity(intent1);
                    MainActivity.this.finish();
                }
                break;
            case R.id.personal:
                if (isLogin == 0){
                    Toast.makeText(this , "还没有登录\n请先登录" , Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this , "显示个人信息" , Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent();
//                    String name1 = intent.getStringExtra("name1");
//                    intent.putExtra("name1" , name_quanju);
//                    intent.putExtra("number1" , username.getText().toString().trim());
//                    intent.setClass(MainActivity.this, PersonalActivity.class);
//                    MainActivity.this.startActivity(intent);
                    Intent intentPersonal = new Intent(MainActivity.this , PersonalActivity.class);
                    startActivity(intentPersonal);
                }
                break;
            case R.id.imageView_code://点击图片，对验证码进行刷新
                getCheckCode();
                break;
            case R.id.textView_code://点击文字，对验证码进行刷新
                getCheckCode();
                break;
        }
    }


    /**
     * 获取验证码
     */
    private void getCheckCode() {
        //若正在请求中，直接return
        if (isGetCheckCode) {
            return;
        }
        isGetCheckCode = true;
        try {
            Request request = OkHttpUtil.getRequest(OkHttpUtil.getUrlCode());
            OkHttpUtil.getOkHttpClient().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Message message = Message.obtain();
                    message.obj = BitmapFactory.decodeResource(getResources(), R.drawable.code_error);
                    handler.sendMessage(message);
                    Log.d(TAG, "getCheckCode -->>> onFailure -->>>" + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Message message = Message.obtain();
                    if (response.code() == 200) {
                        message.obj = BitmapFactory.decodeStream(response.body().byteStream());
                    } else {
                        message.obj = BitmapFactory.decodeResource(getResources(), R.drawable.code_error);
                    }
                    handler.sendMessage(message);
                    Log.d(TAG, " getCheckCode -->>> onResponse --> response.code -->" + response.code());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //加载验证码结束
            isGetCheckCode = false;
        }
    }

    /**
     * 登录
     */
    private void login() {
        if (TextUtils.isEmpty(username.getText())) {
            username.setError("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(password.getText())) {
            password.setError("密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(code.getText())) {
            code.setError("验证码不能为空");
            return;
        }
        //添加请求参数
        RequestBody requestBody = new FormBody.Builder()
                .add("__VIEWSTATE", "dDwtNTE2MjI4MTQ7Oz61IGQDPAm6cyppI+uTzQcI8sEH6Q==")
                .add("__VIEWSTATEGENERATOR", "92719903")
                .add("txtUserName", username.getText().toString().trim())
                .add("TextBox2", password.getText().toString().trim())
                .add("txtSecretCode", code.getText().toString().toString())
                .add("Button1", "")
                .build();
        Log.d(TAG, "requestBody -->\ntxtUserName:" + username.getText().toString().trim()
                + "\nTextBox2 :" + password.getText().toString().trim()
                + "\ntxtSecretCode :" + code.getText().toString().toString());
        Request request = OkHttpUtil.getRequest(OkHttpUtil.getUrlLogin(), requestBody);
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("正在登录...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        OkHttpUtil.getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = Message.obtain();
                message.obj = "登录失败，请检查网络";
                handler.sendMessage(message);
                Log.d(TAG, " login -->>> onFailure --> " + e.getMessage());
                progressDialog.dismiss();
                getCheckCode();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    Message message = Message.obtain();
                    if (response.code() == 200) {
                        String content = new String(response.body().bytes(), "gb2312");
                        //登录成功
                        if (JsoupService.isLogin(content) != null) {
                            isLogin = 1;
                            Intent intent = new Intent();
                            //将响应的html代码放入intent
                            intent.putExtra("content", content);
                            //将名字放入intent
                            intent.putExtra("name", JsoupService.isLogin(content));
                            Log.d("姓名------" , JsoupService.isLogin(content));//"***同学"
                            String name1 = JsoupService.isLogin(content).substring(0 , JsoupService.isLogin(content).length()-2);
                            Log.d("姓名截取" , name1);//"***"
                            intent.putExtra("name1" , name1);
                            intent.putExtra("number1" , username.getText().toString().trim());
                            name_quanju = name1;
                            intent.setClass(MainActivity.this , PersonalActivity.class);
                            MainActivity.this.startActivity(intent);
                        }//登录失败，显示登陆失败后的提示信息
                        else if (JsoupService.getLoginErrorMessage(content) != null) {
                            message.obj = JsoupService.getLoginErrorMessage(content);
                            handler.sendMessage(message);
                            Log.d(TAG, "getUrlLogin --> onSuccess -->" + JsoupService.getLoginErrorMessage(content));
                            //登录失败重新加载验证码
                            getCheckCode();
                        }//若没有提示信息
                        else {
                            message.obj = "登录失败，请检查网络";
                            handler.sendMessage(message);
                            getCheckCode();
                        }
                    } else {
                        message.obj = "登录失败，请检查网络";
                        handler.sendMessage(message);
                        getCheckCode();
                    }
                    Log.d(TAG, " login -->>> onResponse --> response.code -->" + response.code());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    progressDialog.dismiss();
                }
            }
        });
    }


    /**
     * 再按一次退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis() - exitTime > 2000){
                Toast.makeText(this , "再按一次退出程序" , Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else  {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
