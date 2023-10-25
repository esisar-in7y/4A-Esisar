package fr.esisar.cook;

public class RoboticOn implements RoboticState {

	@Override
	public void walk(Robot robot) {
		robot.log("Walking...");
	}

	@Override
	public void cook(Robot robot) {
		robot.setState(new RoboticCook());
		robot.log("Cooking...");
		
	}

	@Override
	public void switchOff(Robot robot) {
		robot.setState(new RoboticOff());
		robot.log("Robot is switched off");
		
	}

}
