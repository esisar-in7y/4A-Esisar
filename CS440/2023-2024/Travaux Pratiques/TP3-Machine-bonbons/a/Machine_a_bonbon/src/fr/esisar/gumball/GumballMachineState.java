package fr.esisar.gumball;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Cette classe représente l'implémentation des états de la machine à bonbons.
 * 
 * @author Leprat-Moncorgé
 * @version 2.0
 */
public interface GumballMachineState {

	static final Logger LOGGER = LogManager.getLogger(GumballMachine.class);

	/**
	 * Insère une pièce dans la machine.
	 *
	 * @param gumballMachine la machine à gomme
	 */
	public void insertQuarter(GumballMachine gumballMachine);

	/**
	 * Éjecte une pièce de la machine.
	 *
	 * @param gumballMachine la machine à gomme
	 */
	public void ejectQuarter(GumballMachine gumballMachine);

	/**
	 * Tourne la manivelle de la machine.
	 *
	 * @param gumballMachine la machine à gomme
	 */
	public void turnCrank(GumballMachine gumballMachine);

	/**
	 * Distribue un bonbon de la machine.
	 *
	 * @param gumballMachine la machine à gomme
	 */
	public void dispenseState(GumballMachine gumballMachine);

	/**
	 * Retourne une représentation sous forme de chaîne de l'état de la machine.
	 *
	 * @param gumballMachine la machine à gomme
	 * @return une représentation sous forme de chaîne de l'état de la machine
	 */

	public String toString(GumballMachine gumballMachine);

}
