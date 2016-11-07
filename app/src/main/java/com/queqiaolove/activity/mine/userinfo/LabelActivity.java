package com.queqiaolove.activity.mine.userinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.adapter.mine.LabelUserInfoGvAdapter;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;

/**
 * Created by WD on 2016/10/14.
 */
public class LabelActivity extends BaseActivity implements View.OnClickListener {
    private static String LOVE = "love";
    private ImageView iv_back;
    private TextView tv_finish;
    private GridView gv_label_userinfo;

    @Override
    protected void activityOnCreate(Bundle extras) {

    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_base_userinfo,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_userinfo_label,null);
    }

    @Override
    protected void initTitle() {
        TextView tv_title = (TextView) mTitleView.findViewById(R.id.tv_title);
        tv_title.setText("编辑标签");
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);
        tv_finish = (TextView) mTitleView.findViewById(R.id.tv_finish);

        gv_label_userinfo = (GridView) mContentView.findViewById(R.id.gv_label_userinfo);


    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);
        tv_finish.setOnClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        gv_label_userinfo.setAdapter(new LabelUserInfoGvAdapter(mActivity));
        return ContentPage.RequestState.STATE_SUCCESS;
    }
    /**
     * 从外部跳转到本类的反复
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, LabelActivity.class);
        intent.putExtra(LOVE, data);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_finish:
                finish();
                break;
        }
    }
}
