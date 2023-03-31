/**
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.ocl;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dynamic Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.DynamicType#getOwnedDynamicProperties <em>Owned Dynamic Properties</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getDynamicType()
 * @generated
 */
public interface DynamicType extends org.eclipse.ocl.ocl.Class, DynamicElement
{
	/**
	 * Returns the value of the '<em><b>Owned Dynamic Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.DynamicProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Dynamic Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Dynamic Properties</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getDynamicType_OwnedDynamicProperties()
	 * @generated
	 */
	List<DynamicProperty> getOwnedDynamicProperties();

} // DynamicType
