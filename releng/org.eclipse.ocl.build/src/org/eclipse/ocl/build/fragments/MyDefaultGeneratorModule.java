/*******************************************************************************
 * Copyright (c) 2020, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.build.fragments;

import org.eclipse.xtext.xtext.generator.DefaultGeneratorModule;
import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;

/**
 * This override supports the specification of an activatorName to fix Bug 564238
 */
public class MyDefaultGeneratorModule extends DefaultGeneratorModule
{
	public Class<? extends XtextGeneratorNaming> bindXtextGeneratorNaming() {
		return MyXtextGeneratorNaming.class;
	}
}
