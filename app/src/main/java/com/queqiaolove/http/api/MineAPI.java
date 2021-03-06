package com.queqiaolove.http.api;

import com.queqiaolove.javabean.BaseBean;
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

    /**
     * 个人-修改教育及工作
     * @param userid
     * @param school
     * @param major
     * @param job
     * @param company_industry  acode值
     * @param company_nature    acode值
     * @param mylanguage    acode值，多个值之间使用英文逗号分隔
     * @return
     */
    @FormUrlEncoded
    @POST("api/user/userjob_upd/")
    Call<BaseBean> changeEduAndWork(@Field("userid") int userid,
                                    @Field("school") String school,
                                    @Field("major") String major,
                                    @Field("job") String job,
                                    @Field("company_industry") String company_industry,
                                    @Field("company_nature") String company_nature,
                                    @Field("mylanguage") String mylanguage);

    /**
     * 个人-修改个人标签
     * @param userid
     * @param label_list    多个标签ID之间用英文逗号分隔（从用户所有标签列表接口获取标签ID）
     * @return
     */
    @FormUrlEncoded
    @POST("api/user/userlabel_upd/")
    Call<BaseBean> changeMyLabel(@Field("userid") int userid,
                                                @Field("label_list") String label_list);

    /**
     * 个人-修改个人详细资料
     * @param userid
     * @param username
     * @param age
     * @param sex
     * @param areaid
     * @param cityid
     * @param nation
     * @param education
     * @param myheight
     * @param myweight
     * @param month_income
     * @param marital_status
     * @param child_status
     * @param buy_house
     * @param buy_car
     * @return
     */
    @FormUrlEncoded
    @POST("api/user/userinfo_upd/")
    Call<BaseBean> changeUserInfoDetail(@Field("userid") int userid,
                                    @Field("username") String username,
                                    @Field("age") String age,
                                    @Field("sex") String sex,
                                    @Field("areaid") String areaid,
                                    @Field("cityid") String cityid,
                                    @Field("nation") String nation,
                                        @Field("education") String education,
                                        @Field("myheight") String myheight,
                                        @Field("myweight") String myweight,
                                        @Field("month_income") String month_income,
                                        @Field("marital_status") String marital_status,
                                        @Field("child_status") String child_status,
                                        @Field("buy_house") String buy_house,
                                        @Field("buy_car") String buy_car);

}
