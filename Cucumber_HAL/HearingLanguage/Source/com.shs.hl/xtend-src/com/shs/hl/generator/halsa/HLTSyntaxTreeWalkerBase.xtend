package com.shs.hl.generator.halsa

import com.shs.common.commonLanguage.EnumLiteral
import com.shs.hl.generator.halsa.evaluation.LRValueSwitch
import com.shs.hl.hearingLanguage.And
import com.shs.hl.hearingLanguage.ApplyCurve
import com.shs.hl.hearingLanguage.ArgList
import com.shs.hl.hearingLanguage.ArrayAccessExpression
import com.shs.hl.hearingLanguage.AssertGetExpression
import com.shs.hl.hearingLanguage.AssignmentLikeThings
import com.shs.hl.hearingLanguage.AssignmentStatement
import com.shs.hl.hearingLanguage.AtsideExpression
import com.shs.hl.hearingLanguage.BitAnd
import com.shs.hl.hearingLanguage.BitCheck
import com.shs.hl.hearingLanguage.BitComplementExpr
import com.shs.hl.hearingLanguage.BitOr
import com.shs.hl.hearingLanguage.BitWiseAndEqualsStatement
import com.shs.hl.hearingLanguage.BitWiseOrEqualsStatement
import com.shs.hl.hearingLanguage.BitXor
import com.shs.hl.hearingLanguage.BreakStatement
import com.shs.hl.hearingLanguage.BuiltInCall
import com.shs.hl.hearingLanguage.BuiltInMethodCall
import com.shs.hl.hearingLanguage.BuiltinStatement
import com.shs.hl.hearingLanguage.DefinedExpression
import com.shs.hl.hearingLanguage.DoWhileStatement
import com.shs.hl.hearingLanguage.ElseIf
import com.shs.hl.hearingLanguage.EnumLeft
import com.shs.hl.hearingLanguage.EnumRight
import com.shs.hl.hearingLanguage.Equals
import com.shs.hl.hearingLanguage.ExistsCurve
import com.shs.hl.hearingLanguage.ExistsCurveFromLc
import com.shs.hl.hearingLanguage.ExistsProgramControl
import com.shs.hl.hearingLanguage.ExistsVolumeControl
import com.shs.hl.hearingLanguage.Expression
import com.shs.hl.hearingLanguage.ExpressionStatement
import com.shs.hl.hearingLanguage.FalseLiteral
import com.shs.hl.hearingLanguage.ForeachStatement
import com.shs.hl.hearingLanguage.FunctionDeclaration
import com.shs.hl.hearingLanguage.GetCurve
import com.shs.hl.hearingLanguage.GetCurveFromLc
import com.shs.hl.hearingLanguage.GetProposalText1
import com.shs.hl.hearingLanguage.GetProposalText2
import com.shs.hl.hearingLanguage.GetStepsAboveMin
import com.shs.hl.hearingLanguage.GetStepsBelowMax
import com.shs.hl.hearingLanguage.Greater
import com.shs.hl.hearingLanguage.GreaterOrEquals
import com.shs.hl.hearingLanguage.IfStatement
import com.shs.hl.hearingLanguage.InIntervalExpression
import com.shs.hl.hearingLanguage.IntRangeExpression
import com.shs.hl.hearingLanguage.IntSetExpression
import com.shs.hl.hearingLanguage.IsCurveApplicable
import com.shs.hl.hearingLanguage.IsMaxExpression
import com.shs.hl.hearingLanguage.IsMinExpression
import com.shs.hl.hearingLanguage.IsOffsAppExpression
import com.shs.hl.hearingLanguage.IsValidExpression
import com.shs.hl.hearingLanguage.LocalVariableDeclaration
import com.shs.hl.hearingLanguage.LocalVariableDeclarationStatement
import com.shs.hl.hearingLanguage.LogChanCountExpression
import com.shs.hl.hearingLanguage.Minus
import com.shs.hl.hearingLanguage.MinusEqualsStatement
import com.shs.hl.hearingLanguage.Module
import com.shs.hl.hearingLanguage.Multi
import com.shs.hl.hearingLanguage.MultiplicationEqualsStatement
import com.shs.hl.hearingLanguage.Namespace
import com.shs.hl.hearingLanguage.NotExpression
import com.shs.hl.hearingLanguage.NullExpression
import com.shs.hl.hearingLanguage.NumberLiteral
import com.shs.hl.hearingLanguage.OppositeExpression
import com.shs.hl.hearingLanguage.Or
import com.shs.hl.hearingLanguage.PackageKind
import com.shs.hl.hearingLanguage.ParameterDeclaration
import com.shs.hl.hearingLanguage.ParameterReadExpression
import com.shs.hl.hearingLanguage.ParenExpr
import com.shs.hl.hearingLanguage.Plus
import com.shs.hl.hearingLanguage.PlusEqualsStatement
import com.shs.hl.hearingLanguage.PreDecrementExpr
import com.shs.hl.hearingLanguage.PreIncrementExpr
import com.shs.hl.hearingLanguage.ReturnStatement
import com.shs.hl.hearingLanguage.SetStepsAboveMin
import com.shs.hl.hearingLanguage.SetStepsBelowMax
import com.shs.hl.hearingLanguage.SitExpression
import com.shs.hl.hearingLanguage.Smaller
import com.shs.hl.hearingLanguage.SmallerOrEquals
import com.shs.hl.hearingLanguage.StatementList
import com.shs.hl.hearingLanguage.SwitchStatement
import com.shs.hl.hearingLanguage.SymbolReference
import com.shs.hl.hearingLanguage.TextLiteral
import com.shs.hl.hearingLanguage.TrueLiteral
import com.shs.hl.hearingLanguage.UnEquals
import com.shs.hl.hearingLanguage.WhileStatement
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess

