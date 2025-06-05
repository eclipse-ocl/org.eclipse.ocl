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
package org.eclipse.ocl.pivot.internal.library.executor;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.BooleanType;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.StandardLibraryImpl;
import org.eclipse.ocl.pivot.internal.executor.ExecutorBagType;
import org.eclipse.ocl.pivot.internal.executor.ExecutorCollectionType;
import org.eclipse.ocl.pivot.internal.executor.ExecutorMapType;
import org.eclipse.ocl.pivot.internal.executor.ExecutorOrderedSetType;
import org.eclipse.ocl.pivot.internal.executor.ExecutorSequenceType;
import org.eclipse.ocl.pivot.internal.executor.ExecutorSetType;
import org.eclipse.ocl.pivot.internal.executor.ExecutorTupleType;
import org.eclipse.ocl.pivot.internal.library.ecore.EcoreExecutorPackage;
import org.eclipse.ocl.pivot.internal.manager.AbstractCollectionTypeManager;
import org.eclipse.ocl.pivot.internal.manager.AbstractMapTypeManager;
import org.eclipse.ocl.pivot.internal.manager.AbstractTupleTypeManager;
import org.eclipse.ocl.pivot.internal.manager.CollectionTypeManager;
import org.eclipse.ocl.pivot.internal.manager.MapTypeManager;
import org.eclipse.ocl.pivot.internal.manager.TupleTypeManager;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.options.PivotValidationOptions;
import org.eclipse.ocl.pivot.values.CollectionTypeArguments;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.MapTypeArguments;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

public class ExecutorStandardLibrary extends StandardLibraryImpl
{
	/**
	 * @since 7.0
	 */
	public static class ExecutorCollectionTypeManager extends AbstractCollectionTypeManager
	{
		public ExecutorCollectionTypeManager(@NonNull StandardLibrary standardLibrary) {
			super(standardLibrary);
		}

		@Override
		protected @NonNull CollectionType createCollectionType(@NonNull CollectionTypeArguments typeArguments) {
			CollectionTypeId collectionTypeId = typeArguments.getCollectionTypeId();
			Type elementType = typeArguments.getElementType();
			boolean isNullFree = typeArguments.isNullFree();
			IntegerValue lower = typeArguments.getLower();
			UnlimitedNaturalValue upper = typeArguments.getUpper();
			if (collectionTypeId == TypeId.BAG) {
				return new ExecutorBagType(TypeId.BAG_NAME, standardLibrary.getBagType(), elementType, isNullFree, lower, upper);
			}
			else if (collectionTypeId == TypeId.ORDERED_SET) {
				return new ExecutorOrderedSetType(TypeId.ORDERED_SET_NAME, standardLibrary.getOrderedSetType(), elementType, isNullFree, lower, upper);
			}
			else if (collectionTypeId == TypeId.SEQUENCE) {
				return new ExecutorSequenceType(TypeId.SEQUENCE_NAME, standardLibrary.getSequenceType(), elementType, isNullFree, lower, upper);
			}
			else if (collectionTypeId == TypeId.SET) {
				return new ExecutorSetType(TypeId.SET_NAME, standardLibrary.getSetType(), elementType, isNullFree, lower, upper);
			}
			else {
				return new ExecutorCollectionType(TypeId.COLLECTION_NAME, standardLibrary.getCollectionType(), elementType, isNullFree, lower, upper);
			}
		}

		@Override
		protected boolean isValid(@Nullable Type type) {
			return type != null;
		}
	}

	/**
	 * @since 7.0
	 */
	public static class ExecutorMapTypeManager extends AbstractMapTypeManager
	{
		public ExecutorMapTypeManager(@NonNull StandardLibrary standardLibrary) {
			super(standardLibrary.getMapType());
		}

		@Override
		protected @NonNull MapType createMapType(@NonNull MapTypeArguments typeArguments, org.eclipse.ocl.pivot.@Nullable Class entryClass) {
			ExecutorMapType executorMapType = new ExecutorMapType(TypeId.MAP_NAME, genericMapType, typeArguments.getKeyType(), typeArguments.isKeysAreNullFree(), typeArguments.getValueType(), typeArguments.isValuesAreNullFree());
			if (entryClass != null) {
				executorMapType.setEntryClass(entryClass);
			}
			return executorMapType;
		}

		@Override
		protected boolean isValid(@Nullable Type type) {
			return type != null;
		}
	}

	/**
	 * @since 7.0
	 */
	public static class ExecutorTupleTypeManager extends AbstractTupleTypeManager
	{
		public ExecutorTupleTypeManager(@NonNull StandardLibrary standardLibrary) {
			super(standardLibrary);
		}

		@Override
		protected @NonNull TupleType createTupleType(@NonNull TupleTypeId typeId) {
			return new ExecutorTupleType(typeId);
			// 		parts.add(new ExecutorTuplePart(partName, partType, isRequired));
		}
	}

	/**
	 * Configuration of validation preferences.
	 *
	 * The key used to be a magic String publicly exports from XXXTables polluting the API.
	 *
	 * Now it is the EOperation literal of the validation method.
	 */
	private /*LazyNonNull*/ Map<@Nullable Object, StatusCodes.@Nullable Severity> validationKey2severity = null;

