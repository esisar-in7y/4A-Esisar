import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;

/**
 * Client basique TCP
 * 
 */
public class EX4
{

    public static void main(String[] args) throws Exception
    {
        EX4 clientTCP = new EX4();
        clientTCP.execute();                
    }

    /**
     * Le client cree une socket, envoie un message au serveur
     * et attend la reponse 
     * 
     */
    private void execute() throws IOException
    {
        //
        System.out.println("Demarrage du client ...");

        //Creation de la socket
        Socket socket = new Socket();

        // Connexion au serveur 
        InetSocketAddress adrDest = new InetSocketAddress("127.0.0.1", 7500);
        socket.connect(adrDest);        

        /* Envoi de la requete
        byte[] bufE = new String("question du client").getBytes();
        OutputStream os = socket.getOutputStream();
        os.write(bufE);
        System.out.println("Message envoye");
        */
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        //try {
	        byte[] bufR = new byte[2048];
	        int lenBufR;
	        String reponse = "";
        while(true) {
	        // Attente de la reponse 
        	lenBufR = 0;
	        while(!reponse.contains("=?")) {
	        	bufR[0]=0;
	        	lenBufR = is.read(bufR);
	        	reponse = reponse + new String(bufR, 0 , lenBufR );
	        }
	        if (lenBufR!=-1)
	        {
	            
	            System.out.println("Reponse recue:"+reponse);
	            
	            String[] questions = reponse.split("=\\?");
	            if(reponse.endsWith("=?")) {
	            	reponse = "";
	            }
	            else {
		            reponse = questions[questions.length - 1];
		            questions = Arrays.copyOf(questions, questions.length - 1);
	            }
	            	
	            for(String q : questions ) {
	            	String[] op = q.split("\\+");
	            	Integer a = Integer.parseInt(op[0]) + Integer.parseInt(op[1]);
	            	
	            	byte[] bufE = new String(""+a+";").getBytes();
	                os.write(bufE);
	                System.out.println(a+";");
	            }
	        }
        }
        //} catch(Exception a) {System.out.println("catch");}

        // Fermeture de la socket
        //socket.close();
        //System.out.println("Arret du client .");
    }
}
