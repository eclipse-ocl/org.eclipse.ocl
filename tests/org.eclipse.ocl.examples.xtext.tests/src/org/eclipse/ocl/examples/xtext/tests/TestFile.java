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

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;

/**
 * A TestFile provides polymorphism between writeable files for a variety of test hartnesses.
 */
public interface TestFile
{
	/**
	 * Return the absolute file that may be used by non-Eclipse tools such as the Java compiler.
	 */
	@NonNull File getFile();

	/**
	 * Return the non-null absolute file string that may be used by non-Eclipse tools such as the Java compiler.
	 */
	@NonNull String getFileString();

	/**
	 * Return a file:: schem URI that accesses the underlying file.
	 */
	@NonNull URI getFileURI();

	/**
	 * Return the logical name of the file or project. Note that the physical name of
	 * project failesmay be prefixed to avoid clashes.
	 */
	@NonNull String getName();

	/**
	 * Return the full Eclipse platform:/resource/test-project URI.
	 */
	@NonNull URI getURI();
}