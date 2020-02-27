/*******************************************************************************
 * Copyright (c) 2017, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.analysis;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.BooleanLiteralExp;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CollectionItem;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.CollectionLiteralPart;
import org.eclipse.ocl.pivot.CollectionRange;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.EnumLiteralExp;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.IntegerLiteralExp;
import org.eclipse.ocl.pivot.InvalidLiteralExp;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.IteratorExp;
import org.eclipse.ocl.pivot.IteratorVariable;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LetVariable;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.MapLiteralExp;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.MessageExp;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.NullLiteralExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.RealLiteralExp;
import org.eclipse.ocl.pivot.ResultVariable;
import org.eclipse.ocl.pivot.ShadowExp;
import org.eclipse.ocl.pivot.ShadowPart;
import org.eclipse.ocl.pivot.StateExp;
import org.eclipse.ocl.pivot.StringLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.internal.analysis.AbstractSymbolicValue.KnownBooleanValue;
import org.eclipse.ocl.pivot.library.LibraryIterationOrOperation;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * ASSymbolicEvaluationVisitor uses symbolic evauation to determine the 'value' of each expression node.
 * This performs constant folding and invalidoty analysis.
 *
 * @since 1.12
 */
public class ASSymbolicEvaluationVisitor extends AbstractExtendingVisitor<@NonNull SymbolicValue, @NonNull EnvironmentFactory>
{
	private final @Nullable ASSymbolicEvaluationVisitor parentSymbolicEvaluationVisitor;
	private final @NonNull Map<@NonNull Variable, @NonNull SymbolicValue> variable2knownValue = new HashMap<>();
//	private final @NonNull Map<@NonNull OCLExpression, @NonNull SymbolicValue> expression2symbolicValue = new HashMap<>();

	public ASSymbolicEvaluationVisitor(@NonNull EnvironmentFactory environmentFactory) {
		super(environmentFactory);
		this.parentSymbolicEvaluationVisitor = null;
	}

	public ASSymbolicEvaluationVisitor(@NonNull ASSymbolicEvaluationVisitor parentSymbolicEvaluationVisitor) {
		super(parentSymbolicEvaluationVisitor.context);
		this.parentSymbolicEvaluationVisitor = parentSymbolicEvaluationVisitor;
	}

	protected @NonNull ASSymbolicEvaluationVisitor createNestedSymbolicEvaluationVisitor(@NonNull ASSymbolicEvaluationVisitor parentSymbolicEvaluationVisitor) {
		return new ASSymbolicEvaluationVisitor(parentSymbolicEvaluationVisitor);
	}

	protected void deduceFromKnownValue(@NonNull SymbolicValue symbolicValue, @NonNull SymbolicValue knownValue) {
		// TODO Auto-generated method stub
	}

	protected @NonNull SymbolicValue getVariableValue(@NonNull VariableDeclaration asVariable) {
		SymbolicValue symbolicValue = variable2knownValue.get(asVariable);
		if (symbolicValue != null) {
			return symbolicValue;
		}
		if (parentSymbolicEvaluationVisitor != null) {
			return parentSymbolicEvaluationVisitor.getVariableValue(asVariable);
		}
		throw new IllegalStateException("Undefined variable " + asVariable);
	}

	private @NonNull SymbolicValue resolveResultValue(@NonNull CallExp asCallExp) {
		Operation operation = PivotUtil.getReferredOperation(asCallExp);
		SymbolicValue resultValue = visit(operation);
		boolean returnIsRequired = operation.isIsRequired();
		LibraryIterationOrOperation implementationClass = (LibraryIterationOrOperation)operation.getImplementation();
		if (implementationClass != null) {
			returnIsRequired = implementationClass.resolveReturnNullity(context, asCallExp, returnIsRequired);
		}
		if (returnIsRequired) {
			resultValue = resultValue.setNonNullValue();
		}
		return resultValue;
	}

