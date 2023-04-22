/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * AbstractIterationOrOperation realizes shared characteristics of library iterations and operations.
 *
 * @since 1.18
 */
public abstract class AbstractIterationOrOperation extends AbstractFeature implements LibraryIterationOrOperation
{
	/**
	 * Special case processing for return collection types based on the source collection type and multiplicity.
	 */
	protected @Nullable Type resolveCollectionAsCollectionReturnType(@NonNull EnvironmentFactory environmentFactory, @NonNull CallExp callExp, @Nullable Type returnType) {
		if (returnType instanceof CollectionType) {
			OCLExpression ownedSource = callExp.getOwnedSource();
			if (ownedSource != null) {
				Type sourceType = ownedSource.getType();
				CollectionType returnCollectionType = (CollectionType)returnType;
				if (sourceType instanceof CollectionType) {
					CollectionType sourceCollectionType = (CollectionType)sourceType;
					Type elementType = PivotUtil.getElementType(sourceCollectionType);
					CompleteStandardLibrary standardLibrary = environmentFactory.getStandardLibrary();
					CollectionType genericCollectionType = standardLibrary.getCollectionType(returnCollectionType.isOrdered(), returnCollectionType.isUnique());
					returnType = standardLibrary.getCollectionType(genericCollectionType, elementType,
						sourceCollectionType.isIsNullFree(), sourceCollectionType.getLowerValue(), sourceCollectionType.getUpperValue());
				}
			}
		}
		return returnType;
	}

	/**
	 * Special case processing for return types based on the source collection element types.
	 */
	protected boolean resolveCollectionSourceElementReturnNullity(@NonNull EnvironmentFactory environmentFactory, @NonNull CallExp callExp, boolean returnIsRequired) {
		OCLExpression ownedSource = callExp.getOwnedSource();
		if (ownedSource != null) {
			Type sourceType = ownedSource.getType();
			if (sourceType instanceof CollectionType) {
				returnIsRequired = ((CollectionType)sourceType).isIsNullFree();
			}
		}
		return returnIsRequired;
	}

	/**
	 * Special case processing for return collection types based on the return collection element type and source and argument nullities.
	 */
	protected @Nullable Type resolveSourceAndArgumentsAsCollectionReturnType(@NonNull EnvironmentFactory environmentFactory, @NonNull CallExp callExp, @Nullable Type returnType) {
		assert returnType != null;
		CollectionType collectionReturnType = (CollectionType)returnType;
		CollectionType genericCollectionType = PivotUtil.getUnspecializedTemplateableElement(collectionReturnType);
		Type elementType = PivotUtil.getElementType(collectionReturnType);
		boolean isNullFree = true;
		OCLExpression ownedSource = PivotUtil.getOwnedSource(callExp);
		Type sourceType = ownedSource.getType();
		if ((sourceType instanceof CollectionType) && !((CollectionType)sourceType).isIsNullFree()) {
			isNullFree = false;
		}
		for (OCLExpression argument : PivotUtil.getOwnedArguments((OperationCallExp)callExp)) {
			Type type = argument.getType();
			if ((type instanceof CollectionType) && !((CollectionType)type).isIsNullFree()) {
				isNullFree = false;
			}
			else if (!argument.isIsRequired()) {
				isNullFree = false;
			}
		}
		return environmentFactory.getStandardLibrary().getCollectionType(genericCollectionType, elementType, isNullFree, null, null);
	}

	/**
	 * Special case processing for return map types based on the return collection key/value type and source and argument nullities.
	 */
	protected @Nullable Type resolveSourceAndArgumentsAsMapReturnType(@NonNull EnvironmentFactory environmentFactory, @NonNull CallExp callExp, @Nullable Type returnType) {
		assert returnType != null;
		MapType mapReturnType = (MapType)returnType;
		Type keyType = PivotUtil.getKeyType(mapReturnType);
		Type valueType = PivotUtil.getValueType(mapReturnType);
		boolean keysAreNullFree = true;
		boolean valuesAreNullFree = true;
		OCLExpression ownedSource = PivotUtil.getOwnedSource(callExp);
		Type sourceType = ownedSource.getType();
		if ((sourceType instanceof CollectionType) && !((CollectionType)sourceType).isIsNullFree()) {
			keysAreNullFree = false;
			valuesAreNullFree = false;
		}
		else if (sourceType instanceof MapType) {
			if (!((MapType)sourceType).isKeysAreNullFree()) {
				keysAreNullFree = false;
			}
			if (!((MapType)sourceType).isValuesAreNullFree()) {
				valuesAreNullFree = false;
			}
		}
		for (OCLExpression argument : PivotUtil.getOwnedArguments((OperationCallExp)callExp)) {
			Type type = argument.getType();
			if ((type instanceof CollectionType) && !((CollectionType)type).isIsNullFree()) {
				keysAreNullFree = false;
				valuesAreNullFree = false;
			}
			else if (type instanceof MapType) {
				if (!((MapType)type).isKeysAreNullFree()) {
					keysAreNullFree = false;
				}
				if (!((MapType)type).isValuesAreNullFree()) {
					valuesAreNullFree = false;
				}
			}
			else if (!argument.isIsRequired()) {
				keysAreNullFree = false;
				valuesAreNullFree = false;
			}
		}
		return environmentFactory.getStandardLibrary().getMapType(keyType, keysAreNullFree, valueType, valuesAreNullFree);
	}

