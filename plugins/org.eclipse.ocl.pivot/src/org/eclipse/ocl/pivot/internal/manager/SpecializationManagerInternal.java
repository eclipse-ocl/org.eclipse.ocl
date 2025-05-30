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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.types.TemplateArguments;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * CollectionTypeManagerInternal encapsulates the knowledge about known collection types.
 *
 * @since 7.0
 */
public class SpecializationManagerInternal implements SpecializationManager
{
	protected final @NonNull CompleteModelInternal completeModel;

	/**
	 * Map from actual types to specialization.
	 * <br>
	 * The specializations are weakly referenced so that stale specializations are garbage collected.
	 */
	// FIXME tests fail if keys are weak since GC is too aggressive across tests
	// The actual types are weak keys so that parameterizations using stale types are garbage collected.
	//
	private @Nullable /*WeakHash*/Map<@NonNull TemplateArguments, @NonNull WeakReference<org.eclipse.ocl.pivot.@NonNull Class>> specializations = null;

	public SpecializationManagerInternal(@NonNull CompleteModelInternal completeModel) {
	//	super(completeModel.getStandardLibrary());
		this.completeModel = completeModel;
	}

	/**
	 * @since 7.0
	 */
	protected org.eclipse.ocl.pivot.@NonNull Class createSpecialization(org.eclipse.ocl.pivot.@NonNull Class primaryClass, @NonNull TemplateArguments templateArguments) {
		org.eclipse.ocl.pivot.Class unspecializedType = primaryClass;
		String typeName = unspecializedType.getName();
		TemplateSignature templateSignature = unspecializedType.getOwnedSignature();
		List<TemplateParameter> templateParameters = templateSignature.getOwnedParameters();
		EClass eClass = unspecializedType.eClass();
		EFactory eFactoryInstance = eClass.getEPackage().getEFactoryInstance();
		org.eclipse.ocl.pivot.Class specializedType = (org.eclipse.ocl.pivot.Class) eFactoryInstance.create(eClass);
		specializedType.setName(typeName);
		TemplateBinding templateBinding = PivotFactory.eINSTANCE.createTemplateBinding();
		for (int i = 0; i < templateParameters.size(); i++) {
			TemplateParameter formalParameter = templateParameters.get(i);
			if (formalParameter != null) {
				Element templateArgument = templateArguments.get(i);
				if (templateArgument instanceof Type) {
					Type actualType = (Type) templateArgument;
					TemplateParameterSubstitution templateParameterSubstitution = PivotUtil.createTemplateParameterSubstitution(formalParameter, actualType);
					templateBinding.getOwnedSubstitutions().add(templateParameterSubstitution);
				}
			}
		}
		specializedType.getOwnedBindings().add(templateBinding);
		completeModel.resolveSuperClasses(specializedType, unspecializedType);
//		if (specializedType instanceof Metaclass) {
//			Type instanceType = (Type) templateArguments.get(0);
//			Metaclass specializedMetaclass = (Metaclass)specializedType;
//			specializedMetaclass.setInstanceType(instanceType);
//		}
		specializedType.setUnspecializedElement(unspecializedType);
		Orphanage orphanage = completeModel.getOrphanage();
		specializedType.setOwningPackage(orphanage);
		return specializedType;
	}

	/**
	 * @since 7.0
	 */
	public synchronized @Nullable Type findSpecializedType(org.eclipse.ocl.pivot.@NonNull Class primaryClass, @NonNull TemplateArguments templateArguments) {
		TemplateSignature templateSignature = primaryClass.getOwnedSignature();
		List<TemplateParameter> templateParameters = templateSignature.getOwnedParameters();
		int iMax = templateParameters.size();
		if (templateArguments.parametersSize() != iMax) {
			return null;
		}
		Map<@NonNull TemplateArguments, @NonNull WeakReference<org.eclipse.ocl.pivot.@NonNull Class>> specializations2 = specializations;
		if (specializations2 == null) {
			return null;
		}
		WeakReference<org.eclipse.ocl.pivot.@NonNull Class> weakReference = specializations2.get(templateArguments);
		if (weakReference == null) {
			return null;
		}
		org.eclipse.ocl.pivot.Class specializedType = weakReference.get();
		if (specializedType != null) {
			int templateArgumentSize = templateArguments.parametersSize();
			for (int i = 0; i < templateArgumentSize; i++) {
				Type templateArgument = templateArguments.get(i);
				if (templateArgument.eResource() == null) {		// If GC pending
					specializedType = null;
					break;
				}
			}
		}
		if (specializedType == null) {
			synchronized (specializations2) {
				specializedType = weakReference.get();
				if (specializedType == null) {
					specializations2.remove(templateArguments);
				}
			}
		}
		return specializedType;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getSpecializedType(org.eclipse.ocl.pivot.@NonNull Class primaryClass, @NonNull List<@NonNull ? extends Type> templateArguments) {
		TemplateSignature templateSignature = primaryClass.getOwnedSignature();
		List<TemplateParameter> templateParameters = templateSignature.getOwnedParameters();
		int iMax = templateParameters.size();
		if (templateArguments.size() != iMax) {
			throw new IllegalArgumentException("Incompatible template argument count");
		}
		return getSpecializedType(primaryClass, new TemplateArguments(primaryClass.getTypeId(), templateArguments));
	}

	/**
	 * @since 7.0
	 */
	private synchronized org.eclipse.ocl.pivot.@NonNull Class getSpecializedType(org.eclipse.ocl.pivot.@NonNull Class primaryClass, @NonNull TemplateArguments templateArguments) {
		Map<@NonNull TemplateArguments, @NonNull WeakReference<org.eclipse.ocl.pivot.@NonNull Class>> specializations2 = specializations;
		if (specializations2 == null) {
			synchronized(this) {
				specializations2 = specializations;
				if (specializations2 == null) {
					specializations2 = specializations = new /*Weak*/HashMap<>();
				}
			}
		}
		synchronized (specializations2) {
			org.eclipse.ocl.pivot.Class specializedType = null;
			WeakReference<org.eclipse.ocl.pivot.Class> weakReference = specializations2.get(templateArguments);
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
				specializations2.put(templateArguments, new WeakReference<>(specializedType));
			}
			return specializedType;
		}
	}
}