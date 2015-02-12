package com.shs.hl.ui.generator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.internal.core.JarPackageFragmentRoot;
import org.eclipse.jdt.internal.core.Openable;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.resource.XtextResource;

import com.shs.hl.hearingLanguage.FunctionDeclaration;
import com.shs.hl.hearingLanguage.SymbolReference;
import com.shs.hl.ui.internal.HearingLanguageActivator;
import com.shs.hl.ui.utils.LoggerUtil;

@SuppressWarnings("restriction")
public class XMIGenerator extends AbstractHLGenerator {

	private final String		XMIOUTPUTDIR	= "/xmioutput/";
	IPreferenceStore			store			= HearingLanguageActivator.getInstance().getPreferenceStore();
	static Map<String, Object>	loadOptions		= new HashMap<String, Object>();

	static {
		loadOptions.put(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
	}

	public XMIGenerator(final String messageConsoleName) {
		super(messageConsoleName);
	}

	public void generate(final IProgressMonitor monitor, final ISelection selection) {
		Map<IResource, Set<IResource>> map = null;
		String outputfile = "";
		final Set<Resource> model = new HashSet<Resource>();
		// for
		final IStructuredSelection str = (IStructuredSelection) selection;
		final Object bSelected = str.getFirstElement();
		if (bSelected != null && bSelected instanceof JarPackageFragmentRoot) {
			model.addAll(getJarLibResources("com.shs.hl.stdlib"));
			outputfile = "stdlib.xmi";
		} else {
			map = getResourcesMap(selection);
			for (final Set<IResource> x : map.values()) {
				model.addAll(getResources(x, monitor));
			}
			outputfile = "complete.xmi";
		}
		monitor.beginTask("Generating XMI files..", model.size());

		writeFile(model, monitor, outputfile, selection);
		messageConsoleStream.println("done");
		monitor.done();
	}

	/**
	 * get Models from the Lib-Dir
	 * 
	 * @param bundleID
	 * @return
	 */
	protected Set<Resource> getJarLibResources(final String bundleID) {
		final Set<Resource> result = new HashSet<Resource>();
		final String[] paths = { "params", "stdlib" };
		for (final String path : paths)
			try {
				final URL fileURL = new URL("platform:/plugin/" + bundleID + "/" + path);
				final URL fileURl = FileLocator.toFileURL(fileURL);
				final File fl = new File(fileURl.getFile());
				final File[] files = fl.listFiles();
				for (final File hlFile : files) {
					if (!hlFile.isDirectory() && hlFile.getName().contains(".cl")) {
						//##########################
						//##########################
//						final Resource re = rs.getResource(URI.createURI("platform:/plugin/" + bundleID + "/" + path + "/" + hlFile.getName()), true);
//						re.load(loadOptions);
//						result.add(re);
						//##########################
						//##########################
					}
				}
			} catch (final IOException e1) {
				e1.printStackTrace();
			}

		return result;
	}

	private String getCurrentProject(final ISelection selection) {
		final StringBuilder name = new StringBuilder();
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				IProject project = null;
				if (selection instanceof IStructuredSelection) {
					final Object element = ((IStructuredSelection) selection).getFirstElement();
					if (element instanceof IResource) {
						project = ((IResource) element).getProject();
					} else if (element instanceof Openable) {
						final IJavaProject jProject = ((Openable) element).getJavaProject();
						project = jProject.getProject();
					} else if (selection instanceof ITextSelection) {
						// TODO
					}

					if (project != null)
						name.append(project.getName());
				}

			}
		});
		return name.toString();
	}

	private void writeFile(final Set<Resource> resources, final IProgressMonitor monitor, final String fileName, final ISelection selection) {

		final String path = getCurrentProject(selection) + XMIOUTPUTDIR + "/" + fileName;
		final URI xmiOutput = URI.createPlatformResourceURI(path, false);
		//##########################
		//##########################
		
		final Resource xmiRes = null;//rs.createResource(xmiOutput);
		//##########################
		//##########################
		final Set<EObject> elements = new HashSet<EObject>();
		for (final Resource res : resources) {
			if (!res.getContents().isEmpty()) {
				final EObject element = res.getContents().get(0);
				elements.addAll(EcoreUtil2.eAllContentsAsList(element));
			}
		}
		for (final EObject ele : elements) {
			EcoreUtil2.resolveAll(ele);
		}

		for (final Resource res : resources) {
			monitor.subTask("Generating XMI for resource: " + res.getURI().lastSegment());
			messageConsoleStream.println("Generating XMI for resource " + res.getURI().lastSegment() + "...");
			// could filter function elements
			if (!res.getContents().isEmpty()) {
				final EObject element = res.getContents().get(0);
				EcoreUtil2.resolveAll(element);
				resolveall(element);
				final List<FunctionDeclaration> pp = EcoreUtil2.getAllContentsOfType(element, FunctionDeclaration.class);
				for (final FunctionDeclaration funct : pp) {
					EcoreUtil2.resolveAll(funct);
					final List<SymbolReference> bm = EcoreUtil2.getAllContentsOfType(funct, SymbolReference.class);
					for (final SymbolReference ref : bm) {
						EcoreUtil2.resolveAll(ref);
						resolveall(ref);
						if ((ref.getSymbol() instanceof FunctionDeclaration) && ref.getSymbol().getName() != null) {
							EcoreUtil2.resolveAll(ref.getSymbol());
						}
					}

				}

			}

		}

		for (final Resource res : resources) {
			monitor.subTask("Generating XMI for resource: " + res.getURI().lastSegment());
			messageConsoleStream.println("Generating XMI for resource " + res.getURI().lastSegment() + "...");
			// could filter function elements
			if (!res.getContents().isEmpty()) {
				final EObject element = res.getContents().get(0);
				xmiRes.getContents().add(element);
			}
			monitor.worked(1);
		}

		try {
			xmiRes.save(Collections.EMPTY_MAP);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private void resolveall(final EObject obj) {
		final Resource res = obj.eResource();
		try {
			res.load(loadOptions);
			EcoreUtil2.resolveAll(res);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private Set<Resource> getResources(final Set<IResource> resources, final IProgressMonitor monitor) {
		final Set<Resource> result = new HashSet<Resource>();
		if (!resources.isEmpty()) {

			try {
				for (final IResource resource : resources) {
					final URI fileURI = URI.createPlatformResourceURI(resource.getFullPath().toString(), false);
					
					
					//##########################
					final Resource res = null;//rs.getResource(fileURI, true);
					//##########################
					//##########################
					final Map<String, Object> m = new HashMap<String, Object>();
					m.put(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
					result.add(res);
					res.load(m);
				}

			} catch (final IOException e) {
				LoggerUtil.log(IStatus.ERROR, "This exception should never be thrown!!!", e);
			}
		}
		return result;
	}

	@Override
	public void runGeneration(final IProgressMonitor monitor, final ISelection selection) {
		generate(monitor, selection);
	}

}
