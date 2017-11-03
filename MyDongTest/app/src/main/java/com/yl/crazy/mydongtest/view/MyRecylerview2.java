package com.yl.crazy.mydongtest.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.yl.crazy.mydongtest.R;
import com.yl.crazy.mydongtest.adapter.Test_adapter2;
import com.yl.crazy.mydongtest.bean.CarBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongdong on 2017/10/22.
 */

public class MyRecylerview2 extends FrameLayout {
    private RecyclerView list;


    private LinearLayoutManager manager;
    private Test_adapter2 adapter;

    private List<CarBean.DataBeanX.DataBean.GoodsBean> mAllDatas = new ArrayList<>();

    public MyRecylerview2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MyRecylerview2(Context context) {
        this(context,null);
    }

    public MyRecylerview2(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_rcy_view, null);
        addView(view);

        list = view.findViewById(R.id.my_rc);
        adapter=new Test_adapter2(context,mAllDatas);
        manager = new LinearLayoutManager(context);
        list.setLayoutManager(manager);
        list.setAdapter(adapter);

        adapter.setCallBackData(new Test_adapter2.OnCallBackData() {
            @Override
            public void getList(List<CarBean.DataBeanX.DataBean.GoodsBean> mDatas) {
                if (callBackData!=null) {
                    callBackData.getList(mDatas);
                }
            }

            @Override
            public void addClicked(int postion) {
                if (onAddClicked!=null){
                    onAddClicked.addClicked(postion);
                }
            }

            @Override
            public void reduceClicked(int postion) {
                if (reduceClicked!=null){
                    reduceClicked.reduceClicked(postion);
                }
            }
        });
    }

    public void setDatas(List<CarBean.DataBeanX.DataBean.GoodsBean> datass,int pos){

        mAllDatas.clear();
        mAllDatas.addAll(datass);
        adapter.notifyDataSetChanged();
    }


    private OnCallBackData callBackData;
    private OnAddClicked onAddClicked;
    private OnReduceClicked reduceClicked;

    public void setOnAddClicked(OnAddClicked onAddClicked) {
        this.onAddClicked = onAddClicked;
    }

    public void setReduceClicked(OnReduceClicked reduceClicked) {
        this.reduceClicked = reduceClicked;
    }

    public void setCallBackData(OnCallBackData callBackData) {
        this.callBackData = callBackData;
    }


    public interface OnCallBackData{
        void getList(List<CarBean.DataBeanX.DataBean.GoodsBean> mDatas);
    }

    public interface OnAddClicked{
        void addClicked(int chiledPostion);
    }

    public interface OnReduceClicked{
        void reduceClicked(int chiledPostion);
    }




}
