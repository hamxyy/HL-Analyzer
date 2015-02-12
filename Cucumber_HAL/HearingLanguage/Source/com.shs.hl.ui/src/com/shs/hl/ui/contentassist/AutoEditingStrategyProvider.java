package com.shs.hl.ui.contentassist;

import org.eclipse.jface.text.IDocument;
import org.eclipse.xtext.ui.editor.autoedit.DefaultAutoEditStrategyProvider;

public class AutoEditingStrategyProvider extends
		DefaultAutoEditStrategyProvider {

	@Override
	protected void configureCompoundBracesBlocks(final IEditStrategyAcceptor acceptor) {
		acceptor.accept(compoundMultiLineTerminals.newInstanceFor("[", "]").and("(", ")"), IDocument.DEFAULT_CONTENT_TYPE);
	}

}
