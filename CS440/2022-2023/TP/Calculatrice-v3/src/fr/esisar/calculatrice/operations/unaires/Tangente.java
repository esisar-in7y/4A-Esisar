package fr.esisar.calculatrice.operations.unaires;

import fr.esisar.calculatrice.operations.OperationUnaire;

public class Tangente extends OperationUnaire {

	public String getNom() {
		return "tan";
	}
	
	protected Double doCalculer(Double operande1) {
		return Math.tan(operande1);
	}

}
