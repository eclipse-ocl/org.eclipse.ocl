/**
 * Copyright (c) 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.BooleanLiteralExp;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.NullLiteralExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * The (static) FlowAnalysis of some context OCLExpression exploits the hierarchy of the encompassing AS expression to
 * identify expressions that are necessarily true/false when the context expression evaluates. For instance the if the
 * contsxt expression is at or below the elseExpression of some IfExp, then the conditionExpression of the IfExp is
 * necessarily true. Given a set of known true/false expressions reverse evaluation of certain expressions allows
 * deduction that certain variables are null/not-null. THis is then used to imprive the precision of null safety guards.
 *
 * e.g. in "let x : Integer = ... in x <> null implies x.toString()" we do not want to diagnose that "x.toString()" is unsafe.
 *
 * An optimum implementation of this class might perform some very powerful/slow symbolic analyses. The current implementation
 * has useful but incomplete understanding of comparisons and Boolean operations. It lacks deep traversal of variables.

 * @since 1.3
 */
public class FlowAnalysis
{
	/**
	 * A deduction performs a reverse valuation of an expression whose result is known.
	 * Each term is visited returning true/false/null according to whether the term
	 * confirms or contradicts or is unconstrained by the required result.
	 */
	protected static class AbstractDeduction extends AbstractExtendingVisitor<@Nullable Boolean, org.eclipse.ocl.pivot.internal.manager.FlowAnalysis>
	{
		public AbstractDeduction(@NonNull FlowAnalysis analyzer) {
			super(analyzer);
		}

		/**
		 * Return a verdict on the hypothesesis: "asExpression = knownValue", where knownValue is determined by the
		 * derived class. true for conformance, false for contradiction, null for not-known.
		 */
		public @Nullable Boolean evaluate(@NonNull OCLExpression asExpression) {
			return asExpression.accept(this);
		}

		/**
		 * Return true if asExpression is-non-null using the prevailing state of the analysis - avoiding a potentially
		 * infinite recursion of nested analyses.
		 */
		protected boolean isAlreadyNonNull(@NonNull OCLExpression asExpression) {
			if (asExpression.isIsRequired()) {
				return true;
			}
			if (asExpression instanceof VariableExp) {
				Boolean nullOrNonNull = context.variable2nullOrNonNull.get(((VariableExp)asExpression).getReferredVariable());
				return nullOrNonNull == Boolean.FALSE;
			}
			return false;
		}

		/**
		 * Return true if asExpression is-null using the prevailing state of the analysis - avoiding a potentially
		 * infinite recursion of nested analyses.
		 */
		protected boolean isAlreadyNull(@NonNull OCLExpression asExpression) {
			if (asExpression instanceof NullLiteralExp) {
				return true;
			}
			if (asExpression instanceof VariableExp) {
				Boolean nullOrNonNull = context.variable2nullOrNonNull.get(((VariableExp)asExpression).getReferredVariable());
				return nullOrNonNull == Boolean.TRUE;
			}
			return false;
		}

		@Override
		public @Nullable Boolean visiting(@NonNull Visitable visitable) {
			throw new IllegalArgumentException("Unsupported " + visitable.eClass().getName() + " for " + getClass().getSimpleName());
		}

		@Override
		public @Nullable Boolean visitBooleanLiteralExp(@NonNull BooleanLiteralExp object) {
			return null;
		}

		@Override
		public @Nullable Boolean visitNullLiteralExp(@NonNull NullLiteralExp object) {
			return null;
		}

		@Override
		public @Nullable Boolean visitOCLExpression(@NonNull OCLExpression object) {
			return null;
		}
	}

	protected static class DeductionFromFalse extends AbstractDeduction
	{
		public DeductionFromFalse(@NonNull FlowAnalysis analyzer) {
			super(analyzer);
		}

		@Override
		public @Nullable Boolean visitBooleanLiteralExp(@NonNull BooleanLiteralExp object) {
			return !object.isBooleanSymbol();
		}

