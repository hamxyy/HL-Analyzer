package com.shs.hl.ui.highlighting;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfigurationAcceptor;
import org.eclipse.xtext.ui.editor.utils.TextStyle;

public class HLHighlightingConfiguration extends DefaultHighlightingConfiguration {
	
	public static final String ATTR = "attr";
	public static final String UNIT = "unit";
	
	@Override
	public void configure(IHighlightingConfigurationAcceptor acceptor) {
		// TODO Auto-generated method stub
		super.configure(acceptor);
		acceptor.acceptDefaultHighlighting(UNIT, "unit", unitTextStyle());
		acceptor.acceptDefaultHighlighting(ATTR, "attr", attrTextStyle());
	}
	
	private TextStyle unitTextStyle() {
		TextStyle t = defaultTextStyle().copy();
		t.setColor(new RGB(100,100,255));
		t.setStyle(SWT.ITALIC);
		return t;
	}
	
	private TextStyle attrTextStyle() {
		TextStyle t = defaultTextStyle().copy();
		t.setColor(new RGB(100,100,100));
		t.setStyle(SWT.ITALIC);
		return t;
	}
	

}
