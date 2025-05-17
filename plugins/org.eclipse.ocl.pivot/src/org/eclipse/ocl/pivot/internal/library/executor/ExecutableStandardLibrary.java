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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.BooleanType;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteEnvironment;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.executor.ExecutorBagType;
import org.eclipse.ocl.pivot.internal.executor.ExecutorCollectionType;
import org.eclipse.ocl.pivot.internal.executor.ExecutorMapType;
import org.eclipse.ocl.pivot.internal.executor.ExecutorOrderedSetType;
import org.eclipse.ocl.pivot.internal.executor.ExecutorSequenceType;
import org.eclipse.ocl.pivot.internal.executor.ExecutorSetType;
import org.eclipse.ocl.pivot.internal.executor.ExecutorTupleType;
import org.eclipse.ocl.pivot.internal.manager.AbstractCollectionTypeManager;
import org.eclipse.ocl.pivot.internal.manager.AbstractMapTypeManager;
import org.eclipse.ocl.pivot.internal.manager.CollectionTypeManager;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.options.PivotValidationOptions;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionTypeArguments;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.MapTypeArguments;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

public abstract class ExecutableStandardLibrary /*extends AbstractExecutorElement*/ implements CompleteEnvironment, StandardLibrary
{
	/**
	 * @since 7.0
	 */
	public static class ExecutorCollectionTypeManager extends AbstractCollectionTypeManager
	{
		public ExecutorCollectionTypeManager(@NonNull ExecutableStandardLibrary standardLibrary) {
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
		public ExecutorMapTypeManager(@NonNull ExecutableStandardLibrary standardLibrary) {
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
	 * Shared cache of the lazily created lazily deleted specializations of each collection type.
	 */
//	private @NonNull Map<@NonNull Type, @NonNull Map<@NonNull CollectionTypeArguments, @NonNull WeakReference<@Nullable ExecutorCollectionType>>> collectionSpecializations = new /*Weak*/HashMap<>();	// Keys are not singletons
	private /*@LazyNonNull*/ ExecutorCollectionTypeManager collectionTypeManager = null;

	/**
	 * Shared cache of the lazily created lazily deleted specializations of each map type.
	 */
//	private @NonNull Map<@NonNull Type, @NonNull Map<@NonNull MapTypeParameters, @NonNull WeakReference<@Nullable ExecutorMapType>>> mapSpecializations = new /*Weak*/HashMap<>();		// Keys are not singletons
	private /*@LazyNonNull*/ ExecutorMapTypeManager mapTypeManager = null;

	/**
	 * Shared cache of the lazily created lazily deleted tuples.
	 */
	private @NonNull Map<@NonNull TupleTypeId, @NonNull WeakReference<@NonNull TupleType>> tupleTypeMap = new WeakHashMap<>();		// Keys are singletons

	/**
	 * Configuration of validation preferences.
	 *
	 * The key used to be a magic String publicly exports from XXXTables polluting the API.
	 *
	 * Now it is the EOperation literal of the validation method.
	 */
	private /*LazyNonNull*/ Map<@Nullable Object, StatusCodes.@Nullable Severity> validationKey2severity = null;

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
	public @NonNull CollectionType getBagType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getBagType(), elementType, isNullFree, lower, upper);
	}

	@Override
	public @NonNull BooleanType getBooleanType() {
		return OCLstdlibTables.Types._Boolean;
	}

	@Override
	public @NonNull CollectionType getCollectionType() {
		return OCLstdlibTables.Types._Collection;
	}

