package fr.esisar.cook;

public class RoboticCook implements RoboticState {

	@Override
	public void walk(Robot robot) {
		robot.setState(new RoboticOn());
		robot.log("Walking...");
	}

	@Override
	public void cook(Robot robot) {
		robot.log("Cooking...");
		
	}

	@Override
	public void switchOff(Robot robot) {
		robot.log("Cannot switch off while cooking...");
		
	}

}
