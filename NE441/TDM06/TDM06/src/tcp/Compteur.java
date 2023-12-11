package tcp;

public class Compteur{
    private Integer numero;

    public Compteur() {
        super();
        this.numero = 1;
    }
    
    synchronized public Integer getNumero() {
        return numero++;
    }
    
}