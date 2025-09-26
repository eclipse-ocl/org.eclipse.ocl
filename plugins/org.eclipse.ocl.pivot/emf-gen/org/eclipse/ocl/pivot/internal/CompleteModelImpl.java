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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.ocl.pivot.CompleteEnvironment;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.OrphanCompletePackage;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PrimitiveCompletePackage;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Stereotype;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.CompletePackageId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.internal.complete.AbstractCompletePackages;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteEnvironmentInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.complete.PartialModels;
import org.eclipse.ocl.pivot.internal.complete.RootCompletePackages;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.internal.plugin.CompletePackageIdRegistryReader;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Complete Model</b></em>'.
 * @extends org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CompleteModelImpl#getOrphanCompletePackage <em>Orphan Complete Package</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CompleteModelImpl#getOwnedCompletePackages <em>Owned Complete Packages</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CompleteModelImpl#getOwningCompleteEnvironment <em>Owning Complete Environment</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CompleteModelImpl#getPartialModels <em>Partial Models</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CompleteModelImpl#getPrimitiveCompletePackage <em>Primitive Complete Package</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CompleteModelImpl extends NamedElementImpl implements CompleteModel, org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal
{
	/**
	 * @since 7.0
	 */
	protected static final class ConstraintExecutabilityComparator implements Comparator<@NonNull Constraint>
	{
		public static final @NonNull ConstraintExecutabilityComparator INSTANCE = new ConstraintExecutabilityComparator();

		@Override
		public int compare(@NonNull Constraint o1, @NonNull Constraint o2) {
			EObject e1 = o1.getESObject();
			EObject e2 = o2.getESObject();
			if ((e1 != null) && (e2 == null)) {
				return 1;
			}
			if ((e1 == null) && (e2 != null)) {
				return 1;
			}
			return 0;		// XXX $$complete-ocl$$ comparison
		}
	}

	/**
	 * The number of structural features of the '<em>Complete Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPLETE_MODEL_FEATURE_COUNT = NamedElementImpl.NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Complete Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPLETE_MODEL_OPERATION_COUNT = NamedElementImpl.NAMED_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The cached value of the '{@link #getOrphanCompletePackage() <em>Orphan Complete Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrphanCompletePackage()
	 * @generated
	 * @ordered
	 */
	protected OrphanCompletePackage orphanCompletePackage;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PivotPackage.Literals.COMPLETE_MODEL;
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
			case 6:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedCompletePackages()).basicAdd(otherEnd, msgs);
			case 7:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningCompleteEnvironment((CompleteEnvironment)otherEnd, msgs);
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
			case 6:
				return ((InternalEList<?>)getOwnedCompletePackages()).basicRemove(otherEnd, msgs);
			case 7:
				return basicSetOwningCompleteEnvironment(null, msgs);
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
			case 7:
				return eInternalContainer().eInverseRemove(this, 4, CompleteEnvironment.class, msgs);
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
				return getOrphanCompletePackage();
			case 6:
				return getOwnedCompletePackages();
			case 7:
				return getOwningCompleteEnvironment();
			case 8:
				return getPartialModels();
			case 9:
				return getPrimitiveCompletePackage();
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
				getOwnedCompletePackages().clear();
				getOwnedCompletePackages().addAll((Collection<? extends CompletePackage>)newValue);
				return;
			case 7:
				setOwningCompleteEnvironment((CompleteEnvironment)newValue);
				return;
			case 8:
				getPartialModels().clear();
				getPartialModels().addAll((Collection<? extends Model>)newValue);
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
				getOwnedCompletePackages().clear();
				return;
			case 7:
				setOwningCompleteEnvironment((CompleteEnvironment)null);
				return;
			case 8:
				getPartialModels().clear();
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
				return orphanCompletePackage != null;
			case 6:
				return ownedCompletePackages != null && !ownedCompletePackages.isEmpty();
			case 7:
				return getOwningCompleteEnvironment() != null;
			case 8:
				return partialModels != null && !partialModels.isEmpty();
			case 9:
				return primitiveCompletePackage != null;
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
			case 0:
				return allOwnedElements();
			case 1:
				return getValue((Type)arguments.get(0), (String)arguments.get(1));
			case 2:
				return getOwnedCompletePackage((String)arguments.get(0));
		}
		return eDynamicInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningCompleteEnvironment(CompleteEnvironment newOwningCompleteEnvironment, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningCompleteEnvironment, 7, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CompleteEnvironment getOwningCompleteEnvironment()
	{
		if (eContainerFeatureID() != (7)) return null;
		return (CompleteEnvironment)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningCompleteEnvironment(CompleteEnvironment newOwningCompleteEnvironment)
	{
		if (newOwningCompleteEnvironment != eInternalContainer() || (eContainerFeatureID() != (7) && newOwningCompleteEnvironment != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningCompleteEnvironment))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningCompleteEnvironment != null)
				msgs = ((InternalEObject)newOwningCompleteEnvironment).eInverseAdd(this, 4, CompleteEnvironment.class, msgs);
			msgs = basicSetOwningCompleteEnvironment(newOwningCompleteEnvironment, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 7, newOwningCompleteEnvironment, newOwningCompleteEnvironment));
	}

	/**
	 * The cached value of the '{@link #getOwnedCompletePackages() <em>Owned Complete Packages</em>}' containment reference list.
	 */
	protected /*final @NonNull*/ RootCompletePackages ownedCompletePackages;

	/**
	 * The cached value of the '{@link #getPartialModels() <em>Partial Roots</em>}' reference list.
	 */
	protected /*final @NonNull*/ PartialModels partialModels;

	/**
	 * The cached value of the '{@link #getPrimitiveCompletePackage() <em>Primitive Complete Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrimitiveCompletePackage()
	 * @generated
	 * @ordered
	 */
	protected PrimitiveCompletePackage primitiveCompletePackage;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompleteModelImpl()
	{
		super();
	}

	/**
	 * Map from each partial Class to the CompleteClass that supervises its merge. CompleteClass are created lazily.
	 */
	private /*final @NonNull*/ CompleteEnvironmentInternal completeEnvironment;

	/**
	 * Map of (hierarchical) complete package id to CompletePackage.
	 * (Each CompletePackage has a distinct (hierarchical) complete package id.)
	 */
	private final @NonNull Map<@NonNull CompletePackageId, @NonNull CompletePackage> completePackageId2completePackage = new HashMap<>();

	/**
	 * Map of Package to CompletePackage.
	 * (All packages have corresponding CompletePackages, duplicate Package URIs fold to the same CompletePackage.)
	 */
	private final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull CompletePackage> package2completePackage = new HashMap<>();

	/**
	 * Map of Package URI to CompletePackage.
	 * (Only packages with URIs have corresponding CompletePackages, duplicate Package URIs fold to the same CompletePackage.)
	 */
	private final @NonNull Map<@NonNull String, @NonNull CompletePackage> packageURI2completePackage = new HashMap<>();

	protected /*final @NonNull*/ EnvironmentFactoryInternal environmentFactory;

	private Orphanage orphanage = null;

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitCompleteModel(this);
	}

	/**
	 * Partial models such as the OCL Standard Library have their own distinct package URI. These partial
	 * models are merged by mapping the package URI to a complete URI. DomainConstants.METAMODEL_NAME is the
	 * complete URI for all contributions merged as the overall OCL metamodel.
	 *
	private void addPackageURI2completeURI(@NonNull String packageURI, @NonNull CompletePackage completePackage) {
		CompletePackage old = packageURI2completePackage.put(packageURI, completePackage);
		assert (old == null) || (old == completePackage);

		String completeURI = PivotUtil.getURI(completePackage);


	/*	String oldCompleteURI = packageURI2completeURI.get(packageURI);
		if (completePackage.equals(oldCompleteURI)) {
			return;
		}
		if (oldCompleteURI != null) {
			throw new IllegalMetamodelException(completeURI, oldCompleteURI);	// FIXME Better name
		}
	//	if (completeURI2packageURIs.containsKey(packageURI)) {
	//		throw new IllegalMetamodelException(packageURI, oldCompleteURI);	// FIXME Better name
	//	}
		packageURI2completeURI.put(packageURI, completeURI);

		completePackage.didAddPackageURI(packageURI); * /
	} */

	/**
	 * @since 1.23
	 */
	@Override
	public @Nullable CompleteClassInternal basicGetCompleteClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		if (asClass instanceof ElementExtension) {
			Stereotype stereotype = ((ElementExtension)asClass).getStereotype();
			if (stereotype != null) {
				asClass = stereotype;
			}
		}
		return completeEnvironment.basicGetCompleteClass(asClass);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @Nullable CompletePackage basicGetCompletePackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		return package2completePackage.get(asPackage);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @Nullable CompletePackage basicGetCompletePackage(@NonNull CompletePackageId completePackageId) {
		return completePackageId2completePackage.get(completePackageId);
	}

	@Override
	public @Nullable CompletePackage basicGetCompletePackageForURI(@NonNull String packageURI) {
		int lastIndex = packageURI.lastIndexOf("#/");
		if (lastIndex > 0) {
			@NonNull String substring = packageURI.substring(0, lastIndex);
			packageURI = substring;
		}
		return packageURI2completePackage.get(packageURI);
		/*String completePackageName = getCompleteURI(packageURI);
		return completePackageId2completePackage.get(completePackageName); */
	}

	@Override
	public @Nullable CompletePackage basicGetCompletePackageForPackageURI(@NonNull String packageURI) {
		return packageURI2completePackage.get(packageURI);
	}

	/**
	 * @since 7.0
	 */
	protected @NonNull CompletePackage createCompletePackage(@NonNull CompletePackageId completePackageId, @Nullable String prefix, @Nullable String uri) {
		assert !completePackageId2completePackage.containsKey(completePackageId);
		CompletePackageImpl completePackage = (CompletePackageImpl)PivotFactory.eINSTANCE.createCompletePackage();
		completePackage.init(completePackageId, prefix, uri);
		return completePackage;
	}

	@Override
	public void didAddClass(org.eclipse.ocl.pivot.@NonNull Class partialClass, @NonNull CompleteClassInternal completeClass) {
		completeEnvironment.didAddClass(partialClass, completeClass);
	}

	/**
	 * @since 7.0
	 */
	public void didAddCompletePackage(@NonNull CompletePackage completePackage) {
		//		if ((completePackage != completeModel.getOrphanCompletePackage()) && (completePackage != completeModel.getPrimitiveCompletePackage())) {
		CompletePackageId completePackageId = completePackage.getCompletePackageId();
		CompletePackage oldCompletePackage = completePackageId2completePackage.put(completePackageId, completePackage);
		assert oldCompletePackage == null;
		//	if (COMPLETE_URIS.isActive()) {
		//		traceURImapping(completeURI);
		//	}
	}

	/**
	 * @since 7.0
	 */
	public void didAddPackage(@NonNull CompletePackage completePackage, org.eclipse.ocl.pivot.@NonNull Package partialPackage) {
		CompletePackage old = package2completePackage.put(partialPackage, completePackage);
		assert (old == null) || (old == completePackage);
	}

	@Override
	public void didAddPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		if (asPackage instanceof Library) {
			getStandardLibrary().installLibrary((Library)asPackage);
		}
	//	CompletePackage completePackage = getCompletePackage(PivotUtil.getName(asPackage), asPackage.getNsPrefix(), packageURI);
		CompletePackage completePackage = getCompletePackage3(asPackage);
		assert completePackage != null;
		assert (completePackage instanceof PrimitiveCompletePackage) || completePackage.getPartialPackages().contains(asPackage);			// XXX Lose PrimitiveCompletePackage irregularity
	//	CompletePackage completePackage = ownedCompletePackages.didAddPackage(asPackage);
		CompletePackage old1 = package2completePackage.put(asPackage, completePackage);
		assert (old1 == null) || (old1 == completePackage);
		String packageURI = asPackage.getURI();
		if (packageURI != null) {
			completePackage.didAddPackageURI(packageURI);
			assert Iterables.contains(completePackage.getPackageURIs(), packageURI);
		//	completePackage.didAddPackageURI(packageURI);
		//	addPackageURI2completeURI(packageURI, completePackage);
			CompletePackage old2 = packageURI2completePackage.put(packageURI, completePackage);
			assert (old2 == null) || (old2 == completePackage);
		//	packageURI2completePackage.put(packageURI, completePackage);
		}
		assert completePackageId2completePackage.get(completePackage.getCompletePackageId()) == completePackage;
		if (!(completePackage instanceof PrimitiveCompletePackage)) {
			assert completePackage.getPartialPackages().contains(asPackage);			// XXX Lose PrimitiveCompletePackage irregularity
			assert package2completePackage.get(asPackage) == completePackage;
			if (packageURI != null) {
				assert packageURI2completePackage.get(packageURI) == completePackage;
			}
		}
	//	getCompleteClasses(asPackage);
	}

	@Override
	public void didAddPartialModel(@NonNull Model partialModel) {
		for (org.eclipse.ocl.pivot.@NonNull Package asPackage : PivotUtil.getOwnedPackages(partialModel)) {
			didAddPackage(asPackage);
		//	CompletePackage completePackage = getCompletePackage3(asPackage);
		//	assert completePackage != null;
		//	completePackage.getPartialPackages().add(asPackage);
		}
	}

	@Override
	public void didRemoveClass(org.eclipse.ocl.pivot.@NonNull Class pivotType) {
		completeEnvironment.didRemoveClass(pivotType);
	}

	@Override
	public void didRemoveCompletePackage(@NonNull CompletePackage completePackage) {
		if (completePackage == primitiveCompletePackage) {
			primitiveCompletePackage = null;
		}
		CompletePackageId completePackageId = completePackage.getCompletePackageId();
		completePackageId2completePackage.remove(completePackageId);
		for (@NonNull String packageURI : completePackage.getPackageURIs()) {
			packageURI2completePackage.remove(packageURI);
		}
	/*	String completeURI = PivotUtil.getURI(completePackage);
		Set<@NonNull String> packageURIs = completeURI2packageURIs.remove(completeURI);
		if (packageURIs != null) {
			for (String packageURI : packageURIs) {
				packageURI2completeURI.remove(packageURI);
			}
		}
		if (COMPLETE_URIS.isActive()) {
			traceURImapping(completeURI);
		} */
	}

	@Override
	public void didRemoveNestedPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {				// XXX not 'Nested'
		CompletePackage completePackage = ownedCompletePackages.didRemovePackage(asPackage);			// XXX getCompletePackage
		String packageURI = asPackage.getURI();
		if (packageURI != null) {
			packageURI2completePackage.remove(packageURI);
			for (@NonNull String packageURI2 : completePackage.getPackageURIs()) {
				packageURI2completePackage.put(packageURI2, completePackage);		// Restore any duplicate residues
				completePackageId2completePackage.put(completePackage.getCompletePackageId(), completePackage);
			}
		}
	}

	@Override
	public void didRemovePartialModel(@NonNull Model partialModel) {		// UPdates occur fia didRemovePackage etc
	/*	for (org.eclipse.ocl.pivot.Package asPackage : partialModel.getOwnedPackages()) {
			String packageURI = asPackage.getURI();
			String completeURI = getCompleteURI(packageURI);
			if (completeURI == packageURI) {
				PackageId packageId = asPackage.getPackageId();
				assert packageId != IdManager.METAMODEL_ID;
				if (packageId == IdManager.METAMODEL_ID) {
					if (packageURI != null) {
						//FIXME						removePackageURI2completeURI(packageURI, DomainConstants.METAMODEL_NAME);
					}
				}
			}
		} */
	}

	@Override
	public synchronized void dispose() {
		completeEnvironment.dispose();
		ownedCompletePackages.dispose();
		completePackageId2completePackage.clear();
		package2completePackage.clear();
		packageURI2completePackage.clear();
		Orphanage orphanage2 = orphanage;
		if (orphanage2 != null) {
			orphanage2.removePackageListener(getOrphanCompletePackage().getPartialPackages());
			orphanage = null;
		}
	}

	@Override
	public @Nullable Iterable<@NonNull Object> getAllCompleteInvariants(@NonNull Type asType) {
		List<@NonNull Object> knownInvariantOrInvariants = null;
		Iterable<@NonNull CompleteClass> allSuperCompleteClasses = environmentFactory.getMetamodelManager().getAllSuperCompleteClasses(asType);
		for (CompleteClass superType : allSuperCompleteClasses) {
			Map<@NonNull String, @NonNull Object> name2invariantOrInvariants = null;
			List<org.eclipse.ocl.pivot.@NonNull Class> partialClasses = ClassUtil.nullFree(superType.getPartialClasses());
			for (org.eclipse.ocl.pivot.@NonNull Class partialSuperType : partialClasses) {
				org.eclipse.ocl.pivot.Package partialPackage = partialSuperType.getOwningPackage();
				if (!(partialPackage instanceof PackageImpl) || !((PackageImpl)partialPackage).isIgnoreInvariants()) {
					for (@NonNull Constraint asInvariant : ClassUtil.nullFree(partialSuperType.getOwnedInvariants())) {
						if (name2invariantOrInvariants == null) {
							name2invariantOrInvariants = new HashMap<>();
						}
						String name = String.valueOf(asInvariant.getName());
						Object invariantOrInvariants = name2invariantOrInvariants.get(name);
						if (invariantOrInvariants == null) {
							name2invariantOrInvariants.put(name, asInvariant);
						}
						else if (invariantOrInvariants instanceof Constraint) {
							invariantOrInvariants = Lists.newArrayList((Constraint)invariantOrInvariants, asInvariant);
							assert invariantOrInvariants != null;
							name2invariantOrInvariants.put(name, invariantOrInvariants);
						}
						else {
							@SuppressWarnings("unchecked")
							List<@NonNull Constraint> asInvariants = (List<@NonNull Constraint>)invariantOrInvariants;
							asInvariants.add(asInvariant);
						}
					}
				}
			}
			if (name2invariantOrInvariants != null) {
				if (knownInvariantOrInvariants == null) {
					knownInvariantOrInvariants = new ArrayList<>();
				}
				List<@NonNull String> names = new ArrayList<>(name2invariantOrInvariants.keySet());
				if (names.size() > 1) {
					Collections.sort(names);
				}
				for (@NonNull String name : names) {
					Object invariantOrInvariants = name2invariantOrInvariants.get(name);
					assert invariantOrInvariants != null;
					if (invariantOrInvariants instanceof Constraint) {
						knownInvariantOrInvariants.add(invariantOrInvariants);
					}
					else {
						@SuppressWarnings("unchecked")
						List<@NonNull Constraint> asInvariants = (List<@NonNull Constraint>)invariantOrInvariants;
						Collections.sort(asInvariants, ConstraintExecutabilityComparator.INSTANCE);
						knownInvariantOrInvariants.add(asInvariants);
					}
				}
			}
		}
		return knownInvariantOrInvariants;
	}

	@Override
	public @NonNull Iterable<@NonNull CompletePackage> getAllCompletePackages() {
		return completePackageId2completePackage.values();
	}

