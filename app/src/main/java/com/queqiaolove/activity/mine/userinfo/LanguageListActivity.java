package com.queqiaolove.activity.mine.userinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.adapter.mine.LanguageEditGvAdapter;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.global.Constants;
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.SysAPI;
import com.queqiaolove.javabean.sys.AttributeListBean;

import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WD on 2016/10/14.
 */
public class LanguageListActivity extends BaseActivity implements View.OnClickListener {
    private static String LOVE = "love";
    private ImageView iv_back;
    private TextView tv_finish;
    private GridView gv_language_userinfo;
    private HashSet<String> set;

    @Override
    protected void activityOnCreate(Bundle extras) {

    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_base_userinfo,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_userinfo_language,null);
    }

    @Override
    protected void initTitle() {
        TextView tv_title = (TextView) mTitleView.findViewById(R.id.tv_title);
        tv_title.setText("掌握语言");
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);
        tv_finish = (TextView) mTitleView.findViewById(R.id.tv_finish);

        gv_language_userinfo = (GridView) mContentView.findViewById(R.id.gv_language_userinfo);


    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);
        tv_finish.setOnClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {

        loadAttributeList();
        return ContentPage.RequestState.STATE_SUCCESS;
    }

    @Override
    public View onCreateSuccessView() {

        return super.onCreateSuccessView();
    }

    /**
     * 从外部跳转到本类的反复
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, LanguageListActivity.class);
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
    /*语言列表*/
    private void loadAttributeList() {
        SysAPI sysAPI = Http.getInstance().create(SysAPI.class);
        sysAPI.attributelist(Constants.ATTRIBUTE_LAGUAGE).enqueue(new Callback<AttributeListBean>() {
            @Override
            public void onResponse(Call<AttributeListBean> call, Response<AttributeListBean> response) {
                AttributeListBean body = response.body();
                if (body.getReturnvalue().equals("true")){
                    List<AttributeListBean.ListBean> list = body.getList();
                    gv_language_userinfo.setAdapter(new LanguageEditGvAdapter(mActivity,list));
                }else {
                    toast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Call<AttributeListBean> call, Throwable t) {
                toast("网络数据异常");
            }
        });
    }
}
