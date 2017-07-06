/*******************************************************************************
 * Copyright (c) 2010, 2017 Willink Transformations and others.
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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.ParameterTypes;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameters;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ValueSpecification;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.ParametersId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.logical.BooleanImpliesOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation;
import org.eclipse.ocl.pivot.library.oclany.OclComparableLessThanEqualOperation;
import org.eclipse.ocl.pivot.library.string.CGStringGetSeverityOperation;
import org.eclipse.ocl.pivot.library.string.CGStringLogDiagnosticOperation;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getOwnedConstraints <em>Owned Constraints</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getOwnedBindings <em>Owned Bindings</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getOwnedSignature <em>Owned Signature</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getUnspecializedElement <em>Unspecialized Element</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getBodyExpression <em>Body Expression</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#isIsInvalidating <em>Is Invalidating</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#isIsTransient <em>Is Transient</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#isIsTypeof <em>Is Typeof</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#isIsValidating <em>Is Validating</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getOwnedParameters <em>Owned Parameters</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getOwnedPostconditions <em>Owned Postconditions</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getOwnedPreconditions <em>Owned Preconditions</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getOwningClass <em>Owning Class</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getPrecedence <em>Precedence</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getRaisedExceptions <em>Raised Exceptions</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.OperationImpl#getRedefinedOperations <em>Redefined Operations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class OperationImpl
extends FeatureImpl
implements Operation {

	/**
	 * The cached value of the '{@link #getOwnedConstraints() <em>Owned Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<Constraint> ownedConstraints;

	/**
	 * The cached value of the '{@link #getOwnedBindings() <em>Owned Bindings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedBindings()
	 * @generated
	 * @ordered
	 */
	protected EList<TemplateBinding> ownedBindings;

	/**
	 * The cached value of the '{@link #getOwnedSignature() <em>Owned Signature</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSignature()
	 * @generated
	 * @ordered
	 */
	protected TemplateSignature ownedSignature;

	/**
	 * The cached value of the '{@link #getUnspecializedElement() <em>Unspecialized Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnspecializedElement()
	 * @generated
	 * @ordered
	 */
	protected TemplateableElement unspecializedElement;

	/**
	 * The cached value of the '{@link #getBodyExpression() <em>Body Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBodyExpression()
	 * @generated
	 * @ordered
	 */
	protected LanguageExpression bodyExpression;

	/**
	 * The default value of the '{@link #isIsInvalidating() <em>Is Invalidating</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInvalidating()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_INVALIDATING_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsInvalidating() <em>Is Invalidating</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsInvalidating()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_INVALIDATING_EFLAG = 1 << 10;

	/**
	 * The default value of the '{@link #isIsTransient() <em>Is Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @see #isIsTransient()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_TRANSIENT_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsTransient() <em>Is Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @see #isIsTransient()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_TRANSIENT_EFLAG = 1 << 11;

	/**
	 * The default value of the '{@link #isIsTypeof() <em>Is Typeof</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsTypeof()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_TYPEOF_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsTypeof() <em>Is Typeof</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsTypeof()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_TYPEOF_EFLAG = 1 << 12;

	/**
	 * The default value of the '{@link #isIsValidating() <em>Is Validating</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsValidating()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_VALIDATING_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isIsValidating() <em>Is Validating</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsValidating()
	 * @generated
	 * @ordered
	 */
	protected static final int IS_VALIDATING_EFLAG = 1 << 13;

	/**
	 * The cached value of the '{@link #getOwnedParameters() <em>Owned Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Parameter> ownedParameters;

	/**
	 * The cached value of the '{@link #getOwnedPostconditions() <em>Owned Postconditions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPostconditions()
	 * @generated
	 * @ordered
	 */
	protected EList<Constraint> ownedPostconditions;

	/**
	 * The cached value of the '{@link #getOwnedPreconditions() <em>Owned Preconditions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPreconditions()
	 * @generated
	 * @ordered
	 */
	protected EList<Constraint> ownedPreconditions;

	/**
	 * The cached value of the '{@link #getPrecedence() <em>Precedence</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecedence()
	 * @generated
	 * @ordered
	 */
	protected Precedence precedence;

	/**
	 * The cached value of the '{@link #getRaisedExceptions() <em>Raised Exceptions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRaisedExceptions()
	 * @generated
	 * @ordered
	 */
	protected EList<Type> raisedExceptions;

	/**
	 * The cached value of the '{@link #getRedefinedOperations() <em>Redefined Operations</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRedefinedOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<Operation> redefinedOperations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.OPERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("null")
	public @NonNull List<Constraint> getOwnedConstraints()
	{
		if (ownedConstraints == null)
		{
			ownedConstraints = new EObjectContainmentEList<Constraint>(Constraint.class, this, PivotPackage.OPERATION__OWNED_CONSTRAINTS);
		}
		return ownedConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<TemplateBinding> getOwnedBindings()
	{
		if (ownedBindings == null)
		{
			ownedBindings = new EObjectContainmentWithInverseEList<TemplateBinding>(TemplateBinding.class, this, PivotPackage.OPERATION__OWNED_BINDINGS, PivotPackage.TEMPLATE_BINDING__OWNING_ELEMENT);
		}
		return ownedBindings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("null")
	public @NonNull List<Type> getRaisedExceptions()
	{
		if (raisedExceptions == null)
		{
			raisedExceptions = new EObjectResolvingEList<Type>(Type.class, this, PivotPackage.OPERATION__RAISED_EXCEPTIONS);
		}
		return raisedExceptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("null")
	@Override
	public @NonNull List<Operation> getRedefinedOperations()
	{
		if (redefinedOperations == null)
		{
			redefinedOperations = new EObjectResolvingEList<Operation>(Operation.class, this, PivotPackage.OPERATION__REDEFINED_OPERATIONS);
		}
		return redefinedOperations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("null")
	public @NonNull List<Parameter> getOwnedParameters()
	{
		if (ownedParameters == null)
		{
			ownedParameters = new EObjectContainmentWithInverseEList<Parameter>(Parameter.class, this, PivotPackage.OPERATION__OWNED_PARAMETERS, PivotPackage.PARAMETER__OWNING_OPERATION);
		}
		return ownedParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("null")
	@Override
	public @NonNull List<Constraint> getOwnedPostconditions()
	{
		if (ownedPostconditions == null)
		{
			ownedPostconditions = new EObjectContainmentWithInverseEList<Constraint>(Constraint.class, this, PivotPackage.OPERATION__OWNED_POSTCONDITIONS, PivotPackage.CONSTRAINT__OWNING_POST_CONTEXT);
		}
		return ownedPostconditions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("null")
	@Override
	public @NonNull List<Constraint> getOwnedPreconditions()
	{
		if (ownedPreconditions == null)
		{
			ownedPreconditions = new EObjectContainmentWithInverseEList<Constraint>(Constraint.class, this, PivotPackage.OPERATION__OWNED_PRECONDITIONS, PivotPackage.CONSTRAINT__OWNING_PRE_CONTEXT);
		}
		return ownedPreconditions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplateSignature getOwnedSignature() {
		return ownedSignature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedSignature(TemplateSignature newOwnedSignature, NotificationChain msgs)
	{
		TemplateSignature oldOwnedSignature = ownedSignature;
		ownedSignature = newOwnedSignature;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.OPERATION__OWNED_SIGNATURE, oldOwnedSignature, newOwnedSignature);
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
	public void setOwnedSignature(
			TemplateSignature newOwnedSignature) {
		if (newOwnedSignature != ownedSignature)
		{
			NotificationChain msgs = null;
			if (ownedSignature != null)
				msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, PivotPackage.TEMPLATE_SIGNATURE__OWNING_ELEMENT, TemplateSignature.class, msgs);
			if (newOwnedSignature != null)
				msgs = ((InternalEObject)newOwnedSignature).eInverseAdd(this, PivotPackage.TEMPLATE_SIGNATURE__OWNING_ELEMENT, TemplateSignature.class, msgs);
			msgs = basicSetOwnedSignature(newOwnedSignature, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.OPERATION__OWNED_SIGNATURE, newOwnedSignature, newOwnedSignature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public TemplateableElement getUnspecializedElement()
	{
		//		throw new UnsupportedOperationException();	// FIXME Eliminate this feature once Acceleo bug 349278 fixed
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void setUnspecializedElement(TemplateableElement newUnspecializedElement)
	{
		throw new UnsupportedOperationException();	// FIXME Eliminate this feature once Acceleo bug 349278 fixed
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Precedence getPrecedence() {
		if (precedence != null && precedence.eIsProxy())
		{
			InternalEObject oldPrecedence = (InternalEObject)precedence;
			precedence = (Precedence)eResolveProxy(oldPrecedence);
			if (precedence != oldPrecedence)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.OPERATION__PRECEDENCE, oldPrecedence, precedence));
			}
		}
		return precedence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Precedence basicGetPrecedence() {
		return precedence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrecedence(Precedence newPrecedence) {
		Precedence oldPrecedence = precedence;
		precedence = newPrecedence;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.OPERATION__PRECEDENCE, oldPrecedence, precedence));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LanguageExpression getBodyExpression()
	{
		return bodyExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBodyExpression(LanguageExpression newBodyExpression, NotificationChain msgs)
	{
		LanguageExpression oldBodyExpression = bodyExpression;
		bodyExpression = newBodyExpression;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.OPERATION__BODY_EXPRESSION, oldBodyExpression, newBodyExpression);
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
	public void setBodyExpression(LanguageExpression newBodyExpression)
	{
		if (newBodyExpression != bodyExpression)
		{
			NotificationChain msgs = null;
			if (bodyExpression != null)
				msgs = ((InternalEObject)bodyExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.OPERATION__BODY_EXPRESSION, null, msgs);
			if (newBodyExpression != null)
				msgs = ((InternalEObject)newBodyExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PivotPackage.OPERATION__BODY_EXPRESSION, null, msgs);
			msgs = basicSetBodyExpression(newBodyExpression, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.OPERATION__BODY_EXPRESSION, newBodyExpression, newBodyExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsInvalidating()
	{
		return (eFlags & IS_INVALIDATING_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsInvalidating(boolean newIsInvalidating)
	{
		boolean oldIsInvalidating = (eFlags & IS_INVALIDATING_EFLAG) != 0;
		if (newIsInvalidating) eFlags |= IS_INVALIDATING_EFLAG; else eFlags &= ~IS_INVALIDATING_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.OPERATION__IS_INVALIDATING, oldIsInvalidating, newIsInvalidating));
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsTransient()
	{
		return (eFlags & IS_TRANSIENT_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsTransient(boolean newIsTransient)
	{
		boolean oldIsTransient = (eFlags & IS_TRANSIENT_EFLAG) != 0;
		if (newIsTransient) eFlags |= IS_TRANSIENT_EFLAG; else eFlags &= ~IS_TRANSIENT_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.OPERATION__IS_TRANSIENT, oldIsTransient, newIsTransient));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsTypeof()
	{
		return (eFlags & IS_TYPEOF_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsTypeof(boolean newIsTypeof)
	{
		boolean oldIsTypeof = (eFlags & IS_TYPEOF_EFLAG) != 0;
		if (newIsTypeof) eFlags |= IS_TYPEOF_EFLAG; else eFlags &= ~IS_TYPEOF_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.OPERATION__IS_TYPEOF, oldIsTypeof, newIsTypeof));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsValidating()
	{
		return (eFlags & IS_VALIDATING_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsValidating(boolean newIsValidating)
	{
		boolean oldIsValidating = (eFlags & IS_VALIDATING_EFLAG) != 0;
		if (newIsValidating) eFlags |= IS_VALIDATING_EFLAG; else eFlags &= ~IS_VALIDATING_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.OPERATION__IS_VALIDATING, oldIsValidating, newIsValidating));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.eclipse.ocl.pivot.Class getOwningClass() {
		if (eContainerFeatureID() != PivotPackage.OPERATION__OWNING_CLASS) return null;
		return (org.eclipse.ocl.pivot.Class)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningClass(org.eclipse.ocl.pivot.Class newOwningClass, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningClass, PivotPackage.OPERATION__OWNING_CLASS, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningClass(org.eclipse.ocl.pivot.Class newOwningClass)
	{
		if (newOwningClass != eInternalContainer() || (eContainerFeatureID() != PivotPackage.OPERATION__OWNING_CLASS && newOwningClass != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningClass))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningClass != null)
				msgs = ((InternalEObject)newOwningClass).eInverseAdd(this, PivotPackage.CLASS__OWNED_OPERATIONS, org.eclipse.ocl.pivot.Class.class, msgs);
			msgs = basicSetOwningClass(newOwningClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.OPERATION__OWNING_CLASS, newOwningClass, newOwningClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateCompatibleReturn(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv CompatibleReturn:
		 *   let severity : Integer[1] = 'Operation::CompatibleReturn'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[?] = bodyExpression <> null and
		 *         bodyExpression.oclAsType(ExpressionInOCL).ownedBody <> null implies
		 *         CompatibleBody(bodyExpression)
		 *       in
		 *         'Operation::CompatibleReturn'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_Operation_c_c_CompatibleReturn);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @Nullable Object CAUGHT_result;
			try {
				/*@Caught*/ @NonNull Object CAUGHT_and;
				try {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable LanguageExpression bodyExpression = this.getBodyExpression();
					final /*@NonInvalid*/ boolean ne = bodyExpression != null;
					/*@Thrown*/ boolean and;
					if (ne) {
						final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_ExpressionInOCL = idResolver.getClass(PivotTables.CLSSid_ExpressionInOCL, null);
						final /*@Thrown*/ org.eclipse.ocl.pivot.@NonNull ExpressionInOCL oclAsType = ClassUtil.nonNullState((ExpressionInOCL)OclAnyOclAsTypeOperation.INSTANCE.evaluate(executor, bodyExpression, TYP_ExpressionInOCL));
						final /*@Thrown*/ org.eclipse.ocl.pivot.@Nullable OCLExpression ownedBody = oclAsType.getOwnedBody();
						final /*@Thrown*/ boolean ne_0 = ownedBody != null;
						and = ne_0;
					}
					else {
						and = ValueUtil.FALSE_VALUE;
					}
					CAUGHT_and = and;
				}
				catch (Exception e) {
					CAUGHT_and = ValueUtil.createInvalidValue(e);
				}
				/*@Caught*/ @NonNull Object CAUGHT_CompatibleBody;
				try {
					final /*@NonInvalid*/ org.eclipse.ocl.pivot.@Nullable LanguageExpression bodyExpression_1 = this.getBodyExpression();
					final /*@Thrown*/ boolean CompatibleBody = this.CompatibleBody(bodyExpression_1);
					CAUGHT_CompatibleBody = CompatibleBody;
				}
				catch (Exception e) {
					CAUGHT_CompatibleBody = ValueUtil.createInvalidValue(e);
				}
				final /*@Thrown*/ java.lang.@Nullable Boolean result = BooleanImpliesOperation.INSTANCE.evaluate(CAUGHT_and, CAUGHT_CompatibleBody);
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_Operation_c_c_CompatibleReturn, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public boolean validateLoadableImplementation(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 * inv LoadableImplementation: true
		 */
		return ValueUtil.TRUE_VALUE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean validateUniquePreconditionName(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv UniquePreconditionName:
		 *   let
		 *     severity : Integer[1] = 'Operation::UniquePreconditionName'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[1] = ownedPreconditions->isUnique(name)
		 *       in
		 *         'Operation::UniquePreconditionName'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_Operation_c_c_UniquePreconditionName);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_result;
			try {
				final /*@NonInvalid*/ java.util.@NonNull List<Constraint> ownedPreconditions = this.getOwnedPreconditions();
				final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull CollectionValue BOXED_ownedPreconditions = idResolver.createSetOfAll(PivotTables.SET_CLSSid_Constraint, ownedPreconditions);
				/*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull MutableIterable accumulator = ValueUtil.createSetAccumulatorValue(PivotTables.SET_CLSSid_Constraint);
				@NonNull Iterator<Object> ITERATOR__1 = ValueUtil.lazyIterator(BOXED_ownedPreconditions);
				/*@Thrown*/ boolean result;
				while (true) {
					if (!ITERATOR__1.hasNext()) {
						result = ValueUtil.TRUE_VALUE;
						break;
					}
					@SuppressWarnings("null")
					/*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Constraint _1 = (Constraint)ITERATOR__1.next();
					/**
					 * name
					 */
					final /*@NonInvalid*/ java.lang.@Nullable String name = _1.getName();
					//
					if (accumulator.includes(name) == ValueUtil.TRUE_VALUE) {
						result = ValueUtil.FALSE_VALUE;			// Abort after second find
						break;
					}
					else {
						accumulator.mutableIncluding(name);
					}
				}
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_Operation_c_c_UniquePreconditionName, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public boolean validateUniquePostconditionName(final DiagnosticChain diagnostics, final Map<Object, Object> context)
	{
		/**
		 *
		 * inv UniquePostconditionName:
		 *   let
		 *     severity : Integer[1] = 'Operation::UniquePostconditionName'.getSeverity()
		 *   in
		 *     if severity <= 0
		 *     then true
		 *     else
		 *       let result : Boolean[1] = ownedPostconditions->isUnique(name)
		 *       in
		 *         'Operation::UniquePostconditionName'.logDiagnostic(self, null, diagnostics, context, null, severity, result, 0)
		 *     endif
		 */
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.evaluation.@NonNull Executor executor = PivotUtilInternal.getExecutor(this);
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.ids.@NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull IntegerValue severity_0 = CGStringGetSeverityOperation.INSTANCE.evaluate(executor, PivotTables.STR_Operation_c_c_UniquePostconditionName);
		final /*@NonInvalid*/ boolean le = OclComparableLessThanEqualOperation.INSTANCE.evaluate(executor, severity_0, PivotTables.INT_0).booleanValue();
		/*@NonInvalid*/ boolean symbol_0;
		if (le) {
			symbol_0 = ValueUtil.TRUE_VALUE;
		}
		else {
			/*@Caught*/ @NonNull Object CAUGHT_result;
			try {
				final /*@NonInvalid*/ java.util.@NonNull List<Constraint> ownedPostconditions = this.getOwnedPostconditions();
				final /*@NonInvalid*/ org.eclipse.ocl.pivot.values.@NonNull CollectionValue BOXED_ownedPostconditions = idResolver.createSetOfAll(PivotTables.SET_CLSSid_Constraint, ownedPostconditions);
				/*@Thrown*/ org.eclipse.ocl.pivot.values.@NonNull MutableIterable accumulator = ValueUtil.createSetAccumulatorValue(PivotTables.SET_CLSSid_Constraint);
				@NonNull Iterator<Object> ITERATOR__1 = ValueUtil.lazyIterator(BOXED_ownedPostconditions);
				/*@Thrown*/ boolean result;
				while (true) {
					if (!ITERATOR__1.hasNext()) {
						result = ValueUtil.TRUE_VALUE;
						break;
					}
					@SuppressWarnings("null")
					/*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Constraint _1 = (Constraint)ITERATOR__1.next();
					/**
					 * name
					 */
					final /*@NonInvalid*/ java.lang.@Nullable String name = _1.getName();
					//
					if (accumulator.includes(name) == ValueUtil.TRUE_VALUE) {
						result = ValueUtil.FALSE_VALUE;			// Abort after second find
						break;
					}
					else {
						accumulator.mutableIncluding(name);
					}
				}
				CAUGHT_result = result;
			}
			catch (Exception e) {
				CAUGHT_result = ValueUtil.createInvalidValue(e);
			}
			final /*@NonInvalid*/ boolean logDiagnostic = CGStringLogDiagnosticOperation.INSTANCE.evaluate(executor, TypeId.BOOLEAN, PivotTables.STR_Operation_c_c_UniquePostconditionName, this, (Object)null, diagnostics, context, (Object)null, severity_0, CAUGHT_result, PivotTables.INT_0).booleanValue();
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case PivotPackage.OPERATION__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.OPERATION__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.OPERATION__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.OPERATION__OWNED_BINDINGS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedBindings()).basicAdd(otherEnd, msgs);
			case PivotPackage.OPERATION__OWNED_SIGNATURE:
				if (ownedSignature != null)
					msgs = ((InternalEObject)ownedSignature).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PivotPackage.OPERATION__OWNED_SIGNATURE, null, msgs);
				return basicSetOwnedSignature((TemplateSignature)otherEnd, msgs);
			case PivotPackage.OPERATION__OWNED_PARAMETERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedParameters()).basicAdd(otherEnd, msgs);
			case PivotPackage.OPERATION__OWNED_POSTCONDITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedPostconditions()).basicAdd(otherEnd, msgs);
			case PivotPackage.OPERATION__OWNED_PRECONDITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedPreconditions()).basicAdd(otherEnd, msgs);
			case PivotPackage.OPERATION__OWNING_CLASS:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningClass((org.eclipse.ocl.pivot.Class)otherEnd, msgs);
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
			case PivotPackage.OPERATION__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.OPERATION__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.OPERATION__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.OPERATION__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.OPERATION__OWNED_CONSTRAINTS:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case PivotPackage.OPERATION__OWNED_BINDINGS:
				return ((InternalEList<?>)getOwnedBindings()).basicRemove(otherEnd, msgs);
			case PivotPackage.OPERATION__OWNED_SIGNATURE:
				return basicSetOwnedSignature(null, msgs);
			case PivotPackage.OPERATION__BODY_EXPRESSION:
				return basicSetBodyExpression(null, msgs);
			case PivotPackage.OPERATION__OWNED_PARAMETERS:
				return ((InternalEList<?>)getOwnedParameters()).basicRemove(otherEnd, msgs);
			case PivotPackage.OPERATION__OWNED_POSTCONDITIONS:
				return ((InternalEList<?>)getOwnedPostconditions()).basicRemove(otherEnd, msgs);
			case PivotPackage.OPERATION__OWNED_PRECONDITIONS:
				return ((InternalEList<?>)getOwnedPreconditions()).basicRemove(otherEnd, msgs);
			case PivotPackage.OPERATION__OWNING_CLASS:
				return basicSetOwningClass(null, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(
			NotificationChain msgs) {
		switch (eContainerFeatureID())
		{
			case PivotPackage.OPERATION__OWNING_CLASS:
				return eInternalContainer().eInverseRemove(this, PivotPackage.CLASS__OWNED_OPERATIONS, org.eclipse.ocl.pivot.Class.class, msgs);
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
			case PivotPackage.OPERATION__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.OPERATION__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.OPERATION__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.OPERATION__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.OPERATION__NAME:
				return getName();
			case PivotPackage.OPERATION__IS_MANY:
				return isIsMany();
			case PivotPackage.OPERATION__IS_REQUIRED:
				return isIsRequired();
			case PivotPackage.OPERATION__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case PivotPackage.OPERATION__IMPLEMENTATION:
				return getImplementation();
			case PivotPackage.OPERATION__IMPLEMENTATION_CLASS:
				return getImplementationClass();
			case PivotPackage.OPERATION__IS_STATIC:
				return isIsStatic();
			case PivotPackage.OPERATION__OWNED_CONSTRAINTS:
				return getOwnedConstraints();
			case PivotPackage.OPERATION__OWNED_BINDINGS:
				return getOwnedBindings();
			case PivotPackage.OPERATION__OWNED_SIGNATURE:
				return getOwnedSignature();
			case PivotPackage.OPERATION__UNSPECIALIZED_ELEMENT:
				return getUnspecializedElement();
			case PivotPackage.OPERATION__BODY_EXPRESSION:
				return getBodyExpression();
			case PivotPackage.OPERATION__IS_INVALIDATING:
				return isIsInvalidating();
			case PivotPackage.OPERATION__IS_TRANSIENT:
				return isIsTransient();
			case PivotPackage.OPERATION__IS_TYPEOF:
				return isIsTypeof();
			case PivotPackage.OPERATION__IS_VALIDATING:
				return isIsValidating();
			case PivotPackage.OPERATION__OWNED_PARAMETERS:
				return getOwnedParameters();
			case PivotPackage.OPERATION__OWNED_POSTCONDITIONS:
				return getOwnedPostconditions();
			case PivotPackage.OPERATION__OWNED_PRECONDITIONS:
				return getOwnedPreconditions();
			case PivotPackage.OPERATION__OWNING_CLASS:
				return getOwningClass();
			case PivotPackage.OPERATION__PRECEDENCE:
				if (resolve) return getPrecedence();
				return basicGetPrecedence();
			case PivotPackage.OPERATION__RAISED_EXCEPTIONS:
				return getRaisedExceptions();
			case PivotPackage.OPERATION__REDEFINED_OPERATIONS:
				return getRedefinedOperations();
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
			case PivotPackage.OPERATION__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.OPERATION__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.OPERATION__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.OPERATION__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.OPERATION__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.OPERATION__IS_REQUIRED:
				setIsRequired((Boolean)newValue);
				return;
			case PivotPackage.OPERATION__TYPE:
				setType((Type)newValue);
				return;
			case PivotPackage.OPERATION__IMPLEMENTATION:
				setImplementation((LibraryFeature)newValue);
				return;
			case PivotPackage.OPERATION__IMPLEMENTATION_CLASS:
				setImplementationClass((String)newValue);
				return;
			case PivotPackage.OPERATION__IS_STATIC:
				setIsStatic((Boolean)newValue);
				return;
			case PivotPackage.OPERATION__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.OPERATION__OWNED_BINDINGS:
				getOwnedBindings().clear();
				getOwnedBindings().addAll((Collection<? extends TemplateBinding>)newValue);
				return;
			case PivotPackage.OPERATION__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignature)newValue);
				return;
			case PivotPackage.OPERATION__UNSPECIALIZED_ELEMENT:
				setUnspecializedElement((TemplateableElement)newValue);
				return;
			case PivotPackage.OPERATION__BODY_EXPRESSION:
				setBodyExpression((LanguageExpression)newValue);
				return;
			case PivotPackage.OPERATION__IS_INVALIDATING:
				setIsInvalidating((Boolean)newValue);
				return;
			case PivotPackage.OPERATION__IS_TRANSIENT:
				setIsTransient((Boolean)newValue);
				return;
			case PivotPackage.OPERATION__IS_TYPEOF:
				setIsTypeof((Boolean)newValue);
				return;
			case PivotPackage.OPERATION__IS_VALIDATING:
				setIsValidating((Boolean)newValue);
				return;
			case PivotPackage.OPERATION__OWNED_PARAMETERS:
				getOwnedParameters().clear();
				getOwnedParameters().addAll((Collection<? extends Parameter>)newValue);
				return;
			case PivotPackage.OPERATION__OWNED_POSTCONDITIONS:
				getOwnedPostconditions().clear();
				getOwnedPostconditions().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.OPERATION__OWNED_PRECONDITIONS:
				getOwnedPreconditions().clear();
				getOwnedPreconditions().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.OPERATION__OWNING_CLASS:
				setOwningClass((org.eclipse.ocl.pivot.Class)newValue);
				return;
			case PivotPackage.OPERATION__PRECEDENCE:
				setPrecedence((Precedence)newValue);
				return;
			case PivotPackage.OPERATION__RAISED_EXCEPTIONS:
				getRaisedExceptions().clear();
				getRaisedExceptions().addAll((Collection<? extends Type>)newValue);
				return;
			case PivotPackage.OPERATION__REDEFINED_OPERATIONS:
				getRedefinedOperations().clear();
				getRedefinedOperations().addAll((Collection<? extends Operation>)newValue);
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
			case PivotPackage.OPERATION__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.OPERATION__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.OPERATION__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.OPERATION__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.OPERATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.OPERATION__IS_REQUIRED:
				setIsRequired(IS_REQUIRED_EDEFAULT);
				return;
			case PivotPackage.OPERATION__TYPE:
				setType((Type)null);
				return;
			case PivotPackage.OPERATION__IMPLEMENTATION:
				setImplementation(IMPLEMENTATION_EDEFAULT);
				return;
			case PivotPackage.OPERATION__IMPLEMENTATION_CLASS:
				setImplementationClass(IMPLEMENTATION_CLASS_EDEFAULT);
				return;
			case PivotPackage.OPERATION__IS_STATIC:
				setIsStatic(IS_STATIC_EDEFAULT);
				return;
			case PivotPackage.OPERATION__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				return;
			case PivotPackage.OPERATION__OWNED_BINDINGS:
				getOwnedBindings().clear();
				return;
			case PivotPackage.OPERATION__OWNED_SIGNATURE:
				setOwnedSignature((TemplateSignature)null);
				return;
			case PivotPackage.OPERATION__UNSPECIALIZED_ELEMENT:
				setUnspecializedElement((TemplateableElement)null);
				return;
			case PivotPackage.OPERATION__BODY_EXPRESSION:
				setBodyExpression((LanguageExpression)null);
				return;
			case PivotPackage.OPERATION__IS_INVALIDATING:
				setIsInvalidating(IS_INVALIDATING_EDEFAULT);
				return;
			case PivotPackage.OPERATION__IS_TRANSIENT:
				setIsTransient(IS_TRANSIENT_EDEFAULT);
				return;
			case PivotPackage.OPERATION__IS_TYPEOF:
				setIsTypeof(IS_TYPEOF_EDEFAULT);
				return;
			case PivotPackage.OPERATION__IS_VALIDATING:
				setIsValidating(IS_VALIDATING_EDEFAULT);
				return;
			case PivotPackage.OPERATION__OWNED_PARAMETERS:
				getOwnedParameters().clear();
				return;
			case PivotPackage.OPERATION__OWNED_POSTCONDITIONS:
				getOwnedPostconditions().clear();
				return;
			case PivotPackage.OPERATION__OWNED_PRECONDITIONS:
				getOwnedPreconditions().clear();
				return;
			case PivotPackage.OPERATION__OWNING_CLASS:
				setOwningClass((org.eclipse.ocl.pivot.Class)null);
				return;
			case PivotPackage.OPERATION__PRECEDENCE:
				setPrecedence((Precedence)null);
				return;
			case PivotPackage.OPERATION__RAISED_EXCEPTIONS:
				getRaisedExceptions().clear();
				return;
			case PivotPackage.OPERATION__REDEFINED_OPERATIONS:
				getRedefinedOperations().clear();
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
			case PivotPackage.OPERATION__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.OPERATION__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.OPERATION__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.OPERATION__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.OPERATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.OPERATION__IS_MANY:
				return isIsMany() != IS_MANY_EDEFAULT;
			case PivotPackage.OPERATION__IS_REQUIRED:
				return ((eFlags & IS_REQUIRED_EFLAG) != 0) != IS_REQUIRED_EDEFAULT;
			case PivotPackage.OPERATION__TYPE:
				return type != null;
			case PivotPackage.OPERATION__IMPLEMENTATION:
				return IMPLEMENTATION_EDEFAULT == null ? implementation != null : !IMPLEMENTATION_EDEFAULT.equals(implementation);
			case PivotPackage.OPERATION__IMPLEMENTATION_CLASS:
				return IMPLEMENTATION_CLASS_EDEFAULT == null ? implementationClass != null : !IMPLEMENTATION_CLASS_EDEFAULT.equals(implementationClass);
			case PivotPackage.OPERATION__IS_STATIC:
				return ((eFlags & IS_STATIC_EFLAG) != 0) != IS_STATIC_EDEFAULT;
			case PivotPackage.OPERATION__OWNED_CONSTRAINTS:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case PivotPackage.OPERATION__OWNED_BINDINGS:
				return ownedBindings != null && !ownedBindings.isEmpty();
			case PivotPackage.OPERATION__OWNED_SIGNATURE:
				return ownedSignature != null;
			case PivotPackage.OPERATION__UNSPECIALIZED_ELEMENT:
				return unspecializedElement != null;
			case PivotPackage.OPERATION__BODY_EXPRESSION:
				return bodyExpression != null;
			case PivotPackage.OPERATION__IS_INVALIDATING:
				return ((eFlags & IS_INVALIDATING_EFLAG) != 0) != IS_INVALIDATING_EDEFAULT;
			case PivotPackage.OPERATION__IS_TRANSIENT:
				return ((eFlags & IS_TRANSIENT_EFLAG) != 0) != IS_TRANSIENT_EDEFAULT;
			case PivotPackage.OPERATION__IS_TYPEOF:
				return ((eFlags & IS_TYPEOF_EFLAG) != 0) != IS_TYPEOF_EDEFAULT;
			case PivotPackage.OPERATION__IS_VALIDATING:
				return ((eFlags & IS_VALIDATING_EFLAG) != 0) != IS_VALIDATING_EDEFAULT;
			case PivotPackage.OPERATION__OWNED_PARAMETERS:
				return ownedParameters != null && !ownedParameters.isEmpty();
			case PivotPackage.OPERATION__OWNED_POSTCONDITIONS:
				return ownedPostconditions != null && !ownedPostconditions.isEmpty();
			case PivotPackage.OPERATION__OWNED_PRECONDITIONS:
				return ownedPreconditions != null && !ownedPreconditions.isEmpty();
			case PivotPackage.OPERATION__OWNING_CLASS:
				return getOwningClass() != null;
			case PivotPackage.OPERATION__PRECEDENCE:
				return precedence != null;
			case PivotPackage.OPERATION__RAISED_EXCEPTIONS:
				return raisedExceptions != null && !raisedExceptions.isEmpty();
			case PivotPackage.OPERATION__REDEFINED_OPERATIONS:
				return redefinedOperations != null && !redefinedOperations.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Namespace.class)
		{
			switch (derivedFeatureID)
			{
				case PivotPackage.OPERATION__OWNED_CONSTRAINTS: return PivotPackage.NAMESPACE__OWNED_CONSTRAINTS;
				default: return -1;
			}
		}
		if (baseClass == TemplateableElement.class)
		{
			switch (derivedFeatureID)
			{
				case PivotPackage.OPERATION__OWNED_BINDINGS: return PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_BINDINGS;
				case PivotPackage.OPERATION__OWNED_SIGNATURE: return PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_SIGNATURE;
				case PivotPackage.OPERATION__UNSPECIALIZED_ELEMENT: return PivotPackage.TEMPLATEABLE_ELEMENT__UNSPECIALIZED_ELEMENT;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Namespace.class)
		{
			switch (baseFeatureID)
			{
				case PivotPackage.NAMESPACE__OWNED_CONSTRAINTS: return PivotPackage.OPERATION__OWNED_CONSTRAINTS;
				default: return -1;
			}
		}
		if (baseClass == TemplateableElement.class)
		{
			switch (baseFeatureID)
			{
				case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_BINDINGS: return PivotPackage.OPERATION__OWNED_BINDINGS;
				case PivotPackage.TEMPLATEABLE_ELEMENT__OWNED_SIGNATURE: return PivotPackage.OPERATION__OWNED_SIGNATURE;
				case PivotPackage.TEMPLATEABLE_ELEMENT__UNSPECIALIZED_ELEMENT: return PivotPackage.OPERATION__UNSPECIALIZED_ELEMENT;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object eInvoke(int operationID, EList<?> arguments)
			throws InvocationTargetException {
		switch (operationID)
		{
			case PivotPackage.OPERATION___ALL_OWNED_ELEMENTS:
				return allOwnedElements();
			case PivotPackage.OPERATION___GET_VALUE__TYPE_STRING:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case PivotPackage.OPERATION___COMPATIBLE_BODY__VALUESPECIFICATION:
				return CompatibleBody((ValueSpecification)arguments.get(0));
			case PivotPackage.OPERATION___VALIDATE_NAME_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP:
				return validateNameIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.OPERATION___VALIDATE_TYPE_IS_NOT_INVALID__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsNotInvalid((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.OPERATION___VALIDATE_TYPE_IS_NOT_NULL__DIAGNOSTICCHAIN_MAP:
				return validateTypeIsNotNull((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.OPERATION___VALIDATE_COMPATIBLE_RETURN__DIAGNOSTICCHAIN_MAP:
				return validateCompatibleReturn((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.OPERATION___VALIDATE_LOADABLE_IMPLEMENTATION__DIAGNOSTICCHAIN_MAP:
				return validateLoadableImplementation((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.OPERATION___VALIDATE_UNIQUE_POSTCONDITION_NAME__DIAGNOSTICCHAIN_MAP:
				return validateUniquePostconditionName((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
			case PivotPackage.OPERATION___VALIDATE_UNIQUE_PRECONDITION_NAME__DIAGNOSTICCHAIN_MAP:
				return validateUniquePreconditionName((DiagnosticChain)arguments.get(0), (Map<Object, Object>)arguments.get(1));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitOperation(this);
	}

	@Override
	public int getIndex() {
		return -1;		// WIP
	}

	@Override
	public @Nullable CompleteInheritance getInheritance(@NonNull StandardLibrary standardLibrary) {
		org.eclipse.ocl.pivot.Class owningType = getOwningClass();
		if (owningType != null) {
			return standardLibrary.getInheritance(owningType);
		}
		else {
			return null;
		}
	}

	@Override
	public @NonNull ParametersId getParametersId() {
		return getOperationId().getParametersId();
	}

	@Override
	public @NonNull ParameterTypes getParameterTypes() {
		List<Parameter> ownedParameter = getOwnedParameters();
		int iMax = ownedParameter.size();
		@NonNull Type @NonNull [] types = new @NonNull Type[iMax];
		for (int i = 0; i < iMax; i++) {
			Type parameterType = ownedParameter.get(i).getType();
			assert parameterType != null;
			types[i] = parameterType;
		}
		return TypeUtil.createParameterTypes(types);
	}

	@Override
	public @NonNull TemplateParameters getTypeParameters() {
		return TemplateSignatureImpl.getTypeParameters(getOwnedSignature());
	}

	private OperationId operationId = null;

	@Override
	public final @NonNull OperationId getOperationId() {
		OperationId operationId2 = operationId;
		if (operationId2 == null) {
			synchronized (this) {
				operationId2 = operationId;
				if (operationId2 == null) {
					operationId = operationId2 = IdManager.getOperationId(this);
				}
			}
		}
		return operationId2;
	}
} //OperationImpl
