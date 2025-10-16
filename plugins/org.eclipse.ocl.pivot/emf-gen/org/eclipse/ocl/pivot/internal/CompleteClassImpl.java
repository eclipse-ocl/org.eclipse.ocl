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
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.State;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.flat.AbstractFlatClass;
import org.eclipse.ocl.pivot.flat.CompleteFlatClass;
import org.eclipse.ocl.pivot.flat.CompleteFlatModel;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.FlatFragment;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.internal.complete.ClassListeners;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsTypeOperation;
import org.eclipse.ocl.pivot.util.PivotPlugin;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.FeatureFilter;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TracingOption;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Complete Class</b></em>'.
 * @extends org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CompleteClassImpl#getOwningCompletePackage <em>Owning Complete Package</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.CompleteClassImpl#getPartialClasses <em>Partial Classes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CompleteClassImpl extends NamedElementImpl implements CompleteClass, org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal
{
	private static class PartialClasses extends EObjectResolvingEList<Class>
	{
		private static final long serialVersionUID = 1L;

		protected PartialClasses(java.lang.Class<?> dataClass, CompleteClassImpl owner, int featureID) {
			super(dataClass, owner, featureID);
		}

		@Override
		protected void didAdd(int index, org.eclipse.ocl.pivot.Class partialClass) {
			assert partialClass != null;
			CompleteClassImpl owner = (CompleteClassImpl)this.owner;
			if (PARTIAL_CLASSES.isActive()) {
				PARTIAL_CLASSES.println("Do-didAdd " + partialClass + " => " + this);
			}
			if (owner.classListeners != null) {
				owner.classListeners.didAddPartialClass(index, partialClass);
			}
			if (partialClass.getUnspecializedElement() == null) {
				((CompleteModelImpl)owner.getCompleteModel()).didAddClass(partialClass, owner);
			}
		}

		@Override
		protected void didRemove(int index, org.eclipse.ocl.pivot.Class partialClass) {
			assert partialClass != null;
			CompleteClassImpl owner = (CompleteClassImpl)this.owner;
			if (PARTIAL_CLASSES.isActive()) {
				PARTIAL_CLASSES.println("Do-didRemove " + partialClass + " => " + this);
			}
		//	if (partialClass instanceof TupleType) {
		//		System.out.println("Do-didRemove " + partialClass + " => " + this);
		//		getClass();
		//	}
			if (owner.classListeners != null) {
				owner.classListeners.didRemovePartialClass(index, partialClass);
			}
			// XXX ?? getCompleteModel().didRemove...
		}
	}

	/**
	 * @since 7.0
	 */
	public static final @NonNull TracingOption PARTIAL_CLASSES = new TracingOption(PivotPlugin.PLUGIN_ID, "partialClasses");

	/**
	 * The number of structural features of the '<em>Complete Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPLETE_CLASS_FEATURE_COUNT = NamedElementImpl.NAMED_ELEMENT_FEATURE_COUNT + 2;
	/**
	 * The number of operations of the '<em>Complete Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COMPLETE_CLASS_OPERATION_COUNT = NamedElementImpl.NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PivotPackage.Literals.COMPLETE_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CompletePackage getOwningCompletePackage()
	{
		if (eContainerFeatureID() != (5)) return null;
		return (CompletePackage)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningCompletePackage(CompletePackage newOwningCompletePackage, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningCompletePackage, 5, msgs);
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
		if (newOwningCompletePackage != eInternalContainer() || (eContainerFeatureID() != (5) && newOwningCompletePackage != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningCompletePackage))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningCompletePackage != null)
				msgs = ((InternalEObject)newOwningCompletePackage).eInverseAdd(this, 5, CompletePackage.class, msgs);
			msgs = basicSetOwningCompletePackage(newOwningCompletePackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 5, newOwningCompletePackage, newOwningCompletePackage));
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
			case 5:
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
			case 0:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case 1:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case 2:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case 3:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case 5:
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
			case 5:
				return eInternalContainer().eInverseRemove(this, 5, CompletePackage.class, msgs);
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
				return getOwningCompletePackage();
			case 6:
				return getPartialClasses();
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
			case 5:
				setOwningCompletePackage((CompletePackage)newValue);
				return;
			case 6:
				getPartialClasses().clear();
				getPartialClasses().addAll((Collection<? extends org.eclipse.ocl.pivot.Class>)newValue);
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
			case 5:
				setOwningCompletePackage((CompletePackage)null);
				return;
			case 6:
				getPartialClasses().clear();
				return;
		}
		eDynamicUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("null")
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
				return getOwningCompletePackage() != null;
			case 6:
				return partialClasses != null && !partialClasses.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}

	private /*@LazyNonNull*/ CompleteFlatClass flatClass = null;

	/**
	 * The cached value of the '{@link #getPartialClasses() <em>Partial Classes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPartialClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<org.eclipse.ocl.pivot.Class> partialClasses;

	private @Nullable ClassListeners<ClassListeners.@NonNull IClassListener> classListeners = null;

	protected CompleteClassImpl()
	{
		super();
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitCompleteClass(this);
	}

	@Override
	public void addClass(org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		getPartialClasses().add(partialClass);
	}

	/**
	 * @since 7.0
	 */
	public synchronized void addClassListener(ClassListeners.@NonNull IClassListener classListener) {
		ClassListeners<ClassListeners.@NonNull IClassListener> classListeners2 = classListeners;
		if (classListeners2 == null) {
			classListeners2 = classListeners = new ClassListeners<>();
		}
		classListeners2.addListener(classListener);
	}

	@Override
	public boolean conformsTo(@NonNull StandardLibrary standardLibrary, @NonNull CompleteClass thatCompleteClass) {
		FlatClass thisFlatClass = getFlatClass();
		FlatClass thatFlatClass = thatCompleteClass.getFlatClass();
		if (thisFlatClass == thatFlatClass) {
			return true;
		}
		return thatFlatClass.isSuperFlatClassOf(thisFlatClass);
	}

	@Override
	public boolean conformsTo(@NonNull StandardLibrary standardLibrary, @NonNull Type thatType) {
		FlatClass thisFlatClass = getFlatClass();
		FlatClass thatFlatClass = thatType.getFlatClass(standardLibrary);
		if (thisFlatClass == thatFlatClass) {
			return true;
		}
		return thatFlatClass.isSuperFlatClassOf(thisFlatClass);
	}

	@Override
	public void didAddClass(org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		if (PARTIAL_CLASSES.isActive()) {
			PARTIAL_CLASSES.println("Do-didAddClass " + partialClass + " => " + this);
		}
		partialClasses.add(partialClass);
	}

	@Override
	public boolean didRemoveClass(org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		if (PARTIAL_CLASSES.isActive()) {
			PARTIAL_CLASSES.println("Do-didRemoveClass " + this + " " + partialClass);
		}
		partialClasses.remove(partialClass);
		return partialClasses.size() <= 0;		// FIXME Need to invalidate all derived inheritances
	}

	@Override
	public void dispose() {
		CompletePackage owningCompletePackage = getOwningCompletePackage();
		if (owningCompletePackage != null) {
			((CompletePackageImpl)owningCompletePackage).getPartialPackages().uninstalled(this);
		}
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Class getBehavioralClass() {
		Iterable<org.eclipse.ocl.pivot.@NonNull Class> partialClasses = PivotUtil.getPartialClasses(this);
		for (org.eclipse.ocl.pivot.@NonNull Class partialClass : partialClasses) {
			if (partialClass instanceof DataType) {
				org.eclipse.ocl.pivot.Class behavioralClass = ((DataType)partialClass).getBehavioralClass();
				if (behavioralClass != null) {
					return behavioralClass;
				}
			}
		}
		for (org.eclipse.ocl.pivot.@NonNull Class partialClass : partialClasses) {
			return partialClass;
		}
		return null;
	}

	@Override
	public @NonNull CompleteModel getCompleteModel() {
		return getOwningCompletePackage().getCompleteModel();
	}

	/**
	 * Return the ES object from a partial class.
	 */
	@Override
	public @Nullable EObject getESObject() {
		throw new UnsupportedOperationException("CompleteClass.esObject not supported, use partial classes' esObject");
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		return getCompleteModel().getEnvironmentFactory();
	}

	/**
	 * @since 7.0
	 */
	@Override
	public final @NonNull CompleteFlatClass getFlatClass() {
		CompleteFlatClass flatClass2 = flatClass;
		if (flatClass2 == null) {
			CompleteFlatModel completeFlatModel = getStandardLibrary().getFlatModel();
			flatClass = flatClass2 = completeFlatModel.createFlatClass(this);
		}
		return flatClass2;
	}

	public @NonNull Iterable<Operation> getMemberOperations() {
		return getFlatClass().getOperations();
	}

	@Override
	public @NonNull MetamodelManager getMetamodelManager() {
		return getCompleteModel().getMetamodelManager();
	}

	@Override
	public @Nullable Operation getOperation(@NonNull OperationId operationId) {
		return getFlatClass().getOperation(operationId);
	}

	@Override
	public @Nullable Operation getOperation(@NonNull Operation operation) {
		return getFlatClass().getOperation(operation);
	}

	@Override
	public @Nullable Iterable<@NonNull Operation> getOperationOverloads(@NonNull Operation pivotOperation) {
		return getFlatClass().getOperationOverloads(pivotOperation);
	}

	@Override
	public @NonNull Iterable<@NonNull Operation> getOperations(final @Nullable FeatureFilter featureFilter) {
		return getFlatClass().getOperations(featureFilter);
	}

	@Override
	public @NonNull Iterable<@NonNull Operation> getOperations(final @Nullable FeatureFilter featureFilter, @Nullable String name) {
		return getFlatClass().getOperationOverloads(featureFilter, name);
	}

	/**
	 * <!-- begin-user-doc -->
	 * Reference types used by the auto-generated overridden body. - Bug 543180
	 * {@link List},  {@link EList}, {@link EObjectResolvingEList}, {@link OclAnyOclAsTypeOperation}
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public @NonNull List<org.eclipse.ocl.pivot.Class> getPartialClasses() {
		EList<org.eclipse.ocl.pivot.Class> partialClasses2 = partialClasses;
		if (partialClasses2 == null)
		{
			partialClasses2 = partialClasses = new PartialClasses(org.eclipse.ocl.pivot.Class.class, this, PivotPackage.Literals.COMPLETE_CLASS__PARTIAL_CLASSES.getFeatureID());
		}
		return partialClasses2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getPrimaryClass() {
		Iterable<org.eclipse.ocl.pivot.@NonNull Class> partialClasses = PivotUtil.getPartialClasses(this);
		for (org.eclipse.ocl.pivot.@NonNull Class partialClass : partialClasses) {
			if (partialClass.getESObject() != null) {
				return partialClass;
			}
		}
		for (org.eclipse.ocl.pivot.@NonNull Class partialClass : partialClasses) {
			return partialClass;
		}
		throw new IllegalStateException();
	}

	@Override
	public @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> getProperSuperClasses() {
		FlatClass flatClass = getFlatClass();
		return Iterables.transform(flatClass.getAllProperSuperFragments(), new Function<@NonNull FlatFragment, org.eclipse.ocl.pivot.@NonNull Class>()
		{
			@Override
			public org.eclipse.ocl.pivot.@NonNull Class apply(@NonNull FlatFragment input) {
				return input.getBaseFlatClass().getPivotClass();
			}
		});
	}

	@Override
	public @NonNull Iterable<@NonNull CompleteClass> getProperSuperCompleteClasses() {
		FlatClass flatClass = getFlatClass();
		return Iterables.transform(flatClass.getAllProperSuperFragments(), new Function<@NonNull FlatFragment, @NonNull CompleteClass>()
		{
			@Override
			public @NonNull CompleteClass apply(@NonNull FlatFragment input) {
				return ((AbstractFlatClass)input.getBaseFlatClass()).getCompleteClass();		// FIXME cast
			}
		});
	}

	@Override
	public @Nullable Iterable<@NonNull Property> getProperties(@NonNull Property pivotProperty) {
		return getFlatClass().getProperties(null, PivotUtil.getName(pivotProperty));		// XXX surely the property we first thought of
	}

	@Override
	public @NonNull Iterable<@NonNull Property> getProperties(final @Nullable FeatureFilter featureFilter) {
		return getFlatClass().getProperties(featureFilter, null);
	}

	@Override
	public @NonNull Iterable<@NonNull Property> getProperties(final @Nullable FeatureFilter featureFilter, @Nullable String name) {
		return getFlatClass().getProperties(featureFilter, name);
	}

	@Override
	public @Nullable Iterable<@NonNull Property> getProperties(@Nullable String propertyName) {
		return getFlatClass().getProperties(null, propertyName);
	}

	@Override
	public @Nullable Property getProperty(@NonNull String propertyName) {
		return getFlatClass().basicGetProperty(propertyName);
	}

	public @NonNull CompleteStandardLibrary getStandardLibrary() {
		return getCompleteModel().getStandardLibrary();
	}

	@Override
	public @NonNull Iterable<@NonNull State> getStates() {
		return getFlatClass().getStates();
	}

	@Override
	public @NonNull Iterable<@NonNull State> getStates(@Nullable String name) {
		return getFlatClass().getStates(name);
	}

	@Override
	public @NonNull Iterable<@NonNull CompleteClass> getSuperCompleteClasses() {
		FlatClass flatClass = getFlatClass();
		return Iterables.transform(flatClass.getAllSuperFragments(), new Function<FlatFragment, @NonNull CompleteClass>()
		{
			@Override
			public @NonNull CompleteClass apply(FlatFragment input) {
				return input.getBaseFlatClass().getCompleteClass();
			}
		});
	}

	/**
	 * Return true if completeClass conforms to elementType but not to oclVoidType.
	 *
	 * @since 7.0
	 */
	@Override
	public boolean isElementType(@NonNull StandardLibrary standardLibrary, @NonNull Type elementType, @NonNull VoidType oclVoidType) {
		return conformsTo(standardLibrary, elementType) && !conformsTo(standardLibrary, oclVoidType);
	}

	/**
	 * @since 7.0
	 */
	public synchronized void removeClassListener(ClassListeners.@NonNull IClassListener classListener) {
		ClassListeners<ClassListeners.@NonNull IClassListener> classListeners2 = classListeners;
		if ((classListeners2 != null) && classListeners2.removeListener(classListener)) {
			classListeners = null;
		}
	}

	/**
	 * @since 7.0
	 */
	public void resetFragments() {
		if (flatClass != null) {
			flatClass.resetFragments();
		}
	}

	/**
	 * @since 7.0
	 */
	public void resetOperations() {
		if (flatClass != null) {
			flatClass.resetOperations();
		}
	}

	/**
	 * @since 7.0
	 */
	public void resetProperties() {
		if (flatClass != null) {
			flatClass.resetProperties();
		}
	}

	@Override
	public void setESObject(@NonNull EObject newTarget) {
		throw new UnsupportedOperationException("CompleteClass.esObject not supported, use partial classes'esObject");
	}

	@Override
	public void uninstall() {
		dispose();
	}
} //CompleteClassImpl
