package fr.esisar.calculatrice.operations;

import fr.esisar.calculatrice.CalculatriceException;

public abstract class OperationUnaire implements Operation {

	public Double calculer(Double ...operandes) throws CalculatriceException
	{
		Double op1 = operandes[0];
		return doCalculer(op1);
	}
	
	protected abstract Double doCalculer (Double op1);
}
