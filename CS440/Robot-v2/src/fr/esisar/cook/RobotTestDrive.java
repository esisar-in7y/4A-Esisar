package fr.esisar.cook;

public class RobotTestDrive {

	public static void main(String[] args) {
		RobotTestDrive main = new RobotTestDrive();
		main.execute();
	}

	private void execute() {
		Robot robot = new Robot();
		robot.cook();
		robot.cook();
		robot.switchOff();
		robot.walk();
		robot.walk();
		robot.switchOff();
		robot.cook();
		robot.switchOff();
		robot.walk();
	}

}
