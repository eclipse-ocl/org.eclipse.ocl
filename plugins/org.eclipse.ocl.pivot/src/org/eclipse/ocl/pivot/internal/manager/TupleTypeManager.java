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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Orphanage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.TupleTypeImpl;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * TupleTypeManager manages the TupleTypes created within an Orphanage.
 */
public class TupleTypeManager extends AbstractTypeManager
{
	/**
	 * Map from the tuple typeId to the tuple type.
	 */
	private @NonNull Map<@NonNull TupleTypeId, @NonNull Object> tupleId2tupleOrWeakTuple;

	public TupleTypeManager(@NonNull Orphanage orphanage, @NonNull StandardLibrary standardLibrary) {
		super(orphanage, standardLibrary);
		this.tupleId2tupleOrWeakTuple = new HashMap<>();
	}

	private @Nullable TupleType basicGetTupleType(@NonNull TupleTypeId tupleTypeId) {
		return (TupleType)tupleId2tupleOrWeakTuple.get(tupleTypeId);
	}

	@Override
	public void dispose() {
		tupleId2tupleOrWeakTuple.clear();
	}

	protected @NonNull Type getPrimaryType(@NonNull Type asType) {
		return asType;
	}

	public @NonNull TupleType getTupleType(@NonNull IdResolver idResolver, @NonNull TupleTypeId tupleTypeId) {
		TupleType tupleType = basicGetTupleType(tupleTypeId);
		if (tupleType == null) {
			synchronized (tupleId2tupleOrWeakTuple) {
				tupleType = basicGetTupleType(tupleTypeId);
				if (tupleType == null) {
					tupleType = new TupleTypeImpl(tupleTypeId);
					@NonNull TuplePartId[] partIds = tupleTypeId.getPartIds();
					List<Property> ownedAttributes = tupleType.getOwnedProperties();
					for (@NonNull TuplePartId partId : partIds) {
						Type partType = idResolver.getType(partId.getTypeId());
						Type partType2 = getPrimaryType(partType);
						Property property = PivotUtil.createProperty(NameUtil.getSafeName(partId), partType2);
						ownedAttributes.add(property);
					}
					tupleType.getSuperClasses().add(standardLibrary.getOclTupleType());
					putTupleType(tupleTypeId, tupleType);
					getOrphanage().addOrphanClass(tupleType);
				}
			}
		}
		return tupleType;
	}

	public @NonNull TupleType getTupleType(@NonNull Iterable<@NonNull ? extends TypedElement> parts) {
		@NonNull TupleTypeId tupleTypeId = IdManager.getOrderedTupleTypeId(TypeId.TUPLE_NAME, parts);
		TupleType tupleType = basicGetTupleType(tupleTypeId);
		if (tupleType == null) {
			synchronized (tupleId2tupleOrWeakTuple) {
				tupleType = basicGetTupleType(tupleTypeId);
				if (tupleType == null) {
					tupleType = new TupleTypeImpl(tupleTypeId);
					EList<@NonNull Property> ownedAttributes = (EList<@NonNull Property>)tupleType.getOwnedProperties();
					for (@NonNull TypedElement part : parts) {
						String partName = PivotUtil.getName(part);
						Type partType = PivotUtil.getType(part);
						Property property = PivotUtil.createProperty(partName, partType);
						ownedAttributes.add(property);
					}
					ECollections.sort(ownedAttributes, NameUtil.NAMEABLE_COMPARATOR);
					tupleType.getSuperClasses().add(standardLibrary.getOclTupleType());
					getOrphanage().addOrphanClass(tupleType);
					putTupleType(tupleTypeId, tupleType);
				}
			}
		}
		return tupleType;
	}

	private void putTupleType(@NonNull TupleTypeId tupleTypeId, @NonNull TupleType tupleType) {
		tupleId2tupleOrWeakTuple.put(tupleTypeId, tupleType);
	}
}