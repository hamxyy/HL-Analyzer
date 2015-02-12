package com.shs.hl.generator.halsa

import com.shs.hl.hearingLanguage.FunctionDeclaration
import com.shs.hl.hearingLanguage.IfStatement
import com.shs.hl.hearingLanguage.LocalVariableDeclarationStatement
import com.shs.hl.hearingLanguage.Module
import com.shs.hl.hearingLanguage.Namespace
import com.shs.hl.hearingLanguage.PackageKind
import com.shs.hl.hearingLanguage.ReturnStatement
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import com.shs.hl.generator.GenerationInfo.PlatformInformation

class HLStaticAnalysis implements com.shs.hl.generator.IHLGenerator
{

	PlatformInformation platformInfo;

	override SetPlatformVersion(PlatformInformation info)
	{
		platformInfo = info
	}

	override doGenerate(PlatformInformation info, Resource input, IFileSystemAccess fsa)
	{
		platformInfo = info;
		doGenerate(input, fsa);
	}

	override doGenerate(Resource resource, IFileSystemAccess fsa)
	{
		var fileName = "";
		var stringBuilder = new HLSAStringBuilder;
		for (namesp : resource.allContents.toIterable.filter(typeof(Namespace)))
			if (isModule(namesp.eContainer))
			{
				var module = namesp.eContainer as Module
				for (namespace : module.namespaces)
				{
					for (package : namespace.namespaces)
					{
						if (package.isIsPackage)
						{
							fileName = package.name
							stringBuilder.append("=== " + package.packKind.toString + " " + package.name + " ===\n")

							if (package.packKind.equals(PackageKind.LIB))
							{
								generateLibrary(package, stringBuilder)
							}
							else
							{
								generateApplicationMacro(package, stringBuilder)
							}

							stringBuilder.append("=== " + package.packKind.toString + " " + package.name + " ===\n")
						}
					}
				}
			}

		fsa.generateFile(
			"d:/hl_analysis/generated_" + fileName + ".hlt",
			stringBuilder.toString
		)

	}

	def generateApplicationMacro(Namespace macroPackage, HLSAStringBuilder stringBuilder)
	{
		for (symbol : macroPackage.functions)
		{
			if (symbol instanceof FunctionDeclaration)
			{
				var function = symbol as FunctionDeclaration;
				stringBuilder.append("application function: " + function.name + "\n")
				generateFunction(function, stringBuilder);
			}
		}
	}

	def generateLibrary(Namespace macroPackage, HLSAStringBuilder stringBuilder)
	{
		for (symbol : macroPackage.functions)
		{
			if (symbol instanceof FunctionDeclaration)
			{
				var function = symbol as FunctionDeclaration;
				stringBuilder.append("library function: " + function.name + "\n")
				stringBuilder.IncreaseIndent;
				generateFunction(function, stringBuilder);
				stringBuilder.DecreaseIndent;
			}
		}
	}

	def generateFunction(FunctionDeclaration function, HLSAStringBuilder stringBuilder)
	{
		for (statement : function.body.statements)
		{
			switch (statement.class)
			{
				case LocalVariableDeclarationStatement:
				{
					var localVarDecl = statement as LocalVariableDeclarationStatement
					stringBuilder.append("declare " + localVarDecl.^var.name + "\n");
				}
				case ReturnStatement:
				{
					stringBuilder.append("function returns.\n");
				}
				case IfStatement:
				{
					stringBuilder.append("enter if block.\n");
					stringBuilder.append("exit if block.\n");
				}
			}

		}
	}

	def boolean isModule(EObject ob)
	{
		return (ob instanceof Module)
	}

}
