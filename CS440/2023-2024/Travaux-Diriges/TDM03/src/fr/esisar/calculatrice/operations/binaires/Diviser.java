package fr.esisar.calculatrice.operations.binaires;

import fr.esisar.calculatrice.CalculatriceException;
import fr.esisar.calculatrice.operations.OperationBinaire;

public class Diviser extends OperationBinaire{
	public String getNom()
	{
		return "/";
	}
	
	public Double doCalculer(Double op1, Double op2) throws CalculatriceException
	{
		if(op2 != 0d) {
			return op1 / op2;
		}
		throw new CalculatriceException("Division by 0");
	}
}
