package fr.esisar.calculatrice.operations.binaires;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fr.esisar.calculatrice.CalculatriceException;

public class DiviserTest {
	private final Diviser diviser = new Diviser();

	@Test
	void shouldGetNomSign() throws CalculatriceException {
		assertEquals("/", diviser.getNom());
	}

	@Test
	void shouldTestNormal() throws CalculatriceException {
		assertEquals(2.0, diviser.calculer(4.0, 2.0));
	}

	@Test
	void shouldTestZero() throws CalculatriceException {
		assertDoesNotThrow(() -> diviser.calculer(0.0, 1.0));
	}

	@Test
	void shouldTestFloating() throws CalculatriceException {
		assertEquals(1.75, diviser.calculer(1.4, 0.8));
	}

	@Test
	void shouldTestNeg2() throws CalculatriceException {
		assertEquals(1.0, diviser.calculer(-1.0, -1.0));
	}

	@Test
	void shouldTestNeg3() throws CalculatriceException {
		assertDoesNotThrow(() -> diviser.calculer(-1.0, 2.0));
	}

	@Test
	void shouldTestNeg4() throws CalculatriceException { // l'argument a gauche doit etre >0
		assertDoesNotThrow(() -> diviser.calculer(-3.0, -1.0));
	}

	@Test
	void shouldTestNeg5() throws CalculatriceException {
		assertEquals(-3.0, diviser.calculer(3.0, -1.0));
	}

	@Test
	void shouldTestSmall() throws CalculatriceException { // l'argument a gauche doit etre >= 2
		assertDoesNotThrow(() -> diviser.calculer(1.9, 2.0));
	}

	@Test
	void shouldTestSmall2() throws CalculatriceException { // l'argument a gauche doit etre >= element de droite
		assertEquals(4.1 / 4.0, diviser.calculer(4.1, 4.0));
		assertDoesNotThrow(() -> diviser.calculer(3.9, 4.0));
		assertEquals(4.0 / 4.0, diviser.calculer(4.0, 4.0));
	}

	@Test
	void shouldTestEqual() throws CalculatriceException { // si a == b alors ca renvoie 0
		assertEquals(1.0, diviser.calculer(10.0, 10.0));
	}

	@Test
	void shouldTestEqualSmallDiff() throws CalculatriceException {
		assertEquals(1.01, diviser.calculer(10.1, 10.0));
	}

	@Test
	void shouldTestBig() throws CalculatriceException {
		for (double i = 2.1; i < 100_000; i += 100) {
			assertEquals(i / 2.0, diviser.calculer(i, 2.0));
		}
	}

	@Test
	void shouldTestDivideByZero() throws CalculatriceException {
		assertThrows(CalculatriceException.class, () -> {
			diviser.calculer(1.0, 0.0);
		});
	}

	@Test
	void shouldTestNullInput() throws CalculatriceException {
		assertThrows(NullPointerException.class, () -> {
			diviser.calculer(null, 1.0);
		});
	}

	@Test
	void shouldTestMatrix() {
		boolean test = true;
		for (double a = -10; a < 15; a++) {
			for (double b = -10; b < 15; b++) {
				if (b == 0.0)
					continue;
				double res = a / b;
				try {
					if (res != diviser.calculer(a, b)) {
						System.out.println(a + " " + b + ":" + (res - diviser.calculer(a, b)));
						test = false;
					}
				} catch (CalculatriceException e) {
					System.out.println(a + " " + b + ":" + e.getMessage());
					test = false;
				}
			}
		}
		assertEquals(true, test);
	}
}