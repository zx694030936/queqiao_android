package com.queqiaolove.activity.main.matchmaking;

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
 * Created by WD on 2016/10/17.
 */
public class ActivityDetailActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv_back;
    private static final String TYPE = "type";
    private String title;
    private TextView tv_next;

    @Override
    protected void activityOnCreate(Bundle extras) {
        title = extras.getString(TYPE);
    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_base_mine,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_activitydetail_matchmaking,null);
    }

    @Override
    protected void initTitle() {
        TextView tv_title = (TextView) mTitleView.findViewById(R.id.tv_title);
        tv_title.setText("相亲活动名");
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);
        tv_next = (TextView) mContentView.findViewById(R.id.tv_next);
    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);
        tv_next.setOnClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        return ContentPage.RequestState.STATE_SUCCESS;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_next://报名
                ConstructionActivity.intent(mActivity,"");
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    /**
     * 从外部跳转到本类的反复
     *
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, ActivityDetailActivity.class);
        intent.putExtra(TYPE, data);
        activity.startActivity(intent);
    }
}
