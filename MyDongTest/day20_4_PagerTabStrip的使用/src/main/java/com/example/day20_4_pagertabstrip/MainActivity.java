package com.example.day20_4_pagertabstrip;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;


/**
 * 通过ViewPager+PagerTabStrip实现书签导航效果
 *
 */
public class MainActivity extends FragmentActivity {

	private ViewPager pager;
	private PagerTabStrip strip;
	
	private ArrayList<Fragment> list = new ArrayList<Fragment>();
	
	private String[] titles = {"电影","电视剧","综艺"};
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView ();
        
        initData();
        
        initAdapter();


    }

	private void initAdapter() {
		// TODO Auto-generated method stub
		pager.setAdapter(new MyAdapter(getSupportFragmentManager()));
	}

	private void initData() {
		// TODO Auto-generated method stub
		list.add(new MyFragment());
		list.add(new YourFragment());
		list.add(new HisFragment());
	}

	private void initView() {
		// TODO Auto-generated method stub
		pager = (ViewPager)findViewById(R.id.pager);
		strip = (PagerTabStrip)findViewById(R.id.strip);
		
		//更改strip中的显示
		strip.setTabIndicatorColor(Color.GREEN);
		strip.setTextColor(Color.GREEN);
		strip.setBackgroundColor(Color.YELLOW);
		
		//更改文字之间的间距,注意：间距最小设置64dp
		strip.setTextSpacing(400);
	}

	
	class MyAdapter extends FragmentPagerAdapter {

		public MyAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return list.get(arg0);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}
		
		/*
		 * 手动添加此方法，
		 * 用于通过返回值控制位于position位置的标签上应该显示的文字内容
		 */
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return titles[position];
		}
	}

   
}
