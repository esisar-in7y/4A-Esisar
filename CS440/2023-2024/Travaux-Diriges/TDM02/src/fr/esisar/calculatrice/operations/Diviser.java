package fr.esisar.calculatrice.operations;

import fr.esisar.calculatrice.CalculatriceException;

public class Diviser implements Operation{
	public String getNom()
	{
		return "/";
	}
	
	public Double calculer(Double op1, Double op2) throws CalculatriceException
	{
		if(op2 != 0d) {
			return op1 / op2;
		}
		throw new CalculatriceException("Division by 0");
	}
}
