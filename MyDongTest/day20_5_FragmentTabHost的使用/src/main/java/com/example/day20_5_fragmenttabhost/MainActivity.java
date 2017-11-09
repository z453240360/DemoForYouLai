package com.example.day20_5_fragmenttabhost;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabWidget;


public class MainActivity extends FragmentActivity {

	private FragmentTabHost host;
	private TabWidget widget;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        //初始化，参数3用于控制稍后页面上显示的Fragment要添加到哪个布局中显示
        host.setup(this, getSupportFragmentManager(),android.R.id.tabcontent);
        
        View view1  = LayoutInflater.from(this).inflate(R.layout.tab, null);
        /**
         * 通过addTab方法添加书签
         * 参数：
         * 1. TabSpec对象， 单独的一个书签对象的封装类，控制标签上的显示内容
         * 2. class类对象， 用于设置改变对应要显示哪个Fragment类
         * 3. Bundle对象 ，用于向Fragment中传递数据
         */
        host.addTab(host.newTabSpec("1").setIndicator(view1), MyFragment.class, null);
        
        View view2  = LayoutInflater.from(this).inflate(R.layout.tab, null);
        host.addTab(host.newTabSpec("2").setIndicator(view2), YourFragment.class, null);
        
        View view3  = LayoutInflater.from(this).inflate(R.layout.tab, null);
        /**
         * 准备用于传递数据的Bundle对象
         */
        Bundle bun = new Bundle();
        bun.putString("data", "jdofuioasufoasdufoasdufoiasdufiasd");
        host.addTab(host.newTabSpec("3").setIndicator(view3), HisFragment.class, bun);
        
//        widget = host.getTabWidget();
//        widget.setBackgroundColor(Color.BLUE);
        
        
    }

	private void initView() {
		// TODO Auto-generated method stub
		host  = (FragmentTabHost)findViewById(android.R.id.tabhost);
	}


   
}
