package fr.esisar.gumball;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class GumballHasQuarterTest {

	private final GumballMachine gm = new GumballMachine(0);
	
	@BeforeEach
	void beforeEach() {
		gm.setState(new GumballHasQuarter());
	}
	
	/* 5 Bonbons # # # # # # # # */
	@Nested
	@DisplayName("Tests for 5 bonbons")
	class BonbonsX5 {
		@BeforeEach
		void beforeEach() {
			gm.setcount(5);
		}
		@Test
		void testInsert() {
			gm.insertQuarter();
			assertEquals(5 ,gm.getcount());
			assertEquals("Mighty Gumball, Inc.\n"
					+ "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 5 gumballs\n"
					+ "Machine is waiting for turn of crank\n"
					, gm.toString());
		}
		@Test
		void testEject() {
			gm.ejectQuarter();
			assertEquals(5 ,gm.getcount());
			assertEquals("Mighty Gumball, Inc.\n"
					+ "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 5 gumballs\n"
					+ "Machine is waiting for quarter\n"
					, gm.toString());
		}
		@Test
		void testTrunCrank() {
			gm.turnCrank();
			assertEquals(4 ,gm.getcount());
			assertEquals("Mighty Gumball, Inc.\n"
					+ "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 4 gumballs\n"
					+ "Machine is waiting for quarter\n"
					, gm.toString());
		}
	}
	
	/* 1 Bonbon  # # # # # # # # */
	@Nested
	@DisplayName("Tests for 1 bonbon")
	class BonbonsX1 {
		@BeforeEach
		void beforeEach() {
			gm.setcount(1);
		}
		@Test
		void testInsert() {
			gm.insertQuarter();
			assertEquals(1 ,gm.getcount());
			assertEquals("Mighty Gumball, Inc.\n"
					+ "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 1 gumballs\n"
					+ "Machine is waiting for turn of crank\n"
					, gm.toString());
		}
		@Test
		void testEject() {
			gm.ejectQuarter();
			assertEquals(1 ,gm.getcount());
			assertEquals("Mighty Gumball, Inc.\n"
					+ "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 1 gumballs\n"
					+ "Machine is waiting for quarter\n"
					, gm.toString());
		}
		@Test
		void testTrunCrank() {
			gm.turnCrank();
			assertEquals(0 ,gm.getcount());
			assertEquals("Mighty Gumball, Inc.\n"
					+ "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 0 gumballs\n"
					+ "Machine is sold out\n"
					, gm.toString());
		}
	}
	
	/* 0 Bonbons # # # # # # # # */
	@Nested
	@DisplayName("Tests for 0 bonbons")
	class BonbonsX0 { /* Condition impossible, ca n'a pas de sens */
		@BeforeEach
		void beforeEach() {
			gm.setcount(0);
		}
			@Test
		void testInsert() {
			gm.insertQuarter();
			assertEquals(0 ,gm.getcount());
			assertEquals("Mighty Gumball, Inc.\n"
					+ "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 0 gumball\n"
					+ "Machine is waiting for turn of crank\n"
					, gm.toString());
		}
		@Test
		void testEject() {
			gm.ejectQuarter();
			assertEquals(0 ,gm.getcount());
			assertEquals("Mighty Gumball, Inc.\n"
					+ "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 0 gumball\n"
					+ "Machine is waiting for quarter\n"
					, gm.toString());
		}
		@Test
		void testTrunCrank() {
			gm.turnCrank();
			assertEquals(0 ,gm.getcount());
			assertEquals("Mighty Gumball, Inc.\n"
					+ "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 4 gumballs\n"
					+ "Machine is sold out\n"
					, gm.toString());
		}
	}
}
