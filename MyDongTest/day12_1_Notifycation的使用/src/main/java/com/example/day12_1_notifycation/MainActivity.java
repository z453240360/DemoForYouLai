package com.example.day12_1_notifycation;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.BigPictureStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.InboxStyle;
import android.view.View;
import android.widget.RemoteViews;


public class MainActivity extends Activity {

	private NotificationManager manager;
	
	private int pro; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //初始化manager对象
        manager = (NotificationManager)this.getSystemService(Service.NOTIFICATION_SERVICE);
    }

    
    public void click (View v) {
    	switch (v.getId()) {
		case R.id.but_normal:
			//发送普通Notifycation
			
			//封装PendingIntent对象
			/*
			 * 通过getActivity方法获取PedingIntent对象，代表稍候点击后要执行的操作为打开页面的操作
			 * 1. Context
			 * 2. 请求码，给本次封装的pi对象设置一个唯一标识
			 * 3. Intent对象，用于描述具体的跳转动作
			 * 4. flag标识，用于设置当多次创建请求码相同的pi对象时的创建特点（如是否重复创建等）
			 * PendingIntent.FLAG_UPDATE_CURRENT
			 * PendingIntent.FLAG_NO_CREATE
			 * PendingIntent.FLAG_ONE_SHOT
			 * PendingIntent.FLAG_CANCEL_CURRENT
			 */
			PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(MainActivity.this,TwoActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

			
			//封装Notifycation对象
			NotificationCompat.Builder nb = new NotificationCompat.Builder(this)
			//设置显示内容，注意：设置小图标的方法绝对不可以省略，其他的方法随意
			.setContentTitle("通知标题")
			.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.pic2)) //设置大图标
			.setContentText("文本信息内容概要")
			.setContentInfo("小左")  //设置info信息
			.setSmallIcon(R.drawable.ic_launcher)  //设置小图标
			.setWhen(System.currentTimeMillis())   //设置发送时间
			.setTicker("提示文本")  //设置滚动提醒的文字
			.setOngoing(true)  //设置通知信息是否只能通过代码调用方法移除
			/*
			 * 设置点击事件 
			 * 参数：PendingIntent  延迟意图
			 */
			.setContentIntent(pi)
			;
			
			//通过manager发送
			/*
			 * 参数1： 发送id，作用：
			 * 当多次发送通知时，用于区分多个通知对象是否是同一个，即在通知列表中是显示多条数据还是一条数据
			 * 参数2：要发送的通知对象
			 */
			manager.notify(1, nb.build());
//			manager.notify(1, nb.build());
//			manager.notify(1, nb.build());
			
			break;

		case R.id.but_cancel:
			manager.cancel(1);
			break;
		case R.id.but_progress:
			final NotificationCompat.Builder nb1 = new NotificationCompat.Builder(this)
			.setSmallIcon(R.drawable.ic_launcher)
			.setContentTitle("带进度条的通知")
			/*
			 * 设置进度条显示效果
			 * 1. 设置进度条的最大值
			 * 2. 设置进度条的当前进度值
			 * 3. 是否模糊显示进度条
			 */
			.setProgress(100, 0, false)
			;
			manager.notify(2, nb1.build());
			
			//通过子线程控制进度条中的进度自动增长
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					while (pro < 100) {
						pro++;
						nb1.setProgress(100, pro, false);
						manager.notify(2, nb1.build());
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}).start();
			break;
			
		case R.id.but_big:
			//显示超大图片的通知
			//超大图片样式
			BigPictureStyle style = new BigPictureStyle();
			style.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.desert));
			
			NotificationCompat.Builder nb2 = new NotificationCompat.Builder(this)
			.setSmallIcon(R.drawable.ic_launcher)
			.setContentTitle("超大图片")
			//通过设置样式的方法显示超大图片
			.setStyle(style);
			
			
			manager.notify(3, nb2.build());
			break;
		case R.id.but_multi_line:
			InboxStyle style1 = new InboxStyle();
			style1.setBigContentTitle("多行文本标题");
			for (int i = 0; i < 5; i++) {
				style1.addLine("多行数据多行数据  "+i);
			}
			
			NotificationCompat.Builder nb3 = new NotificationCompat.Builder(this)
			.setSmallIcon(R.drawable.ic_launcher)
			.setStyle(style1);
			
			manager.notify(4, nb3.build());
			break;
		case R.id.but_custom:
			//创建用于控制页面显示的RemoteViews对象
			//参数1：当前工程的程序包名，2： 要显示的布局文件
			RemoteViews rv = new RemoteViews("com.example.day12_1_notifycation", R.layout.custom1);
			 
			
			NotificationCompat.Builder nb4 = new NotificationCompat.Builder(this)
			.setSmallIcon(R.drawable.ic_launcher)
			/*
			 * 自定义通知显示内容的方式一：
			 * 缺陷：自定义内容的高度不能太高，超出指定高度的部分不显示
			 * 通过setContent方法设置
			 * 参数为RemoteViews，远程视图，即即使页面退出，也不会对RemoteViews产生影响，依然能够正常显示
			 * 
			 */
			.setContent(rv);
			
			manager.notify(5, nb4.build());
			
			break;
		case R.id.but_high:
			//自定义显示内容的方式二：此种不会显示高度
			RemoteViews rvs = new RemoteViews("com.example.day12_1_notifycation", R.layout.custom2);
			
			//在代码中控制rvs中控件的显示
			rvs.setTextViewText(R.id.textView1, "超高超高超超高");
			//单独给自定义布局中的button设置点击事件
//			rvs.setOnClickPendingIntent(R.id.button1, pendingIntent)
			
			Notification nbh = new NotificationCompat.Builder(this)
							.setSmallIcon(R.drawable.ic_launcher).build();
			
			nbh.bigContentView = rvs;
			
			manager.notify(6, nbh);
			
			
			
			break;
		}
    }

    
}
