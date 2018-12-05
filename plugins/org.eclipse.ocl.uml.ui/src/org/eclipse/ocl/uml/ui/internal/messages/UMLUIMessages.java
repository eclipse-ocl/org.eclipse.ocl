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

package org.eclipse.ocl.uml.ui.internal.messages;

import org.eclipse.osgi.util.NLS;

/**
 * An accessor class for externalized strings.
 */
public class UMLUIMessages
{	
	static {
		NLS.initializeMessages(UMLUIMessages.class.getName(), UMLUIMessages.class);
	}

	public static String UML_AssociationClassType;
	public static String UML_EvaluationMode;
	public static String UML_EvaluationMode_Adaptive;
	public static String UML_EvaluationMode_InstanceModel;
	public static String UML_EvaluationMode_RuntimeObjects;
	public static String UML_PageTitle;
}
