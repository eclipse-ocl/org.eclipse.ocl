/**
 * Copyright (c) 2010, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.IterableType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Iterable Type</b></em>'.
 * @since 1.6
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class IterableTypeImpl extends DataTypeImpl implements IterableType
{
	/**
	 * The number of structural features of the '<em>Iterable Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERABLE_TYPE_FEATURE_COUNT = DataTypeImpl.DATA_TYPE_FEATURE_COUNT + 0;
	/**
	 * The number of operations of the '<em>Iterable Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERABLE_TYPE_OPERATION_COUNT = DataTypeImpl.DATA_TYPE_OPERATION_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IterableTypeImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PivotPackage.Literals.ITERABLE_TYPE;
	}

	@Override
	public @NonNull List<Operation> getOwnedOperations() {
		if (unspecializedElement != null) {
			return ((IterableTypeImpl)unspecializedElement).getOwnedOperations();
		}
		else {
			return super.getOwnedOperations();
		}
	}

	@Override
	public @NonNull List<Property> getOwnedProperties() {
		if (unspecializedElement != null) {
			return ((IterableTypeImpl)unspecializedElement).getOwnedProperties();
		}
		else {
			return super.getOwnedProperties();
		}
	}

} //IterableTypeImpl
