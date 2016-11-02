package com.queqiaolove.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.queqiaolove.QueQiaoLoveApp;
import com.queqiaolove.R;
import com.queqiaolove.activity.main.MainActivity;
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.LoginAPI;
import com.queqiaolove.javabean.login.LoginBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WD on 2016/10/11.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private static String LOGIN = "login";
    private TextView tv_register;
    private ImageView tv_back;
    private TextView tv_login_login;
    private TextView et_phone;
    private TextView et_pwd;
    private String phone;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (QueQiaoLoveApp.getMemberId()!=-1){
            MainActivity.intent(LoginActivity.this,new String[]{""});
        }
        setContentView(R.layout.activity_login);
        initView();
        initEvent();
    }

    private void initView() {
        tv_back = (ImageView) findViewById(R.id.iv_back);
        tv_register = (TextView) findViewById(R.id.tv_register_login);
        tv_login_login = (TextView) findViewById(R.id.tv_login_login);

        et_phone = (TextView) findViewById(R.id.et_phone);
        et_pwd = (TextView) findViewById(R.id.et_pwd);
    }
    private void initEvent() {
        tv_back.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        tv_login_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_register_login:
                RegisterActivity.intent(this,"");
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_login_login:
                phone = et_phone.getText().toString().trim();
                pwd = et_pwd.getText().toString().trim();
                if (phone.length()<11){
                    toast("手机号不合法");
                    break;
                }
                if (pwd.length()<6){
                    toast("登录密码不合法");
                    break;
                }
                login();
                break;
        }
    }

    private void login() {
        LoginAPI loginAPI = Http.getInstance().create(LoginAPI.class);
        loginAPI.login(phone,pwd).enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                if (response.body().getReturnvalue().equals("true")){
                    LoginBean body = response.body();
                    Log.e("userid",response.body().getUserid()+"");
                    QueQiaoLoveApp.setMemberId(Integer.parseInt(body.getUserid()));
                    finish();
                    MainActivity.intent(LoginActivity.this,new String[]{""});
                }else {
                    toast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                toast("网络数据异常");
            }
        });
    }

    /**
     * 从外部跳转到本类的反复
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, LoginActivity.class);
        intent.putExtra(LOGIN, data);
        activity.startActivity(intent);
    }

    /**
     * toast工具类
     */
    public void toast(String str){
        Toast.makeText(this,str, Toast.LENGTH_SHORT).show();
    }
}
