package com.yl.crazy.mydongtest;

import android.app.Application;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.rest.RequestQueue;

/**
 * Created by Administrator on 2017/10/30.
 */

public class InitApp extends Application {
    //请求队列
    private static RequestQueue mRequestQueue;
    public static RequestQueue getmRequestQueue(){
        return mRequestQueue;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this, new NoHttp.Config()
                .setNetworkExecutor(new OkHttpNetworkExecutor())
                .setConnectTimeout(10000));
        mRequestQueue = NoHttp.newRequestQueue();
    }
}
