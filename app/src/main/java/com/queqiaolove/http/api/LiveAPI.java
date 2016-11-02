package com.queqiaolove.http.api;

import com.queqiaolove.global.Constants;
import com.queqiaolove.javabean.push.GetPushUrlBean;
import com.queqiaolove.javabean.live.LiveUrlListBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * 登录和注册api
 * Created by WD on 2016/8/30.
 */
public interface LiveAPI {

    /**
     * 获取推流url
     *
     * @param btitle   标题
     * @param saytitle 话题
     * @param map      ticket_price门票，begin_age开始年龄，end_age结束年龄，
     *                 month_income月收入，marital_status婚姻状态，livetype直播方式（1手机，2pc）
     * @return
     */
    @FormUrlEncoded
    @POST("api/live/create_live/")
    Call<GetPushUrlBean> getPushUrl(@Field("encoding") String encoding,
                                    @Field(Constants.BTITLE) String btitle,
                                    @Field(Constants.SAYTITLE) String saytitle,
                                    @QueryMap Map<String, Integer> map);

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
