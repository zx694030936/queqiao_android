package com.queqiaolove.fragment.find;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.queqiaolove.R;
import com.queqiaolove.activity.find.welfare.ILikeWhoActivity;
import com.queqiaolove.activity.find.welfare.MemberPowerDetailActivity;
import com.queqiaolove.activity.find.welfare.WhoLookedMeActivity;
import com.queqiaolove.activity.mine.member.OpenMemberActivity;
import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.base.ContentPage;

/**
 * Created by WD on 2016/10/2.
 */
public class WelfareFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout ll_pear_openmember;
    private LinearLayout ll_coral_openmember;
    private LinearLayout ll_jade_openmember;
    private LinearLayout ll_diamond_openmember;
    private LinearLayout ll_crown_openmember;
    private LinearLayout ll_tourist_openmember;

    private RelativeLayout rl_wholookme_welfare;
    private RelativeLayout rl_wholoveme_welfare;
    private RelativeLayout rl_ilikewho_welfare;
    private RelativeLayout rl_mutual_mine;

    @Override
    protected View initTitleView() {
        return null;
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout.fragment_welfare_find,null);
    }

    @Override
    protected void initView() {
        ll_pear_openmember = (LinearLayout) mContentView.findViewById(R.id.ll_pear_openmember);
        ll_coral_openmember = (LinearLayout) mContentView.findViewById(R.id.ll_coral_openmember);
        ll_jade_openmember = (LinearLayout) mContentView.findViewById(R.id.ll_jade_openmember);
        ll_diamond_openmember = (LinearLayout) mContentView.findViewById(R.id.ll_diamond_openmember);
        ll_crown_openmember = (LinearLayout) mContentView.findViewById(R.id.ll_crown_openmember);
        ll_tourist_openmember = (LinearLayout) mContentView.findViewById(R.id.ll_tourist_openmember);

        rl_wholookme_welfare = (RelativeLayout) mContentView.findViewById(R.id.rl_wholookme_welfare);
        rl_wholoveme_welfare = (RelativeLayout) mContentView.findViewById(R.id.rl_wholoveme_welfare);
        rl_ilikewho_welfare = (RelativeLayout) mContentView.findViewById(R.id.rl_ilikewho_welfare);
        rl_mutual_mine = (RelativeLayout) mContentView.findViewById(R.id.rl_mutual_mine);
    }

    @Override
    protected void initEvent() {
        ll_pear_openmember.setOnClickListener(this);
        ll_coral_openmember.setOnClickListener(this);
        ll_jade_openmember.setOnClickListener(this);
        ll_diamond_openmember.setOnClickListener(this);
        ll_crown_openmember.setOnClickListener(this);
        ll_tourist_openmember.setOnClickListener(this);

        rl_wholookme_welfare.setOnClickListener(this);
        rl_wholoveme_welfare.setOnClickListener(this);
        rl_ilikewho_welfare.setOnClickListener(this);
        rl_mutual_mine.setOnClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        return ContentPage.RequestState.STATE_SUCCESS;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_pear_openmember:
                MemberPowerDetailActivity.intent(mActivity,"珍珠");
                break;
            case R.id.ll_coral_openmember:
                MemberPowerDetailActivity.intent(mActivity,"珊瑚");
                break;
            case R.id.ll_jade_openmember:
                MemberPowerDetailActivity.intent(mActivity,"翡翠");
                break;
            case R.id.ll_diamond_openmember:
                MemberPowerDetailActivity.intent(mActivity,"钻石");
                break;
            case R.id.ll_crown_openmember:
                OpenMemberActivity.intent(mActivity,"皇冠");
                break;
            /*case R.id.ll_tourist_openmember:
                OpenMemberActivity.intent(mActivity,"游客");
                break;*/

            case R.id.rl_wholookme_welfare:
                WhoLookedMeActivity.intent(mActivity,"谁看过我");
                break;
            case R.id.rl_wholoveme_welfare:
                WhoLookedMeActivity.intent(mActivity,"谁喜欢我");
                break;
            case R.id.rl_ilikewho_welfare:
                WhoLookedMeActivity.intent(mActivity,"我喜欢谁");
                break;
            case R.id.rl_mutual_mine:
                ILikeWhoActivity.intent(mActivity,"互相喜欢");
                break;
        }
    }
}
