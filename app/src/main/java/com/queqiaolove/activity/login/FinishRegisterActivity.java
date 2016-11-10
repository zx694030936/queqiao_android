package com.queqiaolove.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.queqiaolove.R;

/**
 * Created by WD on 2016/10/11.
 */
public class FinishRegisterActivity extends Activity implements View.OnClickListener {

    private static String REGISTER = "register";
    private TextView tv_next;
    private ImageView tv_back;

    private EditText et_nickname;
    private EditText et_pwd;
    private EditText et_cetainpwd;
    private EditText et_invitecode;

    private TextView tv_location;
    private TextView tv_high;
    private TextView tv_education;
    private String nickname;
    private String location;
    private String high;
    private String pwd;
    private String certianpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_register);
        initView();
        initEvent();
    }

    private void initView() {
        tv_back = (ImageView) findViewById(R.id.iv_back);
        tv_next = (TextView) findViewById(R.id.tv_next);

        et_nickname = (EditText) findViewById(R.id.et_nickname);

        tv_location = (TextView) findViewById(R.id.tv_address);
        tv_high = (TextView) findViewById(R.id.tv_high);
        tv_education = (TextView) findViewById(R.id.tv_education);

        et_pwd = (EditText) findViewById(R.id.et_pwd);
        et_cetainpwd = (EditText) findViewById(R.id.et_cetainpwd);
        et_invitecode = (EditText) findViewById(R.id.et_invitecode);
    }
    private void initEvent() {
        tv_next.setOnClickListener(this);
        tv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_next:
                if(hasNull()) {//非空判断
                    break;
                }

                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private boolean hasNull() {
        nickname = et_nickname.getText().toString().trim();
        if (nickname.length()==0){
            toast("昵称不能为空");
            return true;
        }
        location = tv_location.getText().toString().trim();
        if (location.length()==0){
            toast("地区不能为空");
            return true;
        }
        high = tv_high.getText().toString().trim();
        if (high.length()==0){
            toast("身高不能为空");
            return true;
        }
        pwd = et_pwd.getText().toString().trim();
        if (pwd.length()==0){
            toast("密码不能为空");
            return true;
        }
        certianpwd = et_cetainpwd.getText().toString().trim();
        if (certianpwd.length()==0){
            toast("不能为空");
            return true;
        }

        return false;
    }

    /**
     * 从外部跳转到本类的反复
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, FinishRegisterActivity.class);
        intent.putExtra(REGISTER, data);
        activity.startActivity(intent);
    }
    /**
     * toast工具类
     */
    public void toast(String str){
        Toast.makeText(this,str, Toast.LENGTH_SHORT).show();
    }
}
