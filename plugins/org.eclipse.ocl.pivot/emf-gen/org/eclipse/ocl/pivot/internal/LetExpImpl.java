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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.LetExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Let Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.LetExpImpl#getOwnedIn <em>Owned In</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.LetExpImpl#getOwnedVariable <em>Owned Variable</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LetExpImpl
extends OCLExpressionImpl
implements LetExp {

	/**
	 * The cached value of the '{@link #getOwnedIn() <em>Owned In</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedIn()
	 * @generated
	 * @ordered
	 */
	protected OCLExpression ownedIn;

	/**
	 * The cached value of the '{@link #getOwnedVariable() <em>Owned Variable</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedVariable()
	 * @generated
	 * @ordered
	 */
	protected Variable ownedVariable;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LetExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.LET_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OCLExpression getOwnedIn() {
		return ownedIn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedIn(OCLExpression newOwnedIn, NotificationChain msgs)
	{
		OCLExpression oldOwnedIn = ownedIn;
		ownedIn = newOwnedIn;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.LET_EXP__OWNED_IN, oldOwnedIn, newOwnedIn);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwnedIn(OCLExpression newOwnedIn) {
		if (newOwnedIn != ownedIn)
		{
			NotificationChain msgs = null;
			if (ownedIn != null)
				msgs = ((InternalEObject)ownedIn).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.LET_EXP__OWNED_IN, null, msgs);
			if (newOwnedIn != null)
				msgs = ((InternalEObject)newOwnedIn).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.LET_EXP__OWNED_IN, null, msgs);
			msgs = basicSetOwnedIn(newOwnedIn, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.LET_EXP__OWNED_IN, newOwnedIn, newOwnedIn));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Variable getOwnedVariable() {
		return ownedVariable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedVariable(Variable newOwnedVariable, NotificationChain msgs)
	{
		Variable oldOwnedVariable = ownedVariable;
		ownedVariable = newOwnedVariable;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.LET_EXP__OWNED_VARIABLE, oldOwnedVariable, newOwnedVariable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwnedVariable(Variable newOwnedVariable) {
		if (newOwnedVariable != ownedVariable)
		{
			NotificationChain msgs = null;
			if (ownedVariable != null)
				msgs = ((InternalEObject)ownedVariable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.LET_EXP__OWNED_VARIABLE, null, msgs);
			if (newOwnedVariable != null)
				msgs = ((InternalEObject)newOwnedVariable).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.LET_EXP__OWNED_VARIABLE, null, msgs);
			msgs = basicSetOwnedVariable(newOwnedVariable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.LET_EXP__OWNED_VARIABLE, newOwnedVariable, newOwnedVariable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateCompatibleNullityForIn(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv CompatibleNullityForIn:
		 *   let severity : Integer[1] = 'LetExp::CompatibleNullityForIn'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let status : OclAny[1] = isRequired = ownedIn.isRequired
		 *       in
		 *         'LetExp::CompatibleNullityForIn'.logDiagnostic(self, null, diagnostics, context, null, severity, status, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_LetExp_c_c_CompatibleNullityForIn);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_status;
			try {
				final /*@Thrown*/ boolean isRequired = this.isIsRequired();
				@SuppressWarnings("null")
				final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull OCLExpression ownedIn = this.getOwnedIn();
				final /*@Thrown*/ boolean isRequired_0 = ownedIn.isIsRequired();
				final /*@Thrown*/ boolean status = isRequired == isRequired_0;
				CAUGHT_status = status;
			}
			catch (Exception e) {
				CAUGHT_status = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_LetExp_c_c_CompatibleNullityForIn, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_status, PivotTables.INT_0).booleanValue();
			symbol_0 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateTypeIsInType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv TypeIsInType:
		 *   let severity : Integer[1] = 'LetExp::TypeIsInType'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let status : OclAny[1] = type = ownedIn.type
		 *       in
		 *         'LetExp::TypeIsInType'.logDiagnostic(self, null, diagnostics, context, null, severity, status, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_LetExp_c_c_TypeIsInType);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_status;
			try {
				final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = this.getType();
				@SuppressWarnings("null")
				final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull OCLExpression ownedIn = this.getOwnedIn();
				final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type_0 = ownedIn.getType();
				final /*@Thrown*/ boolean status = (type != null) && (type_0 != null) ? (type.getTypeId() == type_0.getTypeId()) : false;
				CAUGHT_status = status;
			}
			catch (Exception e) {
				CAUGHT_status = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_LetExp_c_c_TypeIsInType, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_status, PivotTables.INT_0).booleanValue();
			symbol_0 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateTypeIsNotInvalid(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv TypeIsNotInvalid:
		 *   let severity : Integer[1] = 'LetExp::TypeIsNotInvalid'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let status : OclAny[1] = type <> OclInvalid
		 *       in
		 *         'LetExp::TypeIsNotInvalid'.logDiagnostic(self, null, diagnostics, context, null, severity, status, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_LetExp_c_c_TypeIsNotInvalid);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_status;
			try {
				final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_OclInvalid_0 = idResolver.getClass(TypeId.OCL_INVALID, null);
				final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = this.getType();
				final /*@Thrown*/ boolean status = (type != null) ? (type.getTypeId() != TYP_OclInvalid_0.getTypeId()) : true;
				CAUGHT_status = status;
			}
			catch (Exception e) {
				CAUGHT_status = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_LetExp_c_c_TypeIsNotInvalid, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_status, PivotTables.INT_0).booleanValue();
			symbol_0 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case PivotPackage.LET_EXP__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.LET_EXP__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.LET_EXP__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.LET_EXP__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.LET_EXP__OWNED_IN:
				return basicSetOwnedIn(null, msgs);
			case PivotPackage.LET_EXP__OWNED_VARIABLE:
				return basicSetOwnedVariable(null, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
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
			case PivotPackage.LET_EXP__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.LET_EXP__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.LET_EXP__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.LET_EXP__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.LET_EXP__NAME:
				return getName();
			case PivotPackage.LET_EXP__IS_MANY:
				return isIsMany();
			case PivotPackage.LET_EXP__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.LET_EXP__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case PivotPackage.LET_EXP__TYPE_VALUE:
				return getTypeValue();
			case PivotPackage.LET_EXP__OWNED_IN:
				return getOwnedIn();
			case PivotPackage.LET_EXP__OWNED_VARIABLE:
				return getOwnedVariable();
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
			case PivotPackage.LET_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.LET_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.LET_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.LET_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.LET_EXP__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.LET_EXP__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.LET_EXP__TYPE:
				setType((Type)newValue);
				return;
			case PivotPackage.LET_EXP__TYPE_VALUE:
				setTypeValue((Type)newValue);
				return;
			case PivotPackage.LET_EXP__OWNED_IN:
				setOwnedIn((OCLExpression)newValue);
				return;
			case PivotPackage.LET_EXP__OWNED_VARIABLE:
				setOwnedVariable((Variable)newValue);
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
			case PivotPackage.LET_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.LET_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.LET_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.LET_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.LET_EXP__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.LET_EXP__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.LET_EXP__TYPE:
				setType((Type)null);
				return;
			case PivotPackage.LET_EXP__TYPE_VALUE:
				setTypeValue((Type)null);
				return;
			case PivotPackage.LET_EXP__OWNED_IN:
				setOwnedIn((OCLExpression)null);
				return;
			case PivotPackage.LET_EXP__OWNED_VARIABLE:
				setOwnedVariable((Variable)null);
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
			case PivotPackage.LET_EXP__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.LET_EXP__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.LET_EXP__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.LET_EXP__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.LET_EXP__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.LET_EXP__IS_MANY:
				return isIsMany() != IS_MANY_EDEFAULT;
			case PivotPackage.LET_EXP__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.LET_EXP__TYPE:
				return type != null;
			case PivotPackage.LET_EXP__TYPE_VALUE:
				return typeValue != null;
			case PivotPackage.LET_EXP__OWNED_IN:
				return ownedIn != null;
			case PivotPackage.LET_EXP__OWNED_VARIABLE:
				return ownedVariable != null;
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
			case PivotPackage.LET_EXP___ALL_OWNED_ELEMENTS:
				return allOwnedElements();
			case PivotPackage.LET_EXP___GET_VALUE__TYPE_STRING:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case PivotPackage.LET_EXP___COMPATIBLE_BODY__VALUESPECIFICATION:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case PivotPackage.LET_EXP___VALIDATE_COMPATIBLE_NULLITY_FOR_IN__DIAGNOSTICCHAIN_MAP:
				return validateCompatibleNullityForIn((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.LET_EXP___VALIDATE_TYPE_IS_IN_TYPE__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsInType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.LET_EXP___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsNotInvalid((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitLetExp(this);
	}
} //LetExpImpl
