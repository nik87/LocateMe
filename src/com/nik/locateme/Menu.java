package com.nik.locateme;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends Activity implements OnClickListener {

	private DatePicker dppickerDate;
	private TextView tvDisplayDate;
	private Button btnChangeDate, btnSave;
	private Spinner spSelectAdress;
	
	private int year;
	private int month;
	private int day;

	static final int DATE_DIALOG_ID = 999;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		spSelectAdress = (Spinner) findViewById(R.id.spAdress);
		btnSave = (Button) findViewById(R.id.btnSave);
		
		setCurrentDateOnView();
		addListenerButton();
		getAdress();
	}
	/**
	 * soll sich die eingtragenden Adressen holen 
	 */
	private void getAdress() {
		// TODO Auto-generated method stub
		String[] adress = getApplicationContext().fileList();
		List<String> list = new ArrayList<String>();
		for(int i = 0; i<adress.length; i++){
			//Log.d("Filename", filenames[i]);
			list.add(adress[i]);
		}
		ArrayAdapter<String> filenameAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list);
		spSelectAdress.setAdapter(filenameAdapter);
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

		//tvDisplayDate.setText(new StringBuilder().append(month + 1).append("-")
			//	.append(day).append("-").append(year).append(" "));

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

	/**
	 * soll sich beim drücken die Daten aus dem STream holen und im Spinner anzeigen
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		String selectedItem = String.valueOf(spSelectAdress.getSelectedItem());
		openFile(selectedItem);
	}
	private void openFile(String selectedFile) {
		// TODO Auto-generated method stub
		String value = "";
		FileInputStream fis;
		
		try {
			fis = openFileInput(selectedFile);
			byte[] input = new byte[fis.available()];
		while (fis.read(input) != -1) {
			value += new String(input);
		}
		fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tvDisplayDate.setText(value);
		
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
