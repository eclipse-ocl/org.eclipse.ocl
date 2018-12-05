/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ids;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.TemplateableId;

public abstract class AbstractGeneralizedIdImpl<T extends TemplateableId> extends AbstractTemplateableIdImpl<T>
{
	protected final @NonNull String name;

	protected AbstractGeneralizedIdImpl(@NonNull Integer hashCode, int templateParameters, @NonNull String name) {
		super(hashCode, templateParameters);
		this.name = name;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}
}