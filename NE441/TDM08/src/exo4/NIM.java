package exo4;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NIM extends Remote {
	public void retirerPion(int nbPions) throws RemoteException;
	public void testWinner(int nbTotalPions) throws RemoteException;
}
