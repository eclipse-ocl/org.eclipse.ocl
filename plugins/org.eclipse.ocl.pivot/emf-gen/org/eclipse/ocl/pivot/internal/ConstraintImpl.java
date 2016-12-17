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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.State;
import org.eclipse.ocl.pivot.Transition;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ConstraintImpl#getConstrainedElements <em>Constrained Elements</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ConstraintImpl#getContext <em>Context</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ConstraintImpl#isIsCallable <em>Is Callable</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ConstraintImpl#getOwnedSpecification <em>Owned Specification</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ConstraintImpl#getOwningPostContext <em>Owning Post Context</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ConstraintImpl#getOwningPreContext <em>Owning Pre Context</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ConstraintImpl#getOwningState <em>Owning State</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ConstraintImpl#getOwningTransition <em>Owning Transition</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ConstraintImpl#getRedefinedConstraints <em>Redefined Constraints</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConstraintImpl
		extends NamedElementImpl
		implements Constraint {

	/**
	 * The cached value of the '{@link #getConstrainedElements() <em>Constrained Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstrainedElements()
	 * @generated
	 * @ordered
	 */
	protected EList<Element> constrainedElements;

	/**
	 * The default value of the '{@link #isIsCallable() <em>Is Callable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsCallable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_CALLABLE_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsCallable() <em>Is Callable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsCallable()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_CALLABLE_EFLAG = 1 << 8;

	/**
	 * The cached value of the '{@link #getOwnedSpecification() <em>Owned Specification</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSpecification()
	 * @generated
	 * @ordered
	 */
	protected LanguageExpression ownedSpecification;

	/**
	 * The cached value of the '{@link #getRedefinedConstraints() <em>Redefined Constraints</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRedefinedConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<Constraint> redefinedConstraints;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("null")
	@Override
	public @NonNull List<Element> getConstrainedElements()
	{
		if (constrainedElements == null)
		{
			constrainedElements = new EObjectResolvingEList<Element>(Element.class, this, PivotPackage.CONSTRAINT__CONSTRAINED_ELEMENTS);
		}
		return constrainedElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LanguageExpression getOwnedSpecification() {
		return ownedSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedSpecification(LanguageExpression newOwnedSpecification, NotificationChain msgs)
	{
		LanguageExpression oldOwnedSpecification = ownedSpecification;
		ownedSpecification = newOwnedSpecification;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.CONSTRAINT__OWNED_SPECIFICATION, oldOwnedSpecification, newOwnedSpecification);
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
	public void setOwnedSpecification(LanguageExpression newOwnedSpecification)
	{
		if (newOwnedSpecification != ownedSpecification)
		{
			NotificationChain msgs = null;
			if (ownedSpecification != null)
				msgs = ((InternalEObject)ownedSpecification).eInverseRemove(this, PivotPackage.LANGUAGE_EXPRESSION__OWNING_CONSTRAINT, LanguageExpression.class, msgs);
			if (newOwnedSpecification != null)
				msgs = ((InternalEObject)newOwnedSpecification).eInverseAdd(this, PivotPackage.LANGUAGE_EXPRESSION__OWNING_CONSTRAINT, LanguageExpression.class, msgs);
			msgs = basicSetOwnedSpecification(newOwnedSpecification, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.CONSTRAINT__OWNED_SPECIFICATION, newOwnedSpecification, newOwnedSpecification));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Operation getOwningPostContext()
	{
		if (eContainerFeatureID() != PivotPackage.CONSTRAINT__OWNING_POST_CONTEXT) return null;
		return (Operation)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningPostContext(Operation newOwningPostContext, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningPostContext, PivotPackage.CONSTRAINT__OWNING_POST_CONTEXT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningPostContext(Operation newOwningPostContext)
	{
		if (newOwningPostContext != eInternalContainer() || (eContainerFeatureID() != PivotPackage.CONSTRAINT__OWNING_POST_CONTEXT && newOwningPostContext != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningPostContext))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningPostContext != null)
				msgs = ((InternalEObject)newOwningPostContext).eInverseAdd(this, PivotPackage.OPERATION__OWNED_POSTCONDITIONS, Operation.class, msgs);
			msgs = basicSetOwningPostContext(newOwningPostContext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.CONSTRAINT__OWNING_POST_CONTEXT, newOwningPostContext, newOwningPostContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Operation getOwningPreContext()
	{
		if (eContainerFeatureID() != PivotPackage.CONSTRAINT__OWNING_PRE_CONTEXT) return null;
		return (Operation)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningPreContext(Operation newOwningPreContext, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningPreContext, PivotPackage.CONSTRAINT__OWNING_PRE_CONTEXT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningPreContext(Operation newOwningPreContext)
	{
		if (newOwningPreContext != eInternalContainer() || (eContainerFeatureID() != PivotPackage.CONSTRAINT__OWNING_PRE_CONTEXT && newOwningPreContext != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningPreContext))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningPreContext != null)
				msgs = ((InternalEObject)newOwningPreContext).eInverseAdd(this, PivotPackage.OPERATION__OWNED_PRECONDITIONS, Operation.class, msgs);
			msgs = basicSetOwningPreContext(newOwningPreContext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.CONSTRAINT__OWNING_PRE_CONTEXT, newOwningPreContext, newOwningPreContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsCallable(boolean newIsCallable)
	{
		boolean oldIsCallable = (eFlags & IS_CALLABLE_EFLAG) != 0;
		if (newIsCallable) eFlags |= IS_CALLABLE_EFLAG; else eFlags &= ~IS_CALLABLE_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.CONSTRAINT__IS_CALLABLE, oldIsCallable, newIsCallable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public State getOwningState()
	{
		if (eContainerFeatureID() != PivotPackage.CONSTRAINT__OWNING_STATE) return null;
		return (State)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningState(State newOwningState, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningState, PivotPackage.CONSTRAINT__OWNING_STATE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningState(State newOwningState)
	{
		if (newOwningState != eInternalContainer() || (eContainerFeatureID() != PivotPackage.CONSTRAINT__OWNING_STATE && newOwningState != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningState))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningState != null)
				msgs = ((InternalEObject)newOwningState).eInverseAdd(this, PivotPackage.STATE__OWNED_STATE_INVARIANT, State.class, msgs);
			msgs = basicSetOwningState(newOwningState, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.CONSTRAINT__OWNING_STATE, newOwningState, newOwningState));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Transition getOwningTransition()
	{
		if (eContainerFeatureID() != PivotPackage.CONSTRAINT__OWNING_TRANSITION) return null;
		return (Transition)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningTransition(Transition newOwningTransition, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningTransition, PivotPackage.CONSTRAINT__OWNING_TRANSITION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningTransition(Transition newOwningTransition)
	{
		if (newOwningTransition != eInternalContainer() || (eContainerFeatureID() != PivotPackage.CONSTRAINT__OWNING_TRANSITION && newOwningTransition != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningTransition))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningTransition != null)
				msgs = ((InternalEObject)newOwningTransition).eInverseAdd(this, PivotPackage.TRANSITION__OWNED_GUARD, Transition.class, msgs);
			msgs = basicSetOwningTransition(newOwningTransition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.CONSTRAINT__OWNING_TRANSITION, newOwningTransition, newOwningTransition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Constraint> getRedefinedConstraints()
	{
		if (redefinedConstraints == null)
		{
			redefinedConstraints = new EObjectResolvingEList<Constraint>(Constraint.class, this, PivotPackage.CONSTRAINT__REDEFINED_CONSTRAINTS);
		}
		return redefinedConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateUniqueName(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv UniqueName:
		 *   let severity : Integer[1] = 'Constraint::UniqueName'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let status : Boolean[1] = true
		 *       in
		 *         'Constraint::UniqueName'.logDiagnostic(self, null, diagnostics, context, null, severity, status, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_Constraint_c_c_UniqueName);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_Constraint_c_c_UniqueName, this, (Object)null, diagnostics, context, (Object)null, severity_0, ValueUtil.TRUE_VALUE, PivotTables.INT_0).booleanValue();
			symbol_0 = logDiagnostic;
		}
		return Boolean.TRUE == symbol_0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case PivotPackage.CONSTRAINT__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.CONSTRAINT__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.CONSTRAINT__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.CONSTRAINT__OWNED_SPECIFICATION:
				if (ownedSpecification != null)
					msgs = ((InternalEObject)ownedSpecification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.CONSTRAINT__OWNED_SPECIFICATION, null, msgs);
				return basicSetOwnedSpecification((LanguageExpression)otherEnd, msgs);
			case PivotPackage.CONSTRAINT__OWNING_POST_CONTEXT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningPostContext((Operation)otherEnd, msgs);
			case PivotPackage.CONSTRAINT__OWNING_PRE_CONTEXT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningPreContext((Operation)otherEnd, msgs);
			case PivotPackage.CONSTRAINT__OWNING_STATE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningState((State)otherEnd, msgs);
			case PivotPackage.CONSTRAINT__OWNING_TRANSITION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningTransition((Transition)otherEnd, msgs);
		}
		return eDynamicInverseAdd(otherEnd, featureID, msgs);
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
			case PivotPackage.CONSTRAINT__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.CONSTRAINT__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.CONSTRAINT__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.CONSTRAINT__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.CONSTRAINT__OWNED_SPECIFICATION:
				return basicSetOwnedSpecification(null, msgs);
			case PivotPackage.CONSTRAINT__OWNING_POST_CONTEXT:
				return basicSetOwningPostContext(null, msgs);
			case PivotPackage.CONSTRAINT__OWNING_PRE_CONTEXT:
				return basicSetOwningPreContext(null, msgs);
			case PivotPackage.CONSTRAINT__OWNING_STATE:
				return basicSetOwningState(null, msgs);
			case PivotPackage.CONSTRAINT__OWNING_TRANSITION:
				return basicSetOwningTransition(null, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
	{
		switch (eContainerFeatureID())
		{
			case PivotPackage.CONSTRAINT__OWNING_POST_CONTEXT:
				return eInternalContainer().eInverseRemove(this, PivotPackage.OPERATION__OWNED_POSTCONDITIONS, Operation.class, msgs);
			case PivotPackage.CONSTRAINT__OWNING_PRE_CONTEXT:
				return eInternalContainer().eInverseRemove(this, PivotPackage.OPERATION__OWNED_PRECONDITIONS, Operation.class, msgs);
			case PivotPackage.CONSTRAINT__OWNING_STATE:
				return eInternalContainer().eInverseRemove(this, PivotPackage.STATE__OWNED_STATE_INVARIANT, State.class, msgs);
			case PivotPackage.CONSTRAINT__OWNING_TRANSITION:
				return eInternalContainer().eInverseRemove(this, PivotPackage.TRANSITION__OWNED_GUARD, Transition.class, msgs);
		}
		return eDynamicBasicRemoveFromContainer(msgs);
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
			case PivotPackage.CONSTRAINT__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.CONSTRAINT__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.CONSTRAINT__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.CONSTRAINT__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.CONSTRAINT__NAME:
				return getName();
			case PivotPackage.CONSTRAINT__CONSTRAINED_ELEMENTS:
				return getConstrainedElements();
			case PivotPackage.CONSTRAINT__CONTEXT:
				return getContext();
			case PivotPackage.CONSTRAINT__IS_CALLABLE:
				return isIsCallable();
			case PivotPackage.CONSTRAINT__OWNED_SPECIFICATION:
				return getOwnedSpecification();
			case PivotPackage.CONSTRAINT__OWNING_POST_CONTEXT:
				return getOwningPostContext();
			case PivotPackage.CONSTRAINT__OWNING_PRE_CONTEXT:
				return getOwningPreContext();
			case PivotPackage.CONSTRAINT__OWNING_STATE:
				return getOwningState();
			case PivotPackage.CONSTRAINT__OWNING_TRANSITION:
				return getOwningTransition();
			case PivotPackage.CONSTRAINT__REDEFINED_CONSTRAINTS:
				return getRedefinedConstraints();
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
			case PivotPackage.CONSTRAINT__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.CONSTRAINT__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.CONSTRAINT__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.CONSTRAINT__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.CONSTRAINT__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.CONSTRAINT__CONSTRAINED_ELEMENTS:
				getConstrainedElements().clear();
				getConstrainedElements().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.CONSTRAINT__IS_CALLABLE:
				setIsCallable((Boolean)newValue);
				return;
			case PivotPackage.CONSTRAINT__OWNED_SPECIFICATION:
				setOwnedSpecification((LanguageExpression)newValue);
				return;
			case PivotPackage.CONSTRAINT__OWNING_POST_CONTEXT:
				setOwningPostContext((Operation)newValue);
				return;
			case PivotPackage.CONSTRAINT__OWNING_PRE_CONTEXT:
				setOwningPreContext((Operation)newValue);
				return;
			case PivotPackage.CONSTRAINT__OWNING_STATE:
				setOwningState((State)newValue);
				return;
			case PivotPackage.CONSTRAINT__OWNING_TRANSITION:
				setOwningTransition((Transition)newValue);
				return;
			case PivotPackage.CONSTRAINT__REDEFINED_CONSTRAINTS:
				getRedefinedConstraints().clear();
				getRedefinedConstraints().addAll((Collection<? extends Constraint>)newValue);
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
			case PivotPackage.CONSTRAINT__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.CONSTRAINT__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.CONSTRAINT__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.CONSTRAINT__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.CONSTRAINT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.CONSTRAINT__CONSTRAINED_ELEMENTS:
				getConstrainedElements().clear();
				return;
			case PivotPackage.CONSTRAINT__IS_CALLABLE:
				setIsCallable(IS_CALLABLE_EDEFAULT);
				return;
			case PivotPackage.CONSTRAINT__OWNED_SPECIFICATION:
				setOwnedSpecification((LanguageExpression)null);
				return;
			case PivotPackage.CONSTRAINT__OWNING_POST_CONTEXT:
				setOwningPostContext((Operation)null);
				return;
			case PivotPackage.CONSTRAINT__OWNING_PRE_CONTEXT:
				setOwningPreContext((Operation)null);
				return;
			case PivotPackage.CONSTRAINT__OWNING_STATE:
				setOwningState((State)null);
				return;
			case PivotPackage.CONSTRAINT__OWNING_TRANSITION:
				setOwningTransition((Transition)null);
				return;
			case PivotPackage.CONSTRAINT__REDEFINED_CONSTRAINTS:
				getRedefinedConstraints().clear();
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
			case PivotPackage.CONSTRAINT__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.CONSTRAINT__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.CONSTRAINT__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.CONSTRAINT__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.CONSTRAINT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.CONSTRAINT__CONSTRAINED_ELEMENTS:
				return constrainedElements != null && !constrainedElements.isEmpty();
			case PivotPackage.CONSTRAINT__CONTEXT:
				return getContext() != null;
			case PivotPackage.CONSTRAINT__IS_CALLABLE:
				return ((eFlags & IS_CALLABLE_EFLAG) != 0) != IS_CALLABLE_EDEFAULT;
			case PivotPackage.CONSTRAINT__OWNED_SPECIFICATION:
				return ownedSpecification != null;
			case PivotPackage.CONSTRAINT__OWNING_POST_CONTEXT:
				return getOwningPostContext() != null;
			case PivotPackage.CONSTRAINT__OWNING_PRE_CONTEXT:
				return getOwningPreContext() != null;
			case PivotPackage.CONSTRAINT__OWNING_STATE:
				return getOwningState() != null;
			case PivotPackage.CONSTRAINT__OWNING_TRANSITION:
				return getOwningTransition() != null;
			case PivotPackage.CONSTRAINT__REDEFINED_CONSTRAINTS:
				return redefinedConstraints != null && !redefinedConstraints.isEmpty();
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
			case PivotPackage.CONSTRAINT___ALL_OWNED_ELEMENTS:
				return allOwnedElements();
			case PivotPackage.CONSTRAINT___GET_VALUE__TYPE_STRING:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case PivotPackage.CONSTRAINT___VALIDATE_UNIQUE_NAME__DIAGNOSTICCHAIN_MAP:
				return validateUniqueName((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitConstraint(this);
	}

	@Override
	public Namespace getContext() {
		for (EObject context = eContainer(); context != null; context = context.eContainer()) {
			if (context instanceof Namespace) {
				return (Namespace) context;
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsCallable()
	{
		return (eFlags & IS_CALLABLE_EFLAG) != 0;
	}

	public boolean isSetContext() {
		return getContext() != null;
	}


	@Override
	public String toString()
	{
		return super.toString();
	}
} //ConstraintImpl
