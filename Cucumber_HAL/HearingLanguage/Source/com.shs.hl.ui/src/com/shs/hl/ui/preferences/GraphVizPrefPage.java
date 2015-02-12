package com.shs.hl.ui.preferences;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.xtext.ui.editor.preferences.AbstractPreferencePage;

import com.shs.hl.ui.internal.HearingLanguageActivator;

public class GraphVizPrefPage extends AbstractPreferencePage {

	public GraphVizPrefPage() {
		setPreferenceStore(HearingLanguageActivator.getInstance().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		addField(new DirectoryFieldEditor("GraphvizPath", "Graphviz Installation:", getFieldEditorParent()));
		addField(new RadioGroupFieldEditor("CallGraphParent", "Call Graph Type:", 2, new String[][] {
				{ "Graph with Namespace", "Namespace" },
				{ "Graph with Filenames", "filenames" } }, getFieldEditorParent()));
		addField(new RadioGroupFieldEditor("layout", "Layout:", 2, new String[][] {
				{ "Circular", "circular" }, { "linear", "linear" } }, getFieldEditorParent()));

	}

}
