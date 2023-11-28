package fr.esisar.tp4;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Employe
{
	public Manager(String numEmploye, String nom) {
		super(numEmploye, nom);
	}
	private List<Employe> Subordonnes = new ArrayList<Employe>();
	@Override
	int countEmployes() {
		int count=0;
		for(Employe sub: Subordonnes) {
			count += sub.countEmployes();
		}
		return 1+count;
	}
	
	public void addSubordonne(Employe employe) {
		if(!Subordonnes.contains(employe)) {
			Subordonnes.add(employe);
		}
	}
	public void removeSubordonne(Employe employe) {
		Subordonnes.remove(employe);
	}
}
