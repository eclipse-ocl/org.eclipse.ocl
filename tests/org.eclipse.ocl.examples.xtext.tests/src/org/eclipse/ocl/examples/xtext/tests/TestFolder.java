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
package org.eclipse.ocl.examples.xtext.tests;

import org.eclipse.core.resources.IContainer;
import org.eclipse.jdt.annotation.NonNull;

/**
 * A TestProject provides polymorphism between writeable project areas for a variety of test hartnesses.
 */
public interface TestFolder extends TestFile
{

	/**
	 * Return the Eclipse IContainer behind this TestFolder.
	 *
	 * @throws IllegalStateException if not an Eclipse file system
	 */
	@NonNull IContainer getIContainer();
}