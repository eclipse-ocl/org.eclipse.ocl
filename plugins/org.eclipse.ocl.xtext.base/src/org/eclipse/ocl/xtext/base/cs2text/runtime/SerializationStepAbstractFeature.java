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

public abstract class SerializationStepAbstractFeature extends SerializationStepAbstract
{
	protected final @NonNull EStructuralFeature eStructuralFeature;

	protected SerializationStepAbstractFeature(int variableIndex, /*@NonNull*/ EStructuralFeature eStructuralFeature) {
		super(variableIndex);
		assert eStructuralFeature != null;
		this.eStructuralFeature = eStructuralFeature;
	}

	protected boolean equalTo(@NonNull SerializationStepAbstractFeature that) {
		return super.equalTo(that) && eStructuralFeature.equals(that.eStructuralFeature);
	}

	public @NonNull EStructuralFeature getEStructuralFeature() {
		return eStructuralFeature;
	}

	@Override
	public int hashCode() {
		return super.hashCode() + 3 * eStructuralFeature.hashCode();
	}
}