package com.shs.hl.generator.utils

import com.shs.common.commonLanguage.EnumLiteral
import com.shs.common.commonLanguage.EnumParameter
import com.shs.common.commonLanguage.ParameterScope
import com.shs.common.commonLanguage.SymbolDeclaration
import com.shs.hl.hearingLanguage.BoolType
import com.shs.hl.hearingLanguage.BuiltInOp
import com.shs.hl.hearingLanguage.ChannelSetType
import com.shs.hl.hearingLanguage.CurvePointType
import com.shs.hl.hearingLanguage.CurveType
import com.shs.hl.hearingLanguage.DoubleType
import com.shs.hl.hearingLanguage.FrequencyType
import com.shs.hl.hearingLanguage.FunctionDeclaration
import com.shs.hl.hearingLanguage.FunctionKind
import com.shs.hl.hearingLanguage.GainType
import com.shs.hl.hearingLanguage.IsOffsAppExpression
import com.shs.hl.hearingLanguage.LevelCurvePointType
import com.shs.hl.hearingLanguage.LevelCurvesType
import com.shs.hl.hearingLanguage.Module
import com.shs.hl.hearingLanguage.Namespace
import com.shs.hl.hearingLanguage.NumericType
import com.shs.hl.hearingLanguage.ParameterReadExpression
import com.shs.hl.hearingLanguage.SymbolReference
import com.shs.hl.hearingLanguage.TestingStatement
import com.shs.hl.hearingLanguage.TextType
import com.shs.hl.hearingLanguage.Type
import com.shs.hl.hearingLanguage.VoidType
import com.shs.hl.typesystem.HLTypesystem
import de.itemis.xtext.typesystem.ITypesystem
import de.itemis.xtext.typesystem.trace.TypeCalculationTrace
import java.util.ArrayList
import java.util.List
import org.eclipse.emf.common.util.TreeIterator
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import com.google.inject.Singleton
import java.util.Locale

@Singleton
class GenUtils
{ 

	
	/////////////////////////////////////////////////////////
	
	// private static final ParameterReadExpression appEx = null;

	
	val private static ITypesystem ts = new HLTypesystem();
	var private static int uid = 0;

	val private static List<String> testFunctionCalls = new ArrayList<String>();

	def public static Boolean hasTestFunctions(Object ns)
	{
		switch (ns)
		{
			Namespace: return hasTestFunctions(ns)
			default: false
		}
	}

	def public static Boolean hasTestFunctions(Namespace ns)
	{
		for (SymbolDeclaration decl : ns.getFunctions())
		{
			if((decl as FunctionDeclaration).getKind() == FunctionKind::TEST)
			{
				return true;
			}
		}
		return false;
	}

	// TODO method too many args that cn be derived from 1st arg
	def public static String registerTestFunction(FunctionDeclaration d, String ns, String classname)
	{
		testFunctionCalls.add("new " + ns + "." + classname + "()." + d.getName() + "()");
		return "";
	}

	def public static List<String> getTestFunctionCalls()
	{
		return testFunctionCalls;
	}

	def public static List<ParameterReadExpression> getParameterReadExpressions(Object functionDeclaration)
	{
		val List<ParameterReadExpression> parameterReadExpressions = new ArrayList<ParameterReadExpression>();
		val List<String> functionNames = new ArrayList<String>();
		getRecursiveParameterReadExpressions(functionDeclaration, parameterReadExpressions, functionNames);
		return parameterReadExpressions;
	}

	def public static void getRecursiveParameterReadExpressions(Object functionDeclaration,
		List<ParameterReadExpression> parameterReadExpressions, List<String> functionNames)
	{

		if(!AddFunctionNameToList(functionDeclaration, functionNames)) return;

		val TreeIterator<EObject> allContents = (functionDeclaration as EObject).eAllContents();
		while(allContents.hasNext())
		{
			val EObject objj = allContents.next();

			if(typeof(ParameterReadExpression).isAssignableFrom(objj.getClass()))
			{
				if(parameterReadExpressions.findFirst [ para |
					para.getParam() == (objj as ParameterReadExpression).getParam()
				] == null)
				{
					parameterReadExpressions.add(objj as ParameterReadExpression);
				}

			}
		}

		for (SymbolReference calledFunctionDeclaration : EcoreUtil2::getAllContentsOfType(functionDeclaration as EObject,
			typeof(SymbolReference)))
		{
			getRecursiveParameterReadExpressions(calledFunctionDeclaration.getSymbol(), parameterReadExpressions, functionNames);
		}
	}

	def public static List<EnumLiteral> getEnumLiterals(Object functionDeclaration)
	{
		var List<EnumLiteral> enumLiteralElements = new ArrayList<EnumLiteral>();
		var List<String> functionNames = new ArrayList<String>();
		getRecursiveEnumLiterals(functionDeclaration, enumLiteralElements, functionNames);
		return enumLiteralElements;
	}

