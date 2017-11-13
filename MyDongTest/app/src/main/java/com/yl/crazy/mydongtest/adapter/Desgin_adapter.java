package com.yl.crazy.mydongtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.yl.crazy.mydongtest.R;
import com.yl.crazy.mydongtest.bean.CarBean;
import com.yl.crazy.mydongtest.view.MyRecylerview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Desgin_adapter extends RecyclerView.Adapter<Desgin_adapter.MyViewHolder> {
    private List<String> mDatas = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater;
    private Map<Integer,List<CarBean.DataBeanX.DataBean.GoodsBean>> map = new HashMap<>();



    public Desgin_adapter(Context context, List<String> datas) {
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
        View view = mInflater.inflate(R.layout.test_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.minPay.setText(mDatas.get(position));

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private MyRecylerview list;
        private CheckBox checkBox;
        private TextView postMoney, minPay, shopName;

        public MyViewHolder(View itemView) {
            super(itemView);
            list = (MyRecylerview) itemView.findViewById(R.id.sssss);
            shopName = (TextView) itemView.findViewById(R.id.shopName);
            postMoney = (TextView) itemView.findViewById(R.id.postMoney);
            minPay = (TextView) itemView.findViewById(R.id.minPay);
            checkBox = (CheckBox) itemView.findViewById(R.id.isSelectedAll);
        }
    }


}
