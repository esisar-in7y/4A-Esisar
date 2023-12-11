package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Exo6
{
	//TODO : faire cette merde
    public static void main(String[] args) throws Exception
    {
        Exo6 serveurTCP = new Exo6();
        serveurTCP.execute();

    }

    private void execute() throws IOException
    {
    	Ball ball = new Ball("red", 0, 0);
    	Boolean endCommunication = false;
        System.out.println("Demarrage du serveur ...");

        ServerSocket socketEcoute = new ServerSocket();
        socketEcoute.bind(new InetSocketAddress(8000));

        System.out.println("Attente de la connexion du client ...");
        Socket socketConnexion = socketEcoute.accept();

        System.out.println("Un client est connecté");
        System.out.println("IP:"+socketConnexion.getInetAddress());
        System.out.println("Port:"+socketConnexion.getPort());
        
        receive(socketConnexion);
        send("ok", socketConnexion);

        socketConnexion.close();

        if(endCommunication)
        {
            socketEcoute.close();
            System.out.println("Arret du serveur .");
        }
    }
    
    private void send(String message, Socket socketConnexion) throws IOException
    {
    	byte[] bufE = new String(message).getBytes();
        OutputStream os = socketConnexion.getOutputStream();
        os.write(bufE);
        System.out.println("Message envoye = " + message);
    }

    private String receive(Socket socketConnexion) throws IOException
    {
    	byte[] bufR = new byte[2048];
        InputStream is = socketConnexion.getInputStream();
        int lenBufR = is.read(bufR);
        String message = "";
        if (lenBufR!=-1)
        {
            message = new String(bufR, 0 , lenBufR);
            System.out.println("Message recu = "+message);
        }
        return message;
    }
}
