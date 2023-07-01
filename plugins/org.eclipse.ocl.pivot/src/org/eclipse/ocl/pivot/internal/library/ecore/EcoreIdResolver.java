/*******************************************************************************
 * Copyright (c) 2011, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.ecore;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.flat.EcoreFlatClass;
import org.eclipse.ocl.pivot.flat.EcoreFlatModel;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.ids.RootPackageId;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.ClassImpl;
import org.eclipse.ocl.pivot.internal.EnumerationImpl;
import org.eclipse.ocl.pivot.internal.EnumerationLiteralImpl;
import org.eclipse.ocl.pivot.internal.PackageImpl;
import org.eclipse.ocl.pivot.internal.ecore.EcoreASResourceFactory;
import org.eclipse.ocl.pivot.internal.library.executor.AbstractIdResolver;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorStandardLibrary;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;

/**
 * EcoreIdResolver provides a package discovery capability so that package identifiers can be resolved.
 * <p>
 * Given an initial seed of a standard library and one or more root EObjects, packages are discovered
 * by locating all packages and nested packages directly contained by the seed roots or by the roots of
 * any object referenced by any contained by the seed roots.
 */
public class EcoreIdResolver extends AbstractIdResolver implements Adapter
{
	//	protected @NonNull Map<ElementId, DomainElement> id2element = new HashMap<>();
//	private @NonNull Map<EClassifier, WeakReference<EcoreFlatClass>> typeMap = new WeakHashMap<>();		// XXX duplicates EcoreFlatModel

	public EcoreIdResolver(@NonNull Iterable<? extends EObject> roots, @NonNull ExecutorStandardLibrary standardLibrary) {
		super(standardLibrary);
		for (@SuppressWarnings("null")@NonNull EObject root : roots) {
			addRoot(root);
		}
	}

	@Override
	protected org.eclipse.ocl.pivot.@NonNull Package addEPackage(@NonNull EPackage ePackage) {
		String nsURI = ePackage.getNsURI();
		org.eclipse.ocl.pivot.Package asPackage = nsURI2package.get(nsURI);
		if (asPackage == null) {
			asPackage = createPackage(ePackage);			// XXX Re-use avoid duplicating Ecore2AS
			PackageId packageId = asPackage.getPackageId();
			nsURI2package.put(nsURI, asPackage);
			if (packageId instanceof RootPackageId) {
				roots2package.put(((RootPackageId)packageId).getName(), asPackage);
			}
			URI asURI = PivotUtilInternal.getASURI(URI.createURI(nsURI));
			Resource asResource = EcoreASResourceFactory.getInstance().createResource(asURI);
			asResource.getContents().add(asPackage);
		}
		return asPackage;
	}

	protected @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> computeClasses(org.eclipse.ocl.pivot.@NonNull Package evaluationPackage, @NonNull EPackage ePackage) {
		List<org.eclipse.ocl.pivot.@NonNull Class> asClasses = new ArrayList<>();
		for (EClassifier eClassifier : ePackage.getEClassifiers()) {
			if (eClassifier != null) {
				ClassImpl asClass;
				if (eClassifier instanceof EEnum) {
					EnumerationImpl asEnumeration = (EnumerationImpl) PivotFactory.eINSTANCE.createEnumeration();
					for (@NonNull EnumerationLiteral asEnumerationLiteral : computeEnumerationLiterals((EEnum)eClassifier)) {
						asEnumeration.getOwnedLiterals().add(asEnumerationLiteral);
					}
					asClass = asEnumeration;
				}
				else {
					asClass = (ClassImpl) PivotFactory.eINSTANCE.createClass();
				}
				asClass.setName(eClassifier.getName());
				asClass.setESObject(eClassifier);
				EcoreFlatModel flatModel = (EcoreFlatModel)getStandardLibrary().getFlatModel();
				EcoreFlatClass flatClass = flatModel.getEcoreFlatClass(asClass);
				asClass.setFlatClass(flatClass);
				asClasses.add(asClass);
			}
		}
		return asClasses;
	}

	protected @NonNull Iterable<@NonNull EnumerationLiteral> computeEnumerationLiterals(@NonNull EEnum eEnum) {
		List<@NonNull EnumerationLiteral> asEnumerationLiterals = new ArrayList<>();
		for (EEnumLiteral eEnumLiteral : eEnum.getELiterals()) {
			if (eEnumLiteral != null) {
				EnumerationLiteralImpl asEnumerationLiteral = (EnumerationLiteralImpl)PivotFactory.eINSTANCE.createEnumerationLiteral();
				asEnumerationLiteral.setName(eEnumLiteral.getName());
				asEnumerationLiteral.setESObject(eEnumLiteral);
				asEnumerationLiterals.add(asEnumerationLiteral);
			}
		}
		return asEnumerationLiterals;
	}

	protected @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Package> computeNestedPackages(@NonNull EPackage ePackage) {
		List<org.eclipse.ocl.pivot.@NonNull Package> nestedPackages = new ArrayList<>();
		for (EPackage eSubPackage : ePackage.getESubpackages()) {
			if (eSubPackage != null) {
				org.eclipse.ocl.pivot.Package asPackage = createPackage(eSubPackage);
				nestedPackages.add(asPackage);
			}
		}
		return nestedPackages;
	}

	protected org.eclipse.ocl.pivot.@NonNull Package createPackage(@NonNull EPackage ePackage) {
		PackageId packageId = IdManager.getPackageId(ePackage);
		PackageImpl asPackage = (PackageImpl)PivotFactory.eINSTANCE.createPackage();
		asPackage.setName(ClassUtil.nonNullEMF(ePackage.getName()));
		asPackage.setNsPrefix(ePackage.getNsPrefix());
		asPackage.setURI(ePackage.getNsURI());
		asPackage.setPackageId(packageId);
		asPackage.setESObject(ePackage);
		for (org.eclipse.ocl.pivot.@NonNull Class asClass : computeClasses(asPackage, ePackage)) {
			asPackage.getOwnedClasses().add(asClass);
		}
		for (org.eclipse.ocl.pivot.@NonNull Package asSubPackage : computeNestedPackages(ePackage)) {
			asPackage.getOwnedPackages().add(asSubPackage);
		}
		return asPackage;
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public synchronized @NonNull EcoreFlatClass getFlatClass(@NonNull EClassifier eClassifier) {
		return ((EcoreFlatModel)standardLibrary.getFlatModel()).getEcoreFlatClass(eClassifier);
	}

	@Override
	public Notifier getTarget() {
		return null;
	}

	@Override
	public synchronized @NonNull TupleType getTupleType(@NonNull TupleTypeId typeId) {
		return ((ExecutorStandardLibrary)standardLibrary).getTupleType(this, typeId);
	}

	public @NonNull TupleType getTupleType(@NonNull TypedElement @NonNull ... parts) {
		int iSize = parts.length;
		List<@NonNull TuplePartId> partsList = new ArrayList<>(iSize);
		for (int i = 0; i < iSize; i++) {
			TypedElement part = parts[i];
			String partName = NameUtil.getSafeName(part);
			partsList.add(IdManager.getTuplePartId(i, partName, part.getTypeId()));
		}
		return getTupleType(IdManager.getTupleTypeId(TypeId.TUPLE_NAME, partsList));
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getType(@NonNull EClassifier eClassifier) {
		return getFlatClass(eClassifier).getASClass();
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return false;
	}

	@Override
	public void notifyChanged(Notification notification) {}			// FIXME ?? invalidate

	@Override
	public void setTarget(Notifier newTarget) {
		//			assert newTarget == resource;
	}
}