	private @NonNull SymbolicValue setSafeKnownValue(@NonNull CallExp asCallExp, @NonNull SymbolicValue sourceValue, @NonNull SymbolicValue knownValue) {
		if (sourceValue.isInvalid()) {
			return AbstractSymbolicValue.KNOWN_INVALID;
		}
		else if (sourceValue.isNull()) {
			if (!asCallExp.isIsSafe()) {
				return AbstractSymbolicValue.KNOWN_INVALID;
			}
			else {
				return AbstractSymbolicValue.KNOWN_NULL;
			}
		}
		else {
			boolean maybeInvalid = sourceValue.maybeInvalid() || knownValue.maybeInvalid();
			boolean maybeNull = knownValue.maybeNull();
			if (sourceValue.maybeNull()) {
				if (!asCallExp.isIsSafe()) {
					maybeInvalid = true;
				}
				else {
					maybeNull = true;
				}
			}
			if (maybeNull || maybeInvalid) {
				return AbstractSymbolicValue.getUnknownValue(maybeNull, maybeInvalid);
			}
			else {
				return knownValue;
			}
		}
	}

	public @NonNull SymbolicValue visit(@NonNull OCLExpression asExp) {
		SymbolicValue symbolicValue = asExp.accept(this);
//		expression2symbolicValue.put(asExp, symbolicValue);
		return symbolicValue;
	}

	@Override
	public @NonNull SymbolicValue visitBooleanLiteralExp(@NonNull BooleanLiteralExp asBooleanLiteralExp) {
		return asBooleanLiteralExp.isBooleanSymbol() ? SymbolicBooleanValue.KNOWN_TRUE : SymbolicBooleanValue.KNOWN_FALSE;
	}

	@Override
	public @NonNull SymbolicValue visitCollectionItem(@NonNull CollectionItem asCollectionItem) {
		return visit(PivotUtil.getOwnedItem(asCollectionItem));
	}

	@Override
	public @NonNull SymbolicValue visitCollectionLiteralExp(@NonNull CollectionLiteralExp asCollectionLiteralExp) {
		SymbolicValue partsValue = AbstractSymbolicValue.KNOWN_NOT_NULL_LITERALS;
		for (@NonNull CollectionLiteralPart asPart : PivotUtil.getOwnedParts(asCollectionLiteralExp)) {
			partsValue = partsValue.union(visit(asPart));
		}
		if (partsValue.isInvalid()) {
			return AbstractSymbolicValue.KNOWN_INVALID;
		}
		else if (partsValue.isKnown()) {
			return AbstractSymbolicValue.KNOWN_NOT_NULL_LITERALS;
		}
		else {
			return AbstractSymbolicValue.getUnknownValue(false, partsValue.maybeInvalid());
		}
	}

	@Override
	public @NonNull SymbolicValue visitCollectionRange(@NonNull CollectionRange asCollectionRange) {
		SymbolicValue firstValue = visit(PivotUtil.getOwnedFirst(asCollectionRange));
		SymbolicValue lastValue = visit(PivotUtil.getOwnedLast(asCollectionRange));
		return firstValue.union(lastValue);
	}

	@Override
	public @NonNull SymbolicValue visitEnumLiteralExp(@NonNull EnumLiteralExp asEnumLiteralExp) {
		return SymbolicValue.KNOWN_NOT_NULL_LITERALS;
	}

	@Override
	public @NonNull SymbolicValue visitExpressionInOCL(@NonNull ExpressionInOCL asExpression) {
		OCLExpression asBody = PivotUtil.getOwnedBody(asExpression);
		SymbolicValue bodyValue = visit(asBody);
		return bodyValue;
	}

	@Override
	public @NonNull SymbolicValue visitIfExp(@NonNull IfExp asIfExp) {
		SymbolicBooleanValue conditionValue = (SymbolicBooleanValue) visit(PivotUtil.getOwnedCondition(asIfExp));
		ASSymbolicEvaluationVisitor thenSymbolicEvaluationVisitor = createNestedSymbolicEvaluationVisitor(this);
		thenSymbolicEvaluationVisitor.deduceFromKnownValue(conditionValue, KnownBooleanValue.KNOWN_TRUE);
		SymbolicValue thenValue = thenSymbolicEvaluationVisitor.visit(PivotUtil.getOwnedThen(asIfExp));
		ASSymbolicEvaluationVisitor elseSymbolicEvaluationVisitor = createNestedSymbolicEvaluationVisitor(this);
		thenSymbolicEvaluationVisitor.deduceFromKnownValue(conditionValue, KnownBooleanValue.KNOWN_FALSE);
		SymbolicValue elseValue = elseSymbolicEvaluationVisitor.visit(PivotUtil.getOwnedElse(asIfExp));
		if (conditionValue.isTrue()) {
			return thenValue;
		}
		else if (conditionValue.isFalse()) {
			return elseValue;
		}
		else if (conditionValue.isNull() || conditionValue.isInvalid()) {
			return AbstractSymbolicValue.KNOWN_INVALID;
		}
		else if (thenValue == elseValue)  {
			return thenValue;
		}
		else  {
			return thenValue.union(elseValue);
		}
	}

