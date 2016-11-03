package com.queqiaolove.http.api;

import com.queqiaolove.javabean.RecommendDataBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by LENOVO on 2016/10/25.
 */
public interface MainAPI {

    /**
     * 推荐首页列表
     * @return
     */
    @GET("api/home/home_index/")
    Call<RecommendDataBean> recommendData();


}
