package com.queqiaolove.http.api;

import com.queqiaolove.javabean.ProviceListBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 登录和注册api
 * Created by WD on 2016/8/30.
 */
public interface SysAPI {

    /**
     * 省列表接口
     * @param encoding
     * @param isshow
     * @return
     */
    @FormUrlEncoded
    @POST("api/sys/province/")
    Call<ProviceListBean> provinceList(@Field("encoding") String encoding,
                                       @Field("isshow") int isshow);
}
