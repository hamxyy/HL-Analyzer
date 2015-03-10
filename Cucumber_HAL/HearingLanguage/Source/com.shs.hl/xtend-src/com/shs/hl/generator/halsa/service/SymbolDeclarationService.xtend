package com.shs.hl.generator.halsa.service

import com.shs.common.commonLanguage.EnumParameter
import com.shs.common.commonLanguage.IntegerParameter
import com.shs.common.commonLanguage.SymbolDeclaration
import java.util.ArrayList
import java.util.List

class SymbolDeclarationService
{
	def static SymbolDeclarationStruct getStruct(SymbolDeclaration symbol)
	{
		switch (symbol)
		{
			case symbol instanceof EnumParameter:
			{
				val enumParam = (symbol as EnumParameter)

				// Add possible values for generating tests purpose
				val possibleValues = new ArrayList<String>
				for (literal : enumParam.literals)
				{
					possibleValues.add(enumParam.name + "." + literal.name)
				}
				return new SymbolDeclarationStruct(enumParam.name, possibleValues)
			}
			case symbol instanceof IntegerParameter:
			{
				val intParam = (symbol as IntegerParameter)

				// Add possible values for generating tests purpose
				val possibleValues = new ArrayList<String>
				val minimun = Integer.parseInt(intParam.minimum)
				val maximum = Integer.parseInt(intParam.maximum)
				for (var i = minimun; i <= maximum; i++)
				{
					possibleValues.add(i.toString)
				}
				return new SymbolDeclarationStruct(intParam.name, possibleValues)
			}
		}
	}
}

class SymbolDeclarationStruct
{
	public String name
	public List<String> possibleValues

	new(String name, List<String> possibleValues)
	{
		this.name = name
		this.possibleValues = possibleValues
	}
}
