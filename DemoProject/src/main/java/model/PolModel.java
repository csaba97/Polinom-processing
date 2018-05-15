package model;

/**
 * Model Class for the MVC model of the GUI
 */
public class PolModel {
	private Polinom result;
	private Polinom rest;

	public PolModel() {
		reset();
	}

	/**
	 * Resets the Result and Rest to 0
	 */
	public void reset() {
		result = new Polinom("0x^0");
		rest = new Polinom("0x^0");
	}

	/**
	 * Method for adding a Polynom to the result
	 */
	public void add(String repr) {
		result = result.add(new Polinom(repr));
	}

	/**
	 * Method for substracting a Polynom to the result
	 */
	public void sub(String repr) {
		result = result.sub(new Polinom(repr));
	}

	/**
	 * Method for multiplying with a Polynom the result
	 */
	public void mul(String repr) {
		result = result.mul(new Polinom(repr));
	}

	/**
	 * Method for dividing with a Polynom the result
	 */
	public void divide(String repr) throws IllegalArgumentException {
		Polinom[] arrayResults = result.divide(new Polinom(repr));
		result = arrayResults[0];
		rest = arrayResults[1];
	}

	/**
	 * Method for derivating the result
	 */
	public void derivate() {
		result = result.derivate();
	}

	/**
	 * Method for integrating the result
	 */
	public void integrate() {
		result = result.integrate();
	}

	/**
	 * Sets the result to a new value
	 */
	public void setResult(String repr) {
		result = new Polinom(repr);
	}

	/**
	 * Returns the result as a String
	 */
	public String getResult() {
		return result.getRepresentation();
	}

	/**
	 * Method for setting the Rest to a new value
	 */
	public void setRest(String repr) {
		rest = new Polinom(repr);
	}

	/**
	 * Returns the rest as a String
	 */
	public String getRest() {
		return rest.getRepresentation();
	}
}
