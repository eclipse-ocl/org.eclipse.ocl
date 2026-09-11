/*******************************************************************************
 * Copyright (c) 2026 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.TypeId;

/**
 * ParameterDescriptor provides a compact hashable descriptor for a Parameter.
 * @since 7.0
 */
public class ParameterDescriptor
{
//	private final @NonNull String name;
	private final @NonNull Type type;
	private final boolean isRequired;
	private final boolean isTypeof;
	private final int hashCode;

	public ParameterDescriptor(@NonNull Type type, boolean isRequired, boolean isTypeof) {
//		this.name = name;
		this.type = type;
		this.isRequired = isRequired;
		this.isTypeof = isTypeof;
		this.hashCode = /*name.hashCode() +*/ type.hashCode() + (isRequired ? 0x777 : 0x0) + (isTypeof ? 0x333 : 0x0);
	}

	public ParameterDescriptor(@NonNull Parameter parameter) {
		this(parameter.getType(), parameter.isIsRequired(), parameter.isIsTypeof());
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ParameterDescriptor)) {
			return false;
		}
		ParameterDescriptor that = (ParameterDescriptor)obj;
		if (hashCode() != that.hashCode()) {
			return false;
		}
		if (this.isRequired != that.isRequired) {
			return false;
		}
		if (this.isTypeof != that.isTypeof) {
			return false;
		}
//		if (!this.name.equals(that.name)) {
//			return false;
//		}
		if (!this.type.equals(that.type)) {
			return false;
		}
		return true;
	}

//	public @NonNull String getName() {
//		return name;
//	}

	public @NonNull Type getType() {
		return type;
	}

	public @NonNull TypeId getTypeId() {
		return type.getTypeId();
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	public boolean isRequired() {
		return isRequired;
	}

	public boolean isTypeof() {
		return isTypeof;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
//		s.append(name);
//		s.append(" : ");
		s.append(type);
		s.append(" ");
		s.append(isRequired ? "[1]" : "[?]");
		if (isTypeof) {
			s.append(" typeof");
		}
		return s.toString();
	}
}