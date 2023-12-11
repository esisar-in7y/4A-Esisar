package tdm5;

public class PIThread extends Thread {

	private long N = 10_000_000_000l;
	private long start;
	private double res = 0;
	
	public PIThread(long start) {
		super();
		this.start = start;
	}

	public void run()
	{
		for (long i = start; i < N; i+=4)
		{
			res += 1.0/(2.0*i+1.0);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		PIThread pi1 = new PIThread(0l);
		PIThread pi2 = new PIThread(1l);
		PIThread pi3 = new PIThread(2l);
		PIThread pi4 = new PIThread(3l);
		
		pi1.start();
		pi2.start();
		pi3.start();
		pi4.start();
		
		pi1.join();
		pi2.join();
		pi3.join();
		pi4.join();
		
		double result = 4*(pi1.res - pi2.res + pi3.res - pi4.res);
		System.out.println("PI = " + result);
	}

}
