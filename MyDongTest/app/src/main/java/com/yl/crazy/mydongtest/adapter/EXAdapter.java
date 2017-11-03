package com.yl.crazy.mydongtest.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yl.crazy.mydongtest.R;
import com.yl.crazy.mydongtest.bean.CarBean;
import com.yl.crazy.mydongtest.callBack.IAddCarBack;
import com.yl.crazy.mydongtest.expandableListable.EXpandAdapter;
import com.yl.crazy.mydongtest.utils.CatUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.yl.crazy.mydongtest.R.drawable.shopcart_add_red;
import static com.yl.crazy.mydongtest.R.id.checkBox;
import static com.yl.crazy.mydongtest.R.id.danwei;
import static com.yl.crazy.mydongtest.R.id.jiage;
import static com.yl.crazy.mydongtest.R.id.mImg_add;
import static com.yl.crazy.mydongtest.R.id.mImg_reduce;
import static com.yl.crazy.mydongtest.R.id.shopName;

/**
 * Created by Administrator on 2017/10/31.
 */

public class EXAdapter extends BaseExpandableListAdapter {


    private List<Map<Integer,List<String>>> mDatas;


    private Context context;

    public EXAdapter(List<Map<Integer,List<String>>> mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return mDatas.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mDatas.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return mDatas.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mDatas.get(i).get(i1).size();
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i+i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        final GroupHolder groupHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.ex_test, viewGroup, false);
            groupHolder = new GroupHolder(view);
            view.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) view.getTag();
        }

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        final ChildHolder childHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.ex_test2, viewGroup, false);
            childHolder = new ChildHolder(view);
            view.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) view.getTag();
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }



    //holder容器
    class GroupHolder {

        private TextView txt1;

        public GroupHolder(View itemView) {
            txt1 = (TextView) itemView.findViewById(R.id.txt1);

        }
    }

    class ChildHolder {
        private TextView txt2;
        public ChildHolder(View itemView) {
            txt2 = (TextView) itemView.findViewById(R.id.txt_test2);

        }
    }
}
