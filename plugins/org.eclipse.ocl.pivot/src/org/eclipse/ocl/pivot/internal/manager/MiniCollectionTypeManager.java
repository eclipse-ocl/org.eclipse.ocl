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
public abstract class MiniCollectionTypeManager
{
	private static final Logger logger = Logger.getLogger(MiniCollectionTypeManager.class);

	protected final boolean useWeakReferences;

	/*
	 * Map from CollectionTypeId to Map specialization optionally using weak references.
	 */
	private final @NonNull Map<@NonNull CollectionTypeId, @NonNull Object> collectionTypes;

	protected MiniCollectionTypeManager(boolean useWeakReferences) {
		this.useWeakReferences = useWeakReferences;
		this.collectionTypes = useWeakReferences ? new WeakHashMap<>() : new HashMap<>();
	}

	protected abstract void addOrphanClass(@NonNull CollectionType collectionType);

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

/*	protected @NonNull MapType createSpecialization(@NonNull MapTypeParameters<@NonNull Type, @NonNull Type> typeParameters) {
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
		TemplateParameterSubstitution keyTemplateParameterSubstitution = CompleteInheritanceImpl.createTemplateParameterSubstitution(keyFormalParameter, keyType);
		TemplateParameterSubstitution valueTemplateParameterSubstitution = CompleteInheritanceImpl.createTemplateParameterSubstitution(valueFormalParameter, valueType);
		templateBinding.getOwnedSubstitutions().add(keyTemplateParameterSubstitution);
		templateBinding.getOwnedSubstitutions().add(valueTemplateParameterSubstitution);
		specializedMapType.getOwnedBindings().add(templateBinding);
		resolveSuperClasses(specializedMapType, unspecializedType);
		specializedMapType.setKeysAreNullFree(typeParameters.isKeysAreNullFree());
		specializedMapType.setValuesAreNullFree(typeParameters.isValuesAreNullFree());
		specializedMapType.setUnspecializedElement(unspecializedType);
		specializedMapType.setEntryClass(typeParameters.getEntryClass());
		addOrphanClass(specializedMapType);
		return specializedMapType;
	} */

	protected @NonNull CollectionType createSpecialization(@NonNull CollectionTypeParameters<@NonNull Type> typeParameters) {
		CollectionType unspecializedType = getUnspecializedType();
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
		resolveSuperClasses(specializedCollectionType, unspecializedType);
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
		addOrphanClass(specializedCollectionType);
		return specializedCollectionType;
	}

	public void dispose() {
		collectionTypes.clear();
	}

	public @NonNull CollectionType getCollectionType(@NonNull CollectionTypeParameters<@NonNull Type> typeParameters) {
		CollectionTypeId collectionTypeId = typeParameters.getCollectionTypeId();
		synchronized (collectionTypes) {
			CollectionType specializedType = basicGetCollectionType(collectionTypeId);
			if (specializedType == null) {
				specializedType = createSpecialization(typeParameters);
				collectionTypes.put(collectionTypeId, useWeakReferences ? new WeakReference<@Nullable CollectionType>(specializedType) : specializedType);
			}
			return specializedType;
		}
	}

	protected abstract @NonNull CollectionType getUnspecializedType();

	protected abstract void resolveSuperClasses(@NonNull CollectionType specializedCollectionType, @NonNull CollectionType unspecializedCollectionType);

/*	@Override
	public void resolveSuperClasses(org.eclipse.ocl.pivot.@NonNull Class specializedClass, org.eclipse.ocl.pivot.@NonNull Class unspecializedClass) {
		List<TemplateBinding> specializedTemplateBindings = specializedClass.getOwnedBindings();
		for (org.eclipse.ocl.pivot.Class superClass : unspecializedClass.getSuperClasses()) {
			List<TemplateBinding> superTemplateBindings = superClass.getOwnedBindings();
			if (superTemplateBindings.size() > 0) {
				List<TemplateParameterSubstitution> superSpecializedTemplateParameterSubstitutions = new ArrayList<TemplateParameterSubstitution>();
				for (TemplateBinding superTemplateBinding : superTemplateBindings) {
					for (TemplateParameterSubstitution superParameterSubstitution : superTemplateBinding.getOwnedSubstitutions()) {
						TemplateParameterSubstitution superSpecializedTemplateParameterSubstitution = null;
						Type superActual = superParameterSubstitution.getActual();
						for (TemplateBinding specializedTemplateBinding : specializedTemplateBindings) {
							for (TemplateParameterSubstitution specializedParameterSubstitution : specializedTemplateBinding.getOwnedSubstitutions()) {
								if (specializedParameterSubstitution.getFormal() == superActual) {
									Type specializedActual = ClassUtil.nonNullModel(specializedParameterSubstitution.getActual());
									TemplateParameter superFormal = ClassUtil.nonNullModel(superParameterSubstitution.getFormal());
									superSpecializedTemplateParameterSubstitution = PivotUtil.createTemplateParameterSubstitution(superFormal, specializedActual);
									break;
								}
							}
							if (superSpecializedTemplateParameterSubstitution != null) {
								break;
							}
						}
						if (superSpecializedTemplateParameterSubstitution != null) {
							superSpecializedTemplateParameterSubstitutions.add(superSpecializedTemplateParameterSubstitution);
						}
					}
				}
				org.eclipse.ocl.pivot.@NonNull Class unspecializedSuperClass = PivotUtil.getUnspecializedTemplateableElement(superClass);
				CompleteClassInternal superCompleteClass = environmentFactory.getMetamodelManager().getCompleteClass(unspecializedSuperClass);
				org.eclipse.ocl.pivot.Class superPivotClass = superCompleteClass.getPrimaryClass();
				if (superPivotClass instanceof CollectionType) {
					if (superSpecializedTemplateParameterSubstitutions.size() == 1) {
						Type templateArgument = superSpecializedTemplateParameterSubstitutions.get(0).getActual();
						if (templateArgument != null) {
							org.eclipse.ocl.pivot.Class specializedSuperClass = /*completeEnvironment.* /getCollectionType(superCompleteClass, TypeUtil.createCollectionTypeParameters(templateArgument, false, null, null));
							specializedClass.getSuperClasses().add(specializedSuperClass);
						}
					}
				}
				else {
					List<@NonNull Type> superTemplateArgumentList = new ArrayList<@NonNull Type>(superSpecializedTemplateParameterSubstitutions.size());
					for (TemplateParameterSubstitution superSpecializedTemplateParameterSubstitution : superSpecializedTemplateParameterSubstitutions) {
						Type actual = superSpecializedTemplateParameterSubstitution.getActual();
						if (actual != null) {
							superTemplateArgumentList.add(actual);
						}
					}
					org.eclipse.ocl.pivot.Class specializedSuperType = superCompleteClass.getSpecializedType(superTemplateArgumentList);
					specializedClass.getSuperClasses().add(specializedSuperType);
				}
			}
			else {
				specializedClass.getSuperClasses().add(superClass);
			}
		}
	} */
}