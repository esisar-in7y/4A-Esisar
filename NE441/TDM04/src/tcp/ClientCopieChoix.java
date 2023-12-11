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
public class ClientCopieChoix
{

	private String fileClient = "/home/lysio4/file_client.txt";
    public static void main(String[] args) throws Exception //file path is an argument
    {
        ClientCopieChoix clientTCP = new ClientCopieChoix();
        clientTCP.execute(args[0]);                
    }

    /**
     * Le client cree une socket, envoie un message au serveur
     * et attend la reponse 
     * 
     */
    private void execute(String filepath) throws IOException
    {
    	OutputStream destination = new FileOutputStream(fileClient);
        System.out.println("Demarrage du client ...");

        //Creation de la socket
        Socket socket = new Socket();

        // Connexion au serveur 
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 5099);
        socket.connect(adrDest);        

        // Envoi de la requete
        byte[] bufE = new String(filepath).getBytes();
        OutputStream os = socket.getOutputStream();
        os.write(bufE);
        System.out.println("Message envoye");
        
        String fileLength="";
		do {
			byte[] bufR = new byte[2048];
			InputStream is = socket.getInputStream();
			int lenBufR;
			lenBufR = is.read(bufR);
			if (lenBufR != -1) {
				String received = new String(bufR, 0, lenBufR);
				fileLength = fileLength+received;
			} 
		} while (!fileLength.contains("\n"));
		
		fileLength=fileLength.substring(0, fileLength.length()-1);
		

        // Attente de la reponse 
		Long taille = Long.parseLong(fileLength); 
		Long nbBytesReceived = 0L;
		Long percent = 0L;
        byte[] bufR = new byte[2048];
        InputStream is = socket.getInputStream();
        int lenBufR = is.read(bufR);
        System.out.println("len = " + lenBufR);
        while (lenBufR != -1) {
            String reponse = new String(bufR, 0, lenBufR);
            System.out.println("Reponse recue = " + reponse);
            destination.write(bufR, 0, lenBufR);
            nbBytesReceived += lenBufR;
            percent = nbBytesReceived * 100 / taille;
            System.out.println(percent + "%");
            lenBufR = is.read(bufR);
        }
        destination.close();

        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client .");
    }
}
