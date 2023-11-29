package fr.esisar.gumball;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class GumballSoldTest {
	private final GumballMachine gm = new GumballMachine(0);

	@BeforeEach
	void beforeEach() {
		gm.setState(new GumballSold());
	}

	/* 0 Bonbons # # # # # # # # */
	@Nested
	@DisplayName("Tests for 0 bonbons")
	class BonbonsX0 {
		@BeforeEach
		void beforeEach() {
			gm.setCount(0);
		}

		@Test
		void testInsert() {
			gm.insertQuarter();
			assertEquals(0, gm.getCount());
			assertEquals("\nMighty Gumball, Inc.\n" + "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 0 gumball\n" + "Machine is delivering a gumball\n", gm.toString());
		}

		@Test
		void testEject() {
			gm.ejectQuarter();
			assertEquals(0, gm.getCount());
			assertEquals("\nMighty Gumball, Inc.\n" + "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 0 gumball\n" + "Machine is delivering a gumball\n", gm.toString());
		}

		@Test
		void testTurnCrank() {
			gm.turnCrank();
			assertEquals(0, gm.getCount());
			assertEquals("\nMighty Gumball, Inc.\n" + "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 0 gumball\n" + "Machine is delivering a gumball\n", gm.toString());
		}
	}

	/* 5 Bonbons # # # # # # # # */
	@Nested
	@DisplayName("Tests for 5 bonbons")
	class BonbonsX5 { /* Condition impossible, ca n'a pas de sens */
		@BeforeEach
		void beforeEach() {
			gm.setCount(5);
		}

		@Test
		void testInsert() {
			gm.insertQuarter();
			assertEquals(5, gm.getCount());
			assertEquals("\nMighty Gumball, Inc.\n" + "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 5 gumballs\n" + "Machine is delivering a gumball\n", gm.toString());
		}

		@Test
		void testEject() {
			gm.ejectQuarter();
			assertEquals(5, gm.getCount());
			assertEquals("\nMighty Gumball, Inc.\n" + "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 5 gumballs\n" + "Machine is delivering a gumball\n", gm.toString());
		}

		@Test
		void testTurnCrank() {
			gm.turnCrank();
			assertEquals(5, gm.getCount());
			assertEquals("\nMighty Gumball, Inc.\n" + "Java-enabled Standing Gumball Model #2022\n"
					+ "Inventory: 5 gumballs\n" + "Machine is delivering a gumball\n", gm.toString());
		}
	}

}
