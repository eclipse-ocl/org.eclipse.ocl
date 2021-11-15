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
import org.eclipse.ocl.pivot.Constraint;
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
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.symbolic.AbstractLeafSymbolicValue.SymbolicNavigationCallValue;
import org.eclipse.ocl.pivot.internal.symbolic.AbstractSymbolicRefinedValue;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicNumericValue;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicReason;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUtil;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryIteration;
import org.eclipse.ocl.pivot.library.LibraryOperation;
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
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * A symbolic evaluation decorator for an evaluation visitor.
 *
 * FIXME This shou;d replicate the PivotValidator. Share constraints somehow.
 *
 * @since 1.17
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

	public void addKnownVariable(@NonNull TypedElement typedElement, Object knownValue) {
		evaluationVisitor.getEvaluationEnvironment().add(typedElement, knownValue);
	}

	protected @NonNull SymbolicValue doNavigationCallExp(@NonNull NavigationCallExp navigationCallExp) {
		Property referredProperty = PivotUtil.getReferredProperty(navigationCallExp);
		OCLExpression source = PivotUtil.getOwnedSource(navigationCallExp);
		SymbolicValue sourceValue = symbolicEvaluationEnvironment.symbolicEvaluate(source);
		Type returnType = PivotUtil.getType(navigationCallExp);
		SymbolicValue compatibilityProblem = symbolicEvaluationEnvironment.checkCompatibility(source, returnType);
		if (compatibilityProblem != null) {
			return compatibilityProblem;
		}
		if (!symbolicEvaluationEnvironment.checkConformance(source, returnType, source, navigationCallExp)) {
			return symbolicEvaluationEnvironment.getInvalidValue("incompatible source for '" + navigationCallExp.getName() + "'");
		}
		SymbolicReason propertyMayBeNullReason = SymbolicUtil.isRequiredReason(referredProperty);
		SymbolicValue invalidProblem = symbolicEvaluationEnvironment.checkNotInvalid(source, returnType, propertyMayBeNullReason, navigationCallExp);
		if (invalidProblem != null) {
			return invalidProblem;
		}
	//	if (sourceValue.isNull()) {  -- redundant
	//		if (navigationCallExp.isIsSafe()) {
	//			return context.getKnownValue(null);
	//		}
	//		else {
	//			return context.getKnownValue(ValueUtil.INVALID_VALUE);
	//		}
	//	}
		if (!navigationCallExp.isIsSafe()) {
			SymbolicValue nullSourceProblem = symbolicEvaluationEnvironment.checkNotNull(source, returnType, propertyMayBeNullReason, navigationCallExp);
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
			SymbolicReason sourceMayBeInvalidReason = sourceValue.mayBeInvalidReason();
			SymbolicReason sourceMayBeNullReason = sourceValue.mayBeNullReason();
			boolean sourceIsMany = navigationCallExp.isIsMany();
			SymbolicReason resultMayBeInvalidReason = sourceMayBeInvalidReason;
			if (!isSafe && !sourceIsMany && (resultMayBeInvalidReason == null)) {
				resultMayBeInvalidReason = sourceMayBeNullReason;
			}
			SymbolicReason resultMayBeNullReason = propertyMayBeNullReason;
			if (isSafe && !sourceIsMany && (resultMayBeNullReason == null)) {
				resultMayBeNullReason = sourceMayBeNullReason;
			}
			SymbolicNavigationCallValue symbolicValue = new SymbolicNavigationCallValue(context.createVariableName(), navigationCallExp, resultMayBeNullReason, resultMayBeInvalidReason, sourceValue);
			if (referredProperty.isIsMany()) {
				CollectionType collectionType = (CollectionType)referredProperty.getType();
				IntegerValue lowerValue = collectionType.getLowerValue();
				UnlimitedNaturalValue upperValue = collectionType.getUpperValue();
				if (!lowerValue.equals(ValueUtil.ZERO_VALUE) || !upperValue.equals(ValueUtil.UNLIMITED_VALUE)) {
					SymbolicContent content = symbolicValue.getContent();
					SymbolicValue size = SymbolicNumericValue.get(lowerValue, upperValue);
					content.setSize(size);
				}
			}
			return symbolicValue;
		}
	}

	/**
	 * @since 1.12
	 */
	protected @NonNull SymbolicValue doOperationCallExp(@NonNull OperationCallExp operationCallExp) {
		Operation apparentOperation = PivotUtil.getReferredOperation(operationCallExp);
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
	//	boolean mayBeNull = !operationCallExp.isIsRequired();
		//
		//	Safe navigation of null source return null.
		/*
		if (operationCallExp.isIsSafe() && (source != null)) {
			if (sourceValue == null) {
				return context.getKnownValue(null);
			}
			if (sourceValue.isNull()) {
				return sourceValue;
			}
			SymbolicValue sourceProblem = symbolicEvaluationEnvironment.checkNotNull(source, operationCallExp.getTypeId(), mayBeNull);
			if (sourceProblem != null) {
				return sourceProblem;
			}
			if (sourceValue.isMap()) {
				throw new InvalidValueException(PivotMessages.MapValueForbidden);
			}
			if (sourceValue.isCollection()) {
				sourceValue = AbstractSymbolicRefinedValue.createNullFreeValue(sourceValue);
			}
		} */
		PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		Operation actualOperation;
		if (apparentOperation.isIsStatic()) {
			actualOperation = apparentOperation;
		}
		else {
			assert source != null;
			org.eclipse.ocl.pivot.Class actualSourceType =  sourceValue != null ? PivotUtil.getClass(sourceValue.getType(), standardLibrary) : standardLibrary.getOclVoidType();
			List<@NonNull Parameter> ownedParameters = PivotUtilInternal.getOwnedParametersList(apparentOperation);
			if (ownedParameters.size() == 1) {
				Parameter onlyParameter = ownedParameters.get(0);
				Type onlyType = onlyParameter.getType();
				if (onlyType == standardLibrary.getOclSelfType()) {
					List<@NonNull OCLExpression> arguments = ClassUtil.nullFree(operationCallExp.getOwnedArguments());
					SymbolicValue onlyArgumentValue = symbolicEvaluationEnvironment.symbolicEvaluate(arguments.get(0));
					org.eclipse.ocl.pivot.Class actualArgType = (org.eclipse.ocl.pivot.Class)onlyArgumentValue.getType();
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

	public @NonNull SymbolicAnalysis getSymbolicAnalysis() {
		return context;
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
		SymbolicReason mayBeInvalidReason = null;
		for (@NonNull CollectionLiteralPart part : PivotUtil.getOwnedParts(literalExp)) {
			SymbolicValue partValue = symbolicEvaluate(part);
			if (partValue.isInvalid()) {
				return partValue;
			}
			if (!partValue.isKnown()) {
				isKnown = false;
			}
			if (mayBeInvalidReason == null) {
				mayBeInvalidReason = partValue.mayBeInvalidReason();
			}
			if (isNullFree) {
				if (partValue.isNull()) {
					return symbolicEvaluationEnvironment.getInvalidValue("null part value");
				}
				if (mayBeInvalidReason == null) {
					mayBeInvalidReason = partValue.mayBeNullReason();
				}
			}
		}
		if (isKnown) {
			Object resultValue = evaluationVisitor.visitCollectionLiteralExp(literalExp);
			return context.getKnownValue(resultValue);
		}
		else {
			return context.getUnknownValue(literalExp, null, mayBeInvalidReason);
		}
	}

	@Override
	public @NonNull SymbolicValue visitCollectionRange(@NonNull CollectionRange range) {
		OCLExpression first = PivotUtil.getOwnedFirst(range);
		OCLExpression last = PivotUtil.getOwnedLast(range);
		SymbolicValue firstValue = symbolicEvaluate(first);
		SymbolicValue lastValue = symbolicEvaluate(last);
		if (!firstValue.isKnown() || !lastValue.isKnown()) {
			SymbolicReason mayBeInvalidReason = firstValue.mayBeInvalidReason();
			if (mayBeInvalidReason == null) {
				mayBeInvalidReason = firstValue.mayBeNullReason();
				if (mayBeInvalidReason == null) {
					mayBeInvalidReason = lastValue.mayBeInvalidReason();
					if (mayBeInvalidReason == null) {
						mayBeInvalidReason = lastValue.mayBeNullReason();
					}
				}
			}
			return context.getUnknownValue(range, null, mayBeInvalidReason);
		}
		Object resultValue;
		CollectionType type = (CollectionType) ((CollectionLiteralExp)range.eContainer()).getType();
		CollectionTypeId typeId = type.getTypeId();
		IntegerValue firstInteger = ValueUtil.asIntegerValue(firstValue.getKnownValue());
		IntegerValue lastInteger = ValueUtil.asIntegerValue(lastValue.getKnownValue());
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
		SymbolicValue symbolicValue = symbolicEvaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedBody(expression));
		if (symbolicValue.asIncompatibility() != null) {
			return symbolicValue;
		}
		if (expression.eContainer() instanceof Constraint) {		// Anything definitely not-true for a Constraint is an incompatibility.
			if (symbolicValue.isInvalid()) {
				return AbstractSymbolicRefinedValue.createIncompatibility(symbolicValue, "invalid-constraint-result", expression);
			}
			if (symbolicValue.isNull()) {
				return AbstractSymbolicRefinedValue.createIncompatibility(symbolicValue, "null-constraint-result", expression);
			}
			if (symbolicValue.isFalse()) {
				return AbstractSymbolicRefinedValue.createIncompatibility(symbolicValue, "false-constraint-result", expression);
			}
		}
		return symbolicValue;
	}

	@Override
	public @NonNull SymbolicValue visitIfExp(@NonNull IfExp ifExp) {
		OCLExpression conditionExpression = PivotUtil.getOwnedCondition(ifExp);
		SymbolicValue compatibilityProblem = symbolicEvaluationEnvironment.checkCompatibility(conditionExpression, PivotUtil.getType(ifExp));
		if (compatibilityProblem != null) {
			return compatibilityProblem;
		}
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
		else if (conditionValue.isInvalid()) {
			symbolicEvaluationEnvironment.setDead(thenExpression);
			symbolicEvaluationEnvironment.setDead(elseExpression);
			return conditionValue;
		}
		else if (conditionValue.isNull()) {
			symbolicEvaluationEnvironment.setDead(thenExpression);
			symbolicEvaluationEnvironment.setDead(elseExpression);
			return symbolicEvaluationEnvironment.getInvalidValue("null condition");
		}
		else {
			SymbolicReason mayBeInvalidReason = SymbolicUtil.mayBeInvalidReason(conditionValue, "condition");;
			SymbolicReason mayBeNullReason = SymbolicUtil.mayBeNullReason(conditionValue, "condition");
			SymbolicValue thenValue = symbolicEvaluationEnvironment.symbolicEvaluate(thenExpression);
			SymbolicValue elseValue = symbolicEvaluationEnvironment.symbolicEvaluate(elseExpression);
			if (mayBeInvalidReason == null) {
				mayBeInvalidReason = SymbolicUtil.mayBeInvalidReason(thenValue, "then");
				if (mayBeInvalidReason == null) {
					mayBeInvalidReason = SymbolicUtil.mayBeInvalidReason(elseValue, "else");
				}
			}
			if (mayBeNullReason == null) {
				mayBeNullReason = SymbolicUtil.mayBeNullReason(thenValue, "then");
				if (mayBeNullReason == null) {
					mayBeNullReason = SymbolicUtil.mayBeNullReason(elseValue, "else");
				}
			}
			return context.getUnknownValue(ifExp, mayBeNullReason, mayBeInvalidReason);
		}
	}

	@Override
	public @NonNull SymbolicValue visitIntegerLiteralExp(@NonNull IntegerLiteralExp integerLiteralExp) {
		Object resultValue = evaluationVisitor.visitIntegerLiteralExp(integerLiteralExp);
		return context.getKnownValue(resultValue);
	}

	@Override
	public @NonNull SymbolicValue visitInvalidLiteralExp(@NonNull InvalidLiteralExp invalidLiteralExp) {
		return symbolicEvaluationEnvironment.getInvalidValue("invalid literal");
	}

	@Override
	public @NonNull SymbolicValue visitIteratorVariable(@NonNull IteratorVariable iteratorVariable) {
		OCLExpression initExp = iteratorVariable.getOwnedInit();
		if (initExp == null) {
			LoopExp loopExp = (LoopExp)iteratorVariable.eContainer();
			assert loopExp != null;
			initExp = PivotUtil.getOwnedSource(loopExp);
		}
		SymbolicValue initSymbolicValue = symbolicEvaluationEnvironment.symbolicEvaluate(initExp);
		SymbolicValue elementalSymbolicValue = initSymbolicValue.getContent().getElementalSymbolicValue(this, iteratorVariable);
		return symbolicEvaluationEnvironment.setSymbolicValue(iteratorVariable, elementalSymbolicValue, "iter");
	}

	@Override
	public @NonNull SymbolicValue visitLetExp(@NonNull LetExp letExp) {
		SymbolicValue symbolicValue = symbolicEvaluationEnvironment.getSymbolicValue(PivotUtil.getOwnedVariable(letExp));
		if (symbolicValue.asIncompatibility() != null) {
			return symbolicValue;
		}
		return symbolicEvaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedIn(letExp));
	}

	@Override
	public @NonNull SymbolicValue visitLetVariable(@NonNull LetVariable letVariable) {
		SymbolicValue symbolicValue = symbolicEvaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedInit(letVariable));
		return symbolicValue;
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
		SymbolicReason mayBeInvalidReason = null;
		for (@NonNull MapLiteralPart part : PivotUtil.getOwnedParts(literalExp)) { // Inlined because of Bug 577242
			SymbolicValue keyValue = symbolicEvaluate(PivotUtil.getOwnedKey(part));
			SymbolicValue valueValue = symbolicEvaluate(PivotUtil.getOwnedValue(part));
			if (keyValue.isInvalid()) {
				return keyValue;
			}
			if (valueValue.isInvalid()) {
				return valueValue;
			}
			if (!keyValue.isKnown() || !valueValue.isKnown()) {
				isKnown = false;
			}
			if (mayBeInvalidReason == null) {
				mayBeInvalidReason = keyValue.mayBeInvalidReason();
				if (mayBeInvalidReason == null) {
					mayBeInvalidReason = valueValue.mayBeInvalidReason();
					if (mayBeInvalidReason == null) {
						mayBeInvalidReason = valueValue.mayBeInvalidReason();
					}
				}
			}
			if (isKeysAreNullFree) {
				if (keyValue.isNull()) {
					return symbolicEvaluationEnvironment.getInvalidValue("null key value");
				}
				if (mayBeInvalidReason == null) {
					mayBeInvalidReason = keyValue.mayBeNullReason();
				}
			}
			if (isValuesAreNullFree) {
				if (valueValue.isNull()) {
					return symbolicEvaluationEnvironment.getInvalidValue("null value value");
				}
				if (mayBeInvalidReason == null) {
					mayBeInvalidReason = valueValue.mayBeNullReason();
				}
			}
		}
		if (isKnown) {
			Object resultValue = evaluationVisitor.visitMapLiteralExp(literalExp);
			return context.getKnownValue(resultValue);
		}
		else {
			return context.getUnknownValue(literalExp, null, mayBeInvalidReason);
		}
	}

/*	@Override		-- see Bug 577242
	public @NonNull SymbolicValue visitMapLiteralPart(@NonNull MapLiteralPart mapLiteralPart) {
		final @NonNull MapLiteralExp literalExp = (MapLiteralExp)mapLiteralPart.eContainer();
		final boolean isKeysAreNullFree = ((MapType)literalExp.getType()).isKeysAreNullFree();
		final boolean isValuesAreNullFree = ((MapType)literalExp.getType()).isValuesAreNullFree();
		boolean isKnown = true;
		SymbolicReason mayBeInvalidReason = null;
		SymbolicValue keyValue = symbolicEvaluate(PivotUtil.getOwnedKey(mapLiteralPart));
		SymbolicValue valueValue = symbolicEvaluate(PivotUtil.getOwnedValue(mapLiteralPart));
		if (keyValue.isInvalid()) {
			return keyValue;
		}
		if (valueValue.isInvalid()) {
			return valueValue;
		}
		if (!keyValue.isKnown() || !valueValue.isKnown()) {
			isKnown = false;
		}
		if (mayBeInvalidReason == null) {
			mayBeInvalidReason = keyValue.mayBeInvalidReason();
			if (mayBeInvalidReason == null) {
				mayBeInvalidReason = valueValue.mayBeInvalidReason();
				if (mayBeInvalidReason == null) {
					mayBeInvalidReason = valueValue.mayBeInvalidReason();
				}
			}
		}
		if (isKeysAreNullFree) {
			if (keyValue.isNull()) {
				return symbolicEvaluationEnvironment.getInvalidValue(("null key value");
			}
			if (mayBeInvalidReason == null) {
				mayBeInvalidReason = keyValue.mayBeNullReason();
			}
		}
		if (isValuesAreNullFree) {
			if (valueValue.isNull()) {
				return symbolicEvaluationEnvironment.getInvalidValue(("null value value");
			}
			if (mayBeInvalidReason == null) {
				mayBeInvalidReason = valueValue.mayBeNullReason();
			}
		}
		return context.getUnknownValue(mapLiteralPart, null, mayBeInvalidReason);
	} */

	@Override
	public @NonNull SymbolicValue visitNullLiteralExp(@NonNull NullLiteralExp nullLiteralExp) {
		return context.getKnownValue(null);
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
			SymbolicValue symbolicValue = context.getUnknownValue(resultVariable, SymbolicUtil.isRequiredReason(resultVariable), null);
			return symbolicEvaluationEnvironment.setSymbolicValue(resultVariable, symbolicValue, "init");
		}
	}

	@Override
	public @NonNull SymbolicValue visitShadowExp(@NonNull ShadowExp shadowExp) {
		boolean isKnown = true;
		SymbolicReason mayBeInvalidReason = null;
		for (@NonNull ShadowPart part : PivotUtil.getOwnedParts(shadowExp)) {
			SymbolicValue partValue = symbolicEvaluationEnvironment.symbolicEvaluate(part);
			if (partValue.isInvalid()) {
				return partValue;
			}
			if (partValue.isNull()) {
				return symbolicEvaluationEnvironment.getKnownValue("null part");
			}
			if (!partValue.isKnown()) {
				isKnown = false;
			}
			if (mayBeInvalidReason == null) {
				mayBeInvalidReason = partValue.mayBeInvalidReason();
				if (mayBeInvalidReason == null) {
					mayBeInvalidReason = partValue.mayBeNullReason();
				}
			}
		}
		if (isKnown) {
			Object resultValue = evaluationVisitor.visitShadowExp(shadowExp);
			return context.getKnownValue(resultValue);
		}
		else {
			return context.getUnknownValue(shadowExp, null, mayBeInvalidReason);
		}
	}

	@Override
	public @NonNull SymbolicValue visitShadowPart(@NonNull ShadowPart shadowPart) {
		OCLExpression ownedInit = PivotUtil.getOwnedInit(shadowPart);
		SymbolicValue initValue = symbolicEvaluationEnvironment.symbolicEvaluate(ownedInit);
		if (initValue.isInvalid()) {
			return initValue;
		}
	//	if (initValue.isKnown()) {		// FIXME known ShadowPart is not supported
	//		Object resultValue = evaluationVisitor.visitShadowPart(shadowPart);
	//		return context.getKnownValue(resultValue);
	//	}
	//	else {
			SymbolicReason mayBeInvalidReason = initValue.mayBeInvalidReason();
			return context.getUnknownValue(shadowPart, null, mayBeInvalidReason);
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
		SymbolicReason mayBeInvalidReason = null;
		for (@NonNull TupleLiteralPart part : PivotUtil.getOwnedParts(literalExp)) {
			SymbolicValue partValue = symbolicEvaluationEnvironment.symbolicEvaluate(part);
			if (partValue.isInvalid()) {
				return partValue;
			}
			if (partValue.isNull()) {
				return symbolicEvaluationEnvironment.getInvalidValue("null part");
			}
			if (!partValue.isKnown()) {
				isKnown = false;
			}
			if (mayBeInvalidReason == null) {
				mayBeInvalidReason = partValue.mayBeInvalidReason();
				if (mayBeInvalidReason == null) {
					mayBeInvalidReason = partValue.mayBeNullReason();
				}
			}
		}
		if (isKnown) {
			Object resultValue = evaluationVisitor.visitTupleLiteralExp(literalExp);
			return context.getKnownValue(resultValue);
		}
		else {
			return context.getUnknownValue(literalExp, null, mayBeInvalidReason);
		}
	}

	@Override
	public @NonNull SymbolicValue visitTupleLiteralPart(@NonNull TupleLiteralPart part) {
		boolean isKnown = true;
		SymbolicValue partValue = symbolicEvaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedInit(part));
		if (!partValue.isKnown()) {
			isKnown = false;
		}
		if (partValue.isInvalid()) {
			return partValue;
		}
		if (partValue.isNull()) {
			return symbolicEvaluationEnvironment.getInvalidValue("null part");
		}
		SymbolicReason mayBeInvalidReason = partValue.mayBeInvalidReason();
		if (mayBeInvalidReason == null) {
			mayBeInvalidReason = partValue.mayBeNullReason();
		}
		if (isKnown) {
			Object resultValue = evaluationVisitor.visitTupleLiteralPart(part);
			return context.getKnownValue(resultValue);
		}
		else {
			return context.getUnknownValue(part, null, mayBeInvalidReason);
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
			SymbolicValue symbolicValue = context.getUnknownValue(iteratorVariable, SymbolicUtil.isRequiredReason(iteratorVariable), null);
			return symbolicEvaluationEnvironment.setSymbolicValue(iteratorVariable, symbolicValue, "init");
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
