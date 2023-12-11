package thread;

public class Baguette { 
	int numero;
	Boolean available = true;
	
	synchronized public void baguette(int numero, Boolean available)
	{
		this.numero = numero;
		this.available = available;
	}
	
}
