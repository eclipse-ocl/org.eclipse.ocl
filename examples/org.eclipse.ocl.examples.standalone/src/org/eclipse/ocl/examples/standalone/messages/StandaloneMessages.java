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
package org.eclipse.ocl.examples.standalone.messages;

import org.eclipse.osgi.util.NLS;

/** An accessor class for externalized strings. */
public final class StandaloneMessages
{
	static {
		NLS.initializeMessages(StandaloneMessages.class.getName(), StandaloneMessages.class);
	}

	// Application
	public static String OCLValidatorApplication_Aborted;
	public static String OCLValidatorApplication_ValidationStarting;
	public static String OCLValidatorApplication_ValidationComplete;
	public static String OCLValidatorApplication_ExportStarting;
	public static String OCLValidatorApplication_ExportProblem;
	public static String OCLValidatorApplication_ExportedFileGenerated;
	public static String OCLValidatorApplication_ModelLoadProblem;
	public static String OCLValidatorApplication_OclLoadProblem;
	public static String OCLValidatorApplication_OclUriProblem;
	public static String OCLValidatorApplication_MetamodelsLoadProblem;

	// Analyzer
	// Missing keyword
	public static String OCLArgumentAnalyzer_NoDefinedKeyword;

	// Mandatory Arguments
	public static String OCLArgumentAnalyzer_ModelArg;
	public static String OCLArgumentAnalyzer_RulesArg;
	// Mandatory Arguments values Missing
	public static String OCLArgumentAnalyzer_ModelPathMissing;
	public static String OCLArgumentAnalyzer_RulesPathMissing;

	// Optional Arguments
	public static String OCLArgumentAnalyzer_OutputArg;
	public static String OCLArgumentAnalyzer_ExporterArg;
	public static String OCLArgumentAnalyzer_RestrictionArg;

	// Optional Arguments values Missing
	public static String OCLArgumentAnalyzer_OutputFilePathMissing;
	public static String OCLArgumentAnalyzer_OutputFileInvalidExtension;
	public static String OCLArgumentAnalyzer_OutputFileCreationProblem;
	public static String OCLArgumentAnalyzer_ExporterMissing;
	public static String OCLArgumentAnalyzer_RestrictionMissing;

	public static String OCLArgumentAnalyzer_FileExt;
	public static String OCLArgumentAnalyzer_OutputFile;
	public static String OCLArgumentAnalyzer_OutputDir;
	public static String OCLArgumentAnalyzer_OCLFileNotFound;
	public static String OCLArgumentAnalyzer_NoOCLFiles;
	public static String OCLArgumentAnalyzer_CannotBeRead;
	public static String OCLArgumentAnalyzer_ExtensionPb;
	public static String OCLArgumentAnalyzer_found;

	public static String OCLArgumentAnalyzer_ignored;
	public static String OCLArgumentAnalyzer_ModelFile;
	public static String OCLArgumentAnalyzer_AlreadyExists;
	public static String OCLArgumentAnalyzer_NotFile;
	public static String OCLArgumentAnalyzer_NotExist;
	public static String OCLArgumentAnalyzer_OCLFile;
	public static String OCLArgumentAnalyzer_OCLResource;

	public static String Standalone_Help;
	public static String StandaloneCommand_Output_Help;

	public static String ExecuteCommand_Bad;
	public static String ExecuteCommand_Help;
	public static String ExecuteCommand_Exporter_Help;
	public static String ExecuteCommand_Query_Help;
	public static String ExecuteCommand_Self_Help;

	public static String HelpCommand_Bad;
	public static String HelpCommand_Help;
	public static String HelpText_Optional;
	public static String HelpText_Required;
	public static String HelpText_repeatable;
	public static String HelpText_token;

	public static String ValidateCommand_Help;
	public static String ValidateCommand_Model_Help;
	public static String ValidateCommand_Rules_Help;
	public static String ValidateCommand_Exporter_Help;
	public static String ValidateCommand_Using_Help;
}
