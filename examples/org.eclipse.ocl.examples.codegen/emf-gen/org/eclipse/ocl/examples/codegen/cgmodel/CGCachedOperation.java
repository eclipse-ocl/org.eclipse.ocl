/**
 * <copyright>
 * 
 * Copyright (c) 2015, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.ocl.examples.codegen.cgmodel;

import java.util.List;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CG Cached Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A native operation that must be called using the native calling convention.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation#getFinalOperations <em>Final Operations</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation#getVirtualOperations <em>Virtual Operations</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGCachedOperation()
 * @generated
 */
public interface CGCachedOperation extends CGOperation {

	/**
	 * Returns the value of the '<em><b>Final Operations</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation#getVirtualOperations <em>Virtual Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Final Operations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Final Operations</em>' reference list.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGCachedOperation_FinalOperations()
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation#getVirtualOperations
	 * @generated
	 */
	List<CGCachedOperation> getFinalOperations();

	/**
	 * Returns the value of the '<em><b>Virtual Operations</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation#getFinalOperations <em>Final Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Virtual Operations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Virtual Operations</em>' reference list.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGCachedOperation_VirtualOperations()
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation#getFinalOperations
	 * @generated
	 */
	List<CGCachedOperation> getVirtualOperations();
} // CGCachedOperation
