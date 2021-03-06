package com.queqiaolove.fragment.main;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.queqiaolove.QueQiaoLoveApp;
import com.queqiaolove.R;
import com.queqiaolove.activity.login.ConstructionActivity;
import com.queqiaolove.activity.mine.AllServiceActivity;
import com.queqiaolove.activity.mine.AttentionMineActivity;
import com.queqiaolove.activity.mine.FansConstructionMineActivity;
import com.queqiaolove.activity.mine.GiftMineActivity;
import com.queqiaolove.activity.mine.InviteCodeMineActivity;
import com.queqiaolove.activity.mine.LiveVideoMineActivity;
import com.queqiaolove.activity.mine.MyAccountMineActivity;
import com.queqiaolove.activity.mine.PhotoMineActivity;
import com.queqiaolove.activity.mine.SettingMineActivity;
import com.queqiaolove.activity.mine.UpLoadPhotoActivity;
import com.queqiaolove.activity.mine.UserInfoMineActivity;
import com.queqiaolove.activity.mine.VideoMineActivity;
import com.queqiaolove.activity.mine.member.OpenMemberActivity;
import com.queqiaolove.adapter.mine.baseinfo.PicMlvAdapter;
import com.queqiaolove.adapter.mine.baseinfo.VideoMlvAdapter;
import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.MineAPI;
import com.queqiaolove.javabean.mine.UserBaseInfoBean;
import com.queqiaolove.util.CommonUtil;
import com.queqiaolove.widget.CircleImageView;
import com.queqiaolove.widget.MyGridView;
import com.queqiaolove.widget.dialog.SelectUserIconDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WD on 2016/10/2.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {
    private View rl_accountdetail_mine;//个人详情
    private View tv_setting_mine;//设置
    private View rl_invitecode_mine;//我的邀请码
    private View rl_gift_mine;//我的礼物
    private View rl_order_mine;//我的订单
    private View rl_fanscontribution_mine;//粉丝贡献
    private View rl_attention_mine;//我的关注
    private View rl_livevideo_mine;//我的点播
    private View rl_myaccount_mine;//我的账户
    private View tv_allservice_mine;//全部服务
    private View tv_myphoto_mine;//我的图片
    private View tv_myvideo_mine;//我的视频
    /*开通会员*/
    private View tv_pear_openmember;
    private View tv_coral_openmember;
    private View tv_jade_openmember;
    private View tv_diamond_openmember;
    private UserBaseInfoBean baseInfoBean;
    /*基本信息*/
    private TextView tv_nickname_mine;
    private ImageView iv_level_mine;
    private TextView tv_id_mine;
    private TextView tv_percent_mine;
    private ImageView iv_uploadpic_mine;
    private ImageView iv_uploadvideo_mine;
    /*基本信息*/
    private CircleImageView cir_usericon_mine;
    private String nickname;
    private String upic;
    private String step;
    private String ucode;
    private String integrity_degree;
    private List<UserBaseInfoBean.PicListBean> pic_list;
    private List<String> video_list;
    private MyGridView mlv_pic_mine;
    private MyGridView mlv_video_mine;
    /*上传图片*/
    private final int SELECT_PIC_BY_TACK_PHOTO = 0;
    private final int SELECT_PIC_BY_PICK_PHOTO = 1;
    private Uri photoUri;
    private String picPath = "";
    private ProgressDialog progressDialog;
    private final int MY_PERMISSIONS_REQUEST_SELECTPHOTO_PHONE = 123;
    private final int MY_PERMISSIONS_REQUEST_TAKEPHOTO_PHONE = 124;
    private final int RESULT_CAMERA_CROP_PATH_RESULT = 3;
    private String carrier;//厂商
    private int selectflag;//上传方式

    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_mine, null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.fragment_mine_main, null);
    }

    @Override
    protected void initView() {
        tv_setting_mine = mTitleView.findViewById(R.id.tv_setting_mine);
        rl_accountdetail_mine = mContentView.findViewById(R.id.rl_userinfo_mine);

        tv_allservice_mine = mContentView.findViewById(R.id.tv_allservice_mine);
        tv_myphoto_mine = mContentView.findViewById(R.id.tv_myphoto_mine);
        tv_myvideo_mine = mContentView.findViewById(R.id.tv_myvideo_mine);

        rl_gift_mine = mContentView.findViewById(R.id.rl_gift_mine);
        rl_order_mine = mContentView.findViewById(R.id.rl_order_mine);
        rl_fanscontribution_mine = mContentView.findViewById(R.id.rl_fanscontribution_mine);
        rl_attention_mine = mContentView.findViewById(R.id.rl_attention_mine);
        rl_livevideo_mine = mContentView.findViewById(R.id.rl_livevideo_mine);
        rl_myaccount_mine = mContentView.findViewById(R.id.rl_myaccount_mine);
        rl_invitecode_mine = mContentView.findViewById(R.id.rl_invitecode_mine);
        /*开通会员*/
        tv_pear_openmember = mContentView.findViewById(R.id.tv_pear_openmember);
        tv_coral_openmember = mContentView.findViewById(R.id.tv_coral_openmember);
        tv_jade_openmember = mContentView.findViewById(R.id.tv_jade_openmember);
        tv_diamond_openmember = mContentView.findViewById(R.id.tv_diamond_openmember);
        /*基本信息*/
        cir_usericon_mine = (CircleImageView) mContentView.findViewById(R.id.cir_usericon_mine);
        tv_nickname_mine = (TextView) mContentView.findViewById(R.id.tv_nickname_mine);
        iv_level_mine = (ImageView) mContentView.findViewById(R.id.iv_level_mine);
        tv_id_mine = (TextView) mContentView.findViewById(R.id.tv_id_mine);
        tv_percent_mine = (TextView) mContentView.findViewById(R.id.tv_percent_mine);
        iv_uploadpic_mine = (ImageView) mContentView.findViewById(R.id.iv_uploadpic_mine);
        iv_uploadvideo_mine = (ImageView) mContentView.findViewById(R.id.iv_uploadvideo_mine);

        mlv_pic_mine = (MyGridView) mContentView.findViewById(R.id.mgv_pic_mine);
        mlv_video_mine = (MyGridView) mContentView.findViewById(R.id.mgv_video_mine);


    }

    @Override
    protected void initEvent() {
        tv_setting_mine.setOnClickListener(this);
        rl_accountdetail_mine.setOnClickListener(this);
        tv_allservice_mine.setOnClickListener(this);

        tv_myphoto_mine.setOnClickListener(this);
        tv_myvideo_mine.setOnClickListener(this);

        rl_gift_mine.setOnClickListener(this);
        rl_order_mine.setOnClickListener(this);
        rl_fanscontribution_mine.setOnClickListener(this);
        rl_attention_mine.setOnClickListener(this);
        rl_livevideo_mine.setOnClickListener(this);
        rl_myaccount_mine.setOnClickListener(this);
        rl_invitecode_mine.setOnClickListener(this);
        /*开通会员*/
        tv_pear_openmember.setOnClickListener(this);
        tv_coral_openmember.setOnClickListener(this);
        tv_jade_openmember.setOnClickListener(this);
        tv_diamond_openmember.setOnClickListener(this);
        /*上传图片、视频*/
        iv_uploadpic_mine.setOnClickListener(this);
        iv_uploadvideo_mine.setOnClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {

        return ContentPage.RequestState.STATE_SUCCESS;
    }

    @Override
    public void onResume() {
        super.onResume();
        userid = QueQiaoLoveApp.getUserId();
        loadUserBaseInfo();
    }

    /*加载基本信息*/
    private void loadUserBaseInfo() {
        MineAPI mineAPI = Http.getInstance().create(MineAPI.class);
        mineAPI.userBaseInfo(userid).enqueue(new Callback<UserBaseInfoBean>() {
            @Override
            public void onResponse(Call<UserBaseInfoBean> call, Response<UserBaseInfoBean> response) {
                baseInfoBean = response.body();
                if (baseInfoBean.getReturnvalue().equals("true")) {
                    showUserBaseInfo();
                } else {
                    toast(baseInfoBean.getMsg());
                }
            }

            @Override
            public void onFailure(Call<UserBaseInfoBean> call, Throwable t) {
                toast("网络数据异常");
            }
        });
    }

    private void showUserBaseInfo() {
        nickname = baseInfoBean.getNickname();
        upic = baseInfoBean.getUpic();
        step = baseInfoBean.getStep();
        ucode = baseInfoBean.getUcode();
        integrity_degree = baseInfoBean.getIntegrity_degree();
        pic_list = baseInfoBean.getPic_list();
        video_list = baseInfoBean.getVideo_list();

        tv_nickname_mine.setText(nickname.trim().equals("") ? "暂无" : nickname);
        tv_id_mine.setText(ucode);
        tv_percent_mine.setText(integrity_degree);
        CommonUtil.loadImage(R.mipmap.ic_default_usericon, cir_usericon_mine, upic);
        iv_level_mine.setImageResource(CommonUtil.getLevelImage(step));

        mlv_pic_mine.setAdapter(new PicMlvAdapter(mActivity, pic_list));
        mlv_video_mine.setAdapter(new VideoMlvAdapter(mActivity, video_list));

        /*设置相片缩略图列表高度*/
        ViewTreeObserver viewTreeObserver = mlv_pic_mine.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = iv_uploadpic_mine.getMeasuredHeight();
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mlv_pic_mine.getLayoutParams();
                params.height = (int) (width/3.5*5);
                mlv_pic_mine.setLayoutParams(params);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_userinfo_mine://用户资料
                //LoginActivity.intent(mActivity,"1");
                UserInfoMineActivity.intent(mActivity, "1");
                break;
            case R.id.tv_setting_mine://设置
                SettingMineActivity.intent(mActivity, "1");
                break;
            case R.id.tv_allservice_mine://全部服务
                AllServiceActivity.intent(mActivity, "1");
                break;
            case R.id.tv_myphoto_mine://我的图片
                PhotoMineActivity.intent(mActivity, "1");
                break;
            case R.id.tv_myvideo_mine://我的视频
                VideoMineActivity.intent(mActivity, "1");
                break;
            case R.id.rl_gift_mine://我的礼物
                GiftMineActivity.intent(mActivity, "1");
                break;
            case R.id.rl_order_mine://我的订单
                ConstructionActivity.intent(mActivity, "1");
                break;
            case R.id.rl_fanscontribution_mine://粉丝贡献
                FansConstructionMineActivity.intent(mActivity, "1");
                break;
            case R.id.rl_attention_mine://我的关注
                AttentionMineActivity.intent(mActivity, "1");
                break;
            case R.id.rl_livevideo_mine://我的点播
                LiveVideoMineActivity.intent(mActivity, "1");
                break;
            case R.id.rl_myaccount_mine://我的账户
                MyAccountMineActivity.intent(mActivity, "1");
                break;
            case R.id.rl_invitecode_mine://我的邀请码
                InviteCodeMineActivity.intent(mActivity, "1");
                break;
            /*开通会员*/
            case R.id.tv_pear_openmember://珍珠
                OpenMemberActivity.intent(mActivity, "珍珠");
                break;
            case R.id.tv_coral_openmember://珊瑚
                OpenMemberActivity.intent(mActivity, "珊瑚");
                break;
            case R.id.tv_jade_openmember://翡翠
                OpenMemberActivity.intent(mActivity, "翡翠");
                break;
            case R.id.tv_diamond_openmember://钻石
                OpenMemberActivity.intent(mActivity, "钻石");
                break;
            case R.id.iv_uploadpic_mine://上传图片
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
                break;
            case R.id.iv_uploadvideo_mine://上传视频
                OpenMemberActivity.intent(mActivity, "钻石");
                break;

        }
    }


}
