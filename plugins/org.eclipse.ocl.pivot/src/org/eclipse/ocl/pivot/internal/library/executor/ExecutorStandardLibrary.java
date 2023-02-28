/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.executor;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.flat.EcoreFlatClass;
import org.eclipse.ocl.pivot.flat.EcoreFlatModel;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.NamedFlatClass;
import org.eclipse.ocl.pivot.flat.TypeIdFlatClass;
import org.eclipse.ocl.pivot.ids.BuiltInTypeId;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorAnyType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorBagType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorBooleanType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorCollectionType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorEnumeration;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorInvalidType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorOrderedSetType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorPackage;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorPrimitiveType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorSequenceType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorSetType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorVoidType;
import org.eclipse.ocl.pivot.utilities.NameUtil;

public class ExecutorStandardLibrary extends ExecutableStandardLibrary
{
	private @NonNull Map<@NonNull String, WeakReference<@NonNull EcoreExecutorPackage>> ePackageMap = new WeakHashMap<>();		// Keys are interned
	private Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull WeakReference<@NonNull DomainReflectivePackage>> asPackageMap = null;
	private /*@LazyNonNull*/ Map<@NonNull EcoreExecutorPackage, @NonNull List<@NonNull EcoreExecutorPackage>> extensions = null;
	private /*@LazyNonNull*/ org.eclipse.ocl.pivot.Class classType = null;
	private /*@LazyNonNull*/ org.eclipse.ocl.pivot.Class enumerationType = null;

	public ExecutorStandardLibrary(EcoreExecutorPackage... execPackages) {
	//	OCLstdlibTables.PACKAGE.getClass();
		for (EcoreExecutorPackage execPackage : execPackages) {
			assert execPackage != null;
			addPackage(execPackage, null);
		}
	}

	public void addExtension(@NonNull EcoreExecutorPackage basePackage, @NonNull EcoreExecutorPackage extensionPackage) {
		Map<@NonNull EcoreExecutorPackage, @NonNull List<@NonNull EcoreExecutorPackage>> extensions2 = extensions;
		if (extensions2 == null) {
			extensions = extensions2 = new HashMap<>();
		}
		List<@NonNull EcoreExecutorPackage> list = extensions2.get(basePackage);
		if (list == null) {
			list = new ArrayList<>();
			extensions2.put(basePackage, list);
		}
		list.add(extensionPackage);
	}

	public synchronized void addPackage(@NonNull EcoreExecutorPackage execPackage, @Nullable EcoreExecutorPackage extendedPackage) {
		String uri = execPackage.getURI();
		assert uri != null;
		String internedURI = uri.intern();
		@SuppressWarnings("unused")
		WeakReference<@NonNull EcoreExecutorPackage> oldExecPackage = ePackageMap.put(internedURI, new WeakReference<>(execPackage));
		//		if ((oldExecPackage != null) && (oldExecPackage != execPackage)) {
		//			Iterable<ExecutorType> newTypes = execPackage.getOwnedType();
		//			for (DomainType oldType : oldExecPackage.getOwnedType()) {
		//				-- check for type compatibility
		//			}
		//		}
	}

	public @NonNull EcoreExecutorAnyType createEcoreExecutorAnyType(@NonNull BuiltInTypeId typeId, @NonNull ExecutorPackage evaluationPackage, int flags) {
		EcoreExecutorAnyType type = new EcoreExecutorAnyType(typeId, evaluationPackage, flags);
		EcoreFlatModel flatModel = getFlatModel();
		FlatClass flatClass = flatModel.getTypeIdFlatClass(type);
		type.setFlatClass(flatClass);
		return type;
	}

	public @NonNull EcoreExecutorBagType createEcoreExecutorBagType(@NonNull CollectionTypeId typeId,
			@NonNull ExecutorPackage evaluationPackage, int flags, @NonNull ExecutorTypeParameter typeParameter) {
		EcoreExecutorBagType type = new EcoreExecutorBagType(typeId, evaluationPackage, flags, typeParameter);
		EcoreFlatModel flatModel = getFlatModel();
		FlatClass flatClass = flatModel.getTypeIdFlatClass(type);
		type.setFlatClass(flatClass);
		return type;
	}

