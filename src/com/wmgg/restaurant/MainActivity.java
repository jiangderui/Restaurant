package com.wmgg.restaurant;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	
	private static final int RESTAURANT_SUCCESS = 0;
	private static final int RESTAURANT_FAILURE = 1;
	private FragmentTabHost mTabHost;
	private RestaurantDBHelper mDBHelper;
	
    private boolean ExistSDCard() {  
	      if (android.os.Environment.getExternalStorageState().equals(  
	          android.os.Environment.MEDIA_MOUNTED)) {  
	    	  return true;  
	      } 
	      
	      return false;
	}
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (!ExistSDCard())
		{
	    	 AlertDialog.Builder builder = new AlertDialog.Builder(this);  
 	         //对话框的标题  
 	         builder.setTitle(R.string.app_name);  
 	         //显示的信息内容  
 	         builder.setMessage(R.string.notice_insert_sdcard_message);  
 	         //设置android内中的图标库为对话框图标  
 	         builder.setIcon(android.R.drawable.ic_menu_help);  
 	         builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {  
	
	             @Override  
	             public void onClick(DialogInterface dialog, int which) {  
	            	 finish();
	             }  
 	         }); 
 	         
 	         builder.show();      	 

			
			return;
		}
		
		setContentView(R.layout.main);
		CreateTodayTable();
		initView();
	}
	
	private void CreateTodayTable()
	{
		String mTodayTable = "CREATE TABLE todayaccount ( _id integer PRIMARY KEY autoincrement, today date UNIQUE, income INTEGER, greens INTEGER, rices INTEGER, oil INTEGER, bunkers INTEGER, flavour INTEGER, other INTEGER);";
    	mDBHelper = new RestaurantDBHelper(this, null, null, 1);
    	mDBHelper.setDatabase(mDBHelper.getWritableDatabase());
    	if (RESTAURANT_FAILURE == mDBHelper.isTableExists("todayaccount"))
    	{
    		mDBHelper.execSQL(mTodayTable);
    	}
    	
    	mDBHelper.close();
	}
	
	private void initView()
	{
		Resources res = getResources(); // Resource object to get Drawables
		mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost); // The activity TabHost
		TabHost.TabSpec spec; // Reusable TabSpec for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
		// Do the same for the other tabs
		spec = mTabHost
				.newTabSpec("流水")
				.setIndicator("流水",
						res.getDrawable(R.drawable.tab_icon_03));
		mTabHost.addTab(spec, WatercourseFragment.class, null);

		//intent = new Intent().setClass(this, MonthDetailsActivity.class);
		spec = mTabHost.newTabSpec("当月")
				.setIndicator("当月", res.getDrawable(R.drawable.tab_icon_02));
		mTabHost.addTab(spec, MonthDetailsFragment.class, null);
		
		 
		//intent = new Intent().setClass(this, DefineDetailsActivity.class);
		spec = mTabHost
				.newTabSpec("自定义")
				.setIndicator("自定义",
						res.getDrawable(R.drawable.tab_icon_03));
		mTabHost.addTab(spec, DefineDetailsFragment.class, null);
		
		//set tab which one you want open first time 0 or 1 or 2
		mTabHost.setCurrentTab(0);	
	}
}
