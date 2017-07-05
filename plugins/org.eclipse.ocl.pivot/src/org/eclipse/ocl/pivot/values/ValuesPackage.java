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
package org.eclipse.ocl.pivot.values;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.pivot.values.ValuesFactory
 * @generated
 */
public interface ValuesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "values";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ocl/2015/Values";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "values";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ValuesPackage eINSTANCE = org.eclipse.ocl.pivot.internal.values.ValuesPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.pivot.values.impl.ValueImpl <em>Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.pivot.values.impl.ValueImpl
	 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getValue()
	 * @generated
	 */
	int VALUE = 15;

	/**
	 * The number of structural features of the '<em>Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.pivot.values.impl.CollectionValueImpl <em>Collection Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.pivot.values.impl.CollectionValueImpl
	 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getCollectionValue()
	 * @generated
	 */
	int COLLECTION_VALUE = 1;

	/**
	 * The number of structural features of the '<em>Collection Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLECTION_VALUE_FEATURE_COUNT = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Bag Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BAG_VALUE_FEATURE_COUNT = COLLECTION_VALUE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.pivot.values.impl.ObjectValueImpl <em>Object Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.pivot.values.impl.ObjectValueImpl
	 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getObjectValue()
	 * @generated
	 */
	int OBJECT_VALUE = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.pivot.values.impl.IntegerValueImpl <em>Integer Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.pivot.values.impl.IntegerValueImpl
	 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getIntegerValue()
	 * @generated
	 */
	int INTEGER_VALUE = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.pivot.values.impl.NullValueImpl <em>Null Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.pivot.values.impl.NullValueImpl
	 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getNullValue()
	 * @generated
	 */
	int NULL_VALUE = 5;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.pivot.values.impl.RealValueImpl <em>Real Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.pivot.values.impl.RealValueImpl
	 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getRealValue()
	 * @generated
	 */
	int REAL_VALUE = 9;

	/**
	 * The number of structural features of the '<em>Real Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REAL_VALUE_FEATURE_COUNT = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Integer Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_VALUE_FEATURE_COUNT = REAL_VALUE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.pivot.values.impl.MapValueImpl <em>Map Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.pivot.values.impl.MapValueImpl
	 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getMapValue()
	 * @generated
	 */
	int MAP_VALUE = 4;

	/**
	 * The number of structural features of the '<em>Object Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_VALUE_FEATURE_COUNT = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Null Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL_VALUE_FEATURE_COUNT = OBJECT_VALUE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.pivot.values.impl.InvalidValueImpl <em>Invalid Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.pivot.values.impl.InvalidValueImpl
	 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getInvalidValue()
	 * @generated
	 */
	int INVALID_VALUE = 3;

	/**
	 * The number of structural features of the '<em>Invalid Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_VALUE_FEATURE_COUNT = NULL_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Map Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_VALUE_FEATURE_COUNT = VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Ordered Collection Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_COLLECTION_VALUE_FEATURE_COUNT = COLLECTION_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Ordered Set Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ORDERED_SET_VALUE_FEATURE_COUNT = ORDERED_COLLECTION_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Sequence Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_VALUE_FEATURE_COUNT = ORDERED_COLLECTION_VALUE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.pivot.values.impl.SetValueImpl <em>Set Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.pivot.values.impl.SetValueImpl
	 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getSetValue()
	 * @generated
	 */
	int SET_VALUE = 11;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.pivot.values.impl.TupleValueImpl <em>Tuple Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.pivot.values.impl.TupleValueImpl
	 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getTupleValue()
	 * @generated
	 */
	int TUPLE_VALUE = 12;

	/**
	 * The number of structural features of the '<em>Unique Collection Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIQUE_COLLECTION_VALUE_FEATURE_COUNT = COLLECTION_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Set Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SET_VALUE_FEATURE_COUNT = UNIQUE_COLLECTION_VALUE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tuple Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUPLE_VALUE_FEATURE_COUNT = VALUE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.pivot.values.impl.UnlimitedValueImpl <em>Unlimited Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.pivot.values.impl.UnlimitedValueImpl
	 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getUnlimitedValue()
	 * @generated
	 */
	int UNLIMITED_VALUE = 14;

	/**
	 * The number of structural features of the '<em>Unlimited Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNLIMITED_VALUE_FEATURE_COUNT = INTEGER_VALUE_FEATURE_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.values.CollectionValue <em>Collection Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Value</em>'.
	 * @see org.eclipse.ocl.pivot.values.CollectionValue
	 * @generated
	 */
	EClass getCollectionValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.values.IntegerValue <em>Integer Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Value</em>'.
	 * @see org.eclipse.ocl.pivot.values.IntegerValue
	 * @generated
	 */
	EClass getIntegerValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.values.InvalidValue <em>Invalid Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invalid Value</em>'.
	 * @see org.eclipse.ocl.pivot.values.InvalidValue
	 * @generated
	 */
	EClass getInvalidValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.values.MapValue <em>Map Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map Value</em>'.
	 * @see org.eclipse.ocl.pivot.values.MapValue
	 * @generated
	 */
	EClass getMapValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.values.NullValue <em>Null Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Null Value</em>'.
	 * @see org.eclipse.ocl.pivot.values.NullValue
	 * @generated
	 */
	EClass getNullValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.values.ObjectValue <em>Object Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Object Value</em>'.
	 * @see org.eclipse.ocl.pivot.values.ObjectValue
	 * @generated
	 */
	EClass getObjectValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.values.RealValue <em>Real Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Real Value</em>'.
	 * @see org.eclipse.ocl.pivot.values.RealValue
	 * @generated
	 */
	EClass getRealValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.values.TupleValue <em>Tuple Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tuple Value</em>'.
	 * @see org.eclipse.ocl.pivot.values.TupleValue
	 * @generated
	 */
	EClass getTupleValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.values.UnlimitedValue <em>Unlimited Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unlimited Value</em>'.
	 * @see org.eclipse.ocl.pivot.values.UnlimitedValue
	 * @generated
	 */
	EClass getUnlimitedValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.values.Value <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value</em>'.
	 * @see org.eclipse.ocl.pivot.values.Value
	 * @generated
	 */
	EClass getValue();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ValuesFactory getValuesFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.values.impl.CollectionValueImpl <em>Collection Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.values.impl.CollectionValueImpl
		 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getCollectionValue()
		 * @generated
		 */
		EClass COLLECTION_VALUE = eINSTANCE.getCollectionValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.values.impl.IntegerValueImpl <em>Integer Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.values.impl.IntegerValueImpl
		 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getIntegerValue()
		 * @generated
		 */
		EClass INTEGER_VALUE = eINSTANCE.getIntegerValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.values.impl.InvalidValueImpl <em>Invalid Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.values.impl.InvalidValueImpl
		 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getInvalidValue()
		 * @generated
		 */
		EClass INVALID_VALUE = eINSTANCE.getInvalidValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.values.impl.MapValueImpl <em>Map Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.values.impl.MapValueImpl
		 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getMapValue()
		 * @generated
		 */
		EClass MAP_VALUE = eINSTANCE.getMapValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.values.impl.NullValueImpl <em>Null Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.values.impl.NullValueImpl
		 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getNullValue()
		 * @generated
		 */
		EClass NULL_VALUE = eINSTANCE.getNullValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.values.impl.ObjectValueImpl <em>Object Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.values.impl.ObjectValueImpl
		 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getObjectValue()
		 * @generated
		 */
		EClass OBJECT_VALUE = eINSTANCE.getObjectValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.values.impl.RealValueImpl <em>Real Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.values.impl.RealValueImpl
		 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getRealValue()
		 * @generated
		 */
		EClass REAL_VALUE = eINSTANCE.getRealValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.values.impl.TupleValueImpl <em>Tuple Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.values.impl.TupleValueImpl
		 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getTupleValue()
		 * @generated
		 */
		EClass TUPLE_VALUE = eINSTANCE.getTupleValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.values.impl.UnlimitedValueImpl <em>Unlimited Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.values.impl.UnlimitedValueImpl
		 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getUnlimitedValue()
		 * @generated
		 */
		EClass UNLIMITED_VALUE = eINSTANCE.getUnlimitedValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.values.impl.ValueImpl <em>Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.values.impl.ValueImpl
		 * @see org.eclipse.ocl.pivot.values.impl.ValuesPackageImpl#getValue()
		 * @generated
		 */
		EClass VALUE = eINSTANCE.getValue();

	}

} //ValuesPackage
