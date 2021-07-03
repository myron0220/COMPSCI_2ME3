/**
 * Author: Mingzhe Wang
 * Revised: Mar 27, 2021
 * 
 * Description: Test for ProgramT.
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;

public class TestProgramT
{
      private CourseT SE2AA4;
      private CourseT SE2C03;
      private CourseT SE3A04;
      private AttributeT design;
      ProgramT softwareEngineering;

      private static double[] sumMeas(double[] a, double[] b) throws IllegalArgumentException {
         if ((a.length != 4) || (b.length != 4)) {
            throw new IllegalArgumentException("array's length must be 4.");
         }
         return new double[] {a[0] + b[0], a[1] + b[1], a[2] + b[2], a[3] + b[3]};
      }

      private static void assertEqualsDoubleArray(double[] expecteds, double[] actuals, double delta) throws IllegalArgumentException, AssertionError {
         if (expecteds.length != actuals.length) {
            throw new IllegalArgumentException("Compared double array don't have the same length!");
         }
         for (int i = 0; i < expecteds.length; i++) {
            assertEquals(expecteds[i], actuals[i], delta);
         }
      }

      @Before
   	public void setUp() {
         IndicatorT[] SE2AA4_indicators = new IndicatorT[] {IndicatorT.math, IndicatorT.specEngKnow, IndicatorT.assumpt,
                                                     IndicatorT.suitableFund, IndicatorT.recogTheory, IndicatorT.modelSelect,
                                                     IndicatorT.estOutcomes, IndicatorT.desProcess, IndicatorT.desPrinciples,
                                                     IndicatorT.openEnded, IndicatorT.tools, IndicatorT.engInSoc,
                                                     IndicatorT.awarePEO};
         IndicatorT[] SE2C03_indicators = new IndicatorT[] {IndicatorT.desPrinciples};
         IndicatorT[] SE3A04_indicators = new IndicatorT[] {IndicatorT.standards, IndicatorT.healthSafety};
         IndicatorT[] Unused_indicators = new IndicatorT[] {IndicatorT.ideaGeneration, IndicatorT.tools};
         CourseT SE2AA4 = new CourseT("Software Engineering Design 1", SE2AA4_indicators);
         CourseT SE2C03 = new CourseT("Data Structures and Algorithms", SE2C03_indicators);
         CourseT SE3A04 = new CourseT("Software Design II Large Scale Systems", SE3A04_indicators);
         SE2AA4.addLO(IndicatorT.desProcess, new LOsT("Recog and follow eng des process", 5, 16, 90, 60));
         SE2AA4.addLO(IndicatorT.desProcess, new LOsT("Modularization and interface design", 20, 8, 80, 30));
         SE2AA4.addLO(IndicatorT.desPrinciples, new LOsT("Software qualities", 60, 40, 50, 50));
         SE2AA4.addLO(IndicatorT.openEnded, new LOsT("Complete design, implement and test for a set of modules", 5, 4, 3, 2));
         SE2C03.addLO(IndicatorT.desPrinciples, new LOsT("Identify time/space trade-offs", 0, 4, 4, 22));
         SE3A04.addLO(IndicatorT.standards, new LOsT("Select among design methodologies", 80, 150, 97, 110));      
         SE3A04.addLO(IndicatorT.standards, new LOsT("State the design principles", 70, 120, 85, 130));
         SE3A04.addLO(IndicatorT.standards, new LOsT("Evaluate design solution against requirements", 200, 80, 100, 4));      
         SE3A04.addLO(IndicatorT.healthSafety, new LOsT("Organize and plan the development of a software system", 80, 150, 97, 110));
         SE3A04.addLO(IndicatorT.healthSafety, new LOsT("Select among development doc templates", 50, 50, 20, 30));
         SE3A04.addLO(IndicatorT.healthSafety, new LOsT("Assess the impact of a requirement on the architecture", 200, 80, 100, 4));
         design = new AttributeT("Design", new IndicatorT[] {IndicatorT.desProcess, IndicatorT.desPrinciples,
                                                                     IndicatorT.openEnded, IndicatorT.ideaGeneration,
                                                                     IndicatorT.standards});
         softwareEngineering = new ProgramT();
         softwareEngineering.add(SE2AA4);
         softwareEngineering.add(SE2C03);
         softwareEngineering.add(SE3A04);
   	}

   	@After
   	public void tearDown() {
         SE2AA4 = null;
         SE2C03 = null;
         SE3A04 = null;
         design = null;
   	}

      @Test(expected = UnsupportedOperationException.class)
      public void testMeasures1() {
         softwareEngineering.measures();
      }

      @Test(expected = UnsupportedOperationException.class)
      public void testMeasures2() {
         softwareEngineering.measures(IndicatorT.healthSafety);
      }

   	@Test
   	public void testMeasures3() {
         double[] sumArray = new double[] {0.0, 0.0, 0.0, 0.0};
         for (CourseT c : softwareEngineering) {
            sumArray = sumMeas(sumArray, c.measures(design));
         }
         assertEqualsDoubleArray(Services.normal(sumArray), softwareEngineering.measures(design), 1e-6);
   	}
}
