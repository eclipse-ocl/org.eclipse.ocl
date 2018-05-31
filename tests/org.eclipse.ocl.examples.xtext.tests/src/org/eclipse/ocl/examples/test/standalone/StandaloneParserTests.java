/*******************************************************************************
 * Copyright (c) 2014, 2017 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.standalone;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Appender;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.emf.validation.validity.export.HTMLExporter;
import org.eclipse.ocl.examples.emf.validation.validity.export.TextExporter;
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
	private StandaloneCommandAnalyzer commandAnalyzer = new StandaloneCommandAnalyzer(new StandaloneApplication());

	protected static void assertCommandInvalid(@NonNull StandaloneCommand command, @NonNull Map<CommandToken, List<String>> token2strings) {
		boolean status = command.check(token2strings);
		assertFalse(status);
	}

	protected static void assertCommandValid(@NonNull StandaloneCommand command, @NonNull Map<CommandToken, List<String>> token2strings) {
		boolean status = command.check(token2strings);
		assertTrue(status);
	}

	protected List<String> normalize(List<String> strings) {
		List<String> normalized = new ArrayList<String>(strings.size());
		for (String string : strings) {
			normalized.add(string.replace("\\", "/"));
		}
		return normalized;
	}

	protected String normalize(String string) {
		return string.replace("\\", "/");
	}

	@SuppressWarnings("unchecked")
	protected @NonNull <T extends StandaloneCommand> T parseCommand(@NonNull Class<T> commandClass, @NonNull String @NonNull [] arguments) {
		StandaloneCommand command = commandAnalyzer.parse(arguments);
		assert command != null;
		assertEquals(commandClass, command.getClass());
		return (T) command;
	}

	protected void parseInvalidArguments(@NonNull StandaloneCommand command, @NonNull String @NonNull [] arguments) {
		Map<CommandToken, List<String>> token2strings = command.parse(arguments);
		assertNull(token2strings);
	}

	protected @NonNull Map<CommandToken, List<String>> parseValidArguments(@NonNull StandaloneCommand command, @NonNull String @NonNull [] arguments) {
		Map<CommandToken, List<String>> token2strings = command.parse(arguments);
		assertNotNull(token2strings);
		assert token2strings != null;
		return token2strings;
	}

	@Test
	public void test_help() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"help"};
		HelpCommand command = parseCommand(HelpCommand.class, arguments);
		Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
		assertEquals(0, token2strings.size());
	}

	@Test
	public void test_help_extraText() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"help", "yy"};
			HelpCommand command = parseCommand(HelpCommand.class, arguments);
			parseInvalidArguments(command, arguments);
			String logMessage = TestCaseLogger.INSTANCE.get();
			assertTrue(logMessage.contains("Bad help command"));
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void test_mandatoryArguments() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI)};
			ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
			Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertEquals(null, command.exporterToken.getExporter(token2strings));
			assertEquals(String.valueOf(inputModelURI), command.modelToken.getModelFileName(token2strings));
			assertEquals(null, command.outputToken.getOutputFile(token2strings));
			assertEquals(Lists.newArrayList(String.valueOf(inputOCLURI)), normalize(command.rulesToken.getOCLFileNames(token2strings)));
			assertEquals(true, command.usingToken.doRunJavaConstraints(token2strings));
			assertEquals(true, command.usingToken.doRunOCLConstraints(token2strings));
			assertEquals(true, command.usingToken.doRunUMLConstraints(token2strings));
	}

	@Test
	public void test_missingOutputArgument() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(inputOCLURI),
				"-output"
			};
			ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
			parseInvalidArguments(command, arguments);
			String logMessage = TestCaseLogger.INSTANCE.get();
			assertTrue(logMessage.contains("Missing argument for"));
			assertTrue(logMessage.contains("-output"));
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void test_missingExporterArgument() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(inputOCLURI),
				"-exporter"
			};
			ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
			parseInvalidArguments(command, arguments);
			String logMessage = TestCaseLogger.INSTANCE.get();
			assertTrue(logMessage.contains("Missing argument for"));
			assertTrue(logMessage.contains("-exporter"));
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void test_missingUsingArgument() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(inputOCLURI),
				"-using"
			};
			ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
			parseInvalidArguments(command, arguments);
			String logMessage = TestCaseLogger.INSTANCE.get();
			assertTrue(logMessage.contains("No argument for"));
			assertTrue(logMessage.contains("-using"));
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void test_textExportedFile() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE};
			ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
			Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertTrue(command.exporterToken.getExporter(token2strings) instanceof TextExporter);
			assertEquals(String.valueOf(inputModelURI), command.modelToken.getModelFileName(token2strings));
			assertEquals(getTextLogFileName(), normalize(command.outputToken.getOutputFile(token2strings).toString()));
			assertEquals(Lists.newArrayList(String.valueOf(inputOCLURI)), Lists.newArrayList(normalize(command.rulesToken.getOCLFileNames(token2strings).get(0))));
			assertEquals(true, command.usingToken.doRunJavaConstraints(token2strings));
			assertEquals(true, command.usingToken.doRunOCLConstraints(token2strings));
			assertEquals(true, command.usingToken.doRunUMLConstraints(token2strings));
	}

	@Test
	public void test_htmlExportedFile() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getHTMLLogFileName(),
			"-exporter", HTMLExporter.EXPORTER_TYPE};
			ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
			Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertTrue(command.exporterToken.getExporter(token2strings) instanceof HTMLExporter);
			assertEquals(String.valueOf(inputModelURI), command.modelToken.getModelFileName(token2strings));
			assertEquals(getHTMLLogFileName(), normalize(command.outputToken.getOutputFile(token2strings).toString()));
			assertEquals(Lists.newArrayList(String.valueOf(inputOCLURI)), normalize(command.rulesToken.getOCLFileNames(token2strings)));
			assertEquals(true, command.usingToken.doRunJavaConstraints(token2strings));
			assertEquals(true, command.usingToken.doRunOCLConstraints(token2strings));
			assertEquals(true, command.usingToken.doRunUMLConstraints(token2strings));
	}

	@Test
	public void test_unknownExporter() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", "anotherExporterAttribute"};
			ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
			Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
			assertCommandInvalid(command, token2strings);
	}

	@Test
	public void test_nonExistentModel() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(getTestModelURI("models/nonExistent.ecore")),
				"-rules", String.valueOf(inputOCLURI),
				"-output", getTextLogFileName(),
				"-exporter", TextExporter.EXPORTER_TYPE};
				ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
				Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
				assertCommandInvalid(command, token2strings);
				String logMessage = TestCaseLogger.INSTANCE.get();
				assertTrue(logMessage.contains("does not exist"));
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void test_nonExistentOclFile() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(getTestModelURI("models/nonExistent.ocl")),
				"-output", getTextLogFileName(),
				"-exporter", TextExporter.EXPORTER_TYPE};
				ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
				Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
				assertCommandValid(command, token2strings);			// missing file is ignored
				String logMessage = TestCaseLogger.INSTANCE.get();
				assertTrue(logMessage.contains("does not exist"));
				assertTrue(logMessage.contains("ignored"));
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void test_nonExistentOutputFolder() throws Exception {
		Iterable<Appender> savedAppenders = TestCaseLogger.INSTANCE.install();
		try {
			@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
				"-model", String.valueOf(inputModelURI),
				"-rules", String.valueOf(inputOCLURI),
				"-output", "nonExistentFolder/log.file",
				"-exporter", TextExporter.EXPORTER_TYPE};
				ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
				Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
				assertCommandInvalid(command, token2strings);
				String logMessage = TestCaseLogger.INSTANCE.get();
				assertTrue(logMessage.contains("does not exist"));
		} finally {
			TestCaseLogger.INSTANCE.uninstall(savedAppenders);
		}
	}

	@Test
	public void test_textOCLFiles() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(textInputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE};
			ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
			Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertTrue(command.exporterToken.getExporter(token2strings) instanceof TextExporter);
			assertEquals(String.valueOf(inputModelURI), command.modelToken.getModelFileName(token2strings));
			assertEquals(getTextLogFileName(), normalize(command.outputToken.getOutputFile(token2strings).toString()));
			assertEquals(Lists.newArrayList(String.valueOf(inputOCLURI), String.valueOf(inputOCLURI2)), normalize(command.rulesToken.getOCLFileNames(token2strings)));
			assertEquals(true, command.usingToken.doRunJavaConstraints(token2strings));
			assertEquals(true, command.usingToken.doRunOCLConstraints(token2strings));
			assertEquals(true, command.usingToken.doRunUMLConstraints(token2strings));
	}

	@Test
	public void test_usingAllLocators() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE,
			"-using", "all"};
			ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
			Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertEquals(true, command.usingToken.doRunJavaConstraints(token2strings));
			assertEquals(true, command.usingToken.doRunOCLConstraints(token2strings));
			assertEquals(true, command.usingToken.doRunUMLConstraints(token2strings));
	}

	@Test
	public void test_usingOCLLocator() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE,
			"-using", "ocl"};
			ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
			Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertEquals(false, command.usingToken.doRunJavaConstraints(token2strings));
			assertEquals(true, command.usingToken.doRunOCLConstraints(token2strings));
			assertEquals(false, command.usingToken.doRunUMLConstraints(token2strings));
	}

	@Test
	public void test_usingJavaLocator() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE,
			"-using", "java"};
			ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
			Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertEquals(true, command.usingToken.doRunJavaConstraints(token2strings));
			assertEquals(false, command.usingToken.doRunOCLConstraints(token2strings));
			assertEquals(false, command.usingToken.doRunUMLConstraints(token2strings));
	}

	@Test
	public void test_usingUMLLocator() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE,
			"-using", "uml"};
			ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
			Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertEquals(false, command.usingToken.doRunJavaConstraints(token2strings));
			assertEquals(false, command.usingToken.doRunOCLConstraints(token2strings));
			assertEquals(true, command.usingToken.doRunUMLConstraints(token2strings));
	}

	@Test
	public void test_usingOCLUMLLocators() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE,
			"-using", "ocl,uml"};
			ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
			Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertEquals(false, command.usingToken.doRunJavaConstraints(token2strings));
			assertEquals(true, command.usingToken.doRunOCLConstraints(token2strings));
			assertEquals(true, command.usingToken.doRunUMLConstraints(token2strings));
	}

	@Test
	public void test_usingOCLJavaLocators() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE,
			"-using", "ocl,java"};
			ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
			Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertEquals(true, command.usingToken.doRunJavaConstraints(token2strings));
			assertEquals(true, command.usingToken.doRunOCLConstraints(token2strings));
			assertEquals(false, command.usingToken.doRunUMLConstraints(token2strings));
	}

	@Test
	public void test_usingJavaUmlLocators() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE,
			"-using", "uml,java"};
			ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
			Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertEquals(true, command.usingToken.doRunJavaConstraints(token2strings));
			assertEquals(false, command.usingToken.doRunOCLConstraints(token2strings));
			assertEquals(true, command.usingToken.doRunUMLConstraints(token2strings));
	}

	@Test
	public void test_usingOCLJavaUmlLocators() throws Exception {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(inputModelURI),
			"-rules", String.valueOf(inputOCLURI),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE,
			"-using", "ocl,uml,java"};
			ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
			Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
			assertCommandValid(command, token2strings);
			assertEquals(true, command.usingToken.doRunJavaConstraints(token2strings));
			assertEquals(true, command.usingToken.doRunOCLConstraints(token2strings));
			assertEquals(true, command.usingToken.doRunUMLConstraints(token2strings));
	}
}
