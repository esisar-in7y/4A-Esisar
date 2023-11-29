package tcp;

public class Tableau {
	
	

	public byte[] buf;
	public int len;

	public Tableau() {
		buf = new byte[1024*1024];
		len = 0;
	}

}
