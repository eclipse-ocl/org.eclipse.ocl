/*******************************************************************************
 * Copyright (c) 2011, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LambdaParameter;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

public class TypeUtil
{
	public static boolean conformsToCollectionType(@NonNull StandardLibrary standardLibrary, @NonNull CollectionType firstCollectionType, @NonNull CollectionType secondCollectionType) {
		Type firstContainerType = firstCollectionType.getContainerType();
		Type secondContainerType = secondCollectionType.getContainerType();
		if (firstContainerType != secondContainerType) {
			CompleteInheritance firstInheritance = firstContainerType.getInheritance(standardLibrary);
			CompleteInheritance secondInheritance = secondContainerType.getInheritance(standardLibrary);
			if (!secondInheritance.isSuperInheritanceOf(firstInheritance)) {
				return false;
			}
		}
		boolean firstIsNullFree = firstCollectionType.isIsNullFree();
		boolean secondIsNullFree = secondCollectionType.isIsNullFree();
		if (firstIsNullFree && !secondIsNullFree) {
			return false;
		}
		Type firstElementType = firstCollectionType.getElementType();
		Type secondElementType = secondCollectionType.getElementType();
		if (firstElementType != secondElementType) {
			if ((firstElementType == null) || (secondElementType == null)) {
				return false;
			}
			if (!firstElementType.conformsTo(standardLibrary, secondElementType)) {
				return false;
			}
		}
		IntegerValue firstLower = firstCollectionType.getLowerValue();
		IntegerValue secondLower = secondCollectionType.getLowerValue();
		if (firstLower.compareTo(secondLower) < 0) {
			return false;
		}
		UnlimitedNaturalValue firstUpper = firstCollectionType.getUpperValue();
		UnlimitedNaturalValue secondUpper = secondCollectionType.getUpperValue();
		if (firstUpper.compareTo(secondUpper) > 0) {
			return false;
		}
		return true;
	}

	public static boolean conformsToLambdaType(@NonNull StandardLibrary standardLibrary, @NonNull LambdaType firstLambdaType, @NonNull LambdaType secondLambdaType) {
		throw new UnsupportedOperationException();
	}

	public static boolean conformsToMapType(@NonNull StandardLibrary standardLibrary, @NonNull MapType firstMapType, @NonNull MapType secondMapType) {
		//		Type firstContainerType = firstMapType.getContainerType();
		//		Type secondContainerType = secondMapType.getContainerType();
		//		if (firstContainerType != secondContainerType) {
		//			CompleteInheritance firstInheritance = firstContainerType.getInheritance(standardLibrary);
		//			CompleteInheritance secondInheritance = secondContainerType.getInheritance(standardLibrary);
		//			if (!secondInheritance.isSuperInheritanceOf(firstInheritance)) {
		//				return false;
		//			}
		//		}
		boolean firstIsKeysAreNullFree = firstMapType.isKeysAreNullFree();
		boolean secondIsKeysAreNullFree = secondMapType.isKeysAreNullFree();
		if (firstIsKeysAreNullFree && !secondIsKeysAreNullFree) {
			return false;
		}
		boolean firstIsValuesAreNullFree = firstMapType.isValuesAreNullFree();
		boolean secondIsValuesAreNullFree = secondMapType.isValuesAreNullFree();
		if (firstIsValuesAreNullFree && !secondIsValuesAreNullFree) {
			return false;
		}
		Type firstKeyType = firstMapType.getKeyType();
		Type secondKeyType = secondMapType.getKeyType();
		if (firstKeyType != secondKeyType) {
			if ((firstKeyType == null) || (secondKeyType == null)) {
				return false;
			}
			if (!firstKeyType.conformsTo(standardLibrary, secondKeyType)) {
				return false;
			}
		}
		Type firstValueType = firstMapType.getValueType();
		Type secondValueType = secondMapType.getValueType();
		if (firstValueType != secondValueType) {
			if ((firstValueType == null) || (secondValueType == null)) {
				return false;
			}
			if (!firstValueType.conformsTo(standardLibrary, secondValueType)) {
				return false;
			}
		}
		return true;
	}

	public static boolean conformsToTupleType(@NonNull StandardLibrary standardLibrary, @NonNull TupleType firstTupleType, @NonNull TupleType secondTupleType) {
		List<@NonNull Property> firstParts = PivotUtil.getOwnedPropertiesList(firstTupleType);
		List<@NonNull Property> secondParts = PivotUtil.getOwnedPropertiesList(secondTupleType);
		int iSize = firstParts.size();
		if (iSize != secondParts.size()) {
			return false;
		}
		for (int i = 0; i < iSize; i++) {
			Property firstPart = firstParts.get(i);
			Property secondPart = secondParts.get(i);
			boolean firstIsRequired = firstPart.isIsRequired();
			boolean secondIsRequired = secondPart.isIsRequired();
			if (!firstIsRequired && secondIsRequired) {
				return false;
			}
			Type firstElementType = firstPart.getType();
			Type secondElementType = secondPart.getType();
			if (firstElementType != secondElementType) {
				if ((firstElementType == null) || (secondElementType == null)) {
					return false;
				}
				if (!firstElementType.conformsTo(standardLibrary, secondElementType)) {
					return false;
				}
			}
		}
		return true;
	/*	if (isEqualToTupleType(standardLibrary, firstTupleType, secondTupleType)) {
			return true;
		}
		CompleteInheritance firstInheritance = firstTupleType.getInheritance(standardLibrary);
		CompleteInheritance secondInheritance = secondTupleType.getInheritance(standardLibrary);
		return firstInheritance.isSuperInheritanceOf(secondInheritance); */
	}

	public static @NonNull Type @NonNull [] getLambdaParameterTypes(@NonNull LambdaType lambdaType) {
		int iParameter = 0;
		List<LambdaParameter> ownedParameters = lambdaType.getOwnedParameters();
		@NonNull Type @NonNull [] parameterTypes = new @NonNull Type[ownedParameters.size() + 2];
		parameterTypes[iParameter++] = ClassUtil.requireNonNull(lambdaType.getContextType());
		parameterTypes[iParameter++] = ClassUtil.requireNonNull(lambdaType.getResultType());
		for (LambdaParameter parameter : ownedParameters) {
			parameterTypes[iParameter++] = ClassUtil.requireNonNull(parameter.getType());
		}
		return parameterTypes;
	}

	public static @NonNull Type @NonNull [] getOperationParameterTypes(@NonNull Operation anOperation) {
		@NonNull Type @NonNull [] parameterTypes;
		int iParameter = 0;
		List<@NonNull ? extends TypedElement> ownedParameters = ClassUtil.nullFree(anOperation.getOwnedParameters());
		if (anOperation instanceof Iteration) {
			Iteration anIteration = (Iteration)anOperation;
			List<@NonNull ? extends TypedElement> ownedIterators = ClassUtil.nullFree(anIteration.getOwnedIterators());
			TypedElement ownedAccumulator = anIteration.getOwnedAccumulator();
			parameterTypes = new @NonNull Type[ownedIterators.size() + (ownedAccumulator != null ? 1 : 0) + ownedParameters.size()];
			for (@NonNull TypedElement ownedIterator : ownedIterators) {
				parameterTypes[iParameter++] = ClassUtil.requireNonNull(ownedIterator.getType());
			}
			if (ownedAccumulator != null) {
				parameterTypes[iParameter++] = ClassUtil.requireNonNull(ownedAccumulator.getType());
			}
		}
		else {
			parameterTypes = new @NonNull Type[ownedParameters.size()];
		}
		for (@NonNull TypedElement ownedParameter : ownedParameters) {
			parameterTypes[iParameter++] = ClassUtil.requireNonNull(ownedParameter.getType());
		}
		return parameterTypes;
	}

	public static boolean isEqualToCollectionType(@NonNull StandardLibrary standardLibrary, @NonNull CollectionType firstCollectionType, @NonNull CollectionType secondCollectionType) {
		Type firstContainerType = firstCollectionType.getContainerType();
		Type secondContainerType = secondCollectionType.getContainerType();
		if ((firstContainerType != secondContainerType) && !firstContainerType.isEqualToUnspecializedType(standardLibrary, secondContainerType)) {
			return false;
		}
		Type firstElementType = firstCollectionType.getElementType();
		Type secondElementType = secondCollectionType.getElementType();
		if (firstElementType != secondElementType) {
			if ((firstElementType == null) || (secondElementType == null)) {
				return false;
			}
			if (!firstElementType.isEqualTo(standardLibrary, secondElementType)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isEqualToMapType(@NonNull StandardLibrary standardLibrary, @NonNull MapType firstMapType, @NonNull MapType secondMapType) {
		//		Type firstContainerType = firstMapType.getContainerType();
		//		Type secondContainerType = secondMapType.getContainerType();
		//		if ((firstContainerType != secondContainerType) && !firstContainerType.isEqualToUnspecializedType(standardLibrary, secondContainerType)) {
		//			return false;
		//		}
		Type firstKeyType = firstMapType.getKeyType();
		Type secondKeyType = secondMapType.getKeyType();
		if (firstKeyType != secondKeyType) {
			if ((firstKeyType == null) || (secondKeyType == null)) {
				return false;
			}
			if (!firstKeyType.isEqualTo(standardLibrary, secondKeyType)) {
				return false;
			}
		}
		Type firstValueType = firstMapType.getValueType();
		Type secondValueType = secondMapType.getValueType();
		if (firstValueType != secondValueType) {
			if ((firstValueType == null) || (secondValueType == null)) {
				return false;
			}
			if (!firstValueType.isEqualTo(standardLibrary, secondValueType)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isEqualToTupleType(@NonNull StandardLibrary standardLibrary, @NonNull TupleType firstTupleType, @NonNull TupleType secondTupleType) {
		TypeId firstParts = firstTupleType.getTypeId();
		TypeId secondParts = secondTupleType.getTypeId();
		return firstParts == secondParts;
	}
}
