package fr.esisar.gumball;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GumballMachine {

	private static final Logger LOGGER = LogManager.getLogger(GumballMachine.class);

	static final int SOLD_OUT = 0;
	static final int NO_QUARTER = 1;
	static final int HAS_QUARTER = 2;
	static final int SOLD = 3;

	private int count = 0;

	private GumballMachineState state = new GumballSoldOut();

	public GumballMachine(int count) {
		super();
		this.count = count;
		if (count > 0) {
			state = new GumballNoQuarter();
		}
	}

	public static Logger getLogger() {
		return LOGGER;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public GumballMachineState getState() {
		return state;
	}

	public void setState(GumballMachineState state) {
		this.state = state;
	}

	public void insertQuarter() {
		state.insertQuarter(this);
	}

	public void ejectQuarter() {
		state.ejectQuarter(this);
	}

	public void turnCrank() {
		state.turnCrank(this);
	}

	@SuppressWarnings("unused")
	private void dispense() {
		state.dispenseState(this);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("\nMighty Gumball, Inc.");
		result.append("\nJava-enabled Standing Gumball Model #2022\n");
		result.append("Inventory: " + count + " gumball");
		if (count <= 1) {
			result.append("s");
		}
		result.append("\nMachine is ");
		result.append(state.toString(this));
		result.append("\n");
		return result.toString();
	}
}
