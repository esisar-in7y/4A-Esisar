package fr.esisar.gumball;

public class GumballSold implements GumballMachineState {

	@Override
	public void insertQuarter(GumballMachine gumball) {
		gumball.log("Please wait, we're already giving you a gumball");
	}

	@Override
	public void ejectQuarter(GumballMachine gumball) {
		gumball.log("Sorry, you already turned the crank");
	}

	@Override
	public void turnCrank(GumballMachine gumball) {
		gumball.log("Turning twice doesn't get you another gumball!");
	}

	@Override
	public String toString(GumballMachine gumball) {
		return "delivering a gumball";
	}

}
