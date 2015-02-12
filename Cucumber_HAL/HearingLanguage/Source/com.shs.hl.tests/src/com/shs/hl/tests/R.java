package com.shs.hl.tests;

public class R {

	
	public static String modelroot = "../com.shs.hl.tests.models/models";
	public static String paramroot = "../com.shs.hl.tests.models/stdlib/params";
	public static String builtins =  "../com.shs.hl.tests.models/stdlib/stdlib/builtins.hl";
	
	
	public static String[] allParameterFiles = 
	{
		paramroot+"/cap.cl",
		paramroot+"/crv.cl",
		paramroot+"/env.cl",
		paramroot+"/fit.cl",
		paramroot+"/hiHighLevel.cl",
		paramroot+"/hiLLD8.cl",
		paramroot+"/hiLLF8.cl",
		paramroot+"/hiLLFW.cl",
		paramroot+"/hiSwCombPar.cl",
		paramroot+"/hiSWHIPar.cl",
		paramroot+"/lvlcrv.cl",
		paramroot+"/pat.cl",
		paramroot+"/D8.plf.cl",
		paramroot+"/std.cl"
	};

	public static String[] allParameterFilesAndBuiltIns =
	{
		paramroot+"/cap.cl",
		paramroot+"/crv.cl",
		paramroot+"/env.cl",
		paramroot+"/fit.cl",
		paramroot+"/hiHighLevel.cl",
		paramroot+"/hiLLD8.cl",
		paramroot+"/hiLLF8.cl",
		paramroot+"/hiLLFW.cl",
		paramroot+"/hiSwCombPar.cl",
		paramroot+"/hiSWHIPar.cl",
		paramroot+"/lvlcrv.cl",
		paramroot+"/pat.cl",
		paramroot+"/std.cl",
		builtins
	};

	
}
