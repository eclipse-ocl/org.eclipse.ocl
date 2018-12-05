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
package org.eclipse.ocl.common.internal.preferences;

import org.eclipse.ocl.common.internal.options.CommonOptions;

/**
 * Class used to initialize default preference values.
 */
public class CommonPreferenceInitializer extends AnnotatedPreferenceInitializer
{
	@Override
	public void initializeDefaultPreferences() {
		putPreference(CommonOptions.CODE_GENERATION_MODE);
		putPreference(CommonOptions.DEFAULT_DELEGATION_MODE);
	}
}
