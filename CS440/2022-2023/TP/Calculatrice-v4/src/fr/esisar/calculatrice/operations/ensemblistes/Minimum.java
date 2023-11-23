package fr.esisar.calculatrice.operations.ensemblistes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import fr.esisar.calculatrice.operations.OperationEnsembliste;

public class Minimum extends OperationEnsembliste {

	public String getNom() {
		return "min";
	}
	
	@Override
	protected Double doCalculer(Double[] operandes) {

		List<Double> list = Arrays.asList(operandes);
		return Collections.min(list);
	}

	
}