	@Override
	public @NonNull SymbolicValue visitIntegerLiteralExp(@NonNull IntegerLiteralExp asIntegerLiteralExp) {
		return SymbolicValue.KNOWN_NOT_NULL_LITERALS;
	}

	@Override
	public @NonNull SymbolicValue visitInvalidLiteralExp(@NonNull InvalidLiteralExp asInvalidLiteralExp) {
		return SymbolicValue.KNOWN_INVALID;
	}

	@Override
	public @NonNull SymbolicValue visitIterateExp(@NonNull IterateExp asIterateExp) {
		SymbolicValue sourceValue = visit(PivotUtil.getOwnedSource(asIterateExp));
		for (@NonNull Variable asIterator : PivotUtil.getOwnedIterators(asIterateExp)) {
			visit(asIterator);
		}
		visit(PivotUtil.getOwnedResult(asIterateExp));
		@SuppressWarnings("unused")
		SymbolicValue bodyValue = visit(PivotUtil.getOwnedBody(asIterateExp));
		SymbolicValue resultValue = resolveResultValue(asIterateExp);
		return setSafeKnownValue(asIterateExp, sourceValue, resultValue);
	}

	@Override
	public @NonNull SymbolicValue visitIteratorExp(@NonNull IteratorExp asIteratorExp) {
		SymbolicValue sourceValue = visit(PivotUtil.getOwnedSource(asIteratorExp));
		for (@NonNull Variable asIterator : PivotUtil.getOwnedIterators(asIteratorExp)) {
			visit(asIterator);
		}
		@SuppressWarnings("unused")
		SymbolicValue bodyValue = visit(PivotUtil.getOwnedBody(asIteratorExp));
		SymbolicValue resultValue = resolveResultValue(asIteratorExp);
		return setSafeKnownValue(asIteratorExp, sourceValue, resultValue);
	}

	@Override
	public @NonNull SymbolicValue visitIteratorVariable(@NonNull IteratorVariable asIteratorVariable) {
		SymbolicValue knownValue = variable2knownValue.get(asIteratorVariable);
		if (knownValue == null) {
			OCLExpression asInit = asIteratorVariable.getOwnedInit();
			if (asInit != null) {
				SymbolicValue initValue = visit(asInit);
				if (asIteratorVariable.isIsRequired()) {
					initValue = initValue.setNonNullValue();
				}
				knownValue = initValue;
			}
			else {
				boolean elementsMayBeNull = false;
				if (!asIteratorVariable.isIsRequired()) {
					LoopExp asLoopExp = (LoopExp)asIteratorVariable.eContainer();
					assert asLoopExp != null;
					if (!asLoopExp.isIsSafe()) {
						OCLExpression asSource = PivotUtil.getOwnedSource(asLoopExp);
						CollectionType sourceType = (CollectionType) PivotUtil.getType(asSource);
						if (!sourceType.isIsNullFree()) {
							elementsMayBeNull = true;
						}
					}
				}
				knownValue = AbstractSymbolicValue.getUnknownValue(elementsMayBeNull, false);
			}
			variable2knownValue.put(asIteratorVariable, knownValue);
		}
		return knownValue;
	}

	@Override
	public @NonNull SymbolicValue visitLetExp(@NonNull LetExp asLetExp) {
		@SuppressWarnings("unused")
		SymbolicValue letValue = visit(PivotUtil.getOwnedVariable(asLetExp));
		SymbolicValue inValue = visit(PivotUtil.getOwnedIn(asLetExp));
		return inValue;
	}

