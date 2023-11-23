package fr.esisar.cook;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CookBot {

	private static final Logger LOGGER = LogManager.getLogger(CookBot.class);
	
	static final int ON = 0;
	static final int OFF = 1;
	static final int COOK = 2;
	
	private int state;

	public CookBot() {
		super();
		state = ON;
	}
	
	public void switchOn() {
		if (state == ON) {
			LOGGER.info("CookBot is switched on...");
		} else if (state == COOK) {
			state = ON;
			LOGGER.info("CookBot is switched on...");
		} else if (state == OFF) {
			state = ON;
			LOGGER.info("CookBot is switched on...");
		}
	}
	
	public void regularCook() {
		if (state == ON) {
			state = COOK;
			LOGGER.info("CookBot is cooking...");
		} else if (state == COOK) {
			LOGGER.info("CookBot is cooking...");
		} else if (state == OFF) {
			LOGGER.warn("Cannot cook when it is switched off");
		}
	}
	
	public void switchOff() {
		if (state == ON) {
			state = OFF;
			LOGGER.info("CookBot is switched off");
		} else if (state == COOK) {
			LOGGER.warn("Cannot be switched off while cooking");
		} else if (state == OFF) {
			LOGGER.info("Already switch off");
		}
	}
}
