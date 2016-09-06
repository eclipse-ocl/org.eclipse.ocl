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
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallOperationAction;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.MessageExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.SendSignalAction;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.collection.CollectionSizeOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanNotOperation;
import org.eclipse.ocl.pivot.library.numeric.NumericPlusOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Message Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.MessageExpImpl#getOwnedArguments <em>Owned Arguments</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.MessageExpImpl#getOwnedCalledOperation <em>Owned Called Operation</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.MessageExpImpl#getOwnedSentSignal <em>Owned Sent Signal</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.MessageExpImpl#getOwnedTarget <em>Owned Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MessageExpImpl
		extends OCLExpressionImpl
		implements MessageExp {

	/**
	 * The cached value of the '{@link #getOwnedArguments() <em>Owned Arguments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedArguments()
	 * @generated
	 * @ordered
	 */
	protected EList<OCLExpression> ownedArguments;

	/**
	 * The cached value of the '{@link #getOwnedCalledOperation() <em>Owned Called Operation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedCalledOperation()
	 * @generated
	 * @ordered
	 */
	protected CallOperationAction ownedCalledOperation;

	/**
	 * The cached value of the '{@link #getOwnedSentSignal() <em>Owned Sent Signal</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSentSignal()
	 * @generated
	 * @ordered
	 */
	protected SendSignalAction ownedSentSignal;

	/**
	 * The cached value of the '{@link #getOwnedTarget() <em>Owned Target</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedTarget()
	 * @generated
	 * @ordered
	 */
	protected OCLExpression ownedTarget;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MessageExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.MESSAGE_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OCLExpression getOwnedTarget() {
		return ownedTarget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedTarget(OCLExpression newOwnedTarget, NotificationChain msgs)
	{
		OCLExpression oldOwnedTarget = ownedTarget;
		ownedTarget = newOwnedTarget;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.MESSAGE_EXP__OWNED_TARGET, oldOwnedTarget, newOwnedTarget);
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
	public void setOwnedTarget(OCLExpression newOwnedTarget) {
		if (newOwnedTarget != ownedTarget)
		{
			NotificationChain msgs = null;
			if (ownedTarget != null)
				msgs = ((InternalEObject)ownedTarget).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.MESSAGE_EXP__OWNED_TARGET, null, msgs);
			if (newOwnedTarget != null)
				msgs = ((InternalEObject)newOwnedTarget).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.MESSAGE_EXP__OWNED_TARGET, null, msgs);
			msgs = basicSetOwnedTarget(newOwnedTarget, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.MESSAGE_EXP__OWNED_TARGET, newOwnedTarget, newOwnedTarget));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("null")
	@Override
	public @NonNull List<OCLExpression> getOwnedArguments()
	{
		if (ownedArguments == null)
		{
			ownedArguments = new EObjectContainmentEList<OCLExpression>(OCLExpression.class, this, PivotPackage.MESSAGE_EXP__OWNED_ARGUMENTS);
		}
		return ownedArguments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CallOperationAction getOwnedCalledOperation() {
		return ownedCalledOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedCalledOperation(CallOperationAction newOwnedCalledOperation, NotificationChain msgs)
	{
		CallOperationAction oldOwnedCalledOperation = ownedCalledOperation;
		ownedCalledOperation = newOwnedCalledOperation;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.MESSAGE_EXP__OWNED_CALLED_OPERATION, oldOwnedCalledOperation, newOwnedCalledOperation);
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
	public void setOwnedCalledOperation(CallOperationAction newOwnedCalledOperation)
	{
		if (newOwnedCalledOperation != ownedCalledOperation)
		{
			NotificationChain msgs = null;
			if (ownedCalledOperation != null)
				msgs = ((InternalEObject)ownedCalledOperation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.MESSAGE_EXP__OWNED_CALLED_OPERATION, null, msgs);
			if (newOwnedCalledOperation != null)
				msgs = ((InternalEObject)newOwnedCalledOperation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.MESSAGE_EXP__OWNED_CALLED_OPERATION, null, msgs);
			msgs = basicSetOwnedCalledOperation(newOwnedCalledOperation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.MESSAGE_EXP__OWNED_CALLED_OPERATION, newOwnedCalledOperation, newOwnedCalledOperation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SendSignalAction getOwnedSentSignal() {
		return ownedSentSignal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedSentSignal(SendSignalAction newOwnedSentSignal, NotificationChain msgs)
	{
		SendSignalAction oldOwnedSentSignal = ownedSentSignal;
		ownedSentSignal = newOwnedSentSignal;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.MESSAGE_EXP__OWNED_SENT_SIGNAL, oldOwnedSentSignal, newOwnedSentSignal);
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
	public void setOwnedSentSignal(SendSignalAction newOwnedSentSignal)
	{
		if (newOwnedSentSignal != ownedSentSignal)
		{
			NotificationChain msgs = null;
			if (ownedSentSignal != null)
				msgs = ((InternalEObject)ownedSentSignal).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.MESSAGE_EXP__OWNED_SENT_SIGNAL, null, msgs);
			if (newOwnedSentSignal != null)
				msgs = ((InternalEObject)newOwnedSentSignal).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.MESSAGE_EXP__OWNED_SENT_SIGNAL, null, msgs);
			msgs = basicSetOwnedSentSignal(newOwnedSentSignal, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.MESSAGE_EXP__OWNED_SENT_SIGNAL, newOwnedSentSignal, newOwnedSentSignal));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateOneCallOrOneSend(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 * 
		 * inv validateOneCallOrOneSend:
		 *   let severity : Integer[1] = 'MessageExp::OneCallOrOneSend'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let
		 *         status : OclAny[1] = ownedCalledOperation->size() +
		 *         ownedSentSignal->size() = 1
		 *       in
		 *         'MessageExp::OneCallOrOneSend'.logDiagnostic(self, null, diagnostics, context, null, severity, status, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_MessageExp_c_c_OneCallOrOneSend);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
		    symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
		    /*@Caught*/ @NonNull Object CAUGHT_status;
		    try {
		        final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable CallOperationAction ownedCalledOperation = this.getOwnedCalledOperation();
		        final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull SetValue oclAsSet = OclAnyOclAsSetOperation.INSTANCE.evaluate(executor, PivotTables.SET_CLSSid_CallOperationAction, ownedCalledOperation);
		        final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue size = CollectionSizeOperation.INSTANCE.evaluate(oclAsSet);
		        final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable SendSignalAction ownedSentSignal = this.getOwnedSentSignal();
		        final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull SetValue oclAsSet_0 = OclAnyOclAsSetOperation.INSTANCE.evaluate(executor, PivotTables.SET_CLSSid_SendSignalAction, ownedSentSignal);
		        final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue size_0 = CollectionSizeOperation.INSTANCE.evaluate(oclAsSet_0);
		        final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue sum = (IntegerValue)NumericPlusOperation.INSTANCE.evaluate(size, size_0);
		        final /*@Thrown*/ boolean status = sum.equals(PivotTables.INT_1);
		        CAUGHT_status = status;
		    }
		    catch (Exception e) {
		        CAUGHT_status = ValueUtil.createInvalidValue(e);
		    }
		    final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_MessageExp_c_c_OneCallOrOneSend, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_status, PivotTables.INT_0).booleanValue();
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
	public boolean validateTargetIsNotACollection(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 * 
		 * inv validateTargetIsNotACollection:
		 *   let
		 *     severity : Integer[1] = 'MessageExp::TargetIsNotACollection'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let
		 *         status : OclAny[?] = not ownedTarget.type.oclIsKindOf(CollectionType)
		 *       in
		 *         'MessageExp::TargetIsNotACollection'.logDiagnostic(self, null, diagnostics, context, null, severity, status, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_MessageExp_c_c_TargetIsNotACollection);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
		    symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
		    /*@Caught*/ @Nullable Object CAUGHT_status;
		    try {
		        /*@Caught*/ @NonNull Object CAUGHT_oclIsKindOf;
		        try {
		            final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_0 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
		            @SuppressWarnings("null")
		            final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull OCLExpression ownedTarget = this.getOwnedTarget();
		            final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = ownedTarget.getType();
		            final /*@Thrown*/ boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, type, TYP_CollectionType_0).booleanValue();
		            CAUGHT_oclIsKindOf = oclIsKindOf;
		        }
		        catch (Exception e) {
		            CAUGHT_oclIsKindOf = ValueUtil.createInvalidValue(e);
		        }
		        final /*@Thrown*/ java.lang.@Nullable Boolean status = BooleanNotOperation.INSTANCE.evaluate(CAUGHT_oclIsKindOf);
		        CAUGHT_status = status;
		    }
		    catch (Exception e) {
		        CAUGHT_status = ValueUtil.createInvalidValue(e);
		    }
		    final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_MessageExp_c_c_TargetIsNotACollection, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_status, PivotTables.INT_0).booleanValue();
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
			case PivotPackage.MESSAGE_EXP__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.MESSAGE_EXP__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.MESSAGE_EXP__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.MESSAGE_EXP__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.MESSAGE_EXP__OWNED_ARGUMENTS:
				return ((InternalEList<?>)getOwnedArguments()).basicRemove(otherEnd, msgs);
			case PivotPackage.MESSAGE_EXP__OWNED_CALLED_OPERATION:
				return basicSetOwnedCalledOperation(null, msgs);
			case PivotPackage.MESSAGE_EXP__OWNED_SENT_SIGNAL:
				return basicSetOwnedSentSignal(null, msgs);
			case PivotPackage.MESSAGE_EXP__OWNED_TARGET:
				return basicSetOwnedTarget(null, msgs);
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
			case PivotPackage.MESSAGE_EXP__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.MESSAGE_EXP__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.MESSAGE_EXP__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.MESSAGE_EXP__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.MESSAGE_EXP__NAME:
				return getName();
			case PivotPackage.MESSAGE_EXP__IS_MANY:
				return isIsMany();
			case PivotPackage.MESSAGE_EXP__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.MESSAGE_EXP__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case PivotPackage.MESSAGE_EXP__TYPE_VALUE:
				return getTypeValue();
			case PivotPackage.MESSAGE_EXP__OWNED_ARGUMENTS:
				return getOwnedArguments();
			case PivotPackage.MESSAGE_EXP__OWNED_CALLED_OPERATION:
				return getOwnedCalledOperation();
			case PivotPackage.MESSAGE_EXP__OWNED_SENT_SIGNAL:
				return getOwnedSentSignal();
			case PivotPackage.MESSAGE_EXP__OWNED_TARGET:
				return getOwnedTarget();
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
			case PivotPackage.MESSAGE_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.MESSAGE_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.MESSAGE_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.MESSAGE_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.MESSAGE_EXP__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.MESSAGE_EXP__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.MESSAGE_EXP__TYPE:
				setType((Type)newValue);
				return;
			case PivotPackage.MESSAGE_EXP__TYPE_VALUE:
				setTypeValue((Type)newValue);
				return;
			case PivotPackage.MESSAGE_EXP__OWNED_ARGUMENTS:
				getOwnedArguments().clear();
				getOwnedArguments().addAll((Collection<? extends OCLExpression>)newValue);
				return;
			case PivotPackage.MESSAGE_EXP__OWNED_CALLED_OPERATION:
				setOwnedCalledOperation((CallOperationAction)newValue);
				return;
			case PivotPackage.MESSAGE_EXP__OWNED_SENT_SIGNAL:
				setOwnedSentSignal((SendSignalAction)newValue);
				return;
			case PivotPackage.MESSAGE_EXP__OWNED_TARGET:
				setOwnedTarget((OCLExpression)newValue);
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
			case PivotPackage.MESSAGE_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.MESSAGE_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.MESSAGE_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.MESSAGE_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.MESSAGE_EXP__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.MESSAGE_EXP__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.MESSAGE_EXP__TYPE:
				setType((Type)null);
				return;
			case PivotPackage.MESSAGE_EXP__TYPE_VALUE:
				setTypeValue((Type)null);
				return;
			case PivotPackage.MESSAGE_EXP__OWNED_ARGUMENTS:
				getOwnedArguments().clear();
				return;
			case PivotPackage.MESSAGE_EXP__OWNED_CALLED_OPERATION:
				setOwnedCalledOperation((CallOperationAction)null);
				return;
			case PivotPackage.MESSAGE_EXP__OWNED_SENT_SIGNAL:
				setOwnedSentSignal((SendSignalAction)null);
				return;
			case PivotPackage.MESSAGE_EXP__OWNED_TARGET:
				setOwnedTarget((OCLExpression)null);
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
			case PivotPackage.MESSAGE_EXP__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.MESSAGE_EXP__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.MESSAGE_EXP__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.MESSAGE_EXP__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.MESSAGE_EXP__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.MESSAGE_EXP__IS_MANY:
				return isIsMany() != IS_MANY_EDEFAULT;
			case PivotPackage.MESSAGE_EXP__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.MESSAGE_EXP__TYPE:
				return type != null;
			case PivotPackage.MESSAGE_EXP__TYPE_VALUE:
				return typeValue != null;
			case PivotPackage.MESSAGE_EXP__OWNED_ARGUMENTS:
				return ownedArguments != null && !ownedArguments.isEmpty();
			case PivotPackage.MESSAGE_EXP__OWNED_CALLED_OPERATION:
				return ownedCalledOperation != null;
			case PivotPackage.MESSAGE_EXP__OWNED_SENT_SIGNAL:
				return ownedSentSignal != null;
			case PivotPackage.MESSAGE_EXP__OWNED_TARGET:
				return ownedTarget != null;
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
			case PivotPackage.MESSAGE_EXP___ALL_OWNED_ELEMENTS:
				return allOwnedElements();
			case PivotPackage.MESSAGE_EXP___GET_VALUE__TYPE_STRING:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case PivotPackage.MESSAGE_EXP___COMPATIBLE_BODY__VALUESPECIFICATION:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case PivotPackage.MESSAGE_EXP___VALIDATE_ONE_CALL_OR_ONE_SEND__DIAGNOSTICCHAIN_MAP:
				return validateOneCallOrOneSend((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.MESSAGE_EXP___VALIDATE_TARGET_IS_NOT_ACOLLECTION__DIAGNOSTICCHAIN_MAP:
				return validateTargetIsNotACollection((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitMessageExp(this);
	}
} //MessageExpImpl
