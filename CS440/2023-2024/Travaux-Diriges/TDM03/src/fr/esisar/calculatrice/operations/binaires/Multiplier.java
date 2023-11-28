package fr.esisar.calculatrice.operations.binaires;

import fr.esisar.calculatrice.operations.OperationBinaire;

public class Multiplier extends OperationBinaire {
	public String getNom()
	{
		return "*";
	}
	
	public Double doCalculer(Double op1, Double op2)
	{
		return op1 * op2;
	}
}
