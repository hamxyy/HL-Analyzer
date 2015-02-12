package com.shs.hl.validation;

import static de.itemis.xtext.typesystem.util.Utils.eString;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckType;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.shs.common.commonLanguage.ChannelParameter;
import com.shs.common.commonLanguage.CommonLanguagePackage;
import com.shs.common.commonLanguage.CrvParameter;
import com.shs.common.commonLanguage.DoubleParameter;
import com.shs.common.commonLanguage.EnumLiteral;
import com.shs.common.commonLanguage.EnumParameter;
import com.shs.common.commonLanguage.IntegerParameter;
import com.shs.common.commonLanguage.SymbolDeclaration;
import com.shs.hl.hearingLanguage.AddToTGStatement;
import com.shs.hl.hearingLanguage.AppOffsStatement;
import com.shs.hl.hearingLanguage.ApplyCurve;
import com.shs.hl.hearingLanguage.ArrayAccessExpression;
import com.shs.hl.hearingLanguage.AssignmentLikeThings;
import com.shs.hl.hearingLanguage.AssignmentStatement;
import com.shs.hl.hearingLanguage.AtsideExpression;
import com.shs.hl.hearingLanguage.BreakStatement;
import com.shs.hl.hearingLanguage.BuiltInCall;
import com.shs.hl.hearingLanguage.BuiltInOp;
import com.shs.hl.hearingLanguage.Case;
import com.shs.hl.hearingLanguage.ChannelSetType;
import com.shs.hl.hearingLanguage.ChannelType;
import com.shs.hl.hearingLanguage.CurvePointType;
import com.shs.hl.hearingLanguage.CurveType;
import com.shs.hl.hearingLanguage.DefinedExpression;
import com.shs.hl.hearingLanguage.ExistsCurveFromLc;
//import com.shs.hl.hearingLanguage.DelegateNative;
import com.shs.hl.hearingLanguage.Expression;
import com.shs.hl.hearingLanguage.FittingAssistStatement;
import com.shs.hl.hearingLanguage.ForVarDeclaration;
import com.shs.hl.hearingLanguage.ForeachStatement;
import com.shs.hl.hearingLanguage.FunctionDeclaration;
import com.shs.hl.hearingLanguage.FunctionKind;
import com.shs.hl.hearingLanguage.GetCurveFromLc;
import com.shs.hl.hearingLanguage.GetStepsAboveMin;
import com.shs.hl.hearingLanguage.GetStepsBelowMax;
import com.shs.hl.hearingLanguage.HearingLanguagePackage;
import com.shs.hl.hearingLanguage.IntType;
import com.shs.hl.hearingLanguage.IsCurveApplicable;
import com.shs.hl.hearingLanguage.IsMaxExpression;
import com.shs.hl.hearingLanguage.IsMinExpression;
import com.shs.hl.hearingLanguage.IsOffsAppExpression;
import com.shs.hl.hearingLanguage.IsValidExpression;
import com.shs.hl.hearingLanguage.LevelCurvePointType;
import com.shs.hl.hearingLanguage.LevelCurvesType;
import com.shs.hl.hearingLanguage.LocalVariableDeclaration;
import com.shs.hl.hearingLanguage.LocalVariableDeclarationStatement;
import com.shs.hl.hearingLanguage.MinusEqualsStatement;
import com.shs.hl.hearingLanguage.MultToTGStatement;
import com.shs.hl.hearingLanguage.Namespace;
import com.shs.hl.hearingLanguage.NumberLiteral;
import com.shs.hl.hearingLanguage.OppositeExpression;
import com.shs.hl.hearingLanguage.PackageKind;
import com.shs.hl.hearingLanguage.ParameterDeclaration;
import com.shs.hl.hearingLanguage.ParameterReadExpression;
import com.shs.hl.hearingLanguage.PlusEqualsStatement;
import com.shs.hl.hearingLanguage.ReturnStatement;
import com.shs.hl.hearingLanguage.SetApply1IsEnabled;
import com.shs.hl.hearingLanguage.SetApply1IsVisible;
import com.shs.hl.hearingLanguage.SetApply2IsEnabled;
import com.shs.hl.hearingLanguage.SetApply2IsVisible;
import com.shs.hl.hearingLanguage.SetMaxStatement;
import com.shs.hl.hearingLanguage.SetMinStatement;
import com.shs.hl.hearingLanguage.SetProposalTextID1;
import com.shs.hl.hearingLanguage.SetProposalTextID2;
import com.shs.hl.hearingLanguage.SetStepsAboveMin;
import com.shs.hl.hearingLanguage.SetStepsBelowMax;
import com.shs.hl.hearingLanguage.SitExpression;
import com.shs.hl.hearingLanguage.StringIdType;
import com.shs.hl.hearingLanguage.SwitchStatement;
import com.shs.hl.hearingLanguage.SymbolReference;
import com.shs.hl.hearingLanguage.TestingStatement;
import com.shs.hl.hearingLanguage.TextLiteral;
import com.shs.hl.hearingLanguage.Type;
import com.shs.hl.hearingLanguage.Using;
import com.shs.hl.hearingLanguage.VoidType;
import com.shs.hl.hearingLanguage.impl.SymbolReferenceImpl;
import com.shs.hl.typesystem.HLTypesystem;

import de.itemis.xtext.typesystem.ITypesystem;
import de.itemis.xtext.typesystem.trace.TypeCalculationTrace;
import de.itemis.xtext.typesystem.util.Utils;

public class HearingLanguageJavaValidator extends AbstractHearingLanguageJavaValidator
{

	// we need the typesytem, so we let Guice inject it for us
	@Inject
	private ITypesystem ts;

	// the language packages so we cann access language constructs from Java
	private final HearingLanguagePackage hl = HearingLanguagePackage.eINSTANCE;

	/**
	 * makes sure that namespaces and packages contain only what they are
	 * allowed to contain - TODO: Make the package a subtype of namespace
	 */
	@Check
	public void checkNamespaceContents(final Namespace ctx)
	{
		if (ctx.isIsPackage())
		{
			if (!ctx.getNamespaces().isEmpty())
			{
				error("packages cannot contain further namespaces", CommonLanguagePackage.Literals.SYMBOL_DECLARATION__NAME, -1);
			}
			if (!ctx.getBuiltIns().isEmpty())
			{
				error("packages cannot contain builtins", CommonLanguagePackage.Literals.SYMBOL_DECLARATION__NAME, -1);
			}
		} else
		{
			if (!ctx.getFunctions().isEmpty())
			{
				warning("only packages should contain functions (these will not be generated!)", HearingLanguagePackage.Literals.NAMESPACE__NAME, -1);
			}
		}
	}

