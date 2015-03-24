package com.wmgg.restaurant;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.DatePickerDialog;
import android.content.Intent;

public class DefineDetailsFragment extends Fragment {
	private RestaurantDBHelper mDBHelper;
	private ListView listView;
	private Button  mAnalyzeButton;
	private Button  mStartDateButton;
	private Button  mEndDateButton;
	private Cursor cur;
    private Calendar mCal = null;
    private DatePickerDialog mStartDatePDlg;
    private DatePickerDialog mEndDatePDlg;
    private String mStartDate;
    private String mEndDate;
    private TextView mItemStartDay;
    private TextView mItemEndDay;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		int iDay = 0;
		Calendar mCalIn = null;
		SimpleDateFormat df;
		View v = inflater.inflate(R.layout.definedetailslayout, null);
		
		listView = (ListView) v.findViewById(R.id.detailslist);

    	mDBHelper = new RestaurantDBHelper(getActivity(), null, null, 1);
    	mDBHelper.setDatabase(mDBHelper.getWritableDatabase());
    	mCal = Calendar.getInstance();
    	mCalIn = Calendar.getInstance();
    	
    	iDay = mCal.get(Calendar.DAY_OF_MONTH);
    	if (iDay < 15)
    	{
	        df = new SimpleDateFormat("yyyy-MM");
	        mStartDate = df.format(mCalIn.getTime()); 
	        mStartDate = mStartDate+"-14";
	
	        mCalIn.add(Calendar.MONTH, -1);
	        mEndDate = df.format(mCalIn.getTime());
	        mEndDate= mEndDate+"-15";
    	}
    	else
    	{
	        df = new SimpleDateFormat("yyyy-MM");
	        mStartDate = df.format(mCalIn.getTime()); 
	        mStartDate = mStartDate+"-15";
	
	        mCalIn.add(Calendar.MONTH, 1);
	        mEndDate = df.format(mCalIn.getTime());
	        mEndDate= mEndDate+"-14";
    	}
    	mItemStartDay = (TextView)v.findViewById(R.id.itemstartday);
    	mItemEndDay   = (TextView)v.findViewById(R.id.itemendday);
    	
		mAnalyzeButton = (Button) v.findViewById(R.id.analyze);

		mAnalyzeButton.setOnClickListener(new View.OnClickListener() {
        	
        	@Override  
        	public void onClick(View v){
        		startDataAnalyzeActivity();
        	}
        });        
    	

		mStartDateButton = (Button) v.findViewById(R.id.pickstartdate);

		mStartDateButton.setOnClickListener(new View.OnClickListener() {
        	
        	@Override  
        	public void onClick(View v){
        		mStartDatePDlg.show();
        	}
        });        
    	
		mEndDateButton = (Button) v.findViewById(R.id.pickenddate);

		mEndDateButton.setOnClickListener(new View.OnClickListener() {
        	
        	@Override  
        	public void onClick(View v){
        		mEndDatePDlg.show();
        	}
        });        
    	
		
        mStartDatePDlg = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener()
        {
        	@Override
        	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
        	{
        		int iMonth = monthOfYear + 1;
        		//formattedDate = year + "-" + iMonth + "-" + dayOfMonth;
        		
        		mStartDate = String.format("%04d-%02d-%02d", year, iMonth, dayOfMonth);
        		mItemStartDay.setText(mStartDate);
        		onQueryData();
        		
        	}
        }, mCal.get(Calendar.YEAR),mCal.get(Calendar.MONTH),mCal.get(Calendar.DAY_OF_MONTH));
        
        mEndDatePDlg = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener()
        {
        	@Override
        	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
        	{
        		int iMonth = monthOfYear + 1;
        		//formattedDate = year + "-" + iMonth + "-" + dayOfMonth;
        		
        		mEndDate = String.format("%04d-%02d-%02d", year, iMonth, dayOfMonth);
        		mItemEndDay.setText(mEndDate);
        		onQueryData();
        		
        	}
        }, mCal.get(Calendar.YEAR),mCal.get(Calendar.MONTH),mCal.get(Calendar.DAY_OF_MONTH));
        onQueryData();
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
	     intent.putExtra("iRecordCount", cur.getCount());

	     //第一个参数为"从那里"，第二个参数为"到那里"
	     intent.setClass(getActivity(), DataAnalyzeActivity.class);
	    
	     //执行跳转
	     getActivity().startActivity(intent);	
	 }
	
    public void onQueryData()
    {
    	String strSQL = null;
        strSQL = "select _id, today,income,greens,rices,oil,bunkers,flavour,other from todayaccount where today between " + "'"+mStartDate +"'"+ " and "+"'" +mEndDate+"'";
        
     	Log.e("WatercourseFragment", strSQL);
    	cur=mDBHelper.rawQueryData(strSQL);
    	
	    SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity().getApplicationContext(), R.layout.listitemlayout,cur,new String[]{"today", "income", "greens", "rices", "oil","bunkers","flavour","other"},new int[]{R.id.listitemdate,R.id.listitemincome, R.id.listitemgreens, R.id.listitemrices, R.id.listitemoil, R.id.listitembunkers,R.id.listitemflavour,R.id.listitemother},SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listView.setAdapter(adapter);
    }
	
	  public void onDestroyView()
	  {
		  cur.close();
		  mDBHelper.closeDB();
		  super.onDestroyView();
	  }

}
