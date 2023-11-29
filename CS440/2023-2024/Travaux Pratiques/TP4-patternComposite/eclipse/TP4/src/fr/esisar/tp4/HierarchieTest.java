package fr.esisar.tp4;

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

	public void createHierarchie() {
		chef.addSubordonne(manager1);
		chef.addSubordonne(ouvrier5);
		chef.addSubordonne(manager2);
		manager1.addSubordonne(ouvrier1);
		manager1.addSubordonne(manager3);
		manager2.addSubordonne(ouvrier4);
		manager3.addSubordonne(ouvrier2);
		manager3.addSubordonne(ouvrier3);
		System.out.println("Chef : " + chef.countEmployes());
		System.out.println("manager1 : " + manager1.countEmployes());
		System.out.println("manager2 : " + manager2.countEmployes());
		System.out.println("manager3 : " + manager3.countEmployes());
		System.out.println("ouvrier1 : " + ouvrier1.countEmployes());
	}
}