import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EX51 {
	
	List<Client5> clients = Collections.synchronizedList(new ArrayList<Client5>());

	public static void main(String[] args) throws IOException, InterruptedException {

		EX51 clientUDP = new EX51();
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
        DatagramSocket socket_rcp = new DatagramSocket(null);
        socket_rcp.bind(new InetSocketAddress(4000));
        System.out.println("Socket Prête");
        
        Thread t1 = new Thread() {
        	public void run() {
		        while(true) {
			       	try {
						wait_client(socket_rcp);
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
		        }
        	}
        };
        Thread t2 = new Thread() {
        	public void run() {
        		while(true) {
			       	try {
			       		System.out.println("Chenillard loop");
						chenillard();
						if(clients.size()<2) Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
		        }
        	}
        };
              
        t1.start();
        t2.start();
    }

    
    private void wait_client(DatagramSocket socket_rcp) throws IOException
    {

        String message;
        // Attente de l'arrivée d'un client
        byte[] bufR = new byte[2048];
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        socket_rcp.receive(dpR);
        message = new String(bufR, dpR.getOffset(), dpR.getLength());
        System.out.println("Nouveau Client: "+message);
        //Traitement coordonées
        String[] coord = message.split(":");
        Client5 newcommer = new Client5();
        newcommer.setIp(coord[0]);
        newcommer.setPort(coord[1]);
        clients.add(newcommer);
        newcommer.sendVert();
        
        
    }
    /*
    private void remove_client(String ip, Integer port) {
    	for(int i=0; i<clients.size(); i++) {
    		if(clients[i].getIp().equals(ip))
    			if(clients[i].getPort()==port) {
    				clients.remove(i);
    				return;
    			}
    	}
    }
    */



	private void chenillard() throws IOException, InterruptedException {
		for (Client5 client : clients) {
			client.sendRouge();
			if( ! client.waitOK()) {
				System.out.println("client mort");
				clients.remove(client);
				break;
			}
			Thread.sleep(1000);
			client.sendVert();
		}
	}
	
}
