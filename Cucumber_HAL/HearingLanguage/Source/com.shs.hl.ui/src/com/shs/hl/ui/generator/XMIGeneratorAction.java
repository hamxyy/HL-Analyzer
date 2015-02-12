package com.shs.hl.ui.generator;

import org.eclipse.jface.viewers.ISelection;

public class XMIGeneratorAction {

	private XMIGenerator gen;
 
	public void run(ISelection selection) {
		gen = new XMIGenerator("XMIGenerator");
		gen.startUpGeneration(selection);

	}

}
