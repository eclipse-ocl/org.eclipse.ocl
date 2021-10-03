/**
 * Copyright (c) 2020, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.symbolic;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.IteratorVariable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicAnalysis;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicEvaluationVisitor;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IterableValue;
import org.eclipse.ocl.pivot.values.MapValue;
import org.eclipse.ocl.pivot.values.NumberValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.17
 */
public abstract class SymbolicContent
{
	public static class SymbolicCollectionContent extends SymbolicContent
	{
		protected final @Nullable CollectionValue collectionValue;

		public SymbolicCollectionContent(@NonNull String name, @NonNull CollectionTypeId typeId, @Nullable CollectionValue collectionValue) {
			super(name, typeId);
			this.collectionValue = collectionValue;
		}

		protected SymbolicCollectionContent(@NonNull SymbolicCollectionContent originalContent) {
			super(originalContent);
			this.collectionValue = originalContent.collectionValue;
		}

		@Override
		public @NonNull SymbolicValue getElementalSymbolicValue(@NonNull SymbolicEvaluationVisitor symbolicEvaluationVisitor, @NonNull IteratorVariable iteratorVariable) {
			SymbolicAnalysis symbolicAnalysis = symbolicEvaluationVisitor.getSymbolicAnalysis();
			TypeId elementTypeId = ((CollectionTypeId)typeId).getElementTypeId();
			CollectionValue collectionValue2 = collectionValue;
			if ((collectionValue2 == null) || !(elementTypeId instanceof CollectionTypeId)) {
				SymbolicValue symbolicValue = symbolicAnalysis.getUnknownValue(iteratorVariable, SymbolicUtil.isRequiredReason(iteratorVariable), null);
				return symbolicValue;
			}
			SymbolicNumericValue sizeValue = computeElementSize(symbolicAnalysis, collectionValue2);
			String constantName = symbolicAnalysis.createConstantName();
			SymbolicCollectionContent content = new SymbolicCollectionContent("c#" + constantName + "%", collectionValue2.getTypeId(), collectionValue2);
			content.setSize(sizeValue);
			return new SymbolicKnownValue(constantName, elementTypeId, null, content);
		}

		@Override
		public @NonNull SymbolicContent shallowClone() {
			return new SymbolicCollectionContent(this);
		}
	}

	public static class SymbolicMapContent extends SymbolicContent
	{
		protected final @Nullable MapValue mapValue;

		public SymbolicMapContent(@NonNull String name, @NonNull MapTypeId typeId, @Nullable MapValue mapValue) {
			super(name, typeId);
			this.mapValue = mapValue;
		}

		protected SymbolicMapContent(@NonNull SymbolicMapContent originalContent) {
			super(originalContent);
			this.mapValue = originalContent.mapValue;
		}

		@Override
		public @NonNull SymbolicValue getElementalSymbolicValue(@NonNull SymbolicEvaluationVisitor symbolicEvaluationVisitor, @NonNull IteratorVariable iteratorVariable) {
			SymbolicAnalysis symbolicAnalysis = symbolicEvaluationVisitor.getSymbolicAnalysis();
			TypeId keyTypeId = ((MapTypeId)typeId).getKeyTypeId();
			MapValue mapValue2 = mapValue;
			if ((mapValue2 == null) || !(keyTypeId instanceof MapTypeId)) {
				SymbolicValue symbolicValue = symbolicAnalysis.getUnknownValue(iteratorVariable, SymbolicUtil.isRequiredReason(iteratorVariable), null);
				return symbolicValue;
			}
			SymbolicNumericValue sizeValue = computeElementSize(symbolicAnalysis, mapValue2);
			String constantName = symbolicAnalysis.createConstantName();
			SymbolicMapContent content = new SymbolicMapContent("c#" + constantName + "%", mapValue2.getTypeId(), mapValue2);
			content.setSize(sizeValue);
			return new SymbolicKnownValue(constantName, keyTypeId, null, content);
		}

		@Override
		public @NonNull SymbolicContent shallowClone() {
			return new SymbolicMapContent(this);
		}
	}

	protected final @NonNull String name;
	protected final @NonNull TypeId typeId;
	private int cloneCount = 0;
	private @Nullable SymbolicValue sizeValue;

	protected SymbolicContent(@NonNull String name, @NonNull TypeId typeId) {
		this.name = name;
		this.typeId = typeId;
	}

	protected SymbolicContent(@NonNull SymbolicContent originalContent) {
		this.name = originalContent.name + originalContent.cloneCount++ + "%";
		this.typeId = originalContent.typeId;
		this.sizeValue = originalContent.sizeValue;
	}

	protected @NonNull SymbolicNumericValue computeElementSize(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull IterableValue iterableValue) {
		NumberValue minimumLowerBound = null;
		NumberValue minimumUpperBound = null;
		for (@Nullable Object elementValue : iterableValue) {
			SymbolicValue elementSymbolicValue = symbolicAnalysis.getKnownValue(elementValue);
			SymbolicValue elementSize = elementSymbolicValue.getContent().getSize();
			NumberValue elementLowerBound = elementSize.getLowerBound();
			if (minimumLowerBound == null) {
				minimumLowerBound = elementLowerBound;
			}
			else if (elementLowerBound.compareTo(minimumLowerBound) < 0) {
				minimumLowerBound = elementLowerBound;
			}
			NumberValue elementUpperBound = elementSize.getUpperBound();
			if (elementUpperBound != null) {
				if (minimumUpperBound == null) {
					minimumUpperBound = elementUpperBound;
				}
				else if (elementUpperBound.compareTo(minimumUpperBound) < 0) {
					minimumUpperBound = elementUpperBound;
				}
			}
		}
		if (minimumLowerBound == null) {
			minimumLowerBound = ValueUtil.ZERO_VALUE;
		}
		return SymbolicNumericValue.get(minimumLowerBound, minimumUpperBound);
	}

	public abstract @NonNull SymbolicValue getElementalSymbolicValue(@NonNull SymbolicEvaluationVisitor symbolicEvaluationVisitor, @NonNull IteratorVariable iteratorVariable);

	public @NonNull SymbolicValue getSize() {
		SymbolicValue sizeValue2 = sizeValue;
		if (sizeValue2 == null) {
			sizeValue = sizeValue2 = new SymbolicUnknownValue(name + "size", TypeId.INTEGER, null, null);
		}
		return sizeValue2;
	}

	public boolean isEmpty() {
		return getSize().isZero();
	}

	public boolean mayBeEmpty() {
		return getSize().mayBeZero();
	}

	public void setSize(@NonNull SymbolicValue sizeValue) {
		this.sizeValue = sizeValue;
	}

	public abstract @NonNull SymbolicContent shallowClone();

	@Override
	public /*final*/ @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s);
		return s.toString();
	}

	public void toString(@NonNull StringBuilder s) {
		SymbolicValue sizeValue2 = sizeValue;
		if (sizeValue2 != null) {
			s.append("size:");
			sizeValue2.toString(s);
		}
	}
}
