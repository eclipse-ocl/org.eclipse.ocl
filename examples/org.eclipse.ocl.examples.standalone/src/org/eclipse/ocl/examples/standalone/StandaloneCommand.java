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
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.URIConverter;
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

	public static @NonNull URIConverter getURIConverter() {
		URIConverter uriConverter = URIConverter.INSTANCE;
		if (!EcorePlugin.IS_ECLIPSE_RUNNING && uriConverter.getURIMap().isEmpty()) {
			EcorePlugin.ExtensionProcessor.process(null);
			uriConverter.getURIMap().putAll(EcorePlugin.computePlatformURIMap(true));
		}
		return uriConverter;
	}

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
		protected final @NonNull String name;
		protected final @NonNull String commandHelp;
		protected final @NonNull String argumentsHelp;
		protected boolean isRequired = false;

		protected CommandToken(@NonNull String name, @NonNull String commandHelp, @Nullable String argumentsHelp) {
			this.name = name;
			this.commandHelp = commandHelp;
			this.argumentsHelp = argumentsHelp;
		}

		public final boolean analyze(@NonNull StandaloneApplication standaloneApplication, @NonNull List<@NonNull String> strings) {
			for (@NonNull String string : strings) {
				String checkError = analyze(standaloneApplication, string);
				if (checkError != null) {
					logger.error(checkError);
					return false;
				}
			}
			return true;
		}

		/**
		 * Check that the string form a semantically consistent arguments for this token.
		 * Returns an error message for inconsistent 'enumerated' options.
		 */
		protected @Nullable String analyze(@NonNull StandaloneApplication standaloneApplication, @NonNull String string) {
			return null;
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

		public boolean isRequired() {
			return isRequired;
		}

		public boolean isSingleton() {
			return true;
		}

		/**
		 * Check that the strings form a semantically consistent sequence of arguments for this token.
		 */
		public boolean parseCheck(@NonNull List<@NonNull String> strings) {
			int size = strings.size();
			if (isRequired()) {
				if (size <= 0) {
					logger.error("Missing argument for '" + getName() + "'");
					return false;
				}

			}
			if (isSingleton()) {
				if (size > 1) {
					logger.error("Too many '" + getName() + "' tokens");
					return false;
				}

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

			public OutputToken() {
			super("-output", StandaloneMessages.StandaloneCommand_Output_Help, "<file-name>");
		}

		@Override
		public @Nullable String analyze(@NonNull StandaloneApplication standaloneApplication, @NonNull String string) {
			try {
				File file = new File(string).getCanonicalFile();
				if (file.exists()) {
					if (file.isFile()) {
						file.delete();
					} else {
						return StandaloneMessages.OCLArgumentAnalyzer_OutputFile + file.getAbsolutePath() + StandaloneMessages.OCLArgumentAnalyzer_NotFile;
					}
				}
				if (!file.exists()) {
					//					outputFilePath = new Path(file.getAbsolutePath());
					//					outputFile = file;
					File outputFolder = file.getParentFile();
					if (!outputFolder.exists()) {
						return StandaloneMessages.OCLArgumentAnalyzer_OutputDir + outputFolder.getAbsolutePath() + StandaloneMessages.OCLArgumentAnalyzer_NotExist;
					} else {
						outputFile = file;
					}
				}
			} catch (IOException e) {
				return e.getMessage();
			}
			return null;
		}

		public File getOutputFile() {
			return outputFile;
		}

		@Override
		public boolean isRequired() {
			return true;
		}
	}

	public static class BooleanToken extends CommandToken
	{
		private boolean isPresent = false;

		public BooleanToken(@NonNull String name, @NonNull String commandHelp) {
			super(name, commandHelp, null);
		}

		@Override
		protected @Nullable String analyze(@NonNull StandaloneApplication standaloneApplication, @NonNull String string) {
			isPresent = true;
			return null;
		}

		public boolean isPresent() {
			return isPresent;
		}
	}

	public static class StringToken extends CommandToken
	{
		public StringToken(@NonNull String name, @NonNull String commandHelp, @Nullable String argumentsHelp) {
			super(name, commandHelp, "<string-value>");
		}

	/*	@Override
		public int parseArgument(@NonNull List<@NonNull String> strings, @NonNull String @NonNull [] arguments, int i) {
			if (i < arguments.length){
				String argument = arguments[i++];
				strings.add(argument);
				return i;
			}
			else {
				logger.error("Missing argument for '" + name + "'");
				return -1;
			}
		} */
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
			if (!token.analyze(standaloneApplication, strings)) {
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
	public @NonNull Map<@NonNull CommandToken, @NonNull List<@NonNull String>> parse(@NonNull String @NonNull [] arguments) {
		Map<@NonNull CommandToken, @NonNull List<@NonNull String>> parsedTokens = new HashMap<>();
		List<@NonNull String> currentStrings = null;
		for (int i = 1; i < arguments.length;) {
			String argument = arguments[i++];
			CommandToken token = tokens.get(argument);
			if (token != null) {
				currentStrings = parsedTokens.get(token);
				if (currentStrings == null) {
					currentStrings = new ArrayList<>();
					parsedTokens.put(token, currentStrings);
				}
				else if (token.isSingleton()) {
					logger.error("Token '" + token.getName() + "' may only be used once");
					return null;
				}

			}
			else {
				if (currentStrings == null) {
					currentStrings = new ArrayList<>();
					parsedTokens.put(new CommandToken("", "", null) {}, currentStrings);
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
		for (@NonNull CommandToken token : token2strings.keySet()) {
			List<@NonNull String> strings = token2strings.get(token);
			if (!token.parseCheck(strings)) {
				return false;
			}
		}
	/*	for (CommandToken token : tokens.values()) {
			if (token.isRequired()) {
				if (!token2strings.containsKey(token)) {
					logger.error("Missing argument for '" + token.getName() + "'");
					return false;
				}
			}
		} */
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
}
