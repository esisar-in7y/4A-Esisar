package tcp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Serveur basique TCP
 */
public class ServeurCopie
{

	private String fileServeur = "/home/lysio4/file_serveur.txt";
    public static void main(String[] args) throws Exception
    {
        ServeurCopie serveurTCP = new ServeurCopie();
        serveurTCP.execute();

    }



    private void execute() throws IOException
    {
        //
		InputStream source = new FileInputStream(fileServeur);
        System.out.println("Demarrage du serveur ...");

        // Le serveur se declare aupres de la couche transport
        // sur le port 5099
        ServerSocket socketEcoute = new ServerSocket();
        socketEcoute.bind(new InetSocketAddress(5099));


        // Attente de la connexion d'un client
        System.out.println("Attente de la connexion du client ...");
        Socket socketConnexion = socketEcoute.accept();

        // Affichage du port et de l'ip du client 
        System.out.println("Un client est connecté");
        System.out.println("IP:"+socketConnexion.getInetAddress());
        System.out.println("Port:"+socketConnexion.getPort());


        // Un client s'est connecte, le serveur lit le message envoye par le client (sans garantie de lire tout le message)
        long start = System.currentTimeMillis();
        byte[] bufR = new byte[2048];
        InputStream is = socketConnexion.getInputStream();
        int lenBufR = is.read(bufR);
        if (lenBufR!=-1)
        {
            String message = new String(bufR, 0 , lenBufR);
            System.out.println("Message recu = "+message);
        }


        // Emission d'un message en retour
		 byte[] bufE = new byte[2048];
	     int readByte = source.read(bufE);
	     OutputStream os = socketConnexion.getOutputStream();
	     while (readByte != -1) {
	         os.write(bufE, 0, readByte);
	         readByte = source.read(bufE);
	     }
        source.close();
		long stop = System.currentTimeMillis();
		System.out.println("Elapsed Time = "+(stop-start)+" ms");
        
        // Fermeture de la socket de connexion
        socketConnexion.close();


        // Arret du serveur 
        socketEcoute.close();
        System.out.println("Arret du serveur .");
    }

}
