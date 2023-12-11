package fr.esisar;

import java.awt.Color;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import javax.swing.JFrame;

public class Color1 {

	public static void main(String[] args) throws Exception
    {
		Color1 c = new Color1();
        c.execute();
    }

    private void execute() throws Exception
    {

        System.out.println("Demarrage du serveur ...");

        @SuppressWarnings("resource")
		DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(4001));
        
        JFrame frame = new JFrame("Color 1");
        frame.setSize(300,300);
        frame.getContentPane().setBackground(Color.RED);
        frame.setVisible(true);
        Thread.sleep(1000);
        
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);
        
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 4002);
        byte[] bufE = new String("red").getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
        socket.send(dpE); 
        System.out.println("Message envoyé");
        
        while(true)
        {
            byte[] bufR = new byte[2048];
            DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            String message = new String(bufR, dpR.getOffset(), dpR.getLength());
            System.out.println("Message recu sur serveur 1 = "+message);
            System.out.println("IP = "+dpR.getAddress());
            System.out.println("Port = "+dpR.getPort());
            System.out.println(message.contains("red"));
            System.out.println(message.contains("green"));

             
            if(message.contains("red"))
            {
            	frame.getContentPane().setBackground(Color.RED);
                frame.setVisible(true);
                byte[] bufE2 = new String("green").getBytes();
                DatagramPacket dpE2 = new DatagramPacket(bufE2, bufE2.length, 
                        dpR.getAddress(),dpR.getPort());
                socket.send(dpE2);
                System.out.println("Message envoye = ok");
                Thread.sleep(1000);
                frame.getContentPane().setBackground(Color.GREEN);
                frame.setVisible(true);
                Thread.sleep(1000);
            }
            
            else if(message.contains("green"))
            {
            	frame.getContentPane().setBackground(Color.GREEN);
                frame.setVisible(true);
                byte[] bufE2 = new String("red").getBytes();
                DatagramPacket dpE2 = new DatagramPacket(bufE2, bufE2.length, 
                        dpR.getAddress(),dpR.getPort());
                socket.send(dpE2);
                System.out.println("Message envoye = ok");
                Thread.sleep(1000);
                frame.getContentPane().setBackground(Color.RED);
                frame.setVisible(true);
                Thread.sleep(1000);
            }
        }
    }
       
}
