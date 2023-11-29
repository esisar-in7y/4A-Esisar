package exo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class ServeurUDP{

	private String msg;
	private int port;
	private DatagramSocket socket;
	
	public ServeurUDP (int port) throws SocketException {
		this.port = port;
		this.socket = new DatagramSocket(null);
		//socket.bind(new InetSocketAddress(this.port));
	}
	

	public boolean receive() {
        byte[] bufR = new byte[2048];
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        
        try {
			this.socket.receive(dpR);
		} catch (IOException e) {
			socket.close();
			e.printStackTrace();
			return false;
		}
        
        this.msg = new String(bufR, dpR.getOffset(), dpR.getLength());
        return true;
	}
	

	public void send(String msg, String IPDest, int portDest) throws IOException {
		InetSocketAddress adrDest = new InetSocketAddress(IPDest, portDest);
        byte[] bufE = msg.getBytes();
        DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, adrDest);
		socket.send(dpE);
		
	}
	
	public void changePort(int portDest) throws SocketException {
		socket.close();
		this.port = portDest;
		socket.bind(new InetSocketAddress(this.port));
	}
	
	public void kill() {
		socket.close();
	}
	
	public String getMsg() {
		return msg;
	}
	
}