class HLTSyntaxTreeWalkerBase
{
	protected LRValueSwitch lrValueSwitch = LRValueSwitch.RValue

	def walk(Resource resource, IFileSystemAccess fsa)
	{
		var fileName = "";
		var error = "";

		for (namesp : resource.allContents.toIterable.filter(typeof(Namespace)))
		{
			if (isModule(namesp.eContainer))
			{
				val module = namesp.eContainer as Module
				for (namespace : module.namespaces)
				{
					for (package : namespace.namespaces)
					{
						if (package.isIsPackage)
						{
							fileName = package.name

							try
							{
								walkPackage(package);
							}
							catch (Exception e)
							{
								error += "error: " + e.toString + "\n"

							//								var s = ""
							//								e.printStackTrace(new PrintWriter(s))
							//								error += s
							}
						}
					}
				}
			}
		}

		//		fsa.generateFile(
		//			"d:/hl_analysis/generated_" + fileName + ".hlt",
		//			error + handler.log
		//		)
		fsa.generateFile(
			"/log/" + fileName + ".log",
			error + "\n" + log
		)
	}

	protected def getLog()
	{
		return ""
	}

	protected def void walkPackage(Namespace ^package)
	{
		if (^package.packKind.equals(PackageKind.LIB))
		{
			walkLibrary(^package)
		}
		else
		{
			walkApplicationMacro(^package)
		}
	}

	protected def void walkApplicationMacro(Namespace macro)
	{
		for (symbol : macro.functions)
		{
			if (symbol instanceof FunctionDeclaration)
			{
				walkFunction(symbol as FunctionDeclaration);
			}
		}
	}

	protected def void walkLibrary(Namespace macroPackage)
	{
		for (symbol : macroPackage.functions)
		{
			if (symbol instanceof FunctionDeclaration)
			{
				walkFunction(symbol as FunctionDeclaration);
			}
		}
	}

	protected def void walkFunction(FunctionDeclaration function)
	{
		walkStatementList(function.body)
	}

