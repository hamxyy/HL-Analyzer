package de.itemis.xtext.typesystem.testing;

import static de.itemis.xtext.typesystem.util.Utils.ancestor;
import static de.itemis.xtext.typesystem.util.Utils.eString;
import static de.itemis.xtext.typesystem.util.Utils.egetAndResolve;
import static de.itemis.xtext.typesystem.util.Utils.getEObject;
import static de.itemis.xtext.typesystem.util.Utils.name;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.validation.Issue;

 

public class IssueCollection {

	private static Logger LOGGER = Logger.getLogger(IssueCollection.class);

	private List<Issue> issues;
	private List<String> messages;
	private XtextResource resource;
	
	private boolean state;
	private boolean stateIsSet;

	public IssueCollection( XtextResource res, List<Issue> issues, List<String> messages ) {
		this.resource = res;
		this.issues = issues;
		this.messages = messages;
	}
 
	public IssueCollection(XtextResource res, List<String> messages) {
		this.issues = new ArrayList<Issue>();
		this.resource = res;
		this.messages = messages;
	}
	
	private void addMessage( String m ) {
		messages.add( m );
	}
	
	public void addIssue( Issue issue ) {
		issues.add( issue );
	}
	
	public IssueCollection forType( Class<? extends EObject> cls ) {
		IssueCollection res = new IssueCollection(resource, messages);
		for (Issue i : issues) {
			URI uri = i.getUriToProblem();
			EObject eObject = resource.getEObject(uri.fragment());
			if ( cls.isInstance(eObject)) {
				res.addIssue(i);
			}
		}
		if (res.getIssueCount() == 0 ) {
			res.addMessage("No issues found for type "+cls.getName());
		}
		return res;
	}

	public IssueCollection get( int index ) {
		IssueCollection res = new IssueCollection(resource, messages);
		if ( index >= getIssueCount() ) {
			res.addMessage( "trying to get element at "+index+", but only have "+getIssueCount()+" elements -> creating empty collection!");
		} else {
			res.addIssue( getIssues().get(index) );
		}
		return res;
	}

	public IssueCollection inLine( int lineNo ) {
		IssueCollection res = new IssueCollection(resource, messages);
		int rc = 0;
		for (Issue i: issues) {
			if ( i.getLineNumber() == lineNo ) {
				res.addIssue( i );
				rc++;
			}
		} 
		if ( rc == 0 ) {
			res.addMessage("no issues found for line number "+lineNo);
		}
		return res;
	}

	
	
	public IssueCollection withStringFeatureValue( String featureName, String value ) {
		IssueCollection res = new IssueCollection(resource, messages);
		for (Issue i: issues) {
			EObject eObject = getEObject(i, resource);
			String v = eString(egetAndResolve(eObject, featureName, resource.getResourceSet()));
			if ( v.contains(value)) {
				res.addIssue( i );
			}
		}
		if (res.getIssueCount() == 0 ) {
			res.addMessage("no elements found with feature "+featureName+" valued '"+value+"'");
		}
		return res;
	}

	public IssueCollection errorsOnly() {
		IssueCollection res = new IssueCollection(resource, messages);
		for (Issue i: issues) {
			if ( i.getSeverity() == Severity.ERROR ) {
				res.addIssue( i );
			}
		}
		return res;
	}

	public IssueCollection named( String expectedName ) {
		IssueCollection res = new IssueCollection(resource, messages);
		for (Issue i: issues) {
			EObject eObject = getEObject(i, resource);
			String name = name(eObject);
			if ( name.contains(expectedName)) {
				res.addIssue( i );
			}
		}
		if (res.getIssueCount() == 0 ) {
			res.addMessage("no elements found with name "+expectedName);
		}
		return res;
	}


	
	public IssueCollection forElement( Class<? extends EObject> cls, String name ) {
		IssueCollection res = new IssueCollection(resource, messages);
		for (Issue i : issues) {
			EObject eObject = getEObject(i, resource);
			if ( cls.isInstance(eObject)) {
				if ( name.equalsIgnoreCase(name(eObject)) ) {
					res.addIssue(i);
				}
			}
		}
		if (res.getIssueCount() == 0 ) {
			res.addMessage("no elements of type "+cls.getName()+" named '"+name+"' found");
		}
		return res;
	}

