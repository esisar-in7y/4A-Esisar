package exo4;

import java.rmi.RemoteException;

public class NIMimpl implements NIM {
	public int nbTotalPions;

	@Override
	public void retirerPion(int nbPions) throws RemoteException {
		if(nbPions >= 1 && nbPions <= 3)
		{
			nbTotalPions -= nbPions;
			System.out.println("Il reste " + nbPions + " pions.");
		}
	}

	@Override
	public void testWinner(int nbTotalPions) throws RemoteException {
		if(nbTotalPions == 0)
		{
			System.out.println("On a un gagnant !");
		}
	}
	

}
