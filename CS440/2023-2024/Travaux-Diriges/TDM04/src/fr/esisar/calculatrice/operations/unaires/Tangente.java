package fr.esisar.calculatrice.operations.unaires;

import fr.esisar.calculatrice.operations.OperationUnaire;

public class Tangente extends OperationUnaire{

	public String getNom()
	{
		return "tan";
	}

	protected Double doCalculer(Double op1) {
		return (Double) Math.tan(op1);
	}
}
