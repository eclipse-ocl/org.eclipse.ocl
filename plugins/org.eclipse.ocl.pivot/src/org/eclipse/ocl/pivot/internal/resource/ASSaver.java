/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
//import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.internal.utilities.AS2Moniker;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.ASSaverLocateVisitor;
import org.eclipse.ocl.pivot.utilities.ASSaverNormalizeVisitor;
import org.eclipse.ocl.pivot.utilities.ASSaverResolveVisitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

/**
 * ASSaver ensures that all references to synthesized types are terminated
 * by local copies of the synthesized types.
 */
public class ASSaver
{
	protected final @NonNull Resource resource;
	
	public ASSaver(@NonNull Resource resource) {
		this.resource = resource;
	}

	/**
	 * The moniker to operation map for operations defined with the saved resource.
	 */
	private Map<String, Operation> operations = new HashMap<String, Operation>();

	/**
	 * TypedElements that refer to specializations.
	 */
	private @NonNull List<Element> specializingElements = new ArrayList<Element>();

	/**
	 * The extra package for copies of specializations.
	 */
	private /*@LazyNonNull*/ org.eclipse.ocl.pivot.Package orphanage = null;

	/**
	 * Map of original specialization to local specialization
	 */
	private @NonNull Map<org.eclipse.ocl.pivot.Class, org.eclipse.ocl.pivot.Class> specializations = new HashMap<org.eclipse.ocl.pivot.Class, org.eclipse.ocl.pivot.Class>();

	/**
	 * The extra package for copies of specializations.
	 */
	private /*@LazyNonNull*/ org.eclipse.ocl.pivot.Class orphanageClass = null;
	
	/**
	 * The appropriate normalization visitor for each Resource.
	 */
	private /*@LazyNonNull*/ Map<@NonNull Resource, @NonNull ASSaverNormalizeVisitor> resource2normalizeVisitor = null;

	public void addSpecializingElement(@NonNull Element object) {
		specializingElements.add(object);
	}

	public boolean addSpecializingElement(@NonNull Element object, @NonNull Operation referredOperation) {
		if (!isOrphanOperation(referredOperation)) {
			return false;
		}
		else {
			specializingElements.add(object);
			return true;
		}
	}

	public boolean addSpecializingElement(@NonNull Element object, org.eclipse.ocl.pivot.@NonNull Class referredType) {
		if (PivotUtilInternal.isLibraryType(referredType)) {
			return false;
		}
		else {
			specializingElements.add(object);
			return true;
		}
	}

	protected @NonNull ASSaverLocateVisitor getLocateVisitor(@NonNull EObject eObject) {
		Resource resource = eObject.eResource();
		if (resource instanceof ASResource) {
			return ((ASResource)resource).getASResourceFactory().createASSaverLocateVisitor(this);
		}
		else if (resource == null) {
			throw new IllegalStateException("Cannot locate " + ASSaverLocateVisitor.class.getName() + " for resource-less " + eObject.eClass().getName());
		}
		else {
			throw new IllegalStateException("Cannot locate " + ASSaverLocateVisitor.class.getName() + " for non-OCL " + resource.getClass().getName());
		}
	}

	protected @NonNull ASSaverNormalizeVisitor getNormalizeVisitor(@NonNull EObject eObject) {
		Resource resource = eObject.eResource();
		if (resource == null) {
			throw new IllegalStateException("Cannot locate " + ASSaverLocateVisitor.class.getName() + " for resource-less " + eObject.eClass().getName());
		}
		if (resource2normalizeVisitor == null) {
			resource2normalizeVisitor = new HashMap<@NonNull Resource, @NonNull ASSaverNormalizeVisitor>();
		}
		ASSaverNormalizeVisitor visitor = resource2normalizeVisitor.get(resource);
		if (visitor != null) {
			return visitor;
		}
		if (resource instanceof ASResource) {
			ASResource asResource = (ASResource)resource;
			visitor = asResource.getASResourceFactory().createASSaverNormalizeVisitor(this);
			resource2normalizeVisitor.put(resource, visitor);
			return visitor;
		}
		else {
			throw new IllegalStateException("Cannot locate " + ASSaverLocateVisitor.class.getName() + " for non-OCL " + resource.getClass().getName());
		}
	}

