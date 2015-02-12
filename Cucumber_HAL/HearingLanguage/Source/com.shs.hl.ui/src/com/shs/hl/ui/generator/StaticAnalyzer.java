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
import com.shs.hl.generator.halsa.evaluation.EvaluationContext;
import com.shs.hl.generator.halsa.evaluation.EvaluationEngine;
import com.shs.hl.ui.internal.HearingLanguageActivator;
import com.shs.hl.ui.utils.BundleHelper;
import com.shs.hl.ui.utils.BundleHelper.StdlibInfo;
import com.shs.hl.ui.utils.Constants;
import com.shs.hl.ui.utils.LoggerUtil;

public class StaticAnalyzer extends AbstractHLGenerator
{
	@Inject
	protected IHLGenerator hlGenerator;
	protected final IPreferenceStore store = HearingLanguageActivator.getInstance().getPreferenceStore();
	int proceed = -1;

	public void startUpAnalysis(ISelection selection)
	{
		startUpGeneration(selection);
	}

	/**
	 * 
	 * @param currentPlfInfo
	 * @param res
	 */
	private void DoGenerate(PlatformInformation currentPlfInfo, Resource res)
	{
		//new EvaluationEngine(res, fsa, new EvaluationContext()).walk(res, fsa);
	}

	public StaticAnalyzer(final String consoleName)
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

			srcGenFolder = project.getFolder(store.getDefaultString(Constants.GENERATION_OUTPUT_FOLDER));
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

