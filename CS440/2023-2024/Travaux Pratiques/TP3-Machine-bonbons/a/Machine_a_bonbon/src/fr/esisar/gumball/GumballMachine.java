package fr.esisar.gumball;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Cette classe représente l'implémentation d'une classe Gumball Machine. Ici,
 * on n'implémente que la machine en elle-même.
 * 
 * @author Leprat-Moncorgé
 * @version 2.0
 */
public class GumballMachine {

	private static final Logger LOGGER = LogManager.getLogger(GumballMachine.class);

	/**
	 * Le nombre de bonbons dans la machine, initialisé par défaut à 0.
	 */
	private int count = 0;

	/**
	 * L'état initial de la machine, par défaut à Sold Out.
	 */
	private GumballMachineState state = new GumballSoldOut();

	/**
	 * Constructeur pour la machine, qui permet d'initialiser le nombre de bonbons
	 * et l'état.
	 *
	 * @param Le nombre de bonbons dans la machine
	 */
	public GumballMachine(int count) {
		super();
		this.count = count;
		if (count > 0) {
			state = new GumballNoQuarter();
		}
	}

	/**
	 * Insère une pièce dans la machine.
	 *
	 */
	public void insertQuarter() {
		state.insertQuarter(this);
	}

	/**
	 * Éjecte une pièce de la machine.
	 *
	 */
	public void ejectQuarter() {
		state.ejectQuarter(this);
	}

	/**
	 * Tourne la manivelle.
	 *
	 */
	public void turnCrank() {
		state.turnCrank(this);
	}

	/**
	 * Distribue un bonbon.
	 *
	 */
	@SuppressWarnings("unused")
	private void dispense() {
		state.dispenseState(this);
	}

	/**
	 * Getter pour le logger.
	 *
	 * @return LOGGER
	 */
	public static Logger getLogger() {
		return LOGGER;
	}

	/**
	 * Getter pour le nombre de bonbons dans la machine.
	 *
	 * @return count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Setter pour le nombre de bonbons dans la machine.
	 *
	 * @param nouveau count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Getter pour l'état.
	 *
	 * @return state
	 */
	public GumballMachineState getState() {
		return state;
	}

	/**
	 * Setter pour l'état de la machine.
	 *
	 * @param nouveau state
	 */
	public void setState(GumballMachineState state) {
		this.state = state;
	}
	
	/**
	 * Méthode toString de la machine.
	 *
	 * @return String
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("\nMighty Gumball, Inc.");
		result.append("\nJava-enabled Standing Gumball Model #2022\n");
		result.append("Inventory: " + count + " gumball");
		if (count <= 1) {
			result.append("s");
		}
		result.append("\nMachine is ");
		result.append(state.toString(this));
		result.append("\n");
		return result.toString();
	}
}
