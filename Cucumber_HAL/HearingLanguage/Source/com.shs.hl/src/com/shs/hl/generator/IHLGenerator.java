package com.shs.hl.generator;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;

public interface IHLGenerator extends IGenerator
{
	
	public void SetPlatformVersion(GenerationInfo.PlatformInformation info);
	
	public void doGenerate(GenerationInfo.PlatformInformation info, Resource input, IFileSystemAccess fsa);

}
