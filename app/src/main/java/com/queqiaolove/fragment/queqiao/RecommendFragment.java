package com.queqiaolove.fragment.queqiao;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.queqiaolove.util.CommonUtils;
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
    private List<RecommendDataBean.HdspListBean> hdsp_list;
    private List<RecommendDataBean.HdygListBean> hdyg_list;
    /*活动预告*/
    private ImageView iv_cover_video;
    private TextView tv_name_activity;
    private TextView tv_city_activity;
    private TextView tv_day_activity;
    private TextView tv_numofjoin_activity;
    private TextView tv_numoflook_activity;
    private TextView tv_numofpraise_activity;
    private RecommendDataBean.HdygListBean hdygListBean;

    @Override
    protected View initTitleView() {
        return null;
    }

    @Override
    protected View initContentLayout() {
        return View.inflate(mActivity, R.layout._fragment_recommend_queqiao, null);
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
        iv_cover_video = (ImageView) mContentView.findViewById(R.id.iv_cover_video);
        tv_name_activity = (TextView) mContentView.findViewById(R.id.tv_name_activity);
        tv_city_activity = (TextView) mContentView.findViewById(R.id.tv_city_activity);
        tv_day_activity = (TextView) mContentView.findViewById(R.id.tv_day_activity);
        tv_numofjoin_activity = (TextView) mContentView.findViewById(R.id.tv_numofjoin_activity);
        tv_numoflook_activity = (TextView) mContentView.findViewById(R.id.tv_numoflook_activity);
        tv_numofpraise_activity = (TextView) mContentView.findViewById(R.id.tv_numofpraise_activity);
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

                HorizontalLiveActivity.intent(mActivity, "");
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
                VerticalLiveActivity.intent(mActivity, bean);
            }
        });
        //相亲活动
        gv_live_matchmaking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int makemakingposition, long l) {
                RecommendDataBean.HdzbListBean data = hdzb_list.get(makemakingposition);
                LiveUrlListBean.ListBean bean = new LiveUrlListBean.ListBean();
                bean.setBtitle(data.getBtitle());
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
                VerticalLiveActivity.intent(mActivity, bean);
            }
        });
        gv_video_matchmaking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LiveVideoActivity.intent(mActivity, "");
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
    /*加载推荐页数据*/
    private void loadData() {
        MainAPI mainAPI = Http.getInstance().create(MainAPI.class);
        mainAPI.recommendData().enqueue(new Callback<RecommendDataBean>() {
            @Override
            public void onResponse(Call<RecommendDataBean> call, Response<RecommendDataBean> response) {
                RecommendDataBean body = response.body();
                if (response.body().getReturnvalue().equals("true")) {
                    pczb_list = body.getPczb_list();
                    appzb_list = body.getAppzb_list();
                    hdzb_list = body.getHdzb_list();
                    hdsp_list = body.getHdsp_list();
                    hdyg_list = body.getHdyg_list();
                    showView();
                } else {
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
        gv_pclive.setAdapter(new RecommendPcLiveGvAdapter(mActivity, pczb_list));//pc列表
        gv_phonelive.setAdapter(new RecommendPhoneLiveGvAdapter(mActivity, appzb_list));
        gv_live_matchmaking.setAdapter(new MatchMakingLiveGvAdapter(mActivity, hdzb_list));
        gv_video_matchmaking.setAdapter(new MatchMakingVideoGvAdapter(mActivity, hdsp_list));
        showHdyg();

    }
    /*活动预告*/
    private void showHdyg() {
        hdygListBean = hdyg_list.get(0);
        String trailer_pic = hdygListBean.getTrailer_pic();
        String atitle = hdygListBean.getAtitle();
        String city = hdygListBean.getCity();
        String daydiff = hdygListBean.getDaydiff();
        String participant_num = hdygListBean.getParticipant_num();
        String watch_num = hdygListBean.getWatch_num();
        String like_num = hdygListBean.getLike_num();

        tv_name_activity.setText(atitle);
        CommonUtils.loadImage(R.mipmap.ic_default_welfare, iv_cover_video, trailer_pic);
        tv_city_activity.setText(city);
        tv_day_activity.setText(daydiff);
        tv_numofjoin_activity.setText(participant_num);
        tv_numoflook_activity.setText(watch_num);
        tv_numofpraise_activity.setText(like_num);

    }

    @Override
    public View onCreateSuccessView() {

        return mContentView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            /*pc直播*/
            case R.id.tv_more_pclive:
                PcLiveActivity.intent(mActivity, "1");
                break;
            /*手机直播*/
            case R.id.tv_more_phonelive:
                PhoneLiveActivity.intent(mActivity, "1");
                break;
			 /*相亲活动*/
            case R.id.tv_more_matchmaking://更多
                MatchMakingActivity.intent(mActivity, "1");
                break;
            case R.id.activityPrevue_recommend://详情
                String id = hdygListBean.getId();
                ActivityDetailActivity.intent(mActivity, id);
                break;
        }

    }

}
