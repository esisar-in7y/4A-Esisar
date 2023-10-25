package fr.esisar.calculatrice.operations.binaires;

import fr.esisar.calculatrice.operations.OperationBinaire;

public class Soustraire extends OperationBinaire {

	public String getNom() {
		return "-";
	}
	
	protected Double doCalculer(Double operande1, Double operande2) {
		return operande1 - operande2;
	}

	
}
