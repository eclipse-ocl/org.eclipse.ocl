/*******************************************************************************
 * Copyright (c) 2017, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
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
	public static @NonNull TestFileSystem create(@NonNull TestFileSystemHelper helper) {
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			return JUnitStandaloneFileSystem.create(helper);
		}
		else{
			return JUnitPluginFileSystem.create(helper);
		}
	}

	protected final @NonNull TestFileSystemHelper helper;

	protected TestFileSystem(@NonNull TestFileSystemHelper helper) {
		this.helper = helper;
	}

	public abstract @NonNull TestProject getTestProject(@NonNull String projectName, boolean cleanProject);
}
