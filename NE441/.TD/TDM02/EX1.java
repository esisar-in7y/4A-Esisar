

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
public class EX1
{
    public static void main(String[] args) throws Exception
    {
    	EX1 clientUDP = new EX1();
    	clientUDP.execute();

    }


    /**
     * Le client cree une socket, envoie un message au serveur
     * et attend la reponse 
     * 
     */
    private void execute() throws IOException{

        //Creation de la socket
        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(7050));
        JFrame frame = new JFrame("TeleJsion");
        frame.setSize(300,300);
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);
        while(true) {
	       	partie(socket, frame);
	       	System.out.println("Couleur changée");
       }
    }
    
    
    private void partie(DatagramSocket socket, JFrame frame) throws IOException
    {

        // Attente de la reponse 
        byte[] bufR = new byte[2048];
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        socket.receive(dpR);
        String reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
        reponse = reponse.replaceAll("\\n",""); // sanitize input
        System.out.println("Reponse: "+reponse);
          if(reponse.equals("red")) {
        	  frame.getContentPane().setBackground(Color.RED);
              frame.setVisible(true);
        } else if (reponse.equals("green")) {
        	frame.getContentPane().setBackground(Color.GREEN);
            frame.setVisible(true);
        } else {
        	frame.getContentPane().setBackground(Color.BLUE);
            frame.setVisible(true);
        }

        
        
    }

}