	/**
	 * The type of a return statement must be the same (or a subtype of) the
	 * type of the function that contains the return TODO: this should be
	 * possible via a predefined function in the type system
	 */
	@Check
	public void checkReturnType(final ReturnStatement ctx)
	{

		final TypeCalculationTrace trace = new TypeCalculationTrace();
		final FunctionDeclaration functionDecl = Utils.ancestor(ctx, FunctionDeclaration.class);
		final EObject funType = ts.typeof(functionDecl.getType(), trace.child("function", functionDecl));
		final EObject ctxType = ts.typeof(ctx, trace.child("return", ctx));

		// // ok since it is problematic to check for type if we have a void
		// type we shall perform our own test here
		// if (functionDecl.getType().eClass()==hl.getVoidType())
		// {
		//
		// if (ctxType!=null)
		// {
		// if (ctxType.eClass().equals(hl.getVoidType()))
		// {
		// error("incompatible type; expected no return type or void",
		// HearingLanguagePackage.Literals.FUNCTION_DECLARATION__TYPE, null,
		// trace.toStringArray());
		// }
		// }
		// System.out.println("EXIT ON VOID -------------------");
		// return;
		// }
		//

		// ok if we come here and one of found types is null - we cannot perform
		// following check
		//
		// if (ctxType == null)
		// {
		// String message =
		// "incompatible type: Cannot calculate type for return statement";
		// //error(message,
		// ctx.eContainingFeature(),HearingLanguagePackage.Literals.FUNCTION_DECLARATION__TYPE,
		// trace.toStringArray());
		// error(message, ctx,null,-1);
		// }
		// else
		// {
		final boolean isCompatible = ts.isCompatibleTypeOrdered(functionDecl, funType, ctx, ctxType, trace);
		if (!isCompatible)
		{
			final String message = "incompatible type; expected " + ts.typeString(funType) + ", actual " + ts.typeString(ctxType) + " (on a "
					+ ctx.eClass().getName() + ")";

			error(message, ctx, null, -1);
			// error(message,ctx,HearingLanguagePackage.Literals.FUNCTION_DECLARATION__TYPE,
			// trace.toStringArray());
		}
		// }

		/*
		 * TypeCalculationTrace trace = new TypeCalculationTrace();
		 * FunctionDeclaration functionDecl = Utils.ancestor(ctx,
		 * FunctionDeclaration.class); //EObject ctxType = ts.typeof(
		 * ctx.eContainingFeature(), trace.child("return", ctx) ); EObject
		 * ctxType = ts.typeof( ctx.eContainingFeature(), trace.child("return",
		 * ctx) );
		 * 
		 * EObject funType = ts.typeof( functionDecl.getType(),
		 * trace.child("function", functionDecl) );
		 * 
		 * boolean isCompatibleReturnType = ts.isCompatibleTypeOrdered(
		 * functionDecl.getType(), funType, ctx.eContainingFeature(), ctxType,
		 * trace ); if ( !isCompatibleReturnType ) {
		 * error("incompatible type; expected "
		 * +ts.typeString(funType)+", actual "+
		 * ts.typeString(ctxType)+" (on a "+ctx.eClass().getName()+")",
		 * HearingLanguagePackage.Literals.FUNCTION_DECLARATION__TYPE, null,
		 * trace.toStringArray() ); }
		 */
	}

	/**
	 * defined can only be used on ParameterReadExpression or OppositeExpression
	 */
	@Check
	public void checkDefinedExpression(final DefinedExpression ctx)
	{
		final Expression expr = ctx.getExpr();
		if (!(expr instanceof ParameterReadExpression) && !(expr instanceof OppositeExpression))
		{
			error("isDefined(..) can only be called on parameter reads", ctx, null, -1);
		}
		if (expr instanceof ParameterReadExpression)
		{
			final ParameterReadExpression pre = (ParameterReadExpression) expr;
			final String scope = pre.getScope().toString();
			if (!scope.equals("pat") && !scope.equals("crv") && !scope.equals("levelcrv"))
			{
				error("isDefined(..) can only be used with pat, , crv and levelcrv (on a " + ctx.eClass().getName() + ")", ctx, null, -1);
			}
		}
	}

	/**
	 * IsValid can only be used on enum literals
	 */
	@Check
	public void checkIsValidExpression(final IsValidExpression ctx)
	{
		final Expression expr = ctx.getExpr();

		if (expr instanceof SymbolReference)
		{
			final SymbolDeclaration symbol = ((SymbolReference) expr).getSymbol();
			if (symbol instanceof EnumLiteral)
			{
				return;
			}
		}
		error("IsValid(..) can only be called on enum literals", ctx, null, -1);
	}

	/**
	 * Make sure the opposite marker is only valid for certain scopes
	 */
	@Check
	public void checkOppositeMarker(final OppositeExpression ctx)
	{
		final Expression expr = ctx.getExpr();
		if (expr instanceof ParameterReadExpression)
		{
			final ParameterReadExpression pre = (ParameterReadExpression) expr;
			final String scope = pre.getScope().toString();
			if (!scope.equals("cap") && !scope.equals("acp") && !scope.equals("crv") && !scope.equals("levelcrv") && !scope.equals("hi"))

			{
				error("opposite operator can only be used with cap, acp, crv and levelcrv (on a " + ctx.eClass().getName() + ")", ctx, null, -1);
			}
		}
	}

	/**
	 * report assignments to const variables TODO: this check should be
	 * integrated with isValidLValue
	 */
	@Check
	public void checkInitForConst(final AssignmentLikeThings ctx)
	{
		Expression left = null;
		Expression right = null;
		if (ctx instanceof AssignmentStatement)
		{
			left = ((AssignmentStatement) ctx).getLvalue();
			right = ((AssignmentStatement) ctx).getRvalue();
		}
		if (ctx instanceof PlusEqualsStatement)
		{
			left = ((PlusEqualsStatement) ctx).getLvalue();
			right = ((PlusEqualsStatement) ctx).getRvalue();
		}
		if (ctx instanceof MinusEqualsStatement)
		{
			left = ((MinusEqualsStatement) ctx).getLvalue();
			right = ((MinusEqualsStatement) ctx).getRvalue();
		}
		if (left == null)
			return; // should never happen!
		if (right == null)
			return; // should never happen!
		if (left instanceof ParameterReadExpression)
		{
			final ParameterReadExpression pre = (ParameterReadExpression) left;
			EObject param = pre.getParam();
			if (param != null)
			{
				if (param.eIsProxy())
				{
					param = EcoreUtil.resolve(param, param.eResource().getResourceSet());
				}
				if (param instanceof DoubleParameter)
				{
					final DoubleParameter dp = (DoubleParameter) pre.getParam();
					if (dp.getPrecSpec() != null)
					{
						if (right instanceof NumberLiteral)
						{
							@SuppressWarnings("unused")
							final String val = ((NumberLiteral) right).getValue();
							warning("precspec Dingens", ctx, null, -1);
						}
					}
				}
			}
		}
		if (left instanceof SymbolReference)
		{
			final SymbolDeclaration d = ((SymbolReference) left).getSymbol();
			if (d instanceof LocalVariableDeclaration)
			{
				if (((LocalVariableDeclaration) d).isIsConst())
				{
					error("cannot assign to const variables (on a " + ctx.eClass().getName() + ")", ctx, null, -1);
				}
				if (ts.typeof(d, new TypeCalculationTrace()) instanceof StringIdType)
				{
					error("cannot assign to short string types; they are implicitly const (on a " + ctx.eClass().getName() + ")", ctx, null, -1);
				}
			} else if (d instanceof ParameterDeclaration)
			{
				// should never be executed!?
				error("cannot assign to parameters (on a " + ctx.eClass().getName() + ")", ctx, null, -1);
			}
		}

	}

