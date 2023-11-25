package tdm10;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.FileOutputStream;
import java.io.IOException;

public class extractor {
	Socket socket;
	InputStream is;
    public static void main(String[] args) throws Exception
    {
    	extractor clientTCP = new extractor();
        clientTCP.execute();                
    }
    public void send(String msg) throws Exception
    {
        byte[] bufE = msg.getBytes();
        OutputStream os = socket.getOutputStream();
        os.write(bufE);
        System.out.println("Message envoye");
    }
    public int readheader() throws Exception
    { 
        byte[] bufR = new byte[1];
        String tmp="";
        boolean end_headers=false;
        int length=0;
        InputStream is = socket.getInputStream();
        int lenBufR = is.read(bufR);
        while (lenBufR!=-1 && !end_headers)
        {
        	tmp+=new String(bufR);
        	if(tmp.endsWith("\r\n\r\n")) {
        		end_headers=true;
        		String[] a=tmp.split("Content-Length:");
        		if(a.length>1) {
        			length=Integer.parseInt(a[1].split("\n")[0].strip());
        		}
        	}
        	lenBufR = is.read(bufR);
        }
        return length;
    }

    public boolean read(int len,int id) throws Exception
    { 
        System.out.println("Read: "+len+"bytes");
    	FileOutputStream fos = new FileOutputStream("bmp/out"+id+".bmp");
        byte[] bufR = new byte[2048];
        int lenBufR = is.read(bufR,0,len>2048?2048:len);
        while (lenBufR!=-1 && len>0)
        {
        	len-=lenBufR;
        	fos.write(bufR,0,lenBufR);
        	lenBufR = is.read(bufR,0,len>2048?2048:len);
        }
        fos.close();
        System.out.println("Written: "+"bmp/out"+id+".bmp");
    	return false;
    }


    private void execute() throws Exception
    {
        //
        System.out.println("Demarrage du client ...");

        //Creation de la socket
        socket = new Socket();
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 8000);
        socket.connect(adrDest);
        is = socket.getInputStream();
        
        send("GET / HTTP/1.1\nHost: 127.0.0.1:8000\nUser-Agent: 0\nAccept: */*;\nAccept-Encoding: identity");
        readheader();
        boolean end=false;
        int i=0;
        while(!end) {
            int length=readheader();
            read(length,i);
            i++;
        }
        
        // Fermeture de la socket
        socket.close();
        System.out.println("Arret du client .");
    }
}