	protected org.eclipse.ocl.pivot.@NonNull Class getOrphanClass(org.eclipse.ocl.pivot.@NonNull Package orphanagePackage) {
		org.eclipse.ocl.pivot.Class orphanageClass2 = orphanageClass;
		if (orphanageClass2 == null) {
			orphanageClass = orphanageClass2 = PivotFactory.eINSTANCE.createAnyType();		// No superclasses
			orphanageClass2.setName(PivotConstants.ORPHANAGE_NAME);
			orphanagePackage.getOwnedClasses().add(orphanageClass2);
		}
		return orphanageClass2;
	}

	protected org.eclipse.ocl.pivot.@NonNull Package getOrphanPackage(@NonNull Resource resource) {
		Package orphanage2 = orphanage;
		if (orphanage2 == null) {
			orphanage = orphanage2 = PivotFactory.eINSTANCE.createPackage();
			orphanage2.setName(PivotConstants.ORPHANAGE_NAME);
			orphanage2.setNsPrefix(PivotConstants.ORPHANAGE_PREFIX);
			orphanage2.setURI(PivotConstants.ORPHANAGE_URI);
			EList<EObject> contents = resource.getContents();
			if ((contents.size() > 0) && (contents.get(0) instanceof Model)) {
				((Model)contents.get(0)).getOwnedPackages().add(orphanage2);
			}
			else {
				contents.add(orphanage2);
			}
		}
		return orphanage2;
	}

	protected @NonNull ASSaverResolveVisitor getResolveVisitor(@NonNull EObject eObject) {
		Resource resource = eObject.eResource();
		if (resource instanceof ASResource) {
			return ((ASResource)resource).getASResourceFactory().createASSaverResolveVisitor(this);
		}
		else if (resource == null) {
			throw new IllegalStateException("Cannot locate " + ASSaverResolveVisitor.class.getName() + " for resource-less " + eObject.eClass().getName());
		}
		else {
			throw new IllegalStateException("Cannot locate " + ASSaverResolveVisitor.class.getName() + " for non-OCL " + resource.getClass().getName());
		}
	}

