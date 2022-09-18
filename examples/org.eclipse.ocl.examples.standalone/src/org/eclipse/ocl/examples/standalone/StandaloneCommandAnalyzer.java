/*******************************************************************************
 * Copyright (c) 2014, 2018 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.standalone;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.standalone.validity.ValidateCommand;

/**
 * This class executes an OCL evaluation of a model with one or several OCL
 * file(s). This class is intended to be used only in Standalone mode. The
 * result may be saved in a XMI file or exported as a HTML report.<br>
 *
 * @noextend This class is not intended to be subclassed by clients.
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class StandaloneCommandAnalyzer
{
	private static final Logger logger = Logger.getLogger(StandaloneCommandAnalyzer.class);

	private final @NonNull Map<String, StandaloneCommand> commands = new HashMap<String, StandaloneCommand>();

	public StandaloneCommandAnalyzer(@NonNull StandaloneApplication standaloneApplication) {
		addCommand(new HelpCommand(standaloneApplication));
		addCommand(new ExecuteCommand(standaloneApplication));
		addCommand(new ValidateCommand(standaloneApplication));
	}

	protected void addCommand(@NonNull StandaloneCommand command) {
		commands.put(command.getName(), command);
	}

	public @NonNull Collection<StandaloneCommand> getCommands() {
		return commands.values();
	}

	/**
	 * This launch the application using the entered arguments.
	 *
	 * @param args
	 *            the application arguments.
	 * @return the application return code.
	 */
	public @Nullable StandaloneCommand parse(@NonNull String @NonNull [] args) {
		if (args.length <= 0) {
			logger.error("Missing command keyword");
			return null;
		}
		StandaloneCommand command = commands.get(args[0]);
		if (command == null) {
			logger.error("Unknown command keyword '" + args[0] + "'");
			return null;
		}
		return command;
	}
}
