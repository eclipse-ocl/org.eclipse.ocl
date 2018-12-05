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
			uriConverter.getURIMap().putAll(EcorePlugin.computePlatformURIMap(false));
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
		protected final @NonNull String help;
		protected boolean isRequired = false;

		protected CommandToken(@NonNull String name, @NonNull String help) {
			this.name = name;
			this.help = help;
		}

		public boolean check(@NonNull List<String> strings) {
			return true;
		}

		public @Nullable String getArgsHelp() {
			return null;
		}

		public @NonNull String getHelp() {
			return help;
		}

		public @NonNull String getName() {
			return name;
		}

		public boolean isRequired() {
			return isRequired;
		}

		public int parseArgument(@NonNull List<String> strings, @NonNull String @NonNull [] arguments, int i) {
			return i;
		}

		public void setIsRequired() {
			isRequired = true;
		}

		public boolean isSingleton() {
			return true;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public static class StringToken extends CommandToken
	{
		protected StringToken(@NonNull String name, @NonNull String help) {
			super(name, help);
		}

		@Override
		public boolean check(@NonNull List<String> strings) {
			return strings.size() == 1;
		}

		@Override
		public @Nullable String getArgsHelp() {
			return "<string-value>";
		}

		@Override
		public int parseArgument(@NonNull List<String> strings, @NonNull String @NonNull [] arguments, int i) {
			if (i < arguments.length){
				String argument = arguments[i++];
				strings.add(argument);
				return i;
			}
			else {
				logger.error("Missing argument for '" + name + "'");
				return -1;
			}
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

	public boolean check(@NonNull Map<CommandToken, List<String>> token2strings) {
		for (CommandToken token : token2strings.keySet()) {
			List<String> strings = token2strings.get(token);
			if ((token != null) && !token.check(strings)) {
				return false;
			}
		}
		for (CommandToken token : tokens.values()) {
			if (token.isRequired()) {
				if (!token2strings.containsKey(token)) {
					logger.error("Missing mandatory token '" + token.getName() + "'");
					return false;
				}
			}
		}
		return true;
	}

	public abstract @NonNull StandaloneResponse execute(@NonNull Map<CommandToken, List<String>> tokens);

	public @NonNull String getHelp() {
		return help;
	}

	public @NonNull String getName() {
		return name;
	}

	public @NonNull Collection<CommandToken> getTokens() {
		return tokens.values();
	}

	public @Nullable Map<CommandToken, List<String>> parse(@NonNull String @NonNull [] arguments) {
		Map<CommandToken, List<String>> parsedTokens = new HashMap<CommandToken, List<String>>();
		for (int i = 1; i < arguments.length;) {
			String argument = arguments[i++];
			CommandToken token = tokens.get(argument);
			List<String> strings = parsedTokens.get(token);
			if (strings == null) {
				strings = new ArrayList<String>();
				parsedTokens.put(token, strings);
			}
			else if (token.isSingleton()) {
				logger.error("Token '" + token.getName() + "' may only be used once");
				return null;
			}
			if (token == null) {
				strings.add(argument);
			}
			else {
				i = token.parseArgument(strings, arguments, i);
				if (i < 0) {
					return null;
				}
			}
		}
		return parsedTokens;
	}

	@Override
	public String toString() {
		return name;
	}
}
