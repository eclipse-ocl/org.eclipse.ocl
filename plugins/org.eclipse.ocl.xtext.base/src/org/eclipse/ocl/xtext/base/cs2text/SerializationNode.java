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
package org.eclipse.ocl.xtext.base.cs2text;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public interface SerializationNode
{
	public @NonNull MultiplicativeCardinality getMultiplicativeCardinality();
	/**
	 * Return the required slots in disjunctive normal form.
	 */
	@NonNull RequiredSlots getRequiredSlots();
	boolean isNull();
	void preSerialize(@NonNull PreSerializer preSerializer);
	@Nullable ConsumedSlotsConjunction selectSerializedNodes(@NonNull UserModelAnalysis modelAnalysis, @NonNull EObject element);
	void serialize(@NonNull ConsumedSlotsConjunction consumedSlotsConjunction, @NonNull SerializationBuilder serializationBuilder);
	void toString(@NonNull StringBuilder s, int depth);
}