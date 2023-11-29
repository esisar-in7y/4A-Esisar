package fr.esisar.gumball;

public class GumballHasQuarter implements GumballMachineState {

	@Override
	public void insertQuarter(GumballMachine gumballMachine) {
		LOGGER.info("You can't insert another quarter");
	}

	@Override
	public void ejectQuarter(GumballMachine gumballMachine) {
		LOGGER.info("Quarter returned");
		gumballMachine.setStateR(new GumballNoQuarter());
	}

	@Override
	public void turnCrank(GumballMachine gumballMachine) {
		LOGGER.info("You turned...");
		gumballMachine.setStateR(new GumballSold());
		gumballMachine.dispense();
	}

}
