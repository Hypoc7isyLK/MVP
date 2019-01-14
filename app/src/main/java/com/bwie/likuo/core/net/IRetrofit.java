package com.bwie.likuo.core.net;

import com.bwie.likuo.bean.BannerBean;
import com.bwie.likuo.bean.LoginBean;
import com.bwie.likuo.bean.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * date:2019/1/7
 * author:李阔(淡意衬优柔)
 * function:
 */
public interface IRetrofit {

    @POST("user/v1/register")
    @FormUrlEncoded
    Observable<Result> showRegister(@Field("phone") String phone,@Field("pwd") String pwd);

    @POST("user/v1/login")
    @FormUrlEncoded
    Observable<Result<LoginBean>> showLogin(@Field("phone") String phone, @Field("pwd") String pwd);

    @GET("commodity/v1/bannerShow")
    Observable<Result<List<BannerBean>>> showBanner();
}
