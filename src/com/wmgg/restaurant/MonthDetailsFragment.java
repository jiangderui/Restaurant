package com.wmgg.restaurant;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class MonthDetailsFragment extends Fragment {
	private RestaurantDBHelper mDBHelper;
	private ListView listView;
	private Button  mButton;
	private Cursor cur;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.monthdetailslayout, null);
		
		listView = (ListView) v.findViewById(R.id.detailslist);

    	mDBHelper = new RestaurantDBHelper(getActivity(), null, null, 1);
    	mDBHelper.setDatabase(mDBHelper.getWritableDatabase());
    	Calendar c = Calendar.getInstance();
    	 
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        String formattedMonth = df.format(c.getTime()); 
        String formattedDate = df.format(c.getTime()); 
        formattedDate = formattedMonth+"-01";

        c.add(Calendar.MONTH, 1);
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM");
        String formattedDate1 = df.format(c.getTime());
        formattedDate1= formattedDate1+"-01";
        
    	String strSQL = "select _id, today,income,greens,rices,oil,bunkers,flavour,other from todayaccount where today between " + "'"+formattedDate +"'"+ " and "+"'" +formattedDate1+"'";
    	Log.e("definedetailsfragment", strSQL);
    	cur=mDBHelper.rawQueryData(strSQL);
    	
	    SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity().getApplicationContext(), R.layout.listitemlayout,cur,new String[]{"today", "income", "greens", "rices", "oil","bunkers","flavour","other"},new int[]{R.id.listitemdate,R.id.listitemincome, R.id.listitemgreens, R.id.listitemrices, R.id.listitemoil, R.id.listitembunkers,R.id.listitemflavour,R.id.listitemother},SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listView.setAdapter(adapter);
        mButton = (Button) v.findViewById(R.id.monthanalyze);

        mButton.setOnClickListener(new View.OnClickListener() {
        	
        	@Override  
        	public void onClick(View v){
        		startDataAnalyzeActivity();
        	}
        });        
    	
		
		return v;
	}

	private void startDataAnalyzeActivity()
	{
		Integer iIncomeTotal, iGreensTotal, iRicesTotal, iOilTotal, iBunkersTotal, iFlavourTotal, iOtherTotal; 
	    //生成一个Intent对象
	    Intent intent = new Intent();
	    //利用Intent传输数据，数据名为testIntent，值为123
	    cur.moveToFirst();
	    iIncomeTotal = 0;
	    iGreensTotal = 0;
	    iRicesTotal  = 0;
	    iOilTotal    = 0;
	    iBunkersTotal = 0;
	    iFlavourTotal = 0;
	    iOtherTotal   = 0;
	    if (cur.getCount() == 0)
	    {
	    	return;
	    }
	    
        do {  
        	
        	iIncomeTotal = iIncomeTotal + cur.getInt(cur.getColumnIndex("income"));
        	iGreensTotal = iGreensTotal + cur.getInt(cur.getColumnIndex("greens"));
        	iRicesTotal = iRicesTotal + cur.getInt(cur.getColumnIndex("rices"));
        	iOilTotal = iOilTotal + cur.getInt(cur.getColumnIndex("oil"));
        	iBunkersTotal = iBunkersTotal + cur.getInt(cur.getColumnIndex("bunkers"));
        	iFlavourTotal = iFlavourTotal + cur.getInt(cur.getColumnIndex("flavour"));
        	iOtherTotal = iOtherTotal + cur.getInt(cur.getColumnIndex("other"));
         }while (cur.moveToNext());
        
	     intent.putExtra("iIncomeTotal", iIncomeTotal);
	     intent.putExtra("iGreensTotal", iGreensTotal);
	     intent.putExtra("iRicesTotal", iRicesTotal);
	     intent.putExtra("iOilTotal", iOilTotal);
	     intent.putExtra("iBunkersTotal", iBunkersTotal);
	     intent.putExtra("iFlavourTotal", iFlavourTotal);
	     intent.putExtra("iOtherTotal", iOtherTotal);

	     //第一个参数为"从那里"，第二个参数为"到那里"
	     intent.setClass(getActivity(), DataAnalyzeActivity.class);
	    
	     //执行跳转
	     getActivity().startActivity(intent);	
	 }

	
}

