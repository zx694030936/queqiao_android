package com.queqiaolove.activity.mine.userinfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.queqiaolove.QueQiaoLoveApp;
import com.queqiaolove.R;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.global.Constants;
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.MineAPI;
import com.queqiaolove.http.api.SysAPI;
import com.queqiaolove.javabean.BaseBean;
import com.queqiaolove.javabean.sys.AttributeListBean;
import com.queqiaolove.util.CommonUtil;
import com.queqiaolove.util.SharedPrefUtil;
import com.queqiaolove.widget.WheelView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WD on 2016/10/14.
 */
public class EducationAndWorkActivity extends BaseActivity implements View.OnClickListener {
    private static String LOVE = "love";
    private ImageView iv_back;
    private TextView tv_finish;
    private RelativeLayout rl_school_eduandwork;//毕业院校
    private RelativeLayout rl_major_eduandwork;//专业
    private RelativeLayout rl_job_eduandwork;//职业
    private RelativeLayout rl_companybusiness_eduandwork;//公司行业
    private RelativeLayout rl_companynature_eduandwork;//公司性质
    private RelativeLayout rl_language_eduandwork;//掌握语言
    private List<AttributeListBean.ListBean> AttributeList;

    private TextView tv_companybussiness_eduandwork;//公司行业
    private TextView tv_companynature_eduandwork;//公司性质
    private int index;
    private String acode_companyBusiness;
    private String acode_companyNature;
    private AttributeListBean.ListBean attributeData;
    private String aname;
    private TextView tv_school_eduandwork;//毕业学校
    private String school;
    private String major;
    private String job;
    private TextView tv_major_eduandwork;
    private TextView tv_job_eduandwork;
    private String companybusiness;
    private String companynature;
    private String languageCodelist = "";
    private String languageStrlist = "";
    private TextView tv_language_eduandwork;

