/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.delegate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.OCLCommon;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.Pivotable;
import org.eclipse.ocl.pivot.utilities.UniqueList;

/**
 * ExtendedEObjectValidatorAdapter adapts a ResourceSet to enable the ExtendedEObjectValidator functionality for
 * elements of the ResourceSet and provide a cache of the delegates available to an ExtendedEObjectValidator.
 *
 * @since 7.0
 */
public class ExtendedEObjectValidatorAdapter implements Adapter
{
	/**
	 * Return an ExtendedEObjectValidatorAdapter for this resourceSet.
	 */
	public static @Nullable ExtendedEObjectValidatorAdapter basicGetAdapter(@NonNull ResourceSet resourceSet) {
		synchronized (resourceSet) {
			List<Adapter> eAdapters = resourceSet.eAdapters();
			for (Adapter adapter : eAdapters) {
				if (adapter instanceof ExtendedEObjectValidatorAdapter) {
					return (ExtendedEObjectValidatorAdapter)adapter;
				}
			}
			return null;
		}
	}

	/**
	 * The user ResourceSet for which validation is installed.
	 */
	private @Nullable ResourceSet resourceSet;

	/**
	 * The per EClass list of the URIs of constraints realized by delegates.
	 */
	private @NonNull Map<@NonNull EClass, @NonNull UniqueList<@NonNull URI>> eClass2delegateURIs = new HashMap<>();

	/**
	 * The cached list of all the non-fragment URIs of constraints realized by delegates.
	 */
	private @Nullable UniqueList<@NonNull URI> delegateURIs = null;

