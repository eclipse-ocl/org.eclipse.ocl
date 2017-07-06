/*******************************************************************************
 * Copyright (c) 2012, 2017 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.TemplateParameters;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.TemplateableId;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.iterators.AsBagIterator;
import org.eclipse.ocl.pivot.internal.iterators.AsOrderedSetIterator;
import org.eclipse.ocl.pivot.internal.iterators.AsSequenceIterator;
import org.eclipse.ocl.pivot.internal.iterators.AsSetIterator;
import org.eclipse.ocl.pivot.internal.iterators.LazyCollectionValueImpl;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.values.BagImpl;
import org.eclipse.ocl.pivot.internal.values.BagValueImpl;
import org.eclipse.ocl.pivot.internal.values.BigIntegerValueImpl;
import org.eclipse.ocl.pivot.internal.values.CollectionValueImpl;
import org.eclipse.ocl.pivot.internal.values.IntIntegerValueImpl;
import org.eclipse.ocl.pivot.internal.values.IntegerRangeImpl;
import org.eclipse.ocl.pivot.internal.values.JavaObjectValueImpl;
import org.eclipse.ocl.pivot.internal.values.LongIntegerValueImpl;
import org.eclipse.ocl.pivot.internal.values.MapEntryImpl;
import org.eclipse.ocl.pivot.internal.values.MapValueImpl;
import org.eclipse.ocl.pivot.internal.values.NullValueImpl;
import org.eclipse.ocl.pivot.internal.values.OrderedSetImpl;
import org.eclipse.ocl.pivot.internal.values.RangeSequenceValueImpl;
import org.eclipse.ocl.pivot.internal.values.RealValueImpl;
import org.eclipse.ocl.pivot.internal.values.SparseOrderedSetValueImpl;
import org.eclipse.ocl.pivot.internal.values.SparseSequenceValueImpl;
import org.eclipse.ocl.pivot.internal.values.TupleValueImpl;
import org.eclipse.ocl.pivot.internal.values.UnlimitedValueImpl;
import org.eclipse.ocl.pivot.library.UnsupportedOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.types.AbstractInheritance;
import org.eclipse.ocl.pivot.types.ParameterTypesImpl;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IntegerRange;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.LazyCollectionValue;
import org.eclipse.ocl.pivot.values.LazyIterator;
import org.eclipse.ocl.pivot.values.MapEntry;
import org.eclipse.ocl.pivot.values.MapValue;
import org.eclipse.ocl.pivot.values.MutableIterable;
import org.eclipse.ocl.pivot.values.NullValue;
import org.eclipse.ocl.pivot.values.NumberValue;
import org.eclipse.ocl.pivot.values.ObjectValue;
import org.eclipse.ocl.pivot.values.OrderedSet;
import org.eclipse.ocl.pivot.values.RealValue;
import org.eclipse.ocl.pivot.values.TupleValue;
import org.eclipse.ocl.pivot.values.Unlimited;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;
import org.eclipse.ocl.pivot.values.UnlimitedValue;
import org.eclipse.ocl.pivot.values.Value;
import org.eclipse.ocl.pivot.values.ValuesPackage;

/**
 * @since 1.1
 */
public abstract class ValueUtil
{
	private static class EmptyLazyIterator implements LazyIterator
	{
		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public int hasNextCount() {
			return 0;
		}

		@Override
		public Object next() {
			return null;
		}

		@Override
		public void remove() {
		}
	}

	public static @NonNull LazyIterator EMPTY_ITERATOR = new EmptyLazyIterator();

	private static final @NonNull String METAMODEL_NAME_PREFIX = PivotConstants.METAMODEL_NAME + "::";

	public static final @NonNull String NULL_STRING = "null";

	private static final int NEGATIVE_INTEGERS = 256;
	private static final int POSITIVE_INTEGERS = 1025;
	private static final @Nullable IntegerValue @NonNull [] INTEGER_VALUES = new @Nullable IntegerValue[NEGATIVE_INTEGERS + POSITIVE_INTEGERS];

	public static @NonNull Bag<?> EMPTY_BAG = new BagImpl<>();
	public static final @NonNull Set<@Nullable Object> EMPTY_SET = Collections.<@Nullable Object>emptySet();

	@SuppressWarnings("null")
	public static final @NonNull BigInteger INTEGER_MAX_VALUE = BigInteger.valueOf(Integer.MAX_VALUE);
	@SuppressWarnings("null")
	public static final @NonNull BigInteger INTEGER_MIN_VALUE = BigInteger.valueOf(Integer.MIN_VALUE);
	@SuppressWarnings("null")
	public static final @NonNull BigInteger LONG_MAX_VALUE = BigInteger.valueOf(Long.MAX_VALUE);
	@SuppressWarnings("null")
	public static final @NonNull BigInteger LONG_MIN_VALUE = BigInteger.valueOf(Long.MIN_VALUE);
	private static final String maxLongValue = Long.toString(Long.MAX_VALUE);
	private static final int maxLongSize = maxLongValue.length();

	public static final @NonNull Boolean FALSE_VALUE = Boolean.FALSE;
	public static final @NonNull InvalidValueException INVALID_VALUE = new InvalidValueException("invalid");
	public static final @NonNull NullValue NULL_VALUE = new NullValueImpl();
	public static final @NonNull IntegerValue ONE_VALUE = integerValueOf(1);
	public static final @NonNull UnlimitedNaturalValue UNLIMITED_ONE_VALUE = (UnlimitedNaturalValue)ONE_VALUE;
	public static final @NonNull Boolean TRUE_VALUE = Boolean.TRUE;
	public static final @NonNull UnlimitedValue UNLIMITED_VALUE = new UnlimitedValueImpl();
	public static final @NonNull IntegerValue ZERO_VALUE = integerValueOf(0);

	private static boolean allStaticsInitialized = false;

