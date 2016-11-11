package com.queqiaolove.http.api;

import com.queqiaolove.javabean.RecommendDataBean;
import com.queqiaolove.javabean.main.PhotoListBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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

    /**
     * 鹊桥-照片列表
     * @param userid
     * @return
     */
    @FormUrlEncoded
    @POST("api/home/match_user_pic/")
    Call<PhotoListBean>photoList(@Field("userid") int userid);

}
