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
import com.queqiaolove.javabean.mine.ChangeContactWayBean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WD on 2016/10/14.
 */
public class ContactWayActivity extends BaseActivity implements View.OnClickListener {
    private static String LOVE = "love";
    private ImageView iv_back;
    private TextView tv_finish;
    private EditText et_weixin_contactway;
    private EditText et_qq_contactway;
    private String weixin;
    private String qq;
    private String[] mData;

    @Override
    protected void activityOnCreate(Bundle extras) {
        mData = extras.getStringArray(LOVE);
    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_base_userinfo,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_userinfo_contactwayl,null);
    }

    @Override
    protected void initTitle() {
        TextView tv_title = (TextView) mTitleView.findViewById(R.id.tv_title);
        tv_finish = (TextView) mTitleView.findViewById(R.id.tv_finish);
        tv_title.setText("联系方式");
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);

        et_weixin_contactway = (EditText) mContentView.findViewById(R.id.et_weixin_contactway);
        et_qq_contactway = (EditText) mContentView.findViewById(R.id.et_qq_contactway);

        if (mData[0].trim().equals("")||mData[0].equals("暂无")){
            et_weixin_contactway.setHint("暂无");
        }else {
            et_weixin_contactway.setText(mData[0].trim());
        }
        if (mData[1].trim().equals("")||mData[1].equals("暂无")){
            et_qq_contactway.setHint("暂无");
        }else {
            et_qq_contactway.setText(mData[1].trim());
        }

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
    public static void intent(Activity activity, String[] data) {
        Intent intent = new Intent();
        intent.setClass(activity, ContactWayActivity.class);
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
                weixin = et_weixin_contactway.getText().toString().trim();
                qq = et_qq_contactway.getText().toString().trim();
                if (isNull()){
                    break;
                }
                changeContactWay();

                break;
        }
    }

    private boolean isNull() {
        if (weixin.length()==0){
            toast("微信号不能为空");
            return true;
        }
        String regEx = "[\u4e00-\u9fa5]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(weixin);
        if (matcher.find()){
            toast("微信号不能包含中文");
            return true;
        }
        if (qq.length()==0){
            toast("QQ号不能为空");
            return true;
        }

        try {
            qq= Integer.parseInt(qq)+"";
        } catch (NumberFormatException e) {
            e.printStackTrace();
            toast("QQ号包含非法字符");
            return true;
        }
        return false;
    }

    /*更改联系方式*/
    private void changeContactWay() {
        MineAPI mineAPI = Http.getInstance().create(MineAPI.class);
        mineAPI.changeContactWay(userid,weixin,qq).enqueue(new Callback<ChangeContactWayBean>() {
            @Override
            public void onResponse(Call<ChangeContactWayBean> call, Response<ChangeContactWayBean> response) {
                ChangeContactWayBean body = response.body();
                if (body.getReturnvalue().equals("true")){
                    toast(body.getMsg());
                    finish();
                }else {
                    toast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Call<ChangeContactWayBean> call, Throwable t) {
                toast("网络数据异常");
            }
        });
    }
}
