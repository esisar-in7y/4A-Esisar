package fr.esisar.calculatrice.operations.binaires;

import fr.esisar.calculatrice.operations.*;

public class Ajouter extends OperationBinaire {
	public String getNom()
	{
		return "+";
	}
	
	public Double doCalculer(Double op1, Double op2)
	{
		return op1 + op2;
	}
}
