package fr.esisar.calculatrice.tests.operations.binaires;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.esisar.calculatrice.CalculatriceException;
import fr.esisar.calculatrice.operations.binaires.Ajouter;

public class AjouterTest {

	private final Ajouter ajouter = new Ajouter();
	
	@Test
	void testGetNomPlusSign() { // "+"
		assertEquals("+", ajouter.getNom());
	}
	@Test
	void testCalculerNormal() { // 99+1 = 100  
		try {
			assertEquals(100.0, ajouter.calculer(99d,1d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	
	/* Operandes Negatifs */
	@Test
	void testCalculerOperande1Negatif() { // -50+10 = -40  
		try {
			assertEquals(-40.0, ajouter.calculer(-50d,10d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerOperande2Negatif() { // 20+-5 = 15  
		try {
			assertEquals(15.0, ajouter.calculer(20d,-5d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	
	/* Operandes Nuls */
	@Test
	void testCalculerOperande1Null() { // 0+1 = 1
		try {
			assertEquals(1.0, ajouter.calculer(0d,1d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerOperande2Null() { // 1+0 = 1
		try {
			assertEquals(1.0, ajouter.calculer(1d,0d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerAllOperandeNull() { // 0+0 = 0
		try {
			assertEquals(0.0, ajouter.calculer(0d,0d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}

	/* Operandes Décimaux */
	@Test
	void testCalculerFloat() { // 0.5+0.6 = 1.1
		try {
			assertEquals(1.1, ajouter.calculer(0.5d,0.6d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerImpossibleFloatResult() { // 0.6+0.6 = 1.2
		try {
			assertEquals(11.4d, ajouter.calculer(5.6d,5.8d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	
}
