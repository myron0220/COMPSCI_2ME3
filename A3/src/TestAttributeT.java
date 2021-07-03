/**
 * Author: Mingzhe Wang
 * Revised: Mar 25, 2021
 * 
 * Description: Test for AttributeT.
 */

package src;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestAttributeT 
{
	private IndicatorT[] SE2AA4_indicators;
	private IndicatorT[] SE2C03_indicators;
	private IndicatorT[] SE3A04_indicators;
	private IndicatorT[] Unused_indicators;
	private AttributeT se2aa4Attribute;
	private AttributeT se2c03Attribute;
	private AttributeT se3a04Attribute;
	private AttributeT unusedAttribute;

    @Before
   	public void setUp() {
   		SE2AA4_indicators = new IndicatorT[] {IndicatorT.math, IndicatorT.specEngKnow, IndicatorT.assumpt,
                                                     IndicatorT.suitableFund, IndicatorT.recogTheory, IndicatorT.modelSelect,
                                                     IndicatorT.estOutcomes, IndicatorT.desProcess, IndicatorT.desPrinciples,
                                                     IndicatorT.openEnded, IndicatorT.tools, IndicatorT.engInSoc,
                                                     IndicatorT.awarePEO};
      	SE2C03_indicators = new IndicatorT[] {IndicatorT.desPrinciples};
      	SE3A04_indicators = new IndicatorT[] {IndicatorT.standards, IndicatorT.healthSafety};
        // test with duplicate
      	Unused_indicators = new IndicatorT[] {IndicatorT.ideaGeneration, IndicatorT.ideaGeneration, IndicatorT.tools, IndicatorT.tools, IndicatorT.tools};
      	se2aa4Attribute = new AttributeT("Attribute of SE2AA4", SE2AA4_indicators);
   		se2c03Attribute = new AttributeT("Attribute of SE2C03", SE2C03_indicators);
   		se3a04Attribute = new AttributeT("Attribute of SE3A04", SE3A04_indicators);
   		unusedAttribute = new AttributeT("Attribute of UNUSED", Unused_indicators);
   	}

   	@After
   	public void tearDown() {
   		SE2AA4_indicators = null;
   		SE2C03_indicators = null;
   		SE3A04_indicators = null;
   		Unused_indicators = null;
   		se2aa4Attribute = null;
   		se2c03Attribute = null;
   		se3a04Attribute = null;
   		unusedAttribute = null;
   	}

   	@Test
   	public void testGetName() {
   		assertEquals("Attribute of SE2AA4", se2aa4Attribute.getName());
   		assertEquals("Attribute of SE2C03", se2c03Attribute.getName());
   		assertEquals("Attribute of SE3A04", se3a04Attribute.getName());
   		assertEquals("Attribute of UNUSED", unusedAttribute.getName());
   	}

   	@Test
   	public void testGetIndicators() {
   		Set<IndicatorT> se2aa4Exp = new HashSet<>(Arrays.asList(SE2AA4_indicators));
   		Set<IndicatorT> se2aa4Act = new HashSet<>(Arrays.asList(se2aa4Attribute.getIndicators()));
      assertTrue(se2aa4Exp.size() == se2aa4Attribute.getIndicators().length);
   		assertEquals(se2aa4Exp, se2aa4Act);

   		Set<IndicatorT> se2c03Exp = new HashSet<>(Arrays.asList(SE2C03_indicators));
   		Set<IndicatorT> se2c03Act = new HashSet<>(Arrays.asList(se2c03Attribute.getIndicators()));
      assertTrue(se2c03Exp.size() == se2c03Attribute.getIndicators().length);
   		assertEquals(se2c03Exp, se2c03Act);

   		Set<IndicatorT> se3a04Exp = new HashSet<>(Arrays.asList(SE3A04_indicators));
   		Set<IndicatorT> se3a04Act = new HashSet<>(Arrays.asList(se3a04Attribute.getIndicators()));
      assertTrue(se3a04Exp.size() == se3a04Attribute.getIndicators().length);
   		assertEquals(se3a04Exp, se3a04Act);

   		Set<IndicatorT> unusedExp = new HashSet<>(Arrays.asList(Unused_indicators));
   		Set<IndicatorT> unusedAct = new HashSet<>(Arrays.asList(unusedAttribute.getIndicators()));
      assertTrue(unusedExp.size() == unusedAttribute.getIndicators().length);
   		assertEquals(unusedExp, unusedAct);
   	}
}
