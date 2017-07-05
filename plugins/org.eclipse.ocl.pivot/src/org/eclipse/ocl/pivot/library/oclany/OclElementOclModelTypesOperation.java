/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library.oclany;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractUnaryOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * OclElementOclModelTypesOperation realises the OclElement::oclModelTypes() library operation.
 *
 * @since 1.1
 */
public class OclElementOclModelTypesOperation extends AbstractUnaryOperation
{
	public static final @NonNull OclElementOclModelTypesOperation INSTANCE = new OclElementOclModelTypesOperation();

	/**
	 * @since 1.1
	 */
	@Override
	public @NonNull CollectionValue evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceVal) {
		if (sourceVal instanceof InvalidValueException) {
			throw (InvalidValueException)sourceVal;
		}
		IdResolver.IdResolverExtension idResolver = (IdResolver.IdResolverExtension)executor.getIdResolver();
		Type sourceType = idResolver.getDynamicTypeOf(sourceVal);
		if (sourceVal == null) {
			throw new InvalidValueException(PivotMessages.NullNavigation, "source value", "oclModelTypes");
		}
		@SuppressWarnings("null")
		Iterable<org.eclipse.ocl.pivot.@Nullable Class> modelClasses = (Iterable<org.eclipse.ocl.pivot.@Nullable Class>)idResolver.getModelClassesOf(sourceVal);
		if (modelClasses == null) {
			throw new InvalidValueException(PivotMessages.IncompatibleModelType, sourceType);
		}
		return idResolver.createSetOfAll((@NonNull CollectionTypeId) returnTypeId, modelClasses);
	}
}
