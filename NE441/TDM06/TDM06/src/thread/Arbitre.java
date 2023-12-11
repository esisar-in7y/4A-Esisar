package thread;

import java.util.ArrayList;
import java.util.List;

public class Arbitre {

	int nbBaguettes;
    List<Baguette> listBaguette = new ArrayList<>();
	
	public Arbitre(int nbBaguettes, List<Baguette> listBaguette) {
		super();
		this.nbBaguettes = nbBaguettes;
		this.listBaguette = listBaguette;
	}

	boolean autorisation(int numPhilo)
	{
		if(numPhilo < 49)
		{
			if(listBaguette.get(numPhilo-1).available && listBaguette.get(numPhilo).available)
			{
				listBaguette.get(numPhilo-1).available = false;
				listBaguette.get(numPhilo).available = false;
	    		//System.out.println("Prends tes baguettes salope");
				return true;
			}
			else
			{
	    		//System.out.println("Suce-moi et on verra");
				return false;
			}
		}
		else
		{
			if(listBaguette.get(numPhilo-1).available && listBaguette.get(0).available)
			{
				listBaguette.get(numPhilo-1).available = false;
				listBaguette.get(0).available = false;
	    		//System.out.println("Prends tes baguettes salope");
				return true;
			}
			else
			{
	    		//System.out.println("Suce-moi et on verra");
				return false;
			}
		}
	}
	
	void liberation(int numPhilo)
	{
		if(numPhilo < 49)
		{
			listBaguette.get(numPhilo-1).available = true;
			listBaguette.get(numPhilo).available = true;
		}
		else
		{
			listBaguette.get(numPhilo-1).available = true;
			listBaguette.get(0).available = true;
		}
	}
}
