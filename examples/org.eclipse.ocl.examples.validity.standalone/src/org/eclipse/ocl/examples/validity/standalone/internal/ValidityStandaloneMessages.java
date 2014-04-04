/**
 * <copyright>
 *
 * Copyright (c) 2014 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Obeo - initial API and implementation 
 *
 * </copyright>
 */
package org.eclipse.ocl.examples.validity.standalone.internal;

import org.eclipse.osgi.util.NLS;

/** An accessor class for externalized strings. */
public final class ValidityStandaloneMessages {

	static {
		NLS.initializeMessages(ValidityStandaloneMessages.class.getName(),
				ValidityStandaloneMessages.class);
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
	public static String OCLValidatorApplication_MetaModelsLoadProblem;

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
	
	public static String OCLArgumentAnalyzer_help;
}
