package com.queqiaolove.fragment.queqiao;

import android.view.View;
import android.widget.AdapterView;

import com.queqiaolove.R;
import com.queqiaolove.activity.live.horizontal.HorizontalLiveActivity;
import com.queqiaolove.activity.live.vertical.VerticalLiveActivity;
import com.queqiaolove.activity.main.PcLiveActivity;
import com.queqiaolove.activity.main.PhoneLiveActivity;
import com.queqiaolove.activity.main.matchmaking.MatchMakingActivity;
import com.queqiaolove.adapter.main.MatchMakingLiveGvAdapter;
import com.queqiaolove.adapter.queqiao.recommend.RecommendPcLiveGvAdapter;
import com.queqiaolove.adapter.queqiao.recommend.RecommendPhoneLiveGvAdapter;
import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.base.ContentPage;
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.MainAPI;
import com.queqiaolove.javabean.RecommendDataBean;
import com.queqiaolove.javabean.live.LiveUrlListBean;
import com.queqiaolove.widget.MyGridView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by WD on 2016/10/2.
 */
public class LiveFragment extends BaseFragment implements View.OnClickListener {
    private int pageno = 1;
    private int pagesize = 10;
    private View tv_more_pclive;
    private View tv_more_phonelive;
    private View tv_more_matchmaking;//相亲活动更多
    private MyGridView gv_pclive;
    private MyGridView gv_phonelive;
    private MyGridView gv_live_matchmaking;
    private List<LiveUrlListBean.ListBean> phonelivelist;//手机直播列表
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
        return View.inflate(mActivity, R.layout.fragment_live_queqiao,null);
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

    }

    @Override
    protected void initEvent() {
        tv_more_pclive.setOnClickListener(this);
        tv_more_phonelive.setOnClickListener(this);
        tv_more_matchmaking.setOnClickListener(this);
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

                HorizontalLiveActivity.intent(mActivity, bean);
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
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        loadData();
        return ContentPage.RequestState.STATE_SUCCESS;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*pc直播*/
            case R.id.tv_more_pclive:
                PcLiveActivity.intent(mActivity,"1");
                break;
            /*手机直播*/
            case R.id.tv_more_phonelive:
                PhoneLiveActivity.intent(mActivity,"1");
                break;
             /*相亲活动*/
            case R.id.tv_more_matchmaking://更多
                MatchMakingActivity.intent(mActivity, "1");
                break;
        }
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

    }


}
