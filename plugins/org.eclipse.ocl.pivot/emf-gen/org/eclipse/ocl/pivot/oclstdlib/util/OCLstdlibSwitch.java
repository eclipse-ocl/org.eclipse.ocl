/*******************************************************************************
 * Copyright (c) 2014, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
/**
 */
package org.eclipse.ocl.pivot.oclstdlib.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.oclstdlib.Boolean_Class;
import org.eclipse.ocl.pivot.oclstdlib.Integer_Class;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage;
import org.eclipse.ocl.pivot.oclstdlib.Real_Class;
import org.eclipse.ocl.pivot.oclstdlib.String_Class;
import org.eclipse.ocl.pivot.oclstdlib.UnlimitedNatural_Class;
import org.eclipse.ocl.pivot.oclstdlib._Lambda_1;
import org.eclipse.ocl.pivot.oclstdlib._Lambda_2;
import org.eclipse.ocl.pivot.oclstdlib._Tuple_1;
import org.eclipse.ocl.pivot.values.Bag;
import org.eclipse.ocl.pivot.values.OrderedSet;

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
 * @see org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage
 * @generated
 */
public class OCLstdlibSwitch<@Nullable T1> extends Switch<T1> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static OCLstdlibPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OCLstdlibSwitch() {
		if (modelPackage == null) {
			modelPackage = OCLstdlibPackage.eINSTANCE;
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
	protected T1 doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case 0: {
				Bag<?> bag = (Bag<?>)theEObject;
				T1 result = caseBag(bag);
				if (result == null) result = caseCollection(bag);
				if (result == null) result = caseOclAny(bag);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 1: {
				Boolean_Class boolean_Class = (Boolean_Class)theEObject;
				T1 result = caseBoolean_Class(boolean_Class);
				if (result == null) result = caseOclAny(boolean_Class);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 2: {
				Collection<?> collection = (Collection<?>)theEObject;
				T1 result = caseCollection(collection);
				if (result == null) result = caseOclAny(collection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 3: {
				Integer_Class integer_Class = (Integer_Class)theEObject;
				T1 result = caseInteger_Class(integer_Class);
				if (result == null) result = caseReal_Class(integer_Class);
				if (result == null) result = caseOclComparable(integer_Class);
				if (result == null) result = caseOclSummable(integer_Class);
				if (result == null) result = caseOclAny(integer_Class);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 4: {
				Map<?, ?> map = (Map<?, ?>)theEObject;
				T1 result = caseMap(map);
				if (result == null) result = caseOclAny(map);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 5: {
				Object oclAny = theEObject;
				T1 result = caseOclAny(oclAny);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 6: {
				Object oclComparable = theEObject;
				T1 result = caseOclComparable(oclComparable);
				if (result == null) result = caseOclAny(oclComparable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 7: {
				Object oclElement = theEObject;
				T1 result = caseOclElement(oclElement);
				if (result == null) result = caseOclAny(oclElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 8: {
				Object oclEnumeration = theEObject;
				T1 result = caseOclEnumeration(oclEnumeration);
				if (result == null) result = caseOclType(oclEnumeration);
				if (result == null) result = caseOclElement(oclEnumeration);
				if (result == null) result = caseOclAny(oclEnumeration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 9: {
				Object oclInvalid = theEObject;
				T1 result = caseOclInvalid(oclInvalid);
				if (result == null) result = caseOclVoid(oclInvalid);
				if (result == null) result = caseOclAny(oclInvalid);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 10: {
				Object oclLambda = theEObject;
				T1 result = caseOclLambda(oclLambda);
				if (result == null) result = caseOclAny(oclLambda);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 11: {
				Object oclMessage = theEObject;
				T1 result = caseOclMessage(oclMessage);
				if (result == null) result = caseOclAny(oclMessage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 12: {
				Object oclSelf = theEObject;
				T1 result = caseOclSelf(oclSelf);
				if (result == null) result = caseOclAny(oclSelf);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 13: {
				Object oclState = theEObject;
				T1 result = caseOclState(oclState);
				if (result == null) result = caseOclAny(oclState);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 14: {
				Object oclStereotype = theEObject;
				T1 result = caseOclStereotype(oclStereotype);
				if (result == null) result = caseOclType(oclStereotype);
				if (result == null) result = caseOclElement(oclStereotype);
				if (result == null) result = caseOclAny(oclStereotype);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 15: {
				Object oclSummable = theEObject;
				T1 result = caseOclSummable(oclSummable);
				if (result == null) result = caseOclAny(oclSummable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 16: {
				Object oclTuple = theEObject;
				T1 result = caseOclTuple(oclTuple);
				if (result == null) result = caseOclAny(oclTuple);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 17: {
				Object oclType = theEObject;
				T1 result = caseOclType(oclType);
				if (result == null) result = caseOclElement(oclType);
				if (result == null) result = caseOclAny(oclType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 18: {
				Object oclVoid = theEObject;
				T1 result = caseOclVoid(oclVoid);
				if (result == null) result = caseOclAny(oclVoid);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 19: {
				Collection<?> orderedCollection = (Collection<?>)theEObject;
				T1 result = caseOrderedCollection(orderedCollection);
				if (result == null) result = caseCollection(orderedCollection);
				if (result == null) result = caseOclAny(orderedCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 20: {
				OrderedSet<?> orderedSet = (OrderedSet<?>)theEObject;
				T1 result = caseOrderedSet(orderedSet);
				if (result == null) result = caseOrderedCollection(orderedSet);
				if (result == null) result = caseUniqueCollection(orderedSet);
				if (result == null) result = caseCollection(orderedSet);
				if (result == null) result = caseOclAny(orderedSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 21: {
				Real_Class real_Class = (Real_Class)theEObject;
				T1 result = caseReal_Class(real_Class);
				if (result == null) result = caseOclComparable(real_Class);
				if (result == null) result = caseOclSummable(real_Class);
				if (result == null) result = caseOclAny(real_Class);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 22: {
				List<?> sequence = (List<?>)theEObject;
				T1 result = caseSequence(sequence);
				if (result == null) result = caseOrderedCollection(sequence);
				if (result == null) result = caseCollection(sequence);
				if (result == null) result = caseOclAny(sequence);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 23: {
				Set<?> set = (Set<?>)theEObject;
				T1 result = caseSet(set);
				if (result == null) result = caseUniqueCollection(set);
				if (result == null) result = caseCollection(set);
				if (result == null) result = caseOclAny(set);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 24: {
				String_Class string_Class = (String_Class)theEObject;
				T1 result = caseString_Class(string_Class);
				if (result == null) result = caseOclComparable(string_Class);
				if (result == null) result = caseOclSummable(string_Class);
				if (result == null) result = caseOclAny(string_Class);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 25: {
				Collection<?> uniqueCollection = (Collection<?>)theEObject;
				T1 result = caseUniqueCollection(uniqueCollection);
				if (result == null) result = caseCollection(uniqueCollection);
				if (result == null) result = caseOclAny(uniqueCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 26: {
				UnlimitedNatural_Class unlimitedNatural_Class = (UnlimitedNatural_Class)theEObject;
				T1 result = caseUnlimitedNatural_Class(unlimitedNatural_Class);
				if (result == null) result = caseOclComparable(unlimitedNatural_Class);
				if (result == null) result = caseOclAny(unlimitedNatural_Class);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 27: {
				Map.Entry<?, ?> _Entry_1 = (Map.Entry<?, ?>)theEObject;
				T1 result = case_Entry_1(_Entry_1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 28: {
				Map.Entry<?, ?> _Entry_2 = (Map.Entry<?, ?>)theEObject;
				T1 result = case_Entry_2(_Entry_2);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 29: {
				_Lambda_1<?, ?> _Lambda_1 = (_Lambda_1<?, ?>)theEObject;
				T1 result = case_Lambda_1(_Lambda_1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 30: {
				_Lambda_2<?, ?> _Lambda_2 = (_Lambda_2<?, ?>)theEObject;
				T1 result = case_Lambda_2(_Lambda_2);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case 31: {
				_Tuple_1<?, ?> _Tuple_1 = (_Tuple_1<?, ?>)theEObject;
				T1 result = case_Tuple_1(_Tuple_1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bag</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bag</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseBag(Bag<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseBoolean_Class(Boolean_Class object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseCollection(Collection<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseInteger_Class(Integer_Class object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Map</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Map</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <K, V> T1 caseMap(Map<K, V> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Any</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Any</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclAny(Object object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Comparable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Comparable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclComparable(Object object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclElement(Object object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Enumeration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Enumeration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclEnumeration(Object object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Invalid</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Invalid</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclInvalid(Object object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Lambda</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Lambda</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclLambda(Object object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Message</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Message</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclMessage(Object object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Self</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Self</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclSelf(Object object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl State</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl State</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclState(Object object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Stereotype</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Stereotype</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclStereotype(Object object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Summable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Summable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclSummable(Object object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Tuple</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Tuple</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclTuple(Object object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclType(Object object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Void</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Void</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseOclVoid(Object object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ordered Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ordered Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseOrderedCollection(Collection<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ordered Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ordered Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseOrderedSet(OrderedSet<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Real Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Real Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseReal_Class(Real_Class object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sequence</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseSequence(List<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseSet(Set<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseString_Class(String_Class object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unique Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unique Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T> T1 caseUniqueCollection(Collection<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unlimited Natural Class</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unlimited Natural Class</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseUnlimitedNatural_Class(UnlimitedNatural_Class object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entry 1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entry 1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <K, V> T1 case_Entry_1(Map.Entry<K, V> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entry 2</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entry 2</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <K, V> T1 case_Entry_2(Map.Entry<K, V> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Lambda 1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Lambda 1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <C, R> T1 case_Lambda_1(_Lambda_1<C, R> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Lambda 2</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Lambda 2</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <C, R> T1 case_Lambda_2(_Lambda_2<C, R> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tuple 1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tuple 1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T, T2> T1 case_Tuple_1(_Tuple_1<T, T2> object) {
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
	public T1 defaultCase(EObject object) {
		return null;
	}

} //OCLstdlibSwitch
