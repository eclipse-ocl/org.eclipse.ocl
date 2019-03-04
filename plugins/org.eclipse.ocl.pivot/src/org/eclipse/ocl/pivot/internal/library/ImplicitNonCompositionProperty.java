/*******************************************************************************
 * Copyright (c) 2011, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.library.AbstractProperty;
import org.eclipse.ocl.pivot.values.InvalidValueException;

import com.google.common.collect.Iterables;

/**
 * An instance of ImplicitNonCompositionProperty supports evaluation of an implicit (unnavigable opposite) property
 * searching the ModelManager's cache of opposite types for the one that has the source as an opposite opposite target.
 *
 * This includes the implicit opposite for a UML extension_XXXX in a statically compiled profile.
 */
public class ImplicitNonCompositionProperty extends AbstractProperty
{
	protected @NonNull Property property;

	public ImplicitNonCompositionProperty(@NonNull Property property) {
		this.property = property;
	}

	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
		if (sourceValue == null) {
			return null;
		}
		ModelManager.ModelManagerExtension2 modelManager = (ModelManager.ModelManagerExtension2)executor.getModelManager();
		Iterable<@NonNull Object> results = modelManager.getOpposite(property, sourceValue);
		if (property.isIsMany()) {
			return executor.getIdResolver().createCollectionOfAll(PivotConstantsInternal.DEFAULT_IMPLICIT_OPPOSITE_ORDERED,
				PivotConstantsInternal.DEFAULT_IMPLICIT_OPPOSITE_UNIQUE, returnTypeId, results);
		}
		int size = Iterables.size(results);
		if (size == 0) {
			return null;
		}
		else if (size == 1) {
			return results.iterator().next();
		}
		else {
			throw new InvalidValueException("Multiple opposites for " + property);
		}
	}
}