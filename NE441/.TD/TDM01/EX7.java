

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * Client basique UDP
 * 
 */
public class EX7
{

    public static void main(String[] args) throws Exception
    {
    	EX7 clientUDP = new EX7();
        for(int i=1; i<=10; i++) {
        	 System.out.println("===================================\n"
        	+ "Début de la partie "+i);
        	clientUDP.execute();
        	System.out.println("Fin de la partie "+i);
        }

    }


    /**
     * Le client cree une socket, envoie un message au serveur
     * et attend la reponse 
     * 
     */
    private void execute() throws IOException
    {
        //Creation de la socket
        DatagramSocket socket = new DatagramSocket();

        // Envoi du message JOUER
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 11000);
        byte[] bufE = new String("JOUER").getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
        socket.send(dpE);
        System.out.println("Envoi d'un paquet UDP avec JOUER");

        // Attente de la reponse 
        byte[] bufR = new byte[2048];
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        socket.receive(dpR);
        int a = bufR[dpR.getOffset()] - 48; // Convertion en int
        
        // Attente de la reponse 
        bufR = new byte[2048];
        dpR = new DatagramPacket(bufR, bufR.length);
        socket.receive(dpR);
        int b = bufR[dpR.getOffset()] - 48; // Convertion en int
        System.out.println("Le serveur a répondu "+a+" et "+b);
                
        // Envoi de la reponse
        bufE = (a*b + ";").getBytes();
        dpE = new DatagramPacket(bufE, bufE.length, adrDest);
        socket.send(dpE);
        System.out.println("Envoi d'un paquet UDP avec "+a*b);

        // Attente de la reponse 
        bufR = new byte[2048];
        dpR = new DatagramPacket(bufR, bufR.length);
        socket.receive(dpR);
        String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
        System.out.println("Le serveur a répondu :"+reponse);

        
        
        
        // Fermeture de la socket
        socket.close();
    }

}
