package com.shs.hl.generator.utils

import com.google.inject.Inject
import com.shs.common.commonLanguage.ChannelParameter
import com.shs.common.commonLanguage.DoubleParameter
import com.shs.common.commonLanguage.EnumLiteral
import com.shs.common.commonLanguage.EnumParameter
import com.shs.common.commonLanguage.IntegerParameter
import com.shs.common.commonLanguage.ParameterScope
import com.shs.common.commonLanguage.SymbolDeclaration
import com.shs.hl.hearingLanguage.AddToTGStatement
import com.shs.hl.hearingLanguage.AdjustCompStatement
import com.shs.hl.hearingLanguage.And
import com.shs.hl.hearingLanguage.AppOffsStatement
import com.shs.hl.hearingLanguage.ArrayAccessExpression
import com.shs.hl.hearingLanguage.AssertEqualsStatement
import com.shs.hl.hearingLanguage.AssertGetExpression
import com.shs.hl.hearingLanguage.AssertSetStatement
import com.shs.hl.hearingLanguage.AssertStatement
import com.shs.hl.hearingLanguage.AssertionStatement
import com.shs.hl.hearingLanguage.AssignmentStatement
import com.shs.hl.hearingLanguage.AtsideExpression
import com.shs.hl.hearingLanguage.BitAnd
import com.shs.hl.hearingLanguage.BitComplementExpr
import com.shs.hl.hearingLanguage.BitOr
import com.shs.hl.hearingLanguage.BreakStatement
import com.shs.hl.hearingLanguage.BuiltInCall
import com.shs.hl.hearingLanguage.BuiltinStatement
import com.shs.hl.hearingLanguage.DefinedExpression
import com.shs.hl.hearingLanguage.DoWhileStatement
import com.shs.hl.hearingLanguage.ElseIf
import com.shs.hl.hearingLanguage.EnumLeft
import com.shs.hl.hearingLanguage.EnumRight
import com.shs.hl.hearingLanguage.Equals
import com.shs.hl.hearingLanguage.Expression
import com.shs.hl.hearingLanguage.ExpressionStatement
import com.shs.hl.hearingLanguage.FailStatement
import com.shs.hl.hearingLanguage.FalseLiteral
import com.shs.hl.hearingLanguage.FittingAssistStatement
import com.shs.hl.hearingLanguage.ForVarDeclaration
import com.shs.hl.hearingLanguage.ForeachStatement
import com.shs.hl.hearingLanguage.FunctionDeclaration
import com.shs.hl.hearingLanguage.GetProposalText1
import com.shs.hl.hearingLanguage.GetProposalText2
import com.shs.hl.hearingLanguage.Greater
import com.shs.hl.hearingLanguage.GreaterOrEquals
import com.shs.hl.hearingLanguage.IfStatement
import com.shs.hl.hearingLanguage.IsMaxExpression
import com.shs.hl.hearingLanguage.IsMinExpression
import com.shs.hl.hearingLanguage.IsOffsAppExpression
import com.shs.hl.hearingLanguage.IsValidExpression
import com.shs.hl.hearingLanguage.LocalVariableDeclaration
import com.shs.hl.hearingLanguage.LocalVariableDeclarationStatement
import com.shs.hl.hearingLanguage.LogChanCountExpression
import com.shs.hl.hearingLanguage.Minus
import com.shs.hl.hearingLanguage.MinusEqualsStatement
import com.shs.hl.hearingLanguage.MultToTGStatement
import com.shs.hl.hearingLanguage.Multi
import com.shs.hl.hearingLanguage.MultiplicationEqualsStatement
import com.shs.hl.hearingLanguage.Namespace
import com.shs.hl.hearingLanguage.NotExpression
import com.shs.hl.hearingLanguage.NullExpression
import com.shs.hl.hearingLanguage.NumberLiteral
import com.shs.hl.hearingLanguage.OppositeExpression
import com.shs.hl.hearingLanguage.Or
import com.shs.hl.hearingLanguage.ParameterReadExpression
import com.shs.hl.hearingLanguage.ParenExpr
import com.shs.hl.hearingLanguage.Plus
import com.shs.hl.hearingLanguage.PlusEqualsStatement
import com.shs.hl.hearingLanguage.PreDecrementExpr
import com.shs.hl.hearingLanguage.PreIncrementExpr
import com.shs.hl.hearingLanguage.PrintStatement
import com.shs.hl.hearingLanguage.ReturnStatement
import com.shs.hl.hearingLanguage.SetApply1IsEnabled
import com.shs.hl.hearingLanguage.SetApply1IsVisible
import com.shs.hl.hearingLanguage.SetApply2IsEnabled
import com.shs.hl.hearingLanguage.SetApply2IsVisible
import com.shs.hl.hearingLanguage.SetMaxStatement
import com.shs.hl.hearingLanguage.SetMinStatement
import com.shs.hl.hearingLanguage.SetProposalTextID1
import com.shs.hl.hearingLanguage.SetProposalTextID2
import com.shs.hl.hearingLanguage.Smaller
import com.shs.hl.hearingLanguage.SmallerOrEquals
import com.shs.hl.hearingLanguage.Statement
import com.shs.hl.hearingLanguage.StatementList
import com.shs.hl.hearingLanguage.SwitchStatement
import com.shs.hl.hearingLanguage.SymbolReference
import com.shs.hl.hearingLanguage.TestingStatement
import com.shs.hl.hearingLanguage.TextLiteral
import com.shs.hl.hearingLanguage.TrueLiteral
import com.shs.hl.hearingLanguage.Type
import com.shs.hl.hearingLanguage.UnEquals
import com.shs.hl.hearingLanguage.WhileStatement
import org.eclipse.emf.ecore.EObject
import com.shs.hl.hearingLanguage.AssignmentLikeThings
import com.shs.hl.hearingLanguage.SitExpression
import com.shs.hl.hearingLanguage.GetCurve
import com.shs.hl.hearingLanguage.ExistsCurve
import com.shs.hl.hearingLanguage.ApplyCurve
import com.shs.hl.hearingLanguage.IsCurveApplicable
import com.shs.hl.hearingLanguage.SetStepsBelowMax
import com.shs.hl.hearingLanguage.GetStepsBelowMax
import com.shs.hl.hearingLanguage.ExistsVolumeControl
import com.shs.hl.hearingLanguage.ExistsCurveFromLc
import com.shs.hl.hearingLanguage.ExistsProgramControl
import com.shs.hl.hearingLanguage.GetStepsAboveMin
import com.shs.hl.hearingLanguage.SetStepsAboveMin
import com.shs.hl.hearingLanguage.GetCurveFromLc
import com.shs.hl.generator.GenerationInfo.ArithmeticKind

