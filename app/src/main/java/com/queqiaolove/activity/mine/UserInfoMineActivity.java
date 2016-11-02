package com.queqiaolove.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.activity.mine.userinfo.ContactWayActivity;
import com.queqiaolove.activity.mine.userinfo.EducationAndWorkActivity;
import com.queqiaolove.activity.mine.userinfo.InfoDetailActivity;
import com.queqiaolove.activity.mine.userinfo.IntroduceActivity;
import com.queqiaolove.activity.mine.userinfo.LabelActivity;
import com.queqiaolove.activity.mine.userinfo.LoveManifestoActivity;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;

/**
 * Created by WD on 2016/10/13.
 */
public class UserInfoMineActivity extends BaseActivity implements View.OnClickListener {

    private static String CODE = "code";
    private ImageView iv_back;
    private TextView tv_edit_label;//标签
    private TextView tv_edit_lovemanifesto;//爱情宣言
    private TextView tv_edit_introduce;//自我介绍
    private TextView tv_edit_infodetail;//详细资料
    private TextView tv_edit_contactway;//联系方式
    private TextView tv_edit_education;//教育及工作

    @Override
    protected void activityOnCreate(Bundle extras) {

    }

    @Override
    protected View initTitleView() {
        return null;
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_userinfo_mine,null);
    }

    @Override
    protected void initTitle() {
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mContentView.findViewById(R.id.iv_back);

        tv_edit_label = (TextView) mContentView.findViewById(R.id.tv_edit_label);
        tv_edit_lovemanifesto = (TextView) mContentView.findViewById(R.id.tv_edit_lovemanifesto);
        tv_edit_introduce = (TextView) mContentView.findViewById(R.id.tv_edit_introduce);
        tv_edit_infodetail = (TextView) mContentView.findViewById(R.id.tv_edit_infodetail);
        tv_edit_contactway = (TextView) mContentView.findViewById(R.id.tv_edit_contactway);
        tv_edit_education = (TextView) mContentView.findViewById(R.id.tv_edit_education);


    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);

        tv_edit_label.setOnClickListener(this);
        tv_edit_lovemanifesto.setOnClickListener(this);
        tv_edit_introduce.setOnClickListener(this);
        tv_edit_infodetail.setOnClickListener(this);
        tv_edit_contactway.setOnClickListener(this);
        tv_edit_education.setOnClickListener(this);

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
        intent.setClass(activity, UserInfoMineActivity.class);
        intent.putExtra(CODE, data);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back://返回
                finish();
                break;
            case R.id.tv_edit_label://标签
                LabelActivity.intent(mActivity,"1");
                break;
            case R.id.tv_edit_lovemanifesto://爱情宣言
                LoveManifestoActivity.intent(mActivity,"1");
                break;
            case R.id.tv_edit_introduce://自我介绍
                IntroduceActivity.intent(mActivity,"1");
                break;
            case R.id.tv_edit_infodetail://详细资料
                InfoDetailActivity.intent(mActivity,"1");
                break;
            case R.id.tv_edit_contactway://联系方式
                ContactWayActivity.intent(mActivity,"1");
                break;
            case R.id.tv_edit_education://教育及工作
                EducationAndWorkActivity.intent(mActivity,"1");
                break;
        }
    }

}
