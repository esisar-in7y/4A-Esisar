package fr.esisar.gumball.original;

public class GumballMachineTestDrive {

	public static void main(String[] args) {
		
		GumballMachine gm = new GumballMachine(1);
		System.out.println(gm.toString());
		
		/* STATE == NO_QUARTER */
			gm.ejectQuarter();
			gm.turnCrank();
		
		/* STATE == HAS_QUARTER */
		gm.insertQuarter();
			gm.ejectQuarter();
		gm.insertQuarter();
			gm.turnCrank();
		
			gm = new GumballMachine(0);
		/* STATE == SOLD_OUT */
			gm.insertQuarter();
			gm.ejectQuarter();
			gm.turnCrank();

		System.out.println(gm.toString());

	}

}
