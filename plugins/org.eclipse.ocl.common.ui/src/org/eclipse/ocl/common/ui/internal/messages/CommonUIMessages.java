/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.common.ui.internal.messages;

import org.eclipse.osgi.util.NLS;

/**
 * An accessor class for externalized strings.
 */
public class CommonUIMessages
{	
	static {
		NLS.initializeMessages(CommonUIMessages.class.getName(), CommonUIMessages.class);
	}
	
	public static String CodeGenerationMode;
	public static String CodeGenerationMode_Compiled;
	public static String CodeGenerationMode_Delegated;

	public static String Common_PageTitle;

	public static String ConfigureWorkspaceSettings;

	public static String DefaultDelegationMode;
	
	public static String EnableProjectSpecificSettings;
	
	public static String Preference_False;
	public static String Preference_True;
	
	public static String Preference_Null;
	public static String Preference_Invalid;
}
