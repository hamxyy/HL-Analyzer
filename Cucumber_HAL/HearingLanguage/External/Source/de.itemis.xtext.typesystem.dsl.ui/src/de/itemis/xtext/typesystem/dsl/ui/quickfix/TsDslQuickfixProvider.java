
package de.itemis.xtext.typesystem.dsl.ui.quickfix;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.IModification;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;

import de.itemis.xtext.typesystem.dsl.validation.ErrorConstants;

public class TsDslQuickfixProvider extends DefaultQuickfixProvider {

	@Fix(ErrorConstants.METACLASSSPEC_MISSING)
	public void fixMissingRule(final Issue issue, IssueResolutionAcceptor acceptor) {
		final String targetType = issue.getData()[1];
		acceptor.accept(issue, "Add Rule for "+targetType, "Add a typeof rule for "+targetType+" right before this one.", null, new IModification() {
			public void apply(IModificationContext context) throws BadLocationException {
				IXtextDocument xtextDocument = context.getXtextDocument();
				final int GOBACKLEN = 300;
				String stuff = xtextDocument.get(issue.getOffset()-GOBACKLEN, GOBACKLEN);
				int d = 10;
				while ( !stuff.substring(GOBACKLEN-d,GOBACKLEN-d+6).equals("typeof") ) 
					d++;
				xtextDocument.replace(issue.getOffset()-d-1, 1, 
						"    typeof "+targetType+" -> tbd\n\n    ");
			}
		});
	}

}
