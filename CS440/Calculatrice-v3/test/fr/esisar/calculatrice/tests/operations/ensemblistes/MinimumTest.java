package fr.esisar.calculatrice.tests.operations.ensemblistes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fr.esisar.calculatrice.CalculatriceException;
import fr.esisar.calculatrice.operations.ensemblistes.Minimum;

public class MinimumTest {

	private final Minimum minimum = new Minimum();
	
	@Test
	void testGetNom() { // "min"
		assertEquals("min", minimum.getNom());
	}
	@Test
	void testCalculerMinDebut() { // min(1,100,2,3,4,5) = 1  
		try {
			assertEquals(1.0, minimum.calculer(100d,1d,2d,3d,4d,5d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerMinFin() { // min(100,2,3,4,5,1) = 1  
		try {
			assertEquals(1.0, minimum.calculer(100d,2d,3d,4d,5d,1d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	
	/* Operandes Negatifs */
	@Test
	void testCalculerOperandesNegatif() { //  min(-1,-2,-3,-4,-5) = -5 
		try {
			assertEquals(-5.0, minimum.calculer(-1d,-2d,-3d,-4d,-5d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerOperandesMix() { //  min(-2,-3,1,-4,-5) = -5  
		try {
			assertEquals(-5.0, minimum.calculer(-2d,-3d,1d,-4d,-5d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	
	/* Operandes Nuls */
	@Test
	void testCalculerOperande1Null() { // min(0,1) = 0
		try {
			assertEquals(0d, minimum.calculer(0d,1d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerOperande2Null() { // min(5,0) = 0
		try {
			assertEquals(0d, minimum.calculer(1d,0d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerAllOperandeNull() { // min(0,0) = 0
		try {
			assertEquals(0.0, minimum.calculer(0d,0d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}

	/* Nombres Operandes */
	@Test
	void testCalculerZeroOperande() { // min() -> ThrowException
		assertThrows(CalculatriceException.class, () ->  minimum.calculer() );
	}
	@Test
	void testCalculerUnOperande() { // min(5) = 5
		try {
			assertEquals(5d, minimum.calculer(5d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerDeuxOperande() { // min(5,10) = 5
		try {
			assertEquals(5d, minimum.calculer(5d,10d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerCinqOperande() { // min(10,20,30,40,50) = 10
		try {
			assertEquals(10d, minimum.calculer(10d,20d,30d,40d,50d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerDixOperande() { // min(100,200,300,400,500,600,700,800,900,1000) = 100
		try {
			assertEquals(100d, minimum.calculer(100d,200d,300d,400d,500d,600d,700d,800d,900d,1000d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}

	/* Operandes Décimaux */
	@Test
	void testCalculerFloat() { // min(0.1,0.2,0.3,0.4,-0.5) = -0.5
		try {
			assertEquals(-0.5, minimum.calculer(0.1,0.2,0.3,0.4,-0.5));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	
}
