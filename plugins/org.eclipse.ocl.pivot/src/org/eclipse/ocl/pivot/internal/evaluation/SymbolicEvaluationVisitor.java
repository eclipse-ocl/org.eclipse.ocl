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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionItem;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.CollectionLiteralPart;
import org.eclipse.ocl.pivot.CollectionRange;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.IfExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.IteratorVariable;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LetVariable;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.MapLiteralExp;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.ShadowExp;
import org.eclipse.ocl.pivot.ShadowPart;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.manager.SymbolicExecutor;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.internal.values.AbstractRefinedSymbolicValue;
import org.eclipse.ocl.pivot.internal.values.SymbolicExpressionValueImpl;
import org.eclipse.ocl.pivot.internal.values.SymbolicNavigationCallValueImpl;
import org.eclipse.ocl.pivot.internal.values.SymbolicUnknownValueImpl;
import org.eclipse.ocl.pivot.library.LibraryIteration;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerRange;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SymbolicKnownValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

import com.google.common.collect.Lists;

/**
 * A symbolic evaluation decorator for an evaluation visitor.
 *
 * @since 1.16
 */
public class SymbolicEvaluationVisitor extends EvaluationVisitorDecorator implements EvaluationVisitor.EvaluationVisitorExtension
{
	protected final @NonNull EnvironmentFactoryInternal environmentFactory;
	protected final @NonNull IdResolver idResolver;
	protected final @NonNull StandardLibraryInternal standardLibrary;

	/**
	 * Initializes the symbolic analysis that delegates to a non-symbolic evaluation visitor.
	 */
	protected SymbolicEvaluationVisitor(@NonNull EvaluationVisitor visitor) {
		super(visitor);
		this.environmentFactory = (EnvironmentFactoryInternal) visitor.getEnvironmentFactory();
		this.idResolver = environmentFactory.getIdResolver();
		this.standardLibrary = environmentFactory.getStandardLibrary();
	}

	protected @Nullable Object doNavigationCallExp(@NonNull NavigationCallExp navigationCallExp) {
		Object result = null;
		Property referredProperty = PivotUtil.getReferredProperty(navigationCallExp);
		OCLExpression source = PivotUtil.getOwnedSource(navigationCallExp);

		AbstractSymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		SymbolicValue sourceValue = evaluationEnvironment.symbolicEvaluate(source);
		@SuppressWarnings("unused")
		List<@Nullable Object> sourceAndArgumentValues = Lists.newArrayList(sourceValue);
		TypeId returnTypeId = navigationCallExp.getTypeId();
		SymbolicValue invalidProblem = evaluationEnvironment.checkNotInvalid(source, returnTypeId);
		if (invalidProblem != null) {
			return invalidProblem;
		}
		if (sourceValue.isNull()) {
			if (navigationCallExp.isIsSafe()) {
				return evaluationEnvironment.getKnownValue(null);
			}
			else {
				return evaluationEnvironment.getKnownValue(ValueUtil.INVALID_VALUE);
			}
		}
		if (!navigationCallExp.isIsSafe()) {
			SymbolicValue nullSourceProblem = evaluationEnvironment.checkNotNull(source, returnTypeId);
			if (nullSourceProblem != null) {
				return nullSourceProblem;
			}
		//	Hypothesis hypothesis = new Hypothesis.MayBeNullHypothesis(symbolicAnalysis, source, sourceValue);
		//	symbolicAnalysis.addHypothesis(source, hypothesis);
		//	return evaluationEnvironment.getMayBeInvalidValue(returnTypeId);
		}
		if (sourceValue instanceof SymbolicKnownValue) {
			result = context.internalExecuteNavigationCallExp(navigationCallExp, referredProperty, ((SymbolicKnownValue)sourceValue).getValue());
		}
		else {
			boolean isSafe = navigationCallExp.isIsSafe();
			boolean sourceMayBeInvalid = ValueUtil.mayBeInvalid(sourceValue);
			boolean sourceMayBeNull = ValueUtil.mayBeNull(sourceValue);
			boolean sourceIsMany = navigationCallExp.isIsMany();
			boolean propertyMayBeNull = !referredProperty.isIsRequired();
			boolean resultMayBeInvalid = sourceMayBeInvalid || (sourceMayBeNull && !isSafe && !sourceIsMany);
			boolean resultMayBeNull = propertyMayBeNull || (sourceMayBeNull && isSafe && !sourceIsMany);
			result = new SymbolicNavigationCallValueImpl(navigationCallExp, resultMayBeNull, resultMayBeInvalid, sourceValue);
		}
		return result;
	}

