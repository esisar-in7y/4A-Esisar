package fr.esisar.calculatrice.operations.binaires;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import fr.esisar.calculatrice.CalculatriceException;

public class MultiplierTest {
	private final Multiplier multiplier= new Multiplier();

	@BeforeEach
	public void reset() throws IllegalAccessException {
		Field[] fields = Multiplier.class.getDeclaredFields();
		for (Field field : fields) {
			if (Modifier.isStatic(field.getModifiers())) {
				field.setAccessible(true);
				if (field.getType() == int.class) {
					field.set(null, 0);
				} else if (field.getType() == boolean.class) {
					field.set(null, false);
				} else {
					field.set(null, null);
				}
			}
		}
	}

	@Test
	void shouldGetNomTimesSign() throws CalculatriceException {
		assertEquals("*", multiplier.getNom());
	}

	@Test
    void shouldMultiplyZeroByAnyNumber() throws CalculatriceException {
        assertEquals(0.0, multiplier.calculer(0.0, 5.0));
        assertEquals(0.0, multiplier.calculer(0.0, -8.0));
        assertEquals(0.0, multiplier.calculer(0.0, 0.0));
    }
	
	@Test
    void shouldMultiplyByZero() throws CalculatriceException {
        assertEquals(0.0, multiplier.calculer(7.0, 0.0));
        assertEquals(0.0, multiplier.calculer(-11.0, 0.0));
    }


	@Test
	void shouldTestTimesOne() throws CalculatriceException {
		assertEquals(2.0, multiplier.calculer(1.0, 2.0));
	}

	@Test
	void shouldTestTimesOne2() throws CalculatriceException {
		assertEquals(2.0, multiplier.calculer(2.0, 1.0));
	}

	@Test
	void shouldTestTimesOne3() throws CalculatriceException {
		assertEquals(3.0 * 2.0, multiplier.calculer(3.0, 2.0));
		assertEquals(4.0 * 2.0, multiplier.calculer(4.0, 2.0));
		assertEquals(5.0 * 2.0, multiplier.calculer(5.0, 2.0));
		assertEquals(6.0 * 2.0, multiplier.calculer(6.0, 2.0));
	}

	@Test
	void shouldTestTimesNeg() throws CalculatriceException {
		assertEquals(4.0, multiplier.calculer(-2.0, -2.0));
	}


	@Test
	void shouldTestBig2() throws CalculatriceException {
		assertEquals(3.0 * 6.0, multiplier.calculer(3.0, 6.0));
		assertEquals(3.0 * 7.0, multiplier.calculer(3.0, 7.0));
		assertEquals(3.0 * 8.0, multiplier.calculer(3.0, 8.0));
		assertEquals(3.0 * 9.0, multiplier.calculer(3.0, 9.0));
		assertEquals(3.0 * 10.0, multiplier.calculer(3.0, 10.0));
		assertEquals(3.0 * 11.0, multiplier.calculer(3.0, 11.0));
		assertEquals(3.0 * 12.0, multiplier.calculer(3.0, 12.0));
	}

	@Test
	void shouldTestBig3() throws CalculatriceException {
		assertEquals(2.0 * 2.0, multiplier.calculer(2.0, 2.0));
		assertEquals(2.0 * 3.0, multiplier.calculer(2.0, 3.0));
		assertEquals(2.0 * 4.0, multiplier.calculer(2.0, 4.0));
		assertEquals(2.0 * 5.0, multiplier.calculer(2.0, 5.0));
		assertEquals(2.0 * 5.0, multiplier.calculer(2.0, 6.0));
	}

	@Test
	void shouldTestBig4() throws CalculatriceException {
		assertEquals(2.0 * 2.0, multiplier.calculer(2.0, 2.0));
		assertEquals(2.0 * 3.0, multiplier.calculer(2.0, 3.0));
		assertEquals(2.0 * 4.0, multiplier.calculer(2.0, 4.0));
		assertEquals(2.0 * 5.0, multiplier.calculer(2.0, 5.0));
	}
	
	@Test
    void shouldMultiplyFractionalNumbers() throws CalculatriceException {
        assertEquals(0.75, multiplier.calculer(1.5, 0.5));
        assertEquals(-2.5, multiplier.calculer(5.0, -0.5));
    }

	@Test
	void shouldTestMatrix() throws CalculatriceException {
		boolean test = true;
		for (double a = -10; a < 15; a++) {
			for (double b = -10; b < 15; b++) {
				double res = a * b;
				try{
					if (res != multiplier.calculer(a, b)) {
						System.out.println(a + " " + b + ":" + (res - multiplier.calculer(a, b)));
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
			multiplier.calculer(null, 1.0);
		});
	}
}