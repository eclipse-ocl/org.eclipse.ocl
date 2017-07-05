/*******************************************************************************
 * Copyright (c) 2013, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
/**
 */
package org.eclipse.ocl.pivot.values.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.values.BagValue;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValue;
import org.eclipse.ocl.pivot.values.NullValue;
import org.eclipse.ocl.pivot.values.ObjectValue;
import org.eclipse.ocl.pivot.values.OrderedCollectionValue;
import org.eclipse.ocl.pivot.values.OrderedSetValue;
import org.eclipse.ocl.pivot.values.RealValue;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.TupleValue;
import org.eclipse.ocl.pivot.values.UniqueCollectionValue;
import org.eclipse.ocl.pivot.values.UnlimitedValue;
import org.eclipse.ocl.pivot.values.Value;
import org.eclipse.ocl.pivot.values.ValuesPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.pivot.values.ValuesPackage
 * @generated
 */
public class ValuesSwitch<@Nullable T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ValuesPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValuesSwitch() {
		if (modelPackage == null) {
			modelPackage = ValuesPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ValuesPackage.BAG_VALUE: {
				BagValue bagValue = (BagValue)theEObject;
				T result = caseBagValue(bagValue);
				if (result == null) result = caseCollectionValue(bagValue);
				if (result == null) result = caseValue(bagValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuesPackage.COLLECTION_VALUE: {
				CollectionValue collectionValue = (CollectionValue)theEObject;
				T result = caseCollectionValue(collectionValue);
				if (result == null) result = caseValue(collectionValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuesPackage.INTEGER_VALUE: {
				IntegerValue integerValue = (IntegerValue)theEObject;
				T result = caseIntegerValue(integerValue);
				if (result == null) result = caseRealValue(integerValue);
				if (result == null) result = caseValue(integerValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuesPackage.INVALID_VALUE: {
				InvalidValue invalidValue = (InvalidValue)theEObject;
				T result = caseInvalidValue(invalidValue);
				if (result == null) result = caseNullValue(invalidValue);
				if (result == null) result = caseObjectValue(invalidValue);
				if (result == null) result = caseBagValue(invalidValue);
				if (result == null) result = caseOrderedSetValue(invalidValue);
				if (result == null) result = caseSequenceValue(invalidValue);
				if (result == null) result = caseTupleValue(invalidValue);
				if (result == null) result = caseUnlimitedValue(invalidValue);
				if (result == null) result = caseOrderedCollectionValue(invalidValue);
				if (result == null) result = caseUniqueCollectionValue(invalidValue);
				if (result == null) result = caseIntegerValue(invalidValue);
				if (result == null) result = caseCollectionValue(invalidValue);
				if (result == null) result = caseRealValue(invalidValue);
				if (result == null) result = caseValue(invalidValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuesPackage.NULL_VALUE: {
				NullValue nullValue = (NullValue)theEObject;
				T result = caseNullValue(nullValue);
				if (result == null) result = caseObjectValue(nullValue);
				if (result == null) result = caseBagValue(nullValue);
				if (result == null) result = caseOrderedSetValue(nullValue);
				if (result == null) result = caseSequenceValue(nullValue);
				if (result == null) result = caseTupleValue(nullValue);
				if (result == null) result = caseUnlimitedValue(nullValue);
				if (result == null) result = caseOrderedCollectionValue(nullValue);
				if (result == null) result = caseUniqueCollectionValue(nullValue);
				if (result == null) result = caseIntegerValue(nullValue);
				if (result == null) result = caseCollectionValue(nullValue);
				if (result == null) result = caseRealValue(nullValue);
				if (result == null) result = caseValue(nullValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuesPackage.OBJECT_VALUE: {
				ObjectValue objectValue = (ObjectValue)theEObject;
				T result = caseObjectValue(objectValue);
				if (result == null) result = caseValue(objectValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuesPackage.ORDERED_COLLECTION_VALUE: {
				OrderedCollectionValue orderedCollectionValue = (OrderedCollectionValue)theEObject;
				T result = caseOrderedCollectionValue(orderedCollectionValue);
				if (result == null) result = caseCollectionValue(orderedCollectionValue);
				if (result == null) result = caseValue(orderedCollectionValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuesPackage.ORDERED_SET_VALUE: {
				OrderedSetValue orderedSetValue = (OrderedSetValue)theEObject;
				T result = caseOrderedSetValue(orderedSetValue);
				if (result == null) result = caseOrderedCollectionValue(orderedSetValue);
				if (result == null) result = caseUniqueCollectionValue(orderedSetValue);
				if (result == null) result = caseCollectionValue(orderedSetValue);
				if (result == null) result = caseValue(orderedSetValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuesPackage.REAL_VALUE: {
				RealValue realValue = (RealValue)theEObject;
				T result = caseRealValue(realValue);
				if (result == null) result = caseValue(realValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuesPackage.SEQUENCE_VALUE: {
				SequenceValue sequenceValue = (SequenceValue)theEObject;
				T result = caseSequenceValue(sequenceValue);
				if (result == null) result = caseOrderedCollectionValue(sequenceValue);
				if (result == null) result = caseCollectionValue(sequenceValue);
				if (result == null) result = caseValue(sequenceValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuesPackage.TUPLE_VALUE: {
				TupleValue tupleValue = (TupleValue)theEObject;
				T result = caseTupleValue(tupleValue);
				if (result == null) result = caseValue(tupleValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuesPackage.UNIQUE_COLLECTION_VALUE: {
				UniqueCollectionValue uniqueCollectionValue = (UniqueCollectionValue)theEObject;
				T result = caseUniqueCollectionValue(uniqueCollectionValue);
				if (result == null) result = caseCollectionValue(uniqueCollectionValue);
				if (result == null) result = caseValue(uniqueCollectionValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuesPackage.UNLIMITED_VALUE: {
				UnlimitedValue unlimitedValue = (UnlimitedValue)theEObject;
				T result = caseUnlimitedValue(unlimitedValue);
				//				if (result == null) result = caseIntegerValue(unlimitedValue);
				//				if (result == null) result = caseRealValue(unlimitedValue);
				if (result == null) result = caseValue(unlimitedValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ValuesPackage.VALUE: {
				Value value = (Value)theEObject;
				T result = caseValue(value);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bag Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bag Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBagValue(BagValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collection Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collection Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCollectionValue(CollectionValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerValue(IntegerValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Invalid Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Invalid Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInvalidValue(InvalidValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Null Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Null Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNullValue(NullValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Object Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Object Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseObjectValue(ObjectValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ordered Collection Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ordered Collection Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrderedCollectionValue(OrderedCollectionValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ordered Set Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ordered Set Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrderedSetValue(OrderedSetValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Real Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Real Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRealValue(RealValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sequence Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sequence Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSequenceValue(SequenceValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tuple Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tuple Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTupleValue(TupleValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unique Collection Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unique Collection Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUniqueCollectionValue(UniqueCollectionValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unlimited Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unlimited Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnlimitedValue(UnlimitedValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValue(Value object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //ValuesSwitch