	protected def void walkStatementList(StatementList body)
	{
		for (statement : body.statements)
		{
			switch (statement)
			{
				case statement instanceof LocalVariableDeclarationStatement:
				{
					walkLocalVariableDeclarationStatement(statement as LocalVariableDeclarationStatement)
				}
				case statement instanceof ReturnStatement:
				{
					walkReturnStatement(statement as ReturnStatement)
				}
				case statement instanceof IfStatement:
				{
					walkIfStatement(statement as IfStatement)
				}
				case statement instanceof BreakStatement:
				{
					neverGonnaHappen
				}
				case statement instanceof ForeachStatement:
				{
					neverGonnaHappen
				}
				case statement instanceof SwitchStatement:
				{
					walkSwitchStatement(statement as SwitchStatement)
				}
				case statement instanceof WhileStatement:
				{
					walkWhileStatement(statement as WhileStatement)
				}
				case statement instanceof DoWhileStatement:
				{
					neverGonnaHappen
				}
				case statement instanceof ExpressionStatement:
				{
					var expressionStmt = statement as ExpressionStatement
					walkExpression(expressionStmt.expr)
				}
				case statement instanceof BuiltinStatement:
				{
					// TODO
				}
				case statement instanceof AssignmentLikeThings:
				{
					var assignmentLike = statement as AssignmentLikeThings
					switch (assignmentLike)
					{
						case assignmentLike instanceof AssignmentStatement:
						{
							walkAssignmentStatement(assignmentLike as AssignmentStatement)
						}
						case assignmentLike instanceof PlusEqualsStatement:
						{
							walkPlusEqualsStatement(assignmentLike as PlusEqualsStatement)
						}
						case assignmentLike instanceof MinusEqualsStatement:
						{
						}
						case assignmentLike instanceof MultiplicationEqualsStatement:
						{
						}
						case assignmentLike instanceof BitWiseAndEqualsStatement:
						{
						}
						case assignmentLike instanceof BitWiseOrEqualsStatement:
						{
						}
					}
				}
			}
		}
	}

	protected def void walkWhileStatement(WhileStatement statement)
	{
		walkExpression(statement.expr as Expression)
		walkStatementList(statement.body)
	}

	protected def void walkSwitchStatement(SwitchStatement switchStmt)
	{
		walkExpression(switchStmt.expr as Expression)
		for (^case : switchStmt.cases)
		{
			walkExpression(^case.expr)
			walkStatementList(^case.body)
		}
		walkStatementList(switchStmt.defaultBlock)
	}

	protected def walkAssignmentStatement(AssignmentStatement assign)
	{
		walkExpression(assign.lvalue)
		walkExpression(assign.rvalue)
	}

	protected def walkPlusEqualsStatement(PlusEqualsStatement assign)
	{
		walkExpression(assign.lvalue)
		walkExpression(assign.rvalue)
	}

	protected def walkReturnStatement(ReturnStatement returnStmt)
	{
		walkExpression(returnStmt.expr as Expression)
	}

	protected def walkLocalVariableDeclarationStatement(LocalVariableDeclarationStatement statement)
	{
		walkLocalVariableDeclaration(statement.^var as LocalVariableDeclaration)
	}

	protected def walkLocalVariableDeclaration(LocalVariableDeclaration declaration)
	{
		walkExpression(declaration.init as Expression)
	}

	protected def void walkIfStatement(IfStatement ifStmt)
	{
		walkIfCondition(ifStmt.condition as Expression)

		for (elseIf : ifStmt.elseIfs)
		{
			walkElseIf(elseIf as ElseIf)
		}

		if (ifStmt.elseBody != null)
		{
			walkElse(ifStmt.elseBody)
		}
	}

	protected def void walkIfCondition(Expression condition)
	{
		walkExpression(condition)
	}

	protected def void walkElseIf(ElseIf elseIf)
	{
		walkExpression(elseIf.condition)
		walkStatementList(elseIf.body)
	}

	protected def void walkElse(StatementList list)
	{
	}

