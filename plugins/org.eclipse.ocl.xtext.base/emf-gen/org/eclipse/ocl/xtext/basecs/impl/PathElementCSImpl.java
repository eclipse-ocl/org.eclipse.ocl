/*******************************************************************************
 * Copyright (c) 2012, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.basecs.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.internal.utilities.External2AS;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.Pivotable;
import org.eclipse.ocl.pivot.utilities.SemanticException;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.PathElementCS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Simple Named Element Ref CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.PathElementCSImpl#getElementType <em>Element Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.PathElementCSImpl#getOwningPathName <em>Owning Path Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.PathElementCSImpl#getReferredElement <em>Referred Element</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PathElementCSImpl extends ElementCSImpl implements PathElementCS
{
	/**
	 * The number of structural features of the '<em>Path Element CS</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PATH_ELEMENT_CS_FEATURE_COUNT = ElementCSImpl.ELEMENT_CS_FEATURE_COUNT + 3;

	/**
	 * The cached value of the '{@link #getElementType() <em>Element Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementType()
	 * @generated
	 * @ordered
	 */
	protected EClassifier elementType;

	/**
	 * The cached value of the '{@link #getReferredElement() <em>Referred Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredElement()
	 * @generated
	 * @ordered
	 */
	protected Element referredElement;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PathElementCSImpl()
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
		return BaseCSPackage.Literals.PATH_ELEMENT_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PathNameCS getOwningPathName()
	{
		if (eContainerFeatureID() != (3)) return null;
		return (PathNameCS)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningPathName(PathNameCS newOwningPathName, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newOwningPathName, 3, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningPathName(PathNameCS newOwningPathName)
	{
		if (newOwningPathName != eInternalContainer() || (eContainerFeatureID() != (3) && newOwningPathName != null))
		{
			if (EcoreUtil.isAncestor(this, newOwningPathName))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningPathName != null)
				msgs = ((InternalEObject)newOwningPathName).eInverseAdd(this, 3, PathNameCS.class, msgs);
			msgs = basicSetOwningPathName(newOwningPathName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 3, newOwningPathName, newOwningPathName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReferredElement(Element newReferredElement)
	{
		Element oldReferredElement = referredElement;
		referredElement = newReferredElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 4, oldReferredElement, referredElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClassifier getElementType()
	{
		if (elementType != null && elementType.eIsProxy())
		{
			InternalEObject oldElementType = (InternalEObject)elementType;
			elementType = (EClassifier)eResolveProxy(oldElementType);
			if (elementType != oldElementType)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 2, oldElementType, elementType));
			}
		}
		return elementType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClassifier basicGetElementType()
	{
		return elementType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setElementType(EClassifier newElementType)
	{
		EClassifier oldElementType = elementType;
		elementType = newElementType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 2, oldElementType, elementType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case 3:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningPathName((PathNameCS)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
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
			case 3:
				return basicSetOwningPathName(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
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
			case 3:
				return eInternalContainer().eInverseRemove(this, 3, PathNameCS.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
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
			case 2:
				if (resolve) return getElementType();
				return basicGetElementType();
			case 3:
				return getOwningPathName();
			case 4:
				if (resolve) return getReferredElement();
				return basicGetReferredElement();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case 2:
				setElementType((EClassifier)newValue);
				return;
			case 3:
				setOwningPathName((PathNameCS)newValue);
				return;
			case 4:
				setReferredElement((Element)newValue);
				return;
		}
		super.eSet(featureID, newValue);
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
			case 2:
				setElementType((EClassifier)null);
				return;
			case 3:
				setOwningPathName((PathNameCS)null);
				return;
			case 4:
				setReferredElement((Element)null);
				return;
		}
		super.eUnset(featureID);
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
			case 2:
				return elementType != null;
			case 3:
				return getOwningPathName() != null;
			case 4:
				return referredElement != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return visitor.visitPathElementCS(this);
	}

	/*
	 * Overloaded to promote references to persisted CS/ES elements to their internal AS elements.
	 */
	@Override
	public EObject eResolveProxy(InternalEObject proxy) {
		Notifier esResolvedProxy = resolveProxy(proxy);
		EObject asResolvedProxy = null;
		if (esResolvedProxy == null) {									// Not resolved
		}
		else if (esResolvedProxy instanceof Resource) {					// If resolution is to an Ecore resource resolve to its AS Model
			EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			if (environmentFactory != null) {
				try {
					External2AS es2as = External2AS.getAdapter((Resource)esResolvedProxy, environmentFactory);
					asResolvedProxy = es2as.getASModel();
				} catch (ParserException e) {
					e.printStackTrace();		// Never happens proxies do not parse
				}
			}
		}
		else if (esResolvedProxy instanceof EModelElement) {			// If resolution is to an Ecore element resolve to its AS
			EModelElement eModelElement = (EModelElement)esResolvedProxy;
			assert !eModelElement.eIsProxy();
			EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			if (environmentFactory != null) {
				try {
					asResolvedProxy = ((EnvironmentFactoryInternalExtension)environmentFactory).getASOf(Element.class, eModelElement);
					if (proxy.eProxyURI().fragment() == null) {
						asResolvedProxy = EcoreUtil.getRootContainer(asResolvedProxy);
					}
				} catch (ParserException e) {
					e.printStackTrace();		// Never happens proxies do not parse
				}
			}
		}
		else if (esResolvedProxy instanceof Element) {
			Element asElement = (Element)esResolvedProxy;
			assert !asElement.eIsProxy();
			return asElement;											// Xtext proxies are resolved directly to AS
		}
		else if (esResolvedProxy instanceof Pivotable) {				// If resolution is to a CS element resolve to its AS
			Pivotable esPivotable = (Pivotable)esResolvedProxy;
			assert !esPivotable.eIsProxy();
			asResolvedProxy = esPivotable.getPivot();
			if (asResolvedProxy == null) {
				// XXX need to reload but need to avoid recursion/repetition - a CSResource error lock out
				EnvironmentFactoryInternal environmentFactory = ThreadLocalExecutor.getEnvironmentFactory();
				CSResource csResource = (CSResource)eResource();
				CS2AS cs2as = (CS2AS)csResource.getCS2AS(environmentFactory);
				try {
					@SuppressWarnings("unused") ASResource asResource = cs2as.reload();
					asResolvedProxy = ((Pivotable)esResolvedProxy).getPivot();
					assert asResolvedProxy != null;
				} catch (SemanticException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//	environmentFactory.re
			//	ASResource asResource = environmentFactory.reload(csResource);
			//	element = ((Pivotable)eObject).getPivot();
			}
		/*	if ((asResolvedProxy != null) && asResolvedProxy.eIsProxy()) {	// XXX pivot is never a proxy
			//	proxy = (InternalEObject)eResolveProxy(proxy);			// XXX need to displace stale AS
				EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension)ThreadLocalExecutor.getEnvironmentFactory();
				try {
					asResolvedProxy = environmentFactory.getASOf(Element.class, esResolvedProxy);
				} catch (ParserException e) {
					// XXX Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (asResolvedProxy != null) {
				getClass();		// XXX
				if (s != null) {
					s.append(" => " + NameUtil.debugSimpleName(asResolvedProxy));
					ASResourceImpl.RESOLVE_PROXY.println(s.toString());
				}
				return asResolvedProxy;
			} */
		/*	if (proxy instanceof Parameter) {
				assert false;
				ExpressionInOCL asExpression = null;
				for (EObject eObject = this; eObject != null; eObject = eObject.eContainer()) {
					if (eObject instanceof PivotableElementCS) {
						Element asElement = ((PivotableElementCS)eObject).getPivot();
						if (asElement instanceof ExpressionInOCL) {
							asExpression = (ExpressionInOCL)asElement;
							break;
						}
					}
				}
				if (asExpression != null) {
					Parameter asParameter = (Parameter)proxy;
					if ("text".equals(((ParameterCS)esResolvedProxy).getName())) {
						getClass();
					}
					ParameterVariable asParameterVariable = (ParameterVariable)NameUtil.getNameable(asExpression.getOwnedParameters(), ((ParameterCS)esResolvedProxy).getName());
					assert asParameterVariable != null;
					/*	if (asParameterVariable == null) {
						asParameterVariable = PivotFactory.eINSTANCE.createParameterVariable();
					//	if (resolvedProxy instanceof Parameter) {
							asParameterVariable.setRepresentedParameter(asParameter);
							asParameterVariable.setName(asParameter.getName());
							asParameterVariable.setType(asParameter.getType());
							asParameterVariable.setIsRequired(asParameter.isIsRequired());
							asExpression.getOwnedParameters().add(asParameterVariable);
					//	}
					} * /
					asResolvedProxy = asParameterVariable;
				/*	ParameterVariable proxyParameterVariable = PivotFactory.eINSTANCE.createParameterVariable();
					if (resolvedProxy instanceof Parameter) {
						Parameter asParameter = (Parameter)resolvedProxy;
						if ("text".equals(asParameter.getName())) {
							getClass();
						}
						proxyParameterVariable.setRepresentedParameter(asParameter);
						proxyParameterVariable.setName(asParameter.getName());
						proxyParameterVariable.setType(asParameter.getType());
						proxyParameterVariable.setIsRequired(asParameter.isIsRequired());
						resolvedProxy = proxyParameterVariable;
					}
					else if (resolvedProxy instanceof Type) {
						assert proxy.eContainingFeature() == PivotPackage.Literals.EXPRESSION_IN_OCL__OWNED_CONTEXT;
						proxyParameterVariable.setRepresentedParameter(null);
					//	((ParameterVariableImpl)proxy).eSetProxyURI(null);
						resolvedProxy = asExpression.getOwnedContext();
					}
					else {
						throw new IllegalStateException();
					} * /
				}
			} */
		}
		else {
			assert false;												// unsupported never happens
		}
		if (ASResourceImpl.RESOLVE_PROXY.isActive()) {
			ASResourceImpl.RESOLVE_PROXY.println("\t\t\t\t\t\t\t\t" + NameUtil.debugSimpleName(esResolvedProxy) + " => " + NameUtil.debugSimpleName(asResolvedProxy));
		}
		return asResolvedProxy != null ? asResolvedProxy : (EObject)esResolvedProxy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Element getReferredElement() {
		if (referredElement != null && referredElement.eIsProxy())
		{
			InternalEObject oldReferredElement = (InternalEObject)referredElement;
			referredElement = (Element)eResolveProxy(oldReferredElement);
			if (referredElement != oldReferredElement)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, 4, oldReferredElement, referredElement));
			}
		}
		return referredElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Element basicGetReferredElement()
	{
		return referredElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Element getPivot() {
		return getReferredElement();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isType() {
		return elementType == PivotPackage.Literals.TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void resetPivot() {}
} //SimpleNamedElementRefCSImpl
