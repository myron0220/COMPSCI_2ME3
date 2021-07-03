/**
 * Author: Mingzhe Wang
 * Revised: Mar 26, 2021
 * 
 * Description: Test for courseT.
 */

// need to consider duplicate indicator


package src;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestCourseT
{
	private CourseT courseA;
	private IndicatorT[] courseA_indicators;
	private CourseT courseB;
	private IndicatorT[] courseB_indicators;

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
   		courseA_indicators = new IndicatorT[] {IndicatorT.math, IndicatorT.specEngKnow, IndicatorT.math, IndicatorT.assumpt};
     	   courseA = new CourseT("Indicator with Duplicate Test", courseA_indicators);
     	   courseB_indicators = new IndicatorT[] {};
     	   courseB = new CourseT("Indicator is Empty Test", courseB_indicators);
   	}

   	@After
   	public void tearDown() {
   		courseA_indicators = null;
   		courseA = null;
   		courseB_indicators = null;
   		courseB = null;
   	}

   	@Test
   	public void testGetName() {
   		assertEquals("Indicator with Duplicate Test", courseA.getName());
   		assertEquals("Indicator is Empty Test", courseB.getName());
   	}

   	@Test
   	public void testGetIndicators() throws AssertionError {
   		IndicatorT[] actualArrayA = courseA.getIndicators();
   		// test design check
   		// actualArrayA = new IndicatorT[] {IndicatorT.math, IndicatorT.specEngKnow, IndicatorT.math, IndicatorT.assumpt};
   		HashSet<IndicatorT> actualSetA = new HashSet<>(Arrays.asList(actualArrayA));
   		// test if removed duplicate in the constructor.
   		assertTrue("duplicate test FAIL", actualArrayA.length == actualSetA.size());
   		IndicatorT[] expectedArrayForMemTestA = new IndicatorT[] {IndicatorT.math, IndicatorT.specEngKnow, IndicatorT.assumpt};
   		HashSet<IndicatorT> expectedSetForMemTestA = new HashSet<>(Arrays.asList(expectedArrayForMemTestA));
   		// test member.
   		assertEquals("member test FAIL", expectedSetForMemTestA, actualSetA);

   		IndicatorT[] actualArrayB = courseB.getIndicators();
   		// test design check
   		// actualArrayB = new IndicatorT[] {IndicatorT.math, IndicatorT.specEngKnow, IndicatorT.math, IndicatorT.assumpt};
   		HashSet<IndicatorT> actualSetB = new HashSet<>(Arrays.asList(actualArrayB));
   		// test if removed duplicate in the constructor.
   		assertTrue("duplicate test FAIL", actualArrayB.length == actualSetB.size());
   		IndicatorT[] expectedArrayForMemTestB = new IndicatorT[] {};
   		HashSet<IndicatorT> expectedSetForMemTestB = new HashSet<>(Arrays.asList(expectedArrayForMemTestB));
   		// test member.
   		assertEquals("member test FAIL", expectedSetForMemTestB, actualSetB);
	}

	// Assumption ?: LOsT with the same name but different seq double numbers.
   	@Test
   	public void testAddLO() {
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 1", 5, 16, 90, 60));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 2", 20, 8, 80, 30));
   		courseA.addLO(IndicatorT.math, new LOsT("LOsT 3", 22, 32, 12, 3));
   		courseA.addLO(IndicatorT.assumpt, new LOsT("LOsT 4", 1, 220, 0, 0));
   		HashSet<LOsT> expectedSetA = new HashSet<LOsT>();
   		expectedSetA.add(new LOsT("LOsT 1", 5, 16, 90, 60));
   		expectedSetA.add(new LOsT("LOsT 2", 20, 8, 80, 30));
   		HashSet<LOsT> actualSetA = new HashSet<> (Arrays.asList(courseA.getLOs(IndicatorT.specEngKnow)));
   		assertEquals(expectedSetA, actualSetA);
   		expectedSetA.clear();
   		expectedSetA.add(new LOsT("LOsT 3", 22, 32, 12, 3));
   		actualSetA = new HashSet<> (Arrays.asList(courseA.getLOs(IndicatorT.math)));
   		assertEquals(expectedSetA, actualSetA);
   		expectedSetA.clear();
   		expectedSetA.add(new LOsT("LOsT 4", 1, 220, 0, 0));
   		actualSetA = new HashSet<> (Arrays.asList(courseA.getLOs(IndicatorT.assumpt)));
   		assertEquals(expectedSetA, actualSetA);

   		courseB.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 10", 5323, 16, 90, 60));
   		assertTrue(courseB.getLOs(IndicatorT.specEngKnow).length == 0);
   	}

   	@Test
   	public void testGetLOs() {
   		courseA.addLO(IndicatorT.assumpt, new LOsT("LOsT 4", 1, 220, 0, 0));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 1", 5, 16, 90, 60));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 2", 20, 8, 80, 30));
   		courseA.addLO(IndicatorT.math, new LOsT("LOsT 3", 22, 32, 12, 3));
   		LOsT[] actualArrayA = courseA.getLOs(IndicatorT.specEngKnow);
   		HashSet<LOsT> actualSetA = new HashSet<>(Arrays.asList(actualArrayA));
   		LOsT[] expectedArrayForMemTestA = new LOsT[] {new LOsT("LOsT 1", 5, 16, 90, 60), new LOsT("LOsT 2", 20, 8, 80, 30)};
   		HashSet<LOsT> expectedSetForMemTestA = new HashSet<>(Arrays.asList(expectedArrayForMemTestA));
   		// test duplicate.
   		assertTrue("duplicate test FAIL", actualArrayA.length == actualSetA.size());
   		// test member.
   		assertEquals("member test FAIL", expectedSetForMemTestA, actualSetA);

   		courseB.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 10", 5323, 16, 90, 60));
   		assertTrue(courseB.getLOs(IndicatorT.specEngKnow).length == 0);
   	}

   	@Test
   	public void testDelLO() {
   		courseA.addLO(IndicatorT.math, new LOsT("LOsT 3", 22, 32, 12, 3));
   		courseA.addLO(IndicatorT.assumpt, new LOsT("LOsT 4", 1, 220, 0, 0));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 1", 5, 16, 90, 60));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 2", 20, 8, 80, 30));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 5", 0, 80, 82, 30));

   		LOsT[] prevArrayA = courseA.getLOs(IndicatorT.assumpt);
   		courseA.delLO(IndicatorT.assumpt, new LOsT("LOsT 6", 3, 8, 2, 30));
   		assertEquals(prevArrayA, courseA.getLOs(IndicatorT.assumpt));

   		prevArrayA = courseA.getLOs(IndicatorT.specEngKnow);
   		courseA.delLO(IndicatorT.specEngKnow, new LOsT("LOsT 5", 0, 80, 82, 30));
   		courseA.delLO(IndicatorT.specEngKnow, new LOsT("LOsT 1", 5, 16, 90, 60));
   		courseA.delLO(IndicatorT.specEngKnow, new LOsT("LOsT 2", 20, 8, 80, 30));
   		assertTrue(courseA.getLOs(IndicatorT.specEngKnow).length == 0);
   	}

   	@Test
   	public void testMember() {
   		courseA.addLO(IndicatorT.math, new LOsT("LOsT 3", 22, 32, 12, 3));
   		courseA.addLO(IndicatorT.assumpt, new LOsT("LOsT 4", 1, 220, 0, 0));
   		courseA.addLO(IndicatorT.assumpt, new LOsT("LOsT 6", 9, 2, 0, 0));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 1", 5, 16, 90, 60));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 2", 20, 8, 80, 30));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 5", 0, 80, 82, 30));
   		LOsT[] specEngKnowmArray = new LOsT[] {new LOsT("LOsT 2", 20, 8, 80, 30), new LOsT("LOsT 1", 5, 16, 90, 60), new LOsT("LOsT 5", 0, 80, 82, 30)};
   		assertTrue(courseA.member(IndicatorT.specEngKnow, specEngKnowmArray));
   		courseA.delLO(IndicatorT.specEngKnow, new LOsT("LOsT 5", 0, 80, 82, 30));
   		assertFalse(courseA.member(IndicatorT.specEngKnow, specEngKnowmArray));
   		courseA.delLO(IndicatorT.specEngKnow, new LOsT("LOsT 2", 20, 8, 80, 30));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 5", 0, 80, 82, 30));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 2", 20, 8, 80, 30));
   		assertTrue(courseA.member(IndicatorT.specEngKnow, specEngKnowmArray));

   		assertFalse(courseA.member(IndicatorT.awarePEO, specEngKnowmArray));
   		assertFalse(courseA.member(IndicatorT.estOutcomes, new LOsT[0]));
   	}

   	@Test(expected = UnsupportedOperationException.class)
   	public void testMeasures1() {
   		courseA.measures();
   	}

   	// getLOs(ind) == []
   	@Test
   	public void testMeasures2Branch1() {
   		Norm.setNorms(false, false, false);
   		courseA.addLO(IndicatorT.math, new LOsT("LOsT 3", 22, 32, 12, 3));
   		courseA.addLO(IndicatorT.assumpt, new LOsT("LOsT 4", 1, 220, 0, 0));
   		courseA.addLO(IndicatorT.assumpt, new LOsT("LOsT 6", 9, 2, 0, 0));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 1", 5, 16, 90, 60));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 2", 20, 8, 80, 30));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 5", 0, 80, 82, 30));
   		TestCourseT.assertEqualsDoubleArray(new double[] {0.0, 0.0, 0.0, 0.0}, courseA.measures(IndicatorT.openEnded), 1e-9);
   	}

   	// course.getLOs(ind) != []
   	// NInd = true
   	@Test
   	public void testMeasures2Branch2() {
   		Norm.setNorms(false, false, false);
   		Norm.setNInd(true);
   		courseA.addLO(IndicatorT.math, new LOsT("LOsT 3", 22, 32, 12, 3));
   		courseA.addLO(IndicatorT.assumpt, new LOsT("LOsT 4", 1, 220, 0, 0));
   		courseA.addLO(IndicatorT.assumpt, new LOsT("LOsT 6", 9, 2, 0, 0));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 1", 5, 16, 90, 60));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 2", 20, 8, 80, 30));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 5", 0, 80, 82, 30));
   		// NInd = true, NLOs = false;
   		double[] actual = courseA.measures(IndicatorT.specEngKnow);
   		double[] expected = new double[] {0.0499001996007984, 0.20758483033932135, 0.5029940119760479, 0.23952095808383234};
   		TestCourseT.assertEqualsDoubleArray(expected, actual, 1e-6);
   		// NInd = true, NLOs = true;
   		Norm.setNLOs(true);
   		actual = courseA.measures(IndicatorT.specEngKnow);
   		expected = new double[] {0.05805577, 0.18940164, 0.51103642, 0.24150617};
   		TestCourseT.assertEqualsDoubleArray(expected, actual, 1e-6);
   	}

   	// course.getLOs(ind) != []
   	// NInd = false
   	@Test
   	public void testMeasures2Branch3() {
   		Norm.setNorms(false, false, false);
   		Norm.setNInd(false);
   		courseA.addLO(IndicatorT.math, new LOsT("LOsT 3", 22, 32, 12, 3));
   		courseA.addLO(IndicatorT.assumpt, new LOsT("LOsT 4", 1, 220, 0, 0));
   		courseA.addLO(IndicatorT.assumpt, new LOsT("LOsT 6", 9, 2, 0, 0));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 1", 5, 16, 90, 60));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 2", 20, 8, 80, 30));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 5", 0, 80, 82, 30));
   		// NInd = false, NLOs = false;
   		double[] actual = courseA.measures(IndicatorT.assumpt);
   		double[] expected = new double[] {10.0, 222.0, 0.0, 0.0};
   		TestCourseT.assertEqualsDoubleArray(expected, actual, 1e-6);
   		// NInd = false, NLOs = true;
   		Norm.setNLOs(true);
   		actual = courseA.measures(IndicatorT.assumpt);
   		expected = new double[] {0.82270671, 1.17729329, 0.0, 0.0};
   		TestCourseT.assertEqualsDoubleArray(expected, actual, 1e-6);
   	}

   	// att.getIndicators() == []
   	@Test
   	public void testMeasures3Branch1() {
   		Norm.setNorms(false, false, false);
   		courseA.addLO(IndicatorT.math, new LOsT("LOsT 3", 22, 32, 12, 3));
   		courseA.addLO(IndicatorT.assumpt, new LOsT("LOsT 4", 1, 220, 0, 0));
   		courseA.addLO(IndicatorT.assumpt, new LOsT("LOsT 6", 9, 2, 0, 0));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 1", 5, 16, 90, 60));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 2", 20, 8, 80, 30));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 5", 0, 80, 82, 30));
   		AttributeT emptyAtt = new AttributeT("empty att", new IndicatorT[] {});
   		TestCourseT.assertEqualsDoubleArray(new double[] {0.0, 0.0, 0.0, 0.0}, courseA.measures(emptyAtt), 1e-6);
   	}

   	// att.getIndicators() != []
   	// NAtt = true
   	@Test
   	public void testMeasures3Branch2() {
   		courseA.addLO(IndicatorT.math, new LOsT("LOsT 3", 22, 32, 12, 3));
   		courseA.addLO(IndicatorT.assumpt, new LOsT("LOsT 4", 1, 220, 0, 0));
   		courseA.addLO(IndicatorT.assumpt, new LOsT("LOsT 6", 9, 2, 0, 0));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 1", 5, 16, 90, 60));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 2", 20, 8, 80, 30));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 5", 0, 80, 82, 30));
   		AttributeT att1 = new AttributeT("att1", new IndicatorT[] {IndicatorT.specEngKnow, IndicatorT.assumpt});

   		// NInd = false, NLOs = false
   		Norm.setNorms(false, false, false);
   		Norm.setNAtt(true);
   		Norm.setNInd(false);
   		Norm.setNLOs(false);
   		double[] actual = courseA.measures(att1);
   		double[] expected = new double[4];
   		for (int i = 0; i < 4; i ++) {
   			expected[i] = courseA.measures(IndicatorT.assumpt)[i] + courseA.measures(IndicatorT.specEngKnow)[i];
   		}
   		// normal
   		double sum = 0;
   		for (int i = 0; i < 4; i ++) {
   			sum += expected[i];
   		}
   		for (int i = 0; i < 4; i ++) {
   			expected[i] = expected[i] / sum;
   		}
   		TestCourseT.assertEqualsDoubleArray(expected, courseA.measures(att1), 1e-6);

   		// NInd = false, NLOs = true
   		Norm.setNorms(false, false, false);
   		Norm.setNAtt(true);
   		Norm.setNInd(false);
   		Norm.setNLOs(true);
   		actual = courseA.measures(att1);
   		expected = new double[4];
   		for (int i = 0; i < 4; i ++) {
   			expected[i] = courseA.measures(IndicatorT.assumpt)[i] + courseA.measures(IndicatorT.specEngKnow)[i];
   		}
   		// normal
   		sum = 0;
   		for (int i = 0; i < 4; i ++) {
   			sum += expected[i];
   		}
   		for (int i = 0; i < 4; i ++) {
   			expected[i] = expected[i] / sum;
   		}
   		TestCourseT.assertEqualsDoubleArray(expected, courseA.measures(att1), 1e-6);

   		// NInd = true, NLOs = false
   		Norm.setNorms(false, false, false);
   		Norm.setNAtt(true);
   		Norm.setNInd(true);
   		Norm.setNLOs(false);
   		actual = courseA.measures(att1);
   		expected = new double[4];
   		for (int i = 0; i < 4; i ++) {
   			expected[i] = courseA.measures(IndicatorT.assumpt)[i] + courseA.measures(IndicatorT.specEngKnow)[i];
   		}
   		// normal
   		sum = 0;
   		for (int i = 0; i < 4; i ++) {
   			sum += expected[i];
   		}
   		for (int i = 0; i < 4; i ++) {
   			expected[i] = expected[i] / sum;
   		}
   		TestCourseT.assertEqualsDoubleArray(expected, courseA.measures(att1), 1e-6);

   		// NInd = true, NLOs = true
   		Norm.setNorms(false, false, false);
   		Norm.setNAtt(true);
   		Norm.setNInd(true);
   		Norm.setNLOs(true);
   		actual = courseA.measures(att1);
   		expected = new double[4];
   		for (int i = 0; i < 4; i ++) {
   			expected[i] = courseA.measures(IndicatorT.assumpt)[i] + courseA.measures(IndicatorT.specEngKnow)[i];
   		}
   		// normal
   		sum = 0;
   		for (int i = 0; i < 4; i ++) {
   			sum += expected[i];
   		}
   		for (int i = 0; i < 4; i ++) {
   			expected[i] = expected[i] / sum;
   		}
   		TestCourseT.assertEqualsDoubleArray(expected, courseA.measures(att1), 1e-6);
   	}

   	// att.getIndicators() != []
   	// NAtt = false
   	@Test
   	public void testMeasures3Branch3() {
   		courseA.addLO(IndicatorT.math, new LOsT("LOsT 3", 22, 32, 12, 3));
   		courseA.addLO(IndicatorT.assumpt, new LOsT("LOsT 4", 1, 220, 0, 0));
   		courseA.addLO(IndicatorT.assumpt, new LOsT("LOsT 6", 9, 2, 0, 0));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 1", 5, 16, 90, 60));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 2", 20, 8, 80, 30));
   		courseA.addLO(IndicatorT.specEngKnow, new LOsT("LOsT 5", 0, 80, 82, 30));
   		AttributeT att1 = new AttributeT("att1", new IndicatorT[] {IndicatorT.specEngKnow, IndicatorT.assumpt});

   		// NInd = false, NLOs = false
   		Norm.setNorms(false, false, false);
   		Norm.setNAtt(false);
   		Norm.setNInd(false);
   		Norm.setNLOs(false);
   		double[] actual = courseA.measures(att1);
   		double[] expected = new double[4];
   		for (int i = 0; i < 4; i ++) {
   			expected[i] = courseA.measures(IndicatorT.assumpt)[i] + courseA.measures(IndicatorT.specEngKnow)[i];
   		}
   		TestCourseT.assertEqualsDoubleArray(expected, courseA.measures(att1), 1e-6);

   		// NInd = false, NLOs = true
   		Norm.setNorms(false, false, false);
   		Norm.setNAtt(false);
   		Norm.setNInd(false);
   		Norm.setNLOs(true);
   		actual = courseA.measures(att1);
   		expected = new double[4];
   		for (int i = 0; i < 4; i ++) {
   			expected[i] = courseA.measures(IndicatorT.assumpt)[i] + courseA.measures(IndicatorT.specEngKnow)[i];
   		}
   		TestCourseT.assertEqualsDoubleArray(expected, courseA.measures(att1), 1e-6);

   		// NInd = true, NLOs = false
   		Norm.setNorms(false, false, false);
   		Norm.setNAtt(false);
   		Norm.setNInd(true);
   		Norm.setNLOs(false);
   		actual = courseA.measures(att1);
   		expected = new double[4];
   		for (int i = 0; i < 4; i ++) {
   			expected[i] = courseA.measures(IndicatorT.assumpt)[i] + courseA.measures(IndicatorT.specEngKnow)[i];
   		}
   		TestCourseT.assertEqualsDoubleArray(expected, courseA.measures(att1), 1e-6);

   		// NInd = true, NLOs = true
   		Norm.setNorms(false, false, false);
   		Norm.setNAtt(false);
   		Norm.setNInd(true);
   		Norm.setNLOs(true);
   		actual = courseA.measures(att1);
   		expected = new double[4];
   		for (int i = 0; i < 4; i ++) {
   			expected[i] = courseA.measures(IndicatorT.assumpt)[i] + courseA.measures(IndicatorT.specEngKnow)[i];
   		}
   		TestCourseT.assertEqualsDoubleArray(expected, courseA.measures(att1), 1e-6);
   	}
}
