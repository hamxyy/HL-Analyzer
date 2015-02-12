SET eclipseDir="C:\Program Files\Eclipse4.3.0.0"

SET eclipseLauncher=%eclipseDir%\plugins\org.eclipse.equinox.launcher_1.3.0.v20130327-1440.jar

java -jar %eclipseLauncher% -application org.eclipse.ant.core.antRunner -buildfile BuildAndTest.xml