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
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.EnumLiteralExp;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enum Literal Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.EnumLiteralExpImpl#getReferredLiteral <em>Referred Literal</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EnumLiteralExpImpl
		extends LiteralExpImpl
		implements EnumLiteralExp {

	/**
	 * The cached value of the '{@link #getReferredLiteral() <em>Referred Literal</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredLiteral()
	 * @generated
	 * @ordered
	 */
	protected EnumerationLiteral referredLiteral;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumLiteralExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.ENUM_LITERAL_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EnumerationLiteral getReferredLiteral() {
		if (referredLiteral != null && referredLiteral.eIsProxy())
		{
			InternalEObject oldReferredLiteral = (InternalEObject)referredLiteral;
			referredLiteral = (EnumerationLiteral)eResolveProxy(oldReferredLiteral);
			if (referredLiteral != oldReferredLiteral)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.ENUM_LITERAL_EXP__REFERRED_LITERAL, oldReferredLiteral, referredLiteral));
			}
		}
		return referredLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumerationLiteral basicGetReferredLiteral()
	{
		return referredLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredLiteral(EnumerationLiteral newReferredLiteral) {
		EnumerationLiteral oldReferredLiteral = referredLiteral;
		referredLiteral = newReferredLiteral;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.ENUM_LITERAL_EXP__REFERRED_LITERAL, oldReferredLiteral, referredLiteral));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateTypeIsEnumerationType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv TypeIsEnumerationType:
		 *   let
		 *     severity : Integer[1] = 'EnumLiteralExp::TypeIsEnumerationType'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let status : OclAny[1] = self.type = referredLiteral?.owningEnumeration
		 *       in
		 *         'EnumLiteralExp::TypeIsEnumerationType'.logDiagnostic(self, null, diagnostics, context, null, severity, status, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_EnumLiteralExp_c_c_TypeIsEnumerationType);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_1;
		if (le) {
			symbol_1 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_status;
			try {
				final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = this.getType();
				final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable EnumerationLiteral referredLiteral = this.getReferredLiteral();
				/*@Caught*/ @Nullable Object CAUGHT_referredLiteral;
				try {
					CAUGHT_referredLiteral = referredLiteral;
				}
				catch (Exception e) {
					CAUGHT_referredLiteral = ValueUtil.createInvalidValue(e);
				}
				final /*@NonInvalid*/ @NonNull Object symbol_0 = CAUGHT_referredLiteral == null;
				/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Enumeration safe_owningEnumeration_source;
				if (symbol_0 == Boolean.TRUE) {
					safe_owningEnumeration_source = null;
				}
				else {
					assert referredLiteral != null;
					@SuppressWarnings("null")
					final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull Enumeration owningEnumeration = referredLiteral.getOwningEnumeration();
					safe_owningEnumeration_source = owningEnumeration;
				}
				final /*@Thrown*/ boolean status = (type != null) && (safe_owningEnumeration_source != null) ? (type.getTypeId() == safe_owningEnumeration_source.getTypeId()) : false;
				CAUGHT_status = status;
			}
			catch (Exception e) {
				CAUGHT_status = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_EnumLiteralExp_c_c_TypeIsEnumerationType, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_status, PivotTables.INT_0).booleanValue();
			symbol_1 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_1;
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
			case PivotPackage.ENUM_LITERAL_EXP__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.ENUM_LITERAL_EXP__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.ENUM_LITERAL_EXP__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.ENUM_LITERAL_EXP__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.ENUM_LITERAL_EXP__NAME:
				return getName();
			case PivotPackage.ENUM_LITERAL_EXP__IS_MANY:
				return isIsMany();
			case PivotPackage.ENUM_LITERAL_EXP__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.ENUM_LITERAL_EXP__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case PivotPackage.ENUM_LITERAL_EXP__TYPE_VALUE:
				return getTypeValue();
			case PivotPackage.ENUM_LITERAL_EXP__REFERRED_LITERAL:
				if (resolve) return getReferredLiteral();
				return basicGetReferredLiteral();
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
			case PivotPackage.ENUM_LITERAL_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.ENUM_LITERAL_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.ENUM_LITERAL_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.ENUM_LITERAL_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.ENUM_LITERAL_EXP__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.ENUM_LITERAL_EXP__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.ENUM_LITERAL_EXP__TYPE:
				setType((Type)newValue);
				return;
			case PivotPackage.ENUM_LITERAL_EXP__TYPE_VALUE:
				setTypeValue((Type)newValue);
				return;
			case PivotPackage.ENUM_LITERAL_EXP__REFERRED_LITERAL:
				setReferredLiteral((EnumerationLiteral)newValue);
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
			case PivotPackage.ENUM_LITERAL_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.ENUM_LITERAL_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.ENUM_LITERAL_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.ENUM_LITERAL_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.ENUM_LITERAL_EXP__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.ENUM_LITERAL_EXP__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.ENUM_LITERAL_EXP__TYPE:
				setType((Type)null);
				return;
			case PivotPackage.ENUM_LITERAL_EXP__TYPE_VALUE:
				setTypeValue((Type)null);
				return;
			case PivotPackage.ENUM_LITERAL_EXP__REFERRED_LITERAL:
				setReferredLiteral((EnumerationLiteral)null);
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
			case PivotPackage.ENUM_LITERAL_EXP__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.ENUM_LITERAL_EXP__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.ENUM_LITERAL_EXP__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.ENUM_LITERAL_EXP__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.ENUM_LITERAL_EXP__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.ENUM_LITERAL_EXP__IS_MANY:
				return isIsMany() != IS_MANY_EDEFAULT;
			case PivotPackage.ENUM_LITERAL_EXP__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.ENUM_LITERAL_EXP__TYPE:
				return type != null;
			case PivotPackage.ENUM_LITERAL_EXP__TYPE_VALUE:
				return typeValue != null;
			case PivotPackage.ENUM_LITERAL_EXP__REFERRED_LITERAL:
				return referredLiteral != null;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException
	{
		switch (operationID)
		{
			case PivotPackage.ENUM_LITERAL_EXP___ALL_OWNED_ELEMENTS:
				return allOwnedElements();
			case PivotPackage.ENUM_LITERAL_EXP___GET_VALUE__TYPE_STRING:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case PivotPackage.ENUM_LITERAL_EXP___COMPATIBLE_BODY__VALUESPECIFICATION:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case PivotPackage.ENUM_LITERAL_EXP___VALIDATE_TYPE_IS_ENUMERATION_TYPE__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsEnumerationType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitEnumLiteralExp(this);
	}
} //EnumLiteralExpImpl
