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
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.IdResolver.IdResolverExtension;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorSingleIterationManager;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.resource.ICSI2ASMapping;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotObjectImpl;
import org.eclipse.ocl.pivot.library.AbstractBinaryOperation;
import org.eclipse.ocl.pivot.library.LibraryIteration.LibraryIterationExtension;
import org.eclipse.ocl.pivot.library.classifier.ClassifierOclContentsOperation;
import org.eclipse.ocl.pivot.library.collection.CollectionSelectByKindOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyOclAsSetOperation;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.utilities.ToStringVisitor;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SetValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ElementImpl#getAnnotatingComments <em>Annotating Comments</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ElementImpl#getOwnedAnnotations <em>Owned Annotations</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ElementImpl#getOwnedComments <em>Owned Comments</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ElementImpl#getOwnedExtensions <em>Owned Extensions</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ElementImpl
		extends PivotObjectImpl
		implements Element {

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ELEMENT_FEATURE_COUNT = 4;
	/**
	 * The number of operations of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ELEMENT_OPERATION_COUNT = 2;
	/**
	 * The cached value of the '{@link #getAnnotatingComments() <em>Annotating Comments</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnnotatingComments()
	 * @generated
	 * @ordered
	 */
	protected EList<Comment> annotatingComments;
	/**
	 * The cached value of the '{@link #getOwnedAnnotations() <em>Owned Annotations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedAnnotations()
	 * @generated
	 * @ordered
	 */
	protected EList<Element> ownedAnnotations;
	/**
	 * The cached value of the '{@link #getOwnedComments() <em>Owned Comments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedComments()
	 * @generated
	 * @ordered
	 */
	protected EList<Comment> ownedComments;
	/**
	 * The cached value of the '{@link #getOwnedExtensions() <em>Owned Extensions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedExtensions()
	 * @generated
	 * @ordered
	 */
	protected EList<ElementExtension> ownedExtensions;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotPackage.Literals.ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Comment> getAnnotatingComments()
	{
		if (annotatingComments == null)
		{
			annotatingComments = new EObjectWithInverseResolvingEList.ManyInverse<Comment>(Comment.class, this, 0, 4);
		}
		return annotatingComments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("null")
	public @NonNull List<ElementExtension> getOwnedExtensions()
	{
		if (ownedExtensions == null)
		{
			ownedExtensions = new EObjectContainmentWithInverseEList<ElementExtension>(ElementExtension.class, this, 3, 20);
		}
		return ownedExtensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("null")
	public @NonNull List<Element> getOwnedAnnotations()
	{
		if (ownedAnnotations == null)
		{
			ownedAnnotations = new EObjectContainmentEList<Element>(Element.class, this, 1);
		}
		return ownedAnnotations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Comment> getOwnedComments()
	{
		if (ownedComments == null)
		{
			ownedComments = new EObjectContainmentWithInverseEList<Comment>(Comment.class, this, 2, 6);
		}
		return ownedComments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull List<Element> allOwnedElements()
	{
		/**
		 * self->closure(oclContents()->selectByKind(Element))
		 */
		final /*@NonInvalid*/ @NonNull Executor executor = PivotUtil.getExecutor(this);
		final /*@NonInvalid*/ @NonNull IdResolver idResolver = executor.getIdResolver();
		final /*@NonInvalid*/ @NonNull StandardLibrary standardLibrary = idResolver.getStandardLibrary();
		final /*@NonInvalid*/ @NonNull SetValue oclAsSet = OclAnyOclAsSetOperation.INSTANCE.evaluate(executor, PivotTables.SET_CLSSid_Element, this);
		final org.eclipse.ocl.pivot.@NonNull Class TYPE_closure_0 = executor.getStaticTypeOfValue(null, oclAsSet);
		final @NonNull LibraryIterationExtension IMPL_closure_0 = (LibraryIterationExtension)TYPE_closure_0.lookupImplementation(standardLibrary, OCLstdlibTables.Operations._Set__closure);
		final @NonNull Object ACC_closure_0 = IMPL_closure_0.createAccumulatorValue(executor, PivotTables.SET_CLSSid_Element, PivotTables.SET_CLSSid_Element);
		/**
		 * Implementation of the iterator body.
		 */
		final @NonNull AbstractBinaryOperation BODY_closure_0 = new AbstractBinaryOperation()
		{
			/**
			 * oclContents()->selectByKind(Element)
			 */
			@Override
			public @Nullable Object evaluate(final @NonNull Executor executor, final @NonNull TypeId typeId, final @Nullable Object oclAsSet, final /*@NonInvalid*/ @Nullable Object _1) {
				final /*@NonInvalid*/ org.eclipse.ocl.pivot.@NonNull Class TYP_Element_0 = idResolver.getClass(PivotTables.CLSSid_Element, null);
				final /*@NonInvalid*/ @Nullable Element CAST_null = (Element)_1;
				if (CAST_null == null) {
					throw new InvalidValueException("Null \'\'OclElement\'\' rather than \'\'OclVoid\'\' value required");
				}
				final /*@Thrown*/ @NonNull SetValue oclContents = ClassifierOclContentsOperation.INSTANCE.evaluate(executor, PivotTables.SET_CLSSid_OclElement, CAST_null);
				final /*@Thrown*/ @NonNull SetValue selectByKind = (@Nullable SetValue)CollectionSelectByKindOperation.INSTANCE.evaluate(executor, oclContents, TYP_Element_0);
				return selectByKind;
			}
		};
		final @NonNull ExecutorSingleIterationManager MGR_closure_0 = new ExecutorSingleIterationManager(executor, PivotTables.SET_CLSSid_Element, BODY_closure_0, oclAsSet, ACC_closure_0);
		@SuppressWarnings("null")
		final /*@Thrown*/ @NonNull SetValue closure = (@NonNull SetValue)IMPL_closure_0.evaluateIteration(MGR_closure_0);
		final /*@Thrown*/ @NonNull List<Element> ECORE_closure = ((IdResolverExtension)idResolver).ecoreValueOfAll(Element.class, closure);
		return ECORE_closure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Element getValue(final Type stereotype, final String propertyName)
	{
		/**
		 * null
		 */
		return null;
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
			case 0:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case 1:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case 2:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case 3:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
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
			case 0:
				return getAnnotatingComments();
			case 1:
				return getOwnedAnnotations();
			case 2:
				return getOwnedComments();
			case 3:
				return getOwnedExtensions();
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
			case 0:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case 1:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case 2:
				return ownedComments != null && !ownedComments.isEmpty();
			case 3:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
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
		}
		return eDynamicInvoke(operationID, arguments);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitElement(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return ToStringVisitor.toString(this);
	}

	/**
	 * @since 1.23
	 */
	public @Nullable Object getReloadableEObjectOrURI() {
		// Look for a specific ES
		EObject esObject = getESObject();
		if (esObject != null) {
			return esObject;
		}
		EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
		if (environmentFactory == null) {
			ASResourceImpl.SET_PROXY.println(ThreadLocalExecutor.getBracketedThreadName() + " No EnvironmentFactory when proxifying " + NameUtil.debugSimpleName(this));
			return null;
		}
		// Look for a specific CS
		ICSI2ASMapping csi2asMapping = environmentFactory.getCSI2ASMapping();		// cf ElementUtil.getCsElement
		if (csi2asMapping != null) {
			EObject csElement = csi2asMapping.getCSElement(this);
			if (csElement != null) {		// If a CS Element references that AS Element
				return csElement;
			}
		}
	/*	// Look for any ES
		CompleteClassInternal completeClass = environmentFactory.getCompleteModel().getCompleteClass(this);
		for (org.eclipse.ocl.pivot.Class asClass : completeClass.getPartialClasses()) {
			esObject = asClass.getESObject();
			if (esObject != null) {
				return esObject;
			}
		} */
		if (csi2asMapping == null) {
			ASResourceImpl.SET_PROXY.println(ThreadLocalExecutor.getBracketedThreadName() + " No CSI2ASMappings when proxifying " + NameUtil.debugSimpleName(this));
			return null;
		}
		ASResourceImpl.SET_PROXY.println(ThreadLocalExecutor.getBracketedThreadName() + " No CSI2ASMapping when proxifying " + NameUtil.debugSimpleName(this));
		return null;
	}

	/**
	 * @since 1.23
	 *
	public @Nullable Object getReloadableEObjectOrURI() {
		InternalEObject eInternalContainer = eInternalContainer();
		assert eInternalContainer != null;
		Notifier esProxyTarget = null;
		EObject esObject = getESObject();
		if (esObject != null) {						// If there is a known ES
			esProxyTarget = esObject;				//  use es to create proxy
		}
		else {										// else need a CS
			EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			if (environmentFactory == null) {
				ASResourceImpl.SET_PROXY.println(ThreadLocalExecutor.getBracketedThreadName() + " No EnvironmentFactory when proxifying " + NameUtil.debugSimpleName(this));
				return null;
			}
			// Look for a specific CS
			ICSI2ASMapping csi2asMapping = environmentFactory.getCSI2ASMapping();		// cf ElementUtil.getCsElement
			if (csi2asMapping == null) {
				ASResourceImpl.SET_PROXY.println(ThreadLocalExecutor.getBracketedThreadName() + " No CSI2ASMappings when proxifying " + NameUtil.debugSimpleName(this));
				return null;
			}
			EObject csElement = csi2asMapping.getCSElement(this);
			if ((csElement == null) && !(this instanceof Constraint)) {		// If a CS Element references that AS Element
				csElement = csi2asMapping.getCSElement(this);			// XXX happens for UML2Ecore2AS, and for the Java-implemented Ecore constraints
				ASResourceImpl.SET_PROXY.println(ThreadLocalExecutor.getBracketedThreadName() + " No CSI2ASMapping when proxifying " + NameUtil.debugSimpleName(this));
			}
			esProxyTarget = csElement;
			if (esProxyTarget == null) { // XXX && !environmentFactory.isDisposing()) {
				// XXX Else any old ES
				esProxyTarget = resolveESNotifier(environmentFactory.getCompleteModel());
			}
		}
		return esProxyTarget;
	} */

	/**
	 * @since 1.23
	 */
	public @Nullable URI getReloadableURI() {
		Object reloadableEObjectOrURI = getReloadableEObjectOrURI();
		if (reloadableEObjectOrURI instanceof EObject) {
			return EcoreUtil.getURI((EObject)reloadableEObjectOrURI);
		}
		else if (reloadableEObjectOrURI instanceof URI) {
			return (URI)reloadableEObjectOrURI;
		}
		else {
			return null;
		}
	}

	/**
	 * preUnload() is invoked to support the depth-first traversal of an ASResource contents from ASResourceImpl.doUnload().
	 * The traversal assigns proxies from the esObject that is then set to null. Other pivot artefacts are also reset.
	 *
	 * @since 1.23
	 */
	public void preUnload() {
	    assert eResource() != null;
		for (EObject eObject : eContents()) {
			if (eObject instanceof ElementImpl) {		// Propagate resetESObject through hierarchy (except for internal ExpressionInOCL)
				((ElementImpl)eObject).preUnload();		// proxify the esObject before the eContainer() vanishes
			}
		}
		resetESObject();
	}

	/**
	 * resetESObject is called at the end of preUnload() to assign the URI of esObject as the proxy
	 * and optionally to diagnose non-proxies.
	 *
	 * @since 1.23
	 */
	@Override
	protected void resetESObject() {
	    InternalEObject eInternalContainer = eInternalContainer();
	    assert eInternalContainer != null;
	    EObject esProxyTarget = null;
		EObject esObject = getESObject();
		if (esObject != null) {						// If there is a known ES
			esProxyTarget = esObject;				//  use es to create proxy
		}
		else {										// else need a CS
			EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			if (environmentFactory == null) {
				ASResourceImpl.SET_PROXY.println("No EnvironmentFactory when proxifying " + NameUtil.debugSimpleName(this));
				return;
			}
			// Look for a specific CS
			ICSI2ASMapping csi2asMapping = environmentFactory.getCSI2ASMapping();		// cf ElementUtil.getCsElement
			if (csi2asMapping == null) {
				ASResourceImpl.SET_PROXY.println("No CSI2ASMappings when proxifying  " + NameUtil.debugSimpleName(this));
				return;
			}
			EObject csElement = csi2asMapping.getCSElement(this);
			if (csElement == null) {		// If a CS Element references that AS Element
				ASResourceImpl.SET_PROXY.println("No CSI2ASMapping when proxifying " + NameUtil.debugSimpleName(this));
			}
			esProxyTarget = csElement;
			if ((esProxyTarget == null) && !environmentFactory.isDisposing()) {
				// Else any old ES
				esProxyTarget = resolveESNotifier(environmentFactory.getCompleteModel());
				Object eObjectOrURI = getReloadableEObjectOrURI();
				assert eObjectOrURI != esProxyTarget;				// XXX
			}
		}
		if (esProxyTarget != null) {
			URI uri = EcoreUtil.getURI(esProxyTarget);
			eSetProxyURI(uri);
	//	}
	//	else if (esProxyTarget instanceof Resource) {
	//		URI uri = ((Resource)esProxyTarget).getURI();
	//		eSetProxyURI(uri);
		}
		else {
			ASResourceImpl.SET_PROXY.println("No ES or CS Object when proxifying " + NameUtil.debugSimpleName(this));
		}
		super.resetESObject();
	}

	/**
	 * resolveESNotifier is called from resetESObject() to locate the ES Object that provides the Proxy URI.
	 * Derived classes may navigate the complete element to find an ESObject, or access the AS2CS mapping or
	 * bypass bloated AS such as Import.
	 *
	 * @since 1.23
	 */
	protected @Nullable EObject resolveESNotifier(@NonNull CompleteModel completeModel) {
		return null;
	}
} //ElementImpl
