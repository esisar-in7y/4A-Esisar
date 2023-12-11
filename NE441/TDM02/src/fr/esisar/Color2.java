package fr.esisar;

import java.awt.Color;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import javax.swing.JFrame;

public class Color2 {

	public static void main(String[] args) throws Exception
    {
		Color2 c = new Color2();
        c.execute();
    }

    private void execute() throws Exception
    {
    	 //
    	
    	JFrame frame = new JFrame("Color 2");
        frame.setSize(300,300);
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);
        
        System.out.println("Demarrage du serveur ...");

        @SuppressWarnings("resource")
		DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(4002));
        
        while(true)
        {
            byte[] bufR = new byte[2048];
            DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            String message = new String(bufR, dpR.getOffset(), dpR.getLength());
            System.out.println("Message recu sur serveur 2 = "+message);
            System.out.println("IP = "+dpR.getAddress());
            System.out.println("Port = "+dpR.getPort());

             
            if(message.contains("red"))
            {
            	frame.getContentPane().setBackground(Color.RED);
                frame.setVisible(true);
                InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 4001);
                byte[] bufE = new String("green").getBytes();
                DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
                socket.send(dpE); 
                System.out.println("Message envoyé");
                Thread.sleep(1000);
                frame.getContentPane().setBackground(Color.GREEN);
                frame.setVisible(true);
                Thread.sleep(1000);
            }
            
            else if(message.contains("green"))
            {
            	frame.getContentPane().setBackground(Color.GREEN);
                frame.setVisible(true);
                InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 4001);
                byte[] bufE = new String("red").getBytes();
                DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
                socket.send(dpE);
                System.out.println("Message envoye = ok");
                Thread.sleep(1000);
                frame.getContentPane().setBackground(Color.RED);
                frame.setVisible(true);
                Thread.sleep(1000);
            }
        }
    }
       
}