	@Override
	public @NonNull CollectionType getCollectionType(@NonNull CollectionType genericType, @NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		IntegerValue lower2 = lower;
		UnlimitedNaturalValue upper2 = upper;
		if (lower2 == null) {
			lower2 = ValueUtil.ZERO_VALUE;
		}
		if (upper2 == null) {
			upper2 = ValueUtil.UNLIMITED_VALUE;
		}
		CollectionTypeArguments typeArguments = new CollectionTypeArguments(genericType.getTypeId(), elementType, isNullFree, lower2, upper2);
		return getCollectionTypeManager().getCollectionType(typeArguments);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull CollectionType getCollectionType(@NonNull CollectionTypeArguments typeArguments) {
		return getCollectionTypeManager().getCollectionType(typeArguments);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull CollectionType getCollectionType(@NonNull CollectionTypeId collectionTypeId) {
		return getCollectionTypeManager().getCollectionType(collectionTypeId);
	}

	@Override
	public @NonNull CollectionTypeManager getCollectionTypeManager() {
		ExecutorCollectionTypeManager collectionTypeManager2 = collectionTypeManager;
		if (collectionTypeManager2 == null) {
			collectionTypeManager = collectionTypeManager2 = new ExecutorCollectionTypeManager(this);
		}
		return collectionTypeManager2;
	}

	@Override
	public @NonNull PrimitiveType getIntegerType() {
		return OCLstdlibTables.Types._Integer;
	}

	@Override
	public @NonNull LambdaType getLambdaType(@NonNull String typeName, @NonNull Type contextType,
			@NonNull List<@NonNull ? extends Type> parameterTypes, @NonNull Type resultType, @Nullable TemplateParameterSubstitutions bindings) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @since 7.0
	 */
	@Override
	public synchronized @NonNull MapType getMapEntryType(org.eclipse.ocl.pivot.@NonNull Class entryClass) {
		return getMapTypeManager().getMapEntryType(entryClass);
	}

	@Override
	public @NonNull MapType getMapType() {
		return OCLstdlibTables.Types._Map;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public synchronized @NonNull MapType getMapType(@NonNull Type keyType, boolean keyValuesAreNullFree, @NonNull Type valueType, boolean valuesAreNullFree) {
		MapTypeArguments typeArguments = new MapTypeArguments(keyType, keyValuesAreNullFree, valueType, valuesAreNullFree);
		return getMapTypeManager().getMapType(typeArguments);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull ExecutorMapTypeManager getMapTypeManager() {
		ExecutorMapTypeManager mapTypeManager2 = mapTypeManager;
		if (mapTypeManager2 == null) {
			mapTypeManager = mapTypeManager2 = new ExecutorMapTypeManager(this);
		}
		return mapTypeManager2;
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Package getNestedPackage(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name) {
		return NameUtil.getNameable(parentPackage.getOwnedPackages(), name);
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Class getNestedType(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name) {
		return NameUtil.getNameable(parentPackage.getOwnedClasses(), name);
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
	public @Nullable Element getOperationTemplateParameter(@NonNull Operation anOperation, int index) {
		return anOperation.getTypeParameters().get(index);
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
	public @NonNull CollectionType getOrderedSetType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getOrderedSetType(), elementType, isNullFree, lower, upper);
	}

	@Override
	public @NonNull CompleteModel getOwnedCompleteModel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull StandardLibrary getOwnedStandardLibrary() {
		return this;
	}

	@Override
	public CompleteEnvironment getOwningCompleteEnvironment() {
		throw new UnsupportedOperationException();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Package getPackage() {
		return OCLstdlibTables.PACKAGE;
	}

	public org.eclipse.ocl.pivot.@Nullable Class getPivotType(@NonNull String className) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @Nullable Type getPrimitiveType(@NonNull PrimitiveTypeId typeId) {
		return TypeUtil.getPrimitiveType(this, typeId);
	}

	@Override
	public @NonNull PrimitiveType getRealType() {
		return OCLstdlibTables.Types._Real;
	}

	@Override
	public org.eclipse.ocl.pivot.Package getRootPackage(@NonNull String name) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull SequenceType getSequenceType() {
		return OCLstdlibTables.Types._Sequence;
	}

	@Override
	public @NonNull CollectionType getSequenceType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getSequenceType(), elementType, isNullFree, lower, upper);
	}

	@Override
	public @NonNull SetType getSetType() {
		return OCLstdlibTables.Types._Set;
	}

	@Override
	public @NonNull CollectionType getSetType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getSetType(), elementType, isNullFree, lower, upper);
	}

	//	@Override
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

	public @NonNull Element getTemplateParameter(@NonNull TemplateParameterId id, Element context) {
		throw new UnsupportedOperationException();
	}

	public synchronized @NonNull TupleType getTupleType(@NonNull TupleTypeId typeId) {
		WeakReference<@NonNull TupleType> ref = tupleTypeMap.get(typeId);
		if (ref != null) {
			TupleType domainTupleType = ref.get();
			if (domainTupleType != null) {
				return domainTupleType;
			}
		}
		TupleType domainTupleType = new ExecutorTupleType(typeId);
		tupleTypeMap.put(typeId, new WeakReference<>(domainTupleType));
		return domainTupleType;
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull String typeName, @NonNull Collection<@NonNull ? extends TypedElement> parts,
			@Nullable TemplateParameterSubstitutions bindings) {
		throw new UnsupportedOperationException();
	}

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

	@Override
	public void setOwnedCompleteModel(CompleteModel value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setOwnedStandardLibrary(StandardLibrary value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setOwningCompleteEnvironment(CompleteEnvironment value) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Return the map.get(key).get() entry if there is one or null if not, removing any stale
	 * entry that may be encountered.
	 */
	protected <K, V> @Nullable V weakGet(@NonNull Map<K, @NonNull WeakReference<V>> map, @NonNull K key) {
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
