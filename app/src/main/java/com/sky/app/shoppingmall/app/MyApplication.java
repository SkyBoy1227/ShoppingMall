package com.sky.app.shoppingmall.app;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created with Android Studio.
 * 描述: 整个软件
 * Date: 2018/7/27
 * Time: 15:54
 *
 * @author 晏琦云
 * @version ${VERSION}
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initOkHttpClient();
    }

    /**
     * 初始化OkHttpUtils
     */
    private void initOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                // 其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
