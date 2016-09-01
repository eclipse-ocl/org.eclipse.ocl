/*******************************************************************************
 * Copyright (c) 2011, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ecore;

import java.util.List;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.Executor.ExecutorExtension;
import org.eclipse.ocl.pivot.library.AbstractOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * An EObjectOperation provides the standard LibraryOperation to implement an EOperation defined by an OCL specification.
 * The specification is resolved lazily and so an invalid specification may throw an InvalidValueException at run-time.
 */
public class EObjectOperation extends AbstractOperation
{
	protected final @NonNull Operation operation;
	protected final @NonNull EOperation eFeature;
	protected final @NonNull ExpressionInOCL specification;

	public EObjectOperation(@NonNull Operation operation, @NonNull EOperation eFeature, @NonNull ExpressionInOCL specification) {
		this.operation = operation;
		this.eFeature = eFeature;
		this.specification = specification;
	}

	/**
	 * @since 1.1
	 */
	@Override
	public @Nullable Object dispatch(@NonNull Executor executor, @NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		if (specification.getOwnedBody() == null) {
			try {
				MetamodelManager metamodelManager = executor.getMetamodelManager();
				metamodelManager.parseSpecification(specification);
			} catch (ParserException e) {
				throw new InvalidValueException(e, "parse failure", executor.getEvaluationEnvironment(), sourceValue, callExp);
			}
		}
		ExpressionInOCL query = specification;
		List<? extends OCLExpression> arguments = callExp.getOwnedArguments();
		Object[] argumentValues = new Object[arguments.size()];
		for (int i = 0; i < arguments.size(); i++) {
			OCLExpression argument = arguments.get(i);
			assert argument != null;
			argumentValues[i] = executor.evaluate(argument);
		}
		EvaluationEnvironment nestedEvaluationEnvironment = ((ExecutorExtension)executor).pushEvaluationEnvironment(query, (TypedElement)callExp);
		nestedEvaluationEnvironment.add(ClassUtil.nonNullModel(query.getOwnedContext()), sourceValue);
		List<Variable> parameterVariables = query.getOwnedParameters();
		int iMax = Math.min(parameterVariables.size(), argumentValues.length);
		for (int i = 0; i < iMax; i++) {
			nestedEvaluationEnvironment.add(ClassUtil.nonNullModel(parameterVariables.get(i)), argumentValues[i]);
		}
		try {
			return executor.evaluate(ClassUtil.nonNullPivot(query.getOwnedBody()));
		}
		finally {
			executor.popEvaluationEnvironment();
		}
	}

	/**
	 * @since 1.3
	 */
	@Override
	public @Nullable Object basicEvaluate(@NonNull Executor executor, @NonNull TypedElement caller, @Nullable Object @NonNull [] boxedSourceAndArgumentValues) {
		if (specification.getOwnedBody() == null) {
			try {
				MetamodelManager metamodelManager = executor.getMetamodelManager();
				metamodelManager.parseSpecification(specification);
			} catch (ParserException e) {
				throw new InvalidValueException(e, "parse failure", executor.getEvaluationEnvironment(), boxedSourceAndArgumentValues[0], caller);
			}
		}
		ExpressionInOCL query = specification;
		EvaluationEnvironment nestedEvaluationEnvironment = ((ExecutorExtension)executor).pushEvaluationEnvironment(query, caller);
		nestedEvaluationEnvironment.add(ClassUtil.nonNullModel(query.getOwnedContext()), boxedSourceAndArgumentValues[0]);
		List<Variable> parameterVariables = query.getOwnedParameters();
		int iMax = Math.min(parameterVariables.size(), boxedSourceAndArgumentValues.length-1);
		for (int i = 0; i < iMax; i++) {
			nestedEvaluationEnvironment.add(ClassUtil.nonNullModel(parameterVariables.get(i)), boxedSourceAndArgumentValues[i+1]);
		}
		try {
			return executor.evaluate(ClassUtil.nonNullPivot(query.getOwnedBody()));
		}
		finally {
			executor.popEvaluationEnvironment();
		}
	}
}