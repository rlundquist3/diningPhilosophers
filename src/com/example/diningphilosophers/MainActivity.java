package com.example.diningphilosophers;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnTouchListener {

	final int TOLERANCE = 25;
	static Philosopher phil[] = new Philosopher[5];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		for (int i=0; i<5; i++)
			phil[i] = new Philosopher(this, i);
			
		initialSetup();
		
		ImageView colorImage = (ImageView) findViewById(R.id.colorImage);
		colorImage.setOnTouchListener(this);
	}
	
	public void initialSetup() {
		for (int i=0; i<5; i++) {
			phil[i] = new Philosopher(this, i);
			update(phil[i]);
		}
		Philosopher.available = new boolean[] {true, true, true, true, true};
		Philosopher.actions.clear();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.action_refresh:
			initialSetup();
			Toast.makeText(getApplicationContext(), "Data Cleared", Toast.LENGTH_LONG).show();
			return true;
		case R.id.action_status:
			Intent intent = new Intent(MainActivity.this, StatusActivity.class);
			startActivity(intent);
			return true;
		default:
            return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		
		System.out.println("Touch: " + Integer.toString(x) + "," + Integer.toString(y));
		
		if (event.getAction() == MotionEvent.ACTION_UP) {
			System.out.println("Action: " + Integer.toString(event.getAction()));
			int color = getColor(R.id.colorImage, x, y);
			System.out.println("Color: " + Integer.toString(color));
			
			if (colorMatch(Color.RED, color)) {
				phil[0].nextState();
				System.out.println("Aristotle touched");
			}
			else if (colorMatch(Color.YELLOW, color)) {
				phil[1].nextState();
				System.out.println("Max touched");
			}
			else if (colorMatch(Color.GREEN, color)) {
				phil[2].nextState();
				System.out.println("Confucius touched");
			}
			else if (colorMatch(Color.BLUE, color)) {
				phil[3].nextState();
				System.out.println("Chris touched");
			}
			else if (colorMatch(Color.MAGENTA, color)) {
				phil[4].nextState();
				System.out.println("Plato touched");
			}
		}
		
		
		
		return true;
	}

	private boolean colorMatch(int color1, int color2) {
		if (Math.abs(Color.red(color1) - Color.red(color2)) > TOLERANCE)
			return false;
		if (Math.abs(Color.green(color1) - Color.green(color2)) > TOLERANCE)
			return false;
		if (Math.abs(Color.blue(color1) - Color.blue(color2)) > TOLERANCE)
			return false;
		return true;
	}

	private int getColor(int imageId, int x, int y) {
		ImageView image = (ImageView) findViewById(imageId);
		image.setDrawingCacheEnabled(true);
		Bitmap hotspots = Bitmap.createBitmap(image.getDrawingCache()); 
		image.setDrawingCacheEnabled(false);
		return hotspots.getPixel(x, y);
	}
	
	public void update(Philosopher philosopher) {		
		String philTextId = Philosopher.names[philosopher.number].toLowerCase() + "Text";
		TextView philText = (TextView) findViewById(getResources().getIdentifier(philTextId, "id", getPackageName()));
		philText.setText(" " + Philosopher.states[philosopher.state]);
		if (philosopher.state == philosopher.THINKING)
			philText.setTextColor(Color.BLUE);
		else if (philosopher.state == philosopher.WAITING)
			philText.setTextColor(Color.RED);
		else
			philText.setTextColor(Color.GREEN);
		
		Philosopher.actions.add(Philosopher.names[philosopher.number] + " is " + Philosopher.states[philosopher.state].toLowerCase());
		
		//for (int i=0; i<5; i++) 
			//phil[i].incrementTime();
	}

}
