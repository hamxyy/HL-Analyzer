SET eclipseDir=..\

SET eclipseLauncher=%eclipseDir%\plugins\org.eclipse.equinox.launcher_1.3.0.v20130327-1440.jar

java -jar %eclipseLauncher% -application org.eclipse.ant.core.antRunner -buildfile GenerateCSharp.xml -propertyfile GenerateCSharp.properties %*