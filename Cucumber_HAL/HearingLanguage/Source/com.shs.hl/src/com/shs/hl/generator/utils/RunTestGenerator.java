package com.shs.hl.generator.utils;

import java.io.FileWriter;

import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;

public class RunTestGenerator extends AbstractWorkflowComponent {

	@Override
	public void checkConfiguration(Issues issues) {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	protected void invokeInternal(WorkflowContext ctx, ProgressMonitor monitor,
			Issues issues) {
		StringBuffer bf = new StringBuffer();
		bf.append( "using System;");
		bf.append( "public class RunTests {\n" );
		bf.append( "    static void Main(string[] args) {\n" );
		for (String call: GenUtils.getTestFunctionCalls()) {
			bf.append( call+";\n" );
		}
		bf.append( "    Console.WriteLine(\"Tests finished; press Enter to terminate...\");");
		bf.append( "    Console.ReadLine();");
		bf.append( "	}\n" );
		bf.append( "}\n" );
		
		try  {
			FileWriter w = new FileWriter("src-gen/RunTests.cs");
			w.write(bf.toString());
			w.close();
		} catch ( Exception ex ) {
			ex.printStackTrace();
		}
	}

}
