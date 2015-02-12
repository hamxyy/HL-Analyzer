package com.shs.hl.ui.generator;

import java.util.HashSet;
import java.util.Set;

public class GraphvizSubgraph {
	private final String	name;
	private String			header;
	Set<String>				edges	= new HashSet<String>();

	public Set<String> getEdges() {
		return edges;
	}

	public void setEdges(final Set<String> edges) {
		this.edges = edges;
	}

	public GraphvizSubgraph(final String fName) {
		name = fName;
		setHeader();
	}

	private void setHeader() {
		header = "subgraph cluster" + name.replaceAll("\\.", "_") + "{ node [style=filled,color=white];" + "style=filled;" + "label=\"" + name + "\" ";

	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append(header + "\n");
		for (final String edge : getEdges()) {
			builder.append(edge + "\n");
		}
		builder.append("}");
		return builder.toString();
	}

}
