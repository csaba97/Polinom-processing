package model;

/**
 * Class implementing Monoms
 */
public class Monom {

	private double coefficient;
	private int degree;
	private String representation;

	public double getCoefficient() {
		return coefficient;
	}

	/**
	 * Sets the coefficient of the Monom and recalculates its representation
	 */
	public void setCoefficient(double coefficient) {
		String s = String.format("%.2f", coefficient);// work with only 2 digits
		this.coefficient = Double.parseDouble(s);
		this.representation = makeRepresentation(this.coefficient, this.degree);
	}

	public String getRepresentation() {
		return representation;
	}

	public int getDegree() {
		return degree;
	}

	/**
	 * Sets the degree of the Monom and recalculates its representation
	 */
	public void setDegree(int degree) {
		this.degree = degree;
		this.representation = makeRepresentation(this.coefficient, this.degree);
	}

	/**
	 * Constructs a Monom knowing its coefficient and degree
	 */
	public Monom(double coefficient, int degree) {
		String s = String.format("%.2f", coefficient);// work with only 2 digits
		this.coefficient = Double.parseDouble(s);
		this.degree = degree;
		// even if I know the representation of the Monom, I need to recalculate it,
		// because some elements may be in bad order
		this.representation = makeRepresentation(this.coefficient, this.degree);
	}

	/**
	 * Constructs a Monom knowing its representation
	 */
	public Monom(String element) {
		this(extractCoefficient(element), extractDegree(element));

	}

	/**
	 * Extracts the coefficient from a String of format ?x^?
	 */
	public static double extractCoefficient(String element) {
		double coef = 0;
		try {
			// if element doesn't start with + or -, then put a + in front of it
			if (element.charAt(0) != '+' && element.charAt(0) != '-')
				element = "+" + element;

			if (element.contains("x"))
				// 2 cases here
				// if x is the first character(after +/- mark), for example "+x^3"
				// then no coefficient is present => coefficient is either 1 or -1
				if (element.charAt(1) == 'x')
					if (element.charAt(0) == '-')
						coef = -1;
					else
						coef = 1;
				else// or the coefficient appears in front of x
					coef = Double.parseDouble(element.substring(0, element.indexOf("x")));
			else// the String contains only a number with a sign -> degree=0
				coef = Double.parseDouble(element);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return coef;
	}

	/**
	 * Extracts the degree from a String of format ?x^?
	 */
	public static int extractDegree(String element) {
		int deg = 0;
		try {
			// if element doesn't start with + or -, then put a + in front of it
			if (element.charAt(0) != '+' && element.charAt(0) != '-')
				element = "+" + element;

			// we have 3 cases
			// 1: x^3
			if (element.contains("x^"))
				deg = Integer.parseInt(element.substring(element.indexOf("^") + 1));
			// 2: x
			else if (element.contains("x"))
				deg = 1;
			// 3: 7
			else
				deg = 0;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return deg;
	}

	/**
	 * Makes the representation of the Monom knowing its coefficient and degree
	 */
	public static String makeRepresentation(double coef, int deg) {
		String repr = "";
		String deg_actual;// for easier manipulation of the output we concatenate the degree with "x^"
							// when possible
		if (deg == 1)
			deg_actual = "x";
		else if (deg == 0)
			if (coef == 1 || coef == -1)
				deg_actual = "1";// for -1 minus sign(-) will be put afterward
			else
				deg_actual = "";// if the coefficient is not 0,not 1,not -1 we can eliminate totally the form
								// "x^0" from the representation
		else
			deg_actual = "x^" + deg;// if the degree>1 then we MUST include it

		if (coef == 1)
			repr = "+" + deg_actual;// we don't include '1' in the final representation - it is ugly
		else if (coef == -1)
			repr = "-" + deg_actual;// we don't include '-1' in the final representation - it is ugly
		else if (coef > 0)
			if ((coef % 1) == 0)// if the coefficient is in fact a whole number, don't print it like a real
								// number
				repr = "+" + (int) coef + deg_actual;
			else
				repr = "+" + coef + deg_actual;
		else if (coef < 0)
			if ((coef % 1) == 0)// if the coefficient is in fact a whole number, don't print it like a real
								// number
				repr = (int) coef + deg_actual;// - sign is already included
			else
				repr = coef + deg_actual;// - sign is already included
		else
			repr = "0";// 0 coefficient means the Monom is 0
		return repr;

	}

	/**
	 * Divides the current Monom with another Monom and returns a new Monom as a
	 * result
	 */
	public Monom div(Monom b) throws IllegalArgumentException {
		Monom m = new Monom(0, 0);
		if (b.coefficient != 0) {
			if (this.getDegree() - b.getDegree() >= 0)
				m = new Monom(this.getCoefficient() / b.getCoefficient(), this.getDegree() - b.getDegree());
			else
				m = new Monom(0, 0);// the result is not really 0, but we work only with degrees>=0
		} else
			throw new IllegalArgumentException("Argument 'b' is 0 - cannot divide with 0");

		return m;
	}

}
