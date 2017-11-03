package com.yl.crazy.mydongtest.utils;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import com.yl.crazy.mydongtest.Constant;
import com.yl.crazy.mydongtest.bean.AddCarBean;
import com.yl.crazy.mydongtest.callBack.IAddCarBack;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/10/30.
 */

public class CatUtils {
    //同步购物车
    public static void addCar(final int num, int goodId, int specId, int type, final IAddCarBack carBack) {
        Request<String> stringRequest = NoHttp.createStringRequest(Constant.BASE_URL + "api/cart/store", RequestMethod.POST);
        stringRequest.addHeader("Authorization", Constant.Token);
        stringRequest.add("goodsId", goodId);//商品编号
        stringRequest.add("specId", specId);//规格
        stringRequest.add("type", type);
        NoHttp.newRequestQueue().add(1, stringRequest, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                if (response.responseCode() == 200) {
                    carBack.successed(response.responseCode());
                }else {
                    Gson gson = new Gson();
                    AddCarBean addCarBean = gson.fromJson(response.get().toString(), AddCarBean.class);
                    String msg = addCarBean.getMsg();
                    carBack.failed(msg);
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                carBack.failed("联网失败");
            }

            @Override
            public void onFinish(int what) {
            }

        });

    }
}