	/**
	 * Special case processing for return collection types based on the source collection types.
	 */
	protected @Nullable Type resolveSourceAsCollectionReturnType(@NonNull EnvironmentFactory environmentFactory, @NonNull CallExp callExp, @Nullable Type returnType) {
		if (returnType instanceof CollectionType) {
			OCLExpression ownedSource = callExp.getOwnedSource();
			if (ownedSource != null) {
				Type sourceType = ownedSource.getType();
				CollectionType collectionType = (CollectionType)returnType;
				if ((sourceType instanceof CollectionType) && ((CollectionType)sourceType).isIsNullFree() && !collectionType.isIsNullFree()) {
					@SuppressWarnings("null")@NonNull Type elementType = collectionType.getElementType();
					CompleteStandardLibrary standardLibrary = environmentFactory.getStandardLibrary();
					CollectionType genericCollectionType = standardLibrary.getCollectionType(collectionType.isOrdered(), collectionType.isUnique());
					returnType = standardLibrary.getCollectionType(genericCollectionType, elementType,
						true, collectionType.getLowerValue(), collectionType.getUpperValue());
				}
			}
		}
		return returnType;
	}

	/**
	 * Special case processing for return map types based on the source map type and multiplicity.
	 */
	protected @Nullable Type resolveSourceAsMapReturnType(@NonNull EnvironmentFactory environmentFactory, @NonNull CallExp callExp, @Nullable Type returnType) {
		if (returnType instanceof MapType) {
			OCLExpression ownedSource = callExp.getOwnedSource();
			if (ownedSource != null) {
				Type sourceType = ownedSource.getType();
				if (sourceType instanceof MapType) {
					MapType sourceMapType = (MapType)sourceType;
					Type keyType = PivotUtil.getKeyType(sourceMapType);
					Type valueType = PivotUtil.getValueType(sourceMapType);
					CompleteStandardLibrary standardLibrary = environmentFactory.getStandardLibrary();
					returnType = standardLibrary.getMapType(keyType, sourceMapType.isKeysAreNullFree(), valueType, sourceMapType.isValuesAreNullFree());
				}
			}
		}
		return returnType;
	}

	/**
	 * @since 1.15
	 *
	@Override
	public @NonNull SymbolicValue symbolicEvaluate(@NonNull AbstractSymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp) {
		SymbolicValue symbolicPreconditionValue = checkPreconditions(evaluationEnvironment, callExp);
		if (symbolicPreconditionValue != null) {
			return symbolicPreconditionValue;
		}
		SymbolicValue sourceSymbolicValue = evaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedSource(callExp));
		boolean isKnown = sourceSymbolicValue.isKnown();
		Iterable<@NonNull OCLExpression> ownedArguments = PivotUtil.getOwnedArguments(callExp);
		int argumentsSize = Iterables.size(ownedArguments);
		List<@NonNull SymbolicValue> argumentSymbolicValues = new ArrayList<@NonNull SymbolicValue>(argumentsSize);
		for (@NonNull OCLExpression argument : ownedArguments) {
			SymbolicValue argumentSymbolicValue = evaluationEnvironment.symbolicEvaluate(argument);
			if (!argumentSymbolicValue.isKnown()) {
				isKnown = false;
			}
			argumentSymbolicValues.add(argumentSymbolicValue);
		}
		if (isKnown) {
			@Nullable Object[] sourceAndArgumentValues = new @Nullable Object[1+argumentsSize];
			sourceAndArgumentValues[0] = ((SymbolicKnownValue)sourceSymbolicValue).getValue();
			for (int i = 0; i < argumentsSize; i++) {
				sourceAndArgumentValues[i+1] = ((SymbolicKnownValue)argumentSymbolicValues.get(i)).getValue();
			}
			Object result = ((LibraryOperationExtension2)this).evaluate(evaluationEnvironment.getExecutor(), callExp, sourceAndArgumentValues);
			return evaluationEnvironment.getKnownValue(result);
		}
		else {
			return createChildSymbolicValue(evaluationEnvironment, callExp, sourceSymbolicValue, argumentSymbolicValues);
		}
	} */
}
