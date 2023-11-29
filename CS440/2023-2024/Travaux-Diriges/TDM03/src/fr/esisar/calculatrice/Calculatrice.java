package fr.esisar.calculatrice;
import java.util.HashSet;
import java.util.Set;

import fr.esisar.calculatrice.operations.Operation;

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
	
	public void ajouterOperation(Operation operation)
	{
		this.operations.add(operation);
	}

	public void retirerOperation(Operation operation)
	{
		this.operations.remove(operation);
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
	
	public Double calculer(String nom, Double ...ops) throws CalculatriceException
	{
		Operation opToDo = this.chercherOperation(nom);
		Double result = opToDo.calculer(ops);
		return result;
	}
}
