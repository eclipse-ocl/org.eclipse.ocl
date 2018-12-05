/*******************************************************************************
 * Copyright (c) 2010, 2018 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   E.D.Willink - rework of LPG OCL Console for Xtext
 *******************************************************************************/

package org.eclipse.ocl.examples.xtext.console.messages;

import org.eclipse.osgi.util.NLS;

/**
 * An accessor class for externalized strings.
 */
public class ConsoleMessages
{
	static {
		NLS.initializeMessages(ConsoleMessages.class.getName(), ConsoleMessages.class);
	}

	public static String CloseAction_Label;
	public static String CloseAction_Tip;
	public static String Console_Title;
	public static String Console_TitleWithContext;
	public static String ContextSource_Title;
	public static String ContextSource_ToolTip;
	public static String Debug_FailCreate;
	public static String Debug_FailLaunch;
	public static String Debug_FailLoad;
	public static String Debug_FailStart;
	public static String Debug_FailStart_NoOCL;
	public static String Debug_FailStart_NoShell;
	public static String Debug_ProgressCreate;
	public static String Debug_ProgressLaunch;
	public static String Debug_ProgressLoad;
	public static String Debug_Starter;
	public static String Debug_Title;
	public static String Debug_ToolTip;
	public static String Heading_Evaluating;
	public static String Heading_Results;
	public static String LoadAction_Label;
	public static String LoadAction_Tip;
	public static String LoadAction_Title;
	public static String LoadActionError_Title;
	public static String LoadActionWarning_Title;
	public static String LoadActionWarning_NoExpression;
	public static String LoadResourceAction_Tip;
	public static String ModelTypesUsage_Message;
//	public static String ModelTypesUsage_Question;
	public static String ModelTypesUsage_Title;
	public static String Output_Exception;
	public static String Progress_Evaluating;
	public static String Progress_Extent;
	public static String Progress_Synchronising;
	public static String Progress_Title;
	public static String Result_EvaluationFailure;
	public static String Result_EvaluationTerminated;
	public static String Result_MappingFailure;
	public static String Result_NoExpression;
	public static String Result_Parsed;
	public static String Result_ParsingFailure;
	public static String SaveAction_Label;
	public static String SaveAction_Tip;
	public static String SaveAction_Title;
	public static String SaveActionError_Title;
	public static String SaveActionWarning_Title;
	public static String SaveActionWarning_NoExpression;
	public static String SelectionError_Message;
	public static String SelectionError_Metamodel;
	public static String SelectionError_Model;
	public static String SelectionError_Title;
	public static String ValueFactory_Cancelable;
}
