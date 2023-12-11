import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.awt.Color;
import javax.swing.JFrame;

public class Client {
    DatagramSocket Client_socket;
    int port;
    InetSocketAddress server_Address;
    public static void main(String args[]){
        Client c = new Client();
        c.server_Address = new InetSocketAddress("localhost",4005);
        c.execute(Integer.parseInt(args[0]));
    }
    private void send(String text)  throws Exception{
        byte[] bufE = new String(text).getBytes();
        Client_socket.send(new DatagramPacket(bufE, bufE.length,server_Address));
    }
    private String receive()  throws Exception{
        byte[] bufR = new byte[2048];
        DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
        Client_socket.receive(dpR);
        return new String(bufR, dpR.getOffset(), dpR.getLength());
    }

    private void execute(int num) throws Exception{
        port = 3100+num;
        //se déclare au niveau de la couche transport
        Client_socket = new DatagramSocket(null);
        Client_socket.bind(new InetSocketAddress(port));
        System.out.println("Démarrage du client sur le port: "+port);

        JFrame frame = new JFrame(String.valueOf(port));
		frame.setSize(300,300);
        frame.setVisible(true);
        if(num==-1){
            send("localhost:"+String.valueOf(port));
        }else{
            send("localhost:"+String.valueOf(port)+";");
        }
        System.out.println("message au server: "+server_Address);

        String response="";
        while(response.equals("fin")){
            response = receive();
            System.out.println("Jmsg:"+response);
            if(response.equals("red")){
                frame.getContentPane().setBackground(Color.RED);
            }else{
                frame.getContentPane().setBackground(Color.GREEN);
            }
            frame.setVisible(true);
        }
        Client_socket.close();
        //socket.close();
        //System.out.println("Arrête du client");
    }
}
