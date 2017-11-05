package com.yl.crazy.mydongtest.model;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import com.yl.crazy.mydongtest.Constant;
import com.yl.crazy.mydongtest.ICallBack.ICallback;

import java.util.List;

/**
 * Created by Administrator on 2017/11/3.
 */

public class Model {

    //获取商品详情列表
    public void getProductDetial(String token, String id, final ICallback callback){
        Request<String> stringRequest = NoHttp.createStringRequest(Constant.BASE_URL + "api/goods/" + id);
        stringRequest.addHeader("Authorization", token);
        NoHttp.newRequestQueue().add(1, stringRequest, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response response) {
                if (response.responseCode()!=200)
                {
                    callback.fialed("服务器异常："+response.responseCode());
                    return;
                }
                callback.sucessed(response.get().toString());
            }

            @Override
            public void onFailed(int what, Response response) {
                callback.fialed("联网失败");
            }

            @Override
            public void onFinish(int what) {

            }
        });

    }


}
