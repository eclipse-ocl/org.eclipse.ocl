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
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.collection.CollectionIsEmptyOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclIsKindOfOperation;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Loop Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.LoopExpImpl#getOwnedBody <em>Owned Body</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.LoopExpImpl#getOwnedIterators <em>Owned Iterators</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.LoopExpImpl#getReferredIteration <em>Referred Iteration</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class LoopExpImpl
extends CallExpImpl
implements LoopExp {

	/**
	 * The cached value of the '{@link #getOwnedBody() <em>Owned Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedBody()
	 * @generated
	 * @ordered
	 */
	protected OCLExpression ownedBody;

	/**
	 * The cached value of the '{@link #getOwnedIterators() <em>Owned Iterators</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedIterators()
	 * @generated
	 * @ordered
	 */
	protected EList<Variable> ownedIterators;

	/**
	 * The cached value of the '{@link #getReferredIteration() <em>Referred Iteration</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredIteration()
	 * @generated
	 * @ordered
	 */
	protected Iteration referredIteration;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LoopExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.LOOP_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OCLExpression getOwnedBody() {
		return ownedBody;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedBody(OCLExpression newOwnedBody, NotificationChain msgs)
	{
		OCLExpression oldOwnedBody = ownedBody;
		ownedBody = newOwnedBody;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.LOOP_EXP__OWNED_BODY, oldOwnedBody, newOwnedBody);
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
	public void setOwnedBody(OCLExpression newOwnedBody) {
		if (newOwnedBody != ownedBody)
		{
			NotificationChain msgs = null;
			if (ownedBody != null)
				msgs = ((InternalEObject)ownedBody).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.LOOP_EXP__OWNED_BODY, null, msgs);
			if (newOwnedBody != null)
				msgs = ((InternalEObject)newOwnedBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.LOOP_EXP__OWNED_BODY, null, msgs);
			msgs = basicSetOwnedBody(newOwnedBody, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.LOOP_EXP__OWNED_BODY, newOwnedBody, newOwnedBody));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("null")
	public @NonNull List<Variable> getOwnedIterators()
	{
		if (ownedIterators == null)
		{
			ownedIterators = new EObjectContainmentEList<Variable>(Variable.class, this, PivotPackage.LOOP_EXP__OWNED_ITERATORS);
		}
		return ownedIterators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Iteration getReferredIteration()
	{
		if (referredIteration != null && referredIteration.eIsProxy())
		{
			InternalEObject oldReferredIteration = (InternalEObject)referredIteration;
			referredIteration = (Iteration)eResolveProxy(oldReferredIteration);
			if (referredIteration != oldReferredIteration)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.LOOP_EXP__REFERRED_ITERATION, oldReferredIteration, referredIteration));
			}
		}
		return referredIteration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Iteration basicGetReferredIteration()
	{
		return referredIteration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredIteration(Iteration newReferredIteration)
	{
		Iteration oldReferredIteration = referredIteration;
		referredIteration = newReferredIteration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.LOOP_EXP__REFERRED_ITERATION, oldReferredIteration, referredIteration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateSourceIsCollection(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv SourceIsCollection:
		 *   let severity : Integer[1] = 'LoopExp::SourceIsCollection'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let
		 *         result : Boolean[1] = ownedSource?.type.oclIsKindOf(CollectionType)
		 *       in
		 *         'LoopExp::SourceIsCollection'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_LoopExp_c_c_SourceIsCollection);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_1;
		if (le) {
			symbol_1 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_result;
			try {
				final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_CollectionType_14 = idResolver.getClass(PivotTables.CLSSid_CollectionType, null);
				final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedSource = this.getOwnedSource();
				final /*@NonInvalid*/ @NonNull Object symbol_0 = ownedSource == null;
				/*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type safe_type_source;
				if (symbol_0 == Boolean.TRUE) {
					safe_type_source = null;
				}
				else {
					assert ownedSource != null;
					final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable Type type = ownedSource.getType();
					safe_type_source = type;
				}
				final /*@Thrown*/ boolean result = OclAnyOclIsKindOfOperation.INSTANCE.evaluate(executor, safe_type_source, TYP_CollectionType_14).booleanValue();
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_LoopExp_c_c_SourceIsCollection, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public boolean validateNoInitializers(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv NoInitializers:
		 *   let severity : Integer[1] = 'LoopExp::NoInitializers'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let
		 *         result : Boolean[?] = self.ownedIterators->forAll(
		 *           ownedInit->isEmpty())
		 *       in
		 *         'LoopExp::NoInitializers'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_LoopExp_c_c_NoInitializers);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_result;
			try {
				final /*@NonInvalid*/ java.util.@NonNull List<Variable> ownedIterators = this.getOwnedIterators();
				final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull CollectionValue BOXED_ownedIterators = idResolver.createOrderedSetOfAll(PivotTables.ORD_CLSSid_Variable, ownedIterators);
				/*@Thrown*/ java.lang.@Nullable Object accumulator = ValueUtil.TRUE_VALUE;
				@NonNull Iterator<Object> ITERATOR__1 = ValueUtil.lazyIterator(BOXED_ownedIterators);
				/*@Thrown*/ boolean result;
				while (true) {
					if (!ITERATOR__1.hasNext()) {
						if (accumulator == ValueUtil.TRUE_VALUE) {
							result = ValueUtil.TRUE_VALUE;
						}
						else {
							throw (InvalidValueException)accumulator;
						}
						break;
					}
					@SuppressWarnings("null")
					/*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Variable _1 = (Variable)ITERATOR__1.next();
					/**
					 * ownedInit->isEmpty()
					 */
					/*@Caught*/ @NonNull Object CAUGHT_isEmpty;
					try {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedInit = _1.getOwnedInit();
						final /*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull CollectionValue oclAsSet = OclAnyOclAsSetOperation.INSTANCE.evaluate(executor, PivotTables.SET_CLSSid_OCLExpression, ownedInit);
						final /*@Thrown*/ boolean isEmpty = CollectionIsEmptyOperation.INSTANCE.evaluate(oclAsSet).booleanValue();
						CAUGHT_isEmpty = isEmpty;
					}
					catch (Exception e) {
						CAUGHT_isEmpty = ValueUtil.createInvalidValue(e);
					}
					//
					if (CAUGHT_isEmpty == ValueUtil.FALSE_VALUE) {					// Normal unsuccessful body evaluation result
						result = ValueUtil.FALSE_VALUE;
						break;														// Stop immediately
					}
					else if (CAUGHT_isEmpty == ValueUtil.TRUE_VALUE) {				// Normal successful body evaluation result
						;															// Carry on
					}
					else if (CAUGHT_isEmpty instanceof InvalidValueException) {		// Abnormal exception evaluation result
						accumulator = CAUGHT_isEmpty;									// Cache an exception failure
					}
					else {															// Impossible badly typed result
						accumulator = new InvalidValueException(PivotMessages.NonBooleanBody, "forAll");
					}
				}
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_LoopExp_c_c_NoInitializers, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
			case PivotPackage.LOOP_EXP__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.LOOP_EXP__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.LOOP_EXP__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.LOOP_EXP__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.LOOP_EXP__OWNED_SOURCE:
				return basicSetOwnedSource(null, msgs);
			case PivotPackage.LOOP_EXP__OWNED_BODY:
				return basicSetOwnedBody(null, msgs);
			case PivotPackage.LOOP_EXP__OWNED_ITERATORS:
				return ((InternalEList<?>)getOwnedIterators()).basicRemove(otherEnd, msgs);
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
			case PivotPackage.LOOP_EXP__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.LOOP_EXP__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.LOOP_EXP__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.LOOP_EXP__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.LOOP_EXP__NAME:
				return getName();
			case PivotPackage.LOOP_EXP__IS_MANY:
				return isIsMany();
			case PivotPackage.LOOP_EXP__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.LOOP_EXP__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case PivotPackage.LOOP_EXP__TYPE_VALUE:
				return getTypeValue();
			case PivotPackage.LOOP_EXP__IS_IMPLICIT:
				return isIsImplicit();
			case PivotPackage.LOOP_EXP__IS_SAFE:
				return isIsSafe();
			case PivotPackage.LOOP_EXP__OWNED_SOURCE:
				return getOwnedSource();
			case PivotPackage.LOOP_EXP__OWNED_BODY:
				return getOwnedBody();
			case PivotPackage.LOOP_EXP__OWNED_ITERATORS:
				return getOwnedIterators();
			case PivotPackage.LOOP_EXP__REFERRED_ITERATION:
				if (resolve) return getReferredIteration();
				return basicGetReferredIteration();
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
			case PivotPackage.LOOP_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.LOOP_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.LOOP_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.LOOP_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.LOOP_EXP__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.LOOP_EXP__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.LOOP_EXP__TYPE:
				setType((Type)newValue);
				return;
			case PivotPackage.LOOP_EXP__TYPE_VALUE:
				setTypeValue((Type)newValue);
				return;
			case PivotPackage.LOOP_EXP__IS_IMPLICIT:
				setIsImplicit((Boolean)newValue);
				return;
			case PivotPackage.LOOP_EXP__IS_SAFE:
				setIsSafe((Boolean)newValue);
				return;
			case PivotPackage.LOOP_EXP__OWNED_SOURCE:
				setOwnedSource((OCLExpression)newValue);
				return;
			case PivotPackage.LOOP_EXP__OWNED_BODY:
				setOwnedBody((OCLExpression)newValue);
				return;
			case PivotPackage.LOOP_EXP__OWNED_ITERATORS:
				getOwnedIterators().clear();
				getOwnedIterators().addAll((Collection<? extends Variable>)newValue);
				return;
			case PivotPackage.LOOP_EXP__REFERRED_ITERATION:
				setReferredIteration((Iteration)newValue);
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
			case PivotPackage.LOOP_EXP__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.LOOP_EXP__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.LOOP_EXP__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.LOOP_EXP__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.LOOP_EXP__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.LOOP_EXP__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.LOOP_EXP__TYPE:
				setType((Type)null);
				return;
			case PivotPackage.LOOP_EXP__TYPE_VALUE:
				setTypeValue((Type)null);
				return;
			case PivotPackage.LOOP_EXP__IS_IMPLICIT:
				setIsImplicit(IS_IMPLICIT_EDEFAULT);
				return;
			case PivotPackage.LOOP_EXP__IS_SAFE:
				setIsSafe(IS_SAFE_EDEFAULT);
				return;
			case PivotPackage.LOOP_EXP__OWNED_SOURCE:
				setOwnedSource((OCLExpression)null);
				return;
			case PivotPackage.LOOP_EXP__OWNED_BODY:
				setOwnedBody((OCLExpression)null);
				return;
			case PivotPackage.LOOP_EXP__OWNED_ITERATORS:
				getOwnedIterators().clear();
				return;
			case PivotPackage.LOOP_EXP__REFERRED_ITERATION:
				setReferredIteration((Iteration)null);
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
			case PivotPackage.LOOP_EXP__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.LOOP_EXP__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.LOOP_EXP__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.LOOP_EXP__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.LOOP_EXP__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.LOOP_EXP__IS_MANY:
				return isIsMany() != IS_MANY_EDEFAULT;
			case PivotPackage.LOOP_EXP__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.LOOP_EXP__TYPE:
				return type != null;
			case PivotPackage.LOOP_EXP__TYPE_VALUE:
				return typeValue != null;
			case PivotPackage.LOOP_EXP__IS_IMPLICIT:
				return ((eFlags & IS_IMPLICIT_EFLAG) != 0) != IS_IMPLICIT_EDEFAULT;
			case PivotPackage.LOOP_EXP__IS_SAFE:
				return ((eFlags & IS_SAFE_EFLAG) != 0) != IS_SAFE_EDEFAULT;
			case PivotPackage.LOOP_EXP__OWNED_SOURCE:
				return ownedSource != null;
			case PivotPackage.LOOP_EXP__OWNED_BODY:
				return ownedBody != null;
			case PivotPackage.LOOP_EXP__OWNED_ITERATORS:
				return ownedIterators != null && !ownedIterators.isEmpty();
			case PivotPackage.LOOP_EXP__REFERRED_ITERATION:
				return referredIteration != null;
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
			case PivotPackage.LOOP_EXP___ALL_OWNED_ELEMENTS:
				return allOwnedElements();
			case PivotPackage.LOOP_EXP___GET_VALUE__TYPE_STRING:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case PivotPackage.LOOP_EXP___COMPATIBLE_BODY__VALUESPECIFICATION:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case PivotPackage.LOOP_EXP___IS_NON_NULL:
				return isNonNull();
			case PivotPackage.LOOP_EXP___IS_NULL:
				return isNull();
			case PivotPackage.LOOP_EXP___VALIDATE_TYPE_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.LOOP_EXP___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsNotInvalid((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.LOOP_EXP___VALIDATE_NO_INITIALIZERS__DIAGNOSTICCHAIN_MAP:
				return validateNoInitializers((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.LOOP_EXP___VALIDATE_SOURCE_IS_COLLECTION__DIAGNOSTICCHAIN_MAP:
				return validateSourceIsCollection((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitLoopExp(this);
	}
} //LoopExpImpl
