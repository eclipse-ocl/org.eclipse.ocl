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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Orphanage;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.internal.MapTypeImpl;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.MapTypeParameters;

/**
 * MapTypeManager manages the MapTypes created within an Orphanage.
 */
public class MapTypeManager extends AbstractTypeManager
{
	/*
	 * Map from MapTypeId to Map specialization.
	 */
	private final @NonNull Map<@NonNull MapTypeId, @NonNull MapType> typeId2mapType;

	public MapTypeManager(@NonNull Orphanage orphanage) {
		super(orphanage);
		this.typeId2mapType = new HashMap<>();
	}

	public synchronized @Nullable MapType basicGetMapType(@NonNull MapTypeId mapTypeId) {
		return typeId2mapType.get(mapTypeId);
	}

	private @NonNull MapType createSpecialization(@NonNull MapTypeParameters<@NonNull Type, @NonNull Type> typeParameters) {
		MapType unspecializedType = standardLibrary.getMapType();
		String typeName = unspecializedType.getName();
		TemplateSignature templateSignature = unspecializedType.getOwnedSignature();
		List<TemplateParameter> templateParameters = templateSignature.getOwnedParameters();
		EClass eClass = unspecializedType.eClass();
		EFactory eFactoryInstance = eClass.getEPackage().getEFactoryInstance();
		MapType specializedType = (MapType) eFactoryInstance.create(eClass);
		specializedType.setName(typeName);
		TemplateBinding templateBinding = PivotFactory.eINSTANCE.createTemplateBinding();
		TemplateParameter keyFormalParameter = templateParameters.get(0);
		TemplateParameter valueFormalParameter = templateParameters.get(1);
		assert keyFormalParameter != null;
		assert valueFormalParameter != null;
		Type keyType = typeParameters.getKeyType();
		Type valueType = typeParameters.getValueType();
		assert (keyType != null) && (keyType.eResource() != null);
		assert (valueType != null) && (valueType.eResource() != null);
		TemplateParameterSubstitution keyTemplateParameterSubstitution = PivotUtil.createTemplateParameterSubstitution(keyFormalParameter, keyType);
		TemplateParameterSubstitution valueTemplateParameterSubstitution = PivotUtil.createTemplateParameterSubstitution(valueFormalParameter, valueType);
		templateBinding.getOwnedSubstitutions().add(keyTemplateParameterSubstitution);
		templateBinding.getOwnedSubstitutions().add(valueTemplateParameterSubstitution);
		specializedType.getOwnedBindings().add(templateBinding);
	//	resolveSuperClasses(specializedMapType, unspecializedType);
		specializedType.getSuperClasses().addAll(unspecializedType.getSuperClasses());
		specializedType.setKeysAreNullFree(typeParameters.isKeysAreNullFree());
		specializedType.setValuesAreNullFree(typeParameters.isValuesAreNullFree());
		specializedType.setUnspecializedElement(unspecializedType);
		specializedType.setEntryClass(typeParameters.getEntryClass());
		return specializedType;
	}

	@Override
	public void dispose() {
		typeId2mapType.clear();
	}

	public @NonNull MapType getMapType(@NonNull MapTypeParameters<@NonNull Type, @NonNull Type> typeParameters) {
		MapTypeId mapTypeId = typeParameters.getMapTypeId();
		synchronized (typeId2mapType) {
			MapType specializedType = basicGetMapType(mapTypeId);
			if (specializedType == null) {
				specializedType = createSpecialization(typeParameters);
				typeId2mapType.put(mapTypeId, specializedType);
				assert mapTypeId == ((MapTypeImpl)specializedType).immutableGetTypeId();		// XXX
				orphanage.addOrphanClass(specializedType);
			}
			return specializedType;
		}
	}
}