	def static void getRecursiveEnumLiterals(Object functionDeclaration, List<EnumLiteral> literalList, List<String> functionNames)
	{
		if(!AddFunctionNameToList(functionDeclaration, functionNames)) return;

		var TreeIterator<EObject> allContents = (functionDeclaration as EObject).eAllContents();
		while(allContents.hasNext())
		{
			var EObject obj = allContents.next();

			if(typeof(IsOffsAppExpression).isAssignableFrom(obj.getClass))
			{

				var SymbolReference to = ((obj as IsOffsAppExpression).getArgTo() as SymbolReference)

				if(to != null)
				{
					var Object someTmp = to.getSymbol();

					if(!(someTmp instanceof EnumLiteral)) return;

					val EnumLiteral lit = someTmp as EnumLiteral;
					val EnumParameter parm = (lit.eContainer() as EnumParameter);

					if(literalList.findFirst
						[
							en|en.name == lit.getName && (en.eContainer as EnumParameter).getName() == parm.getName
						] ==null)
					{
						literalList.add(lit);
					}

				}
			}
		}

		for (SymbolReference calledFunctionDeclaration : EcoreUtil2::getAllContentsOfType(functionDeclaration as EObject,
			typeof(SymbolReference)))
		{
			getRecursiveEnumLiterals(calledFunctionDeclaration.getSymbol(), literalList, functionNames);
		}
	}

	def private static boolean AddFunctionNameToList(Object functionDeclaration, List<String> functionNames)
	{

		if(!typeof(FunctionDeclaration).isAssignableFrom(functionDeclaration.getClass()))
			return false;

		var String ownFunctionName = (functionDeclaration as FunctionDeclaration).getName();
	    var Namespace containerNS = ((functionDeclaration as FunctionDeclaration).eContainer() as Namespace);
		

		// Due to a naming conflict we need a fully qualified namespace lookup!
		val res = new StringBuffer();
		parentNamespacesInclThis(containerNS).reverse().forEach([ns|res.append(ns.name+".")]);
		
		res.append(ownFunctionName);
		ownFunctionName = res.toString();
		
		for (String functionName : functionNames)
		{
			if(functionName == ownFunctionName)
				return false;
		}
		functionNames.add(ownFunctionName);
		return true;
	}

	def public static Object typeOf(Object o)
	{
		ts.^typeof(o as EObject, new TypeCalculationTrace())
	}

	def public static String uniquify(String name)
	{
		uid = uid + 1; //unfortunately uid++ does not wor here
		name + (uid)
	}

	/////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////
	// private static final ParameterReadExpression appEx = null;
	def boolean isEmpty(Namespace namespace)
	{
		namespace.functions.size == 0;
	}

	def String className(Namespace namespace)
	{
		namespace.name;
	}

	def String csFileName(Namespace namespace)
	{

		//TODO:test methods
		//parentNamespacesInclThis().reverse().name.toString("/")+"/"+hlFileName()+".cs";
		val resNS = new StringBuffer();
		parentNamespacesInclThis(namespace).reverse().forEach[ns|resNS.append("/" + ns.name)];
		resNS.append("/" + hlFileName(namespace) + ".cs").toString;
	}

	def static String nameSpacesToRoot(Namespace name)
	{
		val res = new StringBuffer();
		parentNamespacesInclThis(name).reverse().forEach([ns|res.append("." + ns.name)]);
		res.append("." + hlFileName(name)).toString;
	}

	//TODO reimplement cleaner
	def String csNamespace(Namespace namespace)
	{
		var List<Namespace> lst = parentNamespaces(namespace);
		if(lst.empty)
		{
			namespace.name
		}
		else
		{
			var str = "";
			for (tmp : parentNamespaces(namespace).reverse())
			{
				str = str + "." + tmp.name
			}
			if(!str.empty)
			{
				str.replaceFirst(".", "") + "." + namespace.name
			}
			else
				namespace.name
		}
	}

	def String qualID(TestingStatement testSt)
	{

		//((Namespace)goUpToFunction().eContainer()).csNamespace()+":"+goUpToFunction(this).name+"."+id;
		(goUpToFunction(testSt).eContainer() as Namespace).csNamespace + ":" + (goUpToFunction(testSt)).name + "." + testSt.id
	}

	def String csFunction(BuiltInOp b)
	{
		b.name
	}

	def FunctionDeclaration goUpToFunction(Object ctx)
	{
		EcoreUtil2::getContainerOfType(ctx as EObject, typeof(FunctionDeclaration));
	}

	def asCsType(Type t)
	{
		switch (t)
		{
			VoidType:
				"void"
			TextType:
				"string"
			BoolType:
				"bool"
			DoubleType:
				"double" // attention: DoubleType is a subtype of numeric type - therefor it has to be specified before!
			NumericType:
				"int"
			FrequencyType:
				"int"
			GainType:
				"int"
			CurvePointType:
				"ICurvePoint"
			CurveType:
				"ICurve"
			LevelCurvesType:
				"ILevelCurve"
			LevelCurvePointType:
				"ILevelCurvePoint"
			ChannelSetType:
				"HIChannels"
			default:
				"ERROR: not yet implemented: " + t.eClass.name
		}
	} //TODO metatype 

