package com.example.fragementactivity;

import android.app.Activity;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.provider.Settings.System;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MyActivity extends Activity{
    private Button mButton;
    private Intent mIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ImageView mImageView = new ImageView(getApplicationContext());
		mImageView.setBackgroundResource(R.id.action_bar);
		setContentView(R.layout.my_activity);
		mButton = (Button) findViewById(R.id.button);
		mButton.setVisibility(View.GONE);
		mButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mIntent = new Intent(getApplicationContext(),MainActivity.class);
				mIntent.putExtra("fragement",1);
				startActivity(mIntent);
			} 
		});
	}
	
    private ContentObserver mVoiceKeyStateChangeObserver = new ContentObserver(new Handler()) {

		@Override
		public void onChange(boolean selfChange) {
			// TODO Auto-generated method stub
			super.onChange(selfChange);
		}
    	
	};
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.e("tianjianwei","MyActivity onRestart()");
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.e("tianjianwei","MyActivity onResume()");
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.e("tianjianwei","MyActivity onStart()");
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.e("tianjianwei","MyActivity onStop()");
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.e("tianjianwei","MyActivity onDestroy()");
	}	
}
