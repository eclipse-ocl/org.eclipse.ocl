/*******************************************************************************
 * Copyright (c) 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.values;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;

/**
 * AbstractCollectionFactory provides the common functionality for CollectionValue creation.
 * @since 1.3
 */
public abstract class AbstractCollectionFactory implements CollectionFactory
{
	public static @NonNull CollectionFactory getCollectionFactory(@NonNull CollectionTypeId typeId) {
		typeId = typeId.getGeneralizedId();
		if (typeId == TypeId.BAG) {
			return BagFactory.INSTANCE;
		}
		//		else if (typeId == TypeId.COLLECTION) {
		//			return "COLLECTION";
		//		}
		else if (typeId == TypeId.ORDERED_SET) {
			return OrderedSetFactory.INSTANCE;
		}
		else if (typeId == TypeId.SEQUENCE) {
			return SequenceFactory.INSTANCE;
		}
		else if (typeId == TypeId.SET) {
			return SetFactory.INSTANCE;
		}
		//		else if (typeId == TypeId.UNIQUE_COLLECTION) {
		//			return "UNIQUE_COLLECTION";
		//		}
		else {
			//			return null;
			throw new UnsupportedOperationException();
		}
	}

	private static class BagFactory extends AbstractCollectionFactory
	{
		public static final @NonNull CollectionFactory INSTANCE = new BagFactory();

		private BagFactory() {
			super(false, false);
		}
	}

	private static class OrderedSetFactory extends AbstractCollectionFactory
	{
		public static final @NonNull CollectionFactory INSTANCE = new OrderedSetFactory();

		private OrderedSetFactory() {
			super(true, true);
		}
	}

	private static class SequenceFactory extends AbstractCollectionFactory
	{
		public static final @NonNull CollectionFactory INSTANCE = new SequenceFactory();

		private SequenceFactory() {
			super(true, false);
		}
	}

	private static class SetFactory extends AbstractCollectionFactory
	{
		public static final @NonNull CollectionFactory INSTANCE = new SetFactory();

		private SetFactory() {
			super(false, true);
		}
	}

	protected final boolean isOrdered;
	protected final boolean isUnique;

	protected AbstractCollectionFactory(boolean isOrdered, boolean isUnique) {
		this.isOrdered = isOrdered;
		this.isUnique = isUnique;
	}

	@Override
	public @NonNull String getKind() {
		if (this == TypeId.BAG) {
			return TypeId.BAG_NAME;
		}
		//		else if (this == TypeId.COLLECTION) {
		//			return "COLLECTION";
		//		}
		else if (this == TypeId.ORDERED_SET) {
			return TypeId.ORDERED_SET_NAME;
		}
		else if (this == TypeId.SEQUENCE) {
			return TypeId.SEQUENCE_NAME;
		}
		else if (this == TypeId.SET) {
			return TypeId.SET_NAME;
		}
		//		else if (this == TypeId.UNIQUE_COLLECTION) {
		//			return "UNIQUE_COLLECTION";
		//		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	//	@Override
	//	public @NonNull CollectionTypeId getTypeId() {
	//		return typeId;
	//	}

	@Override
	public boolean isOrdered() {
		return isOrdered;
	}

	@Override
	public boolean isUnique() {
		return isUnique;
	}
}
