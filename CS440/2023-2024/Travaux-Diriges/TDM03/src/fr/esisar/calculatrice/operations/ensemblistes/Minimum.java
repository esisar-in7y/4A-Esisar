package fr.esisar.calculatrice.operations.ensemblistes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import fr.esisar.calculatrice.operations.OperationEnsembliste;

public class Minimum extends OperationEnsembliste {

	@Override
	public String getNom() {
		return "min";
	}

	protected Double doCalculer(Double[] ops) {
		List<Double> operandes = Arrays.asList(ops);
		return Collections.min(operandes);
	}

}
