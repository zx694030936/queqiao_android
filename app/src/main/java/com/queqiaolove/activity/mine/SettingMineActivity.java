package com.queqiaolove.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.queqiaolove.R;
import com.queqiaolove.activity.login.LoginActivity;
import com.queqiaolove.activity.mine.setting.AboutUsActivity;
import com.queqiaolove.activity.mine.setting.ChangePwdActivity;
import com.queqiaolove.activity.mine.setting.InfromSettingActivity;
import com.queqiaolove.activity.mine.setting.UploadActivity;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.global.Constants;
import com.queqiaolove.util.CommonUtil;
import com.queqiaolove.util.SharedPrefUtil;

/**
 * Created by WD on 2016/10/11.
 */
public class SettingMineActivity extends BaseActivity implements View.OnClickListener {

    private static String REGISTER = "register";
    private TextView tv_next;
    private ImageView tv_back;
    private TextView tv_title;
    private View rl_inform_setting;//通知设置
    private View rl_upload_setting;//更新
    private View rl_changpwd_setting;//修改密码
    private View rl_aboutus_setting;// 关于我们
    private View rl_report_setting;//预报与投诉
    private TextView tv_servicetel;
    private String servicetel;

    @Override
    protected void initTitle() {
        tv_title = (TextView) mTitleView.findViewById(R.id.tv_title);
        tv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);
        tv_title.setText("设置");
    }

    @Override
    protected void activityOnCreate(Bundle extras) {

    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity,R.layout.title_base_mine,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity,R.layout.activity_setting_mine,null);
    }

    @Override
    protected void initView() {
        tv_next = (TextView) mContentView.findViewById(R.id.tv_next);

        tv_servicetel = (TextView) mContentView.findViewById(R.id.tv_servicetel);
        rl_changpwd_setting = mContentView.findViewById(R.id.rl_changpwd_setting);
        rl_inform_setting = mContentView.findViewById(R.id.rl_inform_setting);
        rl_upload_setting = mContentView.findViewById(R.id.rl_upload_setting);
        rl_aboutus_setting = mContentView.findViewById(R.id.rl_aboutus_setting);
        rl_report_setting = mContentView.findViewById(R.id.rl_report_setting);
    }
    @Override
    protected void initEvent() {
        tv_next.setOnClickListener(this);
        tv_back.setOnClickListener(this);

        rl_inform_setting.setOnClickListener(this);
        rl_changpwd_setting.setOnClickListener(this);
        rl_upload_setting.setOnClickListener(this);
        rl_aboutus_setting.setOnClickListener(this);
        rl_report_setting.setOnClickListener(this);
    }


    @Override
    protected ContentPage.RequestState onLoad() {
        return ContentPage.RequestState.STATE_SUCCESS;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_next://退出账号
                SharedPrefUtil.clear(mActivity);
                LoginActivity.intent(mActivity,"");
                finish();
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_inform_setting://通知设置
                InfromSettingActivity.intent(mActivity,"");
                break;
            case R.id.rl_changpwd_setting://修改密码
                ChangePwdActivity.intent(mActivity,"");
                break;
            case R.id.rl_upload_setting://版本更新
                UploadActivity.intent(mActivity,"");
                break;
            case R.id.rl_aboutus_setting://关于我们
                AboutUsActivity.intent(mActivity,"");
                break;
            case R.id.rl_report_setting://举报与投诉
                servicetel = tv_servicetel.getText().toString().trim();
                CommonUtil.callPhone(mActivity, servicetel);
                break;

        }
    }
    /**
     * 从外部跳转到本类的反复
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, SettingMineActivity.class);
        intent.putExtra(REGISTER, data);
        activity.startActivity(intent);
    }

    /*判断用户是否给予权限*/
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == Constants.MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                CommonUtil.callPhone(mActivity,servicetel);
            } else {
                // Permission Denied
                Toast.makeText(mActivity, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
