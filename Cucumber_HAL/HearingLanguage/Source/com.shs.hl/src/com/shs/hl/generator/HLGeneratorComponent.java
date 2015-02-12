package com.shs.hl.generator;

import java.lang.reflect.Field;

import org.eclipse.emf.mwe2.runtime.workflow.IWorkflowContext;
import org.eclipse.xtext.generator.GeneratorComponent;
import org.eclipse.xtext.generator.IGenerator;

import com.google.inject.Injector;
import com.shs.hl.generator.GenerationInfo.PlatformInformation;

public class HLGeneratorComponent extends GeneratorComponent
{
	
	PlatformInformation platform = PlatformInformation.UNKNOWN;
	
	
	public void setPlatform(String plf)
	{
		platform = PlatformInformation.GetBestMatch(plf,null);
	}
	
	
	@Override
	public void invoke(IWorkflowContext ctx)
	{
		super.invoke(ctx);
	}
	
	
	@Override
	protected  IGenerator getCompiler()
	{
		IHLGenerator gen=null;
		
		try{
			Field privateInjector = GeneratorComponent.class.getDeclaredField("injector");
			privateInjector.setAccessible(true);
		
			Injector inject = (Injector) privateInjector.get((GeneratorComponent) this); 
		
			gen= inject.getInstance(IHLGenerator.class);
			
			if (gen!=null)
			{
				gen.SetPlatformVersion(platform);
			}
			
		}
		catch(Exception e)
		{
			
		}

		
		return gen;
	}
}
