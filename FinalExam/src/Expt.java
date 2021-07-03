/**
 *Author: S. Smith
 *Revised: Apr 27, 2021
 * 
 *Description: Exam code examples.  The purpose of these examples is to
 *exercise the  module interfaces for the exam.  If there are syntax errors when
 *compiling this file, or odd output when running it, this is a sign
 *that something has gone wrong with the implementation.  That is, the
 *code doesn't match the expected interface given in the MIS.
 */

package src;

import java.util.ArrayList;
import java.util.Arrays;

public class Expt
{
   public static void main(String[] args) {

      MeanCalculator mharm = new HarmonicMean();
      MeanCalculator mquad = new QuadraticMean();      
      // Seq1D s = new Seq1D(new ArrayList<Double>(Arrays.asList(2.0, 2.0, 1.0, 1.0)), mharm);
      // Seq1D s = new Seq1D(new ArrayList<Double>(Arrays.asList(1.0, 2.0, 4.0)), mharm);
      Seq1D s = new Seq1D(new ArrayList<Double>(Arrays.asList(1.0, 2.0, 3.0)), mharm);
      System.out.println("Harmonic Mean: " + s.mean());
      s.setMeanCalculator(mquad);
      System.out.println("Quadratic Mean: " + s.mean());      

  }
}

