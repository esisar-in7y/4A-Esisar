package fr.esisar;

import java.awt.Color;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import javax.swing.JFrame;

public class Television {

	public static void main(String[] args) throws Exception
    {
		Television c = new Television();
        c.execute();
    }

    private void execute() throws Exception
    {
    	 //
    	
    	JFrame frame = new JFrame("Television");
        frame.setSize(300,300);
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);
        
        System.out.println("Demarrage du serveur ...");

        DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(7050));
        boolean closed = false;
        
        while(!closed)
        {
        	 // Attente du premier message
            byte[] bufR = new byte[2048];
            DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            String message = new String(bufR, dpR.getOffset(), dpR.getLength());
            System.out.println("Message recu = "+message);
            System.out.println("IP = "+dpR.getAddress());
            System.out.println("Port = "+dpR.getPort());

             
            if(message.contains("red"))
            {
            	frame.getContentPane().setBackground(Color.RED);
                frame.setVisible(true);
            }
            
            else if(message.contains("green"))
            {
            	frame.getContentPane().setBackground(Color.GREEN);
                frame.setVisible(true);
            }
            else if(message.contains("logout"))
            {
            	closed = true;
            }
        }
        frame.dispose();
        socket.close();
        System.out.println("Arret du serveur.");
    }
       
}
