/**
 * Author: Mingzhe Wang
 * Revised: Mar 27, 2021
 * 
 * Description: ProgramT ADT class.
 */

package src;

import java.util.HashSet;

public class ProgramT extends HashSet<CourseT> implements Measures {
	// private HashSet<CourseT> s;

	public double[] measures() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("ProgramT cannot be measured directly.");
	}

	public double[] measures(IndicatorT ind) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("ProgramT cannot be measured by IndicatorT.");
	}

	public double[] measures(AttributeT att) {
		double[] result = new double[] {0.0, 0.0, 0.0, 0.0};
		for (CourseT c : this) {
			result = sumMeas(result, c.measures(att));
		}
		return Services.normal(result);
	}

	private static double[] sumMeas(double[] a, double[] b) throws IllegalArgumentException {
		if ((a.length != 4) || (b.length != 4)) {
			throw new IllegalArgumentException("array's length must be 4.");
		}
		return new double[] {a[0] + b[0], a[1] + b[1], a[2] + b[2], a[3] + b[3]};
	}

}