//	@Override
//	public @NonNull Iterable<@NonNull CompletePackage> getAllCompletePackagesWithUris() {
//		return packageURI2completePackage.values();
//	}

	@Override
	public @NonNull CompleteClassInternal getCompleteClass(@NonNull Type pivotType) {
		if (pivotType instanceof TemplateParameter) {
			pivotType = PivotUtil.getLowerBound((TemplateParameter)pivotType, getStandardLibrary().getOclAnyType());
		}
		if (pivotType instanceof org.eclipse.ocl.pivot.Class) {
			org.eclipse.ocl.pivot.Class asClass = (org.eclipse.ocl.pivot.Class)pivotType;
			return getCompleteClass(asClass);
		}
		else {
			throw new UnsupportedOperationException("TemplateType");
		}
	}

	@Override
	public @NonNull CompleteClassInternal getCompleteClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		if (asClass instanceof ElementExtension) {
			Stereotype stereotype = ((ElementExtension)asClass).getStereotype();
			if (stereotype != null) {
				asClass = stereotype;
			}
		}
		CompleteClassInternal completeClass = completeEnvironment.basicGetCompleteClass(asClass);
		if (completeClass != null) {
			return completeClass;
		}
		CompletePackage completePackage = getCompletePackage4(asClass);
		return (CompleteClassInternal)completePackage.getCompleteClass(asClass);
	}

	@Override
	public void getCompleteClasses(@NonNull ASResource asResource) {
		// XXX check called just once
		// XXX pass completePackage
		for (EObject eObject : asResource.getContents()) {
			if (eObject instanceof Model) {
				for (org.eclipse.ocl.pivot.@NonNull Package asPackage : PivotUtil.getOwnedPackages((Model)eObject)) {
					getCompleteClasses(asPackage);
				}
			}
		}
	}

	@Override
	public void getCompleteClasses(org.eclipse.ocl.pivot.@NonNull Package asPackage) {				// XXX migrate to lazy first getCompleteClass
		// XXX check called just once
		// XXX pass completePackage
		for (org.eclipse.ocl.pivot.Class asClass : PivotUtil.getOwnedClasses(asPackage)) {
			getCompleteClass(asClass);
		}
		for (org.eclipse.ocl.pivot.@NonNull Package asNestedPackage : PivotUtil.getOwnedPackages(asPackage)) {
			getCompleteClasses(asNestedPackage);
		}
	}

	@Override
	public @NonNull CompleteEnvironmentInternal getCompleteEnvironment() {
		assert completeEnvironment != null;
		return completeEnvironment;
	}

	@Override
	public @NonNull CompletePackage getCompletePackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		CompletePackage aT = package2completePackage.get(asPackage);
		return ClassUtil.requireNonNull(aT);
	}

	@Override
	public @NonNull CompletePackage getCompletePackage(@NonNull CompletePackageId completePackageId, @Nullable String prefix, @Nullable String uri) {
		CompletePackage completePackage = completePackageId2completePackage.get(completePackageId);
		if (completePackage == null) {
			completePackage = createCompletePackage(completePackageId, prefix, uri);
	//		completePackageId2completePackage.put(completePackageName, completePackage);
			getOwnedCompletePackages().add(completePackage);
		}
		assert completePackage == completePackageId2completePackage.get(completePackageId);
	//	assert Objects.equals(prefix, completePackage.getNsPrefix());
	//	assert Objects.equals(uri, completePackage.getURI());
	//	completePackage.didAddPackageURI(packageURI);
	//	CompletePackage old = packageURI2completePackage.put(uri, completePackage);			// XXX remove
	//	assert (old == null) || (old == completePackage);
		return completePackage;
	}

	@Override
	public @Nullable CompletePackage getCompletePackage2(org.eclipse.ocl.pivot.@NonNull Package pivotPackage) {
		String packageURI = pivotPackage.getURI();
		if (packageURI == null) {
			return null;
		}
		URI semantics = PivotUtil.basicGetPackageSemantics(pivotPackage);
		String completePackageName;
		if (semantics != null) {
			completePackageName = semantics.trimFragment().toString();
		}
		else {
			completePackageName = getCompleteURI(packageURI);
		}
		if (completePackageName == null) {
			return null;
		}
		CompletePackageId completePackageId = IdManager.getCompletePackageId(completePackageName);
		return completePackageId2completePackage.get(completePackageId);
	}

	/**
	 * @since 7.0
	 */
	public @NonNull CompletePackage getCompletePackage3(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		boolean packageAdded = false;
		CompletePackage completePackage = package2completePackage.get(asPackage);
		if (completePackage == null) {
			String packageURI = asPackage.getURI();
			if (packageURI != null) {			// XXX and not blank
				completePackage = packageURI2completePackage.get(packageURI);
			}
			if (completePackage == null) {
				CompletePackageId completePackageId = CompletePackageIdRegistryReader.basicGetCompletePackageId(packageURI);
				if (completePackageId == null) {
					URI semantics = PivotUtil.basicGetPackageSemantics(asPackage);
					if (semantics != null) {
						completePackageId = IdManager.getCompletePackageId(String.valueOf(semantics.trimFragment()));
					}
					else if (Orphanage.isOrphanage(asPackage)) {
						completePackageId = PivotConstants.ORPHANAGE_ID;
					}
					else if (packageURI != null) {			// XXX ??? and not blank
						completePackageId = IdManager.getCompletePackageId(packageURI); // getCompleteURI(packageURI);
					}
				//	else if (packageURI != null) {
				//XXX		completePackageId = IdManager.getCompletePackageId(packageURI); // getCompleteURI(packageURI);
				//	}
					else {
						String packageName = PivotUtil.getName(asPackage);
					//	 parentCompletePackage = null;
					//	AbstractCompletePackages parentCompletePackages2 = ownedCompletePackages;
						org.eclipse.ocl.pivot.Package parentPackage = asPackage.getOwningPackage();
						if (parentPackage != null) {
							CompletePackage parentCompletePackage = getCompletePackage3(parentPackage);
						//	if (parentCompletePackage != null) {
							//	parentCompletePackages2 = ((CompletePackageImpl)parentCompletePackage).getOwnedCompletePackages();
							completePackageId = IdManager.getCompletePackageId(parentCompletePackage, packageName);
						//	}
						}
						else {
							completePackageId = IdManager.getCompletePackageId(packageName);
						}
					}
				}
				completePackage = completePackageId2completePackage.get(completePackageId);
				if (completePackage == null) {
					AbstractCompletePackages parentCompletePackages = ownedCompletePackages;
					org.eclipse.ocl.pivot.Package parentPackage = asPackage.getOwningPackage();
					if (parentPackage != null) {
						CompletePackage parentCompletePackage = getCompletePackage3(parentPackage);
						parentCompletePackages = ((CompletePackageImpl)parentCompletePackage).getOwnedCompletePackages();
					}
				//	completePackage.assertSamePackage(asPackage);		// XXX obsolete / rewrite
					if (Orphanage.isOrphanage(asPackage)) {
						completePackage = getOrphanCompletePackage();
						//	assert completePackageName.equals(completePackage.getName());
					//	assert PivotConstants.ORPHANAGE_NAME.equals(completePackage.getName());
					//	assert Objects.equals(asPackage.getNsPrefix(), completePackage.getNsPrefix());
						assert Objects.equals(packageURI, completePackage.getURI());
					}
					else {
						completePackage = createCompletePackage(completePackageId, asPackage.getNsPrefix(), packageURI);
					}
					package2completePackage.put(asPackage, completePackage);
					//		completePackageId2completePackage.put(completePackageName, completePackage);
					parentCompletePackages.add(completePackage);		//didAddCompletePackage
				//	didAddPackage(asPackage);
					assert completePackageId2completePackage.get(completePackageId) == completePackage;
					//	CompletePackage old1 = package2completePackage.put(asPackage, completePackage);
					//	CompletePackage old2 = completePackageId2completePackage.put(completePackageName, completePackage);
					assert package2completePackage.get(asPackage) == completePackage;
					completePackage.getPartialPackages().add(asPackage);
					packageAdded = true;
				//	}
				}
			//	if (packageURI != null) {
			//		completePackage.didAddPackageURI(packageURI);
			//	}
			}
		}
//		String name = pivotPackage.getName();
//		if (packageURI != null) {										// Explicit packageURI for explicit package (merge)
//			completePackage = getCompleteModel().basicGetCompletePackageForPackageURI(packageURI);
//		}
//		else if (name != null) {										// Null packageURI can merge into same named package
//			completePackage = basicGetOwnedCompletePackage(name);
//		}
//		if (completePackage == null) {
//			completePackage = getOwnedCompletePackage(pivotPackage);
//			completePackage.assertSamePackage(pivotPackage);		// XXX obsolete / rewrite
//		}
//		completePackage.getPartialPackages().add(pivotPackage);
//		if (packageURI != null) {
//			completePackage.didAddPackageURI(packageURI);
//		}
//		completePackage.addTrackedPackage(pivotPackage);
//		for (org.eclipse.ocl.pivot.Package nestedPackage : pivotPackage.getOwnedPackages()) {
//			if (nestedPackage != null) {
//				addPackage(completePackage, nestedPackage);
//			}
//		}
	//	completePackage.getPartialPackages().add(asPackage);
		if (!packageAdded) {								// Maybe folding an additional package into a CompletePackage found by name/URI.
			completePackage.getPartialPackages().add(asPackage);						// UML 2.5 recurses for nested packages mapping to a parent
			assert package2completePackage.get(asPackage) == completePackage;
		}
		if (!(completePackage instanceof PrimitiveCompletePackage)) {
			assert completePackage.getPartialPackages().contains(asPackage);			// XXX Lose PrimitiveCompletePackage irregularity
			assert package2completePackage.get(asPackage) == completePackage;
		//	if (packageURI != null) {
		//		assert packageURI2completePackage.get(packageURI) == completePackage;
		//	}
		}


	//	assert (asPackage.getURI() == null) || (packageURI2completePackage.get(asPackage.getURI()) == completePackage);		in didAddPackage caller
		CompletePackage completePackage2 = completePackageId2completePackage.get(completePackage.getCompletePackageId());
		assert (completePackage2 == completePackage) || (completePackage2 == null);
		return completePackage;
	}

	private @NonNull CompletePackage getCompletePackage4(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		CompletePackage completePackage;
		if (asClass instanceof PrimitiveType) {					// XXX ?? Any/Invalid/Void too ?? Collection/Lambda/Map/Tuple too
			completePackage = getPrimitiveCompletePackage();			// namespacelessCompletePackage
		}
		else if (asClass.eContainer() instanceof Orphanage) {			// XXX
			completePackage = getOrphanCompletePackage();
		}
		else if (/*(asClass instanceof IterableType) &&*/ (asClass.getUnspecializedElement() != null)) {
			completePackage = getOrphanCompletePackage();
		}
		else if ((asClass instanceof LambdaType) /*&& (((LambdaType)asClass).getContextType() != null)*/) {
			completePackage = getOrphanCompletePackage();
		}
		else {
			org.eclipse.ocl.pivot.Package pivotPackage = asClass.getOwningPackage();
			if (pivotPackage == null) {
				throw new IllegalStateException("type has no package");
			}
			completePackage = getCompletePackage3(pivotPackage);
		}
		return completePackage;
	}

	@Deprecated	// fold packageURI2completePackage.get() inline
	@Override
	public @Nullable String getCompleteURI(@Nullable String packageURI) {
		CompletePackage completePackage = packageURI2completePackage.get(packageURI);
		if (completePackage != null) {
			return completePackage.getURI();
		}
		else {
			return packageURI;
		}
	}

	@Override
	public @NonNull EnvironmentFactoryInternal getEnvironmentFactory() {
		return ClassUtil.requireNonNull(environmentFactory);
	}

