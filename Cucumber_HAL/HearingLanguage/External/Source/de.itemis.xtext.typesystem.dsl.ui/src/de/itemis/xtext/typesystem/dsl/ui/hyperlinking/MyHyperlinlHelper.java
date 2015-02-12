package de.itemis.xtext.typesystem.dsl.ui.hyperlinking;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.text.Region;
import org.eclipse.xtext.common.types.xtext.ui.TypeAwareHyperlinkHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkAcceptor;

import de.itemis.xtext.typesystem.dsl.generator.Utils;
import de.itemis.xtext.typesystem.dsl.tsDsl.MetaclassSpec;
import de.itemis.xtext.typesystem.dsl.tsDsl.TypesystemSpec;

public class MyHyperlinlHelper extends TypeAwareHyperlinkHelper {

	
	@Override
	public void createHyperlinksTo(XtextResource from, Region region, EObject to, IHyperlinkAcceptor acceptor) {
		if ( to instanceof EStructuralFeature ) {
			EClass cls = (EClass) ((EStructuralFeature) to).getEType();
			TypesystemSpec tss = (TypesystemSpec) from.getContents().get(0);
			Iterable<MetaclassSpec> all = new Utils().all(tss);
			for (MetaclassSpec metaclassSpec : all) {
				if ( metaclassSpec.getClazz() == cls ) {
					super.createHyperlinksTo(from, region, metaclassSpec, acceptor);
				}
			}
		} else {
			super.createHyperlinksTo(from, region, to, acceptor);
		}
	}

}
