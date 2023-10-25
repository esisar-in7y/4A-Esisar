package fr.esisar.cook;

public class RoboticOff implements RoboticState {

	@Override
	public void walk(Robot robot) {
		robot.setState(new RoboticOn());
		robot.log("Walking...");
	}

	@Override
	public void cook(Robot robot) {
		robot.log("Cannot cook at Off state.");
		
	}

	@Override
	public void switchOff(Robot robot) {
		robot.log("Already switched off");
		
	}

}
