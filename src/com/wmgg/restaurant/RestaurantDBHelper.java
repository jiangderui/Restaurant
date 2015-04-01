package com.wmgg.restaurant;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.content.Context;

public class RestaurantDBHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	
	private static final int RESTAURANT_SUCCESS = 0;
	private static final int RESTAURANT_FAILURE = 1;
	private SQLiteDatabase mDatabase;

	RestaurantDBHelper(Context context, String name, CursorFactory cursorFactory, int version) 
	{     
	    super(context, Constants.DATABASE_NAME, cursorFactory, DATABASE_VERSION);
	}   	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	public int execSQL(String strSQL){
		try{
			mDatabase.execSQL(strSQL);
			return RESTAURANT_SUCCESS;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			//Toast.makeText(mContext, "已经记账",Toast.LENGTH_LONG ).show();
		}
		return RESTAURANT_FAILURE;
	}
	
	public void closeDB(){
		mDatabase.close();
	}
	
	public int isTableExists(String tableName)
	{
	    Cursor cursor = mDatabase.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'", null);
	    if(null != cursor) 
	    {
	        if(cursor.getCount()>0)
	        {
	            cursor.close();
	            return RESTAURANT_SUCCESS;
	        }
	        
	        cursor.close();
	    }
		return RESTAURANT_FAILURE;
	}
	
	public Cursor rawQueryData(String strSQL)
	{
	    Cursor cursor = mDatabase.rawQuery(strSQL, null);
	    return cursor;
	}

	public void setDatabase(SQLiteDatabase db)
	{
		mDatabase = db;
	}

}
