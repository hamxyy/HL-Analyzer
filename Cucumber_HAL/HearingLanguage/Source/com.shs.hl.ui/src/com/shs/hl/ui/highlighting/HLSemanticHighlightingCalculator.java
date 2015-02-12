package com.shs.hl.ui.highlighting;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.ui.editor.syntaxcoloring.ISemanticHighlightingCalculator;

import com.shs.hl.hearingLanguage.FunctionAttribute;
import com.shs.hl.hearingLanguage.Module;
import com.shs.hl.hearingLanguage.PseudoUnitType;

public class HLSemanticHighlightingCalculator implements ISemanticHighlightingCalculator
{

	@Override
	public void provideHighlightingFor(XtextResource resource, IHighlightedPositionAcceptor acceptor)
	{
		if (resource == null) return;
		EList<EObject> contents = resource.getContents();
		if (contents.size() == 0) return;
		EObject root = contents.get(0);
		if (root instanceof Module)
		{
			TreeIterator<EObject> eAllContents = root.eAllContents();
			while (eAllContents.hasNext())
			{
				EObject o = (EObject) eAllContents.next();
				if (o instanceof PseudoUnitType)
				{
					ICompositeNode n = NodeModelUtils.findActualNodeFor(o);
					acceptor.addPosition(n.getOffset(), n.getLength(), HLHighlightingConfiguration.UNIT);
				}
				if (o instanceof FunctionAttribute)
				{
					ICompositeNode n = NodeModelUtils.findActualNodeFor(o);
					acceptor.addPosition(n.getOffset(), n.getLength(), HLHighlightingConfiguration.ATTR);
				}
			}
		}
	}

}
