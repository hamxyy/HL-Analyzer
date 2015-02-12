package com.shs.hl.debug.ui.launching;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;

import com.shs.hl.debug.ui.Constants;

public class HLDebugLaunchShortCut implements ILaunchShortcut {

	@Override
	public void launch(ISelection selection, String mode) {
		 // must be a structured selection with one file selected
        IFile file = (IFile) ((IStructuredSelection)selection).getFirstElement();

        // check for an existing launch config for the HL file
        String path = file.getFullPath().toString(); 
        ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
        ILaunchConfigurationType type = launchManager.getLaunchConfigurationType(Constants.HL_DEBUG_LAUNCH_TYPE);
        try {
            ILaunchConfiguration[] configurations = launchManager.getLaunchConfigurations(type);
            for (int i = 0; i < configurations.length; i++) {
                ILaunchConfiguration configuration = configurations[i];
                String attribute = configuration.getAttribute(Constants.HL_PROGRAM, (String)null);
                if (path.equals(attribute)) {
                    DebugUITools.launch(configuration, mode);
                    return;
                }
            }
        } catch (CoreException e) {
            return;
        }
        
        try {
            ILaunchConfigurationWorkingCopy workingCopy = type.newInstance(null, file.getName());
            workingCopy.setAttribute(Constants.HL_PROGRAM, path);
            ILaunchConfiguration configuration = workingCopy.doSave();
            DebugUITools.launch(configuration, mode);
        } catch (CoreException e1) {
        }

	}

	@Override
	public void launch(IEditorPart editor, String mode) {
		// TODO Auto-generated method stub

	}

}
