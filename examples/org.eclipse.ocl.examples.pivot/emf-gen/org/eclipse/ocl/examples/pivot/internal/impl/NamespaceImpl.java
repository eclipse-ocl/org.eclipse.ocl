/**
 * <copyright>
 *
 * Copyright (c) 2010 E.D.Willink and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: NamespaceImpl.java,v 1.1.2.1 2010/10/01 13:49:57 ewillink Exp $
 */
package org.eclipse.ocl.examples.pivot.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ocl.examples.pivot.Namespace;
import org.eclipse.ocl.examples.pivot.PivotPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Namespace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class NamespaceImpl extends NamedElementImpl implements Namespace
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NamespaceImpl()
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
		return PivotPackage.Literals.NAMESPACE;
	}

} //NamespaceImpl
