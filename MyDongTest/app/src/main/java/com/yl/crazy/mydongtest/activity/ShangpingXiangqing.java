package com.yl.crazy.mydongtest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;

import com.google.gson.Gson;
import com.yl.crazy.mydongtest.Constant;
import com.yl.crazy.mydongtest.ICallBack.IVIew;
import com.yl.crazy.mydongtest.R;
import com.yl.crazy.mydongtest.adapter.ProductDetialAdapter;
import com.yl.crazy.mydongtest.bean.ProductDetialBean;
import com.yl.crazy.mydongtest.model.Present;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShangpingXiangqing extends AppCompatActivity implements IVIew {

    @BindView(R.id.productDetial)
    RecyclerView productDetial;
    private Present present;
    private LinearLayoutManager manager;
    private ProductDetialAdapter adapter;
    private List<ProductDetialBean.DataBean.SpecBean> allData = new ArrayList<>();


    private Intent intent;
    private Intent dongdong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shangping_xiangqing);
        ButterKnife.bind(this);
        present = new Present(this);
        intent = getIntent();
        dongdong = new Intent();

        int goods_id = intent.getIntExtra("goods_id", -1);


        present.getProductDetial(Constant.Token, "" + goods_id);
        adapter = new ProductDetialAdapter(this,allData);
        manager = new LinearLayoutManager(this);

        productDetial.setLayoutManager(manager);
        productDetial.setAdapter(adapter);

        adapter.setGetAllDatas(new ProductDetialAdapter.GetAllDatas() {
            @Override
            public void getDates(List<ProductDetialBean.DataBean.SpecBean> mDatas) {
                dongdong.putExtra("backData",(Serializable) (mDatas));

            }
        });

    }

    @Override
    public void dialogShow() {

    }

    @Override
    public void getFirstData(String s) {
        Gson gson = new Gson();
        ProductDetialBean productDetialBean = gson.fromJson(s, ProductDetialBean.class);
        ProductDetialBean.DataBean data = productDetialBean.getData();
        int goods_id = data.getGoods_id();
        List<ProductDetialBean.DataBean.SpecBean> spec = data.getSpec();
        for (int i = 0; i < spec.size(); i++) {
            spec.get(i).setGoods_id(goods_id);
        }
        dongdong.putExtra("backData",(Serializable) (spec));
        allData.addAll(spec);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getSecondDate(String s) {

    }

    @Override
    public void getThirdDate(String s) {

    }

    @Override
    public void dialogCancel() {

    }

    @Override
    public void showFailed(String s) {
        Log.i("dd", "showFailed: " + s);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            List<ProductDetialBean.DataBean.SpecBean> backData = (List<ProductDetialBean.DataBean.SpecBean>)dongdong.getSerializableExtra("backData");
            setResult(RESULT_OK, dongdong);
            finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
