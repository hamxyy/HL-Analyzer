package com.shs.hl.net;


public class DebugCommandNet

{

	private final String	command;
	private final String	payload;
	private final String	result;
	private final String	commandTyp;
	private final int		id;

	public DebugCommandNet(int uid, String commandTyp, String command, String payload, String result) {
		this.id = uid;
		this.command = command;
		this.payload = payload;
		this.result = result;
		this.commandTyp = commandTyp;

	}

	public String getCommand() {
		return command;
	}

	public String getPayload() {
		return payload;
	}

	public String getResult() {
		return result;
	}

	public String getCommandTyp() {
		return commandTyp;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		// #cmd#2#setValue#$var1=21$var2=534##
		return "#" + commandTyp + "#" + getIDAsInteger() + "#" + command + "#" + payload + "##";
	}

	private int getIDAsInteger() {
		String str = "" + getId();
		int uid = str.hashCode();
		String filterStr = "" + uid;
		str = filterStr.replaceAll("-", "");
		return Integer.parseInt(str);
	}

}
