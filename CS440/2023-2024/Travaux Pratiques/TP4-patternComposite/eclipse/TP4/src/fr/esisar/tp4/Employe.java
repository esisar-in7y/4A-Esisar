package fr.esisar.tp4;

public abstract class Employe {
	
	public Employe(String numEmploye, String nom) {
		super();
		this.numEmploye = numEmploye;
		this.nom = nom;
	}

	protected String numEmploye;
	protected String nom;
	
	abstract int countEmployes();

	public String getNumEmploye() {
		return numEmploye;
	}

	public void setNumEmploye(String numEmploye) {
		this.numEmploye = numEmploye;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
}
