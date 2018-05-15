package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Class implementing polynoms
 */
public class Polinom {
	private int degree = 0;
	private ArrayList<Monom> monoms = new ArrayList<Monom>();
	private String representation = "";

	public String getRepresentation() {
		return representation;
	}

	/**
	 * Constructs a Polynom knowing its representation
	 */
	public Polinom(String input) {
		this(processSTring(input));
	}

	/**
	 * Constructs the Polynom knowing the coefficients and degrees of all of its
	 * members
	 */
	public Polinom(ArrayList<Monom> monomList) {
		this.monoms = removeDuplicatesMonoms(sortMonoms(monomList));
		this.representation = makeRepresentation(this.monoms);
		// if the Polinom is 0 then its degree will be 0
		// it can happen that the polinom has a Monom which is 0x^3
		if (this.monoms.get(this.monoms.size() - 1).getCoefficient() != 0)
			this.degree = this.monoms.get(this.monoms.size() - 1).getDegree();
		else
			this.degree = 0;
	}

	public int getDegree() {
		return degree;
	}

	public ArrayList<Monom> getMonoms() {
		return monoms;
	}

	/**
	 * Sorts the Monoms in the Polynom in ascending order by their degree
	 */
	private ArrayList<Monom> sortMonoms(ArrayList<Monom> monomList) {
		Collections.sort(monomList, new DegreeComparator());
		return monomList;
	}

	/**
	 * Removes the 0 elements from the Monoms and sums up all the Monoms which have
	 * the same degree
	 */
	private ArrayList<Monom> removeDuplicatesMonoms(ArrayList<Monom> monomList) {
		for (int i = monomList.size() - 1; i >= 1; i--) {
			Monom a = monomList.get(i);
			Monom b = monomList.get(i - 1);
			// remove elements with 0 coeff(won't remove first element)
			if (a.getCoefficient() == 0) {
				monomList.remove(a);
			} else if (a.getDegree() == b.getDegree()) {
				// sum up all the Monoms which have the same degree, in the end only unique
				// Monoms will remain
				a.setCoefficient(a.getCoefficient() + b.getCoefficient());
				monomList.remove(b);
			}
		}
		// remove the first element if its coeff is 0 AND the polinom has
		// more than one monom(we don't want polinoms with empty arraylists)
		if (monomList.size() > 1 && monomList.get(0).getCoefficient() == 0)
			monomList.remove(monomList.get(0));

		return monomList;
	}

	/**
	 * Calculates the Representation of the Polynom based on the coefficients and
	 * degrees of its Monoms
	 */
	public static String makeRepresentation(ArrayList<Monom> mon) {
		boolean isNull = true;// check if all its monoms are 0
		String repr = "";
		for (Monom monom : mon) {
			repr += monom.getRepresentation();
			if (monom.getRepresentation() != "0")
				isNull = false;
		}
		if (isNull == true)
			repr = "0";
		return repr;
	}

	/**
	 * This method processes a String and returns an ArrayList of Monoms containing
	 * all the coefficients and degrees
	 */
	public static ArrayList<Monom> processSTring(String input) {
		ArrayList<Monom> values = new ArrayList<Monom>();
		Monom monom;
		if (input.equals("0")) {
			// we consider 0 a polinom
			monom = new Monom(0, 0);
			values.add(monom);
		} else {
			// remove whitespaces and special characters from input
			input = input.replaceAll("[,?!' ]", "");
			// we want to preserve the minus and plus sign
			// so we put some character before them and split based on
			// that character(now ',')
			input = input.replaceAll("\\+", ",+");
			input = input.replaceAll("\\-", ",-");

			// constructs the monoms
			StringTokenizer st = new StringTokenizer(input, ",");
			while (st.hasMoreTokens()) {
				String element = st.nextToken();
				monom = new Monom(element);
				values.add(monom);
			}
		}
		return values;
	}

	/**
	 * Method for adding to the current Polynom another Polynom
	 */
	public Polinom add(Polinom other) {
		ArrayList<Monom> resMonoms = new ArrayList<Monom>();
		// we make another arraylist merging the arraylists from the 2 polinoms
		// the polinom's constructor will do its job: will remove 0 monoms,
		// will sum up monoms with the same degree
		resMonoms.addAll(this.monoms);
		resMonoms.addAll(other.getMonoms());
		return new Polinom(resMonoms);
	}

