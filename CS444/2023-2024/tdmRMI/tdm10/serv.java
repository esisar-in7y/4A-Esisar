package tdm10;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;

public class serv {
	int WIDTH=100;
	int HEIGHT=100;

    private Area[] obstacles = new Area[2];
    private Area walls;

    int x; 
    int y;
    int xDelta = 3;
    int yDelta = 2;
	
    public static void main(String[] args) throws Exception
    {
    	serv serveurTCP = new serv();
        serveurTCP.execute();

    }


    /** A method to determine if two instances of Area intersect */
    public boolean doAreasCollide(Area area1, Area area2) {
        boolean collide = false;

        Area collide1 = new Area(area1);
        collide1.subtract(area2);
        if (!collide1.equals(area1)) {
            collide = true;
        }

        Area collide2 = new Area(area2);
        collide2.subtract(area1);
        if (!collide2.equals(area2)) {
            collide = true;
        }

        return collide;
    }
    public BufferedImage animate(int nwindows) {
    	BufferedImage img = new BufferedImage(WIDTH*nwindows, HEIGHT, BufferedImage.TYPE_INT_RGB);
    	walls=new Area(new Rectangle(0,0,WIDTH*nwindows, HEIGHT));
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.BLUE);
        g.fillRect(0, 0, img.getWidth(), img.getHeight());
        x+=xDelta;
        y+=yDelta;
        int s = 15;
        Area player = new Area(new Ellipse2D.Double(x, y, s, s));

        // Acid test of edge collision;
        if (doAreasCollide(player,walls)) {
            if ( x+s>img.getWidth() || x<0 ) {
                xDelta *= -1;
            } 
            if(y+s>img.getHeight() || y<0 ) {
                yDelta *= -1;
            }
        }
        g.setColor(Color.ORANGE);
        g.fill(player);


        g.dispose();
        return img;
    }

    private void read(Socket sock) throws Exception
    {
        // Un client s'est connecte, le serveur lit le message envoye par le client (sans garantie de lire tout le message)
        byte[] bufR = new byte[2048];
        InputStream is = sock.getInputStream();
        int lenBufR = is.read(bufR);
        if (lenBufR!=-1)
        {
            String message = new String(bufR, 0 , lenBufR);
            System.out.println("Message recu = "+message);
        }

    	
    }
    private void send(Socket sock,byte[] data) throws Exception
    {
        // Emission d'un message en retour
        byte[] bufE = new String("ok").getBytes();
        OutputStream os = sock.getOutputStream();
        os.write(bufE);
        System.out.println("Message envoye = ok");
    	
    }
    private void execute() throws IOException
    {
        //
    	for(int i=0;i<100;i++) {
            ImageIO.write(animate(3), "BMP", new File("filename.bmp"));
    	}
        System.out.println("Demarrage du serveur ...");

        // Le serveur se declare aupres de la couche transport
        // sur le port 5099
        ServerSocket socketEcoute = new ServerSocket();
        socketEcoute.bind(new InetSocketAddress(8080));


        // Attente de la connexion d'un client
        System.out.println("Attente de la connexion du client ...");
        Socket socketConnexion = socketEcoute.accept();

        // Affichage du port et de l'ip du client 
        System.out.println("Un client est connecté");
        System.out.println("IP:"+socketConnexion.getInetAddress());
        System.out.println("Port:"+socketConnexion.getPort());


        // Fermeture de la socket de connexion
        socketConnexion.close();


        // Arret du serveur 
        socketEcoute.close();
        System.out.println("Arret du serveur .");
        

    }

}

