package com.queqiaolove.activity.mine.userinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.activity.mine.userinfo.contactway.ChangePhoneActivity;
import com.queqiaolove.activity.mine.userinfo.contactway.ChangeQQActivity;
import com.queqiaolove.activity.mine.userinfo.contactway.ChangeWeiChartActivity;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;

/**
 * Created by WD on 2016/10/14.
 */
public class ContactWayActivity extends BaseActivity implements View.OnClickListener {
    private static String LOVE = "love";
    private ImageView iv_back;
    private RelativeLayout rl_phone_contactway;//电话
    private RelativeLayout rl_weichart_contactway;
    private RelativeLayout rl_qq_contactway;

    @Override
    protected void activityOnCreate(Bundle extras) {

    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_base_mine,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_userinfo_contactwayl,null);
    }

    @Override
    protected void initTitle() {
        TextView tv_title = (TextView) mTitleView.findViewById(R.id.tv_title);
        tv_title.setText("联系方式");
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);

        rl_phone_contactway = (RelativeLayout) mContentView.findViewById(R.id.rl_phone_contactway);
        rl_weichart_contactway = (RelativeLayout) mContentView.findViewById(R.id.rl_weichart_contactway);
        rl_qq_contactway = (RelativeLayout) mContentView.findViewById(R.id.rl_qq_contactway);


    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);
        rl_phone_contactway.setOnClickListener(this);
        rl_weichart_contactway.setOnClickListener(this);
        rl_qq_contactway.setOnClickListener(this);
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
        intent.setClass(activity, ContactWayActivity.class);
        intent.putExtra(LOVE, data);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_phone_contactway://手机号
                ChangePhoneActivity.intent(mActivity,"1");
                break;
            case R.id.rl_weichart_contactway:
                ChangeWeiChartActivity.intent(mActivity,"1");
                break;
            case R.id.rl_qq_contactway:
                ChangeQQActivity.intent(mActivity,"1");
                break;
        }
    }
}
