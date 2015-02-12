package com.shs.hl.ui;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.PartSite;

@SuppressWarnings("restriction")
public class PluginChecker implements IStartup {

	private final String STDTLIBID = "com.shs.hl.stdlib";
	private final String ERRORMSG = "Multiple installations of the same version of STDLIB installed";

	@Override
	public void earlyStartup() {
		boolean isDublicateInstallation = false;
		final Set<String> versions = new HashSet<String>();
		final Set<String> tmp = new HashSet<String>();
		File f = new File(Platform.getInstallLocation().getURL().getPath()
				+ "/plugins");
		for (File file : f.listFiles()) {
			if (file.isFile() && file.getName().contains(STDTLIBID)) {
				String major = getMajorVersion(file.getName());
				if (!versions.add(major)) {
					isDublicateInstallation = true;
					tmp.add(major);
				}
			}
		}
		if (isDublicateInstallation) {
			final IWorkbench workbench = PlatformUI.getWorkbench();
			final StringBuilder bld = new StringBuilder();
			bld.append("\n");
			for (String str : tmp) {
				bld.append(str + "\n");
			}
			workbench.getDisplay().asyncExec(new Runnable() {
				@Override
				public void run() {
					IWorkbenchWindow window = workbench
							.getActiveWorkbenchWindow();
					if (window != null) {
						MessageDialog.openError(window.getShell(), "ERROR",
								ERRORMSG + bld.toString());
						IActionBars actionBars = ((PartSite) window
								.getActivePage().getActivePart().getSite())
								.getActionBars();
						if (actionBars != null
								&& actionBars.getStatusLineManager() != null) {
							actionBars.getStatusLineManager().setErrorMessage(
									ERRORMSG);
						}
					}
				}
			});

		}
	}

	private String getMajorVersion(String str) {
		String[] tokens = str.split("_");
		return tokens[0] + "_" + tokens[1];
	}
}
