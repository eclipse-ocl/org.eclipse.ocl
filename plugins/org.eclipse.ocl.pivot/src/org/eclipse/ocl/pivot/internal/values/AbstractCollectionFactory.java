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
			//			throw new UnsupportedOperationException();
			return SequenceFactory.INSTANCE;
		}
	}

	private static class BagFactory extends AbstractCollectionFactory
	{
		public static final @NonNull CollectionFactory INSTANCE = new BagFactory();

		private BagFactory() {
			super(TypeId.BAG_NAME, false, false);
		}
	}

	private static class BaseCollectionFactory extends AbstractCollectionFactory
	{
		public static final @NonNull CollectionFactory INSTANCE = new BaseCollectionFactory();

		private BaseCollectionFactory() {
			super(TypeId.COLLECTION_NAME, false, false);
		}
	}

	private static class OrderedSetFactory extends AbstractCollectionFactory
	{
		public static final @NonNull CollectionFactory INSTANCE = new OrderedSetFactory();

		private OrderedSetFactory() {
			super(TypeId.ORDERED_SET_NAME, true, true);
		}
	}

	private static class SequenceFactory extends AbstractCollectionFactory
	{
		public static final @NonNull CollectionFactory INSTANCE = new SequenceFactory();

		private SequenceFactory() {
			super(TypeId.SEQUENCE_NAME, true, false);
		}
	}

	private static class SetFactory extends AbstractCollectionFactory
	{
		public static final @NonNull CollectionFactory INSTANCE = new SetFactory();

		private SetFactory() {
			super(TypeId.SET_NAME, false, true);
		}
	}

	protected final @NonNull String kind;
	protected final boolean isOrdered;
	protected final boolean isUnique;

	protected AbstractCollectionFactory(@NonNull String kind, boolean isOrdered, boolean isUnique) {
		this.kind = kind;
		this.isOrdered = isOrdered;
		this.isUnique = isUnique;
	}

	@Override
	public @NonNull String getKind() {
		return kind;
	}

	@Override
	public boolean isBag() {
		return !isUnique && !isOrdered;
	}

	@Override
	public boolean isOrdered() {
		return isOrdered;
	}

	@Override
	public boolean isOrderedSet() {
		return isUnique && isOrdered;
	}

	@Override
	public boolean isSequence() {
		return !isUnique && isOrdered;
	}

	@Override
	public boolean isSet() {
		return isUnique && !isOrdered;
	}

	@Override
	public boolean isUnique() {
		return isUnique;
	}
}
