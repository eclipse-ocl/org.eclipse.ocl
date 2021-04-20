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
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TupleLiteralExp;
import org.eclipse.ocl.pivot.TupleLiteralPart;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.evaluation.EvaluationHaltedException;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.manager.SymbolicExecutor;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.internal.values.SymbolicCollectionValueImpl;
import org.eclipse.ocl.pivot.internal.values.SymbolicExpressionValueImpl;
import org.eclipse.ocl.pivot.internal.values.SymbolicNavigationCallValueImpl;
import org.eclipse.ocl.pivot.internal.values.SymbolicOperationCallValueImpl;
import org.eclipse.ocl.pivot.internal.values.SymbolicUnknownValueImpl;
import org.eclipse.ocl.pivot.internal.values.SymbolicVariableValueImpl;
import org.eclipse.ocl.pivot.labels.ILabelGenerator;
import org.eclipse.ocl.pivot.library.LibraryBinaryOperation;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.LibraryOperation.LibraryOperationExtension2;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IntegerRange;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.MapValue;
import org.eclipse.ocl.pivot.values.NullValue;
import org.eclipse.ocl.pivot.values.SymbolicConstraint;
import org.eclipse.ocl.pivot.values.SymbolicValue;

import com.google.common.collect.Lists;

/**
 * A decorator for evaluation visitors that is installed when evaluation tracing
 * is enabled, to trace interim evaluation results to the console.
 * @since 1.15
 */
public class SymbolicAnalysis extends EvaluationVisitorDecorator implements EvaluationVisitor
{
	protected final @NonNull ExpressionInOCL expressionInOCL;
	protected final @NonNull EnvironmentFactoryInternal environmentFactory;
	protected final @NonNull IdResolver idResolver;

	/**
	 * Initializes the symbolic analysis of expressionInOCL that delegates to a non-symbolic evaluation visitor.
	 */
	public SymbolicAnalysis(@NonNull ExpressionInOCL expressionInOCL, @NonNull EvaluationVisitor visitor,
			@Nullable Object context, @Nullable Object @NonNull [] parameters) {
		super(visitor);
		this.expressionInOCL = expressionInOCL;
		this.environmentFactory = (EnvironmentFactoryInternal) visitor.getEnvironmentFactory();
		this.idResolver = environmentFactory.getIdResolver();
		VariableDeclaration contextVariable = PivotUtil.getOwnedContext(expressionInOCL);
	//	Object contextValue = idResolver.boxedValueOf(context);
		trace(contextVariable, context);
		int i = 0;
		for (Variable parameterVariable : PivotUtil.getOwnedParameters(expressionInOCL)) {
	//		Object parameterValue = idResolver.boxedValueOf(parameters[i++]);
			trace(parameterVariable, parameters[i++]);
		}
	}

	public @Nullable SymbolicValue basicGet(@NonNull Element element) {
		return getElement2SymbolicValue().get(element);
	}

