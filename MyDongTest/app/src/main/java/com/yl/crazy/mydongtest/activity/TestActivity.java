package com.yl.crazy.mydongtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import com.yl.crazy.mydongtest.Constant;
import com.yl.crazy.mydongtest.R;
import com.yl.crazy.mydongtest.adapter.Test_adapter;
import com.yl.crazy.mydongtest.bean.CarBean;
import com.yl.crazy.mydongtest.bean.GouWuCheBean;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private String TAG = "dd";
    private XRecyclerView recyclerView;
    private Test_adapter adapter;
    private LinearLayoutManager manager;
    private List<GouWuCheBean.DataBeanX> mDates = new ArrayList<>();

    private List<CarBean.DataBeanX.DataBean> allData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        adapter = new Test_adapter(this, allData);
        manager = new LinearLayoutManager(this);

        recyclerView = (XRecyclerView) findViewById(R.id.test_shopping);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        recyclerView.addHeaderView(LayoutInflater.from(this).inflate(R.layout.expand_1,null,false));
        recyclerView.setFootView(LayoutInflater.from(this).inflate(R.layout.expand_1,null,false));

        getCarData();
    }


    //仅第一次进入调用
    private void getCarData() {
        Request<String> stringRequest = NoHttp.createStringRequest(Constant.BASE_URL + "api/cart");
        stringRequest.addHeader("Authorization", Constant.Token);
        NoHttp.newRequestQueue().add(1, stringRequest, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                Gson gson = new Gson();
                CarBean carBean = gson.fromJson(response.get().toString(), CarBean.class);
                CarBean.DataBeanX data = carBean.getData();
                List<CarBean.DataBeanX.DataBean> storeBean = data.getData();//所有商店的集合
                for (int i = 0; i < storeBean.size(); i++) {

                    int store_id = storeBean.get(i).getStore_id();
                    int min_pay_money = storeBean.get(i).getMin_pay_money();
                    int logistics_cost = storeBean.get(i).getLogistics_cost();

                    List<CarBean.DataBeanX.DataBean.GoodsBean> goods = storeBean.get(i).getGoods();

                    for (int j = 0; j < goods.size(); j++) {
                        String price = goods.get(j).getPrice();
                        CarBean.DataBeanX.DataBean.GoodsBean goodsBean = goods.get(j);
                    }

                }
                allData.addAll(storeBean);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                Log.i(TAG, "onSucceed: " + response.get().toString());
            }

            @Override
            public void onFinish(int what) {
            }
        });
    }

}
