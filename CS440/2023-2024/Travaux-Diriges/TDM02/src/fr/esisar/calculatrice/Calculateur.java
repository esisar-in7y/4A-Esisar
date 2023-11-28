package fr.esisar.calculatrice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.esisar.calculatrice.operations.Ajouter;
import fr.esisar.calculatrice.operations.Diviser;
import fr.esisar.calculatrice.operations.Multiplier;
import fr.esisar.calculatrice.operations.Operation;
import fr.esisar.calculatrice.operations.Soustraire;

public class Calculateur {

	public static void main(String[] args) {
		Set<Operation> operations = new HashSet<Operation>();
		operations.add(new Ajouter());
		operations.add(new Soustraire());
		operations.add(new Multiplier());
		operations.add(new Diviser());
		Calculatrice c = new Calculatrice(operations);
		Double op1 = 6d;
		Double op2 = 6d;
		Double result;
		try {
			result = c.calculer("/", op1, op2);
			System.out.println(result);
		} catch (CalculatriceException e) {
			e.printStackTrace();
		}
	}

}