	protected @Nullable Object doNavigationCallExp(@NonNull NavigationCallExp navigationCallExp) {
		Object result = null;
		try {
			Property referredProperty = PivotUtil.getReferredProperty(navigationCallExp);
			OCLExpression source = PivotUtil.getOwnedSource(navigationCallExp);

			SymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		//	SymbolicAnalysis.evaluateHypothesis(evaluationEnvironment, source, )



			Object sourceValue = evaluate(source);
			List<@Nullable Object> sourceAndArgumentValues = Lists.newArrayList(sourceValue);
			Iterable<@Nullable Object> symbolicConstraints = evaluationEnvironment.getSymbolicConstraints(referredProperty, sourceAndArgumentValues);
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
			else if (sourceValue instanceof SymbolicValue) {
				boolean isSafe = navigationCallExp.isIsSafe();
				boolean sourceMayBeInvalid = ValueUtil.mayBeInvalid(sourceValue);
				boolean sourceMayBeNull = ValueUtil.mayBeNull(sourceValue);
				boolean sourceIsMany = navigationCallExp.isIsMany();
				boolean propertyMayBeNull = !referredProperty.isIsRequired();
				boolean resultMayBeInvalid = sourceMayBeInvalid || (sourceMayBeNull && !isSafe && !sourceIsMany);
				boolean resultMayBeNull = propertyMayBeNull || (sourceMayBeNull && isSafe && !sourceIsMany);
				result = new SymbolicNavigationCallValueImpl(navigationCallExp, resultMayBeNull, resultMayBeInvalid, (SymbolicValue) sourceValue);
			}
			else {
				result = context.internalExecuteNavigationCallExp(navigationCallExp, referredProperty, sourceValue);
			}
		}
		catch (InvalidValueException e) {
			result = e;
		}
		return trace(navigationCallExp, result);
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
		boolean isValidating = apparentOperation.isIsValidating();
		//
		//	Resolve source value catching invalid values for validating operations.
		//
		OCLExpression source = operationCallExp.getOwnedSource();
		Object sourceValue;
		if (source == null) {							// Static functions may have null source
			sourceValue = null;
		}
		else if (!isValidating) {
			sourceValue = evaluate(source);
		}
		else {
			try {
				sourceValue = evaluate(source);
				assert ValueUtil.isBoxed(sourceValue);	// Make sure Integer/Real are boxed, invalid is an exception, null is null
			}
			catch (EvaluationHaltedException e) {
				throw e;
			}
			catch (InvalidValueException e) {
				sourceValue = e;	// FIXME ?? propagate part of environment
			}
		}
		//
		//	Safe navigation of null source return null.
		//
		if (operationCallExp.isIsSafe()) {
			if (sourceValue == null) {
				return null;
			}
			if (!(sourceValue instanceof SymbolicValue)) {
				if (sourceValue instanceof MapValue) {
					throw new InvalidValueException(PivotMessages.MapValueForbidden);
				}
				if (sourceValue instanceof CollectionValue) {
					sourceValue = ((CollectionValue)sourceValue).excluding(null);
				}
			}
		}
		PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		SymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		Operation actualOperation;
		if (apparentOperation.isIsStatic()) {
			actualOperation = apparentOperation;
		}
		else {
			assert source != null;
			StandardLibrary standardLibrary = environmentFactory.getStandardLibrary();
			org.eclipse.ocl.pivot.Class actualSourceType = idResolver.getStaticTypeOfValue(source.getType(), sourceValue);
			if (!isValidating) {  // OclSelf dispatch wrt invalid is unnecessarily hard symbolically - no Boolean overloads
				List<@NonNull Parameter> ownedParameters = PivotUtilInternal.getOwnedParametersList(apparentOperation);
				if (ownedParameters.size() == 1) {
					Parameter onlyParameter = ownedParameters.get(0);
					Type onlyType = onlyParameter.getType();
					if (onlyType == standardLibrary.getOclSelfType()) {
						List<@NonNull OCLExpression> arguments = ClassUtil.nullFree(operationCallExp.getOwnedArguments());
						Object onlyArgumentValue = evaluate(arguments.get(0));
						org.eclipse.ocl.pivot.Class actualArgType = idResolver.getStaticTypeOfValue(onlyType, onlyArgumentValue);
						actualSourceType = (org.eclipse.ocl.pivot.Class)actualSourceType.getCommonType(idResolver, actualArgType);
						// FIXME direct evaluate using second argument
						actualOperation = actualSourceType.lookupActualOperation(standardLibrary, apparentOperation);
						LibraryBinaryOperation implementation = (LibraryBinaryOperation)metamodelManager.getImplementation(actualOperation);
						try {
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
						}
					}
				}
			}
			actualOperation = actualSourceType.lookupActualOperation(standardLibrary, apparentOperation);
		}
		LibraryOperation implementation = (LibraryOperation)metamodelManager.getImplementation(actualOperation);
		try {
			if (!isValidating) {
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
					//	if (apparentOperation.getImplementation() == OrderedCollectionAtOperation.INSTANCE) {
					//		getClass();
					//	}
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
			else {
				//
				//	Validating/short-circuit - dispatch the source for assessment before looking at the argument
				//
				return implementation.symbolicDispatch(this, operationCallExp, sourceValue);
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
		}
	}

	public void evaluate(@NonNull ExpressionInOCL expressionInOCL) {
		Object result;
		try {
			result = evaluate(PivotUtil.getOwnedBody(expressionInOCL));
		}
		catch (InvalidValueException e) {
			result = e;
		}
	//	SymbolicValue symbolicResultValue = getEvaluationEnvironment().getKnownValue(result);
		//	getElement2SymbolicValue().put(expressionInOCL, symbolicResultValue);
		trace(expressionInOCL, result);
	}

	public @Nullable Object evaluate(@NonNull CollectionLiteralPart expression) {
		SymbolicValue symbolicValue = basicGet(expression);
		if (symbolicValue != null) {
			return symbolicValue;
		}
		Object result;
		try {
			result = expression.accept(getUndecoratedVisitor());
		}
		catch (InvalidValueException e) {
			result = e;
		}
		return trace(expression, result);
	}

	@Override
	public @Nullable Object evaluate(@NonNull OCLExpression expression) {
		SymbolicValue symbolicValue = basicGet(expression);
		if (symbolicValue != null) {
			return symbolicValue;
		}
		Object result;
		try {
			result = super.evaluate(expression);
		}
		catch (InvalidValueException e) {
			result = e;
		}
		return trace(expression, result);
	}

	private void evaluate(@NonNull VariableDeclaration variable) {
		SymbolicVariableValueImpl variableValue = new SymbolicVariableValueImpl(variable, !variable.isIsRequired(), false);
		context.getEvaluationEnvironment().add(variable, variableValue);
	//	Object old = getElement2SymbolicValue().put(variable, variableValue);
	//	assert old == null;
		trace(variable, variableValue);
	}

	public @NonNull SymbolicValue get(@NonNull Element element) {
		SymbolicValue symbolicValue = getElement2SymbolicValue().get(element);
		assert symbolicValue != null;
		return symbolicValue;
	}

	@Override
	public @NonNull SymbolicEvaluationEnvironment getEvaluationEnvironment() {
		return (SymbolicEvaluationEnvironment) super.getEvaluationEnvironment();
	}

	public boolean isDead(@NonNull Element element) {
		return basicGet(element) == null;
	}

	public boolean isFalse(@NonNull Element element) {
		return get(element).isFalse();
	}

	public boolean isInvalid(@NonNull Element element) {
		return get(element).isInvalid();
	}

	public boolean isNull(@NonNull Element element) {
		return get(element).isNull();
	}

	public boolean isTrue(@NonNull Element element) {
		return get(element).isTrue();
	}

	public boolean mayBeInvalid(@NonNull Element element) {
		return get(element).mayBeInvalid();
	}

	public boolean mayBeNull(@NonNull Element element) {
		return get(element).mayBeNull();
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

	protected @Nullable Object nestedEvaluate(@NonNull SymbolicValue scopeValue, @NonNull Object knownScopeValue, @NonNull OCLExpression expression) {
			if (scopeValue instanceof SymbolicConstraint) {
				return scopeValue;		// XXX FIXME deductions
			}
		//	if (scopeValue instanceof SymbolicUnknownValue) {
		//		return scopeValue;
		//	}
	//	else if (scopeValue instanceof SymbolicVariableValue) {
	//		return scopeValue;
	//	}
		SymbolicExecutor symbolicExecutor = (SymbolicExecutor) getExecutor();
		try {
			symbolicExecutor.pushSymbolicEvaluationEnvironment(scopeValue, knownScopeValue, expression);
			return evaluate(expression);
		}
		finally {
			symbolicExecutor.popEvaluationEnvironment();
		}
	}

	@Override
	public @NonNull String toString() {
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
			SymbolicValue symbolicValue = getElement2SymbolicValue().get(eObject);
			if (symbolicValue == null) {
				s.append("not-computed");
			}
			else {
				s.append(symbolicValue.getClass().getSimpleName());
				s.append(" : ");
				s.append(symbolicValue);
			}
		}
		return s.toString();
	}

	public @NonNull Map<@NonNull Element, @NonNull SymbolicValue> getElement2SymbolicValue() {
		return getEvaluationEnvironment().getElement2SymbolicValue();
	}

	protected @Nullable Object trace(@NonNull Element expression, @Nullable Object value) {
		SymbolicValue symbolicValue;
		if (value instanceof SymbolicValue) {
			symbolicValue = (SymbolicValue) value;
		}
		else {
			Object boxedValue = idResolver.boxedValueOf(value);
			symbolicValue = getEvaluationEnvironment().getKnownValue(boxedValue);
		}
		if ("self.name".equals(expression.toString())) {
			getClass();		// XXX
		}
		Object old = getElement2SymbolicValue().put(expression, symbolicValue);
		assert (old == null);// || (old == symbolicValue) || old.equals(symbolicValue);
		if (value instanceof InvalidValueException) {
			throw (InvalidValueException)value;
		}
		return value;
	}

	@Override
	public @Nullable Object visitCollectionItem(@NonNull CollectionItem item) {
		Object result;
//			result = delegate.visitCollectionItem(item);
		boolean isSymbolic = false;
		boolean mayBeInvalid = false;
		boolean mayBeNull = false;
	//	Object itemValue = evaluate(item);
		OCLExpression ownedItem = PivotUtil.getOwnedItem(item);
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
		EvaluationVisitor undecoratedVisitor = delegate.getUndecoratedVisitor();
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
	public @Nullable Object visitIfExp(@NonNull IfExp ifExp) {
		Object result;
		OCLExpression condition = PivotUtil.getOwnedCondition(ifExp);
		Object conditionValue = evaluate(condition);
		if (conditionValue == ValueUtil.TRUE_VALUE) {
			OCLExpression expression = PivotUtil.getOwnedThen(ifExp);
			result = evaluate(expression);
		}
		else if (conditionValue == ValueUtil.FALSE_VALUE) {
			OCLExpression expression = PivotUtil.getOwnedElse(ifExp);
			result = evaluate(expression);
		}
		else if (conditionValue instanceof SymbolicValue) {
			boolean mayBeInvalid = ValueUtil.mayBeInvalid(conditionValue);
			boolean mayBeNull = ValueUtil.mayBeNull(conditionValue);
			OCLExpression thenExpression = PivotUtil.getOwnedThen(ifExp);
			Object thenValue = nestedEvaluate((SymbolicValue)conditionValue, Boolean.TRUE, thenExpression);
			if (ValueUtil.mayBeInvalid(thenValue)) {
				mayBeInvalid = true;
			}
			if (ValueUtil.mayBeNull(thenValue)) {
				mayBeNull = true;
			}
			OCLExpression elseExpression = PivotUtil.getOwnedElse(ifExp);
			Object elseValue = nestedEvaluate((SymbolicValue)conditionValue, Boolean.FALSE, elseExpression);
			if (ValueUtil.mayBeInvalid(elseValue)) {
				mayBeInvalid = true;
			}
			if (ValueUtil.mayBeNull(elseValue)) {
				mayBeNull = true;
			}
			result = new SymbolicExpressionValueImpl(ifExp, mayBeNull, mayBeInvalid);
		}
		else {
			result = new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.BOOLEAN_NAME, ValueUtil.getTypeName(conditionValue));
		}
		return result;
	}

	@Override
	public Object visitLoopExp(@NonNull LoopExp loopExp) {
		Object result;
		evaluate(PivotUtil.getOwnedSource(loopExp));
		if (loopExp.isIsMany()) {
			result = new SymbolicCollectionValueImpl(loopExp, false, false);
		}
		else {
			result = new SymbolicExpressionValueImpl(loopExp, false, false);		// FIXME null / invalid
		}
		try {
			OCLExpression bodyExpression = PivotUtil.getOwnedBody(loopExp);
			context.pushEvaluationEnvironment(bodyExpression, (Object)loopExp);
			for (@NonNull VariableDeclaration iterator : PivotUtil.getOwnedIterators(loopExp)) {
				evaluate(iterator);
			}
			if (loopExp instanceof IterateExp) {
				evaluate(PivotUtil.getOwnedResult((IterateExp)loopExp));
			}
			@SuppressWarnings("unused")
			Object bodyValue = evaluate(bodyExpression);
		}
		finally {
			context.popEvaluationEnvironment();
		}
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

	@Override
	public @Nullable Object visitVariableExp(@NonNull VariableExp variableExp) {
		VariableDeclaration variableDeclaration = variableExp.getReferredVariable();
		if (variableDeclaration == null) {
			throw new InvalidValueException("Undefined variable", null, null, variableExp);
		}
		return getEvaluationEnvironment().getValueOf(variableDeclaration);
	}
}
