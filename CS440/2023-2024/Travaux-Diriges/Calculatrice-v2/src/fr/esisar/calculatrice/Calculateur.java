/**
 * 
 */
package fr.esisar.calculatrice;

import java.util.HashSet;
import java.util.Set;

import fr.esisar.calculatrice.operations.*;

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
		
		Calculatrice calculatrice = new Calculatrice(toutesLesOperations);

		System.out.println("1+1 = " + calculatrice.calculer("+", 1.0, 1.0));
		System.out.println("1-1 = " + calculatrice.calculer("-", 1.0, 1.0));
		System.out.println("1/0 = " + calculatrice.calculer("/", 1.0, 0.0));
		System.out.println("1*1 = " + calculatrice.calculer("*", 1.0, 1.0));
		
	}

}
