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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Template Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TemplateParameterImpl#getConstrainingClasses <em>Constraining Classes</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.TemplateParameterImpl#getOwningSignature <em>Owning Signature</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TemplateParameterImpl
		extends TypeImpl
		implements TemplateParameter {

	/**
	 * The cached value of the '{@link #getConstrainingClasses() <em>Constraining Classes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstrainingClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<org.eclipse.ocl.pivot.Class> constrainingClasses;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TemplateParameterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.TEMPLATE_PARAMETER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("null")
	@Override
	public @NonNull List<org.eclipse.ocl.pivot.Class> getConstrainingClasses()
	{
		if (constrainingClasses == null)
		{
			constrainingClasses = new EObjectResolvingEList<org.eclipse.ocl.pivot.Class>(org.eclipse.ocl.pivot.Class.class, this, PivotPackage.TEMPLATE_PARAMETER__CONSTRAINING_CLASSES);
		}
		return constrainingClasses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TemplateSignature getOwningSignature() {
		if (eContainerFeatureID() != PivotPackage.TEMPLATE_PARAMETER__OWNING_SIGNATURE) return null;
		return (TemplateSignature)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningSignature(TemplateSignature newOwningSignature, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningSignature, PivotPackage.TEMPLATE_PARAMETER__OWNING_SIGNATURE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningSignature(TemplateSignature newOwningSignature)
	{
		if (newOwningSignature != eInternalContainer() || (eContainerFeatureID() != PivotPackage.TEMPLATE_PARAMETER__OWNING_SIGNATURE && newOwningSignature != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningSignature))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningSignature != null)
				msgs = ((InternalEObject)newOwningSignature).eInverseAdd(this, PivotPackage.TEMPLATE_SIGNATURE__OWNED_PARAMETERS, TemplateSignature.class, msgs);
			msgs = basicSetOwningSignature(newOwningSignature, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.TEMPLATE_PARAMETER__OWNING_SIGNATURE, newOwningSignature, newOwningSignature));
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
			case PivotPackage.TEMPLATE_PARAMETER__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.TEMPLATE_PARAMETER__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.TEMPLATE_PARAMETER__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.TEMPLATE_PARAMETER__OWNING_SIGNATURE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningSignature((TemplateSignature)otherEnd, msgs);
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
			case PivotPackage.TEMPLATE_PARAMETER__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.TEMPLATE_PARAMETER__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.TEMPLATE_PARAMETER__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.TEMPLATE_PARAMETER__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.TEMPLATE_PARAMETER__OWNING_SIGNATURE:
				return basicSetOwningSignature(null, msgs);
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
			case PivotPackage.TEMPLATE_PARAMETER__OWNING_SIGNATURE:
				return eInternalContainer().eInverseRemove(this, PivotPackage.TEMPLATE_SIGNATURE__OWNED_PARAMETERS, TemplateSignature.class, msgs);
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
			case PivotPackage.TEMPLATE_PARAMETER__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.TEMPLATE_PARAMETER__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.TEMPLATE_PARAMETER__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.TEMPLATE_PARAMETER__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.TEMPLATE_PARAMETER__NAME:
				return getName();
			case PivotPackage.TEMPLATE_PARAMETER__CONSTRAINING_CLASSES:
				return getConstrainingClasses();
			case PivotPackage.TEMPLATE_PARAMETER__OWNING_SIGNATURE:
				return getOwningSignature();
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
			case PivotPackage.TEMPLATE_PARAMETER__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.TEMPLATE_PARAMETER__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.TEMPLATE_PARAMETER__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.TEMPLATE_PARAMETER__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.TEMPLATE_PARAMETER__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.TEMPLATE_PARAMETER__CONSTRAINING_CLASSES:
				getConstrainingClasses().clear();
				getConstrainingClasses().addAll((Collection<? extends org.eclipse.ocl.pivot.Class>)newValue);
				return;
			case PivotPackage.TEMPLATE_PARAMETER__OWNING_SIGNATURE:
				setOwningSignature((TemplateSignature)newValue);
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
			case PivotPackage.TEMPLATE_PARAMETER__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.TEMPLATE_PARAMETER__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.TEMPLATE_PARAMETER__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.TEMPLATE_PARAMETER__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.TEMPLATE_PARAMETER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.TEMPLATE_PARAMETER__CONSTRAINING_CLASSES:
				getConstrainingClasses().clear();
				return;
			case PivotPackage.TEMPLATE_PARAMETER__OWNING_SIGNATURE:
				setOwningSignature((TemplateSignature)null);
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
			case PivotPackage.TEMPLATE_PARAMETER__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.TEMPLATE_PARAMETER__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.TEMPLATE_PARAMETER__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.TEMPLATE_PARAMETER__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.TEMPLATE_PARAMETER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.TEMPLATE_PARAMETER__CONSTRAINING_CLASSES:
				return constrainingClasses != null && !constrainingClasses.isEmpty();
			case PivotPackage.TEMPLATE_PARAMETER__OWNING_SIGNATURE:
				return getOwningSignature() != null;
		}
		return eDynamicIsSet(featureID);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitTemplateParameter(this);
	}

	@Override
	public boolean conformsTo(@NonNull StandardLibrary standardLibrary, @NonNull Type type) {
		CompleteInheritance thisInheritance = standardLibrary.getOclAnyType().getInheritance(standardLibrary);
		CompleteInheritance thatInheritance = type.getInheritance(standardLibrary);
		return thisInheritance.isSubInheritanceOf(thatInheritance);
	}

	@Override
	public @NonNull CompleteInheritance getInheritance(@NonNull StandardLibrary standardLibrary) {
		return standardLibrary.getInheritance(standardLibrary.getOclAnyType());		// FIXME loaer bound
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getNormalizedType(@NonNull StandardLibrary standardLibrary) {
		try {
			return getInheritance(standardLibrary).getPivotClass();
		}
		catch (Throwable e) {
			return standardLibrary.getOclAnyType();			// WIP FIXME should never happen
		}
	}

	private /*@LazyNonNull*/ TemplateParameterId templateParameterId;

	@Override
	public @NonNull TemplateParameterId getTemplateParameterId() {
		TemplateParameterId templateParameterId2 = templateParameterId;
		if (templateParameterId2 == null) {
			synchronized (this) {
				templateParameterId2 = templateParameterId;
				if (templateParameterId2 == null) {
					int index = 0;
					TemplateSignature templateSignature1 = getOwningSignature();
					if (templateSignature1 != null) {
						index = templateSignature1.getOwnedParameters().indexOf(this);
						TemplateableElement template = templateSignature1.getOwningElement();
						if (template != null) {
							for (EObject eContainer = template.eContainer(); eContainer != null; eContainer = eContainer.eContainer()) {
								if (eContainer instanceof TemplateableElement) {
									TemplateableElement eObject = PivotUtil.getUnspecializedTemplateableElement((TemplateableElement)eContainer);
									TemplateSignature templateSignature2 = eObject.getOwnedSignature();
									if (templateSignature2 != null) {
										index += templateSignature2.getOwnedParameters().size();
									}
								}
							}
						}
					}
					templateParameterId = templateParameterId2 = IdManager.getTemplateParameterId(index);
				}
			}
		}
		return templateParameterId2;
	}

	@Override
	public @NonNull TemplateParameterId getTypeId() {
		return getTemplateParameterId();
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Class isClass() {
		return null;
	}

	@Override
	public @NonNull TemplateParameter isTemplateParameter() {
		return this;
	}

	@Override
	public @NonNull Type specializeIn(/*@NonNull*/ CallExp expr, @Nullable Type selfType) {
		Resource eResource = ((EObject) expr).eResource();
		if ((eResource != null) && (selfType != null)) {
			EnvironmentFactoryInternal environmentFactory = PivotUtilInternal.getEnvironmentFactory(eResource);
			PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
			return metamodelManager.specializeType(this, expr, selfType, null);
		}
		return this;
	}
} //TemplateParameterImpl
