package fr.esisar.calculatrice.tests.operations.binaires;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import fr.esisar.calculatrice.CalculatriceException;
import fr.esisar.calculatrice.operations.binaires.Multiplier;

public class MultiplierTest {

	private Multiplier multiplier = new Multiplier();
	
	@AfterEach 
	void resetObject() { // fix uniquement pour calculatrticev7  
		try {
			while(multiplier.calculer(1d,1d) !=666);
		} catch (CalculatriceException e) {
		}
	}
	
	
	@Test
	void testGetNom() { // "*"  
		assertEquals("*", multiplier.getNom());
	}
	@Test
	void testCalculer() { // 15*2 = 30  
		try {
			assertEquals(30.0, multiplier.calculer(15d,2d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculer1() { // 15*1 = 15  
		try {
			assertEquals(15.0, multiplier.calculer(15d,1d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	
	/* Operandes Negatives */
	@Test
	void testCalculerOperande1Negatif() { // -10*2 = -20  
		try {
			assertEquals(-20.0, multiplier.calculer(-10d,2d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerOperande2Negatif() { // 10*-2 = -20  
		try {
			assertEquals(-20.0, multiplier.calculer(10d,-2d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	
	/* Operandes Nulles */
	@Test
	void testCalculerOperande1Null() { // 0*2 = 0  
		try {
			assertEquals(0.0, multiplier.calculer(0d,2d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerOperande2Null() { // 2*0 = 0  
		try {
			assertEquals(0.0, multiplier.calculer(2d,0d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	
	/* Opérandes Décimaux */
	@Test
	void testCalculerFloat() { // 1.2*3 = 3.6  
		try {
			assertEquals(3.6, multiplier.calculer(1.2d,3d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerImpossibleFloatResult() { // 0.3*3 = 0.9  
		try {
			assertEquals(0.9, multiplier.calculer(0.3d,3d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
}
