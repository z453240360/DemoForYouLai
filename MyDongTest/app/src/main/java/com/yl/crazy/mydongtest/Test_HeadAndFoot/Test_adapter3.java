package com.yl.crazy.mydongtest.Test_HeadAndFoot;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.yl.crazy.mydongtest.R;
import com.yl.crazy.mydongtest.bean.CarBean;
import com.yl.crazy.mydongtest.view.MyRecylerview;
import com.yl.crazy.mydongtest.view.MyRecylerview2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.id.list;

public class Test_adapter3 extends RecyclerView.Adapter<Test_adapter3.MyViewHolder> {
    private List<CarBean.DataBeanX.DataBean> mDatas = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater;

    public Test_adapter3(Context context, List<CarBean.DataBeanX.DataBean> datas) {
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
        View view = mInflater.inflate(R.layout.test_item3, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.postMoney.setText(mDatas.get(position).getLogistics_cost()+"");
        holder.minPay.setText(mDatas.get(position).getMin_pay_money()+"");
        holder.shopName.setText(mDatas.get(position).getStore_name());

        holder.list.setDatas(mDatas.get(position).getGoods(),position);
        holder.list.setCallBackData(new MyRecylerview2.OnCallBackData() {
            @Override
            public void getList(List<CarBean.DataBeanX.DataBean.GoodsBean> mDatass) {

            }
        });

        holder.list.setOnAddClicked(new MyRecylerview2.OnAddClicked() {
            @Override
            public void addClicked(int chiledPostion) {
                Toast.makeText(mContext, "父："+position+"  子："+chiledPostion, Toast.LENGTH_SHORT).show();
            }
        });

       holder.list.setReduceClicked(new MyRecylerview2.OnReduceClicked() {
           @Override
           public void reduceClicked(int chiledPostion) {
               Toast.makeText(mContext, "父："+position+"  子："+chiledPostion, Toast.LENGTH_SHORT).show();
           }
       });



    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private MyRecylerview2 list;
        private CheckBox checkBox;
        private TextView postMoney, minPay, shopName;

        public MyViewHolder(View itemView) {
            super(itemView);
            list = (MyRecylerview2) itemView.findViewById(R.id.sssss);
            shopName = (TextView) itemView.findViewById(R.id.shopName);
            postMoney = (TextView) itemView.findViewById(R.id.postMoney);
            minPay = (TextView) itemView.findViewById(R.id.minPay);
            checkBox = (CheckBox) itemView.findViewById(R.id.isSelectedAll);
        }
    }


}
