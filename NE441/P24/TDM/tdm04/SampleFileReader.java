
import java.io.FileInputStream;
import java.io.IOException;

public class SampleFileReader 
{
	public static void main(String[] args) throws Exception
	{
		SampleFileReader fr = new SampleFileReader();
		fr.execute();
	}
				
				
	/**
	 * 
	 */
	private void execute() throws IOException
	{
		System.out.println("Début lecture du fichier");
		
		FileInputStream fis = new FileInputStream("c:/tmp/lecture_fichier.txt");
		byte[] buf = new byte[10];
		
		int len = fis.read(buf);
		while(len!=-1)
		{
			displayBufContent(buf,len);
			len = fis.read(buf);
		}
		fis.close();
		
		System.out.println("Fin lecture du fichier");
	}


	private void displayBufContent(byte[] buf, int len) 
	{
		System.out.println("len="+len);
		for (int i = 0; i < len; i++) 
		{
			System.out.println("Caractère lu : "+buf[i]);
		}
		
	}
}
