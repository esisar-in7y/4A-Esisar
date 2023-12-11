package tdm5;

public class PIClassic {

	private long N = 10_000_000_000l;
	
	public double calcul()
	{
		double result = 0d;
		for (long i = 0; i < N; i++)
		{
			if (i%2 == 0) {
				result += 1d/(2*i+1);
			}
			else {
				result -= 1d/(2*i+1);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		PIClassic pi = new PIClassic();
		double result = 4*pi.calcul();
		System.out.println("PI = " + result);
	}

}
