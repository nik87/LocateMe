package com.nik.locateme;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.DateKeyListener;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends Activity implements OnClickListener {

	private DatePicker dppickerDate;
	private TextView tvDisplayDate;
	private Button btnChangeDate;

	private int year;
	private int month;
	private int day;

	static final int DATE_DIALOG_ID = 999;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		setCurrentDateOnView();
		addListenerButton();
	}

	private void setCurrentDateOnView() {
		// TODO Auto-generated method stub
		tvDisplayDate = (TextView) findViewById(R.id.info);
		dppickerDate = (DatePicker) findViewById(R.id.pickerdate);

		final Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DAY_OF_MONTH);

		// Datum in Textview

		tvDisplayDate.setText(new StringBuilder().append(month + 1).append("-")
				.append(day).append("-").append(year).append(" "));

		dppickerDate.init(year, month, day, null);
	}

	private void addListenerButton() {
		// TODO Auto-generated method stub
		btnChangeDate = (Button) findViewById(R.id.btnChangeDate);

		btnChangeDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showDialog(DATE_DIALOG_ID);

			}

		});
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener, year, month,
					day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			tvDisplayDate.setText(new StringBuilder()
					.append(day)
					.append("-")
					.append(month + 1)
					.append("-").append(year)
					.append(" "));

			// set selected date into datepicker also
			dppickerDate.init(year, month, day, null);

		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		FileOutputStream out = null;
		OutputStreamWriter writer = null;

		try {
			out = openFileOutput("Daten.csv", MODE_ENABLE_WRITE_AHEAD_LOGGING);
			writer = new OutputStreamWriter(out);
			writer.write("'das hier' ist wichtig");
			writer.flush();
			Toast.makeText(this, "Datei wurde gesperichert", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (out != null)
					out.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public boolean onCreateOptionsMenu(android.view.Menu menu){
		super.onCreateOptionsMenu(menu);
		
		MenuInflater awesome = getMenuInflater();
		awesome.inflate(R.menu.main_menu, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.edit:
			Intent intent = new Intent(this, Edit.class);
			startActivity(intent);
			return true;
		
		
		case R.id.erase:
			Intent intent2 = new Intent(this, Delete.class);
			startActivity(intent2);
			return true;
		}
		return false;
	}
}
