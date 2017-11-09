/*******************************************************************************
 * Copyright (c) 2017 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.tests;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.jdt.annotation.NonNull;

/**
 * A TestFileSystem manages a uniform set of project/file handles. Derived implementation
 * provide a uniform treatment of a platform:/resource/test-project for a variety of different
 * test harnesses.
 */
public abstract class TestFileSystem
{
	public static @NonNull TestFileSystem create() {
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			return JUnitStandaloneFileSystem.create();
		}
		else{
			return JUnitPluginFileSystem.create();
		}
	}

	public abstract @NonNull TestProject getTestProject(@NonNull String projectName);
}
