import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ShutDownServer 
{
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		ShutDownServer shutDownServer = new ShutDownServer();
		shutDownServer.execute();
	}
	
		
	private void execute() throws IOException, InterruptedException 
	{

		ServerSocket s = new ServerSocket(2000);
		
		Thread.sleep(10_000);
		
		Socket socket = s.accept();
		
		
		// Fermeture de la socket , uniquement dans le sens Serveur vers Client
		socket.shutdownOutput();
		
		
		// Le serveur peut toujours recevoir des messages du client
		
		byte[] buf = new byte[1024];
		InputStream is = socket.getInputStream();
		int len=is.read(buf);
		
		while(len!=-1)
		{
			String message = new String(buf,0,len);
			System.out.println("m="+message);
			len=is.read(buf);
		}
		
		
		
	}
}
