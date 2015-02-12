package com.shs.hl.ui.generator;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import com.shs.hl.ui.internal.HearingLanguageActivator;

/**
 * This class is used if you select Generate C# files and Build Assembly on a
 * folder, project, or file. It generates the C# code from the hearing language
 * files and generates a dll file.
 * 
 */
public class EnhancedGeneratorAction
{

	private static final String			console_name	= "com.shs.hl.generator.console";
	protected final IPreferenceStore	store			= HearingLanguageActivator.getInstance().getPreferenceStore();

	private final CSharpGenerator		gen;

	public EnhancedGeneratorAction()
	{
		gen = new CSharpGenerator(console_name);
	}

	public void run(ISelection fselection)
	{
		if (fselection != null && !fselection.isEmpty() && fselection instanceof IStructuredSelection)
		{
			// first cast the structured selection
			final IStructuredSelection structSel = (IStructuredSelection) fselection;
			if (gen.startUpGenerationChecked(fselection))
			{
				gen.startUpAssemblyGeneration(structSel);
			}
		}
	}

}