				DoGenerate(currentPlfInfo, res);
				messageConsoleStream.println("done");

			} catch (final IOException e)
			{
				LoggerUtil.log(IStatus.ERROR, "Problem during resource gathering and code generation  --> " + e.getMessage());
			}
		}
	}

	public void startUpAssemblyGeneration(final IStructuredSelection structSel)
	{
		final String nameSpace = store.getString(Constants.ASSEMBLY_NAMESPACE);
		final String assemblyNameD8 = store.getString(Constants.PLATFORM_MACROS_D8);
		final String assemblyNameD9 = store.getString(Constants.PLATFORM_MACROS_D9);
		final String vendorOutput = store.getString(Constants.VENDOR_OUT_PUT);
		final String referenceDirectory = store.getString(Constants.REFERENCE_DIR);
		final String genDir = store.getString(Constants.GENERATION_OUTPUT_FOLDER);
		// GENERATION_OUTPUT_FOLDER
		final String cmd = store.getString(Constants.BUILD_ASSEMBLY_BATCH);
		final String cmdPath = "." + File.separator + store.getString(Constants.ASSEMBLY_GENERATOR_BASEDIR) + File.separator;

		final ImmutableMap<String, String> map = new ImmutableMap.Builder<String, String>().put(Constants.BUILD_ASSEMBLY_BATCH, cmd)
				.put(Constants.BUILD_ASSEMBLY_COMMAND_PATH, cmdPath).put(Constants.REFERENCE_DIR, referenceDirectory)
				.put(Constants.FULLY_QUALIFIED_NAME_D8, nameSpace + "." + assemblyNameD8)
				.put(Constants.FULLY_QUALIFIED_NAME_D9, nameSpace + "." + assemblyNameD9).put(Constants.FULLY_QUALIFIED_NAME, nameSpace + ".")
				.put(Constants.PLATFORM_MACROS_D8, assemblyNameD8).put(Constants.PLATFORM_MACROS_D9, assemblyNameD9)
				.put(Constants.VENDOR_OUT_PUT, vendorOutput).put(Constants.GENERATION_OUTPUT_FOLDER, genDir).build();

		// super.initPlatformVersion(structSel); // works only if one project is
		// selected
		startUpAssemblyGenerationInternal(structSel, map);
	}

	// Hook up for testing purpose
	public void startUpUnconfiguratedGeneration(final IStructuredSelection structSel, final Map<String, String> configs)
	{
		// if necessary add hooks here ...
		startUpAssemblyGenerationInternal(structSel, configs);
	}

	private void startUpAssemblyGenerationInternal(final IStructuredSelection structSel, final Map<String, String> configs)
	{

		messageConsoleStream.println("Generate file artifacts");

		// the Set is very important to avoid double IProject entries for
		// multi-selected files in one Project
		final Set<IProject> projects = new HashSet<IProject>();

		// in case of Multi-Selections, find all the projectfiles
		for (final Object selectedObject : structSel.toList())
		{
			if (selectedObject != null)
			{
				// find out the resource we want to generate from
				IResource resource = null;
				if (selectedObject instanceof IResource)
				{
					resource = (IResource) selectedObject;
				} else if (selectedObject instanceof IJavaElement)
				{
					resource = ((IJavaElement) selectedObject).getResource();
				}
				// add the IProject for Resource to the set
				if (resource != null)
				{
					projects.add(resource.getProject());
				}
			}
		}

		for (final IProject project : projects)
		{
			String dllName = project.getName();
			dllName = dllName.substring(dllName.lastIndexOf(".") + 1);

			GenerationInfo.PlatformInformation plfInfo = GetPlatformInfoForProject(project);

			PlattformGenerationInformation info = new PlattformGenerationInformation(plfInfo);

			info.setProjectPath(project.getLocation().toString());

			info.setProjectName(dllName);
			final String accessKey = Constants.FULLY_QUALIFIED_NAME + Constants.PLATFORM_DELIMITER + plfInfo.toString();
			final String macroName = configs.get(accessKey);

			if (StringUtils.isNotBlank(macroName))
			{
				info.setDllName(macroName);
			}

			try
			{
				this.generateAssmblyFromSources(info, configs);
			} catch (Exception e) // if we switch to Java 7 reduce stupid boiler
									// code here
			{
				e.printStackTrace();
			}
		}

	}

	private class PlattformGenerationInformation
	{
		PlatformInformation _platformInfo;
		private String _outputFolder;
		private String _dllName;
		private String _projectName;
		private String _projectPath;

		public PlatformInformation getPlatformInfo()
		{
			return _platformInfo;
		}

		public void setProjectName(String name)
		{
			_projectName = name;
		}

		public String getProjectName()
		{
			return _projectName;
		}

		public String getDllName()
		{
			return _dllName;
		}

		public void setDllName(String name)
		{
			_dllName = name;
		}

		public void setProjectPath(String path)
		{
			_projectPath = path;
		}

		public String getProjectPath()
		{
			return _projectPath;
		}

		public String getPlatformString()
		{
			return _platformInfo.PlfName();
		}

		public String getGenerationPath()
		{
			return getProjectPath() + "\\" + getOutputExt();
		}

		public PlattformGenerationInformation(PlatformInformation plf)
		{

			_platformInfo = plf;
			_outputFolder = store.getString(Constants.GENERATION_OUTPUT_FOLDER);
		}

		private String getOutputExt()
		{
			if (_outputFolder == null || _outputFolder == "")
			{
				_outputFolder = store.getString(Constants.GENERATION_OUTPUT_FOLDER);
			}
			return _outputFolder;
		}
	}

	// internal helper method doing the real work
	// private void generateAssmblyFromSources(final String projectPath, final
	// String dllName, String platform, final Map<String, String> configs)
	private void generateAssmblyFromSources(PlattformGenerationInformation info, final Map<String, String> configs)
	{
		boolean errorOccured = false;

		boolean usePrjectName = store.getBoolean(Constants.USE_PROJECT_NAME_AS_DLL_SELECTION);

		// final String generationPath = projectPath + "\\src-gen";
		final String referenceDirectory = cleanString(configs.get(Constants.REFERENCE_DIR)); // FITTING
																								// DLL
		final String generatedModel = info.getGenerationPath();// cleanString(configs.get(Constants.GENERATION_OUTPUT_FOLDER));
																// //
																// MODEL-DIRECTORY
		final String vendorOutput = cleanString(configs.get(Constants.VENDOR_OUT_PUT)); // Where
																						// to
																						// copy
																						// to

		// determine platform specific settings

		String fullyQualifiedName;
		if (usePrjectName)
		{
			fullyQualifiedName = cleanString(configs.get(Constants.FULLY_QUALIFIED_NAME));
			fullyQualifiedName += info.getProjectName();

		} else
		{
			fullyQualifiedName = cleanString(info.getDllName());
		}

		LoggerUtil.log(IStatus.INFO, "Start generating dll from source files");

		final File assemblyDir = new File(configs.get(Constants.BUILD_ASSEMBLY_COMMAND_PATH));
		final File currentDir = new File(".");

		if (!assemblyDir.exists())
		{
			String pathToCheck = currentDir.getAbsolutePath().toString();
			LoggerUtil.log(IStatus.ERROR, "Generating dll from source files failed - cannot find any file generator - Path does not exist");
			LoggerUtil.log(IStatus.WARNING, "Please check " + pathToCheck);
			errorConsoleStream.println("Generating dll from source files failed: cannot find specified command path");
			messageConsoleStream.println("   Please check working directory " + pathToCheck);
			return;
		}

		try
		{
			ProcessBuilder pb = null;
			final File batch = new File(assemblyDir.getCanonicalPath() + File.separator + cleanString(configs.get(Constants.BUILD_ASSEMBLY_BATCH)));
			if (!assemblyDir.exists())
			{
				LoggerUtil.log(IStatus.ERROR, "Generating dll from source files failed - cannot find specified file generator - File does not exist");
				errorConsoleStream.println("Generating dll from source files failed: cannot find specified generator command" + assemblyDir.toString());
				return;
			}

			// REM /P:MacroLibrary =%1 : The name of the resulting assembly
			// without ".dll" #1
			// REM /P:GenerationPath =%2 : Path to the generated cs-files
			// including the src-gen folder #2
			// REM /P:Configuration =%3 : The project configuration to be built
			// (Debug/Release) ---> Always set to DEBUG (not needed to be
			// passed)
			// REM /P:PlatformVersion =%4 : Platform version to compile against
			// (D8/D9) #3 ---> NEW
			// REM /P:TestConfiguration : Indicating if output is planned to be
			// used as TestConfig #4 ---> Always set to NoTest (elsewise Test)
			// REM /P:ReferenceDirectory =%5 : Path to the Fitting dlls #5
			// REM /P:OutputBasePath =%6 : The Path to where the dll will be
			// copied #6

			boolean testing = false;
			// String alternativeOutput = "";

			// HearingLanguageRuntime.ProjectTemplate.csproj
			pb = new ProcessBuilder(batch.getCanonicalFile().toString(), // command
					fullyQualifiedName, // # 1 DLL-Name
					generatedModel, // # 2 c# source
					info.getPlatformString(), // # 3 Platform to compile against
					testing == true ? "Test" : "NoTest", // # 4 Testing
					referenceDirectory, // # 5 needed Fitting dlls
					vendorOutput); // # 6 Vendor Directory
			pb.directory(assemblyDir.getCanonicalFile());

			messageConsoleStream.print(pb.toString());
			runExternal(pb);
		} catch (final IOException e)
		{
			errorOccured = true;
			LoggerUtil.log(IStatus.ERROR, "Generating dll from source files failed");
			errorConsoleStream.println("Generating dll from source files failed: " + e.getMessage());
			e.printStackTrace();
		}

		if (!errorOccured)
		{
			LoggerUtil.log(IStatus.INFO, "Generating dll from source files done");
			messageConsoleStream.println("Generating dll from source files done");
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
