package src;

import java.util.ArrayList;

public class HarmonicMean implements MeanCalculator {
	public Double meanCalc(ArrayList<Double> v) {
		Double de_sum = 0.0;
		for (Double e : v) {
			de_sum += 1.0 / e;
		}
		return v.size() / de_sum;
	}
}