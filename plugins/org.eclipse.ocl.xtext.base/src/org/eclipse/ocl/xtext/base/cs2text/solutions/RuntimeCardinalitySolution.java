/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.cs2text.solutions;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.CardinalityExpression;
import org.eclipse.ocl.xtext.base.cs2text.CardinalityVariable;

public class RuntimeCardinalitySolution extends AbstractCardinalitySolution
{
//	protected final @NonNull CardinalityExpression cardinalityExpression;
//	protected final @NonNull Iterable<@NonNull CardinalityVariable> unresolvedVariables;
	protected final @NonNull Iterable<@NonNull CardinalityVariable> unresolvedVariables;
	protected final @NonNull Iterable<@NonNull CardinalityExpression> unresolvedExpressions;

//	public RuntimeSolution(@NonNull CardinalityExpression cardinalityExpression, @NonNull Iterable<@NonNull CardinalityVariable> unresolvedVariables) {
//		this.cardinalityExpression = cardinalityExpression;
//		this.unresolvedVariables = unresolvedVariables;
//	}

	public RuntimeCardinalitySolution(@NonNull Iterable<@NonNull CardinalityVariable> unresolvedVariables, @NonNull Iterable<@NonNull CardinalityExpression> unresolvedExpressions) {
		this.unresolvedVariables = unresolvedVariables;
		this.unresolvedExpressions = unresolvedExpressions;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof RuntimeCardinalitySolution)) {
			return false;
		}
		RuntimeCardinalitySolution that = (RuntimeCardinalitySolution) obj;
		return this.unresolvedVariables.equals(that.unresolvedVariables) && this.unresolvedExpressions.equals(that.unresolvedExpressions);
	}

	@Override
	public int hashCode() {
		int hashCode = 0;
		for (@NonNull CardinalityVariable unresolvedVariable : unresolvedVariables) {
			hashCode += 3 + unresolvedVariable.hashCode();
		}
		for (@NonNull CardinalityExpression unresolvedExpression : unresolvedExpressions) {
			hashCode += 5 + unresolvedExpression.hashCode();
		}
		return hashCode;
	}

	@Override
	public boolean isRuntime() {
		return true;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		boolean isFirst = true;
		for (@NonNull CardinalityVariable unresolvedVariable : unresolvedVariables) {
			if (!isFirst) {
				s.append(",");
			}
			s.append(unresolvedVariable);
			isFirst = false;
		}
		s.append(" in ");
		isFirst = true;
		for (@NonNull CardinalityExpression unresolvedExpression : unresolvedExpressions) {
			if (!isFirst) {
				s.append(",");
			}
			s.append(unresolvedExpression);
			isFirst = false;
		}
	}
}