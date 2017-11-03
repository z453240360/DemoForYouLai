package com.yl.crazy.mydongtest.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ExpandableListView;

import com.yl.crazy.mydongtest.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExActivity extends AppCompatActivity {

    private ExpandableListView ex_test;
    private String TAG="dd";

    private List<String> mData1 = new ArrayList<>();
    private Map<Integer,List<String>> map =new HashMap<>();

    private List<Map<Integer,List<String>>> mData2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex);
        ex_test = (ExpandableListView) findViewById(R.id.ex_test);






        ex_test.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                Log.i(TAG, "onScrollStateChanged: "+i+"----"+absListView.toString());
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                Log.i(TAG, "onScroll: "+absListView.toString()+"i"+"----"+"i1"+"---"+i2);
            }
        });

    }
}
