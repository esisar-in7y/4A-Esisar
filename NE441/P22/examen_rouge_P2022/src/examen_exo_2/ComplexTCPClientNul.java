package examen_exo_2;

import java.io.IOException;

import tcp.ClientTCP;
import tcp.Tableau;

public class ComplexTCPClientNul {

	public ComplexTCPClientNul() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ComplexTCPClientNul ex1 = new ComplexTCPClientNul();
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
		Tableau t;
		//States
			int a=0; // 0 = beginning, 1 = num, 2 = garbage, 3 = end
			int XbS=0; //
		// Pointers
			int j;
			int b;
			String s;
			int X = 0;
			byte[] Xb = new byte[10];
		//for(int i=1; i<=16; i++) {
			
			t = c.rcv();

			j=0;
			while(j<t.len) {
				System.out.println("Boucle");
				if(a==0 && j<t.len) {
					System.out.println("beginning");
					b=j;
					while(j<=t.len && t.buf[j]=='*')
						j++;
					c.send(t, b, Math.min(j,t.len-b));
					if(j<=t.len-b) a++;
					j++;
				}
				if(a==1 && j<t.len) {
					System.out.println("num");
					b=j;
					System.arraycopy(t.buf, j, Xb, XbS, Math.min(10-XbS,t.len-b));
					XbS=Math.min(10-XbS,t.len-b);
					s = new String(Xb);
					if(s.contains("\\*")) {
						XbS=0;
						Xb = new byte[10];
						s = s.split("\\*")[0];
						X = Integer.parseInt(s);
						j += s.length();
						j++;
						a++;
					} else j=t.len;
				}
				if(a==2 && j<t.len) {
					System.out.println("garbage");
					j += X;
					X -= Math.min(X,t.len-j);
					if(X==0) a++;
				}
				if(a==3 && j<t.len) {
					System.out.println("end");
					b=j;
					while(j<=t.len && t.buf[j]=='*')
						j++;
					c.send(t, b, Math.min(j,t.len-b));
					if(j<=t.len-b) a=0;
					j++;
				}
				
			}
			
			
			
			/* for(int j = 0; j<=t.len; j++) {
				
				if(a==2) {
					
				}
				else {
				
					if(t.buf[j]=='*') {
						a=a+1;
						if(a==3) a=0;
					}
				}
			}*/
			
			
			
		//}
		System.out.println("Fin du programme");		
		c.close();
	}

}
