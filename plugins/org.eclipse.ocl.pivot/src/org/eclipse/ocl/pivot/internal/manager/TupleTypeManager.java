/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.PartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.TupleTypeImpl;
import org.eclipse.ocl.pivot.internal.TypedElementImpl;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

/**
 * TupleTypeManager encapsulates the knowledge about known tuple types.
 */
public class TupleTypeManager
{
	/**
	 * TuplePart provides a convenient descriptor for a tuple part complying with the full EMF model protocols.
	 */
	public static class TuplePart extends TypedElementImpl
	{
		protected final @NonNull PartId partId;

		/**
		 * @since 7.0
		 */
		public TuplePart(@NonNull PartId partId) {
			this.partId = partId;
			setName(partId.getName());
		}

		@Override
		public @NonNull TypeId getTypeId() {
			return partId.getTypeId();
		}

		@Override
		public String toString() {
			return String.valueOf(name) + " : " + String.valueOf(type);
		}
	}

	/**
	 * @since 7.0
	 */
	protected final @NonNull EnvironmentFactoryInternal environmentFactory;
	protected final @NonNull MetamodelManager metamodelManager;
	/**
	 * @since 7.0
	 */
	protected final @NonNull StandardLibraryInternal standardLibrary;
	/**
	 * @since 7.0
	 */
	protected final @NonNull IdResolver idResolver;
	protected final org.eclipse.ocl.pivot.@NonNull Class oclTupleType;

	/**
	 * Map from the tuple typeId to the tuple type.
	 * @since 7.0
	 */
	private @Nullable Map<@NonNull TupleTypeId, @NonNull TupleType> tupleid2tuple = null;

	/**
	 * @since 7.0
	 */
	public TupleTypeManager(@NonNull EnvironmentFactoryInternal environmentFactory) {
		this.environmentFactory = environmentFactory;
		this.metamodelManager = environmentFactory.getMetamodelManager();
		this.standardLibrary = environmentFactory.getStandardLibrary();
		this.idResolver = environmentFactory.getIdResolver();
		this.oclTupleType = standardLibrary.getOclTupleType();
	}

	public void dispose() {
		tupleid2tuple = null;
	}

	public @Nullable Type getCommonType(@NonNull TupleType leftType, @NonNull TemplateParameterSubstitutions leftSubstitutions,
			@NonNull TupleType rightType, @NonNull TemplateParameterSubstitutions rightSubstitutions) {
		List<Property> leftProperties = leftType.getOwnedProperties();
		List<Property> rightProperties = rightType.getOwnedProperties();
		int iSize = leftProperties.size();
		if (iSize != rightProperties.size()) {
			return null;
		}
		List<@NonNull PartId> commonPartIds = new ArrayList<>(iSize);
		for (int i = 0; i < iSize; i++) {
			Property leftProperty = leftProperties.get(i);
			if (leftProperty == null) {
				return null;				// Never happens
			}
			String name = leftProperty.getName();
			if (name == null) {
				return null;				// Never happens
			}
			Property rightProperty = NameUtil.getNameable(rightProperties, name);
			if (rightProperty == null) {
				return null;				// Happens for inconsistent tuples
			}
		//	if (leftProperty.isIsRequired() != rightProperty.isIsRequired()) {
		//		return null;				// ?? Never happens
		//	}
			Type leftPropertyType = leftProperty.getType();
			if (leftPropertyType == null) {
				return null;				// Never happens
			}
			Type rightPropertyType = rightProperty.getType();
			if (rightPropertyType == null) {
				return null;				// Never happens
			}
			boolean commonIsRequired = leftProperty.isIsRequired() && rightProperty.isIsRequired();
			Type commonType = metamodelManager.getCommonType(leftPropertyType, leftSubstitutions, rightPropertyType, rightSubstitutions);
			PartId commonPartId = IdManager.getPartId(i, name, commonType.getTypeId(), commonIsRequired);
			commonPartIds.add(commonPartId);
		}
		TupleTypeId commonTupleTypeId = IdManager.getTupleTypeId(TypeId.TUPLE_NAME, commonPartIds);
		return getTupleType(idResolver, commonTupleTypeId);
	}

