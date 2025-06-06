/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library.iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.IterationManager;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.values.ValueImpl;
import org.eclipse.ocl.pivot.library.AbstractIteration;
import org.eclipse.ocl.pivot.library.LibraryBinaryOperation;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.Value;

/**
 * SelectIteration realizes the Collection::sortedBy() library iteration.
 */
public class SortedByIteration extends AbstractIteration
{
	protected static class SortingValue extends ValueImpl implements Comparator<@NonNull Integer>
	{
		protected final @NonNull CollectionTypeId typeId;
		private final @NonNull Executor executor;
		private final @NonNull LibraryBinaryOperation implementation;
		private final @NonNull ArrayList<Object> rawValues = new ArrayList<>();		// Indexed unsorted User objects
		private final @NonNull ArrayList<Object> rawKeys = new ArrayList<>();		// Index to sortedBy value
		private @Nullable ArrayList<Object> sortedValues = null;					// Sorted elements

		/**
		 * @since 1.1
		 */
		public SortingValue(@NonNull Executor executor, @NonNull CollectionTypeId returnTypeId, @NonNull LibraryBinaryOperation implementation) {
			this.typeId = returnTypeId;
			this.executor = executor;
			this.implementation = implementation;
			// FIXME if IterationManager passed the size we could use more arrays rather than lists.
		}

		@Override
		public @NonNull Object asObject() {
			return sortedValues != null ? sortedValues : rawValues;
		}

		/**
		 * @since 7.0
		 */
		@Override
		public int compare(@NonNull Integer x1, @NonNull Integer x2) {
			assert x1 != x2;
			Object k1 = rawKeys.get(x1);
			Object k2 = rawKeys.get(x2);
			try {
				Object comparison = implementation.evaluate(executor, TypeId.INTEGER, k1, k2);
				int diff = ValueUtil.asIntegerValue(comparison).signum();
				if (diff == 0) {
					diff = x1.compareTo(x2);				// Preserve order
				}
				return diff;
			} catch (InvalidValueException e) {
				throw e;
			} catch (Exception e) {
				throw new InvalidValueException(e);
			} catch (AssertionError e) {
				throw new InvalidValueException(e);
			}
		}

		public @NonNull Value createSortedValue() {
			int size = rawValues.size();
			assert rawKeys.size() == size;
			@NonNull Integer[] indexes = new @NonNull Integer[size];
			for (int i = 0; i < size; i++) {
				indexes[i] = Integer.valueOf(i);
			}
			Arrays.sort(indexes, this);
			ArrayList<Object> sortedValues2 = sortedValues = new ArrayList<>(size);
			for (@NonNull Integer index : indexes) {
				sortedValues2.add(rawValues.get(index));
			}
			CollectionTypeId generalizedId = typeId.getGeneralizedId();
			boolean isUnique = (generalizedId == TypeId.SET) || (generalizedId == TypeId.ORDERED_SET);
			return executor.getIdResolver().createCollectionOfAll(true, isUnique, typeId, sortedValues2);
		}

		@Override
		public @NonNull TypeId getTypeId() {
			return typeId;
		}

		public void put(@Nullable Object iterVal, @Nullable Object comparable) {
			rawValues.add(iterVal);
			rawKeys.add(comparable);
		}

		@Override
		public void toString(@NonNull StringBuilder s, int sizeLimit) {
			s.append(sortedValues!= null ? sortedValues.toString() : rawValues.toString());
		}
	}

	public static final @NonNull SortedByIteration INSTANCE = new SortedByIteration();

	/**
	 * @since 1.1
	 */
	@Override
	public SortedByIteration.@NonNull SortingValue createAccumulatorValue(@NonNull Executor executor, @NonNull TypeId accumulatorTypeId, @NonNull TypeId bodyTypeId) {
		StandardLibrary standardLibrary = executor.getStandardLibrary();
		FlatClass comparableFlatClass = standardLibrary.getOclComparableType().getFlatClass(standardLibrary);
		FlatClass selfFlatClass = standardLibrary.getOclSelfType().getFlatClass(standardLibrary);
		Operation staticOperation = comparableFlatClass.lookupLocalOperation(standardLibrary, LibraryConstants.COMPARE_TO, selfFlatClass);
		if (staticOperation != null) {
			org.eclipse.ocl.pivot.Class bodyType = executor.getIdResolver().getClass(bodyTypeId, null);
			LibraryFeature implementation = bodyType.lookupImplementation(standardLibrary, staticOperation);
			return new SortingValue(executor, (CollectionTypeId)accumulatorTypeId, (LibraryBinaryOperation) implementation);
		}
		throw new InvalidValueException(PivotMessages.UndefinedOperation, String.valueOf(comparableFlatClass) + "::" + LibraryConstants.COMPARE_TO); //$NON-NLS-1$
	}

	/**
	 *	Special case processing for return types based on the source collection element types.
	 *
	 * @since 1.15
	 */
	@Override
	public @Nullable Type resolveReturnType(@NonNull EnvironmentFactory environmentFactory, @NonNull CallExp callExp, @Nullable Type returnType) {
		return resolveCollectionSourceReturnType(environmentFactory, callExp, returnType);
	}

	@Override
	protected @NonNull Object resolveTerminalValue(@NonNull IterationManager iterationManager) {
		SortingValue accumulatorValue = (SortingValue) iterationManager.getAccumulatorValue();
		assert accumulatorValue != null;
		return accumulatorValue.createSortedValue();
	}

	@Override
	protected @Nullable Object updateAccumulator(@NonNull IterationManager iterationManager) {
		Object bodyVal = iterationManager.evaluateBody();
		if (bodyVal == null) {
			throw new InvalidValueException(PivotMessages.UndefinedBody, "sortedBy"); 	// Null body is invalid //$NON-NLS-1$
		}
		Object iterValue = iterationManager.get();
		SortingValue accumulatorValue = (SortingValue) iterationManager.getAccumulatorValue();
		assert accumulatorValue != null;
		accumulatorValue.put(iterValue, bodyVal);
		return CARRY_ON;										// Carry on
	}
}
