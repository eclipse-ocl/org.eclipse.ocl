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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.manager.SpecializedTypeManager;
import org.eclipse.ocl.pivot.types.TemplateArguments;

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
	private @Nullable /*WeakHash*/Map<@NonNull TemplateArguments, @NonNull WeakReference<org.eclipse.ocl.pivot.@NonNull Class>> specializations = null;

	protected AbstractSpecializedTypeManager(@NonNull StandardLibrary standardLibrary) {
		this.standardLibrary = standardLibrary;
	}

	protected abstract org.eclipse.ocl.pivot.@NonNull Class createSpecialization(org.eclipse.ocl.pivot.@NonNull Class primaryClass, @NonNull TemplateArguments templateArguments);

/*	public synchronized @Nullable Type findSpecializedType(org.eclipse.ocl.pivot.@NonNull Class primaryClass, @NonNull TemplateArguments templateArguments) {
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
	} */

	@Override
	public void dispose() {
		specializations = null;
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