package com.example.diningphilosophers;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class StatusActivity extends Activity {

	ArrayAdapter<String> actionAdapter;
	ListView actionList;
	ArrayList<String> gridItems = new ArrayList<String>();
	ArrayAdapter<String> gridAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status);
		
		actionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Philosopher.actions);		
		actionList = (ListView) findViewById(R.id.actionList);
		actionList.setAdapter(actionAdapter);
		actionList.refreshDrawableState();
		
		setupTimes();
	}
	
	private void setupTimes() {
		for (int i=0; i<5; i++)
			for (int j=0; j<3; j++) {
				String id = Philosopher.names[i].toLowerCase() + Philosopher.states[j];
				TextView text = (TextView) findViewById(getResources().getIdentifier(id, "id", getPackageName()));
				text.setText(Integer.toString(MainActivity.phil[i].times[j]));
			}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.status, menu);
		return true;
	}

}