import com.shs.common.services.CommonLanguageGrammarAccess.IntegerParameterElements
import com.shs.hl.generator.GenerationInfo
import com.shs.hl.hearingLanguage.CurveType
import com.shs.hl.hearingLanguage.NumericType

class GenCode implements IGenCode { // ####################################################################################################################
	

	
	
	
	
	//
	// Structure of this generator:																						 
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//
	// Statement 			--> EObject
	// SymbolDeclaration 	--> EObject
	// AssignmentLikeThings --> Statement
	// Expression --> AssignmentLikeThings-->Statement
	// 
	// Each grammar element is specified under its TopContainer unless other requirements force them to be defined
	// somewhere else:
	// --> Ranges related stuff is define in Ranges.xtend
	// --> All utility functionality is specified in GenUtils
	// 
	// 
	// DO NOT ADD "dispatch genCode" !!!!!!
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
	//         Keep mind this structure - and the fact how the dispatching works!
	//         If a more general dispatcher is defined in upper part of the document it will hide all other dispatchers
	//         definitions below!
	// 
	// ####################################################################################################################
	@Inject extension GenUtils utilsEx // special helper for model

	@Inject extension Ranges ranges

	// StatementList --> EObject
	def dispatch genCode(StatementList stList) {
		'''{
			«FOR stm : stList.statements»
				«stm.genCode»
				«ENDFOR»
			}
		'''
	}

