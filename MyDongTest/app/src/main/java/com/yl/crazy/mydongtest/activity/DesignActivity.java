package com.yl.crazy.mydongtest.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;

import com.yl.crazy.mydongtest.R;
import com.yl.crazy.mydongtest.adapter.Desgin_adapter;
import com.yl.crazy.mydongtest.adapter.Test_adapter;
import com.yl.crazy.mydongtest.bean.CarBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DesignActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.dd)
    AppBarLayout dd;
    @BindView(R.id.rc)
    RecyclerView rc;
    @BindView(R.id.activity_design)
    RelativeLayout activityDesign;
    private Desgin_adapter adapter;
    private List<String> list = new ArrayList<>();
    private LinearLayoutManager manager;
    private CarBean.DataBeanX.DataBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        ButterKnife.bind(this);
        bean = new CarBean.DataBeanX.DataBean();


        for (int i = 0; i < 30; i++) {
            list.add(""+i);
        }

        manager = new LinearLayoutManager(this);

        adapter = new Desgin_adapter(this, list);

        rc.setLayoutManager(manager);
        rc.setAdapter(adapter);

    }
}
