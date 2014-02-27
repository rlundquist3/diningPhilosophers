package com.example.diningphilosophers;

import java.util.ArrayList;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Activity implements OnTouchListener {

	final int TOLERANCE = 25;
	Philosopher phil[] = new Philosopher[5];
	ArrayList<String> actions = new ArrayList<String>();
	ArrayAdapter<String> actionAdapter;
	ListView actionList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		for (int i=0; i<5; i++)
			phil[i] = new Philosopher(this, i);
		
		actionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, actions);		
		//actionList = (ListView) findViewById(R.id.actionList);
		//actionList.setAdapter(actionAdapter);
		
		ImageView colorImage = (ImageView) findViewById(R.id.imageView2);
		colorImage.setOnTouchListener(this);
		System.out.println(colorImage);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		
		System.out.println("Touch: " + Integer.toString(x) + "," + Integer.toString(y));
		
		if (event.getAction() == MotionEvent.ACTION_UP) {
			int color = getColor(R.id.imageView2, x, y);
			
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
		
		for (int i=0; i<5; i++) 
			phil[i].incrementTime();
		
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
	
	public void update(Philosopher phil) {
		String lastId = phil.names[phil.number] + phil.states[phil.lastState];
		String nextId = phil.names[phil.number] + phil.states[phil.state];
		ImageView lastImage = (ImageView) findViewById(getResources().getIdentifier(lastId, "id", getPackageName()));
		ImageView nextImage = (ImageView) findViewById(getResources().getIdentifier(nextId, "id", getPackageName()));
		lastImage.setVisibility(1);
		nextImage.setVisibility(0);	
		
		actions.add(phil.names[phil.number] + " is " + phil.states[phil.state].toLowerCase());		
		//actionList.refreshDrawableState();
	}

}
