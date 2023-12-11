package thread;

public class Euler extends Thread
{
	long start;
	long end;
	double res=0;
	

	public Euler(long start,long end)
	{
		this.start = start;
		this.end = end;
	}

	public void run()
	{
		for (long i = start; i <= end; i++)
		{
			res = res + ((double) 1)/( (double) i);
		}	
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException
	{
		
		
		Euler e1 = new Euler(1, 5000000000l);
		Euler e2 = new Euler(5000000001l, 10000000000l);
		
		e1.start();
		e2.start();
		
		e1.join();
		e2.join();
		
		double euler = e1.res+e2.res-Math.log(10000000000l);
		System.out.println("Euler="+euler);

	}

}
