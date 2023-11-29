package fr.esisar.gumball;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GumballNoQuarter implements GumballMachineState {

	@Override
	public void insertQuarter(GumballMachine gumballMachine) {
		LOGGER.info("You inserted a quarter");
		gumballMachine.setStateR(new GumballHasQuarter());
	}

	@Override
	public void ejectQuarter(GumballMachine gumballMachine) {
		LOGGER.info("You haven't inserted a quarter");
	}

	@Override
	public void turnCrank(GumballMachine gumballMachine) {
		LOGGER.info("You turned but there's no quarter");
	}

}
