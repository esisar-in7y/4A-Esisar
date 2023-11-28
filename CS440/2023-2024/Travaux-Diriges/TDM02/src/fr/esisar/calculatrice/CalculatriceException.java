package fr.esisar.calculatrice;

public class CalculatriceException extends Exception {
	private static final long serialVersionUID = 5991714585696464099L;
	private String message = "OperationInvalide";

	@Override
	public String toString() {
		return "CalculatriceException [" + message + "]";
	}

	public CalculatriceException(String message) {
        super(message);
    }
}

