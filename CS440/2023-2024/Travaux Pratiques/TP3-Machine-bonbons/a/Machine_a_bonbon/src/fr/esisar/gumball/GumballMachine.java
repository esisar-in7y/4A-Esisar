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

	public GumballMachineState getStateR() {
		return state;
	}

	public void setStateR(GumballMachineState state) {
		this.state = state;
	}

	public void insertQuarter() {
		
	}

	public void ejectQuarter() {
		
	}

	public void turnCrank() {
		
	}

	private void dispense() {
		if (state == HAS_QUARTER) {
			LOGGER.info("No gumball dispensed");
		} else if (state == NO_QUARTER) {
			LOGGER.info("You need to pay first");
		} else if (state == SOLD) {
			LOGGER.info("A gumball comes rolling out the slot");
			if (count == 0) {
				LOGGER.info("Oops, out of gumballs!");
				state = SOLD_OUT;
			} else {
				count = count - 1;
				state = NO_QUARTER;
			}
		} else if (state == SOLD_OUT) {
			LOGGER.info("No gumball dispensed");
		}
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
		if (state == HAS_QUARTER) {
			result.append("waiting for turn of crank");
		} else if (state == NO_QUARTER) {
			result.append("waiting for quarter");
		} else if (state == SOLD) {
			result.append("delivering a gumball");
		} else if (state == SOLD_OUT) {
			result.append("sold out");
		}
		result.append("\n");
		return result.toString();
	}
}
