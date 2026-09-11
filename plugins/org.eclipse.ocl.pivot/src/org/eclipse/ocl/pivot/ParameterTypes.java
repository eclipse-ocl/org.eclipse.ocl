/*******************************************************************************
 * Copyright (c) 2012, 2020 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.ParameterId;
import org.eclipse.ocl.pivot.ids.ParametersId;

/**
 * ParameterDescriptors provides a hashable list of operation parameters.
 *
 * @since 7.0
 */
@Deprecated /* Replaced by org.eclipse.ocl.codegen.ParameterDescriptors once PivotTables regenerated */
public class ParameterTypes
{
	public static final @NonNull ParameterDescriptor @NonNull [] EMPTY_LIST = new @NonNull ParameterDescriptor [0];

	private static @NonNull ParameterDescriptor @NonNull [] convertParameterTypes(@NonNull Type @NonNull ... types) {
		int iMax = types.length;
		@NonNull ParameterDescriptor parameterDescriptors @NonNull [] = new @NonNull ParameterDescriptor[iMax];
		int i = 0;
		for (@NonNull Type type : types) {
			parameterDescriptors[i] = new ParameterDescriptor(/*"_" + i,*/ type, false, false);
			i++;
		}
		return parameterDescriptors;
	}

	private final @NonNull ParameterDescriptor @NonNull [] parameterDescriptors;
	private final @NonNull ParametersId parametersId;
	private final int hashCode;
	private /*@LazyNonNull*/ List<@NonNull Parameter> parameters = null;  // XXX init from ctor

	public ParameterTypes(@NonNull Type @NonNull ... parameterDescriptors) {
		this(convertParameterTypes(parameterDescriptors));
	}

	private ParameterTypes(@NonNull ParameterDescriptor @NonNull ... parameterDescriptors) {
		this.parameterDescriptors = parameterDescriptors;
		this.parametersId = IdManager.getParametersId(parameterDescriptors);
		hashCode = parametersId.hashCode() + 0x999;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ParameterTypes)) {
			return false;
		}
		ParameterTypes that = (ParameterTypes)obj;
		if (hashCode() != that.hashCode()) {
			return false;
		}
		ParameterDescriptor[] thoseParameters = that.parameterDescriptors;
		if (parameterDescriptors.length != thoseParameters.length) {
			return false;
		}
		for (int i = 0; i < parameterDescriptors.length; i++) {
			if (!parameterDescriptors[i].equals(thoseParameters[i])) {
				return false;
			}
		}
		return true;
	}

	public @NonNull ParameterDescriptor get(int index) {
		return parameterDescriptors[index];
	}

	public @NonNull ParameterDescriptor @NonNull [] getParameterDescriptors() {
		return parameterDescriptors;
	}

	public @NonNull ParameterId getParameterId(int index) {
		return parametersId.get(index);
	}

	public @NonNull ParametersId getParametersId() {
		return parametersId;
	}

	public @NonNull List<@NonNull Parameter> getParameters() {
		List<@NonNull Parameter> parameters2 = parameters;
		if (parameters2 == null) {
			parameters = parameters2 = new ArrayList<>(parameterDescriptors.length);
			for (int i = 0; i < parameterDescriptors.length; i++) {
				ParameterDescriptor parameterType = parameterDescriptors[i];
//				parameters2.add(new AbstractExecutorParameter("_" + i, type, false));
				String name = "_" + i;
			//	AbstractExecutorParameter asParameter = new AbstractExecutorParameter(name, type, false);
				Parameter asParameter = PivotFactory.eINSTANCE.createParameter();
				asParameter.setName(name);
				asParameter.setType(parameterType.getType());
				asParameter.setIsTypeof(false);
				parameters2.add(asParameter);
			}
		}
		return parameters2;
	}

	@Deprecated	// recode to support isTypeOf
	public @NonNull Type getType(int index) {
		ParameterDescriptor parameterType = parameterDescriptors[index];
		assert parameterType != null;
		return parameterType.getType();
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	public int size() {
		return parameterDescriptors.length;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append('(');
		for (int i = 0; i < parameterDescriptors.length; i++) {
			if (i > 0) {
				s.append(',');
			}
			s.append(parameterDescriptors[i].toString());
		}
		s.append(')');
		return s.toString();
	}
}