	public @NonNull TupleType getTupleType(@NonNull IdResolver idResolver, @NonNull TupleTypeId tupleTypeId) {
		Map<@NonNull TupleTypeId, @NonNull TupleType> tupleid2tuple2 = tupleid2tuple;
		if (tupleid2tuple2 == null) {
			synchronized (this) {
				tupleid2tuple2 = tupleid2tuple;
				if (tupleid2tuple2 == null) {
					tupleid2tuple2 = tupleid2tuple = new HashMap<>();
				}
			}
		}
		TupleType tupleType = tupleid2tuple2.get(tupleTypeId);
		if (tupleType == null) {
			synchronized (tupleid2tuple2) {
				tupleType = tupleid2tuple2.get(tupleTypeId);
				if (tupleType == null) {
					tupleType = new TupleTypeImpl(tupleTypeId);
					@NonNull PartId[] partIds = tupleTypeId.getPartIds();
					List<Property> ownedAttributes = tupleType.getOwnedProperties();
					for (@NonNull PartId partId : partIds) {
						Type partType = idResolver.getType(partId.getTypeId());
						Type partType2 = metamodelManager.getPrimaryType(partType);
						Property property = PivotFactory.eINSTANCE.createProperty();
						property.setName(NameUtil.getSafeName(partId));
						property.setIsRequired(partId.isRequired());
						ownedAttributes.add(property);
						property.setType(partType2);			// After container to satisfy Property.setType assertIsNormalizedType
					}
					tupleType.getSuperClasses().add(oclTupleType);
					tupleid2tuple2.put(tupleTypeId, tupleType);
					environmentFactory.addOrphanClass(tupleType);
				}
			}
		}
		return tupleType;
	}

	public @NonNull TupleType getTupleType(@NonNull String tupleName, @NonNull Collection<@NonNull? extends TypedElement> parts,
			@Nullable TemplateParameterSubstitutions usageBindings) {
		List<@NonNull TypedElement> sortedParts = new ArrayList<>(parts);
		Collections.sort(sortedParts, NameUtil.NAMEABLE_COMPARATOR);
		@NonNull PartId @NonNull [] orderedPartIds = new @NonNull PartId [sortedParts.size()];
		int index = 0;
		for (@NonNull TypedElement part : sortedParts) {
			Type type1 = part.getType();
			if (type1 != null) {
				Type type2 = metamodelManager.getPrimaryType(type1);
				Type type3 = standardLibrary.getSpecializedType(type2, usageBindings);
				orderedPartIds[index] = IdManager.getPartId(index, PivotUtil.getName(part), type3.getTypeId(), part.isIsRequired());
			}
			index++;
		}
		//
		//	Create the tuple type id (and then specialize it)
		//
		TupleTypeId tupleTypeId = IdManager.getOrderedTupleTypeId(tupleName, orderedPartIds);
		IdResolver pivotIdResolver = metamodelManager.getEnvironmentFactory().getIdResolver();
		//
		//	Finally create the (specialized) tuple type
		//
		TupleType tupleType = getTupleType(pivotIdResolver, tupleTypeId);
		return tupleType;
	}

	/**
	 * Return the named tuple typeId with the defined parts (which need not be alphabetically ordered).
	 * @since 7.0
	 */
	public @NonNull TupleType getTupleType(@NonNull String tupleName, @NonNull List<@NonNull PartId> partIds) {
		//
		//	Create the tuple type id (and then specialize it)
		//
		TupleTypeId tupleTypeId = IdManager.getOrderedTupleTypeId(tupleName, partIds);
		IdResolver pivotIdResolver = metamodelManager.getEnvironmentFactory().getIdResolver();
		//
		//	Finally create the (specialized) tuple type
		//
		TupleType tupleType = getTupleType(pivotIdResolver, tupleTypeId);
		return tupleType;
	}

	public @NonNull TupleType getTupleType(@NonNull TupleType type, @Nullable TemplateParameterSubstitutions usageBindings) {	// FIXME Remove duplication, unify type/multiplicity
		//		return getTupleType(type.getName(), type.getOwnedAttribute(), usageBindings);
		TupleType specializedTupleType = type;
		Map<String, Type> resolutions =  null;
		List<Property> parts = specializedTupleType.getOwnedProperties();
		for (Property part : parts) {
			if (part != null) {
				Type propertyType = PivotUtil.getTypeInternal(part);
				Type resolvedPropertyType = standardLibrary.getSpecializedType(propertyType, usageBindings);
				if (resolvedPropertyType != propertyType) {
					if (resolutions == null) {
						resolutions = new HashMap<>();
					}
					resolutions.put(NameUtil.getSafeName(part), resolvedPropertyType);
				}
			}
		}
		if (resolutions != null) {
			List<@NonNull PartId> partIds = new ArrayList<>(parts.size());
			for (int i = 0; i < parts.size(); i++) {
				@SuppressWarnings("null") @NonNull Property part = parts.get(i);
				String partName = NameUtil.getSafeName(part);
				Type resolvedPropertyType = resolutions.get(partName);
				TypeId partTypeId = resolvedPropertyType != null ? resolvedPropertyType.getTypeId() : part.getTypeId();
				PartId partId = IdManager.getPartId(i, partName, partTypeId, part.isIsRequired());
				partIds.add(partId);
			}
			TupleTypeId tupleTypeId = IdManager.getTupleTypeId(PivotUtil.getName(type), partIds);
			specializedTupleType = getTupleType(idResolver, tupleTypeId);
			return specializedTupleType;
		}
		else {
			return getTupleType(NameUtil.getSafeName(type), ClassUtil.nullFree(type.getOwnedProperties()), usageBindings);
		}
	}
}