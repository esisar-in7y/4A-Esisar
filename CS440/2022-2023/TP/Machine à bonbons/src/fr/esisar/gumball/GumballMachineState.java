package fr.esisar.gumball;

public interface GumballMachineState {
	public void insertQuarter(GumballMachine gumball);
	public void ejectQuarter(GumballMachine gumball);
	public void turnCrank(GumballMachine gumball);
	public String toString(GumballMachine gumball);
}
