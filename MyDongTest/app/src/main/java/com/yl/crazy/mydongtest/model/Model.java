package com.yl.crazy.mydongtest.model;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import com.yl.crazy.mydongtest.Constant;
import com.yl.crazy.mydongtest.ICallBack.ICallback;
import com.yl.crazy.mydongtest.R;
import com.yl.crazy.mydongtest.ShoppingCar.ShoppingCarBean;
import com.yl.crazy.mydongtest.activity.ShoppingActivity;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.tag;

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


    //获取详细的商品列表
    public void getYouBianData(String token,
                               int classId,
                               int classOneId,
                               String state,
                               String orderBy,
                               String storeId,
                               int page,
                               String lng,
                               String lat,
                               final ICallback callback) {

        Request<String> request = NoHttp.createStringRequest(Constant.BASE_URL + "api/goods");
        request.addHeader("Authorization", token);
        request.add("classId", classId);//二级分类
        request.add("page", page);//页码
        request.add("classOneId", classOneId);//一级分类
        request.add("areaId", "330782");//区域ID
        request.add("state", state);//排序类型
        request.add("orderBy", orderBy);//倒序还是反序
        request.add("lng", lng);//经度
        request.add("lat", lat);//维度
        request.add("storeId", storeId);//商店ID
        NoHttp.newRequestQueue().add(1, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                if (response.responseCode()!=200)
                {
                    callback.fialed("服务器异常："+response.responseCode());
                    return;
                }
                callback.sucessed(response.get().toString());
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                callback.fialed("联网失败");
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }


    //获取一级分类的标签（蔬菜水果，肉禽蛋类......）
    public void getClassOneData(String token, final ICallback callback) {
        Request<String> stringRequest = NoHttp.createStringRequest(Constant.BASE_URL + "api/goods/class");
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


    //二级综合商品分类列表（传值为”“ 时显示全部二级分类列表）
    public void getClassTwoData(String token,String pid,String area_id,final ICallback callback) {
        final Request<String> stringRequest = NoHttp.createStringRequest(Constant.BASE_URL + "api/class/two");
        stringRequest.add("pid", pid);
        stringRequest.add("areaId", area_id);
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
