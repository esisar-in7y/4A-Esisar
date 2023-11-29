package fr.esisar.gumball;

public interface GumballMachineState {

	public void insertQuarter(GumballMachine gumballMachine);

	public void ejectQuarter(GumballMachine gumballMachine);

	public void turnCrank(GumballMachine gumballMachine);

}
