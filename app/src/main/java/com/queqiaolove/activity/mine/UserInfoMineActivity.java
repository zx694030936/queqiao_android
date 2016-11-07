package com.queqiaolove.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.queqiaolove.QueQiaoLoveApp;
import com.queqiaolove.R;
import com.queqiaolove.activity.mine.userinfo.ContactWayActivity;
import com.queqiaolove.activity.mine.userinfo.EducationAndWorkActivity;
import com.queqiaolove.activity.mine.userinfo.InfoDetailActivity;
import com.queqiaolove.activity.mine.userinfo.IntroduceActivity;
import com.queqiaolove.activity.mine.userinfo.LabelActivity;
import com.queqiaolove.activity.mine.userinfo.LoveManifestoActivity;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.MineAPI;
import com.queqiaolove.javabean.mine.UserInfroDetailBean;
import com.queqiaolove.util.CommonUtils;
import com.queqiaolove.widget.CircleImageView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WD on 2016/10/13.
 */
public class UserInfoMineActivity extends BaseActivity implements View.OnClickListener {

    private static String CODE = "code";
    ImageView ivBack;
    LinearLayout llUsernameUserinfo;
    TextView tvEditLabel;
    TextView tvEditLovemanifesto;
    TextView tvEditIntroduce;
    TextView tvEditInfodetail;
    //基本资料
    TextView tvNicknameUserinfo;
    CircleImageView cirUsericonUserinfo;
    private ImageView iv_level_userinfo;
    TextView tvHeight1Userinfo;
    TextView tvAge1Userinfo;
    TextView tvLocationUserinfo;
    //宣言和介绍
    TextView tvDeclarationUserinfo;
    TextView tvUcontentUserinfo;
    //详细资料
    TextView tvUsernameUserinfo;
    TextView tvAge2Userinfo;
    TextView tvSexUserinfo;
    TextView tvNationUserinfo;
    TextView tvHeight2Userinfo;
    TextView tvWeightUserinfo;
    TextView tvEducationUserinfo;
    TextView tvMonthincomeUserinfo;
    TextView tvMaritalstatusUserinfo;
    TextView tvChildstatusUserinfo;
    TextView tvBuyhouseUserinfo;
    TextView tvBuycarUserinfo;
    TextView tvAddressUserinfo;
    TextView tvEditContactway;
    TextView tvEditEducation;
    //联系方式
    TextView tvPhoneUserinfo;
    TextView tvWeicharUserinfo;
    TextView tvQqUserinfo;
    //教育及工作
    private TextView tv_school_userinfo;
    private TextView tv_major_userinfo;
    private TextView tv_job_userinfo;
    private TextView tv_companyindustry_userinfo;
    private TextView tv_companynature_userinfo;
    private TextView tv_language_userinfo;

    private ImageView iv_back;
    private TextView tv_edit_label;//标签
    private TextView tv_edit_lovemanifesto;//爱情宣言
    private TextView tv_edit_introduce;//自我介绍
    private TextView tv_edit_infodetail;//详细资料
    private TextView tv_edit_contactway;//联系方式
    private TextView tv_edit_education;//教育及工作
    private UserInfroDetailBean userInfoBean;//用户资料bean

    private String nickname;//用户昵称
    private String step;//会员等级
    private String username;//姓名
    private String ucode;//用户号
    private int like_num;//点赞数
    private int gz_num;//关注数
    private int fs_num;//粉丝数
    private String sex;//性别ID
    private String sex_str;//性别
    private String upic;//头像图片地址
    private String age;//年龄
    private String areaid;//省份ID
    private String area_str;//:省份
    private String cityid;//城市ID
    private String city_str;//城市
    private String nation;//民族的编号
    private String nation_str;//民族
    private String education;//学历的编号
    private String education_str;//学历
    private String mobile;//的编号
    private String declaration;//爱情宣言
    private String ucontent;//自我介绍
    private String qq;//
    private String weixin;//
    private String address;//详细地址
    private String myheight;//身高
    private String myweight;//身高
    private String month_income;//月收入的编号
    private String month_income_str;//月收入
    private String marital_status;//婚姻状况id
    private String marital_status_str;//婚姻状况
    private String child_status;//婚姻状况
    private String buy_house;//
    private String buy_car;//
    private String school;//
    private String major;//
    private String job;//
    private String company_industry;//公司行业的编号
    private String company_industry_str;//
    private String company_nature;//公司性质的编号
    private String company_nature_str;//
    private List<String> language_list;//掌握的语言
    private List<UserInfroDetailBean.LabelListBean> label_list;//用户的标签
    private List<UserInfroDetailBean.PicListBean> pic_list;//个人相册照片
    private String language = "";
    //控件


