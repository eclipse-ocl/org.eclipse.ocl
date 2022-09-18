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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.standalone.messages.StandaloneMessages;

/**
 * A representation of the literals of the enumeration '<em><b>StandaloneResponse</b></em>',
 * and utility methods for working with them.
 */
public abstract class StandaloneCommand
{
	private static final Logger logger = Logger.getLogger(StandaloneCommand.class);
	protected static @NonNull Appendable DEFAULT_OUTPUT_STREAM = System.out;

	/**
	 * Redirect the default stdout clutter for test purposes.
	 */
	public static @NonNull Appendable setDefaultOutputStream(@NonNull Appendable defaultOutputStream) {
		Appendable savedDefaultOutputStream = defaultOutputStream;
		DEFAULT_OUTPUT_STREAM = defaultOutputStream;
		return savedDefaultOutputStream;
	}

	public static abstract class CommandToken
	{
		protected final @NonNull StandaloneApplication standaloneApplication;
		protected final @NonNull String name;
		protected final @NonNull String commandHelp;
		protected final @NonNull String argumentsHelp;
		protected boolean isRequired = false;

		protected CommandToken(@NonNull StandaloneApplication standaloneApplication, @NonNull String name, @NonNull String commandHelp, @Nullable String argumentsHelp) {
			this.standaloneApplication = standaloneApplication;
			this.name = name;
			this.commandHelp = commandHelp;
			this.argumentsHelp = argumentsHelp;
		}

		public final boolean analyze(@NonNull List<@NonNull String> strings) {
			if (strings.size() > 0) {
				for (@NonNull String string : strings) {
					if (!analyze(string)) {
						return false;
					}
				}
			}
			else {
				if (!analyze((String)null)) {
					return false;
				}
			}
			return true;
		}

		/**
		 * Check that the string form a semantically consistent arguments for this token.
		 * The string may be null to analyze the no-arguments for this token.
		 * Returns true if ok, of false after logging an error message.
		 */
		protected boolean analyze(@Nullable String string) {
			return true;
		}

		public @Nullable String getArgumentsHelp() {
			return argumentsHelp;
		}

		public @NonNull String getCommandHelp() {
			return commandHelp;
		}

		public @NonNull String getName() {
			return name;
		}

		/**
		 * Return the maximum number of arguments; -ve for unlimited.
		 */
		public int getMaxArguments() {
			return -1;
		}

		/**
		 * Return the minimum number of arguments.
		 */
		public int getMinArguments() {
			return 0;
		}

		/**
		 * Return true if this token must be present in the command.
		 */
		public boolean isRequired() {
			return isRequired;
		}

		/**
		 * Return true if this token may only be used once per command.
		 */
		public boolean isSingleton() {
			return true;
		}

		/**
		 * Check that the strings form a semantically consistent sequence of arguments for this token.
		 */
		public boolean parseCheck(@NonNull List<@NonNull String> strings) {
			int size = strings.size();
			if (size < getMinArguments()) {
				logger.error("Missing argument for '" + getName() + "'");
				return false;
			}
			int maxArguments = getMaxArguments();
			if ((maxArguments >= 0) && (size > maxArguments)) {
				logger.error("Too many '" + getName() + "' tokens");
				return false;
			}
			for (@NonNull String string : strings) {
				if (!parseCheck(string)) {
					return false;
				}
			}
			return true;
		}

		/**
		 * Check that the string form a semantically consistent arguments for this token.
		 * Returns true,if ok, or false after logging an error message for inconsistent 'enumerated' options.
		 */
		protected boolean parseCheck(@NonNull String string) {
			return true;
		}