	/**
	 * consts need an intialization value
	 */
	@Check
	public void checkInitForConst(final LocalVariableDeclaration ctx)
	{
		if (ctx.isIsConst() && ctx.getInit() == null)
		{
			error("const variables need an initialization value", ctx, null, -1);
		}
	}

	/**
	 * a warning in case the .* is missing for a using
	 */
	@Check
	public void checkAsteriskInImport(final Using ctx)
	{
		if (!ctx.getImportedNamespace().endsWith("*"))
		{
			warning("an .* is likely missing", ctx, null, 0);
		}
	}

	/**
	 * parameter types cannot be void
	 */
	@Check
	public void argTypeCannotBeVoid(final ParameterDeclaration ctx)
	{
		if (ctx.getType() instanceof VoidType)
		{
			error("parameters cannot be void types", ctx, null, -1);
		}
	}

	/**
	 * Checks the valid dependencies on function calls - see implementation
	 */
	@Check
	public void checkFunctionCallStructure(final SymbolReference ctx)
	{
		final SymbolDeclaration sym = ctx.getSymbol();
		// if we call a function
		if (sym instanceof FunctionDeclaration)
		{
			final FunctionDeclaration fd = (FunctionDeclaration) sym;
			// test functions can never be called other functions,
			// only by the testing framework
			if (fd.getKind().equals(FunctionKind.TEST))
			{
				error("test functions cannot be called from other functions", ctx, null, 0);
			}
			// protected functions can only be called from the same namespace
			// or from a child namespace
			if (fd.getKind().equals(FunctionKind.PROTECTED))
			{
				// Namespace callsiteNS =
				// Utils.ancestor(ctx.eContainingFeature(), Namespace.class);
				final Namespace callsiteNS = Utils.ancestor(ctx, Namespace.class);
				final Namespace functionNS = Utils.ancestor(sym, Namespace.class);
				if (callsiteNS != functionNS)
				{
					final List<EObject> ancestors = Utils.ancestors(callsiteNS, hl.getNamespace());
					if (!ancestors.contains(functionNS))
					{
						error("protected functions cannot be called from outside their namespace", ctx, null, 0);
					}
				}
			}
			// we don't have to check for public functions,
			// since they can be called from anywhere if they are visiblethe
		}
	}

	/**
	 * makes sure that arguments for a symbol reference are only allowed if the
	 * symbol reference references a function It also checks the number and
	 * types of arguments
	 */
	@Check
	public void checkArgsForFuncCall(final SymbolReference ctx)
	{
		final SymbolDeclaration sym = ctx.getSymbol();
		if (sym instanceof FunctionDeclaration)
		{
			// if we refer to a function, we always need an argument list
			if (ctx.getArgList() == null)
			{
				error("function call needs arguments, possibly an empty list ()", ctx, null, 0);
			} else
			{
				// then we check the number of arguments
				final FunctionDeclaration fun = (FunctionDeclaration) sym;
				final int expected = fun.getArgs().size();

				if (expected != ctx.getArgList().getArgs().size())
				{
					error("wrong number of arguments; " + expected + " expected " + argListAsString(fun), ctx, null, 0);
					return;
				}
				// and finally, we make sure, that the type of the actual
				// arguments
				// is compatible with the types of the formal arguments
				for (int i = 0; i < expected; i++)
				{
					final TypeCalculationTrace trace = new TypeCalculationTrace();
					final Expression actual = ctx.getArgList().getArgs().get(i);
					final ParameterDeclaration formal = (ParameterDeclaration) fun.getArgs().get(i);
					final EObject formalType = ts.typeof(formal, trace);
					final EObject actualType = ts.typeof(actual, trace);
					if (!ts.isCompatibleTypeOrdered(formal, formalType, actual, actualType, trace))
					{
						// error(
						// "incompatible type; expected "+ts.typeString(formalType)+", actual "+ts.typeString(actualType),
						// actual.eContainingFeature(), i);
						error("incompatible type; expected " + ts.typeString(formalType) + ", actual " + ts.typeString(actualType), actual, null, i);
					}
				}

			}
		} else
		{
			if (sym != null && ctx.getArgList() != null)
			{
				error("parameter references cannot have arguments", ctx, null, 0);
			}
		}
	}

	/**
	 * checks the number and types of the arguments for a BuiltInCall this
	 * function works the same way as the above one, just for built-in calls
	 * TODO: check if built-ins actually need arguments
	 */
	@Check
	public void checkArgsForBuiltInCall(final BuiltInCall ctx)
	{
		final BuiltInOp builtin = ctx.getBuiltIn();
		if (!builtin.getArgsTypes().isEmpty())
		{
			final int expected = builtin.getArgsTypes().size();
			if (expected != ctx.getActualArgs().size())
			{
				error("wrong number of arguments; " + expected + " expected (on a " + ctx.eClass().getName() + ")", ctx, null, 0);
				return;
			}
			for (int i = 0; i < expected; i++)
			{
				final TypeCalculationTrace trace = new TypeCalculationTrace();
				final Expression actual = ctx.getActualArgs().get(i);
				final Type formal = builtin.getArgsTypes().get(i);
				final EObject formalType = ts.typeof(formal, trace);
				final EObject actualType = ts.typeof(actual, trace);
				if (!ts.isCompatibleTypeOrdered(formal, formalType, actual, actualType, trace))
				{
					error("incompatible type; expected " + ts.typeString(formalType) + ", actual " + ts.typeString(actualType) + " (on a "
							+ actual.eClass().getName() + ")", actual.eContainingFeature(), i);
				}
			}
		}
	}

	/**
	 * helper function that creates a string representation of the argument list
	 * for use in error messages
	 */
	private String argListAsString(final FunctionDeclaration fun)
	{
		final StringBuffer bf = new StringBuffer();
		bf.append("(");
		int c = 0;
		for (final SymbolDeclaration s : fun.getArgs())
		{
			final ParameterDeclaration p = (ParameterDeclaration) s;
			if (c > 0)
				bf.append(", ");
			bf.append(eString(p.getType()));
			bf.append(" ");
			bf.append(p.getName());
			c++;
		}
		bf.append(")");
		return bf.toString();
	}

	/**
	 * Hook to check type system rules
	 */
	@Check(CheckType.NORMAL)
	public void validateTypes(final EObject m)
	{
		ts.checkTypesystemConstraints(m, this);
	}

	/**
	 * Checks whether the thing on the left side of an assignmet is actually an
	 * lvalue
	 */
	@Check
	public void checkForValidLValue(final AssignmentStatement ass)
	{

		if (ass.getLvalue() instanceof AtsideExpression)
		{
			if (!isValidAtSide((AtsideExpression) ass.getLvalue(), ass.getRvalue()))
			{
				error("Specified value cannot be assigned to AtSide expression", ((AtsideExpression) ass.getLvalue()).getExpr().eContainingFeature(), -1);
			}
		} else if (!isValidWritableLValue(ass.getLvalue()))
		{
			error("cannot be assigned (on a " + ass.getLvalue().eClass().getName() + ")", ass.getLvalue(), null, -1);
		}

	}

