package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Serveur basique TCP
 */
public class ServeurCompter extends Thread
{
	
	Compteur compteur;
	int port;

    public static void main(String[] args) throws Exception
    {
    	Compteur c = new Compteur();
    	List<ServeurCompter> threads = new ArrayList<>();
        for (int i = 21000; i <= 23000; i++) {
        	ServeurCompter si = new ServeurCompter(i, c);
            threads.add(si);
        }
        for (ServeurCompter thread : threads) {
            thread.start();
        }
    }

    public ServeurCompter(int port, Compteur compteur) {
		super();
		this.port = port;
		this.compteur = compteur;
	}

	

	/*private String readMessage(InputStream is) throws IOException 
	{
		String res = "";
		
		String s = getOneChar(is);
		while(s.equals("?")==false)
		{
			res = res+s;
			s = getOneChar(is);
		}
		return res;
	}

	private String getOneChar(InputStream is) throws IOException 
	{
		byte[] buf = new byte[1];
		is.read(buf);
		return new String(buf);
	}*/

	public void run()
    {

        try {
			ServerSocket socketEcoute = new ServerSocket();
			socketEcoute.bind(new InetSocketAddress(port));
			String message;
			
			while(true)
			{
				// Attente de la connexion d'un client
				Socket socketConnexion = socketEcoute.accept();

				// Affichage du port et de l'ip du client 
				System.out.println("Un client est connecté");
				System.out.println("IP:"+socketConnexion.getInetAddress());
				System.out.println("Port:"+socketConnexion.getPort());
				
				message =receiveMsg(socketConnexion);
				while((!(message.trim().equals("NUMERO?"))) && message.trim().length()<7) {
					message+=receiveMsg(socketConnexion).trim();
				}
				if (message.trim().equals("NUMERO?")) {
					// Emission d'un message en retour
			    	String answer = compteur.getNumero().toString();
			        byte[] bufE = new String("NUMERO="+answer).getBytes();
			        OutputStream os = socketConnexion.getOutputStream();
			        os.write(bufE);
			        System.out.println("Message envoye = " + answer);
				} 
				 else {
				        byte[] bufE = new String("VOUS AVEZ FAIT UNE ERREUR.").getBytes();
				        OutputStream os = socketConnexion.getOutputStream();
				        os.write(bufE);
				        System.out.println("Message envoye = ok");
				    	
				   }

	      

				// Fermeture de la socket de connexion
				socketConnexion.close();
			}


			// Arret du serveur 
			//socketEcoute.close();
			//System.out.println("Arret du serveur .");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	private String receiveMsg(Socket socket) throws IOException {
		byte[] bufR = new byte[2048];
		InputStream is = socket.getInputStream();
		int lenBufR;
		lenBufR = is.read(bufR);
		if (lenBufR != -1) {
			return new String(bufR, 0, lenBufR);
		} else {
		return null;
		}
	}

}