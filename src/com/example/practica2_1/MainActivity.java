package com.example.practica2_1;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {
	
	private ListView lvData;
	private List<ItemListView> dataListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.lvData = (ListView)findViewById(R.id.lvData);
		this.loadData();
	}

	public void loadData()	
	{		
		this.dataListView = new ArrayList<ItemListView>();
		this.dataListView.add(new ItemListView(R.drawable.ic_launcher,	"Робот"));
		this.dataListView.add(new ItemListView(R.drawable.calculator,	"Калькулятор"));
		this.dataListView.add(new ItemListView(R.drawable.document,		"Документ"));
		this.dataListView.add(new ItemListView(R.drawable.drive_optical,"Привод"));
		this.dataListView.add(new ItemListView(R.drawable.games,		"Игры"));
		this.dataListView.add(new ItemListView(R.drawable.office,		"Офис"));
		
		ItemListViewAdapter dataAdapter = new ItemListViewAdapter(this, this.dataListView);
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

				ItemListView item = this.dataListView.get(position);
				String str = item.GetText();
				
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
	
	/**
	 * Пункт для ListView
	 */
	public class ItemListView{
		private int img_res;
		private String text;
		
		public ItemListView(int _img_res, String _text){
			this.img_res = _img_res;
			this.text = _text;
		}
		
		public int GetImgRes(){
			return this.img_res;
		}
		
		public String GetText(){
			return this.text;
		}
	}
	
	
	private class ItemListViewAdapter extends BaseAdapter
	{
		private Activity activity;
		private List<ItemListView> listData;
		
		public ItemListViewAdapter(Activity _activity, List<ItemListView> _listData)
		{
			super();
			this.activity = _activity;
			this.listData = _listData;
		}
		
		@Override
		public int getCount() {		
			return this.listData.size();
		}
	
		@Override
		public Object getItem(int position) {		
			return this.listData.get(position);
		}
	
		@Override
		public long getItemId(int position) {		
			return position;
		}
	
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View lvElem;
		    
			if( convertView == null ){
		        LayoutInflater inflater = activity.getLayoutInflater();
		        lvElem = inflater.inflate(R.layout.list_item, null);
		    } else {
		    	lvElem = convertView;
		    }
		    
		    ImageView _iv = (ImageView)lvElem.findViewById(R.id.imgRes);
		    TextView _text = (TextView)lvElem.findViewById(R.id.text);
		    
		    ItemListView item = this.listData.get(position);
		    
		     _iv.setImageResource(item.GetImgRes());
		     _text.setText(item.GetText());
		    
		    return lvElem;
		}
	}
		
}
