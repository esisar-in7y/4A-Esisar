package thread;

public class PingPong extends Thread
{
	String chaine;
	int delai;
	

	public PingPong(String chaine, int delai)
	{
		super();
		this.chaine = chaine;
		this.delai = delai;
	}

	public void run()
	{
		while(true)
		{
			System.out.print(chaine+" ");
			try
			{
				sleep(delai);
			} 
			catch (InterruptedException e)
			{
			}
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		PingPong ping = new PingPong("ping", 330);
		PingPong pong = new PingPong("pong", 1000);
		
		ping.start();
		pong.start();

	}

}
