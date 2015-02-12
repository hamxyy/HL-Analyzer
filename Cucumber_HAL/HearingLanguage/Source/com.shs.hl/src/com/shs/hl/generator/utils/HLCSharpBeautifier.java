package com.shs.hl.generator.utils;



public class HLCSharpBeautifier
{	
	public String format(CharSequence code)
	{
		if (code == null) return "";
		String str = code.toString();
		
		if (str==null) return "";
		
		String[] csLines = code.toString().split("\r\n");
		int codeShifting = 0;
		Boolean isNexLine = true;

		String csText = "";

		for (String csLine : csLines)
		{
			csLine = csLine.trim();
			csLine = csLine.replaceAll("\t", "");
			csLine = csLine.replace("  ", " ");
			csLine = csLine.replace("  ", " ");
			if (!csLine.isEmpty())
			{
				if (csLine.startsWith("}"))
				{
					codeShifting--;
				}
				if (isNexLine)
				{
					for (int i = 0; i < codeShifting; i++)
						csText += "\t";
				}
				else
				{
					if (csLine.startsWith("=")
							|| !(csText.endsWith("[") || csText.endsWith("]")
									|| csText.endsWith(".")
									|| csLine.startsWith("[")
									|| csLine.startsWith("]")
									|| csLine.startsWith(".") || csLine
										.equals(";")))
					{
						csLine = " " + csLine;
					}
					isNexLine = true;
				}
				if (csLine.startsWith("//") || csLine.startsWith("/*")
						|| csLine.startsWith("#")
						|| csLine.startsWith("[TestMethod]")
						|| csLine.endsWith("}") || csLine.endsWith(";"))
				{
					csText += csLine;
					csText += "\r\n";
				}
				else if (csLine.endsWith("{"))
				{
					codeShifting++;
					csText += csLine;
					csText += "\r\n";
				}
				else
				{
					csText += csLine;
					isNexLine = false;
				}
			} // if csLine.isEmpty
		}
		return csText;
	}
}
