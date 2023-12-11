package fr.esisar;

import java.awt.Color;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import javax.swing.JFrame;

public class Chenillard {

	public static void main(String[] args) throws Exception
    {
		Chenillard c = new Chenillard();
		switch(Integer.parseInt(args[0]))
		{
			case (1):
			c.execute1();
			break;
			case (2):
			c.execute2();
			break;
			case (3):
			c.execute3();
			break;
			case (4):
			c.execute4();
			break;
			default:
			System.out.println("Invalid");
			break;
		}
    }

    private void execute1() throws Exception
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
        
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 4002);
        byte[] bufE = new String("red").getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
        socket.send(dpE); 
        System.out.println("Message envoyé");
        
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);
        Thread.sleep(1000);
        
        while(true)
        {
            byte[] bufR = new byte[2048];
            DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            String message = new String(bufR, dpR.getOffset(), dpR.getLength());
            //System.out.println("Message recu sur serveur 1 = "+message);
            //System.out.println("Port = "+dpR.getPort());
            System.out.println(message.contains("red"));

             
            if(message.contains("red") && dpR.getPort() == 4004)
            {
            	frame.getContentPane().setBackground(Color.RED);
                frame.setVisible(true);
                Thread.sleep(1000);
                byte[] bufE2 = new String("red").getBytes();
                InetSocketAddress adrDest2 = new InetSocketAddress("127.0.0.1", 4002);
                DatagramPacket dpE2 = new DatagramPacket(bufE2, bufE2.length, adrDest2);
                socket.send(dpE2); 
                System.out.println("Message envoyé");
                frame.getContentPane().setBackground(Color.GREEN);
                frame.setVisible(true);
                Thread.sleep(1000);
            }
        }
    }
    
    private void execute2() throws Exception
    {

        System.out.println("Demarrage du serveur ...");

        @SuppressWarnings("resource")
		DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(4002));
        
        JFrame frame = new JFrame("Color 2");
        frame.setSize(300,300);
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);
        Thread.sleep(1000);
        
        while(true)
        {
            byte[] bufR = new byte[2048];
            DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            String message = new String(bufR, dpR.getOffset(), dpR.getLength());

             
            if(message.contains("red") && dpR.getPort() == 4001)
            {
            	frame.getContentPane().setBackground(Color.RED);
                frame.setVisible(true);
                Thread.sleep(1000);
                byte[] bufE2 = new String("red").getBytes();
                InetSocketAddress adrDest2 = new InetSocketAddress("127.0.0.1", 4003);
                DatagramPacket dpE2 = new DatagramPacket(bufE2, bufE2.length, adrDest2);
                socket.send(dpE2); 
                frame.getContentPane().setBackground(Color.GREEN);
                frame.setVisible(true);
                Thread.sleep(1000);
            }
        }
    }
    
    private void execute3() throws Exception
    {

        System.out.println("Demarrage du serveur ...");

        @SuppressWarnings("resource")
		DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(4003));
        
        JFrame frame = new JFrame("Color 3");
        frame.setSize(300,300);
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);
        Thread.sleep(1000);
        
        while(true)
        {
            byte[] bufR = new byte[2048];
            DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            String message = new String(bufR, dpR.getOffset(), dpR.getLength());

             
            if(message.contains("red") && dpR.getPort() == 4002)
            {
            	frame.getContentPane().setBackground(Color.RED);
                frame.setVisible(true);
                Thread.sleep(1000);
                byte[] bufE2 = new String("red").getBytes();
                InetSocketAddress adrDest2 = new InetSocketAddress("127.0.0.1", 4004);
                DatagramPacket dpE2 = new DatagramPacket(bufE2, bufE2.length, adrDest2);
                socket.send(dpE2); 
                frame.getContentPane().setBackground(Color.GREEN);
                frame.setVisible(true);
                Thread.sleep(1000);
            }
        }
    }
    
    private void execute4() throws Exception
    {

        System.out.println("Demarrage du serveur ...");

        @SuppressWarnings("resource")
		DatagramSocket socket = new DatagramSocket(null);
        socket.bind(new InetSocketAddress(4004));
        
        JFrame frame = new JFrame("Color 4");
        frame.setSize(300,300);
        frame.getContentPane().setBackground(Color.GREEN);
        frame.setVisible(true);
        Thread.sleep(1000);
        
        while(true)
        {
            byte[] bufR = new byte[2048];
            DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
            socket.receive(dpR);
            String message = new String(bufR, dpR.getOffset(), dpR.getLength());

             
            if(message.contains("red") && dpR.getPort() == 4003)
            {
            	frame.getContentPane().setBackground(Color.RED);
                frame.setVisible(true);
                Thread.sleep(1000);
                byte[] bufE2 = new String("red").getBytes();
                InetSocketAddress adrDest2 = new InetSocketAddress("127.0.0.1", 4001);
                DatagramPacket dpE2 = new DatagramPacket(bufE2, bufE2.length, adrDest2);
                socket.send(dpE2); 
                frame.getContentPane().setBackground(Color.GREEN);
                frame.setVisible(true);
                Thread.sleep(1000);
            }
        }
    }
       
}
