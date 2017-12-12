package com.example.day12_1_notifycation;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.Service;
import android.os.Bundle;

public class TwoActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		NotificationManager manager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
		
		manager.cancel(1);
	}
}
