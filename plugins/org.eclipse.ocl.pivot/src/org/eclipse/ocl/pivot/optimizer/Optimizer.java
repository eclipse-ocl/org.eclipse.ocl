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
package org.eclipse.ocl.pivot.optimizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.IteratorVariable;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.internal.complete.CompleteEnvironmentInternal;
import org.eclipse.ocl.pivot.internal.manager.TemplateParameterSubstitutionVisitor;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.Pair;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

/**
 * @since 1.23
 */
public class Optimizer
{
	protected final @NonNull EnvironmentFactoryInternal environmentFactory;

	public Optimizer(@NonNull EnvironmentFactoryInternal environmentFactory) {
		this.environmentFactory = environmentFactory;
	}

	public @NonNull ExpressionInOCL optimize(@NonNull ExpressionInOCL query) {
		for (EObject eObject : new TreeIterable(query, true)) {
			if (eObject instanceof LoopExp) {
				LoopExp asLoopExp = (LoopExp)eObject;
				Iteration asIteration = asLoopExp.getReferredIteration();
				assert asIteration != null;
				LanguageExpression bodyExpression = asIteration.getBodyExpression();
				if (bodyExpression != null) {
					try {
						ExpressionInOCL asExpressionInOCL = ((EnvironmentFactoryInternalExtension)environmentFactory).parseSpecification(bodyExpression);
						optimize(asLoopExp, asExpressionInOCL);
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
//		protected @NonNull Map<@NonNull VariableDeclaration, @NonNull TypedElement> actualParameterSubstitutions;
		protected @Nullable List<@NonNull String> errors = null;

		public ExpressionCopier(@NonNull EnvironmentFactoryInternal environmentFactory,
			@NonNull TemplateParameterSubstitutions templateParameterSubstitutions/*,
			@NonNull Map<@NonNull VariableDeclaration, @NonNull TypedElement> actualParameterSubstitutions*/) {
			this.environmentFactory = environmentFactory;
			this.templateParameterSubstitutions = templateParameterSubstitutions;
//			this.actualParameterSubstitutions = actualParameterSubstitutions;
		}

		protected void addError(@NonNull String string) {
			List<@NonNull String> errors2 = errors;
			if (errors2 == null) {
				errors = errors2 = new ArrayList<>();
			}
			errors2.add(string);
		}

		// Overridden to specialize types to calling context.
		@Override
		public EObject get(Object oIn) {
			if (oIn instanceof Type) {
				CompleteEnvironmentInternal completeEnvironment = environmentFactory.getCompleteEnvironment();
				Type asSpecializedType = completeEnvironment.getSpecializedType((Type) oIn, templateParameterSubstitutions);
				return asSpecializedType;
			}
			return super.get(oIn);
		}

		public @Nullable List<@NonNull String> getErrors() {
			return errors;
		}
	}

	@SuppressWarnings("serial")
	protected class IterationSpecializer extends ExpressionCopier
	{
		protected final @NonNull LoopExp asLoopExp;
		protected final @NonNull Iteration asIteration;
		protected final @NonNull Map<@NonNull String, @NonNull IteratorVariable> name2outerIteratorVariable = new HashMap<>();
		protected final @NonNull Map<@NonNull IteratorVariable, @Nullable IteratorVariable> outerIteratorVariable2specializedIteratorVariable = new HashMap<>();

		public IterationSpecializer(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull LoopExp asLoopExp,
				@NonNull TemplateParameterSubstitutions templateParameterSubstitutions) {
			super(environmentFactory, templateParameterSubstitutions);
			this.asLoopExp = asLoopExp;
			this.asIteration = PivotUtil.getReferredIteration(asLoopExp);
			for (@NonNull Variable asIteratorVariable : PivotUtil.getOwnedIterators(asLoopExp)) {
				Parameter asIterationParameter = PivotUtil.getRepresentedParameter(asIteratorVariable);
				name2outerIteratorVariable.put(PivotUtil.getName(asIterationParameter), (IteratorVariable)asIteratorVariable);
			}
		}

		// Overridden to populate parameter2variable mapping from inner self/iterator to calling context/iterator
		@Override
		public EObject copy(EObject oIn) {			// ?? Clone VE of lambda
			EObject oOut = super.copy(oIn);
			if (oIn instanceof IteratorVariable) {
				IteratorVariable asInnerUnspecializedIteratorVariable = (IteratorVariable)oIn;
				IteratorVariable asInnerSpecializedIteratorVariable = (IteratorVariable)oOut;
				String name = PivotUtil.getName(asInnerUnspecializedIteratorVariable);
				IteratorVariable asOuterIteratorIteratorVariable = name2outerIteratorVariable.get(name);
				if (asOuterIteratorIteratorVariable != null) {
					Variable old = outerIteratorVariable2specializedIteratorVariable.put(asOuterIteratorIteratorVariable, asInnerSpecializedIteratorVariable);
					if (old != null) {
						addError("Conflicting implementations of " + asOuterIteratorIteratorVariable + " representing " + PivotUtil.getRepresentedParameter(asOuterIteratorIteratorVariable));
					}
				}
			}
			return oOut;
		}

		protected void postProcess() {
			assert outerIteratorVariable2specializedIteratorVariable.size() <= name2outerIteratorVariable.size();
			for (@NonNull IteratorVariable asOuterIteratorVariable : name2outerIteratorVariable.values()) {
				IteratorVariable asSpecializedIteratorVariable = outerIteratorVariable2specializedIteratorVariable.get(asOuterIteratorVariable);
				if (asSpecializedIteratorVariable == null) {
					addError("Missing usage of " + asOuterIteratorVariable);
				}
				else {
					asOuterIteratorVariable.setSpecializedIterator(asSpecializedIteratorVariable);
				}
			}
		}

		public @NonNull ExpressionInOCL specialize(@NonNull ExpressionInOCL asExpression) {
			@SuppressWarnings("null")
			ExpressionInOCL asCopy = (@NonNull ExpressionInOCL)copy(asExpression);
			copyReferences();
			postProcess();
			return asCopy;
		}
	}

	//
	//	Update iteration calls for which the iteration has an OCL-defined body with a specialization of that body.
	//
	private void optimize(@NonNull LoopExp asLoopExp, @NonNull ExpressionInOCL asExpressionInOCL) throws ParserException {
		OCLExpression asSource = PivotUtil.getOwnedSource(asLoopExp);
		Type sourceType = PivotUtil.getType(asSource);
		List<@NonNull Variable> asIteratorVariables = PivotUtilInternal.getOwnedIteratorsList(asLoopExp);
		Iteration asIteration = PivotUtil.getReferredIteration(asLoopExp);
		List<@NonNull Parameter> asIterationIterators = PivotUtilInternal.getOwnedIteratorsList(asIteration);
		List<@NonNull Parameter> asIterationParameters = PivotUtilInternal.getOwnedParametersList(asIteration);
		//
		List<@NonNull Pair<@NonNull Type, @NonNull Type>> formal2actuals = new ArrayList<>();
		formal2actuals.add(new Pair<>(PivotUtil.getOwningClass(asIteration), sourceType));
		//
		int iMax = Math.max(asIterationIterators.size(), asIteratorVariables.size());
		for (int i = 0; i < iMax; i++) {
			Parameter asIterationParameter = asIterationIterators.get(i);
			Variable asIteratorVariable = asIteratorVariables.get(i);
			formal2actuals.add(new Pair<>(PivotUtil.getType(asIterationParameter), PivotUtil.getType(asIteratorVariable)));
		}
		//
		if (asLoopExp instanceof IterateExp) {
			IterateExp asIterateExp = (IterateExp)asLoopExp;
			List<@NonNull Parameter> asIterationAccumulators = PivotUtilInternal.getOwnedAccumulatorsList(asIteration);
			iMax = Math.max(asIterationAccumulators.size(), 1);
			for (int i = 0; i < iMax; i++) {
				Parameter asIterationAccumulatorParameter = asIterationAccumulators.get(i);
				Variable asResultVariable = PivotUtil.getOwnedResult(asIterateExp);
				formal2actuals.add(new Pair<>(PivotUtil.getType(asIterationAccumulatorParameter), PivotUtil.getType(asResultVariable)));
			}
			List<@NonNull OCLExpression> asBodyExpressions = PivotUtilInternal.getOwnedBodiesList(asIterateExp);
			iMax = Math.max(asIterationParameters.size(), asBodyExpressions.size());
			for (int i = 0; i < iMax; i++) {						// ?? regular parameters unchanged / lambdas cloned
				Parameter asIterationAccumulatorParameter = asIterationAccumulators.get(i);
				OCLExpression asBodyExpression = asBodyExpressions.get(i);
				formal2actuals.add(new Pair<>(PivotUtil.getType(asIterationAccumulatorParameter), PivotUtil.getType(asBodyExpression)));
			}
		}
		else {
			IteratorExp asIteratorExp = (IteratorExp)asLoopExp;
			Parameter asBodyParameter = asIterationParameters.get(0);
			OCLExpression asBodyExpression = PivotUtil.getOwnedBody(asIteratorExp);
			formal2actuals.add(new Pair<>(PivotUtil.getType(asBodyParameter), PivotUtil.getType(asBodyExpression)));
		}
		TemplateParameterSubstitutions templateParameterSubstitutions = TemplateParameterSubstitutionVisitor.createBindings(environmentFactory, sourceType, asIteration, formal2actuals);
		IterationSpecializer specializer = new IterationSpecializer(environmentFactory, asLoopExp, templateParameterSubstitutions);
		ExpressionInOCL asCopy = specializer.specialize(asExpressionInOCL);
		List<@NonNull String> errors = specializer.getErrors();
		if (errors != null) {
			StringBuilder s = new StringBuilder();
			s.append("Failed to specialize " + asIteration);
			for (@NonNull String anError : errors) {
				s.append("\n\t" + anError);
			}
			throw new ParserException(s.toString());
		}
		asLoopExp.setOwnedSpecializedBody(asCopy);
	}
}
