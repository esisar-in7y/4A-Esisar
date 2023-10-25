package fr.esisar.calculatrice.operations;

import fr.esisar.calculatrice.CalculatriceException;

public abstract class OperationEnsembliste implements Operation {

	@Override
	public OperandeCardinalite getNbOperandes() {
		// TODO Auto-generated method stub
		return OperandeCardinalite.NO_LIMIT;
	}

	public Double calculer(Double... operandes) throws CalculatriceException {
		if (operandes.length == 0) {
			throw new CalculatriceException("pas d'operande");
		}
		return doCalculer(operandes);
	}

	protected abstract Double doCalculer(Double[] operandes);
	
}
