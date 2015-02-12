package de.itemis.xtext.typesystem.dsl.validation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.validation.Check;

import com.google.inject.Inject;

import de.itemis.xtext.typesystem.dsl.generator.Utils;
import de.itemis.xtext.typesystem.dsl.scoping.TsDslScopeProvider;
import de.itemis.xtext.typesystem.dsl.tsDsl.AbstractTypeTypingRule;
import de.itemis.xtext.typesystem.dsl.tsDsl.CharRef;
import de.itemis.xtext.typesystem.dsl.tsDsl.ComatibilityTypeConstraint;
import de.itemis.xtext.typesystem.dsl.tsDsl.CommonSuperTypeOfRule;
import de.itemis.xtext.typesystem.dsl.tsDsl.EClassRef;
import de.itemis.xtext.typesystem.dsl.tsDsl.FeatureTypeConstraint;
import de.itemis.xtext.typesystem.dsl.tsDsl.FixedTypingRule;
import de.itemis.xtext.typesystem.dsl.tsDsl.MetaclassSpec;
import de.itemis.xtext.typesystem.dsl.tsDsl.Section;
import de.itemis.xtext.typesystem.dsl.tsDsl.TbdRule;
import de.itemis.xtext.typesystem.dsl.tsDsl.TsDslPackage;
import de.itemis.xtext.typesystem.dsl.tsDsl.TypeComparisonFeature;
import de.itemis.xtext.typesystem.dsl.tsDsl.TypeOfFeatureRule;
import de.itemis.xtext.typesystem.dsl.tsDsl.TypeRecursionFeatures;
import de.itemis.xtext.typesystem.dsl.tsDsl.TypeSpec;
import de.itemis.xtext.typesystem.dsl.tsDsl.TypesystemSpec;
 

public class TsDslJavaValidator extends AbstractTsDslJavaValidator {

	@Inject
	private IScopeProvider scoper = null;
	
	
	@Check
	public void checkBasicParameters( TbdRule r) {
		error("must be replaced with actual specification", r.eContainer(), TsDslPackage.eINSTANCE.getMetaclassSpec_TypingRule(), -1);
	}
	
	
	
	@Check
	public void checkCommon( CommonSuperTypeOfRule cstr ) {
		TypesystemSpec s = (TypesystemSpec)cstr.eContainer().eContainer().eContainer();
		for(Section sec : s.getSections()) {
			if ( !sec.getSubtypeSpec().isEmpty()) return;
		}
		warning("use of 'common' without a subtype specification is almost certainly an error", cstr.eContainer(), TsDslPackage.eINSTANCE.getMetaclassSpec_TypingRule(), -1);
	}
	
	
	@Check
	public void checkBasicParameters( TypesystemSpec spec ) {
		if ( spec.getName().indexOf(".") < 0) {
			error("cannot use unqualified names here", TsDslPackage.eINSTANCE.getTypesystemSpec_Name());
		}
		TsDslScopeProvider sp = (TsDslScopeProvider)scoper;
		if ( sp.load(spec.getEcoreUri()) == null ) {
			error("cannot load Ecore file (please use a platform:/resource URI)", TsDslPackage.eINSTANCE.getTypesystemSpec_EcoreUri());
		}
	}
	
	
	@Check
	public void checkUniqueTypeRule(MetaclassSpec s) {
		for (MetaclassSpec ms: all(s)) {
			if ( ms != s && ms.getClazz() == s.getClazz()) {
				error("duplicate type spec", TsDslPackage.eINSTANCE.getMetaclassSpec_Clazz());
			}
		}
	}
	

