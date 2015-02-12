package com.shs.hl.ui.actions.handler;

import java.io.File;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import com.shs.hl.ui.internal.HearingLanguageActivator;
import com.shs.hl.ui.utils.Constants;

public class CleanOutputDirHandler extends AbstractHandler
{

	private int proceed = 0;
	protected final IPreferenceStore store = HearingLanguageActivator.getInstance().getPreferenceStore();

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException
	{
		ISelection selectedElement = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
		if (selectedElement != null & selectedElement instanceof IStructuredSelection)
		{

			IProject project = null;
			Object selected = ((IStructuredSelection) selectedElement).toList().get(0);
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
			
			if (project != null)
			{
				String srcGen = store.getString(Constants.GENERATION_OUTPUT_FOLDER);
				if (containsFiles(project, srcGen))
				{
					showWarning();
					if (proceed == Window.OK)
					{
						cleanProject(project, srcGen);
						try
						{
							project.refreshLocal(IResource.DEPTH_INFINITE, null);
						}
						catch (CoreException e)
						{
							e.printStackTrace();
						}
					}
				}

			}
		}
		return null;
	}

	private void showWarning()
	{
		Display.getDefault().syncExec(new Runnable()
		{
			@Override
			public void run()
			{
				String[] labels = new String[] { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL };
				MessageDialog dialog = new MessageDialog(Display.getDefault().getActiveShell(), "Clean output directory", null,
						"The output directory isn't empty. Are you sure to delete the files in the 'src-gen' folder?", MessageDialog.QUESTION,
						labels, SWT.NONE)
				{
					@Override
					public org.eclipse.swt.graphics.Image getQuestionImage()
					{
						return getWarningImage();
					};
				};
				proceed = dialog.open();
			}
		});

	}

	/**
	 * clean dir
	 * 
	 * @param project
	 * @param root
	 */
	private void cleanProject(IProject project, String root)
	{
		IFile file = project.getFile(root);
		String path = file.getLocation().toString();
		try
		{
			for (File child : new File(path).listFiles())
			{
				deleteFile(child);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	private boolean containsFiles(IProject project, String rootPath)
	{
		IFile file = project.getFile(rootPath);
		File lookupPath = file.getRawLocation().makeAbsolute().toFile();
		if (lookupPath.exists())
		{
			File[] c = lookupPath.listFiles();
			if (c != null && c.length > 0) return true;
		}
		return false;
	}

	public void deleteFile(File path)
	{
		if (path.isDirectory())
		{
			File[] c = path.listFiles();
			for (File file : c)
			{
				if (file.isDirectory())
				{
					deleteFile(file);
					file.delete();
				}
				else
				{
					file.delete();
				}
			}
		}
		path.delete();
	}

}
