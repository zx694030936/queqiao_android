package com.queqiaolove.widget.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.base.BaseActivity;


public class SelectSexDialog extends Dialog implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private int memberId;//传入的用户id
    private final BaseActivity mContext;
    private SexCallBackListener mSexCallBackListener;//回调接口引用
    private TextView tv_save;
    private TextView tv_cancel;
    private RadioGroup rg_sex;
    private RadioButton rb_man;
    private RadioButton rb_woman;
    private int sex = 1;//性别

    public SelectSexDialog(Context context, int id) {
        //构造方法中传入一个主题，去除dialog的默认背景
        super(context, R.style.dialog_style_selectnum);
        mContext = (BaseActivity) context;
        memberId = id;
        this.setCanceledOnTouchOutside(true);//点击dialog之外,dialog消失
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //去除dialog的标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.layout_dialog_selectsex);

        //设置dialog的显示位置
        LayoutParams params = getWindow().getAttributes();
        params.width = LayoutParams.MATCH_PARENT;//宽度填充父布局
        params.height = LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        //初始化控件
        initView();
    }

    private void initView() {
        tv_cancel = (TextView) findViewById(R.id.tv_cancel_selectsex_myaccount);
        tv_save = (TextView) findViewById(R.id.tv_save_selectsex_myaccount);
        rg_sex = (RadioGroup) findViewById(R.id.rg_sex_myaccount);
        rb_man = (RadioButton) findViewById(R.id.rb_mansex_myaccount);
        rb_woman = (RadioButton) findViewById(R.id.rb_womansex_myaccount);

        tv_cancel.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        rg_sex.setOnCheckedChangeListener(this);
        //默认选中男
        rg_sex.check(R.id.rb_mansex_myaccount);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_cancel_selectsex_myaccount:
                //mContext.toast("取消");
                dismiss();
                break;
            case R.id.tv_save_selectsex_myaccount:
                mSexCallBackListener.sexCallBack(sex);//回调方法
                dismiss();
                break;
        }

    }

    //设置回调接口的方法
    public void setSexCallBackListener(SexCallBackListener sexCallBackListener){
        this.mSexCallBackListener = sexCallBackListener;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
        switch (checkId){
            case R.id.rb_mansex_myaccount:
                //mContext.toast("男");
                sex = 1;
                break;
            case R.id.rb_womansex_myaccount:
                //mContext.toast("女");
                sex =2;
                break;
        }
    }

    //dialog的回调接口
    public interface SexCallBackListener {
        public void sexCallBack(int i);
    }
}
