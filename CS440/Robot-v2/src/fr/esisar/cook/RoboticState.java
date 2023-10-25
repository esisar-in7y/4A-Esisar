package fr.esisar.cook;

public interface RoboticState {
	public void walk(Robot robot);
	public void cook(Robot robot);
	public void switchOff(Robot robot);
}
