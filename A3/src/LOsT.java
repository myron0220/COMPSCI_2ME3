/**
 * Author: Mingzhe Wang
 * Revised: Mar 27, 2021
 * 
 * Description: LOsT ADT class.
 */

package src;

import java.util.Objects;

public class LOsT implements Measures {

	private String name;
	private int n_blw;
	private int n_mrg;
	private int n_mts;
	private int n_exc;

	public LOsT(String topic, int nblw, int nmrg, int nmts, int nexc) throws IllegalArgumentException {
		if ((nblw < 0) || (nmrg < 0) || (nmts < 0) || (nexc < 0)) {
			throw new IllegalArgumentException("Students number cannot be negative.");
		}
		if ((nblw == 0) && (nmrg == 0) && (nmts == 0) && (nexc == 0)) {
			throw new IllegalArgumentException("Learning outcomes is nonsense if all the inputs are 0.");
		}
		this.name = topic;
		this.n_blw = nblw;
		this.n_mrg = nmrg;
		this.n_mts = nmts;
		this.n_exc = nexc;
	}

	public String getName() {
		return name;
	}

	public boolean equals(LOsT o) {
		return name.equals(o.getName());
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
        if (!(o instanceof LOsT)) {
            return false;
        }
        LOsT lost = (LOsT) o;
        return name.equals(lost.getName());
	}

	// may also override hashcode()
	@Override
    public int hashCode() {
        return Objects.hash(name);
    }

	public double[] measures() {
		double[] measures = {Double.valueOf(n_blw), Double.valueOf(n_mrg), Double.valueOf(n_mts), Double.valueOf(n_exc)};
		if (!(Norm.getNLOs())) {
			return measures;
		} else {
			return Services.normal(measures);
		}
	}

	public double[] measures(IndicatorT ind) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("LOsT cannot be measured by IndicatorT.");
	}

	public double[] measures(AttributeT att) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("LOsT cannot be measured by AttributeT.");
	}
}
