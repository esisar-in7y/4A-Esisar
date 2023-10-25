package fr.esisar.calculatrice.tests.operations.ensemblistes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fr.esisar.calculatrice.CalculatriceException;
import fr.esisar.calculatrice.operations.ensemblistes.Maximum;


public class MaximumTest {
	
	private final Maximum maximum = new Maximum();
	
	@Test
	void testGetNom() { // "max"  
		assertEquals("max", maximum.getNom());
	}
	@Test
	void testCalculerMaxDebut() { // max(100,1,2,3,4,5) = 100  
		try {
			assertEquals(100.0, maximum.calculer(100d,1d,2d,3d,4d,5d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerMaxFin() { // max(1,2,3,4,5,100) = 100  
		try {
			assertEquals(100.0, maximum.calculer(1d,2d,3d,4d,5d,100d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	
	/* Operandes Negatifs */
	@Test
	void testCalculerOperandesNegatif() { //  max(-2,-3,-1,-4,-5) = -1  
		try {
			assertEquals(-1.0, maximum.calculer(-2d,-3d,-1d,-4d,-5d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerOperandesMix() { //  max(-2,-3,1,-4,-5) = 1  
		try {
			assertEquals(1.0, maximum.calculer(-2d,-3d,1d,-4d,-5d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	
	/* Operandes Nuls */
	@Test
	void testCalculerOperande1Null() { // max(0,1) = 1  
		try {
			assertEquals(1d, maximum.calculer(0d,1d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerOperande2Null() { // max(1,0) = 1
		try {
			assertEquals(1d, maximum.calculer(1d,0d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerAllOperandeNull() { // max(0,0) = 0
		try {
			assertEquals(0.0, maximum.calculer(0d,0d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}

	/* Nombres Operandes */
	@Test
	void testCalculerZeroOperande() { // max() -> ThrowException
		assertThrows(CalculatriceException.class, () ->  maximum.calculer() );
	}
	@Test
	void testCalculerUnOperande() { // max(5) = 5
		try {
			assertEquals(5d, maximum.calculer(5d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerDeuxOperande() { // max(5,10) = 10
		try {
			assertEquals(10d, maximum.calculer(5d,10d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerCinqOperande() { // max(1,2,3,4,5) = 5
		try {
			assertEquals(5d, maximum.calculer(1d,2d,3d,4d,5d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerDixOperande() { // max(1,2,3,4,5,6,7,8,9,10) = 10
		try {
			assertEquals(10d, maximum.calculer(1d,2d,3d,4d,5d,6d,7d,8d,9d,10d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}

	/* Operandes Décimaux */
	@Test
	void testCalculerFloat() { // max(0.1,0.2,0.3,0.4,-0.5) = 0.4
		try {
			assertEquals(0.4, maximum.calculer(0.1,0.2,0.3,0.4,-0.5));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	
}
