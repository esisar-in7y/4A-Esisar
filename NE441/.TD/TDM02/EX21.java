

import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import javax.swing.JFrame;

/**
 * Client basique UDP
 * 
 */
public class EX21
{
    public static void main(String[] args) throws Exception
    {
    	EX21 clientUDP = new EX21();
    	clientUDP.execute();

    }


    /**
     * Le client cree une socket, envoie un message au serveur
     * et attend la reponse 
     * @throws InterruptedException 
     * 
     */
    private void execute() throws IOException, InterruptedException{

        //Creation de la socket
        DatagramSocket socket_snd = new DatagramSocket(null);
        DatagramSocket socket_rcp = new DatagramSocket(null);
        socket_rcp.bind(new InetSocketAddress(4001));
        JFrame frame = new JFrame("Ping");
    	frame.setSize(300,300);        
        while(true) {
        	frame.getContentPane().setBackground(Color.RED);
        	frame.setVisible(true);
        	Thread.sleep(1000);
            frame.getContentPane().setBackground(Color.GREEN);
            frame.setVisible(true);
	       	wait_sok(socket_snd, socket_rcp, frame);
       }
    }
    
    
    private void wait_sok(DatagramSocket socket_snd, DatagramSocket socket_rcp, JFrame frame) throws IOException
    {

    	// Envoi du message JOUER
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 4002);
        byte[] bufE = new String("ROUGE").getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
        socket_snd.send(dpE);
        System.out.println("Envoi du signal ROUGE");

        String reponse = "";
        while(!(reponse.equals("ROUGE"))) {
	        // Attente de la reponse 
	        byte[] bufR = new byte[2048];
	        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
	        socket_rcp.receive(dpR);
	        reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
	        System.out.println("Réception du signal "+reponse);
        }
        
        
    }

}
