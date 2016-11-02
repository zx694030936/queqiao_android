package com.queqiaolove.activity.mine.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.global.Constants;
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.LoginAPI;
import com.queqiaolove.http.api.MineAPI;
import com.queqiaolove.javabean.login.GetSmsCodeBean;
import com.queqiaolove.javabean.mine.ChangePwdBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WD on 2016/10/14.
 */
public class ChangePwdActivity extends BaseActivity implements View.OnClickListener {
    private static String LOVE = "love";
    private ImageView iv_back;
    private TextView tv_changepwd_changepwd;
    private TextView tv_obtaincode_changephone;
    private TextView et_phone;
    private TextView et_code;
    private TextView et_newpwd;
    private String phone;
    private String code;
    private String newpwd;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0://重新设置获取验证码按钮图片
                    tv_obtaincode_changephone.setText("获取验证码");
                    tv_obtaincode_changephone.setClickable(true);
                    break;
            }
        }
    };
    @Override
    protected void activityOnCreate(Bundle extras) {

    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_base_mine,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_setting_changepwd,null);
    }

    @Override
    protected void initTitle() {
        TextView tv_title = (TextView) mTitleView.findViewById(R.id.tv_title);
        tv_title.setText("修改秘密");
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);
        et_phone = (TextView) mContentView.findViewById(R.id.et_oldphone);
        et_code = (TextView) mContentView.findViewById(R.id.et_code);
        et_newpwd = (TextView) mContentView.findViewById(R.id.et_newpwd);

        tv_changepwd_changepwd = (TextView) mContentView.findViewById(R.id.tv_changepwd_changepwd);
        tv_obtaincode_changephone = (TextView) mContentView.findViewById(R.id.tv_obtaincode_changephone);

    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);
        tv_changepwd_changepwd.setOnClickListener(this);
        tv_obtaincode_changephone.setOnClickListener(this);

    }

    @Override
    protected ContentPage.RequestState onLoad() {
        return ContentPage.RequestState.STATE_SUCCESS;
    }
    /**
     * 从外部跳转到本类的反复
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, ChangePwdActivity.class);
        intent.putExtra(LOVE, data);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_changepwd_changepwd://修改手机号
                if (isNull()){
                    break;
                }
                changePwd();
                break;
            case R.id.tv_obtaincode_changephone://获取验证码
                phone = et_phone.getText().toString().trim();
                if (phone.length()<11){
                    toast("旧手机号码不合法");
                   break;
                }
                obtaincode();
                break;
        }
    }
    /*修改密码*/
    private void changePwd() {
        MineAPI mineAPI = Http.getInstance().create(MineAPI.class);
        mineAPI.chagePwd(phone,newpwd,code).enqueue(new Callback<ChangePwdBean>() {
            @Override
            public void onResponse(Call<ChangePwdBean> call, Response<ChangePwdBean> response) {
                if (response.body().getReturnvalue().equals("true")){
                    finish();
                    toast("密码修改成功");
                }else {
                    toast(response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<ChangePwdBean> call, Throwable t) {

            }
        });
    }

    private boolean isNull() {
        phone = et_phone.getText().toString().trim();
        code = et_code.getText().toString().trim();
        newpwd = et_newpwd.getText().toString().trim();
        if (phone.length()<11){
            toast("旧手机号码不合法");
            return true;
        }
        if (code.length()<4){
            toast("验证码不合法");
            return true;
        }
        if (newpwd.length()<6){
            toast("新密码不合法");
            return true;
        }
        return false;
    }

    /*获取验证码*/
    private void obtaincode() {
        LoginAPI loginAPI = Http.getInstance().create(LoginAPI.class);
        loginAPI.getSmsCode(phone, Constants.OBTAINCODE_CHANGEPWD).enqueue(new Callback<GetSmsCodeBean>() {
            @Override
            public void onResponse(Call<GetSmsCodeBean> call, Response<GetSmsCodeBean> response) {
                if (response.body().getReturnvalue().equals("true")){
                    tv_obtaincode_changephone.setText("60s后重新获取");
                    tv_obtaincode_changephone.setClickable(false);
                    handler.sendEmptyMessageDelayed(0,60000);
                    Log.e("sms",response.body().getMsg());
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
}
