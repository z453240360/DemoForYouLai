package com.yl.crazy.mydongtest.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
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
import butterknife.OnClick;

import static com.yl.crazy.mydongtest.R.id.btn_new;

public class ShoppingActivity extends AppCompatActivity implements IVIew {

    @BindView(R.id.rv)
    XRecyclerView rv;
    @BindView(R.id.rc_classOne)
    RecyclerView rcClassOne;
    @BindView(R.id.rc_classTwo)
    RecyclerView rcClassTwo;
    @BindView(R.id.btn_price)
    Button btnPrice;
    @BindView(btn_new)
    Button btnNew;
    @BindView(R.id.btn_pro)
    Button btnPro;
    @BindView(R.id.btn_near)
    Button btnNear;
    @BindView(R.id.isHasData)
    TextView isHasData;

    private String TAG = "dd";
    private int page = 1;
    private String state = "5";
    private String classOneId = "";
    private int classTwoId = 0;
    private String orderBy = "";
    private String lng = "";
    private String lat = "";
    private boolean isByPriceUp = true;

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
        gridLayoutManager = new GridLayoutManager(this, 3);
        classOneAdapter = new ClassOneAdapter(this, allClassOneData);
        rcClassOne.setLayoutManager(gridLayoutManager);
        rcClassOne.setAdapter(classOneAdapter);
        classOneAdapter.setClicked(new ClassOneAdapter.OnClicked() {
            @Override
            public void onItemClicked(int postion, int gc_id) {

                initButton();
                classOneId = gc_id + "";
                allAlassTwoData.clear();
                allData.clear();
                adapter3.notifyDataSetChanged();
                classTwoAdapter.init();
                present.getClassTwoData(Constant.Token, gc_id + "", Constant.AreaId);//获取二级分类
            }
        });


        //二级分类适配器
        classTwoAdapter = new ClassTwoAdapter(this, allAlassTwoData);
        classTwoManger = new LinearLayoutManager(this);
        rcClassTwo.setLayoutManager(classTwoManger);
        rcClassTwo.setAdapter(classTwoAdapter);
        classTwoAdapter.setClicked(new ClassTwoAdapter.OnClicked() {
            @Override
            public void onItemClicked(int postion, int gc_id) {

                initButton();

                classTwoId = gc_id;
                allData.clear();
                adapter3.notifyDataSetChanged();
                present.getYouBianData(Constant.Token, classTwoId, classOneId, "", "", "", 1, "", "  ", "330782");
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
        present.getClassTwoData(Constant.Token, "", Constant.AreaId);

        //商品列表设置加载更多
        rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                allData.clear();
                page = 1;
                adapter3.notifyDataSetChanged();
                present.getYouBianData(Constant.Token, classTwoId, classOneId, "", "", "", page, "", "  ", "" + 330782);
                rv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                present.getYouBianData(Constant.Token, classTwoId, classOneId, "", "", "", page, "", "  ", "" + 330782);
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

        if (page == 1) {
            if (data.size() == 0) {
                isHasData.setVisibility(View.VISIBLE);
                rv.setVisibility(View.GONE);
                return;
            }else {
                isHasData.setVisibility(View.GONE);
                rv.setVisibility(View.VISIBLE);
            }
        }else {
            isHasData.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
            if (data.size()==0){
                Toast.makeText(this, "没有更多数据", Toast.LENGTH_SHORT).show();
                return;
            }
        }


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

        for (int i = 0; i < allAlassTwoData.size(); i++) {
            if (i == 0) {
                allAlassTwoData.get(i).setSelected(true);
            } else {
                allAlassTwoData.get(i).setSelected(false);
            }
        }

        classTwoAdapter.notifyDataSetChanged();
        int postion = classTwoAdapter.getPostion();
        classTwoId = allAlassTwoData.get(postion).getGc_id();

        //获取商品列表
        page = 1;
        allData.clear();
        adapter3.notifyDataSetChanged();
        present.getYouBianData(Constant.Token, classTwoId, classOneId, "", "", "", page, "", "  ", Constant.AreaId);
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

    //条件筛选
    @OnClick({R.id.btn_price, btn_new, R.id.btn_pro, R.id.btn_near})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //价格排序
            case R.id.btn_price:

                initButton();

                btnPrice.setTextColor(Color.parseColor("#bb0dfc"));
                state = 1 + "";
                page = 1;
                allData.clear();
                adapter3.notifyDataSetChanged();

                if (isByPriceUp) {

                    Drawable top = getResources().getDrawable(R.mipmap.shang);
                    top.setBounds(0, 0, 20, 20);
                    btnPrice.setCompoundDrawables(null, null, top, null);
                    present.getYouBianData(Constant.Token, classTwoId, classOneId, state, "asc", "", page, "", "", Constant.AreaId);
                    isByPriceUp = false;
                } else {

                    Drawable top = getResources().getDrawable(R.mipmap.xia);
                    top.setBounds(0, 0, 20, 20);
                    btnPrice.setCompoundDrawables(null, null, top, null);
                    present.getYouBianData(Constant.Token, classTwoId, classOneId, state, "desc", "", page, "", "", Constant.AreaId);
                    isByPriceUp = true;
                }

                break;

            //新品排序
            case btn_new:
                initButton();
                btnNew.setTextColor(Color.parseColor("#bb0dfc"));
                state = 2 + "";
                page = 1;

                allData.clear();
                adapter3.notifyDataSetChanged();

                present.getYouBianData(Constant.Token, classTwoId, classOneId, state, "", "", page, "", "", Constant.AreaId);
                break;

            //促销排序
            case R.id.btn_pro:
                initButton();
                btnPro.setTextColor(Color.parseColor("#bb0dfc"));
                state = 3 + "";
                page = 1;
                allData.clear();
                adapter3.notifyDataSetChanged();
                present.getYouBianData(Constant.Token, classTwoId, classOneId, state, "", "", page, "", "", Constant.AreaId);
                break;

            //附近排序
            case R.id.btn_near:
                initButton();
                btnNear.setTextColor(Color.parseColor("#bb0dfc"));
                state = 4 + "";
                page = 1;

                allData.clear();
                adapter3.notifyDataSetChanged();
                present.getYouBianData(Constant.Token, classTwoId, classOneId, state, "", "", page, "0.0", "0.0", Constant.AreaId);
                break;
        }
    }


    //初始化按钮，
    public void initButton() {

        state = "5";

        btnPrice.setTextColor(Color.parseColor("#000000"));
        Drawable top = getResources().getDrawable(R.mipmap.xia);
        top.setBounds(0, 0, 0, 0);
        btnPrice.setCompoundDrawables(null, null, top, null);

        btnNear.setTextColor(Color.parseColor("#000000"));
        btnNew.setTextColor(Color.parseColor("#000000"));
        btnPro.setTextColor(Color.parseColor("#000000"));
    }
}
