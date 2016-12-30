/*******************************************************************************
 * Copyright (c) 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionItem;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.CollectionLiteralPart;
import org.eclipse.ocl.pivot.CollectionRange;
import org.eclipse.ocl.pivot.EnumLiteralExp;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.InvalidLiteralExp;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.MapLiteralExp;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.MessageExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.PrimitiveLiteralExp;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.ShadowExp;
import org.eclipse.ocl.pivot.ShadowPart;
import org.eclipse.ocl.pivot.StateExp;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * A static analysis visitor for the invalidity or otherwise of OCL expressions.
 *
 * true => is not invalid
 * null => may be invalid
 * false => is invalid
 * @since 1.3
 */
public class InvalidAnalysis
{
	protected enum Status { INVALID, VALID, MAYBE_INVALID };

	protected static @NonNull Status accumulate(@NonNull Status... statuses) {
		boolean isValid = true;
		for (@NonNull Status status : statuses) {
			if (status == Status.INVALID) {
				return Status.INVALID;
			}
			if (status == Status.MAYBE_INVALID) {
				isValid = false;;
			}
		}
		return isValid ? Status.VALID : Status.MAYBE_INVALID;
	}

	public static @NonNull Status analyze(@NonNull OCLExpression oclExpression) {
		return oclExpression.accept(new NotInvalidAnalysisVisitor());
	}

	//	private @NonNull Map<@NonNull Element, @NonNull Status> element2status = new HashMap<>();

	protected static class NotInvalidAnalysisVisitor extends AbstractExtendingVisitor<@NonNull Status, @Nullable Object>
	{
		public NotInvalidAnalysisVisitor() {
			super(null);
		}

		@Override
		public @NonNull Status visitCollectionItem(@NonNull CollectionItem collectionItem) {
			OCLExpression ownedItem = PivotUtil.getOwnedItem(collectionItem);
			return ownedItem.accept(this);
		}

		@Override
		public @NonNull Status visitCollectionLiteralExp(@NonNull CollectionLiteralExp collectionLiteralExp) {
			Status status = Status.VALID;
			for (@NonNull CollectionLiteralPart part : PivotUtil.getOwnedParts(collectionLiteralExp)) {
				status = accumulate(status, part.accept(this));
			}
			return status;
		}

		@Override
		public @NonNull Status visitCollectionRange(@NonNull CollectionRange collectionRange) {
			OCLExpression ownedFirst = PivotUtil.getOwnedFirst(collectionRange);
			OCLExpression ownedLast = PivotUtil.getOwnedLast(collectionRange);
			return accumulate(ownedFirst.accept(this), ownedLast.accept(this));
		}

		@Override
		public @NonNull Status visitEnumLiteralExp(@NonNull EnumLiteralExp el) {
			return Status.VALID;
		}

		@Override
		public @NonNull Status visitExpressionInOCL(@NonNull ExpressionInOCL expressionInOCL) {
			OCLExpression ownedBody = PivotUtil.getOwnedBody(expressionInOCL);
			return ownedBody.accept(this);
		}

		@Override
		public @NonNull Status visitIfExp(@NonNull IfExp ifExp) {
			OCLExpression ownedCondition = PivotUtil.getOwnedCondition(ifExp);
			OCLExpression ownedThen = PivotUtil.getOwnedThen(ifExp);
			OCLExpression ownedElse = PivotUtil.getOwnedElse(ifExp);
			return accumulate(ownedCondition.accept(this), ownedThen.accept(this), ownedElse.accept(this));
		}

		@Override
		public @NonNull Status visitInvalidLiteralExp(@NonNull InvalidLiteralExp invalidLiteralExp) {
			return Status.INVALID;
		}

		@Override
		public @NonNull Status visitIterateExp(@NonNull IterateExp iterateExp) {
			OCLExpression ownedSource = PivotUtil.getOwnedSource(iterateExp);
			Variable ownedResult = PivotUtil.getOwnedResult(iterateExp);
			OCLExpression ownedBody = PivotUtil.getOwnedBody(iterateExp);
			return accumulate(ownedSource.accept(this), ownedResult.accept(this), ownedBody.accept(this), Status.INVALID);		// FIXME any/index analysis
		}

