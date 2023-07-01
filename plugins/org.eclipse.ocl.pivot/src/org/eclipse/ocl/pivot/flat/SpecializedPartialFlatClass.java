/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.flat;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;

/**
 * A PartialFlatClass identifies a Pivot Class as the client for which caches are provided.
 * <br>
 * This calls is not yet used by itself since current usage always provides a EClassifier
 * for the more refined EcoreFlatClass.
 */
public class SpecializedPartialFlatClass extends PartialFlatClass
{
	protected final @NonNull AbstractFlatClass genericFlatClass;
	protected final @NonNull List<@NonNull FlatClass> bindingFlatClasses;

	protected SpecializedPartialFlatClass(@NonNull FlatModel flatModel, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		super(flatModel, asClass);
		assert (asClass.getOwnedSignature() == null) && (asClass.getGeneric() != null) && (asClass.getOwnedBindings().size() > 0);
		org.eclipse.ocl.pivot.Class asGenericClass = (org.eclipse.ocl.pivot.Class)asClass.getGeneric();
		assert asGenericClass != null;
		StandardLibrary standardLibrary = flatModel.getStandardLibrary();
		this.genericFlatClass = (AbstractFlatClass)asGenericClass.getFlatClass(standardLibrary);
		this.bindingFlatClasses = new ArrayList<>();
		for (TemplateBinding asTemplateBinding : asClass.getOwnedBindings()) {
			for (TemplateParameterSubstitution asTemplateParameterSubstitution : asTemplateBinding.getOwnedSubstitutions()) {
				bindingFlatClasses.add(asTemplateParameterSubstitution.getActual().getFlatClass(standardLibrary));
			}
		}
		assert bindingFlatClasses.size() > 0;
	}

	@Override
	public @NonNull FlatClass getGenericFlatClass() {
		return genericFlatClass;
	}

	@Override
	public @NonNull TemplateParameterId @Nullable [] getTemplateParameterIds() {
		return genericFlatClass.getTemplateParameterIds();
	}
}
