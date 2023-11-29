package src;

import java.util.ArrayList;
import java.util.List;

public class EX2 extends Thread {
	
	long start;
    long end;
    public double res=0;
    
    public EX2() {
    	;
    }
    
    public EX2(long start,long end)
    {
        this.start = start;
        this.end = end;
    }

    public void run()
    {
        res =  calculpi(start,end);  
    }

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		EX2 obj = new EX2();
		obj.execute(8);

	}

	void execute() {
		long start = System.currentTimeMillis();
		
		double pi = calculpi(0,10000000000l);//10000000000
		System.out.println(pi);
		
		long stop = System.currentTimeMillis();
		System.out.println("Elapsed Time = "+(stop-start)+" ms");
	}

	void execute(int nb_threads) throws InterruptedException {
		if (nb_threads <2){
			execute();
		}
		List<EX2> threads = new ArrayList<EX2>();
		double pi=0;
		long range = 10000000000l /nb_threads;
		for(int i=0;i<nb_threads-1;i++) {
			EX2 tmp = new EX2(i*range,(i+1)*range);
			threads.add(tmp);
		}
		threads.add(new EX2((nb_threads-1)*range,10000000000l));
		
		long start = System.currentTimeMillis();
		for(EX2 t : threads) {
			t.start();
		}
		for(EX2 t : threads) {
			t.join();
		}
		for(EX2 t : threads) {
			pi+=t.res;
		}
		System.out.println("pi="+pi);
		
		long stop = System.currentTimeMillis();
		System.out.println("Elapsed Time = "+(stop-start)+" ms");
	}
		

	private double calculpi(long début, long fin) {
		double pi,tmp,sign;
		pi=0;
		for(long i=début; i<fin; i+=1) {
			tmp=(2*i+1);
			tmp=1/tmp;
			sign=1-2*(i&1);
			pi+=sign*tmp;
		}
		return pi*4;
	}
}
