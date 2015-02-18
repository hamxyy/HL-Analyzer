package com.shs.hl.generator.halsa.evaluation

import com.shs.common.commonLanguage.EnumLiteral
import com.shs.common.commonLanguage.EnumParameter
import com.shs.hl.hearingLanguage.And
import com.shs.hl.hearingLanguage.ArgList
import com.shs.hl.hearingLanguage.AssignmentStatement
import com.shs.hl.hearingLanguage.Equals
import com.shs.hl.hearingLanguage.Expression
import com.shs.hl.hearingLanguage.FalseLiteral
import com.shs.hl.hearingLanguage.FunctionDeclaration
import com.shs.hl.hearingLanguage.Greater
import com.shs.hl.hearingLanguage.GreaterOrEquals
import com.shs.hl.hearingLanguage.IfStatement
import com.shs.hl.hearingLanguage.LocalVariableDeclaration
import com.shs.hl.hearingLanguage.LocalVariableDeclarationStatement
import com.shs.hl.hearingLanguage.Namespace
import com.shs.hl.hearingLanguage.NumberLiteral
import com.shs.hl.hearingLanguage.Or
import com.shs.hl.hearingLanguage.ParameterDeclaration
import com.shs.hl.hearingLanguage.ParameterReadExpression
import com.shs.hl.hearingLanguage.PlusEqualsStatement
import com.shs.hl.hearingLanguage.ReturnStatement
import com.shs.hl.hearingLanguage.Smaller
import com.shs.hl.hearingLanguage.SmallerOrEquals
import com.shs.hl.hearingLanguage.SwitchStatement
import com.shs.hl.hearingLanguage.TrueLiteral
import com.shs.hl.hearingLanguage.UnEquals
import java.util.ArrayList
import java.util.List
import java.util.Stack
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import com.shs.hl.generator.halsa.HalsaASTWalkerBase

class EvaluationEngine extends HalsaASTWalkerBase
{
	String functionName
	List<Object> params

	Resource resource
	IFileSystemAccess fsa

	EvaluationContext context
	StackMachine activeValues = new StackMachine
	AssignToObject lValue
	Object returnVal
	StringBuilder log = new StringBuilder

	new(Resource resource, IFileSystemAccess fsa)
	{
		this.resource = resource
		this.fsa = fsa
	}

	def executeFunction(String functionName, EvaluationContext context, List<Object> params)
	{
		this.context = context
		this.functionName = functionName
		this.params = params

		super.walk(resource, fsa)

		return returnVal
	}

	private def Object evalExpr(Expression expr)
	{
		walkExpression(expr)
		return activeValues.pop
	}

	override walkIfStatement(IfStatement ifStmt)
	{
		val conditionVal = evalExpr(ifStmt.condition) as Boolean
		if (conditionVal)
		{
			walkStatementList(ifStmt.thenBody)
		}
		else
		{
			var elseIfFulfilled = false
			for (elseIf : ifStmt.elseIfs)
			{
				if (!elseIfFulfilled && evalExpr(elseIf.condition) as Boolean)
				{
					elseIfFulfilled = true
					walkStatementList(elseIf.body)
				}
			}

			if (!elseIfFulfilled && ifStmt.elseBody != null)
			{
				walkStatementList(ifStmt.elseBody)
			}
		}
	}

	override walkSwitchStatement(SwitchStatement switchStmt)
	{
		val conditionVal = evalExpr(switchStmt.expr)
		var matched = false
		for (eachCase : switchStmt.cases)
		{
			if (!matched && evalExpr(eachCase.expr).equals(conditionVal))
			{
				matched = true
				walkStatementList(eachCase.body)
			}
		}
		if (!matched && switchStmt.defaultBlock != null)
		{
			walkStatementList(switchStmt.defaultBlock)
		}
	}

	override walkAssignmentStatement(AssignmentStatement assign)
	{
		lrValueSwitch = LRValueSwitch.LValue
		walkExpression(assign.lvalue)
		lrValueSwitch = LRValueSwitch.RValue
		walkExpression(assign.rvalue)
		val value = activeValues.pop()
		lValue.set(value)
	}

	override walkPlusEqualsStatement(PlusEqualsStatement assign)
	{
		lrValueSwitch = LRValueSwitch.LValue
		walkExpression(assign.lvalue)
		lrValueSwitch = LRValueSwitch.RValue
		walkExpression(assign.lvalue)
		walkExpression(assign.rvalue)
		val value = activeValues.pop() as Integer + activeValues.pop() as Integer
		lValue.set(value)
	}

	override walkLocalVariableReference(LocalVariableDeclaration declaration)
	{
		if (lrValueSwitch == LRValueSwitch.RValue)
		{
			activeValues.push(context.getLocal(declaration.name))
		}
		else
		{
			lValue = new AssignToLocal(context, declaration.name)
		}
	}

	override walkNumberLiteral(NumberLiteral literal)
	{
		activeValues.push(Integer.parseInt(literal.value))
	}

	override walkFalseLiteral(FalseLiteral literal)
	{
		activeValues.push(false)
	}

	override walkTrueLiteral(TrueLiteral literal)
	{
		activeValues.push(true)
	}

	override walkAndExpression(And and)
	{
		super.walkAndExpression(and)
		var leftVal = activeValues.pop() as Boolean
		var rightVal = activeValues.pop() as Boolean
		activeValues.push(leftVal && rightVal)
	}

	override walkOrExpression(Or or)
	{
		super.walkOrExpression(or)
		var leftVal = activeValues.pop() as Boolean
		var rightVal = activeValues.pop() as Boolean
		activeValues.push(leftVal || rightVal)
	}

