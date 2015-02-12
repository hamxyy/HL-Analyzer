package com.shs.hl.typesystem;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import com.shs.common.commonLanguage.ArrayKind;
import com.shs.common.commonLanguage.ChannelParameter;
import com.shs.common.commonLanguage.CommonLanguagePackage;
import com.shs.common.commonLanguage.CrvParameter;
import com.shs.common.commonLanguage.DoubleParameter;
import com.shs.common.commonLanguage.EnumLiteral;
import com.shs.common.commonLanguage.EnumParameter;
import com.shs.common.commonLanguage.IntegerParameter;
import com.shs.common.commonLanguage.ParameterScope;
import com.shs.common.commonLanguage.SymbolDeclaration;
import com.shs.hl.hearingLanguage.ArrayAccessExpression;
import com.shs.hl.hearingLanguage.ArrayType;
import com.shs.hl.hearingLanguage.AtsideExpression;
import com.shs.hl.hearingLanguage.BoolType;
import com.shs.hl.hearingLanguage.BuiltInCall;
import com.shs.hl.hearingLanguage.BuiltInOp;
import com.shs.hl.hearingLanguage.ChannelSetType;
import com.shs.hl.hearingLanguage.ChannelType;
import com.shs.hl.hearingLanguage.CurvePointType;
import com.shs.hl.hearingLanguage.CurveType;
import com.shs.hl.hearingLanguage.DoubleParameterType;
import com.shs.hl.hearingLanguage.DoubleType;
import com.shs.hl.hearingLanguage.EnumParameterType;
import com.shs.hl.hearingLanguage.Expression;
import com.shs.hl.hearingLanguage.FrequenceType;
import com.shs.hl.hearingLanguage.GetProposalText1;
import com.shs.hl.hearingLanguage.GetProposalText2;
import com.shs.hl.hearingLanguage.HearingLanguagePackage;
import com.shs.hl.hearingLanguage.IntType;
import com.shs.hl.hearingLanguage.IntegerParameterType;
import com.shs.hl.hearingLanguage.LevelCurvePointType;
import com.shs.hl.hearingLanguage.LevelCurvesType;
import com.shs.hl.hearingLanguage.NullType;
import com.shs.hl.hearingLanguage.NumberLiteral;
import com.shs.hl.hearingLanguage.ParameterReadExpression;
import com.shs.hl.hearingLanguage.Plus;
import com.shs.hl.hearingLanguage.StringIdType;
import com.shs.hl.hearingLanguage.StringType;
import com.shs.hl.hearingLanguage.SymbolReference;
import com.shs.hl.hearingLanguage.TextLiteral;
import com.shs.hl.hearingLanguage.Type;
import com.shs.hl.hearingLanguage.VoidType;

import de.itemis.xtext.typesystem.DefaultTypesystem;
import de.itemis.xtext.typesystem.ITypesystem;
import de.itemis.xtext.typesystem.characteristics.TypeCharacteristic;
import de.itemis.xtext.typesystem.checks.custom.StaticCustomTypeChecker;
import de.itemis.xtext.typesystem.exceptions.TypesystemConfigurationException;
import de.itemis.xtext.typesystem.trace.TypeCalculationTrace;
import de.itemis.xtext.typesystem.util.Utils;

/**
 * Before you try to understand the type system of the hearing language please
 * please please read the type system tutorial. You can find it in the
 * type-system plug in The example code is at you him
 * http://code.google.com/a/eclipselabs.org/p/xtext-typesystem/
 * 
 * If you don't understand the typing rule commented out and run the tests.
 * There is a good chance one will fail and you will understand what the typing
 * rule does.
 */
public class HLTypesystem extends DefaultTypesystem
{

	// we import the two language packages because we need to refer
	// to meta-types from the typesystem definitions
	private CommonLanguagePackage			cl			= CommonLanguagePackage.eINSTANCE;
	private HearingLanguagePackage			hl			= HearingLanguagePackage.eINSTANCE;

	
	public static final TypeCharacteristic	ITERABLE		= new TypeCharacteristic("iterable");	// a characteristic for things that can be iterated over
	public static final TypeCharacteristic	INDEXING		= new TypeCharacteristic("indexing");	// things that can be used for array indexing
	public static final TypeCharacteristic	NEWABLE			= new TypeCharacteristic("newable");	// things that can be instantiated via new
	public static final TypeCharacteristic	NULLABLE		= new TypeCharacteristic("nullable");	// things that can be set to null
	public static final TypeCharacteristic  CURVEARITHMETIC = new TypeCharacteristic("curvearithmetic"); // things that can be combined with curves
	
	// number-like things are floats, ints and units with numbers
//	private Object[]	numberLikeThings					
//								= new Object[] { hl.getIntType(), hl.getDoubleType() };

	// number-like things are floats, ints and units with numbers
	// private Object[] numberLikeThingsAndOrderedEnums 
	//							= new Object[] {hl.getIntType(), hl.getDoubleType(), cl.getBitmaskParameter(), new OrderedEnum() };

	// ... like above, plus curves.
	private Object[]	numberLikeThingsAndCurves	
								= new Object[]{ hl.getIntType(), hl.getDoubleType(), hl.getCurveType() };
	// ... like above, plus curves.
	private Object[]	numberLikeThingsAndCurvesAndString 
								= new Object[]{ hl.getIntType(), hl.getDoubleType(), hl.getCurveType(), hl.getStringType(), hl.getStringIdType() };

	private Object[]	comparableStuff
								= new Object[]{ hl.getIntType(), hl.getDoubleType(), new OrderedEnum(), hl.getBoolType(), hl.getStringType(), 
												 hl.getStringIdType(), hl.getEnumParameterType(), hl.getIntegerParameterType(), hl.getDoubleParameterType(),  
												 hl.getCurvePointType(), hl.getLevelCurvePointType(), hl.getChannelType(), hl.getChannelSetType() };
	
