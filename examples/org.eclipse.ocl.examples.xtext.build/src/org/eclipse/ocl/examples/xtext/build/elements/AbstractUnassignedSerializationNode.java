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
package org.eclipse.ocl.examples.xtext.build.elements;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;

public abstract class AbstractUnassignedSerializationNode extends AbstractSerializationNode
{
	/**
	 * The produced EClass wrt this and preceding nodes as initially defined by the rule's returned EClass and refined by Actions.
	 */
	protected final @NonNull EClass producedEClass;

	protected AbstractUnassignedSerializationNode(@NonNull EClass producedEClass, @NonNull GrammarCardinality grammarCardinality) {
		super(grammarCardinality);
		this.producedEClass = producedEClass;
	}

	public @NonNull EClass getProducedEClass() {
		return producedEClass;
	}
}