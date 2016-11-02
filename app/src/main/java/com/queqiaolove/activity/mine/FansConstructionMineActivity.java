package com.queqiaolove.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.queqiaolove.R;
import com.queqiaolove.adapter.mine.FansConstructionLvAdapter;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;

/**
 * Created by WD on 2016/10/13.
 */
public class FansConstructionMineActivity extends BaseActivity implements View.OnClickListener, PullToRefreshLayout.OnRefreshListener {
    private static String FANS = "fans";
    private ImageView iv_back;
    private TextView tv_title;
    private ListView lv_fans_mine;
    private View headerview;
    private PullToRefreshLayout refresh_view;

    @Override
    protected void activityOnCreate(Bundle extras) {

    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_base_mine,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_fansconstruction_mine,null);
    }

    @Override
    protected void initTitle() {
        tv_title = (TextView) mTitleView.findViewById(R.id.tv_title);
        iv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);
        tv_title.setText("粉丝贡献");
    }

    @Override
    protected void initView() {
        lv_fans_mine = (ListView) mContentView.findViewById(R.id.lv_pulltofresh);
        headerview = View.inflate(mActivity, R.layout.headerview_fanscontruction_mine, null);
        lv_fans_mine.addHeaderView(headerview);
        /*可刷新布局*/
        refresh_view = (PullToRefreshLayout) mContentView.findViewById(R.id.refresh_view);
        refresh_view.setOnRefreshListener(this);
    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        lv_fans_mine.setAdapter(new FansConstructionLvAdapter(mActivity));
        return ContentPage.RequestState.STATE_SUCCESS;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
    /**
     * 从外部跳转到本类的反复
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, FansConstructionMineActivity.class);
        intent.putExtra(FANS, data);
        activity.startActivity(intent);
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
