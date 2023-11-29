package examen_exo_2;

import java.io.IOException;

import tcp.ClientTCP;

public class ComplexTCPClient {

	public static void main(String[] args) {
		ComplexTCPClient ex1 = new ComplexTCPClient();
		try {
			ex1.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void execute() throws IOException {
		/* 
		 * 1) connect
		 * 2) rcv_sanitized
		 * 		get-bytes
		 * 		replace no ascii by spaces
		 * 3) loop pass states
		 * 4) 	if beginning send / add to buf
		 * 5)	if num read number
		 * 6)	if garbage discard
		 * 7)	if end send /  add to buf
		 * 8) print mdp
		 * 9) reset globals (except r (reception buffer))
		 * */
		ClientTCP c = new ClientTCP("127.0.0.1",7500);
		System.out.println("Début de la connexion au serveur 1");
		
		// Globals
			String r="";
			String s="";
			String mdp="";
			String[] list;
			boolean reload=true;
			Long X = 0l;
		//States
			int a=0; // 0 = beginning, 1 = num, 2 = garbage, 3 = end
		
		// Main loop
		for(int i=1; i<=16; i++) {
			a=0;
			mdp="";
			//While password isn't completely proceced
			while(a!=4) {
				
				if(reload) {
					r = c.rcv_sanitized_string();
					reload = false;
					//System.out.println("new r: " + r);
				}
				//System.out.println("curr s: " + r);
				
				if(a==2) {
					if(r.length() > X) {
						r = r.substring(X.intValue());
						X=0l;
						a=3;
					} else {
						reload = true;
						X -= r.length();
						while(X > c.discardSize) {
							X -= c.discard();
							//System.out.println("X: " + X);
						}
					}
				} else {
					if(r.contains("*")) {
						list = r.split("\\*",2);
						s += list[0];
						if(list.length == 1) { // r est vide
							reload = true;
						}
						else r = list[1];
						switch (a) {
						case 0:
							//System.out.println("debut mdp: " + s);
							c.send(s);
							mdp += s;
							break;
						case 1:
							X=Long.parseLong(s);
							//System.out.println("X = " + X);
							break;
						case 3:
							c.send(s+";");
							mdp += s;
							break;
						}
						s="";
						a++;
					} else { // pas d'étoile, il faut reload (incomplete state)
						s += r;
						reload = true;
					}
				}
			}
			
			System.out.println("Mot de passe " + i +" : " + mdp);	

		}
		System.out.println("Fin du programme");		
		c.close();
	}

}
