
import java.io.FileOutputStream;
import java.io.IOException;

public class SampleFileWriter 
{
	public static void main(String[] args) throws Exception
	{
		SampleFileWriter fr = new SampleFileWriter();
		fr.execute();
	}
				
				
	/**
	 * 
	 */
	private void execute() throws IOException
	{
		System.out.println("Début écriture du fichier");
		
		FileOutputStream fos = new FileOutputStream("c:/tmp/ecriture_fichier.txt");
		
		
		byte[] buf = new byte[10];
		
		buf[0] = 69;
		buf[1] = 83;
		buf[2] = 73;
		buf[3] = 83;
		buf[4] = 65;
		buf[5] = 82;
		
		
		// Ecriture des 6 premiers octets du buffer 
		fos.write(buf,0,6);
		
		// Fermeture du fichier
		fos.close();
		
		System.out.println("Fin écriture du fichier");
	}
}
