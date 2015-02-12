package com.shs.hl.ui.generator;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.EcoreUtil2;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.shs.hl.hearingLanguage.FunctionDeclaration;
import com.shs.hl.hearingLanguage.Namespace;
import com.shs.hl.hearingLanguage.SymbolReference;
import com.shs.hl.ui.internal.HearingLanguageActivator;
import com.shs.hl.ui.utils.LoggerUtil;

public class CodeGraphGenerator extends AbstractHLGenerator {

	private final String	OUTPUT				= "plattform:/graphoutput/";
	private final String	graphVizFile		= "graph.viz";
	private final String	outputGraph			= "call.png";
	private boolean			isFilenames			= false;
	IPreferenceStore		store				= HearingLanguageActivator.getInstance().getPreferenceStore();
	private boolean			isLayoutCircular	= false;

	public CodeGraphGenerator(final String messageConsoleName) {
		super(messageConsoleName);

	}

	@Override
	public void runGeneration(final IProgressMonitor monitor, final ISelection selection) {
		isFilenames = store.getString("CallGraphParent").equals("filenames");
		isLayoutCircular = store.getString("layout").equals("circular");
		generate(monitor, selection);
	}

	String getName(final String fullyQualified) {
		return fullyQualified.substring(fullyQualified.lastIndexOf("___") + 3, fullyQualified.length());
	}

	private void createGraphCallInputFile(final Multimap<String, FunctionNode> functionNodeMap, final File file) {
		// candidate for code generation thru xtext framework
		final StringBuilder builder = new StringBuilder();
		final Map<String, GraphvizSubgraph> subs = new HashMap<String, GraphvizSubgraph>();

		builder.append("digraph G { \n");
		if (isFilenames) {
			// create clusters for files with corresponding calls
			for (final String key : functionNodeMap.keys()) {
				subs.put(key, new GraphvizSubgraph(key));
			}
			for (final String key : functionNodeMap.keys()) {
				final GraphvizSubgraph gr = subs.get(key);
				final Collection<FunctionNode> functionNodes = functionNodeMap.get(key);
				for (final FunctionNode cll : functionNodes) {
					gr.getEdges().add(getName(cll.getName()));
					for (final FunctionNode call : cll.getCalls()) {
						if (call.fileName.equalsIgnoreCase(key))
							gr.getEdges().add(getName(call.getName()));
						else {
							if (subs.get(call.fileName) == null) {
								final GraphvizSubgraph temp = new GraphvizSubgraph(call.fileName);
								temp.getEdges().add(getName(call.getName()));
								subs.put(call.fileName, temp);
							} else {
								subs.get(call.fileName).getEdges().add(getName(call.getName()));
							}
						}
					}
				}

			}
			// create connections to other functions <function>-><function>
			final Set<String> connections = new HashSet<String>();
			for (final String key : functionNodeMap.keys()) {
				for (final FunctionNode cll : functionNodeMap.get(key)) {
					for (final FunctionNode call : cll.getCalls())
						connections.add(getName(cll.getName()) + "->" + getName(call.name) + ";\n");
				}
			}
			for (final GraphvizSubgraph sub : subs.values()) {
				builder.append(sub.toString() + "\n");
			}
			for (final String str : connections) {
				builder.append(str);
			}

		} else {
			for (final FunctionNode cll : functionNodeMap.values()) {
				for (final FunctionNode call : cll.getCalls())
					builder.append(cll.getName() + "->" + call.name + ";\n");
			}
		}
		builder.append("}");
		try {
			file.setWritable(true);
			final Writer writer = new BufferedWriter(new FileWriter(file));
			writer.write(builder.toString());
			writer.flush();
			writer.close();
		} catch (final IOException e) {
			LoggerUtil.log(IStatus.ERROR, "Failed to write Call graph File", e);
			errorConsoleStream.println("An exception occured while writing Call graph file " + e.getMessage());
		}
	}

