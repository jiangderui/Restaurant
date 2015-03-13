package com.wmgg.restaurant;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class DataAnalyzeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dataanalyzelayout);
		double dbRate;
		Integer iPCTotal = 0;
		Integer iRecordCount = 0;
		Integer iIncomeTotal, iGreensTotal, iRicesTotal, iOilTotal, iBunkersTotal, iFlavourTotal, iOtherTotal; 
		Intent i = this.getIntent();
		iIncomeTotal = i.getIntExtra("iIncomeTotal", 0);
		iGreensTotal = i.getIntExtra("iGreensTotal", 0);
		iRicesTotal = i.getIntExtra("iRicesTotal", 0);
		iOilTotal = i.getIntExtra("iOilTotal", 0);
		iBunkersTotal = i.getIntExtra("iBunkersTotal", 0);
		iFlavourTotal = i.getIntExtra("iFlavourTotal", 0);
		iOtherTotal = i.getIntExtra("iOtherTotal", 0);
		iRecordCount = i.getIntExtra("iRecordCount", 0);
		iPCTotal = iPCTotal + iGreensTotal;
		iPCTotal = iPCTotal + iRicesTotal;
		iPCTotal = iPCTotal + iOilTotal;
		iPCTotal = iPCTotal + iBunkersTotal;
		iPCTotal = iPCTotal + iFlavourTotal;
		iPCTotal = iPCTotal + iOtherTotal;
		dbRate = (double)(iIncomeTotal - iPCTotal)/((double)iIncomeTotal);
		
		((TextView)findViewById(R.id.analyzeincome)).setText("收入   " + iIncomeTotal.toString() + "  平均 " + Double.valueOf((double)(iIncomeTotal/iRecordCount)).toString());
		((TextView)findViewById(R.id.analyzegreens)).setText("菜     " + iGreensTotal.toString()+ "  平均 " + Double.valueOf((double)(iGreensTotal/iRecordCount)).toString());
		((TextView)findViewById(R.id.analyzerices)).setText("米     " + iRicesTotal.toString()+ "  平均 " + Double.valueOf((double)(iRicesTotal/iRecordCount)).toString());
		((TextView)findViewById(R.id.analyzeoil)).setText("油     " + iOilTotal.toString()+ "  平均 " + Double.valueOf((double)(iOilTotal/iRecordCount)).toString());
		((TextView)findViewById(R.id.analyzebunkers)).setText("燃料   " + iBunkersTotal.toString()+ "  平均 " + Double.valueOf((double)(iBunkersTotal/iRecordCount)).toString());
		((TextView)findViewById(R.id.analyzeflavour)).setText("调料   " + iFlavourTotal.toString()+ "  平均 " + Double.valueOf((double)(iFlavourTotal/iRecordCount)).toString());
		((TextView)findViewById(R.id.analyzeother)).setText("其它   " + iOtherTotal.toString()+ "  平均 " + Double.valueOf((double)(iOtherTotal/iRecordCount)).toString());
		((TextView)findViewById(R.id.analyzepctotal)).setText("生产成本 " + iPCTotal.toString()+ "  平均 " + Double.valueOf((double)(iPCTotal/iRecordCount)).toString());
		((TextView)findViewById(R.id.analyzegrossincome)).setText("毛收入 "+(Integer.valueOf(iIncomeTotal - iPCTotal).toString())+ "  平均 " + Double.valueOf((double)((iIncomeTotal - iPCTotal)/iRecordCount)).toString());
		((TextView)findViewById(R.id.analyzegrossrate)).setText("毛利率 "+(Double.valueOf(dbRate).toString()));
		((TextView)findViewById(R.id.analyzedaycount)).setText("工作天数 "+ iRecordCount);
	}

}
