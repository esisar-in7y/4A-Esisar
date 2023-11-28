package fr.esisar.calculatrice.operations.ensemblistes;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.experimental.ParallelComputer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import fr.esisar.calculatrice.CalculatriceException;

public class MinimumTest {
    
    private final Minimum minimum = new Minimum();

    @Test
    void checkName() {
        assertEquals("min", minimum.getNom());
    }

    @Test
    @Timeout(value=1, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
    void Large() throws CalculatriceException {  
        assertEquals(-9.0, minimum.calculer(-9d, -8d, -8d, -8d, -8d, -8d, -8d, -8d, -8d, -8d, -8d, -8d, -8d, -8d, -8d));
    }

    @Test
    void simple() throws CalculatriceException {  
        assertEquals(7.0, minimum.calculer(7d, 8d));
    }
    @Test
    void simple2() throws CalculatriceException {  
        assertEquals(-9.0, minimum.calculer(-9d, -8d));
    }
    @Test
    void negativeNumbers() throws CalculatriceException{
        double a=-11.0;
        double b=-10.0;
        Double[] values = {a,b};
        assertDoesNotThrow(()-> minimum.calculer(values));
        assertEquals(Math.min(a,b),minimum.calculer(values));
    }
    @Test
    void testOperandeZero1() throws CalculatriceException {
        assertEquals(0d, minimum.calculer(0d,1d));
    }
    @Test
    void testOperandeZero2() throws CalculatriceException { 
        assertEquals(0d, minimum.calculer(7d,0d));
    }
    @Test
    void testCalculerUnOperande()  throws CalculatriceException { 
        assertEquals(5d, minimum.calculer(5d));
    }
    @Test
    void testCalculerCinqOperande()  throws CalculatriceException {
        assertEquals(10d, minimum.calculer(10d,20d,30d,40d,50d));
    }
}