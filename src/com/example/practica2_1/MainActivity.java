package com.example.practica2_1;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {
	private ListView lvData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.lvData = (ListView)findViewById(R.id.lvData);
		this.loadData();
	}

	public void loadData()	
	{
		List<String> list = new ArrayList<String>();		
		list.add("CENTER");
		list.add("CENTER_INSIDE");
		list.add("CENTER_CROP");
		list.add("FIT_XY");
		list.add("FIT_START");
		list.add("FIT_END");
		list.add("MATRIX");	
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		this.lvData.setAdapter(dataAdapter);
		
		this.lvData.setOnItemClickListener(this);

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void Click_lvData()
	{
		Toast.makeText(getApplicationContext(), "Click_lvData", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
				
		if(parent.getId() == R.id.lvData){
			if(view instanceof TextView){
				TextView tv = (TextView)view;
				String str = tv.getText().toString();				
				
				AlertDialog.Builder buildDlg = new AlertDialog.Builder(this);
				buildDlg.setTitle("Нажали");
				buildDlg.setMessage(str);
 				buildDlg.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();			
					}
				});
				
				AlertDialog dlg = buildDlg.create();		
				dlg.show();
			}
		}
	}
	

}
