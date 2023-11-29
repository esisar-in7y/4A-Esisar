import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;

public class Client5 {
	
	private String ip;
	private Integer port;
	
	
	
	public void sendRouge() throws IOException{
		sendMessage("RED");
	}
	public void sendVert() throws IOException{
		sendMessage("GREEN");
	}
	
	public void sendMessage(String Color) throws IOException {
        DatagramSocket socket_snd = new DatagramSocket(null);
		InetSocketAddress adrDest = new InetSocketAddress(ip, port);
        byte[] bufE = new String(Color).getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
        socket_snd.send(dpE);
        System.out.println("Envoi du signal "+Color+" à "+ip+":"+port);
        socket_snd.close();
	}
	
	
	public boolean waitOK() throws IOException {
		return waitMessage("OK");
	}
	
	public boolean waitMessage(String waitingMess) throws IOException {
        DatagramSocket socket_rcv = new DatagramSocket(null);
        socket_rcv.bind(new InetSocketAddress(4001));
        byte[] bufR = new byte[2048];
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        socket_rcv.setSoTimeout(2000);
        try {
        	socket_rcv.receive(dpR);
        }
        catch (SocketTimeoutException e) {
            socket_rcv.close();
        	return false;
        }
        String message = new String(bufR, dpR.getOffset(), dpR.getLength());
        System.out.println("Client reçu "+message);
        socket_rcv.close();
		return waitingMess.equals(message);
	}
	
	//Getters et Setters
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public void setPort(String port) {
		this.port = Integer.valueOf(port);
	}

}
