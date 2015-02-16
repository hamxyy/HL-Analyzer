/*
 * generated by Xtext
 */
package com.shs.hl;

import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;

//import com.shs.hl.generator.HearingLanguageGenerator2;
import com.shs.hl.generator.HearingLanguageGenerator2;
import com.shs.hl.naming.HLQualifiedNameProvider;
import com.shs.hl.od.HLResourceDescriptionStrategy;
import com.shs.hl.typesystem.HLTypesystem;

import de.itemis.xtext.typesystem.ITypesystem;

/**
 * Use this class to register components to be used at runtime / without the
 * Equinox extension registry.
 */
public class HearingLanguageRuntimeModule extends
		com.shs.hl.AbstractHearingLanguageRuntimeModule {

	public Class<? extends ITypesystem> bindITypestem() {
		return HLTypesystem.class;
	}

	public Class<? extends IDefaultResourceDescriptionStrategy> bindIDefaultResourceDescriptionStrategy() {
		return HLResourceDescriptionStrategy.class;
	}

	@Override
	public Class<? extends org.eclipse.xtext.naming.IQualifiedNameProvider> bindIQualifiedNameProvider() {
		return HLQualifiedNameProvider.class;
	}

	@Override
	public Class<? extends org.eclipse.xtext.generator.IGenerator> bindIGenerator() {
		return HearingLanguageGenerator2.class;
		//return HearingLanguageGenerator2.class;

	}
	
	
	public Class<? extends com.shs.hl.generator.IHLGenerator> bindIHLGenerator()
	{
		return HearingLanguageGenerator2.class;
	}
	
	// contributed by org.eclipse.xtext.generator.scoping.AbstractScopingFragment
	@Override	
	public void configureIScopeProviderDelegate(com.google.inject.Binder binder) {
		binder.bind(org.eclipse.xtext.scoping.IScopeProvider.class).annotatedWith(com.google.inject.name.Names.named(org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider.NAMED_DELEGATE)).to(com.shs.hl.scoping.HearingLanguageLocalScopeProvider.class);
		
	}

}