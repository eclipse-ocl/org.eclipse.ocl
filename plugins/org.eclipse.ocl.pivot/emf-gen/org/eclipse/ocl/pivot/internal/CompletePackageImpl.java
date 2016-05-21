/**
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import java.util.List;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteClasses;
import org.eclipse.ocl.pivot.internal.complete.CompleteInheritanceImpl;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.complete.CompletePackageInternal;
import org.eclipse.ocl.pivot.internal.complete.NestedCompletePackages;
import org.eclipse.ocl.pivot.internal.complete.PartialPackages;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Complete Package</b></em>'.
 * @extends org.eclipse.ocl.pivot.internal.complete.CompletePackageInternal
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CompletePackageImpl#getOwnedCompleteClasses <em>Owned Complete Classes</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CompletePackageImpl#getOwnedCompletePackages <em>Owned Complete Packages</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CompletePackageImpl#getOwningCompleteModel <em>Owning Complete Model</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CompletePackageImpl#getOwningCompletePackage <em>Owning Complete Package</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CompletePackageImpl#getPartialPackages <em>Partial Packages</em>}</li>
 * </ul>
 *
 * @generated
 */
@SuppressWarnings("unused")
public class CompletePackageImpl extends NamedElementImpl implements CompletePackage, org.eclipse.ocl.pivot.internal.complete.CompletePackageInternal
{

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PivotPackage.Literals.COMPLETE_PACKAGE;
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
			case PivotPackage.COMPLETE_PACKAGE__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.COMPLETE_PACKAGE__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.COMPLETE_PACKAGE__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.COMPLETE_PACKAGE__OWNED_COMPLETE_CLASSES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedCompleteClasses()).basicAdd(otherEnd, msgs);
			case PivotPackage.COMPLETE_PACKAGE__OWNED_COMPLETE_PACKAGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedCompletePackages()).basicAdd(otherEnd, msgs);
			case PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_MODEL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningCompleteModel((CompleteModel)otherEnd, msgs);
			case PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_PACKAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningCompletePackage((CompletePackage)otherEnd, msgs);
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
			case PivotPackage.COMPLETE_PACKAGE__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.COMPLETE_PACKAGE__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.COMPLETE_PACKAGE__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.COMPLETE_PACKAGE__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.COMPLETE_PACKAGE__OWNED_COMPLETE_CLASSES:
				return ((InternalEList<?>)getOwnedCompleteClasses()).basicRemove(otherEnd, msgs);
			case PivotPackage.COMPLETE_PACKAGE__OWNED_COMPLETE_PACKAGES:
				return ((InternalEList<?>)getOwnedCompletePackages()).basicRemove(otherEnd, msgs);
			case PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_MODEL:
				return basicSetOwningCompleteModel(null, msgs);
			case PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_PACKAGE:
				return basicSetOwningCompletePackage(null, msgs);
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
			case PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_MODEL:
				return eInternalContainer().eInverseRemove(this, PivotPackage.COMPLETE_MODEL__OWNED_COMPLETE_PACKAGES, CompleteModel.class, msgs);
			case PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_PACKAGE:
				return eInternalContainer().eInverseRemove(this, PivotPackage.COMPLETE_PACKAGE__OWNED_COMPLETE_PACKAGES, CompletePackage.class, msgs);
		}
		return eDynamicBasicRemoveFromContainer(msgs);
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
			case PivotPackage.COMPLETE_PACKAGE__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.COMPLETE_PACKAGE__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.COMPLETE_PACKAGE__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.COMPLETE_PACKAGE__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.COMPLETE_PACKAGE__NAME:
				return getName();
			case PivotPackage.COMPLETE_PACKAGE__OWNED_COMPLETE_CLASSES:
				return getOwnedCompleteClasses();
			case PivotPackage.COMPLETE_PACKAGE__OWNED_COMPLETE_PACKAGES:
				return getOwnedCompletePackages();
			case PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_MODEL:
				return getOwningCompleteModel();
			case PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_PACKAGE:
				return getOwningCompletePackage();
			case PivotPackage.COMPLETE_PACKAGE__PARTIAL_PACKAGES:
				return getPartialPackages();
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
			case PivotPackage.COMPLETE_PACKAGE__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.COMPLETE_PACKAGE__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.COMPLETE_PACKAGE__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.COMPLETE_PACKAGE__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.COMPLETE_PACKAGE__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.COMPLETE_PACKAGE__OWNED_COMPLETE_CLASSES:
				getOwnedCompleteClasses().clear();
				getOwnedCompleteClasses().addAll((Collection<? extends CompleteClass>)newValue);
				return;
			case PivotPackage.COMPLETE_PACKAGE__OWNED_COMPLETE_PACKAGES:
				getOwnedCompletePackages().clear();
				getOwnedCompletePackages().addAll((Collection<? extends CompletePackage>)newValue);
				return;
			case PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_MODEL:
				setOwningCompleteModel((CompleteModel)newValue);
				return;
			case PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_PACKAGE:
				setOwningCompletePackage((CompletePackage)newValue);
				return;
			case PivotPackage.COMPLETE_PACKAGE__PARTIAL_PACKAGES:
				getPartialPackages().clear();
				getPartialPackages().addAll((Collection<? extends org.eclipse.ocl.pivot.Package>)newValue);
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
			case PivotPackage.COMPLETE_PACKAGE__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.COMPLETE_PACKAGE__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.COMPLETE_PACKAGE__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.COMPLETE_PACKAGE__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.COMPLETE_PACKAGE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.COMPLETE_PACKAGE__OWNED_COMPLETE_CLASSES:
				getOwnedCompleteClasses().clear();
				return;
			case PivotPackage.COMPLETE_PACKAGE__OWNED_COMPLETE_PACKAGES:
				getOwnedCompletePackages().clear();
				return;
			case PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_MODEL:
				setOwningCompleteModel((CompleteModel)null);
				return;
			case PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_PACKAGE:
				setOwningCompletePackage((CompletePackage)null);
				return;
			case PivotPackage.COMPLETE_PACKAGE__PARTIAL_PACKAGES:
				getPartialPackages().clear();
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
			case PivotPackage.COMPLETE_PACKAGE__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.COMPLETE_PACKAGE__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.COMPLETE_PACKAGE__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.COMPLETE_PACKAGE__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.COMPLETE_PACKAGE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.COMPLETE_PACKAGE__OWNED_COMPLETE_CLASSES:
				return ownedCompleteClasses != null && !ownedCompleteClasses.isEmpty();
			case PivotPackage.COMPLETE_PACKAGE__OWNED_COMPLETE_PACKAGES:
				return ownedCompletePackages != null && !ownedCompletePackages.isEmpty();
			case PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_MODEL:
				return getOwningCompleteModel() != null;
			case PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_PACKAGE:
				return getOwningCompletePackage() != null;
			case PivotPackage.COMPLETE_PACKAGE__PARTIAL_PACKAGES:
				return partialPackages != null && !partialPackages.isEmpty();
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
			case PivotPackage.COMPLETE_PACKAGE___ALL_OWNED_ELEMENTS:
				return allOwnedElements();
			case PivotPackage.COMPLETE_PACKAGE___GET_VALUE__TYPE_STRING:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case PivotPackage.COMPLETE_PACKAGE___GET_OWNED_COMPLETE_CLASS__STRING:
				return getOwnedCompleteClass((String)arguments.get(0));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	private /*final*/ /*@NonNull*/ String nsPrefix;
	private /*final*/ /*@NonNull*/ String nsURI;
