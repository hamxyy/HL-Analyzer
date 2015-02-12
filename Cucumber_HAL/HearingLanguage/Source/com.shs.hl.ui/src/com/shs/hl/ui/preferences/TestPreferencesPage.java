package com.shs.hl.ui.preferences;

import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.xtext.ui.editor.preferences.AbstractPreferencePage;

import com.shs.hl.ui.internal.HearingLanguageActivator;
import com.shs.hl.ui.utils.Constants;

public class TestPreferencesPage extends AbstractPreferencePage {

	public TestPreferencesPage() {
		setPreferenceStore(HearingLanguageActivator.getInstance().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		addField(new FileFieldEditor(Constants.TEST_RUNNER_PATH, "Test Runner Path:", getFieldEditorParent()));
		addField(new StringFieldEditor(Constants.TEST_ASSEMBLY_NAME, "Test Assembly Name:", getFieldEditorParent()));
		addField(new StringFieldEditor(Constants.TEST_ASSEMBLY_NAMESPACE, "Test Assembly Namespace:", getFieldEditorParent()));
		addField(new StringFieldEditor(Constants.VENDOR_TEST_OUTPUT, "Vendor Test Output:", getFieldEditorParent()));
		addField(new FileFieldEditor(Constants.TEST_RUNTIME, "Test Runtime:", getFieldEditorParent()));
		addField(new DirectoryFieldEditor(Constants.GENERATION_OUTPUT_FOLDER, "Output Root:", getFieldEditorParent()));
	}
}