	// ****************************************************************************************************************	
	// Statements 
	// ****************************************************************************************************************
	// Statement --> EObject
	def dispatch genCode(Statement stm) {
		switch (stm) {
			LocalVariableDeclarationStatement: '''«stm.^var.genCode»'''
			ExpressionStatement:	'''	«stm.expr.genCode»;'''
			WhileStatement: 		'''	while ( «stm.expr.genCode» )«stm.body.genCode»'''
			DoWhileStatement: 		''' do «stm.body.genCode» while ( «stm.expr.genCode»);'''
			ForeachStatement:		'''«stm.rangeForeachState»'''
			BreakStatement: 		'''break;'''
			ReturnStatement: 		'''return «stm.expr.genCode»;'''
			IfStatement:			genCodeIFStatement(stm)
			SwitchStatement:		genCodeSwitchStatement(stm)
			AssertionStatement: '''
			Debug.Assert(«stm.assertDecide.genCode»,«stm.assertMessage.messageCode»);'''
			PrintStatement: '''
			Debug.WriteLine(«messageCode(stm.msg)»);'''
			// Testing ---------------			
			TestingStatement:		genCodeTestingStatement(stm)
			//AssertSetStatement --> TestingControlStatement --> Statement
			AssertSetStatement: ''' 
			_AssertSet("«stm.id»",«stm.assertSetValue»);'''
			// -----------------------------------------------------------------------------------------
			// SubHandlers of derived types
			// ----------------------------------------------------------------------------------------- 
			BuiltinStatement:		genCodeBuiltinStatement(stm)
			AssignmentLikeThings:	genCodeAssignmentLikeThings(stm)
			default: '''	// no code generator for statement «stm.eClass.name»'''
		}
	}

	def messageCode(Expression literal) {
		switch (literal) {
			TextLiteral: 		'''"«literal.value»" '''
			NumberLiteral: 		'''«literal.value»'''
			SymbolReference: 	'''«literal.symbol.name»'''
			//Expression
			default: 			'''«literal.genCode»'''
		}
	}

	// =======================================================
	// SymbolDeclaration --> EObject
	// =======================================================
	def dispatch genCode(SymbolDeclaration symb) {
		switch (symb) {
			EnumLiteral: 				'''«symb.eContainer.enumCode»_«symb.name»'''
			LocalVariableDeclaration:	'''«symb.type.genCode» «symb.name» «IF symb.init != null»= «symb.init.genCode»«ENDIF»;'''
			ForVarDeclaration:			'''«symb.genCode»'''
			default: ''''''
		}
	}

	def enumCode(EObject para) {
		switch (para) {
			EnumParameter:	'''«para.enumScopeTag»«para.name»'''
			default:		'''//ERROR no code generator for «para»	'''
		}
	}