	protected boolean isOrphanOperation(@NonNull Operation operation) {		// FIXME Non-static PivotUtils
		// FIXME surely an orphan is one for which eResource() is null,
		//  or one that is in the orphanage.
		if (operation.getOwnedBindings().size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Prepare a pivot resource for save by redirecting all type references to
	 * specializations to local copies of the specializations.
	 */
	public void localizeSpecializations() {
		locateSpecializations(resource.getContents());
		if (specializingElements.size() > 0) {
			loadOrphanage(resource);
			for (int i = 0; i < specializingElements.size(); i++) {	// Domain may grow
				Element element = specializingElements.get(i);
				if (element != null) {
					ASSaverResolveVisitor resolveVisitor = getResolveVisitor(element);
					resolveVisitor.safeVisit(element);
				}
			}
//			List<Type> ownedTypes = orphanage.getOwnedType();
//			List<Type> sorted = ownedTypes; //WIP PivotUtil.sortByMoniker(new ArrayList<Type>(ownedTypes));
//			ownedTypes.clear();
//			ownedTypes.addAll(sorted);
		}
	}

	protected void loadOrphanage(@NonNull Resource resource) {
		Model root = null;
		Package orphanage2 = orphanage;
		if (orphanage2 == null) {
			for (EObject eRoot : resource.getContents()) {
				if (eRoot instanceof Model) {
					if (root == null) {
						root = (Model) eRoot;
					}
					if (orphanage2 == null) {
						for (org.eclipse.ocl.pivot.Package asPackage : ((Model)eRoot).getOwnedPackages()) {
							if (Orphanage.isTypeOrphanage(asPackage)) {
								orphanage = orphanage2 = asPackage;
								for (org.eclipse.ocl.pivot.Class asType : orphanage2.getOwnedClasses()) {
									if (PivotConstants.ORPHANAGE_NAME.equals(asType.getName())) {
										orphanageClass = asType;
									}
									else {
										specializations.put(asType, asType);
									}
								}
								break;
							}
						}					
					}
				}
				if ((eRoot instanceof org.eclipse.ocl.pivot.Package) && Orphanage.isTypeOrphanage((org.eclipse.ocl.pivot.Package)eRoot)) {	// FIXME Obsolete
					orphanage = orphanage2 = (org.eclipse.ocl.pivot.Package)eRoot;
					for (org.eclipse.ocl.pivot.Class asType : orphanage2.getOwnedClasses()) {
						if (PivotConstants.ORPHANAGE_NAME.equals(asType.getName())) {
							orphanageClass = asType;
						}
						else {
							specializations.put(asType, asType);
						}
					}
					break;
				}
			}
		}
	}

	protected void locateSpecializations(/*@NonNull*/ List<? extends EObject> eObjects) {
		for (EObject eObject : eObjects) {
			if (eObject instanceof Visitable) {
				ASSaverLocateVisitor locateVisitor = getLocateVisitor(eObject);
				locateVisitor.safeVisit((Visitable) eObject);
			}
			locateSpecializations(eObject.eContents());
		}
	}

	public void normalizeContents() {
		for (@NonNull TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof Visitable) {
				ASSaverNormalizeVisitor normalizeVisitor = getNormalizeVisitor(eObject);
				normalizeVisitor.safeVisit((Visitable) eObject);
			}
		}
	}

	/**
	 * Return the resolved variant of referredType, which may require creation
	 * of a local copy of a specialization.
	 */
	public @NonNull <T extends Operation> T resolveOperation(@NonNull T referredOperation) {
		if (!isOrphanOperation(referredOperation)) {
			return referredOperation;
		}
		String moniker = AS2Moniker.toString(referredOperation);
		Operation operation = operations.get(moniker);
		if (operation != null) {
			@SuppressWarnings("unchecked") 
			T castOperation = (T) operation;
			return castOperation;
		}
		T resolvedOperation = ClassUtil.nonNullEMF(EcoreUtil.copy(referredOperation));
		if (orphanageClass == null) {
			Package orphanage2 = orphanage;
			if (orphanage2 == null) {
				orphanage2 = getOrphanPackage(resource);
			}
			orphanageClass = getOrphanClass(orphanage2);
		}
		orphanageClass.getOwnedOperations().add(resolvedOperation);
		operations.put(moniker, resolvedOperation);
		String newMoniker = AS2Moniker.toString(resolvedOperation);
		assert moniker.equals(newMoniker);
		locateSpecializations(Collections.singletonList(resolvedOperation));
		return resolvedOperation;
	}

	/**
	 * Return the resolved variant of referredType, which may require creation
	 * of a local copy of a specialization.
	 */
	public @NonNull <T extends org.eclipse.ocl.pivot.Class> T resolveType(@NonNull T referredType) {
		if (PivotUtilInternal.isLibraryType(referredType)) {
			return referredType;
		}
		org.eclipse.ocl.pivot.Class resolvedType = specializations.get(referredType);
		if (resolvedType == null) {
			resolvedType = ClassUtil.nonNullEMF(EcoreUtil.copy(referredType));	// FIXME cast
			specializations.put(referredType, resolvedType);	// FIXME cast
			specializations.put(resolvedType, resolvedType);
			EObject eContainer = resolvedType.eContainer();
			if (eContainer == null) {
				Package orphanage2 = orphanage;
				if (orphanage2 == null) {
					orphanage2 = getOrphanPackage(resource);
				}
				orphanage.getOwnedClasses().add(resolvedType);
			}
		}
/*			String moniker = AS2Moniker.toString(referredType);
		Type type = types.get(moniker);
		if (type != null) {
			@SuppressWarnings("unchecked") 
			T castType = (T) type;
			return castType;
		}
		T resolvedType = EcoreUtil.copy(referredType);
		orphanage.getOwnedType().add(resolvedType);
		types.put(moniker, resolvedType);
		String newMoniker = AS2Moniker.toString(resolvedType);
//		assert moniker.equals(newMoniker) : newMoniker + " is not equal to " + moniker;
		if (!moniker.equals(newMoniker)) {
			String moniker2 = AS2Moniker.toString(referredType);
			String newMoniker2 = AS2Moniker.toString(resolvedType);
			assert moniker.equals(newMoniker) : newMoniker + " is not equal to " + moniker;
		} */
		locateSpecializations(Collections.singletonList(resolvedType));
		@SuppressWarnings("unchecked")
		T castType = (T)resolvedType;
		return castType;
	}
}