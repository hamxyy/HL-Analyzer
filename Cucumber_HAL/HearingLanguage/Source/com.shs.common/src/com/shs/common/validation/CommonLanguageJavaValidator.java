package com.shs.common.validation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.xtext.validation.Check;

import com.shs.common.commonLanguage.CommonLanguagePackage;
import com.shs.common.commonLanguage.CrvParameter;
import com.shs.common.commonLanguage.ParameterScope;
import com.shs.common.commonLanguage.IntegerParameter;

public class CommonLanguageJavaValidator extends AbstractCommonLanguageJavaValidator
{

	@Check
	public void curvesOnlyInCrv(CrvParameter p)
	{
		if (p.getScope() != ParameterScope.CRV && p.getScope() != ParameterScope.LVLCRV)
		{
			error("Curves can only be in crv or levelcurve scope", p.eContainingFeature());
		}
	}

	@Check
	public void IntParamsOnlyMaxMinCheck(IntegerParameter param)
	{
		String max = param.getMaximum();
		String min = param.getMinimum();
		if (max != null || min != null)
		{

			if (max.indexOf(".") >= 0)
			{
				error("Only interger values are allowed!", CommonLanguagePackage.eINSTANCE.getIntegerParameter_Maximum());
			}
			if (min.indexOf(".") >= 0)
			{
				error("Only interger values are allowed!", CommonLanguagePackage.eINSTANCE.getIntegerParameter_Minimum());
			}

		}
	}

	@Check
	public void IntParamsCheckInit(IntegerParameter param)
	{
		if (param == null) return;
		EList<String> definedValues = param.getValues();
		if (definedValues != null && !definedValues.isEmpty())
		{
			int min = 0;
			int max = 0;
			boolean rangeDefined = true;
			try
			{
				max = Integer.parseInt(param.getMaximum());
				min = Integer.parseInt(param.getMinimum());
			}
			catch (NumberFormatException ex)
			{
				rangeDefined = false;
			}
			for (String s : definedValues)
			{
				if (s.indexOf(".") >= 0)
				{
					// might work correctly if we switch to xtext 2.0 - it is a
					// well known bug that
					// on lists only the first element is marked
					// as long it does not mark the correct item - just mark the
					// whole line
					error("Only integer values are allowed!", CommonLanguagePackage.Literals.INTEGER_PARAMETER__VALUES);
					// error("Only integer values are allowed!",
					// CommonLanguagePackage.INTEGER_PARAMETER__VALUES);
				}
				if (rangeDefined)
				{
					int val;
					try
					{
						val = Integer.parseInt(s);
					}
					catch (NumberFormatException ex)
					{
						continue;
					}

					if (val > max)
					{

						// might work correctly if we switch to xtext 2.0 - it
						// is a well known bug that
						// on lists only the first element is marked
						// as long it does not mark the correct item - just mark
						// the whole line
						error("Value is out of defined maximum value", CommonLanguagePackage.Literals.INTEGER_PARAMETER__MAXIMUM);
					}
					if (val < min)
					{
						// might work correctly if we switch to xtext 2.0 - it
						// is a well known bug that
						// on lists only the first element is marked
						// as long it does not mark the correct item - just mark
						// the whole line
						error("Value is lower than defined minimum value", CommonLanguagePackage.Literals.INTEGER_PARAMETER__MINIMUM);
					}

				}
			}

		}
	}
}
