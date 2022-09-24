/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.codegen.tests;

import org.eclipse.ocl.examples.pivot.tests.PivotTestCase;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;

public class CodegenTests extends org.eclipse.ocl.examples.test.xtext.AllEvaluationTests
{
	protected CodegenTests() {
		super();
		PivotUtilInternal.noDebug = false;
		PivotTestCase.DEBUG_ID = true;
	}
}