	/**
	 * Checks whether the thing on the left side of += is actually an lvalue
	 */
	@Check
	public void checkForValidLValue(final PlusEqualsStatement ass)
	{
		if (!isValidWritableLValue(ass.getLvalue()))
		{
			error("cannot be assigned (on a " + ass.getLvalue().eClass().getName() + ")", ass.getLvalue(), ass.getLvalue().eContainingFeature(), -1);
		} else if (ass.getLvalue() instanceof AtsideExpression)
		{
			error("AtSide operator is not allowed", ass.getLvalue().eContainingFeature(), -1);
		}
	}

	/**
	 * Checks whether the thing on the left side of -= is actually an lvalue
	 */
	@Check
	public void checkForValidLValue(final MinusEqualsStatement ass)
	{
		if (!isValidWritableLValue(ass.getLvalue()))
		{
			error("cannot be assigned (on a " + ass.getLvalue().eClass().getName() + ")", ass.getLvalue().eContainingFeature(), -1);
		} else if (ass.getLvalue() instanceof AtsideExpression)
		{
			error("AtSide operator is not allowed", ass.getLvalue().eContainingFeature(), -1);
		}
	}

	/**
	 * deteremines whether an expression is an lvalue, ie. can be assigned to
	 */
	private boolean isValidWritableLValue(final Expression expr)
	{
		Expression toBeChecked = expr;

		if (toBeChecked instanceof AtsideExpression)
		{
			toBeChecked = ((AtsideExpression) toBeChecked).getExpr();
		}

		// check the "contents" of the array access, not the array itself
		if (toBeChecked instanceof ArrayAccessExpression)
		{
			toBeChecked = ((ArrayAccessExpression) expr).getExpr();
		}

		if (toBeChecked instanceof SymbolReference)
		{
			final SymbolDeclaration symbol = ((SymbolReference) expr).getSymbol();
			if (symbol instanceof LocalVariableDeclaration || symbol instanceof ParameterDeclaration || symbol instanceof ForVarDeclaration)
				return true;
		}

		if (toBeChecked instanceof ParameterReadExpression)
		{
			final SymbolDeclaration param = ((ParameterReadExpression) toBeChecked).getParam();

			if (param instanceof ChannelParameter)
			{
				return !((ChannelParameter) param).isReadonly();
			}
			if (param instanceof EnumParameter)
			{
				return !((EnumParameter) param).isReadonly();
			}
			if (param instanceof IntegerParameter)
			{
				return !((IntegerParameter) param).isReadonly();
			}
			if (param instanceof DoubleParameter)
			{
				return !((DoubleParameter) param).isReadonly();
			}
			if (param instanceof CrvParameter)
			{
				return !((CrvParameter) param).isReadonly();
			}
		}

		if (toBeChecked instanceof BuiltInCall)
		{
			final BuiltInCall call = (BuiltInCall) toBeChecked;
			final BuiltInOp op = call.getBuiltIn();
			return op.isIsWritable();
		}

		return false;

	}

	private boolean isWritableSymbolDeclaration(final Expression param)
	{
		if (param instanceof SymbolDeclaration)
		{
			return isWritableSymbolDeclaration((SymbolDeclaration) param);
		}
		if (param instanceof ParameterReadExpression)
		{
			return isWritableSymbolDeclaration((ParameterReadExpression) param);
		}
		return false;
	}

	private boolean isWritableSymbolDeclaration(final ParameterReadExpression expr)
	{
		return isWritableSymbolDeclaration(expr.getParam());
	}

	@SuppressWarnings("unused")
	private boolean isWritableSymbolDeclaration(final SymbolReference ref)
	{
		return isWritableSymbolDeclaration(ref.getSymbol());
	}

	private boolean isWritableSymbolDeclaration(final SymbolDeclaration param)
	{
		if (param == null)
			return false;
		if (param instanceof ChannelParameter)
		{
			return !((ChannelParameter) param).isReadonly();
		}
		if (param instanceof EnumParameter)
		{
			return !((EnumParameter) param).isReadonly();
		}
		if (param instanceof IntegerParameter)
		{
			return !((IntegerParameter) param).isReadonly();
		}
		if (param instanceof DoubleParameter)
		{
			return !((DoubleParameter) param).isReadonly();
		}
		if (param instanceof CrvParameter)
		{
			return !((CrvParameter) param).isReadonly();
		}
		return false;
	}

	private boolean isValidAtSide(final AtsideExpression leftArg, final Expression rightSide)
	{
		final Expression leftSide = leftArg.getExpr();
		if (leftSide == null || rightSide == null)
			return false;
		// only identical types can be assigned

		SymbolDeclaration referenceSymbol = null;

		if (leftSide instanceof ParameterReadExpression)
		{
			final ParameterReadExpression left = (ParameterReadExpression) leftSide;
			referenceSymbol = left.getParam();
		}

		if (rightSide instanceof SymbolReference)
		{
			if (((SymbolReference) rightSide).getSymbol().eContainer().equals(referenceSymbol))
			{
				if (isWritableSymbolDeclaration(leftSide))
				{
					return true;
				}
				error("Specified data type is not writable", leftSide.eContainingFeature(), -1);
			} else
			{
				error("Different types are not supported", rightSide.eContainingFeature(), -1);
			}
		}
		return false;
	}

	/**
	 * the type of the case expressions must be compatible to the type of the
	 * switch
	 */
	@Check
	public void checkCases(final Case c)
	{

		final TypeCalculationTrace trace = new TypeCalculationTrace();
		final SwitchStatement switchSta = (SwitchStatement) c.eContainer();
		final EObject switchStaType = ts.typeof(switchSta.getExpr(), trace);
		final EObject caseExprType = ts.typeof(c.getExpr(), trace);

		if (!ts.isCompatibleTypeOrdered(switchSta.getExpr(), switchStaType, c.getExpr(), caseExprType, trace))
		{
			error("incompatible type; expected " + ts.typeString(switchStaType) + ", actual " + ts.typeString(caseExprType), c, null, 0);
		}

		/*
		 * old implementation - check --- TODO: CHECK IF OLD IMPLEMENTATION DOES
		 * NOT BETTER FIT
		 */
		// TypeCalculationTrace trace = new TypeCalculationTrace();
		// SwitchStatement switchSta = (SwitchStatement) c.eContainer();
		// EObject switchStaType = ts.typeof(switchSta.getExpr(), trace);
		// EObject caseExprType = ts.typeof(c.getExpr(), trace);
		//
		// if(
		// ( switchSta.getExpr().eClass() == hl.getSymbolReference() &&
		// c.getExpr().eClass() == hl.getNumberLiteral()
		// ) == false
		// &&
		// ( switchSta.getExpr().eClass() == hl.getParameterReadExpression()
		// && c.getExpr().eClass() == hl.getSymbolReference()
		// ) == false
		// )
		// {
		// error("switch/case contains invalid types" ,c,null,-1);
		// }
		//
		//
		//
		// Expression expr = c.getExpr();
		// if (expr instanceof SymbolReference)
		// {
		// SymbolReference symb = (SymbolReference)expr;
		// if (symb.getSymbol() instanceof EnumLiteral)
		// {
		// EnumLiteral enumLit = (EnumLiteral)symb.getSymbol();
		// if (switchSta.getExpr() instanceof ParameterReadExpression)
		// {
		// if
		// (enumLit.eContainer()!=((ParameterReadExpression)switchSta.getExpr()).getParam())
		// {
		// error ("Invalid data element for swicht case",symb,null,-1);
		// }
		// }
		//
		// }
		// }
		//
		// if ( !ts.isCompatibleTypeOrdered(switchSta.getExpr(), switchStaType,
		// c.getExpr(), caseExprType, trace)) {
		// error("incompatible type; expected "+ts.typeString(switchStaType)+
		// ", actual "+ts.typeString(caseExprType), c,null, 0 );
		// }

		checkUniqueCase(c);

	}

