package fr.esisar.cook;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Robot {
	
	private static final Logger LOGGER = LogManager.getLogger(Robot.class);
	private RoboticState state;

	public Robot() {
		super();
		this.state = new RoboticOn();
	}

	public void walk() {
		state.walk(this);
	}
	
	public void cook() {
		state.cook(this);
	}
	
	public void switchOff() {
		state.switchOff(this);
	}
	
	/**
	 * Set the current state. Normally only called by classes implementing
	 * the RoboticState interface.
	 * 
	 * @param state the new state of this context (Robot)
	 */
	public void setState(RoboticState state) {
		this.state = state;
		
	}
	
	public void log(String str) {
		LOGGER.info(str);
	}
}
