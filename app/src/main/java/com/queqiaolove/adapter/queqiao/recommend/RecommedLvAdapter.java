package com.queqiaolove.adapter.queqiao.recommend;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.queqiaolove.R;
import com.queqiaolove.activity.live.horizontal.HorizontalLiveActivity;
import com.queqiaolove.activity.live.vertical.VerticalLiveActivity;
import com.queqiaolove.activity.main.PcLiveActivity;
import com.queqiaolove.activity.main.PhoneLiveActivity;
import com.queqiaolove.activity.main.matchmaking.ActivityDetailActivity;
import com.queqiaolove.activity.main.matchmaking.MatchMakingActivity;
import com.queqiaolove.adapter.main.PhoneLiveGvAdapter;
import com.queqiaolove.adapter.queqiao.PartnerGalleryAdapter;
import com.queqiaolove.global.Constants;
import com.queqiaolove.http.Http;
import com.queqiaolove.http.api.LiveAPI;
import com.queqiaolove.javabean.live.LiveUrlListBean;
import com.queqiaolove.widget.MyGridView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LENOVO on 2016/10/25.
 */
public class RecommedLvAdapter extends BaseAdapter implements View.OnClickListener {
    private final Activity mActivity;
    private int pageno = 1;
    private int pagesize = 10;
    private RecyclerView rv_partner;//合作加盟
    private View tv_more_matchmaking;
    private View tv_more_pclive;
    private View tv_more_phonelive;
    private View activityPrevue_recommend;

    private MyGridView gv_pclive;
    private MyGridView gv_phonelive;
    private List<LiveUrlListBean.ListBean> phonelivelist;
    public RecommedLvAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View mContentView =null;
        switch (position){
            case 0://轮播图
                mContentView = View.inflate(mActivity, R.layout.lvitem_recommend_banner,null);
                break;
            case 1://pc直播
                mContentView = View.inflate(mActivity, R.layout.lvitem_recommend_pclive,null);
                tv_more_pclive = mContentView.findViewById(R.id.tv_more_pclive);
                gv_pclive = (MyGridView) mContentView.findViewById(R.id.gv_pclive);
                gv_pclive.setAdapter(new RecommendPcLiveGvAdapter(mActivity));
                gv_pclive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        HorizontalLiveActivity.intent(mActivity,"");
                    }
                });
                tv_more_pclive.setOnClickListener(this);
                break;
            case 2://手机直播
                mContentView = View.inflate(mActivity, R.layout.lvitem_recommend_phonelive,null);
                tv_more_phonelive = mContentView.findViewById(R.id.tv_more_phonelive);
                gv_phonelive = (MyGridView) mContentView.findViewById(R.id.gv_phonelive);
                loadPhotoLivelist();//加载手机直播列表
                gv_phonelive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int phoneposition, long l) {
                        LiveUrlListBean.ListBean data = phonelivelist.get(phoneposition);
                        VerticalLiveActivity.intent(mActivity,data);
                    }
                });
                tv_more_phonelive.setOnClickListener(this);
                break;
            case 3://相亲活动
                mContentView = View.inflate(mActivity, R.layout.lvitem_recommend_matchmaking,null);
                tv_more_matchmaking = mContentView.findViewById(R.id.tv_more_matchmaking);
                /*详情活动预告*/
                activityPrevue_recommend = mContentView.findViewById(R.id.activityPrevue_recommend);
                tv_more_matchmaking.setOnClickListener(this);
                activityPrevue_recommend.setOnClickListener(this);
                break;
            case 4://合作者
                mContentView = View.inflate(mActivity, R.layout.lvitem_recommend_partner,null);
                rv_partner = (RecyclerView) mContentView.findViewById(R.id.rv_partner_queqiao);
                //设置为横向的recyclerview
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                rv_partner.setLayoutManager(linearLayoutManager);
                rv_partner.setAdapter(new PartnerGalleryAdapter(mActivity,new String[]{"1","1","1","1","1","1"}));
                break;
        }

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
                    gv_phonelive.setAdapter(new PhoneLiveGvAdapter(mActivity,phonelivelist));
                }else {
                    //toast("数据异常");

                }
            }

            @Override
            public void onFailure(Call<LiveUrlListBean> call, Throwable t) {
                //toast("网络数据异常");
            }
        });
    }
}
