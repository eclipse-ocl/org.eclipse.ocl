/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.merge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * The MergerGroupVisitor supports the grouping of the nested list of partial elements from each is-many
 * partial element to form a list of to-be-merged partial elements for each merged element.
 */
public class MergerGroupVisitor extends AbstractExtendingVisitor<@NonNull ListOfList<@NonNull Element>, @NonNull Merger>
{
	interface Partitioner<E extends Element>
	{
		public @NonNull ListOfList<@NonNull Element> partition(@NonNull ListOfList<@NonNull E> ungroupedPartialElements);
	}

	private static abstract class AbstractPartitioner<E extends Element, K> implements Partitioner<E>
	{
		protected abstract @Nullable K getKey(@NonNull E partialElement);

		@Override
		public @NonNull ListOfList<@NonNull Element> partition(@NonNull ListOfList<@NonNull E> ungroupedPartialElements) {
			Map<@NonNull K, @NonNull List<@NonNull Element>> key2groupedElements = new HashMap<>();
			for (@NonNull Iterable<@NonNull E> partialElements : ungroupedPartialElements.getOuterIterable()) {
				for (@NonNull E partialElement : partialElements) {
					@Nullable K key = getKey(partialElement);
					if (key != null) {
						List<@NonNull Element> groupedElements = key2groupedElements.get(key);
						if (groupedElements == null) {
							groupedElements = new ArrayList<>();
							key2groupedElements.put(key, groupedElements);
						}
						if (!groupedElements.contains(partialElement)) {
							groupedElements.add(partialElement);
						}
					}
				}
			}
			ListOfList<@NonNull Element> groupedPartialElements = new ListOfList<>();
			List<@NonNull K> keys = new ArrayList<>(key2groupedElements.keySet());
			sort(keys);
			for (@NonNull K key : keys) {
				List<@NonNull Element> groupedElements = key2groupedElements.get(key);
				assert groupedElements != null;
				groupedPartialElements.add(groupedElements);
			}
			return groupedPartialElements;
		}

		protected abstract void sort(@NonNull List<@NonNull K> keys);
	}

	private static class CommentPartitioner extends AbstractPartitioner<@NonNull Comment, @NonNull String>
	{
		@Override
		protected @NonNull String getKey(@NonNull Comment partialElement) {
			return PivotUtil.getBody(partialElement);
		}

		@Override
		protected void sort(@NonNull List<@NonNull String> keys) {
			Collections.sort(keys);
		}
	}

	private static class ConstraintPartitioner extends AbstractPartitioner<@NonNull Constraint, @NonNull String>
	{
		@Override
		protected @NonNull String getKey(@NonNull Constraint partialElement) {
			String name = partialElement.getName();
			return (name != null ? name : "") + ":" + partialElement.toString();
		}

		@Override
		protected void sort(@NonNull List<@NonNull String> keys) {
			Collections.sort(keys);
		}
	}

	private static class NamePartitioner<E extends NamedElement> extends AbstractPartitioner<E, @NonNull String>
	{
		@Override
		protected @NonNull String getKey(@NonNull E partialElement) {
			return NameUtil.getName(partialElement);
		}

		@Override
		protected void sort(@NonNull List<@NonNull String> keys) {
			Collections.sort(keys);
		}
	}

	private static class OperationPartitioner extends AbstractPartitioner<@NonNull Operation, OperationId>
	{
		@Override
		protected @NonNull OperationId getKey(@NonNull Operation partialElement) {
			return partialElement.getOperationId();
		}

		@Override
		protected void sort(@NonNull List<@NonNull OperationId> keys) {
			Collections.sort(keys, NameUtil.TO_STRING_COMPARATOR);
		}
	}

	private static class PropertyPartitioner extends AbstractPartitioner<@NonNull Property, @NonNull String>
	{
		@Override
		protected @Nullable String getKey(@NonNull Property partialElement) {
			String name = NameUtil.getName(partialElement);
			if ("Property".equals(name)) {
				getClass();			// XXX
			}
			if (partialElement.getOwningClass() == partialElement.getType()) {
				getClass();			// XXX
		//		return null;
			}
			if (partialElement.isIsImplicit()) {
				return null;
			}
			return name;
		}

		@Override
		protected void sort(@NonNull List<@NonNull String> keys) {
			Collections.sort(keys);
		}
	}

	private @NonNull CommentPartitioner commentPartitioner = new CommentPartitioner();
	private @NonNull ConstraintPartitioner constraintPartitioner = new ConstraintPartitioner();
	private @NonNull NamePartitioner<@NonNull NamedElement> namePartitioner = new NamePartitioner<>();
	private @NonNull OperationPartitioner operationPartitioner = new OperationPartitioner();
	private @NonNull PropertyPartitioner propertyPartitioner = new PropertyPartitioner();

	public MergerGroupVisitor(@NonNull Merger context) {
		super(context);
	}

	@Override
	public @NonNull ListOfList<@NonNull Element> visiting(@NonNull Visitable visitable) {
		throw new UnsupportedOperationException("Unsupported " + visitable.eClass().getName() + " for " + getClass().getSimpleName());
	//	return null;
	}

	@Override
	public @NonNull ListOfList<@NonNull Element> visitComment(@NonNull Comment protoComment) {
		ListOfList<@NonNull Comment> ungroupedPartialElements = context.getUngroupedPartialElements(protoComment);
		ListOfList<@NonNull Element> groupedPartialElements = commentPartitioner.partition(ungroupedPartialElements);
		return groupedPartialElements;
	}

	@Override
	public @NonNull ListOfList<@NonNull Element> visitConstraint( @NonNull Constraint protoConstraint) {
		ListOfList<@NonNull Constraint> ungroupedPartialElements = context.getUngroupedPartialElements(protoConstraint);
		ListOfList<@NonNull Element> groupedPartialElements = constraintPartitioner.partition(ungroupedPartialElements);
		return groupedPartialElements;
	}

	@Override
	public @NonNull ListOfList<@NonNull Element> visitNamedElement(@NonNull NamedElement protoNamedElement) {
		ListOfList<@NonNull NamedElement> ungroupedPartialElements = context.getUngroupedPartialElements(protoNamedElement);
		ListOfList<@NonNull Element> groupedPartialElements = namePartitioner.partition(ungroupedPartialElements);
		return groupedPartialElements;
	}

	@Override
	public @NonNull ListOfList<@NonNull Element> visitOperation(@NonNull Operation protoOperation) {
		ListOfList<@NonNull Operation> ungroupedPartialElements = context.getUngroupedPartialElements(protoOperation);
		ListOfList<@NonNull Element> groupedPartialElements = operationPartitioner.partition(ungroupedPartialElements);
		return groupedPartialElements;
	}

	@Override
	public @NonNull ListOfList<@NonNull Element> visitProperty(@NonNull Property protoProperty) {
		ListOfList<@NonNull Property> ungroupedPartialElements = context.getUngroupedPartialElements(protoProperty);
		ListOfList<@NonNull Element> groupedPartialElements = propertyPartitioner.partition(ungroupedPartialElements);
		return groupedPartialElements;
	//	if (ungroupedPartialElements.toString().contains("opposite")) {
	//		getClass();		// XXX
	//	}
	//	return super.visitProperty(protoProperty);
	}
}
