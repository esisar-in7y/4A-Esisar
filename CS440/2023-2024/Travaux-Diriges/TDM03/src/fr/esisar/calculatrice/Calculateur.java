package fr.esisar.calculatrice;

import java.util.HashSet;
import java.util.Set;

import fr.esisar.calculatrice.operations.*;
import fr.esisar.calculatrice.operations.binaires.*;
import fr.esisar.calculatrice.operations.unaires.*;
import fr.esisar.calculatrice.operations.ensemblistes.*;

public class Calculateur {

	public static void main(String[] args) {
		Set<Operation> operations = new HashSet<Operation>();
		operations.add(new Ajouter());
		operations.add(new Soustraire());
		operations.add(new Multiplier());
		operations.add(new Diviser());
		operations.add(new Cosinus());
		operations.add(new Sinus());
		operations.add(new Tangente());
		operations.add(new Maximum());
		operations.add(new Minimum());
		Calculatrice c = new Calculatrice(operations);
		c.ajouterOperation(new ValeurAbsolue());
		Double op1 = -6d;
		Double op2 = 6d;
		Double result;
		Double valabs;
		try {
			result = c.calculer("/", op1, op2);
			System.out.println(result);
			valabs = c.calculer("abs", op1);
			System.out.println(valabs);
		} catch (CalculatriceException e) {
			e.printStackTrace();
		}
	}

}
