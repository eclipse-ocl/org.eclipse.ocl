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

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.CollectionTypeParameters;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * MiniCollectionTypeManager abstracts the legacy CompleteClasses.CollectionCompleteClassImpl functionality for re-use by an Orphanage.
 */
public abstract class CollectionTypeManager extends AbstractTypeManager
{
	private static final Logger logger = Logger.getLogger(CollectionTypeManager.class);

	/*
	 * Map from CollectionTypeId to Map specialization optionally using weak references.
	 */
	private final @NonNull Map<@NonNull CollectionTypeId, @NonNull Object> collectionTypes;

	protected CollectionTypeManager(boolean useWeakReferences) {
		super(useWeakReferences);
		this.collectionTypes = useWeakReferences ? new WeakHashMap<>() : new HashMap<>();
	}

	public synchronized @Nullable CollectionType basicGetCollectionType(@NonNull CollectionTypeId collectionTypeId) {
		if (!useWeakReferences) {
			return (CollectionType)collectionTypes.get(collectionTypeId);
		}
		@SuppressWarnings("unchecked")
		WeakReference<@NonNull CollectionType> ref = (WeakReference<@NonNull CollectionType>)collectionTypes.get(collectionTypeId);
		if (ref == null) {
			return null;
		}
		CollectionType collectionType = ref.get();
		if (collectionType != null) {
			Type elementType = collectionType.getElementType();
			if ((elementType != null) && (elementType.eResource() != null)) {		// If no GC pending
				return collectionType;
			}
			elementType = null;		// Eliminate entry for stale type
			ref.clear();
		}
		collectionTypes.remove(collectionTypeId);
		return null;
	}

	protected @NonNull CollectionType createSpecialization(@NonNull CollectionTypeParameters<@NonNull Type> typeParameters) {
		StandardLibrary standardLibrary = getStandardLibrary();
		CollectionTypeId genericTypeId = typeParameters.getGenericTypeId();
		CollectionType unspecializedType = standardLibrary.getCollectionType(genericTypeId);
		String typeName = unspecializedType.getName();
		TemplateSignature templateSignature = unspecializedType.getOwnedSignature();
		List<@NonNull TemplateParameter> templateParameters = ClassUtil.nullFree(templateSignature.getOwnedParameters());
		EClass eClass = unspecializedType.eClass();
		EFactory eFactoryInstance = eClass.getEPackage().getEFactoryInstance();
		CollectionType specializedCollectionType = (CollectionType) eFactoryInstance.create(eClass);
		specializedCollectionType.setName(typeName);
		TemplateBinding templateBinding = PivotFactory.eINSTANCE.createTemplateBinding();
		TemplateParameter formalParameter = ClassUtil.nonNull(templateParameters.get(0));
		assert formalParameter != null;
		Type elementType = typeParameters.getElementType();
		TemplateParameterSubstitution templateParameterSubstitution = PivotUtil.createTemplateParameterSubstitution(formalParameter, elementType);
		templateBinding.getOwnedSubstitutions().add(templateParameterSubstitution);
		specializedCollectionType.getOwnedBindings().add(templateBinding);
		standardLibrary.resolveSuperClasses(specializedCollectionType, unspecializedType);
	//	specializedCollectionType.getSuperClasses().addAll(unspecializedType.getSuperClasses());
		specializedCollectionType.setIsNullFree(typeParameters.isNullFree());
		try {
			specializedCollectionType.setLowerValue(typeParameters.getLower());
		} catch (InvalidValueException e) {
			logger.error("Out of range lower bound", e);
		}
		try {
			specializedCollectionType.setUpperValue(typeParameters.getUpper());
		} catch (InvalidValueException e) {
			logger.error("Out of range upper bound", e);
		}
		specializedCollectionType.setUnspecializedElement(unspecializedType);
		standardLibrary.addOrphanClass(specializedCollectionType);
		return specializedCollectionType;
	}

	@Override
	public void dispose() {
		collectionTypes.clear();
	}

	public @NonNull CollectionType getCollectionType(@NonNull CollectionTypeParameters<@NonNull Type> typeParameters) {
		CollectionTypeId collectionTypeId = typeParameters.getSpecializedTypeId();
		synchronized (collectionTypes) {
			CollectionType specializedType = basicGetCollectionType(collectionTypeId);
			if (specializedType == null) {
				specializedType = createSpecialization(typeParameters);
				collectionTypes.put(collectionTypeId, useWeakReferences ? new WeakReference<@Nullable CollectionType>(specializedType) : specializedType);
			}
			return specializedType;
		}
	}

	@Override
	protected abstract @NonNull StandardLibrary getStandardLibrary();
}