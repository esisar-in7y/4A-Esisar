package fr.esisar.gumball;

public class GumballSoldOut implements GumballMachineState {

	@Override
	public void insertQuarter(GumballMachine gumballMachine) {
		LOGGER.info("You can't insert a quarter, the machine is sold out");
	}

	@Override
	public void ejectQuarter(GumballMachine gumballMachine) {
		LOGGER.info("You can't eject, you haven't inserted a quarter yet");
	}

	@Override
	public void turnCrank(GumballMachine gumballMachine) {
		LOGGER.info("You turned, but there are no gumballs");
	}

}
