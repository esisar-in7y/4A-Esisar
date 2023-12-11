package thread;

import java.util.ArrayList;
import java.util.List;

import tdm.tdm07.exo3.checker.CodeChecker;


public class Philosophe2 extends Thread
{
	int numero;
	Arbitre arbitre;
	Baguette gauche;
	Baguette droite;

    public Philosophe2(int numero, Arbitre a, Baguette baguetteGauche, Baguette baguetteDroite)
    {
        super();
        this.numero = numero;
        this.arbitre = a;
        this.gauche = baguetteGauche;
        this.droite = baguetteDroite;
    }
    
    public void discuter(double temps) throws InterruptedException
    {
    	//System.out.println("Je suis le philosophe " + numero + " et je discute pendant " + temps + "secondes");
    	Thread.sleep((int) temps * 10);
    }
    
    public void manger(double temps, Arbitre a) throws InterruptedException
    {
    	if(arbitre.autorisation(numero))
    	{
    		CodeChecker.startEating(numero); // A inserer à l'endroit où votre philosophe commence de manger
    		//System.out.println("Je suis le philosophe " + numero + " et je mange pendant " + temps + "secondes");
        	Thread.sleep((int) temps * 10);
        	arbitre.liberation(numero);
        	CodeChecker.stopEating(numero);  // A inserer à l'endroit où votre philosophe arrête de manger
    	}
    	else
    	{
    		//System.out.println("Can I have it? " + numero + " I want my baguettes");
    		Object lock = new Object();
    		synchronized(lock){
    		    lock.wait(1);
    		}
    	}
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
				manger(temps2, arbitre);
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
        List<Baguette> listBaguette = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
        	Baguette bi = new Baguette();
        	listBaguette.add(bi);
        }
    	Arbitre a = new Arbitre(50, listBaguette);
        List<Philosophe2> threadsPhilo = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
        	if(i < 5)
        	{
        		Philosophe2 pi = new Philosophe2(i, a, listBaguette.get(i-1), listBaguette.get(i));
            	threadsPhilo.add(pi);
                pi.start();
        	}
        	else if(i == 50)
        	{
        		Philosophe2 pi = new Philosophe2(i, a, listBaguette.get(i-1), listBaguette.get(0));
            	threadsPhilo.add(pi);
                pi.start();
        	}
        }

    }

}
