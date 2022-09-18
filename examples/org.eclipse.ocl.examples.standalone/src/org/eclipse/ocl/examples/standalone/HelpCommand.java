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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.standalone.messages.StandaloneMessages;

/**
 * The HelpCommand provides interactive help.
 */
public class HelpCommand extends StandaloneCommand
{
	protected static final class CommandComparator implements Comparator<StandaloneCommand>
	{
		public static final @NonNull CommandComparator INSTANCE = new CommandComparator();

		@Override
		public int compare(StandaloneCommand o1, StandaloneCommand o2) {
			String n1 = o1.getName();
			String n2 = o2.getName();
			return n1.compareTo(n2);
		}
	}

	protected static final class TokenComparator implements Comparator<StandaloneCommand.CommandToken>
	{
		public static final @NonNull TokenComparator INSTANCE = new TokenComparator();

		@Override
		public int compare(StandaloneCommand.CommandToken o1, StandaloneCommand.CommandToken o2) {
			String n1 = o1.getName();
			String n2 = o2.getName();
			return n1.compareTo(n2);
		}
	}

	private static final Logger logger = Logger.getLogger(HelpCommand.class);

	public HelpCommand(@NonNull StandaloneApplication standaloneApplication) {
		super(standaloneApplication, "help", StandaloneMessages.HelpCommand_Help);
	}

	@Override
	public @NonNull StandaloneResponse execute() {
		List<StandaloneCommand> commands = new ArrayList<StandaloneCommand>(standaloneApplication.getCommands());
		Collections.sort(commands, CommandComparator.INSTANCE);
		StringBuilder s = new StringBuilder();
		s.append(StandaloneMessages.Standalone_Help);
		s.append("\n");
		for (StandaloneCommand command : commands) {
			s.append("\nocl ");
			s.append(command.getName());
			List<StandaloneCommand.CommandToken> tokens = new ArrayList<StandaloneCommand.CommandToken>(command.getTokens());
			Collections.sort(tokens, TokenComparator.INSTANCE);
			for (StandaloneCommand.CommandToken token : tokens) {
				s.append(" ");
				if (!token.isSingleton()) {
					s.append("(");
				}
				if (!token.isRequired()) {
					s.append("[");
				}
				s.append(token.getName());
				String argsHelp = token.getArgumentsHelp();
				if (argsHelp != null) {
					s.append(" ");
					s.append(argsHelp);
					int max = token.getMaxArguments();
					int min = token.getMinArguments();
					if (min == 0) {
						if (max == 1) {
							s.append("?");
						}
						else if (max < 0) {
							s.append("*");
						}
						else {
							s.append("[" + max + "]");
						}
					}
					else if (min == 1) {
						if (max == 1) {
						}
						else if (max < 0) {
							s.append("+");
						}
						else {
							s.append("[1.." + max + "]");
						}
					}
					else {
						if (max < 0) {
							s.append("[" + min + "..*]");
						}
						else {
							s.append("[" + min + ".." + max + "]");
						}
					}
				}
				if (!token.isRequired()) {
					s.append("]");
				}
				if (!token.isSingleton()) {
					s.append(")*");
				}
			}
			s.append("\n  ");
			s.append(command.getHelp());
			for (StandaloneCommand.CommandToken token : tokens) {
				s.append("\n    ");
				s.append(token.getName());
				s.append(": ");
				s.append(token.isRequired() ? StandaloneMessages.HelpText_Required : StandaloneMessages.HelpText_Optional);
				if (!token.isSingleton()) {
					s.append(" " + StandaloneMessages.HelpText_repeatable);
				}
				s.append(" " + StandaloneMessages.HelpText_token);
				s.append(". ");
				s.append(token.getCommandHelp().replace("\n",  "\n        "));
			}
			s.append("\n");
		}
		try {
			DEFAULT_OUTPUT_STREAM.append(s.toString());
			return StandaloneResponse.OK;
		} catch (IOException e) {}
		return StandaloneResponse.FAIL;
	}

	@Override
	public boolean parseCheck(@NonNull Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings) {
		if (token2strings.size() > 0) {
			logger.error(StandaloneMessages.HelpCommand_Bad);
			return false;
		}
		return super.parseCheck(token2strings);
	}
}
