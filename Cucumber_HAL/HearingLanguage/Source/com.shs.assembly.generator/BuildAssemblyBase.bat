@echo off
REM This batch compiles all *.cs files of a Macro folder with the given parameters:


REM  AVAILABLE VARIABLES in project file
REM  ------------------------------------------------------------------------------------------------------------------
REM  VARIABLE			    CMD  OPT DEFAULT 								Description
REM  ------------------------------------------------------------------------------------------------------------------
REM /P:MacroLibrary			=%1 											The name of the resulting assembly without ".dll"
REM /P:GenerationPath		=%2												Path to the generated cs-files including the src-gen folder
REM /P:Configuration		=%3 											The project configuration to be built (Debug/Release)
REM /P:HAPlatform			=%4 											The HearingAidPlatform (e.g. D8, D9)
REM /P:ReferenceDirectory	=%5		$(ProgramFiles)\SAT\Fitting				Path to the Fitting dlls
REM /P:VendorOutputBase		=%6	 	SI
REM /P:OutputDBDir				 x	"HiDB"
REM /P:OutputBasePath			 x	[OutputBaseDir] = ($ReferenceDirectory) 			Root node where the the HIDB folder resides
REM /P:OutputRoot				 x	$(OutputBaseDir)\$(OutputDBDIR)						--> e.g \SAT\Fitting\HIDB
REM /P:OutputPathOverwrite 	=%7	 x	[OutputPath] = $(OutputRoot)\$(VendorOutputBase)	--> e.g \SAT\Fitting\HIDB\SI
	
REM /P:ReferencePath			 x	$(ReferenceDirectory)
REM /P:GenerationPath			 x	.


REM In general to specify a fully self-defined output folder OutputPathOverwrite has to be set to the full specified output path
REM Elsewise the path is used as defined by
REM OutputRoot = $(OutputBaseDir)\(OutputDBDIR)		--> e.g. SAT\Fitting\HIDB\
REM OutputPath = $(OutputRoot)\(VendorOutputBase)		e.g. SAT\Fitting\HIDB\SI
REM In general all Resulting assemblies should be located under their own Vendor-Specific output folder



@echo on

%SYSTEMROOT%\Microsoft.NET\Framework\v4.0.30319\MSBuild /T:Rebuild /v:n /P:Testing=NoTest /P:MacroLibrary=%1 /P:GenerationPath=%2 /P:Configuration=%3 /P:HAPlatform=%4  /P:ReferenceDirectory=%5 /P:VendorOutputBase=%6  /P:OutputPathOverwrite=%7 HearingLanguageRuntime.ProjectTemplate%4.csproj