	override walkParameterRead(ParameterReadExpression expression)
	{
		if (lrValueSwitch == LRValueSwitch.RValue)
		{
			val param = expression.param as EnumParameter
			activeValues.push(context.getGlobal(expression.scope.getName() + ":" + param.name))
		}
		else
		{
			val param = expression.param as EnumParameter
			lValue = new AssignToGlobal(context, expression.scope.getName() + ":" + param.name)
		}
	}

	override walkEnumLiteralReference(EnumLiteral symbol)
	{
		activeValues.push(symbol.name)
	}

	override walkParameterReference(ParameterDeclaration declaration)
	{
		activeValues.push(context.getLocal(declaration.name))
	}

	override walkFunctionCall(FunctionDeclaration function, ArgList argList)
	{
		context.enterNewScope()
		var actualArgs = new ArrayList
		for (arg : argList.args)
		{
			actualArgs.add(evalExpr(arg))
		}
		for (var i = 0; i < function.args.length; i++)
		{
			context.addLocalVar(function.args.get(i).name, actualArgs.get(i))
		}
		log.append((function.eContainer as Namespace).name + "." + function.name + " {\n")
		super.walkFunction(function)
		log.append("}\n")
		context.exitScope()
		activeValues.push(returnVal)
	}

	override walkFunction(FunctionDeclaration function)
	{
		if (function.name == this.functionName)
		{
			log.append((function.eContainer as Namespace).name + "." + function.name + " {\n")
			context.enterNewScope()

			for (var i = 0; i < function.args.length; i++)
			{
				context.addLocalVar(function.args.get(i).name, this.params.get(i))
			}

			try
			{
				super.walkFunction(function)
			}
			catch (ReturnException e)
			{
			}

			context.exitScope()
			log.append("}\n")
		}
	}

	override walkReturnStatement(ReturnStatement returnStmt)
	{
		super.walkReturnStatement(returnStmt)
		returnVal = activeValues.pop()
		throw new ReturnException
	}

	override walkEqualsExpression(Equals equals)
	{
		super.walkEqualsExpression(equals)
		var leftVal = activeValues.pop()
		var rightVal = activeValues.pop()
		activeValues.push(leftVal.equals(rightVal))
	}

	override walkUnEqualsExpression(UnEquals unEquals)
	{
		super.walkUnEqualsExpression(unEquals)
		var leftVal = activeValues.pop()
		var rightVal = activeValues.pop()
		activeValues.push(!leftVal.equals(rightVal))
	}

	override walkGreaterExpression(Greater greater)
	{
		super.walkGreaterExpression(greater)
		var leftVal = activeValues.pop() as Integer
		var rightVal = activeValues.pop() as Integer
		activeValues.push(leftVal > rightVal)
	}

	override walkSmallerExpression(Smaller smaller)
	{
		super.walkSmallerExpression(smaller)
		var leftVal = activeValues.pop() as Integer
		var rightVal = activeValues.pop() as Integer
		activeValues.push(leftVal < rightVal)
	}

	override walkGreaterOrEqualsExpression(GreaterOrEquals greaterOrEquals)
	{
		super.walkGreaterOrEqualsExpression(greaterOrEquals)
		var leftVal = activeValues.pop() as Integer
		var rightVal = activeValues.pop() as Integer
		activeValues.push(leftVal >= rightVal)
	}

	override walkSmallerOrEqualsExpression(SmallerOrEquals smallerOrEquals)
	{
		super.walkSmallerOrEqualsExpression(smallerOrEquals)
		var leftVal = activeValues.pop() as Integer
		var rightVal = activeValues.pop() as Integer
		activeValues.push(leftVal <= rightVal)
	}

	override walkLocalVariableDeclarationStatement(LocalVariableDeclarationStatement statement)
	{
		var localVarDeclaration = statement.^var as LocalVariableDeclaration
		walkExpression(localVarDeclaration.init as Expression)
		context.addLocalVar(localVarDeclaration.name, activeValues.pop())
	}

	override getLog()
	{
		if (returnVal == null)
		{
			return "void" + "\n" + log.toString + "\n" + context.errorLog
		}
		return returnVal.toString + "\n" + log.toString + "\n" + context.errorLog
	}

}

class ReturnException extends RuntimeException
{
}

enum LRValueSwitch
{
	LValue,
	RValue
}

interface AssignToObject
{
	def void set(Object rValue)
}

class AssignToGlobal implements AssignToObject
{
	String key
	EvaluationContext context

	new(EvaluationContext context, String key)
	{
		this.key = key
	}

	override set(Object rValue)
	{
		context.setGlobalVar(key, rValue as String)
	}
}

class AssignToLocal implements AssignToObject
{
	String key
	EvaluationContext context

	new(EvaluationContext context, String key)
	{
		this.key = key
	}

	override set(Object rValue)
	{
		context.addLocalVar(key, rValue)
	}
}

class StackMachine
{
	Object register
	Stack<Object> activeValues = new Stack

	def push(Object obj)
	{
		if (register == null)
		{
			register = obj
		}
		else
		{
			activeValues.push(register)
			register = obj
		}
	}

	def Object pop()
	{
		if (register == null)
		{
			try
			{
				return activeValues.pop()
			}
			catch (Exception e)
			{
				throw e
			}
		}
		else
		{
			var result = register
			register = null
			return result
		}
	}

	def boolean isEmpty()
	{
		return register != null || !activeValues.isEmpty
	}

}
