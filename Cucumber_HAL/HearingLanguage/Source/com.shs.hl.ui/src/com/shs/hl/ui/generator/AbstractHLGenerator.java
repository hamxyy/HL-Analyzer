package com.shs.hl.ui.generator;

//import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
//import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2;
//import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.shs.hl.ui.internal.HearingLanguageActivator;

import com.shs.hl.ui.utils.LoggerUtil;

public abstract class AbstractHLGenerator
{
	@Inject
	private Provider<EclipseResourceFileSystemAccess2>	fileAccessProvider;

	@Inject
	protected IResourceSetProvider						resourceSetProvider;

	@Inject
	protected IContainer.Manager						containerManager;

	@Inject
	protected IResourceDescription.Manager				descriptionManager;

	@Inject
	protected ResourceDescriptionsProvider				provider;

	// @Inject
	// protected Provider<XtextResourceSet> resourceProvider;

	protected MessageConsoleStream						messageConsoleStream;
	protected MessageConsoleStream						errorConsoleStream;
	private final String								consoleName;
	final EclipseResourceFileSystemAccess2				fsa;

	private int											proceed	= -1;

	public AbstractHLGenerator(final String messageConsoleName)
	{
		HearingLanguageActivator.getInstance().getInjector("com.shs.hl.HearingLanguage").injectMembers(this);
		consoleName = messageConsoleName;
		messageConsoleStream = findConsole(messageConsoleName).newMessageStream();
		errorConsoleStream = findConsole(messageConsoleName).newMessageStream();
		setUpConsoleWriters();
		fsa = fileAccessProvider.get();

		// rs = resourceProvider.get();
	}

	
	private void setUpConsoleWriters()
	{
		messageConsoleStream = findConsole(consoleName).newMessageStream();
		errorConsoleStream = findConsole(consoleName).newMessageStream();
		errorConsoleStream.setColor(new Color(Display.getCurrent(), 255, 0, 0));
	}

	protected static MessageConsole findConsole(final String name)
	{
		return LoggerUtil.findConsole(name);
	}

	
	
	
	protected Map<IResource, Set<IResource>> getResourcesMap(final ISelection selection)
	{
		// Set<IResource> members = new HashSet<IResource>();
		final Map<IResource, Set<IResource>> map = new HashMap<IResource, Set<IResource>>();

		// we then find out how many objects are in the selection
		// and iterate over it - this is in case of Multi-Selections!
		for (final Object selectedObject : ((IStructuredSelection) selection).toList())
		{

			if (selectedObject != null)
			{

				// find out the resource we want to generate from
				final IResource resource;
				if (selectedObject instanceof IResource)
				{
					resource = (IResource) selectedObject;
				}
				else if (selectedObject instanceof IJavaElement)
				{
					resource = ((IJavaElement) selectedObject).getResource();

				}
				else
				{
					resource = null;
				}

				// we then find out all the members of the resource
				// and put it into the HashMap from which we generate
				if ((resource != null) && !(resource.getName().startsWith(".")) && !(resource.getName().equals("bin")))
				{
					final Set<IResource> members = getMembers(resource);
					map.put(resource, members);
				}
			}
		}
		return map;
	}

	/**
	 * this method collect all members of the resource
	 */
	protected Set<IResource> getMembers(final IResource resource)
	{
		final Set<IResource> result = new HashSet<IResource>();

		// the behavior depends on the kind of resource
		switch (resource.getType())
		{
			case IResource.PROJECT:
				// if it is a project, it may contain other folders
				// so we called get member recursively on every member
				try
				{
					final IResource[] projectMembers = ((IProject) resource).members(false);
					for (final IResource iResource : projectMembers)
					{
						result.addAll(getMembers(iResource));
					}
				}
				catch (final CoreException e)
				{
					LoggerUtil.log(IStatus.ERROR, "An exception occured while collecting the members of " + resource.getName(), e);
					errorConsoleStream.println("An exception occured while collecting the members of " + resource.getName() + e.getMessage());
				}
				break;
			case IResource.FOLDER:
				// if it is a folder it may contain more subfolders or files,
				// so we call it recursively again
				try
				{
					final IFolder folder = (IFolder) resource;
					IResource[] folderMembers;

					// Exclude the binary folder...
					if (!folder.getName().equals("bin") && !folder.getName().startsWith("."))
					{
						folderMembers = ((IFolder) resource).members(false);
						for (final IResource iResource : folderMembers)
						{
							result.addAll(getMembers(iResource));
						}
					}
				}
				catch (final CoreException e)
				{
					LoggerUtil.log(IStatus.ERROR, "An exception occured while collecting the members of " + resource.getName(), e);
					errorConsoleStream.println("An exception occured while collecting the members of " + resource.getName() + e.getMessage());
				}
				break;
			case IResource.FILE:
				// finally, if it is a file, add the file itself
				// so in the end, only the files are in the list we return
				// note that we only add hearing language files
				if (hasFileExtension(resource))
				{
					result.add(resource);
				}
				break;
		}

		return result;
	}

