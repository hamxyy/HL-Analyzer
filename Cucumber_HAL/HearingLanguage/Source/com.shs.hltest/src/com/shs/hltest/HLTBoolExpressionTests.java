package com.shs.hltest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.shs.hl.generator.halsa.struct.HLTBoolExpression;
import com.shs.hl.generator.halsa.struct.HLTCompareExpression;
import com.shs.hl.generator.halsa.struct.HLTCompareRelation;
import com.shs.hl.generator.halsa.struct.HLTConstantExpression;

public class HLTBoolExpressionTests
{

	@Test
	public void When_bool_and_bool_then_equivalent_works_correctly()
	{
		HLTConstantExpression constFalse = HLTConstantExpression.alwaysFalse();
		HLTConstantExpression constTrue = HLTConstantExpression.alwaysTrue();

		HLTBoolExpression trueAndTrue = constTrue.and(constTrue);
		HLTBoolExpression trueAndFalse = constTrue.and(constFalse);
		HLTBoolExpression falseAndFalse = constFalse.and(constFalse);
		HLTBoolExpression falseAndTrue = constFalse.and(constTrue);

		assertTrue(trueAndTrue.equivalent(constTrue));
		assertTrue(falseAndFalse.equivalent(constFalse));
		assertTrue(trueAndFalse.equivalent(falseAndTrue));
	}

	@Test
	public void When_bool_or_bool_then_equivalent_works_correctly()
	{
		HLTConstantExpression constFalse = HLTConstantExpression.alwaysFalse();
		HLTConstantExpression constTrue = HLTConstantExpression.alwaysTrue();

		HLTBoolExpression trueOrTrue = constTrue.or(constTrue);
		HLTBoolExpression trueOrFalse = constTrue.or(constFalse);
		HLTBoolExpression falseOrFalse = constFalse.or(constFalse);
		HLTBoolExpression falseOrTrue = constFalse.or(constTrue);

		assertTrue(trueOrTrue.equivalent(constTrue));
		assertTrue(falseOrFalse.equivalent(constFalse));
		assertTrue(trueOrFalse.equivalent(falseOrTrue));
	}

	@Test
	public void When_a_compare_b_then_equivalent_works_correctly()
	{
		HLTBoolExpression aEqualB = new HLTCompareExpression("a", HLTCompareRelation.Equals, "b");
		HLTBoolExpression aEqualB1 = new HLTCompareExpression("a", HLTCompareRelation.Equals, "b");
		HLTBoolExpression bEqualA = new HLTCompareExpression("b", HLTCompareRelation.Equals, "a");

		HLTBoolExpression bGreaterThanA = new HLTCompareExpression("b", HLTCompareRelation.GreaterThan, "a");
		HLTBoolExpression aLessThanB = new HLTCompareExpression("a", HLTCompareRelation.LessThan, "b");

		assertTrue(aEqualB.equivalent(aEqualB1));
		assertTrue(aEqualB.equivalent(bEqualA));
		assertTrue(bGreaterThanA.equivalent(aLessThanB));
	}

	@Test
	public void When_a_and_or_b_then_imply_works_correctly()
	{
		HLTBoolExpression ab = new HLTCompareExpression("a", HLTCompareRelation.Equals, "b");
		HLTBoolExpression cd = new HLTCompareExpression("c", HLTCompareRelation.Equals, "d");
		HLTBoolExpression xy = new HLTCompareExpression("x", HLTCompareRelation.Equals, "y");

		HLTBoolExpression ab_and_xy = ab.and(xy);
		HLTBoolExpression ab_or_xy = ab.or(xy);

		HLTBoolExpression ab_and_xy_or_cd_and_xy = ab.and(xy).or(cd.and(xy));
		HLTBoolExpression ab_or_xy_and_cd_or_xy = ab.or(xy).and(cd.or(xy));

		assertEquals(ab_and_xy.and(ab), ab_and_xy);
		assertEquals(ab_and_xy.or(ab), ab);
		assertEquals(ab_or_xy.and(xy), xy);
		assertEquals(ab_or_xy.or(xy), ab_or_xy);
		
		assertEquals(ab_and_xy_or_cd_and_xy.or(xy), xy);
		assertEquals(ab_and_xy_or_cd_and_xy.and(xy), ab_and_xy_or_cd_and_xy);
		assertEquals(ab_or_xy_and_cd_or_xy.or(xy), ab_or_xy_and_cd_or_xy);
		assertEquals(ab_or_xy_and_cd_or_xy.and(xy), xy);
	}

	@Test
	public void When_a_imply_b_then_correct()
	{
		HLTBoolExpression aEqualB = new HLTCompareExpression("a", HLTCompareRelation.Equals, "b");
		HLTBoolExpression aEqualB1 = new HLTCompareExpression("a", HLTCompareRelation.Equals, "b");
		HLTBoolExpression bEqualA = new HLTCompareExpression("b", HLTCompareRelation.Equals, "a");

		HLTBoolExpression xEqualY = new HLTCompareExpression("x", HLTCompareRelation.Equals, "y");

		assertTrue(aEqualB.and(xEqualY).imply(bEqualA));
		assertTrue(!aEqualB.or(xEqualY).imply(aEqualB1));
		assertTrue(aEqualB.or(aEqualB.and(xEqualY)).imply(bEqualA));
	}
}
