/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment.EvaluationEnvironmentExtension;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.evaluation.BasicOCLExecutor;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.internal.values.CompositeSymbolicConstraintImpl;
import org.eclipse.ocl.pivot.internal.values.SymbolicConstraintImpl;
import org.eclipse.ocl.pivot.internal.values.SymbolicNavigationCallValueImpl;
import org.eclipse.ocl.pivot.values.SymbolicConstraint;
import org.eclipse.ocl.pivot.values.SymbolicOperator;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.12
 */
public class SymbolicOCLExecutor extends BasicOCLExecutor implements SymbolicExecutor
{
	public SymbolicOCLExecutor( @NonNull EnvironmentFactoryInternalExtension environmentFactory, @NonNull ModelManager modelManager) {
		super(environmentFactory, modelManager);
	}

	@Override
	public void add(@NonNull VariableDeclaration variable, @NonNull SymbolicConstraint symbolicConstraint) {
		Object oldConstraint = super.replaceInternal(variable, symbolicConstraint);
		if (oldConstraint != null) {
			CompositeSymbolicConstraintImpl compositeSymbolicConstraint;
			if (oldConstraint instanceof CompositeSymbolicConstraintImpl) {
				compositeSymbolicConstraint = (CompositeSymbolicConstraintImpl) oldConstraint;
			}
			else {
				compositeSymbolicConstraint = new CompositeSymbolicConstraintImpl();
				compositeSymbolicConstraint.add((SymbolicConstraint)oldConstraint);
				super.replaceInternal(variable, compositeSymbolicConstraint);
			}
			compositeSymbolicConstraint.add(symbolicConstraint);
		}

	}

	@Override
	public @Nullable Object internalExecuteNavigationCallExp(@NonNull NavigationCallExp navigationCallExp, @NonNull Property referredProperty, @Nullable Object sourceValue) {
		return new SymbolicNavigationCallValueImpl(navigationCallExp, sourceValue);
	}

	@Override
	public Object internalExecuteOperationCallExp(@NonNull OperationCallExp operationCallExp, @Nullable Object @NonNull [] sourceAndArgumentValues) {
		return super.internalExecuteOperationCallExp(operationCallExp, sourceAndArgumentValues);
	}

	@Override
	public @NonNull EvaluationEnvironment pushSymbolicEvaluationEnvironment(@NonNull NamedElement executableObject, @Nullable Object symbolicValue, @Nullable Object constantValue) {
		EvaluationEnvironment.EvaluationEnvironmentExtension evaluationEnvironment2 = (EvaluationEnvironmentExtension) getEvaluationEnvironment(); //ClassUtil.nonNullState(evaluationEnvironment);
		EvaluationEnvironment.EvaluationEnvironmentExtension nestedEvaluationEnvironment = createNestedEvaluationEnvironment(evaluationEnvironment2, executableObject, (TypedElement)null);
		pushEvaluationEnvironment(nestedEvaluationEnvironment);
		if (symbolicValue instanceof SymbolicValue) {
			((SymbolicValue)symbolicValue).deduceFrom(this, new SymbolicConstraintImpl(TypeId.BOOLEAN, false, false, SymbolicOperator.EQUALS, constantValue));
		}
		return nestedEvaluationEnvironment;
	}
}