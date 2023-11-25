package md5;

import java.util.Optional;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;


/**
 * Programme utilisant les services offerts par CalculetteServer
 *
 */
public class Client extends Thread
{
    Crack stub;
    String hash;
    String start_string;
    int taille;
    int START;
    int STOP;
    public Client(Crack stub,String hash,String start_string,int taille,int START,int STOP){
        this.stub=stub;
        this.hash=hash;
        this.start_string=start_string;
        this.taille=taille;
        this.START=START;
        this.STOP=STOP;
    }
    public void run()
    {
        try{
            Integer c = stub.cracker(START, STOP,hash,start_string);
            if(c>=0){
                System.out.println("Le resultat est "+c);
            }
        }catch(Exception e){
            System.out.println("Erreur");
        }
    }
    public static void main(String[] args) throws Exception
    {
        ArrayList<Registry> registries = new ArrayList<Registry>();
        ArrayList<Crack> stubs = new ArrayList<Crack>();
        for(int port=5000;port<5100;port++){
            try{
                Registry registry = LocateRegistry.getRegistry("127.0.0.1",port);
                stubs.add((Crack) registry.lookup("cracker"));
                registries.add(registry);
            }catch(Exception e){
                System.out.println("Registry "+port+" not found");
            }
        }


        // On va maintenant pourvoir s'en servir pour faire un calcul 
        System.out.println("Début du programme client");
        int START=0;
        int STOP=1_000_000_000;
        int threads_per_pc=5;
        String hash="d31722b646a4ad1b5bf2abdfac2b4e17";
        String start_string="p2023esisar"; 
        int taille = (STOP - START) / (stubs.size()*threads_per_pc);
        ArrayList<Thread> threads = new ArrayList<Thread>();

        for(int i=0;i<stubs.size();i++){
            for(int j=0;j<threads_per_pc;j++){
                Client client = new Client(stubs.get(i),hash,start_string,taille,START,START+taille);
                client.start();
                threads.add(client);
                START+=taille;
            }
        }
        for(Thread t:threads){
            t.join();
        }

    }
}