package com.shs.hl.debug.core.sourcelookup;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.sourcelookup.AbstractSourceLookupParticipant;

import com.shs.hl.debug.core.model.HLDebugStackFrame;

public class HLDebugSourceLookupParticipant extends
		AbstractSourceLookupParticipant {

	@Override
	public String getSourceName(Object object) throws CoreException {
		if (object instanceof HLDebugStackFrame) {
			return ((HLDebugStackFrame) object).getSourceName();
		}
		return null;
	}

}
