/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.pivot.internal.evaluation;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor.EvaluationVisitorExtension;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.internal.cse.CommonSubExpressionAnalysis;
import org.eclipse.ocl.pivot.internal.manager.SymbolicOCLExecutor;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.15
 */
public class SymbolicAnalysis extends SymbolicOCLExecutor	// FIXME merge SymbolicAnalysis + SymbolicOCLExecutor
{
	protected final @NonNull ExpressionInOCL expressionInOCL;
	protected final @NonNull CommonSubExpressionAnalysis cseAnalysis;

	/**
	 * Initializes the symbolic analysis of expressionInOCL that delegates to a non-symbolic evaluation visitor.
	 */
	public SymbolicAnalysis(@NonNull ExpressionInOCL expressionInOCL, @NonNull EnvironmentFactoryInternalExtension environmentFactory, @NonNull ModelManager modelManager) {
		super(environmentFactory, modelManager);
		this.expressionInOCL = expressionInOCL;
		this.cseAnalysis = new CommonSubExpressionAnalysis();
	}

	@Override
	protected @NonNull EvaluationVisitorExtension createEvaluationVisitor() {
		EvaluationVisitorExtension evaluationVisitor = super.createEvaluationVisitor();
		SymbolicEvaluationVisitor symbolicEvaluationVisitor = new SymbolicEvaluationVisitor(evaluationVisitor);
		return symbolicEvaluationVisitor;
	}

	public void initializeEvaluationEnvironment(@NonNull ExpressionInOCL expressionInOCL, @Nullable Object contextElement, @Nullable Object @Nullable [] parameters) {
		cseAnalysis.analyze(expressionInOCL);
		AbstractSymbolicEvaluationEnvironment symbolicEvaluationEnvironment = (AbstractSymbolicEvaluationEnvironment) initializeEvaluationEnvironment(expressionInOCL);
		IdResolver idResolver = environmentFactory.getIdResolver();
		Variable contextVariable = expressionInOCL.getOwnedContext();
		if (contextVariable != null) {
			Object contextValue = idResolver.boxedValueOf(contextElement);
			symbolicEvaluationEnvironment.add(contextVariable, contextValue);
			symbolicEvaluationEnvironment.traceValue(contextVariable, contextElement);
		}
		int i = 0;
		assert parameters != null;
		for (Variable parameterVariable : PivotUtil.getOwnedParameters(expressionInOCL)) {
			Object parameter = parameters[i++];
			Object parameterValue = idResolver.boxedValueOf(parameter);
			symbolicEvaluationEnvironment.add(parameterVariable, parameterValue);
			symbolicEvaluationEnvironment.traceValue(parameterVariable, parameter);
		}
		symbolicEvaluate(expressionInOCL);
	}

	public @NonNull SymbolicValue symbolicEvaluate(@NonNull ExpressionInOCL expressionInOCL) {
		AbstractSymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		SymbolicValue result = evaluationEnvironment.symbolicEvaluate(expressionInOCL);
		resolveHypotheses();
		return result;
	}

	@Override
	public @NonNull String toString() {
		AbstractSymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		StringBuilder s = new StringBuilder();
		for (EObject eObject : new TreeIterable(expressionInOCL, true)) {
			s.append("\n  ");
			for (EObject eParent = eObject; eParent != null && eParent != expressionInOCL; eParent = eParent.eContainer()) {
				s.append("  ");
			}
			s.append(eObject.eClass().getName());
			s.append(" : ");
			s.append(eObject.toString());
			s.append("\n  ");
			for (EObject eParent = eObject; eParent != null && eParent != expressionInOCL; eParent = eParent.eContainer()) {
				s.append("  ");
			}
			s.append("  => ");
			SymbolicValue symbolicValue = evaluationEnvironment.basicGetSymbolicValue(eObject);
			if (symbolicValue == null) {
				s.append("not-computed");
			}
			else {
				s.append(symbolicValue.getClass().getSimpleName());
				s.append(" : ");
				s.append(symbolicValue);
			}
		}
		List<@NonNull HypothesizedSymbolicEvaluationEnvironment> hypothesizedEvaluationEnvironments = getHypothesizedEvaluationEnvironments();
		if (hypothesizedEvaluationEnvironments != null) {
			StringUtil.appendIndentation(s, 1);
			for (HypothesizedSymbolicEvaluationEnvironment hypothesizedSymbolicEvaluationEnvironments : hypothesizedEvaluationEnvironments) {
				hypothesizedSymbolicEvaluationEnvironments.toString(s, 1);
			}
		}
		return s.toString();
	}
}
