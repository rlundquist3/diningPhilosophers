/*
 * The Philosopher class contains state information about philosophers.
 * It also contains static availability flags for the chopsticks (for 
 * determining when a philosopher can or cannot eat).
 */

package com.example.diningphilosophers;

import java.util.ArrayList;

public class Philosopher {
	
	final int THINKING = 0, WAITING = 1, EATING = 2;
	
	MainActivity parent;
	int number, state, lastState;
	static boolean available[] = {true, true, true, true, true};
	int times[];
	static String[] states = new String[] {"Thinking", "Waiting", "Eating"};
	static String[] names = new String[] {"Aristotle", "Max", "Confucius", "Chris", "Plato"};
	static ArrayList<String> actions = new ArrayList<String>();
	
	public Philosopher (MainActivity mainActivity, int num) {
		parent = mainActivity;
		number = num;
		state = THINKING;
		times = new int[] {0, 0, 0};
	}

	//Facilitates the transition between states
	public void nextState(boolean increment) {
		System.out.print("Availability: ");
		for (int i=0; i<5; i++)
			System.out.print(available[i] + " ");
		System.out.println();
		
		lastState = state;
		if (state == THINKING || state == WAITING) {
			if (available[number] && available[(number+1)%5]) {
				available[number] = false;
				available[(number+1)%5] = false;
				state = EATING;
			} else
				state = WAITING;
		} else {
			available[number] = true;
			available[(number+1)%5] = true;
			state = THINKING;
			
			//Checks if neighbors can move from waiting
			if (MainActivity.phil[(number-1)%5].state == WAITING)
				MainActivity.phil[(number-1)%5].nextState(false);
			if (MainActivity.phil[(number+1)%5].state == WAITING)
				MainActivity.phil[(number+1)%5].nextState(false);
		}
		
		parent.update(this);
		
		if (increment)
			for (int i=0; i<5; i++)
				MainActivity.phil[i].incrementTime();
	}
	
	//Increments the time (number of turns) spent at the current state
	public void incrementTime() {
		times[state]++;
	}
	
}