	@Check
	public void checkTypeOfFeature( AbstractTypeTypingRule r ) {
		MetaclassSpec spec = (MetaclassSpec) r.eContainer();
		TypesystemSpec tsspec = (TypesystemSpec)spec.eContainer().eContainer();
		Iterable<EClass> subtypes = new Utils().subtypes(spec.getClazz());
		for (EClassifier sub : subtypes) {
			if (! new Utils().hasSpecFor(tsspec, (EClass)sub)) {
				warning("subtype "+sub.getName()+" not handled", spec, TsDslPackage.eINSTANCE.getMetaclassSpec_TypingRule(), -1);
			}
		}
	}

	
	@Check
	public void checkTypeOfFeature( TypeOfFeatureRule r ) {
		MetaclassSpec spec = (MetaclassSpec) r.eContainer();
		EClass target = (EClass)r.getFeature().getEType();
		checkTypePropagation(spec, target, TsDslPackage.eINSTANCE.getTypeOfFeatureRule_Feature());
	}

	@Check
	public void checkTypeOfFeature( FixedTypingRule r ) {
		MetaclassSpec spec = (MetaclassSpec) r.eContainer();
		checkTypePropagation(spec, r.getClazz(), TsDslPackage.eINSTANCE.getFixedTypingRule_Clazz());
	}

	@Check
	public void checkTypeOfFeature( TypeRecursionFeatures f ) {
		MetaclassSpec spec = (MetaclassSpec) f.eContainer();
		checkTypePropagation(spec, (EClass)f.getFeature().getEType(), TsDslPackage.eINSTANCE.getTypeRecursionFeatures_Feature());
	}


	@Check
	public void checkTypeOfFeature( CommonSuperTypeOfRule r ) {
		MetaclassSpec spec = (MetaclassSpec) r.eContainer();
		checkTypePropagation(spec, (EClass)r.getFeature1().getEType(), TsDslPackage.eINSTANCE.getCommonSuperTypeOfRule_Feature1());
		checkTypePropagation(spec, (EClass)r.getFeature2().getEType(), TsDslPackage.eINSTANCE.getCommonSuperTypeOfRule_Feature2());
	}


	
	
	@Check
	public void checkTypeOfFeature( FeatureTypeConstraint c ) {
		MetaclassSpec spec = (MetaclassSpec) c.eContainer();
		EClass target = (EClass)c.getFeature().getEType();
		checkTypePropagation(spec, target, TsDslPackage.eINSTANCE.getFeatureTypeConstraint_Feature());
		for (TypeSpec cls: c.getTypes()) {
			if ( cls instanceof EClassRef ) {
				checkTypePropagation(spec, ((EClassRef)cls).getClazz(), TsDslPackage.eINSTANCE.getFeatureTypeConstraint_Types());
			}
		}
	}

	@Check
	public void checkTypeOfFeature( ComatibilityTypeConstraint c ) {
		MetaclassSpec spec = (MetaclassSpec) c.eContainer();
		EClass target1 = (EClass)c.getFeature1().getEType();
		checkTypePropagation(spec, target1, TsDslPackage.eINSTANCE.getComatibilityTypeConstraint_Feature1());
		EClass target2 = (EClass)c.getFeature1().getEType();
		checkTypePropagation(spec, target2, TsDslPackage.eINSTANCE.getComatibilityTypeConstraint_Feature2());
	}

	
	
	
	private void checkTypePropagation( MetaclassSpec spec, EClass target, EStructuralFeature errorFeature ) {
		TypesystemSpec tsspec = (TypesystemSpec)spec.eContainer().eContainer();
		if ( ! new Utils().hasSpecFor(tsspec, target)) {
			warning("no typing rule available for "+target.getName(), errorFeature, -1, ErrorConstants.METACLASSSPEC_MISSING, errorFeature.getName(), target.getName());
		}
	}
	
	
	private List<MetaclassSpec> all(MetaclassSpec mcs) {
		List<MetaclassSpec> res = new ArrayList<MetaclassSpec>();
		TypesystemSpec tss = (TypesystemSpec) mcs.eContainer().eContainer();
		for (Section section: tss.getSections()) {
			for (MetaclassSpec ms: section.getMetaclassSpecs()) {
				res.add(ms);
			}
		}
		return res;
	}
	
	
	
	
	

}