	protected def void walkExpression(Expression expression)
	{
		switch (expression)
		{
			// LogicalLevel
			case expression instanceof And:
			{
				walkAndExpression(expression as And)
			}
			case expression instanceof Or:
			{
				walkOrExpression(expression as Or)
			}
			// ComparisonLevel
			case expression instanceof Equals:
			{
				walkEqualsExpression(expression as Equals)
			}
			case expression instanceof UnEquals:
			{
				walkUnEqualsExpression(expression as UnEquals)
			}
			case expression instanceof Greater:
			{
				walkGreaterExpression(expression as Greater)
			}
			case expression instanceof Smaller:
			{
				walkSmallerExpression(expression as Smaller)
			}
			case expression instanceof EnumRight:
			{
				walkExpression((expression as EnumRight).left)
				walkExpression((expression as EnumRight).right)
			}
			case expression instanceof EnumLeft:
			{
				walkExpression((expression as EnumLeft).left)
				walkExpression((expression as EnumLeft).right)
			}
			case expression instanceof GreaterOrEquals:
			{
				walkGreaterOrEqualsExpression(expression as GreaterOrEquals)
			}
			case expression instanceof SmallerOrEquals:
			{
				walkSmallerOrEqualsExpression(expression as SmallerOrEquals)
			}
			// AdditionLevel
			case expression instanceof Plus:
			{
				walkExpression((expression as Plus).left)
				walkExpression((expression as Plus).right)
			}
			case expression instanceof Minus:
			{
				walkExpression((expression as Minus).left)
				walkExpression((expression as Minus).right)
			}
			case expression instanceof BitOr:
			{
				walkExpression((expression as BitOr).left)
				walkExpression((expression as BitOr).right)
			}
			case expression instanceof BitXor:
			{
				walkExpression((expression as BitXor).left)
				walkExpression((expression as BitXor).right)
			}
			// MultiplicationLevel
			case expression instanceof Multi:
			{
				walkExpression((expression as Multi).left)
				walkExpression((expression as Multi).right)
			}
			case expression instanceof BitCheck:
			{
				walkExpression((expression as BitCheck).left)
				walkExpression((expression as BitCheck).right)
			}
			case expression instanceof BitAnd:
			{
				walkExpression((expression as BitAnd).left)
				walkExpression((expression as BitAnd).right)
			}
			// UnaryOperatorLevel2
			case expression instanceof BuiltInCall:
			{
				walkExpression((expression as BuiltInCall).expr)
				for (actualArg : (expression as BuiltInCall).actualArgs)
				{
					walkExpression(actualArg)
				}
			}
			case expression instanceof IntRangeExpression:
			{
				neverGonnaHappen
			}
			case expression instanceof InIntervalExpression:
			{
				neverGonnaHappen
			}
			// UnaryOperatorLevel1
			case expression instanceof ArrayAccessExpression:
			{
				walkExpression((expression as ArrayAccessExpression).expr)
				walkExpression((expression as ArrayAccessExpression).index)
			}
			// PrimaryExpression
			case expression instanceof TrueLiteral:
			{
				walkTrueLiteral(expression as TrueLiteral)
			}
			case expression instanceof FalseLiteral:
			{
				walkFalseLiteral(expression as FalseLiteral)
			}
			case expression instanceof BuiltInMethodCall:
			{
			}
			case expression instanceof ParenExpr:
			{
			}
			case expression instanceof NumberLiteral:
			{
				walkNumberLiteral(expression as NumberLiteral)
			}
			case expression instanceof TextLiteral:
			{
			}
			case expression instanceof NullExpression:
			{
			}
			case expression instanceof NotExpression:
			{
			}
			case expression instanceof PreIncrementExpr:
			{
				walkPreIncrementExpr(expression as PreIncrementExpr)
			}
			case expression instanceof PreDecrementExpr:
			{
			}
			case expression instanceof BitComplementExpr:
			{
			}
			case expression instanceof IntSetExpression:
			{
			}
			case expression instanceof ParameterReadExpression:
			{
				walkParameterRead(expression as ParameterReadExpression)
			}
			case expression instanceof SymbolReference:
			{
				val symbol = (expression as SymbolReference).symbol
				switch (symbol)
				{
					case symbol instanceof FunctionDeclaration:
					{
						walkFunctionCall(symbol as FunctionDeclaration, (expression as SymbolReference).argList)
					}
					case symbol instanceof EnumLiteral:
					{
						walkEnumLiteralReference(symbol as EnumLiteral)
					}
					case symbol instanceof LocalVariableDeclaration:
					{
						walkLocalVariableReference(symbol as LocalVariableDeclaration)
					}
					case symbol instanceof ParameterDeclaration:
					{
						walkParameterReference(symbol as ParameterDeclaration)
					}
					default:
					{
					}
				}
			}
			case expression instanceof OppositeExpression:
			{
			}
			case expression instanceof AtsideExpression:
			{
			}
			case expression instanceof SitExpression:
			{
			}
			case expression instanceof DefinedExpression:
			{
			}
			case expression instanceof IsValidExpression:
			{
			}
			case expression instanceof IsMinExpression:
			{
			}
			case expression instanceof IsMaxExpression:
			{
			}
			case expression instanceof IsOffsAppExpression:
			{
			}
			case expression instanceof LogChanCountExpression:
			{
			}
			case expression instanceof AssertGetExpression:
			{
			}
			case expression instanceof GetProposalText1:
			{
			}
			case expression instanceof GetProposalText2:
			{
			}
			case expression instanceof GetCurve:
			{
			}
			case expression instanceof ExistsCurve:
			{
			}
			case expression instanceof IsCurveApplicable:
			{
			}
			case expression instanceof ApplyCurve:
			{
			}
			case expression instanceof ExistsCurveFromLc:
			{
			}
			case expression instanceof GetCurveFromLc:
			{
			}
			case expression instanceof ExistsProgramControl:
			{
			}
			case expression instanceof ExistsVolumeControl:
			{
			}
			case expression instanceof GetStepsBelowMax:
			{
			}
			case expression instanceof SetStepsBelowMax:
			{
			}
			case expression instanceof GetStepsAboveMin:
			{
			}
			case expression instanceof SetStepsAboveMin:
			{
			}
			default:
			{
				//walkExpression(expression)
			}
		}

	}

