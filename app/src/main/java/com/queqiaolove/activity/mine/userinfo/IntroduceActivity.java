package com.queqiaolove.activity.mine.userinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.queqiaolove.QueQiaoLoveApp;
import com.queqiaolove.R;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.MineAPI;
import com.queqiaolove.javabean.mine.LoveDeclarationBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WD on 2016/10/14.
 */
public class IntroduceActivity extends BaseActivity implements View.OnClickListener {
    private static String LOVE = "love";
    private ImageView iv_back;
    private TextView tv_finish;
    private EditText et_introduce;
    private String introduce;

    @Override
    protected void activityOnCreate(Bundle extras) {

    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_base_userinfo,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_introduce_userinfo,null);
    }

    @Override
    protected void initTitle() {
        TextView tv_title = (TextView) mTitleView.findViewById(R.id.tv_title);
        tv_title.setText("自我介绍");
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);
        tv_finish = (TextView) mTitleView.findViewById(R.id.tv_finish);

        et_introduce = (EditText) mContentView.findViewById(R.id.et_introduce);

        userid = QueQiaoLoveApp.getUserId();
    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);
        tv_finish.setOnClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        return ContentPage.RequestState.STATE_SUCCESS;
    }
    /**
     * 从外部跳转到本类的反复
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, IntroduceActivity.class);
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
                introduce = et_introduce.getText().toString().trim();
                if (introduce.equals("")||introduce.length()==0){
                    toast("自我介绍不能为空");
                    break;
                }
                changeMyIntroduce();
                break;
        }
    }
/*修改自我介绍*/
    private void changeMyIntroduce() {
        MineAPI mineAPI = Http.getInstance().create(MineAPI.class);
        mineAPI.changeMyIntroduce(userid,introduce).enqueue(new Callback<LoveDeclarationBean>() {
            @Override
            public void onResponse(Call<LoveDeclarationBean> call, Response<LoveDeclarationBean> response) {
                LoveDeclarationBean body = response.body();
                if (body.getReturnvalue().equals("true")){
                    toast(body.getMsg());
                    finish();
                }else {
                    toast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Call<LoveDeclarationBean> call, Throwable t) {
                toast("网络数据异常");
            }
        });
    }
}
