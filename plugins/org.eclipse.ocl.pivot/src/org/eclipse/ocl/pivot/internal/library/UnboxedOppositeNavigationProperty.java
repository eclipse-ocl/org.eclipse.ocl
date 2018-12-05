/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
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
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractProperty;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

public class UnboxedOppositeNavigationProperty extends AbstractProperty
{
	protected @NonNull PropertyId oppositePropertyId;
	
	public UnboxedOppositeNavigationProperty(@NonNull PropertyId oppositePropertyId) {
		this.oppositePropertyId = oppositePropertyId;
	}
	
	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
		IdResolver idResolver = executor.getIdResolver();
		Property oppositeProperty = idResolver.getProperty(oppositePropertyId);		
		ModelManager.ModelManagerExtension modelManager = (ModelManager.ModelManagerExtension)executor.getModelManager();
		Type thatType = ClassUtil.nonNullModel(oppositeProperty.getType());
		if (thatType instanceof CollectionType) {
			thatType = ((CollectionType)thatType).getElementType();
		}
		List<Object> results = new ArrayList<Object>();
		if (thatType instanceof org.eclipse.ocl.pivot.Class) {
			for (@NonNull Object eObject : modelManager.get((org.eclipse.ocl.pivot.Class)thatType)) {	// FIXME Use a cache
				EClass eClass = modelManager.eClass(eObject);
				EStructuralFeature eFeature = eClass.getEStructuralFeature(oppositeProperty.getName());
				assert eFeature != null;
				Object eGet = modelManager.eGet(eObject, eFeature);
				if (eGet == sourceValue) {
					results.add(eObject);
				}
			}
		}
		return results;
	}
}