	@Override
	public @NonNull SymbolicValue visitLetVariable(@NonNull LetVariable asLetVariable) {
		SymbolicValue knownValue = variable2knownValue.get(asLetVariable);
		if (knownValue == null) {
			SymbolicValue initValue = visit(PivotUtil.getOwnedInit(asLetVariable));
			if (asLetVariable.isIsRequired()) {
				initValue = initValue.setNonNullValue();
			}
			knownValue = initValue;
			variable2knownValue.put(asLetVariable, knownValue);
		}
		return knownValue;
	}

	@Override
	public @NonNull SymbolicValue visitMapLiteralExp(@NonNull MapLiteralExp asMapLiteralExp) {
		SymbolicValue partsValue = AbstractSymbolicValue.KNOWN_NOT_NULL_LITERALS;
		for (@NonNull MapLiteralPart asPart : PivotUtil.getOwnedParts(asMapLiteralExp)) {
			partsValue = partsValue.union(visit(asPart));
		}
		if (partsValue.isInvalid()) {
			return AbstractSymbolicValue.KNOWN_INVALID;
		}
		else if (partsValue.isKnown()) {
			return AbstractSymbolicValue.KNOWN_NOT_NULL_LITERALS;
		}
		else {
			return AbstractSymbolicValue.getUnknownValue(false, partsValue.maybeInvalid());
		}
	}

	@Override
	public @NonNull SymbolicValue visitMapLiteralPart(@NonNull MapLiteralPart asMapLiteralPart) {
		SymbolicValue keyValue = visit(PivotUtil.getOwnedKey(asMapLiteralPart));
		SymbolicValue valueValue = visit(PivotUtil.getOwnedValue(asMapLiteralPart));
		return keyValue.union(valueValue);
	}

	@Override
	public @NonNull SymbolicValue visitMessageExp(@NonNull MessageExp asMessageExp) {
		return SymbolicValue.KNOWN_NOT_NULL_LITERALS;
	}

	@Override
	public @NonNull SymbolicValue visitNavigationCallExp(@NonNull NavigationCallExp asNavigationCallExp) {
		SymbolicValue sourceValue = visit(PivotUtil.getOwnedSource(asNavigationCallExp));
		SymbolicValue slotValue = visit(PivotUtil.getReferredProperty(asNavigationCallExp));
		return setSafeKnownValue(asNavigationCallExp, sourceValue, slotValue);
	}

	@Override
	public @NonNull SymbolicValue visitNullLiteralExp(@NonNull NullLiteralExp asNullLiteralExp) {
		return SymbolicValue.KNOWN_NULL;
	}

	@Override
	public @NonNull SymbolicValue visitOperation(@NonNull Operation asOperation) {
		boolean maybeNull = false;
		if (!asOperation.isIsRequired()) {
			maybeNull = true;
		}
		return AbstractSymbolicValue.getUnknownValue(maybeNull, false);
	}

	@Override
	public @NonNull SymbolicValue visitOperationCallExp(@NonNull OperationCallExp asOperationCallExp) {
		SymbolicValue sourceValue = visit(PivotUtil.getOwnedSource(asOperationCallExp));
		for (@NonNull OCLExpression asArgument : PivotUtil.getOwnedArguments(asOperationCallExp)) {
			visit(asArgument);
		}
		SymbolicValue resultValue = resolveResultValue(asOperationCallExp);
		return setSafeKnownValue(asOperationCallExp, sourceValue, resultValue);
	}

	@Override
	public @NonNull SymbolicValue visitParameterVariable(@NonNull ParameterVariable asParameterVariable) {
		return AbstractSymbolicValue.getUnknownValue(!asParameterVariable.isIsRequired(), false);
	}

	@Override
	public @NonNull SymbolicValue visitProperty(@NonNull Property asProperty) {
		boolean maybeNull = false;
		if (!asProperty.isIsRequired()) {
			maybeNull = true;
		}
		return AbstractSymbolicValue.getUnknownValue(maybeNull, false);
	}

	@Override
	public @NonNull SymbolicValue visitRealLiteralExp(@NonNull RealLiteralExp asRealLiteralExp) {
		return SymbolicValue.KNOWN_NOT_NULL_LITERALS;
	}

