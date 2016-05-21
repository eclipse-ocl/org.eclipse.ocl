/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;
import org.eclipse.ocl.pivot.values.UnlimitedValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Typed Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TypedElementImpl#isIsMany <em>Is Many</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TypedElementImpl#isIsRequired <em>Is Required</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TypedElementImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class TypedElementImpl
		extends NamedElementImpl
		implements TypedElement {

	/**
	 * The default value of the '{@link #isIsMany() <em>Is Many</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsMany()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_MANY_EDEFAULT = false;
	/**
	 * The default value of the '{@link #isIsRequired() <em>Is Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsRequired()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_REQUIRED_EDEFAULT = true;
	/**
	 * The flag representing the value of the '{@link #isIsRequired() <em>Is Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsRequired()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_REQUIRED_EFLAG = 1 << 8;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected Type type;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypedElementImpl() {
		super();
		eFlags |= IS_REQUIRED_EFLAG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.TYPED_ELEMENT;
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsRequired()
	{
		return (eFlags & IS_REQUIRED_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Type getType() {
		if (type != null && type.eIsProxy())
		{
			InternalEObject oldType = (InternalEObject)type;
			type = (Type)eResolveProxy(oldType);
			if (type != oldType)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.TYPED_ELEMENT__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(Type newType) {
		Type oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.TYPED_ELEMENT__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean CompatibleBody(final ValueSpecification bodySpecification)
	{
		/**
		 * bodySpecification.type?.conformsTo(self.type)
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = bodySpecification.getType();
		/*@Caught*/ @Nullable Object CAUGHT_type;
		try {
		    CAUGHT_type = type;
		}
		catch (Exception e) {
		    CAUGHT_type = ValueUtil.createInvalidValue(e);
		}
		final /*@NonInvalid*/ @NonNull Object symbol_2 = CAUGHT_type == null;
		/*@Thrown*/ java.lang.@Nullable Boolean safe_conformsTo_source;
		if (symbol_2 == Boolean.TRUE) {
		    safe_conformsTo_source = null;
		}
		else {
		    final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type_0 = this.getType();
		    final /*@Thrown*/ boolean conformsTo = OclTypeConformsToOperation.INSTANCE.evaluate(executor, type, type_0).booleanValue();
		    safe_conformsTo_source = conformsTo;
		}
		if (safe_conformsTo_source == null) {
		    throw new InvalidValueException("Null body for \'pivot::TypedElement::CompatibleBody(ValueSpecification[1]) : Boolean[1]\'");
		}
		return safe_conformsTo_source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsRequired(boolean newIsRequired)
	{
		boolean oldIsRequired = (eFlags & IS_REQUIRED_EFLAG) != 0;
		if (newIsRequired) eFlags |= IS_REQUIRED_EFLAG; else eFlags &= ~IS_REQUIRED_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.TYPED_ELEMENT__IS_REQUIRED, oldIsRequired, newIsRequired));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID)
		{
			case PivotPackage.TYPED_ELEMENT__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.TYPED_ELEMENT__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.TYPED_ELEMENT__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.TYPED_ELEMENT__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.TYPED_ELEMENT__NAME:
				return getName();
			case PivotPackage.TYPED_ELEMENT__IS_MANY:
				return isIsMany();
			case PivotPackage.TYPED_ELEMENT__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.TYPED_ELEMENT__TYPE:
				if (resolve) return getType();
				return basicGetType();
		}
		return eDynamicGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID)
		{
			case PivotPackage.TYPED_ELEMENT__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.TYPED_ELEMENT__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.TYPED_ELEMENT__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.TYPED_ELEMENT__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.TYPED_ELEMENT__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.TYPED_ELEMENT__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.TYPED_ELEMENT__TYPE:
				setType((Type)newValue);
				return;
		}
		eDynamicSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID)
		{
			case PivotPackage.TYPED_ELEMENT__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.TYPED_ELEMENT__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.TYPED_ELEMENT__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.TYPED_ELEMENT__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.TYPED_ELEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.TYPED_ELEMENT__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.TYPED_ELEMENT__TYPE:
				setType((Type)null);
				return;
		}
		eDynamicUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID)
		{
			case PivotPackage.TYPED_ELEMENT__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.TYPED_ELEMENT__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.TYPED_ELEMENT__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.TYPED_ELEMENT__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.TYPED_ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.TYPED_ELEMENT__IS_MANY:
				return isIsMany() != IS_MANY_EDEFAULT;
			case PivotPackage.TYPED_ELEMENT__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.TYPED_ELEMENT__TYPE:
				return type != null;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException
	{
		switch (operationID)
		{
			case PivotPackage.TYPED_ELEMENT___ALL_OWNED_ELEMENTS:
				return allOwnedElements();
			case PivotPackage.TYPED_ELEMENT___GET_VALUE__TYPE_STRING:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case PivotPackage.TYPED_ELEMENT___COMPATIBLE_BODY__VALUESPECIFICATION:
				return CompatibleBody((ValueSpecification)arguments.get(0));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString()
	{
		return super.toString();
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitTypedElement(this);
	}

	@Override
	public @NonNull TypeId getTypeId() {
		Type type2 = getType();
		return type2 != null ? type2.getTypeId() : TypeId.OCL_INVALID;
	}

	@Override
	public boolean isIsMany() {
		Type type = getType();
		if (type instanceof CollectionType) {
			CollectionType collectionType = (CollectionType)type;
			UnlimitedNaturalValue upperValue = collectionType.getUpperValue();
			return (upperValue instanceof UnlimitedValue) || (upperValue.intValue() > 1);
		}
		return false;
	}
} //TypedElementImpl
