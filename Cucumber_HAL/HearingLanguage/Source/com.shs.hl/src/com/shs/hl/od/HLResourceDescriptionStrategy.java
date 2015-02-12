package com.shs.hl.od;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionStrategy;
import org.eclipse.xtext.util.IAcceptor;

import com.google.inject.Inject;
import com.shs.hl.hearingLanguage.BuiltInMethodCall;
import com.shs.hl.hearingLanguage.BuiltInOp;
import com.shs.hl.hearingLanguage.FunctionDeclaration;
import com.shs.hl.hearingLanguage.FunctionKind;
import com.shs.hl.hearingLanguage.Type;

import de.itemis.xtext.typesystem.ITypesystem;
import de.itemis.xtext.typesystem.trace.TypeCalculationTrace;

public class HLResourceDescriptionStrategy extends DefaultResourceDescriptionStrategy
{

	public static final String		KEY_CONTEXT_TYPE	= "contextType";

	@Inject
	private IQualifiedNameProvider	nameProv;

	@Inject
	private ITypesystem				typesystem;

	@Override
	public boolean createEObjectDescriptions(EObject from, IAcceptor<IEObjectDescription> acceptor)
	{
		if (nameProv == null) return false;
		QualifiedName qualifiedName = nameProv.getFullyQualifiedName(from);

		if (qualifiedName != null)
		{
			// so if we are about to index a built-in op
			if (from instanceof BuiltInOp)
			{
				// get the context type from the built-in op
				// (i.e., that type of thing on which it can be called)
				Type ctxtype = ((BuiltInOp) from).getContextType();
				if (ctxtype != null)
				{
					// and then store the context type in the index.
					// it will be used for scoping. Read on there!
					EObject contextType = typesystem.typeof(ctxtype, new TypeCalculationTrace());
					acceptor.accept(createWithUserData(qualifiedName.toString(), from, KEY_CONTEXT_TYPE, contextType.eClass().getName()));
					return true;
				}
			}
			if (from instanceof Package)
			{
				
			}
			// if (from instanceof BuiltInMethod)
			// {
			//
			// }
		}
		if (from instanceof FunctionDeclaration)
		{
			// only public functions externally visible
			if (((FunctionDeclaration) from).getKind().equals(FunctionKind.PUBLIC))
			{
				return super.createEObjectDescriptions(from, acceptor);
			}
			else
			{
				return false;
			}
		}
		else
		{
			return super.createEObjectDescriptions(from, acceptor);
		}
	}

	private IEObjectDescription createWithUserData(String qname, EObject object, String key, String value)
	{
		Map<String, String> userData = new HashMap<String, String>();
		userData.put(key, value);
		return EObjectDescription.create(qname, object, userData);
	}

}
