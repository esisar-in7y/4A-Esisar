package fr.esisar.tp4;

public class Ouvrier extends Employe {
	public Ouvrier(String numEmploye, String nom) {
		super(numEmploye, nom);
	}

	@Override
	int countEmployes() {
		return 1;
	}

}
