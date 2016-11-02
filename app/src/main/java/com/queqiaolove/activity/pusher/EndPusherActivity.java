package com.queqiaolove.activity.pusher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;

/**
 * Created by WD on 2016/10/8.
 */
public class EndPusherActivity extends BaseActivity implements View.OnClickListener {
    private static String END_PUSHER = "end";
    private TextView tv_endpusher;

    @Override
    protected void initTitle() {

    }

    @Override
    protected void activityOnCreate(Bundle extras) {

    }

    @Override
    protected View initTitleView() {
        return null;
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_endpusher,null);
    }

    @Override
    protected void initView() {
        tv_endpusher = (TextView) mContentView.findViewById(R.id.tv_endpusher);
    }

    @Override
    protected void initEvent() {
        tv_endpusher.setOnClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        return ContentPage.RequestState.STATE_SUCCESS;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_endpusher:// 返回按钮
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
        intent.setClass(activity, EndPusherActivity.class);
        intent.putExtra(END_PUSHER, data);
        activity.startActivity(intent);
    }
}
