package exo;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Soldat extends Thread {

	private String pass;
	private int pas;
	private long start;
	private long N;
	private MessageDigest digest;
	private String hashFinal;
	private ServeurUDP serveur;
	String iPServeur;
	int portServeur;
	
	public Soldat(long debut,long N, int totalThread, int idThread, ServeurUDP serveur, String iPServeur,int portServeur, String hash) throws NoSuchAlgorithmException {
		pas = totalThread;
		start = idThread+debut;
		this.N = N;
		this.serveur = serveur;
		
		this.iPServeur = iPServeur;
		this.portServeur = portServeur;
		
		this.hashFinal = hash;
		
		this.digest = MessageDigest.getInstance("MD5");
		
		System.out.println("Demarrage T"+idThread);
	}
	
	public void run() {
		
		for (long i=start; i<N; i+=pas) {
			String hashTestStr = String.format("p2024esisar%09d", i);
			byte[] hashTest = digest.digest(hashTestStr.getBytes());
			
			StringBuilder sb = new StringBuilder(); 
			for (byte b : hashTest){
				sb.append(String.format("%02X",b));
			}
			if (hashFinal.equalsIgnoreCase(sb.toString())){
				System.out.println("W");
				try {
					serveur.send(hashTestStr,iPServeur , portServeur);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	
}
