package com.yl.crazy.mydongtest.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yl.crazy.mydongtest.Constant;
import com.yl.crazy.mydongtest.ICallBack.IVIew;
import com.yl.crazy.mydongtest.R;
import com.yl.crazy.mydongtest.ShoppingCar.ShoppingAdapter;
import com.yl.crazy.mydongtest.ShoppingCar.ShoppingCarBean;
import com.yl.crazy.mydongtest.adapter.ClassOneAdapter;
import com.yl.crazy.mydongtest.adapter.ClassTwoAdapter;
import com.yl.crazy.mydongtest.bean.ClassOneBean;
import com.yl.crazy.mydongtest.bean.ClassTwoBean;
import com.yl.crazy.mydongtest.bean.ProductDetialBean;
import com.yl.crazy.mydongtest.model.Present;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingActivity extends AppCompatActivity implements IVIew {

    @BindView(R.id.rv)
    XRecyclerView rv;
    @BindView(R.id.rc_classOne)
    RecyclerView rcClassOne;
    @BindView(R.id.rc_classTwo)
    RecyclerView rcClassTwo;

    private String TAG = "dd";
    private int page = 1;
    private int classOneId=0;
    private int classTwoId=0;

    private Present present;
    private Dialog dialog;

    private ShoppingAdapter adapter3;
    private ClassOneAdapter classOneAdapter;
    private ClassTwoAdapter classTwoAdapter;

    private LinearLayoutManager manager;
    private LinearLayoutManager classTwoManger;
    private GridLayoutManager gridLayoutManager;

    private List<ClassOneBean.DataBean> allClassOneData = new ArrayList<>();
    private List<ClassTwoBean.DataBean> allAlassTwoData = new ArrayList<>();
    private List<ShoppingCarBean.DataBeanX.DataBean> allData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_shopping);
        ButterKnife.bind(this);

        present = new Present(this);

        dialog = new Dialog(this);
        dialog.setTitle("正在加载");

        //一级分类
        gridLayoutManager = new GridLayoutManager(this,3);
        classOneAdapter=new ClassOneAdapter(this,allClassOneData);
        rcClassOne.setLayoutManager(gridLayoutManager);
        rcClassOne.setAdapter(classOneAdapter);
        classOneAdapter.setClicked(new ClassOneAdapter.OnClicked() {
            @Override
            public void onItemClicked(int postion,int gc_id) {
                classOneId = gc_id;
                allAlassTwoData.clear();
                classTwoAdapter.init();
                present.getClassTwoData(Constant.Token,gc_id+"",Constant.AreaId);
            }
        });


        //二级分类适配器
        classTwoAdapter=new ClassTwoAdapter(this,allAlassTwoData);
        classTwoManger = new LinearLayoutManager(this);
        rcClassTwo.setLayoutManager(classTwoManger);
        rcClassTwo.setAdapter(classTwoAdapter);
        classTwoAdapter.setClicked(new ClassTwoAdapter.OnClicked() {
            @Override
            public void onItemClicked(int postion, int gc_id) {
                classTwoId = gc_id;

                //获取商品列表
                present.getYouBianData(Constant.Token,2837, 2, "", "", "", 1, "", "  ");
            }
        });


        //商品详情
        manager = new LinearLayoutManager(this);
        adapter3 = new ShoppingAdapter(this, allData);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter3);


        //获取商品一级分类列表
        present.getClassOneData(Constant.Token);

        //获取商品二级分类列表
        present.getClassTwoData(Constant.Token,"",Constant.AreaId);

//        //获取商品列表
//        present.getYouBianData(Constant.Token,2837, 2, "", "", "", 1, "", "  ");

        //商品列表设置加载更多
        rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                allData.clear();
                page = 1;
                adapter3.notifyDataSetChanged();

                present.getYouBianData(Constant.Token,2837, 2, "", "", "", page, "", "  ");
                rv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                present.getYouBianData(Constant.Token,2837, 2, "", "", "", page, "", "  ");
                rv.loadMoreComplete();
            }
        });

        //商品列表的点击跳转详情事件
        adapter3.setClicked(new ShoppingAdapter.OnItemClicked() {
            @Override
            public void getDetial(int goods_id) {
                Intent intent = new Intent(ShoppingActivity.this, ShangpingXiangqing.class);
                intent.putExtra("goods_id", goods_id);
                startActivityForResult(intent, 100);//请求吗100
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == 100) {
                List<ProductDetialBean.DataBean.SpecBean> backData = (List<ProductDetialBean.DataBean.SpecBean>) data.getSerializableExtra("backData");
                ProductDetialBean.DataBean.SpecBean specBean = backData.get(0);
                int spec_id = specBean.getSpec_id();
                adapter3.refish(backData);
            }

        }

    }

    @Override
    public void dialogShow() {
        dialog.show();
    }

    //获取详细的商品列表
    @Override
    public void getFirstData(String s) {
        Gson gson = new Gson();
        ShoppingCarBean shoppingCarBean = gson.fromJson(s, ShoppingCarBean.class);
        int code = shoppingCarBean.getCode();
        if (code != 200) {
            Toast.makeText(ShoppingActivity.this, "" + shoppingCarBean.getMsg(), Toast.LENGTH_SHORT).show();
            dialog.cancel();
        }

        List<ShoppingCarBean.DataBeanX.DataBean> data = shoppingCarBean.getData().getData();
        allData.addAll(data);
        dialog.cancel();
        adapter3.notifyDataSetChanged();
    }

    //商品的一级分类列表
    @Override
    public void getSecondDate(String s) {
        Gson gosn = new Gson();
        ClassOneBean classOneBean = gosn.fromJson(s, ClassOneBean.class);
        List<ClassOneBean.DataBean> classOneData = classOneBean.getData();
        allClassOneData.addAll(classOneData);
        classOneAdapter.notifyDataSetChanged();

    }

    //获取二级分类列表
    @Override
    public void getThirdDate(String s) {
        Gson gosn = new Gson();
        ClassTwoBean classTwoBean = gosn.fromJson(s, ClassTwoBean.class);
        List<ClassTwoBean.DataBean> classTwoData = classTwoBean.getData();
        allAlassTwoData.addAll(classTwoData);



        classTwoAdapter.notifyDataSetChanged();


        int postion = classTwoAdapter.getPostion();
        classOneId=allAlassTwoData.get(postion).getGc_id();

        //获取商品列表
        present.getYouBianData(Constant.Token,classOneId, 2, "", "", "", 1, "", "  ");



    }

    @Override
    public void dialogCancel() {
        dialog.cancel();
    }

    @Override
    public void showFailed(String s) {
        dialog.cancel();
        Toast.makeText(ShoppingActivity.this, "" + s, Toast.LENGTH_SHORT).show();
    }
}
