/**
 * Author: Mingzhe Wang
 * Revised: Mar 27, 2021
 * 
 * Description: AttributeT ADT class.
 */

package src;

import java.util.*;

public class AttributeT {
	private String name;
	private HashSet<IndicatorT> s;

	public AttributeT(String attribName, IndicatorT[] indicators) {
		this.name = attribName;
		this.s = new HashSet<IndicatorT>();
		for (IndicatorT ind : indicators) {
			this.s.add(ind);
		}
	}

	public String getName() {
		return name;
	}

	public IndicatorT[] getIndicators() {
		return s.toArray(new IndicatorT[s.size()]);
	}
}
