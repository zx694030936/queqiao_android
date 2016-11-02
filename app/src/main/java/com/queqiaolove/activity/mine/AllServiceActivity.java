package com.queqiaolove.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.activity.mine.member.OpenMemberActivity;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;

/**
 * Created by WD on 2016/10/14.
 */
public class AllServiceActivity extends BaseActivity implements View.OnClickListener {
    private static String LOVE = "love";
    private ImageView iv_back;
    private TextView tv_finish;
    private GridView gv_label_userinfo;
    private LinearLayout ll_pear_openmember;
    private LinearLayout ll_coral_openmember;
    private LinearLayout ll_jade_openmember;
    private LinearLayout ll_diamond_openmember;
    private LinearLayout ll_crown_openmember;
    private LinearLayout ll_tourist_openmember;

    @Override
    protected void activityOnCreate(Bundle extras) {

    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_base_mine,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_mine_allservice,null);
    }

    @Override
    protected void initTitle() {
        TextView tv_title = (TextView) mTitleView.findViewById(R.id.tv_title);
        tv_title.setText("全部服务");
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);

        ll_pear_openmember = (LinearLayout) mContentView.findViewById(R.id.ll_pear_openmember);
        ll_coral_openmember = (LinearLayout) mContentView.findViewById(R.id.ll_coral_openmember);
        ll_jade_openmember = (LinearLayout) mContentView.findViewById(R.id.ll_jade_openmember);
        ll_diamond_openmember = (LinearLayout) mContentView.findViewById(R.id.ll_diamond_openmember);
        ll_crown_openmember = (LinearLayout) mContentView.findViewById(R.id.ll_crown_openmember);
        ll_tourist_openmember = (LinearLayout) mContentView.findViewById(R.id.ll_tourist_openmember);



    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);

        ll_pear_openmember.setOnClickListener(this);
        ll_coral_openmember.setOnClickListener(this);
        ll_jade_openmember.setOnClickListener(this);
        ll_diamond_openmember.setOnClickListener(this);
        ll_crown_openmember.setOnClickListener(this);
        ll_tourist_openmember.setOnClickListener(this);
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
        intent.setClass(activity, AllServiceActivity.class);
        intent.putExtra(LOVE, data);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_pear_openmember:
                OpenMemberActivity.intent(mActivity,"珍珠");
                break;
            case R.id.ll_coral_openmember:
                OpenMemberActivity.intent(mActivity,"珊瑚");
                break;
            case R.id.ll_jade_openmember:
                OpenMemberActivity.intent(mActivity,"翡翠");
                break;
            case R.id.ll_diamond_openmember:
                OpenMemberActivity.intent(mActivity,"钻石");
                break;
            case R.id.ll_crown_openmember:
                OpenMemberActivity.intent(mActivity,"皇冠");
                break;
            /*case R.id.ll_tourist_openmember:
                OpenMemberActivity.intent(mActivity,"游客");
                break;*/

        }
    }
}
