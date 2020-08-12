/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.cs2text.runtime;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;

public abstract class RTSerializationAbstractFeatureStep extends RTAbstractSerializationStep
{
	protected final @NonNull EStructuralFeature eStructuralFeature;

	public RTSerializationAbstractFeatureStep(@Nullable CardinalityVariable variable, @NonNull EStructuralFeature eStructuralFeature) {
		super(variable);
		this.eStructuralFeature = eStructuralFeature;
	}

	protected boolean equalTo(@NonNull RTSerializationAbstractFeatureStep that) {
		return super.equalTo(that) && eStructuralFeature.equals(that.eStructuralFeature);
	}

	@Override
	public int hashCode() {
		return super.hashCode() + 3 * eStructuralFeature.hashCode();
	}
}