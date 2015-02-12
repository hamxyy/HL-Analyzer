package com.shs.hl.debug.core.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

public class HLDebugVariable extends HLDebugElement implements IVariable {

	private final String frameName;
	private final HLDebugStackFrame frame;

	public HLDebugVariable(HLDebugStackFrame fFrame, String name) {
		super(fFrame.getDebugTarget());
		this.frame = fFrame;
		this.frameName = name;
	}

	@Override
	public void setValue(String expression) throws DebugException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValue(IValue value) throws DebugException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean supportsValueModification() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifyValue(String expression) throws DebugException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifyValue(IValue value) throws DebugException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IValue getValue() throws DebugException {
		// TODO
		String value = "value is";
		return new HLDebugValue(this.getDebugTarget(), value);
	}

	@Override
	public String getName() throws DebugException {
		return frameName;
	}

	@Override
	public String getReferenceTypeName() throws DebugException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasValueChanged() throws DebugException {
		// TODO Auto-generated method stub
		return false;
	}

	public String getFrameName() {
		return frameName;
	}

	public HLDebugStackFrame getFrame() {
		return frame;
	}

}
