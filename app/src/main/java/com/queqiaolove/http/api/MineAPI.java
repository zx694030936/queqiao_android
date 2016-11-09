package com.queqiaolove.http.api;

import com.queqiaolove.javabean.mine.ChangeContactWayBean;
import com.queqiaolove.javabean.mine.ChangePwdBean;
import com.queqiaolove.javabean.mine.LoveDeclarationBean;
import com.queqiaolove.javabean.mine.MyPhotoListBean;
import com.queqiaolove.javabean.mine.UploadImageBean;
import com.queqiaolove.javabean.mine.UserBaseInfoBean;
import com.queqiaolove.javabean.mine.UserInfoLabelListbean;
import com.queqiaolove.javabean.mine.UserInfroDetailBean;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

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

    /**
     *  个人-标签列表
     * @return
     */
    @GET("api/sys/label_list/")
    Call<UserInfoLabelListbean> userInfroLabelList();

    /**
     * 上传头像
     * @param file
     * @param userid
     * @param upflag    1头像，2相册
     * @param imgwidth
     * @param imgheight
     * @return
     */
    @Multipart
    @POST("api/user/user_pic_upload/")
    Call<UploadImageBean> uploadImage(@Part("userid") int userid,
                                      @Part("upflag") int upflag,
                                      @Part("imgwidth") int imgwidth,
                                      @Part("imgheight") int imgheight,
                                      @Part("file\";filename=\"usericon.jpg\"") RequestBody file);

    /**
     * 个人 - 相册列表
     * @param userid
     * @param pageno
     * @param pagesize
     * @return
     */
    @FormUrlEncoded
    @POST("api/user/user_pic_list/")
    Call<MyPhotoListBean> myphotolist(@Field("userid") int userid,
                                      @Field("pageno") int pageno,
                                      @Field("pagesize") int pagesize);

    /**
     * 个人-爱情宣言
     * @param userid
     * @param declaration   宣言
     * @return
     */
    @FormUrlEncoded
    @POST("api/user/userdeclaration_upd/")
    Call<LoveDeclarationBean> lovedeclaration(@Field("userid") int userid,
                                              @Field("declaration") String declaration);

    /**
     * 个人 - 自我介绍
     * @param userid
     * @param content
     * @return
     */
    @GET("api/user/usercontent_upd/")
    Call<LoveDeclarationBean> changeMyIntroduce(@Query("userid") int userid,
                                              @Query("content") String content);

    /**
     * 个人-修改联系方式
     * @param userid
     * @param weixin
     * @param qq
     * @return
     */
    @FormUrlEncoded
    @POST("api/user/usercontact_upd/")
    Call<ChangeContactWayBean> changeContactWay(@Field("userid") int userid,
                                                @Field("weixin") String weixin,
                                                @Field("qq") String qq);

}
