package com.example.ge;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import bean.Student;

/**
 * GridView的使用
 */
public class MainActivity extends Activity {

	private GridView gv;
	
	private int[] imgs = {R.drawable.pic1,R.drawable.pic2,R.drawable.pic4,R.drawable.pic5,R.drawable.pic6,R.drawable.pic7};
	
	private ArrayList<Student> list = new ArrayList<Student>();

	private MyGridAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        
        initData();
        
        initAdapter();
        
        
        gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Log.i("oye", "被点击的数据内容为：   "+list.get(position).toString());
			}
		});
        
    }

	private void initAdapter() {
		// TODO Auto-generated method stub
		adapter = new MyGridAdapter();
		gv.setAdapter(adapter);
	}

	private void initData() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 50; i++) {
			list.add(new Student(imgs[i % imgs.length],"name"+i));
		}
	}

	private void initView() {
		gv = (GridView)findViewById(R.id.gv);
	}

	class MyGridAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated  method stub
			ViewHolder holder ;
			if (convertView == null) {
				convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, parent,false);
				
				holder = new ViewHolder(convertView);
				
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.iv.setImageResource(list.get(position).getIcon());
			holder.tv.setText(list.get(position).getName());
			return convertView;
		}
		
		class ViewHolder {
			ImageView iv;
			TextView tv;
			
			public ViewHolder (View convertView) {
				iv = (ImageView) convertView.findViewById(R.id.imageView1);
				tv = (TextView)convertView.findViewById(R.id.textView1);
			}
		}
		
	}
}
