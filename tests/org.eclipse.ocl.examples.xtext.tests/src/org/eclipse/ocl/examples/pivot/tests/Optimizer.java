/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.pivot.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.internal.complete.CompleteEnvironmentInternal;
import org.eclipse.ocl.pivot.internal.manager.TemplateParameterSubstitutionVisitor;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.utilities.Pair;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

public class Optimizer
{
	protected final @NonNull EnvironmentFactoryInternal environmentFactory;

	public Optimizer(@NonNull EnvironmentFactoryInternal environmentFactory) {
		this.environmentFactory = environmentFactory;
	}


	public @NonNull ExpressionInOCL optimize(@NonNull ExpressionInOCL query) {
	//	List<@NonNull CallExp> asCallExps = new ArrayList<>();
		for (EObject eObject : new TreeIterable(query, true)) {
			if (eObject instanceof LoopExp) {
				LoopExp asLoopExp = (LoopExp)eObject;
				Iteration asIteration = asLoopExp.getReferredIteration();
				assert asIteration != null;
				if (asIteration.getBodyExpression() != null) {
					try {
						optimize(asLoopExp);
					} catch (ParserException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else if (eObject instanceof OperationCallExp) {
				OperationCallExp asOperationCallExp = (OperationCallExp)eObject;
				Operation asOperation = asOperationCallExp.getReferredOperation();
				assert asOperation != null;
				if (asOperation.getBodyExpression() != null) {
			//		asCallExps.add(asOperationCallExp);
				}
			}
		}
		return query;
	}


	/**
	 * An ExpressionCopier deep copies an OCLExpression tree, exploiting the forward traceability of context to
	 * update references and using sibling to distinguish multiple targets.
	 */
	@SuppressWarnings("serial")
	protected class ExpressionCopier extends EcoreUtil.Copier
	{
		protected @NonNull EnvironmentFactoryInternal environmentFactory;
		protected @NonNull TemplateParameterSubstitutions templateParameterSubstitutions;
		protected @NonNull Map<@NonNull VariableDeclaration, @NonNull TypedElement> actualParameterSubstitutions;

		public ExpressionCopier(@NonNull EnvironmentFactoryInternal environmentFactory,
			@NonNull TemplateParameterSubstitutions templateParameterSubstitutions,
			@NonNull Map<@NonNull VariableDeclaration, @NonNull TypedElement> actualParameterSubstitutions) {
			this.environmentFactory = environmentFactory;
			this.templateParameterSubstitutions = templateParameterSubstitutions;
			this.actualParameterSubstitutions = actualParameterSubstitutions;
		}

	// FIXME enforce unique names on let-variables, iterators
		@Override
		public EObject get(Object oIn) {
			if (oIn instanceof Type) {
				CompleteEnvironmentInternal completeEnvironment = environmentFactory.getCompleteEnvironment();
				Type asSpecializedType = completeEnvironment.getSpecializedType((Type) oIn, templateParameterSubstitutions);
				return asSpecializedType;
			}
			if (oIn instanceof Parameter) {
				TypedElement typedElement = actualParameterSubstitutions.get(oIn);
				CompleteEnvironmentInternal completeEnvironment = environmentFactory.getCompleteEnvironment();
			//	Type asSpecializedType = completeEnvironment.getSpecializedType((Type) oIn, templateParameterSubstitutions);
			//	return asSpecializedType;
			}
			if (oIn instanceof VariableExp) {
				CompleteEnvironmentInternal completeEnvironment = environmentFactory.getCompleteEnvironment();
			//	Type asSpecializedType = completeEnvironment.getSpecializedType((Type) oIn, templateParameterSubstitutions);
			//	return asSpecializedType;
			}
			if (oIn instanceof Variable) {
				TypedElement typedElement = actualParameterSubstitutions.get(oIn);
				CompleteEnvironmentInternal completeEnvironment = environmentFactory.getCompleteEnvironment();
			//	Type asSpecializedType = completeEnvironment.getSpecializedType((Type) oIn, templateParameterSubstitutions);
				if (typedElement != null) return typedElement;
			}
			if (oIn instanceof Element) {
			/*	List<@NonNull Element> oOuts = source2targets.get(oIn);
				if (oOuts != null) {
					assert oOuts.size() == 1;
					return oOuts.get(0);
				}
				oOuts = qvtr2qvtc.getGlobalTargets((Element) oIn);
				if (oOuts != null) {
					assert oOuts.size() == 1;
					return oOuts.get(0);
				} */
				return super.get(oIn);		// e.g. Features/Collections which are re-useable externals
			}
			else {
				return super.get(oIn);
			}
		}

		@Override
		public EObject copy(EObject oIn) {			// Clone VE of lambda
		/*	try {
				if (oIn instanceof IteratorVariable) {
					VariableDeclaration coreVariable = variablesAnalysis.getCoreVariable((IteratorVariable)oIn);
					putTrace(coreVariable, (IteratorVariable)oIn);
					return coreVariable;
				}
				else if (oIn instanceof LetVariable) {
					VariableDeclaration coreVariable = variablesAnalysis.getCoreVariable((LetVariable)oIn);
					putTrace(coreVariable, (LetVariable)oIn);
					return coreVariable;
				}
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */
			return super.copy(oIn);
		}
	}

	private void optimize(@NonNull LoopExp asLoopExp) throws ParserException {
		Iteration asIteration = asLoopExp.getReferredIteration();
		assert asIteration != null;
		List<Parameter> asIterators = asIteration.getOwnedIterators();
		ExpressionInOCL asExpressionInOCL = ((EnvironmentFactoryInternalExtension)environmentFactory).parseSpecification(asIteration.getBodyExpression());
		assert asExpressionInOCL != null;
		Type sourceType = Objects.requireNonNull(asLoopExp.getOwnedSource().getType());
		//
		List<@NonNull Pair<@NonNull Type, @NonNull Type>> formal2actuals = new ArrayList<>();
		formal2actuals.add(new Pair<>(Objects.requireNonNull(asIteration.getOwningClass()), sourceType));
		//
		Map<@NonNull VariableDeclaration, @NonNull TypedElement> actualParameterSubstitutions = new HashMap<>();
	//	actualParameterSubstitutions.put(asExpressionInOCL.getOwnedContext(), asLoopExp.getOwnedSource());		-- source remains a parameter
		//
		List<Variable> asIteratorVariables = asLoopExp.getOwnedIterators();
		int iMax = Math.max(asIterators.size(), asIteratorVariables.size());
		for (int i = 0; i < iMax; i++) {
			Parameter asIteratorParameter = asIterators.get(i);
			Variable asIteratorVariable = asIteratorVariables.get(i);
			formal2actuals.add(new Pair<>(PivotUtil.getType(asIteratorParameter), PivotUtil.getType(asIteratorVariable)));
			actualParameterSubstitutions.put(asIteratorParameter, asIteratorVariable);
		}
		//
		if (asLoopExp instanceof IterateExp) {
			IterateExp asIterateExp = (IterateExp)asLoopExp;
			List<Parameter> asAccumulators = asIteration.getOwnedAccumulators();
			iMax = Math.max(asAccumulators.size(), 1);
			for (int i = 0; i < iMax; i++) {
				Parameter asAccumulatorParameter = asAccumulators.get(i);
				Variable asResultVariable = asIterateExp.getOwnedResult();
				formal2actuals.add(new Pair<>(PivotUtil.getType(asAccumulatorParameter), PivotUtil.getType(asResultVariable)));
				actualParameterSubstitutions.put(asAccumulatorParameter, asResultVariable);
			}
			List<Parameter> asBodyParameters = asIteration.getOwnedParameters();
			List<OCLExpression> asBodyExpressions = asIterateExp.getOwnedBodies();
			iMax = Math.max(asBodyParameters.size(), asBodyExpressions.size());
			for (int i = 0; i < iMax; i++) {						// ?? regular parameters unchanged / lambdas cloned
				Parameter asAccumulatorParameter = asAccumulators.get(i);
				OCLExpression asBodyExpression = asBodyExpressions.get(i);
				formal2actuals.add(new Pair<>(PivotUtil.getType(asAccumulatorParameter), PivotUtil.getType(asBodyExpression)));
				actualParameterSubstitutions.put(asAccumulatorParameter, asBodyExpression);
			}
		}
		else {
			IteratorExp asIteratorExp = (IteratorExp)asLoopExp;
			Parameter asBodyParameter = asIteration.getOwnedParameters().get(0);
			OCLExpression asBodyExpression = asIteratorExp.getOwnedBody();
			formal2actuals.add(new Pair<>(PivotUtil.getType(asBodyParameter), PivotUtil.getType(asBodyExpression)));
			actualParameterSubstitutions.put(asBodyParameter, asBodyExpression);
		}
		TemplateParameterSubstitutions templateParameterSubstitutions = TemplateParameterSubstitutionVisitor.createBindings(environmentFactory, sourceType, asIteration, formal2actuals);



		ExpressionCopier copier = new ExpressionCopier(environmentFactory, templateParameterSubstitutions, actualParameterSubstitutions);
		ExpressionInOCL asCopy = (ExpressionInOCL)copier.copy(asExpressionInOCL);
		copier.copyReferences();
		asLoopExp.setOwnedInlinedBody(asCopy);
	}

}
