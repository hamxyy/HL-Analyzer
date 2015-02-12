package com.shs.common.od;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionStrategy;
import org.eclipse.xtext.util.IAcceptor;

import com.google.inject.Inject;
import com.shs.common.commonLanguage.ChannelParameter;
import com.shs.common.commonLanguage.CrvParameter;
import com.shs.common.commonLanguage.DoubleParameter;
import com.shs.common.commonLanguage.EnumParameter;
import com.shs.common.commonLanguage.IntegerParameter;
import com.shs.common.commonLanguage.SymbolDeclaration;

public class CommonResourceDescriptionStrategy extends
		DefaultResourceDescriptionStrategy {

	public static final String		KEY_LITERALS	= "literals";
	public static final String		KEY_VALUES		= "values";
	public static final String		KEY_SCOPE		= "scope";

	@Inject
	private IQualifiedNameProvider	nameProv;

	@Override
	public boolean createEObjectDescriptions(final EObject from, final IAcceptor<IEObjectDescription> acceptor) {
		if (nameProv == null) return false;
		final QualifiedName qualifiedName = nameProv.getFullyQualifiedName(from);
		if (qualifiedName != null) {
			if (from instanceof IntegerParameter) {
				final IntegerParameter ip = (IntegerParameter) from;
				final String scope = ip.getScope().getLiteral();
				acceptor.accept(createWithUserData(qualifiedName.toString(), from, KEY_SCOPE, scope));
				return true;
			} else if (from instanceof DoubleParameter) {
				final DoubleParameter dp = (DoubleParameter) from;
				final String scope = dp.getScope().getLiteral();
				acceptor.accept(createWithUserData(qualifiedName.toString(), from, KEY_SCOPE, scope));
				return true;
			} else if (from instanceof EnumParameter) {
				// in this case we don't trust store the scope in the user data
				// but also all the literals of the enumeration
				final EnumParameter ep = (EnumParameter) from;
				final String scope = ep.getScope().getLiteral();
				final Map<String, String> userData = new HashMap<String, String>();
				userData.put(KEY_SCOPE, scope);
				final EList<SymbolDeclaration> literals = ep.getLiterals();
				String literalString = "";
				for (final SymbolDeclaration l : literals) {
					literalString += l.getName() + " ";
				}
				userData.put(KEY_LITERALS, literalString);
				acceptor.accept(EObjectDescription.create(qualifiedName, from, userData));
				return true;
			} else if (from instanceof ChannelParameter) {
				final ChannelParameter ep = (ChannelParameter) from;
				final String scope = ep.getScope().getLiteral();
				acceptor.accept(createWithUserData(qualifiedName.toString(), from, KEY_SCOPE, scope));
				return true;
			} else if (from instanceof CrvParameter) {
				final String scope = ((CrvParameter) from).getScope().getLiteral();
				acceptor.accept(createWithUserData(qualifiedName.toString(), from, KEY_SCOPE, scope));
				return true;
			} else {
				acceptor.accept(EObjectDescription.create(qualifiedName, from));
				return true;
			}
		}
		return super.createEObjectDescriptions(from, acceptor);
	}

	/**
	 * this method creates an IEObjectDescription with one key value pair in its
	 * user data
	 * 
	 * @return
	 */
	private IEObjectDescription createWithUserData(final String qname, final EObject object, final String key, final String value) {
		final Map<String, String> userData = new HashMap<String, String>();
		userData.put(key, value);
		return EObjectDescription.create(qname, object, userData);
	}
}
