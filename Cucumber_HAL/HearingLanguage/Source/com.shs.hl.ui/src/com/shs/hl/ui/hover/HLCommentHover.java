package com.shs.hl.ui.hover;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.ui.texteditor.DefaultMarkerAnnotationAccess;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.hover.AbstractProblemHover;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.inject.Inject;
import com.shs.common.commonLanguage.ChannelParameter;
import com.shs.common.commonLanguage.CrvParameter;
import com.shs.common.commonLanguage.EnumParameter;
import com.shs.common.commonLanguage.IntegerParameter;
import com.shs.common.commonLanguage.SymbolDeclaration;
import com.shs.hl.hearingLanguage.ParameterReadExpression;

/**
 * TODO Alexander
 * 
 * @author reincl29
 * 
 */
public class HLCommentHover extends AbstractProblemHover {

	@Inject
	private DefaultMarkerAnnotationAccess	markerAnnotationAccess;

	@Override
	protected IRegion getHoverRegionInternal(final int lineNumber, final int offset) {
		final List<Annotation> annotations = getAnnotations(lineNumber, offset);
		if (annotations == null) return null;
		for (final Annotation annotation : annotations) {
			final Position position = sourceViewer.getAnnotationModel().getPosition(annotation);
			if (position != null) {
				final int start = position.getOffset();
				return new Region(start, position.getLength());
			}
		}
		return null;
	}

	@Override
	protected boolean isHandled(final Annotation annotation) {
		return null != annotation
				&& !annotation.isMarkedDeleted()
				&& markerAnnotationAccess != null
				&& (markerAnnotationAccess.isSubtype(annotation.getType(),
						"org.eclipse.ui.workbench.texteditor.error")
						|| markerAnnotationAccess.isSubtype(
								annotation.getType(),
								"org.eclipse.ui.workbench.texteditor.warning") || markerAnnotationAccess
							.isSubtype(annotation.getType(),
									"org.eclipse.ui.workbench.texteditor.bookmark"));
	}

	@Override
	protected Object getHoverInfoInternal(final ITextViewer textViewer, final int lineNumber, final int offset) {
		String errorText = "";
		if (errorText.trim().equals("")) errorText = null;
		String documentationText = null;

		final IXtextDocument document = (IXtextDocument) sourceViewer.getDocument();
		documentationText = document.readOnly(new IUnitOfWork<String, XtextResource>() {
			@Override
			public String exec(final XtextResource resource) throws Exception {
				final IParseResult parseResult = resource.getParseResult();
				final ICompositeNode rootNode = parseResult.getRootNode();
				final INode currentNode = NodeModelUtils.findLeafNodeAtOffset(rootNode, offset);
				final EObject semanticObject = NodeModelUtils.findActualSemanticObjectFor(currentNode);
				if (semanticObject instanceof ParameterReadExpression) {
					final SymbolDeclaration param = ((ParameterReadExpression) semanticObject).getParam();
					if (param instanceof IntegerParameter) return ((IntegerParameter) param).getDoc();
					if (param instanceof EnumParameter) return ((EnumParameter) param).getDoc();
					if (param instanceof ChannelParameter) return ((ChannelParameter) param).getDoc();
					if (param instanceof CrvParameter) return ((CrvParameter) param).getDoc();

				}
				return null;
			}
		});

		if (documentationText != null) {
			if (documentationText.startsWith("/$")) documentationText = documentationText.substring(2);
			if (documentationText.endsWith("$/")) documentationText = documentationText.substring(0, documentationText.length() - 2);
			documentationText = documentationText.trim();
		}

		if (errorText == null && documentationText == null)
			return null;
		else if (errorText == null && documentationText != null)
			return documentationText;
		else if (errorText != null && documentationText == null)
			return errorText;
		else
			return errorText + "\n\n" + documentationText;

	}

}
