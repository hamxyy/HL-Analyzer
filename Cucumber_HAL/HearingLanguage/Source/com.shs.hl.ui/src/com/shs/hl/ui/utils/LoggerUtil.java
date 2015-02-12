package com.shs.hl.ui.utils;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;

import com.shs.hl.ui.internal.HearingLanguageActivator;
/**
 * 
 * @author z00345pb
 *
 */
public class LoggerUtil {

	public static final ILog	log			= HearingLanguageActivator.getInstance().getLog();
	private static final String	PLUGIN_ID	= "com.shs.hl.ui";

	public static void log(int severity, String message, Throwable throwable) {
		IStatus status = new Status(severity, PLUGIN_ID, message, throwable);
		log.log(status);
	}

	public static void log(int severity, String message) {
		IStatus status = new Status(severity, PLUGIN_ID, message);
		log.log(status);
	}

	public static MessageConsole findConsole(String name) {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		for (int i = 0; i < existing.length; i++)
			if (name.equals(existing[i].getName()))
				return (MessageConsole) existing[i];
		// no console found, so create a new one
		MessageConsole myConsole = new MessageConsole(name, null);
		conMan.addConsoles(new IConsole[] { myConsole });
		return myConsole;
	}
}
