package com.onekey.takeaway;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.onekey.common.Common;
import com.onekey.common.CrashHandler;
import com.onekey.common.LogUtils;
import com.onekey.http.CloudManager;
import com.onekey.takeaway.tabview.TabView;
import com.onekey.takeaway.tabview.TabViewChild;

public class MainActivity extends FragmentActivity {

	TabView tabView;
	String tab1 = "订单";
	String tab2 = "设备";
	String tab3 = "菜品";
	String tab4 = "统计";
	
	private TextView mTextView;
	
	private FragmentOrder mFragmentOrder;
	
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case 1:

//				byte[] data = new byte[4096];
//				SocketHelper.getInstance().readDataToDataThread(data);
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
    	LogUtils.d("ll1 onCreate");
    	
    	CloudManager.getInstance().generateShopBean();

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		CrashHandler.getInstance().init(this);

		Common.s_Metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getRealMetrics(Common.s_Metric);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quick_start_activity);
		
		mTextView = (TextView) findViewById(R.id.tv_title);
		
		mHandler.sendEmptyMessageDelayed(1, 1000);

		tabView = (TabView) findViewById(R.id.tabView);
		
		mFragmentOrder = FragmentOrder.newInstance(tab1);
		// start add data
		List<TabViewChild> tabViewChildList = new ArrayList<TabViewChild>();
		TabViewChild tabViewChild01 = new TabViewChild(R.drawable.tab03_sel,
				R.drawable.tab03_unsel, tab1, mFragmentOrder);
		TabViewChild tabViewChild02 = new TabViewChild(R.drawable.tab02_sel,
				R.drawable.tab02_unsel, tab2, FragmentDevices.newInstance(tab2));
		TabViewChild tabViewChild03 = new TabViewChild(R.drawable.tab01_sel,
				R.drawable.tab01_unsel, tab3, FragmentFood.newInstance(tab3));
		TabViewChild tabViewChild04 = new TabViewChild(R.drawable.tab01_sel,
				R.drawable.tab01_unsel, tab4, FragmentCount.newInstance(tab4));
		tabViewChildList.add(tabViewChild01);
		tabViewChildList.add(tabViewChild02);
		tabViewChildList.add(tabViewChild03);
		tabViewChildList.add(tabViewChild04);
		// end add data
		tabView.setTabViewDefaultPosition(0);
		tabView.setTextViewSelectedColor(getResources().getColor(R.color.title));
		tabView.setTabViewGravity(Gravity.TOP);
		tabView.setTabViewChild(tabViewChildList, getFragmentManager());
		tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
			@Override
			public void onTabChildClick(int position,
					ImageView currentImageIcon, TextView currentTextView) {
//				Toast.makeText(getApplicationContext(), "position:" + position,
//						Toast.LENGTH_SHORT).show();
				switch (position) {
//				case 0:
//					mTextView.setText("状态");
//					break;
//				case 1:
//					mTextView.setText("信号");
//					break;
//				case 2:
//					mTextView.setText("网口");
//					break;
				}
			}
		});
	}
	
	public void onResume() {
		super.onResume();
    	LogUtils.d("ll1 onResume");
	}
	

	public void onPause() {
		super.onPause();
    	LogUtils.d("ll1 onPause");
	}
	
	public void onDestroy() {
		super.onDestroy();
    	LogUtils.d("ll1 onDestroy");
    	mHandler.removeMessages(1);
//		SocketHelper.getInstance().setReadDataToDataThreadStop();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		mFragmentOrder.onActivityResult(requestCode, resultCode, data);
	}
	
	

}
