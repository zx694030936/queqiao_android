package com.queqiaolove.http;

/**
 * Created by WD on 2016/8/30.
 */

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class Http {
    //服务器地址
    private static final String API_URL = "http://182.92.213.51:1070/";
    private static Retrofit retrofit;

    public static Retrofit getInstance(){
        if (retrofit != null) {
            return retrofit;
        } else {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(30, TimeUnit.SECONDS);
            builder.readTimeout(30, TimeUnit.SECONDS);
            builder.writeTimeout(30,TimeUnit.SECONDS);
            OkHttpClient client = builder.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit;
        }

    }
}
