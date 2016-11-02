package com.queqiaolove.widget.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import com.queqiaolove.R;

public class NoPusherDialog extends Dialog{

    private final Activity mActivity;
    private NumDialogCallBackListener mNumDialogCallBackListener;//回调接口引用
    private TextView tv_wait;

    public NoPusherDialog(Context context) {
        //构造方法中传入一个主题，去除dialog的默认背景
        super(context, R.style.dialog_style_nopusher);
        mActivity = (Activity) context;
        this.setCanceledOnTouchOutside(true);//点击dialog之外,dialog消失
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //去除dialog的标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_nopusher_live);

        //设置dialog的显示位置
        LayoutParams params = getWindow().getAttributes();
        params.width = LayoutParams.MATCH_PARENT;//宽度填充父布局
        params.height = LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        //初始化控件
        initView();
    }

    private void initView() {
        tv_wait = (TextView) findViewById(R.id.tv_wait_nopush);
        tv_wait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                if (mNumDialogCallBackListener!=null){
                    mNumDialogCallBackListener.callBack();
                }
            }
        });
    }

    //设置回调接口的方法
    public void setDialogCallBackListener(NumDialogCallBackListener numDialogCallBackListener){
        this.mNumDialogCallBackListener = numDialogCallBackListener;
    }
    //dialog的回调接口
    public interface NumDialogCallBackListener {
        public void callBack();
    }
}
