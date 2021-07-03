/**
 * Author: Mingzhe Wang
 * Revised: Mar 27, 2021
 * 
 * Description: Measures interface.
 */

package src;

public interface Measures {
	public double[] measures() throws UnsupportedOperationException;
	public double[] measures(IndicatorT ind) throws UnsupportedOperationException;
	public double[] measures(AttributeT att) throws UnsupportedOperationException;
}
