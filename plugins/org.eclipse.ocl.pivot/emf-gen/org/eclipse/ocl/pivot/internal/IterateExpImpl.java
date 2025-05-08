/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
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
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.IterateExp;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.IteratorVariable;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.ReferringElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.OrderedSetValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Iterate Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.IterateExpImpl#getOwnedBodies <em>Owned Bodies</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.IterateExpImpl#getOwnedResult <em>Owned Result</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IterateExpImpl extends LoopExpImpl implements IterateExp
{
	/**
	 * The number of structural features of the '<em>Iterate Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATE_EXP_FEATURE_COUNT = LoopExpImpl.LOOP_EXP_FEATURE_COUNT + 2;
	/**
	 * The number of operations of the '<em>Iterate Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATE_EXP_OPERATION_COUNT = LoopExpImpl.LOOP_EXP_OPERATION_COUNT + 7;
	/**
	 * The cached value of the '{@link #getOwnedBodies() <em>Owned Bodies</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedBodies()
	 * @generated
	 * @ordered
	 */
	protected EList<OCLExpression> ownedBodies;
	/**
	 * The cached value of the '{@link #getOwnedResult() <em>Owned Result</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedResult()
	 * @generated
	 * @ordered
	 */
	protected Variable ownedResult;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IterateExpImpl()
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
		return PivotPackage.Literals.ITERATE_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<OCLExpression> getOwnedBodies()
	{
		if (ownedBodies == null)
		{
			ownedBodies = new EObjectContainmentEList<OCLExpression>(OCLExpression.class, this, 15);
		}
		return ownedBodies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Variable getOwnedResult()
	{
		return ownedResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedResult(Variable newOwnedResult, NotificationChain msgs)
	{
		Variable oldOwnedResult = ownedResult;
		ownedResult = newOwnedResult;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 16, oldOwnedResult, newOwnedResult);
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
	public void setOwnedResult(Variable newOwnedResult)
	{
		if (newOwnedResult != ownedResult)
		{
			NotificationChain msgs = null;
			if (ownedResult != null)
				msgs = ((InternalEObject)ownedResult).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (16), null, msgs);
			if (newOwnedResult != null)
				msgs = ((InternalEObject)newOwnedResult).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (16), null, msgs);
			msgs = basicSetOwnedResult(newOwnedResult, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 16, newOwnedResult, newOwnedResult));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case 0:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case 1:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case 2:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case 3:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case 11:
				return basicSetOwnedSource(null, msgs);
			case 12:
				return ((InternalEList<?>)getOwnedCoIterators()).basicRemove(otherEnd, msgs);
			case 13:
				return ((InternalEList<?>)getOwnedIterators()).basicRemove(otherEnd, msgs);
			case 15:
				return ((InternalEList<?>)getOwnedBodies()).basicRemove(otherEnd, msgs);
			case 16:
				return basicSetOwnedResult(null, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
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
				return getAnnotatingComments();
			case 1:
				return getOwnedAnnotations();
			case 2:
				return getOwnedComments();
			case 3:
				return getOwnedExtensions();
			case 4:
				return getName();
			case 5:
				return isIsMany();
			case 6:
				return isIsRequired();
			case 7:
				if (resolve) return getType();
				return basicGetType();
			case 8:
				return getTypeValue();
			case 9:
				return isIsImplicit();
			case 10:
				return isIsSafe();
			case 11:
				return getOwnedSource();
			case 12:
				return getOwnedCoIterators();
			case 13:
				return getOwnedIterators();
			case 14:
				if (resolve) return getReferredIteration();
				return basicGetReferredIteration();
			case 15:
				return getOwnedBodies();
			case 16:
				return getOwnedResult();
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
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case 0:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 1:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case 2:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 3:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case 4:
				setName((String)newValue);
				return;
			case 6:
				setIsRequired((Boolean)newValue);
				return;
			case 7:
				setType((Type)newValue);
				return;
			case 8:
				setTypeValue((Type)newValue);
				return;
			case 9:
				setIsImplicit((Boolean)newValue);
				return;
			case 10:
				setIsSafe((Boolean)newValue);
				return;
			case 11:
				setOwnedSource((OCLExpression)newValue);
				return;
			case 12:
				getOwnedCoIterators().clear();
				getOwnedCoIterators().addAll((Collection<? extends IteratorVariable>)newValue);
				return;
			case 13:
				getOwnedIterators().clear();
				getOwnedIterators().addAll((Collection<? extends Variable>)newValue);
				return;
			case 14:
				setReferredIteration((Iteration)newValue);
				return;
			case 15:
				getOwnedBodies().clear();
				getOwnedBodies().addAll((Collection<? extends OCLExpression>)newValue);
				return;
			case 16:
				setOwnedResult((Variable)newValue);
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
	public void eUnset(int featureID)
	{
		switch (featureID)
		{
			case 0:
				getAnnotatingComments().clear();
				return;
			case 1:
				getOwnedAnnotations().clear();
				return;
			case 2:
				getOwnedComments().clear();
				return;
			case 3:
				getOwnedExtensions().clear();
				return;
			case 4:
				setName(NAME_EDEFAULT);
				return;
			case 6:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case 7:
				setType((Type)null);
				return;
			case 8:
				setTypeValue((Type)null);
				return;
			case 9:
				setIsImplicit(IS_IMPLICIT_EDEFAULT);
				return;
			case 10:
				setIsSafe(IS_SAFE_EDEFAULT);
				return;
			case 11:
				setOwnedSource((OCLExpression)null);
				return;
			case 12:
				getOwnedCoIterators().clear();
				return;
			case 13:
				getOwnedIterators().clear();
				return;
			case 14:
				setReferredIteration((Iteration)null);
				return;
			case 15:
				getOwnedBodies().clear();
				return;
			case 16:
				setOwnedResult((Variable)null);
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
	public boolean eIsSet(int featureID)
	{
		switch (featureID)
		{
			case 0:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case 1:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case 2:
				return ownedComments != null && !ownedComments.isEmpty();
			case 3:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case 4:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case 5:
				return isIsMany() != IS_MANY_EDEFAULT;
			case 6:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case 7:
				return type != null;
			case 8:
				return typeValue != null;
			case 9:
				return ((eFlags & IS_IMPLICIT_EFLAG) != 0) != IS_IMPLICIT_EDEFAULT;
			case 10:
				return ((eFlags & IS_SAFE_EFLAG) != 0) != IS_SAFE_EDEFAULT;
			case 11:
				return ownedSource != null;
			case 12:
				return ownedCoIterators != null && !ownedCoIterators.isEmpty();
			case 13:
				return ownedIterators != null && !ownedIterators.isEmpty();
			case 14:
				return referredIteration != null;
			case 15:
				return ownedBodies != null && !ownedBodies.isEmpty();
			case 16:
				return ownedResult != null;
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
		if (baseClass == CallExp.class)
		{
			switch (baseOperationID)
			{
				case 6: return 20;
				default: return super.eDerivedOperationID(baseOperationID, baseClass);
			}
		}
		if (baseClass == ReferringElement.class)
		{
			switch (baseOperationID)
			{
				case 0: return 16;
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
			case 0:
				return allOwnedElements();
			case 1:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case 2:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case 3:
				return isNonNull();
			case 4:
				return isNull();
			case 5:
				return validateTypeIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 20:
				return validateSafeSourceCanBeNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 7:
				return validateSafeSourceCannotBeMap((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 8:
				return validateTypeIsNotInvalid((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 9:
				return validateMatchingMapCoIterators((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 10:
				return validateMatchingOrderedCollectionCoIterators((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 11:
				return validateNoCoInitializers((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 12:
				return validateNoInitializers((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 13:
				return validateNoNotOrderedCollectionCoIterators((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 14:
				return validateSourceIsCollection((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 15:
				return validateSourceIsIterable((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 16:
				return getReferredElement();
			case 17:
				return validateBodyTypeConformsToResultType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 18:
				return validateOneInitializer((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 19:
				return validateSafeIteratorIsRequired((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 21:
				return validateTypeIsResultType((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case 22:
				return validateUnsafeSourceCanNotBeNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitIterateExp(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Element getReferredElement()
	{
		return getReferredIteration();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateTypeIsResultType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 * inv TypeIsResultType: true
		 */
		return ValueUtil.TRUE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateUnsafeSourceCanNotBeNull(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "IterateExp::UnsafeSourceCanNotBeNull";
		try {
			/**
			 *
			 * inv UnsafeSourceCanNotBeNull:
			 *   let severity : Integer[?] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[?] = not isSafe and
			 *         ownedIterators->exists(isRequired) implies
			 *         let sourceType : Type[?] = ownedSource?.type
			 *         in
			 *           if sourceType.oclIsKindOf(MapType)
			 *           then sourceType.oclAsType(MapType).keysAreNullFree
			 *           else sourceType.oclAsType(CollectionType).isNullFree
			 *           endif
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @Nullable IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.ITERATE_EXP___VALIDATE_UNSAFE_SOURCE_CAN_NOT_BE_NULL__DIAGNOSTICCHAIN_MAP);
			if (severity_0 == null) {
				throw new InvalidValueException("Null \'\'OclComparable\'\' rather than \'\'OclVoid\'\' value required");
			}
			final /*@Thrown*/ @Nullable Boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0);
			if (le == null) {
				throw new InvalidValueException("Null if condition");
			}
			/*@NonInvalid*/ @Nullable Boolean IF_le;
			if (le) {
				IF_le = ValueUtil.TRUE_VALUE;
			}
			else {
				/*@Caught*/ @Nullable Object CAUGHT_result;
				try {
					/*@Caught*/ @Nullable Object CAUGHT_and;
					try {
						/*@Caught*/ @Nullable Object CAUGHT_not;
						try {
							/*@Caught*/ @NonNull Object CAUGHT_isSafe;
							try {
								if (this == null) {
									throw new InvalidValueException("Null source for \'CallExp::isSafe\'");
								}
								final /*@Thrown*/ boolean isSafe = this.isIsSafe();
								CAUGHT_isSafe = isSafe;
							}
							catch (Exception e) {
								CAUGHT_isSafe = ValueUtil.createInvalidValue(e);
							}
							if (CAUGHT_isSafe instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_isSafe;
							}
							final /*@Thrown*/ @Nullable Boolean not;
							if (CAUGHT_isSafe == ValueUtil.FALSE_VALUE) {
								not = ValueUtil.TRUE_VALUE;
							}
							else {
								if (CAUGHT_isSafe == ValueUtil.TRUE_VALUE) {
									not = ValueUtil.FALSE_VALUE;
								}
								else {
									not = null;
								}
							}
							CAUGHT_not = not;
						}
						catch (Exception e) {
							CAUGHT_not = ValueUtil.createInvalidValue(e);
						}
						final /*@Thrown*/ @Nullable Boolean and;
						if (CAUGHT_not == ValueUtil.FALSE_VALUE) {
							and = ValueUtil.FALSE_VALUE;
						}
						else {
							/*@Caught*/ @Nullable Object CAUGHT_exists;
							try {
								if (this == null) {
									throw new InvalidValueException("Null source for \'LoopExp::ownedIterators\'");
								}
								@SuppressWarnings("null")
								final /*@Thrown*/ @NonNull List<Variable> ownedIterators = this.getOwnedIterators();
								final /*@Thrown*/ @NonNull OrderedSetValue BOXED_ownedIterators = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Variable, ownedIterators);
								/*@Thrown*/ @Nullable Object accumulator = ValueUtil.FALSE_VALUE;
								@NonNull Iterator<Object> ITERATOR__1 = BOXED_ownedIterators.iterator();
								/*@Thrown*/ @Nullable Boolean exists;
								while (true) {
									if (!ITERATOR__1.hasNext()) {
										if (accumulator == ValueUtil.FALSE_VALUE) {
											exists = ValueUtil.FALSE_VALUE;
										}
										else {
											throw (InvalidValueException)accumulator;
										}
										break;
									}
									@SuppressWarnings("null")
									/*@NonInvalid*/ @NonNull Variable _1 = (@NonNull Variable)ITERATOR__1.next();
									/**
									 * isRequired
									 */
									final /*@NonInvalid*/ boolean isRequired = _1.isIsRequired();
									//
									if (isRequired) {					// Normal successful body evaluation result
										exists = ValueUtil.TRUE_VALUE;
										break;														// Stop immediately
									}
									else if (!isRequired) {				// Normal unsuccessful body evaluation result
										;															// Carry on
									}
									else {															// Impossible badly typed result
										accumulator = new InvalidValueException(PivotMessages.NonBooleanBody, "exists");
									}
								}
								CAUGHT_exists = exists;
							}
							catch (Exception e) {
								CAUGHT_exists = ValueUtil.createInvalidValue(e);
							}
							if (CAUGHT_exists == ValueUtil.FALSE_VALUE) {
								and = ValueUtil.FALSE_VALUE;
							}
							else {
								if (CAUGHT_not instanceof InvalidValueException) {
									throw (InvalidValueException)CAUGHT_not;
								}
								if (CAUGHT_exists instanceof InvalidValueException) {
									throw (InvalidValueException)CAUGHT_exists;
								}
								if ((CAUGHT_not == null) || (CAUGHT_exists == null)) {
									and = null;
								}
								else {
									and = ValueUtil.TRUE_VALUE;
								}
							}
						}
						CAUGHT_and = and;
					}
					catch (Exception e) {
						CAUGHT_and = ValueUtil.createInvalidValue(e);
					}
					final /*@Thrown*/ @Nullable Boolean result;
					if (CAUGHT_and == ValueUtil.FALSE_VALUE) {
						result = ValueUtil.TRUE_VALUE;
					}
					else {
						/*@Caught*/ @NonNull Object CAUGHT_IF_oclIsKindOf;
						try {
							if (this == null) {
								throw new InvalidValueException("Null source for \'CallExp::ownedSource\'");
							}
							final /*@Thrown*/ @Nullable OCLExpression ownedSource = this.getOwnedSource();
							/*@Caught*/ @Nullable Object CAUGHT_ownedSource;
							try {
								CAUGHT_ownedSource = ownedSource;
							}
							catch (Exception e) {
								CAUGHT_ownedSource = ValueUtil.createInvalidValue(e);
							}
							final /*@NonInvalid*/ @NonNull Object type = CAUGHT_ownedSource == null;
							/*@Thrown*/ @Nullable Type safe_type_source;
							if (type == Boolean.TRUE) {
								safe_type_source = null;
							}
							else {
								assert ownedSource != null;
								final /*@Thrown*/ @Nullable Type type_0 = ownedSource.getType();
								safe_type_source = type_0;
							}
							final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_MapType_0 = idResolver.getClass(PivotTables.CLSSid_MapType, null);
							final /*@Thrown*/ @Nullable Boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, safe_type_source, TYP_MapType_0);
							if (oclIsKindOf == null) {
								throw new InvalidValueException("Null if condition");
							}
							/*@Thrown*/ boolean IF_oclIsKindOf;
							if (oclIsKindOf) {
								final /*@Thrown*/ @Nullable MapType oclAsType = (@Nullable MapType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, safe_type_source, TYP_MapType_0);
								if (oclAsType == null) {
									throw new InvalidValueException("Null source for \'MapType::keysAreNullFree\'");
								}
								final /*@Thrown*/ boolean keysAreNullFree = oclAsType.isKeysAreNullFree();
								IF_oclIsKindOf = keysAreNullFree;
							}
							else {
								final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_0 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
								final /*@Thrown*/ @Nullable CollectionType oclAsType_0 = (@Nullable CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, safe_type_source, TYP_CollectionType_0);
								if (oclAsType_0 == null) {
									throw new InvalidValueException("Null source for \'CollectionType::isNullFree\'");
								}
								final /*@Thrown*/ boolean isNullFree = oclAsType_0.isIsNullFree();
								IF_oclIsKindOf = isNullFree;
							}
							CAUGHT_IF_oclIsKindOf = IF_oclIsKindOf;
						}
						catch (Exception e) {
							CAUGHT_IF_oclIsKindOf = ValueUtil.createInvalidValue(e);
						}
						if (CAUGHT_IF_oclIsKindOf == ValueUtil.TRUE_VALUE) {
							result = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_and instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_and;
							}
							if (CAUGHT_IF_oclIsKindOf instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_IF_oclIsKindOf;
							}
							if (CAUGHT_and == null) {
								result = null;
							}
							else {
								result = ValueUtil.FALSE_VALUE;
							}
						}
					}
					CAUGHT_result = result;
				}
				catch (Exception e) {
					CAUGHT_result = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_this;
				try {
					if (this == null) {
						throw new InvalidValueException("Null \'\'OclAny\'\' rather than \'\'OclVoid\'\' value required");
					}
					CAUGHT_this = this;
				}
				catch (Exception e) {
					CAUGHT_this = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_severity_0;
				try {
					CAUGHT_severity_0 = severity_0;
				}
				catch (Exception e) {
					CAUGHT_severity_0 = ValueUtil.createInvalidValue(e);
				}
				if (CAUGHT_this instanceof InvalidValueException) {
					throw (InvalidValueException)CAUGHT_this;
				}
				if (CAUGHT_severity_0 instanceof InvalidValueException) {
					throw (InvalidValueException)CAUGHT_severity_0;
				}
				final /*@NonInvalid*/ @Nullable Boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, CAUGHT_this, (Object)null, diagnostics, context, (Object)null, CAUGHT_severity_0, CAUGHT_result, PivotTables.INT_0);
				IF_le = logDiagnostic;
			}
			return Boolean.TRUE == IF_le;
		}
		catch (Throwable e) {
			return ValueUtil.validationFailedDiagnostic(constraintName, this, diagnostics, context, e);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateBodyTypeConformsToResultType(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 * inv BodyTypeConformsToResultType: true
		 */
		return ValueUtil.TRUE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateOneInitializer(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 * inv OneInitializer: true
		 */
		return ValueUtil.TRUE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateSafeIteratorIsRequired(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "IterateExp::SafeIteratorIsRequired";
		try {
			/**
			 *
			 * inv SafeIteratorIsRequired:
			 *   let severity : Integer[?] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[?] = isSafe implies
			 *         ownedIterators->forAll(isRequired)
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @Nullable IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.ITERATE_EXP___VALIDATE_SAFE_ITERATOR_IS_REQUIRED__DIAGNOSTICCHAIN_MAP);
			if (severity_0 == null) {
				throw new InvalidValueException("Null \'\'OclComparable\'\' rather than \'\'OclVoid\'\' value required");
			}
			final /*@Thrown*/ @Nullable Boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0);
			if (le == null) {
				throw new InvalidValueException("Null if condition");
			}
			/*@NonInvalid*/ @Nullable Boolean IF_le;
			if (le) {
				IF_le = ValueUtil.TRUE_VALUE;
			}
			else {
				/*@Caught*/ @Nullable Object CAUGHT_result;
				try {
					/*@Caught*/ @NonNull Object CAUGHT_isSafe;
					try {
						if (this == null) {
							throw new InvalidValueException("Null source for \'CallExp::isSafe\'");
						}
						final /*@Thrown*/ boolean isSafe = this.isIsSafe();
						CAUGHT_isSafe = isSafe;
					}
					catch (Exception e) {
						CAUGHT_isSafe = ValueUtil.createInvalidValue(e);
					}
					final /*@Thrown*/ @Nullable Boolean result;
					if (CAUGHT_isSafe == ValueUtil.FALSE_VALUE) {
						result = ValueUtil.TRUE_VALUE;
					}
					else {
						/*@Caught*/ @Nullable Object CAUGHT_forAll;
						try {
							if (this == null) {
								throw new InvalidValueException("Null source for \'LoopExp::ownedIterators\'");
							}
							@SuppressWarnings("null")
							final /*@Thrown*/ @NonNull List<Variable> ownedIterators = this.getOwnedIterators();
							final /*@Thrown*/ @NonNull OrderedSetValue BOXED_ownedIterators = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Variable, ownedIterators);
							/*@Thrown*/ @Nullable Object accumulator = ValueUtil.TRUE_VALUE;
							@NonNull Iterator<Object> ITERATOR__1 = BOXED_ownedIterators.iterator();
							/*@Thrown*/ @Nullable Boolean forAll;
							while (true) {
								if (!ITERATOR__1.hasNext()) {
									if (accumulator == ValueUtil.TRUE_VALUE) {
										forAll = ValueUtil.TRUE_VALUE;
									}
									else {
										throw (InvalidValueException)accumulator;
									}
									break;
								}
								@SuppressWarnings("null")
								/*@NonInvalid*/ @NonNull Variable _1 = (@NonNull Variable)ITERATOR__1.next();
								/**
								 * isRequired
								 */
								final /*@NonInvalid*/ boolean isRequired = _1.isIsRequired();
								//
								if (!isRequired) {					// Normal unsuccessful body evaluation result
									forAll = ValueUtil.FALSE_VALUE;
									break;														// Stop immediately
								}
								else if (isRequired) {				// Normal successful body evaluation result
									;															// Carry on
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
						if (CAUGHT_forAll == ValueUtil.TRUE_VALUE) {
							result = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_isSafe instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_isSafe;
							}
							if (CAUGHT_forAll instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_forAll;
							}
							if (CAUGHT_forAll == null) {
								result = null;
							}
							else {
								result = ValueUtil.FALSE_VALUE;
							}
						}
					}
					CAUGHT_result = result;
				}
				catch (Exception e) {
					CAUGHT_result = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_this;
				try {
					if (this == null) {
						throw new InvalidValueException("Null \'\'OclAny\'\' rather than \'\'OclVoid\'\' value required");
					}
					CAUGHT_this = this;
				}
				catch (Exception e) {
					CAUGHT_this = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_severity_0;
				try {
					CAUGHT_severity_0 = severity_0;
				}
				catch (Exception e) {
					CAUGHT_severity_0 = ValueUtil.createInvalidValue(e);
				}
				if (CAUGHT_this instanceof InvalidValueException) {
					throw (InvalidValueException)CAUGHT_this;
				}
				if (CAUGHT_severity_0 instanceof InvalidValueException) {
					throw (InvalidValueException)CAUGHT_severity_0;
				}
				final /*@NonInvalid*/ @Nullable Boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, CAUGHT_this, (Object)null, diagnostics, context, (Object)null, CAUGHT_severity_0, CAUGHT_result, PivotTables.INT_0);
				IF_le = logDiagnostic;
			}
			return Boolean.TRUE == IF_le;
		}
		catch (Throwable e) {
			return ValueUtil.validationFailedDiagnostic(constraintName, this, diagnostics, context, e);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateSafeSourceCanBeNull(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		final @NonNull String constraintName = "IterateExp::SafeSourceCanBeNull";
		try {
			/**
			 *
			 * inv SafeSourceCanBeNull:
			 *   let severity : Integer[?] = constraintName.getSeverity()
			 *   in
			 *     if severity <= 0
			 *     then true
			 *     else
			 *       let result : Boolean[?] = isSafe implies
			 *         not let sourceType : Type[?] = ownedSource?.type
			 *         in
			 *           if sourceType.oclIsKindOf(MapType)
			 *           then sourceType.oclAsType(MapType).keysAreNullFree
			 *           else sourceType.oclAsType(CollectionType).isNullFree
			 *           endif
			 *       in
			 *         constraintName.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
			 *     endif
			 */
			final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
			final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
			final /*@NonInvalid*/ @Nullable IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotPackage.Literals.ITERATE_EXP___VALIDATE_SAFE_SOURCE_CAN_BE_NULL__DIAGNOSTICCHAIN_MAP);
			if (severity_0 == null) {
				throw new InvalidValueException("Null \'\'OclComparable\'\' rather than \'\'OclVoid\'\' value required");
			}
			final /*@Thrown*/ @Nullable Boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0);
			if (le == null) {
				throw new InvalidValueException("Null if condition");
			}
			/*@NonInvalid*/ @Nullable Boolean IF_le;
			if (le) {
				IF_le = ValueUtil.TRUE_VALUE;
			}
			else {
				/*@Caught*/ @Nullable Object CAUGHT_result;
				try {
					/*@Caught*/ @NonNull Object CAUGHT_isSafe;
					try {
						if (this == null) {
							throw new InvalidValueException("Null source for \'CallExp::isSafe\'");
						}
						final /*@Thrown*/ boolean isSafe = this.isIsSafe();
						CAUGHT_isSafe = isSafe;
					}
					catch (Exception e) {
						CAUGHT_isSafe = ValueUtil.createInvalidValue(e);
					}
					final /*@Thrown*/ @Nullable Boolean result;
					if (CAUGHT_isSafe == ValueUtil.FALSE_VALUE) {
						result = ValueUtil.TRUE_VALUE;
					}
					else {
						/*@Caught*/ @Nullable Object CAUGHT_not;
						try {
							/*@Caught*/ @NonNull Object CAUGHT_IF_oclIsKindOf;
							try {
								if (this == null) {
									throw new InvalidValueException("Null source for \'CallExp::ownedSource\'");
								}
								final /*@Thrown*/ @Nullable OCLExpression ownedSource = this.getOwnedSource();
								/*@Caught*/ @Nullable Object CAUGHT_ownedSource;
								try {
									CAUGHT_ownedSource = ownedSource;
								}
								catch (Exception e) {
									CAUGHT_ownedSource = ValueUtil.createInvalidValue(e);
								}
								final /*@NonInvalid*/ @NonNull Object type = CAUGHT_ownedSource == null;
								/*@Thrown*/ @Nullable Type safe_type_source;
								if (type == Boolean.TRUE) {
									safe_type_source = null;
								}
								else {
									assert ownedSource != null;
									final /*@Thrown*/ @Nullable Type type_0 = ownedSource.getType();
									safe_type_source = type_0;
								}
								final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_MapType_0 = idResolver.getClass(PivotTables.CLSSid_MapType, null);
								final /*@Thrown*/ @Nullable Boolean oclIsKindOf = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, safe_type_source, TYP_MapType_0);
								if (oclIsKindOf == null) {
									throw new InvalidValueException("Null if condition");
								}
								/*@Thrown*/ boolean IF_oclIsKindOf;
								if (oclIsKindOf) {
									final /*@Thrown*/ @Nullable MapType oclAsType = (@Nullable MapType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, safe_type_source, TYP_MapType_0);
									if (oclAsType == null) {
										throw new InvalidValueException("Null source for \'MapType::keysAreNullFree\'");
									}
									final /*@Thrown*/ boolean keysAreNullFree = oclAsType.isKeysAreNullFree();
									IF_oclIsKindOf = keysAreNullFree;
								}
								else {
									final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_0 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
									final /*@Thrown*/ @Nullable CollectionType oclAsType_0 = (@Nullable CollectionType)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, safe_type_source, TYP_CollectionType_0);
									if (oclAsType_0 == null) {
										throw new InvalidValueException("Null source for \'CollectionType::isNullFree\'");
									}
									final /*@Thrown*/ boolean isNullFree = oclAsType_0.isIsNullFree();
									IF_oclIsKindOf = isNullFree;
								}
								CAUGHT_IF_oclIsKindOf = IF_oclIsKindOf;
							}
							catch (Exception e) {
								CAUGHT_IF_oclIsKindOf = ValueUtil.createInvalidValue(e);
							}
							if (CAUGHT_IF_oclIsKindOf instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_IF_oclIsKindOf;
							}
							final /*@Thrown*/ @Nullable Boolean not;
							if (CAUGHT_IF_oclIsKindOf == ValueUtil.FALSE_VALUE) {
								not = ValueUtil.TRUE_VALUE;
							}
							else {
								if (CAUGHT_IF_oclIsKindOf == ValueUtil.TRUE_VALUE) {
									not = ValueUtil.FALSE_VALUE;
								}
								else {
									not = null;
								}
							}
							CAUGHT_not = not;
						}
						catch (Exception e) {
							CAUGHT_not = ValueUtil.createInvalidValue(e);
						}
						if (CAUGHT_not == ValueUtil.TRUE_VALUE) {
							result = ValueUtil.TRUE_VALUE;
						}
						else {
							if (CAUGHT_isSafe instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_isSafe;
							}
							if (CAUGHT_not instanceof InvalidValueException) {
								throw (InvalidValueException)CAUGHT_not;
							}
							if (CAUGHT_not == null) {
								result = null;
							}
							else {
								result = ValueUtil.FALSE_VALUE;
							}
						}
					}
					CAUGHT_result = result;
				}
				catch (Exception e) {
					CAUGHT_result = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_this;
				try {
					if (this == null) {
						throw new InvalidValueException("Null \'\'OclAny\'\' rather than \'\'OclVoid\'\' value required");
					}
					CAUGHT_this = this;
				}
				catch (Exception e) {
					CAUGHT_this = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_severity_0;
				try {
					CAUGHT_severity_0 = severity_0;
				}
				catch (Exception e) {
					CAUGHT_severity_0 = ValueUtil.createInvalidValue(e);
				}
				if (CAUGHT_this instanceof InvalidValueException) {
					throw (InvalidValueException)CAUGHT_this;
				}
				if (CAUGHT_severity_0 instanceof InvalidValueException) {
					throw (InvalidValueException)CAUGHT_severity_0;
				}
				final /*@NonInvalid*/ @Nullable Boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, constraintName, CAUGHT_this, (Object)null, diagnostics, context, (Object)null, CAUGHT_severity_0, CAUGHT_result, PivotTables.INT_0);
				IF_le = logDiagnostic;
			}
			return Boolean.TRUE == IF_le;
		}
		catch (Throwable e) {
			return ValueUtil.validationFailedDiagnostic(constraintName, this, diagnostics, context, e);
		}
	}

} //IterateExpImpl
