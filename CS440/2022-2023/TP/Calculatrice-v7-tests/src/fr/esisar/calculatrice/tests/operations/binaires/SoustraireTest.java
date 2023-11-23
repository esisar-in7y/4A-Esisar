package fr.esisar.calculatrice.tests.operations.binaires;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.esisar.calculatrice.CalculatriceException;
import fr.esisar.calculatrice.operations.binaires.Soustraire;

public class SoustraireTest {

	private Soustraire soustraire = new Soustraire();;
	
	
	@Test
	void testGetNom() { // "-"  
		assertEquals("-", soustraire.getNom());
	}
	@Test
	void testCalculer() { // 15-2 = 13  
		try {
			assertEquals(13.0, soustraire.calculer(15d,2d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	
	/* Operandes Negatives */
	@Test
	void testCalculerOperande1Negatif() { // -10-2 = -12  
		try {
			assertEquals(-12.0, soustraire.calculer(-10d,2d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerOperande2Negatif() { // 10--2 = 12  
		try {
			assertEquals(12.0, soustraire.calculer(10d,-2d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	
	/* Operandes Nulles */
	@Test
	void testCalculerOperande1Null() { // 0-2 = -2  
		try {
			assertEquals(-2.0, soustraire.calculer(0d,2d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerOperande2Null() { // 2-0 = 2  
		try {
			assertEquals(2.0, soustraire.calculer(2d,0d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	
	/* Opérandes Décimaux */
	@Test
	void testCalculerFloat() { // 3.5-1.2 = 2.3  
		try {
			assertEquals(2.3, soustraire.calculer(3.5d,1.2d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
	@Test
	void testCalculerFloatArondissement() { // 2.1-0.9 = 1.2  on déduit que l'arondissement est à l'entrée  
		try {
			assertEquals(1.2, soustraire.calculer(2.1d,0.9d));
		} catch (CalculatriceException e) {
			assertEquals(null, e);
		}
	}
}
