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

/**
 * The RequiredSlots are a disjunction (alternatives) of conjunctions of SimpleRequiredSlot identifying the cardinality for a single
 * assigned feature.
 */
public interface ConsumedSlots
{
//	@NonNull Iterable<@NonNull ConsumedSlots> getConjunctions();
//	@NonNull Iterable<@NonNull SimpleRequiredSlot> getSimpleRequiredSlots();
	boolean isNull();
}