	private @NonNull Map<@NonNull String, WeakReference<@NonNull EcoreExecutorPackage>> ePackageMap = new WeakHashMap<>();		// Keys are interned
	private Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull WeakReference<@NonNull ExecutorReflectivePackage>> asPackageMap = null;
	private /*@LazyNonNull*/ Map<@NonNull EcoreExecutorPackage, @NonNull List<@NonNull EcoreExecutorPackage>> extensions = null;
	private /*@LazyNonNull*/ org.eclipse.ocl.pivot.Class classType = null;
	private /*@LazyNonNull*/ org.eclipse.ocl.pivot.Class enumerationType = null;

	public ExecutorStandardLibrary(EcoreExecutorPackage... execPackages) {
		OCLstdlibTables.PACKAGE.getClass();
		for (EcoreExecutorPackage execPackage : execPackages) {
			assert execPackage != null;
			addPackage(execPackage, null);
		}
	}

//	@Override
//	public <R> R accept(@NonNull Visitor<R> visitor) {
//		throw new UnsupportedOperationException();
//	}

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

	@Override
	protected @Nullable Type basicGetBehavioralType(@NonNull Type secondType) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean conformsToType(@NonNull Type firstType, @NonNull TemplateParameterSubstitutions firstSubstitutions,
			@NonNull Type secondType, @NonNull TemplateParameterSubstitutions secondSubstitutions) {
		if (firstType != secondType) {
			throw new UnsupportedOperationException();
		}
		return true;
	}

	@Override
	protected @NonNull CollectionTypeManager createCollectionTypeManager() {
		return new ExecutorCollectionTypeManager(this);
	}

	@Override
	protected @NonNull MapTypeManager createMapTypeManager() {
		return new ExecutorMapTypeManager(this);
	}

	@Override
	protected @NonNull TupleTypeManager createTupleTypeManager() {
		return new ExecutorTupleTypeManager(this);
	}

	protected @NonNull HashMap<@Nullable Object, StatusCodes.@Nullable Severity> createValidationKey2severityMap() {
		return PivotValidationOptions.createValidationKey2severityMap();
	}

	@Override
	public @NonNull Iterable<@NonNull ? extends CompletePackage> getAllCompletePackages() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull BagType getBagType() {
		return OCLstdlibTables.Types._Bag;
	}

	@Override
	public @NonNull BooleanType getBooleanType() {
		return OCLstdlibTables.Types._Boolean;
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
		classType = basicGetLibraryClass(TypeId.CLASS_NAME);
		if (classType != null) {
			return classType;
		}
		throw new IllegalStateException("No extension package defines Class type"); //$NON-NLS-1$
	}

	@Override
	public @NonNull CollectionType getCollectionType() {
		return OCLstdlibTables.Types._Collection;
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
		enumerationType = basicGetLibraryClass(TypeId.ENUMERATION_NAME);
		if (enumerationType != null) {
			return enumerationType;
		}
		throw new IllegalStateException("No extension package defines Enumeration type"); //$NON-NLS-1$
	}

	private @NonNull IdResolver getIdResolver() {
		throw new UnsupportedOperationException();			// XXX
	//	return new EcoreIdResolver(zzannotatingComments, this);			// XXX
	}

