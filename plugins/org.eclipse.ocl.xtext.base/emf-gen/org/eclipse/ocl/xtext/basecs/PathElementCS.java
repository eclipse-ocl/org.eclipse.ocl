/*******************************************************************************
 * Copyright (c) 2012, 2014 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.basecs;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.utilities.Pivotable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simple Named Element Ref CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.PathElementCS#getElementType <em>Element Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.PathElementCS#getOwningPathName <em>Owning Path Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.PathElementCS#getReferredElement <em>Referred Element</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.PathElementCS#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getPathElementCS()
 * @model superTypes="org.eclipse.ocl.xtext.basecs.ElementCS org.eclipse.ocl.pivot.Pivotable"
 * @generated
 */
public interface PathElementCS extends ElementCS, Pivotable
{
	/**
	 * Returns the value of the '<em><b>Owning Path Name</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getOwnedPathElements <em>Owned Path Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualified Name</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Path Name</em>' container reference.
	 * @see #setOwningPathName(PathNameCS)
	 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getPathElementCS_OwningPathName()
	 * @see org.eclipse.ocl.xtext.basecs.PathNameCS#getOwnedPathElements
	 * @model opposite="ownedPathElements" required="true" transient="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/2015/BaseCS!PathElementCS!owningPathName'"
	 * @generated
	 */
	PathNameCS getOwningPathName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.basecs.PathElementCS#getOwningPathName <em>Owning Path Name</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Path Name</em>' container reference.
	 * @see #getOwningPathName()
	 * @generated
	 */
	void setOwningPathName(PathNameCS value);

	/**
	 * Returns the value of the '<em><b>Referred Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Element</em>' reference.
	 * @see #setReferredElement(Element)
	 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getPathElementCS_ReferredElement()
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/2015/BaseCS!PathElementCS!referredElement'"
	 * @generated
	 */
	Element getReferredElement();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.basecs.PathElementCS#getReferredElement <em>Referred Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referred Element</em>' reference.
	 * @see #getReferredElement()
	 * @generated
	 */
	void setReferredElement(Element value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getPathElementCS_Name()
	 * @model required="true" transient="true" volatile="true" derived="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='/**\n * referredElement.oclAsType(NamedElement).name\n \052/\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.evaluation.@<%org.eclipse.jdt.annotation.NonNull%> Executor executor = <%org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal%>.getExecutor(this);\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.ids.@<%org.eclipse.jdt.annotation.NonNull%> IdResolver idResolver = executor.getIdResolver();\nfinal /*@NonInvalid\052/ org.eclipse.ocl.pivot.@<%org.eclipse.jdt.annotation.NonNull%> Class TYP_NamedElement_0 = idResolver.getClass(<%org.eclipse.ocl.xtext.basecs.BaseCSTables%>.CLSSid_NamedElement, null);\n@SuppressWarnings(\"null\")\nfinal /*@Thrown\052/ org.eclipse.ocl.pivot.@<%org.eclipse.jdt.annotation.NonNull%> Element referredElement = this.getReferredElement();\nfinal /*@Thrown\052/ org.eclipse.ocl.pivot.@<%org.eclipse.jdt.annotation.NonNull%> NamedElement oclAsType = <%org.eclipse.ocl.pivot.utilities.ClassUtil%>.nonNullState((<%org.eclipse.ocl.pivot.NamedElement%>)<%org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation%>.INSTANCE.evaluate(executor, referredElement, TYP_NamedElement_0));\nfinal /*@Thrown\052/ java.lang.@<%org.eclipse.jdt.annotation.Nullable%> String name = oclAsType.getName();\nif (name == null) {\n    throw new <%org.eclipse.ocl.pivot.values.InvalidValueException%>(\"Null body for \\\'basecs::PathElementCS::name\\\'\");\n}\nreturn name;'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.basecs.PathElementCS#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Element Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element Type</em>' reference.
	 * @see #setElementType(EClassifier)
	 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getPathElementCS_ElementType()
	 * @model transient="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented http://www.eclipse.org/ocl/2015/BaseCS!PathElementCS!elementType'"
	 * @generated
	 */
	EClassifier getElementType();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.basecs.PathElementCS#getElementType <em>Element Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element Type</em>' reference.
	 * @see #getElementType()
	 * @generated
	 */
	void setElementType(EClassifier value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	Element basicGetReferredElement();

	boolean isType();

} // SimpleNamedElementRefCS