/**
	 * The cached value of the '{@link #getOwnedCompleteClasses() <em>Owned Complete Classes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedCompleteClasses()
	 * @generated NOT
	 * @ordered
	 */
	protected @Nullable CompleteClasses ownedCompleteClasses;
	/**
	 * The cached value of the '{@link #getOwnedCompletePackages() <em>Owned Complete Packages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedCompletePackages()
	 * @generated NOT
	 * @ordered
	 */
	protected @Nullable NestedCompletePackages ownedCompletePackages;
	//	private /*final*/ /*@NonNull*/ PackageId packageId;

	/**
	 * The cached value of the '{@link #getPartialPackages() <em>Partial Packages</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartialPackages()
	 * @generated NOT
	 * @ordered
	 */
	protected final @NonNull PartialPackages partialPackages;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected CompletePackageImpl()
	{
		partialPackages = new PartialPackages(this);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitCompletePackage(this);
	}

	@Override
	public void assertSamePackage(org.eclipse.ocl.pivot.Package pivotPackage) {
		assert pivotPackage != null;
		if (getOwningCompletePackage() == null) {
//		if ((this instanceof OrphanCompletePackage) || (this instanceof ParentCompletePackage) || (this instanceof PrimitiveCompletePackage)) {
			org.eclipse.ocl.pivot.Package parentPackage = pivotPackage.getOwningPackage();
//			assert parentPackage == null;
			String typeBasedNsURI = pivotPackage.getURI();
			String serverBasedNsURI = getURI();
			if (typeBasedNsURI == null) {
//				assert serverBasedNsURI == null;
			}
			else {
				CompleteModelInternal completeModel = getCompleteModel();
				assert (serverBasedNsURI == null) || (completeModel.getCompletePackageByURI(typeBasedNsURI) == completeModel.getCompletePackageByURI(serverBasedNsURI));
			}
		}
		else {
			String typeBasedNsURI = pivotPackage.getURI();
			String serverBasedNsURI = getURI();
			assert (typeBasedNsURI == serverBasedNsURI) || typeBasedNsURI.equals(serverBasedNsURI);
		}
	}

	protected void didAddNestedPackage(@NonNull CompleteModel completeModel, org.eclipse.ocl.pivot.@NonNull Package pivotPackage) {
/*		CompletePackage completePackage = null;
		String name = pivotPackage.getName();
//		String packageURI = pivotPackage.getURI();
//		if (packageURI != null) {										// Explicit packageURI for explicit package (merge)
//			completePackage = completeURI2completePackage.get(packageURI);
//		}
		if (name != null) {										// Null packageURI can merge into same named package
			completePackage = getOwnedCompletePackage(name);
		}
		if (completePackage == null) {
			completePackage = getPartialPackages().createCompletePackage(pivotPackage);
			completePackage.assertSamePackage(pivotPackage);
		} */
		getPartialPackages().add(pivotPackage);
		if (ownedCompleteClasses != null) {
			ownedCompleteClasses.didAddPackage(pivotPackage);
		}
//		completePackage.addTrackedPackage(pivotPackage);
//		for (org.eclipse.ocl.pivot.Package nestedPackage : pivotPackage.getOwnedPackages()) {
//			if (nestedPackage != null) {
//				addPackage(completePackage, nestedPackage);
//			}
//		}
	}

	public void didAddClass(org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		if (ownedCompleteClasses != null) {
			ownedCompleteClasses.didAddClass(partialClass);
		}
	}

	public void didAddNestedPackage(org.eclipse.ocl.pivot.@NonNull Package nestedPackage) {
		getOwnedCompletePackages().didAddPackage(nestedPackage);
	}

	public void didAddPartialPackage(org.eclipse.ocl.pivot.@NonNull Package partialPackage) {
		if (ownedCompleteClasses != null) {
			ownedCompleteClasses.didAddPackage(partialPackage);
		}
	}

	public void didRemoveClass(org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		if (ownedCompleteClasses != null) {
			ownedCompleteClasses.didRemoveClass(partialClass);
		}
	}

	public void didRemoveNestedPackage(org.eclipse.ocl.pivot.@NonNull Package nestedPackage) {
		getOwnedCompletePackages().didRemovePackage(nestedPackage);
	}

	public void didRemovePartialPackage(org.eclipse.ocl.pivot.@NonNull Package partialPackage) {
		if (ownedCompleteClasses != null) {
			ownedCompleteClasses.didRemovePackage(partialPackage);
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> getAllClasses() {
		return Iterables.transform(ClassUtil.nullFree(getOwnedCompleteClasses()), new Function<@NonNull CompleteClass, org.eclipse.ocl.pivot.@NonNull Class>()
			{
				@Override
				public org.eclipse.ocl.pivot.@NonNull Class apply(@NonNull CompleteClass input) {
					return input.getPrimaryClass();
				}
			});
	}

	@Override
	public @NonNull CompleteClassInternal getCompleteClass(org.eclipse.ocl.pivot.@NonNull Class pivotType) {
		return ClassUtil.nonNullState(getOwnedCompleteClass(pivotType.getName()));
	}

	@Override
	public @NonNull CompleteInheritanceImpl getCompleteInheritance(@NonNull CompleteClassInternal completeClass) {
		assert this != getCompleteModel().getOrphanCompletePackage();		// OrphanCompletePackage overrides
		return getPartialPackages().getCompleteInheritance(completeClass);
	}

	@Override
	public @NonNull CompleteModelInternal getCompleteModel() {
		for (EObject eContainer = eContainer(); eContainer != null; eContainer = eContainer.eContainer()) {
			if (eContainer instanceof CompleteModelInternal) {
				return (CompleteModelInternal) eContainer;
			}
		}
		throw new IllegalStateException();
	}

	@Override
	public EPackage getEPackage() {
		for (org.eclipse.ocl.pivot.Package partialPackage : getPartialPackages()) {
			EPackage ePackage = partialPackage.getEPackage();
			if (ePackage != null) {
				return ePackage;
			}
		}
		return null;
	}

	@Override
	public int getIndex(org.eclipse.ocl.pivot.Package p1) {
		return getPartialPackages().indexOf(p1);
	}

	@Override
	public org.eclipse.ocl.pivot.Class getMemberType(String name) {
		CompleteClass completeClass = name != null ? getOwnedCompleteClass(name) : null;
		return completeClass != null ? completeClass.getPrimaryClass() : null;
	}

	@Override
	public String getNsPrefix() {
		return nsPrefix;
	}

	@Override
	public CompleteClassInternal getOwnedCompleteClass(String name) {
		return getOwnedCompleteClasses().getOwnedCompleteClass(name);
	}
	
	@Override
	public CompletePackageInternal getOwnedCompletePackage(@Nullable String name) {
		return getOwnedCompletePackages().getOwnedCompletePackage(name);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public @NonNull CompleteClasses getOwnedCompleteClasses()
	{
		CompleteClasses ownedCompleteClasses2 = ownedCompleteClasses;
		if (ownedCompleteClasses2 == null)
		{
			ownedCompleteClasses = ownedCompleteClasses2 = new CompleteClasses(this);
		}
		return ownedCompleteClasses2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public @NonNull NestedCompletePackages getOwnedCompletePackages()
	{
		NestedCompletePackages ownedCompletePackages2 = ownedCompletePackages;
		if (ownedCompletePackages2 == null)
		{
			ownedCompletePackages = ownedCompletePackages2 = new NestedCompletePackages(this);
		}
		return ownedCompletePackages2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CompleteModel getOwningCompleteModel()
	{
		if (eContainerFeatureID() != PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_MODEL) return null;
		return (CompleteModel)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningCompleteModel(CompleteModel newOwningCompleteModel, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningCompleteModel, PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningCompleteModel(CompleteModel newOwningCompleteModel)
	{
		if (newOwningCompleteModel != eInternalContainer() || (eContainerFeatureID() != PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_MODEL && newOwningCompleteModel != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningCompleteModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningCompleteModel != null)
				msgs = ((InternalEObject)newOwningCompleteModel).eInverseAdd(this, PivotPackage.COMPLETE_MODEL__OWNED_COMPLETE_PACKAGES, CompleteModel.class, msgs);
			msgs = basicSetOwningCompleteModel(newOwningCompleteModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_MODEL, newOwningCompleteModel, newOwningCompleteModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @Nullable CompletePackage getOwningCompletePackage() {
		if (eContainerFeatureID() != PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_PACKAGE) return null;
		return (CompletePackage)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningCompletePackage(CompletePackage newOwningCompletePackage, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningCompletePackage, PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_PACKAGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningCompletePackage(CompletePackage newOwningCompletePackage)
	{
		if (newOwningCompletePackage != eInternalContainer() || (eContainerFeatureID() != PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_PACKAGE && newOwningCompletePackage != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningCompletePackage))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningCompletePackage != null)
				msgs = ((InternalEObject)newOwningCompletePackage).eInverseAdd(this, PivotPackage.COMPLETE_PACKAGE__OWNED_COMPLETE_PACKAGES, CompletePackage.class, msgs);
			msgs = basicSetOwningCompletePackage(newOwningCompletePackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.COMPLETE_PACKAGE__OWNING_COMPLETE_PACKAGE, newOwningCompletePackage, newOwningCompletePackage));
	}

	@Override
	public @NonNull PartialPackages getPartialPackages() {
		return partialPackages;
	}

	@Override
	public final org.eclipse.ocl.pivot.@NonNull Package getPrimaryPackage() {
		for (org.eclipse.ocl.pivot.Package partialPackage : getPartialPackages()) {
			if (partialPackage != null) {
				return partialPackage;
			}
		}
//		assert false;
		// If there are no pivot packages (e.g. for an orphan) return the metamodel to avoid an NPE constructing a CompleteInheritance
		Package partialPackage = getCompleteModel().getStandardLibrary().getOclAnyType().getOwningPackage();
		return ClassUtil.nonNullState(partialPackage);
	}
	
	@Override
	public @NonNull CompletePackageInternal getRootCompletePackage() {
		for (EObject eContainer = eContainer(); eContainer instanceof CompletePackageInternal; eContainer = eContainer.eContainer()) {
			CompletePackageInternal completePackageInternal = (CompletePackageInternal)eContainer;
			if (completePackageInternal.getOwningCompletePackage() == null) {
				return (CompletePackageInternal)eContainer;
			}
		}
		return this;
	}

	@Override
	public Type getType(String metatypeName) {
		CompleteClass completeClass = getOwnedCompleteClass(metatypeName);
		return completeClass != null ? completeClass.getPrimaryClass() : null;
	}

	@Override
	public String getURI() {
		return nsURI;
	}

	@Override
	public void init(String name, @Nullable String nsPrefix, @Nullable String nsURI) {
		setName(name);
		this.nsPrefix = nsPrefix;
		this.nsURI = nsURI;
	}
} //CompletePackageImpl
