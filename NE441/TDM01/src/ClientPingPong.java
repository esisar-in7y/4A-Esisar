import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * Client basique UDP
 * 
 */
public class ClientPingPong
{

    public static void main(String[] args) throws Exception
    {
        ClientPingPong clientUDP = new ClientPingPong();
        clientUDP.execute();

    }


    /**
     * Le client cree une socket, envoie un message au serveur
     * et attend la reponse 
     * 
     */
    private void execute() throws IOException
    {
        //
        System.out.println("Demarrage du client ...");

        int compteur = 0;
        //Creation de la socket
        DatagramSocket socket = new DatagramSocket();

        // Creation et envoi du message
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 29000);
        byte[] bufE = new String("JOUER").getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
        socket.send(dpE);
        System.out.println("Message envoyé");

        while(compteur <= 10)
        {
        	// Attente de la reponse 
            byte[] bufR = new byte[2048];
            DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
            System.out.println("Reponse recue = "+reponse);
            
            if (reponse.equals("PONG"))
            {
            	byte[] ping = new String("PING").getBytes();
                DatagramPacket pingDpE = new DatagramPacket(ping, ping.length, adrDest);
                socket.send(pingDpE);
                System.out.println("Message envoyé");
            }
            
            else if (reponse.equals("PING"))
            {
            	byte[] pong = new String("PONG").getBytes();
                DatagramPacket pongDpE = new DatagramPacket(pong, pong.length, adrDest);
                socket.send(pongDpE);
                System.out.println("Message envoyé");
            }
            
            else if (reponse.equals("GAGNE"))
            {
                socket.send(dpE);
                System.out.println("Message envoyé");
                compteur++;
            }

            
        }
     // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client .");
    }

}