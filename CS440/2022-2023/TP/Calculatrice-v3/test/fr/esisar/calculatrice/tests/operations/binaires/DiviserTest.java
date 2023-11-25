package fr.esisar.calculatrice.tests.operations.binaires;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fr.esisar.calculatrice.CalculatriceException;
import fr.esisar.calculatrice.operations.binaires.Diviser;

public class DiviserTest {

	private final Diviser diviser = new Diviser();
	
	@Test
	void testGetNomDivisionSign() { // "/"
		assertEquals("/", diviser.getNom());
	}
	@Test
	void testCalculerNormal() { // 99/1 = 100  
		try {
			assertEquals(99.0, diviser.calculer(99d,1d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerMemeOperandes() { // 99/1 = 1   
		try {
			assertEquals(1.0, diviser.calculer(10d,10d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerRationnelResult() { // 10/3 = 3.333333333...   
		try {
			assertEquals(10d/3, diviser.calculer(10d,3d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	
	/* Operandes Negatif */
	@Test
	void testCalculerOperande1Negatif() { // -10/2 = -5
		try {
			assertEquals(-5.0, diviser.calculer(-10d,2d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerOperande2Negatif() { // 10/-2 = -5
		try {
			assertEquals(-5.0, diviser.calculer(10d,-2d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculer2OperandesNegatif() { // -1/-2 = 0.5
		try {
			assertEquals(0.5, diviser.calculer(-1d,-2d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	
	/* Operandes Nuls */
	@Test
	void testCalculerOperande1Null() { // 0/1 = 0
		try {
			assertEquals(0.0, diviser.calculer(0d,1d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerOperande2Null() { // 1/0 -> ThrowException
		assertThrows(CalculatriceException.class, () -> { diviser.calculer(1d,0d); } );
	}
	
	/* Operandes Décimaux */
	@Test
	void testCalculerFloat() { // 4.2/0.2 = 21
		try {
			assertEquals(21.0, diviser.calculer(4.2,0.2));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerFloatResult() { // 99/2 = 45.5  
		try {
			assertEquals(49.5, diviser.calculer(99d,2d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerImpossibleFloatResult() { // 1.2/3 = 0.4  
		try {
			assertEquals(0.4, diviser.calculer(1.2d,3d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerImpossibleFloatResult2() { // 4.2/3 = 1.4  
		try {
			assertEquals(1.4, diviser.calculer(4.2d,3d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	
}

/*Assert DoesNotThrow */
