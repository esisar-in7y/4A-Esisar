import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ShutDownClient 
{
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		ShutDownClient shutDownClient = new ShutDownClient();
		shutDownClient.execute();
	}

	private void execute() throws IOException, InterruptedException 
	{
		// Connection au serveur : on peut voir 3 paquets IP (dialogue ternaire)
		Socket socket = new Socket("127.0.0.1", 2000);
		
		// Attente de 30 seconces
		Thread.sleep(30_000);
		
		
		// Envoi d'un segment TCP toutes les 5 secondes
		OutputStream os = socket.getOutputStream();
		while(true)
		{
			os.write("xyz".getBytes());
			Thread.sleep(5000);
		}
		
	}
}
