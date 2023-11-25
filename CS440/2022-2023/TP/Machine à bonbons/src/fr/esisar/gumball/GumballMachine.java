package fr.esisar.gumball;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * BONJOUR
 * Ce n'est pas finis.
 * Je mettrais dans le bon paquet à la fin.
 * Merci :p
 * */

public class GumballMachine {
	
	private static final Logger LOGGER = LogManager.getLogger(GumballMachineState.class);
	private GumballMachineState state = new GumballSoldOut();
	private int count = 0;

	public GumballMachine(int count) {
		super();
		this.count = count;
		if(count > 0) {
			this.state = new GumballNoQuarter();
		}
	}

	public void insertQuarter() {
		state.insertQuarter(this);
	}
	
	public void ejectQuarter() {
		state.ejectQuarter(this);
	}
	
	public void turnCrank () {
		state.turnCrank(this);
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Mighty Gumball, Inc.");
		result.append("\nJava-enabled Standing Gumball Model #2022\n");
		result.append("Inventory: " + count + " gumball");
		if (count >= 1) {
			result.append("s");
		}
		result.append("\nMachine is ");
		result.append(state.toString(this));
		result.append("\n");
		return result.toString();
	}
	
	/**
	 * Set the current state. Normally only called by classes implementing
	 * the GumballMachineState interface.
	 * 
	 * @param state the new state of this context (Robot)
	 */
	public void setState(GumballMachineState state) {
		this.state = state;
		
	}
	
	public int getcount() {
		return count;
		
	}
	
	public void setcount(int count) {
		this.count = count;
		
	}
	
	public void log(String str) {
		LOGGER.info(str);
	}
}