		@Override
		public @Nullable Boolean visitOperationCallExp(@NonNull OperationCallExp object) {
			OperationId operationId = PivotUtil.getReferredOperation(object).getOperationId();
			if (PivotUtil.isSameOperation(operationId, OperationId.BOOLEAN_NOT)) {
				context.falseExpressions.add(PivotUtil.getOwnedSource(object));
				return Boolean.TRUE;
			}
			else if (PivotUtil.isSameOperation(operationId, OperationId.BOOLEAN_OR)) {
				context.falseExpressions.add(PivotUtil.getOwnedSource(object));
				context.falseExpressions.add(PivotUtil.getOwnedArgument(object, 0));
				return Boolean.TRUE;
			}
			else if (PivotUtil.isSameOperation(operationId, OperationId.OCLANY_EQUALS)) {
				OCLExpression ownedSource = PivotUtil.getOwnedSource(object);
				OCLExpression ownedArgument = PivotUtil.getOwnedArgument(object, 0);
				if (isAlreadyNull(ownedSource)) {
					context.nonNullExpressions.add(ownedArgument);
					return Boolean.TRUE;
				}
				else if (isAlreadyNull(ownedArgument)) {
					context.nonNullExpressions.add(ownedSource);
					return Boolean.TRUE;
				}
				// if isFutureNull ...
			}
			else if (PivotUtil.isSameOperation(operationId, OperationId.OCLANY_NOT_EQUALS)) {
				OCLExpression ownedSource = PivotUtil.getOwnedSource(object);
				OCLExpression ownedArgument = PivotUtil.getOwnedArgument(object, 0);
				if (isAlreadyNull(ownedSource)) {
					context.nullExpressions.add(ownedArgument);
					return Boolean.TRUE;
				}
				else if (isAlreadyNull(ownedArgument)) {
					context.nullExpressions.add(ownedSource);
					return Boolean.TRUE;
				}
				// if isFutureNull ...
			}
			return super.visitOperationCallExp(object);
		}
	}

	protected static class DeductionFromNull extends AbstractDeduction
	{
		protected final boolean isNull;

		public DeductionFromNull(@NonNull FlowAnalysis analyzer, boolean isNull) {
			super(analyzer);
			this.isNull = isNull;
		}

		@Override
		public @Nullable Boolean visitNullLiteralExp(@NonNull NullLiteralExp object) {
			return isNull;
		}

		@Override
		public @Nullable Boolean visitNavigationCallExp(@NonNull NavigationCallExp object) {
			boolean isRequired = object.isIsRequired();
			return isRequired ? !isNull : null;
		}

		@Override
		public @Nullable Boolean visitVariableExp(@NonNull VariableExp object) {
			VariableDeclaration variable = PivotUtil.getReferredVariable(object);
			return context.setVariable(variable, isNull);
		}
	}

	protected static class DeductionFromTrue extends AbstractDeduction
	{
		public DeductionFromTrue(@NonNull FlowAnalysis analyzer) {
			super(analyzer);
		}

		@Override
		public @Nullable Boolean visitBooleanLiteralExp(@NonNull BooleanLiteralExp object) {
			return object.isBooleanSymbol();
		}

		@Override
		public @Nullable Boolean visitOperationCallExp(@NonNull OperationCallExp object) {
			OperationId operationId = PivotUtil.getReferredOperation(object).getOperationId();
			if (PivotUtil.isSameOperation(operationId, OperationId.BOOLEAN_AND)) {
				context.trueExpressions.add(PivotUtil.getOwnedSource(object));
				context.trueExpressions.add(PivotUtil.getOwnedArgument(object, 0));
				return Boolean.TRUE;
			}
			else if (PivotUtil.isSameOperation(operationId, OperationId.BOOLEAN_IMPLIES)) {
				context.trueExpressions.add(PivotUtil.getOwnedSource(object));
				context.falseExpressions.add(PivotUtil.getOwnedArgument(object, 0));
				return Boolean.TRUE;
			}
			else if (PivotUtil.isSameOperation(operationId, OperationId.BOOLEAN_NOT)) {
				context.falseExpressions.add(PivotUtil.getOwnedSource(object));
				return Boolean.TRUE;
			}
			else if (PivotUtil.isSameOperation(operationId, OperationId.OCLANY_EQUALS)) {
				OCLExpression ownedSource = PivotUtil.getOwnedSource(object);
				OCLExpression ownedArgument = PivotUtil.getOwnedArgument(object, 0);
				if (isAlreadyNull(ownedSource)) {
					context.nullExpressions.add(ownedArgument);
					return Boolean.TRUE;
				}
				else if (isAlreadyNonNull(ownedSource)) {
					context.nonNullExpressions.add(ownedArgument);
					return Boolean.TRUE;
				}
				else if (isAlreadyNull(ownedArgument)) {
					context.nullExpressions.add(ownedSource);
					return Boolean.TRUE;
				}
				else if (isAlreadyNonNull(ownedArgument)) {
					context.nonNullExpressions.add(ownedSource);
					return Boolean.TRUE;
				}
				// if isFutureNull ...
			}
			else if (PivotUtil.isSameOperation(operationId, OperationId.OCLANY_NOT_EQUALS)) {
				OCLExpression ownedSource = PivotUtil.getOwnedSource(object);
				OCLExpression ownedArgument = PivotUtil.getOwnedArgument(object, 0);
				if (isAlreadyNull(ownedSource)) {
					context.nonNullExpressions.add(ownedArgument);
					return Boolean.TRUE;
				}
				else if (isAlreadyNull(ownedArgument)) {
					context.nonNullExpressions.add(ownedSource);
					return Boolean.TRUE;
				}
				// if isFutureNull ...
			}
			return super.visitOperationCallExp(object);
		}
	}

