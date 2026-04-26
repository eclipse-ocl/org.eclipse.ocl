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

import java.util.Objects;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.NormalizedTemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.utilities.IndexableIterable;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

/**
 * TemplateSpecialization facilitates the use of template parameters by aggregating a containment hierarchy of
 * TemplateableElement-TemplateBinding-TemplateParameterSubstitution as a simple array
 * aligned with a TemplateParameterization.
 *
 * @since 7.0
 */
public class BasicTemplateSpecialization implements TemplateParameterSubstitutions, IndexableIterable<@NonNull Type>
{
	protected class Iterator implements java.util.Iterator<@NonNull Type>
	{
		private int index = 0;

		@Override
		public boolean hasNext() {
			return index < templateActuals.length;
		}

		@Override
		public @NonNull Type next() {
			return BasicTemplateSpecialization.this.get(index++);
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static @Nullable BasicTemplateSpecialization basicGetTemplateSpecialization(@NonNull Element element) {
		for (EObject eContainer = element; eContainer != null; eContainer = eContainer.eContainer()) {
			if (eContainer instanceof TemplateableElement) {
				TemplateableElement specializedElement = (TemplateableElement)eContainer;
				TemplateParameterization templateParameterization = TemplateParameterization.basicGetTemplateParameterization(specializedElement);
				if (templateParameterization != null) {
					return new BasicTemplateSpecialization(specializedElement, templateParameterization);
				}
			}
		}
		return null;
	}

	public static @NonNull BasicTemplateSpecialization getTemplateSpecialization(@NonNull Element element) {
		return Objects.requireNonNull(basicGetTemplateSpecialization(element));
	}

	protected final @NonNull TemplateableElement specializedElement;
	protected final @NonNull TemplateParameterization templateParameterization;
	protected final @Nullable Type @NonNull [] templateActuals;

	protected BasicTemplateSpecialization(@NonNull TemplateableElement specializedElement, @NonNull TemplateParameterization templateParameterization) {
		this.specializedElement = specializedElement;
		this.templateParameterization = templateParameterization;
		this.templateActuals = new @Nullable Type[templateParameterization.size()];
	}

	public @Nullable Type basicGet(int index) {
		return templateActuals[index];
	}

	@Override
	public @NonNull Type get(int index) {
		Type actual = templateActuals[index];
		if (actual != null) {
			return actual;
		}
		else {
			return templateParameterization.get(index);
		}
	}

	@Override
	public @Nullable Type get(@Nullable TemplateParameter templateParameter) {
		assert templateParameter != null;
		int index;
		if (templateParameter instanceof NormalizedTemplateParameter) {
			index = ((NormalizedTemplateParameter)templateParameter).getIndex();
		}
		else {
			index = templateParameterization.indexOf(templateParameter);
		}
		return templateActuals[index];
	}

	public @NonNull TemplateableElement getSpecializedElement() {
		return specializedElement;
	}

	public @NonNull TemplateParameterization getTemplateParameterization() {
		return templateParameterization;
	}

	@Override
	public boolean isEmpty() {
		return templateActuals.length == 0;
	}

	@Override
	public @NonNull Iterator iterator() {
		return new Iterator();
	}

	@Override
	public @Nullable Type put(@NonNull TemplateParameter templateParameter, @NonNull Type actualType) {
		int index;
		if (templateParameter instanceof NormalizedTemplateParameter) {
			index = ((NormalizedTemplateParameter)templateParameter).getIndex();
		}
		else {
			index = templateParameterization.indexOf(templateParameter);
		}
		Type oldType = templateActuals[index];
		templateActuals[index] = actualType;
		return oldType;
	}

	@Override
	public int size() {
		return templateActuals.length;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append(specializedElement);
		s.append(" : ");
		s.append(templateParameterization.getTemplateableElement());
		for (int i = 0; i < templateActuals.length; i++) {
			s.append("\n\t");
			s.append(Integer.toString(i));
			s.append(" : ");
			s.append(templateParameterization.get(i));
			s.append(" : ");
			s.append(get(i));
		}
		return s.toString();
	}
}