/**
 * Author: Mingzhe Wang
 * Revised: Mar 27, 2021
 * 
 * Description: Course ADT class.
 */

package src;

import java.util.*;

/**
 * @brief An ADT that represents a course.
 * @details The course is represented in a set of MapInd2LOsT, which is a local type of tuple 
 * 			of (one indicator, multiple (a set of) LO). <br>
 *			Note: Due to the behavior of constructor and addLO, there is a gurantee that there
 * 			will be not duplicate indicators in a course.
 */
public class CourseT implements Measures {

	// may need to consider equals() and hashCode()
	private class MapInd2LOsT { 

  		private final IndicatorT ind;
  		private final HashSet<LOsT> LOs;

  		public MapInd2LOsT(IndicatorT ind, HashSet<LOsT> LOs) {
    		this.ind = ind; 
    		this.LOs = LOs; 
  		}

  		public IndicatorT getInd() {
  			return ind;
  		}

  		public HashSet<LOsT> getLOs() {
  			return LOs;
  		}

  		// may need further consideration.
  		@Override
  		public boolean equals(Object o) {
  			if (o == this) return true;
        	if (!(o instanceof MapInd2LOsT)) {
            	return false;
        	}
        	MapInd2LOsT i2l = (MapInd2LOsT) o;
        	return Objects.equals(ind, i2l.getInd()) && Objects.equals(LOs, i2l.getLOs());
  		}

  		@Override
  		public int hashCode() {
  			return Objects.hash(ind, LOs);
  		}
	}

	private String name;
	private HashSet<MapInd2LOsT> m;

	private static double[] sumMeas(double[] a, double[] b) throws IllegalArgumentException {
		if ((a.length != 4) || (b.length != 4)) {
			throw new IllegalArgumentException("array's length must be 4.");
		}
		return new double[] {a[0] + b[0], a[1] + b[1], a[2] + b[2], a[3] + b[3]};
	}

	/**
   	* @brief Initializes a course object.
   	* @details  There couble be duplicate in the input, but the constructor should automatically delete duplicates.
   	* @param courseName The name of the course.
   	* @param indicators The indicators of the course.
   	*/
	public CourseT(String courseName, IndicatorT[] indicators) {
		this.name = courseName;
		this.m = new HashSet<MapInd2LOsT>();
		for (IndicatorT i : indicators) {
			MapInd2LOsT ele = new MapInd2LOsT(i, new HashSet<LOsT>());
			this.m.add(ele);
		}
	}

	/**
   	* @brief Get the name of the course object.
   	* @return Course name.
   	*/
	public String getName() {
		return name;
	}

	/**
   	* @brief Get all indicators of the course object.
   	* @return The seq of all indicators.
   	*/
	public IndicatorT[] getIndicators() {
		if (m.size() == 0) {
			return new IndicatorT[0];
		}
		IndicatorT[] indicators = new IndicatorT[m.size()];
		int i = 0;
		for (MapInd2LOsT s : m) {
			indicators[i] = s.getInd();
			i++;
		}
		return indicators;
	}

	/**
   	* @brief Get the LO of the course object based on the given indicator.
   	* @param indicator the indicator from which you want to find all LO.
   	* @return The seq of all LO that belongs to that indicator in this course.
   	*/
	public LOsT[] getLOs(IndicatorT indicator) {
		for (MapInd2LOsT s : m) {
			// can use "==" for comparing enums.
			if (s.getInd() == indicator) {
				HashSet<LOsT> curLOs = s.getLOs();
				return curLOs.toArray(new LOsT[curLOs.size()]);
			}
		}
		return new LOsT[0];
	}

	/**
   	* @brief Add a LO based on an indicator.
   	* @details If the given indicator exists in the course, add the LO.<br>
   	*		   Otherwise, do nothing.
   	* @param indicator the indicator you want to add the LO into.
   	* @param outcome the LO you want to add.
   	*/
	public void addLO(IndicatorT indicator, LOsT outcome) {
		HashSet<MapInd2LOsT> mNew = new HashSet<>();
		for (MapInd2LOsT s : m) {
			if (s.getInd() == indicator) {
				HashSet<LOsT> newLOs = new HashSet<>();
				newLOs.addAll(s.getLOs());
				newLOs.add(outcome);
				mNew.add(new MapInd2LOsT(indicator, newLOs));
			} else {
				mNew.add(s);
			}
		}
		this.m = mNew;
	}

