package com.queqiaolove.http.api;

import com.queqiaolove.global.Constants;
import com.queqiaolove.javabean.live.LiveUrlListBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 登录和注册api
 * Created by WD on 2016/8/30.
 */
public interface LiveVideoAPI {

    /**
     * 获取直播列表
     * @param encoding
     * @param pageno
     * @param pagesize
     * @param livetype
     * @return
     */
    @FormUrlEncoded
    @POST("api/live/live_list/")
    Call<LiveUrlListBean> getLiveUrlList(@Field("encoding") String encoding,
                                         @Field(Constants.PAGENO) int pageno,
                                         @Field(Constants.PAGESIZE) int pagesize,
                                         @Field(Constants.LIVETYPE) int livetype);
}
