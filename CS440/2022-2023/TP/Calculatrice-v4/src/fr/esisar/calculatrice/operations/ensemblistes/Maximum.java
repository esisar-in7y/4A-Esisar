package fr.esisar.calculatrice.operations.ensemblistes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import fr.esisar.calculatrice.operations.OperationEnsembliste;

public class Maximum extends OperationEnsembliste {

	public String getNom() {
		return "max";
	}
	
	@Override
	protected Double doCalculer(Double[] operandes) {

		List<Double> list = Arrays.asList(operandes);
		return Collections.max(list);
	}

	
}
