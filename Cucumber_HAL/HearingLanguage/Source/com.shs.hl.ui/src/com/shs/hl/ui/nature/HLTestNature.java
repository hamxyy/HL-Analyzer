package com.shs.hl.ui.nature;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class HLTestNature implements IProjectNature {

	public static final String NATURE_ID = "com.shs.hl.ui.hltestnature";

	private IProject project;

	public void configure() throws CoreException {
		 
	 }
 

	public void deconfigure() throws CoreException {
		
	}

	public IProject getProject() {
		return project;
	}

	public void setProject(IProject value) {
		project = value;
	}
}
