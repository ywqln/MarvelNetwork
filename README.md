# MarvelNetwork
marvel network demo

## 认识 marvel network
项目中难免会有多方接口，意味着多个baseUrl，多个Header，多种数据结构。
而我们在使用过程中一个retrofit，方便的切换baseUrl，Header就会感觉不太舒服了，
marvel network使用注解的方式来描述接口声明的interface文件，统一来管理一个interface
下的所有接口都使用同一个baseUrl，同一个Header。
在项目中，baseUrl也可能是从一个接口中获取到的，注解上要求字符串的值是常量，这种情况下，
marvel network提供了动态的baseUrl设置，设置方式：
`@BaseUrl(dynamic = NewsBaseUrlInterceptor.class)`
NewsBaseUrlInterceptor必须是一个实现BaseUrlInterceptor接口的类，并实现`void getBaseUrl()`方法

## 如何使用

在你的 app gradle 中添加以下代码

``` java

apply plugin: 'com.android.application'

android {
    // 略...
}

repositories {
    maven { url "https://github.com/ywqln/marvel-network/raw/master" }
}

dependencies {
    // 略...
    implementation 'com.marvel:network:1.0.0'
}

```

## 如何定义一个api

`@BaseUrl`用在整个interface，表示但凡是在整个interface中声明的接口，他们的baseUrl都是一样的。同理，`@Interceptors`也是一样。

``` java
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
```

## 如何发起网络请求

``` java
Requestor.instance()
                .getApi(NewsApi.class)
                .getNews("yule")
                .compose(new ApiThreadTransformer<>())
                .compose(new NewsTransformer<>())
                .subscribe(new ResponseObserver<NewsResult>() {
                    @Override
                    protected void onSuccess(NewsResult result) {
                        WLog.json(new Gson().toJson(result.getData()));
                    }

                    @Override
                    protected void onFail(ResponseException responseException) {
                        WLog.e(responseException.message);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        WLog.p("开始发送请求...");
                    }

                    @Override
                    public void onComplete() {
                        WLog.p("请求处理完成...");
                    }
                });
```