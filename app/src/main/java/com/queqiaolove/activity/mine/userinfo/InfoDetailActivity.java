package com.queqiaolove.activity.mine.userinfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.queqiaolove.javabean.mine.UserInfroDetailBean;
import com.queqiaolove.javabean.sys.AttributeListBean;
import com.queqiaolove.javabean.sys.CityListBean;
import com.queqiaolove.javabean.sys.ProviceListBean;
import com.queqiaolove.util.CommonUtil;
import com.queqiaolove.widget.WheelView;
import com.queqiaolove.widget.dialog.NumOfSelectDialog;
import com.queqiaolove.widget.dialog.SelectSexDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WD on 2016/10/14.
 */
public class InfoDetailActivity extends BaseActivity implements View.OnClickListener {
    private static String LOVE = "love";
    private ImageView iv_back;
    private RelativeLayout rl_name_infodetail;//
    private RelativeLayout rl_age_infodetail;//
    private RelativeLayout rl_sex_infodetail;//
    private RelativeLayout rl_nation_infodetail;//
    private RelativeLayout rl_height_infodetail;//
    private RelativeLayout rl_weight_infodetail;//
    private RelativeLayout rl_education_infodetail;//
    private RelativeLayout rl_monthlyincome_infodetail;//
    private RelativeLayout rl_maritalstatus_infodetail;//
    private RelativeLayout rl_childstatus_infodetail;//
    private RelativeLayout rl_housestatus_infodetail;//
    private RelativeLayout rl_carstatus_infodetail;//
    private RelativeLayout rl_location_infodetail;//
    private TextView tv_finish;

    private EditText et_name;
    private TextView tv_age;
    private TextView tv_sex;
    private TextView tv_nation;
    private TextView tv_height;
    private TextView tv_weight;
    private TextView tv_education;
    private TextView tv_monthlyincome;
    private TextView tv_maritalstatus;
    private TextView tv_childstatus;
    private TextView tv_housestatus;
    private TextView tv_carstatus;
    private TextView tv_address;
    private UserInfroDetailBean detailBean;

    private String username;//姓名
    private String ucode;//用户号
    private int like_num;//点赞数
    private int gz_num;//关注数
    private int fs_num;//粉丝数
    private String sex;//性别ID
    private String sex_str;//性别
    private String age;//年龄
    private String areaid;//省份ID
    private String area_str;//:省份
    private String cityid;//城市ID
    private String city_str;//城市
    private String nation;//民族的编号
    private String nation_str;//民族
    private String education;//学历的编号
    private String education_str;//学历
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
    private List<AttributeListBean.ListBean> AttributeList;
    private AttributeListBean.ListBean attributeData;
    private String aname;
    private int index;
    private List<ProviceListBean.ListBean> provincelist;
    private List<CityListBean.ListBean> cityList;

