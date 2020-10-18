/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.examples.xtext.idioms;

import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Comment Segment Support</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * CommentSegmentSupport defines the framework for a user-defined class that supports
 * comment insertion. The derived class must implement getComment to locate the comment.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.CommentSegmentSupport#getEpilogue <em>Epilogue</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.CommentSegmentSupport#getIndentation <em>Indentation</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.CommentSegmentSupport#getPrologue <em>Prologue</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getCommentSegmentSupport()
 * @model abstract="true"
 * @generated
 */
public interface CommentSegmentSupport //extends CustomSegmentSupport
{
	/**
	 * Returns the value of the '<em><b>Epilogue</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Epilogue</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Epilogue</em>' attribute.
	 * @see #setEpilogue(String)
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getCommentSegmentSupport_Epilogue()
	 * @model required="true"
	 * @generated
	 */
	String getEpilogue();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.idioms.CommentSegmentSupport#getEpilogue <em>Epilogue</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Epilogue</em>' attribute.
	 * @see #getEpilogue()
	 * @generated
	 */
	void setEpilogue(String value);

	/**
	 * Returns the value of the '<em><b>Indentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Indentation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indentation</em>' attribute.
	 * @see #setIndentation(String)
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getCommentSegmentSupport_Indentation()
	 * @model required="true"
	 * @generated
	 */
	String getIndentation();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.idioms.CommentSegmentSupport#getIndentation <em>Indentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Indentation</em>' attribute.
	 * @see #getIndentation()
	 * @generated
	 */
	void setIndentation(String value);

	/**
	 * Returns the value of the '<em><b>Prologue</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Prologue</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Prologue</em>' attribute.
	 * @see #setPrologue(String)
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getCommentSegmentSupport_Prologue()
	 * @model required="true"
	 * @generated
	 */
	String getPrologue();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.idioms.CommentSegmentSupport#getPrologue <em>Prologue</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prologue</em>' attribute.
	 * @see #getPrologue()
	 * @generated
	 */
	void setPrologue(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	String getComment(EObject eObject);

} // CommentSegmentSupport
