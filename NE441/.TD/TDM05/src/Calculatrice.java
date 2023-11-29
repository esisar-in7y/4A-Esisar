package src;

public class Calculatrice extends Thread
{
    private Somme somme;
    public int res;

    public Calculatrice(Somme somme)
    {
        this.somme = somme;
    }

    @Override
    public void run()
    {
        res = 0;
        for (int i = 0; i < 1000; i++)
        {
            res= somme.somme(res, i);
        }
        
    }


    public static void main(String[] args) throws InterruptedException
    {
        Somme somme1 = new Somme();
        Somme somme2 = new Somme();
        Calculatrice c1 = new Calculatrice(somme1);
        Calculatrice c2 = new Calculatrice(somme2);

        c1.start();
        c2.start();
        c1.join();
        c2.join();
        System.out.println("La somme 1+2+3+4+...+998+999 est égale à :"+c1.res);
    }


    static  class Somme
    {

        int c;

        public int somme(int a, int b) 
        {
            c=a+b;
            System.out.println("c="+c);
            return c;
        }

    }

}
