package fr.esisar.tp4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Cette classe abstraite représente un employé générique. Les classes concrètes
 * qui héritent de cette classe doivent implémenter la méthode countEmployes().
 */
public abstract class Employe {
	/**
	 * Logger pour le logging.
	 */
	protected static final Logger LOGGER = LogManager.getLogger(Employe.class);
	/**
	 * Référence vers le manager de l'employé.
	 */
	public Manager myManager = null;
	/**
	 * Numéro de l'employé.
	 */
	protected String numEmploye;
	/**
	 * Nom de l'employé.
	 */
	protected String nom;

	/**
	 * Constructeur de la classe Employe.
	 *
	 * @param numEmploye Le numéro de l'employé.
	 * @param nom        Le nom de l'employé.
	 */
	public Employe(String numEmploye, String nom) {
		super();
		this.numEmploye = numEmploye;
		this.nom = nom;
	}

	/**
	 * Méthode abstraite pour compter le nombre d'employés, à implémenter dans les
	 * classes dérivées.
	 *
	 * @return Le nombre d'employés.
	 */
	abstract public int countEmployes();

	/**
	 * Getter pour le numéro de l'employé.
	 *
	 * @return Le numéro de l'employé.
	 */
	public String getNumEmploye() {
		return numEmploye;
	}

	/**
	 * Setter pour le numéro de l'employé.
	 *
	 * @param numEmploye Le nouveau numéro de l'employé.
	 */
	public void setNumEmploye(String numEmploye) {
		this.numEmploye = numEmploye;
	}

	/**
	 * Getter pour le nom de l'employé.
	 *
	 * @return Le nom de l'employé.
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter pour le nom de l'employé.
	 *
	 * @param nom Le nouveau nom de l'employé.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
}
