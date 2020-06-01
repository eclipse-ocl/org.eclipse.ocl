/*******************************************************************************
 * Copyright (c) 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.codegen.tests;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.pivot.tests.IteratorsTest4;

public class CompatibilityCodegenTests extends org.eclipse.ocl.examples.test.xtext.AllEvaluationTests
{
	void dummy() {
		@SuppressWarnings("unused")
		@NonNull IteratorsTest4 x;
	}
}
