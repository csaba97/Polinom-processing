package model;

import java.util.Comparator;

/**
 * Comparator Class for Monom Class, comparing by degree
 */
public class DegreeComparator implements Comparator<Monom> {
	/**
	 * compare the two monoms by their degree
	 */
	public int compare(Monom a, Monom b) {
		return (a.getDegree() - b.getDegree());
	}
}