	// for some reasons the dublicate componente not functioning on test
	// functions

	@Check
	public void checkDuplicates(final FunctionDeclaration decl)
	{
		final List<FunctionDeclaration> functions = EcoreUtil2.getAllContentsOfType(decl.eContainer(), FunctionDeclaration.class);
		for (final FunctionDeclaration function : functions)
		{
			final FunctionDeclaration[] dublics = Iterables.toArray(Iterables.filter(functions, new Predicate<FunctionDeclaration>()
			{
				@Override
				public boolean apply(final FunctionDeclaration input)
				{
					return input.getName().equals(function.getName());
				}
			}), FunctionDeclaration.class);
			if (dublics.length > 1)
				for (final FunctionDeclaration fDecl : dublics)
				{
					error("Duplicate function ", fDecl, CommonLanguagePackage.Literals.SYMBOL_DECLARATION__NAME, -1);
				}
		}

	}

	private void checkUniqueCase(final Case c)
	{
		// final SwitchStatement switchsT = (SwitchStatement) c.eContainer();
		// final Set<String> expressions = new HashSet<String>();
		// final List<Case> cases = EcoreUtil2.getAllContentsOfType(switchsT,
		// Case.class);
		//
		// for (final Case cs : cases) {
		// String expr = "";
		// if (cs.getExpr() instanceof NumberLiteral) {
		// final NumberLiteral lt = (NumberLiteral) cs.getExpr();
		// expr = lt.getValue();
		// }
		// if (cs.getExpr() instanceof TextLiteral) {
		// final TextLiteral lt = (TextLiteral) cs.getExpr();
		// expr = lt.getValue();
		// }
		//
		// if (!expr.isEmpty() && !expressions.add(expr)) {
		// error("Case already defined: ", cs, null, 0);
		// }
		// }
	}

	/**
	 * non-void functions must have return statement and void functions should
	 * not
	 */
	@Check
	public void checkForReturnStatement(final FunctionDeclaration fun)
	{
		final List<ReturnStatement> returns = EcoreUtil2.getAllContentsOfType(fun, ReturnStatement.class);
		if (fun.getType().eClass() != hl.getVoidType() && !fun.getKind().equals(FunctionKind.NATIVE))
		{
			if (returns.isEmpty())
				error("non-void functions need a return statement!", fun, CommonLanguagePackage.Literals.SYMBOL_DECLARATION__NAME, -1);
		}

		if (fun.getType().eClass() == hl.getVoidType())
		{
			for (final ReturnStatement stmnt : returns)
			{
				if (stmnt.getExpr() != null)
				{
					error("Void functions cannot return a value", fun, CommonLanguagePackage.Literals.SYMBOL_DECLARATION__NAME, -1);
					return;
				}
			}
		}

	}

	@Check
	public void checkVoidSymbolDeclaration(final LocalVariableDeclarationStatement decl)
	{
		if (decl.getVar() instanceof LocalVariableDeclaration)
		{
			final LocalVariableDeclaration variable = (LocalVariableDeclaration) decl.getVar();
			if (variable.getType().eClass() == hl.getVoidType())
			{
				error("void type can not be used to declare a variable", variable, CommonLanguagePackage.Literals.SYMBOL_DECLARATION__NAME, -1);
			}
		}
	}

	// /**
	// * native functions must have exactly one statement in their body that is
	// a
	// * DelegateNative statement
	// */
	// @Check
	// public void checkNativeFunctions(final FunctionDeclaration d) {
	// if (d.getKind().equals(FunctionKind.NATIVE)) {
	// final EList<Statement> statements = d.getBody().getStatements();
	// if (statements.size() != 1 || !(statements.get(0) instanceof
	// DelegateNative))
	// {
	// error("native functions can only contain the delegateNative statement",
	// d, CommonLanguagePackage.Literals.SYMBOL_DECLARATION__NAME, -1);
	// }
	// }
	//
	// }

	// /**
	// * and DelegateNative may only be used within native functions
	// */
	// @Check
	// public void checkNativeFunctions(final DelegateNative ctx) {
	// final FunctionDeclaration fun = Utils.ancestor(ctx,
	// FunctionDeclaration.class);
	// if (!fun.getKind().equals(FunctionKind.NATIVE)) {
	// error("delegateNative can only appear in native functions", ctx, null,
	// -1);
	// }
	// }

	/**
	 * ensure that test functions have no arguments
	 */
	@Check
	public void checkNoArgsForTest(final FunctionDeclaration ctx)
	{
		if (ctx.getKind().equals(FunctionKind.TEST))
		{
			if (ctx.getArgs().size() != 0)
			{
				error("test functions cannot have arguments", CommonLanguagePackage.Literals.SYMBOL_DECLARATION__NAME, -1);
			}
		}
	}

	@Check
	public void checkArgNamesSane(final FunctionDeclaration ctx)
	{
		if (ctx.getArgs().size() > 1)
		{
			final List<String> array = new ArrayList<String>();
			for (final SymbolDeclaration para : ctx.getArgs())
			{
				if (para instanceof ParameterDeclaration)
				{
					final ParameterDeclaration paraDecl = (ParameterDeclaration) para;
					final String name = paraDecl.getName();

					if (array.contains(name))
					{
						error("parameter names are defined multiple times", CommonLanguagePackage.Literals.SYMBOL_DECLARATION__NAME, -1);
					} else
					{
						array.add(name);
					}
				}
			}
		}

	}

	/**
	 * ensure that assert statements are only used in test functions: TODO this
	 * may be removed as testing of void functions requires puting asserts in
	 * target function under test
	 */
	@Check
	public void checkAsserts(final TestingStatement ctx)
	{
		final FunctionDeclaration function = Utils.ancestor(ctx.eContainingFeature(), FunctionDeclaration.class);
		if (!function.getKind().equals(FunctionKind.TEST))
		{
			error("asserts can only be used in test functions", ctx, null, -1);
		}
	}

	/**
	 * check that the various special cases in the for statement archived
	 * correctly
	 */
	@Check
	public void compatibleFor(final ForeachStatement ctx)
	{
		final Expression range = ctx.getRange();
		final EObject rangeType = ts.typeof(range, new TypeCalculationTrace());
		if (ts.hasCharacteristic(rangeType, HLTypesystem.ITERABLE))
		{
			final ForVarDeclaration iterator = (ForVarDeclaration) ctx.getIterator();
			final EObject iteratorType = ts.typeof(iterator.getType(), new TypeCalculationTrace());
			if (rangeType instanceof ChannelSetType && !(iteratorType instanceof ChannelType))
			{
				error("incompatible type; channels requires channel (on a ForStatement).", ctx, null, -1);
			}
			if (rangeType instanceof LevelCurvesType && !(iteratorType instanceof LevelCurvePointType))
			{
				error("incompatible type; levelcurves requires levelcurvepoint (on a ForStatement).", ctx, null, -1);
			}
			if (rangeType instanceof CurveType && !(iteratorType instanceof CurvePointType))
			{
				error("incompatible type; curve requires curvepoint (on a ForStatement).", ctx, null, -1);
			}
		}
	}