	/**
	 * @since 7.0
	 */
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
		Map<org.eclipse.ocl.pivot.@NonNull Package, @NonNull WeakReference<@NonNull ExecutorReflectivePackage>> asPackageMap2;
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
			ExecutorReflectivePackage executorPackage = weakGet(asPackageMap2, asPackage);
			if (executorPackage == null) {
				executorPackage = new ExecutorReflectivePackage(this, asPackage);
				asPackageMap2.put(asPackage, new WeakReference<>(executorPackage));
			}
			return executorPackage.getInheritance(asClass);
		}
	}

	@Override
	public @NonNull PrimitiveType getIntegerType() {
		return OCLstdlibTables.Types._Integer;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public org.eclipse.ocl.pivot.@Nullable Class basicGetLibraryClass(@NonNull String className) {
		Map<@NonNull EcoreExecutorPackage, @NonNull List<@NonNull EcoreExecutorPackage>> extensions2 = extensions;
		if (extensions2 != null) {
			for (@NonNull List<@NonNull EcoreExecutorPackage> packages : extensions2.values()) {
				for (@NonNull EcoreExecutorPackage extensionPackage : packages) {
					org.eclipse.ocl.pivot.Class executorType = extensionPackage.getOwnedClass(className);
					if (executorType != null) {
						return executorType;
					}
				}
			}
		}
		return null;
	}

	@Override
	public @NonNull MapType getMapType() {
		return OCLstdlibTables.Types._Map;
	}

/*	@Override
	public org.eclipse.ocl.pivot.@Nullable Class getNestedType(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name) {
		org.eclipse.ocl.pivot.Class nestedType = NameUtil.getNameable(parentPackage.getOwnedClasses(), name);
		if (nestedType != null) {
			return nestedType;
		}
		nestedType = getPivotType(name);
		return nestedType;
	} */

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
	public @NonNull AnyType getOclAnyType() {
		return OCLstdlibTables.Types._OclAny;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclComparableType() {
		return OCLstdlibTables.Types._OclComparable;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclElementType() {
		return OCLstdlibTables.Types._OclElement;
	}

	/**
	 * @since 1.1
	 */
	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclEnumerationType() {
		return OCLstdlibTables.Types._OclEnumeration;
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

	@Override
	public @NonNull InvalidType getOclInvalidType() {
		return OCLstdlibTables.Types._OclInvalid;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclMessageType() {
		return OCLstdlibTables.Types._OclMessage;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclSelfType() {
		return OCLstdlibTables.Types._OclSelf;
	}

	/**
	 * @since 1.1
	 */
	@Override
	public @NonNull Class getOclStereotypeType() {
		return OCLstdlibTables.Types._OclStereotype;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclSummableType() {
		return OCLstdlibTables.Types._OclSummable;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclTupleType() {
		return OCLstdlibTables.Types._OclTuple;
	}

	@Override
	public @NonNull VoidType getOclVoidType() {
		return OCLstdlibTables.Types._OclVoid;
	}

	@Override
	public @NonNull CollectionType getOrderedCollectionType() {
		return OCLstdlibTables.Types._OrderedCollection;
	}

	@Override
	public @NonNull OrderedSetType getOrderedSetType() {
		return OCLstdlibTables.Types._OrderedSet;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Package getPackage() {
		return OCLstdlibTables.PACKAGE;
	}

	public synchronized @Nullable EcoreExecutorPackage getPackage(@NonNull EPackage ePackage) {
		String nsURI = ePackage.getNsURI();
		return nsURI != null ? weakGet(ePackageMap, nsURI.intern()) : null;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull Type getPrimaryType(@NonNull Type asType) {
		return asType;
	}

	@Override
	public @NonNull PrimitiveType getRealType() {
		return OCLstdlibTables.Types._Real;
	}

	@Override
	public @NonNull SequenceType getSequenceType() {
		return OCLstdlibTables.Types._Sequence;
	}

	@Override
	public @NonNull SetType getSetType() {
		return OCLstdlibTables.Types._Set;
	}

	public StatusCodes.@Nullable Severity getSeverity(@Nullable Object validationKey) {
		Map<@Nullable Object, StatusCodes.@Nullable Severity> validationKey2severity2 = validationKey2severity;
		if (validationKey2severity2 == null) {
			validationKey2severity = validationKey2severity2 = createValidationKey2severityMap();
		}
		return validationKey2severity2.get(validationKey);
	}

	@Override
	public @NonNull Type getSpecializedType(@NonNull Type type, @Nullable TemplateParameterSubstitutions substitutions) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull PrimitiveType getStringType() {
		return OCLstdlibTables.Types._String;
	}

	/*	public @NonNull DomainTupleType getTupleType(@NonNull List<? extends DomainTypedElement> parts) {
		StringBuilder s = new StringBuilder();
		for (DomainTypedElement part : parts) {
			s.append(part.getName());
			s.append("\n"); //$NON-NLS-1$
		}
		String key = s.toString();
		synchronized (this) {
			List<WeakReference<DomainTupleType>> tupleTypes = tupleTypeMap.get(key);
			if (tupleTypes != null) {
				for (int j = tupleTypes.size(); --j >= 0; ) {
					WeakReference<DomainTupleType> tupleTypeRef = tupleTypes.get(j);
					DomainTupleType tupleType = tupleTypeRef.get();
					if (tupleType == null) {
						tupleTypes.remove(j);		// Trim stale list entry.
					}
					else {
						int i = 0;
						for (; i < parts.size(); i++) {
							List<? extends DomainTypedElement> ownedAttributes = tupleType.getOwnedAttribute();
							if (ownedAttributes.get(i).getType() != parts.get(i).getType()) {
								break;
							}
						}
						if (i >= parts.size()) {
							return tupleType;
						}
					}
				}
			}
			else {
				tupleTypes = new ArrayList<>();
				tupleTypeMap.put(key, tupleTypes);
			}
			DomainTupleType tupleType = new AbstractTupleType(this, parts);
			tupleTypes.add(new WeakReference<>(tupleType));
			return tupleType;
		}
	} */

	@Override
	public @NonNull CollectionType getUniqueCollectionType() {
		return OCLstdlibTables.Types._UniqueCollection;
	}

	@Override
	public @NonNull PrimitiveType getUnlimitedNaturalType() {
		return OCLstdlibTables.Types._UnlimitedNatural;
	}

	public void resetSeverities() {
		validationKey2severity = null;
	}

	/**
	 * Return the map.get(key).get() entry if there is one or null if not, removing any stale
	 * entry that may be encountered.
	 */
	private <K, V> @Nullable V weakGet(@NonNull Map<K, @NonNull WeakReference<V>> map, @NonNull K key) {
		WeakReference<V> ref = map.get(key);
		if (ref == null) {
			return null;
		}
		@Nullable V value = ref.get();
		if (value == null) {
			map.remove(key);
		}
		return value;
	}
}
