package com.queqiaolove.http.api;

import com.queqiaolove.javabean.mine.ChangePwdBean;
import com.queqiaolove.javabean.mine.UserBaseInfoBean;
import com.queqiaolove.javabean.mine.UserInfroDetailBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by LENOVO on 2016/10/25.
 */
public interface MineAPI {

    /**
     * 个人-修改密码
     * @param mobile
     * @param upass
     * @param yzm
     * @return
     */
    @FormUrlEncoded
    @POST("api/user/updpass/")
    Call<ChangePwdBean> chagePwd(@Field("mobile") String mobile,
                                 @Field("upass") String upass,
                                 @Field("yzm") String yzm);

    /**
     * 个人-基本信息
     * @param userid
     * @return
     */
    @FormUrlEncoded
    @POST("api/user/userinfo_base/")
    Call<UserBaseInfoBean> userBaseInfo(@Field("userid") int userid);
    /**
     * 个人-个人资料
     * @param userid
     * @return
     */
    @FormUrlEncoded
    @POST("api/user/userinfo/")
    Call<UserInfroDetailBean> userInfoDetail(@Field("userid") int userid);
}
