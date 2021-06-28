/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.evaluation;

import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.internal.evaluation.ExecutorInternal;
import org.eclipse.ocl.pivot.utilities.Adaptable;
import org.eclipse.ocl.pivot.utilities.Customizable;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;

/**
 * The evaluation environment keeps track of the current values of variables
 * in the evaluation of an OCL expression.
 */
public interface EvaluationEnvironment extends Adaptable, Customizable
{
	/**
	 * @since 1.1
	 */
	public interface EvaluationEnvironmentExtension extends EvaluationEnvironment
	{
		@Override
		@NonNull ExecutorInternal getExecutor();
		@Override
		EvaluationEnvironment.@Nullable EvaluationEnvironmentExtension getParentEvaluationEnvironment();
	}

	/**
	 * Adds the supplied variable declaration and value binding to the
	 * environment. The variable declaration must not already be bound.
	 *
	 * @param referredVariable
	 *            the variable declaration to add
	 * @param value
	 *            the associated binding
	 *
	 * @see #replace(TypedElement, Object)
	 */
	void add(@NonNull VariableDeclaration referredVariable, @Nullable Object value);

	/**
	 * Clears the environment of variables.
	 */
	void clear();
	void dispose();
	@NonNull EnvironmentFactory getEnvironmentFactory();

	/**
	 * @since 1.16
	 */
	default EvaluationEnvironment.@Nullable EvaluationEnvironmentExtension getParentEvaluationEnvironment() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @since 1.16
	 */
	default @NonNull ExecutorInternal getExecutor() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the value associated with the supplied variable declaration
	 *
	 * @param referredVariable
	 *            the name whose value is to be returned
	 * @return the value associated with the name
	 */
	@Nullable Object getValueOf(@NonNull VariableDeclaration referredVariable);

	/**
	 * Return the set of all locally registered variables.
	 */
	@NonNull Set<@NonNull VariableDeclaration> getVariables();

	/**
	 * Removes the supplied variable declaration and binding from the
	 * environment (if it exists) and returns it.
	 *
	 * @param referredVariable
	 *            the variable declaration to remove
	 * @return the value associated with the removed variable declaration
	 */
	@Nullable Object remove(@NonNull VariableDeclaration referredVariable);

	/**
	 * Replaces the current value of the supplied variable declaration with the
	 * supplied value.
	 *
	 * @param referredVariable
	 *            the variable declaration
	 * @param value
	 *            the new value
	 */
	void replace(@NonNull VariableDeclaration referredVariable, @Nullable Object value);

	/** @deprecated moved to Evaluator */
	@Deprecated
	@NonNull ModelManager getModelManager();

	@NonNull NamedElement getExecutableObject();

	/**
	 * @since 1.16
	 */
	default @Nullable Object replaceInternal(@NonNull VariableDeclaration referredVariable, @Nullable Object value) {
		return null;
	}

	/**
	 * @since 1.16
	 */
	default void toString(@NonNull StringBuilder s) {}
}
