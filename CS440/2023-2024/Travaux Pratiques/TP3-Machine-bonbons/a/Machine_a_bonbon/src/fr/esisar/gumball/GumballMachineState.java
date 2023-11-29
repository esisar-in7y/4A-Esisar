package fr.esisar.gumball;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface GumballMachineState {

	static final Logger LOGGER = LogManager.getLogger(GumballMachine.class);

	public void insertQuarter(GumballMachine gumballMachine);

	public void ejectQuarter(GumballMachine gumballMachine);

	public void turnCrank(GumballMachine gumballMachine);

	public void dispenseState(GumballMachine gumballMachine);

	public String toString(GumballMachine gumballMachine);

}
