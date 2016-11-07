package com.queqiaolove.http.api;

import com.queqiaolove.global.Constants;
import com.queqiaolove.javabean.find.ILikeWhoListBean;
import com.queqiaolove.javabean.find.JoinActivityBean;
import com.queqiaolove.javabean.find.LoveTogetherListBean;
import com.queqiaolove.javabean.find.MakemakingActivityDetailBean;
import com.queqiaolove.javabean.find.MakemakingActivityListBean;
import com.queqiaolove.javabean.find.WhoLikeMeListBean;
import com.queqiaolove.javabean.find.WhoLookMeListBean;

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

    /**
     * 发现 - 报名参加活动
     * @param userid
     * @param aid
     * @return
     */
    @FormUrlEncoded
    @POST("api/activity/activity_join/")
    Call<JoinActivityBean> joinActivity(@Field("userid") int userid,
                                        @Field("aid") int aid);

    /**
     * 谁看过我
     * @param userid
     * @param pageno
     * @param pagesize
     * @return
     */
    @FormUrlEncoded
    @POST("api/find/seeme_list/")
    Call<WhoLookMeListBean> wholookmeList(@Field("userid") int userid,
                                          @Field(Constants.PAGENO) int pageno,
                                          @Field(Constants.PAGESIZE) int pagesize);

    /**
     * 谁喜欢我
     * @param userid
     * @param pageno
     * @param pagesize
     * @return
     */
    @FormUrlEncoded
    @POST("api/find/loveme_list/")
    Call<WhoLikeMeListBean> wholikemeList(@Field("userid") int userid,
                                          @Field(Constants.PAGENO) int pageno,
                                          @Field(Constants.PAGESIZE) int pagesize);

    /**
     * 我喜欢谁
     * @param userid
     * @param pageno
     * @param pagesize
     * @return
     */
    @FormUrlEncoded
    @POST("api/find/mylove_list/")
    Call<ILikeWhoListBean> iLikeWhoList(@Field("userid") int userid,
                                        @Field(Constants.PAGENO) int pageno,
                                        @Field(Constants.PAGESIZE) int pagesize);

    /**
     * 互相喜欢
     * @param userid
     * @param pageno
     * @param pagesize
     * @return
     */
    @FormUrlEncoded
    @POST("api/find/mylove_list/")
    Call<LoveTogetherListBean> loveTogetherList(@Field("userid") int userid,
                                                @Field(Constants.PAGENO) int pageno,
                                                @Field(Constants.PAGESIZE) int pagesize);
}
