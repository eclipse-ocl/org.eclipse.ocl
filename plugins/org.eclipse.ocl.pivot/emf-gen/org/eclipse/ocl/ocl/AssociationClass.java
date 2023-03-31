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
 * A representation of the model object '<em><b>Association Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.AssociationClass#getUnownedAttributes <em>Unowned Attributes</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getAssociationClass()
 * @generated
 */
public interface AssociationClass extends org.eclipse.ocl.ocl.Class
{
	/**
	 * Returns the value of the '<em><b>Unowned Attributes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Property}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Property#getAssociationClass <em>Association Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unowned Attributes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unowned Attributes</em>' reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getAssociationClass_UnownedAttributes()
	 * @see org.eclipse.ocl.ocl.Property#getAssociationClass
	 * @generated
	 */
	List<Property> getUnownedAttributes();

} // AssociationClass
