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
package org.eclipse.ocl.xtext.base.cs2text.xtext;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;

public class EReferenceData
{
	protected final @NonNull EReference eReference;
	protected final @NonNull IndexVector parserRuleValueIndexes;

	public EReferenceData(/*@NonNull*/ EReference eReference, @NonNull IndexVector parserRuleValueIndexes) {
		assert eReference != null;
		this.eReference = eReference;
		this.parserRuleValueIndexes = parserRuleValueIndexes;
	}

	public @NonNull EReference getEReference() {
		return eReference;
	}

	public @NonNull IndexVector getAssignedTargetRuleValues() {
		return parserRuleValueIndexes;
	}

//	@Override
//	public @NonNull String getName() {
//		return name;
//	}

//	public @NonNull SerializationRule @NonNull [] getSerializationRules() {
//		return serializationRules;
//	}

//	@Override
//	public @NonNull String toString() {
//		return name;
//	}
}