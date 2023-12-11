package tcp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Client basique TCP
 * 
 */
public class ClientCopie
{

	private String fileClient = "/home/lysio4/file_client.txt";
    public static void main(String[] args) throws Exception
    {
        ClientCopie clientTCP = new ClientCopie();
        clientTCP.execute();                
    }

    /**
     * Le client cree une socket, envoie un message au serveur
     * et attend la reponse 
     * 
     */
    private void execute() throws IOException
    {
    	OutputStream destination = new FileOutputStream(fileClient);
        System.out.println("Demarrage du client ...");

        //Creation de la socket
        Socket socket = new Socket();

        // Connexion au serveur 
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 5099);
        socket.connect(adrDest);        

        // Envoi de la requete
        byte[] bufE = new String("question du client").getBytes();
        OutputStream os = socket.getOutputStream();
        os.write(bufE);
        System.out.println("Message envoye");

        // Attente de la reponse 
        byte[] bufR = new byte[2048];
        InputStream is = socket.getInputStream();
        int lenBufR = is.read(bufR);
        System.out.println("len = " + lenBufR);
        while (lenBufR != -1) {
            destination.write(bufR, 0, lenBufR);
            lenBufR = is.read(bufR);
        }
        
        destination.close();

        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client .");
    }
}
