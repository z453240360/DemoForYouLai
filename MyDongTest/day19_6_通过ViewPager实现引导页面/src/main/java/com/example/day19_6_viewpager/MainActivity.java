package com.example.day19_6_viewpager;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ImageView.ScaleType;
import android.widget.RadioGroup;


public class MainActivity extends Activity {

	private ViewPager pager;
	
	private ArrayList<ImageView> list = new ArrayList<ImageView>();
	
	private int [] imgs={R.drawable.bg_guide_01,R.drawable.sample_0,R.drawable.bg_guide_02,R.drawable.sample_3,R.drawable.bg_guide_03};

	private MyAdapter adapter;
	
	private Button but;
	
	private SharedPreferences pref;

	private RadioGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = getSharedPreferences("login", Context.MODE_PRIVATE);
        boolean flag = pref.getBoolean("flag", false);
        if (!flag) {
        	 setContentView(R.layout.activity_main);
             
             initView();
             
             initData();
             
             initAdapter();
             
             //设置ViewPager的选中监听
             pager.setOnPageChangeListener(new OnPageChangeListener() {
     			//当item被选中后运行此方法
             	//参数：被选中的item对应的position位置
     			@Override
     			public void onPageSelected(int position) {
//     				Log.i("oye", "======= onPageSelected   "+position);
     				if(position == list.size()-1) {
     					but.setVisibility(View.VISIBLE);
     				} else {
     					but.setVisibility(View.GONE);
     				}
     				
     				//根据position位置选中对应的radiobutton
     				
     				//用于获取radiogroup中位于position位置子控件对象
     				RadioButton rb = (RadioButton) group.getChildAt(position);
     				//通过getId方法获取应该被选中的rb的id值
     				group.check(rb.getId());
     			}
     			//当页面滚动过程中不断调用的方法
     			/**
     			 * 1. 当前屏幕上主要显示的item的位置
     			 * 2. 滑动的偏移的百分比
     			 * 3. 滑动的偏移像素值
     			 */
     			@Override
     			public void onPageScrolled(int position, float positionOffset,
     					int positionOffsetPixels) {
     				// TODO Auto-generated method stub
     				Log.i("oye", "onPageScrolled   "+position+"  "+positionOffset+"  "+positionOffsetPixels);
     			}
     			//当滚动状态发生改变时调用的方法
     			//参数：改变后的滚动状态
     			@Override
     			public void onPageScrollStateChanged(int state) {
     				// TODO Auto-generated method stub
     				switch (state) {
					case ViewPager.SCROLL_STATE_DRAGGING:
						Log.i("oye", "手指开始滑动状态");
						break;

					case ViewPager.SCROLL_STATE_IDLE:
						Log.i("oye", "空闲状态，滑动结束状态");
						break;
					case ViewPager.SCROLL_STATE_SETTLING:
						Log.i("oye", "结束前过渡状态");
						break;
					}
     			}
     		});
        } else {
        	startActivity(new Intent(MainActivity.this,TwoActivity.class));
    		finish();
        }
       
        
    }

	private void initAdapter() {
		// TODO Auto-generated method stub
		adapter = new MyAdapter();
		pager.setAdapter(adapter);
	}

	private void initData() {
		// TODO Auto-generated method stub
		for (int i = 0; i < imgs.length; i++) {
			ImageView iv = new ImageView(this);
			iv.setScaleType(ScaleType.FIT_XY);
			list.add(iv);
		}
	}

	private void initView() {
		pager = (ViewPager)findViewById(R.id.pager);
		but = (Button)findViewById(R.id.button1);
		group = (RadioGroup)findViewById(R.id.radioGroup1);
	}

	public void click (View v) {
		pref.edit().putBoolean("flag", true).commit();
		startActivity(new Intent(MainActivity.this,TwoActivity.class));
		finish();
	}
	
	class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view == object;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			ImageView iv = list.get(position);
			//设置控件上的显示内容
			iv.setImageResource(imgs[position]);
			
			container.addView(iv);
			
			
			return iv;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			//当需要将位于position位置的item移除显示时
			//方式一
//			container.removeView(list.get(position));
			//方式二
			container.removeView((View) object);
		}
		
	}

    
}
