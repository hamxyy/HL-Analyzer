SET eclipseDir="D:\eclipse"

SET eclipseLauncher=%eclipseDir%\plugins\org.eclipse.equinox.launcher_1.3.0.v20140415-2008.jar

java -jar %eclipseLauncher% -application org.eclipse.ant.core.antRunner -buildfile build-shs.xml