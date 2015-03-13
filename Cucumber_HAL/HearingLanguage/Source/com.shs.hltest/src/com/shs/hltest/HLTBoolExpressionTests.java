package com.shs.hltest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.shs.hl.generator.halsa.struct.HLTBoolExpression;
import com.shs.hl.generator.halsa.struct.HLTCompareExpression;
import com.shs.hl.generator.halsa.struct.HLTCompareRelation;
import com.shs.hl.generator.halsa.struct.HLTConstantExpression;

public class HLTBoolExpressionTests
{

	@Test
	public void When_bool_and_bool_then_correct()
	{
		HLTConstantExpression constFalse = HLTConstantExpression.alwaysFalse();
		HLTConstantExpression constTrue = HLTConstantExpression.alwaysTrue();

		HLTBoolExpression trueAndTrue = constTrue.and(constTrue);
		HLTBoolExpression trueAndFalse = constTrue.and(constFalse);
		HLTBoolExpression falseAndFalse = constFalse.and(constFalse);
		HLTBoolExpression falseAndTrue = constFalse.and(constTrue);

		assertEquals(trueAndTrue, constTrue);
		assertEquals(falseAndFalse, constFalse);
		assertEquals(trueAndFalse, falseAndTrue);
	}

	@Test
	public void When_bool_or_bool_then_correct()
	{
		HLTConstantExpression constFalse = HLTConstantExpression.alwaysFalse();
		HLTConstantExpression constTrue = HLTConstantExpression.alwaysTrue();

		HLTBoolExpression trueOrTrue = constTrue.or(constTrue);
		HLTBoolExpression trueOrFalse = constTrue.or(constFalse);
		HLTBoolExpression falseOrFalse = constFalse.or(constFalse);
		HLTBoolExpression falseOrTrue = constFalse.or(constTrue);

		assertEquals(trueOrTrue, constTrue);
		assertEquals(falseOrFalse, constFalse);
		assertEquals(trueOrFalse, falseOrTrue);
	}

	@Test
	public void When_a_compare_b_then_correct()
	{
		HLTBoolExpression aEqualB = new HLTCompareExpression("a", HLTCompareRelation.Equals, "b");
		HLTBoolExpression aEqualB1 = new HLTCompareExpression("a", HLTCompareRelation.Equals, "b");
		HLTBoolExpression bEqualA = new HLTCompareExpression("b", HLTCompareRelation.Equals, "a");

		HLTBoolExpression bGreaterThanA = new HLTCompareExpression("b", HLTCompareRelation.GreaterThan, "a");
		HLTBoolExpression aLessThanB = new HLTCompareExpression("a", HLTCompareRelation.LessThan, "b");

		assertEquals(aEqualB, aEqualB1);
		assertEquals(aEqualB, bEqualA);
		assertEquals(bGreaterThanA, aLessThanB);
	}
}
