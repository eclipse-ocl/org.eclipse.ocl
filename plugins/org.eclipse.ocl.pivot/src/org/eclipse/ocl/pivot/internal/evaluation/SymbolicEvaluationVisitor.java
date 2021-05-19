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
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.MapLiteralExp;
import org.eclipse.ocl.pivot.MapLiteralPart;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.PropertyCallExp;
import org.eclipse.ocl.pivot.ShadowExp;
import org.eclipse.ocl.pivot.ShadowPart;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.evaluation.EvaluationHaltedException;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.manager.SymbolicExecutor;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.internal.values.SymbolicCollectionValueImpl;
import org.eclipse.ocl.pivot.internal.values.SymbolicExpressionValueImpl;
import org.eclipse.ocl.pivot.internal.values.SymbolicNavigationCallValueImpl;
import org.eclipse.ocl.pivot.internal.values.SymbolicUnknownValueImpl;
import org.eclipse.ocl.pivot.internal.values.SymbolicVariableValueImpl;
import org.eclipse.ocl.pivot.labels.ILabelGenerator;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IntegerRange;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.MapValue;
import org.eclipse.ocl.pivot.values.SymbolicKnownValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

import com.google.common.collect.Lists;

/**
 * A symbolic evaluation decorator for an evaluation visitor.
 *
 * @since 1.15
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
	//	SymbolicAnalysis.evaluateHypothesis(evaluationEnvironment, source, )



		SymbolicValue sourceValue = evaluationEnvironment.symbolicEvaluate(source);
		@SuppressWarnings("unused")
		List<@Nullable Object> sourceAndArgumentValues = Lists.newArrayList(sourceValue);
	/*	Iterable<@Nullable Object> symbolicConstraints = evaluationEnvironment.getSymbolicConstraints(referredProperty, sourceAndArgumentValues);
		if (symbolicConstraints != null) {
			boolean mayBeInvalid = true;
			boolean mayBeNull = true;
			for (@Nullable Object symbolicConstraint : symbolicConstraints) {
				if (!ValueUtil.mayBeInvalid(symbolicConstraint)) {
					mayBeInvalid = false;
				}
				if (!ValueUtil.mayBeNull(symbolicConstraint)) {
					mayBeNull = false;
				}
			}
			result = new SymbolicExpressionValueImpl(navigationCallExp, mayBeNull, mayBeInvalid);
		}
		else */ if (sourceValue instanceof SymbolicKnownValue) {
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
		if (isCanceled()) {
			throw new EvaluationHaltedException("Canceled");
		}
		Operation apparentOperation = PivotUtil.getReferredOperation(operationCallExp);
		if ("size".equals(apparentOperation.getName())) {
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
	//	else {
	//		try {
	//			sourceValue = symbolicEvaluate(source);
	//			assert ValueUtil.isBoxed(sourceValue);	// Make sure Integer/Real are boxed, invalid is an exception, null is null
	//		}
	//		catch (EvaluationHaltedException e) {
	//			throw e;
	//		}
	//		catch (InvalidValueException e) {
	//			sourceValue = e;	// FIXME ?? propagate part of environment
	//		}
	//	}
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
			if (evaluationEnvironment.mayBeNull(source)) { //, sourceValue)) {
				mayBeNull = true;
			}
			if ((sourceValue instanceof MapValue) || (source.getTypeId() instanceof MapTypeId)) {
				throw new InvalidValueException(PivotMessages.MapValueForbidden);
			}
			if ((sourceValue instanceof CollectionValue) || (source.getTypeId() instanceof MapTypeId)) {
			//	sourceValue = ((CollectionValue)sourceValue).excluding(null);		// XXX
				sourceValue = sourceValue.setIsNullFree();
			}
			else if (source.getTypeId() instanceof CollectionTypeId) {
				sourceValue = sourceValue.setIsNullFree();
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
				//	if (onlyArgumentValue instanceof SymbolicKnownValue) {
				//		onlyArgumentValue = ((SymbolicKnownValue)onlyArgumentValue).getValue();
				//	}
					org.eclipse.ocl.pivot.Class actualArgType = idResolver.getStaticTypeOfValue(onlyType, onlyArgumentValue);
					actualSourceType = (org.eclipse.ocl.pivot.Class)actualSourceType.getCommonType(idResolver, actualArgType);
					// FIXME direct evaluate using second argument
					actualOperation = actualSourceType.lookupActualOperation(standardLibrary, apparentOperation);
				//	implementation = (LibraryBinaryOperation)metamodelManager.getImplementation(actualOperation);
				/*	try {
						boolean isSymbolic = (sourceValue instanceof SymbolicValue) || (onlyArgumentValue instanceof SymbolicValue);
						if (!isSymbolic) {
							TypeId returnTypeId = operationCallExp.getTypeId();
							Object result = implementation.evaluate(context, returnTypeId, sourceValue, onlyArgumentValue);
							assert !(result instanceof NullValue);// || (result instanceof SymbolicValue);
							return result;
						}
						else {
							List<@Nullable Object> boxedSourceAndArgumentValues = Lists.newArrayList(sourceValue, onlyArgumentValue);
							Iterable<@Nullable Object> symbolicConstraints = evaluationEnvironment.getSymbolicConstraints(apparentOperation, boxedSourceAndArgumentValues);
							if (symbolicConstraints != null) {
								boolean mayBeInvalid = true;
								boolean mayBeNull = true;
								for (@Nullable Object symbolicConstraint : symbolicConstraints) {
									if (!ValueUtil.mayBeInvalid(symbolicConstraint)) {
										mayBeInvalid = false;
									}
									if (!ValueUtil.mayBeNull(symbolicConstraint)) {
										mayBeNull = false;
									}
								}
								return new SymbolicOperationCallValueImpl(operationCallExp, mayBeNull, mayBeInvalid, implementation, boxedSourceAndArgumentValues);
							}
							Object result = implementation.symbolicEvaluate(context, operationCallExp, sourceValue, onlyArgumentValue);
							assert !(result instanceof NullValue);// || (result instanceof SymbolicValue);
							evaluationEnvironment.addSymbolicResult(apparentOperation, boxedSourceAndArgumentValues, result);
							return result;
						}
					}
					catch (InvalidValueException e) {
						throw e;
					}
					catch (Exception e) {
						// This is a backstop. Library operations should catch their own exceptions
						//  and produce a better reason as a result.
						throw new InvalidValueException(e, PivotMessagesInternal.FailedToEvaluate_ERROR_, apparentOperation, ILabelGenerator.Registry.INSTANCE.labelFor(sourceValue), operationCallExp);
					}
					catch (AssertionError e) {
						// This is a backstop. Library operations should catch their own exceptions
						//  and produce a better reason as a result.
						throw new InvalidValueException(e, PivotMessagesInternal.FailedToEvaluate_ERROR_, apparentOperation, ILabelGenerator.Registry.INSTANCE.labelFor(sourceValue), operationCallExp);
					} */
				}
			}
			actualOperation = actualSourceType.lookupActualOperation(standardLibrary, apparentOperation);
		}
		LibraryOperation implementation = (LibraryOperation)metamodelManager.getImplementation(actualOperation);
		try {
		/*	if (!isValidating) {
				//
				//	No validating/short-circuit terms symbolicValues - get the arguments
				//
				List<@NonNull OCLExpression> ownedArguments = PivotUtilInternal.getOwnedArgumentsList(operationCallExp);
				@Nullable Object[] boxedSourceAndArgumentValues = new @Nullable Object[ownedArguments.size()+1];
				boolean isSymbolic = sourceValue instanceof SymbolicValue;
				int i = 0;
				boxedSourceAndArgumentValues[i++] = sourceValue;
				for (@NonNull OCLExpression argument : ownedArguments) {
					Object argumentValue = evaluate(argument);
					if (argumentValue instanceof SymbolicValue) {
						isSymbolic = true;
					}
					boxedSourceAndArgumentValues[i++] = argumentValue;
				}
				if (!isSymbolic) {
					//
					//	No symbolicValues - just evaluate the 'constant'
					//
					return ((LibraryOperationExtension2)implementation).evaluate(context, operationCallExp, boxedSourceAndArgumentValues);
				}
				else {
					List<@Nullable Object> boxedSourceAndArgumentValuesList = Lists.newArrayList(boxedSourceAndArgumentValues);
					Iterable<@Nullable Object> symbolicConstraints = evaluationEnvironment.getSymbolicConstraints(apparentOperation, boxedSourceAndArgumentValuesList);
					if (symbolicConstraints != null) {
						//
						//	SymbolicValues - re-use the symbolic result
						//
						boolean mayBeInvalid = true;
						boolean mayBeNull = true;
						for (@Nullable Object symbolicConstraint : symbolicConstraints) {
							if (!ValueUtil.mayBeInvalid(symbolicConstraint)) {
								mayBeInvalid = false;
							}
							if (!ValueUtil.mayBeNull(symbolicConstraint)) {
								mayBeNull = false;
							}
						}
						return new SymbolicOperationCallValueImpl(operationCallExp, mayBeNull, mayBeInvalid, implementation, boxedSourceAndArgumentValuesList);
					}
					else {
						//
						//	SymbolicValues - cache the symbolic result
						//
						if (apparentOperation.getImplementation() == OrderedCollectionAtOperation.INSTANCE) {
							getClass();
						}


						Object result2 = implementation.symbolicDispatch(this, operationCallExp, sourceValue);


						boolean isSafe = operationCallExp.isIsSafe();
						boolean sourceMayBeInvalid = ValueUtil.mayBeInvalid(sourceValue);
						boolean sourceMayBeNull = ValueUtil.mayBeNull(sourceValue);
						boolean operationIsRequired = apparentOperation.isIsRequired();
						boolean operationMayBeNull = !implementation.resolveReturnNullity(environmentFactory, operationCallExp, operationIsRequired);
						boolean resultMayBeInvalid = sourceMayBeInvalid || (sourceMayBeNull && !isSafe);
						boolean resultMayBeNull = operationMayBeNull || (sourceMayBeNull && isSafe && !operationCallExp.isIsMany());
						Object result = new SymbolicOperationCallValueImpl(operationCallExp, resultMayBeNull, resultMayBeInvalid, implementation, boxedSourceAndArgumentValuesList);
						assert !(result instanceof NullValue);// || (result instanceof SymbolicValue);
						evaluationEnvironment.addSymbolicResult(apparentOperation, boxedSourceAndArgumentValuesList, result);
						return result;
					}
				}
			}
			else { */
				//
				//	Validating/short-circuit - dispatch the source for assessment before looking at the argument
				//
				return implementation.symbolicEvaluate(evaluationEnvironment, operationCallExp);
		//	}
		}
		catch (InvalidValueException e) {
			throw e;
		}
		catch (Exception e) {
			// This is a backstop. Library operations should catch their own exceptions
			//  and produce a better reason as a result.
			throw new InvalidValueException(e, PivotMessagesInternal.FailedToEvaluate_ERROR_, apparentOperation, ILabelGenerator.Registry.INSTANCE.labelFor(sourceValue), operationCallExp);
		}
		catch (AssertionError e) {
			// This is a backstop. Library operations should catch their own exceptions
			//  and produce a better reason as a result.
			throw new InvalidValueException(e, PivotMessagesInternal.FailedToEvaluate_ERROR_, apparentOperation, ILabelGenerator.Registry.INSTANCE.labelFor(sourceValue), operationCallExp);
		}
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

