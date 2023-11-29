package fr.esisar.calculatrice;

import java.util.Set;

import fr.esisar.calculatrice.operations.Operation;

public class Calculatrice {
	
	private Set<Operation> collectionOperateur;
	
	public Calculatrice() throws CalculatriceException {
		throw new CalculatriceException("Pas d'argument !");
	}
	
	public Calculatrice(Set<Operation> a) {
		collectionOperateur = a;
	}

	public Operation chercherOperation(String nom) throws CalculatriceException {
		
		for (Operation operateur : collectionOperateur) {
			if (operateur.getNom().equals(nom)){
				return operateur;
			}
		}
		
		
		throw new CalculatriceException();
		
	}

	public Double calculer(String nom, Double operande1, Double operande2) throws CalculatriceException {
		
		
		Operation operation = chercherOperation(nom);
		
		
		return operation.calculer(operande1, operande2);
		
	}
	
	
}
