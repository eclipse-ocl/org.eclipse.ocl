/**
 * <copyright>
 * 
 * Copyright (c) 2015, 2019 Willink Transformations and others.
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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CG Template Parameter Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A CGTemplateParameterExp is a non-constant expression that references a type's templateParameter.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp#getTemplateableElement <em>Templateable Element</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp#getIndex <em>Index</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGTemplateParameterExp()
 * @generated
 */
public interface CGTemplateParameterExp extends CGValuedElement {
	/**
	 * Returns the value of the '<em><b>Templateable Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The CGExecutorType/Operation whose Class/Operation has template parameters.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Templateable Element</em>' reference.
	 * @see #setTemplateableElement(CGValuedElement)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGTemplateParameterExp_TemplateableElement()
	 * @generated
	 */
	CGValuedElement getTemplateableElement();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp#getTemplateableElement <em>Templateable Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Templateable Element</em>' reference.
	 * @see #getTemplateableElement()
	 * @generated
	 */
	void setTemplateableElement(CGValuedElement value);

	/**
	 * Returns the value of the '<em><b>Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The index of the TemplateParameter in the executorType's signature.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Index</em>' attribute.
	 * @see #setIndex(int)
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGTemplateParameterExp_Index()
	 * @generated
	 */
	int getIndex();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp#getIndex <em>Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index</em>' attribute.
	 * @see #getIndex()
	 * @generated
	 */
	void setIndex(int value);

} // CGTemplateParameterExp
