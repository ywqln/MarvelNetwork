# MarvelNetwork
marvel network demo


## 如何使用

app gradle 中添加以下代码

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
    implementation 'com.marvel:network:1.0.1'
}
```
<br/>

---

<br/>

## 认识 marvel network
项目中难免会有多方接口，那就意味着多种baseUrl，多种Header，多种数据结构。而我们在使用过程中对Retrofit切换baseUrl，Header频繁起来，会觉得不那么舒服，所以这方面需要设计下<br/>

marvel network使用注解的方式来描述接口的interface声明文件，统一来管理一个interface下的所有接口都使用同一个baseUrl，同一个Header。<br/>

在项目中，baseUrl也可能是从一个接口中获取到的，注解上要求字符串的值是常量，考虑到这种情况，marvel network提供了动态的baseUrl设置，设置方式如下：
``` java
@BaseUrl(dynamic = NewsBaseUrlInterceptor.class)
public interface NewsApi {
    // 略...
}
```
NewsBaseUrlInterceptor文件，`NewsBaseUrlInterceptor`必须是一个实现BaseUrlInterceptor接口的类，并实现`void getBaseUrl()`方法
``` java
public class NewsBaseUrlInterceptor implements BaseUrlInterceptor {
    @Override
    public String getBaseUrl() {
        return "http://toutiao-ali.juheapi.com";
    }
}
```

如果你想直接设置baseUrl，不需要动态，可以按以下方式设置：

``` java
@BaseUrl("http://toutiao-ali.juheapi.com")
public interface NewsApi {
    // 略...
}
```

**默认提供的一些工具**
- `ApiThreadTransformer`线程切换，在io线程发起请求，在main线程处理数据。
- `ResponseObserver`继承`Observer`后重写了onError，对错误做了重新定义，通过网络状态码来转为中文消息，且不吞没状态码。对于成功响应定义为数据返回且业务处理正常，失败定义为网络层出错或服务器处理出错。
- `SimpleObserver`继承自`ResponseObserver`，但无需实现`onSubscribe`和`onComplete`。当无需在发起请求和完成请求时处理的时候，可选择。

<br/>

---

<br/>



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
<br/>

---

<br/>

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
                        NetLog.json(new Gson().toJson(result.getData()));
                    }

                    @Override
                    protected void onFail(ResponseException responseException) {
                        NetLog.e(responseException.message);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        NetLog.p("开始发送请求...");
                    }

                    @Override
                    public void onComplete() {
                        NetLog.p("请求处理完成...");
                    }
                });
```