	public @NonNull EcoreExecutorBooleanType createEcoreExecutorBooleanType(@NonNull BuiltInTypeId typeId, @NonNull ExecutorPackage evaluationPackage, int flags) {
		EcoreExecutorBooleanType type = new EcoreExecutorBooleanType(typeId, evaluationPackage, flags);
		EcoreFlatModel flatModel = getFlatModel();
		FlatClass flatClass = flatModel.getTypeIdFlatClass(type);
		type.setFlatClass(flatClass);
		return type;
	}

	public @NonNull EcoreExecutorCollectionType createEcoreExecutorCollectionType(@NonNull CollectionTypeId typeId,
			@NonNull ExecutorPackage evaluationPackage, int flags, @NonNull ExecutorTypeParameter typeParameter) {
		EcoreExecutorCollectionType type = new EcoreExecutorCollectionType(typeId, evaluationPackage, flags, typeParameter);
		EcoreFlatModel flatModel = getFlatModel();
		FlatClass flatClass = flatModel.getTypeIdFlatClass(type);
		type.setFlatClass(flatClass);
		return type;
	}

	public @NonNull EcoreExecutorEnumeration createEcoreExecutorEnumeration(/*@NonNull*/ EEnum eEnum, @NonNull EcoreExecutorPackage evaluationPackage, int flags) {
		EcoreExecutorEnumeration type = new EcoreExecutorEnumeration(eEnum, evaluationPackage, flags);
		EcoreFlatModel flatModel = getFlatModel();
		EcoreFlatClass flatClass = flatModel.getEcoreFlatClass(type);
		type.setFlatClass(flatClass);
		return type;
	}

	public @NonNull EcoreExecutorInvalidType createEcoreExecutorInvalidType(@NonNull BuiltInTypeId typeId, @NonNull ExecutorPackage evaluationPackage, int flags) {
		EcoreExecutorInvalidType type = new EcoreExecutorInvalidType(typeId, evaluationPackage, flags);
		EcoreFlatModel flatModel = getFlatModel();
		FlatClass flatClass = flatModel.getTypeIdFlatClass(type);
		type.setFlatClass(flatClass);
		return type;
	}

	public @NonNull EcoreExecutorOrderedSetType createEcoreExecutorOrderedSetType(@NonNull CollectionTypeId typeId,
			@NonNull ExecutorPackage evaluationPackage, int flags, @NonNull ExecutorTypeParameter typeParameter) {
		EcoreExecutorOrderedSetType type = new EcoreExecutorOrderedSetType(typeId, evaluationPackage, flags, typeParameter);
		EcoreFlatModel flatModel = getFlatModel();
		FlatClass flatClass = flatModel.getTypeIdFlatClass(type);
		type.setFlatClass(flatClass);
		return type;
	}

	public @NonNull EcoreExecutorPrimitiveType createEcoreExecutorPrimitiveType(@NonNull BuiltInTypeId typeId, @NonNull ExecutorPackage evaluationPackage, int flags) {
		EcoreExecutorPrimitiveType type = new EcoreExecutorPrimitiveType(typeId, evaluationPackage, flags);
		EcoreFlatModel flatModel = getFlatModel();
		FlatClass flatClass = flatModel.getTypeIdFlatClass(type);
		type.setFlatClass(flatClass);
		return type;
	}

	public @NonNull EcoreExecutorSequenceType createEcoreExecutorSequenceType(@NonNull CollectionTypeId typeId,
			@NonNull ExecutorPackage evaluationPackage, int flags, @NonNull ExecutorTypeParameter typeParameter) {
		EcoreExecutorSequenceType type = new EcoreExecutorSequenceType(typeId, evaluationPackage, flags, typeParameter);
		EcoreFlatModel flatModel = getFlatModel();
		FlatClass flatClass = flatModel.getTypeIdFlatClass(type);
		type.setFlatClass(flatClass);
		return type;
	}

	public @NonNull EcoreExecutorSetType createEcoreExecutorSetType(@NonNull CollectionTypeId typeId,
			@NonNull ExecutorPackage evaluationPackage, int flags, @NonNull ExecutorTypeParameter typeParameter) {
		EcoreExecutorSetType type = new EcoreExecutorSetType(typeId, evaluationPackage, flags, typeParameter);
		EcoreFlatModel flatModel = getFlatModel();
		TypeIdFlatClass flatClass = flatModel.getTypeIdFlatClass(type);
		type.setFlatClass(flatClass);
		return type;
	}

