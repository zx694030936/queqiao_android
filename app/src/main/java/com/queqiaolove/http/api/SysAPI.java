package com.queqiaolove.http.api;

import com.queqiaolove.javabean.sys.AttributeListBean;
import com.queqiaolove.javabean.sys.ProviceListBean;

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

    /**
     * 属性列表接口
     * @param atcode    民族101，学历102，月收入103，公司行业104，
     *                  公司性质105，语言106，直播话题107
     * @return
     */
    @FormUrlEncoded
    @POST("api/attribute_list/")
    Call<AttributeListBean> attributelist(@Field("atcode") int atcode);
}