	/**
	 * generation actually happens only for hearing language files.
	 */
	private boolean hasFileExtension(final IResource resource)
	{
		if ((resource instanceof IFile) && ("hlt".equals(resource.getFileExtension()))) { return true; }
		return false;
	}

	protected void setGeneratorConsoleToActiveWindow()
	{
		final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		final String id = IConsoleConstants.ID_CONSOLE_VIEW;
		IConsoleView view;
		try
		{
			view = (IConsoleView) page.showView(id);
			view.display(findConsole(consoleName));
		}
		catch (final PartInitException e)
		{
			// do nothing
			e.printStackTrace();
		}
	}

	private boolean showWarningAndCheckForContinue()
	{
		Display.getDefault().syncExec(new Runnable()
		{

			@Override
			public void run()
			{
				String[] labels = new String[] { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL };
				MessageDialog dialog = new MessageDialog(Display.getDefault().getActiveShell(), "Selected resource have errors!", null,
						"There were source code errors. Would you like to continue the build process?", MessageDialog.WARNING, labels, SWT.NONE)
				{
					@Override
					public org.eclipse.swt.graphics.Image getQuestionImage()
					{
						return getErrorImage();
					};
				};
				proceed = dialog.open();
			}
		});

		return (proceed == IDialogConstants.CANCEL_ID) ? false : true;
	}

	protected boolean projectHasErrors(final ISelection sel)
	{
		IProject project = getProject(sel);
		if (project != null)
		{
			try
			{
				IMarker[] errorMarkers = project.findMarkers(null, true, IResource.DEPTH_INFINITE);
				for (IMarker mr : errorMarkers)
				{
					if (mr.getType().equalsIgnoreCase("org.eclipse.xtext.ui.check.fast")) { return true; }
				}

			}
			catch (CoreException e)
			{
				messageConsoleStream.print(e.getMessage());
			}

		}
		return false;
	}

	// protected void initPlatformVersion(final ISelection sel)
	// {
	// IProject project = getProject(sel);
	// if (project == null)
	// {
	// errorConsoleStream.println("Cannot determine selected platform");
	// return;
	// }
	//
	// StdlibInfo info = BundleHelper.getStdlibVersionInfoFromManifest(project);
	//
	// platformInfo =
	// GenerationInfo.PlatformInformation.GetBestMatch(BundleHelper.getStdlibVersion(info.StdlibName),
	// info.StdlibVersion);
	//
	// }

	protected boolean ContinueBuildEvenOnErrors(final ISelection sel)
	{

		if (projectHasErrors(sel)) { return showWarningAndCheckForContinue(); }
		return true;
	}

	private IProject getProject(final ISelection sel)
	{
		if (sel == null) return (IProject) null;

		Object selection = ((IStructuredSelection) sel).getFirstElement();
		if (selection instanceof IJavaElement)
		{
			return ((IJavaElement) selection).getJavaProject().getProject();

		}
		else if (selection instanceof IResource) { return ((IResource) selection).getProject(); }

		return (IProject) selection;
	}

	public boolean startUpGenerationChecked(final ISelection sel)
	{
		if (ContinueBuildEvenOnErrors(sel))
		{
			startUpGeneration(sel);
			return true;
		}
		return false;
	}

	public void startUpGeneration(final ISelection sel)
	{
		setGeneratorConsoleToActiveWindow();

		// first we start a progress monitor
		final ProgressMonitorDialog pmd = new ProgressMonitorDialog(Display.getDefault().getActiveShell());

		// if dirty editors exist - ask for save
		LoggerUtil.log(IStatus.INFO, "check if dirty editors exist and ask for save");
		PlatformUI.getWorkbench().saveAllEditors(true);

		try
		{
			// within the progress monitor we actually run the code generation
			pmd.run(true, true, new IRunnableWithProgress()
			{
				@Override
				public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
				{
					runGeneration(monitor, sel);
				}
			});
		}
		catch (final InvocationTargetException e1)
		{
			LoggerUtil.log(IStatus.ERROR, "An exception occured while executing the generator", e1);
			errorConsoleStream.println("An exception occured while executing the generator" + e1.getMessage());
		}
		catch (final InterruptedException e1)
		{
			LoggerUtil.log(IStatus.ERROR, "An exception occured while executing the generator", e1);
			errorConsoleStream.println("An exception occured while executing the generator" + e1.getMessage());
		}
	}

	public abstract void runGeneration(IProgressMonitor monitor, ISelection selection);

	/**
	 * Initializes the ResourceSet with all potential resources visible from a
	 * given resource. This allows resolving cross-references to these
	 * resources.
	 * 
	 * @param ctx
	 *            A resource
	 */
	protected void initializeResourceSet(Resource ctx)
	{
		IResourceDescription description = descriptionManager.getResourceDescription(ctx);
		IResourceDescriptions resourceDescriptions = provider.getResourceDescriptions(ctx);
		List<IContainer> visibleContainers = containerManager.getVisibleContainers(description, resourceDescriptions);

		for (org.eclipse.xtext.resource.IContainer container : visibleContainers)
		{
			for (IResourceDescription rd : container.getResourceDescriptions())
			{
				ctx.getResourceSet().getResource(rd.getURI(), true);
			}
		}
	}

}