	public @NonNull EcoreExecutorType createEcoreExecutorType(/*@NonNull*/ EClassifier eClassifier,
			@NonNull EcoreExecutorPackage evaluationPackage, int flags, @NonNull ExecutorTypeParameter @NonNull ... typeParameters) {
		assert eClassifier != null;
		EcoreExecutorType type = new EcoreExecutorType(eClassifier, evaluationPackage, flags, typeParameters);
		EcoreFlatModel flatModel = getFlatModel();
		EcoreFlatClass flatClass = flatModel.getEcoreFlatClass(type);
		type.setFlatClass(flatClass);
		return type;
	}

	public @NonNull EcoreExecutorType createEcoreExecutorType(@NonNull String name,
			@NonNull EcoreExecutorPackage evaluationPackage, int flags, @NonNull ExecutorTypeParameter @NonNull ... typeParameters) {
		EcoreExecutorType type = new EcoreExecutorType(name, evaluationPackage, flags, typeParameters);
		EcoreFlatModel flatModel = getFlatModel();
		NamedFlatClass flatClass = flatModel.getNamedFlatClass(type);
		type.setFlatClass(flatClass);
		return type;
	}

	public @NonNull EcoreExecutorVoidType createEcoreExecutorVoidType(@NonNull BuiltInTypeId typeId, @NonNull ExecutorPackage evaluationPackage, int flags) {
		EcoreExecutorVoidType type = new EcoreExecutorVoidType(typeId, evaluationPackage, flags);
		EcoreFlatModel flatModel = getFlatModel();
		FlatClass flatClass = flatModel.getTypeIdFlatClass(type);
		type.setFlatClass(flatClass);
		return type;
	}

