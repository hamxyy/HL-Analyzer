package com.shs.hl.generator.halsa

public class HLSAStringBuilder {
	private val builder = new StringBuilder();
	private var indent = 0

	def append(String s) {
		builder.append(Indent + s);
	}

	def IncreaseIndent() {
		indent++;
	}

	def DecreaseIndent() {
		indent--;
		if (indent < 0)
			indent = 0;
	}

	private def Indent() {
		var b = new StringBuilder;
		for (var i = 0; i < indent; i++) {
			b.append("  ");
		}
		return b.toString
	}

	def override toString() {
		return builder.toString;
	}

}
