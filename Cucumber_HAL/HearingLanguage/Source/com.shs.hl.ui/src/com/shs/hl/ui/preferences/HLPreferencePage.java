package com.shs.hl.ui.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.xtext.ui.editor.preferences.LanguageRootPreferencePage;

import com.shs.hl.ui.internal.HearingLanguageActivator;
import com.shs.hl.ui.utils.Constants;

public class HLPreferencePage extends LanguageRootPreferencePage
{

	public HLPreferencePage()
	{
		setPreferenceStore(HearingLanguageActivator.getInstance().getPreferenceStore());
	}

	
	@Override
	protected void adjustGridLayout() {
	
	};
	
	
	
	private GridData generateGridData( )
	{
		return generateGridData(10);
	}
	
	private GridData generateGridData(int indent )
	{
		GridData gd = new GridData();
		gd.grabExcessHorizontalSpace=true;
		gd.grabExcessVerticalSpace=false;
		gd.horizontalAlignment = SWT.FILL;
		gd.verticalAlignment = SWT.TOP;
		gd.horizontalIndent = 10;
		
		return gd;
	}
	
	@Override
	protected void createFieldEditors() {
		Composite parent = getFieldEditorParent();

		/// --------------------------------------------------------------
		/// Vendor configuration
		/// --------------------------------------------------------------
		org.eclipse.swt.widgets.Group vendorSetting = new org.eclipse.swt.widgets.Group(parent,SWT.SHADOW_ETCHED_IN) ;
		vendorSetting.setText("Vendor configuration");
			
		FontData[] fontData = vendorSetting.getFont().getFontData();
		vendorSetting.setFont(new Font(vendorSetting.getFont().getDevice(),fontData[0].getName(),fontData[0].getHeight(),SWT.BOLD));
		vendorSetting.setLayout(new GridLayout(1,true));
		vendorSetting.setLayoutData(generateGridData());
		Composite vendor = new Composite(vendorSetting, SWT.NO_SCROLL);
		vendor.setLayoutData(generateGridData(15));
		
		addField(new StringFieldEditor(Constants.VENDOR_OUT_PUT, 	 "Vendor subdirectory naming:", vendor));
		addField(new StringFieldEditor(Constants.ASSEMBLY_NAMESPACE, "Namespace for generated assemblies:", vendor));
		addField(new StringFieldEditor(Constants.PLATFORM_MACROS_D8, "D8 Platform dll name:", vendor));
		addField(new StringFieldEditor(Constants.PLATFORM_MACROS_D9, "D9 Platform dll name:", vendor));
		addField(new BooleanFieldEditor(Constants.USE_PROJECT_NAME_AS_DLL_SELECTION, "Use project name as assembly name", BooleanFieldEditor.SEPARATE_LABEL, vendor));
		
		/// --------------------------------------------------------------
		/// Assembly generation configuration
		/// --------------------------------------------------------------
		org.eclipse.swt.widgets.Group codeGenerator = new org.eclipse.swt.widgets.Group(parent,SWT.SHADOW_ETCHED_IN) ;
		
		FontData[] fontData2 = codeGenerator.getFont().getFontData();
		codeGenerator.setFont(new Font(codeGenerator.getFont().getDevice(),fontData2[0].getName(),fontData2[0].getHeight(),SWT.BOLD));
		codeGenerator.setText("Assembly generation utility settings");
		codeGenerator.setLayout(new GridLayout(1,true));			
		codeGenerator.setLayoutData(generateGridData());
		
		Composite comp1 = new Composite(codeGenerator, SWT.NO_SCROLL);
		comp1.setLayoutData(generateGridData(15));
				
		addField(new StringFieldEditor(Constants.BUILD_ASSEMBLY_BATCH, "Batchfile to use for build:", comp1));
		addField(new StringFieldEditor(Constants.ASSEMBLY_GENERATOR_BASEDIR, "Assembly Generator location:       ", comp1));
		addField(new StringFieldEditor(Constants.GENERATION_OUTPUT_FOLDER, "Output Directory      ", comp1));
	
		
		// since it is no required feature for 7.1 it is disabled as long it is not really needed
		
//		Composite comp2 = new Composite(codeGenerator, SWT.NO_SCROLL);
//		comp2.setLayout(new GridLayout(1,true));
//		comp2.setLayoutData(generateGridData(15));
//		
//		
//		addField(new DirectoryFieldEditor(Constants.REFERENCE_DIR, "Reference directory (to necessary libraries):", comp2));
		//                                                         "Assembly Generator location:                 "
		

		/// --------------------------------------------------------------
		/// Debugging configuration
		/// --------------------------------------------------------------
//		org.eclipse.swt.widgets.Group debugger = new org.eclipse.swt.widgets.Group(parent,SWT.SHADOW_ETCHED_IN) ;
//		
//		FontData[] fontData3 = debugger.getFont().getFontData();
//		debugger.setFont(new Font(debugger.getFont().getDevice(),fontData3[0].getName(),fontData3[0].getHeight(),SWT.BOLD));
//		debugger.setText("Debugging configuration");
//		debugger.setLayout(new GridLayout(1,true));			
//		debugger.setLayoutData(generateGridData());
//
//		Composite debugcomp1 = new Composite(debugger, SWT.NO_SCROLL);
//		debugcomp1.setLayoutData(generateGridData(15));
//
//
//		addField(new BooleanFieldEditor(Constants.SHOW_DEBUG_LIST, "Show process list for debugging", debugcomp1));
//

		
		parent.layout(true,true);
	}

}
