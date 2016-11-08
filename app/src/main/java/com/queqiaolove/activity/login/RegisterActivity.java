package com.queqiaolove.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.queqiaolove.R;
import com.queqiaolove.activity.main.MainActivity;
import com.queqiaolove.global.Constants;
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.LoginAPI;
import com.queqiaolove.javabean.login.GetSmsCodeBean;
import com.queqiaolove.javabean.login.RegistBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WD on 2016/10/11.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {

    private Activity mActivity = this;
    private static String REGISTER = "register";
    private TextView tv_next;
    private ImageView tv_back;
    private TextView tv_obtaincode_register;
    private EditText et_phone;
    private String phone;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0://重新设置获取验证码按钮图片
                    tv_obtaincode_register.setText("获取验证码");
                    tv_obtaincode_register.setClickable(true);
                    break;
            }
        }
    };
    private EditText et_code;
    private String code;
    private EditText et_pwd;
    private EditText et_cetainpwd;
    private EditText et_invitecode;
    private EditText et_nickname;

    private String pwd="";
    private String cetainpwd="";
    private String nickname="";
    private String invitecode="";
    private static LoginActivity loginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initEvent();
    }

    private void initView() {
        tv_back = (ImageView) findViewById(R.id.iv_back);
        tv_next = (TextView) findViewById(R.id.tv_next);

        tv_obtaincode_register = (TextView) findViewById(R.id.tv_obtaincode_register);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_code = (EditText) findViewById(R.id.et_code);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        et_cetainpwd = (EditText) findViewById(R.id.et_certainpwd);
        et_nickname = (EditText) findViewById(R.id.et_nickname);
        et_invitecode = (EditText) findViewById(R.id.et_invitecode);
    }
    private void initEvent() {
        tv_next.setOnClickListener(this);
        tv_back.setOnClickListener(this);
        tv_obtaincode_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_next:
                if(hasNull()) {//非空判断
                    break;
                }
                register();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_obtaincode_register:
                phone = et_phone.getText().toString().trim();
                if (phone.length()<11){
                    toast("手机号不合法");
                    break;
                }
                obtaincode();
                break;
        }
    }
    private boolean hasNull() {
        phone = et_phone.getText().toString().trim();
        code = et_code.getText().toString().trim();
        pwd = et_pwd.getText().toString().trim();
        cetainpwd = et_cetainpwd.getText().toString().trim();
        nickname = et_nickname.getText().toString().trim();
        invitecode = et_invitecode.getText().toString().trim();
        if (phone.length()<11){
            toast("手机号不合法");
            return true;
        }

        if (code.length()<4){
            toast("验证码不合法");
            return true;
        }
        if (pwd.length()<6){
            toast("密码不合法");
            return true;
        }
        if (cetainpwd.length()<6){
            toast("确认不合法");
            return true;
        }
        if (nickname.length()==0){
            toast("昵称不合法");
            return true;
        }
        return false;
    }

    /*验证验证码*/
    private void register() {
        LoginAPI loginAPI = Http.getInstance().create(LoginAPI.class);
        loginAPI.regist(phone,pwd,cetainpwd,invitecode,code,nickname).enqueue(new Callback<RegistBean>() {
            @Override
            public void onResponse(Call<RegistBean> call, Response<RegistBean> response) {
                if (response.body().getReturnvalue().equals("true")){
                    //FinishRegisterActivity.intent(mActivity,"1");
                    RegistBean body = response.body();

                    final String username = body.getUuid();//环信ID
                    final String password = body.getPassword();//环信密码
                    /**
                     * 之后都是环信的代码
                     */
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            try {
                                EMClient.getInstance().login(username, password, new EMCallBack() {//环信登录方法（注册完了再登录）
                                    @Override
                                    public void onSuccess() {//登陆成功
                                        EMClient.getInstance().groupManager().loadAllGroups();
                                        EMClient.getInstance().chatManager().loadAllConversations();
                                        MainActivity.intent(mActivity,new String[]{"0"});
                                        finish();
                                        loginActivity.finish();
                                    }

                                    @Override
                                    public void onProgress(int progress, String status) {
                                    }

                                    @Override
                                    public void onError(final int code, final String message) {
                                        runOnUiThread(new Runnable() {
                                            public void run() {
                                                Toast.makeText(getApplicationContext(), getString(R.string.Login_failed) + message,
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }else {
                    toast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<RegistBean> call, Throwable t) {
                toast("网络数据异常");
            }
        });
    }

    /*获取验证码*/
    private void obtaincode() {
        LoginAPI loginAPI = Http.getInstance().create(LoginAPI.class);
        loginAPI.getSmsCode(phone, Constants.OBTAINCODE_REGISTER).enqueue(new Callback<GetSmsCodeBean>() {
            @Override
            public void onResponse(Call<GetSmsCodeBean> call, Response<GetSmsCodeBean> response) {
                if (response==null){
                    return;
                }
                if (response.body().getReturnvalue().equals("true")){
                    tv_obtaincode_register.setText("60s后重新获取");
                    tv_obtaincode_register.setClickable(false);
                    handler.sendEmptyMessageDelayed(0,60000);
                    toast("正在获取短信验证码...");
                }else {
                    toast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<GetSmsCodeBean> call, Throwable t) {
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
        intent.setClass(activity, RegisterActivity.class);
        intent.putExtra(REGISTER, data);
        loginActivity = (LoginActivity) activity;
        activity.startActivity(intent);
    }

    /**
     * toast工具类
     */
    public void toast(String str){
        Toast.makeText(this,str, Toast.LENGTH_SHORT).show();
    }
}
