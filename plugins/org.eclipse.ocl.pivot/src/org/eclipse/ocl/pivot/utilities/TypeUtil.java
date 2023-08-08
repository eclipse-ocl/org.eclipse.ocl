/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.CollectionKind;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.ParameterTypes;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameters;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.types.ParameterTypesImpl;
import org.eclipse.ocl.pivot.types.TemplateParametersImpl;

public class TypeUtil
{
	public static @NonNull Object @NonNull [] EMPTY_PARAMETER_TYPES = new @NonNull Object [] {};

	public static @NonNull ParameterTypes createParameterTypes(@NonNull Type @NonNull ... parameterTypes) {
		return new ParameterTypesImpl(parameterTypes);
	}

	public static @NonNull TemplateParameters createTemplateParameters(@NonNull TemplateParameter @NonNull ... parameters) {
		return new TemplateParametersImpl(parameters);
	}

	public static @NonNull TemplateParameters createTemplateParameters(@NonNull List<@NonNull ? extends Type> parameters) {
		return new TemplateParametersImpl(parameters);
	}

	public static CollectionKind getCollectionKind(CollectionType collectionType) {
		if (collectionType instanceof OrderedSetType) {
			return CollectionKind.ORDERED_SET;
		}
		else if (collectionType instanceof SequenceType) {
			return CollectionKind.SEQUENCE;
		}
		else if (collectionType instanceof SetType) {
			return CollectionKind.SET;
		}
		else if (collectionType instanceof BagType) {
			return CollectionKind.BAG;
		}
		else {
			return CollectionKind.COLLECTION;
		}
	}

	public static @NonNull Type @NonNull [] getLambdaParameterTypes(@NonNull LambdaType lambdaType) {
		int iParameter = 0;
		List<? extends Type> ownedParameters = lambdaType.getParameterTypes();
		@NonNull Type @NonNull [] parameterTypes = new @NonNull Type[ownedParameters.size() + 2];
		parameterTypes[iParameter++] = ClassUtil.nonNullState(lambdaType.getContextType());
		parameterTypes[iParameter++] = ClassUtil.nonNullState(lambdaType.getResultType());
		for (Type parameterType : ownedParameters) {
			parameterTypes[iParameter++] = ClassUtil.nonNullState(parameterType);
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
			List<@NonNull ? extends TypedElement> ownedAccumulators = ClassUtil.nullFree(anIteration.getOwnedAccumulators());
			parameterTypes = new @NonNull Type[ownedIterators.size() + ownedAccumulators.size() + ownedParameters.size()];
			for (@NonNull TypedElement ownedIterator : ownedIterators) {
				parameterTypes[iParameter++] = ClassUtil.nonNullState(ownedIterator.getType());
			}
			for (@NonNull TypedElement ownedAccumulator : ownedAccumulators) {
				parameterTypes[iParameter++] = ClassUtil.nonNullState(ownedAccumulator.getType());
			}
		}
		else {
			parameterTypes = new @NonNull Type[ownedParameters.size()];
		}
		for (@NonNull TypedElement ownedParameter : ownedParameters) {
			parameterTypes[iParameter++] = ClassUtil.nonNullState(ownedParameter.getType());
		}
		return parameterTypes;
	}
}