	private Object[]	parameterStuff
								= new Object[]{ hl.getEnumParameterType(), hl.getIntegerParameterType(), hl.getDoubleParameterType()};

	
	
	
//	private Object[]	curveArtihmeticsOp
//								= new Object[]{ hl.getIntType(), hl.getDoubleType()};
//	
//	private Object[]	curveArithmetics
//								= new Object[]{hl.getIntType(), hl.getDoubleType(),hl.getCurveType()};
	
	
	
	
	@Override
	public EObject computeCommonType(EObject element1, EObject type1, EObject element2, EObject type2, TypeCalculationTrace trace)
	{
		
		
		// left type curve 
		// right type intType --> Curve
		// right type doubleType --> Curve
		// right type curve --> curve
		
//		// for curves we need a special handling
//		// on +/-/* operations applied to a curve, the result is always a curve
//		if (isCurveHandling(element1,type1,element2,type2,trace))
//		{
//			
//		}

		if (isInstanceOf(type1, hl.getCurveType(), trace)){
			

			if (isInstanceOf(type2,hl.getNumericType(),trace))
			{
				
				if (isInstanceOf(type2,hl.getIntType(),trace)||
					isInstanceOf(type2,hl.getDoubleType(),trace))
				{
					return type1;
				}
				// do further processing
			
			} else if (isSameType(element1, type1, element2, type2, trace))
			{
				return type1;
			}
		
		}	

		return super.computeCommonType(element1, type1, element2, type2, trace);
	}
	
	
	
//	private bool isCurveHandling(EObject element1, EObject type1, EObject element2, EObject type2)
//	{
//		
//	}
//	
	
