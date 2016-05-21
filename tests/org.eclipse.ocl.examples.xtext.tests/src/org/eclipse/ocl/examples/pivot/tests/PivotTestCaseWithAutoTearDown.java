/*******************************************************************************
 * Copyright (c) 2011, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Zeligsoft - Bugs 243079, 244948, 244886, 245619
 *   E.D.Willink - Bug 191689, 254919, 296409, 298634
 *   Obeo - Bug 291310
 *   E.D.Willink (CEA LIST) - Bug 388529
 *******************************************************************************/

package org.eclipse.ocl.examples.pivot.tests;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.eclipse.jdt.annotation.NonNull;

/**
 * Default test framework.
 */
@SuppressWarnings("nls")
public abstract class PivotTestCaseWithAutoTearDown extends PivotTestCase
{
	@SuppressWarnings("null")
    protected void autoTearDown() throws Exception {
		//
		//	Null out any references that a test may have left behind, so that unwanted
		//	objects are not locked into memory.
		//
		for (java.lang.Class<?> aClass = getClass(); PivotTestCaseWithAutoTearDown.class.isAssignableFrom(aClass); aClass = aClass.getSuperclass()) {
			for (Field field : aClass.getDeclaredFields()) {
				int modifiers = field.getModifiers();
				if (Modifier.isFinal(modifiers)) {
				}
				else if (!Modifier.isStatic(modifiers)) {
					java.lang.Class<?> fieldType = field.getType();
					if (Object.class.isAssignableFrom(fieldType)) {
						String fieldName = field.getName();
						try {
							String tearDownName = "tearDown_" + fieldName;
							Method method = aClass.getDeclaredMethod(tearDownName);
							try {
								tearDownUsing(method);
							} catch (Exception e) {
								// tearDown_xxx must be public
								fail("Failed to invoke " + getClass().getSimpleName() + "." + tearDownName + " : " + e);  //$NON-NLS-2$//$NON-NLS-3$
							}
						}
						catch (NoSuchMethodException e) {
							try {
								tearDownField(field);
							} catch (Exception e1) {
								// xxx without a tearDown_xxx must be public to ensure that leakage can be stopped
								fail("Failed to set " + getClass().getSimpleName() + "." + fieldName + " to null : " + e1); //$NON-NLS-2$ //$NON-NLS-3$
							}
						}
					}
				} else {
					tearDownStatic(aClass, field);
				}
			}
		}
	}

	protected void tearDownField(@NonNull Field field) throws IllegalAccessException {
		field.set(this, null);
	}

	protected void tearDownStatic(java.lang.@NonNull Class<?> aClass, @NonNull Field field) {
		if (aClass != PivotTestSuite.class) {
			// Tests may not have statics since they are prone to memory leakage
			fail("static test variable:" + field); 
		}
	}

	protected void tearDownUsing(@NonNull Method method)
			throws IllegalAccessException, InvocationTargetException {
		method.invoke(this);
	}
}
