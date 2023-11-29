package src;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;

/**
 * Client basique TCP
 * 
 */
public class ClientTCP extends Thread 
{
	public int max=0;
	public int pmax=0;
	public int somme=0;
	int port_debut;
	int port_fin;

	public ClientTCP(int port_debut, int port_fin) {
		super();
		this.port_debut = port_debut;
		this.port_fin = port_fin;
	}
	
    public ClientTCP() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
     * Le client cree une socket, envoie un message au serveur
     * et attend la reponse 
     * 
     */
    private int getprice(int port) throws IOException
    {
        //
        System.out.println("Demarrage du client ...");

        //Creation de la socket
        Socket socket = new Socket();

        // Connexion au serveur 
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", port);
        socket.connect(adrDest);        

        // Envoi de la requete
        byte[] bufE = new String("COMBIEN").getBytes();
        OutputStream os = socket.getOutputStream();
        os.write(bufE);
        System.out.println("Message envoye");

        // Attente de la reponse 
        byte[] bufR = new byte[2048];
        InputStream is = socket.getInputStream();
        String reponse = "";
        while (! reponse.endsWith("EURO"))
        {
        	int lenBufR = is.read(bufR);
            reponse += new String(bufR, 0 , lenBufR ); Arrays.fill( bufR, (byte) 0 );
            System.out.println("Reponse recue = "+reponse);
        }

        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client .");
        
        return Integer.parseInt(reponse.replaceAll("[A-Z]|=", ""));
    }
    
    public void run()
    {
    	
	        try {
	        	for (int i=port_debut; i<port_fin; i++){
	        		int res =  getprice(i);
	        		if(res>max) {
	        			max = res;
	        			pmax = i;
	        		}
	        		somme += res;
	        	}
			} catch (IOException e) {
				System.out.println("ERREUR DE PRIXX");
				e.printStackTrace();
			}  
    }
}
