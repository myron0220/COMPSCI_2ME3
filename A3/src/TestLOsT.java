/**
 * Author: Mingzhe Wang
 * Revised: Mar 25, 2021
 * 
 * Description: Test for LOsT.
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class TestLOsT
{
	private LOsT lO1;
	private LOsT lO2;
	private LOsT lO3;
	private LOsT lO4;

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
   		Norm.setNorms(false, false, false);
   		lO1 = new LOsT("topic 1", 0, 0, 0, 1);
   		lO2 = new LOsT("topic 2", 89, 10, 6, 0);
   	}

   	@After
   	public void tearDown() {
   		Norm.setNorms(false, false, false);
   		lO1 = null;
   		lO2 = null;
   	}

   	@Test(expected = IllegalArgumentException.class)
   	public void testLOsTExcept1Cond1() {
   		lO1 = new LOsT("topic 1", -1, 0, 0, 0);
   	}

   	@Test(expected = IllegalArgumentException.class)
   	public void testLOsTExcept1Cond2() {
   		lO1 = new LOsT("topic 1", 110, -7, 1, 2);
   	}

   	@Test(expected = IllegalArgumentException.class)
   	public void testLOsTExcept1Cond3() {
   		lO1 = new LOsT("topic 1", 21, 2, -100, 90);
   	}

   	@Test(expected = IllegalArgumentException.class)
   	public void testLOsTExcept1Cond4() {
   		lO1 = new LOsT("topic 1", 12, 84, 0, -7);
   	}

   	@Test(expected = IllegalArgumentException.class)
   	public void testLOsTExcept2() {
   		lO1 = new LOsT("topic 1", 1, 1, 10, -92);
   	}

   	@Test
   	public void testGetName() {
   		assertEquals("topic 1", lO1.getName());
   		assertEquals("topic 2", lO2.getName());
   	}

   	@Test
   	public void testEquals() {
   		LOsT lO3 = new LOsT("topic 1", 12, 90, 12, 10);
   		assertTrue(lO1.equals(lO3));
   		LOsT lO4 = new LOsT("topic 2", 12, 213, 3, 900);
   		assertTrue(lO2.equals(lO4));
   	}

   	@Test
   	public void testMeasuresBranch1() {
		TestLOsT.assertEqualsDoubleArray(new double[] {0.0, 0.0, 0.0, 1.0}, lO1.measures(), 1e-9);
		TestLOsT.assertEqualsDoubleArray(new double[] {89.0, 10.0, 6.0, 0.0}, lO2.measures(), 1e-9);
   	}

   	@Test
   	public void testMeasuresBranch2() {
   		Norm.setNLOs(true);
   		TestLOsT.assertEqualsDoubleArray(new double[] {0.0, 0.0, 0.0, 1.0}, lO1.measures(), 1e-9);
   		TestLOsT.assertEqualsDoubleArray(new double[] {0.8476190476190476, 0.09523809523809523, 0.05714285714285714, 0.0}, lO2.measures(), 1e-9);
   	}
}
