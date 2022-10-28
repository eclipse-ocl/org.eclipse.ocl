/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.utilities;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Element;

/**
 * RereferencingCopier copies an EObject and its children replacing selected children and references to them by alternate objects.
 */
@SuppressWarnings("serial")
public class RereferencingCopier extends EcoreUtil.Copier
{
	public static @NonNull <T extends EObject> T copy(@NonNull T eObject, @NonNull Map<@NonNull Element, @NonNull Element> rereferences) {
		RereferencingCopier copier = new RereferencingCopier(rereferences);
		@SuppressWarnings("null")@NonNull EObject result = copier.copy(eObject);
		copier.copyReferences();
		@SuppressWarnings("unchecked") T t = (T) result;
		return t;
	}

	private final @NonNull Map<@NonNull Element, @NonNull Element> reDefinitions;

	public RereferencingCopier(@NonNull Map<@NonNull Element, @NonNull Element> reDefinitions) {
		this.reDefinitions = reDefinitions;					// FIXME Surely we should set useOriginalReferences to be false ?
	}

	@Override
	public EObject copy(EObject eObject) {
		Element element = reDefinitions.get(eObject);		// FIXME Surely we cannot redefine part of the composition tree ?
		if (element != null) {
			put(eObject, element);
			return element;
		}
		else {
			return super.copy(eObject);
		}
	}

	@Override
	public EObject get(Object key) {
		Element element = reDefinitions.get(key);
		if (element != null) {
			return element;
		}
		else {
			return super.get(key);
		}
	}
}
