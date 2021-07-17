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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.BooleanLiteralExp;
import org.eclipse.ocl.pivot.CollectionItem;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.CollectionLiteralPart;
import org.eclipse.ocl.pivot.CollectionRange;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.EnumLiteralExp;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.IntegerLiteralExp;
import org.eclipse.ocl.pivot.InvalidLiteralExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.IteratorVariable;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LetVariable;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.MapLiteralExp;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.NullLiteralExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.RealLiteralExp;
import org.eclipse.ocl.pivot.ResultVariable;
import org.eclipse.ocl.pivot.ShadowExp;
import org.eclipse.ocl.pivot.ShadowPart;
import org.eclipse.ocl.pivot.StringLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.symbolic.AbstractLeafSymbolicValue.SymbolicNavigationCallValue;
import org.eclipse.ocl.pivot.internal.symbolic.AbstractSymbolicRefinedValue;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryIteration;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerRange;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * A symbolic evaluation decorator for an evaluation visitor.
 *
 * @since 1.16
 */
public class SymbolicEvaluationVisitor extends AbstractExtendingVisitor<@NonNull SymbolicValue, @NonNull SymbolicAnalysis>
{
	protected final @NonNull EvaluationVisitor evaluationVisitor;
	protected final @NonNull EnvironmentFactoryInternal environmentFactory;
	protected final @NonNull IdResolver idResolver;
	protected final @NonNull StandardLibraryInternal standardLibrary;
	protected final @NonNull SymbolicEvaluationEnvironment symbolicEvaluationEnvironment;

	/**
	 * Initializes the symbolic analysis that delegates to a non-symbolic evaluation visitor.
	 * @param symbolicEvaluationEnvironment
	 */
	public SymbolicEvaluationVisitor(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull EvaluationVisitor evaluationVisitor, @NonNull SymbolicEvaluationEnvironment symbolicEvaluationEnvironment) {
		super(symbolicAnalysis);
		this.evaluationVisitor = evaluationVisitor;
		this.environmentFactory = symbolicAnalysis.getEnvironmentFactory();
		this.idResolver = environmentFactory.getIdResolver();
		this.standardLibrary = environmentFactory.getStandardLibrary();
		this.symbolicEvaluationEnvironment = symbolicEvaluationEnvironment;
	}

	protected @NonNull SymbolicValue doNavigationCallExp(@NonNull NavigationCallExp navigationCallExp) {
		Property referredProperty = PivotUtil.getReferredProperty(navigationCallExp);
		OCLExpression source = PivotUtil.getOwnedSource(navigationCallExp);
		SymbolicValue sourceValue = symbolicEvaluationEnvironment.symbolicEvaluate(source);
		TypeId returnTypeId = navigationCallExp.getTypeId();
		SymbolicValue invalidProblem = symbolicEvaluationEnvironment.checkNotInvalid(source, returnTypeId);
		if (invalidProblem != null) {
			return invalidProblem;
		}
		if (sourceValue.isNull()) {
			if (navigationCallExp.isIsSafe()) {
				return context.getKnownValue(null);
			}
			else {
				return context.getKnownValue(ValueUtil.INVALID_VALUE);
			}
		}
		if (!navigationCallExp.isIsSafe()) {
			SymbolicValue nullSourceProblem = symbolicEvaluationEnvironment.checkNotNull(source, returnTypeId);
			if (nullSourceProblem != null) {
				return nullSourceProblem;
			}
		}
		if (sourceValue.isKnown()) {
			Object resultValue = context.getExecutor().internalExecuteNavigationCallExp(navigationCallExp, referredProperty, sourceValue.getKnownValue());
			return context.getKnownValue(resultValue);
		}
		else {
			boolean isSafe = navigationCallExp.isIsSafe();
			boolean sourceMayBeInvalid = sourceValue.mayBeInvalid();
			boolean sourceMayBeNull = sourceValue.mayBeNull();
			boolean sourceIsMany = navigationCallExp.isIsMany();
			boolean propertyMayBeNull = !referredProperty.isIsRequired();
			boolean resultMayBeInvalid = sourceMayBeInvalid || (sourceMayBeNull && !isSafe && !sourceIsMany);
			boolean resultMayBeNull = propertyMayBeNull || (sourceMayBeNull && isSafe && !sourceIsMany);
			return new SymbolicNavigationCallValue(context.createVariableName(), navigationCallExp, resultMayBeNull, resultMayBeInvalid, sourceValue);
		}
	}

