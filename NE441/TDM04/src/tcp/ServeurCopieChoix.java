package tcp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Serveur basique TCP
 */
public class ServeurCopieChoix
{

	private String fileServeur = "/home/lysio4/file_serveur.txt";
    public static void main(String[] args) throws Exception
    {
        ServeurCopieChoix serveurTCP = new ServeurCopieChoix();
        serveurTCP.execute();

    }



    private void execute() throws IOException
    {
        //
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
		OutputStream os = socketConnexion.getOutputStream();
        int lenBufR = is.read(bufR);
        if (lenBufR!=-1)
        {
            String message = new String(bufR, 0 , lenBufR);
            System.out.println("Message recu = "+message);
            InputStream source = new FileInputStream(message);
            File f = new File(message);
            Long fileSize = f.length();
            byte[] bufT = (fileSize+"\n").getBytes();
    		os.write(bufT);
    		System.out.println("Message envoye");
            //byte[] bufTaille = new String(fileSize.toString() + " octets").getBytes();
            //OutputStream osTaille = socketConnexion.getOutputStream();
            //osTaille.write(bufTaille);
           // System.out.println(fileSize.toString() + " octets");
    		byte[] bufE = new byte [2048];
    		int readByte = source.read(bufE);
    		//System.out.println("byte = "+ readByte.toString());
    		Long nbBytesSent = 0L;
    		Long percent = 0L;
    		while(readByte != -1)
    		{
    	        os.write(bufE, 0, readByte);
    	        readByte = source.read(bufE);
    	        nbBytesSent += lenBufR;
                percent = nbBytesSent * 100 / fileSize;
                System.out.println(percent + "%");
    		}
            source.close();
    		long stop = System.currentTimeMillis();
    		System.out.println("Elapsed Time = "+(stop-start)+" ms");
        }

        else
        {
        	byte[] bufE = new String("ok").getBytes();
            os.write(bufE);
            System.out.println("Message envoye = ok");
        }
		
        
        // Fermeture de la socket de connexion
        socketConnexion.close();


        // Arret du serveur 
        socketEcoute.close();
        System.out.println("Arret du serveur .");
    }

}