	public static @NonNull FlowAnalysis getFlowAnalysis(@NonNull EnvironmentFactory environmentFactory, @NonNull OCLExpression contextExpression) {
		OCLExpression eObject = contextExpression;
		for (EObject eContainer; (eContainer = eObject.eContainer()) instanceof OCLExpression; eObject = (OCLExpression)eContainer) {
			if (eContainer instanceof IfExp) {
				IfExp ifExp = (IfExp)eContainer;
				if (eObject == PivotUtil.getOwnedThen(ifExp)) {
					break;
				}
				else if (eObject == PivotUtil.getOwnedElse(ifExp)) {
					break;
				}
			}
			else if (eContainer instanceof OperationCallExp) {
				OperationCallExp operationCallExp = (OperationCallExp)eContainer;
				OperationId operationId = PivotUtil.getReferredOperation(operationCallExp).getOperationId();
				if (PivotUtil.isSameOperation(operationId, OperationId.BOOLEAN_AND)) {
					if (eObject == PivotUtil.getOwnedSource(operationCallExp)) {
						break;
					}
					else if (eObject == PivotUtil.getOwnedArgument(operationCallExp, 0)) {
						break;
					}
				}
				else if (PivotUtil.isSameOperation(operationId, OperationId.BOOLEAN_IMPLIES)) {
					if (eObject == PivotUtil.getOwnedSource(operationCallExp)) {
						break;
					}
					else if (eObject == PivotUtil.getOwnedArgument(operationCallExp, 0)) {
						break;
					}
				}
			}
		}
		MetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		if (metamodelManager instanceof MetamodelManagerInternal.MetamodelManagerInternalExtension2) {
			return ((MetamodelManagerInternal.MetamodelManagerInternalExtension2)metamodelManager).getFlowAnalysis(contextExpression);
		}
		else {
			return new FlowAnalysis(environmentFactory, contextExpression);
		}
	}

	protected final @NonNull EnvironmentFactory environmentFactory;
	protected final @NonNull OCLExpression contextExpression;
	protected final @NonNull List<@NonNull OCLExpression> falseExpressions = new ArrayList<>();
	protected final @NonNull List<@NonNull OCLExpression> nonNullExpressions = new ArrayList<>();
	protected final @NonNull List<@NonNull OCLExpression> nullExpressions = new ArrayList<>();
	protected final @NonNull List<@NonNull OCLExpression> trueExpressions = new ArrayList<>();
	protected final @NonNull AbstractDeduction reverseDeduction = new AbstractDeduction(this);
	protected final @NonNull AbstractDeduction reverseDeductionFromTrue = new DeductionFromTrue(this);
	protected final @NonNull AbstractDeduction reverseDeductionFromNonNull = new DeductionFromNull(this, false);
	protected final @NonNull AbstractDeduction reverseDeductionFromNull = new DeductionFromNull(this, true);
	protected final @NonNull AbstractDeduction reverseDeductionFromFalse = new DeductionFromFalse(this);
	protected final @NonNull Map<@NonNull VariableDeclaration, @Nullable Boolean> variable2nullOrNonNull = new HashMap<>();

