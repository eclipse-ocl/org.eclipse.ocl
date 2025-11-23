/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Template Argument</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A TemplateArgument relates the actual optional type to a formal TemplateParameter by positional equivalence of a TemplateableElement's  hierarchy of TemplateParameters and ownedTemplateArguments.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.TemplateArgument#getActual <em>Actual</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.TemplateArgument#isActualIsRequired <em>Actual Is Required</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.TemplateArgument#getFormal <em>Formal</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.TemplateArgument#getOwningTemplateableElement <em>Owning Templateable Element</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getTemplateArgument()
 * @generated
 */
public interface TemplateArgument
		extends Element {

	/**
	 * Returns the value of the '<em><b>Formal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The formal TemplateParameter that is associated with this TemplateArgument.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Formal</em>' reference.
	 * @see org.eclipse.ocl.pivot.PivotPackage#getTemplateArgument_Formal()
	 * @generated
	 */
	TemplateParameter getFormal();

	/**
	 * Returns the value of the '<em><b>Owning Templateable Element</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.pivot.TemplateableElement#getOwnedTemplateArguments <em>Owned Template Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The TemplateBinding that owns this TemplateArgument.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owning Templateable Element</em>' container reference.
	 * @see #setOwningTemplateableElement(TemplateableElement)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getTemplateArgument_OwningTemplateableElement()
	 * @see org.eclipse.ocl.pivot.TemplateableElement#getOwnedTemplateArguments
	 * @generated
	 */
	TemplateableElement getOwningTemplateableElement();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.TemplateArgument#getOwningTemplateableElement <em>Owning Templateable Element</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Templateable Element</em>' container reference.
	 * @see #getOwningTemplateableElement()
	 * @generated
	 */
	void setOwningTemplateableElement(TemplateableElement value);

	/**
	 * Returns the value of the '<em><b>Actual</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The ParameterableElement that is the actual parameter for this TemplateArgument.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual</em>' reference.
	 * @see #setActual(Type)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getTemplateArgument_Actual()
	 * @generated
	 */
	Type getActual();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.TemplateArgument#getActual <em>Actual</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual</em>' reference.
	 * @see #getActual()
	 * @generated
	 */
	void setActual(Type value);

	/**
	 * Returns the value of the '<em><b>Actual Is Required</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Whether the actual type must be non-null rather than nullable.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Actual Is Required</em>' attribute.
	 * @see #setActualIsRequired(boolean)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getTemplateArgument_ActualIsRequired()
	 * @generated
	 */
	boolean isActualIsRequired();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.TemplateArgument#isActualIsRequired <em>Actual Is Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actual Is Required</em>' attribute.
	 * @see #isActualIsRequired()
	 * @generated
	 */
	void setActualIsRequired(boolean value);

	/**
	 * @since 7.0
	 */
	TemplateableElement getTemplatedElement();

	/**
	 * Return the index of this TEmplateArgument in the parent TemplateableElement ownedTemplateArguments.
	 */
	int getIndex();
} // TemplateArgument
