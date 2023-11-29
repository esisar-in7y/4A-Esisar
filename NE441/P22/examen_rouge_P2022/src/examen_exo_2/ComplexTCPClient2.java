package examen_exo_2;

import java.io.IOException;

import tcp.ClientTCP;

public class ComplexTCPClient2 {

	public ComplexTCPClient2() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ComplexTCPClient2 ex1 = new ComplexTCPClient2();
		try {
			ex1.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void execute() throws IOException {
		/* 
		 * 1) connect
		 * 2) get bytes
		 * 3) loop through bytes
		 * 4) 	if mdp add to buf
		 * 5)	if start read number
		 * 6)	if oct discard
		 * 7)	if end mdp add to buf
		 * 8) send buf
		 * */
		ClientTCP c = new ClientTCP("127.0.0.1",7500);
		System.out.println("Début de la connexion au serveur 1");
		// c.connect("127.0.0.1",7000);
		String r="";
		String s="";
		String[] list;
		boolean reload=true;
		int X = 0;
		//States
			int a=0; // 0 = beginning, 1 = num, 2 = garbage, 3 = end
		for(int i=1; i<=16; i++) {
			a=0;
			while(a!=4) {
				
				if(reload) {
					r = c.rcv_sanitized_string();
					reload = false;
				}
				//System.out.println("curr s: " + r);
				
				switch (a) {
				case 0:
					if(r.contains("*")) {
						list = r.split("\\*",2);
						s += list[0];
						if(list.length == 1) {
							reload = true;
						}
						else r = list[1];
						System.out.println("debut mdp: " + s);
						c.send(s);
						s="";
						a=1;
					} else {
						s += r;
						reload = true;
					}
					break;
				case 1:
					if(r.contains("*")) {
						list = r.split("\\*",2);
						s += list[0];
						if(list.length == 1) {
							reload = true;
						}
						else r = list[1];
						X=Integer.parseInt(s);
						System.out.println("X = " + X);
						s="";
						a=2;
					} else {
						s += r;
						reload = true;
					}
					break;
				case 2:
					if(r.length() > X) {
						r = r.substring(X);
						X=0;
						a=3;
					} else {
						reload = true;
						X -= r.length();
					}
					break;
				case 3:
					if(r.contains("*")) {
						list = r.split("\\*",2);
						s += list[0];
						if(list.length == 1) {
							reload = true;
						}
						else r = list[1];
						System.out.println("fin mdp: " + s);
						c.send(s+";");
						s="";
						a=4;
					} else {
						s += r;
						reload = true;
					}
					break;
				}
			}

			
		}
		System.out.println("Fin du programme");		
		c.close();
	}

}
