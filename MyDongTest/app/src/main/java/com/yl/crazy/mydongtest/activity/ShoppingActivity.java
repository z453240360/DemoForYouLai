package com.yl.crazy.mydongtest.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import com.yl.crazy.mydongtest.Constant;
import com.yl.crazy.mydongtest.R;
import com.yl.crazy.mydongtest.ShoppingCar.ShoppingAdapter;
import com.yl.crazy.mydongtest.ShoppingCar.ShoppingCarBean;
import com.yl.crazy.mydongtest.bean.ProductDetialBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    XRecyclerView rv;

    private String TAG = "dd";
    private int page =1;
    private ShoppingAdapter adapter3;
    private LinearLayoutManager manager;
    private Dialog dialog;
    private List<ShoppingCarBean.DataBeanX.DataBean> allData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        ButterKnife.bind(this);

        dialog = new Dialog(this);
        dialog.setTitle("正在加载");
        manager = new LinearLayoutManager(this);
        adapter3 = new ShoppingAdapter(this,allData);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter3);

        getYouBianData(2837, 2, "", "", "", 1, "", "  ");


        rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                allData.clear();
                page=1;
                adapter3.notifyDataSetChanged();
                getYouBianData(2837, 2, "", "", "", page, "", "  ");
                rv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                getYouBianData(2837, 2, "", "", "", page, "", "");
                rv.loadMoreComplete();
            }
        });

        adapter3.setClicked(new ShoppingAdapter.OnItemClicked() {
            @Override
            public void getDetial(int goods_id) {
                Intent intent = new Intent(ShoppingActivity.this,ShangpingXiangqing.class);
                intent.putExtra("goods_id",goods_id);
                startActivityForResult(intent,100);//请求吗100
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){

            if (requestCode==100)
            {
                List<ProductDetialBean.DataBean.SpecBean> backData = (List<ProductDetialBean.DataBean.SpecBean>)data.getSerializableExtra("backData");
                ProductDetialBean.DataBean.SpecBean specBean = backData.get(0);
                int spec_id = specBean.getSpec_id();
                adapter3.refish(backData);
            }

        }

    }

    //获取详细的商品列表
    public void getYouBianData(int classId, int classOneId, String state, String orderBy, String storeId, int page, String lng, String lat) {

        Request<String> request = NoHttp.createStringRequest(Constant.BASE_URL + "api/goods");
        request.addHeader("Authorization", Constant.Token);
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
                dialog.show();
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                Gson gson = new Gson();
                if (response.responseCode()!=200){
                    dialog.cancel();
                    Toast.makeText(ShoppingActivity.this, ""+response.responseCode(), Toast.LENGTH_SHORT).show();
                    return;
                }
                ShoppingCarBean shoppingCarBean = gson.fromJson(response.get().toString(), ShoppingCarBean.class);
                int code = shoppingCarBean.getCode();
                if (code!=200){
                    Toast.makeText(ShoppingActivity.this, ""+shoppingCarBean.getMsg(), Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }

                List<ShoppingCarBean.DataBeanX.DataBean> data = shoppingCarBean.getData().getData();
                allData.addAll(data);
                adapter3.notifyDataSetChanged();
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                dialog.cancel();
                Toast.makeText(ShoppingActivity.this, ""+response.get().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish(int what) {
                dialog.cancel();
            }
        });
    }
}
