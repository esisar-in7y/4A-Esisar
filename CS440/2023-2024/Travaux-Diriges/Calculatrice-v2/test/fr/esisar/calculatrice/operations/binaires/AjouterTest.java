package fr.esisar.calculatrice.operations.binaires;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

import fr.esisar.calculatrice.CalculatriceException;

public class AjouterTest {
	private final Ajouter ajouter = new Ajouter();

	@Test
	void shouldGetNomPlusSign() throws CalculatriceException {
		assertEquals("+", ajouter.getNom());
	}

	@Test
	void shouldTestPlusZero() throws CalculatriceException {
		assertDoesNotThrow(() -> ajouter.calculer(1.0, 0.0));
	}

	@Test
	void shouldTestPlusZero2() throws CalculatriceException { // if(a ou b == 0) raise J'aime pas rien ajouter
		assertDoesNotThrow(() -> ajouter.calculer(0.0, 1.0));
		assertEquals(1.0, ajouter.calculer(0.0, 1.0));
	}

	@Test
	void shouldTestPlusOne() throws CalculatriceException {
		assertEquals(2.0, ajouter.calculer(1.0, 1.0));
	}

	@Test
	void shouldTestPlusNeg() throws CalculatriceException {
		assertEquals(0.0, ajouter.calculer(-1.0, 1.0));
	}

	@Test
	void shouldTestFloating() throws CalculatriceException {
		assertEquals(2.3, ajouter.calculer(1.1, 1.2));
	}

	@Test
	void shouldTestPlusNeg2() throws CalculatriceException {
		assertEquals(-2.0, ajouter.calculer(-1.0, -1.0));
	}

	@Test
	void shouldTestPlusNeg3() throws CalculatriceException {
		assertEquals(-3.0, ajouter.calculer(-1.0, -2.0));
	}

	@Test
	void shouldTestPlusNeg4() throws CalculatriceException {
		assertEquals(-4.0, ajouter.calculer(-1.0, -3.0));
	}

	@Test
	void shouldTestBig() throws CalculatriceException {
		assertEquals(-100.0 + 15.0, ajouter.calculer(-100.0, 15.0));
	}
	@Test
	void shouldTestBig0() throws CalculatriceException {
		assertEquals(1.0 + 9.1, ajouter.calculer(1.0, 9.1));
	}

	@Test
	void shouldTestBig1() throws CalculatriceException {
		assertEquals(1.0 + 10.1, ajouter.calculer(1.0, 10.1));
	}
	@Test
	void shouldTestBig2() throws CalculatriceException {
		assertEquals(50.5 + 12.1, ajouter.calculer(50.5, 12.1));
	}

	@Test
	void shouldTestBig3() throws CalculatriceException {
		assertEquals(2.5 + 12.1, ajouter.calculer(12.1, 2.5));
	}

	@Test
	void shouldTestPlusMatrix() throws CalculatriceException {
		boolean test = true;
		for (double a = -10; a < 15; a++) {
			for (double b = -10; b < 15; b++) {
				double res = a + b;
				try{
					if (res != ajouter.calculer(a, b)) {
						System.out.println(a + " " + b + ":" + (res - ajouter.calculer(a, b)));
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

	@Test
	void shouldTestNullInput() throws CalculatriceException {
		assertThrows(NullPointerException.class, () -> {
			ajouter.calculer(null, 1.0);
		});
	}
}