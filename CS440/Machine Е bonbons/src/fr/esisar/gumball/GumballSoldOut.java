package fr.esisar.gumball;

public class GumballSoldOut implements GumballMachineState {

	@Override
	public void insertQuarter(GumballMachine gumball) {
		gumball.log("You can't insert a quarter, the machine is sold out");
	}

	@Override
	public void ejectQuarter(GumballMachine gumball) {
		gumball.log("You can't eject, you haven't inserted a quarter"); // mauvais message !?
	}

	@Override
	public void turnCrank(GumballMachine gumball) {
		gumball.log("You turned, but there are no gumballs");
	}

	@Override
	public String toString(GumballMachine gumball) {
		return "sold out";
	}

}