	private int getIssueCount() {
		return issues.size();
	}

	public IssueCollection under( Class<? extends EObject> cls ) {
		return under(cls,null);
	}

	
	public IssueCollection under( Class<? extends EObject> cls, String name ) {
		IssueCollection res = new IssueCollection(resource, messages);
		for (Issue i : issues) {
			URI uri = i.getUriToProblem();
			EObject eObject = resource.getEObject(uri.fragment());
			EObject p = ancestor( eObject, cls );
			if ( p != null ) {
				if ( name != null ) {
					if ( name.equals(name(p))) {
						res.addIssue(i);
					}
				} else {
					res.addIssue(i);
				}
			} 
		}
		if ( res.getIssueCount() == 0 ) {
			res.addMessage("nothing found under a "+cls.getName()+" named '"+name+"'");
		}
		return res;
	}
	

	public IssueCollection sizeIs(int i) {
		if ( issues.size() == i ) {
			state = true;
		} else {
			addMessage("failed size: expected "+i+", actual "+issues.size());
			state = false;
		}
		return this;
	}
	
	public IssueCollection oneOfThemContains(String substring) {
		boolean found = false;
		for (Issue i: issues) {
			if ( i.getMessage().toLowerCase().contains(substring.toLowerCase()) ) { 
				found = true;
			}
		}
		if ( found ) reportOk(); else {
			addMessage("failed oneOfThemContains: none of the issues contains substring '"+substring+"'");
			reportError();
		}
		return this;
	}
	
	public IssueCollection nOfThemContain(int n, String substring) {
		int count = 0;
		for (Issue i: issues) {
			if ( i.getMessage().toLowerCase().contains(substring.toLowerCase()) ) { 
				count++;
			}
		}
		if ( count == n ) reportOk(); else {
			addMessage("failed nOfThemContain: expected "+n+" with substring '"+substring+"', but '"+count+"' found.");
			reportError();
		}
		return this;
	}	
	
	public IssueCollection allOfThemContain(String substring) {
		for (Issue i: issues) {
			if ( !i.getMessage().toLowerCase().contains(substring.toLowerCase()) ) {
				reportError();
				addMessage("failed allOfThemContain: not all issues contain the substring '"+substring+"'");
			}
		}
		reportOk(); 
		return this;
	}
	
	public IssueCollection theOneAndOnlyContains(String substring) {
		if ( issues.size() > 1 ) {
			reportError();
			addMessage("failed theOneAndOnlyContains: expecting a single issue (theSingleOneReads) but found: "+issues.size());
			for (Issue issue : issues) {
				LOGGER.debug("  line "+issue.getLineNumber()+": "+issue.getMessage()+" / "+issue.getUriToProblem());
			}			
			return this;
		}
		return oneOfThemContains(substring);
	}
	
	public boolean evaluate() {
		return state;
	}
	
	
	protected void reportOk() {
		if ( stateIsSet ) {
			if ( state ) state = true;
			if ( !state ) state = false;
		} else  {
			state = true;
		}
		stateIsSet = true;
	}

	protected void reportError() {
		if ( stateIsSet ) {
			if ( state ) state = false;
			if ( !state ) state = false;
		} else  {
			state = false;
		}
		stateIsSet = true;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public List<String> getMessages() {
		return messages;
	}
	
	public String getMessageString() {
		StringBuffer sb = new StringBuffer();
		for (String m : messages) {
			sb.append("\n  - "+m);
		}
		return sb.toString();
	}

	public void dumpIssues() {
		LOGGER.debug("--- Issues ---");
		for (Issue i: issues) {
			dumpIssue(resource, i);
		}
	}
	
	public static void dumpIssue(XtextResource resource, Issue issue) {
		EObject eObject =  resource.getEObject(issue.getUriToProblem().fragment());
		EClass cls = eObject.eClass();
		LOGGER.debug(cls.getName()+"(line "+issue.getLineNumber()+"): "+issue.getMessage());
	}


}
