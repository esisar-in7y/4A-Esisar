package fr.esisar.calculatrice.operations.unaires;

import fr.esisar.calculatrice.operations.OperationUnaire;

public class Cosinus extends OperationUnaire {
	
	public String getNom()
	{
		return "cos";
	}

	protected Double doCalculer(Double op1) {
		return (Double) Math.cos(op1);
	}
}