	/**
	 * the break statement may only be used within loops
	 */
	@Check
	public void breakInALoop(final BreakStatement ctx)
	{
		List<EObject> ancestors = null;
		ancestors = Utils.ancestors(ctx, hl.getForeachStatement());
		if (!ancestors.isEmpty())
			return;
		ancestors = Utils.ancestors(ctx, hl.getWhileStatement());
		if (!ancestors.isEmpty())
			return;
		ancestors = Utils.ancestors(ctx, hl.getDoWhileStatement());
		if (!ancestors.isEmpty())
			return;
		error("break must be inside a for, while or do-while statement (on a BreakStatement)", ctx, null, -1);
	}

	@Check
	public void enumAssignmentCheck(final AssignmentStatement vari)
	{
		EcoreUtil2.resolveAll(vari);
		final Object lv = vari.getLvalue();
		final Object rv = vari.getRvalue();
		if (lv instanceof ArrayAccessExpression)
		{
			final ParameterReadExpression x = (ParameterReadExpression) ((ArrayAccessExpression) lv).getExpr();
			final Object n = x.getParam();
			if (n instanceof EnumParameter)
			{
				if (rv instanceof SymbolReference && ((SymbolReference) rv).getSymbol() instanceof EnumLiteral)
				{
					final EnumParameter parent = (EnumParameter) ((SymbolReference) rv).getSymbol().eContainer();
					if (!parent.getName().equals(((EnumParameter) n).getName()))
					{
						error("Parameter assignment error, name of Enum must be the same", HearingLanguagePackage.Literals.ASSIGNMENT_STATEMENT__RVALUE);
					}
				}

			}
		}

	}

	/**
	 * TODO: do we really need the sit stuff?
	 */
	@Check
	public void checkSitExpression(final SitExpression ctx)
	{
		final Expression index = ctx.getIndex();

		if (index instanceof SymbolReferenceImpl)
		{
			if (index instanceof LocalVariableDeclaration)
			{
				LocalVariableDeclaration locVar = (LocalVariableDeclaration) index;
				if (!(locVar.getType() instanceof IntType))
				{
					error("only int indexes are allowed", HearingLanguagePackage.Literals.SIT_EXPRESSION__INDEX);
				}
			}

		} else
		{
			if (index instanceof NumberLiteral)
			{
				NumberLiteral nrIndex = (NumberLiteral) index;
				final String indexValue = nrIndex.getValue();

				if (indexValue.contains("."))
				{
					error("only int indexes are allowed", HearingLanguagePackage.Literals.SIT_EXPRESSION__INDEX);
				}
			} else
			{
				error("only int indexes are allowed", HearingLanguagePackage.Literals.SIT_EXPRESSION__INDEX);

			}

		}
		// final String index = ctx.getIndex();
		//
		// if (index.contains("."))
		// {
		// error("only int indexes are allowed",
		// HearingLanguagePackage.Literals.SIT_EXPRESSION__INDEX);
		// }
		final Expression expr = ctx.getHiExpression();
		ParameterReadExpression pre = null;
		if (expr instanceof ParameterReadExpression)
		{
			pre = (ParameterReadExpression) expr;
		} else if (expr instanceof ArrayAccessExpression)
		{
			final Expression indexedExpr = ((ArrayAccessExpression) expr).getExpr();
			if (indexedExpr instanceof ParameterReadExpression)
			{
				pre = (ParameterReadExpression) indexedExpr;
			}
		}
		if (pre == null)
		{
			error("only parameter access allowed here", HearingLanguagePackage.Literals.SIT_EXPRESSION__HI_EXPRESSION);
		} else if (!pre.getScope().toString().equals("hi"))
		{
			error("only hi parameter allowed here", HearingLanguagePackage.Literals.SIT_EXPRESSION__HI_EXPRESSION);
		}
	}

	/**
	 * 
	 */
	@Check
	public void checkAtsideExpression(final AtsideExpression ctx)
	{
		final Expression exprSide = ctx.getArgSide();
		if (exprSide == null)
			error("1st argument does not indicate a side", ctx, null, -1);
		else if (exprSide instanceof SymbolReference)
		{
			final SymbolDeclaration symbol = ((SymbolReference) exprSide).getSymbol();
			if (!(symbol instanceof EnumLiteral))
			{
				error("1st argument does not indicate a side", ctx, null, -1);
			}
			// TODO: Extend checks
			// if (!symbol.getName().contains("side"))
			// {
			//
			// }
		}

		final Expression expr = ctx.getExpr();
		if (expr instanceof ParameterReadExpression)
		{
			final ParameterReadExpression pre = (ParameterReadExpression) expr;
			final String scope = pre.getScope().toString();
			if (!scope.equals("cap") && !scope.equals("crv") && !scope.equals("levelcrv") && !scope.equals("fit"))
			{
				error("side operator can only be used with  cap, fit, crv and levelcrv (on a " + ctx.eClass().getName() + ")", ctx, null, -1);
			}
		}

	}

	// --------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------
	private void CheckForStringID(final Expression argStringID, final EObject ctx, final EReference ref)
	{

		if (argStringID == null)
			error("unable to resolve error", ctx, null, -1);
		else if (argStringID instanceof TextLiteral)
		{
			final TextLiteral tl = (TextLiteral) argStringID;
			if (tl.getValue().toString().isEmpty())
			{
				error("string is empty", ref);
			} else if (tl.getValue().toString().length() > 32)
			{
				error("not more than 32 characters", ref);
			}
		}
	}

	private boolean isValidEnumType(Expression parameter, String searchName)
	{

		if (parameter.eIsProxy())
		{
			EObject ob = EcoreUtil.resolve(parameter, parameter.eResource().getResourceSet());
			if (ob == null || !(ob instanceof Expression))
			{
				System.out.println("Error");
			}

			parameter = (Expression) ob;
		}

		// regularly we expect a symbol reference
		if (parameter instanceof SymbolReference)
		{
			SymbolReference symb = (SymbolReference) parameter;
			EObject decl = symb.getSymbol();

			if (decl.eIsProxy())
			{
				decl = EcoreUtil.resolve(decl, decl.eResource().getResourceSet());
			}

			if (decl instanceof EnumLiteral)
			{

				// String search="";
				String name = "";
				if (((EnumLiteral) decl).eContainer() instanceof EnumParameter)
				{
					EnumParameter en = (EnumParameter) ((EnumLiteral) decl).eContainer();
					// ParamKind kind = en.getKind();
					name = en.getName();
				}

				if (searchName != "")
				{
					boolean result = searchName.equalsIgnoreCase(name);
					return result;
				}
			}
		}

		return false;
	}

	private boolean CheckParameterAccess(final Expression expr, final String scope, final EReference ref)
	{
		return CheckParameterAccess(expr, scope, ref, false);
	}

