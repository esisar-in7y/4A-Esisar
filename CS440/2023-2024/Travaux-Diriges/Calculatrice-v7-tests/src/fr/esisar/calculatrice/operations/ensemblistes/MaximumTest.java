package fr.esisar.calculatrice.operations.ensemblistes;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import fr.esisar.calculatrice.CalculatriceException;

public class MaximumTest {

    private final Maximum maximum = new Maximum();

    @Test
    void checkName() {
        assertEquals("max", maximum.getNom());
    }

    @Test
    @Timeout(value=1, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    void Large() throws CalculatriceException {  
        assertEquals(-8.0, maximum.calculer(-9d, -8d, -8d, -8d, -8d, -10d, -8d, -12d, -8d, -8d, -8d, -8d, -8d, -8d, -8d));
    }

    @Test
    void simple() throws CalculatriceException {
        assertEquals(8.0, maximum.calculer(7d, 8d));
    }

    @Test
    void simple2() throws CalculatriceException {
        assertEquals(-8.0, maximum.calculer(-8d, -9d));
    }

    @Test
    void negativeNumbers() throws CalculatriceException {
        double a = -11.0;
        double b = -10.0;
        Double[] values = { a, b };
        assertDoesNotThrow(() -> maximum.calculer(values));
        assertEquals(Math.max(a, b), maximum.calculer(values));
    }

    @Test
    void testOperandeZero1() throws CalculatriceException {
        assertEquals(1d, maximum.calculer(0d, 1d));
    }

    @Test
    void testOperandeZero2() throws CalculatriceException {
        assertEquals(7d, maximum.calculer(7d, 0d));
    }

    @Test
    void testCalculerUnOperande() throws CalculatriceException {
        assertEquals(5d, maximum.calculer(5d));
    }

    @Test
    void testCalculerCinqOperande() throws CalculatriceException {
        assertEquals(50d, maximum.calculer(20d, 30d, 10d, 40d, 50d));
    }
}