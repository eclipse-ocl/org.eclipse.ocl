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
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.ElementId;

/**
 * TemplateArguments provides a hashable list of type values suitable for use when indexing specializations.
 *
 * @since 7.0
 */
public class TemplateArguments
{
	private final @NonNull ElementId elementId;
	private final @NonNull Type @NonNull [] templateArguments;
	private final int hashCode;

	public TemplateArguments(@NonNull ElementId elementId, @NonNull List<@NonNull ? extends Type> parameters) {
		this.elementId = elementId;
		this.templateArguments = new @NonNull Type[parameters.size()];
		int hash = elementId.hashCode();
		for (int i = 0; i < templateArguments.length; i++) {
			Type parameter = parameters.get(i);
			hash = 111 * hash + parameter.hashCode();
			this.templateArguments[i] = parameter;
		}
		this.hashCode = hash;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof TemplateArguments)) {
			return false;
		}
		TemplateArguments that = (TemplateArguments)o;
		if (this.hashCode != that.hashCode){
			return false;
		}
		if (this.elementId != that.elementId){
			return false;
		}
		int iMax = templateArguments.length;
		if (iMax != that.templateArguments.length) {
			return false;
		}
		for (int i = 0; i < iMax; i++) {
			@NonNull Type thisParameter = this.templateArguments[i];
			@NonNull Type thatParameter = that.templateArguments[i];
			if (!thisParameter.equals(thatParameter)) {
				return false;
			}
		}
		return true;
	}

	public @NonNull Type get(int i) {
		return templateArguments[i];
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	public int parametersSize() {
		return templateArguments.length;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(elementId);
		s.append('(');
		for (int i = 0; i < templateArguments.length; i++) {
			if (i > 0) {
				s.append(',');
			}
			s.append(String.valueOf(templateArguments[i]));
		}
		s.append(')');
		return s.toString();
	}
}
