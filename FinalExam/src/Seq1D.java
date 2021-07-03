package src;

import java.util.ArrayList;

public class Seq1D {
	private ArrayList<Double> s;
	private MeanCalculator meanCalculator;

	public Seq1D(ArrayList<Double> x, MeanCalculator m) throws IllegalArgumentException {
		if (x.size() == 0) {
			throw new IllegalArgumentException("Can not be empty list");
		}

		this.s = x;
		this.meanCalculator = m;
	}

	public void setMeanCalculator(MeanCalculator m) {
		this.meanCalculator = m;
	}

	public Double mean() {
		return meanCalculator.meanCalc(s);
	}
}