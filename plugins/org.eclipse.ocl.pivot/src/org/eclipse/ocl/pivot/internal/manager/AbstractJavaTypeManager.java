/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.executor.JavaType;
import org.eclipse.ocl.pivot.manager.JavaTypeManager;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * AbstractJavaTypeManager encapsulates the knowledge about known java types.
 *
 * @since 7.0
 */
public abstract class AbstractJavaTypeManager implements JavaTypeManager
{
	protected final @NonNull StandardLibrary standardLibrary;
	protected final @NonNull Map<@NonNull Object, org.eclipse.ocl.pivot.@NonNull Class> key2class = new HashMap<>();	// Concurrent puts are duplicates

	public AbstractJavaTypeManager(@NonNull StandardLibrary standardLibrary) {
		this.standardLibrary = standardLibrary;
	}

	@Override
	public void dispose() {
		key2class.clear();
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Class getBehavioralClass(@NonNull Class<?> javaClass) {
		if (javaClass == boolean.class) {
			return standardLibrary.getBooleanType();
		}
		if (javaClass == byte.class) {
			return standardLibrary.getIntegerType();
		}
		if (javaClass == char.class) {
			return standardLibrary.getIntegerType();
		}
		if (javaClass == double.class) {
			return standardLibrary.getRealType();
		}
		if (javaClass == float.class) {
			return standardLibrary.getRealType();
		}
		if (javaClass == int.class) {
			return standardLibrary.getIntegerType();
		}
		if (javaClass == long.class) {
			return standardLibrary.getIntegerType();
		}
		if (javaClass == short.class) {
			return standardLibrary.getIntegerType();
		}
		if (javaClass == BigDecimal.class) {
			return standardLibrary.getRealType();
		}
		if (javaClass == BigInteger.class) {
			return standardLibrary.getIntegerType();
		}
		if (javaClass == Boolean.class) {
			return standardLibrary.getBooleanType();
		}
		if (javaClass == Byte.class) {
			return standardLibrary.getIntegerType();
		}
		if (javaClass == Character.class) {
			return standardLibrary.getIntegerType();
		}
		if (javaClass == Double.class) {
			return standardLibrary.getRealType();
		}
		if (javaClass == Float.class) {
			return standardLibrary.getRealType();
		}
		if (javaClass == Integer.class) {
			return standardLibrary.getIntegerType();
		}
		if (javaClass == Long.class) {
			return standardLibrary.getIntegerType();
		}
		if (javaClass == Short.class) {
			return standardLibrary.getIntegerType();
		}
		if (javaClass == String.class) {
			return standardLibrary.getStringType();
		}
		return null;
	}

	@Override
	public final synchronized org.eclipse.ocl.pivot.@NonNull Class getJavaType(@NonNull Object object) {
		/*if (object instanceof ExecutorType) {					// XXX going obsolete  ?? use getTypeId()
			ExecutorType type = (ExecutorType)object;
			org.eclipse.ocl.pivot.Class asClass = key2class.get(type);
			if (asClass == null) {
				String metaclassName = type.getTypeId().getMetaclassName();
				asClass = standardLibrary.getLibraryClass(metaclassName);
				key2class.put(type, asClass);
			}
			return asClass;
		}
		else*/ if (object instanceof TypeId) {
			TypeId typeId = (TypeId)object;
			org.eclipse.ocl.pivot.Class asClass;
			asClass = key2class.get(typeId);
			if (asClass == null) {
				Type type = (Type)typeId.accept(standardLibrary.getIdResolver());
				if (type == null) {
					type = standardLibrary.getOclAnyType();
				}
				asClass = PivotUtil.getClass(type, standardLibrary);
				key2class.put(typeId, asClass);
			}
			return asClass;
		}
		else if (object instanceof java.lang.Class) {
			java.lang.Class<?> javaClass = (java.lang.Class<?>)object;
			org.eclipse.ocl.pivot.Class asClass = key2class.get(javaClass);
			if (asClass != null) {
				return asClass;
			}
			if (javaClass == Boolean.class) {
				asClass = standardLibrary.getBooleanType();
			}
			else if (javaClass == String.class) {
				asClass = standardLibrary.getStringType();
			}
			else if ((javaClass == BigDecimal.class) || (javaClass == Double.class) || (javaClass == Float.class)) {
				asClass = standardLibrary.getRealType();
			}
			else if ((javaClass == BigInteger.class) || (javaClass == Byte.class) || (javaClass == Integer.class) || (javaClass == Long.class) || (javaClass == Short.class)) {
				asClass = standardLibrary.getIntegerType();
			}
			else {
				asClass = new JavaType(javaClass);
			}
			key2class.put(javaClass, asClass);
			return asClass;
		}
		else if (object instanceof EObject) {
			EObject eObject = (EObject)object;
			@SuppressWarnings("null") @NonNull EClass eClass = eObject.eClass();
			org.eclipse.ocl.pivot.Class asClass = key2class.get(eClass);
			if (asClass == null) {
				asClass = standardLibrary.getIdResolver().getFlatClass(eClass).getPivotClass();
				key2class.put(eClass, asClass);
			}
			return asClass;
		}
		throw new UnsupportedOperationException();
	}
}