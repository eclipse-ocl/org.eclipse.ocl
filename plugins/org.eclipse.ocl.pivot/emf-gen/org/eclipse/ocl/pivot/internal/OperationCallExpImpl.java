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
import java.util.Iterator;
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
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.ReferringElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.classifier.OclTypeConformsToOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionSizeOperation;
import org.eclipse.ocl.pivot.library.collection.OrderedCollectionAtOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanAndOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation;
import org.eclipse.ocl.pivot.library.logical.BooleanNotOperation;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Call Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationCallExpImpl#isIsVirtual <em>Is Virtual</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationCallExpImpl#getOwnedArguments <em>Owned Arguments</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationCallExpImpl#getReferredOperation <em>Referred Operation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperationCallExpImpl
		extends FeatureCallExpImpl
		implements OperationCallExp {

	/**
	 * The default value of the '{@link #isIsVirtual() <em>Is Virtual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 1.1
	 * <!-- end-user-doc -->
	 * @see #isIsVirtual()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_VIRTUAL_EDEFAULT = true;

	/**
	 * The flag representing the value of the '{@link #isIsVirtual() <em>Is Virtual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 1.1
	 * <!-- end-user-doc -->
	 * @see #isIsVirtual()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_VIRTUAL_EFLAG = 1 << 12;

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
	 * The cached value of the '{@link #getReferredOperation() <em>Referred Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredOperation()
	 * @generated
	 * @ordered
	 */
	protected Operation referredOperation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationCallExpImpl() {
		super();
		eFlags |= IS_VIRTUAL_EFLAG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.OPERATION_CALL_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.1
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsVirtual()
	{
		return (eFlags & IS_VIRTUAL_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.1
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsVirtual(boolean newIsVirtual)
	{
		boolean oldIsVirtual = (eFlags & IS_VIRTUAL_EFLAG) != 0;
		if (newIsVirtual) eFlags |= IS_VIRTUAL_EFLAG; else eFlags &= ~IS_VIRTUAL_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.OPERATION_CALL_EXP__IS_VIRTUAL, oldIsVirtual, newIsVirtual));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("null")
	public @NonNull List<OCLExpression> getOwnedArguments()
	{
		if (ownedArguments == null)
		{
			ownedArguments = new EObjectContainmentEList<OCLExpression>(OCLExpression.class, this, PivotPackage.OPERATION_CALL_EXP__OWNED_ARGUMENTS);
		}
		return ownedArguments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Operation getReferredOperation() {
		if (referredOperation != null && referredOperation.eIsProxy())
		{
			InternalEObject oldReferredOperation = (InternalEObject)referredOperation;
			referredOperation = (Operation)eResolveProxy(oldReferredOperation);
			if (referredOperation != oldReferredOperation)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.OPERATION_CALL_EXP__REFERRED_OPERATION, oldReferredOperation, referredOperation));
			}
		}
		return referredOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation basicGetReferredOperation() {
		return referredOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredOperation(Operation newReferredOperation) {
		Operation oldReferredOperation = referredOperation;
		referredOperation = newReferredOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.OPERATION_CALL_EXP__REFERRED_OPERATION, oldReferredOperation, referredOperation));
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
			case PivotPackage.OPERATION_CALL_EXP__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.OPERATION_CALL_EXP__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.OPERATION_CALL_EXP__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.OPERATION_CALL_EXP__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.OPERATION_CALL_EXP__OWNED_SOURCE:
				return basicSetOwnedSource(null, msgs);
			case PivotPackage.OPERATION_CALL_EXP__OWNED_ARGUMENTS:
				return ((InternalEList<?>)getOwnedArguments()).basicRemove(otherEnd, msgs);
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
			case PivotPackage.OPERATION_CALL_EXP__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.OPERATION_CALL_EXP__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.OPERATION_CALL_EXP__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.OPERATION_CALL_EXP__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.OPERATION_CALL_EXP__NAME:
				return getName();
			case PivotPackage.OPERATION_CALL_EXP__IS_MANY:
				return isIsMany();
			case PivotPackage.OPERATION_CALL_EXP__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.OPERATION_CALL_EXP__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case PivotPackage.OPERATION_CALL_EXP__TYPE_VALUE:
				return getTypeValue();
			case PivotPackage.OPERATION_CALL_EXP__IS_IMPLICIT:
				return isIsImplicit();
			case PivotPackage.OPERATION_CALL_EXP__IS_SAFE:
				return isIsSafe();
			case PivotPackage.OPERATION_CALL_EXP__OWNED_SOURCE:
				return getOwnedSource();
			case PivotPackage.OPERATION_CALL_EXP__IS_PRE:
				return isIsPre();
			case PivotPackage.OPERATION_CALL_EXP__IS_VIRTUAL:
				return isIsVirtual();
			case PivotPackage.OPERATION_CALL_EXP__OWNED_ARGUMENTS:
				return getOwnedArguments();
			case PivotPackage.OPERATION_CALL_EXP__REFERRED_OPERATION:
				if (resolve) return getReferredOperation();
				return basicGetReferredOperation();
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
			case PivotPackage.OPERATION_CALL_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.OPERATION_CALL_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.OPERATION_CALL_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.OPERATION_CALL_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.OPERATION_CALL_EXP__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.OPERATION_CALL_EXP__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.OPERATION_CALL_EXP__TYPE:
				setType((Type)newValue);
				return;
			case PivotPackage.OPERATION_CALL_EXP__TYPE_VALUE:
				setTypeValue((Type)newValue);
				return;
			case PivotPackage.OPERATION_CALL_EXP__IS_IMPLICIT:
				setIsImplicit((Boolean)newValue);
				return;
			case PivotPackage.OPERATION_CALL_EXP__IS_SAFE:
				setIsSafe((Boolean)newValue);
				return;
			case PivotPackage.OPERATION_CALL_EXP__OWNED_SOURCE:
				setOwnedSource((OCLExpression)newValue);
				return;
			case PivotPackage.OPERATION_CALL_EXP__IS_PRE:
				setIsPre((Boolean)newValue);
				return;
			case PivotPackage.OPERATION_CALL_EXP__IS_VIRTUAL:
				setIsVirtual((Boolean)newValue);
				return;
			case PivotPackage.OPERATION_CALL_EXP__OWNED_ARGUMENTS:
				getOwnedArguments().clear();
				getOwnedArguments().addAll((Collection<? extends OCLExpression>)newValue);
				return;
			case PivotPackage.OPERATION_CALL_EXP__REFERRED_OPERATION:
				setReferredOperation((Operation)newValue);
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
			case PivotPackage.OPERATION_CALL_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.OPERATION_CALL_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.OPERATION_CALL_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.OPERATION_CALL_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.OPERATION_CALL_EXP__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.OPERATION_CALL_EXP__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.OPERATION_CALL_EXP__TYPE:
				setType((Type)null);
				return;
			case PivotPackage.OPERATION_CALL_EXP__TYPE_VALUE:
				setTypeValue((Type)null);
				return;
			case PivotPackage.OPERATION_CALL_EXP__IS_IMPLICIT:
				setIsImplicit(IS_IMPLICIT_EDEFAULT);
				return;
			case PivotPackage.OPERATION_CALL_EXP__IS_SAFE:
				setIsSafe(IS_SAFE_EDEFAULT);
				return;
			case PivotPackage.OPERATION_CALL_EXP__OWNED_SOURCE:
				setOwnedSource((OCLExpression)null);
				return;
			case PivotPackage.OPERATION_CALL_EXP__IS_PRE:
				setIsPre(IS_PRE_EDEFAULT);
				return;
			case PivotPackage.OPERATION_CALL_EXP__IS_VIRTUAL:
				setIsVirtual(IS_VIRTUAL_EDEFAULT);
				return;
			case PivotPackage.OPERATION_CALL_EXP__OWNED_ARGUMENTS:
				getOwnedArguments().clear();
				return;
			case PivotPackage.OPERATION_CALL_EXP__REFERRED_OPERATION:
				setReferredOperation((Operation)null);
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
			case PivotPackage.OPERATION_CALL_EXP__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.OPERATION_CALL_EXP__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.OPERATION_CALL_EXP__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.OPERATION_CALL_EXP__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.OPERATION_CALL_EXP__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.OPERATION_CALL_EXP__IS_MANY:
				return isIsMany() != IS_MANY_EDEFAULT;
			case PivotPackage.OPERATION_CALL_EXP__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.OPERATION_CALL_EXP__TYPE:
				return type != null;
			case PivotPackage.OPERATION_CALL_EXP__TYPE_VALUE:
				return typeValue != null;
			case PivotPackage.OPERATION_CALL_EXP__IS_IMPLICIT:
				return ((eFlags & IS_IMPLICIT_EFLAG) != 0) != IS_IMPLICIT_EDEFAULT;
			case PivotPackage.OPERATION_CALL_EXP__IS_SAFE:
				return ((eFlags & IS_SAFE_EFLAG) != 0) != IS_SAFE_EDEFAULT;
			case PivotPackage.OPERATION_CALL_EXP__OWNED_SOURCE:
				return ownedSource != null;
			case PivotPackage.OPERATION_CALL_EXP__IS_PRE:
				return ((eFlags & IS_PRE_EFLAG) != 0) != IS_PRE_EDEFAULT;
			case PivotPackage.OPERATION_CALL_EXP__IS_VIRTUAL:
				return ((eFlags & IS_VIRTUAL_EFLAG) != 0) != IS_VIRTUAL_EDEFAULT;
			case PivotPackage.OPERATION_CALL_EXP__OWNED_ARGUMENTS:
				return ownedArguments != null && !ownedArguments.isEmpty();
			case PivotPackage.OPERATION_CALL_EXP__REFERRED_OPERATION:
				return referredOperation != null;
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedOperationID(int baseOperationID, Class<?> baseClass)
	{
		if (baseClass == ReferringElement.class)
		{
			switch (baseOperationID)
			{
				case PivotPackage.REFERRING_ELEMENT___GET_REFERRED_ELEMENT: return PivotPackage.OPERATION_CALL_EXP___GET_REFERRED_ELEMENT;
				default: return -1;
			}
		}
		return super.eDerivedOperationID(baseOperationID, baseClass);
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
			case PivotPackage.OPERATION_CALL_EXP___ALL_OWNED_ELEMENTS:
				return allOwnedElements();
			case PivotPackage.OPERATION_CALL_EXP___GET_VALUE__TYPE_STRING:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case PivotPackage.OPERATION_CALL_EXP___COMPATIBLE_BODY__VALUESPECIFICATION:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case PivotPackage.OPERATION_CALL_EXP___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsNotInvalid((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.OPERATION_CALL_EXP___GET_REFERRED_ELEMENT:
				return getReferredElement();
			case PivotPackage.OPERATION_CALL_EXP___VALIDATE_ARGUMENT_COUNT__DIAGNOSTICCHAIN_MAP:
				return validateArgumentCount((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.OPERATION_CALL_EXP___VALIDATE_ARGUMENT_TYPE_IS_CONFORMANT__DIAGNOSTICCHAIN_MAP:
				return validateArgumentTypeIsConformant((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.OPERATION_CALL_EXP___VALIDATE_SAFE_SOURCE_CAN_BE_NULL__DIAGNOSTICCHAIN_MAP:
				return validateSafeSourceCanBeNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
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
		return visitor.visitOperationCallExp(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Element getReferredElement()
	{
		return getReferredOperation();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateArgumentTypeIsConformant(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv validateArgumentTypeIsConformant:
		 *   let
		 *     severity : Integer[1] = 'OperationCallExp::ArgumentTypeIsConformant'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let
		 *         status : OclAny[?] = let operation : Operation[?] = self.referredOperation
		 *         in
		 *           let parameters : OrderedSet(Parameter)[?] = operation?.ownedParameters
		 *           in
		 *             let selfType : Type[?] = operation?.owningClass
		 *             in
		 *               Sequence{1..ownedArguments->size()
		 *               }
		 *               ->forAll(i |
		 *                 let argument : OCLExpression[?] = ownedArguments->at(i)
		 *                 in
		 *                   let parameter : Parameter[?] = parameters->at(i)
		 *                   in
		 *                     let parameterType : Type[?] = parameter?.type
		 *                     in
		 *                       let
		 *                         requiredType : Type[1] = if parameter?.isTypeof
		 *                         then Class
		 *                         else parameterType.specializeIn(self, selfType)
		 *                         endif
		 *                       in argument?.type.conformsTo(requiredType))
		 *       in
		 *         'OperationCallExp::ArgumentTypeIsConformant'.logDiagnostic(self, null, diagnostics, context, null, severity, status, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_OperationCallExp_c_c_ArgumentTypeIsConformant);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_5;
		if (le) {
			symbol_5 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_forAll;
			try {
				final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Operation operation = this.getReferredOperation();
				/*@Caught*/ @Nullable Object CAUGHT_operation;
				try {
					CAUGHT_operation = operation;
				}
				catch (Exception e) {
					CAUGHT_operation = ValueUtil.createInvalidValue(e);
				}
				final /*@NonInvalid*/ @NonNull Object symbol_0 = CAUGHT_operation == null;
				/*@Thrown*/ org.eclipse.ocl.pivot.values.@Nullable OrderedSetValue safe_ownedParameters_source;
				if (symbol_0 == Boolean.TRUE) {
					safe_ownedParameters_source = null;
				}
				else {
					assert operation != null;
					final /*@Thrown*/ java.util.@NonNull List<Parameter> ownedParameters = operation.getOwnedParameters();
					final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull OrderedSetValue BOXED_ownedParameters = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Parameter, ownedParameters);
					safe_ownedParameters_source = BOXED_ownedParameters;
				}
				/*@Caught*/ @Nullable Object CAUGHT_operation_0;
				try {
					CAUGHT_operation_0 = operation;
				}
				catch (Exception e) {
					CAUGHT_operation_0 = ValueUtil.createInvalidValue(e);
				}
				final /*@NonInvalid*/ @NonNull Object symbol_1 = CAUGHT_operation_0 == null;
				/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Class safe_owningClass_source;
				if (symbol_1 == Boolean.TRUE) {
					safe_owningClass_source = null;
				}
				else {
					assert operation != null;
					final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Class owningClass = operation.getOwningClass();
					safe_owningClass_source = owningClass;
				}
				final /*@Thrown*/ java.util.@NonNull List<OCLExpression> ownedArguments = this.getOwnedArguments();
				final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull OrderedSetValue BOXED_ownedArguments = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_OCLExpression, ownedArguments);
				final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue size = CollectionSizeOperation.INSTANCE.evaluate(BOXED_ownedArguments);
				final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull IntegerRange RNG = ValueUtil.createRange(PivotTables.INT_1, size);
				final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull SequenceValue Sequence = ValueUtil.createSequenceRange(PivotTables.SEQ_PRIMid_Integer, RNG);
				/*@Thrown*/ java.lang.@Nullable Object accumulator = ValueUtil.TRUE_VALUE;
				@NonNull Iterator<Object> ITERATOR_i = Sequence.iterator();
				/*@Thrown*/ boolean forAll;
				while (true) {
					if (!ITERATOR_i.hasNext()) {
						if (accumulator == ValueUtil.TRUE_VALUE) {
							forAll = ValueUtil.TRUE_VALUE;
						}
						else {
							throw (InvalidValueException)accumulator;
						}
						break;
					}
					@SuppressWarnings("null")
					/*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue i = (IntegerValue)ITERATOR_i.next();
					/**
					 *
					 * let argument : OCLExpression[?] = ownedArguments->at(i)
					 * in
					 *   let parameter : Parameter[?] = parameters->at(i)
					 *   in
					 *     let parameterType : Type[?] = parameter?.type
					 *     in
					 *       let
					 *         requiredType : Type[1] = if parameter?.isTypeof
					 *         then Class
					 *         else parameterType.specializeIn(self, selfType)
					 *         endif
					 *       in argument?.type.conformsTo(requiredType)
					 */
					/*@Caught*/ @NonNull Object CAUGHT_conformsTo;
					try {
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable OCLExpression argument = (OCLExpression)OrderedCollectionAtOperation.INSTANCE.evaluate(BOXED_ownedArguments, i);
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Parameter parameter = (Parameter)OrderedCollectionAtOperation.INSTANCE.evaluate(safe_ownedParameters_source, i);
						/*@Caught*/ @Nullable Object CAUGHT_parameter;
						try {
							CAUGHT_parameter = parameter;
						}
						catch (Exception e) {
							CAUGHT_parameter = ValueUtil.createInvalidValue(e);
						}
						final /*@NonInvalid*/ @NonNull Object symbol_2 = CAUGHT_parameter == null;
						/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type safe_type_source;
						if (symbol_2 == Boolean.TRUE) {
							safe_type_source = null;
						}
						else {
							assert parameter != null;
							final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = parameter.getType();
							safe_type_source = type;
						}
						/*@Caught*/ @Nullable Object CAUGHT_parameter_0;
						try {
							CAUGHT_parameter_0 = parameter;
						}
						catch (Exception e) {
							CAUGHT_parameter_0 = ValueUtil.createInvalidValue(e);
						}
						final /*@NonInvalid*/ @NonNull Object symbol_3 = CAUGHT_parameter_0 == null;
						/*@Thrown*/ java.lang.@Nullable Boolean safe_isTypeof_source;
						if (symbol_3 == Boolean.TRUE) {
							safe_isTypeof_source = null;
						}
						else {
							assert parameter != null;
							final /*@Thrown*/ boolean isTypeof = parameter.isIsTypeof();
							safe_isTypeof_source = isTypeof;
						}
						if (safe_isTypeof_source == null) {
							throw new InvalidValueException("Null if condition");
						}
						/*@Thrown*/ org.eclipse.ocl.pivot.@NonNull Type requiredType;
						if (safe_isTypeof_source) {
							final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_Class = idResolver.getClass(PivotTables.CLSSid_Class, null);
							requiredType = TYP_Class;
						}
						else {
							if (safe_type_source == null) {
								throw new InvalidValueException("Null source for \'pivot::Type::specializeIn(CallExp[1],Type[1]) : Type[1]\'");
							}
							@SuppressWarnings("null")
							final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull Type specializeIn = safe_type_source.specializeIn(this, safe_owningClass_source);
							requiredType = specializeIn;
						}
						/*@Caught*/ @Nullable Object CAUGHT_argument;
						try {
							CAUGHT_argument = argument;
						}
						catch (Exception e) {
							CAUGHT_argument = ValueUtil.createInvalidValue(e);
						}
						final /*@NonInvalid*/ @NonNull Object symbol_4 = CAUGHT_argument == null;
						/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type safe_type_source_0;
						if (symbol_4 == Boolean.TRUE) {
							safe_type_source_0 = null;
						}
						else {
							assert argument != null;
							final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type_0 = argument.getType();
							safe_type_source_0 = type_0;
						}
						final /*@Thrown*/ boolean conformsTo = OclTypeConformsToOperation.INSTANCE.evaluate(executor, safe_type_source_0, requiredType).booleanValue();
						CAUGHT_conformsTo = conformsTo;
					}
					catch (Exception e) {
						CAUGHT_conformsTo = ValueUtil.createInvalidValue(e);
					}
					//
					if (CAUGHT_conformsTo == ValueUtil.FALSE_VALUE) {					// Normal unsuccessful body evaluation result
						forAll = ValueUtil.FALSE_VALUE;
						break;														// Stop immediately
					}
					else if (CAUGHT_conformsTo == ValueUtil.TRUE_VALUE) {				// Normal successful body evaluation result
						;															// Carry on
					}
					else if (CAUGHT_conformsTo instanceof InvalidValueException) {		// Abnormal exception evaluation result
						accumulator = CAUGHT_conformsTo;									// Cache an exception failure
					}
					else {															// Impossible badly typed result
						accumulator = new InvalidValueException(PivotMessages.NonBooleanBody, "forAll");
					}
				}
				CAUGHT_forAll = forAll;
			}
			catch (Exception e) {
				CAUGHT_forAll = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_OperationCallExp_c_c_ArgumentTypeIsConformant, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_forAll, PivotTables.INT_0).booleanValue();
			symbol_5 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_5;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateSafeSourceCanBeNull(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv validateSafeSourceCanBeNull:
		 *   let
		 *     severity : Integer[1] = 'OperationCallExp::SafeSourceCanBeNull'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let status : OclAny[?] = ownedSource <> null and isSafe implies not ownedSource?.isRequired
		 *       in
		 *         'OperationCallExp::SafeSourceCanBeNull'.logDiagnostic(self, null, diagnostics, context, null, severity, status, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_OperationCallExp_c_c_SafeSourceCanBeNull);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_1;
		if (le) {
			symbol_1 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @Nullable Object CAUGHT_status;
			try {
				/*@Caught*/ @Nullable Object CAUGHT_and;
				try {
					/*@Caught*/ @NonNull Object CAUGHT_ne;
					try {
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedSource = this.getOwnedSource();
						final /*@Thrown*/ boolean ne = ownedSource != null;
						CAUGHT_ne = ne;
					}
					catch (Exception e) {
						CAUGHT_ne = ValueUtil.createInvalidValue(e);
					}
					/*@Caught*/ @Nullable Object CAUGHT_isSafe;
					try {
						final /*@Thrown*/ java.lang.@Nullable Boolean isSafe = this.isIsSafe();
						CAUGHT_isSafe = isSafe;
					}
					catch (Exception e) {
						CAUGHT_isSafe = ValueUtil.createInvalidValue(e);
					}
					final /*@Thrown*/ java.lang.@Nullable Boolean and = BooleanAndOperation.INSTANCE.evaluate(CAUGHT_ne, CAUGHT_isSafe);
					CAUGHT_and = and;
				}
				catch (Exception e) {
					CAUGHT_and = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @Nullable Object CAUGHT_not;
				try {
					/*@Caught*/ @Nullable Object CAUGHT_safe_isRequired_source;
					try {
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedSource_0 = this.getOwnedSource();
						/*@Caught*/ @Nullable Object CAUGHT_ownedSource_0;
						try {
							CAUGHT_ownedSource_0 = ownedSource_0;
						}
						catch (Exception e) {
							CAUGHT_ownedSource_0 = ValueUtil.createInvalidValue(e);
						}
						final /*@NonInvalid*/ @NonNull Object symbol_0 = CAUGHT_ownedSource_0 == null;
						/*@Thrown*/ java.lang.@Nullable Boolean safe_isRequired_source;
						if (symbol_0 == Boolean.TRUE) {
							safe_isRequired_source = null;
						}
						else {
							assert ownedSource_0 != null;
							final /*@Thrown*/ boolean isRequired = ownedSource_0.isIsRequired();
							safe_isRequired_source = isRequired;
						}
						CAUGHT_safe_isRequired_source = safe_isRequired_source;
					}
					catch (Exception e) {
						CAUGHT_safe_isRequired_source = ValueUtil.createInvalidValue(e);
					}
					final /*@Thrown*/ java.lang.@Nullable Boolean not = BooleanNotOperation.INSTANCE.evaluate(CAUGHT_safe_isRequired_source);
					CAUGHT_not = not;
				}
				catch (Exception e) {
					CAUGHT_not = ValueUtil.createInvalidValue(e);
				}
				final /*@Thrown*/ java.lang.@Nullable Boolean status = BooleanImpliesOperation.INSTANCE.evaluate(CAUGHT_and, CAUGHT_not);
				CAUGHT_status = status;
			}
			catch (Exception e) {
				CAUGHT_status = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_OperationCallExp_c_c_SafeSourceCanBeNull, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_status, PivotTables.INT_0).booleanValue();
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
	public boolean validateArgumentCount(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv validateArgumentCount:
		 *   let severity : Integer[1] = 'OperationCallExp::ArgumentCount'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let
		 *         status : OclAny[1] = ownedArguments->size() =
		 *         referredOperation?.ownedParameters->size()
		 *       in
		 *         'OperationCallExp::ArgumentCount'.logDiagnostic(self, null, diagnostics, context, null, severity, status, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_OperationCallExp_c_c_ArgumentCount);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_1;
		if (le) {
			symbol_1 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_status;
			try {
				final /*@Thrown*/ java.util.@NonNull List<OCLExpression> ownedArguments = this.getOwnedArguments();
				final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull OrderedSetValue BOXED_ownedArguments = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_OCLExpression, ownedArguments);
				final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue size = CollectionSizeOperation.INSTANCE.evaluate(BOXED_ownedArguments);
				final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Operation referredOperation = this.getReferredOperation();
				/*@Caught*/ @Nullable Object CAUGHT_referredOperation;
				try {
					CAUGHT_referredOperation = referredOperation;
				}
				catch (Exception e) {
					CAUGHT_referredOperation = ValueUtil.createInvalidValue(e);
				}
				final /*@NonInvalid*/ @NonNull Object symbol_0 = CAUGHT_referredOperation == null;
				/*@Thrown*/ org.eclipse.ocl.pivot.values.@Nullable OrderedSetValue safe_ownedParameters_source;
				if (symbol_0 == Boolean.TRUE) {
					safe_ownedParameters_source = null;
				}
				else {
					assert referredOperation != null;
					final /*@Thrown*/ java.util.@NonNull List<Parameter> ownedParameters = referredOperation.getOwnedParameters();
					final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull OrderedSetValue BOXED_ownedParameters = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Parameter, ownedParameters);
					safe_ownedParameters_source = BOXED_ownedParameters;
				}
				final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue size_0 = CollectionSizeOperation.INSTANCE.evaluate(safe_ownedParameters_source);
				final /*@Thrown*/ boolean status = size.equals(size_0);
				CAUGHT_status = status;
			}
			catch (Exception e) {
				CAUGHT_status = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_OperationCallExp_c_c_ArgumentCount, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_status, PivotTables.INT_0).booleanValue();
			symbol_1 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_1;
	}
} //OperationCallExpImpl
