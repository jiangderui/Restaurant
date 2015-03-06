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
		Integer iIncomeTotal, iGreensTotal, iRicesTotal, iOilTotal, iBunkersTotal, iFlavourTotal, iOtherTotal; 
		Intent i = this.getIntent();
		iIncomeTotal = i.getIntExtra("iIncomeTotal", 0);
		iGreensTotal = i.getIntExtra("iGreensTotal", 0);
		iRicesTotal = i.getIntExtra("iRicesTotal", 0);
		iOilTotal = i.getIntExtra("iOilTotal", 0);
		iBunkersTotal = i.getIntExtra("iBunkersTotal", 0);
		iFlavourTotal = i.getIntExtra("iFlavourTotal", 0);
		iOtherTotal = i.getIntExtra("iOtherTotal", 0);
		iPCTotal = iPCTotal + iGreensTotal;
		iPCTotal = iPCTotal + iRicesTotal;
		iPCTotal = iPCTotal + iOilTotal;
		iPCTotal = iPCTotal + iBunkersTotal;
		iPCTotal = iPCTotal + iFlavourTotal;
		iPCTotal = iPCTotal + iOtherTotal;
		dbRate = (double)(iIncomeTotal - iPCTotal)/((double)iIncomeTotal);
		
		((TextView)findViewById(R.id.analyzeincome)).setText("收入   " + iIncomeTotal.toString());
		((TextView)findViewById(R.id.analyzegreens)).setText("菜     " + iGreensTotal.toString());
		((TextView)findViewById(R.id.analyzerices)).setText("米     " + iRicesTotal.toString());
		((TextView)findViewById(R.id.analyzeoil)).setText("油     " + iOilTotal.toString());
		((TextView)findViewById(R.id.analyzebunkers)).setText("燃料   " + iBunkersTotal.toString());
		((TextView)findViewById(R.id.analyzeflavour)).setText("调料   " + iFlavourTotal.toString());
		((TextView)findViewById(R.id.analyzeother)).setText("其它   " + iOtherTotal.toString());
		((TextView)findViewById(R.id.analyzepctotal)).setText("生产成本 " + iPCTotal.toString());
		((TextView)findViewById(R.id.analyzegrossincome)).setText("毛收入 "+(new Integer(iIncomeTotal - iPCTotal).toString()));
		((TextView)findViewById(R.id.analyzegrossrate)).setText("毛利率"+(new Double(dbRate).toString()));
	}

}
