package com.queqiaolove.activity.mine.userinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;

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

    private TextView tv_name;
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
    private TextView tv_location;

    @Override
    protected void activityOnCreate(Bundle extras) {

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

        tv_name = (TextView) mContentView.findViewById(R.id.tv_name);
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
        tv_location = (TextView) mContentView.findViewById(R.id.tv_location);



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
        rl_location_infodetail.setOnClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        return ContentPage.RequestState.STATE_SUCCESS;
    }

    /**
     * 从外部跳转到本类的反复
     *
     * @param activity
     */
    public static void intent(Activity activity, String data) {
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

                finish();
                break;
            case R.id.rl_name_infodetail:
                EditContentActivity.intent(mActivity,"姓名");
                break;
            case R.id.rl_age_infodetail:

                break;
            case R.id.rl_sex_infodetail:

                break;
            case R.id.rl_nation_infodetail:
                EditContentActivity.intent(mActivity,"民族");
                break;
            case R.id.rl_height_infodetail:

                break;
            case R.id.rl_weight_infodetail:

                break;
            case R.id.rl_education_infodetail:

                break;
            case R.id.rl_monthlyincome_infodetail:

                break;
            case R.id.rl_housestatus_infodetail:

                break;
            case R.id.rl_childstatus_infodetail:
                EditContentActivity.intent(mActivity,"子女状况");
                break;
            case R.id.rl_carstatus_infodetail:

                break;
            case R.id.rl_location_infodetail:
                EditContentActivity.intent(mActivity,"居住地");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                String name = data.getStringExtra("name");
                tv_name.setText(name);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
