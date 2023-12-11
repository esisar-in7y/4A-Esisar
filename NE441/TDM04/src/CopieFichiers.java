import java.io.*;

public class CopieFichiers {

	private String path1 = "/home/lysio4/file1";
	private String path2 = "/home/lysio4/file2";
	
	public static void main(String[] args) throws IOException {
		CopieFichiers c = new CopieFichiers();
		c.copie(c.path1, c.path2);

	}
	public void copie(String path1, String path2) throws IOException
	{
		long start = System.currentTimeMillis();
		byte[] b1 = new byte [1024];
		InputStream source = new FileInputStream(path1);
		OutputStream destination = new FileOutputStream(path2);
		int readByte = source.read(b1);
		while (readByte != -1)
		{
			destination.write(b1, 0, readByte);
			readByte = source.read(b1);
		}
		source.close();
		destination.close();
		long stop = System.currentTimeMillis();
		System.out.println("Elapsed Time = "+(stop-start)+" ms");
	}
}
