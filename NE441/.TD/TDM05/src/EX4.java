package src;

import java.util.ArrayList;
import java.util.List;

public class EX4 {

	int max=0;
	int pmax=0;
	int somme=0;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		EX4 obj = new EX4();
		obj.execute(200);
		System.out.println("max = "+obj.max);
		System.out.println("pmax = "+obj.pmax);
		System.out.println("sum = "+obj.somme);
	}

	private void execute() {
		// TODO Auto-generated method stub
		ClientTCP c1 = new ClientTCP(21000,23000);
		c1.start();
	}
	
	private void execute(int nb_threads) throws InterruptedException{
		if (nb_threads <2){
			execute();
		}
		List<ClientTCP> threads = new ArrayList<ClientTCP>();
		int range = (23000-21000) /nb_threads;
		for(int i=0;i<nb_threads-1;i++) {
			ClientTCP tmp = new ClientTCP(21000+i*range,21000+(i+1)*range);
			threads.add(tmp);
		}
		threads.add(new ClientTCP(21000+(nb_threads-1)*range,23001));
		
		long start = System.currentTimeMillis();
		for(ClientTCP t : threads) {
			t.start();
		}
		for(ClientTCP t : threads) {
			t.join();
			if (max<t.max) {
				max=t.max;
				pmax=t.pmax;
			}
			somme += t.somme;
		}
		
		long stop = System.currentTimeMillis();
		System.out.println("Elapsed Time = "+(stop-start)+" ms");
	}

}
