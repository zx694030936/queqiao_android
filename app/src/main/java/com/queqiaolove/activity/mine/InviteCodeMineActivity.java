package com.queqiaolove.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.jingchen.pulltorefresh.pullableview.PullableListView;
import com.queqiaolove.R;
import com.queqiaolove.adapter.mine.InvitedFriendLvAdapter;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.widget.dialog.SendInviteCodeDialog;

/**
 * Created by WD on 2016/10/13.
 */
public class InviteCodeMineActivity extends BaseActivity implements View.OnClickListener, PullToRefreshLayout.OnRefreshListener {

    private static String CODE = "code";
    private TextView tv_next;
    private ImageView tv_back;
    private PullableListView lv_friend;
    private View headerview;
    protected PullToRefreshLayout refresh_view;//可刷新的布局

    @Override
    protected void activityOnCreate(Bundle extras) {

    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_base_mine,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_invitecode_mine,null);
    }

    @Override
    protected void initTitle() {
        TextView tv_title = (TextView) mTitleView.findViewById(R.id.tv_title);
        tv_title.setText("我的邀请码");
    }

    @Override
    protected void initView() {
        tv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);
        lv_friend = (PullableListView) mContentView.findViewById(R.id.lv_pulltofresh);
        headerview = View.inflate(mActivity, R.layout.headerview_friend_invitecode, null);
        lv_friend.addHeaderView(headerview);

        /*可刷新布局*/
        refresh_view = (PullToRefreshLayout) mContentView.findViewById(R.id.refresh_view);
        refresh_view.setOnRefreshListener(this);

        tv_next = (TextView) headerview.findViewById(R.id.tv_next);
    }

    @Override
    protected void initEvent() {
        tv_back.setOnClickListener(this);
        tv_next.setOnClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        lv_friend.setAdapter(new InvitedFriendLvAdapter(mActivity));
        return ContentPage.RequestState.STATE_SUCCESS;
    }
    /**
     * 从外部跳转到本类的反复
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, InviteCodeMineActivity.class);
        intent.putExtra(CODE, data);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_next:
                SendInviteCodeDialog sendInviteCodeDialog = new SendInviteCodeDialog(mActivity);
                sendInviteCodeDialog.show();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
    }
}
