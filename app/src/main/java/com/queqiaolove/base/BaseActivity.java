package com.queqiaolove.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.queqiaolove.R;

import butterknife.ButterKnife;

/**
 * Created by WD on 2016/10/7.
 */
public abstract class BaseActivity extends Activity{
    protected View mContentView;
    protected Activity mActivity;
    protected LinearLayout mTitleView;//标题布局
    protected LinearLayout mContent;//内容布局
    private View mView;
    protected int userid = -1;
    protected int pageno = 1;
    protected int pagesize = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        activityOnCreate(getIntent().getExtras());
        mView = View.inflate(mActivity, R.layout.activity_base,null);
        mTitleView = (LinearLayout) mView.findViewById(R.id.ll_title_base);
        mContent = (LinearLayout) mView.findViewById(R.id.ll_content_base);
        if (initTitleView()!=null){
            mTitleView.addView(initTitleView());
        }
        mContentView = initContentLayout();
        initTitle();
        initView();
        initEvent();


        ContentPage contentPage = new ContentPage(this) {

            @Override
            protected View onCreateSuccessView() {
                return BaseActivity.this.onCreateSuccessView();
            }

            @Override
            protected RequestState onLoad() {
                return BaseActivity.this.onLoad();
            }

        };
        mContent.addView(contentPage);
        setContentView(mView);
        ButterKnife.inject(this);
    }


    /**
     * 获取activity创建时传入的值
     * @param extras
     */
    protected abstract void activityOnCreate(Bundle extras);

    protected abstract View initTitleView();

    protected abstract View initContentLayout();

    protected abstract void initTitle();

    protected abstract void initView();

    protected abstract void initEvent();
    /**
     * 具体请求网络的操作由子Fragment实现
     * @return
     */
    protected abstract ContentPage.RequestState onLoad();
    /**
     * 成功对应的界面由具体子Fragment实现
     * @return
     */
    public View onCreateSuccessView(){return mContentView;}

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * toast工具类
     */
    public void toast(String str){
        Toast.makeText(mActivity,str, Toast.LENGTH_SHORT).show();
    }
}
