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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Orphanage;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.internal.CollectionTypeImpl;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.CollectionTypeParameters;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * CollectionTypeManager manages the CollectionTypes created within an Orphanage.
 */
public class CollectionTypeManager extends AbstractTypeManager
{
	private static final Logger logger = Logger.getLogger(CollectionTypeManager.class);

	/*
	 * Map from CollectionTypeId to Map specialization.
	 */
	private final @NonNull Map<@NonNull CollectionTypeId, @NonNull CollectionType> typeId2collectionType;

	public CollectionTypeManager(@NonNull Orphanage orphanage) {
		super(orphanage);
		this.typeId2collectionType = new HashMap<>();
	}

	public synchronized @Nullable CollectionType basicGetCollectionType(@NonNull CollectionTypeId collectionTypeId) {
		return typeId2collectionType.get(collectionTypeId);
	}

	private @NonNull CollectionType createSpecialization(@NonNull CollectionTypeParameters<@NonNull Type> typeParameters) {
		CollectionType unspecializedType = typeParameters.getGenericType();
		String typeName = unspecializedType.getName();
		TemplateSignature templateSignature = unspecializedType.getOwnedSignature();
		List<@NonNull TemplateParameter> templateParameters = ClassUtil.nullFree(templateSignature.getOwnedParameters());
		EClass eClass = unspecializedType.eClass();
		EFactory eFactoryInstance = eClass.getEPackage().getEFactoryInstance();
		CollectionType specializedType = (CollectionType) eFactoryInstance.create(eClass);
		specializedType.setName(typeName);
		TemplateBinding templateBinding = PivotFactory.eINSTANCE.createTemplateBinding();
		TemplateParameter formalParameter = ClassUtil.nonNull(templateParameters.get(0));
		assert formalParameter != null;
		Type elementType = typeParameters.getElementType();
		TemplateParameterSubstitution templateParameterSubstitution = PivotUtil.createTemplateParameterSubstitution(formalParameter, elementType);
		templateBinding.getOwnedSubstitutions().add(templateParameterSubstitution);
		specializedType.getOwnedBindings().add(templateBinding);
		standardLibrary.resolveSuperClasses(specializedType, unspecializedType);
	//	specializedType.getSuperClasses().addAll(unspecializedType.getSuperClasses());
		specializedType.setIsNullFree(typeParameters.isNullFree());
		try {
			specializedType.setLowerValue(typeParameters.getLower());
		} catch (InvalidValueException e) {
			logger.error("Out of range lower bound", e);
		}
		try {
			specializedType.setUpperValue(typeParameters.getUpper());
		} catch (InvalidValueException e) {
			logger.error("Out of range upper bound", e);
		}
		specializedType.setUnspecializedElement(unspecializedType);
		specializedType.getTypeId();		// XXX
		String s = specializedType.toString();
//		System.out.println("createSpecialization: " + NameUtil.debugSimpleName(specializedType) + " : " + specializedType);
		if ("Collection(Families::FamilyMember[*|?])".equals(s)) {
			getClass();		// XXX
		}
		return specializedType;
	}

	@Override
	public void dispose() {
		typeId2collectionType.clear();
	}

	public @NonNull CollectionType getCollectionType(@NonNull CollectionTypeParameters<@NonNull Type> typeParameters) {
		CollectionTypeId collectionTypeId = typeParameters.getSpecializedTypeId();
		synchronized (typeId2collectionType) {
			CollectionType specializedType = basicGetCollectionType(collectionTypeId);
			if (specializedType == null) {
				specializedType = createSpecialization(typeParameters);
				Type elementType = specializedType.getElementType();
				assert (elementType != null) && (elementType.eResource() != null);
				typeId2collectionType.put(collectionTypeId, specializedType);
				assert collectionTypeId == ((CollectionTypeImpl)specializedType).immutableGetTypeId();		// XXX
				if (basicGetCollectionType(collectionTypeId) != specializedType) {
					basicGetCollectionType(collectionTypeId);
				}
				orphanage.addOrphanClass(specializedType);
			}
			return specializedType;
		}
	}
}