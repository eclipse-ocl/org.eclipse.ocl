/*******************************************************************************
 * Copyright (c) 2017, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.tests;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.jdt.annotation.NonNull;

/**
 * A TestFileSystem manages a uniform set of project/file handles. Derived implementation
 * provide a uniform treatment of a platform:/resource/test-project for a variety of different
 * test harnesses.
 */
public abstract class TestFileSystem
{
	public static @NonNull TestFileSystem create(@NonNull TestFileSystemHelper helper, @NonNull String pathFromCurrentWorkingDirectoryToFileSystem) {
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			return JUnitStandaloneFileSystem.create(helper, pathFromCurrentWorkingDirectoryToFileSystem);
		}
		else{
			return JUnitPluginFileSystem.create(helper, pathFromCurrentWorkingDirectoryToFileSystem);
		}
	}

	protected final @NonNull TestFileSystemHelper helper;
	protected final @NonNull String pathFromCurrentWorkingDirectoryToFileSystem;

	protected TestFileSystem(@NonNull TestFileSystemHelper helper, @NonNull String pathFromCurrentWorkingDirectoryToFileSystem) {
		this.helper = helper;
		this.pathFromCurrentWorkingDirectoryToFileSystem = pathFromCurrentWorkingDirectoryToFileSystem;
	}

	protected @NonNull String getJDTcorePreferenceContents() {
		return "eclipse.preferences.version=1\n"
//				+ "org.eclipse.jdt.core.compiler.codegen.methodParameters=do not generate\n"
				+ "org.eclipse.jdt.core.compiler.codegen.targetPlatform=1.8\n"
//				+ "org.eclipse.jdt.core.compiler.codegen.unusedLocal=preserve\n"
				+ "org.eclipse.jdt.core.compiler.compliance=1.8\n"
//				+ "org.eclipse.jdt.core.compiler.debug.lineNumber=generate\n"
//				+ "org.eclipse.jdt.core.compiler.debug.localVariable=generate\n"
//				+ "org.eclipse.jdt.core.compiler.debug.sourceFile=generate\n"
//				+ "org.eclipse.jdt.core.compiler.problem.enablePreviewFeatures=disabled\n"
//				+ "org.eclipse.jdt.core.compiler.problem.reportPreviewFeatures=warning\n"
				+ "org.eclipse.jdt.core.compiler.release=disabled\n"
				+ "org.eclipse.jdt.core.compiler.source=1.8\n"
				+ "";
	}

	protected @NonNull String getResourcesPreferenceContents() {
		return "eclipse.preferences.version=1\n" + "encoding/<project>=UTF-8\n";
	}

	protected @NonNull String getRuntimePreferenceContents() {
		return "eclipse.preferences.version=1\n" + "line.separator=\\n\n";
	}

	public abstract @NonNull TestProject getTestProject(@NonNull String projectName, boolean cleanProject);
}
