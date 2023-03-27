/*******************************************************************************
 * Copyright (c) 2015, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library.map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractTernaryOperation;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.MapValue;

/**
 * MapIncludingPairOperation realises the Map::including(key, value) library operation.
 */
public class MapIncludingPairOperation extends AbstractTernaryOperation
{
	public static final @NonNull MapIncludingPairOperation INSTANCE = new MapIncludingPairOperation();

	/** @deprecated use Executor */
	@Deprecated
	@Override
	public @Nullable MapValue evaluate(@NonNull Evaluator evaluator, @NonNull TypeId returnTypeId, @Nullable Object sourceValue, @Nullable Object firstArgumentValue, @Nullable Object secondArgumentValue) {
		return evaluate(getExecutor(evaluator), returnTypeId, sourceValue, firstArgumentValue, secondArgumentValue);
	}

	/**
	 * @since 1.1
	 */
	@Override
	public @NonNull /*@Thrown*/ MapValue evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue, @Nullable Object firstArgumentValue, @Nullable Object secondArgumentValue) {
		MapValue mapValue = asMapValue(sourceValue);
		return mapValue.including((MapTypeId)returnTypeId, firstArgumentValue, secondArgumentValue);
	}

	@Override
	public @Nullable Type resolveReturnType(@NonNull EnvironmentFactory environmentFactory, @NonNull CallExp callExp, @Nullable Type returnType) {
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
			MapType sourceMapType = (MapType)sourceType;
			if (!sourceMapType.isKeysAreNullFree()) {
				keysAreNullFree = false;
			}
			if (!sourceMapType.isValuesAreNullFree()) {
				valuesAreNullFree = false;
			}
		}
		OperationCallExp operationCallExp = (OperationCallExp)callExp;
		OCLExpression keyArgument = PivotUtil.getOwnedArgument(operationCallExp, 0);
		if (!keyArgument.isIsRequired()) {
			keysAreNullFree = false;
		}
		OCLExpression valueArgument = PivotUtil.getOwnedArgument(operationCallExp, 0);
		if (!valueArgument.isIsRequired()) {
			valuesAreNullFree = false;
		}
		return environmentFactory.getStandardLibrary().getMapType(keyType, keysAreNullFree, valueType, valuesAreNullFree);
	}
}
