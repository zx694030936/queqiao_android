package com.queqiaolove.activity.mine.userinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;

/**
 * Created by WD on 2016/10/14.
 */
public class EducationAndWorkActivity extends BaseActivity implements View.OnClickListener {
    private static String LOVE = "love";
    private ImageView iv_back;
    private RelativeLayout rl_school_eduandwork;//毕业院校
    private RelativeLayout rl_major_eduandwork;//专业
    private RelativeLayout rl_job_eduandwork;//职业
    private RelativeLayout rl_companybusiness_eduandwork;//公司行业
    private RelativeLayout rl_companynature_eduandwork;//公司性质
    private RelativeLayout rl_language_eduandwork;//掌握语言

    @Override
    protected void activityOnCreate(Bundle extras) {

    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_base_mine,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_userinfo_educationandwork,null);
    }

    @Override
    protected void initTitle() {
        TextView tv_title = (TextView) mTitleView.findViewById(R.id.tv_title);
        tv_title.setText("联系方式");
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);

        rl_school_eduandwork = (RelativeLayout) mContentView.findViewById(R.id.rl_school_eduandwork);
        rl_major_eduandwork = (RelativeLayout) mContentView.findViewById(R.id.rl_major_eduandwork);
        rl_job_eduandwork = (RelativeLayout) mContentView.findViewById(R.id.rl_job_eduandwork);
        rl_companybusiness_eduandwork = (RelativeLayout) mContentView.findViewById(R.id.rl_companybusiness_eduandwork);
        rl_companynature_eduandwork = (RelativeLayout) mContentView.findViewById(R.id.rl_companynature_eduandwork);
        rl_language_eduandwork = (RelativeLayout) mContentView.findViewById(R.id.rl_language_eduandwork);


    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);

        rl_school_eduandwork.setOnClickListener(this);
        rl_major_eduandwork.setOnClickListener(this);
        rl_job_eduandwork.setOnClickListener(this);
        rl_companybusiness_eduandwork.setOnClickListener(this);
        rl_companynature_eduandwork.setOnClickListener(this);
        rl_language_eduandwork.setOnClickListener(this);
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
        intent.setClass(activity, EducationAndWorkActivity.class);
        intent.putExtra(LOVE, data);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_school_eduandwork://
                EditContentActivity.intent(mActivity,"毕业院校");
                break;
            case R.id.rl_major_eduandwork://
                EditContentActivity.intent(mActivity,"专业");
                break;
            case R.id.rl_job_eduandwork://
                EditContentActivity.intent(mActivity,"职业");
                break;
            case R.id.rl_companybusiness_eduandwork://
                EditContentActivity.intent(mActivity,"公司行业");
                break;
            case R.id.rl_companynature_eduandwork://
                EditContentActivity.intent(mActivity,"公司性质");
                break;
            case R.id.rl_language_eduandwork://
                EditContentActivity.intent(mActivity,"掌握语言");
                break;
        }
    }
}
