package com.wmgg.restaurant;



import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.widget.TabHost;

public class MainActivity extends FragmentActivity {
	
	private FragmentTabHost mTabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		initView();
	}
	
	private void initView()
	{
		Resources res = getResources(); // Resource object to get Drawables
		mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost); // The activity TabHost
		TabHost.TabSpec spec; // Reusable TabSpec for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
		Log.e("MainActivity", "Line 33");
		spec = mTabHost.newTabSpec("今天").setIndicator("今天", res.getDrawable(R.drawable.tab_icon_01));
		//mTabHost
		mTabHost.addTab(spec,TodayAccountFragment.class, null);
		// Do the same for the other tabs

		Log.e("MainActivity", "Line 42");
		//intent = new Intent().setClass(this, MonthDetailsActivity.class);
		spec = mTabHost.newTabSpec("当月")
				.setIndicator("当月", res.getDrawable(R.drawable.tab_icon_02));
		mTabHost.addTab(spec, MonthDetailsFragment.class, null);
		
		 
		Log.e("MainActivity", "Line 50");
		//intent = new Intent().setClass(this, DefineDetailsActivity.class);
		spec = mTabHost
				.newTabSpec("自定义")
				.setIndicator("自定义",
						res.getDrawable(R.drawable.tab_icon_03));
		mTabHost.addTab(spec, DefineDetailsFragment.class, null);
		
		spec = mTabHost
				.newTabSpec("流水")
				.setIndicator("流水",
						res.getDrawable(R.drawable.tab_icon_03));
		mTabHost.addTab(spec, WatercourseFragment.class, null);
		Log.e("MainActivity", "Line 59");
		//set tab which one you want open first time 0 or 1 or 2
		mTabHost.setCurrentTab(0);	}
}
