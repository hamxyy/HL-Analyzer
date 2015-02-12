package com.shs.hl.ui.generator;

import java.util.HashSet;
import java.util.Set;

public class FunctionNode {
	FunctionNode		parent;
	Set<FunctionNode>	calls	= new HashSet<FunctionNode>();
	String				name;
	String				fileName;

	public FunctionNode(final String fname, final String fFilename) {
		name = fname;
		fileName = fFilename;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public FunctionNode getParent() {
		return parent;
	}

	public void setParent(final FunctionNode parent) {
		this.parent = parent;
	}

	public Set<FunctionNode> getCalls() {
		return calls;
	}

	public void setCalls(final Set<FunctionNode> fCalls) {
		calls = fCalls;
	}

	@Override
	public boolean equals(final Object obj) {
		boolean result = false;
		if (obj instanceof FunctionNode && name != null) {
			result = name.equalsIgnoreCase(((FunctionNode) obj).getName());
		}
		return result;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		String tobeHashed = name;
		if (tobeHashed == null) tobeHashed = "null";
		for (int i = 0; i < tobeHashed.length(); i++) {
			hash = hash * 31 + tobeHashed.charAt(i);
		}
		return hash;
	}

}
