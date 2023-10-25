package fr.esisar.calculatrice.operations.binaires;

import fr.esisar.calculatrice.CalculatriceException;
import fr.esisar.calculatrice.operations.OperationBinaire;

public class Diviser extends OperationBinaire {

	public String getNom() {
		return "/";
	}
	
	protected Double doCalculer(Double operande1, Double operande2) throws CalculatriceException {
		if(operande1==0) {
			throw new CalculatriceException("Division par zéro");
		}
		return operande2 / operande1;
	}

	
}
