package com.queqiaolove.widget.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import com.queqiaolove.R;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.widget.CustomNumberPicker;


public class NumOfSelectDialog extends Dialog implements View.OnClickListener {

    private final int mNum;//传入的数据
    private final BaseActivity mContext;
    private final int minNum;
    private final int maxNum;
    private final String mTitle;
    private TextView iv_ok;//确定按钮
    private NumDialogListener mNumDialogListener;//回调接口引用
    private CustomNumberPicker mNumberPicker;
    private TextView tv_title;

    public NumOfSelectDialog(Context context, String title,int selectnum,int min,int max) {
        //构造方法中传入一个主题，去除dialog的默认背景
        super(context, R.style.dialog_style_selectnum);
        mContext = (BaseActivity) context;
        mTitle = title;
        mNum = selectnum;
        minNum = min;
        maxNum = max;
        this.setCanceledOnTouchOutside(true);//点击dialog之外dialog小时
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //去除dialog的标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.layout_dialog_selectage);

        //设置dialog的显示位置
        LayoutParams params = getWindow().getAttributes();
        params.width = LayoutParams.MATCH_PARENT;//宽度填充父布局
        params.height = LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;

        //初始化控件
        initView();
    }

    private void initView() {
        iv_ok = (TextView) findViewById(R.id.iv_ok_dialogbook);
        tv_title = (TextView) findViewById(R.id.tv_title_selectnum);
        iv_ok.setOnClickListener(this);
        tv_title.setText(mTitle);
        mNumberPicker = (CustomNumberPicker) findViewById(R.id.cnp_dialog_bookseat);
        mNumberPicker.setMinValue(minNum);
        mNumberPicker.setMaxValue(maxNum);
        mNumberPicker.setValue(mNum);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_ok_dialogbook:
                mNumDialogListener.selectNum(mNumberPicker.getValue());
                dismiss();
                break;
        }

    }
    //设置回调接口的方法
    public void setNumDialogListener(NumDialogListener numDialogListener){
        this.mNumDialogListener = numDialogListener;
    }
    //dialog的回调接口
    public interface NumDialogListener {
        public void selectNum(int num);
    }
}
