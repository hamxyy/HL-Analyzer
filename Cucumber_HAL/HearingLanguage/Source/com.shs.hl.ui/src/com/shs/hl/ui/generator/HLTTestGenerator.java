package com.shs.hl.ui.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.internal.core.Openable;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.shs.hl.generator.GenerationInfo;
import com.shs.hl.generator.GenerationInfo.PlatformInformation;
import com.shs.hl.generator.IHLGenerator;
import com.shs.hl.generator.halsa.generator.HLTGenerateTestWalker;
import com.shs.hl.ui.internal.HearingLanguageActivator;
import com.shs.hl.ui.utils.BundleHelper;
import com.shs.hl.ui.utils.BundleHelper.StdlibInfo;
import com.shs.hl.ui.utils.Constants;
import com.shs.hl.ui.utils.LoggerUtil;

public class HLTTestGenerator extends AbstractHLGenerator
{
	private static final String GENERATED_TEST_FOLDER = "tests";
	@Inject
	protected IHLGenerator hlGenerator;
	protected final IPreferenceStore store = HearingLanguageActivator.getInstance().getPreferenceStore();
	int proceed = -1;

	public void generateTest(ISelection selection)
	{
		startUpGeneration(selection);
	}

	/**
	 * 
	 * @param currentPlfInfo
	 * @param res
	 * @param resource
	 */
	private void DoGenerate(PlatformInformation currentPlfInfo, Resource res, IResource resource)
	{
		HLTGenerateTestWalker engine = new HLTGenerateTestWalker();
		engine.generate(res, fsa, resource.getParent().getName());
	}

	public HLTTestGenerator(final String consoleName)
	{
		super(consoleName);
	}

	private void configureFSA(ISelection selection, IProgressMonitor monitor)
	{
		if (selection instanceof IStructuredSelection)
		{
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Object firstElement = structuredSelection.getFirstElement();
			IProject project = null;
			IFolder srcGenFolder;

			if (firstElement instanceof IFile)
			{
				IFile file = (IFile) firstElement;
				project = file.getProject();

			} else if (firstElement instanceof Openable)
			{
				project = ((Openable) firstElement).getResource().getProject();
			} else if (firstElement instanceof IContainer)
			{
				project = ((IContainer) firstElement).getProject();
			}

			srcGenFolder = project.getFolder(GENERATED_TEST_FOLDER);
			if (!srcGenFolder.exists())
			{
				try
				{
					srcGenFolder.create(true, true, new NullProgressMonitor());
				} catch (CoreException e)
				{
					e.printStackTrace();
				}
			}

			// c# FSA
			fsa.setMonitor(monitor);
			fsa.setProject(project);
			fsa.setOutputPath(srcGenFolder.getName());

		}
	}

	/**
	 * runs the actual code generation
	 */
	@Override
	public void runGeneration(final IProgressMonitor monitor, final ISelection selection)
	{
		// builder.append(MAP_HEADER);
		int counter = 0;
		final Map<IResource, Set<IResource>> map = getResourcesMap(selection);
		final Set<IResource> rootElememts = map.keySet();

		for (final Set<IResource> x : map.values())
		{
			counter += x.size();
		}

		// set project , monitor and output in FSA
		configureFSA(selection, monitor);

		monitor.beginTask("Generating code...", counter);
		// now iterate over all the resources we had selected
		// and get all their members

		for (final IResource element : rootElememts)
		{
			// this one actually invokes the generator itself
			doGenCode(monitor, map.get(element));
		}

		// create map file
		// mapFileAccess.generateFile("hlcsharp.map", builder);
		monitor.done();
	}

	private PlatformInformation GetPlatformInfoForProject(IProject prj)
	{
		StdlibInfo info = BundleHelper.getStdlibVersionInfoFromManifest(prj);
		return GenerationInfo.PlatformInformation.GetBestMatch(BundleHelper.getStdlibVersion(info.StdlibName), info.StdlibVersion);
	}