		public void setIsRequired() {
			isRequired = true;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	/**
	 * An optional argument to define the output file path. The exporter will
	 * create results within that target file.
	 */
	public static class OutputToken extends StringToken
	{
		private @Nullable File outputFile;

			public OutputToken(@NonNull StandaloneApplication standaloneApplication) {
			super(standaloneApplication, "-output", StandaloneMessages.StandaloneCommand_Output_Help, "<file-name>");
		}

		@Override
		public boolean analyze(@Nullable String string) {
			try {
				File file = new File(string).getCanonicalFile();
				if (file.exists()) {
					if (file.isFile()) {
						file.delete();
					} else {
						logger.error(StandaloneMessages.OCLArgumentAnalyzer_OutputFile + file.getAbsolutePath() + StandaloneMessages.OCLArgumentAnalyzer_NotFile);
						return false;
					}
				}
				if (!file.exists()) {
					//					outputFilePath = new Path(file.getAbsolutePath());
					//					outputFile = file;
					File outputFolder = file.getParentFile();
					if (!outputFolder.exists()) {
						logger.error(StandaloneMessages.OCLArgumentAnalyzer_OutputDir + outputFolder.getAbsolutePath() + StandaloneMessages.OCLArgumentAnalyzer_NotExist);
						return false;
					} else {
						outputFile = file;
					}
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				return false;
			}
			return true;
		}

		public File getOutputFile() {
			return outputFile;
		}
	}

	public static class BooleanToken extends CommandToken
	{
		private boolean isPresent = false;

		public BooleanToken(@NonNull StandaloneApplication standaloneApplication, @NonNull String name, @NonNull String commandHelp) {
			super(standaloneApplication, name, commandHelp, null);
		}

		@Override
		protected boolean analyze(@Nullable String string) {
			isPresent = true;
			return true;
		}

		@Override
		public int getMaxArguments() {
			return 0;
		}

		public boolean isPresent() {
			return isPresent;
		}
	}

	public static class StringToken extends CommandToken
	{
		public StringToken(@NonNull StandaloneApplication standaloneApplication, @NonNull String name, @NonNull String commandHelp, @Nullable String argumentsHelp) {
			super(standaloneApplication, name, commandHelp, argumentsHelp);
		}

		@Override
		public int getMaxArguments() {
			return 1;
		}

		@Override
		public int getMinArguments() {
			return 1;
		}

		@Override
		public boolean isSingleton() {
			return true;
		}
	}

	protected final @NonNull StandaloneApplication standaloneApplication;
	protected final @NonNull String name;
	protected final @NonNull String help;
	protected final @NonNull Map<String, CommandToken> tokens = new HashMap<String, CommandToken>();

	protected StandaloneCommand(@NonNull StandaloneApplication standaloneApplication, @NonNull String name, @NonNull String help) {
		this.standaloneApplication = standaloneApplication;
		this.name = name;
		this.help = help;
	}

	protected void addToken(@NonNull CommandToken commandToken) {
		tokens.put(commandToken.getName(), commandToken);
	}

	/**
	 * Analyze each keyword tpken establishing functional correctness such as file existence and caching
	 * and analyzed results. Returns true if ok to execute, else false after logging any errors or warnings.
	 */
	public boolean analyze(@NonNull Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings) {
		for (@NonNull CommandToken token : token2strings.keySet()) {
			List<@NonNull String> strings = token2strings.get(token);
			if (!token.analyze(strings)) {
				return false;
			}
		}
		return true;
	}

	public abstract @NonNull StandaloneResponse execute() throws IOException;

	public @NonNull String getHelp() {
		return help;
	}

	public @NonNull String getName() {
		return name;
	}

	public @NonNull Collection<@NonNull CommandToken> getTokens() {
		return tokens.values();
	}

	/**
	 * Parse the arguments by distinguishing keyword tokens from their suffix tokens and populating the
	 * CommandToken to String map accordingly. No checking or validation is performed.
	 */
	public @Nullable Map<@NonNull CommandToken, @NonNull List<@NonNull String>> parse(@NonNull String @NonNull [] arguments) {
		Map<@NonNull CommandToken, @NonNull List<@NonNull String>> parsedTokens = new HashMap<>();
		CommandToken nullToken = null;
		CommandToken currentToken = null;
		List<@NonNull String> currentStrings = null;
		for (int i = 1; i < arguments.length;) {
			String argument = arguments[i++];
			CommandToken token = tokens.get(argument);
			if (token != null) {
				currentToken = token;
				currentStrings = parsedTokens.get(currentToken);
				if (currentStrings == null) {
					currentStrings = new ArrayList<>();
					parsedTokens.put(currentToken, currentStrings);
				}
				else if (currentToken.isSingleton()) {
					logger.error("Token '" + token.getName() + "' may only be used once");
					return null;
				}
			}
			else {
				if ((currentToken != null) && (currentStrings != null)) {
					int maxArguments = currentToken.getMaxArguments();
					if ((maxArguments >= 0) && (currentStrings.size() >= maxArguments)) {
						currentToken = null;
						currentStrings = null;
					}
				}
				if (currentStrings == null) {
					if (nullToken == null) {
						nullToken = new CommandToken(standaloneApplication, "", "", null) {};
					}
					currentStrings = parsedTokens.get(nullToken);
					if (currentStrings == null) {
						currentStrings = new ArrayList<>();
						parsedTokens.put(nullToken, currentStrings);
					}
				}
				currentStrings.add(argument);
			}
		}
		return parsedTokens;
	}

	/**
	 * Check the semantic consistency, logging errors for any missing arguments, and returning false
	 * if unsatisfactory. No functional validation such as file existence/readability is performed.
	 */
	public boolean parseCheck(@NonNull Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings) {
		Set<@NonNull CommandToken> validTokens = new HashSet<>(tokens.values());
		for (@NonNull CommandToken token : token2strings.keySet()) {
			List<@NonNull String> strings = token2strings.get(token);
			if (!validTokens.contains(token)) {
				logger.error("Misplaced arguments '" + strings + "'");
				return false;
			}
			if (!token.parseCheck(strings)) {
				return false;
			}
		}
		for (CommandToken token : validTokens) {
			if (token.isRequired() && !token2strings.containsKey(token)) {
				logger.error("Missing token '" + token.getName() + "'");
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
}
