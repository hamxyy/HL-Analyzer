package com.shs.hl.ui.actions.handler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourceAttributes;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.framework.internal.core.Constants;
import org.eclipse.osgi.util.ManifestElement;
import org.eclipse.pde.internal.core.project.PDEProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.wb.swt.SWTResourceManager;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

import com.shs.hl.ui.utils.BundleHelper;
import com.shs.hl.ui.utils.BundleHelper.StdlibInfo;
import com.shs.ui.dialog.PlatformViewer;
import com.shs.ui.dialog.VersionsViewer;

@SuppressWarnings("restriction")
public class ChangeVersion
{

	private VersionsViewer viewer;
	Bundle sourceBundle = null;
	private int proceed = -1;

	public Bundle getSourceBundle()
	{
		return sourceBundle;
	}

	public void setSourceBundle(Bundle sourceBundle)
	{
		this.sourceBundle = sourceBundle;
	}

	public VersionsViewer getViewer()
	{
		return viewer;
	}

	public void setViewer(VersionsViewer viewer)
	{
		this.viewer = viewer;
	}

	private void showVersions(final String title, final String currentStdLib)
	{
		Display.getDefault().syncExec(new Runnable()
		{
			@Override
			public void run()
			{
				Display display = Display.getDefault();
				final PlatformViewer viewer =
						new PlatformViewer(display, BundleHelper.getAvailableBundles(com.shs.hl.ui.utils.Constants.STDLIBNAME),	currentStdLib);
				viewer.open();
				viewer.layout();
				while (!viewer.isDisposed())
				{
					if (!display.readAndDispatch())
					{
						display.sleep();
					}
				}
				if (viewer.getReturnCode() == Window.OK)
				{
					sourceBundle = viewer.getSelectedBundle();
				}
				SWTResourceManager.dispose();
			}

		});

	}

	public String changeVersion(IProject project, String title) throws IOException, BundleException
	{
		String stdlibName = null;
		try
		{
			StdlibInfo info = BundleHelper.getStdlibVersionInfoFromManifest(project);
			String bundleName = info.StdlibName;
			showVersions(title, BundleHelper.getStdlibVersion(bundleName));

			if (sourceBundle != null)
			{

				if (checkVersionChange(bundleName, sourceBundle.getSymbolicName()))
				{
					StringBuilder builderv = new StringBuilder();
					StringBuilder requiredElements = new StringBuilder();
					String[] currentRequired = null;
					String sourceBundleName = sourceBundle.getSymbolicName();
					String sourceVersion = sourceBundle.getVersion().toString();
					IFile manifest = PDEProject.getManifest(project);
					// after check out file with readonly.
					ResourceAttributes attrib = new ResourceAttributes();
					attrib.setReadOnly(false);
					manifest.setResourceAttributes(attrib);

					Map<String, String> targetManifestMap = ManifestElement.parseBundleManifest(manifest.getContents(), null);
					for (Entry<String, String> entry : targetManifestMap.entrySet())
					{
						String key = entry.getKey();
						if (!key.equals(Constants.REQUIRE_BUNDLE))
						{
							builderv.append(entry.getKey() + ": " + entry.getValue() + "\n");
						}
						else
						{
							currentRequired = ManifestElement.getArrayFromList(entry.getValue());
						}

					}
					// add require bundle
					if (currentRequired != null)
					{
						for (int count = 0; count < currentRequired.length; count++)
						{
							if (!currentRequired[count].contains(com.shs.hl.ui.utils.Constants.STDLIBNAME))
							{
								requiredElements.append(currentRequired[count]);
							}
							if (count < currentRequired.length - 1 && !currentRequired[count].contains(com.shs.hl.ui.utils.Constants.STDLIBNAME))
								requiredElements.append(",");
						}
					}

					requiredElements.append("," + sourceBundleName + ";bundle-version=\"" + sourceVersion + "\"");
					builderv.append("Require-Bundle: " + requiredElements.toString() + "\n");

					manifest.getFullPath().toFile().delete();
					String fqnPath = manifest.getRawLocation().toString();
					File maniTemp = new File(fqnPath);
					FileWriter fileWriter = null;
					fileWriter = new FileWriter(maniTemp);
					fileWriter.write(builderv.toString().replaceAll(",,", ","));
					fileWriter.close();
					project.refreshLocal(IResource.DEPTH_INFINITE, null);
				}
			}
		}
		catch (CoreException e)
		{
			e.printStackTrace();
		}

		if (sourceBundle != null)
		{
			stdlibName = sourceBundle.getSymbolicName();
		}
		return stdlibName;
	}

	private boolean checkVersionChange(String oriBundleName, String newBundleName)
	{
		boolean change = true;
		if (oriBundleName.compareTo(newBundleName) > 0)
		{
			showWarning();
			if (proceed == IDialogConstants.CANCEL_ID)
			{
				change = false;
			}
		}
		return change;
	}

	private void showWarning()
	{
		Display.getDefault().syncExec(new Runnable()
		{

			@Override
			public void run()
			{
				String[] labels = new String[] { IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL };
				MessageDialog dialog = new MessageDialog(Display.getDefault().getActiveShell(), "Change from new to old platform", null,
						"A change from a new project to an old one is probably unintended "
								+ "and can cause side effects inclusively a uselessness of .hl code," + "do you really want to proceed ",
						MessageDialog.QUESTION, labels, SWT.NONE)
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
}