	private boolean CheckParameterAccess(final Expression expr, final String scope, final EReference ref, boolean onlyCheck)
	{
		boolean result = true;

		ParameterReadExpression pre = null;
		if (expr instanceof ParameterReadExpression)
		{
			if (expr instanceof ParameterReadExpression)
			{
				pre = (ParameterReadExpression) expr;
			} else if (expr instanceof ArrayAccessExpression)
			{
				final Expression indexedExpr = ((ArrayAccessExpression) expr).getExpr();
				if (indexedExpr instanceof ParameterReadExpression)
				{
					pre = (ParameterReadExpression) indexedExpr;
				}
			}

			if (pre == null)
			{
				if (!onlyCheck)
				{
					error("only parameter access allowed here", ref);
				}
				result = false;
			} else if (!pre.getScope().toString().equals(scope))
			{
				if (!onlyCheck)
				{
					error("only " + scope + " parameters allowed here", ref);
				}
				result = false;
			}
			return result;
		}
		return false;
	}

	private EnumParameter GetEnumParameter(final Expression expr)
	{
		ParameterReadExpression pre = null;
		SymbolDeclaration symbol = null;

		if (!(expr instanceof SymbolReference))
		{
			if (expr instanceof ParameterReadExpression)
			{
				pre = (ParameterReadExpression) expr;
			} else if (expr instanceof ArrayAccessExpression)
			{
				final Expression indexedExpr = ((ArrayAccessExpression) expr).getExpr();
				if (indexedExpr instanceof ParameterReadExpression)
				{
					pre = (ParameterReadExpression) indexedExpr;
				}
			}
			if (pre != null)
			{
				symbol = pre.getParam();
			}
		} else
		{
			symbol = ((SymbolReference) expr).getSymbol();
		}

		return (symbol != null && symbol instanceof EnumParameter) ? (EnumParameter) symbol : null;

	}

	private boolean CheckOrderedEnum(final Expression expr)
	{
		EnumParameter param = GetEnumParameter(expr);
		return (param != null) ? param.isOrdered() : false;
	}

	// --------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------

	/**
	 * 
	 */
	@Check
	public void checkAddToTGStatement(final AddToTGStatement ctx)
	{
		CheckForStringID(ctx.getArg(), ctx, HearingLanguagePackage.Literals.ADD_TO_TG_STATEMENT__ARG);
	}

	/**
	 * 
	 */
	@Check
	public void checkMultToTGStatement(final MultToTGStatement ctx)
	{
		CheckForStringID(ctx.getArg(), ctx, HearingLanguagePackage.Literals.MULT_TO_TG_STATEMENT__ARG);

	}

	/**
	 * 
	 */
	@Check
	public void checkAppOffsStatement(final AppOffsStatement ctx)
	{
		// 1st argument is a string-identifier

		CheckForStringID(ctx.getArgId(), ctx, HearingLanguagePackage.Literals.APP_OFFS_STATEMENT__ARG_ID);
		if (!isValidEnumType(ctx.getArgTo(), "ToSubject"))
		{
			error("2nd argument does not indicate the subject where to apply", ctx, null, -1);
		}
		if (!isValidEnumType(ctx.getArgApply(), "Apply"))
		{
			error("3rd argument does not indicate the scope of application", ctx, null, -1);
		}
	}

	/**
	 * 
	 */
	@Check
	public void checkIsOffsAppExpression(final IsOffsAppExpression ctx)
	{
		// 1st argument is a string-identifier
		CheckForStringID(ctx.getArgId(), ctx, HearingLanguagePackage.Literals.IS_OFFS_APP_EXPRESSION__ARG_ID);
		if (!isValidEnumType(ctx.getArgTo(), "ToSubject"))
		{
			error("2nd argument does not indicate the subject where to apply", ctx, null, -1);
		}
		if (!isValidEnumType(ctx.getArgApply(), "Apply"))
		{
			error("3rd argument does not indicate the scope of application", ctx, null, -1);
		}

	}

	// -----------------------------------------------------------------------------
	@Check
	public void existsCurveFromLcCheck(final ExistsCurveFromLc call)
	{
		Expression levelCurve = call.getArgLC();
		Expression levelID = call.getArgLevel();

		if (!isValidEnumType(levelCurve, "LcId"))
		{
			error("Invalid LcId", call, HearingLanguagePackage.Literals.EXISTS_CURVE_FROM_LC__ARG_LC);
		}
		if (!isValidEnumType(levelID, "Level"))
		{
			error("Invalid Level", call, HearingLanguagePackage.Literals.EXISTS_CURVE_FROM_LC__ARG_LEVEL);
		}
	}

	@Check
	public void getCurveFromLc(final GetCurveFromLc call)
	{
		Expression levelCurve = call.getArgLC();
		Expression levelID = call.getArgLevel();

		if (!isValidEnumType(levelCurve, "LcId"))
		{
			error("Invalid LcId", call, HearingLanguagePackage.Literals.GET_CURVE_FROM_LC__ARG_LC);
		}
		if (!isValidEnumType(levelID, "Level"))
		{
			error("Invalid Level", call, HearingLanguagePackage.Literals.GET_CURVE_FROM_LC__ARG_LEVEL);
		}
	}

	// -----------------------------------------------------------------------------
	// IsCurveApplicable ApplyCurve tests
	// -----------------------------------------------------------------------------

	/*
	 * ApplyCurve and IsCurveApplicable
	 */
	@Check
	public void checkApplyCurve(final Expression inputCurve)
	{
		if (!(inputCurve instanceof IsCurveApplicable) && !(inputCurve instanceof ApplyCurve))
			return;

		Expression argToSub = null;
		EReference refToSub = null;
		;
		Expression argApply = null;
		;
		EReference refApply = null;
		Expression argStartFreq = null;
		Expression argStopFreq = null;

		if (inputCurve instanceof IsCurveApplicable)
		{
			IsCurveApplicable appl = (IsCurveApplicable) inputCurve;

			refToSub = HearingLanguagePackage.Literals.IS_CURVE_APPLICABLE__ARG_TO_SUBJECT;
			refApply = HearingLanguagePackage.Literals.IS_CURVE_APPLICABLE__ARG_APPLY;

			argToSub = appl.getArgToSubject();
			argApply = appl.getArgApply();
			argStartFreq = appl.getArgStartFreq();
			argStopFreq = appl.getArgStopFreq();
		} else if (inputCurve instanceof ApplyCurve)
		{
			ApplyCurve appl = (ApplyCurve) inputCurve;

			refToSub = HearingLanguagePackage.Literals.APPLY_CURVE__ARG_TO_SUBJECT;
			refApply = HearingLanguagePackage.Literals.APPLY_CURVE__ARG_APPLY;

			argToSub = appl.getArgToSubject();
			argApply = appl.getArgApply();
			argStartFreq = appl.getArgStartFreq();
			argStopFreq = appl.getArgStopFreq();
		}

		if (!isValidCurveType_Package(inputCurve))
		{
			error("Invalid package type", inputCurve, null, -1);
		}
		if (!isValidCurveType_Parameter(argToSub, 1))
		{
			error("Invalid call for ArgToSubject! Only calls for std:ToSubject are valid", refToSub);
		}
		if (!isValidCurveType_Parameter(argApply, 2))
		{
			error("Invalid call for ArgApply! Only calls for std:Apply are valid", refApply);
		}
		if (!isValidCurveType_Freq_Range(argStartFreq, argStopFreq))
		{
			error("Invalid range - supported values shall be between 0 and 20000 and starting freq shall be lower than stopFreq", inputCurve, null, -1);
		}
	}

