/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   E.D.Willink - Initial API and implementation
 *   E.D.Willink - Bug 353171
 *******************************************************************************/
package org.eclipse.ocl.ecore.delegate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.common.delegate.DelegateResourceSetAdapter;
import org.eclipse.ocl.common.delegate.VirtualDelegateMapping;

/**
 * DelegateEPackageAdapter extends an EPackage to cache its DelegateDomain
 * that supervises installation of OCL annotations from an OCL document.
 * 
 * @since 3.0
 */
public class DelegateEPackageAdapter extends AdapterImpl {
	/**
	 *	Return the DelegateEPackageAdapter for ePackage, if there is one, or null if none.
	 *
	 * @since 3.1
	 */
	public static DelegateEPackageAdapter findAdapter(EPackage ePackage) {
		return (DelegateEPackageAdapter) EcoreUtil.getAdapter(ePackage.eAdapters(), DelegateEPackageAdapter.class);
	}

	/**
	 *	Return the DelegateEPackageAdapter for ePackage, creating
	 *	one if necessary.
	 */
	public static DelegateEPackageAdapter getAdapter(EPackage ePackage) {
		DelegateEPackageAdapter adapter = (DelegateEPackageAdapter) EcoreUtil.getAdapter(ePackage.eAdapters(), DelegateEPackageAdapter.class);
		if (adapter == null) {
			adapter = new DelegateEPackageAdapter();
			ePackage.eAdapters().add(adapter);
		}
		return adapter;
	}

	/**
	 * The map from delegateURI to known DelegateDomain. Mappings are established
	 * lazily by {@link #getDelegateDomain}.
	 */
	protected Map<String, DelegateDomain> delegateDomainMap = null;

	/**
	 * The map from behavior name to corresponding DelegateDomain. This is
	 * defined by an http://www.eclipse.org/emf/2002/Ecore EPackage annotation
	 * with the behavior name as a key and the delegateURIs as a comma
	 * separated list.
	 * 
	 * 	@deprecated (since 3.3) Known delegate names are not usefully behavior-specific; use getAllDelegateDomains()
	 */
	@Deprecated // since 3.3
	protected Map<String, List<DelegateDomain>> delegatedBehaviorMap = null;

	protected DelegateDomain createDelegateDomain(String delegateURI) {
		EPackage ePackage = getTarget();
		DelegateDomain.Factory.Registry registry = DelegateResourceSetAdapter.getRegistry(
			ePackage, DelegateDomain.Factory.Registry.class, DelegateDomain.Factory.Registry.INSTANCE);
		DelegateDomain.Factory factory = registry.getFactory(delegateURI);
		if (factory == null) {
			factory = OCLDelegateDomainFactory.INSTANCE;
		}
		return factory.createDelegateDomain(delegateURI, ePackage);
	}

	/**
	 * Return all registered delegate domains.
	 * 
	 * @since 3.3
	 */
	public Collection<DelegateDomain> getAllDelegateDomains() {
		if (delegateDomainMap == null) {
			getDelegateDomains();
		}
		return delegateDomainMap.values();
	}

	/**
	 * Return the DelegateDomain for this package and for delegateURI, returning null it does not exist. 
	 */
	public DelegateDomain getDelegateDomain(String delegateURI) {
		if (delegateDomainMap == null) {
			getDelegateDomains();
		}
		return delegateDomainMap.get(delegateURI);
	}

	public Map<String, DelegateDomain> getDelegateDomains() {
		if (delegateDomainMap == null) {
			delegatedBehaviorMap = new HashMap<String, List<DelegateDomain>>();
			delegateDomainMap = new HashMap<String, DelegateDomain>();
			EPackage ePackage = getTarget();
			EAnnotation eAnnotation = ePackage.getEAnnotation(EcorePackage.eNS_URI);
			if (eAnnotation != null) {
				VirtualDelegateMapping registry = VirtualDelegateMapping.getRegistry(ePackage);
				EMap<String, String> details = eAnnotation.getDetails();
				for (DelegatedBehavior<?, ?, ?> delegatedBehavior : AbstractDelegatedBehavior.getDelegatedBehaviors()) {
					String behaviorName = delegatedBehavior.getName();
					String delegateURIs = details.get(behaviorName);
					if (delegateURIs != null) {
						for (StringTokenizer stringTokenizer = new StringTokenizer(delegateURIs); stringTokenizer.hasMoreTokens();) {
							String delegateURI = stringTokenizer.nextToken();
							String resolvedURI = registry.resolve(delegateURI);
							DelegateDomain delegateDomain = loadDelegateDomain(resolvedURI);
							synchronized (delegatedBehaviorMap) {		// Preserved till delegatedBehaviorMap is more than deprecated
								List<DelegateDomain> delegateBehaviorList = delegatedBehaviorMap.get(behaviorName);
								if (delegateBehaviorList == null) {
									delegateBehaviorList = new ArrayList<DelegateDomain>();
									delegatedBehaviorMap.put(behaviorName, delegateBehaviorList);
								}
								if (!delegateBehaviorList.contains(delegateDomain)) {
									delegateBehaviorList.add(delegateDomain);
								}
							}
						}
					}
				}
			}
		}
		return delegateDomainMap;
	}

	/*
	 * Return all the delegate domains registered for a delegatedBehavior.
	 * 
	 * 	@Deprecated Known delegate names are not usefully behavior-specific; use getAllDelegateDomains()
	 */
	@Deprecated // since 3.3
	public List<DelegateDomain> getDelegateDomains(DelegatedBehavior<?, ?, ?> delegatedBehavior) {
		if (delegatedBehaviorMap == null) {
			getDelegateDomains();
		}
		List<DelegateDomain> list = delegatedBehaviorMap.get(delegatedBehavior.getName());
		if (list != null) {
			return list;
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public EPackage getTarget() {
		return (EPackage) super.getTarget();
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return type == DelegateEPackageAdapter.class;
	}

	/**
	 * Return the DelegateDomain for this package and for delegateURI, creating one if it does not already exist. 
	 *
	 * @since 3.2
	 */
	public DelegateDomain loadDelegateDomain(String delegateURI) {
		if (delegateDomainMap == null) {
			getDelegateDomains();
		}
		DelegateDomain delegateDomain = delegateDomainMap.get(delegateURI);
		if (delegateDomain == null) {
			synchronized (delegateDomainMap) {
				delegateDomain = delegateDomainMap.get(delegateURI);
				if (delegateDomain == null) {
					delegateDomain = createDelegateDomain(delegateURI);
					delegateDomainMap.put(delegateURI, delegateDomain);
				}
			}
		}
		return delegateDomain;
	}

	@Override
	public void setTarget(Notifier newTarget) {
		EPackage resourceSet = (EPackage) newTarget;
		super.setTarget(resourceSet);
	}

	public void unloadDelegates() {
		if (delegateDomainMap != null) {
			List<DelegateDomain> delegateDomains;
			synchronized (delegateDomainMap) {
				delegateDomains = new ArrayList<DelegateDomain>(delegateDomainMap.values());
				delegateDomainMap.clear();
			}
			for (DelegateDomain delegateDomain : delegateDomains) {
				delegateDomain.dispose();
			}
		}
		EPackage ePackage = getTarget();
		if (ePackage != null) {
			ePackage.eAdapters().remove(this);
		}
	}
}
