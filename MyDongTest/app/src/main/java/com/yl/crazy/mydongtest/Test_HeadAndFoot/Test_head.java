package com.yl.crazy.mydongtest.Test_HeadAndFoot;

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

public class Test_head extends RecyclerView.Adapter<Test_head.MyViewHolder> {
    private List<CarBean.DataBeanX.DataBean> mDatas = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater;
    private Map<Integer,List<CarBean.DataBeanX.DataBean.GoodsBean>> map = new HashMap<>();

    public Test_head(Context context, List<CarBean.DataBeanX.DataBean> datas) {
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = datas;
        this.mContext = context;
        initMap();
    }

    private void initMap() {
        if (mDatas!=null){
            for (int i = 0; i < mDatas.size(); i++) {
                map.put(i,mDatas.get(i).getGoods());
            }
        }
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


        holder.postMoney.setText(mDatas.get(position).getLogistics_cost()+"");
        holder.minPay.setText(mDatas.get(position).getMin_pay_money()+"");
        holder.shopName.setText(mDatas.get(position).getStore_name());

        if (map.get(position)==null){
            map.put(position,mDatas.get(position).getGoods());
        }

        holder.list.setCallBackData(new MyRecylerview.OnCallBackData() {
            @Override
            public void getList(List<CarBean.DataBeanX.DataBean.GoodsBean> mDatass) {
                if (mDatas!=null){
                    map.put(position,mDatass);
                }
            }
        });

        holder.list.setDatas(map.get(position),position);

        holder.list.refresh();





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
