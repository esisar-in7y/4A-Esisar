package thread;

import java.util.ArrayList;
import java.util.List;


public class Philosophe1 extends Thread
{
	int numero;

    public Philosophe1(int numero)
    {
        super();
        this.numero = numero;
    }
    
    public void discuter(double temps) throws InterruptedException
    {
    	System.out.println("Je suis le philosophe " + numero + " et je discute pendant " + temps + " secondes");
    	Thread.sleep((int) temps * 10);
    }
    
    public void manger(double temps) throws InterruptedException
    {
    	System.out.println("Je suis le philosophe " + numero + " et je mange pendant " + temps + " secondes");
    	Thread.sleep((int) temps * 10);
    }

    public void run()
    {
        while(true)
        {
        	double temps1 = 10*Math.random();
        	try {
				discuter(temps1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	double temps2 = 10*Math.random();
        	try {
				manger(temps2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        List<Philosophe1> threads = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
        	Philosophe1 pi = new Philosophe1(i);
            threads.add(pi);
            pi.start();
        }

    }

}
