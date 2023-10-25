package fr.esisar.calculatrice.operations.unaires;

import fr.esisar.calculatrice.operations.OperationUnaire;

public class Cosinus extends OperationUnaire {

	public String getNom() {
		return "cos";
	}
	
	protected Double doCalculer(Double operande1) {
		return Math.cos(operande1);
	}

}
