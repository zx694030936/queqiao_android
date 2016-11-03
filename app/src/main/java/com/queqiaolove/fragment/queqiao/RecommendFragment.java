package com.queqiaolove.fragment.queqiao;

import android.support.v7.widget.RecyclerView;
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
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.MainAPI;
import com.queqiaolove.javabean.RecommendDataBean;
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
	private List<LiveUrlListBean.ListBean> pclivelist;
	private List<RecommendDataBean.PczbListBean> pczb_list;
	private List<RecommendDataBean.AppzbListBean> appzb_list;
	private List<RecommendDataBean.HdzbListBean> hdzb_list;

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
			public void onItemClick(AdapterView<?> adapterView, View view, int pcposition, long l) {
				RecommendDataBean.PczbListBean data = pczb_list.get(pcposition);
				LiveUrlListBean.ListBean bean = new LiveUrlListBean.ListBean();
				bean.setBtitle(data.getBtitle());
				bean.setCity(data.getCity());
				bean.setGroupid(data.getGroupid());
				bean.setId(data.getId());
				bean.setIf_open(data.getIf_open());
				bean.setIsend(data.getIsend());
				bean.setPlay_flv(data.getPlay_flv());
				bean.setPlay_hls(data.getPlay_hls());
				bean.setPlay_rtmp(data.getPlay_rtmp());
				bean.setSaytitle(data.getSaytitle());
				bean.setUsername(data.getUsername());
				bean.setWatch_num(data.getWatch_num());
				bean.setZhibo_fm_pic(data.getZhibo_fm_pic());

				HorizontalLiveActivity.intent(mActivity,bean);
			}
		});
		//手机直播
		gv_phonelive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int phoneposition, long l) {
				RecommendDataBean.AppzbListBean data = appzb_list.get(phoneposition);
				LiveUrlListBean.ListBean bean = new LiveUrlListBean.ListBean();
				bean.setBtitle(data.getBtitle());
				bean.setCity(data.getCity());
				bean.setGroupid(data.getGroupid());
				bean.setId(data.getId());
				bean.setIf_open(data.getIf_open());
				bean.setIsend(data.getIsend());
				bean.setPlay_flv(data.getPlay_flv());
				bean.setPlay_hls(data.getPlay_hls());
				bean.setPlay_rtmp(data.getPlay_rtmp());
				bean.setSaytitle(data.getSaytitle());
				bean.setUsername(data.getUsername());
				bean.setWatch_num(data.getWatch_num());
				bean.setZhibo_fm_pic(data.getZhibo_fm_pic());
				VerticalLiveActivity.intent(mActivity,bean);
			}
		});
		//相亲活动
		gv_live_matchmaking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int makemakingposition, long l) {
				RecommendDataBean.HdzbListBean data = hdzb_list.get(makemakingposition);
				LiveUrlListBean.ListBean bean = new LiveUrlListBean.ListBean();
				bean.setBtitle(data.getBtitle());
				bean.setCity(data.getCity());
				bean.setGroupid(data.getGroupid());
				bean.setId(data.getId());
				bean.setIf_open(data.getIf_open());
				bean.setIsend(data.getIsend());
				bean.setPlay_flv(data.getPlay_flv());
				bean.setPlay_hls(data.getPlay_hls());
				bean.setPlay_rtmp(data.getPlay_rtmp());
				bean.setSaytitle(data.getSaytitle());
				bean.setUsername(data.getUsername());
				bean.setWatch_num(data.getWatch_num());
				bean.setZhibo_fm_pic(data.getZhibo_fm_pic());
				VerticalLiveActivity.intent(mActivity,bean);
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
		/*loadPCLivelist();//加载pc列表
		loadPhotoLivelist();//加载手机列表*/
		loadData();
		//合作者
		//rv_partner.setAdapter(new PartnerGalleryAdapter(mActivity,new String[]{"1","1","1","1","1","1"}));

		return RequestState.STATE_SUCCESS;
	}

	private void loadData() {
		MainAPI mainAPI = Http.getInstance().create(MainAPI.class);
		mainAPI.recommendData().enqueue(new Callback<RecommendDataBean>() {
			@Override
			public void onResponse(Call<RecommendDataBean> call, Response<RecommendDataBean> response) {
				RecommendDataBean body = response.body();
				if (response.body().getReturnvalue().equals("true")){
					pczb_list = body.getPczb_list();
					appzb_list = body.getAppzb_list();
					hdzb_list = body.getHdzb_list();
					showView();
				}else {
					toast("数据异常");

				}
			}

			@Override
			public void onFailure(Call<RecommendDataBean> call, Throwable t) {
				toast("网络数据异常");
			}
		});
	}

	private void showView() {
		gv_pclive.setAdapter(new RecommendPcLiveGvAdapter(mActivity,pczb_list));//pc列表
		gv_phonelive.setAdapter(new RecommendPhoneLiveGvAdapter(mActivity,appzb_list));
		gv_live_matchmaking.setAdapter(new MatchMakingLiveGvAdapter(mActivity,hdzb_list));
	}

	@Override
	public View onCreateSuccessView() {
		gv_video_matchmaking.setAdapter(new MatchMakingVideoGvAdapter(mActivity));
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
	/*加载pc直播列表*//*
	private void loadPCLivelist() {
		LiveAPI liveAPI = Http.getInstance().create(LiveAPI.class);
		liveAPI.getLiveUrlList("utf-8",pageno,pagesize, Constants.LIVETYPE_PC).enqueue(new Callback<LiveUrlListBean>() {
			@Override
			public void onResponse(Call<LiveUrlListBean> call, Response<LiveUrlListBean> response) {
				if (response.body().getReturnvalue().equals("true")){
					pclivelist = response.body().getList();
					Log.e("pclivelist",pclivelist.size()+"");
					gv_pclive.setAdapter(new RecommendPcLiveGvAdapter(mActivity,pclivelist));//pc列表

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
	*//*加载手机直播列表*//*
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

				}else {
					toast("数据异常");

				}
			}

			@Override
			public void onFailure(Call<LiveUrlListBean> call, Throwable t) {
				toast("网络数据异常");
			}
		});
	}*/
}