	public @NonNull ExecutorTypeParameter createExecutorTypeParameter(int index, @NonNull String name) {
		ExecutorTypeParameter executorTypeParameter = new ExecutorTypeParameter(index, name);
		EcoreFlatModel flatModel = getFlatModel();
		FlatClass flatClass = flatModel.getFlatClass(executorTypeParameter);
		executorTypeParameter.setFlatClass(flatClass);
		return executorTypeParameter;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getClassType() {
		Map<@NonNull EcoreExecutorPackage, @NonNull List<@NonNull EcoreExecutorPackage>> extensions2 = extensions;
		if (extensions2 == null) {
			throw new IllegalStateException("No extension package registered to define Class type"); //$NON-NLS-1$
		}
		if (classType != null) {
			return classType;
		}
		classType = getPivotType(TypeId.CLASS_NAME);
		if (classType != null) {
			return classType;
		}
		throw new IllegalStateException("No extension package defines Class type"); //$NON-NLS-1$
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getEnumerationType() {
		Map<@NonNull EcoreExecutorPackage, @NonNull List<@NonNull EcoreExecutorPackage>> extensions2 = extensions;
		if (extensions2 == null) {
			throw new IllegalStateException("No extension package registered to define Enumeration type"); //$NON-NLS-1$
		}
		if (enumerationType != null) {
			return enumerationType;
		}
		enumerationType = getPivotType(TypeId.ENUMERATION_NAME);
		if (enumerationType != null) {
			return enumerationType;
		}
		throw new IllegalStateException("No extension package defines Enumeration type"); //$NON-NLS-1$
	}

//	@Override
//	public @NonNull FlatClass getFlatClass(org.eclipse.ocl.pivot.@NonNull Class type) {
//		return getInheritance(type).getFlatClass();
//	}

	@Override
	public @NonNull CompleteInheritance getInheritance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		if (asClass instanceof CompleteInheritance) {
			return (CompleteInheritance) asClass;
		}
		/*		if (type instanceof DomainMetaclass) {
			DomainType instanceType = ClassUtil.nonNullPivot(((DomainMetaclass)type).getInstanceType());
			org.eclipse.ocl.pivot.Class metaclass = getMetaclass(instanceType);
			DomainType containerType = metaclass;//.getContainerType();
			return containerType.getInheritance(this);
		} */
		if (asClass instanceof CollectionType) {
			Type containerType = ((CollectionType)asClass).getContainerType();
			if (containerType != asClass) {
				return containerType.getInheritance(this);
			}
		}
		if (asClass instanceof MapType) {
			Type containerType = ((MapType)asClass).getContainerType();
			if (containerType != asClass) {
				return containerType.getInheritance(this);
			}
		}
		org.eclipse.ocl.pivot.Package asPackage = asClass.getOwningPackage();
		Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull WeakReference<@NonNull DomainReflectivePackage>> asPackageMap2;
		synchronized (this) {
			String nsURI = asPackage.getURI();
			EcoreExecutorPackage ecoreExecutorPackage = nsURI != null ? weakGet(ePackageMap, nsURI.intern()) : null;
			if (ecoreExecutorPackage != null) {
				String name = asClass.getName();
				CompleteInheritance executorType = ecoreExecutorPackage.getOwnedClass(name);
				if (executorType != null) {
					return executorType;
				}
				Map<@NonNull EcoreExecutorPackage, @NonNull List<@NonNull EcoreExecutorPackage>> extensions2 = extensions;
				if (extensions2 != null) {
					List<@NonNull EcoreExecutorPackage> packages = extensions2.get(ecoreExecutorPackage);
					if (packages != null) {
						for (@NonNull EcoreExecutorPackage extensionPackage : packages) {
							executorType = extensionPackage.getOwnedClass(name);
							if (executorType != null) {
								break;
							}
						}
					}
				}
				if (executorType != null) {
					return executorType;
				}
			}
			asPackageMap2 = asPackageMap;
			if (asPackageMap2 == null) {
				asPackageMap2 = asPackageMap = new WeakHashMap<>();
			}
		}
		synchronized (asPackageMap2) {
			DomainReflectivePackage executorPackage = weakGet(asPackageMap2, asPackage);
			if (executorPackage == null) {
				executorPackage = new DomainReflectivePackage(this, asPackage);
				asPackageMap2.put(asPackage, new WeakReference<>(executorPackage));
			}
			return executorPackage.getInheritance(asClass);
		}
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Class getNestedType(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name) {
		org.eclipse.ocl.pivot.Class nestedType = NameUtil.getNameable(parentPackage.getOwnedClasses(), name);
		if (nestedType != null) {
			return nestedType;
		}
		nestedType = getPivotType(name);
		return nestedType;
	}

	@Override
	public org.eclipse.ocl.pivot.Package getNsURIPackage(@NonNull String nsURI) {
		WeakReference<EcoreExecutorPackage> weakReference = ePackageMap.get(nsURI.intern());
		if (weakReference == null) {
			return null;
		}
		return weakReference.get();
	}

	/**
	 * @since 1.14
	 */
	@Override
	public @NonNull Set<@NonNull String> getNsURIs() {
		return ePackageMap.keySet();
	}

	@Override
	public @NonNull Operation getOclInvalidOperation() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @since 1.4
	 */
	@Override
	public @NonNull Property getOclInvalidProperty() {
		throw new UnsupportedOperationException();
	}

	public synchronized @Nullable EcoreExecutorPackage getPackage(@NonNull EPackage ePackage) {
		String nsURI = ePackage.getNsURI();
		return nsURI != null ? weakGet(ePackageMap, nsURI.intern()) : null;
	}

	@Override
	public synchronized org.eclipse.ocl.pivot.Class getOclType(@NonNull String typeName) {
		for (WeakReference<EcoreExecutorPackage> dPackage : ePackageMap.values()) {
			// FIXME			if (OCLstdlibTables.PACKAGE.getNsURI().equals(dPackage.getNsURI())) {
			if (dPackage != null) {
				EcoreExecutorPackage packageRef = dPackage.get();
				if (packageRef != null) {
					org.eclipse.ocl.pivot.Class type = packageRef.getOwnedClass(typeName);
					if (type != null) {
						return type;
					}
				}
			}
		}
		return null;
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Class getPivotType(@NonNull String className) {
		Map<EcoreExecutorPackage, List<EcoreExecutorPackage>> extensions2 = extensions;
		if (extensions2 != null) {
			for (@SuppressWarnings("null")@NonNull List<EcoreExecutorPackage> packages : extensions2.values()) {
				for (@SuppressWarnings("null")@NonNull EcoreExecutorPackage extensionPackage : packages) {
					org.eclipse.ocl.pivot.Class executorType = extensionPackage.getOwnedClass(className);
					if (executorType != null) {
						return executorType;
					}
				}
			}
		}
		return null;
	}
}
