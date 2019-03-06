package com.marvel.marvelnetwork;

import com.marvel.marvelnetwork.response.NewsResp;
import com.marvel.marvelnetwork.response.model.NewsResult;
import com.marvel.network.annotation.BaseUrl;
import com.marvel.network.annotation.Interceptors;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 描述:新闻相关接口.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/21
 */
@BaseUrl(value = "http://toutiao-ali.juheapi.com")
@Interceptors(NewsHeaderInterceptor.class)
public interface NewsApi {

    /**
     * 获取新闻列表
     *
     * @param type 可选参数：top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),
     *             tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
     */
    @GET("/toutiao/index")
    Observable<NewsResp<NewsResult>> getNews(@Query("type") String type);
}