	def String extendsClause(Namespace namespace)
	{
		switch namespace.packKind.toString()
		{
			case "ProgramSelection": ": MacroProgramSelectionBase"
			case "MonAppProgramSelection": ": MacroMonauralProgramSelectionBase"
			case "MixedMode": ": MacroMixedModeBase"
			case "FirstFit": ": MacroFirstFitBase"
			case "TargetSelection": ": MacroTargetSelectionBase"
			case "TargetSync": ": MacroTargetSyncBase"
			case "TestSettings": ": MacroMixedModeBase"
			case "AutoFit": ": MacroAutoFitBase"
			case "FittingAssistent": ": MacroFittingAssistentBase"
			case "Lib": ": MacroBase"
			default: ""
		}
	}

	def String controlsClass(ParameterScope s)
	{
		switch s.toString()
		{
			case "hi": "HIControls"
			case "pat": "PatParameters"
			case "env": "EnvParameters"
			case "fit": "FitParameters"
			case "crv": "Curves"
			case "levelcrv": "LevelCurves"
			case "cap": "Capabilities"
			case "std": "StdVariables"
			case "plf": "PlfVariables"
			default: "ERROR: scopeArray not implemented for " + s
		}
	}

	def parameterReadScope(ParameterScope s)
	{
		switch s
		{
			case ParameterScope::HI: ""
			case ParameterScope::CRV: ""
			case ParameterScope::LVLCRV: ""
			default: s.toString()
		}
	}

	def String getEnumScopeTag(EnumParameter t)
	{
		switch t.scope
		{
			case ParameterScope::HI: "HIPositions."
			case ParameterScope::PAT: "PatValues."
			case ParameterScope::ENV: "EnvValues.envval"
			case ParameterScope::CRV: "Crv."
			case ParameterScope::LVLCRV: "LvlCrv."
			case ParameterScope::CAP: "CapValues.capval"
			case ParameterScope::STD: "StdValues.stdval"
			case ParameterScope::FIT: "FitValues.fitval"
			case ParameterScope::PLF: "PlfValues.plfval"
			default: ""
		}
	}

	def scopeArray(ParameterScope s)
	{
		switch s.toString
		{
			case "hi": "_hi"
			case "pat": "_pat"
			case "env": "_env"
			case "fit": "_fit"
			case "crv": "_crv"
			case "levelcrv": "_lvlcrv"
			case "cap": "_cap"
			case "std": "_std"
			case "plf": "_plf"
			default: "ERROR: scopeArray not implemented for " + s
		}
	}

	def boolean isMixedMode(Namespace namespace)
	{
		return namespace.packKind.toString.equals("MixedMode")
	}

	//ToDO: Check if valid
	//	public static Object typeof(Object o) {
	//		return ts.typeof((EObject) o, new TypeCalculationTrace());
	//	}
	def static List<Namespace> parentNamespaces(Namespace s)
	{
		var res = new ArrayList<Namespace>()
		var current = s.eContainer();

		while(current instanceof Namespace)
		{
			res.add(current as Namespace)
			current = current.eContainer();
		}
		return res
	}

	def static List<Namespace> parentNamespacesInclThis(Namespace s)
	{
		var List<Namespace> res = new ArrayList<Namespace>();
		res.add(s);
		res.addAll(parentNamespaces(s));
		return res;
	}

	def static String hlFileName(Namespace s)
	{
		var String n = s.eResource().getURI().toFileString();
		n = n.substring(0, n.lastIndexOf("."));
		var int p = n.lastIndexOf("/");
		if(p >= 0)
		{
			return n.substring(p);
		}
		else
		{
			p = n.lastIndexOf("\\");
			if(p >= 0)
			{
				return n.substring(p);
			}
		}
		return null; // can never happen :-)
	}

	def static String resFileName(Module m)
	{
		var String targetUri = hlsourceFile(m).replaceAll(".hl", ".cs");
		targetUri = targetUri.replaceAll("/", ".");
		if(targetUri.endsWith("'"))
			targetUri = targetUri.substring(0, targetUri.length() - 1);
		return targetUri;
	}

	def static String hlsourceFile(Module m)
	{
		var Resource eResource = m.eResource();
		var String uri = eResource.getURI().toString();
		var String targetUri;
		var int p = uri.indexOf("/bin/");
		if(p >= 0)
		{
			targetUri = uri.substring(p + 5);
		}
		else
		{
			p = uri.indexOf("/models/");
			targetUri = uri.substring(p + 8);
		}
		return targetUri;
	}

	def Integer getLineNumber(EObject ob)
	{
		NodeModelUtils::findActualNodeFor(ob).getStartLine()
	} //	def boolean isLast(List lst, Object ob) {
//		!lst.empty && (ob == lst.get(lst.size - 1));
//	}
}