	/**
	 * @since 1.12
	 */
	protected @NonNull SymbolicValue doOperationCallExp(@NonNull OperationCallExp operationCallExp) {
		Operation apparentOperation = PivotUtil.getReferredOperation(operationCallExp);
		if ("first".equals(apparentOperation.getName())) {
			getClass();		// XXX
		}
		@SuppressWarnings("unused")
		boolean isValidating = apparentOperation.isIsValidating();
		//
		//	Resolve source value catching invalid values for validating operations.
		//
		OCLExpression source = operationCallExp.getOwnedSource();
		SymbolicValue sourceValue;
		if (source == null) {							// Static functions may have null source
			sourceValue = null;
		}
		else { //if (!isValidating) {
			sourceValue = symbolicEvaluationEnvironment.symbolicEvaluate(source);
		}
		//
		@SuppressWarnings("unused")
		boolean mayBeNull = false;
		//
		//	Safe navigation of null source return null.
		//
		if (operationCallExp.isIsSafe() && (source != null)) {
			if (sourceValue == null) {
				return context.getKnownValue(null);
			}
			if (sourceValue.isNull()) {
				return sourceValue;
			}
			SymbolicValue sourceProblem = symbolicEvaluationEnvironment.checkNotNull(source, operationCallExp.getTypeId());
			if (sourceProblem != null) {
				return sourceProblem;
			}
			if (sourceValue.isMap()) {
				throw new InvalidValueException(PivotMessages.MapValueForbidden);
			}
			if (sourceValue.isCollection()) {
				sourceValue = AbstractSymbolicRefinedValue.createNullFreeValue(sourceValue);
			}
		}
		PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		Operation actualOperation;
		if (apparentOperation.isIsStatic()) {
			actualOperation = apparentOperation;
		}
		else {
			assert source != null;
			org.eclipse.ocl.pivot.Class actualSourceType = idResolver.getStaticTypeOfValue(source.getType(), sourceValue);
			List<@NonNull Parameter> ownedParameters = PivotUtilInternal.getOwnedParametersList(apparentOperation);
			if (ownedParameters.size() == 1) {
				Parameter onlyParameter = ownedParameters.get(0);
				Type onlyType = onlyParameter.getType();
				if (onlyType == standardLibrary.getOclSelfType()) {
					List<@NonNull OCLExpression> arguments = ClassUtil.nullFree(operationCallExp.getOwnedArguments());
					SymbolicValue onlyArgumentValue = symbolicEvaluationEnvironment.symbolicEvaluate(arguments.get(0));
					org.eclipse.ocl.pivot.Class actualArgType = idResolver.getStaticTypeOfValue(onlyType, onlyArgumentValue);
					actualSourceType = (org.eclipse.ocl.pivot.Class)actualSourceType.getCommonType(idResolver, actualArgType);
					// FIXME direct evaluate using second argument
					actualOperation = actualSourceType.lookupActualOperation(standardLibrary, apparentOperation);
				}
			}
			actualOperation = actualSourceType.lookupActualOperation(standardLibrary, apparentOperation);
		}
		LibraryOperation implementation = (LibraryOperation)metamodelManager.getImplementation(actualOperation);
		return implementation.symbolicEvaluate(symbolicEvaluationEnvironment, operationCallExp);
	}

	public @NonNull SymbolicValue symbolicEvaluate(@NonNull Element element) {
		try {
			SymbolicValue resultValue = element.accept(this);
			return resultValue;
		}
		catch (InvalidValueException e) {
			Object boxedValue = idResolver.boxedValueOf(e);
			return context.getKnownValue(boxedValue);
		}
	}

	@Override
	public @NonNull SymbolicValue visitBooleanLiteralExp(@NonNull BooleanLiteralExp booleanLiteralExp) {
		Object resultValue = evaluationVisitor.visitBooleanLiteralExp(booleanLiteralExp);
		return context.getKnownValue(resultValue);
	}