	@Override
	public @NonNull SymbolicValue visitResultVariable(@NonNull ResultVariable asResultVariable) {
		SymbolicValue knownValue = variable2knownValue.get(asResultVariable);
		if (knownValue == null) {
			SymbolicValue initValue = visit(PivotUtil.getOwnedInit(asResultVariable));
			if (asResultVariable.isIsRequired()) {
				initValue = initValue.setNonNullValue();
			}
			knownValue = initValue;
			variable2knownValue.put(asResultVariable, knownValue);
		}
		return knownValue;
	}

	@Override
	public @NonNull SymbolicValue visitShadowExp(@NonNull ShadowExp asShadowExp) {
		SymbolicValue partsValue = AbstractSymbolicValue.KNOWN_NOT_NULL_LITERALS;
		for (@NonNull ShadowPart asPart : PivotUtil.getOwnedParts(asShadowExp)) {
			partsValue = partsValue.union(visit(asPart));
		}
		if (partsValue.isInvalid()) {
			return AbstractSymbolicValue.KNOWN_INVALID;
		}
		else if (partsValue.isKnown()) {
			return AbstractSymbolicValue.KNOWN_NOT_NULL_LITERALS;
		}
		else {
			return AbstractSymbolicValue.getUnknownValue(false, partsValue.maybeInvalid());
		}
	}

	@Override
	public @NonNull SymbolicValue visitShadowPart(@NonNull ShadowPart asShadowPart) {
		SymbolicValue knownValue = visit(PivotUtil.getOwnedInit(asShadowPart));
		return knownValue;
	}

	@Override
	public @NonNull SymbolicValue visitStateExp(@NonNull StateExp asStateExp) {
		return SymbolicValue.KNOWN_NOT_NULL_LITERALS;
	}

	@Override
	public @NonNull SymbolicValue visitStringLiteralExp(@NonNull StringLiteralExp asStringLiteralExp) {
		return SymbolicValue.KNOWN_NOT_NULL_LITERALS;
	}

	@Override
	public @NonNull SymbolicValue visitTupleLiteralExp(@NonNull TupleLiteralExp asTupleLiteralExp) {
		SymbolicValue partsValue = AbstractSymbolicValue.KNOWN_NOT_NULL_LITERALS;
		for (@NonNull TupleLiteralPart asPart : PivotUtil.getOwnedParts(asTupleLiteralExp)) {
			partsValue = partsValue.union(visit(asPart));
		}
		if (partsValue.isInvalid()) {
			return AbstractSymbolicValue.KNOWN_INVALID;
		}
		else if (partsValue.isKnown()) {
			return AbstractSymbolicValue.KNOWN_NOT_NULL_LITERALS;
		}
		else {
			return AbstractSymbolicValue.getUnknownValue(false, partsValue.maybeInvalid());
		}
	}

	@Override
	public @NonNull SymbolicValue visitTupleLiteralPart(@NonNull TupleLiteralPart asTupleLiteralPart) {
		SymbolicValue knownValue = visit(PivotUtil.getOwnedInit(asTupleLiteralPart));
		return knownValue;
	}

	@Override
	public @NonNull SymbolicValue visitType(@NonNull Type asType) {
		return AbstractSymbolicValue.getUnknownValue(false, false);
	}

	@Override
	public @NonNull SymbolicValue visitTypeExp(@NonNull TypeExp asTypeExp) {
		SymbolicValue resultValue = visit(PivotUtil.getReferredType(asTypeExp));
		return resultValue;
	}

	@Override
	public @NonNull SymbolicValue visitUnlimitedNaturalLiteralExp(@NonNull UnlimitedNaturalLiteralExp asUnlimitedNaturalLiteralExp) {
		return SymbolicValue.KNOWN_NOT_NULL_LITERALS;
	}

	@Override
	public @NonNull SymbolicValue visitVariableExp(@NonNull VariableExp asVariableExp) {
		SymbolicValue resultValue = visit(PivotUtil.getReferredVariable(asVariableExp));
		return resultValue;
	}

	@Override
	public @NonNull SymbolicValue visiting(@NonNull Visitable visitable) {
		throw new IllegalArgumentException("Unsupported " + visitable.eClass().getName() + " for ASPostProcess pass");
	}
}
