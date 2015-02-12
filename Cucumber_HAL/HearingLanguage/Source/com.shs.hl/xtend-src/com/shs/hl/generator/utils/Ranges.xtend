package com.shs.hl.generator.utils

import com.shs.common.commonLanguage.SymbolDeclaration
import com.shs.hl.hearingLanguage.ForVarDeclaration
import com.shs.hl.hearingLanguage.ForeachStatement
import com.shs.hl.hearingLanguage.StatementList
import com.google.inject.Inject
import com.shs.hl.hearingLanguage.Expression
import com.shs.hl.hearingLanguage.ParenExpr
import com.shs.hl.hearingLanguage.IntRangeExpression

class Ranges {
	
	@Inject
	extension IGenCode gencode

	
	def CharSequence rangeForeachState(ForeachStatement stat){
		foreachCode(stat.range,stat.iterator,stat.body)
	}
	
	def CharSequence foreachIteratorCode(ForVarDeclaration forDecl)
	'''«forDecl.type.genCode» «forDecl.name»'''	
  	
  	
  	
	def CharSequence foreachCode(Object expr, SymbolDeclaration decl, StatementList b)
	{
		
		// regular usecase: to be called with ForVarDeclartion
		// since an inverted logic will lead to much more unreadable code 
		switch(decl)
		{
			ForVarDeclaration:
			{
				switch(expr)
				{
					ParenExpr: 	'''«(expr.eContents.get(0) as Expression).foreachCode(decl,b)»'''
					IntRangeExpression:
					{
								'''
								for («decl.foreachIteratorCode» = «expr.low.genCode» «decl.name» <= «expr.high.genCode»; «decl.name»++)
									«b.genCode»
								'''
					} 
					Expression:
					{
				    			'''	foreach ( «decl.foreachIteratorCode» in «expr.genCode» )
									«b.genCode»
								'''
					}
					default:	'''// ERROR -  no code generator for «decl.eClass.name» in foreachCode for «b.eClass.name»'''
				}
					
			}
			default:			'''// ERROR -  no code generator for «decl.eClass.name» in foreachCode for «b.eClass.name»'''				
			
		}
	}
	
	
}