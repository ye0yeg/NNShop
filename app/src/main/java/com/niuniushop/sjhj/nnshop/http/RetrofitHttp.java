package com.niuniushop.sjhj.nnshop.http;

import com.niuniushop.sjhj.nnshop.api.Api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Created by Administrator on 2017/9/8.
 */

public class RetrofitHttp {

    private static HttpInterface singleton;

    public static HttpInterface getRetrofit(OkHttpClient client){
        if(singleton == null){
            synchronized (RetrofitHttp.class){
                singleton = createRetrofit(client).create(HttpInterface.class);
            }
        }
        return singleton;
    }

    private static Retrofit createRetrofit(OkHttpClient client) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

}
