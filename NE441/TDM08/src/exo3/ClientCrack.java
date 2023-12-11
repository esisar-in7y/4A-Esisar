package exo3;

import java.nio.channels.AlreadyBoundException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientCrack extends Thread {
	private String hash;
	private long debut;
	private long fin;
	private String password;
	private Crack crack;

	public ClientCrack(String hash, long debut, long fin, Crack crack) {
		super();
		this.hash = hash;
		this.debut = debut;
		this.fin = fin;
		this.crack = crack;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void run() {
		try {
			password = crack.getPassword(hash, debut, fin);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
			throws AccessException, RemoteException, AlreadyBoundException, NotBoundException, InterruptedException {

		// Il y a un registry sur le port 5050 sur votre machine
		Registry registry = LocateRegistry.getRegistry("127.0.0.1", 5050);

		long min = 000_000_000;
		long max = 999_999_999;

		int nbThread = 16;
		try (Scanner in = new Scanner(System.in)) {
			String hash;
			while (true) {
				hash = in.nextLine();

				String[] services = registry.list();

				ArrayList<ClientCrack> listeThread = new ArrayList<>();

				int parts = services.length * nbThread;
				long partSize = (max - min + 1) / parts;
				long start, end;
				for (int i = 0; i < parts / nbThread; i++) {
					Crack stub = (Crack) registry.lookup(services[i]);
					for (int y = 0; y < nbThread; y++) {
						start = min + (i * nbThread + y) * partSize;
						end = ((i * nbThread + y) == parts - 1) ? max : start + partSize - 1;
						ClientCrack thread = new ClientCrack(hash.toUpperCase(), start, end, stub);
						listeThread.add(thread);
						thread.start();
					}
				}
				for (ClientCrack client : listeThread) {
					client.join();
					if (client.getPassword() != null) {
						System.out.println(client.getPassword());
						break;
					}
				}

			}
		}
	}

}