	protected def void walkPreIncrementExpr(PreIncrementExpr expr)
	{
	}

	protected def void walkParameterReference(ParameterDeclaration declaration)
	{
	}

	protected def void walkNumberLiteral(NumberLiteral literal)
	{
	}

	protected def void walkFalseLiteral(FalseLiteral literal)
	{
	}

	protected def void walkTrueLiteral(TrueLiteral literal)
	{
	}

	protected def void walkAndExpression(And and)
	{
		walkExpression(and.left)
		walkExpression(and.right)
	}

	protected def void walkOrExpression(Or or)
	{
		walkExpression(or.left)
		walkExpression(or.right)
	}

	protected def void walkEqualsExpression(Equals equals)
	{
		walkExpression(equals.left)
		walkExpression(equals.right)
	}

	protected def void walkUnEqualsExpression(UnEquals unEquals)
	{
		walkExpression(unEquals.left)
		walkExpression(unEquals.right)
	}

	protected def void walkGreaterExpression(Greater greater)
	{
		walkExpression(greater.left)
		walkExpression(greater.right)
	}

	protected def void walkSmallerExpression(Smaller smaller)
	{
		walkExpression(smaller.left)
		walkExpression(smaller.right)
	}

	protected def void walkGreaterOrEqualsExpression(GreaterOrEquals greaterOrEquals)
	{
		walkExpression(greaterOrEquals.left)
		walkExpression(greaterOrEquals.right)
	}

	protected def void walkSmallerOrEqualsExpression(SmallerOrEquals smallerOrEquals)
	{
		walkExpression(smallerOrEquals.left)
		walkExpression(smallerOrEquals.right)
	}

	protected def void walkLocalVariableReference(LocalVariableDeclaration declaration)
	{
		walkLocalVariableDeclaration(declaration)
	}

	protected def void walkFunctionCall(FunctionDeclaration function, ArgList argList)
	{
		walkFunction(function)
	}

	protected def void walkEnumLiteralReference(EnumLiteral literal)
	{
	}

	protected def void walkParameterRead(ParameterReadExpression expression)
	{
	}

	private def void neverGonnaHappen()
	{
		//throw new RuntimeException("This is not supposed to happen!");
	}

	private def boolean isModule(EObject ob)
	{
		return (ob instanceof Module)
	}

}
