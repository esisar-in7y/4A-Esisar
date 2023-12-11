package exo3;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;

public interface Crack extends Remote {
	public String getPassword(String hash, long debut, long fin) throws RemoteException, NoSuchAlgorithmException;
}
