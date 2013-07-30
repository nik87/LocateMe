package com.nik.locateme;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends Activity  {

	DatePicker pickerDate;
	TextView info;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		info = (TextView) findViewById(R.id.info);
		pickerDate = (DatePicker) findViewById(R.id.pickerdate);
		
		Calendar today = Calendar.getInstance();
		
		pickerDate.init(today.get(Calendar.YEAR),
				today.get(Calendar.MONTH),
				today.get(Calendar.DAY_OF_MONTH),
				new OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "onDateChanged", Toast.LENGTH_SHORT).show();
				
				info.setText(
						"Year: " + year + "\n" +
						"Month of Year: " + monthOfYear + "\n" +
						"Day of Month: " + dayOfMonth);
			}
		});
	}

	public boolean onCreateOptionsMenu(android.view.Menu menu){
		super.onCreateOptionsMenu(menu);
		MenuInflater awesome = getMenuInflater();
		awesome.inflate(R.menu.main_menu, menu);
		return true;
	}
	public boolean onOptionItemSelected(MenuItem item){
		//switch falls eine weitere Option eingefuegt wird
		switch(item.getItemId()){
		case R.id.edit:
			startActivity(new Intent("com.nik.locateme.DELETE"));
			return true;
		}
		
		return false;
	}
}
