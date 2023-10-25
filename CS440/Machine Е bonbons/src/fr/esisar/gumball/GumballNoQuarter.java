package fr.esisar.gumball;

public class GumballNoQuarter implements GumballMachineState {

	@Override
	public void insertQuarter(GumballMachine gumball) {
		gumball.log("You inserted a quarter");
		gumball.setState(new GumballHasQuarter());
		
	}

	@Override
	public void ejectQuarter(GumballMachine gumball) {
		gumball.log("You haven't inserted a quarter");
		
	}

	@Override
	public void turnCrank(GumballMachine gumball) {
		gumball.log("You turned but there's no quarter");
		
	}

	@Override
	public String toString(GumballMachine gumball) {
		return "waiting for quarter";
	}

}
