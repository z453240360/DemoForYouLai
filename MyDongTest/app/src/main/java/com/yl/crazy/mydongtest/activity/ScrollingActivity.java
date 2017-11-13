package com.yl.crazy.mydongtest.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yl.crazy.mydongtest.R;
import com.yl.crazy.mydongtest.adapter.Desgin_adapter;
import com.yl.crazy.mydongtest.bean.CarBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollingActivity extends AppCompatActivity {


    @BindView(R.id.rc)
    RecyclerView rc;
    private Desgin_adapter adapter;
    private List<String> list = new ArrayList<>();
    private LinearLayoutManager manager;
    private CarBean.DataBeanX.DataBean bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
