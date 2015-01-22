package com.wmgg.restaurant;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TodayAccountFragment extends Fragment {
	
	private TextView mToday;
	private EditText mGreens, mRices, mOil, mBunkers, mFlavour, mOther, mIncome;  
	private Button  mButton;
    
	private String strGreens, strRices, strOil, strBunkers, strFlavour, strOther, strIncome;   
	private RestaurantDBHelper mDBHelper;
	private View mainView;

	private static final int RESTAURANT_SUCCESS = 0;
	private static final int RESTAURANT_FAILURE = 1;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		String mTodayAccountTable = "CREATE TABLE todayaccount ( _id integer PRIMARY KEY autoincrement, today date UNIQUE, greens INTEGER, rices INTEGER, oil INTEGER, bunkers INTEGER, flavour INTEGER, other INTEGER, income INTEGER);";
		mainView = inflater.inflate(R.layout.todayaccountlayout, null);
		initializeViews();
    	mDBHelper = new RestaurantDBHelper(getActivity(), null, null, 1);
    	mDBHelper.setDatabase(mDBHelper.getWritableDatabase());
    	if (RESTAURANT_FAILURE == mDBHelper.isTableExists("todayaccount"))
    	{
    		mDBHelper.execSQL(mTodayAccountTable);
    	}
    	else
    	{
    		onQueryData();
    	}
    	
    	mDBHelper.closeDB();

		return mainView;
	}
	
    /** 
     * 初始化UI控件 
     */  
    private void initializeViews(){  
    	mToday   = (TextView)mainView.findViewById(R.id.today);
    	mGreens = (EditText)mainView.findViewById(R.id.greens);  
    	mRices = (EditText)mainView.findViewById(R.id.rices); 
    	mOil = (EditText)mainView.findViewById(R.id.oil);  
    	mBunkers = (EditText)mainView.findViewById(R.id.bunkers);
    	mFlavour = (EditText)mainView.findViewById(R.id.flavour); 
    	mOther = (EditText)mainView.findViewById(R.id.other);  
    	mIncome = (EditText)mainView.findViewById(R.id.income);
    	Calendar c = Calendar.getInstance();
 
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime()); 
        mToday.setText("当前日期: " + formattedDate);

        mButton = (Button) mainView.findViewById(R.id.save);

        mButton.setOnClickListener(new View.OnClickListener() {
        	@Override  
        	public void onClick(View v){
        		onSaveData();
        		onSaveTmpData();
        	}
        });        
        
          
    } 
    
    public void onQueryData()
    {
    	mDBHelper = new RestaurantDBHelper(getActivity(), null, null, 1);
    	mDBHelper.setDatabase(mDBHelper.getWritableDatabase());
    	Calendar c = Calendar.getInstance();
    	 
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime()); 

    	String strSQL = "select greens,rices,oil,bunkers,flavour,other,income from todayaccount where today = '" + formattedDate +"'";
    	Cursor cur=mDBHelper.rawQueryData(strSQL);
        while(cur.moveToNext())
        {
        	mGreens.setText(cur.getString(0));
        	mRices.setText(cur.getString(1));
        	mOil.setText(cur.getString(2));
        	mBunkers.setText(cur.getString(3));
        	mFlavour.setText(cur.getString(4));
        	mOther.setText(cur.getString(5));
        	mIncome.setText(cur.getString(6));
        }
        cur.close();    		
    	
    	mDBHelper.closeDB();    	
    }
    
    public void onSaveData()
    {
    	strGreens = mGreens.getText().toString();
    	strRices  = mRices.getText().toString();
    	strOil    = mOil.getText().toString();
    	strBunkers = mBunkers.getText().toString();
    	strFlavour= mFlavour.getText().toString();
    	strOther  = mOther.getText().toString();
    	strIncome = mIncome.getText().toString();
    	mDBHelper = new RestaurantDBHelper(getActivity(), null, null, 1);
    	mDBHelper.setDatabase(mDBHelper.getWritableDatabase());
    	Calendar c = Calendar.getInstance();
    	
    	 
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime()); 

      	String strSQL = "insert into todayaccount(today,greens,rices,oil,bunkers,flavour,other,income) values('"+formattedDate+"',"+"'"+strGreens+"',"+"'"+strRices+"',"+"'"+strOil+"',"+"'"+strBunkers+"',"+"'"+strFlavour+"',"+"'"+strOther+"',"+strIncome+")";
    	if (RESTAURANT_SUCCESS == mDBHelper.execSQL(strSQL))
    	{
    		Toast.makeText(getActivity(), "成功记账",Toast.LENGTH_LONG ).show();
    	}
    	else
    	{
    		Toast.makeText(getActivity(), "已经记账",Toast.LENGTH_LONG ).show();
    	}
    	
    	mDBHelper.closeDB();
    }
    
    public void onSaveTmpData()
    {
    	strGreens = mGreens.getText().toString();
    	strRices  = mRices.getText().toString();
    	strOil    = mOil.getText().toString();
    	strBunkers = mBunkers.getText().toString();
    	strFlavour= mFlavour.getText().toString();
    	strOther  = mOther.getText().toString();
    	strIncome = mIncome.getText().toString();
    	mDBHelper = new RestaurantDBHelper(getActivity(), null, null, 1);
    	mDBHelper.setDatabase(mDBHelper.getWritableDatabase());
    	Calendar c = Calendar.getInstance();
    	
    	 
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime()); 

      	String strSQL1 = "insert into todayaccount(today,greens,rices,oil,bunkers,flavour,other,income) values('"+"2014-12-15"+"',"+"'"+strGreens+"',"+"'"+strRices+"',"+"'"+strOil+"',"+"'"+strBunkers+"',"+"'"+strFlavour+"',"+"'"+strOther+"',"+strIncome+")";
    	if (RESTAURANT_SUCCESS == mDBHelper.execSQL(strSQL1))
    	{
    		Toast.makeText(getActivity(), "成功记账",Toast.LENGTH_LONG ).show();
    	}
    	else
    	{
    		Toast.makeText(getActivity(), "已经记账",Toast.LENGTH_LONG ).show();
    	}
    	
      	String strSQL2 = "insert into todayaccount(today,greens,rices,oil,bunkers,flavour,other,income) values('"+"2014-12-16"+"',"+"'"+strGreens+"',"+"'"+strRices+"',"+"'"+strOil+"',"+"'"+strBunkers+"',"+"'"+strFlavour+"',"+"'"+strOther+"',"+strIncome+")";
    	if (RESTAURANT_SUCCESS == mDBHelper.execSQL(strSQL2))
    	{
    		Toast.makeText(getActivity(), "成功记账",Toast.LENGTH_LONG ).show();
    	}
    	else
    	{
    		Toast.makeText(getActivity(), "已经记账",Toast.LENGTH_LONG ).show();
    	}
    	
      	String strSQL3 = "insert into todayaccount(today,greens,rices,oil,bunkers,flavour,other,income) values('"+"2014-12-30"+"',"+"'"+strGreens+"',"+"'"+strRices+"',"+"'"+strOil+"',"+"'"+strBunkers+"',"+"'"+strFlavour+"',"+"'"+strOther+"',"+strIncome+")";
    	if (RESTAURANT_SUCCESS == mDBHelper.execSQL(strSQL3))
    	{
    		Toast.makeText(getActivity(), "成功记账",Toast.LENGTH_LONG ).show();
    	}
    	else
    	{
    		Toast.makeText(getActivity(), "已经记账",Toast.LENGTH_LONG ).show();
    	}
    	
      	String strSQL4 = "insert into todayaccount(today,greens,rices,oil,bunkers,flavour,other,income) values('"+"2014-12-31"+"',"+"'"+strGreens+"',"+"'"+strRices+"',"+"'"+strOil+"',"+"'"+strBunkers+"',"+"'"+strFlavour+"',"+"'"+strOther+"',"+strIncome+")";
    	if (RESTAURANT_SUCCESS == mDBHelper.execSQL(strSQL4))
    	{
    		Toast.makeText(getActivity(), "成功记账",Toast.LENGTH_LONG ).show();
    	}
    	else
    	{
    		Toast.makeText(getActivity(), "已经记账",Toast.LENGTH_LONG ).show();
    	}
    	
      	String strSQL5 = "insert into todayaccount(today,greens,rices,oil,bunkers,flavour,other,income) values('"+"2015-01-01"+"',"+"'"+strGreens+"',"+"'"+strRices+"',"+"'"+strOil+"',"+"'"+strBunkers+"',"+"'"+strFlavour+"',"+"'"+strOther+"',"+strIncome+")";
    	if (RESTAURANT_SUCCESS == mDBHelper.execSQL(strSQL5))
    	{
    		Toast.makeText(getActivity(), "成功记账",Toast.LENGTH_LONG ).show();
    	}
    	else
    	{
    		Toast.makeText(getActivity(), "已经记账",Toast.LENGTH_LONG ).show();
    	}
    	
      	String strSQL6 = "insert into todayaccount(today,greens,rices,oil,bunkers,flavour,other,income) values('"+"2015-01-02"+"',"+"'"+strGreens+"',"+"'"+strRices+"',"+"'"+strOil+"',"+"'"+strBunkers+"',"+"'"+strFlavour+"',"+"'"+strOther+"',"+strIncome+")";
    	if (RESTAURANT_SUCCESS == mDBHelper.execSQL(strSQL6))
    	{
    		Toast.makeText(getActivity(), "成功记账",Toast.LENGTH_LONG ).show();
    	}
    	else
    	{
    		Toast.makeText(getActivity(), "已经记账",Toast.LENGTH_LONG ).show();
    	}
    	
      	String strSQL7 = "insert into todayaccount(today,greens,rices,oil,bunkers,flavour,other,income) values('"+"2015-01-13"+"',"+"'"+strGreens+"',"+"'"+strRices+"',"+"'"+strOil+"',"+"'"+strBunkers+"',"+"'"+strFlavour+"',"+"'"+strOther+"',"+strIncome+")";
    	if (RESTAURANT_SUCCESS == mDBHelper.execSQL(strSQL7))
    	{
    		Toast.makeText(getActivity(), "成功记账",Toast.LENGTH_LONG ).show();
    	}
    	else
    	{
    		Toast.makeText(getActivity(), "已经记账",Toast.LENGTH_LONG ).show();
    	}
    	
      	String strSQL8 = "insert into todayaccount(today,greens,rices,oil,bunkers,flavour,other,income) values('"+"2015-01-14"+"',"+"'"+strGreens+"',"+"'"+strRices+"',"+"'"+strOil+"',"+"'"+strBunkers+"',"+"'"+strFlavour+"',"+"'"+strOther+"',"+strIncome+")";
    	if (RESTAURANT_SUCCESS == mDBHelper.execSQL(strSQL8))
    	{
    		Toast.makeText(getActivity(), "成功记账",Toast.LENGTH_LONG ).show();
    	}
    	else
    	{
    		Toast.makeText(getActivity(), "已经记账",Toast.LENGTH_LONG ).show();
    	}
    	
      	String strSQL = "insert into todayaccount(today,greens,rices,oil,bunkers,flavour,other,income) values('"+"2015-12-15"+"',"+"'"+strGreens+"',"+"'"+strRices+"',"+"'"+strOil+"',"+"'"+strBunkers+"',"+"'"+strFlavour+"',"+"'"+strOther+"',"+strIncome+")";
    	if (RESTAURANT_SUCCESS == mDBHelper.execSQL(strSQL))
    	{
    		Toast.makeText(getActivity(), "成功记账",Toast.LENGTH_LONG ).show();
    	}
    	else
    	{
    		Toast.makeText(getActivity(), "已经记账",Toast.LENGTH_LONG ).show();
    	}
    	
    	mDBHelper.closeDB();
    	
    }
	

}