		@Override
		public @NonNull Status visitIteratorExp(@NonNull IteratorExp iteratorExp) {
			OCLExpression ownedSource = PivotUtil.getOwnedSource(iteratorExp);
			OCLExpression ownedBody = PivotUtil.getOwnedBody(iteratorExp);
			return accumulate(ownedSource.accept(this), ownedBody.accept(this), Status.INVALID);		// FIXME any/index analysis
		}

		@Override
		public @NonNull Status visitLetExp(@NonNull LetExp letExp) {
			OCLExpression ownedSource = PivotUtil.getOwnedIn(letExp);
			Variable ownedVariable = PivotUtil.getOwnedVariable(letExp);
			return accumulate(ownedSource.accept(this), ownedVariable.accept(this));
		}

		@Override
		public @NonNull Status visitMapLiteralExp(@NonNull MapLiteralExp mapLiteralExp) {
			Status status = Status.VALID;
			for (@NonNull MapLiteralPart part : PivotUtil.getOwnedParts(mapLiteralExp)) {
				status = accumulate(status, part.accept(this));
			}
			return status;
		}

		@Override
		public @NonNull Status visitMessageExp(@NonNull MessageExp m) {
			throw new UnsupportedOperationException("evaluation of MessageExp"); //$NON-NLS-1$
		}

		@Override
		public @NonNull Status visitOperationCallExp(@NonNull OperationCallExp operationCallExp) {
			OCLExpression ownedSource = PivotUtil.getOwnedSource(operationCallExp);
			Status status = ownedSource.accept(this);
			for (@NonNull OCLExpression ownedArgument : PivotUtil.getOwnedArguments(operationCallExp)) {
				status = accumulate(status, ownedArgument.accept(this));
			}
			return accumulate(status, Status.INVALID);		// FIXME Analyze operation
		}

		@Override
		public @NonNull Status visitOppositePropertyCallExp(@NonNull OppositePropertyCallExp oppositePropertyCallExp) {
			OCLExpression ownedSource = PivotUtil.getOwnedSource(oppositePropertyCallExp);
			return ownedSource.accept(this);
		}

		@Override
		public @NonNull Status visitPrimitiveLiteralExp(@NonNull PrimitiveLiteralExp primitiveLiteralExp) {
			return Status.VALID;
		}

		@Override
		public @NonNull Status visitPropertyCallExp(@NonNull PropertyCallExp propertyCallExp) {
			OCLExpression ownedSource = PivotUtil.getOwnedSource(propertyCallExp);
			return ownedSource.accept(this);
		}

		@Override
		public @NonNull Status visitShadowExp(@NonNull ShadowExp shadowExp) {
			Status status = Status.VALID;
			for (@NonNull ShadowPart part : PivotUtil.getOwnedParts(shadowExp)) {
				status = accumulate(status, part.accept(this));
			}
			return status;
		}

		@Override
		public @NonNull Status visitShadowPart(@NonNull ShadowPart shadowPart) {
			OCLExpression ownedInit = PivotUtil.getOwnedInit(shadowPart);
			return ownedInit.accept(this);
		}

		@Override
		public @NonNull Status visitStateExp(@NonNull StateExp s) {
			return Status.VALID;
		}

		@Override
		public @NonNull Status visitTupleLiteralExp(@NonNull TupleLiteralExp tupleLiteralExp) {
			Status status = Status.VALID;
			for (@NonNull TupleLiteralPart part : PivotUtil.getOwnedParts(tupleLiteralExp)) {
				status = accumulate(status, part.accept(this));
			}
			return status;
		}

		@Override
		public @NonNull Status visitTupleLiteralPart(@NonNull TupleLiteralPart tupleLiteralPart) {
			OCLExpression ownedInit = PivotUtil.getOwnedInit(tupleLiteralPart);
			return ownedInit.accept(this);
		}

		@Override
		public @NonNull Status visitTypeExp(@NonNull TypeExp t) {
			return Status.VALID;
		}

		@Override
		public @NonNull Status visitVariable(@NonNull Variable variable) {
			OCLExpression initExp = PivotUtil.getOwnedInit(variable);
			return initExp.accept(this);
		}

		@Override
		public @NonNull Status visitVariableExp(@NonNull VariableExp variableExp) {
			VariableDeclaration variableDeclaration = PivotUtil.getReferredVariable(variableExp);
			return variableDeclaration.accept(this);
		}

		@Override
		public @NonNull Status visiting(@NonNull Visitable visitable) {
			throw new IllegalArgumentException("Unsupported " + visitable.eClass().getName() + " for " + getClass().getSimpleName());
		}
	}
}
