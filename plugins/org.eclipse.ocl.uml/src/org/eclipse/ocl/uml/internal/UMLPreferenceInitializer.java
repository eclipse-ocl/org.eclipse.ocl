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
package org.eclipse.ocl.uml.internal;

import org.eclipse.ocl.common.internal.preferences.AnnotatedPreferenceInitializer;
import org.eclipse.ocl.common.preferences.PreferenceableOption;
import org.eclipse.ocl.uml.options.UMLEvaluationOptions;
import org.eclipse.ocl.uml.options.UMLParsingOptions;

/**
 * Class used to initialize default preference values.
 */
public class UMLPreferenceInitializer extends AnnotatedPreferenceInitializer
{
	@Override
	public void initializeDefaultPreferences() {
		putPreference((PreferenceableOption<?>) UMLEvaluationOptions.EVALUATION_MODE);	
		putPreference((PreferenceableOption<?>) UMLParsingOptions.ASSOCIATION_CLASS_TYPE);	
	}
}
