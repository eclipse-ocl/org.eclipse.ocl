/*******************************************************************************
 * Copyright (c) 2017, 2024 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 * E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.testutils;

import junit.framework.TestCase;


/**
 * This is the super class for all impact analyzer tests.
 */
//@Ignore
public abstract class BaseTest extends TestCase
{
	protected void debugPrint(Object object) {
		//        System.out.print(object);
	}

	protected void debugPrintln() {
		//        System.out.println();
	}

	protected void debugPrintln(Object object) {
		//        System.out.println(object);
	}

	@Override
	public String getName() {
		String testNameSuffix = System.getProperty("testNameSuffix", "");
		return super.getName() + " <" + testNameSuffix + ">";
	}
}
