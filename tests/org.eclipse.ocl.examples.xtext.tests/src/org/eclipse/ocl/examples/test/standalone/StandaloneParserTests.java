/*******************************************************************************
 * Copyright (c) 2014, 2025 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.standalone;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Appender;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.emf.validation.validity.export.HTMLExporter;
import org.eclipse.ocl.emf.validation.validity.export.TextExporter;
import org.eclipse.ocl.examples.standalone.HelpCommand;
import org.eclipse.ocl.examples.standalone.StandaloneApplication;
import org.eclipse.ocl.examples.standalone.StandaloneCommand;
import org.eclipse.ocl.examples.standalone.StandaloneCommand.CommandToken;
import org.eclipse.ocl.examples.standalone.StandaloneCommandAnalyzer;
import org.eclipse.ocl.examples.standalone.validity.ValidateCommand;
import org.eclipse.ocl.examples.xtext.tests.TestCaseLogger;
import org.junit.Test;

import com.google.common.collect.Lists;

public class StandaloneParserTests extends StandaloneTestCase
{
	protected static void assertCommandInvalid(@NonNull StandaloneCommand command, @NonNull Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings) {
		boolean status = command.analyze(token2strings);
		assertFalse(status);
	}

	protected static void assertCommandValid(@NonNull StandaloneCommand command, @NonNull Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings) {
		boolean status = command.analyze(token2strings);
		assertTrue(status);
	}

	protected List<String> normalize(List<String> strings) throws IOException {
		List<String> normalized = new ArrayList<String>(strings.size());
		for (String string : strings) {
			normalized.add(normalize(string));
		}
		return normalized;
	}

	protected String normalize(String string) throws IOException {
		try {
			return new File(string).getCanonicalPath();
		}
		catch (Exception e) {
			return string;
		}
	}

	@SuppressWarnings("unchecked")
	protected @NonNull <T extends StandaloneCommand> T parseCommand(@NonNull StandaloneApplication standaloneApplication, @NonNull Class<@NonNull T> commandClass, @NonNull String @NonNull [] arguments) {
		StandaloneCommandAnalyzer commandAnalyzer = new StandaloneCommandAnalyzer(standaloneApplication);
		StandaloneCommand command = commandAnalyzer.parse(arguments);
		assert command != null;
		assertEquals(commandClass, command.getClass());
		return (T) command;
	}

	protected void parseInvalidArguments(@NonNull StandaloneCommand command, @NonNull String @NonNull [] arguments) {
		Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings = command.parse(arguments);
		assertFalse(command.parseCheck(token2strings));
	}

	protected @NonNull Map<@NonNull CommandToken, @NonNull List<@NonNull String>> parseValidArguments(@NonNull StandaloneCommand command, @NonNull String @NonNull [] arguments) {
		Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings = command.parse(arguments);
		assertTrue(command.parseCheck(token2strings));
		assert token2strings != null;
		return token2strings;
	}

	@Test
	public void testStandaloneParser_help() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"help"};
		StandaloneApplication standaloneApplication = new StandaloneApplication();
		HelpCommand command = parseCommand(standaloneApplication, HelpCommand.class, arguments);
		Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings = parseValidArguments(command, arguments);
		assertEquals(0, token2strings.size());
		standaloneApplication.stop();
	}

	@Test
	public void testStandaloneParser_help_extraText() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"help", "yy"};
			StandaloneApplication standaloneApplication = new StandaloneApplication();
			HelpCommand command = parseCommand(standaloneApplication, HelpCommand.class, arguments);
			parseInvalidArguments(command, arguments);
			String logMessage = TestCaseLogger.INSTANCE.get();
			assertTrue(logMessage.contains("Bad 'help' command"));
			standaloneApplication.stop();
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void testStandaloneParser_mandatoryArguments() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI)};
			StandaloneApplication standaloneApplication = new StandaloneApplication();
			ValidateCommand command = parseCommand(standaloneApplication, ValidateCommand.class, arguments);
			Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertEquals(null, command.exporterToken.getExporter());
			assertEquals(String.valueOf(inputModelURI), command.modelToken.getModelFileName());
			assertEquals(null, command.outputToken.getOutputFile());
			assertEquals(normalize(Lists.newArrayList(String.valueOf(inputOCLURI))), normalize(command.rulesToken.getOCLFileNames()));
			assertEquals(true, command.usingToken.doRunJavaConstraints());
			assertEquals(true, command.usingToken.doRunOCLConstraints());
			assertEquals(true, command.usingToken.doRunUMLConstraints());
			standaloneApplication.stop();
	}

	@Test
	public void testStandaloneParser_missingOutputArgument() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(inputOCLURI),
				"-output"
			};
			StandaloneApplication standaloneApplication = new StandaloneApplication();
			ValidateCommand command = parseCommand(standaloneApplication, ValidateCommand.class, arguments);
			parseInvalidArguments(command, arguments);
			String logMessage = TestCaseLogger.INSTANCE.get();
			assertTrue(logMessage.contains("Missing argument for"));
			assertTrue(logMessage.contains("-output"));
			standaloneApplication.stop();
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void testStandaloneParser_missingExporterArgument() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(inputOCLURI),
				"-exporter"
			};
			StandaloneApplication standaloneApplication = new StandaloneApplication();
			ValidateCommand command = parseCommand(standaloneApplication, ValidateCommand.class, arguments);
			parseInvalidArguments(command, arguments);
			String logMessage = TestCaseLogger.INSTANCE.get();
			assertTrue(logMessage.contains("Missing argument for"));
			assertTrue(logMessage.contains("-exporter"));
			standaloneApplication.stop();
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void testStandaloneParser_missingUsingArgument() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(inputOCLURI),
				"-using"
			};
			StandaloneApplication standaloneApplication = new StandaloneApplication();
			ValidateCommand command = parseCommand(standaloneApplication, ValidateCommand.class, arguments);
			parseInvalidArguments(command, arguments);
			String logMessage = TestCaseLogger.INSTANCE.get();
			assertTrue(logMessage.contains("Missing argument for"));
			assertTrue(logMessage.contains("-using"));
			standaloneApplication.stop();
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void testStandaloneParser_textExportedFile() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE};
			StandaloneApplication standaloneApplication = new StandaloneApplication();
			ValidateCommand command = parseCommand(standaloneApplication, ValidateCommand.class, arguments);
			Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertTrue(command.exporterToken.getExporter() instanceof TextExporter);
			assertEquals(String.valueOf(inputModelURI), command.modelToken.getModelFileName());
			assertEquals(normalize(getTextLogFileName()), normalize(command.outputToken.getOutputFile().toString()));
			assertEquals(normalize(Lists.newArrayList(String.valueOf(inputOCLURI))), Lists.newArrayList(normalize(command.rulesToken.getOCLFileNames().get(0))));
			assertEquals(true, command.usingToken.doRunJavaConstraints());
			assertEquals(true, command.usingToken.doRunOCLConstraints());
			assertEquals(true, command.usingToken.doRunUMLConstraints());
			standaloneApplication.stop();
	}

	@Test
	public void testStandaloneParser_htmlExportedFile() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getHTMLLogFileName(),
			"-exporter", HTMLExporter.EXPORTER_TYPE};
			StandaloneApplication standaloneApplication = new StandaloneApplication();
			ValidateCommand command = parseCommand(standaloneApplication, ValidateCommand.class, arguments);
			Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertTrue(command.exporterToken.getExporter() instanceof HTMLExporter);
			assertEquals(String.valueOf(inputModelURI), command.modelToken.getModelFileName());
			assertEquals(normalize(getHTMLLogFileName()), normalize(command.outputToken.getOutputFile().toString()));
			assertEquals(normalize(Lists.newArrayList(String.valueOf(inputOCLURI))), normalize(command.rulesToken.getOCLFileNames()));
			assertEquals(true, command.usingToken.doRunJavaConstraints());
			assertEquals(true, command.usingToken.doRunOCLConstraints());
			assertEquals(true, command.usingToken.doRunUMLConstraints());
			standaloneApplication.stop();
	}

	@Test
	public void testStandaloneParser_unknownExporter() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(inputOCLURI),
				"-output", getTextLogFileName(),
				"-exporter", "anotherExporterAttribute"};
				StandaloneApplication standaloneApplication = new StandaloneApplication();
			ValidateCommand command = parseCommand(standaloneApplication, ValidateCommand.class, arguments);
			parseInvalidArguments(command, arguments);
			String logMessage = TestCaseLogger.INSTANCE.get();
			assertTrue(logMessage.contains("Unrecognized 'exporter' anotherExporterAttribute"));
			standaloneApplication.stop();
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void testStandaloneParser_nonExistentModel() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(getTestModelURI("models/nonExistent.ecore")),
				"-rules", String.valueOf(inputOCLURI),
				"-output", getTextLogFileName(),
				"-exporter", TextExporter.EXPORTER_TYPE};
				StandaloneApplication standaloneApplication = new StandaloneApplication();
				ValidateCommand command = parseCommand(standaloneApplication, ValidateCommand.class, arguments);
				Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings = parseValidArguments(command, arguments);
				assertCommandInvalid(command, token2strings);
				String logMessage = TestCaseLogger.INSTANCE.get();
				assertTrue(logMessage.contains("does not exist"));
				standaloneApplication.stop();
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void testStandaloneParser_nonExistentOclFile() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(getTestModelURI("models/nonExistent.ocl")),
				"-output", getTextLogFileName(),
				"-exporter", TextExporter.EXPORTER_TYPE};
				StandaloneApplication standaloneApplication = new StandaloneApplication();
				ValidateCommand command = parseCommand(standaloneApplication, ValidateCommand.class, arguments);
				Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings = parseValidArguments(command, arguments);
				assertCommandValid(command, token2strings);			// missing file is ignored
				String logMessage = TestCaseLogger.INSTANCE.get();
				assertTrue(logMessage.contains("does not exist"));
				assertTrue(logMessage.contains("ignored"));
				standaloneApplication.stop();
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void testStandaloneParser_nonExistentOutputFolder() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(inputOCLURI),
				"-output", "nonExistentFolder/log.file",
				"-exporter", TextExporter.EXPORTER_TYPE};
				StandaloneApplication standaloneApplication = new StandaloneApplication();
				ValidateCommand command = parseCommand(standaloneApplication, ValidateCommand.class, arguments);
				Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings = parseValidArguments(command, arguments);
				assertCommandInvalid(command, token2strings);
				String logMessage = TestCaseLogger.INSTANCE.get();
				assertTrue(logMessage.contains("does not exist"));
				standaloneApplication.stop();
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void testStandaloneParser_textOCLFiles() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(textInputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE};
			StandaloneApplication standaloneApplication = new StandaloneApplication();
			ValidateCommand command = parseCommand(standaloneApplication, ValidateCommand.class, arguments);
			Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertTrue(command.exporterToken.getExporter() instanceof TextExporter);
			assertEquals(String.valueOf(inputModelURI), command.modelToken.getModelFileName());
			assertEquals(normalize(getTextLogFileName()), normalize(command.outputToken.getOutputFile().toString()));
			assertEquals(normalize(Lists.newArrayList(String.valueOf(inputOCLURI), String.valueOf(inputOCLURI2))), normalize(command.rulesToken.getOCLFileNames()));
			assertEquals(true, command.usingToken.doRunJavaConstraints());
			assertEquals(true, command.usingToken.doRunOCLConstraints());
			assertEquals(true, command.usingToken.doRunUMLConstraints());
			standaloneApplication.stop();
	}

	@Test
	public void testStandaloneParser_usingAllLocators() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE,
			"-using", "all"};
			StandaloneApplication standaloneApplication = new StandaloneApplication();
			ValidateCommand command = parseCommand(standaloneApplication, ValidateCommand.class, arguments);
			Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertEquals(true, command.usingToken.doRunJavaConstraints());
			assertEquals(true, command.usingToken.doRunOCLConstraints());
			assertEquals(true, command.usingToken.doRunUMLConstraints());
			standaloneApplication.stop();
	}

	@Test
	public void testStandaloneParser_usingOCLLocator() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE,
			"-using", "ocl"};
			StandaloneApplication standaloneApplication = new StandaloneApplication();
			ValidateCommand command = parseCommand(standaloneApplication, ValidateCommand.class, arguments);
			Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertEquals(false, command.usingToken.doRunJavaConstraints());
			assertEquals(true, command.usingToken.doRunOCLConstraints());
			assertEquals(false, command.usingToken.doRunUMLConstraints());
			standaloneApplication.stop();
	}

	@Test
	public void testStandaloneParser_usingJavaLocator() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE,
			"-using", "java"};
			StandaloneApplication standaloneApplication = new StandaloneApplication();
			ValidateCommand command = parseCommand(standaloneApplication, ValidateCommand.class, arguments);
			Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertEquals(true, command.usingToken.doRunJavaConstraints());
			assertEquals(false, command.usingToken.doRunOCLConstraints());
			assertEquals(false, command.usingToken.doRunUMLConstraints());
			standaloneApplication.stop();
	}

	@Test
	public void testStandaloneParser_usingUMLLocator() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE,
			"-using", "uml"};
			StandaloneApplication standaloneApplication = new StandaloneApplication();
			ValidateCommand command = parseCommand(standaloneApplication, ValidateCommand.class, arguments);
			Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertEquals(false, command.usingToken.doRunJavaConstraints());
			assertEquals(false, command.usingToken.doRunOCLConstraints());
			assertEquals(true, command.usingToken.doRunUMLConstraints());
			standaloneApplication.stop();
	}

	@Test
	public void testStandaloneParser_usingOCLUMLLocators() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE,
			"-using", "ocl,uml"};
			StandaloneApplication standaloneApplication = new StandaloneApplication();
			ValidateCommand command = parseCommand(standaloneApplication, ValidateCommand.class, arguments);
			Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertEquals(false, command.usingToken.doRunJavaConstraints());
			assertEquals(true, command.usingToken.doRunOCLConstraints());
			assertEquals(true, command.usingToken.doRunUMLConstraints());
			standaloneApplication.stop();
	}

	@Test
	public void testStandaloneParser_usingOCLJavaLocators() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE,
			"-using", "ocl,java"};
			StandaloneApplication standaloneApplication = new StandaloneApplication();
			ValidateCommand command = parseCommand(standaloneApplication, ValidateCommand.class, arguments);
			Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertEquals(true, command.usingToken.doRunJavaConstraints());
			assertEquals(true, command.usingToken.doRunOCLConstraints());
			assertEquals(false, command.usingToken.doRunUMLConstraints());
			standaloneApplication.stop();
	}

	@Test
	public void testStandaloneParser_usingJavaUmlLocators() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE,
			"-using", "uml,java"};
			StandaloneApplication standaloneApplication = new StandaloneApplication();
			ValidateCommand command = parseCommand(standaloneApplication, ValidateCommand.class, arguments);
			Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertEquals(true, command.usingToken.doRunJavaConstraints());
			assertEquals(false, command.usingToken.doRunOCLConstraints());
			assertEquals(true, command.usingToken.doRunUMLConstraints());
			standaloneApplication.stop();
	}

	@Test
	public void testStandaloneParser_usingOCLJavaUmlLocators() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE,
			"-using", "ocl,uml,java"};
			StandaloneApplication standaloneApplication = new StandaloneApplication();
			ValidateCommand command = parseCommand(standaloneApplication, ValidateCommand.class, arguments);
			Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertEquals(true, command.usingToken.doRunJavaConstraints());
			assertEquals(true, command.usingToken.doRunOCLConstraints());
			assertEquals(true, command.usingToken.doRunUMLConstraints());
			standaloneApplication.stop();
	}
}
