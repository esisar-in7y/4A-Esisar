import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class EX52 {

	public static void main(String[] args) throws Exception{

		EX52 clientUDP = new EX52();
    	clientUDP.execute();

	}

	private void execute() throws IOException
    {

		// Ouverture des sockets
		DatagramSocket socket_snd = new DatagramSocket(null);
        DatagramSocket socket_rcp = new DatagramSocket();
        Integer a = socket_rcp.getLocalPort();
        
    	// Envoi du message JOUER
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 4000);
        byte[] bufE = new String("127.0.0.1:"+a).getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
        socket_snd.send(dpE);
        socket_snd.close();
        System.out.println("Signal d'arrivée envoyé");


        JFrame frame = new JFrame("Pong "+a);
    	frame.setSize(300,300);
    	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    	frame.addWindowListener(new WindowAdapter() {
    		@SuppressWarnings("unused")
			public void WindowClosing(WindowEvent e) {
    	        exitProcedure(frame);
    	    }
    	});
        
        
        byte[] bufR;
        DatagramPacket dpR;
        String reponse;
        while(true) {
		    // Attente de la reponse
        	bufR = new byte[2048];
		    dpR = new DatagramPacket(bufR, bufR.length);
		    socket_rcp.receive(dpR);
		    reponse = new String(bufR, dpR.getOffset(), dpR.getLength());
		    System.out.println("Réception du signal "+reponse);
		    switch(reponse) {
		    case "GREEN":
	            frame.getContentPane().setBackground(Color.GREEN);
	            break;
		    case "RED":
	            frame.getContentPane().setBackground(Color.RED);
	            validate();
	            break;
		    }
		    frame.setVisible(true);
        }
    }
	
	public void exitProcedure(JFrame frame) {
		System.out.println("EXIT !");
	    frame.dispose();
	    System.exit(0);
	}
	
        
    // Envoi du message OK
	public void validate() throws IOException {
		DatagramSocket socket_snd = new DatagramSocket(null);
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 4001);
        byte[] bufE = new String("OK").getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
        socket_snd.send(dpE);
        socket_snd.close();
        System.out.println("Validé");
	}
	
}
