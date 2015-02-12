package com.shs.hl.ui.hover;

import java.util.List;

import org.eclipse.jface.text.ITextHover;
import org.eclipse.xtext.ui.editor.hover.DefaultCompositeHover;

public class HLHover extends DefaultCompositeHover {

	@Override
	protected List<ITextHover> createHovers() {
		List<ITextHover> zeugVonOben = super.createHovers();
		zeugVonOben.add(new HLCommentHover());
		return zeugVonOben;
	}

}
