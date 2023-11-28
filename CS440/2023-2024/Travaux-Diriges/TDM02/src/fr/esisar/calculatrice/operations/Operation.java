package fr.esisar.calculatrice.operations;

import fr.esisar.calculatrice.CalculatriceException;

public interface Operation {
	public String getNom();
	
	public abstract Double calculer(Double op1, Double op2) throws CalculatriceException;
}
