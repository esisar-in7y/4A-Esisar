package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import fr.esisar.tp4.*;

public class HierarchieTest {
	private final Ouvrier ouvrier1 = new Ouvrier("7369", "Smith");
	private final Ouvrier ouvrier2 = new Ouvrier("7499", "Jones");
	private final Ouvrier ouvrier3 = new Ouvrier("7521", "Allen");
	private final Ouvrier ouvrier4 = new Ouvrier("7900", "James");
	private final Ouvrier ouvrier5 = new Ouvrier("7876", "Martin");
	private final Manager chef = new Manager("7902", "Ford");
	private final Manager manager1 = new Manager("7566", "Adams");
	private final Manager manager2 = new Manager("7788", "Scott");
	private final Manager manager3 = new Manager("7901", "Blake");

	@Before
	public void createHierarchie() {
		System.out.println(System.getProperty("org.junit.Test"));
		chef.addSubordonne(manager1);
		chef.addSubordonne(ouvrier5);
		chef.addSubordonne(manager2);
		manager1.addSubordonne(ouvrier1);
		manager1.addSubordonne(manager3);
		manager2.addSubordonne(ouvrier4);
		manager3.addSubordonne(ouvrier2);
		manager3.addSubordonne(ouvrier3);
	}

	@After
	public void cleanHierarchie() {
		chef.Subordonnes.clear();
		manager1.Subordonnes.clear();
		manager2.Subordonnes.clear();
		manager3.Subordonnes.clear();
		ouvrier1.myManager=null;
		ouvrier2.myManager=null;
		ouvrier3.myManager=null;
		ouvrier4.myManager=null;
		ouvrier5.myManager=null;
		chef.myManager=null;
		manager1.myManager=null;
		manager2.myManager=null;
		manager3.myManager=null;
	}

	@Test
	public void ajoutSuppressionOuvrier() {
		Ouvrier ouvrier0 = new Ouvrier("6969", "Jony");
		assertEquals(5, manager1.countEmployes());

		assertEquals(2, manager1.Subordonnes.size());

		assertFalse(manager1.Subordonnes.contains(ouvrier0));

		manager1.addSubordonne(ouvrier0);

		assertEquals(6, manager1.countEmployes());

		assertEquals(3, manager1.Subordonnes.size());

		assertTrue(manager1.Subordonnes.contains(ouvrier0));

		manager1.removeSubordonne(ouvrier0);
		
		assertEquals(5, manager1.countEmployes());

		assertEquals(2, manager1.Subordonnes.size());

		assertFalse(manager1.Subordonnes.contains(ouvrier0));
	}
	
	@Test
	public void ajoutSuppressionManager() {
		Manager manager0 = new Manager("6969", "Mr Jony");
		assertEquals(2, manager2.countEmployes());

		assertEquals(1, manager2.Subordonnes.size());

		assertFalse(manager2.Subordonnes.contains(manager0));

		manager2.addSubordonne(manager0);

		assertEquals(3, manager2.countEmployes());

		assertEquals(2, manager2.Subordonnes.size());

		assertTrue(manager2.Subordonnes.contains(manager0));

		manager2.removeSubordonne(manager0);

		assertEquals(2, manager2.countEmployes());

		assertEquals(1, manager2.Subordonnes.size());

		assertFalse(manager2.Subordonnes.contains(manager0));
	}
	
	@Test
	public void multipleManager() {
		Ouvrier ouvrier0 = new Ouvrier("6969", "Jony");
		chef.addSubordonne(ouvrier0);
		assertTrue(chef.Subordonnes.contains(ouvrier0));
		assertFalse(manager1.Subordonnes.contains(ouvrier0));
		assertFalse(manager2.Subordonnes.contains(ouvrier0));
		assertFalse(manager3.Subordonnes.contains(ouvrier0));
		
		manager2.addSubordonne(ouvrier0);

		assertTrue(chef.Subordonnes.contains(ouvrier0));
		assertFalse(manager1.Subordonnes.contains(ouvrier0));
		assertFalse(manager2.Subordonnes.contains(ouvrier0));
		assertFalse(manager3.Subordonnes.contains(ouvrier0));
	}

}