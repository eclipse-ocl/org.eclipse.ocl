/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.flat;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.StandardLibrary;

public abstract class AbstractFlatModel implements FlatModel		// XXX FIXME immutable metamodels
{
	protected final @NonNull StandardLibrary standardLibrary;
	protected final @NonNull String name;
//	private final @NonNull Map<@NonNull TemplateParameter, @NonNull FlatClass> flatTemplateParameters =  new HashMap<>();

	protected AbstractFlatModel(@NonNull StandardLibrary standardLibrary, @NonNull String name) {
		this.standardLibrary = standardLibrary;
		this.name = name;
	}

/*	public @NonNull FlatClass getFlatClass(@NonNull TemplateParameter templateParameter) {
		FlatClass flatClass = flatTemplateParameters.get(templateParameter);
		if (flatClass == null) {
			flatClass = new PartialFlatClass(this, templateParameter);		// XXX
			flatTemplateParameters.put(templateParameter, flatClass);
		}
		return flatClass;
	} */

	@Override
	public @NonNull String getName() {
		return name;
	}

	@Override
	public @NonNull StandardLibrary getStandardLibrary() {
		return standardLibrary;
	}
}
