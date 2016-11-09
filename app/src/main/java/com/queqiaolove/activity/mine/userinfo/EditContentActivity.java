package com.queqiaolove.activity.mine.userinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.global.Constants;
import com.queqiaolove.util.SharedPrefUtil;

/**
 * Created by WD on 2016/10/14.
 */
public class EditContentActivity extends BaseActivity implements View.OnClickListener {
    private static String LOVE = "love";
    private static int NAME = 1;
    public static int REQUESTCODE_SCHOOL = 110;
    private ImageView iv_back;
    private TextView tv_finish;
    private String title;
    private EditText et_content_editcontent;

    @Override
    protected void activityOnCreate(Bundle extras) {
        title = extras.getString(LOVE);
    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_base_userinfo,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_userinfo_editcontent,null);
    }

    @Override
    protected void initTitle() {
        TextView tv_title = (TextView) mTitleView.findViewById(R.id.tv_title);
        tv_title.setText(title);
    }

    @Override
    protected void initView() {
        iv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);
        tv_finish = (TextView) mTitleView.findViewById(R.id.tv_finish);

        et_content_editcontent = (EditText) mContentView.findViewById(R.id.et_content_editcontent);


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
    public static void intent(Activity activity,String data) {
        Intent intent = new Intent();
        intent.setClass(activity, EditContentActivity.class);
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
                String content = et_content_editcontent.getText().toString().trim();
                if (content.length()==0){
                    toast("内容不能为空");
                    break;
                }
                switch (title){
                    case "毕业院校":
                        SharedPrefUtil.putString(mActivity, Constants.SP_SCHOOL,content);
                        break;
                    case "专业":
                        SharedPrefUtil.putString(mActivity, Constants.SP_MAJOR,content);
                        break;
                    case "职业":
                        SharedPrefUtil.putString(mActivity, Constants.SP_JOB,content);
                        break;
                }
                finish();
                break;
        }
    }
}
