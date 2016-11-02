package com.queqiaolove.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.jingchen.pulltorefresh.pullableview.PullableGridView;
import com.queqiaolove.R;
import com.queqiaolove.activity.livevideo.LiveVideoActivity;
import com.queqiaolove.adapter.mine.MyLiveVideoGvAdapter;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;

/**
 * Created by WD on 2016/10/13.
 */
public class LiveVideoMineActivity extends BaseActivity implements View.OnClickListener, PullToRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    private static String CODE = "code";
    private ImageView tv_back;
    private PullableGridView gv_livevideo;
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
        return View.inflate(mActivity, R.layout.activity_livevideo_mine,null);
    }

    @Override
    protected void initTitle() {
        TextView tv_title = (TextView) mTitleView.findViewById(R.id.tv_title);
        tv_title.setText("我的点播");
    }

    @Override
    protected void initView() {
        tv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);

        gv_livevideo = (PullableGridView) mContentView.findViewById(R.id.gv_pulltofresh);
        //头部间隔条

        /*可刷新布局*/
        refresh_view = (PullToRefreshLayout) mContentView.findViewById(R.id.refresh_view);
        refresh_view.setOnRefreshListener(this);

    }

    @Override
    protected void initEvent() {
        tv_back.setOnClickListener(this);

        gv_livevideo.setOnItemClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        gv_livevideo.setAdapter(new MyLiveVideoGvAdapter(mActivity));
        return ContentPage.RequestState.STATE_SUCCESS;
    }
    /**
     * 从外部跳转到本类的反复
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, LiveVideoMineActivity.class);
        intent.putExtra(CODE, data);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //ConstructionActivity.intent(mActivity,"");
        LiveVideoActivity.intent(mActivity,"");
    }
}
