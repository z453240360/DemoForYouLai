package com.yl.crazy.mydongtest.Test_HeadAndFoot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import com.yl.crazy.mydongtest.Constant;
import com.yl.crazy.mydongtest.R;
import com.yl.crazy.mydongtest.bean.CarBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestHeadActivity extends AppCompatActivity {

    @BindView(R.id.rl_testhead)
    XRecyclerView rlTesthead;
    @BindView(R.id.ll)
    ListView ll;

    private String TAG = "dd";
    private Test_adapter3 adapter3;
    private LinearLayoutManager manager;
    private List<CarBean.DataBeanX.DataBean> allData = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_head);
        ButterKnife.bind(this);


        manager = new LinearLayoutManager(this);
        adapter3 = new Test_adapter3(this,allData);
        rlTesthead.setLayoutManager(manager);
        rlTesthead.setAdapter(adapter3);

        getCarData();


        rlTesthead.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                allData.clear();
                getCarData();
                rlTesthead.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                rlTesthead.refreshComplete();
            }
        });

    }


    //获取数据源
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

                allData.addAll(storeBean);
                adapter3.notifyDataSetChanged();
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
