package com.queqiaolove.fragment.queqiao;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.queqiaolove.R;
import com.queqiaolove.activity.live.horizontal.HorizontalLiveActivity;
import com.queqiaolove.activity.live.vertical.VerticalLiveActivity;
import com.queqiaolove.activity.livevideo.LiveVideoActivity;
import com.queqiaolove.activity.main.PcLiveActivity;
import com.queqiaolove.activity.main.PhoneLiveActivity;
import com.queqiaolove.activity.main.matchmaking.ActivityDetailActivity;
import com.queqiaolove.activity.main.matchmaking.MatchMakingActivity;
import com.queqiaolove.adapter.main.MatchMakingLiveGvAdapter;
import com.queqiaolove.adapter.main.MatchMakingVideoGvAdapter;
import com.queqiaolove.adapter.queqiao.recommend.RecommendPcLiveGvAdapter;
import com.queqiaolove.adapter.queqiao.recommend.RecommendPhoneLiveGvAdapter;
import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.base.ContentPage.RequestState;
import com.queqiaolove.global.Constants;
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.LiveAPI;
import com.queqiaolove.javabean.live.LiveUrlListBean;
import com.queqiaolove.widget.MyGridView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*鹊桥推荐页*/
public class RecommendFragment extends BaseFragment implements View.OnClickListener {

	private int pageno = 1;
	private int pagesize = 10;
	private RecyclerView rv_partner;//合作加盟
	private View tv_more_pclive;//pc直播更多
	private View tv_more_phonelive;//手机直播更多
	private View tv_more_matchmaking;//相亲活动更多
	private View activityPrevue_recommend;//相亲活动预告

	private MyGridView gv_pclive;//pc直播列表
	private MyGridView gv_phonelive;//手机直播列表
	private List<LiveUrlListBean.ListBean> phonelivelist;//手机直播列表list
	private MyGridView gv_live_matchmaking;
	private MyGridView gv_video_matchmaking;

	@Override
	protected View initTitleView() {
		return null;
	}

	@Override
	protected View initContentLayout() {
		return View.inflate(mActivity, R.layout._fragment_recommend_queqiao,null);
	}

	@Override
	protected void initView() {
		/*pc直播*/
		tv_more_pclive = mContentView.findViewById(R.id.tv_more_pclive);
		gv_pclive = (MyGridView) mContentView.findViewById(R.id.gv_pclive);

		/*手机直播*/
		tv_more_phonelive = mContentView.findViewById(R.id.tv_more_phonelive);
		gv_phonelive = (MyGridView) mContentView.findViewById(R.id.gv_phonelive);

		/*相亲活动*/
		tv_more_matchmaking = mContentView.findViewById(R.id.tv_more_matchmaking);
		gv_live_matchmaking = (MyGridView) mContentView.findViewById(R.id.gv_live_matchmaking);
		gv_video_matchmaking = (MyGridView) mContentView.findViewById(R.id.gv_video_matchmaking);
		/*详情活动预告*/
		activityPrevue_recommend = mContentView.findViewById(R.id.activityPrevue_recommend);
		//合作者
		/*rv_partner = (RecyclerView) mContentView.findViewById(R.id.rv_partner_queqiao);
		//设置为横向的recyclerview
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
		linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		rv_partner.setLayoutManager(linearLayoutManager);*/


	}

	@Override
	protected void initEvent() {
		tv_more_pclive.setOnClickListener(this);
		tv_more_phonelive.setOnClickListener(this);
		tv_more_matchmaking.setOnClickListener(this);
		activityPrevue_recommend.setOnClickListener(this);
		//pc直播
		gv_pclive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				HorizontalLiveActivity.intent(mActivity,"");
			}
		});
		//手机直播
		gv_phonelive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int phoneposition, long l) {
				LiveUrlListBean.ListBean data = phonelivelist.get(phoneposition);
				VerticalLiveActivity.intent(mActivity,data);
			}
		});
		//相亲活动
		gv_live_matchmaking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int makemakingposition, long l) {
				LiveUrlListBean.ListBean data = phonelivelist.get(makemakingposition);
				VerticalLiveActivity.intent(mActivity,data);
			}
		});
		gv_video_matchmaking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				LiveVideoActivity.intent(mActivity,"");
			}
		});

	}


	/**
	 * 第一次获取数据，调用onLoad方法
	 */
	@Override
	protected RequestState onLoad() {
		gv_pclive.setAdapter(new RecommendPcLiveGvAdapter(mActivity));//pc列表
		loadPhotoLivelist();//加载手机列表
		//合作者
		//rv_partner.setAdapter(new PartnerGalleryAdapter(mActivity,new String[]{"1","1","1","1","1","1"}));

		return RequestState.STATE_SUCCESS;
	}

	@Override
	public View onCreateSuccessView() {

		return mContentView;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
            /*pc直播*/
			case R.id.tv_more_pclive:
				PcLiveActivity.intent(mActivity,"1");
				break;
            /*手机直播*/
			case R.id.tv_more_phonelive:
				PhoneLiveActivity.intent(mActivity,"1");
				break;
			 /*相亲活动*/
			case R.id.tv_more_matchmaking:
				MatchMakingActivity.intent(mActivity,"1");
				break;
			case R.id.activityPrevue_recommend:
				ActivityDetailActivity.intent(mActivity,"1");
				break;
		}

	}
	/*加载手机直播列表*/
	private void loadPhotoLivelist() {
		LiveAPI liveAPI = Http.getInstance().create(LiveAPI.class);
		liveAPI.getLiveUrlList("utf-8",pageno,pagesize, Constants.LIVETYPE_PHONE).enqueue(new Callback<LiveUrlListBean>() {
			@Override
			public void onResponse(Call<LiveUrlListBean> call, Response<LiveUrlListBean> response) {
				if (response.body().getReturnvalue().equals("true")){
					phonelivelist = response.body().getList();
					Log.e("phonelivelist",phonelivelist.size()+"");
					gv_phonelive.setAdapter(new RecommendPhoneLiveGvAdapter(mActivity,phonelivelist));
					gv_live_matchmaking.setAdapter(new MatchMakingLiveGvAdapter(mActivity,phonelivelist));
					gv_video_matchmaking.setAdapter(new MatchMakingVideoGvAdapter(mActivity));

				}else {
					toast("数据异常");

				}
			}

			@Override
			public void onFailure(Call<LiveUrlListBean> call, Throwable t) {
				toast("网络数据异常");
			}
		});
	}
}