    @Override
    protected void activityOnCreate(Bundle extras) {
        detailBean = (UserInfroDetailBean) extras.getSerializable(LOVE);
        username = detailBean.getUsername().trim();
        sex = detailBean.getSex().trim().trim();
        sex_str = detailBean.getSex_str().trim();
        age = detailBean.getAge().trim();
        areaid = detailBean.getAreaid().trim();
        area_str = detailBean.getArea_str().trim();
        cityid = detailBean.getCityid().trim();
        city_str = detailBean.getCity_str().trim();
        nation_str = detailBean.getNation_str().trim();
        nation = detailBean.getNation().trim();
        education = detailBean.getEducation().trim();
        education_str = detailBean.getEducation_str().trim();
        address = detailBean.getAddress().trim();
        myheight = detailBean.getMyheight().trim();
        myweight = detailBean.getMyweight().trim();
        month_income = detailBean.getMonth_income().trim();
        month_income_str = detailBean.getMonth_income_str().trim();
        marital_status = detailBean.getMarital_status().trim();
        marital_status_str = detailBean.getMarital_status_str().trim();;
        child_status = detailBean.getChild_status().trim();
        buy_house = detailBean.getBuy_house().trim();
        buy_car = detailBean.getBuy_car().trim();

    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_base_userinfo, null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_userinfo_infodetail, null);
    }

    @Override
    protected void initTitle() {
        TextView tv_title =  (TextView) mTitleView.findViewById(R.id.tv_title);
        tv_finish = (TextView) mTitleView.findViewById(R.id.tv_finish);
        tv_finish.setText("保存");
        tv_title.setText("详细资料");
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);

        rl_name_infodetail = (RelativeLayout) mContentView.findViewById(R.id.rl_name_infodetail);
        rl_age_infodetail = (RelativeLayout) mContentView.findViewById(R.id.rl_age_infodetail);
        rl_sex_infodetail = (RelativeLayout) mContentView.findViewById(R.id.rl_sex_infodetail);
        rl_nation_infodetail = (RelativeLayout) mContentView.findViewById(R.id.rl_nation_infodetail);
        rl_height_infodetail = (RelativeLayout) mContentView.findViewById(R.id.rl_height_infodetail);
        rl_weight_infodetail = (RelativeLayout) mContentView.findViewById(R.id.rl_weight_infodetail);
        rl_education_infodetail = (RelativeLayout) mContentView.findViewById(R.id.rl_education_infodetail);
        rl_monthlyincome_infodetail = (RelativeLayout) mContentView.findViewById(R.id.rl_monthlyincome_infodetail);
        rl_maritalstatus_infodetail = (RelativeLayout) mContentView.findViewById(R.id.rl_maritalstatus_infodetail);
        rl_childstatus_infodetail = (RelativeLayout) mContentView.findViewById(R.id.rl_childstatus_infodetail);
        rl_housestatus_infodetail = (RelativeLayout) mContentView.findViewById(R.id.rl_housestatus_infodetail);
        rl_carstatus_infodetail = (RelativeLayout) mContentView.findViewById(R.id.rl_carstatus_infodetail);
        rl_location_infodetail = (RelativeLayout) mContentView.findViewById(R.id.rl_location_infodetail);

        et_name = (EditText) mContentView.findViewById(R.id.et_name);
        tv_age = (TextView) mContentView.findViewById(R.id.tv_age);
        tv_sex = (TextView) mContentView.findViewById(R.id.tv_sex);
        tv_nation = (TextView) mContentView.findViewById(R.id.tv_nation);
        tv_height = (TextView) mContentView.findViewById(R.id.tv_height);
        tv_weight = (TextView) mContentView.findViewById(R.id.tv_weight);
        tv_education = (TextView) mContentView.findViewById(R.id.tv_education);
        tv_monthlyincome = (TextView) mContentView.findViewById(R.id.tv_monthlyincome);
        tv_maritalstatus = (TextView) mContentView.findViewById(R.id.tv_maritalstatus);
        tv_childstatus = (TextView) mContentView.findViewById(R.id.tv_childstatus);
        tv_housestatus = (TextView) mContentView.findViewById(R.id.tv_housestatus);
        tv_carstatus = (TextView) mContentView.findViewById(R.id.tv_carstatus);
        tv_address = (TextView) mContentView.findViewById(R.id.tv_address);



    }

    @Override
    protected void initEvent() {
        iv_back.setOnClickListener(this);
        tv_finish.setOnClickListener(this);

        rl_name_infodetail.setOnClickListener(this);
        rl_age_infodetail.setOnClickListener(this);
        rl_sex_infodetail.setOnClickListener(this);
        rl_nation_infodetail.setOnClickListener(this);
        rl_height_infodetail.setOnClickListener(this);
        rl_weight_infodetail.setOnClickListener(this);
        rl_education_infodetail.setOnClickListener(this);
        rl_monthlyincome_infodetail.setOnClickListener(this);
        rl_childstatus_infodetail.setOnClickListener(this);
        rl_housestatus_infodetail.setOnClickListener(this);
        rl_carstatus_infodetail.setOnClickListener(this);
        rl_maritalstatus_infodetail.setOnClickListener(this);
        rl_location_infodetail.setOnClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        return ContentPage.RequestState.STATE_SUCCESS;
    }

    @Override
    public View onCreateSuccessView() {
        if (username.trim().equals("") ){
            et_name.setHint("暂无");
        }else {
            et_name.setText(username);
        }
        tv_age.setText(age.trim().equals("") ? "暂无" : age);
        tv_sex.setText(sex_str.trim().equals("") ? "暂无" : sex_str);
        tv_nation.setText(nation_str.trim().equals("") ? "暂无" : nation_str);
        tv_height.setText(myheight.trim().equals("") ? "暂无" : myheight);
        tv_weight.setText(myweight.trim().equals("") ? "暂无" : myweight);
        tv_education.setText(education_str.trim().equals("") ? "暂无" : education_str);
        tv_monthlyincome.setText(month_income_str.trim().equals("") ? "暂无" : month_income_str);
        tv_maritalstatus.setText(marital_status_str.trim().equals("") ? "暂无" : marital_status_str);
        if (child_status.trim().equals("")){
            tv_childstatus.setText("暂无");
        }else if(child_status.trim().equals("1")){
            tv_childstatus.setText("有");
        }else {
            tv_childstatus.setText("无");

        }
        if (buy_house.trim().equals("")){
            tv_housestatus.setText("暂无");
        }else if(buy_house.trim().equals("1")){
            tv_housestatus.setText("有");
        }else {
            tv_housestatus.setText("无");

        }
        if (buy_car.trim().equals("")){
            tv_carstatus.setText("暂无");
        }else if(buy_car.trim().equals("1")){
            tv_carstatus.setText("有");
        }else {
            tv_carstatus.setText("无");

        }
        if (area_str.trim().equals("")){
            tv_address.setText("暂无");
        } else {
            tv_address.setText(area_str+city_str);

        }

        return super.onCreateSuccessView();
    }

    /**
     * 从外部跳转到本类的反复
     *
     * @param activity
     */
    public static void intent(Activity activity, UserInfroDetailBean data) {
        Intent intent = new Intent();
        intent.setClass(activity, InfoDetailActivity.class);
        intent.putExtra(LOVE, data);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_finish://保存
                changeUserInfoDetail();
                finish();
                break;
            case R.id.rl_name_infodetail:
                break;
            case R.id.rl_age_infodetail://年龄
                NumOfSelectDialog numOfAgeDialog = new NumOfSelectDialog(mActivity,"选择年龄",20,18,120);
                numOfAgeDialog.show();
                numOfAgeDialog.setNumDialogListener(new NumOfSelectDialog.NumDialogListener() {
                    @Override
                    public void selectNum(int num) {
                        age = num+"";
                        tv_age.setText(num+"");
                    }
                });
                break;
            case R.id.rl_sex_infodetail://性别
                if (sex.equals("")){
                    sex = "1";
                }
                SelectSexDialog selectSexDialog = new SelectSexDialog(mActivity,Integer.valueOf(sex));
                selectSexDialog.show();
                selectSexDialog.setSexCallBackListener(new SelectSexDialog.SexCallBackListener() {
                    @Override
                    public void sexCallBack(int i) {
                        sex = i+"";
                        tv_sex.setText(i==1?"男":"女");
                    }
                });
                break;
            case R.id.rl_nation_infodetail://民族
                showAttributeList(Constants.ATTRIBUTE_NATURE,"民族");
                break;
            case R.id.rl_height_infodetail://身高
                NumOfSelectDialog numOfHeightDialog = new NumOfSelectDialog(mActivity,"选择身高",180,120,300);
                numOfHeightDialog.show();
                numOfHeightDialog.setNumDialogListener(new NumOfSelectDialog.NumDialogListener() {
                    @Override
                    public void selectNum(int num) {
                        myheight = num+"";
                        tv_height.setText(myheight);
                    }
                });
                break;
            case R.id.rl_weight_infodetail://体重
                NumOfSelectDialog numOfWeightDialog = new NumOfSelectDialog(mActivity,"选择体重",60,30,150);
                numOfWeightDialog.show();
                numOfWeightDialog.setNumDialogListener(new NumOfSelectDialog.NumDialogListener() {
                    @Override
                    public void selectNum(int num) {
                        myweight = num+"";
                        tv_weight.setText(myweight);
                    }
                });
                break;
            case R.id.rl_education_infodetail://学历
                showAttributeList(Constants.ATTRIBUTE_EDUCATION,"学历");
                break;
            case R.id.rl_monthlyincome_infodetail://月收入
                showAttributeList(Constants.ATTRIBUTE_MONTHLYINCOME,"月收入");
                break;
            case R.id.rl_maritalstatus_infodetail://婚姻状况
                ArrayList<String> maritalstatuslist = new ArrayList<>();
                maritalstatuslist.add("未婚");
                maritalstatuslist.add("离异");
                showWheelView("婚姻状况",maritalstatuslist);
                break;
            case R.id.rl_housestatus_infodetail://购房状况
                ArrayList<String> houselist = new ArrayList<>();
                houselist.add("有房");
                houselist.add("无房");
                showWheelView("购房状况",houselist);
                break;
            case R.id.rl_childstatus_infodetail://子女状况
                ArrayList<String> childlist = new ArrayList<>();
                childlist.add("有");
                childlist.add("无");
                showWheelView("子女状况",childlist);
                break;
            case R.id.rl_carstatus_infodetail://购车情况
                ArrayList<String> carlist = new ArrayList<>();
                carlist.add("有车");
                carlist.add("无车");
                showWheelView("购车状况",carlist);
                break;
            case R.id.rl_location_infodetail:
                loadProvinceList();
                break;
        }
    }
    /*修改个人详细信息*/
    private void changeUserInfoDetail() {
        userid = QueQiaoLoveApp.getUserId();
        username = et_name.getText().toString().trim();
        MineAPI mineAPI = Http.getInstance().create(MineAPI.class);
        mineAPI.changeUserInfoDetail(userid,username,age,sex,areaid,cityid,nation,
                education,myheight,myweight,month_income,marital_status,child_status,
                buy_house,buy_car).enqueue(new Callback<BaseBean>() {
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

    /*省列表*/
    private void loadProvinceList() {
        SysAPI sysAPI = Http.getInstance().create(SysAPI.class);
        sysAPI.provinceList(0).enqueue(new Callback<ProviceListBean>() {
            @Override
            public void onResponse(Call<ProviceListBean> call, Response<ProviceListBean> response) {
                ProviceListBean body = response.body();
                if (body.getReturnvalue().equals("true")){
                    provincelist = body.getList();
                    ArrayList<String> list = new ArrayList<String>();
                    for (ProviceListBean.ListBean data: provincelist
                            ){
                        list.add(data.getProvince_name());
                    }
                    showWheelView("省",list);
                }else {
                    toast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Call<ProviceListBean> call, Throwable t) {
                toast("网络数据异常");
            }
        });
    }

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
    private void showWheelView(final String title , final List<String> list) {
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
                        if (AttributeList!=null) {
                            attributeData = AttributeList.get(index);
                            aname = attributeData.getAname();
                        }
                        switch (title) {
                            case "民族":
                                nation = attributeData.getAcode();
                                tv_nation.setText(aname);
                                index = 0;
                                break;
                            case "学历":
                                education = attributeData.getAcode();
                                tv_education.setText(aname);
                                index = 0;
                                break;
                            case "月收入":
                                month_income = attributeData.getAcode();
                                tv_monthlyincome.setText(aname);
                                index = 0;
                                break;
                            case "婚姻状况":
                                aname = list.get(index);
                                marital_status = aname.equals("未婚")?1+"" : 2+"";
                                tv_maritalstatus.setText(aname);
                                index = 0;
                                break;
                            case "购房状况":
                                aname = list.get(index);
                                buy_house = aname.equals("有房")?1+"" : 2+"";
                                tv_housestatus.setText(aname);
                                index = 0;
                                break;
                            case "购车状况":
                                aname = list.get(index);
                                buy_car = aname.equals("有车")?1+"" : 2+"";
                                tv_carstatus.setText(aname);
                                index = 0;
                                break;
                            case "子女状况":
                                aname = list.get(index);
                                child_status = aname.equals("有")?1+"" : 2+"";
                                tv_childstatus.setText(aname);
                                index = 0;
                                break;
                            case "省":
                                areaid = provincelist.get(index).getProvince_id();
                                index = 0;
                                loadCityList();
                                break;
                            case "市":
                                cityid = cityList.get(index).getCity_id();
                                tv_address.setText(cityList.get(index).getPname()+
                                        cityList.get(index).getCity_name());
                                index = 0;
                                break;
                        }
                    }
                })
                .show();
        CommonUtil.dialogTitleLineColor(alertDialog, R.color.purple_queqiao);
    }

    private void loadCityList() {
        SysAPI sysAPI = Http.getInstance().create(SysAPI.class);
        sysAPI.cityList(Integer.parseInt(areaid),1).enqueue(new Callback<CityListBean>() {
            @Override
            public void onResponse(Call<CityListBean> call, Response<CityListBean> response) {
                CityListBean body = response.body();
                if (body.getReturnvalue().equals("true")){
                    cityList = body.getList();
                    ArrayList<String> list = new ArrayList<String>();
                    for (CityListBean.ListBean data: cityList
                            ){
                        list.add(data.getCity_name());
                    }
                    showWheelView("市",list);
                }else {
                    toast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Call<CityListBean> call, Throwable t) {
                toast("网络数据异常");
            }
        });
    }

}
