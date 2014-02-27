package com.example.diningphilosophers;

public class Philosopher {
	
	final int THINKING = 0, WAITING = 1, EATING = 2;
	
	MainActivity parent;
	int number, state, lastState;
	static boolean available[] = {true, true, true, true, true};
	int times[];
	static String[] states = new String[] {"Thinking", "Waiting", "Eating"};
	static String[] names = new String[] {"Aristotle", "Max", "Confucius", "Chris", "Plato"};
	
	public Philosopher (MainActivity mainActivity, int num) {
		parent = mainActivity;
		number = num;
		state = THINKING;
		times = new int[] {0, 0, 0};
	}

	public void nextState() {
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
		}
		
		parent.update(this);
	}
	
	public void incrementTime() {
		times[state]++;
	}
	
}
