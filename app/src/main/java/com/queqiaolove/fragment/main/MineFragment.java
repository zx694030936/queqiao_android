package com.queqiaolove.fragment.main;

import android.view.View;

import com.queqiaolove.R;
import com.queqiaolove.activity.login.ConstructionActivity;
import com.queqiaolove.activity.mine.AttentionMineActivity;
import com.queqiaolove.activity.mine.FansConstructionMineActivity;
import com.queqiaolove.activity.mine.GiftMineActivity;
import com.queqiaolove.activity.mine.InviteCodeMineActivity;
import com.queqiaolove.activity.mine.LiveVideoMineActivity;
import com.queqiaolove.activity.mine.MyAccountMineActivity;
import com.queqiaolove.activity.mine.PhotoMineActivity;
import com.queqiaolove.activity.mine.SettingMineActivity;
import com.queqiaolove.activity.mine.UserInfoMineActivity;
import com.queqiaolove.activity.mine.VideoMineActivity;
import com.queqiaolove.activity.mine.member.OpenMemberActivity;
import com.queqiaolove.activity.mine.AllServiceActivity;
import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.base.ContentPage;

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


    @Override
    protected View initTitleView() {
        return View.inflate(mActivity, R.layout.title_mine,null);
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.fragment_mine_main,null);
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
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        return ContentPage.RequestState.STATE_SUCCESS;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_userinfo_mine://用户资料
                //LoginActivity.intent(mActivity,"1");
                UserInfoMineActivity.intent(mActivity,"1");
                break;
            case R.id.tv_setting_mine://设置
                SettingMineActivity.intent(mActivity,"1");
                break;
            case R.id.tv_allservice_mine://全部服务
                AllServiceActivity.intent(mActivity,"1");
                break;
            case R.id.tv_myphoto_mine://我的图片
                PhotoMineActivity.intent(mActivity,"1");
                break;
            case R.id.tv_myvideo_mine://我的视频
                VideoMineActivity.intent(mActivity,"1");
                break;
            case R.id.rl_gift_mine://我的礼物
                GiftMineActivity.intent(mActivity,"1");
                break;
            case R.id.rl_order_mine://我的订单
                ConstructionActivity.intent(mActivity,"1");
                break;
            case R.id.rl_fanscontribution_mine://粉丝贡献
                FansConstructionMineActivity.intent(mActivity,"1");
                break;
            case R.id.rl_attention_mine://我的关注
                AttentionMineActivity.intent(mActivity,"1");
                break;
            case R.id.rl_livevideo_mine://我的点播
                LiveVideoMineActivity.intent(mActivity,"1");
                break;
            case R.id.rl_myaccount_mine://我的账户
                MyAccountMineActivity.intent(mActivity,"1");
                break;
            case R.id.rl_invitecode_mine://我的邀请码
                InviteCodeMineActivity.intent(mActivity,"1");
                break;
            /*开通会员*/
            case R.id.tv_pear_openmember://珍珠
                OpenMemberActivity.intent(mActivity,"珍珠");
                break;
            case R.id.tv_coral_openmember://珊瑚
                OpenMemberActivity.intent(mActivity,"珊瑚");
                break;
            case R.id.tv_jade_openmember://翡翠
                OpenMemberActivity.intent(mActivity,"翡翠");
                break;
            case R.id.tv_diamond_openmember://钻石
                OpenMemberActivity.intent(mActivity,"钻石");
                break;

        }
    }
}
