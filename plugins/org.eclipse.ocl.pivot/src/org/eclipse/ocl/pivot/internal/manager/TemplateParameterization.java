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
import java.util.NoSuchElementException;
import java.util.Objects;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.utilities.IndexableIterable;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * TemplateParameterization facilitates the use of template parameters by aggregating a containment hierarchy of
 * TemplateableElement-TemplateSignature-TemplateParameter as a simple array.
 *
 * @since 7.0
 */
public class TemplateParameterization implements IndexableIterable<@NonNull TemplateParameter>
{
	protected class Iterator implements java.util.Iterator<@NonNull TemplateParameter>
	{
		private int index = 0;

		@Override
		public boolean hasNext() {
			return index < templateParameters.length;
		}

		@Override
		public @NonNull TemplateParameter next() {
			return templateParameters[index++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public static @Nullable TemplateParameterization basicGetTemplateParameterization(@NonNull Element element) {
		for (EObject eContainer = element; eContainer != null; eContainer = eContainer.eContainer()) {
			if (eContainer instanceof TemplateableElement) {
				List<@NonNull TemplateParameter> templateParameters = basicGetTemplateParameters(null, eContainer);
				return templateParameters != null ? new TemplateParameterization((TemplateableElement)eContainer, templateParameters) : null;
			}
		}
		return null;
	}

	@Deprecated /* @deprecated use getTemplateParameterization */
	public static @Nullable List<@NonNull TemplateParameter> basicGetTemplateParameters(@NonNull Element element) {
		List<@NonNull TemplateParameter> templateParameters = basicGetTemplateParameters(null, element);
		return templateParameters;
	}

	public static @NonNull List<@NonNull TemplateParameter> getTemplateParameters(@NonNull Element element) {
		return Objects.requireNonNull(basicGetTemplateParameters(null, element));
	}

	private static @Nullable List<@NonNull TemplateParameter> basicGetTemplateParameters(@Nullable List<@NonNull TemplateParameter> templateParameters, @NonNull EObject element) {
		EObject eContainer = element.eContainer();
		if (eContainer != null) {
			templateParameters = basicGetTemplateParameters(templateParameters, eContainer);
		}
		if (element instanceof TemplateableElement) {
			TemplateSignature templateSignature = ((TemplateableElement)element).getOwnedSignature();
			if (templateSignature != null) {
				for (@NonNull TemplateParameter templateParameter : PivotUtil.getOwnedParameters(templateSignature)) {
					if (templateParameters == null) {
						templateParameters = new ArrayList<>();
					}
					templateParameters.add(templateParameter);
				}
			}
		}
		return templateParameters;
	}

	public static @NonNull TemplateParameterization getTemplateParameterization(@NonNull Element element) {
		for (EObject eContainer = element; eContainer != null; eContainer = eContainer.eContainer()) {
			if (eContainer instanceof TemplateableElement) {
				List<@NonNull TemplateParameter> templateParameters = basicGetTemplateParameters(null, eContainer);
				return new TemplateParameterization((TemplateableElement)eContainer, templateParameters);
			}
		}
		throw new NullPointerException("No TemplateableElement scope for " + element);
	}

	protected final @NonNull TemplateableElement templateableElement;
	protected final @NonNull TemplateParameter @NonNull [] templateParameters;

	private TemplateParameterization(@NonNull TemplateableElement templateableElement,
			@Nullable List<@NonNull TemplateParameter> templateParameters) {
		this.templateableElement = templateableElement;
		if (templateParameters != null) {
			int size = templateParameters.size();
	 		this.templateParameters = templateParameters.toArray(new @NonNull TemplateParameter[size]);
		}
		else {
			this.templateParameters = new @NonNull TemplateParameter[0];
		}
	}

	@Override
	public @NonNull TemplateParameter get(int index) {
		return templateParameters[index];
	}

	public @NonNull TemplateableElement getTemplateableElement() {
		return templateableElement;
	}

	public int indexOf(@NonNull TemplateParameter templateParameter) {
		for (int i = 0; i < templateParameters.length; i++) {
			if (templateParameters[i] == templateParameter) {
				return i;
			}
		}
		throw new NoSuchElementException();
	}

	public boolean isEmpty() {
		return templateParameters.length == 0;
	}

	@Override
	public @NonNull Iterator iterator() {
		return new Iterator();
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append(templateableElement);
		for (int i = 0; i < templateParameters.length; i++) {
			s.append("\n\t");
			s.append(Integer.toString(i));
			s.append(" : ");
			s.append(templateParameters[i]);
		}
		return s.toString();
	}

	@Override
	public int size() {
		return templateParameters.length;
	}
}