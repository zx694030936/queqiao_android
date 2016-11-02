package com.queqiaolove.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.activity.login.ConstructionActivity;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;

/**
 * Created by WD on 2016/10/13.
 */
public class MyAccountMineActivity extends BaseActivity implements View.OnClickListener {

    private static String CODE = "code";
    private TextView tv_next;
    private ImageView tv_back;
    private View rl_queqiaobi_myaccount;
    private View rl_point_myaccount;
    private View rl_favorable_myaccount;
    private View rl_withdraw_myaccount;

    @Override
    protected void activityOnCreate(Bundle extras) {

    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_base_mine,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_myaccount_mine,null);
    }

    @Override
    protected void initTitle() {
        TextView tv_title = (TextView) mTitleView.findViewById(R.id.tv_title);
        tv_title.setText("我的账户");
    }

    @Override
    protected void initView() {
        tv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);
        tv_next = (TextView) mContentView.findViewById(R.id.tv_next);

        rl_queqiaobi_myaccount = mContentView.findViewById(R.id.rl_queqiaobi_myaccount);
        rl_point_myaccount = mContentView.findViewById(R.id.rl_point_myaccount);
        rl_favorable_myaccount = mContentView.findViewById(R.id.rl_favorable_myaccount);
        rl_withdraw_myaccount = mContentView.findViewById(R.id.rl_withdraw_myaccount);
    }

    @Override
    protected void initEvent() {
        tv_back.setOnClickListener(this);
        tv_next.setOnClickListener(this);

        rl_queqiaobi_myaccount.setOnClickListener(this);
        rl_point_myaccount.setOnClickListener(this);
        rl_favorable_myaccount.setOnClickListener(this);
        rl_withdraw_myaccount.setOnClickListener(this);

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
        intent.setClass(activity, MyAccountMineActivity.class);
        intent.putExtra(CODE, data);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_next:
                break;
            case R.id.iv_back:
                finish();
                break;

            case R.id.rl_queqiaobi_myaccount:
                ConstructionActivity.intent(mActivity,"1");
                break;
            case R.id.rl_point_myaccount:
                ConstructionActivity.intent(mActivity,"1");
                break;
            case R.id.rl_favorable_myaccount:
                ConstructionActivity.intent(mActivity,"1");
                break;
            case R.id.rl_withdraw_myaccount:
                ConstructionActivity.intent(mActivity,"1");
                break;
        }
    }

}