	// helper method to load resources (if necessary) and use corresponding
	// generator
	private void doGenCode(final IProgressMonitor monitor, Set<IResource> resources)
	{

		final Hashtable<IProject, ResourceSet> usedProjects = new Hashtable<IProject, ResourceSet>();
		// final Hashtable<IProject,com.shs.hl.ui.utils.BundleHelper.StdlibInfo>
		// stdlibLookup = new
		// Hashtable<IProject,com.shs.hl.ui.utils.BundleHelper.StdlibInfo>();
		final Hashtable<IProject, PlatformInformation> plfLookup = new Hashtable<IProject, PlatformInformation>();

		// String status = "done";
		for (final IResource resource : resources)
		{
			final URI fileURI = URI.createPlatformResourceURI(resource.getFullPath().toString(), false);
			IProject prj = resource.getProject();

			PlatformInformation currentPlfInfo;

			if ((currentPlfInfo = plfLookup.get(prj)) == null)
			{
				monitor.subTask("Analyzing platform information");
				// StdlibInfo info =
				// BundleHelper.getStdlibVersionInfoFromManifest(prj);
				// currentPlfInfo =
				// GenerationInfo.PlatformInformation.GetBestMatch(BundleHelper.getStdlibVersion(info.StdlibName),
				// info.StdlibVersion);
				currentPlfInfo = GetPlatformInfoForProject(prj);
			}

			// we do a project based resource loading
			// it is save to load all ressources according to the first element
			// to be processed

			// If we do not a project based resource loading we get an awful
			// performance issue (it takes several minutes to
			// generate) For example if 250 model elements are to be processed
			// we get for every of these 250 files 249
			// load requests (250*249) in sum about (~62250) plus 250*(count of
			// stdlib files)
			try
			{
				// resource sharing is save as long we do a sequential
				// processing (on write)
				//
				ResourceSet resSet = null;
				boolean initial = false;
				if (!usedProjects.containsKey(prj))
				{
					monitor.subTask("Retrieving resources");
					resSet = resourceSetProvider.get(prj);
					usedProjects.put(prj, resSet);
					// do project related resource loading
					initial = true;
				} else
				{
					resSet = usedProjects.get(prj);
				}

				Resource res = resSet.getResource(fileURI, true);
				res.load(Collections.EMPTY_MAP);

				if (initial)
				{
					monitor.subTask("Loading resources - " + prj.getName());
					// LoggerUtil.log(IStatus.INFO,"## RESOURCE-LOAD ### "+prj.getName()+
					// " ##########################################");
					initializeResourceSet(res);
				}

				final String message = "Generating [" + currentPlfInfo.toString() + " platform] code for resource " + resource.getName();
				messageConsoleStream.print(message + " ...");
				monitor.subTask(message);

				DoGenerate(currentPlfInfo, res, resource);
				messageConsoleStream.println("done");

			} catch (final IOException e)
			{
				LoggerUtil.log(IStatus.ERROR, "Problem during resource gathering and code generation  --> " + e.getMessage());
			}
		}
	}

	String cleanString(final String str)
	{
		if (str == null)
			return "";
		else
			return str;
	}

	protected void runExternal(final ProcessBuilder builder)
	{
		Process p;
		try
		{
			p = builder.start();
			final InputStream is = p.getInputStream();
			final InputStreamReader isr = new InputStreamReader(is);
			final BufferedReader br = new BufferedReader(isr);
			String line;

			LoggerUtil.log(IStatus.INFO, "Output of running: " + builder.command().toString() + " is:");
			// console output for Process
			while ((line = br.readLine()) != null)
			{
				LoggerUtil.log(IStatus.INFO, line);
				printLine(line);
			}
		} catch (final IOException e)
		{
			errorConsoleStream.println("Error running external command : " + builder.toString());
			e.printStackTrace();
		}

	}

	protected Boolean testEmpty(final String key, final String value)
	{
		if (value.trim().isEmpty())
		{
			errorConsoleStream.println("Configuration for " + key + " is not set, use the preferences page to set value");
			return Boolean.TRUE;
		}
		return Boolean.FALSE;

	}

	private void printLine(final String line)
	{
		final int status = getStatus(line);
		switch (status)
		{
			case IStatus.ERROR:
				errorConsoleStream.println(line);
				LoggerUtil.log(status, line);
				break;
			default:
				messageConsoleStream.println(line);
		}
	}

	private int getStatus(final String string)
	{
		if (string.toLowerCase().contains("failed") || string.contains("--->"))
			return Status.ERROR;
		return Status.INFO;
	}

}
