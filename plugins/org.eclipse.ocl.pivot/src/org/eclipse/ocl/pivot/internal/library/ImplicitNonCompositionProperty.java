/*******************************************************************************
 * Copyright (c) 2011, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.library.AbstractProperty;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * The static instance of ImplicitNonCompositionProperty supports evaluation of
 * implicit properties for opposites of non-composition relationships.
 */
public class ImplicitNonCompositionProperty extends AbstractProperty
{
	protected @NonNull Property property;
	
	public ImplicitNonCompositionProperty(@NonNull Property property) {
		this.property = property;
	}
	
	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
		ModelManager.ModelManagerExtension modelManager = (ModelManager.ModelManagerExtension)executor.getModelManager();
		Property thatProperty = property.getOpposite();
		Type thatType = ClassUtil.nonNullModel(property.getType());
		boolean isMany = thatType instanceof CollectionType;
		if (isMany) {
			thatType = ((CollectionType)thatType).getElementType();
		}
		List<Object> results = new ArrayList<Object>();
		if (thatType instanceof org.eclipse.ocl.pivot.Class) {
			for (@NonNull Object eObject : modelManager.get((org.eclipse.ocl.pivot.Class)thatType)) {
				EClass eClass = modelManager.eClass(eObject);
				EStructuralFeature eFeature = eClass.getEStructuralFeature(thatProperty.getName());
				assert eFeature != null;
				Object eGet = modelManager.eGet(eObject, eFeature);
				if (eFeature.isMany()) {
					assert eGet != null;
					for (Object eReference : (List<?>)eGet) {
						if (eReference == sourceValue) {
							results.add(eObject);
						}
					}
				}
				else {
					if (eGet == sourceValue) {
						results.add(eObject);
					}
				}
			}
		}
		if (isMany) {
			return executor.getIdResolver().createCollectionOfAll(PivotConstantsInternal.DEFAULT_IMPLICIT_OPPOSITE_ORDERED,
				PivotConstantsInternal.DEFAULT_IMPLICIT_OPPOSITE_UNIQUE, returnTypeId, results);
		}
		else if (results.size() == 0) {
			return null;
		}
		else if (results.size() == 1) {
			return results.get(0);
		}
		else {
			throw new InvalidValueException("Multiple opposites for " + property);
		}
	}
}