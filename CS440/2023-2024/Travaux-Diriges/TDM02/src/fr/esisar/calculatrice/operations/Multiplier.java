package fr.esisar.calculatrice.operations;

public class Multiplier implements Operation{
	public String getNom()
	{
		return "*";
	}
	
	public Double calculer(Double op1, Double op2)
	{
		return op1 * op2;
	}
}
