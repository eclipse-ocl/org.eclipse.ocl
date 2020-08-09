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
package org.eclipse.ocl.xtext.base.cs2text.idioms.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.idioms.CommentSegmentSupport;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Comment Segment Support</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.CommentSegmentSupportImpl#getEpilogue <em>Epilogue</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.CommentSegmentSupportImpl#getIndentation <em>Indentation</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.impl.CommentSegmentSupportImpl#getPrologue <em>Prologue</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class CommentSegmentSupportImpl extends EObjectImpl implements CommentSegmentSupport
{
	/**
	 * The number of structural features of the '<em>Comment Segment Support</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMMENT_SEGMENT_SUPPORT_FEATURE_COUNT = 3;


	/**
	 * The default value of the '{@link #getEpilogue() <em>Epilogue</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEpilogue()
	 * @generated
	 * @ordered
	 */
	protected static final String EPILOGUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEpilogue() <em>Epilogue</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEpilogue()
	 * @generated
	 * @ordered
	 */
	protected String epilogue = EPILOGUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIndentation() <em>Indentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndentation()
	 * @generated
	 * @ordered
	 */
	protected static final String INDENTATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIndentation() <em>Indentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndentation()
	 * @generated
	 * @ordered
	 */
	protected String indentation = INDENTATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getPrologue() <em>Prologue</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrologue()
	 * @generated
	 * @ordered
	 */
	protected static final String PROLOGUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrologue() <em>Prologue</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrologue()
	 * @generated
	 * @ordered
	 */
	protected String prologue = PROLOGUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CommentSegmentSupportImpl()
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
		return IdiomsPackage.Literals.COMMENT_SEGMENT_SUPPORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getEpilogue()
	{
		return epilogue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEpilogue(String newEpilogue)
	{
		String oldEpilogue = epilogue;
		epilogue = newEpilogue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 0, oldEpilogue, epilogue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getIndentation()
	{
		return indentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIndentation(String newIndentation)
	{
		String oldIndentation = indentation;
		indentation = newIndentation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 1, oldIndentation, indentation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPrologue()
	{
		return prologue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrologue(String newPrologue)
	{
		String oldPrologue = prologue;
		prologue = newPrologue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 2, oldPrologue, prologue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID)
		{
			case 0:
				return getEpilogue();
			case 1:
				return getIndentation();
			case 2:
				return getPrologue();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case 0:
				setEpilogue((String)newValue);
				return;
			case 1:
				setIndentation((String)newValue);
				return;
			case 2:
				setPrologue((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
	{
		switch (featureID)
		{
			case 0:
				setEpilogue(EPILOGUE_EDEFAULT);
				return;
			case 1:
				setIndentation(INDENTATION_EDEFAULT);
				return;
			case 2:
				setPrologue(PROLOGUE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch (featureID)
		{
			case 0:
				return EPILOGUE_EDEFAULT == null ? epilogue != null : !EPILOGUE_EDEFAULT.equals(epilogue);
			case 1:
				return INDENTATION_EDEFAULT == null ? indentation != null : !INDENTATION_EDEFAULT.equals(indentation);
			case 2:
				return PROLOGUE_EDEFAULT == null ? prologue != null : !PROLOGUE_EDEFAULT.equals(prologue);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString()
	{
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (epilogue: "); //$NON-NLS-1$
		result.append(epilogue);
		result.append(", indentation: "); //$NON-NLS-1$
		result.append(indentation);
		result.append(", prologue: "); //$NON-NLS-1$
		result.append(prologue);
		result.append(')');
		return result.toString();
	}

	protected void appendComment(@NonNull SerializationBuilder serializationBuilder, @NonNull String comment) {
		serializationBuilder.append(SerializationBuilder.HALF_NEW_LINE);
		serializationBuilder.append(prologue);
		serializationBuilder.append(SerializationBuilder.PUSH_NEXT);
		serializationBuilder.append(indentation);
		serializationBuilder.append(SerializationBuilder.NEW_LINE);
		serializationBuilder.append(comment);
		serializationBuilder.append(SerializationBuilder.POP);
		serializationBuilder.append(SerializationBuilder.NEW_LINE);
		serializationBuilder.append(epilogue);
		serializationBuilder.append(SerializationBuilder.NEW_LINE);
	}

	@Override
	public void serialize(SerializationNode serializationNode, UserElementSerializer serializer, SerializationBuilder serializationBuilder) {
		assert serializer != null;
		assert serializationBuilder != null;
		EObject eObject = serializer.getElement();
		String comment = getComment(eObject);
		if (comment != null) {
			appendComment(serializationBuilder, comment);
		}
	}
} //CommentSegmentSupportImpl
