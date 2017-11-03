package com.yl.crazy.mydongtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;
import com.yl.crazy.mydongtest.Constant;
import com.yl.crazy.mydongtest.R;
import com.yl.crazy.mydongtest.bean.CarBean;
import com.yl.crazy.mydongtest.callBack.IAddCarBack;
import com.yl.crazy.mydongtest.utils.CatUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Test_adapter2 extends RecyclerView.Adapter<Test_adapter2.MyViewHolder> {
    private List<CarBean.DataBeanX.DataBean.GoodsBean> mDatas = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater;


    public Test_adapter2(Context context, List<CarBean.DataBeanX.DataBean.GoodsBean> datas) {
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = datas;
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    //初始化布局,创建ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_likelist2, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        final int goods_id = mDatas.get(position).getGoods_id();
        final int spec_id = mDatas.get(position).getSpec_id();

        holder.tv_test.setText(mDatas.get(position).getGoods_num()+"");
        holder.tv_name.setText(mDatas.get(position).getGoods_name());


        //购物车数量增加
        holder.mImg_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mImg_reduce.setClickable(false);
                final Integer integer = mDatas.get(position).getGoods_num();
                CatUtils.addCar(1, goods_id, spec_id, 1, new IAddCarBack() {
                    @Override
                    public void successed(int code) {
                        int num = integer + 1;
                        mDatas.get(position).setGoods_num(num);
                        if (callBackData != null) {
                            callBackData.getList(mDatas);
                            callBackData.addClicked(position);
                        }

                        holder.tv_test.setText(mDatas.get(position).getGoods_num()+"");
                        holder.mImg_reduce.setClickable(true);
                    }

                    @Override
                    public void failed(String s) {
                        holder.mImg_reduce.setClickable(true);
                    }
                });
            }
        });

        //数量减少
        holder.mImg_reduce.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                holder.mImg_reduce.setClickable(false);
                final Integer integer = mDatas.get(position).getGoods_num();
                if (integer==1){
                    return;
                }

                CatUtils.addCar(1, goods_id, spec_id, 2, new IAddCarBack() {
                    @Override
                    public void successed(int code) {
                        int num = integer - 1;

                        if (num <= 1) {
                            mDatas.get(position).setGoods_num(1);
                        } else {
                            mDatas.get(position).setGoods_num(num);
                        }
                        if (callBackData != null) {
                            callBackData.getList(mDatas);
                            callBackData.reduceClicked(position);
                        }
                        holder.mImg_reduce.setClickable(true);
                        holder.tv_test.setText(mDatas.get(position).getGoods_num() + "");
                    }

                    @Override
                    public void failed(String s) {
                        holder.mImg_reduce.setClickable(true);
                    }
                });

            }
        });
    }


    public void deleteItem(int postion){
        notifyItemRemoved(postion);
    }

    public OnCallBackData callBackData;

    public void setCallBackData(OnCallBackData callBackData) {
        this.callBackData = callBackData;
    }

    public interface OnCallBackData {
        void getList(List<CarBean.DataBeanX.DataBean.GoodsBean> mDatas);
        void addClicked(int postion);
        void reduceClicked(int postion);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_test,tv_name;
        private ImageView mImg_add, mImg_reduce;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_test = (TextView) itemView.findViewById(R.id.mTxt_test);
            tv_name = (TextView) itemView.findViewById(R.id.mTxt_name);
            mImg_add = (ImageView) itemView.findViewById(R.id.mImg_add);
            mImg_reduce = (ImageView) itemView.findViewById(R.id.mImg_reduce);
        }
    }

}
