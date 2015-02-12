package com.shs.ui.dialog;

public class Process {

	private final int		procID;
	private final String	name;

	public Process(int id, String name) {
		this.procID = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getProcID() {
		return procID;
	}

}
