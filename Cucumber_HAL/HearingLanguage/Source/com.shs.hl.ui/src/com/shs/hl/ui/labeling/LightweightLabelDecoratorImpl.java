package com.shs.hl.ui.labeling;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Level;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

import com.shs.hl.ui.actions.handler.ChangeVersion;
import com.shs.hl.ui.utils.BundleHelper;
import com.shs.hl.ui.utils.BundleHelper.StdlibInfo;
import com.shs.hl.ui.utils.LoggerUtil;

//@SuppressWarnings("restriction")
public class LightweightLabelDecoratorImpl implements ILightweightLabelDecorator
{

	@Override
	public void addListener(ILabelProviderListener listener)
	{

	}

	@Override
	public void dispose()
	{

	}

	@Override
	public boolean isLabelProperty(Object element, String property)
	{
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener)
	{

	}

	@Override
	public void decorate(Object element, IDecoration decoration)
	{
		if (element instanceof IProject)
		{

			try
			{
				((IProject) element).refreshLocal(IResource.DEPTH_INFINITE, null);
				
				
				StdlibInfo libInfo = BundleHelper.getStdlibVersionInfoFromManifest((IProject) element);
				
				String bundleName = libInfo.StdlibName;

				boolean isValid = false;
				// current installed stdlibs
				List<Bundle> currentSTlibs = BundleHelper.getAvailableBundles(com.shs.hl.ui.utils.Constants.STDLIBNAME);
				for (Bundle bundle : currentSTlibs)
				{
					if (bundle.getSymbolicName().equals(bundleName))
					{
						isValid = true;
						break;
					}
				}
				if (isValid)
				{
					decoration.addSuffix("[" + BundleHelper.getStdlibVersion(bundleName) + " Project]");
				}
				else
				{
					final String warning = "Project " + ((IProject) element).getName() + " has invalid STLIB version";
					Display.getDefault().syncExec(new Runnable() {
						@Override
						public void run()
						{
							MessageDialog.openError(new Shell(), warning, warning);
						}
					});
					new ChangeVersion().changeVersion((IProject) element, "Project " + ((IProject) element).getName() + " ");
				}
			}
			catch (IOException e)
			{
				LoggerUtil.log(Level.ERROR.toInt(), e.getMessage());
			}
			catch (BundleException e)
			{
				LoggerUtil.log(Level.ERROR.toInt(), e.getMessage());
			}
			catch (CoreException e)
			{
				LoggerUtil.log(Level.ERROR.toInt(), e.getMessage());
			}
		}
	}
}
