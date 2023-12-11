import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.ArrayList;

public class Serveur {
    DatagramSocket Server_socket;
    ArrayList<InetSocketAddress> listeAdres = new ArrayList<InetSocketAddress>();
    public static void main(String args[]) {
        Serveur s = new Serveur();
        s.Server_socket = new DatagramSocket(null);
        s.Server_socket.bind(new InetSocketAddress(4005));
        s.exectue();
    }
    private void send(String text,InetSocketAddress Client_Address)  throws Exception{
        byte[] bufE = new String(text).getBytes();
        Server_socket.send(new DatagramPacket(bufE, bufE.length,Client_Address));
    }
    private String receive()  throws Exception{
        byte[] bufR = new byte[2048];
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        Server_socket.receive(dpR);
        return new String(bufR, dpR.getOffset(), dpR.getLength());
    }

    private void exectue() throws Exception{
        //déclaration serveur écoute sur le port 4005
        System.out.println("Démmarage du serveur: ");

        String reponse = "";
        //le dernier client enverra "manger"
        while(!reponse.contains(";")){
            reponse = receive();
            System.out.println("J'ai recu: "+reponse);

            listeAdres.add(recepAdr(reponse));
            System.out.println("client added");
        }
        for(int i=0;i<10;i++){
            for(InetSocketAddress client_address:listeAdres){
                send("red",client_address);
                Thread.sleep(1000);
                send("green",client_address);
            }
            System.out.println("Boucle "+i);
        }
        Server_socket.close();
        System.out.println("Arret");
    }

    private InetSocketAddress recepAdr(String reponse) throws Exception{
        String adrIP = reponse.split(":")[0];
        String port = reponse.split(":")[1];
        System.out.println("Port: "+port+" IP: "+adrIP);
        int int_port = Integer.parseInt(port);
        return new InetSocketAddress(adrIP,int_port);
    }
}
