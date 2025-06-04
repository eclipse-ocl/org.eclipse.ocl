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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.PartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.TupleTypeImpl;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;

/**
 * TupleTypeManager encapsulates the knowledge about known tuple types.
 * @since 7.0
 */
public class TupleTypeManagerInternal extends AbstractTupleTypeManager
{
	/**
	 * @since 7.0
	 */
	protected final @NonNull EnvironmentFactoryInternal environmentFactory;
	protected final @NonNull MetamodelManager metamodelManager;
	/**
	 * @since 7.0
	 */
	protected final @NonNull IdResolver idResolver;

	/**
	 * @since 7.0
	 */
	public TupleTypeManagerInternal(@NonNull EnvironmentFactoryInternal environmentFactory) {
		super(environmentFactory.getStandardLibrary());
		this.environmentFactory = environmentFactory;
		this.metamodelManager = environmentFactory.getMetamodelManager();
		this.idResolver = environmentFactory.getIdResolver();
	}

	@Override
	protected @NonNull TupleType createTupleType(@NonNull TupleTypeId tupleTypeId) {
		TupleType tupleType = new TupleTypeImpl(tupleTypeId);
		tupleType.setName(TypeId.TUPLE_NAME);
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
		environmentFactory.addOrphanClass(tupleType);
		return tupleType;
	}
}