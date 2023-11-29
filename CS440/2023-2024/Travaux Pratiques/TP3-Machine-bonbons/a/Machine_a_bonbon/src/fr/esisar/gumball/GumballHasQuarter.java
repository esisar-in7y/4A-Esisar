package fr.esisar.gumball;

public class GumballHasQuarter implements GumballMachineState {

	@Override
	public void insertQuarter(GumballMachine gumballMachine) {
		LOGGER.info("You can't insert another quarter");
	}

	@Override
	public void ejectQuarter(GumballMachine gumballMachine) {
		LOGGER.info("Quarter returned");
		gumballMachine.setState(new GumballNoQuarter());
	}

	@Override
	public void turnCrank(GumballMachine gumballMachine) {
		LOGGER.info("You turned...");
		gumballMachine.setState(new GumballSold());
		gumballMachine.getState().dispenseState(gumballMachine);
	}

	@Override
	public void dispenseState(GumballMachine gumballMachine) {
		LOGGER.info("No gumball dispensed");
	}

	@Override
	public String toString(GumballMachine gumballMachine) {
		return "waiting for turn of crank";
	}

}
