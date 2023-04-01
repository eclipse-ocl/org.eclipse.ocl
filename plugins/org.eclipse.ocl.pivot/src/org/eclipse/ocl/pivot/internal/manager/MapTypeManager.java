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

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.MapTypeParameters;

/**
 * MapTypeManager abstracts the legacy CompleteClasses.MapCompleteClassImpl functionality for re-use by an Orphanage.
 */
public abstract class MapTypeManager
{
	protected final boolean useWeakReferences;

	/*
	 * Map from MapTypeId to Map specialization optionally using weak references.
	 */
	private final @NonNull Map<@NonNull MapTypeId, @NonNull Object> mapTypes;

	protected MapTypeManager(boolean useWeakReferences) {
		this.useWeakReferences = useWeakReferences;
		this.mapTypes = useWeakReferences ? new WeakHashMap<>() : new HashMap<>();
	}

	protected abstract void addOrphanClass(@NonNull MapType mapType);

	public synchronized @Nullable MapType basicGetMapType(@NonNull MapTypeId mapTypeId) {
		if (!useWeakReferences) {
			return (MapType)mapTypes.get(mapTypeId);
		}
		@SuppressWarnings("unchecked")
		WeakReference<@NonNull MapType> ref = (WeakReference<@NonNull MapType>)mapTypes.get(mapTypeId);
		if (ref == null) {
			return null;
		}
		MapType mapType = ref.get();
		if (mapType != null) {
			Type keyType = mapType.getKeyType();
			Type valueType = mapType.getValueType();
			if ((keyType != null) && (valueType != null) && (keyType.eResource() != null) && (valueType.eResource() != null)) {
				return mapType;
			}
			mapType = null;		// Eliminate entry for stale type
			ref.clear();
		}
		mapTypes.remove(mapTypeId);
		return null;
	}

	protected @NonNull MapType createSpecialization(@NonNull MapTypeParameters<@NonNull Type, @NonNull Type> typeParameters) {
		MapType unspecializedType = getUnspecializedType();
		String typeName = unspecializedType.getName();
		TemplateSignature templateSignature = unspecializedType.getOwnedSignature();
		List<TemplateParameter> templateParameters = templateSignature.getOwnedParameters();
		EClass eClass = unspecializedType.eClass();
		EFactory eFactoryInstance = eClass.getEPackage().getEFactoryInstance();
		MapType specializedMapType = (MapType) eFactoryInstance.create(eClass);
		specializedMapType.setName(typeName);
		TemplateBinding templateBinding = PivotFactory.eINSTANCE.createTemplateBinding();
		TemplateParameter keyFormalParameter = templateParameters.get(0);
		TemplateParameter valueFormalParameter = templateParameters.get(1);
		assert keyFormalParameter != null;
		assert valueFormalParameter != null;
		Type keyType = typeParameters.getKeyType();
		Type valueType = typeParameters.getValueType();
		TemplateParameterSubstitution keyTemplateParameterSubstitution = PivotUtil.createTemplateParameterSubstitution(keyFormalParameter, keyType);
		TemplateParameterSubstitution valueTemplateParameterSubstitution = PivotUtil.createTemplateParameterSubstitution(valueFormalParameter, valueType);
		templateBinding.getOwnedSubstitutions().add(keyTemplateParameterSubstitution);
		templateBinding.getOwnedSubstitutions().add(valueTemplateParameterSubstitution);
		specializedMapType.getOwnedBindings().add(templateBinding);
	//	resolveSuperClasses(specializedMapType, unspecializedType);
		specializedMapType.getSuperClasses().addAll(unspecializedType.getSuperClasses());
		specializedMapType.setKeysAreNullFree(typeParameters.isKeysAreNullFree());
		specializedMapType.setValuesAreNullFree(typeParameters.isValuesAreNullFree());
		specializedMapType.setUnspecializedElement(unspecializedType);
		specializedMapType.setEntryClass(typeParameters.getEntryClass());
		addOrphanClass(specializedMapType);
		return specializedMapType;
	}

	public void dispose() {
		mapTypes.clear();
	}

	public @NonNull MapType getMapType(@NonNull MapTypeParameters<@NonNull Type, @NonNull Type> typeParameters) {
		MapTypeId mapTypeId = typeParameters.getMapTypeId();
		synchronized (mapTypes) {
			MapType specializedType = basicGetMapType(mapTypeId);
			if (specializedType == null) {
				specializedType = createSpecialization(typeParameters);
				mapTypes.put(mapTypeId, useWeakReferences ? new WeakReference<@Nullable MapType>(specializedType) : specializedType);
			}
			return specializedType;
		}
	}

	protected abstract @NonNull MapType getUnspecializedType();

//	protected abstract void resolveSuperClasses(@NonNull MapType specializedMapType, @NonNull MapType unspecializedMapType);
}