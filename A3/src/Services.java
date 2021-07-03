/**
 * Author: Mingzhe Wang
 * Revised: Mar 27, 2021
 * 
 * Description: Services abstract object.
 */

package src;

public class Services {

	public static double[] normal(double[] v) {
		double[] normSeq = v.clone();
		double sumV = sum(v);
		for (int i = 0; i < normSeq.length; i++) {
			normSeq[i] = normSeq[i] / sumV;
		}
		return normSeq;
	}

	public static double sum(double[] seq) {
		double ans = 0.0;
		for (double e : seq) {
			ans += e;
		}
		return ans;
	}
}
