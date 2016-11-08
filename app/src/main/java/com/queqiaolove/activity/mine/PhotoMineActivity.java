package com.queqiaolove.activity.mine;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.jingchen.pulltorefresh.pullableview.PullableGridView;
import com.queqiaolove.QueQiaoLoveApp;
import com.queqiaolove.R;
import com.queqiaolove.adapter.mine.MyPhotoGvAdapter;
import com.queqiaolove.base.BaseActivity;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.MineAPI;
import com.queqiaolove.javabean.mine.MyPhotoListBean;
import com.queqiaolove.widget.dialog.SelectUserIconDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WD on 2016/10/13.
 */
public class PhotoMineActivity extends BaseActivity implements View.OnClickListener, PullToRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    private static String CODE = "code";
    private ImageView tv_back;
    protected PullToRefreshLayout refresh_view;//可刷新的布局
    private PullableGridView gv_pulltofresh;
    private List<MyPhotoListBean.ListBean> myphotolist = new ArrayList<>();
    private final int MY_PERMISSIONS_REQUEST_SELECTPHOTO_PHONE = 123;
    private final int MY_PERMISSIONS_REQUEST_TAKEPHOTO_PHONE = 124;
    @Override
    protected void activityOnCreate(Bundle extras) {

    }

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_base_mine,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.activity_myphoto_mine,null);
    }

    @Override
    protected void initTitle() {
        TextView tv_title = (TextView) mTitleView.findViewById(R.id.tv_title);
        tv_title.setText("我的图片");
    }

    @Override
    protected void initView() {
        tv_back = (ImageView) mTitleView.findViewById(R.id.iv_back);
        gv_pulltofresh = (PullableGridView) mContentView.findViewById(R.id.gv_pulltofresh);
        /*可刷新布局*/
        refresh_view = (PullToRefreshLayout) mContentView.findViewById(R.id.refresh_view);
        refresh_view.setOnRefreshListener(this);

    }

    @Override
    protected void initEvent() {
        tv_back.setOnClickListener(this);
        gv_pulltofresh.setOnItemClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        userid = QueQiaoLoveApp.getUserId();
        myphotolist.clear();
        myphotolist.add(0,new MyPhotoListBean.ListBean());
        loadMyPhotoList();
        return ContentPage.RequestState.STATE_SUCCESS;
    }
    /*相册列表*/
    private void loadMyPhotoList() {
        MineAPI mineAPI = Http.getInstance().create(MineAPI.class);
        mineAPI.myphotolist(userid,pageno,pagesize).enqueue(new Callback<MyPhotoListBean>() {
            @Override
            public void onResponse(Call<MyPhotoListBean> call, Response<MyPhotoListBean> response) {
                MyPhotoListBean body = response.body();
                if (body.getReturnvalue().equals("true")){
                    myphotolist.addAll(body.getList());
                    gv_pulltofresh.setAdapter(new MyPhotoGvAdapter(mActivity,myphotolist));
                }else {
                    toast(body.getMsg());
                }
            }

            @Override
            public void onFailure(Call<MyPhotoListBean> call, Throwable t) {
                toast("网络数据异常");
            }
        });
    }

    /**
     * 从外部跳转到本类的反复
     * @param activity
     */
    public static void intent(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, PhotoMineActivity.class);
        intent.putExtra(CODE, data);
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        pageno=1;
        myphotolist.clear();
        myphotolist.add(0,new MyPhotoListBean.ListBean());
        loadMyPhotoList();
        pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        pageno++;
        loadMyPhotoList();
        pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        if (position==0){//点击上传
            if (Build.VERSION.SDK_INT >= 23) {
                //权限已经被授予，在这里直接写要执行的相应方法即可
                SelectUserIconDialog selectUserIconDialog = new SelectUserIconDialog(mActivity, 1);
                selectUserIconDialog.show();
                selectUserIconDialog.setDialogCameraListener(new SelectUserIconDialog.DialogCameraListener() {
                    @Override
                    public void camera() {
                        /*申请权限*/
                        //第二个参数是需要申请的权限
                        if (ContextCompat.checkSelfPermission(mActivity,
                                Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            //权限还没有授予，需要在这里写申请权限的代码
                            ActivityCompat.requestPermissions(mActivity,
                                    new String[]{Manifest.permission.CAMERA,
                                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                            Manifest.permission.MANAGE_DOCUMENTS},
                                    MY_PERMISSIONS_REQUEST_TAKEPHOTO_PHONE);

                        } else {
                            UpLoadPhotoActivity.intent(mActivity,UpLoadPhotoActivity.PHOTO);

                        }
                    }
                });
                selectUserIconDialog.setDialogAlbumListener(new SelectUserIconDialog.DialogAlbumListener() {
                    @Override
                    public void album() {
                        /*申请权限*/
                        //第二个参数是需要申请的权限
                        if (ContextCompat.checkSelfPermission(mActivity,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            //权限还没有授予，需要在这里写申请权限的代码
                            ActivityCompat.requestPermissions(mActivity,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
                                            , Manifest.permission.READ_EXTERNAL_STORAGE,
                                            Manifest.permission.MANAGE_DOCUMENTS},
                                    MY_PERMISSIONS_REQUEST_SELECTPHOTO_PHONE);

                        } else {
                            UpLoadPhotoActivity.intent(mActivity,UpLoadPhotoActivity.PIC24);
                        }

                    }
                });
            } else {
                SelectUserIconDialog selectUserIconDialog = new SelectUserIconDialog(mActivity, 1);
                selectUserIconDialog.show();
                selectUserIconDialog.setDialogCameraListener(new SelectUserIconDialog.DialogCameraListener() {
                    @Override
                    public void camera() {
                        UpLoadPhotoActivity.intent(mActivity,UpLoadPhotoActivity.PHOTO);
                    }
                });
                selectUserIconDialog.setDialogAlbumListener(new SelectUserIconDialog.DialogAlbumListener() {
                    @Override
                    public void album() {
                        UpLoadPhotoActivity.intent(mActivity,UpLoadPhotoActivity.PIC23);
                    }
                });
            }
        }else {//点击其他图片

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        pageno=1;
        myphotolist.clear();
        myphotolist.add(0,new MyPhotoListBean.ListBean());
        loadMyPhotoList();
    }
}
