/**
 * 
 */
package fr.esisar.calculatrice;

import java.util.HashSet;
import java.util.Set;

import fr.esisar.calculatrice.operations.*;
import fr.esisar.calculatrice.operations.binaires.*;
import fr.esisar.calculatrice.operations.ensemblistes.*;
import fr.esisar.calculatrice.operations.unaires.*;

/**
 * @author userir
 *
 */
public class Calculateur {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws CalculatriceException {
		// TODO Auto-generated method stub

		Set<Operation> toutesLesOperations = new HashSet<Operation>();
		toutesLesOperations.add(new Ajouter()	);
		toutesLesOperations.add(new Diviser()	);
		toutesLesOperations.add(new Multiplier());
		toutesLesOperations.add(new Soustraire());
		toutesLesOperations.add(new Maximum()	);
		toutesLesOperations.add(new Minimum()	);
		toutesLesOperations.add(new Cosinus()	);
		toutesLesOperations.add(new Sinus()		);
		toutesLesOperations.add(new Tangente()	);
		toutesLesOperations.add(new ValeurAbsolue());
		
		Calculatrice calculatrice = new Calculatrice(toutesLesOperations);

		System.out.println("1+1 = " + calculatrice.calculer("+", 1.0, 1.0));
		System.out.println("1-1 = " + calculatrice.calculer("-", 1.0, 1.0));
		System.out.println("1/0 = " + calculatrice.calculer("/", 1.0, 0.0));
		System.out.println("1*1 = " + calculatrice.calculer("*", 1.0, 1.0));
		System.out.println("cos(1) = " + calculatrice.calculer("cos", 1.0));
		System.out.println("sin(1) = " + calculatrice.calculer("sin", 1.0));
		System.out.println("tan(1) = " + calculatrice.calculer("tan", 1.0));
		System.out.println("max(1, 2, 3) = " + calculatrice.calculer("max", 1.0, 2.0, 3.0));
		System.out.println("min(1, 2, 3) = " + calculatrice.calculer("min", 1.0, 2.0, 3.0));
		
	}

}
