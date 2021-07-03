package src;

import java.util.ArrayList;

public class QuadraticMean implements MeanCalculator {
	public Double meanCalc(ArrayList<Double> v) {
		Double qu_sum = 0.0;
		for (Double e : v) {
			qu_sum += Math.pow(e, 2);
		}
		return Math.sqrt(qu_sum / v.size());
	}
}