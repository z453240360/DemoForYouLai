package com.yl.crazy.mydongtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yl.crazy.mydongtest.R;
import com.yl.crazy.mydongtest.bean.CarBean;
import com.yl.crazy.mydongtest.bean.ProductDetialBean;
import com.yl.crazy.mydongtest.callBack.IAddCarBack;
import com.yl.crazy.mydongtest.utils.CatUtils;

import java.util.ArrayList;
import java.util.List;


public class ProductDetialAdapter extends RecyclerView.Adapter<ProductDetialAdapter.MyViewHolder> {
    private List<ProductDetialBean.DataBean.SpecBean> mDatas = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater;


    public ProductDetialAdapter(Context context, List<ProductDetialBean.DataBean.SpecBean> datas) {
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
        View view = mInflater.inflate(R.layout.adapter_product, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.mTxt_name.setText(mDatas.get(position).getPrice() + mDatas.get(position).getRatio() + "");
        holder.mTxt_test.setText(mDatas.get(position).getCart_goods_num() + "");

        final int goodsId = mDatas.get(position).getGoods_id();
        final int spec_id = mDatas.get(position).getSpec_id();

        //购物车数量增加
        holder.mImg_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mImg_add.setClickable(false);
                final Integer integer = mDatas.get(position).getCart_goods_num();
                CatUtils.addCar(1, goodsId, spec_id, 1, new IAddCarBack() {
                    @Override
                    public void successed(String s) {
                        int num = integer + 1;
                        mDatas.get(position).setCart_goods_num(num);
                        holder.mTxt_test.setText(mDatas.get(position).getCart_goods_num() + "");
                        holder.mImg_add.setClickable(true);
                        if (getAllDatas != null) {
                            getAllDatas.getDates(mDatas);
                        }
                    }

                    @Override
                    public void failed(String s) {
                        holder.mImg_add.setClickable(true);
                    }
                });
            }
        });


        //数量减少
        holder.mImg_reduce.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                holder.mImg_reduce.setClickable(false);
                final Integer integer = mDatas.get(position).getCart_goods_num();
                if (integer == 0) {
                    return;
                }

                CatUtils.addCar(1, goodsId, spec_id, 2, new IAddCarBack() {
                    @Override
                    public void successed(String s) {
                        int num = integer - 1;
                        if (num <= 0) {
                            mDatas.get(position).setCart_goods_num(0);
                        } else {
                            mDatas.get(position).setCart_goods_num(num);
                        }

                        holder.mImg_reduce.setClickable(true);
                        holder.mTxt_test.setText(mDatas.get(position).getCart_goods_num() + "");
                        if (getAllDatas != null) {
                            getAllDatas.getDates(mDatas);
                        }
                    }

                    @Override
                    public void failed(String s) {
                        holder.mImg_reduce.setClickable(true);
                    }
                });
            }
        });
    }


    private GetAllDatas getAllDatas;

    public void setGetAllDatas(GetAllDatas getAllDatas) {
        this.getAllDatas = getAllDatas;
    }

    public interface GetAllDatas {
        void getDates(List<ProductDetialBean.DataBean.SpecBean> mDatas);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxt_name, mTxt_test;
        private ImageView mImg_add, mImg_reduce;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTxt_name = (TextView) itemView.findViewById(R.id.mTxt_name);
            mTxt_test = (TextView) itemView.findViewById(R.id.mTxt_test);
            mImg_add = (ImageView) itemView.findViewById(R.id.mImg_add);
            mImg_reduce = (ImageView) itemView.findViewById(R.id.mImg_reduce);
        }
    }

}
