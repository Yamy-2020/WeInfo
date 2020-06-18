package com.example.weinfo.util;

import android.util.Log;
import com.example.weinfo.base.Constants;
import com.example.weinfo.net.ApiService;
import com.example.weinfo.net.LoginService;
import com.example.weinfo.net.WanService;
import com.example.weinfo.net.ZhiHuService;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtil {
    //rxjava+retrofit+ok
    //单例
    //数据缓存,日志,请求头(开发一般是固定的,token...)
    //token,身份令牌

    //private final Retrofit mRetrofit;
    private Retrofit.Builder mRetrofitBuilder;

    private HttpUtil() {
        //1.获取一个OkHttpClient
        OkHttpClient mOkHttpClient = getOkHttpClient();
        //2.获取到一个Retrofit.Builder
        getRetrofit(mOkHttpClient);
    }

    private void getRetrofit(OkHttpClient okHttpClient) {
        mRetrofitBuilder = new Retrofit.Builder()
                .client(okHttpClient)//使用自定义的更强大的ok代替原来的弱鸡ok
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    //volatile
    private static volatile HttpUtil instance;

    //Double checked lock
    public static HttpUtil getInstance() {
        if (instance == null) {
            synchronized (HttpUtil.class) {
                if (instance == null) {
                    instance = new HttpUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 固定模板
     * 创建带缓存的OkhttpClient
     * 1.添加了缓存
     * 2.添加了日志拦截器
     *
     * @return
     */
    private OkHttpClient getOkHttpClient() {
        //设置本地缓存文件
        File cacheFile = new File(Constants.PATH_CACHE);
        //设置缓存文件大小
        //1 M = 1024Kb = 1024 * 1024 byte
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(cache)//缓存
                .addInterceptor(new MyCacheinterceptor())
                .addNetworkInterceptor(new MyCacheinterceptor())
                //添加了请求头拦截器之后,所有使用网络框架的网络请求都会添加拦截器中的请求头
                //不需要请求头的也加了请求头,是不影响请求
                //.addInterceptor(new HeadersInterceptor())
                //设置写入时间
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                //设置错误重连
                .retryOnConnectionFailure(true);
        //如果是debug状态(码农调试应用),添加日志拦截器,
        // 如果是正式上线了isDebug该false,就不打印日志
        if (Constants.isDebug) {
            builder.addInterceptor(new LoggingInterceptor());
        }
        return builder.build();
    }

    /**
     * 请求的修改设置
     */
/*    public static class HeadersInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            //sp:shareprefrences
            String token = (String) SpUtil.getParam(Constants.TOKEN,"");
            //LogUtils.print("token:"+token);
            Request request = chain.request().newBuilder()
                    .addHeader("Accept-Encoding", "identity")
                    .build();
            return chain.proceed(request);
        }
    }*/

    /**
     * 固定模板
     */
    private class MyCacheinterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //这里就是说判读我们的网络条件，要是有网络的话我么就直接获取网络上
            // 面的数据，要是没有网络的话我么就去缓存里面取数据
            if (!SystemUtil.isNetworkConnected()) {
                request = request
                        .newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)//强制使用缓存
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (SystemUtil.isNetworkConnected()) {
                int maxAge = 0;
                // 有网络时, 不缓存, 最大保存时长为0
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
                //s秒
                int maxStale = 60 * 60 * 24;
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        //这里的设置的是我们的没有网络的缓存时间，
                        // 想设置多少就是多少。
                        .header("Cache-Control", "public, only-if-cached," +
                                " max-age=" + maxStale)
                        .build();
            }

        }
    }

    //日志拦截器
    public class LoggingInterceptor implements Interceptor {
        private static final String TAG = "LoggingInterceptor";

        @Override
        public Response intercept(Chain chain) throws IOException {
            //通过系统时间的差打印接口请求的信息
            long time = System.nanoTime();
            Request request = chain.request();
            StringBuilder sb = new StringBuilder();
            //把get和post请求提交的参数打印出来
            if ("GET".equals(request.method())) { // GET方法
                HttpUrl httpUrl = request.url().newBuilder().build();
                sb.append("GET,");
                // 打印所有get参数
                Set<String> paramKeys = httpUrl.queryParameterNames();
                for (String key : paramKeys) {
                    String value = httpUrl.queryParameter(key);
                    sb.append(key + ":" + value + ",");
                }

            } else if ("POST".equals(request.method())) { // POST方法
                sb.append("POST,");
                // FormBody和url不太一样，若需添加公共参数，需要新建一个构造器
                /*FormBody.Builder bodyBuilder = new FormBody.Builder();
                // 把已有的post参数添加到新的构造器
                if (request.body() instanceof FormBody) {
                    FormBody formBody = (FormBody) request.body();
                    for (int i = 0; i < formBody.size(); i++) {
                        bodyBuilder.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                    }
                }

                // 这里可以添加一些公共post参数
                bodyBuilder.addEncoded("key_xxx", "value_xxx");
                FormBody newBody = bodyBuilder.build();

                // 打印所有post参数
                for (int i = 0; i < newBody.size(); i++) {
                    Log.d("TEST", newBody.name(i) + " " + newBody.value(i));
                }*/

                if (request.body() instanceof FormBody) {
                    FormBody formBody = (FormBody) request.body();
                    for (int i = 0; i < formBody.size(); i++) {
                        sb.append(formBody.name(i)+":"+formBody.value(i)+",");
                    }
                }

            }

            LogUtil.logd("Request:", String.format("Sending request %s %n %s %n%s", request.url(), sb.toString(),request.headers()));
            Response response = chain.proceed(request);
            long now = System.nanoTime();
            LogUtil.logd("Received:", String.format("Received response for %s in %.1fms%n%s", response.request().url(), (now - time) / 1e6d, response.headers()));


            //response.body().string(); 只能使用一次,使用之后就关流了
            LogUtil.logd("Data:", response.peekBody(4096).string());
            return response;
        }
    }

    volatile ApiService mApiService;
    //创建Retrofit请求数据接口,以前这么写是因为baseurl不固定
    public ApiService getApiService(){
        if (mApiService == null){
            synchronized (HttpUtil.class){
                if (mApiService == null){
                    mApiService = mRetrofitBuilder.baseUrl(ApiService.url)
                            .build()
                            .create(ApiService.class);
                }
            }
        }
        return mApiService;
    }

    volatile LoginService mLoginService;
    public LoginService getLoginService(){
        if (mLoginService == null){
            synchronized (HttpUtil.class){
                if (mLoginService == null){
                    mLoginService = mRetrofitBuilder.baseUrl(LoginService.sUrl)
                            .build()
                            .create(LoginService.class);
                }
            }
        }
        return mLoginService;
    }
    volatile ZhiHuService mZhiHuService;
    public ZhiHuService getZhiHuService(){
        if (mZhiHuService == null){
            synchronized (HttpUtil.class){
                if (mZhiHuService == null){
                    mZhiHuService = mRetrofitBuilder.baseUrl(ZhiHuService.sBaseUrl)
                            .build()
                            .create(ZhiHuService.class);
                }
            }
        }
        return mZhiHuService;
    }
    volatile WanService mWanService;
    public WanService getWanService(){
        if (mWanService == null){
            synchronized (HttpUtil.class){
                if (mWanService == null){
                    mWanService = mRetrofitBuilder.baseUrl(WanService.sUrl)
                            .build()
                            .create(WanService.class);
                }
            }
        }
        return mWanService;
    }
    //简单的日志拦截器
    class SimpleLogInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            Log.d("SimpleLogInterceptor", "url: "+request.url()+
                    ",data:"+response.peekBody(4096).string());
            return response;
        }
    }
}
