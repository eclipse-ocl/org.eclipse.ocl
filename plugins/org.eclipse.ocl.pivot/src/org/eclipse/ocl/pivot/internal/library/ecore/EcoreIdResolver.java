/*******************************************************************************
 * Copyright (c) 2011, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.ecore;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.ids.PartId;
import org.eclipse.ocl.pivot.ids.RootPackageId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.internal.library.executor.AbstractIdResolver;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorPackage;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorStandardLibrary;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorType;
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
	private @NonNull Map<EClassifier, WeakReference<CompleteInheritance>> typeMap = new WeakHashMap<>();

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
			PackageId packageId = IdManager.getPackageId(ePackage);
			asPackage = new EcoreReflectivePackage(ePackage, this, packageId);
			nsURI2package.put(nsURI, asPackage);
			if (packageId instanceof RootPackageId) {
				roots2package.put(((RootPackageId)packageId).getName(), asPackage);
			}
		}
		return asPackage;
	}

	@Override
	public synchronized @NonNull CompleteInheritance getInheritance(@NonNull EClassifier eClassifier) {
		CompleteInheritance type = weakGet(typeMap, eClassifier);
		if (type == null) {
			EPackage ePackage = eClassifier.getEPackage();
			assert ePackage != null;
			ExecutorPackage execPackage = getStandardLibrary().getPackage(ePackage);
			if (execPackage == null) {
				PackageId packageId = IdManager.getPackageId(ePackage);
				Element domainPackage = packageId.accept(this);
				if (domainPackage instanceof ExecutorPackage) {
					execPackage = (ExecutorPackage) domainPackage;
				}
			}
			if (execPackage != null) {
				org.eclipse.ocl.pivot.Class domainType = execPackage.getOwnedClass(eClassifier.getName());
				if (domainType != null) {
					type = standardLibrary.getInheritance(domainType);
					typeMap.put(eClassifier, new WeakReference<>(type));
				}
			}
		}
		return ClassUtil.requireNonNull(type);
	}

	@Override
	protected org.eclipse.ocl.pivot.@NonNull Class getMetaclass(@NonNull Type classType) {
		throw new UnsupportedOperationException();				// XXX
	}

	@Override
	protected org.eclipse.ocl.pivot.@NonNull Type getNestedClass(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name) {
		org.eclipse.ocl.pivot.Class nestedType = NameUtil.getNameable(parentPackage.getOwnedClasses(), name);
		if (nestedType == null) {
			nestedType = getStandardLibrary().basicGetLibraryClass(name);
		}
		return ClassUtil.requireNonNull(nestedType);
	}

	@Override
	protected org.eclipse.ocl.pivot.@NonNull Type getNestedDataType(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name) {
		org.eclipse.ocl.pivot.Class nestedType = NameUtil.getNameable(parentPackage.getOwnedClasses(), name);
		if (nestedType == null) {
			nestedType = getStandardLibrary().basicGetLibraryClass(name);
		}
		return ClassUtil.requireNonNull(nestedType);
	}

	@Override
	protected org.eclipse.ocl.pivot.@NonNull Type getNestedEnumeration(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name) {
		org.eclipse.ocl.pivot.Class nestedType = NameUtil.getNameable(parentPackage.getOwnedClasses(), name);
		if (nestedType == null) {
			nestedType = getStandardLibrary().basicGetLibraryClass(name);
		}
		return ClassUtil.requireNonNull(nestedType);
	}

	@Override
	protected org.eclipse.ocl.pivot.@NonNull Package getNestedPackage(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name) {
		return ClassUtil.requireNonNull(NameUtil.getNameable(parentPackage.getOwnedPackages(), name));
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull ExecutorStandardLibrary getStandardLibrary() {
		return (ExecutorStandardLibrary)standardLibrary;
	}

	@Override
	protected org.eclipse.ocl.pivot.@Nullable Class getStaticClassOfEObject(@NonNull EObject eObject) {
		if (eObject instanceof ExecutorType) {
			ExecutorType executorType = (ExecutorType)eObject;
			org.eclipse.ocl.pivot.Class asClass = key2class.get(executorType);
			if (asClass == null) {
				String metaclassName = executorType.getTypeId().getMetaclassName();
				asClass = getStandardLibrary().getLibraryClass(metaclassName);
				key2class.put(executorType, asClass);
			}
			return asClass;
		}
		return super.getStaticClassOfEObject(eObject);
	}

	@Override
	public Notifier getTarget() {
		return null;
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull TupleTypeId typeId) {
		return getStandardLibrary().getTupleType(typeId);
	}

	public @NonNull TupleType getTupleType(@NonNull TypedElement @NonNull ... parts) {
		int iSize = parts.length;
		List<@NonNull PartId> partsList = new ArrayList<>(iSize);
		for (int i = 0; i < iSize; i++) {
			TypedElement part = parts[i];
			String partName = NameUtil.getSafeName(part);
			partsList.add(IdManager.getPartId(i, partName, part.getTypeId(), part.isIsRequired()));
		}
		return getTupleType(IdManager.getTupleTypeId(partsList));
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getType(@NonNull EClassifier eClassifier) {
		return getInheritance(eClassifier).getPivotClass();
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