	/**
   	* @brief Delete a LO based on an indicator.
   	* @details If the given indicator exists in the course, delete the corresponding LO if it exists.<br>
   	*		   Otherwise, do nothing.
   	* @param indicator the indicator you want to delete the LO from.
   	* @param outcome the LO you want to delete.
   	*/
	public void delLO(IndicatorT indicator, LOsT outcome) {
		HashSet<MapInd2LOsT> mNew = new HashSet<>();
		for (MapInd2LOsT s : m) {
			if (s.getInd() == indicator) {
				HashSet<LOsT> newLOs = new HashSet<>();
				newLOs.addAll(s.getLOs());
				newLOs.remove(outcome);
				mNew.add(new MapInd2LOsT(indicator, newLOs));
			} else {
				mNew.add(s);
			}
		}
		this.m = mNew;
	}

	/**
   	* @brief Determine if a tuple of one indicator and multiple LOs is a member of the course object.
   	* @details The indicator is an enum, so its equality shoulb be exact.<br>
   	*		   Two LOs are equal if their names are the same. That means in usage, you should avoid using
   	*		   the same name for two LOs with different data.
   	* @param indicator the indicator of the input tuple.
   	* @param outcomes the LOs of the input tuple.
   	* @return true iff both the indicator and the set of LO exists in the course object.
   	*/
	public boolean member(IndicatorT indicator, LOsT[] outcomes) {
		HashSet<LOsT> outcomesSet = new HashSet<>();
		for (LOsT e : outcomes) {
			outcomesSet.add(e);
		}
		for (MapInd2LOsT s : m) {
			if ((s.getInd() == indicator) && (s.getLOs().equals(outcomesSet))) {
				return true;
			}
		}
		return false;
	}

	/**
   	* @brief Determine if a tuple of one indicator and multiple LOs is a member of the course object.
	* @throw UnsupportedOperationException Because a course object cannot be measured without other references.
   	*/
	public double[] measures() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("CourseT cannot be measured directly.");
	}

	/**
   	* @brief Calculate the measure of a course bases on the given indicator.
   	* @details Assumption: For each indicator, there will be at least one LO. <br>
   	* 		   Note: The exact way of measure will be based on the state of Norm. <br>
   	*  		   In this case, <br>
   	*    	   if nInd is true, then sum and normalize the measures of all LOs belonged to the given indicator; <br>
   	*		   if nInd is false, then ONLY sum the measures of all LOs belonged to the given indicator. <br>
   	*		   Where, whether we normalize LOsT during measures will be based on nLOs.
   	* @param ind the indicator which the measure bases on.
   	* @return the result of the measure.
   	*/
	public double[] measures(IndicatorT ind) {
		if (this.getLOs(ind).length == 0) {
			return new double[] {0.0, 0.0, 0.0, 0.0};
		} else {
			double[] measureInd = new double[] {0.0, 0.0, 0.0, 0.0};
			for (LOsT o : this.getLOs(ind)) {
				measureInd = sumMeas(measureInd, o.measures());
			}
			if (Norm.getNInd()) {
				return Services.normal(measureInd);
			} else{
				return measureInd;
			}
		}
	}

	/**
   	* @brief Calculate the measure of a course bases on the given attribute.
   	* @details Assumption: For each indicator, there will be at least one LO. <br>
   	* 		   Note: The exact way of measure will be based on the state of Norm. <br>
   	*  		   In this case, <br>
   	*    	   if nAtt is true, then sum and normalize the results of course.measures(ind) for all indicators in the given attribute. <br>
   	*		   if nAtt is false, then ONLY sum the results of course.measures(ind) for all indicators in the given attribute. <br>
   	*		   Where, the behavior of course.measures(ind) will be based on nInd and nLOs.
   	* @param att the attribute which the measure bases on. It can be seen as a set of indicators.
   	* @return the result of the measure.
   	*/
	public double[] measures(AttributeT att) {
		if (att.getIndicators().length == 0) {
			return new double[] {0.0, 0.0, 0.0, 0.0};
		} else {
			double[] measureInd = new double[] {0.0, 0.0, 0.0, 0.0};
			for (IndicatorT i : att.getIndicators()) {
				measureInd = sumMeas(measureInd, this.measures(i));
			}
			if (Norm.getNAtt()) {
				return Services.normal(measureInd);
			} else{
				return measureInd;
			}
		}
	}
}
