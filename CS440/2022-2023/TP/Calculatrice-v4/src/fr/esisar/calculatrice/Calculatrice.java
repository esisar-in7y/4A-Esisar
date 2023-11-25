package fr.esisar.calculatrice;

import java.util.HashSet;
import java.util.Set;

import fr.esisar.calculatrice.operations.Operation;

public class Calculatrice {
	
	private Set<Operation> operations;

	public Calculatrice() {
		super();
		operations = new HashSet<Operation>();
	}

	public Calculatrice(Set<Operation> operations) {
		super();
		this.operations = operations;
	}
	
	public Set<Operation> getOperation() {
		return operations;
	}

	public void ajouterOperation(Operation operation) {
		operations.add(operation);
	}
	
	public void retirerOperation(Operation operation) {
		operations.remove(operation);
	}
	
	public Operation chercherOperation(String nom) {
		
		try {
			return operations
				.stream()
				.filter(op -> op.getNom().equals(nom))
				.findAny()
				.get();
		}
		catch(Exception e){
			
		}
		return null;
	}

	public Double calculer(String nom, Double ... operandes ) throws CalculatriceException {
		Operation operation = chercherOperation(nom);
		
		if(operation==null) {
			throw new CalculatriceException("operation innexistante");
		}
		
		return operation.calculer(operandes);
		
	}
	
	
}
