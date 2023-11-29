package fr.esisar.gumball;

public class GumballSold implements GumballMachineState {

	@Override
	public void insertQuarter(GumballMachine gumballMachine) {
		LOGGER.info("Please wait, we're already giving you a gumball");
	}

	@Override
	public void ejectQuarter(GumballMachine gumballMachine) {
		LOGGER.info("Sorry, you already turned the crank");
	}

	@Override
	public void turnCrank(GumballMachine gumballMachine) {
		LOGGER.info("Turning twice doesn't get you another gumball!");
	}

}
