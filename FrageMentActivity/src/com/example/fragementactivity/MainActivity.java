package com.example.fragementactivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends FragmentActivity {
    //定义FragmentTabHost对象  
    private FragmentTabHost mTabHost; 
    //定义一个布局  
    private LayoutInflater layoutInflater;
    
    private Class fragementArray[] = {FragementOne.class,FragementTwo.class,FragementThree.class};
    
    //定义数组来存放按钮图片  
    private int mImageViewArray[] = {R.drawable.selector_one,R.drawable.selector_one,R.drawable.selector_one};  
      
    //Tab选项卡的文字  
    private String mTextviewArray[] = {"首页", "消息", "更多"};
    
    
    private Fragment fragmentone; 
    private Fragment fragmenttwo;
    private Fragment fragmentthree;
    
    private Button mBackButton;
    private Button mNextButton;
    private Button mJumpButton;
    private boolean oneflagNext = false;
    private boolean twoflagNext = false;
    private boolean threeflagNext = false;
    
    private boolean oneflagBack = false;
    private boolean twoflagBack = false;
    private boolean threeflagBack = false;
    
    private Intent mIntent;
    private int i;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		//initView();
		fragmentone = getSupportFragmentManager().findFragmentById(R.id.one);
		fragmenttwo = getSupportFragmentManager().findFragmentById(R.id.two);
		fragmentthree = getSupportFragmentManager().findFragmentById(R.id.three);
		getSupportFragmentManager().beginTransaction().hide(fragmenttwo).hide(fragmentthree).
		              show(fragmentone).commit();
		mBackButton = (Button) findViewById(R.id.back);
		mNextButton = (Button) findViewById(R.id.next);
		mJumpButton = (Button) findViewById(R.id.jump);
		
		mJumpButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent mIntent = new Intent();
				mIntent.setClass(getApplicationContext(), MyActivity.class);
				startActivity(mIntent);
			}
		});

		mBackButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(fragmentthree.isVisible()){
					showFragmentTwo();
				}else if(fragmenttwo.isVisible()){
					showFragmentOne();
				}
			}
		});
		
		mNextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(fragmentone.isVisible()){
					showFragmentTwo();
				}else if(fragmenttwo.isVisible()){
					showFragmentThree();
				}
			}
		});
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mIntent = getIntent();
		i = mIntent.getIntExtra("fragement", 0);
		if(i == 1){			
			showFragmentTwo();
		}
	}


	public void showFragmentOne(){
		getSupportFragmentManager().beginTransaction().hide(fragmenttwo).hide(fragmentthree).
            show(fragmentone).commit();		
	}
	
	public void showFragmentTwo(){
		getSupportFragmentManager().beginTransaction().hide(fragmentone).hide(fragmentthree).
            show(fragmenttwo).commit();		
	}
	
	public void showFragmentThree(){
		getSupportFragmentManager().beginTransaction().hide(fragmentone).hide(fragmenttwo).
            show(fragmentthree).commit();		
	}
    /** 
     * 初始化组件 
     */  
    /*private void initView(){  
        //实例化布局对象  
        layoutInflater = LayoutInflater.from(this);  
                  
        //实例化TabHost对象，得到TabHost  
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);   

        mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String arg0) {
				// TODO Auto-generated method stub
				Log.e("tianjianwei", "切换界面！！！！！！！！！！");
			}
		});
        //得到fragment的个数  
        int count = fragementArray.length;     
                  
        for(int i = 0; i < count; i++){    
            //为每一个Tab按钮设置图标、文字和内容  
            TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));  
            //将Tab按钮添加进Tab选项卡中  
            mTabHost.addTab(tabSpec, fragementArray[i], null);  
            //设置Tab按钮的背景  
            //mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.usb_android_connected);  
        }
    } */
    
    /** 
     * 给Tab按钮设置图标和文字 
     */  
    /*private View getTabItemView(int index){  
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);  
      
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);  
        imageView.setImageResource(mImageViewArray[index]);  
          
        TextView textView = (TextView) view.findViewById(R.id.textview);          
        textView.setText(mTextviewArray[index]);  
      
        return view;  
    }*/
}
