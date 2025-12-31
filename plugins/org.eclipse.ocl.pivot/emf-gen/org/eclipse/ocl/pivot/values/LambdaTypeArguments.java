/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.values;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.IterableType;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * LambdaTypeArguments aggregates the template parameter specialization of a LambdaType providing a single identity
 * that can be used as a key for a Map lookup.
 *
 * @since 7.0
 */
public class LambdaTypeArguments
{
	private final int hashCode;				// XXX ?? leverage MapTypeId
	private final @NonNull TypedElement context;
	private final @NonNull List<@NonNull TypedElement> parameters;
	private final @NonNull TypedElement result;

	public LambdaTypeArguments(@NonNull TypedElement context, @NonNull Iterable<@NonNull ? extends TypedElement> parameters, @NonNull TypedElement result) {
		int hash = 0;		// Distinctly named same-typed lambdas are kept distinct although conformant
		List<@NonNull TypedElement> parametersList = new ArrayList<>();
		for (@NonNull TypedElement parameter : parameters) {
			hash *= 3;
			Type parameterType = parameter.getType();
			assert PivotUtil.assertIsNormalizedType(parameterType);
			hash += 11*(parameterType.hashCode() + PivotUtil.getName(parameter).hashCode() + (parameter.isIsRequired() ? 1 : 0));
			parametersList.add(parameter);
		}
		Type contextType = context.getType();
		assert PivotUtil.assertIsNormalizedType(contextType);
		hash += 5*(contextType.hashCode() + PivotUtil.getName(context).hashCode() + (context.isIsRequired() ? 1 : 0));
		Type resultType = result.getType();
		assert PivotUtil.assertIsNormalizedType(resultType);
		hash += 7*(resultType.hashCode() + PivotUtil.getName(result).hashCode() + (result.isIsRequired() ? 1 : 0));
		this.hashCode = hash;
		this.context = context;
		this.parameters = parametersList;			// XXX copy ???
		this.result = result;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof LambdaTypeArguments)) {
			return false;
		}
		LambdaTypeArguments that = (LambdaTypeArguments)o;
		if (this.hashCode != that.hashCode){
			return false;
		}
		if (this.context.isIsRequired() != that.context.isIsRequired()) {
			return false;
		}
		if (!this.context.getName().equals(that.context.getName())) {
			return false;
		}
		if (!this.context.getType().equals(that.context.getType())) {
			return false;
		}
		if (this.result.isIsRequired() != that.result.isIsRequired()) {
			return false;
		}
		if (!this.result.getName().equals(that.result.getName())) {
			return false;
		}
		if (!this.result.getType().equals(that.result.getType())) {
			return false;
		}
		int thisSize = this.parameters.size();
		int thatSize = that.parameters.size();
		if (thisSize != thatSize) {
			return false;
		}
		for (int i = 0; i < thisSize; i++) {
			@NonNull TypedElement thisParameter = this.parameters.get(i);
			@NonNull TypedElement thatParameter = that.parameters.get(i);
			if (thisParameter.isIsRequired() != thatParameter.isIsRequired()) {
				return false;
			}
			if (!thisParameter.getName().equals(thatParameter.getName())) {
				return false;
			}
			if (!thisParameter.getType().equals(thatParameter.getType())) {
				return false;
			}
		}
		return true;
	}

	public @NonNull TypedElement getContext() {
		return context;
	}

	public Iterable<@NonNull ? extends TypedElement> getParameters() {
		return parameters;
	}

	public @NonNull TypedElement getResult() {
		return result;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(TypeId.LAMBDA_NAME);
		s.append(context.getType().toString());
		if (!context.isIsRequired()) {
			//	append("[?]");
			}
		else if (!(context.getType() instanceof IterableType) && !(context.getType() instanceof LambdaType) && !(context.getType() instanceof TupleType)) {
			s.append("[1]");
		}
		s.append("(");
		boolean isFirst = true;
		for (TypedElement parameter : parameters) {
			if (!isFirst) {
				s.append(",");
			}
			s.append(context.getName());
			s.append(" : ");
			s.append(parameter.getType().toString());
			if (!parameter.isIsRequired()) {
				//	append("[?]");
				}
			else if (!(parameter.getType() instanceof IterableType) && !(parameter.getType() instanceof LambdaType) && !(parameter.getType() instanceof TupleType)) {
				s.append("[1]");
			}
			s.append("(");
			isFirst = false;
		}
		s.append(") : ");
		s.append(result.getType().toString());
		if (!result.isIsRequired()) {
		//	append("[?]");
		}
		else if (!(result.getType() instanceof IterableType) && !(result.getType() instanceof LambdaType) && !(result.getType() instanceof TupleType)) {
			s.append("[1]");
		}
		return s.toString();
	}
}