package com.shs.hltest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.shs.hl.generator.halsa.struct.ConditionalValue;
import com.shs.hl.generator.halsa.struct.Constant;
import com.shs.hl.generator.halsa.struct.HLTCompareExpression;
import com.shs.hl.generator.halsa.struct.HLTCompareRelation;
import com.shs.hl.generator.halsa.struct.VariableScope;

public class LocalVariableTests
{
	@Test
	public void test_scope()
	{
		VariableScope scope = new VariableScope();

		List<ConditionalValue> values = new ArrayList<ConditionalValue>();
		HLTCompareExpression a_is_a0 = new HLTCompareExpression("a", HLTCompareRelation.Equals, "a0");
		HLTCompareExpression a_is_a1 = new HLTCompareExpression("a", HLTCompareRelation.Equals, "a1");
		values.add(new ConditionalValue("0", a_is_a0));
		values.add(new ConditionalValue("1", a_is_a1));

		scope.assign(values);
		assertEquals(scope.getAllValues().size(), 2);

		HLTCompareExpression b_is_b0 = new HLTCompareExpression("b", HLTCompareRelation.Equals, "b0");
		VariableScope innerScope = scope.enterNewScope(b_is_b0);
		assertEquals(innerScope.getAllValues().size(), 2);

		innerScope.assign(new Constant("2").getAllValues());
		assertEquals(innerScope.getAllValues().size(), 1);

		VariableScope outerScope = innerScope.exitScope();
		assertEquals(outerScope.getAllValues().size(), 3);
	}
}
