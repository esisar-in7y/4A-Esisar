package exo4;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerNIM {

	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException
    {
        // METTRE ICI L'ADRESSE IP DE VOTRE SERVEUR 
        System.setProperty("java.rmi.server.hostname", "192.168.130.166");  

        // Crée un objet réel capable de faire des calculs 
        NIMimpl nimImpl = new NIMimpl();

        // Crée un serveur ecoutant sur le port 7070, permettant d'accéder à cet objet réel
        NIM skeleton = (NIM) UnicastRemoteObject.exportObject(nimImpl,7070);

        //  Il y a un registry sur votre machine sur le port 5050
        Registry registry = LocateRegistry.getRegistry("127.0.0.1",5000);

        // On indique au registry qu'un service est présent sur notre machine, avec son nom 
        registry.bind("calculette", skeleton);

        System.out.println("Le serveur est prêt ...");
    }
}
