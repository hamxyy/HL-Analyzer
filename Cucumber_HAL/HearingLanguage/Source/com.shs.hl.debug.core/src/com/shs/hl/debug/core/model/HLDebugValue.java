package com.shs.hl.debug.core.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;

public class HLDebugValue extends HLDebugElement implements IValue {

    String value;

    public HLDebugValue(IDebugTarget ftarget, String fvalue) {
	super(ftarget);
	this.value = fvalue;
    }

    @Override
    public String getReferenceTypeName() throws DebugException {
	try {
	    Integer.parseInt(value);
	} catch (NumberFormatException e) {
	    return "text";
	}
	return "integer";
    }

    @Override
    public String getValueString() throws DebugException {
	return this.value;
    }

    @Override
    public boolean isAllocated() throws DebugException {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public IVariable[] getVariables() throws DebugException {
	// TODO Auto-generated method stub
	return new IVariable[0];
    }

    @Override
    public boolean hasVariables() throws DebugException {
	// TODO Auto-generated method stub
	return true;
    }

}
