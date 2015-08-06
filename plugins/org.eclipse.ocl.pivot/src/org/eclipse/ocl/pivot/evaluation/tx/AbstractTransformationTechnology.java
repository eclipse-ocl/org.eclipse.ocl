/*******************************************************************************
 * Copyright (c) 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.evaluation.tx;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.evaluation.tx.TransformationTechnologyContribution;

/**
 * AbstractTransformationTechnology provides a partial implementation of TransformationTechnology
 * that is to be extended by all TransformationTechnology implementations.
 * 
 * @since 1.1
 */
public abstract class AbstractTransformationTechnology implements TransformationTechnology, TransformationTechnologyContribution
{
	protected final @NonNull String name;
	
	protected AbstractTransformationTechnology(@NonNull String name) {
		this.name = name;
	}

	@Override
	public @NonNull TransformationTechnologyContribution getContribution() {
		return this;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}
	
	@Override
	public @NonNull TransformationTechnology getTransformationTechnology() {
		return this;
	}
}
