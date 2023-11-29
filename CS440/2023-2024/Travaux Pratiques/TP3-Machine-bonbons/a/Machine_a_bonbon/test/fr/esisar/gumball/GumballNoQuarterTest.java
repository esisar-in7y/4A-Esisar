package fr.esisar.gumball;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class GumballNoQuarterTest {
	private final GumballMachine gm = new GumballMachine(0);

	@BeforeEach
	void beforeEach() {
		gm.setState(new GumballNoQuarter());
	}

	/* 5 Bonbons # # # # # # # # */
	@Nested
	@DisplayName("Tests for 5 bonbons")
	class BonbonsX5 {
		@BeforeEach
		void beforeEach() {
			gm.setCount(5);
		}

		@Test
		void testInsert() {
			gm.insertQuarter();
			assertEquals(5, gm.getCount());
			assertEquals("\nMighty Gumball, Inc.\n" + "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 5 gumballs\n" + "Machine is waiting for turn of crank\n", gm.toString());
		}

		@Test
		void testEject() {
			gm.ejectQuarter();
			assertEquals(5, gm.getCount());
			assertEquals("\nMighty Gumball, Inc.\n" + "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 5 gumballs\n" + "Machine is waiting for quarter\n", gm.toString());
		}

		@Test
		void testTurnCrank() {
			gm.turnCrank();
			assertEquals(5, gm.getCount());
			assertEquals("\nMighty Gumball, Inc.\n" + "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 5 gumballs\n" + "Machine is waiting for quarter\n", gm.toString());
		}
	}

	/* 1 Bonbons # # # # # # # # */
	@Nested
	@DisplayName("Tests for 1 bonbon")
	class BonbonsX1 {
		@BeforeEach
		void beforeEach() {
			gm.setCount(1);
		}

		@Test
		void testInsert() {
			gm.insertQuarter();
			assertEquals(1, gm.getCount());
			assertEquals("\nMighty Gumball, Inc.\n" + "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 1 gumball\n" + "Machine is waiting for turn of crank\n", gm.toString());
		}

		@Test
		void testEject() {
			gm.ejectQuarter();
			assertEquals(1, gm.getCount());
			assertEquals("\nMighty Gumball, Inc.\n" + "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 1 gumball\n" + "Machine is waiting for quarter\n", gm.toString());
		}

		@Test
		void testTurnCrank() {
			gm.turnCrank();
			assertEquals(1, gm.getCount());
			assertEquals("\nMighty Gumball, Inc.\n" + "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 1 gumball\n" + "Machine is waiting for quarter\n", gm.toString());
		}
	}

	/* 0 Bonbons # # # # # # # # */
	@Nested
	@DisplayName("Tests for 0 bonbons")
	class BonbonsX0 { /* Condition impossible, ca n'a pas de sens */
		@BeforeEach
		void beforeEach() {
			gm.setCount(0);
		}

		@Test
		void testInsert() {
			gm.insertQuarter();
			assertEquals(0, gm.getCount());
			assertEquals("\nMighty Gumball, Inc.\n" + "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 0 gumball\n" + "Machine is waiting for turn of crank\n", gm.toString());
		}

		@Test
		void testEject() {
			gm.ejectQuarter();
			assertEquals(0, gm.getCount());
			assertEquals("\nMighty Gumball, Inc.\n" + "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 0 gumball\n" + "Machine is waiting for quarter\n", gm.toString());
		}

		@Test
		void testTurnCrank() {
			gm.turnCrank();
			assertEquals(0, gm.getCount());
			assertEquals("\nMighty Gumball, Inc.\n" + "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 0 gumball\n" + "Machine is waiting for quarter\n", gm.toString());
		}
	}

}
