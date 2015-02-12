package com.shs.hl.naming;

import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;

import com.google.inject.Inject;
import com.shs.hl.hearingLanguage.BuiltInOp;
import com.shs.hl.hearingLanguage.Namespace;
import com.shs.hl.hearingLanguage.Type;

import de.itemis.xtext.typesystem.ITypesystem;

public class HLQualifiedNameProvider extends DefaultDeclarativeQualifiedNameProvider {

	@Inject
	private ITypesystem ts;
	
	/**
	 * The qualified name of BuiltInOp includes its context type,
	 * to make sure we can use the same (unqualified) name for
	 * BuiltInOps of different context types. 
	 */
	public QualifiedName qualifiedName( BuiltInOp op ) {
		Namespace ns = (Namespace) op.eContainer();
		return getFullyQualifiedName(ns).append(ctName( op.getContextType() )).append(op.getName());
	}

	private String ctName(Type contextType) {
		return ts.typeString(contextType);
	}

}
