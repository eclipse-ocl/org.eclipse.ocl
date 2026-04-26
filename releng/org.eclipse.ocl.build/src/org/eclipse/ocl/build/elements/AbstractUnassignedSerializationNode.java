/*******************************************************************************
 * Copyright (c) 2020, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.build.elements;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.serializer.GrammarCardinality;

public abstract class AbstractUnassignedSerializationNode extends AbstractSerializationNode
{
	/**
	 * The produced EClass wrt this and preceding nodes as initially defined by the rule's returned EClass and refined by Actions.
	 */
	protected final @NonNull EClassifier producedEClassifier;

	protected AbstractUnassignedSerializationNode(@NonNull EClassifier producedEClassifier, @NonNull GrammarCardinality grammarCardinality) {
		super(grammarCardinality);
		this.producedEClassifier = producedEClassifier;
	}

	public @NonNull EClassifier getProducedEClassifier() {
		return producedEClassifier;
	}
}