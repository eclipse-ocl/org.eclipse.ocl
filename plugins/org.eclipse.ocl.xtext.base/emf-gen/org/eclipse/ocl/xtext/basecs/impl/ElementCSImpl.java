/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	 E.D.Willink - initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.xtext.basecs.impl;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap.DelegatedSinglePackageResource;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.Pivotable;
import org.eclipse.ocl.pivot.utilities.SemanticException;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.CSI;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.ElementCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.ElementCSImpl#getCsi <em>Csi</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.ElementCSImpl#getParent <em>Parent</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ElementCSImpl extends EObjectImpl implements ElementCS {
	/**
	 * The number of structural features of the '<em>Element CS</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ELEMENT_CS_FEATURE_COUNT = 2;

	/**
	 * The default value of the '{@link #getCsi() <em>Csi</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCsi()
	 * @generated
	 * @ordered
	 */
	protected static final CSI CSI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCsi() <em>Csi</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCsi()
	 * @generated
	 * @ordered
	 */
	protected CSI csi = CSI_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BaseCSPackage.Literals.ELEMENT_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CSI getCsi()
	{
		return csi;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCsi(CSI newCsi)
	{
		CSI oldCsi = csi;
		csi = newCsi;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 0, oldCsi, csi));
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
				return getCsi();
			case 1:
				return getParent();
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
			case 0:
				setCsi((CSI)newValue);
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
			case 0:
				setCsi(CSI_EDEFAULT);
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
			case 0:
				return CSI_EDEFAULT == null ? csi != null : !CSI_EDEFAULT.equals(csi);
			case 1:
				return getParent() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return visitor.visitElementCS(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getDescription()
	{
		return eClass().getName();
	}

	/**
	 * Provide a debug representation of this elemnt's node. Use ElementUtil.getRawText/getTrimmedText for clear real usage.
	 *
	 * @generated NOT
	 */
	@Override
	public String toString() {
		ICompositeNode parserNode = NodeModelUtils.getNode(this);
		if (parserNode != null) {
			String text = parserNode.getText();
			return text.trim();
		}
		return NameUtil.debugFullName(this);
	}

	@Override
	public void eSetProxyURI(URI uri) {
		ASResourceImpl.SET_PROXY.println(ThreadLocalExecutor.getBracketedThreadName() + " " + NameUtil.debugSimpleName(this) + " " + uri);
		assert (uri == null) || !uri.toString().contains(PivotConstants.DOT_OCL_AS_FILE_EXTENSION);
		super.eSetProxyURI(uri);
	}

	@Override
	public EObject eResolveProxy(InternalEObject proxy) {
		Notifier notifier = resolveProxy(proxy);
		if (notifier instanceof Resource) {
			List<@NonNull EObject> contents = ((Resource)notifier).getContents();
			return contents.size() > 0 ? contents.get(0) : proxy;
		}
		else {
			return notifier != null ? (EObject) notifier : proxy;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public ElementCS getParent()
	{
		EObject eContainer = eContainer();
		return eContainer instanceof ElementCS ? (ElementCS) eContainer : null;		// Avoid CCE for Bug 432749
	}

	protected @Nullable Notifier resolveProxy(/*@NonNull*/ InternalEObject proxy) {
		assert proxy != null;
		URI eProxyURI = proxy.eProxyURI();
		assert eProxyURI != null;
		StringBuilder s = null;
		if (ASResourceImpl.RESOLVE_PROXY.isActive()) {
			s = new StringBuilder();
			s.append(NameUtil.debugSimpleName(this) + " «" + proxy.eProxyURI() + "» (" + NameUtil.debugSimpleName(proxy) + ")");
		}
		CSResource csResource = (CSResource)eResource();
		String fragment = eProxyURI.fragment();
		if ((fragment != null) && !fragment.startsWith("|")) {			// i.e. not Xtext parsing intermediate
			for (EObject eObject : csResource.getContents()) {
				if (eObject instanceof Pivotable) {
					Pivotable csElement = (Pivotable)eObject;
					if (csElement.getPivot() == null) {
						EnvironmentFactory environmentFactory = ThreadLocalExecutor.getEnvironmentFactory();
						CS2AS cs2as = (CS2AS)csResource.getCS2AS(environmentFactory);

						try {
							@SuppressWarnings("unused") ASResource asResource = cs2as.reload();
						} catch (SemanticException e) {
							// XXX TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
				}
			}
		}
		ResourceSet resourceSet = csResource.getResourceSet();
		URI resourceURI = eProxyURI.trimFragment();
		if (PivotUtil.isASURI(resourceURI)) {				// XXX review wrt earlier Xtext test
			EnvironmentFactory environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
			if (environmentFactory != null) {
				try {
					Element element = environmentFactory.getMetamodelManager().loadResource(eProxyURI, null, null);
					return element;
				} catch (ParserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		Resource resource = resourceSet.getResource(resourceURI, true);
		if (resource instanceof DelegatedSinglePackageResource) {
			resource = ((DelegatedSinglePackageResource)resource).getResource();
		}
	//	try {
	//		resource.load(null);
	//	} catch (IOException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}
		if (fragment == null) {
			if (s != null) {
				s.append(" => " + NameUtil.debugSimpleName(resource));
				ASResourceImpl.RESOLVE_PROXY.println(s.toString());
			}
		//	if (resource instanceof DelegatedSinglePackageResource) {
		//		resource = ((DelegatedSinglePackageResource)resource).getResource();
		//	}
			return resource;
		}
		else {
			EObject esResolvedProxy = resource.getEObject(fragment);
			if (s != null) {
				s.append(" => " + NameUtil.debugSimpleName(esResolvedProxy));
				ASResourceImpl.RESOLVE_PROXY.println(s.toString());
			}
			return esResolvedProxy;
		}
	}
} //ElementCSImpl
