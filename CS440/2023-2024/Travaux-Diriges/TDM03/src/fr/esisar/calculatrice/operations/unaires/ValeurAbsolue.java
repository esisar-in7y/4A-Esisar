package fr.esisar.calculatrice.operations.unaires;

import fr.esisar.calculatrice.operations.OperationUnaire;

public class ValeurAbsolue extends OperationUnaire{

	public String getNom()
	{
		return "abs";
	}

	protected Double doCalculer(Double op1) {
		return (Double) Math.abs(op1);
	}
}
