package fr.esisar.tp4;
/**
 * Cette classe représente un programme de test pour la hiérarchie d'employés de l'entreprise.
 * Elle crée une structure organisationnelle avec des ouvriers et des managers, puis affiche le nombre
 * d'employés sous chaque manager.
 */
public class EntrepriseTestDrive {
    /**
     * Point d'entrée du programme de test.
     *
     * @param args Les arguments de la ligne de commande (non utilisés dans ce programme).
     */
	public static void main(String[] args) {
        // Création des ouvriers
		final Ouvrier ouvrier1 = new Ouvrier("7369", "Smith");
		final Ouvrier ouvrier2 = new Ouvrier("7499", "Jones");
		final Ouvrier ouvrier3 = new Ouvrier("7521", "Allen");
		final Ouvrier ouvrier4 = new Ouvrier("7900", "James");
		final Ouvrier ouvrier5 = new Ouvrier("7876", "Martin");
		
        // Création des managers
		final Manager chef = new Manager("7902", "Ford");
		final Manager manager1 = new Manager("7566", "Adams");
		final Manager manager2 = new Manager("7788", "Scott");
		final Manager manager3 = new Manager("7901", "Blake");

        // Construction de la hiérarchie
		chef.addSubordonne(manager1);
		chef.addSubordonne(ouvrier5);
		chef.addSubordonne(manager2);
		manager1.addSubordonne(ouvrier1);
		manager1.addSubordonne(manager3);
		manager2.addSubordonne(ouvrier4);
		manager3.addSubordonne(ouvrier2);
		manager3.addSubordonne(ouvrier3);
		
        // Affichage du nombre d'employés sous chaque manager
		System.out.println("Chef : " + chef.countEmployes());
		System.out.println("manager1 : " + manager1.countEmployes());
		System.out.println("manager2 : " + manager2.countEmployes());
		System.out.println("manager3 : " + manager3.countEmployes());
		System.out.println("ouvrier1 : " + ouvrier1.countEmployes());
	}

}
