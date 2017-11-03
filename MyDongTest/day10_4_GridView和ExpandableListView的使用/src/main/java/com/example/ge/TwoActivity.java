package com.example.ge;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.Notification;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * ExpandableListView的使用
 */
public class TwoActivity extends Activity {

	private ExpandableListView elv;
	
	private HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	private ArrayList<String> keys = new ArrayList<String>();

	private MyAdapter adapter;


	private int NEED_REFISH = 10;
	private int NEED_PULL = 11;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);
		
		initView();
		
		initData();
		
		initAdapter();
		//自定义父条目中图标的显示
		elv.setGroupIndicator(getResources().getDrawable(R.drawable.in));
		
		
		//设置子条目的点击事件
		elv.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Toast.makeText(TwoActivity.this, "被点啦！！！"+groupPosition+"  "+childPosition, Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		// 设置默认展开的父条目
		elv.expandGroup(1);


		elv.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView absListView, int i) {



			}

			@Override
			public void onScroll(AbsListView absListView, int i, int i1, int i2) {

				if (i==0){

					NEED_REFISH=110;
					Log.i(TAG, "onScroll: "+"已经到头了");
				}else {
					NEED_REFISH=11;
				}

				if (i+i1==i2){
					NEED_PULL=120;
					Log.i(TAG, "onScroll: "+"已经到底了");
				}else {
					NEED_PULL=12;
				}
			}
		});



		elv.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				switch (motionEvent.getAction()){

					case MotionEvent.ACTION_DOWN:
						float y = motionEvent.getY();
						break;

					case MotionEvent.ACTION_MOVE:
						float y1 = motionEvent.getY();


						break;

					case MotionEvent.ACTION_UP:

						break;
				}
				return false;
			}
		});


	}

	private void initAdapter() {
		adapter = new MyAdapter();
		elv.setAdapter(adapter);
	}

	private void initData() {
		for (int i = 0; i < 20; i++) {
			ArrayList<String> list  = new ArrayList<String>();
			for (int j = 0; j < i+3; j++) {
				list.add("子条目"+j);
			}
			map.put("item"+i, list);
		}
		
		keys.addAll(map.keySet());
	}

	private void initView() {
		// TODO Auto-generated method stub
		elv = (ExpandableListView)findViewById(R.id.elv);
	}
	
	
	class MyAdapter extends BaseExpandableListAdapter{

		//控制总共要显示多少个父条目
		@Override
		public int getGroupCount() {
			// TODO Auto-generated method stub
			return keys.size();
		}
		//获取指定父条目拥有的子条目的个数
		@Override
		public int getChildrenCount(int groupPosition) {
			// TODO Auto-generated method stub
			return map.get(keys.get(groupPosition)).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			// TODO Auto-generated method stub
			return keys.get(groupPosition);
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return map.get(keys.get(groupPosition)).get(childPosition);
		}

		@Override
		public long getGroupId(int groupPosition) {
			// TODO Auto-generated method stub
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return childPosition+groupPosition;
		}
		/**
		 *  默认返回false即可
		 *  返回值代表父条目和子条目中相同的控件id是否代表相同的控件对象
		 *  如：父条目和子条目的布局中都有一个id为tv的控件，
		 *  一旦此方法返回值为true,这两个控件就代表相同的对象
		 *  即父条目文本改变的话，子条目中的文本也会一起改变
		 *  
		 */
		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}
		//获取每一个父条目的显示
		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			GroupHolder holder;
			if (convertView == null) {
				convertView = LayoutInflater.from(TwoActivity.this).inflate(R.layout.groupitem, parent,false);
				holder = new GroupHolder(convertView);
				convertView.setTag(holder);
			} else {
				holder = (GroupHolder) convertView.getTag();
			}
			holder.tv.setText(keys.get(groupPosition));
			return convertView;
		}
		class GroupHolder {
			TextView tv;
			public GroupHolder(View convertView) {
				// TODO Auto-generated constructor stub
				tv = (TextView)convertView.findViewById(R.id.tv_group);
			}
		}
		
		
		//获取每一个子条目的显示
		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			ChildHolder holder;
			if (convertView == null) {
				convertView = LayoutInflater.from(TwoActivity.this).inflate(R.layout.childitem, parent,false);
				holder = new ChildHolder(convertView);
				convertView.setTag(holder);
			} else {
				holder = (ChildHolder) convertView.getTag();
			}
			holder.tv.setText(map.get(keys.get(groupPosition)).get(childPosition));
			holder.iv.setBackgroundColor(Color.GREEN);
			return convertView;
		}
		class ChildHolder {
			TextView tv;
			ImageView iv;
			public ChildHolder(View convertView) {
				// TODO Auto-generated constructor stub
				tv = (TextView)convertView.findViewById(R.id.textView1);
				iv = (ImageView)convertView.findViewById(R.id.imageView1);
			}
		}
		//设置子条目是否可以接收点击事件
		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return true;
		}
		
	}
}
