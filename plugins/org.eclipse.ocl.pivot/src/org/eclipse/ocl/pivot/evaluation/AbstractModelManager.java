/*******************************************************************************
 * Copyright (c) 2016, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.evaluation;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * The abstract implementation of the ModelManager API for Objects supports use of EObject as the basis of models.
 * Derived or future implementations may support plain Java Objects or optimized object representations.
 *
 * @since 1.1
 */
public abstract class AbstractModelManager implements ModelManager.ModelManagerExtension
{
	/**
	 * @since 1.7
	 */
	public static int CONSTRUCTION_COUNT = 0;

	public AbstractModelManager() {
		CONSTRUCTION_COUNT++;
	}

	@Override
	public void dispose() {}

	@SuppressWarnings("null")
	@Override
	public @NonNull TreeIterator<? extends Object> eAllContents(@NonNull Object object) {
		return ((EObject)object).eAllContents();
	}

	@SuppressWarnings("null")
	@Override
	public @NonNull EClass eClass(@NonNull Object object) {
		return ((EObject)object).eClass();
	}

	@Override
	public @Nullable Object eContainer(@NonNull Object object) {
		return ((EObject)object).eContainer();
	}

	@Override
	public @Nullable Object eGet(@NonNull Object object, @NonNull EStructuralFeature eFeature) {
		return ((EObject)object).eGet(eFeature);
	}

	/**
	 * The boxed value of each foreign property of each object. null values are represented by ValueUtil.NULL_VALUE.
	 */
	private @Nullable Map<@NonNull PropertyId, @NonNull Map<@NonNull Object, @NonNull Object>> foreignPropertyId2object2value = null;

	/**
	 * The boxed value of each static property. null values are represented by ValueUtil.NULL_VALUE.
	 */
	private @Nullable Map<@NonNull PropertyId, @NonNull Object> staticPropertyId2value = null;

	/**
	 * @since 1.18
	 */
	@Override
	public @Nullable Object basicGetForeignPropertyValue(@Nullable Object object, @NonNull PropertyId propertyId) {
		if (object != null) {
			Map<@NonNull PropertyId, @NonNull Map<@NonNull Object, @NonNull Object>> foreignPropertyId2object2value2 = foreignPropertyId2object2value;
			if (foreignPropertyId2object2value2 == null) {
				return null;
			}
			Map<@NonNull Object, @NonNull Object> object2value = foreignPropertyId2object2value2.get(propertyId);
			if (object2value == null) {
				return null;
			}
			Object value = object2value.get(object);
			return value;
		}
		else {
			Map<@NonNull PropertyId, @NonNull Object> staticPropertyId2value2 = staticPropertyId2value;
			if (staticPropertyId2value2 == null) {
				return null;
			}
			Object value = staticPropertyId2value2.get(propertyId);
			return value;
		}
	}

	/**
	 * @since 1.18
	 */
	@Override
	public @Nullable Object getForeignPropertyValue(@Nullable Object object, @NonNull PropertyId propertyId, @Nullable OCLExpression initExpression, @Nullable Object defaultValue) {
		if (object != null) {
			Map<@NonNull PropertyId, @NonNull Map<@NonNull Object, @NonNull Object>> foreignPropertyId2object2value2 = foreignPropertyId2object2value;
			if (foreignPropertyId2object2value2 == null) {
				foreignPropertyId2object2value = foreignPropertyId2object2value2 = new HashMap<>();
			}
			Map<@NonNull Object, @NonNull Object> object2value = foreignPropertyId2object2value2.get(propertyId);
			if (object2value == null) {
				object2value = new HashMap<>();
				foreignPropertyId2object2value2.put(propertyId, object2value);
			}
			Object value = object2value.get(object);
			if (value == null) {
				if (initExpression != null) {
					Executor executor = PivotUtil.getExecutor(null, this);
					value = executor.evaluate(initExpression);
				}
				else {
					value = defaultValue;
				}
				if (value == null) {
					value = ValueUtil.NULL_VALUE;
				}
				object2value.put(object, value);
			}
			return value;
		}
		else {
			Map<@NonNull PropertyId, @NonNull Object> staticPropertyId2value2 = staticPropertyId2value;
			if (staticPropertyId2value2 == null) {
				staticPropertyId2value = staticPropertyId2value2 = new HashMap<>();
			}
			Object value = staticPropertyId2value2.get(propertyId);
			if (value == null) {
				if (initExpression != null) {
					Executor executor = PivotUtil.getExecutor(null, this);
					value = executor.evaluate(initExpression);
				}
				else {
					value = defaultValue;
				}
				if (value == null) {
					value = ValueUtil.NULL_VALUE;
				}
				staticPropertyId2value2.put(propertyId, value);
			}
			return value;
		}
	}

	/**
	 * @since 1.18
	 */
	@Override
	public @Nullable Object setForeignPropertyValue(@Nullable Object object, @NonNull PropertyId propertyId, @NonNull Object value) {
		if (object != null) {
			Map<@NonNull PropertyId, @NonNull Map<@NonNull Object, @NonNull Object>> foreignPropertyId2object2value2 = foreignPropertyId2object2value;
			if (foreignPropertyId2object2value2 == null) {
				foreignPropertyId2object2value = foreignPropertyId2object2value2 = new HashMap<>();
			}
			Map<@NonNull Object, @NonNull Object> object2value = foreignPropertyId2object2value2.get(propertyId);
			if (object2value == null) {
				object2value = new HashMap<>();
				foreignPropertyId2object2value2.put(propertyId, object2value);
			}
			return object2value.put(object, value);
		}
		else {
			Map<@NonNull PropertyId, @NonNull Object> staticPropertyId2value2 = staticPropertyId2value;
			if (staticPropertyId2value2 == null) {
				staticPropertyId2value = staticPropertyId2value2 = new HashMap<>();
			}
			return staticPropertyId2value2.put(propertyId, value);
		}
	}
}
