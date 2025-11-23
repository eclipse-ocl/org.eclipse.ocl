/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.types;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateableElement;

/**
 * TemplateParameters provides a list of template parameters. The hashable functionality is not used.
 *
 * @since 7.0
 */
public class TemplateParameters
{
	public static final @NonNull TemplateParameters EMPTY_LIST = new TemplateParameters();

	/**
	 * @since 7.0
	 */
	public static @NonNull TemplateParameters getTemplateParameters(@NonNull TemplateableElement templateableElement) {
		List<@NonNull TemplateParameter> templateParameters = templateableElement.basicGetOwnedTemplateParameters();
		if (templateParameters == null) {
			return TemplateParameters.EMPTY_LIST;
		}
		int iMax = templateParameters.size();
		@NonNull TemplateParameter[] typeParameters = new @NonNull TemplateParameter[iMax];
		for (int i = 0; i < iMax; i++) {
			typeParameters[i] = templateParameters.get(i);
		}
		return new TemplateParameters(typeParameters);
	}

	private final @NonNull TemplateParameter @NonNull [] templateParameters;
	private final int hashCode;

	public TemplateParameters(@NonNull TemplateParameter... templateParameters) {
		this.templateParameters = new @NonNull TemplateParameter[templateParameters.length];
		int hash = 0;
		for (int i = 0; i < templateParameters.length; i++) {
			TemplateParameter parameter = templateParameters[i];
			hash = 111 * hash + parameter.hashCode();
			this.templateParameters[i] = parameter;
		}
		this.hashCode = hash;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof TemplateParameters)) {
			return false;
		}
		TemplateParameters that = (TemplateParameters)o;
		if (this.hashCode != that.hashCode){
			return false;
		}
		int iMax = templateParameters.length;
		if (iMax != that.templateParameters.length) {
			return false;
		}
		for (int i = 0; i < iMax; i++) {
			@NonNull TemplateParameter thisParameter = this.templateParameters[i];
			@NonNull TemplateParameter thatParameter = that.templateParameters[i];
			if (!thisParameter.equals(thatParameter)) {
				return false;
			}
		}
		return true;
	}

	public @NonNull TemplateParameter get(int i) {
		return templateParameters[i];
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	public int parametersSize() {
		return templateParameters.length;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append('(');
		for (int i = 0; i < templateParameters.length; i++) {
			if (i > 0) {
				s.append(',');
			}
			s.append(String.valueOf(templateParameters[i]));
		}
		s.append(')');
		return s.toString();
	}
}
