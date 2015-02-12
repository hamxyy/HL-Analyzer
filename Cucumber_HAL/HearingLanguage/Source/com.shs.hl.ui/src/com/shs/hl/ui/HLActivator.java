package com.shs.hl.ui;

import org.osgi.framework.BundleContext;

import com.shs.hl.ui.internal.HearingLanguageActivator;

public class HLActivator extends HearingLanguageActivator {

	private static BundleContext	hlcontext;

	public static BundleContext getHlBundleContext() {
		return hlcontext;
	}

	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		hlcontext = context;
	}

}
