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

	@Override
	public void dispenseState(GumballMachine gumballMachine) {
		LOGGER.info("A gumball comes rolling out the slot");
		int count = gumballMachine.getCount();
		if (count == 0) {
			LOGGER.info("Oops, out of gumballs!");
			gumballMachine.setState(new GumballSoldOut());
		} else {
			gumballMachine.setCount(count - 1);
			gumballMachine.setState(new GumballNoQuarter());
		}
	}

	@Override
	public String toString(GumballMachine gumballMachine) {
		return "delivering a gumball";
	}

}
