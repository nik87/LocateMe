package com.nik.locateme;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Edit extends Activity implements OnClickListener{

	private Button save;
	private EditText description, street, hNumber, postcode;
	private String DESCRIPTION, STREET, HNUMBER, POSTCODE,DATA;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);
		save = (Button) findViewById(R.id.btn_saveAdress);
		description = (EditText) findViewById(R.id.description);
		street = (EditText) findViewById(R.id.streetname);
		hNumber = (EditText) findViewById(R.id.hNumb);
		postcode = (EditText) findViewById(R.id.postcode);
	}

	/**
	 * Soll die eingetragenden Daten abspeichern
	 */
	@Override
	public void onClick(View arg0) {
		
		DESCRIPTION = description.getText().toString();
		STREET = street.getText().toString();
		HNUMBER = hNumber.getText().toString();
		POSTCODE = postcode.getText().toString();
		
		//DATA.append(STREET).append(" ").append(HNUMBER).append(POSTCODE);
		
		DATA  = STREET + " " + HNUMBER + " " + POSTCODE;
		
		
		try {
			FileOutputStream fos = openFileOutput(DESCRIPTION, Context.MODE_PRIVATE);
			fos.write(DATA.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
