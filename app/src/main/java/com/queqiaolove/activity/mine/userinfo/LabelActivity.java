package com.queqiaolove.activity.mine.userinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.queqiaolove.QueQiaoLoveApp;
import com.queqiaolove.R;
import com.queqiaolove.adapter.mine.LabelEditGvAdapter;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.global.Constants;
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.MineAPI;
import com.queqiaolove.javabean.BaseBean;
import com.queqiaolove.javabean.mine.UserInfoLabelListbean;
import com.queqiaolove.util.SharedPrefUtil;

import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WD on 2016/10/14.
 */
public class LabelActivity extends BaseActivity implements View.OnClickListener {
    private static String LOVE = "love";
    private ImageView iv_back;
    private TextView tv_finish;
    private GridView gv_label_userinfo;
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

        loadlabellist();
        return ContentPage.RequestState.STATE_SUCCESS;
    }
    /*加载标签列表*/
    private void loadlabellist() {
        MineAPI mineAPI = Http.getInstance().create(MineAPI.class);
        mineAPI.userInfroLabelList().enqueue(new Callback<UserInfoLabelListbean>() {
            @Override
            public void onResponse(Call<UserInfoLabelListbean> call, Response<UserInfoLabelListbean> response) {
                UserInfoLabelListbean body = response.body();
                if (body.getReturnvalue().equals("true")) {
                    List<UserInfoLabelListbean.ListBean> list = body.getList();
                    gv_label_userinfo.setAdapter(new LabelEditGvAdapter(mActivity,list));
                } else {
                    toast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Call<UserInfoLabelListbean> call, Throwable t) {
                toast("网络数据异常");
            }
        });
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
                set = new HashSet<String>();
                set = SharedPrefUtil.getSet(mActivity, Constants.SP_LABELLIST,new HashSet<String>());
                changeMyLabel();
                break;
        }
    }
    /*修改我的标签*/
    private void changeMyLabel() {
        String labellist = "";
        for (String label :set
                ) {
            labellist = labellist+label+",";
        }
        userid = QueQiaoLoveApp.getUserId();
        MineAPI mineAPI = Http.getInstance().create(MineAPI.class);
        mineAPI.changeMyLabel(userid,labellist).enqueue(new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseBean body = response.body();
                if (body.getReturnvalue().equals("true")){
                    toast(body.getMsg());
                    finish();
                }else {
                    toast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {
                toast("网络数据异常");
            }
        });
    }
}
