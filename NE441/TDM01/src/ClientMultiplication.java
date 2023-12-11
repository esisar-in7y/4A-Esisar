import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * Client basique UDP
 * 
 */
public class ClientMultiplication
{

    public static void main(String[] args) throws Exception
    {
        ClientMultiplication clientUDP = new ClientMultiplication();
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
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 11000);
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
            String reponse1 = new String(bufR, dpR.getOffset(), dpR.getLength());
            String reponse2;
            if (!reponse1.equals("GAGNE"))
            {
            	socket.receive(dpR);
                reponse2 = new String(bufR, dpR.getOffset(), dpR.getLength());
            }
            else
            {
            	reponse2 = reponse1;
            }
            
            if (reponse1.equals("GAGNE"))
            {
            	socket.send(dpE);
                compteur++;
            }
            
            else
            {
            	int op1 = Integer.parseInt(reponse1.substring(0,1));
            	int op2 = Integer.parseInt(reponse2.substring(0,1));
            	Integer answer = op1 * op2;
            	byte[] ping = new String(answer.toString() + ";").getBytes();
                DatagramPacket pingDpE = new DatagramPacket(ping, ping.length, adrDest);
                socket.send(pingDpE);
                System.out.println("Message envoyé : " + answer.toString() + ";");
            }
        }
     // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client .");
    }

}