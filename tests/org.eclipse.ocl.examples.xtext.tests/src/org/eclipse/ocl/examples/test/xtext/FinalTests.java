/*******************************************************************************
 * Copyright (c) 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import org.eclipse.ocl.examples.xtext.tests.TestUIUtil;
import org.eclipse.ocl.xtext.base.ui.BaseUIActivator;
import org.eclipse.ocl.xtext.base.ui.builder.MultiValidationJob;

/**
 * Tests.
 */
public class FinalTests extends AbstractGrammarTests
{
	/**
	 * Delay while background jobs sort themseleves out.
	 */
	public void testFinalTests_Delay() throws Exception {
		MultiValidationJob multiValidationJob = BaseUIActivator.basicGetMultiValidationJob();
		System.out.println("testFinalTests_Delay start multiValidationJob=" + multiValidationJob);
		//		System.err.println("testFinalTests_Delay start");
		if (multiValidationJob != null) {
			multiValidationJob.cancel();
		}
		TestUIUtil.wait(10000);
		System.out.println("testFinalTests_Delay end");
		//		System.err.println("testFinalTests_Delay end");
	}
}
