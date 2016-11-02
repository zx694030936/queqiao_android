package com.queqiaolove.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.adapter.live.horizontal.UserInfoPicGvAdapter;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.widget.MyGridView;

/**
 * Created by WD on 2016/10/14.
 */
public class UserInfoActivity extends BaseActivity implements View.OnClickListener {
    private static String LOVE = "love";
    private ImageView iv_back;
    private TextView tv_next;
    private MyGridView gv_pic_userinfo;

    @Override
    protected void activityOnCreate(Bundle extras) {

    }

    @Override
    protected View initTitleView() {
        return null;
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_userinfo,null);
    }

    @Override
    protected void initTitle() {
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mContentView.findViewById(R.id.iv_back);
        gv_pic_userinfo = (MyGridView) mContentView.findViewById(R.id.gv_pic_userinfo);


    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);

    }

    @Override
    protected ContentPage.RequestState onLoad() {
        gv_pic_userinfo.setAdapter(new UserInfoPicGvAdapter(mActivity));
        return ContentPage.RequestState.STATE_SUCCESS;
    }
    /**
     * 从外部跳转到本类的反复
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, UserInfoActivity.class);
        intent.putExtra(LOVE, data);
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
}
