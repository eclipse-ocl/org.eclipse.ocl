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
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.NameUtil;

/**
 * The MergerGroupVisitor supports the grouping of the nested list of partial elements from each is-many
 * partial element to form a list of to-be-merged partial elements for each merged element.
 */
public class MergerGroupVisitor extends AbstractExtendingVisitor<@NonNull Iterable<@NonNull Iterable<@NonNull ? extends Element>>, @NonNull Merger>
{

	interface Partitioner<E extends Element>
	{
	//	@NonNull Map<@NonNull K, @NonNull List<@NonNull C>> partition(@NonNull Iterable<@NonNull P> partialElements);
		public @NonNull Iterable<@NonNull Iterable<@NonNull ? extends Element>> partition(@NonNull Iterable<@NonNull Iterable<@NonNull E>> ungroupedPartialElements);
	}

	abstract class AbstractPartitioner<E extends Element> implements Partitioner<E>
	{
	//	protected abstract @NonNull Iterable<@NonNull C> getChildren(@NonNull P parentPartialElement);
	}


	class NamePartitioner<E extends NamedElement> extends AbstractPartitioner<E>
	{
	/*	protected @NonNull Map<@NonNull String, @NonNull List<@NonNull C>> partitionByName(@NonNull Iterable<@NonNull P> partialParents) {
			Map<@NonNull String, @NonNull List<@NonNull C>> name2elements = new HashMap<>();
			for (@NonNull P partialParent : partialParents) {
				Iterable<@NonNull C> partialChildren = getChildren(partialParent);
				for (@NonNull C partialChild : partialChildren) {
					String name = NameUtil.getName(partialChild);
					List<@NonNull C> childElements = name2elements.get(name);
					if (childElements == null) {
						childElements = new ArrayList<>();
					}
					if (!childElements.contains(partialChild)) {
						childElements.add(partialChild);
					}
				}
			}
			List<@NonNull String> names = new ArrayList<>(name2elements.keySet());
			Collections.sort(names);
			for (@NonNull String name : names) {
				List<@NonNull C> elements = name2elements.get(name);
				assert elements != null;
				createElement(elements);
			}
			return name2elements;
		} */

		@Override
		public @NonNull Iterable<@NonNull Iterable<@NonNull ? extends Element>> partition(@NonNull Iterable<@NonNull Iterable<@NonNull E>> ungroupedPartialElements) {
			Map<@NonNull String, @NonNull List<@NonNull E>> name2groupedElements = new HashMap<>();
			for (@NonNull Iterable<@NonNull E> partialElements : ungroupedPartialElements) {
				for (@NonNull E partialElement : partialElements) {
					String name = NameUtil.getName(partialElement);
					List<@NonNull E> groupedElements = name2groupedElements.get(name);
					if (groupedElements == null) {
						groupedElements = new ArrayList<>();
					}
					if (!groupedElements.contains(partialElement)) {
						groupedElements.add(partialElement);
					}
				}
			}
			List<@NonNull Iterable<@NonNull ? extends Element>> groupedPartialElements = new ArrayList<>();
			List<@NonNull String> names = new ArrayList<>(name2groupedElements.keySet());
			Collections.sort(names);
			for (@NonNull String name : names) {
				List<@NonNull E> groupedElements = name2groupedElements.get(name);
				assert groupedElements != null;
				groupedPartialElements.add(groupedElements);
			}
			return groupedPartialElements;
		}
	}

/*	class PackageOwnedClassPartitioner extends AbstractNamePartitioner<org.eclipse.ocl.pivot.@NonNull Package, org.eclipse.ocl.pivot.@NonNull Class>
	{

		@Override
		protected @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> getChildren(org.eclipse.ocl.pivot.@NonNull Package parentPartialElement) {
			return parentPartialElement.getOwnedClasses();
		}

		@Override
		public @NonNull Map<@NonNull String, @NonNull List<org.eclipse.ocl.pivot.@NonNull Class>> partition(@NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Package> partialElements) {
			return partitionByName(partialElements);
		}
	} */

	private @NonNull NamePartitioner<@NonNull NamedElement> namePartitioner = new NamePartitioner<>();
//	private @NonNull Map<@NonNull EClass, @NonNull Integer> eClass2depth = new HashMap<>();

