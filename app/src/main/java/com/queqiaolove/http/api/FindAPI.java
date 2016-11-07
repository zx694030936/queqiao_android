package com.queqiaolove.http.api;

import com.queqiaolove.global.Constants;
import com.queqiaolove.javabean.find.MakemakingActivityDetailBean;
import com.queqiaolove.javabean.find.MakemakingActivityListBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 发现api
 * Created by WD on 2016/8/30.
 */
public interface FindAPI {

    /**
     * 活动列表列表
     * @param userid
     * @param pageno
     * @param pagesize
     * @return
     */
    @FormUrlEncoded
    @POST("api/activity/activity_list/")
    Call<MakemakingActivityListBean> makemakingActivityList(@Field("userid") int userid,
                                                            @Field(Constants.PAGENO) int pageno,
                                                            @Field(Constants.PAGESIZE) int pagesize);

    /**
     * 相亲活动详情
     * @param userid
     * @param aid
     * @return
     */
    @FormUrlEncoded
    @POST("api/activity/activity_info/")
    Call<MakemakingActivityDetailBean> makemakingActivityDetail(@Field("userid") int userid,
                                                                @Field("aid") int aid);
}
