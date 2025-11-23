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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.TemplateArgument;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * TemplateSpecialization facilitates the use of template parameters by aggregating a containment hierarchy of
 * TemplateableElement-TemplateBinding-TemplateArgument as a simple array
 * aligned with a TemplateParameterization.
 *
 * @since 7.0
 */
public class TemplateSpecialization extends BasicTemplateSpecialization
{
	public static @Nullable BasicTemplateSpecialization basicGetTemplateSpecialization(@NonNull Element element) {
		for (EObject eContainer = element; eContainer != null; eContainer = eContainer.eContainer()) {
			if (eContainer instanceof TemplateableElement) {
				List<@NonNull TemplateArgument> templateArguments = basicGetTemplateArguments(null, eContainer);
				TemplateableElement templateableElement = (TemplateableElement)eContainer;
				if (templateArguments != null) {
					return new TemplateSpecialization(templateableElement, templateArguments);
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

	private static @Nullable List<@NonNull TemplateArgument> basicGetTemplateArguments(@Nullable List<@NonNull TemplateArgument> templateArguments, @NonNull EObject element) {
		EObject eContainer = element.eContainer();
		if (eContainer != null) {
			templateArguments = basicGetTemplateArguments(templateArguments, eContainer);
		}
		if (element instanceof TemplateableElement) {
			List<@NonNull TemplateArgument> asTemplateArguments = ((TemplateableElement)element).basicGetOwnedTemplateArguments();
			if (asTemplateArguments != null) {
				for (@NonNull TemplateArgument asTemplateArgument : asTemplateArguments) {
					if (templateArguments == null) {
						templateArguments = new ArrayList<>();
					}
					templateArguments.add(asTemplateArgument);
				}
			}
		}
		return templateArguments;
	}

	public static @NonNull TemplateSpecialization getTemplateSpecialization(@NonNull Element element) {
		return (TemplateSpecialization)ClassUtil.requireNonNull(basicGetTemplateSpecialization(element));
	}

	protected final @NonNull List<@NonNull TemplateArgument> templateArguments;

	private TemplateSpecialization(@NonNull TemplateableElement specializedElement,
			@NonNull List<@NonNull TemplateArgument> templateArguments) {
		super(specializedElement, TemplateParameterization.getTemplateParameterization(PivotUtil.getFormal(templateArguments.get(templateArguments.size()-1))));
		int size = templateArguments.size();
		assert size == templateParameterization.size();
		this.templateArguments = templateArguments;
		for (int i = 0; i < size; i++) {
			templateActuals[i] = PivotUtil.getActual(templateArguments.get(i));
		}
	}

	public List<@NonNull TemplateArgument> getOwnedTemplateArguments() {
		return templateArguments;
	}
}