	/**
	 * @since 1.12
	 */
	protected @Nullable Object doOperationCallExp(@NonNull OperationCallExp operationCallExp) {
		Operation apparentOperation = PivotUtil.getReferredOperation(operationCallExp);
		if ("includes".equals(apparentOperation.getName())) {
			getClass();		// XXX
		}
		@SuppressWarnings("unused")
		boolean isValidating = apparentOperation.isIsValidating();
		//
		//	Resolve source value catching invalid values for validating operations.
		//
		AbstractSymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		OCLExpression source = operationCallExp.getOwnedSource();
		SymbolicValue sourceValue;
		if (source == null) {							// Static functions may have null source
			sourceValue = null;
		}
		else { //if (!isValidating) {
			sourceValue = evaluationEnvironment.symbolicEvaluate(source);
		}
		//
		@SuppressWarnings("unused")
		boolean mayBeNull = false;
		//
		//	Safe navigation of null source return null.
		//
		if (operationCallExp.isIsSafe() && (source != null)) {
			if (sourceValue == null) {
				return null;
			}
			if (sourceValue.isNull()) {
				return sourceValue;
			}
			SymbolicValue sourceProblem = evaluationEnvironment.checkNotNull(source, operationCallExp.getTypeId());
			if (sourceProblem != null) {
				return sourceProblem;
			}
			if (sourceValue.isMap()) {
				throw new InvalidValueException(PivotMessages.MapValueForbidden);
			}
			if (sourceValue.isCollection()) {
				sourceValue = AbstractRefinedSymbolicValue.createNullFreeValue(sourceValue);
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
					SymbolicValue onlyArgumentValue = evaluationEnvironment.symbolicEvaluate(arguments.get(0));
					org.eclipse.ocl.pivot.Class actualArgType = idResolver.getStaticTypeOfValue(onlyType, onlyArgumentValue);
					actualSourceType = (org.eclipse.ocl.pivot.Class)actualSourceType.getCommonType(idResolver, actualArgType);
					// FIXME direct evaluate using second argument
					actualOperation = actualSourceType.lookupActualOperation(standardLibrary, apparentOperation);
				}
			}
			actualOperation = actualSourceType.lookupActualOperation(standardLibrary, apparentOperation);
		}
		LibraryOperation implementation = (LibraryOperation)metamodelManager.getImplementation(actualOperation);
		return implementation.symbolicEvaluate(evaluationEnvironment, operationCallExp);
	}

	@Deprecated /* @deprecated use symbolicEvaluate argument */
	public @Nullable Object evaluate(@NonNull Element element) {
		AbstractSymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		SymbolicValue symbolicValue = evaluationEnvironment.symbolicEvaluate((TypedElement)element);
		if (symbolicValue instanceof SymbolicKnownValue) {
			return ((SymbolicKnownValue)symbolicValue).getValue();
		}
		else {
			return symbolicValue;
		}
	}

	@Deprecated /* @deprecated use symbolicEvaluate argument */
	@Override
	public @Nullable Object evaluate(@NonNull OCLExpression expression) {
		AbstractSymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		SymbolicValue symbolicValue = evaluationEnvironment.symbolicEvaluate(expression);
		if (symbolicValue instanceof SymbolicKnownValue) {
			return ((SymbolicKnownValue)symbolicValue).getValue();
		}
		else {
			return symbolicValue;
		}
	}

	@Override
	public @NonNull AbstractSymbolicEvaluationEnvironment getEvaluationEnvironment() {
		return (AbstractSymbolicEvaluationEnvironment)super.getEvaluationEnvironment();
	}

	/** @deprecated moved to Executor
	 * @since 1.1*/
	@Override
	@Deprecated
	public @NonNull Executor getExecutor() {
		return ((EvaluationVisitor.EvaluationVisitorExtension)delegate).getExecutor();
	}

	/** @deprecated moved to Executor */
	@Override
	@Deprecated
	public @NonNull MetamodelManager getMetamodelManager() {
		return delegate.getMetamodelManager();
	}

	public @NonNull SymbolicAnalysis getSymbolicAnalysis() {
		assert context != null;
		return (SymbolicAnalysis)context;
	}

	public @NonNull SymbolicExecutor getSymbolicExecutor() {
		assert context != null;
		return (SymbolicExecutor)context;
	}

	@Override
	public @Nullable Object visitCollectionItem(@NonNull CollectionItem item) {
		return getEvaluationEnvironment().symbolicEvaluate(PivotUtil.getOwnedItem(item));
	}

	@Override
	public @Nullable Object visitCollectionLiteralExp(@NonNull CollectionLiteralExp literalExp) {
		Object result;
		boolean isSymbolic = false;
		boolean mayBeInvalid = false;
		boolean mayBeNull = false;
		for (@NonNull CollectionLiteralPart part : PivotUtil.getOwnedParts(literalExp)) {
			Object partValue = evaluate(part);
			assert ValueUtil.isBoxed(partValue);	// Make sure Integer/Real are boxed, invalid is an exception, null is null
			if (partValue instanceof SymbolicValue) {
				isSymbolic = true;
			}
			if (ValueUtil.mayBeInvalid(partValue)) {
				mayBeInvalid = true;
			}
			if (ValueUtil.mayBeNull(partValue)) {
				mayBeNull = true;
			}
		}
		if (isSymbolic) {
			result = new SymbolicExpressionValueImpl(literalExp, false, mayBeNull || mayBeInvalid);
		}
		else {
			result = delegate.visitCollectionLiteralExp(literalExp);
		}
		return result;
	}

	@Override
	public @Nullable Object visitCollectionRange(@NonNull CollectionRange range) {
		Object result;
		OCLExpression first = PivotUtil.getOwnedFirst(range);
		OCLExpression last = PivotUtil.getOwnedLast(range);
		Object firstValue = evaluate(first);
		Object lastValue = evaluate(last);
		if ((firstValue instanceof SymbolicValue) || (lastValue instanceof SymbolicValue)) {
			boolean mayBeInvalid = ValueUtil.mayBeInvalid(firstValue) || ValueUtil.mayBeInvalid(firstValue);
			boolean mayBeNull = ValueUtil.mayBeNull(lastValue) || ValueUtil.mayBeNull(lastValue);
			TypeId typeId = range.getTypeId();
			result = new SymbolicUnknownValueImpl(typeId, false, mayBeNull || mayBeInvalid);
		}
		else {
//				result = delegate.visitCollectionRange(range);
			CollectionType type = (CollectionType) ((CollectionLiteralExp)range.eContainer()).getType();
			CollectionTypeId typeId = type.getTypeId();
			IntegerValue firstInteger = ValueUtil.asIntegerValue(firstValue);
			IntegerValue lastInteger = ValueUtil.asIntegerValue(lastValue);
			// construct a lazy integer list for the range
			IntegerRange integerRange = ValueUtil.createRange(firstInteger, lastInteger);
			if (type.isUnique()) {
				return ValueUtil.createOrderedSetRange(typeId, integerRange);
			}
			else {
				return ValueUtil.createSequenceRange(typeId, integerRange);
			}
		}
		return result;
	}

	@Override
	public @NonNull SymbolicValue visitExpressionInOCL(@NonNull ExpressionInOCL expression) {
		AbstractSymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		return evaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedBody(expression));
	}

	@Override
	public @NonNull SymbolicValue visitIfExp(@NonNull IfExp ifExp) {
		AbstractSymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		OCLExpression conditionExpression = PivotUtil.getOwnedCondition(ifExp);
		OCLExpression thenExpression = PivotUtil.getOwnedThen(ifExp);
		OCLExpression elseExpression = PivotUtil.getOwnedElse(ifExp);
		SymbolicValue conditionValue = evaluationEnvironment.symbolicEvaluate(conditionExpression);
		if (conditionValue.isTrue()) {
			evaluationEnvironment.setDead(elseExpression);
			return evaluationEnvironment.symbolicEvaluate(thenExpression);
		}
		else if (conditionValue.isFalse()) {
			evaluationEnvironment.setDead(thenExpression);
			return evaluationEnvironment.symbolicEvaluate(elseExpression);
		}
		else {
			boolean mayBeInvalid = conditionValue.mayBeInvalid();
			boolean mayBeNull = conditionValue.mayBeNull();
			SymbolicValue thenValue = evaluationEnvironment.symbolicEvaluate(thenExpression);
			if (thenValue.mayBeInvalid()) {
				mayBeInvalid = true;
			}
			if (thenValue.mayBeNull()) {
				mayBeNull = true;
			}
			SymbolicValue elseValue = evaluationEnvironment.symbolicEvaluate(elseExpression);
			if (elseValue.mayBeInvalid()) {
				mayBeInvalid = true;
			}
			if (elseValue.mayBeNull()) {
				mayBeNull = true;
			}
			return new SymbolicExpressionValueImpl(ifExp, mayBeNull, mayBeInvalid);
		}
	}

	@Override
	public @NonNull SymbolicValue visitIteratorVariable(@NonNull IteratorVariable iteratorVariable) {
		AbstractSymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		OCLExpression initExp = iteratorVariable.getOwnedInit();
		if (initExp != null) {
			return evaluationEnvironment.symbolicEvaluate(initExp);
		}
		else {
		//	getEvaluationEnvironment().add(parameterVariable, parameterValue);
			CSEElement cseElement = getSymbolicAnalysis().getCSEElement(iteratorVariable);
			SymbolicUnknownValueImpl symbolicValue = new SymbolicUnknownValueImpl(iteratorVariable.getTypeId(), !iteratorVariable.isIsRequired(), false);
			return evaluationEnvironment.traceSymbolicValue(cseElement, symbolicValue);
		}
	}

	@Override
	public @NonNull SymbolicValue visitLetExp(@NonNull LetExp letExp) {
		return getEvaluationEnvironment().symbolicEvaluate(PivotUtil.getOwnedIn(letExp));
	}

	@Override
	public @NonNull SymbolicValue visitLetVariable(@NonNull LetVariable letVariable) {
		return getEvaluationEnvironment().symbolicEvaluate(PivotUtil.getOwnedInit(letVariable));
	}

	@Override
	public Object visitLoopExp(@NonNull LoopExp loopExp) {
		AbstractSymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		Iteration iteration = PivotUtil.getReferredIteration(loopExp);
		LibraryIteration implementation = (LibraryIteration)environmentFactory.getMetamodelManager().getImplementation(iteration);
		return implementation.symbolicEvaluate(evaluationEnvironment, loopExp);
	}

	@Override
	public @Nullable Object visitMapLiteralExp(@NonNull MapLiteralExp literalExp) {
		boolean isSymbolic = false;
		boolean mayBeInvalid = false;
		boolean mayBeNull = false;
		for (@NonNull MapLiteralPart part : PivotUtil.getOwnedParts(literalExp)) {
			Object keyValue = evaluate(PivotUtil.getOwnedKey(part));
			Object valueValue = evaluate(PivotUtil.getOwnedValue(part));
			assert ValueUtil.isBoxed(keyValue);	// Make sure Integer/Real are boxed, invalid is an exception, null is null
			assert ValueUtil.isBoxed(valueValue);	// Make sure Integer/Real are boxed, invalid is an exception, null is null
			if ((keyValue instanceof SymbolicValue) || (valueValue instanceof SymbolicValue)) {
				isSymbolic = true;
			}
			if (ValueUtil.mayBeInvalid(keyValue) || ValueUtil.mayBeInvalid(valueValue)) {
				mayBeInvalid = true;
			}
			if (ValueUtil.mayBeNull(keyValue) || ValueUtil.mayBeNull(valueValue)) {
				mayBeNull = true;
			}
		}
		if (isSymbolic) {
			return new SymbolicExpressionValueImpl(literalExp, false, mayBeNull || mayBeInvalid);
		}
		else {
			return delegate.visitMapLiteralExp(literalExp);
		}
	}

	@Override
	public @Nullable Object visitOperationCallExp(@NonNull OperationCallExp callExp) {
		return doOperationCallExp(callExp);
	}

	@Override
	public @Nullable Object visitOppositePropertyCallExp(@NonNull OppositePropertyCallExp oppositePropertyCallExp) {
		return doNavigationCallExp(oppositePropertyCallExp);
	}

	@Override
	public @NonNull SymbolicValue visitParameterVariable(@NonNull ParameterVariable parameterVariable) {
		AbstractSymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		SymbolicValue symbolicValue = evaluationEnvironment.basicGetSymbolicValue(parameterVariable);
		if (symbolicValue == null) {
			throw new IllegalStateException(StringUtil.bind("Unbound parameter variable ''{0}''", parameterVariable));
		}
		return symbolicValue;
	}

	@Override
	public @Nullable Object visitPropertyCallExp(@NonNull PropertyCallExp propertyCallExp) {
		return doNavigationCallExp(propertyCallExp);
	}

	@Override
	public @Nullable Object visitShadowExp(@NonNull ShadowExp shadowExp) {
		SymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		boolean isSymbolic = false;
		boolean mayBeInvalid = false;
		boolean mayBeNull = false;
		for (@NonNull ShadowPart part : PivotUtil.getOwnedParts(shadowExp)) {
			SymbolicValue partValue = evaluationEnvironment.symbolicEvaluate(part);
			if (!partValue.isKnown()) {
				isSymbolic = true;
			}
			if (partValue.mayBeInvalid()) {
				mayBeInvalid = true;
			}
			if (partValue.mayBeNull()) {
				mayBeNull = true;
			}
		}
		if (isSymbolic) {
			return new SymbolicExpressionValueImpl(shadowExp, false, mayBeNull || mayBeInvalid);
		}
		else {
			return delegate.visitShadowExp(shadowExp);
		}
	}

	@Override
	public Object visitShadowPart(@NonNull ShadowPart shadowPart) {
		SymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		boolean isSymbolic = false;
		boolean mayBeInvalid = false;
		boolean mayBeNull = false;
		OCLExpression ownedInit = PivotUtil.getOwnedInit(shadowPart);		// XXX
		Property partProperty = shadowPart.getReferredProperty();
		boolean isRequired = partProperty.isIsRequired();
		boolean isMany = partProperty.isIsMany();
		SymbolicValue initValue = evaluationEnvironment.symbolicEvaluate(ownedInit);
		if (!initValue.isKnown()) {
			isSymbolic = true;
		}
		if (initValue.mayBeInvalid()) {
			mayBeInvalid = true;
		}
		if (initValue.mayBeNull()) {
			mayBeNull = true;
		}
		if (isSymbolic) {
			return new SymbolicExpressionValueImpl(ownedInit, false, mayBeNull || mayBeInvalid);
		}
		else {
			return new SymbolicExpressionValueImpl(ownedInit, false, mayBeNull || mayBeInvalid);
//			return delegate.visitShadowPart(shadowPart);
		}
	}

	@Override
	public @Nullable Object visitTupleLiteralExp(@NonNull TupleLiteralExp literalExp) {
		SymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		boolean isSymbolic = false;
		boolean mayBeInvalid = false;
		boolean mayBeNull = false;
		for (@NonNull TupleLiteralPart part : PivotUtil.getOwnedParts(literalExp)) {
			SymbolicValue partValue = evaluationEnvironment.symbolicEvaluate(part);
			if (!partValue.isKnown()) {
				isSymbolic = true;
			}
			if (partValue.mayBeInvalid()) {
				mayBeInvalid = true;
			}
			if (partValue.mayBeNull()) {
				mayBeNull = true;
			}
		}
		if (isSymbolic) {
			return new SymbolicExpressionValueImpl(literalExp, false, mayBeNull || mayBeInvalid);
		}
		else {
			return delegate.visitTupleLiteralExp(literalExp);
		}
	}

	@Override
	public Object visitTupleLiteralPart(@NonNull TupleLiteralPart part) {
		SymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		boolean isSymbolic = false;
		boolean mayBeInvalid = false;
		boolean mayBeNull = false;
		SymbolicValue partValue = evaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedInit(part));
		if (!partValue.isKnown()) {
			isSymbolic = true;
		}
		if (partValue.mayBeInvalid()) {
			mayBeInvalid = true;
		}
		if (partValue.mayBeNull()) {
			mayBeNull = true;
		}
		if (isSymbolic) {
			return new SymbolicExpressionValueImpl(part, false, mayBeNull || mayBeInvalid);
		}
		else {
			return delegate.visitTupleLiteralPart(part);
		}
	}

	@Override
	public Object visitVariableDeclaration(@NonNull VariableDeclaration object) {
		// TODO Auto-generated method stub
	//	return super.visitVariableDeclaration(object);
		return visiting(object);
	}
}
