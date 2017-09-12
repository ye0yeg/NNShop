package com.niuniushop.sjhj.nnshop.model;

import android.content.Context;

import com.niuniushop.sjhj.nnshop.http.RetrofitHttp;
import com.niuniushop.sjhj.nnshop.ui.db.DBManager;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 首页的业务逻辑
 * Created by Administrator on 2017/9/6.
 */

public class RecommendModel {


    /*

    ProuductDataModel
    * 从服务器或者网站获取数据的时候，使用okhttp x rxjava x retrofit
    * */
    public static void getProductsDataFromNet(Subscriber<List<ProuctDataModel>> subscriber) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        RetrofitHttp.getRetrofit(builder.build()).getProducts("getProducts")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static void getRecommendBanners(Subscriber<List<BannerDataModel>> subscriber) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        RetrofitHttp.getRetrofit(builder.build()).getBanners("getBanners")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 返回一个被观察者对象
     *
     * @param context
     * @return
     */
    public static Observable<List<RecommendContentModel>> getProductsFromDB(final Context context) {
        return Observable.create(new Observable.OnSubscribe<List<RecommendContentModel>>() {
            @Override
            public void call(Subscriber<? super List<RecommendContentModel>> subscriber) {
                //订阅者回调行为
                subscriber.onNext(DBManager.getManager(context).getRecommendContentsFromDB());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }



}
