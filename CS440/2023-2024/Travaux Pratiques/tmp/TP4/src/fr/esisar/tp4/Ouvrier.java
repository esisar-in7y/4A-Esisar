package fr.esisar.tp4;

/**
 * Cette classe représente un ouvrier, qui est un type spécifique d'employé. Les
 * ouvriers ne peuvent avoir de subordonnés.
 */
public class Ouvrier extends Employe {
	/**
	 * Constructeur de la classe Ouvrier.
	 *
	 * @param numEmploye Le numéro de l'ouvrier.
	 * @param nom        Le nom de l'ouvrier.
	 */
	public Ouvrier(String numEmploye, String nom) {
		super(numEmploye, nom);
	}

	/**
	 * {@inheritDoc} Les ouvriers ne peuvent avoir de subordonnés, donc le nombre
	 * d'employés est toujours 1.
	 */
	@Override
	public int countEmployes() {
		return 1;
	}
}
