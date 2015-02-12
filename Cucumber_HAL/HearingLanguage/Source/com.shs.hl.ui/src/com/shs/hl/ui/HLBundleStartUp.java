package com.shs.hl.ui;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.shs.hl.ui.internal.HearingLanguageActivator;

public class HLBundleStartUp implements IStartup, IPerspectiveListener {

	private Set<String> workingSetIds;

	@Override
	public void perspectiveActivated(final IWorkbenchPage page,
			final IPerspectiveDescriptor perspective) {

	}

	@Override
	public void perspectiveChanged(final IWorkbenchPage page,
			final IPerspectiveDescriptor perspective, final String changeId) {

	}

	@Override
	public void earlyStartup() {
		initWorkingSets();
		removePrefPages();
		removeUnWantedActionSets();
		removePerspectives();

	}

	private void removePerspectives() {

	}

	private void removeUnWantedActionSets() {
		final IWorkbenchWindow[] windows = PlatformUI.getWorkbench()
				.getWorkbenchWindows();
		for (final IWorkbenchWindow window : windows) {
			final IWorkbenchPage page = window.getActivePage();
			if (page != null) {
				wipeActions(page);
			}
			window.addPerspectiveListener(this);
		}

	}

	private void wipeActions(final IWorkbenchPage page) {
		for (final String str : workingSetIds) {
			wipeAction(page, str);
		}
	}

	private void wipeAction(final IWorkbenchPage page, final String actionsetId) {
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				page.hideActionSet(actionsetId);
				
			}
		});
	}

	private void initWorkingSets() {
		workingSetIds = new HashSet<String>();
		workingSetIds.add("org.eclipse.ant.ui.actionSet.presentation");
		workingSetIds.add("org.eclipse.debug.ui.breakpointActionSet");
		workingSetIds.add("org.eclipse.debug.ui.debugActionSet");
		workingSetIds.add("org.eclipse.debug.ui.debugActionSet");
		workingSetIds.add("org.eclipse.debug.ui.launchActionSet");
		workingSetIds.add("org.eclipse.debug.ui.profileActionSet");
		workingSetIds.add("org.eclipse.jdt.debug.ui.JDTDebugActionSet");
		workingSetIds.add("org.eclipse.jdt.junit.JUnitActionSet");
		workingSetIds.add("org.eclipse.jdt.junit.JUnitActionSet");
		workingSetIds.add("org.eclipse.jdt.ui.A_OpenActionSet");
		workingSetIds.add("org.eclipse.jdt.ui.CodingActionSet");
		workingSetIds.add("org.eclipse.jdt.ui.JavaActionSet");
		workingSetIds.add("org.eclipse.jdt.ui.JavaElementCreationActionSet");
		
		workingSetIds
				.add("org.eclipse.jdt.ui.text.java.actionSet.presentation");
		workingSetIds.add("org.eclipse.team.ui.actionSet");
		workingSetIds.add("org.eclipse.ui.actionSet.keyBindings");
		workingSetIds.add("org.eclipse.ui.cheatsheets.actionSet");
		workingSetIds
				.add("org.eclipse.ui.edit.text.actionSet.annotationNavigation");
		workingSetIds
				.add("org.eclipse.ui.edit.text.actionSet.convertLineDelimitersTo");
		workingSetIds.add("org.eclipse.ui.edit.text.actionSet.navigation");
		workingSetIds.add("org.eclipse.ui.edit.text.actionSet.presentation");
		workingSetIds.add("org.eclipse.ui.externaltools.ExternalToolsSet");
		workingSetIds.add("org.eclipse.ui.NavigateActionSet");
		workingSetIds.add("org.eclipse.ui.WorkingSetActionSet");
		workingSetIds.add("org.eclipse.ui.WorkingSetModificationActionSet");
	}

	private void removePrefPages() {
		final PreferenceManager pm = PlatformUI.getWorkbench()
				.getPreferenceManager();
		pm.remove("org.eclipse.help.ui.browsersPreferencePage");
		pm.remove("org.eclipse.ant.ui.AntRuntimePreferencePage");
		pm.remove("org.eclipse.ant.ui.AntEditorPreferencePage");
		pm.remove("org.eclipse.debug.ui.DebugPreferencePage");
		pm.remove("org.eclipse.jdt.ui.preferences.JavaBasePreferencePage");
		pm.remove("org.eclipse.ant.ui.AntPreferencePage");
		pm.remove("org.eclipse.team.ui.TeamPreferences");
		pm.remove("org.eclipse.update.internal.ui.preferences.MainPreferencePage");
		pm.remove("org.eclipse.pde.ui.OSGiFrameworksPreferencePag");
		pm.remove("org.eclipse.pde.ui.MainPreferencePage");
		//pm.remove("org.eclipse.ui.preferencePages.GeneralTextEditor");
		pm.remove("org.eclipse.update.internal.ui.preferences.MainPreferencePage");
		pm.remove("org.eclipse.equinox.internal.p2.ui.sdk.ProvisioningPreferencePage");
		pm.remove("org.eclipse.ui.preferencePages.Workbench");
		// clean launch configuration
		pm.remove("org.eclipse.emf.mwe2.language.Mwe2");
		pm.remove("org.eclipse.xtext.Xtext");
	}

}
