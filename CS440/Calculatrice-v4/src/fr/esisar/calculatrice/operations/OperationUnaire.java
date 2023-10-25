package fr.esisar.calculatrice.operations;

import fr.esisar.calculatrice.CalculatriceException;

public abstract class OperationUnaire implements Operation {

	@Override
	public OperandeCardinalite getNbOperandes() {
		// TODO Auto-generated method stub
		return OperandeCardinalite.ONE;
	}

	public Double calculer(Double... operandes) throws CalculatriceException {
		if (operandes.length != 1) {
			throw new CalculatriceException("trop d'operande ou pas assez");
		}
		return doCalculer(operandes[0]);
	}

	protected abstract Double doCalculer(Double operande1);
	
}
