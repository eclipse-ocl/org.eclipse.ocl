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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateArgument;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.manager.SpecializedTypeManager;
import org.eclipse.ocl.pivot.types.TemplateArgumentValues;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * AbstractSpecializedTypeManager encapsulates the knowledge about known class specializations.
 *
 * @since 7.0
 */
public abstract class AbstractSpecializedTypeManager implements SpecializedTypeManager
{
	protected final @NonNull StandardLibrary standardLibrary;

	/**
	 * Map from actual types to specialization.
	 * <br>
	 * The specializations are weakly referenced so that stale specializations are garbage collected.
	 */
	// FIXME tests fail if keys are weak since GC is too aggressive across tests
	// The actual types are weak keys so that parameterizations using stale types are garbage collected.
	//
	private @NonNull /*WeakHash*/Map<@NonNull TemplateArgumentValues, @NonNull WeakReference<org.eclipse.ocl.pivot.@Nullable Class>> specializations = new HashMap<>();

	protected AbstractSpecializedTypeManager(@NonNull StandardLibrary standardLibrary) {
		this.standardLibrary = standardLibrary;
	}

	protected org.eclipse.ocl.pivot.@NonNull Class createSpecialization(org.eclipse.ocl.pivot.@NonNull Class primaryClass, @NonNull TemplateArgumentValues templateArguments) {
		org.eclipse.ocl.pivot.Class genericType = primaryClass;
		String typeName = genericType.getName();
		List<@NonNull TemplateParameter> templateParameters = PivotUtil.getOwnedTemplateParametersList(genericType, true);
		EClass eClass = genericType.eClass();
		EFactory eFactoryInstance = eClass.getEPackage().getEFactoryInstance();
		org.eclipse.ocl.pivot.Class specializedType = (org.eclipse.ocl.pivot.Class) eFactoryInstance.create(eClass);
		specializedType.setName(typeName);
		List<@NonNull TemplateArgument> asTemplateArguments = PivotUtil.getOwnedTemplateArgumentsList(specializedType, true);
		for (int i = 0; i < templateParameters.size(); i++) {
			Element templateArgument = templateArguments.get(i);
			if (templateArgument instanceof Type) {
				Type actualType = (Type) templateArgument;
				Type primaryActualType = standardLibrary.getPrimaryType(actualType);
				TemplateArgument templateArgument2 = PivotUtil.createTemplateArgument(primaryActualType);
				asTemplateArguments.add(templateArgument2);
			}
		}
		specializedType.setGeneric(genericType);
		standardLibrary.resolveSuperClasses(specializedType, genericType);
		standardLibrary.addOrphanClass(specializedType);
		return specializedType;
	}

	@Override
	public void dispose() {
		specializations.clear();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getSpecializedType(org.eclipse.ocl.pivot.@NonNull Class primaryClass, @NonNull List<@NonNull ? extends Type> templateArguments) {
		List<@NonNull TemplateParameter> templateParameters = PivotUtil.getOwnedTemplateParametersList(primaryClass, true);
		int iMax = templateParameters.size();
		if (templateArguments.size() != iMax) {
			throw new IllegalArgumentException("Incompatible template argument count");
		}
		TemplateArgumentValues templateArgumentValues = new TemplateArgumentValues(primaryClass.getTypeId(), templateArguments);
		return getSpecializedType(primaryClass, templateArgumentValues);
	}

	private synchronized org.eclipse.ocl.pivot.@NonNull Class getSpecializedType(org.eclipse.ocl.pivot.@NonNull Class primaryClass, @NonNull TemplateArgumentValues templateArguments) {
		synchronized (specializations) {
			org.eclipse.ocl.pivot.Class specializedType = null;
			WeakReference<org.eclipse.ocl.pivot.Class> weakReference = specializations.get(templateArguments);
			if (weakReference != null) {
				specializedType = weakReference.get();
				if (specializedType != null) {
					int templateArgumentSize = templateArguments.parametersSize();
					for (int i = 0; i < templateArgumentSize; i++) {
						Type templateArgument = templateArguments.get(i);
						if (templateArgument.eResource() == null) {		// If GC pending
							specializedType = null;
							weakReference.clear();
							break;
						}
					}
				}
			}
			if (specializedType == null) {
				specializedType = createSpecialization(primaryClass, templateArguments);
				specializations.put(templateArguments, new WeakReference<>(specializedType));
			}
			return specializedType;
		}
	}

	@Override
	public void load(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		org.eclipse.ocl.pivot.@NonNull Class genericClass = PivotUtil.getGenericElement(asClass);
		TemplateSpecialization templateSpecialization = TemplateSpecialization.getTemplateSpecialization(asClass);
		List<@NonNull TemplateParameter> asTemplateParameters = genericClass.basicGetOwnedTemplateParameters();
		assert asTemplateParameters != null;
		List<@NonNull Type> templateArguments = new ArrayList<@NonNull Type>(asTemplateParameters.size());
		for (@NonNull TemplateParameter templateParameter : asTemplateParameters) {
			Type templateArgument = templateSpecialization.get(templateParameter);
			assert templateArgument != null;
			templateArguments.add(templateArgument);
		}
		TemplateArgumentValues templateArgumentValues = new TemplateArgumentValues(genericClass.getTypeId(), templateArguments);
		WeakReference<org.eclipse.ocl.pivot.@Nullable Class> ref = specializations.get(templateArgumentValues);
		org.eclipse.ocl.pivot.@Nullable Class old = ref != null ? ref.get() : null;
		if (old == null) {
			specializations.put(templateArgumentValues, new WeakReference<>(asClass));
		}
		else {
			assert old == asClass;
		}
	}
}