	public static @NonNull CollectionValue asBagValue(@Nullable Object value) {
		if (value instanceof Value) {
			return ((Value)value).asBagValue();
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.BAG_NAME, getTypeName(value));
		}
	}

	public static @NonNull Boolean asBoolean(@Nullable Object value) {
		if (value == Boolean.TRUE) {
			return TRUE_VALUE;
		}
		else if (value == Boolean.FALSE) {
			return FALSE_VALUE;
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.BOOLEAN_NAME, getTypeName(value));
		}
	}

	public static org.eclipse.ocl.pivot.@NonNull Class asClass(@Nullable Object value) {
		if (value instanceof org.eclipse.ocl.pivot.Class) {
			return (org.eclipse.ocl.pivot.Class)value;
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, "Class", getTypeName(value));
		}
	}

	public static @NonNull CollectionType asCollectionType(@Nullable Object value) {
		if (value instanceof CollectionType) {
			return (CollectionType)value;
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.COLLECTION_TYPE_NAME, getTypeName(value));
		}
	}

	public static @NonNull CollectionValue asCollectionValue(@Nullable Object value) {
		if (value instanceof Value) {
			return ((Value)value).asCollectionValue();
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.COLLECTION_NAME, getTypeName(value));
		}
	}

	public static @NonNull Integer asInteger(@Nullable Object value) {
		if (value instanceof Value) {
			return ((Value)value).asInteger();
		}
		else if (value instanceof Number) {
			return ((Number)value).intValue();
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.INTEGER_NAME, getTypeName(value));
		}
	}

	public static @NonNull IntegerValue asIntegerValue(@Nullable Object value) {
		if (value instanceof Value) {
			return ((Value)value).asIntegerValue();
		}
		else if (value instanceof Number) {
			if (value instanceof BigInteger) {
				return integerValueOf((BigInteger)value);
			}
			else {
				return integerValueOf(((Number)value).longValue());
			}
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.INTEGER_NAME, getTypeName(value));
		}
	}

	public static @NonNull MapType asMapType(@Nullable Object value) {
		if (value instanceof MapType) {
			return (MapType)value;
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.MAP_TYPE_NAME, getTypeName(value));
		}
	}

	public static @NonNull MapValue asMapValue(@Nullable Object value) {
		if (value instanceof Value) {
			return ((Value)value).asMapValue();
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.MAP_NAME, getTypeName(value));
		}
	}

	/** @deprecated use Executor */
	@Deprecated
	public static @NonNull EObject asNavigableObject(@Nullable Object value, @NonNull Object navigation, @Nullable Evaluator evaluator) {
		return asNavigableObject(value, navigation, evaluator != null ? getExecutor(evaluator) : null);
	}

	/**
	 * @since 1.1
	 */
	public static @NonNull EObject asNavigableObject(@Nullable Object value, @NonNull Object navigation, @Nullable Executor executor) {

		if (value instanceof Value) {
			return ((Value)value).asNavigableObject();
		}
		else if (value instanceof EObject) {
			return (EObject)value;
		}
		else if (value == null) {
			if (navigation instanceof ElementId) {
				throw new InvalidValueException(PivotMessages.NullNavigation, "source", getElementIdName((ElementId)navigation));
			}
			else {
				String qualifiedName = NameUtil.qualifiedNameFor(navigation);
				int index = qualifiedName.indexOf("::");
				if (index > 0) {
					qualifiedName = qualifiedName.substring(index+2);	// Strip metamodel name to match CG naming
				}
				throw new InvalidValueException(PivotMessages.NullNavigation, "source", qualifiedName/*).replace("'", "''")*/);
			}
		}
		else if ((executor != null) && (value instanceof ElementId)) {
			Object unboxedValue = executor.getIdResolver().unboxedValueOf(value);		// Primarily to unbox and so allow navigation of UML EnumerationLiterals
			if (unboxedValue instanceof EObject) {
				return (EObject) unboxedValue;
			}
		}
		throw new InvalidValueException(PivotMessages.TypedValueRequired, "NavigableObject", getTypeName(value));
	}

	public static @Nullable Object asObject(@Nullable Object value) {
		if (value instanceof Value) {
			return ((Value)value).asObject();
		}
		else {
			return value;
		}
	}

	public static @NonNull CollectionValue asOrderedCollectionValue(@Nullable Object value) {
		if (value instanceof Value) {
			return ((Value)value).asOrderedCollectionValue();
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.ORDERED_COLLECTION_NAME, getTypeName(value));
		}
	}

	public static @NonNull CollectionValue asOrderedSetValue(@Nullable Object value) {
		if (value instanceof Value) {
			return ((Value)value).asOrderedSetValue();
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.ORDERED_SET_NAME, getTypeName(value));
		}
	}

	public static @NonNull RealValue asRealValue(@Nullable Object value) {
		if (value instanceof Value) {
			return ((Value)value).asRealValue();
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.REAL_NAME, getTypeName(value));
		}
	}

	public static @NonNull CollectionValue asSequenceValue(@Nullable Object value) {
		if (value instanceof Value) {
			return ((Value)value).asSequenceValue();
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.SEQUENCE_NAME, getTypeName(value));
		}
	}

	public static @NonNull CollectionValue asSetValue(@Nullable Object value) {
		if (value instanceof Value) {
			return ((Value)value).asSetValue();
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.SET_NAME, getTypeName(value));
		}
	}

	public static @NonNull String asString(@Nullable Object value) {
		if (value instanceof String) {
			return (String)value;
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.STRING_NAME, getTypeName(value));
		}
	}

	public static @NonNull TupleValue asTupleValue(@Nullable Object value) {
		if (value instanceof Value) {
			return ((Value)value).asTupleValue();
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.TUPLE_NAME, getTypeName(value));
		}
	}

	public static @NonNull Type asType(@Nullable Object value) {
		if (value instanceof Type) {
			return (Type)value;
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, "Type", getTypeName(value));
		}
	}

	public static @NonNull CollectionValue asUniqueCollectionValue(@Nullable Object value) {
		if (value instanceof Value) {
			return ((Value)value).asUniqueCollectionValue();
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.UNIQUE_COLLECTION_NAME, getTypeName(value));
		}
	}

	public static @NonNull UnlimitedNaturalValue asUnlimitedNaturalValue(@Nullable Object value) {
		if (value instanceof Value) {
			return ((Value)value).asUnlimitedNaturalValue();
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.UNLIMITED_NATURAL_NAME, getTypeName(value));
		}
	}

	public static Object asValue(Object value) {
		if (value != null) {
			return value;
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.OCL_ANY_NAME, getTypeName(value));
		}
	}

	@SuppressWarnings("null")
	public static @NonNull BigDecimal bigDecimalValueOf(@NonNull Object anObject) {
		if (anObject instanceof BigDecimal) {
			return (BigDecimal)anObject;
		}
		//		else if (anObject instanceof Unlimited) {
		//			return BigDecimal.valueOf(Double.POSITIVE_INFINITY);
		//		}
		else if (anObject instanceof Number) {
			return BigDecimal.valueOf(((Number)anObject).doubleValue());
		}
		else if (anObject instanceof Character) {
			return BigDecimal.valueOf(((Character)anObject).charValue());
		}
		else {
			throw new InvalidValueException(PivotMessages.InvalidReal, anObject);
		}
	}

	@SuppressWarnings("null")
	public static @NonNull BigInteger bigIntegerValueOf(@NonNull Object anObject) {
		if (anObject instanceof BigInteger) {
			return (BigInteger)anObject;
		}
		//		else if (anObject instanceof Unlimited) {
		//			return UNLIMITED_VALUE;
		//		}
		else if (anObject instanceof Number) {
			return BigInteger.valueOf(((Number)anObject).longValue());
		}
		else if (anObject instanceof Character) {
			return BigInteger.valueOf(((Character)anObject).charValue());
		}
		else {
			throw new InvalidValueException(PivotMessages.InvalidInteger, anObject);
		}
	}

	/**
	 * @since 1.1
	 */
	public static byte byteValueOf(@Nullable Object anObject) {
		if (anObject instanceof Number) {
			return ((Number)anObject).byteValue();
		}
		else {
			throw new InvalidValueException(PivotMessages.InvalidInteger, anObject);
		}
	}

	/**
	 * Return an iterable that is lazily populated and which my be re-iterated exploiting cached
	 * values from a first iteration. This provides opportunities for redundant iterations to be skipped.
	 * @since 1.3
	 */
	public static @NonNull Iterable<@Nullable Object> cachedIterable(@NonNull CollectionValue c) {
		if (c instanceof LazyCollectionValue) {
			return ((LazyCollectionValue)c).cachedIterable();
		}
		else {
			return c.iterable();
		}
	}

	@SuppressWarnings("null")
	public static @NonNull Character characterValueOf(@NonNull Object anObject) {
		if (anObject instanceof Character) {
			return (Character)anObject;
		}
		//		else if (anObject instanceof Unlimited) {
		//			return UNLIMITED_VALUE;
		//		}
		else if (anObject instanceof Number) {
			return Character.valueOf((char)((Number)anObject).longValue());
		}
		else {
			throw new InvalidValueException(PivotMessages.InvalidCharacter, anObject);
		}
	}

	/**
	 * @since 1.3
	 */
	public static void checkValid(@Nullable Object value) {
		if (value instanceof InvalidValueException) {
			throw (InvalidValueException) value;
		}
	}

	/**
	 * @since 1.3
	 */
	public static void checkValid(@Nullable Object @NonNull [] values) {
		for (@Nullable Object value : values) {
			if (value instanceof InvalidValueException) {
				throw (InvalidValueException) value;
			}
		}
	}

	/**
	 * @since 1.3
	 */
	public static void checkValid(@NonNull Iterable<@Nullable ? extends Object> values) {
		for (@Nullable Object value : values) {
			if (value instanceof InvalidValueException) {
				throw (InvalidValueException) value;
			}
		}
	}

	/**
	 * @since 1.1
	 */
	public static int computeCollectionHashCode(boolean isOrdered, boolean isUnique, @NonNull Iterable<?> elements) {
		long hash = isUnique ? 0x5555555555555555L : 0x7777777777777777L;
		if (isOrdered) {
			for (Object element : elements) {
				hash *= 5;
				if (element != null) {
					hash += element.hashCode();
				}
			}
		}
		else {
			for (Object element : elements) {
				if (element != null) {
					hash += element.hashCode();
				}
			}
		}
		int hashCode = (int) hash;
		if (hashCode == 0) {
			hashCode = (int) (hash >> 32);
			if (hashCode == 0) {
				hashCode = 0x98765432;
			}
		}
		return hashCode;
	}

	/**
	 * @since 1.3
	 */
	public int computeCollectionHashCode(@NonNull Map<@Nullable Object, @NonNull ? extends Number> mapOfElement2elementCount) {
		long hash = 0x7777777777777777L;
		for (Object element : mapOfElement2elementCount.keySet()) {
			if (element != null) {
				Number count = mapOfElement2elementCount.get(element);
				assert count != null;
				hash += element.hashCode() * count.intValue();
			}
		}
		int hashCode = (int) hash;
		if (hashCode == 0) {
			hashCode = (int) (hash >> 32);
			if (hashCode == 0) {
				hashCode = 0x98765432;
			}
		}
		return hashCode;
	}

	public static @NonNull MutableIterable createBagAccumulatorValue(@NonNull CollectionTypeId collectedId) {
		return new LazyCollectionValueImpl.LazyCollectionValueAccumulator(collectedId);
	}

	public static @NonNull CollectionValue createBagOfEach(@NonNull CollectionTypeId typeId, @Nullable Object @NonNull ... boxedValues) {
		checkValid(boxedValues);
		return new AsBagIterator.FromArray(typeId, boxedValues);
		//		return new BagValueImpl(typeId, BagValueImpl.createBagOfEach(boxedValues));
	}

	public static @NonNull CollectionValue createBagRange(@NonNull CollectionTypeId typeId, @Nullable Object... values) {
		Bag<@Nullable Object> allValues = new BagImpl<>();
		for (Object value : values) {
			if (value instanceof IntegerRange) {
				allValues.addAll((IntegerRange)value);
			}
			else {
				allValues.add(value);
			}
		}
		return new BagValueImpl(typeId, allValues);
	}

	public static @NonNull CollectionValue createBagValue(@NonNull CollectionTypeId typeId, @NonNull Bag<@Nullable ? extends Object> boxedValues) {
		checkValid(boxedValues);
		return new AsBagIterator.FromCollection(typeId, boxedValues);		// FIXME reuse Bag
	}

	public static @NonNull MutableIterable createCollectionAccumulatorValue(@NonNull CollectionTypeId collectedId) {
		CollectionTypeId collectionId = collectedId.getGeneralizedId();
		if (collectionId == TypeId.BAG) {
			return new LazyCollectionValueImpl.LazyCollectionValueAccumulator(collectedId);
		}
		else if (collectionId == TypeId.ORDERED_SET) {
			return new LazyCollectionValueImpl.LazyCollectionValueAccumulator(collectedId);
		}
		else if (collectionId == TypeId.SEQUENCE) {
			return new LazyCollectionValueImpl.LazyCollectionValueAccumulator(collectedId);
		}
		else /*if (collectionId == TypeId.SET)*/ {
			return new LazyCollectionValueImpl.LazyCollectionValueAccumulator(collectedId);
		}
	}

	public static @NonNull InvalidValueException createInvalidValue(@NonNull Exception e) {
		if (e instanceof InvalidValueException) {
			return (InvalidValueException)e;
		}
		else {
			return new InvalidValueException(e);
		}
	}

	public static @NonNull MapValue createMapOfEach(@NonNull MapTypeId typeId, @NonNull MapEntry @NonNull ... mapEntries) {
		return MapValueImpl.createMapValueOfEach(typeId, mapEntries);
	}

	public static @NonNull MapEntry createMapEntry(@NonNull Object key, @NonNull Object value) {
		return new MapEntryImpl(key, value);
	}

	public static @NonNull MapValue createMapValue(@NonNull TypeId keyTypeId, @NonNull TypeId valueTypeId, @NonNull Map<@Nullable Object, @Nullable Object> boxedValues) {
		return new MapValueImpl(TypeId.MAP.getSpecializedId(keyTypeId, valueTypeId), boxedValues);
	}

	public static @NonNull ObjectValue createObjectValue(@NonNull TypeId typeId, @NonNull Object object) {
		return new JavaObjectValueImpl(typeId, object);
	}

	public static @NonNull MutableIterable createOrderedSetAccumulatorValue(@NonNull CollectionTypeId collectedId) {
		return new LazyCollectionValueImpl.LazyCollectionValueAccumulator(collectedId);
	}

	//	public static @NonNull CollectionValue createOrderedSetRange(@NonNull CollectionTypeId typeId, @NonNull IntegerRange range) {
	//		return new RangeOrderedSetValueImpl(typeId, range);
	//	}

	public static @NonNull CollectionValue createOrderedSetOfEach(@NonNull CollectionTypeId typeId, @Nullable Object @NonNull ... boxedValues) {
		checkValid(boxedValues);
		return new AsOrderedSetIterator.FromArray(typeId, boxedValues);
	}

	public static @NonNull CollectionValue createOrderedSetRange(@NonNull CollectionTypeId typeId, @NonNull Object... values) {
		OrderedSet<@Nullable Object> allValues = new OrderedSetImpl<>();
		for (Object value : values) {
			if (value instanceof IntegerRange) {
				allValues.addAll((IntegerRange)value);
			}
			else {
				allValues.add(value);
			}
		}
		return new SparseOrderedSetValueImpl(typeId, allValues);
	}

	public static @NonNull CollectionValue createOrderedSetValue(@NonNull CollectionTypeId typeId, @NonNull Collection<@Nullable ? extends Object> boxedValues) {
		checkValid(boxedValues);
		return new AsOrderedSetIterator.FromCollection(typeId, boxedValues);
	}

	public static @NonNull IntegerRange createRange(@NonNull IntegerValue firstInteger, @NonNull IntegerValue lastInteger) {
		return new IntegerRangeImpl(firstInteger, lastInteger);
	}

	public static @NonNull MutableIterable createSequenceAccumulatorValue(@NonNull CollectionTypeId collectedId) {
		return new LazyCollectionValueImpl.LazyCollectionValueAccumulator(collectedId);
	}

	public static @NonNull CollectionValue createSequenceOfEach(@NonNull CollectionTypeId typeId, @Nullable Object @NonNull ... boxedValues) {
		checkValid(boxedValues);
		return new AsSequenceIterator.FromArray(typeId, boxedValues);
	}

	public static @NonNull CollectionValue createSequenceRange(@NonNull CollectionTypeId typeId, @NonNull IntegerRange range) {
		return new RangeSequenceValueImpl(typeId, range);
	}

	public static @NonNull CollectionValue createSequenceRange(@NonNull CollectionTypeId typeId, @NonNull Object... values) {
		List<@Nullable Object> allValues = new ArrayList<>();
		for (Object value : values) {
			if (value instanceof IntegerRange) {
				allValues.addAll((IntegerRange)value);
			}
			else {
				allValues.add(value);
			}
		}
		return new SparseSequenceValueImpl(typeId, allValues);
	}

	public static @NonNull CollectionValue createSequenceValue(@NonNull CollectionTypeId typeId, @NonNull List<@Nullable ? extends Object> boxedValues) {
		checkValid(boxedValues);
		return new AsSequenceIterator.FromCollection(typeId, boxedValues);
	}

	public static @NonNull MutableIterable createSetAccumulatorValue(@NonNull CollectionTypeId collectedId) {
		return new LazyCollectionValueImpl.LazyCollectionValueAccumulator(collectedId);
	}

	public static @NonNull CollectionValue createSetOfEach(@NonNull CollectionTypeId typeId, @Nullable Object @NonNull ... boxedValues) {
		checkValid(boxedValues);
		return new AsSetIterator.FromArray(typeId, boxedValues);
	}

	public static @NonNull CollectionValue createSetRange(@NonNull CollectionTypeId typeId, @NonNull Object... values) {
		MutableIterable allValues = new LazyCollectionValueImpl.LazyCollectionValueAccumulator(typeId);
		for (Object value : values) {
			if (value instanceof IntegerRange) {
				for (@Nullable Object aValue : (IntegerRange)value) {
					allValues.mutableIncluding(aValue);
				}
			}
			else {
				allValues.mutableIncluding(value);
			}
		}
		return allValues;
	}

	public static @NonNull CollectionValue createSetValue(@NonNull CollectionTypeId typeId, @NonNull Collection<@Nullable ? extends Object> boxedValues) {
		checkValid(boxedValues);
		return new AsSetIterator.FromCollection(typeId, boxedValues);
		//		return new SetValueImpl(typeId, boxedValues);
	}

	public static @NonNull TupleValue createTupleValue(@NonNull TupleTypeId typeId, @NonNull Map<@NonNull ? extends TuplePartId, @Nullable Object> values) {
		return new TupleValueImpl(typeId, values);
	}

	public static @NonNull TupleValue createTupleOfEach(@NonNull TupleTypeId typeId, @Nullable Object... values) {
		return new TupleValueImpl(typeId, values);
	}

	/**
	 * @since 1.1
	 */
	public static double doubleValueOf(@Nullable Object anObject) {
		if (anObject instanceof Number) {
			return ((Number)anObject).doubleValue();
		}
		else {
			throw new InvalidValueException(PivotMessages.InvalidReal, anObject);
		}
	}

	/**
	 * Return the collection after ensuring that any lazy sources have been computed.
	 *
	 * An eager evaluation is needed to ensure that any invalid content is discovered before any element is used.
	 * @since 1.3
	 */
	public static @NonNull CollectionValue eagerCollectionValue(@NonNull CollectionValue c) {
		if (c instanceof LazyCollectionValue) {
			((LazyCollectionValue)c).eagerIterable();
		}
		return c;
	}

	/**
	 * Return an iterable that has been eagerly populated. This inhibits opportunities for
	 * redundant iterations to be skipped but may improve the speed of subsequent iterations.
	 *
	 * An eager evaluation is needed to ensure that any invalid content is discovered before any element is used.
	 * @since 1.3
	 */
	public static @NonNull Iterable<@Nullable Object> eagerIterable(@NonNull CollectionValue c) {
		if (c instanceof LazyCollectionValue) {
			return ((LazyCollectionValue)c).eagerIterable();
		}
		else {
			return c.iterable();
		}
	}

	/**
	 * Return an iterator whose source has been eagerly populated. This inhibits opportunities for
	 * redundant iterations to be skipped but may improve the speed of subsequent iterations.
	 *
	 * An eager evaluation is needed to ensure that any invalid content is discovered before any element is used.
	 * @since 1.3
	 */
	public static @NonNull Iterator<@Nullable Object> eagerIterator(@NonNull CollectionValue c) {
		if (c instanceof LazyCollectionValue) {
			return ((LazyCollectionValue)c).eagerIterable().iterator();
		}
		else {
			return c.iterator();
		}
	}

	/**
	 * @since 1.1
	 */
	public static float floatValueOf(@Nullable Object anObject) {
		if (anObject instanceof Number) {
			return ((Number)anObject).floatValue();
		}
		else {
			throw new InvalidValueException(PivotMessages.InvalidReal, anObject);
		}
	}

	public static @NonNull String getElementIdName(@NonNull ElementId elementId) {
		String name = elementId.toString();
		if (name.startsWith(METAMODEL_NAME_PREFIX)) {
			name = name.substring(METAMODEL_NAME_PREFIX.length());
			assert name != null;
		}
		return name;
	}

	/** @deprecated only used to support deprecated code
	 * @since 1.1*/
	@Deprecated
	public static @NonNull Executor getExecutor(@NonNull Evaluator evaluator) {
		if (evaluator instanceof Executor) {
			return (Executor)evaluator;
		}
		return ((EvaluationVisitor.EvaluationVisitorExtension)evaluator).getExecutor();
	}

	public static String getTypeName(@Nullable Object value) {
		if (value instanceof Boolean) {
			return TypeId.BOOLEAN_NAME;
		}
		else if (value instanceof String) {
			return TypeId.STRING_NAME;
		}
		else if (value instanceof Value) {
			return ((Value) value).getTypeId().getDisplayName();
		}
		else if (value == null) {
			return TypeId.OCL_VOID_NAME;
		}
		return "Object";
	}

	/**
	 * Initialize all static variables in this package to avoid thread contention between conflicting initializations.
	 * <p>
	 * Returns true if this invocation performed the initialization.
	 */
	public static boolean initAllStatics() {
		if (!allStaticsInitialized) {
			synchronized (ValueUtil.class) {
				if (!allStaticsInitialized) {
					allStaticsInitialized = true;
					// org.eclipse.ocl.domain.elements
					ParameterTypesImpl.EMPTY_LIST.getClass();
					TemplateParameters.EMPTY_LIST.getClass();
					// org.eclipse.ocl.domain.evaluation
					ModelManager.NULL.getClass();
					// org.eclipse.ocl.domain.ids
					//					IdManager.getClass();
					TemplateableId.NULL_TEMPLATEABLE_ID_ARRAY.getClass();
					//					BindingsId.EMPTY_LIST.getClass();
					//					TemplateParameterId.NULL_TEMPLATE_PARAMETER_ID_ARRAY.getClass();
					TypeId.INTEGER.getClass();
					// org.eclipse.ocl.domain.types
					AbstractInheritance.initStatics();
					// org.eclipse.ocl.domain.library
					UnsupportedOperation.INSTANCE.getClass();
					// org.eclipse.ocl.domain.messages
					PivotMessages.InvalidOperation.getClass();
					new StatusCodes();
					// org.eclipse.ocl.domain.types
					AbstractInheritance.initStatics();
					// org.eclipse.ocl.domain.utilities
					StringUtil.createNumberFromString("0");
					StandaloneProjectMap.initStatics();
					// org.eclipse.ocl.domain.validation
					LabelUtil.SUBSTITUTION_LABEL_PROVIDER.getClass();
					// org.eclipse.ocl.domain.values
					ValuesPackage.eINSTANCE.getClass();
					// org.eclipse.ocl.domain.values.impl
					CollectionValueImpl.initStatics();
					RealValueImpl.initStatics();
					// org.eclipse.ocl.domain.values.util
					//					new ValuesAdapterFactory();
					//					new ValuesSwitch<Object>();
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @since 1.1
	 */
	public static int intValueOf(@Nullable Object anObject) {
		if (anObject instanceof Number) {
			return ((Number)anObject).intValue();
		}
		else {
			throw new InvalidValueException(PivotMessages.InvalidInteger, anObject);
		}
	}

	public static @NonNull IntegerValue integerValueOf(int value) {
		if (value > -NEGATIVE_INTEGERS) {
			if (value < POSITIVE_INTEGERS) {
				int index = value + NEGATIVE_INTEGERS;
				IntegerValue integerValue = INTEGER_VALUES[index];
				if (integerValue != null) {
					return integerValue;
				}
				synchronized (INTEGER_VALUES) {
					integerValue = INTEGER_VALUES[index];
					if (integerValue != null) {
						return integerValue;
					}
					return INTEGER_VALUES[index] = new IntIntegerValueImpl(value);
				}
			}
		}
		return new IntIntegerValueImpl(value);
	}

	public static @NonNull IntegerValue integerValueOf(long value) {
		if ((Integer.MIN_VALUE <= value) && (value <= Integer.MAX_VALUE)) {
			return integerValueOf((int) value);
		}
		else {
			return new LongIntegerValueImpl(value);
		}
	}

	public static @NonNull IntegerValue integerValueOf(@Nullable BigInteger value) {
		if (value == null) {
			throw new InvalidValueException(PivotMessages.InvalidInteger, value);
		}
		else if (value.signum() >= 0) {
			if (value.compareTo(INTEGER_MAX_VALUE) <= 0) {
				return new IntIntegerValueImpl(value.intValue());
			}
			if (value.compareTo(LONG_MAX_VALUE) <= 0) {
				return new LongIntegerValueImpl(value.longValue());
			}
		}
		else {
			if (value.compareTo(INTEGER_MIN_VALUE) >= 0) {
				return new IntIntegerValueImpl(value.intValue());
			}
			if (value.compareTo(LONG_MIN_VALUE) >= 0) {
				return new LongIntegerValueImpl(value.longValue());
			}
		}
		return new BigIntegerValueImpl(value);
	}

	public static @NonNull IntegerValue integerValueOf(@Nullable Object aValue) {
		if (aValue instanceof BigInteger) {
			return integerValueOf((BigInteger)aValue);
		}
		//		else if (aValue instanceof Unlimited) {
		//			return UNLIMITED_VALUE;
		//		}
		else if (aValue instanceof Number) {
			return integerValueOf(((Number)aValue).longValue());
		}
		else if (aValue instanceof Character) {
			return integerValueOf(((Character)aValue).charValue());
		}
		else if (aValue instanceof IntegerValue) {
			return (IntegerValue)aValue;					// Never happens
		}
		else {
			throw new InvalidValueException(PivotMessages.InvalidInteger, aValue);
		}
	}

	/**
	 * Creates an IntegerValue representation for aValue.
	 * @param aValue the string representation of a (non-negative) integer number
	 * @return the numeric representation
	 */
	public static @NonNull IntegerValue integerValueOf(@NonNull String aValue) {
		try {
			int len = aValue.length();
			if ((len < maxLongSize) || ((len == maxLongSize) && (maxLongValue.compareTo(aValue) >= 0))) {
				@SuppressWarnings("null") @NonNull BigInteger result = BigInteger.valueOf(Long.parseLong(aValue));
				return integerValueOf(result);
			}
			else {
				return integerValueOf(new BigInteger(aValue));
			}
		}
		catch (NumberFormatException e) {
			throw new InvalidValueException(e, PivotMessages.InvalidInteger, aValue);
		}
	}

	public static boolean isBoxed(@Nullable Object object) {
		if (object instanceof NullValue) {
			return false;
		}
		if (object instanceof EnumerationLiteral) {
			return false;
		}
		if (object instanceof EEnumLiteral) {
			return false;
		}
		if (object instanceof Enumerator) {
			return false;
		}
		if ((object instanceof Number) && !(object instanceof RealValue) && !(object instanceof UnlimitedNaturalValue)) {
			return false;
		}
		if ((object instanceof Iterable<?>) && !(object instanceof CollectionValue)) {
			return false;
		}
		return true;
	}

	public static @Nullable CollectionValue isCollectionValue(@Nullable Object value) {
		if ((value instanceof CollectionValue) && !(value instanceof NullValue)) {
			return (CollectionValue)value;
		}
		else {
			return null;
		}
	}

	public static boolean isEcore(Object object) {
		if (object instanceof NullValue) {
			return false;
		}
		if (object instanceof ElementId) {
			return false;
		}
		if (object instanceof RealValue) {
			return false;
		}
		if (object instanceof CollectionValue) {
			return false;
		}
		if ((object instanceof Collection) && !(object instanceof List)) {
			return false;
		}
		return true;
	}

	/**
	 * Return true if aNumber is a known integer representation that can be converted to an IntegerValue.
	 * Returns false for other types including IntegerValue.
	 */
	public static boolean isIntegerNumber(@NonNull Number aNumber) {
		return (aNumber instanceof BigInteger) || (aNumber instanceof Long) || (aNumber instanceof Integer) || (aNumber instanceof Short) || (aNumber instanceof Byte);
	}

	public static IntegerValue isIntegerValue(@Nullable Object value) {
		if ((value instanceof IntegerValue) && !(value instanceof NullValue)) {
			return (IntegerValue)value;
		}
		else {
			return null;
		}
	}

	/**
	 * @since 1.3
	 *
	public static @Nullable InvalidValue isInvalid(@Nullable Object @NonNull [] values) {
		for (@Nullable Object value : values) {
			if (value instanceof InvalidValue) {
				return (InvalidValue) value;
			}
		}
		return null;
	} */

	/**
	 * @since 1.3
	 *
	public static @Nullable InvalidValue isInvalid(@NonNull Iterable<@Nullable ? extends Object> values) {
		for (@Nullable Object value : values) {
			if (value instanceof InvalidValue) {
				return (InvalidValue) value;
			}
		}
		return null;
	} */

	/**
	 * Return true if aNumber is a known floating point representation that can be converted to a RealValue.
	 * Returns false for other types including RealValue.
	 */
	public static boolean isRealNumber(@NonNull Number aNumber) {
		return (aNumber instanceof BigDecimal) || (aNumber instanceof Double) || (aNumber instanceof Float);
	}

	public static boolean isUnboxed(Object object) {
		if (object instanceof NullValue) {
			return false;
		}
		if (object instanceof ElementId) {
			return false;
		}
		if (object instanceof RealValue) {
			return false;
		}
		if (object instanceof CollectionValue) {
			return false;
		}
		return true;
	}

	public static boolean isUnlimited(@Nullable Object value) {
		return (value instanceof UnlimitedValue) && !(value instanceof NullValue);
	}

	/**
	 * Return an iterable that is intended to be iterated at most once.
	 * @since 1.3
	 *
	public static @NonNull Iterable<@Nullable Object> lazyIterable(@NonNull CollectionValue c) {
		if (c instanceof LazyCollectionValue) {
			return ((LazyCollectionValue)c).lazyIterable();
		}
		else {
			return c.iterable();
		}
	} */

	/**
	 * @since 1.3
	 */
	public static @NonNull Iterator<@Nullable Object> lazyIterator(@NonNull CollectionValue c) {
		if (c instanceof LazyCollectionValue) {
			return ((LazyCollectionValue)c).lazyIterator();
		}
		else {
			return c.iterator();
		}
	}

	/**
	 * @since 1.3
	 */
	public static @NonNull Iterator<@Nullable Object> lazyIterator(@NonNull Iterable<?> c) {
		if (c instanceof LazyCollectionValue) {
			return ((LazyCollectionValue)c).lazyIterator();
		}
		else {
			@SuppressWarnings("unchecked")
			Iterator<@Nullable Object> castIterator = (Iterator<@Nullable Object>)c.iterator();
			return castIterator;
		}
	}

	/**
	 * @since 1.1
	 */
	public static long longValueOf(@Nullable Object anObject) {
		if (anObject instanceof Number) {
			return ((Number)anObject).intValue();
		}
		else {
			throw new InvalidValueException(PivotMessages.InvalidInteger, anObject);
		}
	}

	/**
	 * @since 1.1
	 */
	public static @NonNull NumberValue numberValueOf(@NonNull Number aNumber) {
		if (aNumber instanceof RealValue) {
			return (RealValue)aNumber;
		}
		else if (aNumber instanceof BigDecimal) {
			return new RealValueImpl((BigDecimal)aNumber);
		}
		else  {
			return integerValueOf(aNumber);
		}
	}

	public static @NonNull String oclToString(@NonNull Object value) {
		@SuppressWarnings("null") @NonNull String result = value.toString();
		return result;
	}

	public static @NonNull RealValue realValueOf(double value) {
		return new RealValueImpl(value);
	}

	public static @NonNull RealValue realValueOf(@Nullable BigDecimal value) {
		if (value != null) {
			return new RealValueImpl(value);
		}
		else {
			throw new InvalidValueException(PivotMessages.InvalidReal, value);
		}
	}

	public static @NonNull RealValue realValueOf(@Nullable IntegerValue integerValue) {
		if (integerValue == null) {
			throw new InvalidValueException(PivotMessages.InvalidInteger, integerValue);
		}
		try {
			return realValueOf(integerValue.bigDecimalValue());
		} catch (InvalidValueException e) {
			throw new InvalidValueException(e, PivotMessages.InvalidInteger, integerValue);
		}
	}

	public static @NonNull RealValue realValueOf(@Nullable Number aNumber) {
		if (aNumber instanceof RealValue) {
			return (RealValue)aNumber;
		}
		else if (aNumber instanceof BigDecimal) {
			return new RealValueImpl((BigDecimal)aNumber);
		}
		else if (aNumber instanceof BigInteger) {
			return new RealValueImpl(new BigDecimal((BigInteger)aNumber));
		}
		//		else if (aNumber instanceof Unlimited) {
		//			return new RealValueImpl(Double.POSITIVE_INFINITY);
		//		}
		else if (aNumber != null) {
			return new RealValueImpl(aNumber.doubleValue());
		}
		else {
			throw new InvalidValueException(PivotMessages.InvalidReal, aNumber);
		}
	}

	public static @NonNull RealValue realValueOf(@NonNull String aValue) {
		try {
			return new RealValueImpl(new BigDecimal(aValue.trim()));
		}
		catch (NumberFormatException e) {
			throw new InvalidValueException(e, PivotMessages.InvalidReal, aValue);
		}
	}

	/**
	 * @since 1.1
	 */
	public static short shortValueOf(@Nullable Object anObject) {
		if (anObject instanceof Number) {
			return ((Number)anObject).shortValue();
		}
		else {
			throw new InvalidValueException(PivotMessages.InvalidInteger, anObject);
		}
	}

	public static @NonNull String stringValueOf(@Nullable Object aValue) {
		String stringValue = null;
		if (aValue == null) {
			stringValue = NULL_STRING;
		}
		else if (aValue instanceof Value) {							// Needed for Iterable Values such as CollectionValue
			stringValue = ((Value)aValue).toString();
		}
		else if (aValue instanceof String) {
			stringValue = "'" + StringUtil.convertToOCLString((String)aValue) + "'";
		}
		//		else if (aValue instanceof DomainType) {
		//			return String.valueOf(aValue);
		//		}
		//		else if (aValue instanceof DomainEnumerationLiteral) {
		//			return String.valueOf(aValue);
		//		}
		//		else if (aValue instanceof EEnumLiteral) {
		//			return String.valueOf(aValue);
		//		}
		else if ((aValue instanceof EObject) &&
				!((aValue instanceof Element) || (aValue instanceof EEnumLiteral))) {
			stringValue = LabelUtil.getLabel(aValue);
		}
		else if (aValue.getClass().isArray()) {
			throw new UnsupportedOperationException();			// Must invoke IdResolver.boxedValueOf() for aggregates
		}
		else if (aValue instanceof Iterable<?>) {
			throw new UnsupportedOperationException();			// Must invoke IdResolver.boxedValueOf() for aggregates
		}
		else {
			stringValue = String.valueOf(aValue);
		}
		return stringValue != null ? stringValue : "<<null>>";
	}

	public static boolean throwBooleanInvalidValueException(@NonNull String string) {
		throw new InvalidValueException(string);
	}

	/**
	 * Throw an InvalidValueException without subsequent code appearing to be unreachable.
	 */
	public static Object throwInvalidValueException() {
		throw new InvalidValueException("invalid");
	}

	public static int throwUnsupportedCompareTo(@Nullable Object left, @Nullable Object right) {
		throw new InvalidValueException(PivotMessages.UnsupportedCompareTo,
			left != null ? left.getClass().getName() : "null", //$NON-NLS-1$
				right != null ? right.getClass().getName() : "null"); //$NON-NLS-1$
	}

	public static void toString(@Nullable Object value, @NonNull StringBuilder s, int sizeLimit) {
		if (value instanceof Value) {
			((Value)value).toString(s, sizeLimit);
		}
		else if (value instanceof String) {
			s.append("'");
			toStringWithLimit(s, (String)value, sizeLimit);
			s.append("'");
		}
		else if (value != null) {
			toStringWithLimit(s, value.toString(), sizeLimit);
		}
		else {
			toStringWithLimit(s, NULL_STRING, sizeLimit);
		}
	}

	private static void toStringWithLimit(@NonNull StringBuilder s, String string, int sizeLimit) {
		int length = string.length();
		int available = sizeLimit - (length + 1);
		if (length <= available) {
			s.append(string);
		}
		else {
			if (available > 0) {
				s.append(string.substring(0, available));
			}
			s.append("...");
		}
	}

	/**
	 * @since 1.1
	 */
	@SuppressWarnings("unchecked")
	public static @NonNull <T> Iterable<T> typedIterable(Class<T> elementClass, @NonNull CollectionValue collectionValue) {
		return (Iterable<T>)collectionValue;
	}

	public static @NonNull UnlimitedNaturalValue unlimitedNaturalValueOf(@Nullable BigInteger value) {
		return (UnlimitedNaturalValue)integerValueOf(value);
	}

	public static @NonNull UnlimitedNaturalValue unlimitedNaturalValueOf(int value) {
		return (UnlimitedNaturalValue)integerValueOf(value);
	}

	public static @NonNull UnlimitedNaturalValue unlimitedNaturalValueOf(long value) {
		return (UnlimitedNaturalValue)integerValueOf(value);
	}

	public static @NonNull UnlimitedNaturalValue unlimitedNaturalValueOf(@Nullable Object aValue) {
		if (aValue instanceof BigInteger) {
			return new BigIntegerValueImpl((BigInteger)aValue);
		}
		else if (aValue instanceof Unlimited) {
			return UNLIMITED_VALUE;
		}
		else if (aValue instanceof Number) {
			return unlimitedNaturalValueOf(((Number)aValue).longValue());
		}
		else if (aValue instanceof Character) {
			return unlimitedNaturalValueOf(((Character)aValue).charValue());
		}
		else if (aValue instanceof UnlimitedNaturalValue) {
			return (UnlimitedNaturalValue)aValue;					// Never happens
		}
		else {
			throw new InvalidValueException(PivotMessages.InvalidInteger, aValue);
		}
	}

	/**
	 * Creates an IntegerValue representation for aValue.
	 * @param aValue the string representation of a (non-negative) integer number
	 * @return the numeric representation
	 */
	public static @NonNull UnlimitedNaturalValue unlimitedNaturalValueOf(@NonNull String aValue) {
		try {
			int len = aValue.length();
			if ((len == 1) && "*".equals(aValue)) {
				return UNLIMITED_VALUE;
			}
			if ((len < maxLongSize) || ((len == maxLongSize) && (maxLongValue.compareTo(aValue) >= 0))) {
				@SuppressWarnings("null") @NonNull BigInteger result = BigInteger.valueOf(Long.parseLong(aValue));
				return unlimitedNaturalValueOf(result);
			}
			else {
				return unlimitedNaturalValueOf(new BigInteger(aValue));
			}
		}
		catch (NumberFormatException e) {
			throw new InvalidValueException(e, PivotMessages.InvalidInteger, aValue);
		}
	}
}
