/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.PartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.TypedElementImpl;
import org.eclipse.ocl.pivot.manager.TupleTypeManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

/**
 * TupleypeManagerInternal encapsulates the knowledge about known tuple types.
 *
 * @since 7.0
 */
public abstract class AbstractTupleTypeManager implements TupleTypeManager
{
	/**
	 * TuplePart provides a convenient descriptor for a tuple part complying with the full EMF model protocols.
	 */
	public static class TuplePart extends TypedElementImpl			// XXX Why not Property else a first class TuplePart
	{
		public TuplePart(@NonNull String name, @NonNull Type type, boolean isRequired) {
			setName(name);
			setType(type);
			setIsRequired(isRequired);
		}
	}

	protected final @NonNull StandardLibrary standardLibrary;

	/**
	 * Map from the tuple typeId to the tuple type.
	 */
	protected final @NonNull Map<@NonNull TupleTypeId, @NonNull TupleType> tupleid2tuple = new HashMap<>();

	protected AbstractTupleTypeManager(@NonNull StandardLibrary standardLibrary) {
		this.standardLibrary = standardLibrary;
	}

	@Override
	public boolean conformsToTupleType(@NonNull TupleType actualType, @Nullable TemplateParameterSubstitutions actualSubstitutions,
			@NonNull TupleType requiredType, @Nullable TemplateParameterSubstitutions requiredSubstitutions, boolean enforceNullity) {
		List<Property> actualProperties = actualType.getOwnedProperties();
		List<Property> requiredProperties = requiredType.getOwnedProperties();
		if (actualProperties.size() != requiredProperties.size()) {
			return false;
		}
		for (Property actualProperty : actualProperties) {
			Property requiredProperty = NameUtil.getNameable(requiredProperties, actualProperty.getName());
			if (requiredProperty == null) {
				return false;
			}
			Type actualPropertyType = PivotUtil.getType(actualProperty);
			Type requiredPropertyType = PivotUtil.getType(requiredProperty);
			if (enforceNullity) {
				boolean actualIsRequired = actualProperty.isIsRequired();
				boolean requiredIsRequired = requiredProperty.isIsRequired();
				if (!standardLibrary.conformsTo(actualPropertyType, actualIsRequired, actualSubstitutions, requiredPropertyType, requiredIsRequired, requiredSubstitutions)) {
					return false;
				}
			}
			else {
				if (!standardLibrary.conformsTo(actualPropertyType, actualSubstitutions, requiredPropertyType, requiredSubstitutions, false)) {
					return false;
				}
			}
		}
		return true;
	}

	protected abstract @NonNull TupleType createTupleType(@NonNull TupleTypeId typeId);

	@Override
	public void dispose() {
		tupleid2tuple.clear();
	}

