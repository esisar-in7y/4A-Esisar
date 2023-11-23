package fr.esisar.calculatrice.operations;

import fr.esisar.calculatrice.CalculatriceException;

public interface Operation {
	
	enum OperandeCardinalite {ONE, TWO, NO_LIMIT}; 
	
	public String getNom();	

	public OperandeCardinalite getNbOperandes();
	
	public Double calculer(Double ... operande) throws CalculatriceException;

}
