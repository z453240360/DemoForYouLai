package com.yl.crazy.mydongtest.expandableListable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import com.yl.crazy.mydongtest.Constant;
import com.yl.crazy.mydongtest.R;
import com.yl.crazy.mydongtest.bean.CarBean;
import com.yl.crazy.mydongtest.bean.GouWuCheBean;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private String TAG = "dd";
    private List<GouWuCheBean.DataBeanX> mDates = new ArrayList<>();
    private List<CarBean.DataBeanX.DataBean> allData = new ArrayList<>();
    private ExpandableListView expandableListView;
    private EXpandAdapter adapter;
    private TextView txt;
    private Button btn;
    private ImageView isAllSelect;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        txt = (TextView) findViewById(R.id.totP);
        btn = (Button) findViewById(R.id.btn);
        expandableListView = (ExpandableListView) findViewById(R.id.expanded_menu);
        isAllSelect = (ImageView) findViewById(R.id.isAllSelect);

        for (int i = 0; i < expandableListView.getCount(); i++) {
            expandableListView.expandGroup(i);
        }
        getCarData();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText(adapter.getCarId());

                goToPay(Constant.Token,adapter.getCarId());
            }
        });

        isAllSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag) {
                    isAllSelect.setImageResource(R.drawable.shopcart_add_red);
                    adapter.setAllSelected(true);
                    flag = false;
                } else {
                    isAllSelect.setImageResource(R.drawable.shopcart_shop);
                    flag = true;
                    adapter.setAllSelected(false);
                }
            }
        });


        expandableListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                switch (i){
                    case SCROLL_STATE_FLING:


                        break;

                    case SCROLL_STATE_IDLE:

                        break;

                    case SCROLL_STATE_TOUCH_SCROLL:

                        break;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {




            }
        });

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
                adapter = new EXpandAdapter(storeBean, Main3Activity.this);
                expandableListView.setAdapter(adapter);
                for (int i = 0; i < storeBean.size(); i++) {
                    expandableListView.expandGroup(i);
                }

                expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                        view.setClickable(true);
                        return true;
                    }
                });

                adapter.setCallBackData(new EXpandAdapter.OnCallBackData() {

                    //购物车列表头被点击了
                    @Override
                    public void getGroupListClicked(int postion) {
                        Toast.makeText(Main3Activity.this, "父条目" + postion, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void getChildClicked(int groupPos, int childPos) {

                    }

                    @Override
                    public void getChildAdd(int groupPos, int childPos) {
//                        Toast.makeText(Main3Activity.this, "父条目"+groupPos+"子条目"+childPos, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void getChildReduce(int groupPos, int childPos) {
//                        Toast.makeText(Main3Activity.this, "父条目"+groupPos+"子条目"+childPos, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void getChildedTotalPrice(int groupPos, int childPos, String price) {

                    }

                    @Override
                    public void getTotalPrice(double s) {
                        txt.setText("总计" + s + "");
                    }

                    @Override
                    public void getAllSelected(int postion, boolean b) {
                        if (b) {
                            isAllSelect.setImageResource(R.drawable.shopcart_add_red);
                            flag = false;
                        } else {
                            isAllSelect.setImageResource(R.drawable.shopcart_shop);
                            flag = true;
                        }
                    }
                });
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

    //去支付
    private void goToPay(String token,String cardsID){
        Request<String> stringRequest = NoHttp.createStringRequest(Constant.BASE_URL + "api/pay/confirm");
        stringRequest.addHeader("Authorization", Constant.Token);
        stringRequest.add("cartIds", cardsID);
        NoHttp.newRequestQueue().add(1333, stringRequest, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response response) {
                Log.i(TAG, "onSucceed: "+response.get().toString());
            }
            @Override
            public void onFailed(int what, Response response) {
            }
            @Override
            public void onFinish(int what) {
            }
        });


    }



}