/*	protected @NonNull SymbolicValue nestedEvaluate(@NonNull OCLExpression constrainedExpression, @NonNull SymbolicValue constrainedValue, @NonNull OCLExpression expression) {
		//	if (scopeValue instanceof SymbolicConstraint) {
		//		return scopeValue;		// XXX FIXME deductions
		//	}
		//	if (scopeValue instanceof SymbolicUnknownValue) {
		//		return scopeValue;
		//	}
	//	else if (scopeValue instanceof SymbolicVariableValue) {
	//		return scopeValue;
	//	}
		SymbolicExecutor symbolicExecutor = getSymbolicExecutor();
		try {
			ConstrainedSymbolicEvaluationEnvironment constrainedSymbolicEvaluationEnvironment = symbolicExecutor.pushConstrainedSymbolicEvaluationEnvironment(expression);
			constrainedSymbolicEvaluationEnvironment.addConstraint(constrainedExpression/ *, unconstrainedValue* /, constrainedValue);
	//		AbstractSymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
			return constrainedSymbolicEvaluationEnvironment.symbolicEvaluate(expression);
		}
		finally {
			symbolicExecutor.popConstrainedSymbolicEvaluationEnvironment();
		}
	} */

/*	public @NonNull SymbolicValue symbolicEvaluate(@NonNull TypedElement element) {
		SymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		SymbolicValue symbolicValue = evaluationEnvironment.basicGetSymbolicValue(element);			// Re-use old value
		if (symbolicValue != null) {
			return symbolicValue;
		}
		Object result;
		try {
			EvaluationVisitor undecoratedVisitor = getUndecoratedVisitor();
			result = element.accept(undecoratedVisitor);
		}
		catch (InvalidValueException e) {
			result = e;
		}
		return evaluationEnvironment.traceValue(element, result);								// Record new value
	} */

	@Override
	public @Nullable Object visitCollectionItem(@NonNull CollectionItem item) {
		Object result;
//			result = delegate.visitCollectionItem(item);
		boolean isSymbolic = false;
		boolean mayBeInvalid = false;
		boolean mayBeNull = false;
	//	Object itemValue = evaluate(item);
		OCLExpression ownedItem = PivotUtil.getOwnedItem(item);		// XXX
		Object itemValue = delegate.evaluate(ownedItem);
		if (itemValue instanceof SymbolicValue) {
			isSymbolic = true;
		}
		if (ValueUtil.mayBeInvalid(itemValue)) {
			mayBeInvalid = true;
		}
		if (ValueUtil.mayBeNull(itemValue)) {
			mayBeNull = true;
		}
		if (isSymbolic) {
			result = new SymbolicExpressionValueImpl(ownedItem, true, mayBeNull || mayBeInvalid);
		}
		else {
			result = itemValue;
		}
		return result;
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
			result = new SymbolicExpressionValueImpl(literalExp, true, mayBeNull || mayBeInvalid);
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
		if (isCanceled()) {
			throw new EvaluationHaltedException("Canceled");
		}
		AbstractSymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		return evaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedBody(expression));
	}

	@Override
	public @NonNull SymbolicValue visitIfExp(@NonNull IfExp ifExp) {
		AbstractSymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		OCLExpression conditionExpression = PivotUtil.getOwnedCondition(ifExp);
		SymbolicValue conditionValue = evaluationEnvironment.symbolicEvaluate(conditionExpression);
		if (conditionValue.isTrue()) {
			OCLExpression thenExpression = PivotUtil.getOwnedThen(ifExp);
			return evaluationEnvironment.symbolicEvaluate(thenExpression);
		}
		else if (conditionValue.isFalse()) {
			OCLExpression elseExpression = PivotUtil.getOwnedElse(ifExp);
			return evaluationEnvironment.symbolicEvaluate(elseExpression);
		}
		else {
			boolean mayBeInvalid = conditionValue.mayBeInvalid();
			boolean mayBeNull = conditionValue.mayBeNull();
			OCLExpression thenExpression = PivotUtil.getOwnedThen(ifExp);
		//	SymbolicValue knownThenValue = evaluationEnvironment.getKnownValue(Boolean.TRUE);
		//	SymbolicValue thenValue = nestedEvaluate(conditionExpression, knownThenValue, thenExpression);
			SymbolicValue thenValue = evaluationEnvironment.symbolicEvaluate(thenExpression);
			if (thenValue.mayBeInvalid()) {
				mayBeInvalid = true;
			}
			if (thenValue.mayBeNull()) {
				mayBeNull = true;
			}
			OCLExpression elseExpression = PivotUtil.getOwnedElse(ifExp);
		//	SymbolicValue knownElseValue = evaluationEnvironment.getKnownValue(Boolean.FALSE);
		//	SymbolicValue elseValue = nestedEvaluate(conditionExpression, knownElseValue, elseExpression);
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
	public Object visitLetExp(@NonNull LetExp letExp) {
		OCLExpression expression = letExp.getOwnedIn();		// Never null when valid
		Variable variable = letExp.getOwnedVariable();		// Never null when valid
		assert variable != null;
		Object value;
		try {
			value = variable.accept(getUndecoratedVisitor());
		}
		catch (EvaluationHaltedException e) {
			throw e;
		}
		catch (InvalidValueException e) {
			value = e;
		}
		//		value = ValuesUtil.asValue(value);
		assert expression != null;
		AbstractSymbolicEvaluationEnvironment nestedEvaluationEnvironment = getEvaluationEnvironment(); //context.pushEvaluationEnvironment(expression, (TypedElement)letExp);
		nestedEvaluationEnvironment.add(variable, value);
		SymbolicValue inValue = nestedEvaluationEnvironment.symbolicEvaluate(expression);
		return inValue;
	}

	@Override
	public Object visitLoopExp(@NonNull LoopExp loopExp) {
		AbstractSymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		Object result;
		OCLExpression ownedSource = PivotUtil.getOwnedSource(loopExp);
		CollectionType sourceType = (CollectionType)ownedSource.getType();
		evaluationEnvironment.symbolicEvaluate(ownedSource);
		if (loopExp.isIsMany()) {
			result = new SymbolicCollectionValueImpl(loopExp, false, false);
		}
		else {
			result = new SymbolicExpressionValueImpl(loopExp, false, false);		// FIXME null / invalid
		}
		OCLExpression bodyExpression = PivotUtil.getOwnedBody(loopExp);
	//	context.pushEvaluationEnvironment(bodyExpression, (Object)loopExp);
	//	try {
			SymbolicAnalysis symbolicAnalysis = getSymbolicAnalysis();
			for (@NonNull VariableDeclaration iterator : PivotUtil.getOwnedIterators(loopExp)) {
				CSEElement iteratorCSE = symbolicAnalysis.getCSEElement(iterator);
				SymbolicValue iteratorValue = new SymbolicVariableValueImpl(iterator, !sourceType.isIsNullFree(), false);
				evaluationEnvironment.traceSymbolicValue(iteratorCSE, iteratorValue);



			//	evaluate(iterator);
			}
			if (loopExp instanceof IterateExp) {
				evaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedResult((IterateExp)loopExp));
			}
			evaluate(bodyExpression);
	//	}
	//	finally {
	//		context.popEvaluationEnvironment();
	//	}
		return result;
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
			return new SymbolicExpressionValueImpl(literalExp, true, mayBeNull || mayBeInvalid);
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
	public @Nullable Object visitPropertyCallExp(@NonNull PropertyCallExp propertyCallExp) {
		return doNavigationCallExp(propertyCallExp);
	}

	@Override
	public @Nullable Object visitShadowExp(@NonNull ShadowExp shadowExp) {
		Object result;
		boolean isSymbolic = false;
		boolean mayBeInvalid = false;
		boolean mayBeNull = false;
		for (@NonNull ShadowPart part : PivotUtil.getOwnedParts(shadowExp)) {
			Object partValue = evaluate(PivotUtil.getOwnedInit(part));
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
			result = new SymbolicExpressionValueImpl(shadowExp, true, mayBeNull || mayBeInvalid);
		}
		else {
			result = delegate.visitShadowExp(shadowExp);
		}
		return result;
	}

	@Override
	public @Nullable Object visitTupleLiteralExp(@NonNull TupleLiteralExp literalExp) {
		Object result;
		boolean isSymbolic = false;
		boolean mayBeInvalid = false;
		boolean mayBeNull = false;
		for (@NonNull TupleLiteralPart part : PivotUtil.getOwnedParts(literalExp)) {
			Object partValue = evaluate(PivotUtil.getOwnedInit(part));
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
			result = new SymbolicExpressionValueImpl(literalExp, true, mayBeNull || mayBeInvalid);
		}
		else {
			result = delegate.visitTupleLiteralExp(literalExp);
		}
		return result;
	}
}