	public ExtendedEObjectValidatorAdapter(@NonNull ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	public void addEClass2DelegateURIs(@NonNull Map<@NonNull EClass, @NonNull UniqueList<@NonNull URI>> eClass2localDelegateURIs) {
		for (Map.@NonNull Entry<@NonNull EClass, @NonNull UniqueList<@NonNull URI>> entry : eClass2localDelegateURIs.entrySet()) {
			UniqueList<@NonNull URI> localDelegateURIs = entry.getValue();
			if (localDelegateURIs.size() > 0) {
				EClass eClass = entry.getKey();
				UniqueList<@NonNull URI> allDelegateURIs = eClass2delegateURIs.get(eClass);
				if (allDelegateURIs == null) {
					allDelegateURIs = new UniqueList<>();
					eClass2delegateURIs.put(eClass, allDelegateURIs);
				}
				for (URI localDelegateURI : localDelegateURIs) {
					allDelegateURIs.add(localDelegateURI);
				}
			}
		}
	}

	public @NonNull UniqueList<@NonNull URI> getDelegateURIs() {
		UniqueList<@NonNull URI> delegateURIs2 = delegateURIs;
		if (delegateURIs2 == null) {
			delegateURIs = delegateURIs2 = new UniqueList<>();
			for (@NonNull UniqueList<@NonNull URI> someDelegateURIs : eClass2delegateURIs.values()) {
				for (URI delegateURI : someDelegateURIs) {
					delegateURIs2.add(delegateURI.trimFragment());
				}
			}
		}
		return delegateURIs2;
	}

	@Override
	public @NonNull ResourceSet getTarget() {
		assert resourceSet != null;
		return resourceSet;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return false;
	}

	@Override
	public void notifyChanged(Notification notification) {}

	public void removeConstraintsOrURIs(@NonNull Map<@NonNull EClass, @NonNull UniqueList<@NonNull Object>> eClass2delegateURIs) {
	//	List<@NonNull EClass> eClassesToRemove = new ArrayList<>();
		for (Map.@NonNull Entry<@NonNull EClass, @NonNull UniqueList<@NonNull Object>> entry : new ArrayList<>(eClass2delegateURIs.entrySet())) {
			EClass eClass = entry.getKey();
			Collection<@NonNull Object> constraintsOrURIs = entry.getValue();
			Collection<@NonNull Object> allConstraints = eClass2delegateURIs.get(eClass);
			if (allConstraints != null) {
				allConstraints.removeAll(constraintsOrURIs);
				if (allConstraints.size() <= 0) {
					eClass2delegateURIs.remove(eClass);
				}
			}
		}
		// XXX recompute
		delegateURIs = null;
	//	for (EClass eClass : eClassesToRemove) {
	//		eClass2delegateURIs.remove(eClass);
	//	}
	}

	/**
	 * Return a per-EClass map that identifies actaul delegate constraints resolved in environmentFactory for each delegate URI.
	 * @throws ParserException
	 */
	public @NonNull Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> resolveDelegateConstraints(@NonNull EnvironmentFactoryInternal environmentFactory) throws ParserException {
		Map<@NonNull EClass, @NonNull UniqueList<@NonNull Constraint>> eClass2Constraints = new HashMap<>();
		ResourceSet externalResourceSet = environmentFactory.getResourceSet();
		List<@NonNull ASResource> asDelegatingResources = new UniqueList<>();
		for (Map.@NonNull Entry<@NonNull EClass, @NonNull UniqueList<@NonNull URI>> entry : eClass2delegateURIs.entrySet()) {
			EClass eClass = entry.getKey();
			if ("Book".equals(eClass.getName())) {
				getClass();		// XXX
			}
			UniqueList<@NonNull Constraint> asConstraints = new UniqueList<>();
			for (@NonNull URI delegateURI : entry.getValue()) {
				Constraint asConstraint = null;
				EObject eObject = externalResourceSet.getEObject(delegateURI, true);
				if (eObject instanceof Pivotable) {															// Complete OCL CS element
					asConstraint = (Constraint)((Pivotable)eObject).getPivot();
					assert asConstraint != null;
					asConstraints.add(asConstraint);						// XXX needs installCompleteOCLDelegates
					EObject esObject = asConstraint.getESObject();
					if (esObject == null) {
						ASResource asResource = (ASResource)asConstraint.eResource();
						assert asResource != null;
						asDelegatingResources.add(asResource);
					}
				}
				else if ((eObject instanceof EAnnotation) && EcorePackage.eNS_URI.equals(((EAnnotation)eObject).getSource())) {		// EMF Java-implemented constraint(s)
					EClassifier eConstrainedClass = (EClassifier)eObject.eContainer();
					assert eConstrainedClass != null;
					org.eclipse.ocl.pivot.Class asConstrainedClass = environmentFactory.getASOf(org.eclipse.ocl.pivot.Class.class, eConstrainedClass);
					assert asConstrainedClass != null;
					for (String constraintKey : DelegateInstaller.getConstraintNames(eConstrainedClass)) {
						asConstraint = NameUtil.getNameable(asConstrainedClass.getOwnedInvariants(), constraintKey);
						assert asConstraint != null;
						asConstraints.add(asConstraint);			// XXX name clashes
					}
				}
				else if (eObject instanceof EOperation) {									// EMF OCL-implemented invariant
					Element asElement = environmentFactory.getASOf(Element.class, eObject);
					asConstraint = (Constraint)asElement;
					assert asConstraint != null;
					asConstraints.add(asConstraint);
				}
				else if (eObject instanceof EStringToStringMapEntryImpl) {									// OCL in Ecore delegate constraint
					EStringToStringMapEntryImpl eDetail = (EStringToStringMapEntryImpl)eObject;
					EAnnotation eAnnotation = (EAnnotation)eObject.eContainer();
					assert OCLCommon.isDelegateURI(eAnnotation.getSource());
					asConstraint = environmentFactory.getASOf(Constraint.class, eDetail);
					assert asConstraint != null;
					asConstraints.add(asConstraint);			// XXX name clashes
				}
				else {
					throw new ParserException("Failed to resolve '" + delegateURI + "'");
				}
			}
			eClass2Constraints.put(eClass, asConstraints);
			StringBuilder s = new StringBuilder();
			s.append(eClass.getName());
			for (Constraint asConstraint : asConstraints) {
				s.append("\n\t" + NameUtil.debugSimpleName(asConstraint) + " " + asConstraint.getName());
			}
			System.out.println(s.toString());
		}
		DelegateInstaller delegateInstaller = new DelegateInstaller(environmentFactory, null);
		for (ASResource asResource : asDelegatingResources) {
			ResourceSet userResourceSet = environmentFactory.getUserResourceSet();
		//	if (userResourceSet != null) {			// XXX no Complete OCL if no user resources / environmentFactory.getResourceSet()
				delegateInstaller.installCompleteOCLDelegates(userResourceSet, asResource);
		//	}
		}
		return eClass2Constraints;
	}

	@Override
	public void setTarget(Notifier newTarget) {
		this.resourceSet = (ResourceSet)newTarget;
	}
}