//	public @Nullable CompletePackage getMemberPackage(@NonNull String memberPackageName) {
//		return ownedCompletePackages.basicGetOwnedCompletePackage(memberPackageName);
//	}

	public @NonNull Iterable<? extends CompletePackage> getMemberPackages() {
		assert ownedCompletePackages != null;
		return ownedCompletePackages;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull MetamodelManager getMetamodelManager() {
		return environmentFactory.getMetamodelManager();
	}

	@Override
	public @NonNull OrphanCompletePackageImpl getOrphanCompletePackage()
	{
		OrphanCompletePackage orphanCompletePackage2 = orphanCompletePackage;
		if (orphanCompletePackage2 == null) {
			orphanCompletePackage2 = orphanCompletePackage = PivotFactory.eINSTANCE.createOrphanCompletePackage();
			ownedCompletePackages.add(orphanCompletePackage2);
		}
		return (OrphanCompletePackageImpl)orphanCompletePackage2;
	}

	@Override
	public @NonNull Orphanage getOrphanage() {
		Orphanage orphanage2 = orphanage;
		if (orphanage2 == null) {
			orphanage2 = orphanage = environmentFactory.getMetamodelManager().createOrphanage();
			@SuppressWarnings("unused") CompletePackage completePackage = getCompletePackage3(orphanage2);
		//	OrphanCompletePackageImpl orphanCompletePackage2 = getOrphanCompletePackage();
		//	package2completePackage.put(orphanage2, orphanCompletePackage2);
		//	packageURI2completePackage.put(PivotConstants.ORPHANAGE_URI, orphanCompletePackage2);
			Model orphanModel = PivotUtil.getContainingModel(orphanage2);
			didAddPartialModel(orphanModel);
		//	PartialPackages partialPackages = getOrphanCompletePackage().getPartialPackages();
		//	orphanage2.addPackageListener(partialPackages);
		//	for (org.eclipse.ocl.pivot.@NonNull Package asPackage : PivotUtil.getOwnedPackages(orphanage2)) {
		//		didAddPackage(asPackage);
		//	}
		}
		return orphanage2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public @Nullable CompletePackage getOwnedCompletePackage(/*@NonNull*/ String packageName) {
		assert packageName != null;
		assert ownedCompletePackages != null;
		return ownedCompletePackages.basicGetOwnedCompletePackage(packageName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public @NonNull List<CompletePackage> getOwnedCompletePackages() {
		assert ownedCompletePackages != null;
		return ownedCompletePackages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * Reference types used by the auto-generated overridden body. - Bug 543180
	 * {@link EObjectResolvingEList}, {@link EObjectContainmentWithInverseEList}
	 * <!-- end-user-doc -->
	 * @generated NOT
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public @NonNull PartialModels getPartialModels() {
		assert partialModels != null;
		return partialModels;
	}

	@Override
	public @NonNull PrimitiveCompletePackageImpl getPrimitiveCompletePackage()
	{
		PrimitiveCompletePackage primitiveCompletePackage2 = primitiveCompletePackage;
		if (primitiveCompletePackage2 == null) {

			primitiveCompletePackage2 = primitiveCompletePackage = PivotFactory.eINSTANCE.createPrimitiveCompletePackage();
			ownedCompletePackages.add(primitiveCompletePackage2);
		}
		return (PrimitiveCompletePackageImpl) primitiveCompletePackage2;
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Package getRootPackage(@NonNull String completePackageName) {
		throw new UnsupportedOperationException();
/*		CompletePackage completePackage = basicGetCompletePackage(completePackageId);
		if ((completePackage != null) && (completePackage.eContainer() == this)) {
			return completePackage.getPrimaryPackage();
		}
		return null;*//*completePackage = getOwnedCompletePackage(completeURIorName);
		return completePackage != null ? completePackage.getPrimaryPackage() : null;*/
	}

	@Override
	public @NonNull CompleteStandardLibrary getStandardLibrary() {
		return completeEnvironment.getOwnedStandardLibrary();
	}

	@Override
	public @NonNull CompleteModelInternal init(@NonNull EnvironmentFactoryInternal environmentFactory) {
		this.environmentFactory = environmentFactory;
		this.completeEnvironment = environmentFactory.getCompleteEnvironment();
		partialModels = new PartialModels(this);
		ownedCompletePackages = new RootCompletePackages(this);
		return this;
	}
} //CompleteModelImpl
