package fr.esisar.gumball;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GumballMachine {

	private static final Logger LOGGER = LogManager.getLogger(GumballMachine.class);

	static final int SOLD_OUT = 0;
	static final int NO_QUARTER = 1;
	static final int HAS_QUARTER = 2;
	static final int SOLD = 3;

	private int state = SOLD_OUT;
	private int count = 0;

	public GumballMachine(int count) {
		super();
		this.count = count;
		if (count > 0) {
			state = NO_QUARTER;
		}
	}

	public void insertQuarter() {
		if (state == HAS_QUARTER) {
			LOGGER.info("You can't insert another quarter");
		} else if (state == NO_QUARTER) {
			LOGGER.info("You inserted a quarter");
			state = HAS_QUARTER;
		} else if (state == SOLD) {
			LOGGER.info("Please wait, we're already giving you a gumball");
		} else if (state == SOLD_OUT) {
			LOGGER.info("You can't insert a quarter, the machine is sold out");
		}
	}

	public void ejectQuarter() {
		if (state == HAS_QUARTER) {
			LOGGER.info("Quarter returned");
			state = NO_QUARTER;
		} else if (state == NO_QUARTER) {
			LOGGER.info("You haven't inserted a quarter");
		} else if (state == SOLD) {
			LOGGER.info("Sorry, you already turned the crank");
		} else if (state == SOLD_OUT) {
			LOGGER.info("You can't eject, you haven't inserted a quarter yet");
		}
	}

	public void turnCrank() {
		if (state == HAS_QUARTER) {
			LOGGER.info("You turned...");
			state = SOLD;
			dispense();
		} else if (state == NO_QUARTER) {
			LOGGER.info("You turned but there's no quarter");
		} else if (state == SOLD) {
			LOGGER.info("Turning twice doesn't get you another gumball!");
		} else if (state == SOLD_OUT) {
			LOGGER.info("You turned, but there are no gumballs");
		}
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
