package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SommeTCP extends Thread
{
	private int port;
	private int finalSum = 0;
	
    public SommeTCP(int port) {
		super();
		this.port = port;
	}

	public void run()
    {

        try {
			Socket socket = new Socket();

			InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", port);
			socket.connect(adrDest);        

			byte[] bufE = new String("COMBIEN").getBytes();
			OutputStream os = socket.getOutputStream();
			os.write(bufE);

			byte[] bufR = new byte[2048];
			InputStream is = socket.getInputStream();
			int lenBufR = is.read(bufR);
			StringBuilder responseBuilder = new StringBuilder();
			while (lenBufR!=-1)
			{
			    String reponse = new String(bufR, 0 , lenBufR );
			    responseBuilder.append(reponse);
			    lenBufR = is.read(bufR);
			}
			String reponseFinale = responseBuilder.toString();
			int indexEgal = reponseFinale.indexOf('=');
			int indexE = reponseFinale.indexOf('E');
			String somme = reponseFinale.substring(indexEgal+1,indexE);
			finalSum += Integer.parseInt(somme);

			socket.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args) throws Exception
    {
        System.out.println("Début de la recherche ...");
        int finalResult = 0;
        int max = 0;
        int portMax = 0;
        List<SommeTCP> threads = new ArrayList<>();
        for (int i = 21000; i <= 23000; i++) {
            SommeTCP ci = new SommeTCP(i);
            threads.add(ci);
            ci.start();
        }
        for (SommeTCP thread : threads) {
            thread.join();
            finalResult += thread.finalSum;
            if (thread.finalSum > max)
            {
            	max = thread.finalSum;
            	portMax = thread.port;
            }
        }
        System.out.println("Le montant maximum est " + max + " euros"); 
        System.out.println("Le port d'écoute correspondant à ce maximum est " + portMax); 
        System.out.println("La somme des montants retournés par tous les ports est " + finalResult); 
        System.out.println("Fin du programme");
    }
}