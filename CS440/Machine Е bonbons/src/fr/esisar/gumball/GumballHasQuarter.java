package fr.esisar.gumball;

public class GumballHasQuarter implements GumballMachineState {

	@Override
	public void insertQuarter(GumballMachine gumball) {
		gumball.log("You can't insert another quarter");
	}

	@Override
	public void ejectQuarter(GumballMachine gumball) {
		gumball.log("Quarter returned");
		gumball.setState(new GumballNoQuarter());
	}

	@Override
	public void turnCrank(GumballMachine gumball) {
		gumball.log("You turned...");
		gumball.setState(new GumballSold());
		dispense(gumball);
	}

	private void dispense(GumballMachine gumball) {
		gumball.log("A gumball comes rolling out of the slot");
		gumball.setcount(gumball.getcount() - 1);
		gumball.setState(new GumballSold());
		if(gumball.getcount() == 0) {
			gumball.log("Oops, out of gumballs!");
			gumball.setState(new GumballSoldOut());
		} else {
			gumball.setState(new GumballNoQuarter());
		}
		
	}

	@Override
	public String toString(GumballMachine gumball) {
		return "waiting for turn of crank";
	}

}
