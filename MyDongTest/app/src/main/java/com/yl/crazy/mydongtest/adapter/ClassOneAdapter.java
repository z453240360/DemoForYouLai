package com.yl.crazy.mydongtest.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yl.crazy.mydongtest.R;
import com.yl.crazy.mydongtest.ShoppingCar.ShoppingCarBean;
import com.yl.crazy.mydongtest.bean.ClassOneBean;
import com.yl.crazy.mydongtest.bean.ProductDetialBean;
import com.yl.crazy.mydongtest.callBack.IAddCarBack;
import com.yl.crazy.mydongtest.utils.CatUtils;
import com.yl.crazy.mydongtest.view.ShoppingCarChildView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.id.list;
import static android.R.id.mask;
import static com.yl.crazy.mydongtest.R.id.mBtn_guige;
import static com.yl.crazy.mydongtest.R.id.mImg_goods;
import static com.yl.crazy.mydongtest.R.id.mTxt_danwei;
import static com.yl.crazy.mydongtest.R.id.mTxt_hot;
import static com.yl.crazy.mydongtest.R.id.mTxt_money;
import static com.yl.crazy.mydongtest.R.id.mTxt_money2;
import static com.yl.crazy.mydongtest.R.id.mTxt_number;
import static com.yl.crazy.mydongtest.R.id.mTxt_title;

public class ClassOneAdapter extends RecyclerView.Adapter<ClassOneAdapter.MyViewHolder> {
    private List<ClassOneBean.DataBean> mDatas = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater;
    private Map<Integer,Boolean> map=new HashMap<>();

    public ClassOneAdapter(Context context, List<ClassOneBean.DataBean> datas) {
        this.mInflater = LayoutInflater.from(context);
        this.mDatas = datas;
        this.mContext = context;
        initMap();
    }

    private void initMap() {
        if (mDatas!=null){
            for (int i = 0; i < mDatas.size(); i++) {
                    map.put(i,false);
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
        View view = mInflater.inflate(R.layout.class_one, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if (map.get(position)==null){
            map.put(position,false);
        }

        if (map.get(position)){
            Drawable drawable = mContext.getResources().getDrawable(R.drawable.conner);
            holder.ll.setBackgroundDrawable(drawable);
            holder.classOne.setTextColor(Color.parseColor("#00FEA1"));
        }else {
            Drawable drawable2 = mContext.getResources().getDrawable(R.drawable.conner2);
            holder.ll.setBackgroundDrawable(drawable2);
            holder.classOne.setTextColor(Color.parseColor("#000000"));
        }




        holder.classOne.setText(mDatas.get(position).getGc_name());
        Glide.with(mContext).load(mDatas.get(position).getImage()).into(holder.mImg_icon);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < mDatas.size(); i++) {
                    map.put(i,false);
                }

                map.put(position,true);

                if (clicked!=null){
                    clicked.onItemClicked(position,mDatas.get(position).getGc_id());
                }
                notifyDataSetChanged();
            }
        });
    }

    private OnClicked clicked;

    public void setClicked(OnClicked clicked) {
        this.clicked = clicked;
    }

    public interface OnClicked{
        void onItemClicked(int postion,int gc_id);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView classOne;
        private ImageView mImg_icon;
        private RelativeLayout ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            classOne = (TextView) itemView.findViewById(R.id.classOne);
            mImg_icon = (ImageView) itemView.findViewById(R.id.mImg_icon);
            ll= (RelativeLayout) itemView.findViewById(R.id.ll);
        }
    }

}
