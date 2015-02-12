package com.shs.hl.ui.actions.handler;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.pde.core.project.IBundleProjectDescription;
import org.eclipse.pde.core.project.IBundleProjectService;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.ui.handlers.HandlerUtil;
import org.osgi.framework.BundleException;

@SuppressWarnings("restriction")
public class ChangeVersionBundleHandler extends AbstractHandler
{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException
	{
		ISelection selectedProject = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
		if (selectedProject != null & selectedProject instanceof IStructuredSelection)
		{

			IProject project = null;
			Object selected = ((IStructuredSelection) selectedProject).toList().get(0);
			if (selected instanceof IJavaElement)
			{
				project = ((IJavaElement) selected).getJavaProject().getProject();

			}
			else if (selected instanceof IResource)
			{
				project = ((IResource) selected).getProject();
			}
			else
			{
				project = (IProject) selected;
			}

			try
			{
				new ChangeVersion().changeVersion(project, "Changing STDLIB for Project : " + project.getName());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (BundleException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	public <K, V> Map<K, V> addDictionaryToMap(Dictionary<K, V> source, Map<K, V> sink)
	{
		for (Enumeration<K> keys = source.keys(); keys.hasMoreElements();)
		{
			K key = keys.nextElement();
			sink.put(key, source.get(key));
		}
		return sink;
	}

	/**
	 * 
	 * @param projectName
	 * @return {@link IBundleProjectDescription}
	 * @throws CoreException
	 *             coreexception
	 */
	protected IBundleProjectDescription getProjectDeascription(String projectName) throws CoreException
	{
		IProject proj = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		IBundleProjectDescription description = getBundleProjectService().getDescription(proj);
		description.setSymbolicName(proj.getName());
		return description;
	}

	public static IBundleProjectService getBundleProjectService()
	{
		return (IBundleProjectService) PDECore.getDefault().acquireService(IBundleProjectService.class.getName());
	}

}
