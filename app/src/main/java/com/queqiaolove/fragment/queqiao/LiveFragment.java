package com.queqiaolove.fragment.queqiao;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.queqiaolove.R;
import com.queqiaolove.activity.live.horizontal.HorizontalLiveActivity;
import com.queqiaolove.activity.live.vertical.VerticalLiveActivity;
import com.queqiaolove.activity.main.PcLiveActivity;
import com.queqiaolove.activity.main.PhoneLiveActivity;
import com.queqiaolove.adapter.main.PhoneLiveGvAdapter;
import com.queqiaolove.adapter.queqiao.recommend.PcLiveGvAdapter;
import com.queqiaolove.base.BaseFragment;
import com.queqiaolove.base.ContentPage;
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
 * Created by WD on 2016/10/2.
 */
public class LiveFragment extends BaseFragment implements View.OnClickListener {
    private int pageno = 1;
    private int pagesize = 10;
    private View tv_more_pclive;
    private View tv_more_phonelive;
    private MyGridView gv_pclive;
    private MyGridView gv_phonelive;
    private List<LiveUrlListBean.ListBean> phonelivelist;//手机直播列表
    private List<LiveUrlListBean.ListBean> pclivelist;

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
        gv_pclive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LiveUrlListBean.ListBean bean = pclivelist.get(i);
                HorizontalLiveActivity.intent(mActivity,bean);
            }
        });
        /*手机直播*/
        tv_more_phonelive = mContentView.findViewById(R.id.tv_more_phonelive);
        gv_phonelive = (MyGridView) mContentView.findViewById(R.id.gv_phonelive);
        gv_phonelive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int phoneposition, long l) {
                LiveUrlListBean.ListBean data = phonelivelist.get(phoneposition);
                VerticalLiveActivity.intent(mActivity,data);
            }
        });

    }

    @Override
    protected void initEvent() {
        tv_more_pclive.setOnClickListener(this);
        tv_more_phonelive.setOnClickListener(this);
    }

    @Override
    protected ContentPage.RequestState onLoad() {
        loadPCLivelist();
        loadPhotoLivelist();//jia加载手机列表
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
        }
    }
    /*加载pc直播列表*/
    private void loadPCLivelist() {
        LiveAPI liveAPI = Http.getInstance().create(LiveAPI.class);
        liveAPI.getLiveUrlList("utf-8",pageno,pagesize, Constants.LIVETYPE_PC).enqueue(new Callback<LiveUrlListBean>() {
            @Override
            public void onResponse(Call<LiveUrlListBean> call, Response<LiveUrlListBean> response) {
                if (response.body().getReturnvalue().equals("true")){
                    pclivelist = response.body().getList();
                    Log.e("pclivelist",pclivelist.size()+"");
                    gv_pclive.setAdapter(new PcLiveGvAdapter(mActivity,pclivelist));//pc列表

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
