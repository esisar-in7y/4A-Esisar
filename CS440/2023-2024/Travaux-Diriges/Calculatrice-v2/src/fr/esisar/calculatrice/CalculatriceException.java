package fr.esisar.calculatrice;

public class CalculatriceException extends Exception {

	private static final long serialVersionUID = 1L;
	private final String message;

	public CalculatriceException() {
		super();
		this.message = "";
	}

	public CalculatriceException(String ex) {
		super(ex);
		message = ex;
	}

	@Override
	public String toString() {
		return message;
	}

}
