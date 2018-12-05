/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.ui.internal.messages;

import org.eclipse.osgi.util.NLS;

/**
 * An accessor class for externalized strings.
 */
public class EcoreAndUMLUIMessages
{	
	static {
		NLS.initializeMessages(EcoreAndUMLUIMessages.class.getName(), EcoreAndUMLUIMessages.class);
	}

	public static String PageTitle;

	public static String Preference_Severity_ByAlias;
	public static String Preference_Severity_ByAliasThenName;
	public static String Preference_Severity_ByName;
	
	public static String Preference_Severity_Error;
	public static String Preference_Severity_Info;
	public static String Preference_Severity_Ok;
	public static String Preference_Severity_Warning;	
	
	public static String LPG_UseTypeCaches;
	public static String LPG_OverloadAmbiguityIsInvalid;
	public static String LPG_AmbiguousAssociationEnds;
	public static String LPG_AnyLessIteratorReturnsInvalid;
	public static String LPG_ClosureIterator;
	public static String LPG_ClosureIncludesSources;
	public static String LPG_DynamicDispatch;
	public static String LPG_ConceptualOperationName;
	public static String LPG_DefinitionConstrainsFeature;
	public static String LPG_ElementNameQuoteEscape;
	public static String LPG_ImplicitRootClass;
	public static String LPG_InheritedFeatureContext;
	public static String LPG_LaxNullHandling;
	public static String LPG_PackageLookupStrategy;
	public static String LPG_StringCaseConversion;
	public static String LPG_StringSingleQuoteEscape;
	public static String LPG_SupportStaticFeatures;
	public static String LPG_UseBackslashEscapeProcessing;
	public static String LPG_UseCompareToOperation;
	public static String LPG_UseLongIntegers;
	public static String LPG_WarnOfXorOrAndPrecedenceChange;
}
