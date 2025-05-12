/*******************************************************************************
 * Copyright (c) 2024, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.tests.xtext;

import org.eclipse.ocl.xtext.idioms.ui.outline.IdiomsOutlineTreeProvider;
import org.eclipse.ocl.xtext.tests.pivot.tests.PivotTestCase;

/**
 * The FirstTest 'suite' works around https://github.com/eclipse-platform/eclipse.platform/issues/1495 whereby
 * non-classpath extensions points are no longer respected. THis dummy test ensures that org.eclipse.ocl.xtext.idioms.ui
 * contributes to the classpath.
 */
public class FirstTest extends PivotTestCase
{
	public void testFirstTest() throws Exception {
		new IdiomsOutlineTreeProvider();
	}
}
