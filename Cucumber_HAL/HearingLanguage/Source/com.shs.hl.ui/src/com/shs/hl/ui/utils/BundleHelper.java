package com.shs.hl.ui.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.osgi.framework.internal.core.Constants;
import org.eclipse.osgi.util.ManifestElement;
import org.eclipse.pde.internal.core.project.PDEProject;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.Version;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
//import com.shs.hl.ui.actions.handler.ChangeVersionBundleHandler;

@SuppressWarnings("restriction")
public class BundleHelper
{

	// ======================================================================
	// Manifest Handling
	// ======================================================================	
	public static class StdlibInfo
	{

		public static StdlibInfo GetNew()
		{
			return new StdlibInfo();
		}

		public String	StdlibName;
		public Version	StdlibVersion	= Version.emptyVersion;
	}


	public static StdlibInfo getStdlibVersionInfoFromManifest(IProject project)
	{
		return getVersionInfoFromManifest(project, com.shs.hl.ui.utils.Constants.STDLIBNAME);
	}

	private static StdlibInfo getVersionInfoFromManifest(IProject project, String bundleToSearch)
	{
		String result = null;
		// if (bundleToSearch==null) return null;
		try
		{
			result = getValueforManifestEntry(project, Constants.REQUIRE_BUNDLE, bundleToSearch);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (BundleException e)
		{
			e.printStackTrace();
		}
		catch (CoreException e)
		{

			e.printStackTrace();
		}

		if (result == null) return null;

		String[] parseResult = result.split(";");

		StdlibInfo libInfo = StdlibInfo.GetNew();
		libInfo.StdlibName = parseResult[0];
		if (parseResult.length > 1)
		{
			String versionString = parseResult[1];
			versionString = versionString.replace("bundle-version", "");
			versionString = versionString.replace("version", "");
			versionString = versionString.replace("=", "");
			versionString = versionString.replace("\"","");
			libInfo.StdlibVersion = new Version(versionString);
		}
		return libInfo;
	}

	private static String getValueforManifestEntry(IProject project, String key, String requiredBundleName) throws IOException, BundleException, CoreException
	{
		IFile manifest = PDEProject.getManifest(project);
		Map<String, String> targetManifestMap = ManifestElement.parseBundleManifest(manifest.getContents(), null);
		for (Entry<String, String> entry : targetManifestMap.entrySet())
		{
			String entryKey = entry.getKey();
			String value = entry.getValue();

			if (entryKey.equals(key))
			{
				for (String token : ManifestElement.getArrayFromList(value))
				{
					if (token.contains(requiredBundleName)) { return token; }
				}
			}
		}
		return "";
	}
	
	// ======================================================================
	// ---- General Bundle Handling
	// ======================================================================
	public static List<Bundle> getAvailableBundles(final String bundleName)
	{
		return filterBundles(getAvailableBundles(), bundleName);
	}
		
	private static List<Bundle> getAvailableBundles()
	{
		final List<Bundle> result = new ArrayList<Bundle>();

		Bundle owner = FrameworkUtil.getBundle(BundleHelper.class);
		if (owner != null)
		{
			org.osgi.framework.BundleContext context = owner.getBundleContext();
//			if (context != null)
//			{
//
//				String name = owner.getSymbolicName();
//				Version version = owner.getVersion();
//
////				System.out.println("BundleContext " + name);
////				System.out.println("BundleVersion " + version);
//			}

			result.addAll(Arrays.asList(context.getBundles()));
		}
		return result;
	}

	private static List<Bundle> filterBundles(final List<Bundle> bundles, final String filterName)
	{
		final List<Bundle> result = new ArrayList<Bundle>();
		final Iterable<Bundle> iterable = Iterables.filter(bundles, new Predicate<Bundle>() {
			@Override
			public boolean apply(final Bundle input)
			{
				return input.getSymbolicName().contains(filterName);
			}
		});

		final Iterator<Bundle> iterator = iterable.iterator();
		while (iterator.hasNext())
		{
			result.add(iterator.next());
		}
		return result;
	}

	// ======================================================================
	// General helper methods
	// ======================================================================
	public static String getStdlibVersion(String str)
	{
		return str.replace(com.shs.hl.ui.utils.Constants.STDLIBNAME, "").replaceAll("^\\.", "");
	}

	public static String getBundleVersionString(Version version)
	{

		if (version != null)
		{
			String complete = version.toString();
			String qualifier = version.getQualifier();

			if (qualifier != null && qualifier.length() > 0)

			complete = complete.replace("." + qualifier, ""); // for debugging
																// purpose split
																// into two
																// lines
			return complete;
		}
		return "";
	}

}
