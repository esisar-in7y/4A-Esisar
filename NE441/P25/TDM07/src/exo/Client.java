package exo;

import java.io.IOException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


public class Client {

	private ServeurUDP serveur;
	private String iPServeur;
	private int portEcoute;
	private String hash;
	
	private long inf;
	private long sup;
	
	private int totalThread;
	
	public Client(String iPServeur,int portEcoute,long inf, long sup, int totalThread) throws UnknownHostException, IOException, NoSuchAlgorithmException {
		serveur = new ServeurUDP(3000);
		
		this.iPServeur = iPServeur;
		this.portEcoute = portEcoute;
		this.inf = inf;
		this.sup = sup;
		this.totalThread = totalThread;
		
		authentification();
		hash = receptionHash();
		System.out.println("Hash : " + hash);
		bruteForce();
	}
	
	public void authentification() throws IOException {
		System.out.println("Authentification ..");
		serveur.send("register", iPServeur, portEcoute);
	}
	
	public String receptionHash() throws IOException {
		serveur.receive();
		return serveur.getMsg();
	}
	
	public void bruteForce() throws NoSuchAlgorithmException {
		System.out.println("BruteForce ..");
		List<Soldat> threads = new ArrayList<Soldat>();
		for (int i=0; i<totalThread; i++) {
			Soldat s = new Soldat(inf,sup,totalThread,i,serveur,iPServeur,portEcoute,hash);
			threads.add(s);
			s.start();
		}
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException, NoSuchAlgorithmException {
		Client c = new Client("192.168.109.200",3000,0L,200_000_000L,8);
	}
	
	
}
