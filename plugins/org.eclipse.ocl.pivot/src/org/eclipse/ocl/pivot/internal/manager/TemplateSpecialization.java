/*******************************************************************************
 * Copyright (c) 2014, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D. Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * TemplateSpecialization facilitates the use of template parameters by aggregating a containment hierarchy of
 * TemplateableElement-TemplateBinding-TemplateParameterSubstitution as a simple array
 * aligned with a TemplateParameterization.
 *
 * @since 7.0
 */
public class TemplateSpecialization extends BasicTemplateSpecialization
{
	public static @Nullable BasicTemplateSpecialization basicGetTemplateSpecialization(@NonNull Element element) {
		for (EObject eContainer = element; eContainer != null; eContainer = eContainer.eContainer()) {
			if (eContainer instanceof TemplateableElement) {
				List<@NonNull TemplateParameterSubstitution> templateParameterSubstitutions = basicGetTemplateParameterSubstitutions(null, eContainer);
				TemplateableElement templateableElement = (TemplateableElement)eContainer;
				if (templateParameterSubstitutions != null) {
					return new TemplateSpecialization(templateableElement, templateParameterSubstitutions);
				}
				else {
					TemplateParameterization templateParameterization = TemplateParameterization.basicGetTemplateParameterization(templateableElement);
					if (templateParameterization != null) {
						return new BasicTemplateSpecialization(templateableElement, templateParameterization);
					}
				}
			}
		}
		return null;
	}

	private static @Nullable List<@NonNull TemplateParameterSubstitution> basicGetTemplateParameterSubstitutions(@Nullable List<@NonNull TemplateParameterSubstitution> templateParameterSubstitutions, @NonNull EObject element) {
		EObject eContainer = element.eContainer();
		if (eContainer != null) {
			templateParameterSubstitutions = basicGetTemplateParameterSubstitutions(templateParameterSubstitutions, eContainer);
		}
		if (element instanceof TemplateableElement) {
			TemplateableElement templateableElement = (TemplateableElement)element;
			for (@NonNull TemplateBinding templateBinding : PivotUtil.getOwnedBindings(templateableElement)) {
				for (@NonNull TemplateParameterSubstitution templateParameterSubstitution : PivotUtil.getOwnedSubstitutions(templateBinding)) {
					if (templateParameterSubstitutions == null) {
						templateParameterSubstitutions = new ArrayList<>();
					}
					templateParameterSubstitutions.add(templateParameterSubstitution);
				}
			}
		}
		return templateParameterSubstitutions;
	}

	public static @NonNull TemplateSpecialization getTemplateSpecialization(@NonNull Element element) {
		return (TemplateSpecialization)Objects.requireNonNull(basicGetTemplateSpecialization(element));
	}

	protected final @NonNull List<@NonNull TemplateParameterSubstitution> templateParameterSubstitutions;

	private TemplateSpecialization(@NonNull TemplateableElement specializedElement,
			@NonNull List<@NonNull TemplateParameterSubstitution> templateParameterSubstitutions) {
		super(specializedElement, TemplateParameterization.getTemplateParameterization(PivotUtil.getFormal(templateParameterSubstitutions.get(templateParameterSubstitutions.size()-1))));
		int size = templateParameterSubstitutions.size();
		assert size == templateParameterization.size();
		this.templateParameterSubstitutions = templateParameterSubstitutions;
		for (int i = 0; i < size; i++) {
			templateActuals[i] = PivotUtil.getActual(templateParameterSubstitutions.get(i));
		}
	}

	public List<@NonNull TemplateParameterSubstitution> getOwnedSubstitutions() {
		return templateParameterSubstitutions;
	}
}