	/**
	 * Method for substracting from the current Polynom another Polynom
	 */
	public Polinom sub(Polinom other) {
		ArrayList<Monom> resMonoms = new ArrayList<Monom>();
		// we make another arraylist merging the arraylists from the 2 polinoms
		// the polinom's constructor will do its job: will remove 0 monoms,
		// will sum up monoms with the same degree
		resMonoms.addAll(this.monoms);
		for (Monom monom : other.getMonoms()) {
			monom.setCoefficient(-1 * monom.getCoefficient());// substraction means +(-)
		}
		resMonoms.addAll(other.getMonoms());
		return new Polinom(resMonoms);
	}

	/**
	 * Method for multiplying the current Polynom with another Polynom
	 */
	public Polinom mul(Polinom other) {
		// we make another arraylist merging the arraylists from the 2 polinoms by
		// multiplying every monom from the 1st polinom with every monom from the 2nd
		// one
		// the polinom's constructor will do its job: will remove 0 monoms,
		// will sum up monoms with the same degree
		ArrayList<Monom> resMonoms = new ArrayList<Monom>();
		for (Monom monom1 : this.monoms) {
			for (Monom monom2 : other.getMonoms()) {
				Monom monom_mul = new Monom(monom1.getCoefficient() * monom2.getCoefficient(),
						monom1.getDegree() + monom2.getDegree());
				resMonoms.add(monom_mul);
			}
		}
		return new Polinom(resMonoms);
	}

	/**
	 * Method for derivating the current Polynom
	 */
	public Polinom derivate() {
		// derivation is simple- the degree of every monom will be smaller with 1 and
		// the coefficient will be
		// multiplied by the degree
		ArrayList<Monom> resMonoms = new ArrayList<Monom>();
		for (Monom monom : this.monoms) {
			if (monom.getDegree() > 0) {
				Monom monom_der = new Monom(monom.getCoefficient() * monom.getDegree(), monom.getDegree() - 1);
				resMonoms.add(monom_der);
			}
		}
		// see if it is 0 -we don't want polinoms with 0 monoms
		if (resMonoms.size() == 0)
			resMonoms.add(new Monom(0, 0));
		return new Polinom(resMonoms);
	}

	/**
	 * Method for integrating the current Polynom
	 */
	public Polinom integrate() {
		// integration is simple- the degree of every monom will be bigger with 1 and
		// the coefficient will be
		// divided by the degree+1
		ArrayList<Monom> resMonoms = new ArrayList<Monom>();
		for (Monom monom : this.monoms) {
			Monom monom_der = new Monom(monom.getCoefficient() / (monom.getDegree() + 1), monom.getDegree() + 1);
			resMonoms.add(monom_der);
		}
		// see if it is 0 -we don't want polinoms with 0 monoms
		if (resMonoms.size() == 0)
			resMonoms.add(new Monom(0, 0));
		return new Polinom(resMonoms);
	}

	/**
	 * LONG DIVISION ALGORITHM Method for dividing the current Polynom with another
	 * Polynom returns the quotient and rest as 2 Polynoms
	 */
	public Polinom[] divide(Polinom other) throws IllegalArgumentException {
		ArrayList<Monom> resMonoms = new ArrayList<Monom>();
		Polinom[] answer = new Polinom[2];

		Monom big;// stores the biggest monom from the divident
		Monom small = other.getMonoms().get(other.getMonoms().size() - 1);// stores the biggest monom from the divisor
		Monom div;// stores the result when dividing the 2 biggest monoms
		Polinom rest = new Polinom(this.representation);// stores the rest, make copy of it, not
														// reference

		Polinom monomToPolinom;// we didn't implement substraction method for Monoms,so we need to conver the
								// result of the division of the monoms to a polinom

		do {
			big = rest.getMonoms().get(rest.getMonoms().size() - 1);
			div = big.div(small);
			monomToPolinom = new Polinom(div.getRepresentation());
			rest = rest.sub(monomToPolinom.mul(other));
			resMonoms.add(div);// with every step we get another monom from the final result
			if (other.getDegree() == 0 && rest.getRepresentation().equals("0"))
				break;// if we divide with a number then the degree will be 0 -> infinite loop
						// we need to break in order not to get an infinite loop
		} while (rest.getDegree() >= other.getDegree());

		Polinom quot = new Polinom(resMonoms);// create the quotient from the monoms we got
		answer[1] = rest;
		answer[0] = quot;
		return answer;
	}

}
