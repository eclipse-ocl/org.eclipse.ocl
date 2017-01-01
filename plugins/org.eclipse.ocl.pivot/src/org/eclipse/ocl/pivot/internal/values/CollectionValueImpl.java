/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.values;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.ValuesPackage;

/**
 * @generated NOT
 */
public abstract class CollectionValueImpl extends AbstractCollectionValueImpl
{

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ValuesPackage.Literals.COLLECTION_VALUE;
	}

	private int hashCode = 0;
	protected final @NonNull Collection<? extends Object> elements;		// Using Value instances where necessary to ensure correct equals semantics

	/**
	 * @since 1.3
	 */
	public static @Nullable Map<@NonNull Class<? extends CollectionValue>, @NonNull Integer> collectionClass2count = null;

	protected CollectionValueImpl(@NonNull CollectionTypeId typeId, @NonNull Collection<? extends Object> values) {
		super(typeId);
		this.elements = values;
		assert checkElementsAreValues(values);
		Map<Class<? extends CollectionValue>, Integer> collectionClass2count2 = collectionClass2count;
		if (collectionClass2count2 != null) {
			Class<? extends @NonNull CollectionValueImpl> collectionClass = getClass();
			Integer count = collectionClass2count2.get(collectionClass);
			count = count != null ? count+1 : 1;
			collectionClass2count2.put(collectionClass, count);
		}
	}

	protected boolean checkElementsAreUnique(Iterable<? extends Object> elements) {
		Set<Object> knownElements = new HashSet<Object>();
		for (Object element : elements) {
			assert knownElements.add(element);
		}
		return true;
	}

	/**
	 * Add a value to a working collection, returning true if the working
	 * collection is changed by the addition.
	 * <p>
	 * The default implementation is appropriate for non-unique collections and
	 * must be overridden to support OCL rather than Java uniqueness semantics.
	 */
	//	protected boolean add(C values, Value value) {
	//		return values.add(value);
	//	}

	@Override
	public @NonNull Collection<? extends Object> asCollection() {
		return elements;
	}

	public @NonNull List<? extends Object> asList() {
		return new ArrayList<Object>(elements);
	}

	@Override
	public @NonNull Object asObject() {
		return elements;
	}

	/*	@Override
	public @NonNull DomainType getActualType(@NonNull DomainStandardLibrary standardLibrary) {
		DomainType actualType2 = actualType;
		if (actualType2 == null) {
			DomainType elementType = null;
			for (Object value : elements) {
				assert value != null;
				DomainType valueType;
				if (value instanceof Value) {
					valueType = ((Value)value).getActualType(standardLibrary);
				}
				else {
					valueType = valueFactory.typeOf(value);
				}
				if (elementType == null) {
					elementType = valueType;
				}
				else {
					elementType = elementType.getCommonType(standardLibrary, valueType);
				}
			}
			if (elementType == null) {
				actualType2 = actualType = type;
			}
			else {
				DomainCollectionType containerType = ((DomainCollectionType)type).getContainerType();
				assert containerType != null;
				actualType2 = actualType = standardLibrary.getCollectionType(containerType, elementType, null, null);
			}
		}
		return actualType2;
	} */

	public @NonNull Collection<? extends Object> getObject() {
		return elements;
	}

	/**
	 * @since 1.1
	 */
	@Override
	public final int hashCode() {		// Need hash to be independent of the Set/List/OrderedSet/Bag actually in use as elements
		if (hashCode == 0) {
			synchronized (this) {
				if (hashCode == 0) {
					hashCode = computeCollectionHashCode(isOrdered(), isUnique(), elements);
				}
			}
		}
		return hashCode;
	}

	@Override
	public int intCount(@Nullable Object value) {
		return count(value).intValue();
	}

	@Override
	public int intSize() {
		return elements.size();
	}

	//	@Override
	//	public @NonNull CollectionValue isCollectionValue() {
	//		return this;
	//	}

	@Override
	public @NonNull Iterable<? extends Object> iterable() {
		return elements;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		appendIterable(s, this.iterable(), lengthLimit);
	}
}
