package fr.esisar.calculatrice.operations.unaires;

import fr.esisar.calculatrice.operations.OperationUnaire;

public class Sinus extends OperationUnaire{

	public String getNom()
	{
		return "sin";
	}

	protected Double doCalculer(Double op1) {
		return (Double) Math.sin(op1);
	}
}