	public void generate(final IProgressMonitor monitor, final ISelection selection) {
		final Map<IResource, Set<IResource>> map = getResourcesMap(selection);
		monitor.beginTask("Generating Call-Graph..", map.values().size());
		final Set<IResource> resources = new HashSet<IResource>();
		for (final Set<IResource> x : map.values()) {
			resources.addAll(x);
		}
		final ArrayListMultimap<String, FunctionNode> functions = ArrayListMultimap.create();
		if (!resources.isEmpty()) {
			final IResource resourceForPath = (IResource) resources.toArray()[0];
			try {
				final File file = getOutPutFile(resourceForPath.getProject());
				for (final IResource resource : resources) {
					final ResourceSet rs = resourceSetProvider.get(resource.getProject());
					final URI fileURI = URI.createPlatformResourceURI(resource.getFullPath().toString(), false);
					final Resource res = rs.getResource(fileURI, true);
					monitor.subTask("Generating Call graph for resource: " + resource.getName());
					messageConsoleStream.println("Generating Call graph for resource " + resource.getName() + "...");
					if (!res.getContents().isEmpty()) {
						final EObject element = res.getContents().get(0);
						EcoreUtil2.resolveAll(element);
						final List<FunctionDeclaration> pp = EcoreUtil2.getAllContentsOfType(element, FunctionDeclaration.class);
						for (final FunctionDeclaration funct : pp) {
							EcoreUtil2.resolveAll(funct);
							final Namespace namespace = EcoreUtil2.getContainerOfType(funct, Namespace.class);
							final FunctionNode f = new FunctionNode(namespace.getName() + "___" + funct.getName(), fileURI.lastSegment());
							final List<SymbolReference> bm = EcoreUtil2.getAllContentsOfType(funct, SymbolReference.class);
							for (final SymbolReference ref : bm) {
								// TODO why is Name null
								if ((ref.getSymbol() instanceof FunctionDeclaration) && ref.getSymbol().getName() != null) {
									final Namespace callSpace = EcoreUtil2.getContainerOfType(ref.getSymbol(), Namespace.class);
									final FunctionNode node = new FunctionNode(callSpace.getName() + "___" + ref.getSymbol().getName(), ref.getSymbol().eResource().getURI().lastSegment());
									f.getCalls().add(node);
								}
							}
							if (!f.getCalls().isEmpty()) // only put functions
															// with calls to
															// other functionss
							functions.put(fileURI.lastSegment(), f);
						}
					}
					monitor.worked(1);
				}
				createGraphCallInputFile(functions, file);
				createGraph(file);

				// refresh project to trigger Resourcechange
				PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
					@Override
					public void run() {
						try {
							resourceForPath.getProject().refreshLocal(IResource.DEPTH_INFINITE, monitor);
							final IFile ifile = resourceForPath.getProject().getFile(outputGraph);
							assert (ifile.isSynchronized(IResource.DEPTH_INFINITE));
						} catch (final CoreException e) {
							e.printStackTrace();
						}

					}
				});
				messageConsoleStream.println("done");
				monitor.done();
			} catch (final Exception e) {

			}
		}
	}

	private void createGraph(final File file) {
		final String graphvizInstallDir = store.getString("GraphvizPath");
		String command;
		if (isLayoutCircular)
			command = "circo.exe";
		else
			command = "dot.exe";
		final String runnerPath = "\"" + graphvizInstallDir + File.separator + "bin" + File.separator + command + "\" -Tpng " + file.getAbsolutePath() + " -o " + outputGraph;
		final File batch = new File(file.getParent() + "/gr.bat");
		LoggerUtil.log(Status.INFO, runnerPath);
		writeBatch(batch, runnerPath);
		try {
			ProcessBuilder pb = null;
			pb = new ProcessBuilder(batch.getCanonicalFile().toString());
			pb.directory(new File(file.getParent()));
			pb.start();

		} catch (final IOException e) {
			LoggerUtil.log(IStatus.ERROR, "Error while trying to write GraphViz file", e);
			errorConsoleStream.println("An exception occured while writing GraphViz file : " + e.getMessage());
		}
	}

	private void writeBatch(final File batch, final String runnerPath) {
		Writer writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(batch));
			writer.write(runnerPath);
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (final IOException e) {
				LoggerUtil.log(IStatus.ERROR, "Error while trying to write Batch file", e);
				errorConsoleStream.println("An exception occured while writing Batch file : " + e.getMessage());
			}
		}

	}

	private File getOutPutFile(final IProject iProject) {
		final IFolder folder = iProject.getFolder(OUTPUT);
		try {
			if (!folder.exists())
				folder.create(IResource.NONE, true, null);
			final IFile file1 = folder.getFile(graphVizFile);
			if (!file1.exists()) {
				final byte[] bytes = "dummy".getBytes();
				final InputStream source = new ByteArrayInputStream(bytes);
				file1.create(source, IResource.NONE, null);
			}
			return file1.getLocation().toFile();
		} catch (final CoreException e) {
			LoggerUtil.log(IStatus.ERROR, "Error occured trying to get the output File", e);
			errorConsoleStream.println("An exception occured getting the output file : " + e.getMessage());
		}
		return null;
	}

}