	private boolean isValidCurveType_Package(Expression curveMethod)
	{
		final Namespace containerNS = Utils.ancestor(curveMethod, Namespace.class);
		if (containerNS != null && containerNS.isIsPackage())
		{
			PackageKind kind = containerNS.getPackKind();
			if (kind == PackageKind.MIXED_MODE | kind == PackageKind.FIRST_FIT | kind == PackageKind.PROGRAM_SELECTION | kind == PackageKind.FITTING_ASSISTENT)
			{
				return true;
			}

			if (kind.equals(PackageKind.MIXED_MODE))
			{
				System.out.println("matched");
			}
		}
		return false;
	}

	private boolean isValidCurveType_Parameter(Expression parameter, int paramID)
	{

		if (parameter.eIsProxy())
		{

			EObject ob = EcoreUtil.resolve(parameter, parameter.eResource().getResourceSet());
			if (ob == null || !(ob instanceof Expression))
			{
				System.out.println("Error");
			}

			parameter = (Expression) ob;
		}

		// ParameterReadExpression pre = null;

		// regularly we expect a symbol reference

		if (parameter instanceof SymbolReference)
		{
			SymbolReference symb = (SymbolReference) parameter;
			EObject decl = symb.getSymbol();

			if (decl.eIsProxy())
			{
				decl = EcoreUtil.resolve(decl, decl.eResource().getResourceSet());
			}

			if (decl instanceof EnumLiteral)
			{

				String search = "";
				String name = "";
				if (((EnumLiteral) decl).eContainer() instanceof EnumParameter)
				{
					EnumParameter en = (EnumParameter) ((EnumLiteral) decl).eContainer();
					// ParamKind kind = en.getKind();
					name = en.getName();
				}

				switch (paramID)
				{
					case 1:
						search = "ToSubject";
						break;
					case 2:
						search = "Apply";
						break;
				}
				if (search != "")
				{
					boolean result = search.equalsIgnoreCase(name);
					return result;
				}
			}
		}

		return false;
	}

	private boolean isValidCurveType_Freq_Range(Expression startFreq, Expression stopFreq)
	{
		String startValue = "";
		String stopValue = "";
		if (startFreq instanceof NumberLiteral)
		{
			startValue = ((NumberLiteral) startFreq).getValue();
		}

		if (stopFreq instanceof NumberLiteral)
		{
			stopValue = ((NumberLiteral) stopFreq).getValue();
		}

		if ("".equals(startValue) || "".equals(stopValue))
			return false;

		int startVal = Integer.parseInt(startValue);
		int stopVal = Integer.parseInt(stopValue);

		return (startVal >= 0 && startVal < 20000 && stopVal > 0 && stopVal <= 20000 && stopVal > startVal);
	}

	// -----------------------------------------------------------------------------
	// IsMin IsMax
	// -----------------------------------------------------------------------------
	/**
	 * 
	 */
	@Check
	public void checkIsMinExpression(final IsMinExpression ctx)
	{
		CheckParameterAccess(ctx.getExpr(), "hi", HearingLanguagePackage.Literals.IS_MIN_EXPRESSION__EXPR);
	}

	/**
	 * 
	 */
	@Check
	public void checkIsMaxExpression(final IsMaxExpression ctx)
	{
		CheckParameterAccess(ctx.getExpr(), "hi", HearingLanguagePackage.Literals.IS_MAX_EXPRESSION__EXPR);
	}

	/**
	 * 
	 */
	@Check
	public void checkSetMinStatement(final SetMinStatement ctx)
	{
		CheckParameterAccess(ctx.getExpr(), "hi", HearingLanguagePackage.Literals.SET_MIN_STATEMENT__EXPR);
	}

	/**
	 * 
	 */
	@Check
	public void checkSetMaxStatement(final SetMaxStatement ctx)
	{
		CheckParameterAccess(ctx.getExpr(), "hi", HearingLanguagePackage.Literals.SET_MAX_STATEMENT__EXPR);
	}

	@Check
	public void checkStepsBelowMax(Expression ctx)
	{
		Expression expr;
		EReference ref;

		if (ctx instanceof GetStepsBelowMax)
		{
			expr = ((GetStepsBelowMax) ctx).getArg();
			ref = HearingLanguagePackage.Literals.GET_STEPS_BELOW_MAX__ARG;
		} else if (ctx instanceof SetStepsBelowMax)
		{
			expr = ((SetStepsBelowMax) ctx).getArg();
			ref = HearingLanguagePackage.Literals.SET_STEPS_BELOW_MAX__ARG;
		} else if (ctx instanceof GetStepsAboveMin)
		{
			expr = ((GetStepsAboveMin) ctx).getArg();
			ref = HearingLanguagePackage.Literals.GET_STEPS_ABOVE_MIN__ARG;
		} else if (ctx instanceof SetStepsAboveMin)
		{
			expr = ((SetStepsAboveMin) ctx).getArg();
			ref = HearingLanguagePackage.Literals.SET_STEPS_ABOVE_MIN__ARG;
		} else
			return;

		CheckParameterAccess(expr, "hi", ref);
		if (!CheckOrderedEnum(expr))
		{
			error("Only valid for ordered data elements ", ctx, ref);
		}

	}

	// -----------------------------------------------------------------------------
	// Fitting Assitant
	// -----------------------------------------------------------------------------

	/*
	 * Fitting Assitant
	 */
	@Check
	public void fittingAssitantOnly(final FittingAssistStatement faStatement)
	{

		final Namespace containerNS = Utils.ancestor(faStatement, Namespace.class);

		if (containerNS != null && containerNS.isIsPackage())
		{
			if (containerNS.getPackKind() != PackageKind.FITTING_ASSISTENT)
			{
				error("Fitting assistant methods can only be used within " + PackageKind.FITTING_ASSISTENT.toString() + " package", faStatement, null, -1);
			}
		}

	}

	@Check
	public void FittingAssistant_SpecialApplicationFunctionsONly(final FittingAssistStatement faStatement)
	{
		final FunctionDeclaration function = Utils.ancestor(faStatement, FunctionDeclaration.class);

		String allowedFunctionName = "";

		if (faStatement instanceof SetApply1IsEnabled || faStatement instanceof SetApply1IsVisible || faStatement instanceof SetProposalTextID1)
		{
			allowedFunctionName = "SelectProposal1";
		}

		if (faStatement instanceof SetApply2IsEnabled || faStatement instanceof SetApply2IsVisible || faStatement instanceof SetProposalTextID2)
		{
			allowedFunctionName = "SelectProposal2";
		}

		if (!function.getName().trim().equalsIgnoreCase(allowedFunctionName))
		{
			error("Function " + faStatement.getClass().getSimpleName().toString() + " is not used within allowed function " + allowedFunctionName
					+ " -- current scope is " + function.getName(), faStatement, null, -1);
		}
	}

}