	@Override
	public @NonNull SymbolicValue visitCollectionItem(@NonNull CollectionItem item) {
		return symbolicEvaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedItem(item));
	}

	@Override
	public @NonNull SymbolicValue visitCollectionLiteralExp(@NonNull CollectionLiteralExp literalExp) {
		final boolean isNullFree = ((CollectionType)literalExp.getType()).isIsNullFree();
		boolean isKnown = true;
		boolean mayBeInvalid = false;
		boolean mayBeNull = false;
		for (@NonNull CollectionLiteralPart part : PivotUtil.getOwnedParts(literalExp)) {
			SymbolicValue partValue = symbolicEvaluate(part);
			if (!partValue.isKnown()) {
				isKnown = false;
			}
			if (partValue.mayBeInvalid()) {
				mayBeInvalid = true;
			}
			if (isNullFree && partValue.mayBeNull()) {
				mayBeNull = true;
			}
		}
		if (isKnown) {
			Object resultValue = evaluationVisitor.visitCollectionLiteralExp(literalExp);
			return context.getKnownValue(resultValue);
		}
		else {
			return context.createUnknownValue(literalExp, false, mayBeInvalid || mayBeNull);
		}
	}

	@Override
	public @NonNull SymbolicValue visitCollectionRange(@NonNull CollectionRange range) {
		OCLExpression first = PivotUtil.getOwnedFirst(range);
		OCLExpression last = PivotUtil.getOwnedLast(range);
		SymbolicValue firstValue = symbolicEvaluate(first);
		SymbolicValue lastValue = symbolicEvaluate(last);
		if (!firstValue.isKnown() || !lastValue.isKnown()) {
			boolean mayBeInvalid = firstValue.mayBeInvalid() || firstValue.mayBeInvalid();
			boolean mayBeNull = lastValue.mayBeNull() || lastValue.mayBeNull();
			return context.createUnknownValue(range, false, mayBeNull || mayBeInvalid);
		}
		Object resultValue;
		CollectionType type = (CollectionType) ((CollectionLiteralExp)range.eContainer()).getType();
		CollectionTypeId typeId = type.getTypeId();
		IntegerValue firstInteger = ValueUtil.asIntegerValue(firstValue);
		IntegerValue lastInteger = ValueUtil.asIntegerValue(lastValue);
		// construct a lazy integer list for the range
		IntegerRange integerRange = ValueUtil.createRange(firstInteger, lastInteger);
		if (type.isUnique()) {
			resultValue = ValueUtil.createOrderedSetRange(typeId, integerRange);
		}
		else {
			resultValue = ValueUtil.createSequenceRange(typeId, integerRange);
		}
		return context.getKnownValue(resultValue);
	}

	@Override
	public @NonNull SymbolicValue visitEnumLiteralExp(@NonNull EnumLiteralExp enumLiteralExp) {
		Object resultValue = evaluationVisitor.visitEnumLiteralExp(enumLiteralExp);
		return context.getKnownValue(resultValue);
	}

	@Override
	public @NonNull SymbolicValue visitExpressionInOCL(@NonNull ExpressionInOCL expression) {
		return symbolicEvaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedBody(expression));
	}

	@Override
	public @NonNull SymbolicValue visitIfExp(@NonNull IfExp ifExp) {
		OCLExpression conditionExpression = PivotUtil.getOwnedCondition(ifExp);
		OCLExpression thenExpression = PivotUtil.getOwnedThen(ifExp);
		OCLExpression elseExpression = PivotUtil.getOwnedElse(ifExp);
		SymbolicValue conditionValue = symbolicEvaluationEnvironment.symbolicEvaluate(conditionExpression);
		if (conditionValue.isTrue()) {
			symbolicEvaluationEnvironment.setDead(elseExpression);
			return symbolicEvaluationEnvironment.symbolicEvaluate(thenExpression);
		}
		else if (conditionValue.isFalse()) {
			symbolicEvaluationEnvironment.setDead(thenExpression);
			return symbolicEvaluationEnvironment.symbolicEvaluate(elseExpression);
		}
		else if (conditionValue.isInvalid() || conditionValue.isNull()) {
			symbolicEvaluationEnvironment.setDead(thenExpression);
			symbolicEvaluationEnvironment.setDead(elseExpression);
			return context.getKnownValue(ValueUtil.INVALID_VALUE);
		}
		else {
			boolean mayBeInvalid = conditionValue.mayBeInvalid();
			boolean mayBeNull = conditionValue.mayBeNull();
			SymbolicValue thenValue = symbolicEvaluationEnvironment.symbolicEvaluate(thenExpression);
			if (thenValue.mayBeInvalid()) {
				mayBeInvalid = true;
			}
			if (thenValue.mayBeNull()) {
				mayBeNull = true;
			}
			SymbolicValue elseValue = symbolicEvaluationEnvironment.symbolicEvaluate(elseExpression);
			if (elseValue.mayBeInvalid()) {
				mayBeInvalid = true;
			}
			if (elseValue.mayBeNull()) {
				mayBeNull = true;
			}
			return context.createUnknownValue(ifExp, mayBeNull, mayBeInvalid);
		}
	}

	@Override
	public @NonNull SymbolicValue visitIntegerLiteralExp(@NonNull IntegerLiteralExp integerLiteralExp) {
		Object resultValue = evaluationVisitor.visitIntegerLiteralExp(integerLiteralExp);
		return context.getKnownValue(resultValue);
	}

	@Override
	public @NonNull SymbolicValue visitInvalidLiteralExp(@NonNull InvalidLiteralExp invalidLiteralExp) {
		Object resultValue = evaluationVisitor.visitInvalidLiteralExp(invalidLiteralExp);
		return context.getKnownValue(resultValue);
	}

	@Override
	public @NonNull SymbolicValue visitIteratorVariable(@NonNull IteratorVariable iteratorVariable) {
		OCLExpression initExp = iteratorVariable.getOwnedInit();
		if (initExp != null) {
			return symbolicEvaluationEnvironment.symbolicEvaluate(initExp);
		}
		else {
			SymbolicValue symbolicValue = context.createUnknownValue(iteratorVariable, !iteratorVariable.isIsRequired(), false);
			return symbolicEvaluationEnvironment.setSymbolicValue(iteratorVariable, symbolicValue);
		}
	}

	@Override
	public @NonNull SymbolicValue visitLetExp(@NonNull LetExp letExp) {
		return symbolicEvaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedIn(letExp));
	}

	@Override
	public @NonNull SymbolicValue visitLetVariable(@NonNull LetVariable letVariable) {
		return symbolicEvaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedInit(letVariable));
	}

	@Override
	public @NonNull SymbolicValue visitLoopExp(@NonNull LoopExp loopExp) {
		Iteration iteration = PivotUtil.getReferredIteration(loopExp);
		LibraryIteration implementation = (LibraryIteration)environmentFactory.getMetamodelManager().getImplementation(iteration);
		return implementation.symbolicEvaluate(symbolicEvaluationEnvironment, loopExp);
	}

	@Override
	public @NonNull SymbolicValue visitMapLiteralExp(@NonNull MapLiteralExp literalExp) {
		final boolean isKeysAreNullFree = ((MapType)literalExp.getType()).isKeysAreNullFree();
		final boolean isValuesAreNullFree = ((MapType)literalExp.getType()).isValuesAreNullFree();
		boolean isKnown = true;
		boolean mayBeInvalid = false;
		boolean mayBeNull = false;
		for (@NonNull MapLiteralPart part : PivotUtil.getOwnedParts(literalExp)) {
			SymbolicValue keyValue = symbolicEvaluate(PivotUtil.getOwnedKey(part));
			SymbolicValue valueValue = symbolicEvaluate(PivotUtil.getOwnedValue(part));
			if (!keyValue.isKnown() || !valueValue.isKnown()) {
				isKnown = false;
			}
			if (keyValue.mayBeInvalid() || valueValue.mayBeInvalid()) {
				mayBeInvalid = true;
			}
			if (isKeysAreNullFree && keyValue.mayBeNull()) {
				mayBeNull = true;
			}
			if (isValuesAreNullFree && valueValue.mayBeNull()) {
				mayBeNull = true;
			}
		}
		if (isKnown) {
			Object resultValue = evaluationVisitor.visitMapLiteralExp(literalExp);
			return context.getKnownValue(resultValue);
		}
		else {
			return context.createUnknownValue(literalExp, false, mayBeNull || mayBeInvalid);
		}
	}

	@Override
	public @NonNull SymbolicValue visitNullLiteralExp(@NonNull NullLiteralExp nullLiteralExp) {
		Object resultValue = evaluationVisitor.visitNullLiteralExp(nullLiteralExp);
		return context.getKnownValue(resultValue);
	}

	@Override
	public @NonNull SymbolicValue visitOperationCallExp(@NonNull OperationCallExp callExp) {
		return doOperationCallExp(callExp);
	}

	@Override
	public @NonNull SymbolicValue visitOppositePropertyCallExp(@NonNull OppositePropertyCallExp oppositePropertyCallExp) {
		return doNavigationCallExp(oppositePropertyCallExp);
	}

	@Override
	public @NonNull SymbolicValue visitParameterVariable(@NonNull ParameterVariable parameterVariable) {
		SymbolicValue symbolicValue = symbolicEvaluationEnvironment.basicGetSymbolicValue(parameterVariable);
		if (symbolicValue == null) {
			throw new IllegalStateException(StringUtil.bind("Unbound parameter variable ''{0}''", parameterVariable));
		}
		return symbolicValue;
	}

	@Override
	public @NonNull SymbolicValue visitPropertyCallExp(@NonNull PropertyCallExp propertyCallExp) {
		return doNavigationCallExp(propertyCallExp);
	}

	@Override
	public @NonNull SymbolicValue visitRealLiteralExp(@NonNull RealLiteralExp realLiteralExp) {
		Object resultValue = evaluationVisitor.visitRealLiteralExp(realLiteralExp);
		return context.getKnownValue(resultValue);
	}

	@Override
	public @NonNull SymbolicValue visitResultVariable(@NonNull ResultVariable resultVariable) {
		OCLExpression initExp = resultVariable.getOwnedInit();
		if (initExp != null) {
			return symbolicEvaluationEnvironment.symbolicEvaluate(initExp);
		}
		else {
			SymbolicValue symbolicValue = context.createUnknownValue(resultVariable, !resultVariable.isIsRequired(), false);
			return symbolicEvaluationEnvironment.setSymbolicValue(resultVariable, symbolicValue);
		}
	}

	@Override
	public @NonNull SymbolicValue visitShadowExp(@NonNull ShadowExp shadowExp) {
		boolean isKnown = true;
		boolean mayBeInvalid = false;
		boolean mayBeNull = false;
		for (@NonNull ShadowPart part : PivotUtil.getOwnedParts(shadowExp)) {
			SymbolicValue partValue = symbolicEvaluationEnvironment.symbolicEvaluate(part);
			if (!partValue.isKnown()) {
				isKnown = false;
			}
			if (partValue.mayBeInvalid()) {
				mayBeInvalid = true;
			}
			if (partValue.mayBeNull()) {
				mayBeNull = true;
			}
		}
		if (isKnown) {
			Object resultValue = evaluationVisitor.visitShadowExp(shadowExp);
			return context.getKnownValue(resultValue);
		}
		else {
			return context.createUnknownValue(shadowExp, false, mayBeNull || mayBeInvalid);
		}
	}

	@Override
	public @NonNull SymbolicValue visitShadowPart(@NonNull ShadowPart shadowPart) {
	//	boolean isKnown = true;
		boolean mayBeInvalid = false;
		boolean mayBeNull = false;
		OCLExpression ownedInit = PivotUtil.getOwnedInit(shadowPart);
		SymbolicValue initValue = symbolicEvaluationEnvironment.symbolicEvaluate(ownedInit);
	//	if (!initValue.isKnown()) {
	//		isKnown = false;
	//	}
		if (initValue.mayBeInvalid()) {
			mayBeInvalid = true;
		}
		if (initValue.mayBeNull()) {
			mayBeNull = true;
		}
	//	if (isKnown) {
	//		Object resultValue = evaluationVisitor.visitShadowPart(shadowPart);		// -- not supported
	//		return context.getKnownValue(resultValue);
	//	}
	//	else {
			return context.createUnknownValue(ownedInit, false, /*mayBeNull ||*/ mayBeInvalid);
	//	}
	}

	@Override
	public @NonNull SymbolicValue visitStringLiteralExp(@NonNull StringLiteralExp stringLiteralExp) {
		Object resultValue = evaluationVisitor.visitStringLiteralExp(stringLiteralExp);
		return context.getKnownValue(resultValue);
	}

	@Override
	public @NonNull SymbolicValue visitTupleLiteralExp(@NonNull TupleLiteralExp literalExp) {
		boolean isKnown = true;
		boolean mayBeInvalid = false;
		boolean mayBeNull = false;
		for (@NonNull TupleLiteralPart part : PivotUtil.getOwnedParts(literalExp)) {
			SymbolicValue partValue = symbolicEvaluationEnvironment.symbolicEvaluate(part);
			if (!partValue.isKnown()) {
				isKnown = false;
			}
			if (partValue.mayBeInvalid()) {
				mayBeInvalid = true;
			}
			if (partValue.mayBeNull()) {
				mayBeNull = true;
			}
		}
		if (isKnown) {
			Object resultValue = evaluationVisitor.visitTupleLiteralExp(literalExp);
			return context.getKnownValue(resultValue);
		}
		else {
			return context.createUnknownValue(literalExp, false, mayBeNull || mayBeInvalid);
		}
	}

	@Override
	public @NonNull SymbolicValue visitTupleLiteralPart(@NonNull TupleLiteralPart part) {
		boolean isKnown = true;
		boolean mayBeInvalid = false;
		boolean mayBeNull = false;
		SymbolicValue partValue = symbolicEvaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedInit(part));
		if (!partValue.isKnown()) {
			isKnown = false;
		}
		if (partValue.mayBeInvalid()) {
			mayBeInvalid = true;
		}
		if (partValue.mayBeNull()) {
			mayBeNull = true;
		}
		if (isKnown) {
			Object resultValue = evaluationVisitor.visitTupleLiteralPart(part);
			return context.getKnownValue(resultValue);
		}
		else {
			return context.createUnknownValue(part, false, mayBeNull || mayBeInvalid);
		}
	}

	@Override
	public @NonNull SymbolicValue visitTypeExp(@NonNull TypeExp typeExp) {
		Object resultValue = evaluationVisitor.visitTypeExp(typeExp);
		return context.getKnownValue(resultValue);
	}

	@Override
	public @NonNull SymbolicValue visitUnlimitedNaturalLiteralExp(@NonNull UnlimitedNaturalLiteralExp unlimitedNaturalLiteralExp) {
		Object resultValue = evaluationVisitor.visitUnlimitedNaturalLiteralExp(unlimitedNaturalLiteralExp);
		return context.getKnownValue(resultValue);
	}

	@Override
	public @NonNull SymbolicValue visitVariable(@NonNull Variable iteratorVariable) {		// Used for CoIterator
		OCLExpression initExp = iteratorVariable.getOwnedInit();
		if (initExp != null) {
			return symbolicEvaluationEnvironment.symbolicEvaluate(initExp);
		}
		else {
			CSEElement cseElement = context.getCSEElement(iteratorVariable);
			SymbolicValue symbolicValue = context.createUnknownValue(iteratorVariable, !iteratorVariable.isIsRequired(), false);
			return symbolicEvaluationEnvironment.setSymbolicValue(iteratorVariable, symbolicValue);
		}
	}

	@Override
	public @NonNull SymbolicValue visitVariableExp(@NonNull VariableExp variableExp) {
		final VariableDeclaration referredVariable = PivotUtil.getReferredVariable(variableExp);
		return symbolicEvaluationEnvironment.symbolicEvaluate(referredVariable);
	}

	@Override
	public @NonNull SymbolicValue visiting(@NonNull Visitable visitable) {
//		return visitable.accept(evaluationVisitor);
		throw new IllegalArgumentException("Unsupported " + visitable.eClass().getName() + " for SymbolicEvaluationVisitor");
	}
}
