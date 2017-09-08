package com.niuniushop.sjhj.nnshop.http;

import com.niuniushop.sjhj.nnshop.model.ProuctDataModel;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/9/8.
 */

public interface HttpInterface {

    /*
    * 获取商品
    * */
    @GET("/AppShopService/index.php/Home/Index/{action}")
    Observable<List<ProuctDataModel>> getProducts(@Path("action")String action);


}
