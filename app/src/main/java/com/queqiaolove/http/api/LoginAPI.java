package com.queqiaolove.http.api;

import com.queqiaolove.javabean.login.GetSmsCodeBean;
import com.queqiaolove.javabean.login.LoginBean;
import com.queqiaolove.javabean.login.RegistBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by LENOVO on 2016/10/25.
 */
public interface LoginAPI {

    /***
     * api/regist/regist
     * 注册
     * @param mobile
     * @param password1
     * @param password2
     * @param yqcode    邀请码
     * @param yzm   验证码
     * @param nickname  昵称
     * @return
     */
    @GET("a/regist/regist/")
    Call<RegistBean>regist(@Query("mobile") String mobile,
                                @Query("password1") String password1,
                                @Query("password2") String password2,
                                @Query("yqcode") String yqcode,
                                @Query("yzm") String yzm,
                                @Query("nickname") String nickname);

    /**
     * 注册第二步
     * @param onemap    手机号码、手机号码、登陆密码、确认密码、邀请码
     * @param twomap    1 男，2 女，省份ID、城市ID、身高（单位cm，不能大于250）、学历ID、婚姻状况（1未婚，2离异）
     * @return
     */
    @FormUrlEncoded
    @POST("api/user/login/")
    Call<RegistBean>registSecond(@QueryMap Map<String, String> onemap,
                                 @QueryMap Map<String, Integer> twomap);

    /**
     * 登录
     * @param uname
     * @param upass 密码
     * @return
     */
    @FormUrlEncoded
    @POST("api/user/login/")
    Call<LoginBean>login(@Field("uname") String uname,
                         @Field("upass") String upass);

    /**
     * 获取短息验证码
     * @param mobile
     * @param if_regist 是否是注册时候调用，1表示注册调用，2表示忘记密码时调用，
     *                  3表示修改手机号时调用，4表示修改密码
     * @return
     */
    @FormUrlEncoded
    @POST("api/regist/send_yzm/")
    Call<GetSmsCodeBean>getSmsCode(@Field("mobile") String mobile,
                                   @Field("if_regist") int if_regist);
}
