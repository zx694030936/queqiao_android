package com.queqiaolove.widget.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import com.queqiaolove.R;


public class SelectUserIconDialog extends Dialog implements View.OnClickListener {

    private final int mNum;//传入的数据
    private final Activity mActivity;
    private TextView tv_camera;
    private TextView tv_album;
    private TextView tv_cancel;
    private Uri photoUri;
    private int SELECT_PIC_BY_TACK_PHOTO = 0;
    private int SELECT_PIC_BY_PICK_PHOTO = 1;
    private DialogCameraListener mDialogCameraListener;//相机监听
    private DialogAlbumListener mDialogAlbumListener;//相册监听

    public SelectUserIconDialog(Context context, int num) {
        //构造方法中传入一个主题，去除dialog的默认背景
        super(context, R.style.dialog_style_uploadicontype);
        mActivity = (Activity) context;
        mNum = num;
        this.setCanceledOnTouchOutside(true);//点击dialog之外,dialog消失
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //去除dialog的标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.layout_dialog_selectusericon);

        //设置dialog的显示位置
        LayoutParams params = getWindow().getAttributes();
        params.width = LayoutParams.MATCH_PARENT;//宽度填充父布局
        params.height = LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.BOTTOM;
        //初始化控件
        initView();
    }

    private void initView() {
        tv_camera = (TextView) findViewById(R.id.tv_camera_selectusericon);
        tv_album = (TextView) findViewById(R.id.tv_album_selectusericon);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel_selectusericon);

        tv_camera.setOnClickListener(this);
        tv_album.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_camera_selectusericon://相机
                mDialogCameraListener.camera();
                dismiss();
                break;
            case R.id.tv_album_selectusericon://相册
                mDialogAlbumListener.album();
                dismiss();
                break;
            case R.id.tv_cancel_selectusericon:
                dismiss();
                break;
        }

    }

    //设置调用相机回调接口的方法
    public void setDialogCameraListener(DialogCameraListener dialogCameraListener){
        this.mDialogCameraListener = dialogCameraListener;
    }
    //dialog的回调接口
    public interface DialogCameraListener {
        public void camera();
    }

    //设置回调接口的方法
    public void setDialogAlbumListener(DialogAlbumListener dialogAlbumListener){
        this.mDialogAlbumListener = dialogAlbumListener;
    }
    //dialog的回调接口
    public interface DialogAlbumListener {
        public void album();
    }
}
