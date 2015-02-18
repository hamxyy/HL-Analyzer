package com.shs.hl.generator.halsa

public class HLSAStringBuilder
{
	private val builder = new StringBuilder();
	private var indent = 0

	def append(String s)
	{
		builder.append(Indent + s);
		return this
	}

	def space()
	{
		builder.append(Indent + " ");
		return this
	}

	def newline()
	{
		builder.append("\n");
		return this
	}

	def increaseIndent()
	{
		indent++;
		return this
	}

	def decreaseIndent()
	{
		indent--;
		if (indent < 0)
			indent = 0;
		return this
	}

	private def Indent()
	{
		var b = new StringBuilder;
		for (var i = 0; i < indent; i++)
		{
			b.append("  ");
		}
		return b.toString
	}

	def override toString()
	{
		return builder.toString;
	}

}
