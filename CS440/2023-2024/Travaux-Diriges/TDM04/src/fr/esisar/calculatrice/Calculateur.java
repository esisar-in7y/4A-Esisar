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
		operations.add(new ValeurAbsolue());
		operations.add(new Maximum());
		Calculatrice c = new Calculatrice(operations);
		Double op1 = 2.3;
		Double op2 = 5.4;
		Double result;
		Double valabs;
		int sum = operations.stream()
				.filter(o -> o.getNbOperandes() == OperandeCardinalite.TWO)
				.mapToInt(o-> operations.size())
				.sum();
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
