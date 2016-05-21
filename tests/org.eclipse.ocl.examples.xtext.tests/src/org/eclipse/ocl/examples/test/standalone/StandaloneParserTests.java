/*******************************************************************************
 * Copyright (c) 2014, 2016 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Obeo - initial API and implementation 
 *******************************************************************************/
package org.eclipse.ocl.examples.test.standalone;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.emf.validation.validity.export.HTMLExporter;
import org.eclipse.ocl.examples.emf.validation.validity.export.TextExporter;
import org.eclipse.ocl.examples.standalone.HelpCommand;
import org.eclipse.ocl.examples.standalone.StandaloneApplication;
import org.eclipse.ocl.examples.standalone.StandaloneCommand;
import org.eclipse.ocl.examples.standalone.StandaloneCommand.CommandToken;
import org.eclipse.ocl.examples.standalone.StandaloneCommandAnalyzer;
import org.eclipse.ocl.examples.standalone.validity.ValidateCommand;
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
	public void test_help() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"help"};
		HelpCommand command = parseCommand(HelpCommand.class, arguments);
		Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
		assertEquals(0, token2strings.size());
	}

	@Test
	public void test_help_extraText() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"help", "yy"};
		HelpCommand command = parseCommand(HelpCommand.class, arguments);
		parseInvalidArguments(command, arguments);
	}

	@Test
	public void test_mandatoryArguments() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName};
		ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
		Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
		assertCommandValid(command, token2strings);
		assertEquals(null, command.exporterToken.getExporter(token2strings));
		assertEquals(inputModelName, command.modelToken.getModelFileName(token2strings));
		assertEquals(null, command.outputToken.getOutputFile(token2strings));
		assertEquals(Lists.newArrayList(inputOCLFileName), normalize(command.rulesToken.getOCLFileNames(token2strings)));
		assertEquals(true, command.usingToken.doRunJavaConstraints(token2strings));
		assertEquals(true, command.usingToken.doRunOCLConstraints(token2strings));
		assertEquals(true, command.usingToken.doRunUMLConstraints(token2strings));
	}

	@Test
	public void test_missingOutputArgument() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
			"-output"
		};
		ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
		parseInvalidArguments(command, arguments);
	}

	@Test
	public void test_missingExporterArgument() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
			"-exporter"
		};
		ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
		parseInvalidArguments(command, arguments);
	}

	@Test
	public void test_missingUsingArgument() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
			"-using"
		};
		ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
		parseInvalidArguments(command, arguments);
	}

	@Test
	public void test_textExportedFile() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE};
		ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
		Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
		assertCommandValid(command, token2strings);
		assertTrue(command.exporterToken.getExporter(token2strings) instanceof TextExporter);
		assertEquals(inputModelName, command.modelToken.getModelFileName(token2strings));
		assertEquals(getTextLogFileName(), normalize(command.outputToken.getOutputFile(token2strings).toString()));
		assertEquals(Lists.newArrayList(inputOCLFileName), Lists.newArrayList(normalize(command.rulesToken.getOCLFileNames(token2strings).get(0))));
		assertEquals(true, command.usingToken.doRunJavaConstraints(token2strings));
		assertEquals(true, command.usingToken.doRunOCLConstraints(token2strings));
		assertEquals(true, command.usingToken.doRunUMLConstraints(token2strings));
	}

	@Test
	public void test_htmlExportedFile() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
			"-output", getHTMLLogFileName(),
			"-exporter", HTMLExporter.EXPORTER_TYPE};
		ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
		Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
		assertCommandValid(command, token2strings);
		assertTrue(command.exporterToken.getExporter(token2strings) instanceof HTMLExporter);
		assertEquals(inputModelName, command.modelToken.getModelFileName(token2strings));
		assertEquals(getHTMLLogFileName(), normalize(command.outputToken.getOutputFile(token2strings).toString()));
		assertEquals(Lists.newArrayList(inputOCLFileName), normalize(command.rulesToken.getOCLFileNames(token2strings)));
		assertEquals(true, command.usingToken.doRunJavaConstraints(token2strings));
		assertEquals(true, command.usingToken.doRunOCLConstraints(token2strings));
		assertEquals(true, command.usingToken.doRunUMLConstraints(token2strings));
	}

	@Test
	public void test_unknownExporter() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
			"-output", getTextLogFileName(),
			"-exporter", "anotherExporterAttribute"};
		ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
		Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
		assertCommandInvalid(command, token2strings);
	}

	@Test
	public void test_nonExistentModel() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", String.valueOf(getProjectFileURI("models/nonExistent.ecore")),
			"-rules", inputOCLFileName,
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE};
		ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
		Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
		assertCommandInvalid(command, token2strings);
	}

	@Test
	public void test_nonExistentOclFile() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", String.valueOf(getProjectFileURI("models/nonExistent.ocl")),
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE};
		ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
		Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
		assertCommandValid(command, token2strings);			// missing file is ignored
	}

	@Test
	public void test_nonExistentOutputFolder() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
			"-output", "nonExistentFolder/log.file",
			"-exporter", TextExporter.EXPORTER_TYPE}; 
		ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
		Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
		assertCommandInvalid(command, token2strings);
	}

	@Test
	public void test_textOCLFiles() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", textInputOCLFileName,
			"-output", getTextLogFileName(),
			"-exporter", TextExporter.EXPORTER_TYPE};
		ValidateCommand command = parseCommand(ValidateCommand.class, arguments);
		Map<CommandToken, List<String>> token2strings = parseValidArguments(command, arguments);
		assertCommandValid(command, token2strings);
		assertTrue(command.exporterToken.getExporter(token2strings) instanceof TextExporter);
		assertEquals(inputModelName, command.modelToken.getModelFileName(token2strings));
		assertEquals(getTextLogFileName(), normalize(command.outputToken.getOutputFile(token2strings).toString()));
		assertEquals(Lists.newArrayList(inputOCLFileName, inputOCLFileName2), normalize(command.rulesToken.getOCLFileNames(token2strings)));
		assertEquals(true, command.usingToken.doRunJavaConstraints(token2strings));
		assertEquals(true, command.usingToken.doRunOCLConstraints(token2strings));
		assertEquals(true, command.usingToken.doRunUMLConstraints(token2strings));
	}

	@Test
	public void test_usingAllLocators() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
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
	public void test_usingOCLLocator() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
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
	public void test_usingJavaLocator() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
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
	public void test_usingUMLLocator() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
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
	public void test_usingOCLUMLLocators() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
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
	public void test_usingOCLJavaLocators() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
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
	public void test_usingJavaUmlLocators() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
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
	public void test_usingOCLJavaUmlLocators() throws CoreException {
		@NonNull String @NonNull [] arguments = new @NonNull String @NonNull []{"validate",
			"-model", inputModelName,
			"-rules", inputOCLFileName,
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
