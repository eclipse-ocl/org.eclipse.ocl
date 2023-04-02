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

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
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
 * AbstractTupleTypeManager abstract the legacy TupleTypeManager functionality for re-use by an Orphanage.
 */
public abstract class AbstractTupleTypeManager extends AbstractTypeManager
{
	/**
	 * Map from the tuple typeId to the tuple type.
	 */
	private @NonNull Map<@NonNull TupleTypeId, @NonNull Object> tupleId2tupleOrWeakTuple;

	protected AbstractTupleTypeManager(boolean useWeakReferences) {
		super(useWeakReferences);
		this.tupleId2tupleOrWeakTuple = useWeakReferences ? new WeakHashMap<>() : new HashMap<>();
	}

	private @Nullable TupleType basicGetTupleType(@NonNull TupleTypeId tupleTypeId) {
		if (useWeakReferences) {
			@SuppressWarnings("unchecked")
			WeakReference<@NonNull TupleType> ref = (WeakReference<@NonNull TupleType>)tupleId2tupleOrWeakTuple.get(tupleTypeId);
			return ref != null ?  ref.get() : null;
		}
		else {
			return (TupleType)tupleId2tupleOrWeakTuple.get(tupleTypeId);
		}
	}

	@Override
	public void dispose() {
		tupleId2tupleOrWeakTuple.clear();
	}

	protected abstract @NonNull Type getPartType(@NonNull Type partType);

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
						Type partType2 = getPartType(partType);
						Property property = PivotUtil.createProperty(NameUtil.getSafeName(partId), partType2);
						ownedAttributes.add(property);
					}
					StandardLibrary standardLibrary = getStandardLibrary();
					tupleType.getSuperClasses().add(standardLibrary.getOclTupleType());
					putTupleType(tupleTypeId, tupleType);
					standardLibrary.addOrphanClass(tupleType);
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
					StandardLibrary standardLibrary = getStandardLibrary();
					tupleType.getSuperClasses().add(standardLibrary.getOclTupleType());
					standardLibrary.addOrphanClass(tupleType);
					putTupleType(tupleTypeId, tupleType);
				}
			}
		}
		return tupleType;
	}

	private void putTupleType(@NonNull TupleTypeId tupleTypeId, @NonNull TupleType tupleType) {
		if (useWeakReferences) {
			tupleId2tupleOrWeakTuple.put(tupleTypeId, new WeakReference<@NonNull TupleType>(tupleType));
		}
		else {
			tupleId2tupleOrWeakTuple.put(tupleTypeId, tupleType);
		}
	}
}