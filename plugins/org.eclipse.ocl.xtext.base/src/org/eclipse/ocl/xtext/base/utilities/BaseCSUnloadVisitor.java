/*******************************************************************************
 * Copyright (c) 2024 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.utilities;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.internal.ElementImpl;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.xtext.basecs.ElementCS;
import org.eclipse.ocl.xtext.basecs.PathElementCS;
import org.eclipse.ocl.xtext.basecs.util.AbstractExtendingBaseCSVisitor;
import org.eclipse.ocl.xtext.basecs.util.VisitableCS;

public class BaseCSUnloadVisitor extends AbstractExtendingBaseCSVisitor<Object, @NonNull CSResource>
{
	protected final @NonNull Map<@NonNull Element, @NonNull Element> target2proxy = new HashMap<>();

	protected BaseCSUnloadVisitor(@NonNull CSResource context) {
		super(context);
	}

	protected <T extends Element> @NonNull T getProxy(@NonNull T asElement) {
		Element asProxy = target2proxy.get(asElement);
		if (asProxy == null) {
			Object reloadableEObjectOrURI = ((ElementImpl)asElement).getReloadableEObjectOrURI();
			URI reloadableURI;
			if (reloadableEObjectOrURI instanceof URI) {
				reloadableURI = (URI)reloadableEObjectOrURI;
			}
			else if (reloadableEObjectOrURI == null) {
				reloadableURI = EcoreUtil.getURI(asElement);
			}
			else {
				reloadableURI = EcoreUtil.getURI((EObject)reloadableEObjectOrURI);
			}
			assert !reloadableURI.toString().contains(".oclas") : NameUtil.debugSimpleName(asElement) + " => " + reloadableURI;
		/*	Notifier reloadableNotifier = asElement;
			Notifier reloadableNotifier2 = ((ElementImpl)asElement).getReloadableNotifier();		// XXX
			if (reloadableNotifier2 != null) {
				reloadableNotifier = reloadableNotifier2;
			}
			URI reloadableURI;
			if (reloadableNotifier instanceof EObject) {
				EObject eObject = (EObject)reloadableNotifier;
				reloadableURI = EcoreUtil.getURI(eObject);
			}
			else {
				Resource eResource = (Resource)reloadableNotifier;
				assert eResource != null;
				reloadableURI = eResource.getURI();
			} */
			EClass eClass = asElement.eClass();
			asProxy = (Element)eClass.getEPackage().getEFactoryInstance().create(eClass);
			((InternalEObject)asProxy).eSetProxyURI(reloadableURI);
			target2proxy.put(asElement, asProxy);
		}
		System.out.println(NameUtil.debugSimpleName(asElement) + " => " + NameUtil.debugSimpleName(asProxy) + " " + EcoreUtil.getURI(asProxy));
		return (T)asProxy;
	}

	public @NonNull Map<@NonNull Element, @NonNull Element> proxify() {
		for (EObject eObject : context.getContents()) {
			((VisitableCS)eObject).accept(this);
		}
		return target2proxy;
	}

	@Override
	public Object visitElementCS(@NonNull ElementCS csElement) {
		for (EObject eObject : csElement.eContents()) {
			((VisitableCS)eObject).accept(this);
		}
		return null;
	}

	@Override
	public Object visitPathElementCS(@NonNull PathElementCS csPathElement) {
		Element asElement = csPathElement.getReferredElement();
		if (asElement != null) {
			Element asProxy = getProxy(asElement);
		//	System.out.println(NameUtil.debugSimpleName(csPathElement) + ".referredElement => " + NameUtil.debugSimpleName(asProxy) + " " + EcoreUtil.getURI(asProxy));
			csPathElement.setReferredElement(asProxy);
		}
		return visitElementCS(csPathElement);
	}

	@Override
	public Object visiting(@NonNull VisitableCS visitable) {
		throw new IllegalArgumentException("Unsupported " + visitable.eClass().getName() + " for CS2AS PreOrder pass");
	}
}
