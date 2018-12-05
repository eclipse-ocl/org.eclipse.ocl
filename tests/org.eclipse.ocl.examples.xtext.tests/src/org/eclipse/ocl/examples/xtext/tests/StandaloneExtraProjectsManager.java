/*******************************************************************************
 * Copyright (c) 2017, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.tests;

import java.util.Arrays;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;

/**
 * A StandaloneExtraProjectsManager extends a StandaloneProjectMap to add further classpath entries; typically for a writeable test area.
 */
public class StandaloneExtraProjectsManager extends StandaloneProjectMap
{
	protected final @NonNull String @NonNull[] extraClassPathEntries;

	public StandaloneExtraProjectsManager(@NonNull String @NonNull... extraClassPathEntries) {
		super(false);
		this.extraClassPathEntries = extraClassPathEntries;
	}

	@Override
	protected @NonNull String @NonNull [] getClassPathEntries() {
		@NonNull String[] classPathEntries = super.getClassPathEntries();
		int length = classPathEntries.length;
		int extraLength = extraClassPathEntries.length;
		@NonNull String[] extendedClassPathEntries = Arrays.copyOf(classPathEntries, length+extraLength);
		System.arraycopy(extraClassPathEntries, 0, extendedClassPathEntries, length, extraLength);
		return extendedClassPathEntries;
	}

}