/*
 * generated by Xtext
 */
package com.shs.common.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;
import org.eclipse.xtext.ui.resource.SimpleResourceSetProvider;

/**
 * Use this class to register components to be used within the IDE.
 */
public class CommonLanguageUiModule extends com.shs.common.ui.AbstractCommonLanguageUiModule {
	public CommonLanguageUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

//	/**
//	 * @see org.eclipse.xtext.ui.DefaultUiModule#bindIResourceForEditorInputFactory()
//	 */
//	@Override
//	public Class<? extends IResourceForEditorInputFactory> bindIResourceForEditorInputFactory() {
//		return ResourceForIEditorInputFactory.class;
//	}


	/**
	 * @see org.eclipse.xtext.ui.DefaultUiModule#bindIResourceSetProvider()
	 */
	@Override
	public Class<? extends IResourceSetProvider> bindIResourceSetProvider() {
		return SimpleResourceSetProvider.class;
	}


	// contributed by org.eclipse.xtext.ui.generator.ImplicitUiFragment
	public com.google.inject.Provider<org.eclipse.xtext.resource.containers.IAllContainersState> provideIAllContainersState() {
		return org.eclipse.xtext.ui.shared.Access.getWorkspaceProjectsState();
	}
}
