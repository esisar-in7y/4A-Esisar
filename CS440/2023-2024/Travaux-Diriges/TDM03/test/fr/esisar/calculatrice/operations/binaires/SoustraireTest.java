package fr.esisar.calculatrice.operations.binaires;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.esisar.calculatrice.CalculatriceException;

public class SoustraireTest {
    private final Soustraire soustraire = new Soustraire();

    @Test
    void testGetNom() {
        assertEquals("-", soustraire.getNom());
    }

    @Test
    void testZeroMinusZero() throws CalculatriceException {
        assertEquals(0.0, soustraire.calculer(0.0, 0.0));
    }

    @Test
    void testZeroMinusOne() throws CalculatriceException {
        assertEquals(-1.0, soustraire.calculer(0.0, 1.0));
    }

    @Test
    void testPositiveNumbers() throws CalculatriceException {
        assertEquals(2.0, soustraire.calculer(3.0, 1.0));
    }

    @Test
    void testNegNumbers() throws CalculatriceException {
        assertEquals(2.0 - 5.0, soustraire.calculer(2.0, 5.0));
    }

    @Test
    void testFloatingNumbers() throws CalculatriceException {
        assertEquals(2.1, soustraire.calculer(3.3, 1.2));
    }
	
	@Test
	void testPositiveMinusNegative() throws CalculatriceException {
		assertEquals(5.0 + 3.0, soustraire.calculer(5.0, -3.0));
	}

	@Test
	void testNegativeMinusPositive() throws CalculatriceException {
		assertEquals(-5.0 - 3.0, soustraire.calculer(-5.0, 3.0));
	}

	@Test
	void testNaNValues() throws CalculatriceException {
		assertThrows(CalculatriceException.class, () -> soustraire.calculer(Double.NaN, 3.0));
		assertThrows(CalculatriceException.class, () -> soustraire.calculer(3.0, Double.NaN));
	}

	@Test
	void testInfinityValues() throws CalculatriceException {
		double result = soustraire.calculer(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY);
		assertTrue(Double.isNaN(result));
	}


	@Test
	void shouldTestMatrix() throws CalculatriceException {
		boolean test = true;
		for (double a = -10; a < 15; a++) {
			for (double b = -10; b < 15; b++) {
				double res = a - b;
				try{
					if (res != soustraire.calculer(a, b)) {
						System.out.println(a + " " + b + ":" + (res - soustraire.calculer(a, b)));
						test = false;
					}
				}catch(CalculatriceException e){
					System.out.println(a+" "+b+":"+e.getMessage());
					test = false;
				}
			}
		}
		assertEquals(true, test);
	}
}