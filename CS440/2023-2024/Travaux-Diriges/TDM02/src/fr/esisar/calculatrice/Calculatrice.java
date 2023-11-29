package fr.esisar.calculatrice;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.esisar.calculatrice.operations.*;

public class Calculatrice {
	
	private Set<Operation> operations = new HashSet<Operation>();
	
	public Calculatrice() {
		super();
	}
	
	public Calculatrice(Set<Operation> operations) {
		for(Operation operat : operations) {
			this.operations.add(operat);
		}
	}
	
	public Operation chercherOperation(String nom) throws CalculatriceException
	{
		for(Operation operat : this.operations) {
			if(operat.getNom().equals(nom)) {
				return operat;
			}
		}
		throw new CalculatriceException("Invalid Operator");
	}
	
	public Double calculer(String nom, Double op1, Double op2) throws CalculatriceException
	{
		Operation opToDo = this.chercherOperation(nom);
		Double result = opToDo.calculer(op1, op2);
		return result;
	}
}
