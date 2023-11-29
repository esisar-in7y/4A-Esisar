package examen_exo1_rouge;

import java.io.IOException;

import tcp.ClientTCP;

public class SimpleTCPClient {
	
	public static void main(String[] args) {
		SimpleTCPClient ex1 = new SimpleTCPClient();
		try {
			ex1.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void execute() throws IOException {
		/* 
		 * 1) connect
		 * 2) receive string
		 * 3) remove stars
		 * 4) send string
		 * */
		ClientTCP c = new ClientTCP("127.0.0.1",7000);
		System.out.println("Début de la connexion au serveur 1");
		// c.connect("127.0.0.1",7000);
		
		String reponse = "";
		for(int i=1; i<=10; i++) {
			// On match "mot**mot*pleindetrucoklm..."
			while ( ! reponse.matches("[a-z]+\\*\\*[a-z]+\\*.*") ) {
				reponse += c.rcv_string();
			}
			reponse = reponse.replaceFirst("\\*\\*", ""); // On efface les deux premiers '*'
			String[] list = reponse.split("\\*", 2); // On coupe en deux
			System.out.println("Mot de passe " + i + " : " + list[0]);
			c.send(list[0]+";");
			if(list.length == 1) // simple précaution
				reponse="";
			else
				reponse = list[1];
		}
		System.out.println("Fin du programme");		
		c.close();
	}

}