	protected FlowAnalysis(@NonNull EnvironmentFactory environmentFactory, @NonNull OCLExpression contextExpression) {
		this.environmentFactory = environmentFactory;
		this.contextExpression = contextExpression;
		for (EObject eObject = contextExpression, eContainer = eObject.eContainer(); eContainer instanceof OCLExpression; eObject = eContainer, eContainer = eContainer.eContainer()) {
			if (eContainer instanceof IfExp) {
				IfExp ifExp = (IfExp)eContainer;
				if (eObject == PivotUtil.getOwnedThen(ifExp)) {
					trueExpressions.add(PivotUtil.getOwnedCondition(ifExp));
				}
				else if (eObject == PivotUtil.getOwnedElse(ifExp)) {
					falseExpressions.add(PivotUtil.getOwnedCondition(ifExp));
				}
			}
			else if (eContainer instanceof LetExp) {
				LetExp letExp = (LetExp)eContainer;
				Variable letVariable = PivotUtil.getOwnedVariable(letExp);
				OCLExpression initExpression = PivotUtil.getOwnedInit(letVariable);
				FlowAnalysis variableAnalysis = ((MetamodelManagerInternal.MetamodelManagerInternalExtension2)environmentFactory.getMetamodelManager()).getFlowAnalysis(initExpression);
				if (variableAnalysis.isNull()) {
					setVariable(letVariable, true);
				}
				else if (variableAnalysis.isNonNull()) {
					setVariable(letVariable, false);
				}
			}
			else if (eContainer instanceof OperationCallExp) {
				OperationCallExp operationCallExp = (OperationCallExp)eContainer;
				OperationId operationId = PivotUtil.getReferredOperation(operationCallExp).getOperationId();
				if (PivotUtil.isSameOperation(operationId, OperationId.BOOLEAN_AND)) {
					if (eObject == PivotUtil.getOwnedSource(operationCallExp)) {
						trueExpressions.add(PivotUtil.getOwnedArgument(operationCallExp, 0));
					}
					else if (eObject == PivotUtil.getOwnedArgument(operationCallExp, 0)) {
						trueExpressions.add(PivotUtil.getOwnedSource(operationCallExp));
					}
				}
				else if (PivotUtil.isSameOperation(operationId, OperationId.BOOLEAN_IMPLIES)) {
					if (eObject == PivotUtil.getOwnedSource(operationCallExp)) {
						falseExpressions.add(PivotUtil.getOwnedArgument(operationCallExp, 0));
					}
					else if (eObject == PivotUtil.getOwnedArgument(operationCallExp, 0)) {
						trueExpressions.add(PivotUtil.getOwnedSource(operationCallExp));
					}
				}
			}
		}
		int iTrue = 0;
		int iNull = 0;
		int iNonNull = 0;
		int iFalse = 0;
		while ((iTrue < trueExpressions.size()) || (iNull < nullExpressions.size()) || (iNonNull < nonNullExpressions.size()) || (iFalse < falseExpressions.size())) {
			if (iTrue < trueExpressions.size()) {
				OCLExpression trueExpression = trueExpressions.get(iTrue++);
				reverseDeductionFromTrue.evaluate(trueExpression);
			}
			if (iNull < nullExpressions.size()) {
				OCLExpression nullExpression = nullExpressions.get(iNull++);
				reverseDeductionFromNull.evaluate(nullExpression);
			}
			if (iNonNull < nonNullExpressions.size()) {
				OCLExpression nonNullExpression = nonNullExpressions.get(iNonNull++);
				reverseDeductionFromNonNull.evaluate(nonNullExpression);
			}
			if (iFalse < falseExpressions.size()) {
				OCLExpression falseExpression = falseExpressions.get(iFalse++);
				reverseDeductionFromFalse.evaluate(falseExpression);
			}
		}
	}

	public boolean setVariable(@NonNull VariableDeclaration variable, boolean isNullOrNonNull) {
		Boolean oldNullOrNonNull = variable2nullOrNonNull.put(variable, isNullOrNonNull);
		if (oldNullOrNonNull == null) {
			return true;
		}
		return oldNullOrNonNull.booleanValue() == isNullOrNonNull;
	}

	public boolean isNonNull() {
		return reverseDeduction.isAlreadyNonNull(contextExpression);
	}

	public boolean isNull() {
		return reverseDeduction.isAlreadyNull(contextExpression);
	}
}
