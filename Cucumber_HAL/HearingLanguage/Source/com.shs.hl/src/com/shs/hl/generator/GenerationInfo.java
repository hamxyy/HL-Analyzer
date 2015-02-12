package com.shs.hl.generator;

import java.util.ArrayList;

import org.osgi.framework.Version;



public class GenerationInfo
{

	// enum for easy platform mapping
	// for each supported plattform a mapping exists

	public static enum PlatformInformation
	{
		// D8 --> represents given platform naming used as extension for various
		// variables
		//
		// ("D8",8,-1,) represents platform information
		//
		// "D8" major or base platform in textual representation
		// --> used to select proper library (stdlib)
		// --> used for generating code
		// --> used to compile against provided dlls (fitting)
		//
		// 8 major platform number (has to be >0)
		// -1 minor platform number
		// (-1) indicates don't care
		// any number different to -1 indicates a specific version
		//
		// "Micron" --> Additional ShowingName

		UNKNOWN	("D8", 	  -1, -1, 	"UNKNOWN"),
		D8		("D8",	   8, -1, 	"Micon"),
		D9		("D9", 	   9, 0, 	"Sapphire"),
		D9Plus	("D9Plus", 9, 1, 	"n/A"),
		D10		("D10",   10, -1, 	"n/A");


		
		// major.minor.micro.qualifier.

		private final String	_PlfName;
		private final int		_MajorID;
		private final int		_MinorID;
		private final String	_ShowingName;

		PlatformInformation(String plfName, int ID, int subID, String showingName)
		{
			this._PlfName = plfName;
			this._MajorID = ID;
			this._MinorID = subID;
			this._ShowingName = showingName;
		}

		public String PlfName()
		{
			return _PlfName;
		}

		public int MajorID()
		{
			return _MajorID;
		}

		public int MinorID()
		{
			return _MinorID;
		}

		public String getShowingName()
		{
			return _ShowingName;
		}

		// Select a platform generation best matching given parameters
		// plf Desription should look like any of defined enum values
		// Version can be obmitted, when sepecified and the major number is 0
		// textual research is required
		public static PlatformInformation GetBestMatch(String plfDescription, Version versionSelector)
		{
			if (versionSelector == null || versionSelector.getMajor() == 0) { return GetBestMachtByText(plfDescription); }
			return getByBestVersion(versionSelector);
		}

		private static PlatformInformation GetBestMachtByText(String PlfDescription)
		{
			String tmp = PlfDescription.trim();
			// first we try plfName - exact match
			for (PlatformInformation info : PlatformInformation.values())
			{
				if (info == UNKNOWN) continue;
				if (info.PlfName().trim().toLowerCase().equals(tmp)) { return info; }
			}

			for (PlatformInformation info : PlatformInformation.values())
			{
				if (info == UNKNOWN) continue;

				String infoString = info.toString();
				if (tmp.toLowerCase().equals(infoString.toLowerCase())) { return info; }
			}

			return PlatformInformation.UNKNOWN;
		}

		private static PlatformInformation getByBestVersion(Version verSel)
		{
			int major = verSel.getMajor();
			int minor = verSel.getMinor();
			if (major == 0 || major < 0) return UNKNOWN;
		
    		java.util.List<PlatformInformation> matches = new ArrayList<PlatformInformation>();
			
			// first get all major numbers or of defaults
			for (PlatformInformation op : PlatformInformation.values())
			{
				if (op.MajorID()==major)
				{
					matches.add(op);
				}
			}

			if (matches.size()>0)
			{
				PlatformInformation generalMatch = UNKNOWN;
				
				
				for (PlatformInformation opMatch: matches)
				{
					if (minor==opMatch.MinorID()) return opMatch; // best match major+minor
					if (opMatch.MinorID()==-1)
					{
						if (generalMatch==UNKNOWN)
						{
							generalMatch=opMatch;
						}
					}
				}
				return generalMatch; // we use first general match
			}
			return UNKNOWN;
		}

	}

	public static enum ArithmeticKind
	{
			Plus		("+",	"Op.plus" ),
			Minus		("-",	"Op.minus"),
			Mult		("*",	"Op.mult" ),
			PlusEquals	("+=",	"Op.plus" ),
			MinusEquals	("-=", 	"Op.minus"),
			MultEquals	("*=",	"Op.mult" );
			
			
			
			private final String _symbol;
			private final String _operation;
			ArithmeticKind(String symb, String curveOperation)
			{
				_symbol=symb;
				_operation = curveOperation;
			}
			
			
			public String Symbol()
			{
				return _symbol;
			}
			
			
			public String CurveOperation()
			{
				return _operation;
			}
	}
	
	
}