	public MergerGroupVisitor(@NonNull Merger context) {
		super(context);
	}

/*	private <E extends Element> @NonNull E createElement(@NonNull Iterable<@NonNull E> elements) {
		EClass largestEClass = computeCommonEClass(elements);
		@SuppressWarnings("unchecked")
		E mergedElement = (E)PivotFactory.eINSTANCE.create(largestEClass);
		context.putPartialElements(mergedElement, elements);
		return mergedElement;
	}

	private boolean mergeAbstract(@NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> partialClasses) {
		boolean mergedAbstract = false;
		for (org.eclipse.ocl.pivot.@NonNull Class partialClass : partialClasses) {
			if (partialClass.isIsAbstract()) {
				mergedAbstract = true;
			}
		}
		return mergedAbstract;
	}

	private @NonNull String mergeName(@NonNull Iterable<@NonNull ? extends NamedElement> partialElements) {
		String mergedName = null;
		for (@NonNull NamedElement partialElement : partialElements) {
			String partialName = NameUtil.getName(partialElement);
			if (mergedName == null) {
				mergedName = partialName;
			}
			else {
				assert partialName.equals(mergedName);
			}
		}
		assert mergedName != null;
		return mergedName;
	}

	private @NonNull String mergeURI(@NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Package> partialPackages) {
		String mergedURI = null;
		for (org.eclipse.ocl.pivot.@NonNull Package partialPackage : partialPackages) {
			String partialName = PivotUtil.getURI(partialPackage);
			if (mergedURI == null) {
				mergedURI = partialName;
			}
			else {
				assert partialName.equals(mergedURI);
			}
		}
		assert mergedURI != null;
		return mergedURI;
	} */

	@Override
	public @NonNull Iterable<@NonNull Iterable<@NonNull ? extends Element>> visiting(@NonNull Visitable visitable) {
		throw new UnsupportedOperationException("Unsupported " + visitable.eClass().getName() + " for " + getClass().getSimpleName());
	//	return null;
	}

/*	@Override
	public @Nullable Element visitClass(org.eclipse.ocl.pivot.@NonNull Class mergedClass) {
		Iterable<org.eclipse.ocl.pivot.@NonNull Class> partialClasses = context.getPartialElements(mergedClass);
		mergedClass.setName(mergeName(partialClasses));
		// NsPrefix
		mergedClass.setIsAbstract(mergeAbstract(partialClasses));
		return super.visitClass(mergedClass);
	} */

/*	@Override
	public @Nullable Element visitElement(@NonNull Element mergedParent) {
		Iterable<@NonNull Element> partialParents = context.getPartialElements(mergedParent);
		for (@NonNull EReference eContainment : mergedParent.eClass().getEAllContainments()) {
			List<@NonNull Element> allPartialChildren = new ArrayList<>();
			for (@NonNull Element partialParent : partialParents) {
				Object partialChildOrChildren = partialParent.eGet(eContainment);
				if (eContainment.isMany()) {
					@SuppressWarnings("unchecked")
					List<@NonNull Element> partialChildren = (List<@NonNull Element>)partialChildOrChildren;
					allPartialChildren.addAll(partialChildren);
				}
				else {
					assert partialChildOrChildren != null;
					Element partialChild = (Element)partialChildOrChildren;
					allPartialChildren.add(partialChild);
				}
				Iterable<@NonNull Element> partialParents = context.getPartialElements(mergedParent);
			}
		}
	//	List<?> owns = cgElement instanceof CGValuedElement ? ((CGValuedElement)cgElement).getOwns() : null;
	//	for (CGElement cgChild : cgElement.getChildren()) {
	//		if ((owns == null) || !owns.contains(cgChild)) {
	//			cgChild.accept(this);
	//		}
	//	}
		return null;
	} */

	@Override
	public @NonNull Iterable<@NonNull Iterable<@NonNull ? extends Element>> visitNamedElement(@NonNull NamedElement protoNamedElement) {
		Iterable<@NonNull Iterable<@NonNull NamedElement>> ungroupedPartialElements = context.getUngroupedPartialElements(protoNamedElement);
		Iterable<@NonNull Iterable<@NonNull ? extends Element>> groupedPartialElements = namePartitioner.partition(ungroupedPartialElements);
		return groupedPartialElements;
	}

/*	@Override
	public @Nullable Iterable<@NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Package>> visitPackage(org.eclipse.ocl.pivot.@NonNull Package protoPackage) {
		Iterable<@NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Package>> ungroupedPartialPackages = context.getUngroupedPartialElements(protoPackage);
		mergedPackage.setName(mergeName(partialPackages));
		// NsPrefix
		mergedPackage.setURI(mergeURI(partialPackages));

		//merged

		Map<@NonNull String, @NonNull List<org.eclipse.ocl.pivot.@NonNull Class>> name2classes = packageOwnedClassPartitioner.partitionByName(ungroupedPartialPackages);
		List<@NonNull String> names = new ArrayList<>(name2classes.keySet());
		Collections.sort(names);
		for (@NonNull String name : names) {
			Iterable<org.eclipse.ocl.pivot.@NonNull Class> classes = name2classes.get(name);
		}
		return super.visitPackage(mergedPackage);
	} */
}
