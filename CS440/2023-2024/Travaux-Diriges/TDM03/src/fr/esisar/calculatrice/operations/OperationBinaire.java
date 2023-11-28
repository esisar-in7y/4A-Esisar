package fr.esisar.calculatrice.operations;

import fr.esisar.calculatrice.CalculatriceException;

public abstract class OperationBinaire implements Operation {
	
	public Double calculer(Double ...operandes) throws CalculatriceException
	{
		Double op1 = operandes[0];
		Double op2 = operandes[1];
		return doCalculer(op1, op2);
	}
	
	protected abstract Double doCalculer (Double op1, Double op2) throws CalculatriceException;
}