	// ----------------------------------------------------------------------------------------------------------------
	// BuiltinStatement --> Statement
	// ----------------------------------------------------------------------------------------------------------------
	def genCodeBuiltinStatement(BuiltinStatement stm) {
		switch (stm) {
			SetMaxStatement: 	'''_setMax(«stm.expr.builtInExpressionCode»;'''
			SetMinStatement: 	'''_setMin(«stm.expr.builtInExpressionCode»;'''
			AdjustCompStatement: '''_adjustComprHoldMedLev();'''
			AddToTGStatement: 	'''_addCurveToTG(«stm.arg.genCode» , «stm.argTo.genCode»);'''
			MultToTGStatement: 	'''_multCurveToTG(«stm.arg.genCode» , «stm.argTo.genCode»);'''
			AppOffsStatement: 	'''_applyOffset(«stm.argId.genCode»,«stm.argTo.genCode»,«stm.argApply.genCode»);'''
			FittingAssistStatement:	genCodeFittingAssist(stm)
			default: '''// error no valid code generator'''
		}
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Fitting assistant
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//FittingAssistStatement --> BuiltinStatement --> Statement
	def genCodeFittingAssist(FittingAssistStatement statement) {
		switch (statement) {
			SetApply1IsVisible: '''_setApply1IsVisible(«statement.argVis.genCode»);'''
			SetApply1IsEnabled: '''_setApply1IsEnabled(«statement.argEn.genCode»);'''
			SetProposalTextID1: '''_setProposalTextID1(«statement.argText.genCode»);'''
			GetProposalText1: 	'''_getProposalTextID1();'''
			SetApply2IsVisible: '''_setApply2IsVisible(«statement.argVis.genCode»);'''
			SetApply2IsEnabled: '''_setApply2IsEnabled(«statement.argEn.genCode»);'''
			SetProposalTextID2: '''_setProposalTextID2(«statement.argText.genCode»);'''
			GetProposalText2: 	'''_getProposalTextID2();'''
			default: 			'''//NO valid codegenerator for FittingAssistent feature found'''
		}
	}
	
	
	def genCodeFittingAssistentGet(Expression expr)
	{
		switch (expr) {
			GetProposalText1: 	'''_getProposalTextID1()'''
			GetProposalText2: 	'''_getProposalTextID2()'''
			default:			'''//NO valid codegenerator for FittingAssistent-getProposalText feature found'''
		}
	}

	// ----------------------------------------------------------------------------------------------------------------
	// Program flow --> Statement
	// ----------------------------------------------------------------------------------------------------------------
	//IfStatement --> Statement
	def genCodeIFStatement(IfStatement statement) '''	if ( «statement.condition.genCode» ) «statement.thenBody.genCode»
		«FOR elzz : statement.elseIfs»«elzz.genCode»«ENDFOR»
		«IF statement.elseBody != null» else «statement.elseBody.genCode»«ENDIF»'''

	//ElseIf --> EObject
	def dispatch genCode(ElseIf statement) '''else if ( «statement.condition.genCode» ) «statement.body.genCode»'''

	//SwitchStatement --> Statement
	def CharSequence genCodeSwitchStatement(SwitchStatement statement) 
	'''switch ( «statement.expr.switchStatementCondition»)
		{
		«FOR ca : statement.cases»
			case  «ca.expr.switchStatementCode» :
			{
				«FOR stmnt : ca.body.statements»
				 «stmnt.genCode»
				«ENDFOR»
				 break;
			}
			«ENDFOR»
		«IF statement.defaultBlock != null» default:
		{
		 «FOR stmt : statement.defaultBlock.statements»
		  «stmt.genCode»
		  «ENDFOR»
		   break;	
		}	
		«ENDIF»
	 }
	'''
	
	def switchStatementCondition(Expression condition)
	{
		var pretag=""
		var posttag=""
		switch(condition)
		{
			ArrayAccessExpression:{
				val Expression arr = condition.expr;
				switch(arr)
				{
					ParameterReadExpression: {
						if (checkSwitchSpecialCondition(arr))
						{
							pretag="(int)(";
							posttag=getSwitchSpecialAccess(arr)+")";
						}
					} 
				}
			}
			
			ParameterReadExpression:
			{
				if (checkSwitchSpecialCondition(condition)) {	
					pretag="(int)";
					posttag=getSwitchSpecialAccess(condition);
				}
			}
		}
		
		'''«pretag»«condition.genCode»«posttag»'''
	}

	def boolean checkSwitchSpecialCondition(ParameterReadExpression expr)
	{
		
		var boolean validScope=false;
		
		switch (expr.scope)
		{
			case ParameterScope::HI : validScope= true
			case ParameterScope::ENV: validScope= true
		}
		// in case we need a more fine tuned handling 
//		if (validScope)
//		{
//			switch(expr.param)
//			{
//				IntegerParameter: 	validScope=true
//				DoubleParameter:	validScope=true
//				//EnumParameter:		validScope=false
//				default: 			validScope=false
//			}
//		}
		
		return validScope;
		
	}


	def getSwitchSpecialAccess(ParameterReadExpression expr)
	{
		switch (expr.scope)
		{
			case ParameterScope::HI:".PhysicalStep"
			default:""
		}
	}


	def switchStatementCode(Expression statement) {
		switch (statement) {
			EnumLiteral: 		'''«statement.pos»'''
			SymbolDeclaration: 	'''«statement.genCode»'''
			SymbolReference: 	'''«statement.symbol.switchStatementSymbolHandling»'''
			default: '''«statement.genCode»'''
		//other cases cannot happen 			
		//default: 			'''//no codegen'''
		}
	}

	def switchStatementSymbolHandling(SymbolDeclaration reference) {
		switch (reference) {
			EnumLiteral: '''«reference.pos»'''
			default:	'''«reference.genCode»'''		
		}
	}







	// ----------------------------------------------------------------------------------------------------------------
	// Testing statements & stuff
	// ----------------------------------------------------------------------------------------------------------------
	//TestingStatement --> Statement
	def genCodeTestingStatement(TestingStatement stm) {
		switch (stm) {
			FailStatement: '''
				_Fail("«stm.qualID()»");
			'''
			AssertStatement: '''
				_Assert("«stm.qualID()»",«stm.expr.genCode»,«stm.msg.genCode»);
			'''
			AssertEqualsStatement: '''
				«val exp = GenUtils::uniquify("expected")»
				«var act = GenUtils::uniquify("actual")»
				Object «exp» = «stm.expected.genCode»;   
				Object «act» = «stm.actual.genCode»;
				_AssertEquals("«stm.qualID()»",«exp»,«act»);
			 		'''
		}
	}

	//AssertGetExpression --> Expression
	def genCodeAssertGetExpression(AssertGetExpression statement) '''
	_AssertGet("«statement.id»");'''

	// ****************************************************************************************************************	
	// AssignmentLikeThings --> Statement
	// ****************************************************************************************************************	
	// AssignmentLikeThings --> Statement
	def genCodeAssignmentLikeThings(AssignmentLikeThings assign) {
		switch (assign) {
			AssignmentStatement: 	'''«assign.lvalue.assignmentCode(assign.rvalue)»'''
			PlusEqualsStatement: 	'''«handleArithmetics(assign.lvalue, assign.rvalue, ArithmeticKind.PlusEquals)»;''' //'''«assign.lvalue.genCode» += «assign.rvalue.genCode»;'''
			MinusEqualsStatement: 	'''«handleArithmetics(assign.lvalue, assign.rvalue, ArithmeticKind.MinusEquals)»;''' //'''«assign.lvalue.genCode» -= «assign.rvalue.genCode»;'''
			MultiplicationEqualsStatement: '''«handleArithmetics(assign.lvalue, assign.rvalue, ArithmeticKind.MultEquals)»;''' //'''«assign.lvalue.genCode» *= «assign.rvalue.genCode»'''
			Expression:				genCodeExpression(assign)
			default: 				'''// error no specific code generator for AssignmentLikeThins --«assign.class.name»'''
		}
	}

	def assignmentCode(Expression lvalue, Expression rvalue) {
		switch (lvalue) {
			AtsideExpression: '''«lvalue.AtsideAssignment(rvalue)»;'''
			//Expression = default
			default: 		'''«lvalue.genCode» = «rvalue.genCode»;'''
		}
	}

	def AtsideAssignment(Expression side, Expression assign) {
		switch (side) {
			AtsideExpression:'''«side.genCode».Val =«assign.genCode»'''
			// Expression = default
			default: 		'''//ERROR «side.genCode»'''
		}
	}

	// ****************************************************************************************************************	
	// Expressions --> AssignmentLikeThings
	// ****************************************************************************************************************	
	//Expression --> AssignmentLikeThings --> Statement
	def genCodeExpression(Expression expr) {
		switch (expr) {
			ArrayAccessExpression: '''«expr.arrayCode(expr.expr)»'''
			SymbolReference:		genCodeSymbolReference(expr)
			AtsideExpression:  		genCodeAtSideExpression(expr)
			OppositeExpression:		genCodeOppositeExpression(expr)
			ParameterReadExpression:  genCodeParameterReadExpression(expr, false)
			SitExpression: 			'''_sit(«expr.index.genCode»,«expr.hiExpression.builtInExpressionCode»)'''
			BuiltInCall: 			'''«expr.expr.genCode».«expr.builtIn.csFunction»'''
			NotExpression: 			'''!(«expr.expr.genCode»)'''
			NullExpression: 		'''null'''

			BitComplementExpr: 		'''~ «expr.expr.genCode»'''
			EnumRight: 				'''«expr.left.genCode» += «expr.right.genCode»''' //TODO handle curve arithmetics
			EnumLeft: 				'''«expr.left.genCode» -= «expr.right.genCode»''' //TODO handle curve arithmetics
			Equals: 				'''«expr.left.genCode» == «expr.right.genCode»'''
			UnEquals:				'''«expr.left.genCode» != «expr.right.genCode»'''
			Greater: 				'''«expr.left.genCode» > «expr.right.genCode»'''
			Smaller: 				'''«expr.left.genCode» < «expr.right.genCode»'''
			GreaterOrEquals:		'''«expr.left.genCode» >= «expr.right.genCode»'''
			SmallerOrEquals:		'''«expr.left.genCode» <= «expr.right.genCode»'''
			Plus:					handleArithmetics(expr.left,expr.right,ArithmeticKind.Plus) //'''«expr.left.genCode» + «expr.right.genCode»'''//TODO handle curve arithmetics
			Minus:					handleArithmetics(expr.left,expr.right,ArithmeticKind.Minus)//'''«expr.left.genCode» - «expr.right.genCode»'''//TODO handle curve arithmetics
			Multi:					handleArithmetics(expr.left,expr.right,ArithmeticKind.Mult)//'''«expr.left.genCode» * «expr.right.genCode»'''//TODO handle curve arithmetics
			And:					'''«expr.left.genCode» && «expr.right.genCode»'''
			Or:						'''«expr.left.genCode» || «expr.right.genCode»'''
			BitAnd:					'''«expr.left.genCode» & «expr.right.genCode»'''
			BitOr:					'''«expr.left.genCode» | «expr.right.genCode»'''
			PreIncrementExpr:		'''++«expr.expr.genCode» '''
			PreDecrementExpr: 		'''--«expr.expr.genCode» '''
			
			IsValidExpression: 		'''_isValid(«expr.expr.builtInExpressionCode»,«expr.expr.genCode»)'''
			IsMinExpression: 		'''_isMin(«expr.expr.builtInExpressionCode»)'''
			IsMaxExpression: 		'''_isMax(«expr.expr.builtInExpressionCode»)'''
			IsOffsAppExpression: 	'''_isOffsetApplicable(«expr.argId.genCode»,«expr.argTo.genCode»,«expr.argApply.genCode»)'''
			LogChanCountExpression: '''_logChanCount(«expr.argId.genCode»)'''
			DefinedExpression: 		'''_isDefined(«expr.expr.builtInExpressionCode»)'''
			
			
			GetProposalText1: 		'''«expr.genCodeFittingAssistentGet»'''
			GetProposalText2:		'''«expr.genCodeFittingAssistentGet»'''
			
			
			GetCurve:				'''_getCurve(«expr.argCurve.genCode»)'''			
			ExistsCurve:			'''_existsCurve(«expr.argCurve.genCode»)'''	
			IsCurveApplicable:		'''_isCurveApplicable(«expr.argCurve.genCode»,«expr.argToSubject.genCode»,«expr.argApply.genCode»,«expr.argStartFreq.genCode»,«expr.argStopFreq.genCode»)'''
			ApplyCurve:				'''_applyCurve(«expr.argCurve.genCode»,«expr.argToSubject.genCode»,«expr.argApply.genCode»,«expr.argStartFreq.genCode»,«expr.argStopFreq.genCode»)'''
			ExistsCurveFromLc:		'''_existsCurveFromLc(«expr.argLC.genCode»,«expr.argLevel.genCode»)'''
			GetCurveFromLc:			'''_getCurveFromLc(«expr.argLC.genCode»,«expr.argLevel.genCode»)'''
			
			ExistsProgramControl: 	'''_existsProgramControl()'''
			ExistsVolumeControl:	'''_existsVolumeControl()'''
		
			GetStepsBelowMax:		'''_getStepsBelowMax(«expr.arg.genCode»)'''
			SetStepsBelowMax:		'''_setStepsBelowMax(«expr.arg.genCode»,«expr.^val.genCode»)'''
			GetStepsAboveMin:		'''_getStepsAboveMin(«expr.arg.genCode»)'''
			SetStepsAboveMin:		'''_setStepsAboveMin(«expr.arg.genCode»,«expr.^val.genCode»)'''
			
			TrueLiteral: 		'''true'''
			FalseLiteral: 		'''false'''
			ParenExpr: 			'''(«expr.expr.genCode»)'''
			NumberLiteral:		'''«expr.value»'''
			TextLiteral: 		'''"«expr.value»"'''
			AssertGetExpression:genCodeAssertGetExpression(expr)
			///////////////////////////////////////////////////////////////////////////////////////
			default: '''//no code generator for expression «expr.eClass.name»'''
		}
	}




	def handleArithmetics(Expression leftSide, Expression rightSide, ArithmeticKind operation)
	{
		
		if (canDoCurveHandling(leftSide,rightSide,operation))	{
			doCurveHandling(leftSide,rightSide,operation)
		}
		else {
			'''«leftSide.genCode»«operation.Symbol»«rightSide.genCode»'''
		}
	}



	
	def boolean canDoCurveHandling(Expression leftSide, Expression rightSide, ArithmeticKind operation)
	{
		var Object typeLeft = GenUtils.typeOf(leftSide)

		switch(typeLeft)
		{
			CurveType: return true
		}
		
		return false
	}
	
	
	def doCurveHandling(Expression leftSide, Expression rightSide, ArithmeticKind operation)
	{
		
//		var boolean aResult = false;
//		var Object typeLeft 	= GenUtils.typeOf(leftSide);
//		var Object typeRight 	= GenUtils.typeOf(rightSide);
//		
//		var String foundL=""
//		var String foundR=""
//		
//		switch(typeLeft)
//		{
//			CurveType: 		foundL=	"curve"
//			NumericType: 	foundL=	"number" // error!
//		}
//		
//		
//		switch(typeRight)
//		{
//			CurveType:  	foundR=	"curve"
//			NumericType: 	foundR=	"number"
//		}
		
		
		
		
		
		
		var pretag =""
		// some preparations
		
		if (operation == ArithmeticKind.PlusEquals||
			operation == ArithmeticKind.MinusEquals||
			operation == ArithmeticKind.MultEquals ){
				
				pretag = '''«leftSide.genCode»=''' // left side can always be generated
				//'''«leftSide.genCode»=«doCurveArithmetics(leftSide, rightSide, operation)»'''	
			}
			
		'''«pretag»_applyCurveOp(«leftSide.genCode»,«rightSide.genCode»,«operation.CurveOperation»)'''
	
	}














	


	// Array access
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
	def arrayCode(ArrayAccessExpression expArry, Expression para) {
		switch (para) {
			ParameterReadExpression: '''«para.genCodeParameterReadExpression(true)» [«expArry.index.arrayIndexCode»]«IF (para.
				scope == ParameterScope::HI)»«para.param.parameterReadScopeAccess»«ENDIF»'''
			//Expression
			default: '''«para.genCode»[«expArry.index.arrayIndexCode»]'''
		}
	}

	def arrayIndexCode(Expression expre) {
		switch (expre) {
			ForVarDeclaration: '''«expre.eCrossReferences().get(0).genCode»'''
			default: '''«expre.genCode»'''
		}
	}

	// Parameter read
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	def genCodeParameterReadExpression(ParameterReadExpression para, boolean array) 
	'''«para.scope.scopeArray»[«para.param.parameterReadScopeCode(para.scope)»«para.param.name»]
	«IF (!array && (para.scope == ParameterScope::HI))»«para.param.parameterReadScopeAccess»«ENDIF»'''

	def parameterReadScopeCode(SymbolDeclaration decl, ParameterScope scope) {
		switch (decl) {
			ChannelParameter: '''HIChannels.'''
			//SymbolDeclaration
			default: '''«scope.controlsClass».«scope.parameterReadScope»'''
		}
	}

	def parameterReadScopeAccess(SymbolDeclaration dparam) {
		switch (dparam) {
			DoubleParameter: 	'''.PhysVal'''
			IntegerParameter: 	'''.PhysVal'''
			//EnumParameter:		''''''
			//SymbolDeclaration:	''''''
			default: ''''''
		}
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Special handling for Expressions
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// OppositeExpression --> Expression --> AssignmentLikeThings --> Statement
	// ===================================
	def genCodeOppositeExpression(OppositeExpression expre) {
		var expression = expre.expr;
		//«IF (para.scope == ParameterScope::HI)»«para.param.parameterReadScopeAccess»«ENDIF»'''

		switch (expression) {
			ArrayAccessExpression: '''_opp(«expression.expr.builtInExpressionCode»)[«expression.index.genCode»]«expression.expr.genCodeOppositeExpressionTypeHandling»'''
			default: '''_opp(«expression.builtInExpressionCode»)«expression.genCodeOppositeExpressionTypeHandling»'''
		}
	}

	def genCodeOppositeExpressionTypeHandling(Expression expr)
	{
		switch (expr)
		{
			ParameterReadExpression: '''«IF(expr.scope == ParameterScope::HI)»«expr.param.parameterReadScopeAccess»«ENDIF»'''
			default:''''''
			
		}
		

	}
	
	// AtsideExpression --> Expression
	// ===================================
	def genCodeAtSideExpression(AtsideExpression atsExpr) {
		switch (atsExpr.expr) {
			ArrayAccessExpression: '''_side(«atsExpr.argSide.genCode» , «atsExpr.expr.codeArrayAccess»)'''
			default: '''_side(«atsExpr.argSide.genCode», «atsExpr.expr.builtInExpressionCode»)'''
		}
	}

	// BuiltIns
	// ===================================
	def builtInExpressionCode(Expression expre) {
		switch (expre) {
			ArrayAccessExpression: 		'''«expre.codeArrayAccess»'''
			ParameterReadExpression: 	'''«expre.scope.controlsClass».«expre.scope.parameterReadScope»«expre.param.name» '''
			SymbolReference: 			'''«(expre.symbol.eContainer  as EnumParameter).scope.controlsClass».«(expre.symbol.eContainer  as EnumParameter).name»'''
			default: '''«expre.genCode()»'''
		//default: '''//no code generator for symbol «expre.eClass.name»'''
		}
	}

	def CharSequence codeArrayAccess(Expression exp) {
		switch (exp) {
			ArrayAccessExpression: '''«exp.expr.builtInExpressionCode»[«exp.index.genCode»]'''
			default: '''	// ERROR -  no code generator for «exp.eClass.name»'''
		}
	}

	// Symbol reference declarations
	// ===================================      
	// SymbolReference --> Expression --> AssignmentLikeThings --> Statement
	def genCodeSymbolReference(SymbolReference ref) {
		switch (ref.symbol) {
			FunctionDeclaration: {
				var String nameSpace = (ref.symbol.eContainer as Namespace).csNamespace;
				var postElement = '''«ref.symbol.name»( «ref.genArgs» )'''

				if (nameSpace.compareToIgnoreCase((ref.goUpToFunction.eContainer as Namespace).csNamespace) != 0) {
					postElement = '''((«nameSpace»)GetMacro<«nameSpace»>()).''' + postElement
				}
				postElement

			}
			default: '''«ref.symbol.SymbolReferenceCode»'''
		}
	}

	def genArgs(SymbolReference ref) '''«var args = ref.argList.args»«FOR arg : args»«arg.genCode»«IF args.get(
		args.size - 1) != arg»,«ENDIF»«ENDFOR»'''

	def SymbolReferenceCode(SymbolDeclaration decl) {
		switch (decl) {
			EnumLiteral: '''«decl.genCode»'''
			//SymbolDeclaration
			default: '''«decl.name»'''
		// cannot happen!!
		//EObject:				'''//NO specific code generation for «decl.eClass.name»'''
		}
	}





//	def genCodeCurveArtithmetic(Expression curve, Expression arithmaticElement2, CurveArithmeticKind kind)
//	{
//		
//		_
//		
//		
//	}

	// ****************************************************************************************************************
	// FALL BACK
	// ****************************************************************************************************************
	def dispatch genCode(Object obj) '''//no specific code generator(genCode) for : «obj»'''

	def dispatch genCode(Type typ) ''' «typ.asCsType» '''
}
