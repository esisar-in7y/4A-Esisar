package fr.esisar.tp4;

import org.apache.logging.log4j.Level;

import java.util.HashSet;

/**
 * Cette classe représente un manager, qui est un type spécifique d'employé. Un
 * manager peut avoir plusieurs subordonnés (employés sous sa supervision).
 */
public class Manager extends Employe {
	/**
	 * Ensemble des subordonnés (employés sous la supervision du manager).
	 */
	public final HashSet<Employe> Subordonnes = new HashSet<Employe>();

	/**
	 * Constructeur de la classe Manager.
	 *
	 * @param numEmploye Le numéro du manager.
	 * @param nom        Le nom du manager.
	 */
	public Manager(String numEmploye, String nom) {
		super(numEmploye, nom);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int countEmployes() {
		int count = 0;
		for (Employe sub : Subordonnes) {
			count += sub.countEmployes();
		}
		return 1 + count;
	}

	/**
	 * Ajoute un nouvel employé en tant que subordonné du manager.
	 *
	 * @param employe L'employé à ajouter comme subordonné.
	 */
	public void addSubordonne(Employe employe) {
		if (employe.myManager == null) {
			employe.myManager = this;
			Subordonnes.add(employe);
			LOGGER.log(Level.INFO, getNumEmploye() + " a un nouveau subordonné:" + employe.getNumEmploye());
		} else {
			LOGGER.log(Level.WARN, getNumEmploye() + " a deja ce subordonné:" + employe.getNumEmploye());
		}
	}

	/**
	 * Supprime un subordonné du manager.
	 *
	 * @param employe L'employé à supprimer.
	 */
	public void removeSubordonne(Employe employe) {
		if (employe.myManager == this) {
			LOGGER.log(Level.INFO, getNumEmploye() + " n'a plus le subordonné:" + employe.getNumEmploye());
			Subordonnes.remove(employe);
			employe.myManager = null;
		}
	}
}
