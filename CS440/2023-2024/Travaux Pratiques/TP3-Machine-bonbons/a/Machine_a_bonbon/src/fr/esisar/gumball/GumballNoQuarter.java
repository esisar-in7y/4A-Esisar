package fr.esisar.gumball;

public class GumballNoQuarter implements GumballMachineState {

	@Override
	public void insertQuarter(GumballMachine gumballMachine) {
		LOGGER.info("You inserted a quarter");
		gumballMachine.setState(new GumballHasQuarter());
	}

	@Override
	public void ejectQuarter(GumballMachine gumballMachine) {
		LOGGER.info("You haven't inserted a quarter");
	}

	@Override
	public void turnCrank(GumballMachine gumballMachine) {
		LOGGER.info("You turned but there's no quarter");
	}

	@Override
	public void dispenseState(GumballMachine gumballMachine) {
		LOGGER.info("You need to pay first");
	}

	@Override
	public String toString(GumballMachine gumballMachine) {
		return "waiting for quarter";
	}

}