	@Override
	public @Nullable TupleType getCommonTupleType(@NonNull TupleType leftType, @Nullable TemplateParameterSubstitutions leftSubstitutions,
			@NonNull TupleType rightType, @Nullable TemplateParameterSubstitutions rightSubstitutions) {
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
			Type leftPropertyType = leftProperty.getType();
			if (leftPropertyType == null) {
				return null;				// Never happens
			}
			Type rightPropertyType = rightProperty.getType();
			if (rightPropertyType == null) {
				return null;				// Never happens
			}
			Type commonType = standardLibrary.getCommonType(leftPropertyType, leftSubstitutions, rightPropertyType, rightSubstitutions);
			boolean commonIsRequired = standardLibrary.getCommonIsRequired(leftProperty.isIsRequired(), rightProperty.isIsRequired());
			PartId commonPartId = IdManager.getPartId(i, name, commonType.getTypeId(), commonIsRequired);
			commonPartIds.add(commonPartId);
		}
		TupleTypeId commonTupleTypeId = IdManager.getTupleTypeId(commonPartIds);
		return getTupleType(commonTupleTypeId);
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull List<@NonNull PartId> partIds) {
		//
		//	Create the tuple type id (and then specialize it)
		//
		TupleTypeId tupleTypeId = IdManager.getOrderedTupleTypeId(partIds);
		//
		//	Finally create the (specialized) tuple type
		//
		return getTupleType(tupleTypeId);
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull TupleTypeId tupleTypeId) {
		TupleType tupleType = tupleid2tuple.get(tupleTypeId);
		if (tupleType == null) {
			synchronized (tupleid2tuple) {
				tupleType = tupleid2tuple.get(tupleTypeId);
				if (tupleType == null) {
					tupleType = createTupleType(tupleTypeId);
					tupleid2tuple.put(tupleTypeId, tupleType);
				}
			}
		}
		return tupleType;
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull Collection<@NonNull? extends TypedElement> parts, @Nullable TemplateParameterSubstitutions usageBindings) {
		List<@NonNull TypedElement> sortedParts = new ArrayList<>(parts);
		Collections.sort(sortedParts, NameUtil.NAMEABLE_COMPARATOR);
		@NonNull PartId @NonNull [] orderedPartIds = new @NonNull PartId [sortedParts.size()];
		int index = 0;
		for (@NonNull TypedElement part : sortedParts) {
			Type type1 = part.getType();
			if (type1 != null) {
				Type type2 = standardLibrary.getPrimaryType(type1);
				Type type3 = standardLibrary.getSpecializedType(type2, usageBindings);
				orderedPartIds[index] = IdManager.getPartId(index, PivotUtil.getName(part), type3.getTypeId(), part.isIsRequired());
			}
			index++;
		}
		//
		//	Create the tuple type id (and then specialize it)
		//
		TupleTypeId tupleTypeId = IdManager.getOrderedTupleTypeId(orderedPartIds);
		//
		//	Finally create the (specialized) tuple type
		//
		return getTupleType(tupleTypeId);
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull TupleType type, @Nullable TemplateParameterSubstitutions usageBindings) {	// FIXME Remove duplication, unify type/multiplicity
		//		return getTupleType(type.getName(), type.getOwnedAttribute(), usageBindings);
		TupleType specializedTupleType = type;
		Map<@NonNull String, @NonNull Type> resolutions =  null;
		List<@NonNull Property> parts = PivotUtil.getOwnedPropertiesList(specializedTupleType);
		for (@NonNull Property part : parts) {
			Type propertyType = PivotUtil.getTypeInternal(part);
			Type resolvedPropertyType = standardLibrary.getSpecializedType(propertyType, usageBindings);
			if (resolvedPropertyType != propertyType) {
				if (resolutions == null) {
					resolutions = new HashMap<>();
				}
				resolutions.put(NameUtil.getSafeName(part), resolvedPropertyType);
			}
		}
		if (resolutions != null) {
			List<@NonNull PartId> partIds = new ArrayList<>(parts.size());
			for (int i = 0; i < parts.size(); i++) {
				Property part = parts.get(i);
				String partName = NameUtil.getSafeName(part);
				Type resolvedPropertyType = resolutions.get(partName);
				TypeId partTypeId = resolvedPropertyType != null ? resolvedPropertyType.getTypeId() : part.getTypeId();
				PartId partId = IdManager.getPartId(i, partName, partTypeId, part.isIsRequired());
				partIds.add(partId);
			}
			TupleTypeId tupleTypeId = IdManager.getTupleTypeId(partIds);
			specializedTupleType = getTupleType(tupleTypeId);
			return specializedTupleType;
		}
		else {
			return getTupleType(PivotUtil.getOwnedPropertiesList(type), usageBindings);
		}
	}

	@Override
	public boolean isEqualToTupleType(@NonNull TupleType leftTupleType, @NonNull TupleType rightTupleType) {
		TypeId leftParts = leftTupleType.getTypeId();
		TypeId rightParts = rightTupleType.getTypeId();
		return leftParts == rightParts;
	}
}