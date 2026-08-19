/*******************************************************************************
 * Copyright (c) 2012, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	 E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.ids;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.Nameable;

/**
 * A TemplateParameterId provides a unique identifier for a template parameter such as Set<T> or product<T>(...).
 *
 * Template parameters form a simple sequence throughout the nesting hierarchy. This for Collection<T>::selectByType<TT>(..) the
 * first template parameter is Collection<T>::T and whose template parameter id is that of a first template parameter. This is
 * available as the preallocated constant TypeId::T_1.The second template parameter is Collection<T>::selectByType<TT>(..)::TT.
 * Its template parameter id is available  as the preallocated constant TypeId::T_2.
 */
public interface TemplateParameterId extends TypeId, Nameable
{
	/**
	 * Return the TypeIds that constrain this TemplateParameterId.
	 * 
	 * @since 7.0
	 */
	@NonNull TypeId @NonNull [] getConstrainingTypeIds();

	/**
	 * Return the index of this TemplateParameterId in the contextual hierarchy of TemplateParameters.
	 */
	int getIndex();

	/**
	 * Return the name of this TemplateParameterId computed from the index and constrainingTypeIds.
	 */
	@Override
	@NonNull String getName();
	
	/**
	 * Return the GeneralizedTypeId that scopes this TemplateParameterId, or null for a NormalizedTemplateParameter,
	 * or unparameterized type.
	 * 
	 * @since 1.18
	 */
	default @Nullable TemplateableId getTemplateableId() { return null; }
}