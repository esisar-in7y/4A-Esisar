package src;


public class EX1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EX1 obj = new EX1();
		obj.execute();

	}

	void execute() {
		long start = System.currentTimeMillis();
		
		double pi = calculpi(10000000000l);//10000000000
		System.out.println(pi);
		
		long stop = System.currentTimeMillis();
		System.out.println("Elapsed Time = "+(stop-start)+" ms");
	}

		

	private double calculpi(long N) {
		double pi,tmp,sign;
		pi=0;
		for(long i=0; i<N; i+=1) {
			tmp=(2*i+1);
			tmp=1/tmp;
			sign=1-2*(i&1);
			pi+=sign*tmp;
		}
		return pi*4;
	}
}