	@Override
	protected void initialize()
	{
		try
		{
			// super.initialize();
			// this makes sure that we get a runtime exception if types are not
			// subtypes of any of these types passed in here
			// TODO: can we make all of them subtypes of Type?
			declareTypeRootEClasses(hl.getType(), cl.getEnumParameter());

			// the following functions set up various aspects of the type system

			primitiveTypes();
			arithmeticOperators();
			booleanOperators();
			bitOperators();
			comparisonOperators();
			loops();
			localVariables();
			functions();
			arrays();
			conditionalStatements();
			assignment();

			parameterRead();
			builtinStatements();
			channels();
			enumparams();
			curves();

			builtIns();

			unitsAndPhysicalTypes();

			rangesAndStuff();

			nullStuff();

			tests();

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void tests() throws TypesystemConfigurationException
	{
		// define Boolean and string types for the assert statement
		ensureFeatureType(hl.getAssertStatement(), hl.getAssertStatement_Expr(), hl.getBoolType());
		ensureFeatureType(hl.getAssertStatement(), hl.getAssertStatement_Msg(), hl.getStringType());
	}

	private void builtIns() throws TypesystemConfigurationException
	{
		// the type of the built in operation is the type specified in the
		// definition
		useTypeOfFeature(hl.getBuiltInOp(), hl.getBuiltInOp_ResultType());
	}

	private void parameterRead() throws TypesystemConfigurationException
	{
		// the type of a parameter read expression is the type of its parameter
		useTypeOfFeature(hl.getParameterReadExpression(), hl.getParameterReadExpression_Param());
	}

	private void builtinStatements() throws TypesystemConfigurationException
	{
		// "setMin": Parameter = HI Control
		ensureFeatureType(hl.getSetMinStatement(), hl.getSetMinStatement_Expr(), hl.getEnumParameterType(), hl.getIntegerParameterType(),
				hl.getDoubleParameterType());
		useFixedType(hl.getSetMinStatement(), hl.getVoidType());

		// "isMin": Parameter = HI Control
		ensureFeatureType(hl.getIsMinExpression(), hl.getIsMinExpression_Expr(), hl.getEnumParameterType(), hl.getIntegerParameterType(),
				hl.getDoubleParameterType());
		useFixedType(hl.getIsMinExpression(), hl.getBoolType());

		// "setMax": Parameter = HI Control
		ensureFeatureType(hl.getSetMaxStatement(), hl.getSetMaxStatement_Expr(), hl.getEnumParameterType(), hl.getIntegerParameterType(),
				hl.getDoubleParameterType());
		useFixedType(hl.getSetMaxStatement(), hl.getVoidType());

		// "isMax": Parameter = HI Control
		ensureFeatureType(hl.getIsMaxExpression(), hl.getIsMaxExpression_Expr(), hl.getEnumParameterType(), hl.getIntegerParameterType(),
				hl.getDoubleParameterType());
		useFixedType(hl.getIsMaxExpression(), hl.getBoolType());

		// "isValid": Parameter = HI Control, only for HIcontrols with steps,
		// check a single step, this is an EnumLiteral
		// OPEN TOPIC: eigentlich muesste 1.Parameter "EnumLiteral" sein
		// ensureFeatureType(hl.getIsValidExpression(),
		// hl.getIsValidExpression_Expr(), cl.getEnumLiteral() );
		ensureFeatureType(hl.getIsValidExpression(), hl.getIsValidExpression_Expr(), hl.getEnumParameterType());
		useFixedType(hl.getIsValidExpression(), hl.getBoolType());

		// "isDefined": Parameter = Pat:Age => IntegerParameterType, Pat:Gender
		// => EnumParameterType, Curve or LevelCurve, doubleType
		ensureFeatureType(hl.getDefinedExpression(), hl.getDefinedExpression_Expr(), hl.getIntegerParameterType(), hl.getDoubleType(),
				hl.getEnumParameterType(), hl.getCurveType(), hl.getLevelCurvesType());
		useFixedType(hl.getDefinedExpression(), hl.getBoolType());

		// "isOffsetApplicable": 1.Parameter=StringId, 2.Parameter=ToSubject =>
		// IntegerParameterType, 3.Parameter=Apply => IntegerParameterType
		// parameters are of same type like "applyOffset"
		ensureFeatureType(hl.getIsOffsAppExpression(), hl.getIsOffsAppExpression_ArgId(), hl.getStringIdType());
		ensureFeatureType(hl.getIsOffsAppExpression(), hl.getIsOffsAppExpression_ArgTo(), hl.getEnumParameterType());
		ensureFeatureType(hl.getIsOffsAppExpression(), hl.getIsOffsAppExpression_ArgApply(), hl.getEnumParameterType());
		useFixedType(hl.getIsOffsAppExpression(), hl.getBoolType());

		useFixedType(hl.getLogChanCountExpression(), hl.getIntType());

		// "applyOffset": 1.Parameter=StringId, 2.Parameter=ToSubject =>
		// IntegerParameterType, 3.Parameter=Apply => IntegerParameterType
		// parameters are of same type like "isOffsetApplicable"
		ensureFeatureType(hl.getAppOffsStatement(), hl.getAppOffsStatement_ArgId(), hl.getStringIdType());
		ensureFeatureType(hl.getAppOffsStatement(), hl.getAppOffsStatement_ArgTo(), hl.getEnumParameterType());
		ensureFeatureType(hl.getAppOffsStatement(), hl.getAppOffsStatement_ArgApply(), hl.getEnumParameterType());
		useFixedType(hl.getAppOffsStatement(), hl.getVoidType());

		// "opp": Parameter = Curve, CurvePoint, LevelCurve, LevelCurvePoint or
		// Capability => EnumParameterType or IntegerParameterType
		ensureFeatureType(hl.getOppositeExpression(), hl.getOppositeExpression_Expr(), hl.getCurveType(), hl.getCurvePointType(), hl.getLevelCurvesType(),
				hl.getLevelCurvePointType(), hl.getIntegerParameterType(), hl.getEnumParameterType(), hl.getDoubleParameterType(), hl.getArrayType());
		useTypeOfFeature(hl.getOppositeExpression(), hl.getOppositeExpression_Expr());

		// "side":
		// 1.Parameter=side => EnumParameterType, 2.Parameter Curve, LevelCurve
		// or Cap => int
		// 2.Parameter takes the same kind of types as "opp"
		// OPEN TOPIC: eigentlich muesste 1.Parameter "EnumLiteral" sein
		// ensureFeatureType(hl.getAtsideExpression(),
		// hl.getAtsideExpression_ArgSide(), cl.getEnumLiteral() );
		ensureFeatureType(hl.getAtsideExpression(), hl.getAtsideExpression_ArgSide(), hl.getEnumParameterType());
		ensureFeatureType(hl.getAtsideExpression(), hl.getAtsideExpression_Expr(), hl.getCurveType(), hl.getCurvePointType(), hl.getLevelCurvesType(),
				hl.getLevelCurvePointType(), hl.getIntegerParameterType(), hl.getEnumParameterType(), hl.getDoubleParameterType());
		useTypeOfFeature(hl.getAtsideExpression(), hl.getAtsideExpression_Expr());

		// "addCurveToTG": Parameter = StringID
		ensureFeatureType(hl.getAddToTGStatement(), hl.getAddToTGStatement_Arg(), hl.getStringIdType());
		ensureFeatureType(hl.getAddToTGStatement(), hl.getAddToTGStatement_ArgTo(), hl.getEnumParameterType());
		useFixedType(hl.getAddToTGStatement(), hl.getVoidType());

		// "multCurveToTG": Parameter = StringID
		ensureFeatureType(hl.getMultToTGStatement(), hl.getMultToTGStatement_Arg(), hl.getStringIdType());
		ensureFeatureType(hl.getMultToTGStatement(), hl.getMultToTGStatement_ArgTo(), hl.getEnumParameterType());
		useFixedType(hl.getMultToTGStatement(), hl.getVoidType());

		ensureFeatureType(hl.getAssertionStatement(), hl.getAssertionStatement_AssertDecide(), hl.getBoolType());
		ensureFeatureType(hl.getAssertionStatement(), hl.getAssertionStatement_AssertMessage(), hl.getStringType());
		useFixedType(hl.getAssertionStatement(), hl.getVoidType());

		// Fitting Assistant relevant type rules

		// SetApply__Visible -------------
		useFixedType(hl.getSetApply1IsVisible(), hl.getVoidType());
		ensureFeatureType(hl.getSetApply1IsVisible(), hl.getSetApply1IsVisible_ArgVis(), hl.getBoolType());

		useFixedType(hl.getSetApply2IsVisible(), hl.getVoidType());
		ensureFeatureType(hl.getSetApply2IsVisible(), hl.getSetApply2IsVisible_ArgVis(), hl.getBoolType());

		// SetApply__IsEnabled
		useFixedType(hl.getSetApply1IsEnabled(), hl.getVoidType());
		ensureFeatureType(hl.getSetApply1IsEnabled(), hl.getSetApply1IsEnabled_ArgEn(), hl.getBoolType());

		useFixedType(hl.getSetApply2IsEnabled(), hl.getVoidType());
		ensureFeatureType(hl.getSetApply2IsEnabled(), hl.getSetApply2IsEnabled_ArgEn(), hl.getBoolType());

		// SetProposalTextID__
		useFixedType(hl.getSetProposalTextID1(), hl.getVoidType());
		ensureFeatureType(hl.getSetProposalTextID1(), hl.getSetProposalTextID1_ArgText(), hl.getStringIdType());

		useFixedType(hl.getSetProposalTextID2(), hl.getVoidType());
		ensureFeatureType(hl.getSetProposalTextID2(), hl.getSetProposalTextID2_ArgText(), hl.getStringIdType());

		// GetProposalTextID
		useFixedType(hl.getGetProposalText1(), hl.getStringIdType());
		useFixedType(hl.getGetProposalText2(), hl.getStringIdType());

		useFixedType(hl.getGetCurve(), hl.getCurveType());
		ensureFeatureType(hl.getGetCurve(), hl.getGetCurve_ArgCurve(), hl.getStringIdType());

		useFixedType(hl.getExistsCurve(), hl.getBoolType());
		ensureFeatureType(hl.getExistsCurve(), hl.getExistsCurve_ArgCurve(), hl.getStringIdType());

		useFixedType(hl.getIsCurveApplicable(), hl.getBoolType());
		ensureFeatureType(hl.getIsCurveApplicable(), hl.getIsCurveApplicable_ArgCurve(), hl.getCurveType());
		ensureFeatureType(hl.getIsCurveApplicable(), hl.getIsCurveApplicable_ArgToSubject(), hl.getEnumParameterType());
		ensureFeatureType(hl.getIsCurveApplicable(), hl.getIsCurveApplicable_ArgApply(), hl.getEnumParameterType());
		ensureFeatureType(hl.getIsCurveApplicable(), hl.getIsCurveApplicable_ArgStartFreq(), hl.getFrequenceType());
		ensureFeatureType(hl.getIsCurveApplicable(), hl.getIsCurveApplicable_ArgStopFreq(), hl.getFrequenceType());

		useFixedType(hl.getApplyCurve(), hl.getVoidType());
		ensureFeatureType(hl.getApplyCurve(), hl.getApplyCurve_ArgCurve(), hl.getCurveType());
		ensureFeatureType(hl.getApplyCurve(), hl.getApplyCurve_ArgToSubject(), hl.getEnumParameterType());
		ensureFeatureType(hl.getApplyCurve(), hl.getApplyCurve_ArgApply(), hl.getEnumParameterType());
		ensureFeatureType(hl.getApplyCurve(), hl.getApplyCurve_ArgStartFreq(), hl.getFrequenceType());
		ensureFeatureType(hl.getApplyCurve(), hl.getApplyCurve_ArgStopFreq(), hl.getFrequenceType());

		useFixedType(hl.getExistsCurveFromLc(), hl.getBoolType());
		ensureFeatureType(hl.getExistsCurveFromLc(), hl.getExistsCurveFromLc_ArgLC(), parameterStuff);
		ensureFeatureType(hl.getExistsCurveFromLc(), hl.getExistsCurveFromLc_ArgLevel(), parameterStuff);

		useFixedType(hl.getGetCurveFromLc(), hl.getCurveType());
		ensureFeatureType(hl.getGetCurveFromLc(), hl.getGetCurveFromLc_ArgLC(), parameterStuff);
		ensureFeatureType(hl.getGetCurveFromLc(), hl.getGetCurveFromLc_ArgLevel(), parameterStuff);

		useFixedType(hl.getExistsProgramControl(), hl.getBoolType()); // done
		useFixedType(hl.getExistsVolumeControl(), hl.getBoolType()); // done

		useFixedType(hl.getGetStepsBelowMax(), hl.getIntType());
		ensureFeatureType(hl.getGetStepsBelowMax(), hl.getGetStepsBelowMax_Arg(), hl.getEnumParameterType(), hl.getIntegerParameterType());

		useFixedType(hl.getSetStepsBelowMax(), hl.getVoidType());
		ensureFeatureType(hl.getSetStepsBelowMax(), hl.getSetStepsBelowMax_Arg(), hl.getEnumParameterType(), hl.getIntegerParameterType());
		ensureFeatureType(hl.getSetStepsBelowMax(), hl.getSetStepsBelowMax_Val(), hl.getIntType());

		useFixedType(hl.getGetStepsAboveMin(), hl.getIntType());
		ensureFeatureType(hl.getGetStepsAboveMin(), hl.getGetStepsAboveMin_Arg(), hl.getEnumParameterType(), hl.getIntegerParameterType());

		useFixedType(hl.getSetStepsAboveMin(), hl.getVoidType());
		ensureFeatureType(hl.getSetStepsAboveMin(), hl.getSetStepsAboveMin_Arg(), hl.getEnumParameterType(), hl.getIntegerParameterType());
		ensureFeatureType(hl.getSetStepsAboveMin(), hl.getSetStepsAboveMin_Val(), hl.getIntType());
	}

	private void unitsAndPhysicalTypes() throws TypesystemConfigurationException
	{
		// defines the special types and how they are broken down
		useFixedType(hl.getGainType(), hl.getIntType());
		// useFixedType(hl.getTimeType(), hl.getIntType());
		useFixedType(hl.getFrequenceType(), hl.getIntType());
		// useFixedType(hl.getPercentType(), hl.getIntType());

	}

	private void curves() throws TypesystemConfigurationException
	{

		declareCharacteristic(hl.getCurveType(), ITERABLE);
		useCloneAsType(hl.getCurveType());
		useCloneAsType(hl.getCurvePointType());

		declareCharacteristic(hl.getLevelCurvesType(), ITERABLE);
		useCloneAsType(hl.getLevelCurvesType());
		useCloneAsType(hl.getLevelCurvePointType());
		
		
		declareCharacteristic(hl.getCurveType(), CURVEARITHMETIC);
		

	}

	private void enumparams() throws TypesystemConfigurationException
	{
		// the type of an enum parameter literal is the type of it's (owning)
		// enum
		useTypeOfAncestor(cl.getEnumLiteral(), cl.getEnumParameter());
		// enum types are only compatible if they have the same name
		declareTypeComparisonFeature(cl.getEnumParameter(), cl.getSymbolDeclaration_Name());

		ensureFeatureType(hl.getSitExpression(), hl.getSitExpression_Index(), hl.getIntType());
		useTypeOfFeature(hl.getSitExpression(), hl.getSitExpression_HiExpression());

		// the type of the shift expressions is obviously also enum
		useFixedType(hl.getEnumRight(), hl.getEnumParameterType());
		useFixedType(hl.getEnumLeft(), hl.getEnumParameterType());

		// and the shift expressions expect an enumeration on the left
		// and integer on the right
		// TODO: rename them to EnumShiftRight ...
		ensureFeatureType(hl.getEnumLeft(), hl.getEnumLeft_Left(), hl.getEnumParameterType());
		ensureFeatureType(hl.getEnumLeft(), hl.getEnumLeft_Right(), hl.getIntType());
		ensureFeatureType(hl.getEnumRight(), hl.getEnumRight_Left(), hl.getEnumParameterType());
		ensureFeatureType(hl.getEnumRight(), hl.getEnumRight_Right(), hl.getIntType());
	}

	private void assignment() throws TypesystemConfigurationException
	{
		// for an assignment statement (etc), the type of the symbol (lvalue)
		// and the type of the expression (rvalue) must be compatible
		ensureOrderedCompatibility(hl.getAssignmentStatement(), hl.getAssignmentStatement_Lvalue(), hl.getAssignmentStatement_Rvalue());
		ensureOrderedCompatibility(hl.getPlusEqualsStatement(), hl.getPlusEqualsStatement_Lvalue(), hl.getPlusEqualsStatement_Rvalue());
		ensureOrderedCompatibility(hl.getMinusEqualsStatement(), hl.getMinusEqualsStatement_Lvalue(), hl.getMinusEqualsStatement_Rvalue());
		ensureOrderedCompatibility(hl.getMultiplicationEqualsStatement(), hl.getMultiplicationEqualsStatement_Lvalue(), hl.getMultiplicationEqualsStatement_Rvalue());
	}

	private void conditionalStatements() throws TypesystemConfigurationException
	{
		// the conditions in an if and elseif must be boolean
		ensureFeatureType(hl.getIfStatement(), hl.getIfStatement_Condition(), hl.getBoolType());
		ensureFeatureType(hl.getElseIf(), hl.getElseIf_Condition(), hl.getBoolType());
	}

	private void arrays() throws TypesystemConfigurationException
	{
		// the array selector's index must be an int type
		ensureFeatureType(hl.getArrayAccessExpression(), hl.getArrayAccessExpression_Index(), INDEXING);
		ensureFeatureType(hl.getArrayAccessExpression(), hl.getArrayAccessExpression_Expr(), hl.getArrayType(), hl.getChannelSetType(), hl.getCurveType(),
				hl.getLevelCurvesType(), hl.getDoubleParameterType());
	}

	private void channels() throws TypesystemConfigurationException
	{
		declareCharacteristic(hl.getChannelSetType(), ITERABLE);
		useCloneAsType(hl.getChannelType());
		useCloneAsType(hl.getChannelSetType());
	}

	private void functions() throws TypesystemConfigurationException
	{
		// the type of a parameter declaration is the type of it's type
		useTypeOfFeature(hl.getParameterDeclaration(), hl.getParameterDeclaration_Type());

		// the type of a function declaration is the type of it's type
		useTypeOfFeature(hl.getFunctionDeclaration(), hl.getFunctionDeclaration_Type());

		// the type of a return statement is the type of it's expression
		useTypeOfFeature(hl.getReturnStatement(), hl.getReturnStatement_Expr());

	}

	private void localVariables() throws TypesystemConfigurationException
	{
		// the type of a local variable declaration is the type of it's type,
		// and the type of the init expression must also be compatible
		useTypeOfFeature(hl.getLocalVariableDeclaration(), hl.getLocalVariableDeclaration_Type());
		ensureOrderedCompatibility(hl.getLocalVariableDeclaration(), hl.getLocalVariableDeclaration_Type(), hl.getLocalVariableDeclaration_Init());
	}

	private void loops() throws TypesystemConfigurationException
	{
		// while and do-while are boolean expressions
		ensureFeatureType(hl.getWhileStatement(), hl.getWhileStatement_Expr(), hl.getBoolType());
		ensureFeatureType(hl.getDoWhileStatement(), hl.getDoWhileStatement_Expr(), hl.getBoolType());

		// old: ensureFeatureType(hl.getFor2VarDeclaration(),
		// hl.getFor2VarDeclaration_Type(), hl.getIntType());
		useTypeOfFeature(hl.getForVarDeclaration(), hl.getForVarDeclaration_Type());

		// the fourth statement can only iterate over iterable who is the stuff
		ensureFeatureType(hl.getForeachStatement(), hl.getForeachStatement_Range(), ITERABLE);

	}

	private void rangesAndStuff() throws TypesystemConfigurationException
	{
		// everything in a for must be ints
		ensureFeatureType(hl.getIntRangeExpression(), hl.getIntRangeExpression_Low(), hl.getIntType());
		ensureFeatureType(hl.getIntRangeExpression(), hl.getIntRangeExpression_High(), hl.getIntType());

		declareCharacteristic(hl.getIntRangeType(), ITERABLE);
		declareCharacteristic(hl.getIntSetType(), ITERABLE);
		declareCharacteristic(hl.getArrayType(), ITERABLE);
		declareCharacteristic(hl.getIntRangeType(), INDEXING);
		declareCharacteristic(hl.getIntSetType(), INDEXING);

		useFixedType(hl.getIntRangeExpression(), hl.getIntRangeType());

		useFixedType(hl.getIntSetExpression(), hl.getIntSetType());
	}

	private void comparisonOperators() throws TypesystemConfigurationException
	{
		// the types of the comparison operators are fixed to boolean
		useFixedType(hl.getGreater(), hl.getBoolType());
		useFixedType(hl.getSmaller(), hl.getBoolType());
		useFixedType(hl.getGreaterOrEquals(), hl.getBoolType());
		useFixedType(hl.getSmallerOrEquals(), hl.getBoolType());
		useFixedType(hl.getEquals(), hl.getBoolType());
		useFixedType(hl.getUnEquals(), hl.getBoolType());

		// make sure the two arguments for an equals are compatible
		// we don't restrict the arguments of equals, basically everything can
		// be compared
		ensureUnorderedCompatibility(hl.getEquals(), hl.getEquals_Left(), hl.getEquals_Right());
		ensureUnorderedCompatibility(hl.getUnEquals(), hl.getUnEquals_Left(), hl.getUnEquals_Right());
		ensureUnorderedCompatibility(hl.getGreater(), hl.getGreater_Left(), hl.getGreater_Right());
		ensureUnorderedCompatibility(hl.getGreaterOrEquals(), hl.getGreaterOrEquals_Left(), hl.getGreaterOrEquals_Right());
		ensureUnorderedCompatibility(hl.getSmaller(), hl.getSmaller_Left(), hl.getSmaller_Right());
		ensureUnorderedCompatibility(hl.getSmallerOrEquals(), hl.getSmallerOrEquals_Left(), hl.getSmallerOrEquals_Right());

		// for > etc., only number like things can be compared.
		ensureFeatureType(hl.getEquals(), hl.getEquals_Left(), comparableStuff);
		ensureFeatureType(hl.getEquals(), hl.getEquals_Right(), comparableStuff);

		ensureFeatureType(hl.getUnEquals(), hl.getUnEquals_Left(), comparableStuff);
		ensureFeatureType(hl.getUnEquals(), hl.getUnEquals_Right(), comparableStuff);

		ensureFeatureType(hl.getGreater(), hl.getGreater_Right(), comparableStuff);
		ensureFeatureType(hl.getGreater(), hl.getGreater_Left(), comparableStuff);

		ensureFeatureType(hl.getSmaller(), hl.getSmaller_Left(), comparableStuff);
		ensureFeatureType(hl.getSmaller(), hl.getSmaller_Right(), comparableStuff);

		ensureFeatureType(hl.getGreaterOrEquals(), hl.getGreaterOrEquals_Left(), comparableStuff);
		ensureFeatureType(hl.getGreaterOrEquals(), hl.getGreaterOrEquals_Right(), comparableStuff);

		ensureFeatureType(hl.getSmallerOrEquals(), hl.getSmallerOrEquals_Left(), comparableStuff);
		ensureFeatureType(hl.getSmallerOrEquals(), hl.getSmallerOrEquals_Right(), comparableStuff);

		useFixedType(hl.getInIntervalExpression(), hl.getBoolType());
		ensureFeatureType(hl.getInIntervalExpression(), hl.getInIntervalExpression_Expr(), hl.getIntType());
		ensureFeatureType(hl.getInIntervalExpression(), hl.getInIntervalExpression_Range(), hl.getIntRangeType());
	}

	private void bitOperators() throws TypesystemConfigurationException
	{
		// for bit operators, require integers
		ensureFeatureType(hl.getBitOr(), hl.getBitOr_Left(), hl.getIntType());
		ensureFeatureType(hl.getBitOr(), hl.getBitOr_Right(), hl.getIntType());
		ensureFeatureType(hl.getBitXor(), hl.getBitXor_Left(), hl.getIntType());
		ensureFeatureType(hl.getBitXor(), hl.getBitXor_Right(), hl.getIntType());
		ensureFeatureType(hl.getBitAnd(), hl.getBitAnd_Left(), hl.getIntType());
		ensureFeatureType(hl.getBitAnd(), hl.getBitAnd_Right(), hl.getIntType());
		ensureFeatureType(hl.getBitComplementExpr(), hl.getBitComplementExpr_Expr(), hl.getIntType());

		// ... and make them integers again
		useFixedType(hl.getBitAnd(), hl.getIntType());
		useFixedType(hl.getBitOr(), hl.getIntType());
		useFixedType(hl.getBitXor(), hl.getIntType());
		useFixedType(hl.getBitComplementExpr(), hl.getIntType());

		useFixedType(hl.getBitCheck(), hl.getBoolType());
		ensureFeatureType(hl.getBitCheck(), hl.getBitCheck_Left(), hl.getIntType());
		ensureFeatureType(hl.getBitCheck(), hl.getBitCheck_Right(), hl.getIntType());

	}

	private void booleanOperators() throws TypesystemConfigurationException
	{
		// and and or are boolean
		useFixedType(hl.getAnd(), hl.getBoolType());
		useFixedType(hl.getOr(), hl.getBoolType());

		// the arguments of and and or have to be boolean as well
		ensureFeatureType(hl.getOr(), hl.getOr_Left(), hl.getBoolType());
		ensureFeatureType(hl.getOr(), hl.getOr_Right(), hl.getBoolType());
		ensureFeatureType(hl.getAnd(), hl.getAnd_Left(), hl.getBoolType());
		ensureFeatureType(hl.getAnd(), hl.getAnd_Right(), hl.getBoolType());

		// The not operator is boolean and can only be applied to booleans
		ensureFeatureType(hl.getNotExpression(), hl.getNotExpression_Expr(), hl.getBoolType());
		useTypeOfFeature(hl.getNotExpression(), hl.getNotExpression_Expr());

	}

	private void nullStuff() throws TypesystemConfigurationException
	{
		declareCharacteristic(hl.getEnumParameterType(), NULLABLE);

		declareCharacteristic(hl.getCurvePointType(), NULLABLE);
		declareCharacteristic(hl.getCurveType(), NULLABLE);

		declareCharacteristic(hl.getLevelCurvePointType(), NULLABLE);
		declareCharacteristic(hl.getLevelCurvesType(), NULLABLE);

		declareCharacteristic(hl.getChannelSetType(), NULLABLE);
		declareCharacteristic(hl.getChannelType(), NULLABLE);

		declareCharacteristic(hl.getStringType(), NULLABLE);
		declareCharacteristic(hl.getStringIdType(), NULLABLE);

	}

	private void arithmeticOperators() throws TypesystemConfigurationException
	{
		// for arithmetic operators, the types on the
		// left and on the right must be compatible
		ensureUnorderedCompatibility(hl.getPlus(), hl.getPlus_Left(), hl.getPlus_Right());
		ensureUnorderedCompatibility(hl.getMinus(), hl.getMinus_Left(), hl.getMinus_Right());
		ensureUnorderedCompatibility(hl.getMulti(), hl.getMulti_Left(), hl.getMulti_Right());
		
		
		// ensureUnorderedCompatibility(hl.getDiv(), hl.getDiv_Left(),
		// hl.getDiv_Right()); //currently we do not support division

		// ... and the resulting type is the more general
		// type of left and right (i.e. if we add an int and a float
		// the resulting type will be float)
		computeCommonType(hl.getPlus(), hl.getPlus_Left(), hl.getPlus_Right());
		computeCommonType(hl.getMinus(), hl.getMinus_Left(), hl.getMinus_Right());
		computeCommonType(hl.getMulti(), hl.getMulti_Left(), hl.getMulti_Right());
		// computeCommonType(hl.getDiv(), hl.getDiv_Left(), hl.getDiv_Right());
		// //currently we do not support division

		// one can add numbers, curves, and numbers with units
		// (numberLikeThingsAndCurves).
		ensureFeatureType(hl.getPlus(), hl.getPlus_Left(), numberLikeThingsAndCurvesAndString);
		ensureFeatureType(hl.getPlus(), hl.getPlus_Right(), numberLikeThingsAndCurvesAndString);
		ensureFeatureType(hl.getMinus(), hl.getMinus_Left(), numberLikeThingsAndCurves);
		ensureFeatureType(hl.getMinus(), hl.getMinus_Right(), numberLikeThingsAndCurves);

		// but one can only multiply numbers (numberLikeThings)
		ensureFeatureType(hl.getMulti(), hl.getMulti_Left(), numberLikeThingsAndCurves); // numberLikeThings
		ensureFeatureType(hl.getMulti(), hl.getMulti_Right(), numberLikeThingsAndCurves); // numberLikeThings

		// ensureFeatureType(hl.getDiv(), hl.getDiv_Left(), numberLikeThings);
		// //currently we do not support division
		// ensureFeatureType(hl.getDiv(), hl.getDiv_Right(),
		// numberLikeThings);//currently we do not support division

		// ++ and -- are ints and can only be applied to ints
		ensureFeatureType(hl.getPreIncrementExpr(), hl.getPreIncrementExpr_Expr(), hl.getIntType());
		useTypeOfFeature(hl.getPreIncrementExpr(), hl.getPreIncrementExpr_Expr());
		ensureFeatureType(hl.getPreDecrementExpr(), hl.getPreDecrementExpr_Expr(), hl.getIntType());
		useTypeOfFeature(hl.getPreDecrementExpr(), hl.getPreDecrementExpr_Expr());

		useTypeOfFeature(hl.getParenExpr(), hl.getParenExpr_Expr());
	}

	private void primitiveTypes() throws TypesystemConfigurationException
	{
		// primitive types use themselves as their types

		// moved to ts //
		useCloneAsType(hl.getIntType());
		useCloneAsType(hl.getDoubleType());
		useCloneAsType(hl.getVoidType());
		useCloneAsType(hl.getBoolType());

		useFixedType(hl.getNullExpression(), hl.getNullType());

		declareCharacteristic(hl.getIntType(), INDEXING);

		// boolean literals are booleans
		useFixedType(hl.getTrueLiteral(), hl.getBoolType());
		useFixedType(hl.getFalseLiteral(), hl.getBoolType());

		// int is a subtype of float (ints can be used
		// where floats are expected)
		declareSubtype(hl.getIntType(), hl.getDoubleType());

		// ----------------------------------------------------
		// string stuff
		useCloneAsType(hl.getStringType());
		useCloneAsType(hl.getStringIdType());

		// a short string can be used where string is expected
		// but not vice versa
		declareSubtype(hl.getStringIdType(), hl.getStringType());
	}

	
	
	
	
	
	
	
	/**
	 * the type of an IntegerParameter depends on whether it has a unit or not
	 * and whether it is an array
	 */
	public EObject type(IntegerParameter ip, TypeCalculationTrace trace)
	{
		trace.add(ip, "no unit -> IntType");
		Type base = (Type) create(hl.getIntegerParameterType());
		if (ip.getKind() instanceof ArrayKind)
		{
			ArrayType arr = (ArrayType) create(hl.getArrayType());
			arr.setBaseType(base);
			return arr;
		}
		else
		{
			return base;
		}
	}

	/**
	 * for enum parameters, the type also depends on whether they are arrays or
	 * not
	 */
	public EObject type(EnumParameter ip, TypeCalculationTrace trace)
	{
		EnumParameterType base = (EnumParameterType) create(hl.getEnumParameterType());
		base.setParam(ip);
		if (ip.getKind() instanceof ArrayKind)
		{
			ArrayType arr = (ArrayType) create(hl.getArrayType());
			arr.setBaseType(base);
			return arr;
		}
		else
		{
			return base;
		}
	}

	public EObject type(DoubleParameter ip, TypeCalculationTrace trace)
	{
		DoubleParameterType base = (DoubleParameterType) create(hl.getDoubleParameterType());
		base.setParam(ip);
		if (ip.getKind() instanceof ArrayKind)
		{
			ArrayType arr = (ArrayType) create(hl.getArrayType());
			arr.setBaseType(base);
			return arr;
		}
		else
		{
			return base;
		}
	}

	/**
	 * note how in case of channels there is a special type for arrays of
	 * channels
	 */

	public EObject type(ChannelParameter ip, TypeCalculationTrace trace)
	{
		if (ip.getKind() instanceof ArrayKind)
		{
			return create(hl.getChannelSetType());
		}
		else
		{
			return create(hl.getChannelType());
		}
	}

	/**
	 * the type of a number literal depends on whether it has a dot (float!),
	 * and whether it has a unit associated with it
	 */
	public EObject type(NumberLiteral nl, TypeCalculationTrace trace)
	{
		EObject type;
		boolean isInt = nl.getValue().indexOf(".") < 0;
		if (isInt)
			type = create(hl.getIntType());
		else
			type = create(hl.getDoubleType());
		return type;
	}


	public EObject type(ParameterReadExpression pre, TypeCalculationTrace trace)
	{
		SymbolDeclaration param = pre.getParam();
		trace.add(pre, "using type of param");
		return typeof(param, trace.child("param", param));
	}

	public EObject type(AtsideExpression atside, TypeCalculationTrace trace)
	{
		trace.add(atside, "using type of expr");
		Expression parameter = atside.getExpr();
		return typeof(parameter, trace.child("expr", parameter));
	}
	
	
	
	
	

	public EObject type(GetProposalText1 proposal, TypeCalculationTrace trace)
	{
		return GetTextProposalType();
	}

	public EObject type(GetProposalText2 proposal, TypeCalculationTrace trace)
	{
		return GetTextProposalType();
	}

	private EObject GetTextProposalType()
	{
		return create(hl.getStringIdType());
	}

	
	
	
	
	/**
	 * and with the special case where a simple reference points to an array
	 */
	public EObject type(SymbolReference ref, TypeCalculationTrace trace)
	{
		SymbolDeclaration symbol = ref.getSymbol();
		EObject symbolType = typeof(symbol, trace.child("type of symbol", symbol));
		if (symbol instanceof EnumLiteral)
		{
			if (symbolType instanceof ArrayType) { return ((ArrayType) symbolType).getBaseType(); }
		}
		return symbolType;
	}

	
	

	public EObject type(BuiltInCall bic, TypeCalculationTrace trace)
	{
		BuiltInOp builtIn = bic.getBuiltIn();
		if (builtIn != null)
		{
			return typeof(builtIn.getResultType(), trace);
		}
		else
		{
			trace.add(bic, "builtin is null -> returning null");
			return null;
		}
	}

	/**
	 * the array access expression can be used on all kinds of multivalued
	 * elements so the typing rule is a little bit more complicated
	 */
	public EObject type(ArrayAccessExpression aa, TypeCalculationTrace trace)
	{
		EObject baseType = typeof(aa.getExpr(), trace);
		if (baseType instanceof ArrayType)
		{
			// for actual array types we return the base type
			ArrayType arrayBase = (ArrayType) baseType;
			EObject elementType = arrayBase.getBaseType();
			trace.add(aa, "using base type => " + typeString(elementType));
			return elementType;
		}
		if (baseType instanceof ChannelSetType)
		{
			// for channel set types we return a channel
			trace.add(aa, "channelset => " + typeString(baseType));
			return create(hl.getChannelType());
		}
		if (baseType instanceof CurveType)
		{
			// for curves, we return a curve point
			trace.add(aa, "curve => " + typeString(baseType));
			return create(hl.getCurvePointType());
		}
		if (baseType instanceof LevelCurvesType)
		{
			// for level curve, we return a level curve point
			trace.add(aa, "using base type => " + typeString(baseType));
			return create(hl.getLevelCurvePointType());
		}
		if (baseType instanceof DoubleParameterType)
		{
			trace.add(aa, "using base type => " + typeString(baseType));
			return create(hl.getDoubleParameterType());
		}
		return null; // should never happen
	}
	
	
	
	

	/**
	 * the type of string literals depends on whether they are more or less than
	 * 16 characters long
	 */
	public EObject type(TextLiteral t, TypeCalculationTrace trace)
	{
		if (t.getValue().length() > 32)
		{
			trace.add(t, "shorter than 32 characters => short string");
			return create(hl.getStringType());
		}
		else
		{
			trace.add(t, "more than 32 characters => string");
			return create(hl.getStringIdType());
		}
	}
	

	
	

	/**
	 * we have to check the parameter to which a curve refers in order to find
	 * out the type
	 */
	public EObject type(CrvParameter t, TypeCalculationTrace trace)
	{
		if (t.getScope() == ParameterScope.LVLCRV)
		{
			trace.add(t, "is a levelcurve -> levelcurves");
			return create(hl.getLevelCurvesType());
		}
		else if (t.getScope() == ParameterScope.CRV)
		{
			trace.add(t, "is a curve -> curve");
			return create(hl.getCurveType());
		}
		return null;
	}

	
	
	class OrderedEnum extends StaticCustomTypeChecker
	{
		public OrderedEnum()
		{
			super("ordered enum");
		}

		public boolean isValid(ITypesystem ts, EObject type, TypeCalculationTrace trace)
		{
			if (type instanceof EnumParameterType) { return ((EnumParameterType) type).getParam().isOrdered(); }
			return false;
		}
	}

	
	
	
	// ATTENTION! These must conform to the keywords used in
	// the grammar!!
	public String typeToString(BoolType ctx)
	{
		return "bool";
	}

	public String typeToString(IntType ctx)
	{
		return "int";
	}

	public String typeToString(StringType ctx)
	{
		return "string";
	}

	public String typeToString(StringIdType ctx)
	{
		return "stringID";
	}

	public String typeToString(DoubleType ctx)
	{
		return "double";
	}

	public String typeToString(CurveType ctx)
	{
		return "curve";
	}

	public String typeToString(VoidType ctx)
	{
		return "void";
	}

	public String typeToString(CurvePointType ctx)
	{
		return "curvepoint";
	}

	public String typeToString(LevelCurvesType ctx)
	{
		return "levelcurves";
	}

	public String typeToString(LevelCurvePointType ctx)
	{
		return "levelcurvepoint";
	}

	public String typeToString(ChannelSetType ctx)
	{
		return "channels";
	}

	public String typeToString(ChannelParameter ctx)
	{
		return "channel-parameter";
	}

	public String typeToString(ChannelType ctx)
	{
		return "channel";
	}

	public String typeToString(IntegerParameter ctx)
	{
		return "integer-parameter";
	}

	public String typeToString(EnumParameter ctx)
	{
		return "enum-parameter";
	}

	// TODO: this is a hack! We have to clean up the EClasses everywhere!
	public String typeToString(EClass ctx)
	{
		return typeString(create(ctx));
	}

	
	
	
	
	
	
	/**
	 * if necessary, a frequency can be considered an int and
	 */
	public EObject typeCoerce(EObject aFrequency, FrequenceType candidate, IntType expectedType, TypeCalculationTrace trace)
	{
		trace.add(candidate, "coercing from Frequency into int");
		return create(hl.getIntType());
	}

	/**
	 * if necessary, and int can be considered a frequency
	 */
	public EObject typeCoerce(EObject aFrequency, IntType candidate, FrequenceType expectedType, TypeCalculationTrace trace)
	{
		trace.add(candidate, "coercing from int into frequ");
		return create(hl.getFrequenceType());
	}

	/**
	 * if necessary, an IntegerParameterType can be considered an int
	 */
	public EObject typeCoerce(EObject e, IntegerParameterType candidate, IntType expectedType, TypeCalculationTrace trace)
	{
		trace.add(candidate, "coercing from integer-parameter into int");
		return create(hl.getIntType());
	}

	public EObject typeCoerce(EObject e, DoubleParameterType candidate, DoubleType expectedType, TypeCalculationTrace trace)
	{
		trace.add(candidate, "coercing from double-parameter into double");
		return create(hl.getDoubleType());
	}

	/**
	 * null can be coerced into any other type, if that other type has the
	 * characteristic nullable
	 */
	public EObject typeCoerce(EObject e, NullType candidate, EObject expectedType, TypeCalculationTrace trace)
	{
		String tname = typeString(expectedType);
		if (hasCharacteristic(expectedType, NULLABLE))
		{
			trace.add(candidate, tname + " is nullable, coercing to " + tname);
			return Utils.clone(expectedType);
		}
		return null;
	}

	/**
	 * if necessary, an int can be considered a string
	 */
	public EObject typeCoerce(EObject e, IntType candidate, StringType expectedType, TypeCalculationTrace trace)
	{
		if (e.eContainer() instanceof Plus)
		{
			trace.add(candidate, "under a plus, so coercing from int into string");
			return create(hl.getStringType());
		}
		return null;
	}

	/**
	 * if necessary, an int can be considered a stringId
	 */
	public EObject typeCoerce(EObject e, IntType candidate, StringIdType expectedType, TypeCalculationTrace trace)
	{
		if (e.eContainer() instanceof Plus)
		{
			trace.add(candidate, "under a plus, so coercing from int into stringid");
			return create(hl.getStringIdType());
		}
		return null;
	}

	/**
	 * if necessary, an bool can be considered a stringId
	 */
	public EObject typeCoerce(EObject e, BoolType candidate, StringIdType expectedType, TypeCalculationTrace trace)
	{
		if (e.eContainer() instanceof Plus)
		{
			trace.add(candidate, "under a plus, so coercing from bool into stringid");
			return create(hl.getStringIdType());
		}
		return null;
	}

	/**
	 * if necessary, an bool can be considered as a string
	 */
	public EObject typeCoerce(EObject e, BoolType candidate, StringType expectedType, TypeCalculationTrace trace)
	{
		if (e.eContainer() instanceof Plus)
		{
			trace.add(candidate, "under a plus, so coercing from bool into string");
			return create(hl.getStringType());
		}
		return null;
	}
	
	
	
	
	public Boolean compareTypes(EObject type1, EObject type2, CheckKind kind, TypeCalculationTrace trace)
	{
		
		if (kind==CheckKind.same)
		{
			return null;
		}
		if (type1 instanceof CurveType)
		{
			if (type2 instanceof CurveType ||
				type2 instanceof IntType ||
				type2 instanceof DoubleType)
			{
				return true;
			}
		
		}
		return null;
	}
}
