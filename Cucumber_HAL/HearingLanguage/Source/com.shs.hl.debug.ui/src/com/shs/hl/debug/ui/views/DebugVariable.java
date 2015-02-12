package com.shs.hl.debug.ui.views;

public class DebugVariable {
	private String name;
	String value;

	public DebugVariable(String fname, String fvalue) {
		this.setName(fname);
		this.value = fvalue;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