    @Override
    protected void activityOnCreate(Bundle extras) {

    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_base_userinfo,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_userinfo_educationandwork,null);
    }

    @Override
    protected void initTitle() {
        TextView tv_title = (TextView) mTitleView.findViewById(R.id.tv_title);
        tv_title.setText("教育及工作");
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);
        tv_finish = (TextView) mTitleView.findViewById(R.id.tv_finish);

        rl_school_eduandwork = (RelativeLayout) mContentView.findViewById(R.id.rl_school_eduandwork);
        rl_major_eduandwork = (RelativeLayout) mContentView.findViewById(R.id.rl_major_eduandwork);
        rl_job_eduandwork = (RelativeLayout) mContentView.findViewById(R.id.rl_job_eduandwork);
        rl_companybusiness_eduandwork = (RelativeLayout) mContentView.findViewById(R.id.rl_companybusiness_eduandwork);
        rl_companynature_eduandwork = (RelativeLayout) mContentView.findViewById(R.id.rl_companynature_eduandwork);
        rl_language_eduandwork = (RelativeLayout) mContentView.findViewById(R.id.rl_language_eduandwork);

        tv_companybussiness_eduandwork = (TextView) mContentView.findViewById(R.id.tv_companybussiness_eduandwork);
        tv_companynature_eduandwork = (TextView) mContentView.findViewById(R.id.tv_companynature_eduandwork);

        tv_school_eduandwork = (TextView) mContentView.findViewById(R.id.tv_school_eduandwork);
        tv_major_eduandwork = (TextView) mContentView.findViewById(R.id.tv_major_eduandwork);
        tv_job_eduandwork = (TextView) mContentView.findViewById(R.id.tv_job_eduandwork);
        tv_language_eduandwork = (TextView) mContentView.findViewById(R.id.tv_language_eduandwork);


    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);
        tv_finish.setOnClickListener(this);

        rl_school_eduandwork.setOnClickListener(this);
        rl_major_eduandwork.setOnClickListener(this);
        rl_job_eduandwork.setOnClickListener(this);
        rl_companybusiness_eduandwork.setOnClickListener(this);
        rl_companynature_eduandwork.setOnClickListener(this);
        rl_language_eduandwork.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        acode_companyBusiness = SharedPrefUtil.getString(mActivity,Constants.SP_COMPANYBUSINESS_CODE,"");
        acode_companyNature = SharedPrefUtil.getString(mActivity,Constants.SP_COMPANYNATURE_CODE,"");
        companybusiness=SharedPrefUtil.getString(mActivity,Constants.SP_COMPANYBUSINESS,"暂无");
        companynature=SharedPrefUtil.getString(mActivity,Constants.SP_COMPANYNATURE,"暂无");
        school=SharedPrefUtil.getString(mActivity,Constants.SP_SCHOOL,"暂无");
        major=SharedPrefUtil.getString(mActivity,Constants.SP_MAJOR,"暂无");
        job=SharedPrefUtil.getString(mActivity,Constants.SP_JOB,"暂无");

        tv_companybussiness_eduandwork.setText(companybusiness);
        tv_companynature_eduandwork.setText(companynature);
        tv_school_eduandwork.setText(school);
        tv_major_eduandwork.setText(major);
        tv_job_eduandwork.setText(job);

        languageCodelist = "";
        languageStrlist = "";
        HashSet<String> languageCodeSet = new HashSet<String>();
        languageCodeSet = SharedPrefUtil.getSet(mActivity, Constants.SP_LANGUAGECODELIST,new HashSet<String>());
        for (String languagecode : languageCodeSet
                ) {
            languageCodelist = languageCodelist +languagecode+",";
        }
        HashSet<String> languageStrSet = new HashSet<String>();
        languageStrSet = SharedPrefUtil.getSet(mActivity, Constants.SP_LANGUAGELIST,new HashSet<String>());
        for (String language : languageStrSet
                ) {
            languageStrlist = languageStrlist +language+",";
        }
        if (languageStrlist.length()>0) {
            languageStrlist = languageStrlist.substring(0, languageStrlist.length() - 1);
        }
        tv_language_eduandwork.setText(languageStrlist.trim().equals("")?"暂无":languageStrlist);
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
        intent.setClass(activity, EducationAndWorkActivity.class);
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
                school = tv_school_eduandwork.getText().toString();
                major = tv_major_eduandwork.getText().toString();
                job=tv_job_eduandwork.getText().toString();

                changeEduAndWork();
                break;
            case R.id.rl_school_eduandwork://
                EditContentActivity.intent(mActivity,"毕业院校");
                break;
            case R.id.rl_major_eduandwork://
                EditContentActivity.intent(mActivity,"专业");
                break;
            case R.id.rl_job_eduandwork://
                EditContentActivity.intent(mActivity,"职业");
                break;
            case R.id.rl_companybusiness_eduandwork://
                //EditContentActivity.intent(mActivity,"公司行业");
                showAttributeList(Constants.ATTRIBUTE_COMPANYBUSSINESS,"公司行业");
                break;
            case R.id.rl_companynature_eduandwork://
                //EditContentActivity.intent(mActivity,"公司性质");
                showAttributeList(Constants.ATTRIBUTE_COMPANYNATURE,"公司性质");
                break;
            case R.id.rl_language_eduandwork://
                //EditContentActivity.intent(mActivity,"掌握语言");
                LanguageListActivity.intent(mActivity,"");
                break;
        }
    }
    /*更改教育及工作*/
    private void changeEduAndWork() {
        userid = QueQiaoLoveApp.getUserId();
        MineAPI mineAPI = Http.getInstance().create(MineAPI.class);
        mineAPI.changeEduAndWork(userid,school,major,job,
                acode_companyBusiness,acode_companyNature, languageCodelist).enqueue(new Callback<BaseBean>() {
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

    /*公司行业列表*/
    private void showAttributeList(int atcode, final String title) {
        SysAPI sysAPI = Http.getInstance().create(SysAPI.class);
        sysAPI.attributelist(atcode).enqueue(new Callback<AttributeListBean>() {
            @Override
            public void onResponse(Call<AttributeListBean> call, Response<AttributeListBean> response) {
                AttributeListBean body = response.body();
                if (body.getReturnvalue().equals("true")){
                    AttributeList = body.getList();
                    ArrayList<String> list = new ArrayList<String>();
                    for (AttributeListBean.ListBean data: AttributeList
                    ){
                        list.add(data.getAname());
                    }
                    showWheelView(title,list);
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

    private void showWheelView(final String title , List<String> list) {
        View outerView = View.inflate(mActivity, R.layout.wheel_view, null);
        WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
        wv.setOffset(2);
        wv.setItems(list);
        wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                //Log.d(TAG, "[Dialog]selectedIndex: " + selectedIndex + ", item: " + item);
                //toast(item);
                /*index = selectedIndex;
                location = item;*/
                index = selectedIndex;

            }
        });
        wv.setSeletion(0);
        //自定义标题
        LinearLayout ll = new LinearLayout(mActivity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, CommonUtil.dip2px(10), 0, CommonUtil.dip2px(10));
        ll.setLayoutParams(params);
        ll.setGravity(Gravity.CENTER);
        TextView tv = new TextView(mActivity);
        tv.setText(title);
        tv.setTextColor(CommonUtil.getColor(R.color.purple_queqiao));
        tv.setTextSize(CommonUtil.dip2px(10));
        ll.addView(tv);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setCustomTitle(ll)
                .setView(outerView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //toast(proviceIndex);
                        if (index!=0){
                            index -=2;
                        }
                        attributeData = AttributeList.get(index);
                        aname = attributeData.getAname();
                        switch (title) {
                            case "公司行业":
                                acode_companyBusiness = attributeData.getAcode();
                                tv_companybussiness_eduandwork.setText(aname);
                                SharedPrefUtil.putString(mActivity,Constants.SP_COMPANYBUSINESS,aname);
                                SharedPrefUtil.putString(mActivity,Constants.SP_COMPANYBUSINESS_CODE,acode_companyBusiness);
                                index = 0;
                                break;
                            case "公司性质":
                                acode_companyNature = attributeData.getAcode();
                                tv_companynature_eduandwork.setText(aname);
                                SharedPrefUtil.putString(mActivity,Constants.SP_COMPANYNATURE,aname);
                                SharedPrefUtil.putString(mActivity,Constants.SP_COMPANYNATURE_CODE,acode_companyNature);
                                index = 0;
                                break;
                        }
                    }
                })
                .show();
        CommonUtil.dialogTitleLineColor(alertDialog, R.color.purple_queqiao);
    }
}
