package com.queqiaolove.fragment.queqiao;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.queqiaolove.R;
import com.queqiaolove.activity.live.horizontal.HorizontalLiveActivity;
import com.queqiaolove.activity.live.vertical.VerticalLiveActivity;
import com.queqiaolove.activity.main.PcLiveActivity;
import com.queqiaolove.activity.main.PhoneLiveActivity;
import com.queqiaolove.activity.main.matchmaking.ActivityDetailActivity;
import com.queqiaolove.activity.main.matchmaking.MatchMakingActivity;
import com.queqiaolove.adapter.queqiao.PartnerGalleryAdapter;
import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.base.ContentPage.RequestState;
import com.queqiaolove.global.Constants;
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.LiveAPI;
import com.queqiaolove.javabean.live.LiveUrlListBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*鹊桥推荐页*/
public class _RecommendFragment extends BaseFragment implements View.OnClickListener {

	private RecyclerView rv_partner;//合作加盟
	private View hot1_recommend;
	private View tv_more_matchmaking;
	private View tv_more_hotlive;
	private View tv_more_phonelive;
	private View matchmaking1_recommend;
	private View activitydetail_recommend;
	private int pageno = 1;
	private int pagesize = 10;
	private List<LiveUrlListBean.ListBean> phonelivelist;

	/*会员直播*/
	private View member1_recommend;
	private View member2_recommend;
	private View member3_recommend;
	private View member4_recommend;
	private View member5_recommend;
	private View member6_recommend;

	@Override
	protected View initTitleView() {
		return null;
	}

	@Override
	protected View initContentLayout() {
		return View.inflate(mActivity, R.layout.fragment_recommend_queqiao,null);
	}

	@Override
	protected void initView() {
		/*最热直播*/
		/*合作者*/
		rv_partner = (RecyclerView) mContentView.findViewById(R.id.rv_partner_queqiao);
		//设置为横向的recyclerview
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
		linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		rv_partner.setLayoutManager(linearLayoutManager);
		/*LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) rv_partner.getLayoutParams();
		params.width = WindowManager.LayoutParams.WRAP_CONTENT;
		params.height = CommonUtils.dip2px(190);
		rv_partner.setLayoutParams(params);*/
		/*最热直播*/
		hot1_recommend = mContentView.findViewById(R.id.hot1_recommend_queqiao);
		/*会员*/
		member1_recommend = mContentView.findViewById(R.id.member1_recommend);
		member2_recommend = mContentView.findViewById(R.id.member2_recommend);
		member3_recommend = mContentView.findViewById(R.id.member3_recommend);
		member4_recommend = mContentView.findViewById(R.id.member4_recommend);
		member5_recommend = mContentView.findViewById(R.id.member5_recommend);
		member6_recommend = mContentView.findViewById(R.id.member6_recommend);
		/*相亲活动*/
		matchmaking1_recommend = mContentView.findViewById(R.id.matchmaking1_recommend);
		/*相亲活动-更多*/
		tv_more_matchmaking = mContentView.findViewById(R.id.tv_more_matchmaking);
		tv_more_hotlive = mContentView.findViewById(R.id.tv_more_pclive);
		tv_more_phonelive = mContentView.findViewById(R.id.tv_more_phonelive);
		/*详情活动详情*/
		activitydetail_recommend = mContentView.findViewById(R.id.activityPrevue_recommend);
	}

	@Override
	protected void initEvent() {
		hot1_recommend.setOnClickListener(this);
		member1_recommend.setOnClickListener(this);
		matchmaking1_recommend.setOnClickListener(this);

		tv_more_matchmaking.setOnClickListener(this);
		tv_more_hotlive.setOnClickListener(this);
		tv_more_phonelive.setOnClickListener(this);

		activitydetail_recommend.setOnClickListener(this);

	}


	/**
	 * 第一次获取数据，调用onLoad方法
	 */
	@Override
	protected RequestState onLoad() {
		loadPhotoLivelist();

		//合作者
		rv_partner.setAdapter(new PartnerGalleryAdapter(mActivity,new String[]{"1","1","1","1","1","1"}));
		return RequestState.STATE_SUCCESS;
	}
	/*加载手机直播列表*/
	private void loadPhotoLivelist() {
		LiveAPI liveAPI = Http.getInstance().create(LiveAPI.class);
		liveAPI.getLiveUrlList("encoding =utf-8",pageno,pagesize, Constants.LIVETYPE_PHONE).enqueue(new Callback<LiveUrlListBean>() {
			@Override
			public void onResponse(Call<LiveUrlListBean> call, Response<LiveUrlListBean> response) {
				if (response.body().getMsg().equals("true")){
					phonelivelist = response.body().getList();
					showPhoneLiveList();
					Log.e("phonelivelist","has");
				}else {
					Log.e("phonelivelist","null");
				}
			}

			@Override
			public void onFailure(Call<LiveUrlListBean> call, Throwable t) {
				Log.e("nointernet","null");
			}
		});
	}
	/*显示手机直播列表*/
	private void showPhoneLiveList() {

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.matchmaking1_recommend:
				HorizontalLiveActivity.intent(mActivity,null);
				break;
			case R.id.member1_recommend:
				LiveUrlListBean.ListBean data1 = phonelivelist.get(0);
				VerticalLiveActivity.intent(mActivity,data1);
				break;
			case R.id.hot1_recommend_queqiao://相亲1
				HorizontalLiveActivity.intent(mActivity,null);
				break;
			case R.id.tv_more_matchmaking://相亲活动-更多页
				MatchMakingActivity.intent(mActivity,"1");
				break;
			case R.id.tv_more_pclive://最热直播-更多页
				PcLiveActivity.intent(mActivity,"1");
				break;
			case R.id.tv_more_phonelive://直播会员-更多页
				PhoneLiveActivity.intent(mActivity,"1");
				break;
			case R.id.activityPrevue_recommend://相亲活动-详情
				ActivityDetailActivity.intent(mActivity,"1");
				break;
		}
	}
}
