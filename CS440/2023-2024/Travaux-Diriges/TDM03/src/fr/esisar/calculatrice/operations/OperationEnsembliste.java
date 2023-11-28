package fr.esisar.calculatrice.operations;

import fr.esisar.calculatrice.CalculatriceException;

public abstract class OperationEnsembliste implements Operation {
	
	public Double calculer(Double ...operandes) throws CalculatriceException
	{
		return doCalculer(operandes);
	}
	
	protected abstract Double doCalculer(Double[] ops);
}
