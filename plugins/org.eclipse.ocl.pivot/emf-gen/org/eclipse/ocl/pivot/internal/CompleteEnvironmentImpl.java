/**
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal;

import java.util.Collection;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteEnvironment;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteEnvironmentInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Complete Environment</b></em>'.
 * @extends org.eclipse.ocl.pivot.internal.complete.CompleteEnvironmentInternal
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CompleteEnvironmentImpl#getOwnedCompleteModel <em>Owned Complete Model</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CompleteEnvironmentImpl#getOwnedStandardLibrary <em>Owned Standard Library</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CompleteEnvironmentImpl extends ElementImpl implements CompleteEnvironment, org.eclipse.ocl.pivot.internal.complete.CompleteEnvironmentInternal
{
	/**
	 * The number of structural features of the '<em>Complete Environment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPLETE_ENVIRONMENT_FEATURE_COUNT = ElementImpl.ELEMENT_FEATURE_COUNT + 2;
	/**
	 * The number of operations of the '<em>Complete Environment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPLETE_ENVIRONMENT_OPERATION_COUNT = ElementImpl.ELEMENT_OPERATION_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompleteEnvironmentImpl()
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
		return PivotPackage.Literals.COMPLETE_ENVIRONMENT;
	}

	/**
	 * @since 1.23
	 */
	@Override
	public @Nullable CompleteClassInternal basicGetCompleteClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return class2completeClass.get(asClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public NotificationChain basicSetOwnedCompleteModel(CompleteModel newOwnedCompleteModel, NotificationChain msgs)
	{
		CompleteModel oldOwnedCompleteModel = ownedCompleteModel;
		ownedCompleteModel = (CompleteModelInternal) newOwnedCompleteModel;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.Literals.COMPLETE_ENVIRONMENT__OWNED_COMPLETE_MODEL.getFeatureID(), oldOwnedCompleteModel, newOwnedCompleteModel);
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
	public void setOwnedCompleteModel(CompleteModel newOwnedCompleteModel)
	{
		if (newOwnedCompleteModel != ownedCompleteModel)
		{
			NotificationChain msgs = null;
			if (ownedCompleteModel != null)
				msgs = ((InternalEObject)ownedCompleteModel).eInverseRemove(this, 7, CompleteModel.class, msgs);
			if (newOwnedCompleteModel != null)
				msgs = ((InternalEObject)newOwnedCompleteModel).eInverseAdd(this, 7, CompleteModel.class, msgs);
			msgs = basicSetOwnedCompleteModel(newOwnedCompleteModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 4, newOwnedCompleteModel, newOwnedCompleteModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public NotificationChain basicSetOwnedStandardLibrary(StandardLibrary newOwnedStandardLibrary, NotificationChain msgs)
	{
		StandardLibrary oldOwnedStandardLibrary = ownedStandardLibrary;
		ownedStandardLibrary = (CompleteStandardLibrary) newOwnedStandardLibrary;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PivotPackage.Literals.COMPLETE_ENVIRONMENT__OWNED_STANDARD_LIBRARY.getFeatureID(), oldOwnedStandardLibrary, newOwnedStandardLibrary);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
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
			case 0:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case 2:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case 3:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case 4:
				if (ownedCompleteModel != null)
					msgs = ((InternalEObject)ownedCompleteModel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (4), null, msgs);
				return basicSetOwnedCompleteModel((CompleteModel)otherEnd, msgs);
		}
		return eDynamicInverseAdd(otherEnd, featureID, msgs);
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
			case 4:
				return basicSetOwnedCompleteModel(null, msgs);
			case 5:
				return basicSetOwnedStandardLibrary(null, msgs);
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
				return getOwnedCompleteModel();
			case 5:
				return getOwnedStandardLibrary();
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
				setOwnedCompleteModel((CompleteModel)newValue);
				return;
			case 5:
				setOwnedStandardLibrary((CompleteStandardLibrary)newValue);
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
				setOwnedCompleteModel((CompleteModel)null);
				return;
			case 5:
				setOwnedStandardLibrary((CompleteStandardLibrary)null);
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
				return ownedCompleteModel != null;
			case 5:
				return ownedStandardLibrary != null;
		}
		return eDynamicIsSet(featureID);
	}
	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitCompleteEnvironment(this);
	}
	protected /*final @NonNull*/ EnvironmentFactoryInternal environmentFactory;
	protected /*final @NonNull*/ CompleteModelInternal ownedCompleteModel;
	protected /*final @NonNull*/ CompleteStandardLibrary ownedStandardLibrary;
	protected final @NonNull Map<org.eclipse.ocl.pivot.Class, CompleteClassInternal> class2completeClass = new WeakHashMap<org.eclipse.ocl.pivot.Class, CompleteClassInternal>();

	private boolean isCodeGeneration = false;

	@Override
	public void didAddClass(org.eclipse.ocl.pivot.@NonNull Class partialClass, @NonNull CompleteClassInternal completeClass) {
		//		assert partialClass.getUnspecializedElement() == null;
		CompleteClass oldCompleteClass = class2completeClass.put(partialClass, completeClass);
		assert (oldCompleteClass == null) || (oldCompleteClass == completeClass);
	}

	@Override
	public void didRemoveClass(org.eclipse.ocl.pivot.@NonNull Class pivotType) {
		class2completeClass.remove(pivotType);
	}

	@Override
	public void dispose() {
		class2completeClass.clear();
	}

	@Override
	public @NonNull CompleteModelInternal getOwnedCompleteModel() {
		return ClassUtil.requireNonNull(ownedCompleteModel);
	}

	@Override
	public @NonNull EnvironmentFactoryInternal getEnvironmentFactory() {
		return ClassUtil.requireNonNull(environmentFactory);
	}

	//	@Override
	//	public @NonNull MetamodelManager getMetamodelManager() {
	//		assert metamodelManager != null;
	//		return metamodelManager;
	//	}

	@Override
	public org.eclipse.ocl.pivot.Package getNestedPackage(org.eclipse.ocl.pivot.@NonNull Package domainPackage, @NonNull String name) {
		MetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		CompletePackage completePackage = metamodelManager.getCompletePackage(domainPackage);
		CompletePackage memberPackage = completePackage.basicGetOwnedCompletePackage(name);
		return memberPackage != null ? memberPackage.getPrimaryPackage() : null;
	}

	@Override
	public org.eclipse.ocl.pivot.Class getNestedType(org.eclipse.ocl.pivot.@NonNull Package domainPackage, @NonNull String name) {
		MetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		CompletePackage completePackage = metamodelManager.getCompletePackage(domainPackage);
		return completePackage.getMemberType(name);
	}

	@Override
	public @NonNull CompleteStandardLibrary getOwnedStandardLibrary() {
		return ClassUtil.requireNonNull(ownedStandardLibrary);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedStandardLibrary(CompleteStandardLibrary newOwnedStandardLibrary, NotificationChain msgs)
	{
		CompleteStandardLibrary oldOwnedStandardLibrary = ownedStandardLibrary;
		ownedStandardLibrary = newOwnedStandardLibrary;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, 5, oldOwnedStandardLibrary, newOwnedStandardLibrary);
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
	public void setOwnedStandardLibrary(CompleteStandardLibrary newOwnedStandardLibrary)
	{
		if (newOwnedStandardLibrary != ownedStandardLibrary)
		{
			NotificationChain msgs = null;
			if (ownedStandardLibrary != null)
				msgs = ((InternalEObject)ownedStandardLibrary).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - (5), null, msgs);
			if (newOwnedStandardLibrary != null)
				msgs = ((InternalEObject)newOwnedStandardLibrary).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - (5), null, msgs);
			msgs = basicSetOwnedStandardLibrary(newOwnedStandardLibrary, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 5, newOwnedStandardLibrary, newOwnedStandardLibrary));
	}

	@Override
	public @NonNull StandardLibrary getStandardLibrary() {
		return getOwnedStandardLibrary();
	}

	@Override
	public @NonNull CompleteEnvironmentInternal init(@NonNull EnvironmentFactoryInternal environmentFactory) {
		this.environmentFactory = environmentFactory;
		setOwnedCompleteModel(environmentFactory.getCompleteModel());
		setOwnedStandardLibrary(environmentFactory.getStandardLibrary());
		return this;
	}

	@Override
	public boolean isCodeGeneration() {
		return isCodeGeneration ;
	}

	@Override
	public void setCodeGeneration(boolean isCodeGeneration) {
		this.isCodeGeneration = isCodeGeneration;
	}
} //CompleteEnvironmentImpl