    @Override
    protected void activityOnCreate(Bundle extras) {
    }

    @Override
    protected View initTitleView() {
        return null;
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_userinfo_mine, null);
    }

    @Override
    protected void initTitle() {
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mContentView.findViewById(R.id.iv_back);

        tv_edit_label = (TextView) mContentView.findViewById(R.id.tv_edit_label);
        tv_edit_lovemanifesto = (TextView) mContentView.findViewById(R.id.tv_edit_lovemanifesto);
        tv_edit_introduce = (TextView) mContentView.findViewById(R.id.tv_edit_introduce);
        tv_edit_infodetail = (TextView) mContentView.findViewById(R.id.tv_edit_infodetail);
        tv_edit_contactway = (TextView) mContentView.findViewById(R.id.tv_edit_contactway);
        tv_edit_education = (TextView) mContentView.findViewById(R.id.tv_edit_education);
        //初始化
        cirUsericonUserinfo = (CircleImageView) mContentView.findViewById(R.id.cir_usericon_userinfo);
        tvNicknameUserinfo = (TextView) mContentView.findViewById(R.id.tv_nickname_userinfo);
        iv_level_userinfo = (ImageView) mContentView.findViewById(R.id.iv_level_userinfo);
        tvAge1Userinfo = (TextView) mContentView.findViewById(R.id.tv_age1_userinfo);
        tvHeight1Userinfo = (TextView) mContentView.findViewById(R.id.tv_height1_userinfo);
        tvLocationUserinfo = (TextView) mContentView.findViewById(R.id.tv_location_userinfo);

        tvDeclarationUserinfo = (TextView) mContentView.findViewById(R.id.tv_declaration_userinfo);
        tvUcontentUserinfo = (TextView) mContentView.findViewById(R.id.tv_ucontent_userinfo);

        tvUsernameUserinfo = (TextView) mContentView.findViewById(R.id.tv_username_userinfo);
        tvAge2Userinfo = (TextView) mContentView.findViewById(R.id.tv_age2_userinfo);
        tvSexUserinfo = (TextView) mContentView.findViewById(R.id.tv_sex_userinfo);
        tvNationUserinfo = (TextView) mContentView.findViewById(R.id.tv_nation_userinfo);
        tvHeight2Userinfo = (TextView) mContentView.findViewById(R.id.tv_height2_userinfo);
        tvWeightUserinfo = (TextView) mContentView.findViewById(R.id.tv_weight_userinfo);
        tvEducationUserinfo = (TextView) mContentView.findViewById(R.id.tv_education_userinfo);
        tvMonthincomeUserinfo = (TextView) mContentView.findViewById(R.id.tv_monthincome_userinfo);
        tvMaritalstatusUserinfo = (TextView) mContentView.findViewById(R.id.tv_maritalstatus_userinfo);
        tvChildstatusUserinfo = (TextView) mContentView.findViewById(R.id.tv_childstatus_userinfo);
        tvBuyhouseUserinfo = (TextView) mContentView.findViewById(R.id.tv_buyhouse_userinfo);
        tvBuycarUserinfo = (TextView) mContentView.findViewById(R.id.tv_buycar_userinfo);
        tvAddressUserinfo = (TextView) mContentView.findViewById(R.id.tv_address_userinfo);

        tvPhoneUserinfo = (TextView) mContentView.findViewById(R.id.tv_phone_userinfo);
        tvWeicharUserinfo = (TextView) mContentView.findViewById(R.id.tv_weichar_userinfo);
        tvQqUserinfo = (TextView) mContentView.findViewById(R.id.tv_qq_userinfo);
        /*教育*/
        tv_school_userinfo = (TextView) mContentView.findViewById(R.id.tv_school_userinfo);
        tv_major_userinfo = (TextView) mContentView.findViewById(R.id.tv_major_userinfo);
        tv_job_userinfo = (TextView) mContentView.findViewById(R.id.tv_job_userinfo);
        tv_companyindustry_userinfo = (TextView) mContentView.findViewById(R.id.tv_companyindustry_userinfo);
        tv_companynature_userinfo = (TextView) mContentView.findViewById(R.id.tv_companynature_userinfo);
        tv_language_userinfo = (TextView) mContentView.findViewById(R.id.tv_language_userinfo);


    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);

        tv_edit_label.setOnClickListener(this);
        tv_edit_lovemanifesto.setOnClickListener(this);
        tv_edit_introduce.setOnClickListener(this);
        tv_edit_infodetail.setOnClickListener(this);
        tv_edit_contactway.setOnClickListener(this);
        tv_edit_education.setOnClickListener(this);

    }

    @Override
    protected ContentPage.RequestState onLoad() {
        userid = QueQiaoLoveApp.getUserId();
        loadUserInfoDetail();
        return ContentPage.RequestState.STATE_SUCCESS;
    }

    /*加载个人资料详情*/
    private void loadUserInfoDetail() {
        MineAPI mineAPI = Http.getInstance().create(MineAPI.class);
        mineAPI.userInfoDetail(userid).enqueue(new Callback<UserInfroDetailBean>() {
            @Override
            public void onResponse(Call<UserInfroDetailBean> call, Response<UserInfroDetailBean> response) {
                userInfoBean = response.body();
                if (userInfoBean.getReturnvalue().equals("true")) {
                    showUserInfo();
                    Log.e("userinfo", userInfoBean.getUsername());
                } else {
                    toast(userInfoBean.getMsg());
                }
            }

            @Override
            public void onFailure(Call<UserInfroDetailBean> call, Throwable t) {
                toast("网络数据异常");
            }
        });
    }

    /*显示个人资料*/
    private void showUserInfo() {
        nickname = userInfoBean.getNickname().trim();
        step = userInfoBean.getStep().trim();
        username = userInfoBean.getUsername().trim();
        ucode = userInfoBean.getUcode().trim();
        like_num = userInfoBean.getLike_num();
        gz_num = userInfoBean.getGz_num();
        fs_num = userInfoBean.getFs_num();
        sex = userInfoBean.getSex().trim().trim();
        sex_str = userInfoBean.getSex_str().trim();
        upic = userInfoBean.getUpic().trim();
        age = userInfoBean.getAge().trim();
        areaid = userInfoBean.getAreaid().trim();
        area_str = userInfoBean.getArea_str().trim();
        cityid = userInfoBean.getCityid().trim();
        city_str = userInfoBean.getCity_str().trim();
        nation_str = userInfoBean.getNation_str().trim();
        nation = userInfoBean.getNation().trim();
        education = userInfoBean.getEducation().trim();
        education_str = userInfoBean.getEducation_str().trim();
        mobile = userInfoBean.getMobile().trim();
        declaration = userInfoBean.getDeclaration().trim();
        ucontent = userInfoBean.getUcontent().trim();
        qq = userInfoBean.getQq().trim();
        weixin = userInfoBean.getWeixin().trim();
        address = userInfoBean.getAddress().trim();
        myheight = userInfoBean.getMyheight().trim();
        myweight = userInfoBean.getMyweight().trim();
        month_income = userInfoBean.getMonth_income().trim();
        month_income_str = userInfoBean.getMonth_income_str().trim();
        marital_status = userInfoBean.getMarital_status().trim();
        marital_status_str = userInfoBean.getMarital_status_str().trim();
        child_status = userInfoBean.getChild_status().trim();
        buy_house = userInfoBean.getBuy_house().trim();
        buy_car = userInfoBean.getBuy_car().trim();
        school = userInfoBean.getSchool().trim();
        major = userInfoBean.getMajor().trim();
        job = userInfoBean.getJob().trim();
        company_industry = userInfoBean.getCompany_industry().trim();
        company_industry_str = userInfoBean.getCompany_industry_str().trim();
        company_nature = userInfoBean.getCompany_nature().trim();
        company_nature_str = userInfoBean.getCompany_nature_str().trim();
        language_list = userInfoBean.getLanguage_list();
        label_list = userInfoBean.getLabel_list();
        pic_list = userInfoBean.getPic_list();

        if (language_list.size() == 0) {
            language = "";
        } else {
            for (String str : language_list
                    ) {
                language = language + str + ";";
            }
        }

        //设置数据/
        /*基本资料*/
        CommonUtils.loadImage(R.mipmap.ic_default_usericon,cirUsericonUserinfo,upic);
        tvNicknameUserinfo.setText(nickname.trim().equals("") ? "暂无" : nickname);
        Log.e("level",step);
        iv_level_userinfo.setImageResource(CommonUtils.getLevelImage(step));
        tvAge1Userinfo.setText(age.trim().equals("") ? "暂无" : age);
        tvHeight1Userinfo.setText(myheight.trim().equals("") ? "暂无" : myheight);
        tvLocationUserinfo.setText(address.trim().equals("") ? "暂无" : address);
        /*宣言和介绍*/
        tvDeclarationUserinfo.setText(declaration.trim().equals("") ? "暂无" : declaration);
        tvUcontentUserinfo.setText(ucontent.trim().equals("") ? "暂无" : ucontent);
        /*消息资料*/
        tvUsernameUserinfo.setText(username.trim().equals("") ? "暂无" : username);
        tvAge2Userinfo.setText(age.trim().equals("") ? "暂无" : age);
        tvSexUserinfo.setText(sex_str.trim().equals("") ? "暂无" : sex_str);
        tvNationUserinfo.setText(nation_str.trim().equals("") ? "暂无" : nation_str);
        tvHeight1Userinfo.setText(myheight.trim().equals("") ? "暂无" : myheight);
        tvWeightUserinfo.setText(myweight.trim().equals("") ? "暂无" : myweight);
        tvEducationUserinfo.setText(education_str.trim().equals("") ? "暂无" : education_str);
        tvMonthincomeUserinfo.setText(month_income_str.trim().equals("") ? "暂无" : month_income_str);
        tvMaritalstatusUserinfo.setText(marital_status_str.trim().equals("") ? "暂无" : marital_status_str);
        tvChildstatusUserinfo.setText(child_status.trim().equals("") ? "暂无" : child_status);
        tvBuyhouseUserinfo.setText(buy_house.trim().equals("") ? "暂无" : buy_house);
        tvBuycarUserinfo.setText(buy_car.trim().equals("") ? "暂无" : buy_car);
        tvAddressUserinfo.setText(address.trim().equals("") ? "暂无" : address);
        /*教育*/
        tv_school_userinfo.setText(school.trim().equals("") ? "暂无" : school);
        tv_major_userinfo.setText(major.trim().equals("") ? "暂无" : major);
        tv_job_userinfo.setText(job.trim().equals("") ? "暂无" : job);
        tv_companyindustry_userinfo.setText(company_industry_str.trim().equals("") ? "暂无" : company_industry_str);
        tv_companynature_userinfo.setText(company_nature_str.trim().equals("") ? "暂无" : company_nature_str);
        tv_language_userinfo.setText(language.trim().equals("") ? "暂无" : language);
    }

    /**
     * 从外部跳转到本类的反复
     *
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, UserInfoMineActivity.class);
        intent.putExtra(CODE, data);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回
                finish();
                break;
            case R.id.tv_edit_label://标签
                LabelActivity.intent(mActivity, "1");
                break;
            case R.id.tv_edit_lovemanifesto://爱情宣言
                LoveManifestoActivity.intent(mActivity, "1");
                break;
            case R.id.tv_edit_introduce://自我介绍
                IntroduceActivity.intent(mActivity, "1");
                break;
            case R.id.tv_edit_infodetail://详细资料
                InfoDetailActivity.intent(mActivity, "1");
                break;
            case R.id.tv_edit_contactway://联系方式
                ContactWayActivity.intent(mActivity, "1");
                break;
            case R.id.tv_edit_education://教育及工作
                EducationAndWorkActivity.intent(mActivity, "1");
                break;
        }
    }

}
