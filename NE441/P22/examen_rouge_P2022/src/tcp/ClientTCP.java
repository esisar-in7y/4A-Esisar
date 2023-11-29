package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientTCP {

	Socket socket;
	OutputStream os;
	InputStream is;
	public int buffsize = 50000;
	public int discardSize = 50*1024*1024;
	private byte[] discardArr = new byte[discardSize];
	
	
	public ClientTCP(String addr,int port) throws IOException {
		socket = new Socket();
		// Connexion au serveur 
        InetSocketAddress adrDest = new InetSocketAddress(addr, port);
        socket.connect(adrDest);     
        
        os = socket.getOutputStream();
        is = socket.getInputStream();
	}
	
	public String rcv_string() throws IOException {
		byte[] bufR = new byte[buffsize];
		String reponse = "";
		int lenBufR = is.read(bufR);
        if (lenBufR!=-1)
        {
            reponse = new String(bufR, 0 , lenBufR );
        }
		return reponse;
	}
	
	public Tableau rcv() throws IOException {
		Tableau t = new Tableau();
		t.len = is.read(t.buf);
        if (t.len==-1) {
        	t.len = 0;
        }
        return t;
	}
	
	public String rcv_sanitized_string() throws IOException {
		byte[] bufR = new byte[buffsize];
		String reponse = "";
		int lenBufR = is.read(bufR);
        if (lenBufR!=-1)
        {
        	for(int i=0; i<lenBufR; i++) {
        		if(bufR[i]>0x7E || bufR[i]<0x20)
        			bufR[i]=0x20;
        	}
            reponse = new String(bufR, 0 , lenBufR );
        }
		return reponse;
	}
	
	public int discard() throws IOException {
		int lenBufR = is.read(discardArr);
        if (lenBufR==-1)
        	lenBufR = 0;
		return lenBufR;
	}
	
	public void send(String str) throws IOException {
		byte[] bufE = str.getBytes();
        os.write(bufE);
	}
	
	public void send(Tableau t) throws IOException {
        os.write(t.buf,0,t.len);
	}
	public void send(Tableau t, int off, int len) throws IOException {
        os.write(t.buf,off,len);
	}
	
	public void close() throws IOException {
		socket.close();
	}
	
	

}
