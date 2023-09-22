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
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Orphanage;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.ids.OverloadId;
import org.eclipse.ocl.pivot.merge.EAssociationHelper.EAssociation;
import org.eclipse.ocl.pivot.util.PivotPlugin;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TracingOption;

import com.google.common.collect.Iterables;

/**
 * The Merger supports the creation of a merged model from one or more partial models.
 * <br>
 * The merging uses the Ecore metamodel to merge the actual models. This avoids the need to code a distinct merger
 * for each class, but unfortunately omits all implicit elements that lack an Ecore counterpart. Missing implicits
 * requires additional irregular coding.
 */
public class Merger
{
	private static @NonNull Class<?> classSortOrder[] = new @NonNull Class<?>[] {
	/*	PivotPackage.Literals.TUPLE_TYPE,
		PivotPackage.Literals.LAMBDA_TYPE,
		PivotPackage.Literals.MAP_TYPE,
		PivotPackage.Literals.SET_TYPE,
		PivotPackage.Literals.SEQUENCE_TYPE,
		PivotPackage.Literals.ORDERED_SET_TYPE,
		PivotPackage.Literals.BAG_TYPE,
		PivotPackage.Literals.COLLECTION_TYPE,
		PivotPackage.Literals.CLASS,
		PivotPackage.Literals.PACKAGE,
		PivotPackage.Literals.LIBRARY, */
		org.eclipse.ocl.pivot.Class.class,
		org.eclipse.ocl.pivot.Package.class
	};

	public static final @NonNull TracingOption MERGER_ADD = new TracingOption(PivotPlugin.PLUGIN_ID, "merger/add");

	public static class MergeNode
	{
	//	protected final @NonNull MergeDescriptor mergeDescriptor;
		protected final @NonNull Element mergedElement;
		protected final @NonNull List<@NonNull ? extends Element> partialElements;

		public MergeNode(/*@NonNull MergeDescriptor mergeDescriptor,*/ @NonNull Element mergedElement, @NonNull List<@NonNull ? extends Element> partialElements) {
	//		this.mergeDescriptor = mergeDescriptor;
			this.mergedElement = mergedElement;
			this.partialElements = partialElements;
		}

		public @NonNull Element getMergedElement() {
			return mergedElement;
		}

		public @NonNull List<? extends Element> getPartialElements() {
			return partialElements;
		}
	}

	/**
	 * A MergeDescriptor supports the re-aggregation of the the multi-elements from multiple partial is many reference values.
	 *
	 * The MergeDescriptor for a particular Class.ownedOperations therefore comprises a list of all operations of the first
	 * partial class followed by those of the second partial class and so forth. After partitioning we have a lidst of
	 * all the partial operations of the first OperationId followed by all the partial operations of the second OperationId and so firth.
	 */
	public static interface MergeDescriptor
	{
		@NonNull EClass getCompatibleEClass(@NonNull ESuperClassHelper eSuperClassHelper, @NonNull List<@NonNull ? extends Element> partialElements);
		int getDepth();
		@NonNull List<@NonNull List<@NonNull Element>> partition();
	}

	protected static abstract class AbstractMergeDescriptor<E extends Element, K> implements MergeDescriptor
	{
		protected final int depth;
		/**
		 * The many partial values resulting from the invocation of eGet for each partial isMany element merge.
		 */
		protected final @NonNull List<@NonNull List<@NonNull E>> manyPartialValues;

		protected AbstractMergeDescriptor(int depth, @NonNull List<@NonNull List<@NonNull E>> manyPartialValues) {
			this.depth = depth;
			this.manyPartialValues = manyPartialValues;
		}

		@Override
		public @NonNull EClass getCompatibleEClass(@NonNull ESuperClassHelper eSuperClassHelper, @NonNull List<@NonNull ? extends Element> partialElements) {
			EClass eClass = eSuperClassHelper.getCompatibleEClass(partialElements);
			return eClass;
		}

		@Override
		public int getDepth() {
			return depth;
		}

		protected abstract @Nullable K getKey(@NonNull E partialElement);

		@Override
		public @NonNull List<@NonNull List<@NonNull Element>> partition() {
			@NonNull List<@NonNull List<@NonNull Element>> manyListsOfPartialElements = new ArrayList<>();
			int size = manyPartialValues.size();
			if (size >= 1) {
				Map<@NonNull K, @NonNull List<@NonNull Element>> key2groupedElements = new HashMap<>();
				for (@NonNull Iterable<@NonNull E> partialValues : manyPartialValues) {
					for (@NonNull E partialValue : partialValues) {
						@Nullable K key = getKey(partialValue);
						if (key != null) {
							List<@NonNull Element> groupedElements = key2groupedElements.get(key);
							if (groupedElements == null) {
								groupedElements = new ArrayList<>();
								key2groupedElements.put(key, groupedElements);
								manyListsOfPartialElements.add(groupedElements);
							}
							if (!groupedElements.contains(partialValue)) {
								groupedElements.add(partialValue);
							}
						}
					}
				}
			}
			else {
				throw new IllegalStateException("No partials");
			}
			return manyListsOfPartialElements;
		}

		protected abstract void sort(@NonNull List<@NonNull K> keys);
	}

	private static class CommentMergeDescriptor extends AbstractMergeDescriptor<@NonNull Comment, @NonNull String>
	{
		public CommentMergeDescriptor(int depth, @NonNull List<@NonNull List<@NonNull Comment>> manyPartialValues) {
			super(depth, manyPartialValues);
		}

		@Override
		protected @NonNull String getKey(@NonNull Comment partialElement) {
			return PivotUtil.getBody(partialElement);
		}

		@Override
		protected void sort(@NonNull List<@NonNull String> keys) {
			Collections.sort(keys);
		}
	}

	private static class ConstraintMergeDescriptor extends AbstractMergeDescriptor<@NonNull Constraint, @NonNull String>
	{
		public ConstraintMergeDescriptor(int depth, @NonNull List<@NonNull List<@NonNull Constraint>> manyPartialValues) {
			super(depth, manyPartialValues);
		}

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

	private static class ElementMergeDescriptor extends AbstractMergeDescriptor<@NonNull Element, @NonNull Integer>
	{
		public ElementMergeDescriptor(int depth, @NonNull List<@NonNull List<@NonNull Element>> manyPartialValues) {
			super(depth, manyPartialValues);
		}

		@Override
		protected @Nullable Integer getKey(@NonNull Element partialElement) {
			assert !partialElement.eContainingFeature().isMany();
			return 0;
		}

		@Override
		protected void sort(@NonNull List<@NonNull Integer> keys) {
			throw new IllegalStateException();
		}
	}

	private static class NamedElementMergeDescriptor extends AbstractMergeDescriptor<@NonNull NamedElement, @NonNull String>
	{
		public NamedElementMergeDescriptor(int depth, @NonNull List<@NonNull List<@NonNull NamedElement>> manyPartialValues) {
			super(depth, manyPartialValues);
		}

		@Override
		protected @Nullable String getKey(@NonNull NamedElement partialElement) {
			return partialElement.getName();
		}

		@Override
		protected void sort(@NonNull List<@NonNull String> keys) {
			Collections.sort(keys);
		}
	}

	private static class OCLExpressionMergeDescriptor extends AbstractMergeDescriptor<@NonNull OCLExpression, @NonNull Integer>
	{
		public OCLExpressionMergeDescriptor(int depth, @NonNull List<@NonNull List<@NonNull OCLExpression>> manyPartialValues) {
			super(depth, manyPartialValues);
		}

		@Override
		protected @Nullable Integer getKey(@NonNull OCLExpression partialElement) {
			EObject eContainer = partialElement.eContainer();
			assert eContainer != null;
			EReference eContainmentFeature = partialElement.eContainmentFeature();
			assert eContainmentFeature != null;
			Object eSiblings = eContainer.eGet(eContainmentFeature);
			if (eSiblings instanceof List) {
				return ((List)eSiblings).indexOf(partialElement);
			}
			else {
				assert eSiblings == partialElement;
				return 0;
			}
		}

		@Override
		protected void sort(@NonNull List<@NonNull Integer> keys) {
			Collections.sort(keys);
		}
	}

	private static class OperationMergeDescriptor extends AbstractMergeDescriptor<@NonNull Operation, @NonNull OverloadId>
	{
		public OperationMergeDescriptor(int depth, @NonNull List<@NonNull List<@NonNull Operation>> manyPartialValues) {
			super(depth, manyPartialValues);
		}

		@Override
		protected @NonNull OverloadId getKey(@NonNull Operation partialElement) {
			return partialElement.getOverloadId();
		}

		@Override
		protected void sort(@NonNull List<@NonNull OverloadId> keys) {
			Collections.sort(keys, NameUtil.TO_STRING_COMPARATOR);
		}
	}

	private static class PackageMergeDescriptor extends AbstractMergeDescriptor<org.eclipse.ocl.pivot.@NonNull Package, @NonNull String>
	{
		public PackageMergeDescriptor(int depth, @NonNull List<@NonNull List<org.eclipse.ocl.pivot.@NonNull Package>> manyPartialValues) {
			super(depth, manyPartialValues);
		}

	//	@Override
	//	public @NonNull EClass getCompatibleEClass(@NonNull ESuperClassHelper eSuperClassHelper, @NonNull List<@NonNull ? extends Element> partialElements) {
	//		return PivotPackage.Literals.PACKAGE;
	//	}

		@Override
		protected @NonNull String getKey(org.eclipse.ocl.pivot.@NonNull Package partialElement) {
			return NameUtil.getName(partialElement);
		}

		@Override
		protected void sort(@NonNull List<@NonNull String> keys) {
			Collections.sort(keys);
		}
	}

	private static class PropertyMergeDescriptor extends AbstractMergeDescriptor<@NonNull Property, @NonNull String>
	{
		public PropertyMergeDescriptor(int depth, @NonNull List<@NonNull List<@NonNull Property>> manyPartialValues) {
			super(depth, manyPartialValues);
		}

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

	protected class MergedElementComparator implements Comparator<@NonNull Element>
	{
		private final @NonNull Map<org.eclipse.ocl.pivot.@NonNull Class, @NonNull Set<org.eclipse.ocl.pivot.@NonNull Class>> mergedClass2mergedClassAndSuperClasses = new HashMap<>();

		@Override
		public int compare(@NonNull Element o1, @NonNull Element o2) {
			int x1 = indexOf(o1.getClass());
			int x2 = indexOf(o2.getClass());
			int diff = x2 - x1;		// Last classSortOrder first
			if (diff != 0) {
				return diff;
			}
			if (o1 instanceof org.eclipse.ocl.pivot.Class) {
				if (o2 instanceof org.eclipse.ocl.pivot.Class) {
					int i1 = getMergedClassAndSuperClasses((org.eclipse.ocl.pivot.Class)o1).size();
					int i2 = getMergedClassAndSuperClasses((org.eclipse.ocl.pivot.Class)o2).size();
					diff = i1 - i2;
					if (diff != 0) {
						return diff;
					}
				}
				else {
					return 1;
				}
			}
			else {
				if (o2 instanceof org.eclipse.ocl.pivot.Class) {
					return -1;
				}

			}
			String n1;
			String n2;
			if ((o1 instanceof Nameable) && ((n1 = ((Nameable)o1).getName()) != null)) {
				if ((o2 instanceof Nameable) && ((n2 = ((Nameable)o2).getName()) != null)) {
					diff = n1.compareTo(n2);
					if (diff != 0) {
						return diff;
					}
				}
				else {
					return 1;
				}
			}
			else {
				if ((o2 instanceof Nameable) && ((n2 = ((Nameable)o2).getName()) != null)) {
					return -1;
				}
			}
		//	String s1 = String.valueOf(o1.toString());
		//	String s2 = String.valueOf(o2.toString());
		//	return s1.compareTo(s2);
			int i1 = System.identityHashCode(o1);
			int i2 = System.identityHashCode(o2);
			return i1 - i2;
		}

	/*	private @NonNull Set<org.eclipse.ocl.pivot.@NonNull Class> getSuperClasses(org.eclipse.ocl.pivot.@NonNull Class mergedClass) {
			Set<org.eclipse.ocl.pivot.@NonNull Class> superClasses = mergedClass2superClasses.get(mergedClass);
			if (superClasses == null) {
				superClasses = new HashSet<>();
				@SuppressWarnings("unchecked")
				List<org.eclipse.ocl.pivot.@NonNull Class> partialClasses = (List<org.eclipse.ocl.pivot.@NonNull Class>)mergedElement2partialElements.get(mergedClass);
				assert partialClasses != null;
				for (org.eclipse.ocl.pivot.@NonNull Class partialClass : partialClasses) {
					for (org.eclipse.ocl.pivot.@NonNull Class partialSuperClass : PivotUtil.getSuperClasses(partialClass)) {
						Set<org.eclipse.ocl.pivot.@NonNull Class> mergedSuperClasses = getSuperClasses(partialSuperClass);
						superClasses.addAll(mergedSuperClasses);
					}
				}
				mergedClass2superClasses.put(mergedClass, superClasses);
			}
			return superClasses;
		} */

		private @NonNull Set<org.eclipse.ocl.pivot.@NonNull Class> computeMergedClassAndSuperClasses(org.eclipse.ocl.pivot.@NonNull Class mergedClass) {
			Set<org.eclipse.ocl.pivot.@NonNull Class> mergedClassAndSuperClasses = new HashSet<>();
			mergedClassAndSuperClasses.add(mergedClass);
			@SuppressWarnings("unchecked")
			MergeNode mergeNode = element2mergeNode.get(mergedClass);
			assert mergeNode != null;
			List<org.eclipse.ocl.pivot.@NonNull Class> partialClasses = (List<org.eclipse.ocl.pivot.@NonNull Class>)mergeNode.getPartialElements();
			assert partialClasses != null;
			for (org.eclipse.ocl.pivot.@NonNull Class partialClass : partialClasses) {
				for (org.eclipse.ocl.pivot.@NonNull Class partialSuperClass : PivotUtil.getSuperClasses(partialClass)) {
					org.eclipse.ocl.pivot.Class mergedSuperClass = (org.eclipse.ocl.pivot.Class)partialElement2mergedElement.get(partialSuperClass);
					if (mergedSuperClass == null) {		// No merged specialization yet; make do with the unspecialized to get correct depth
						TemplateableElement unspecializedElement = partialSuperClass.getGeneric();
						assert unspecializedElement != null;
						mergedSuperClass = (org.eclipse.ocl.pivot.Class)partialElement2mergedElement.get(unspecializedElement);
						assert mergedSuperClass != null;
					}
					Set<org.eclipse.ocl.pivot.@NonNull Class> mergedSuperClasses = getMergedClassAndSuperClasses(mergedSuperClass);
					mergedClassAndSuperClasses.addAll(mergedSuperClasses);
				}
			}
			return mergedClassAndSuperClasses;
		}

		private @NonNull Set<org.eclipse.ocl.pivot.@NonNull Class> getMergedClassAndSuperClasses(org.eclipse.ocl.pivot.@NonNull Class mergedClass) {
		//	@SuppressWarnings("unchecked")
		//	org.eclipse.ocl.pivot.Class mergedClass = (org.eclipse.ocl.pivot.Class)partialElement2mergedElement.get(partialClass);
		//	assert mergedClass != null;
			Set<org.eclipse.ocl.pivot.@NonNull Class> mergedClassAndSuperClasses = mergedClass2mergedClassAndSuperClasses.get(mergedClass);
			if (mergedClassAndSuperClasses == null) {
				mergedClassAndSuperClasses = computeMergedClassAndSuperClasses(mergedClass);
				mergedClass2mergedClassAndSuperClasses.put(mergedClass, mergedClassAndSuperClasses);
			}
			return mergedClassAndSuperClasses;
		}

		private int indexOf(/*@NonNull*/ Class<?> jClass) {
			for (int i = 0; i < classSortOrder.length; i++) {
				if (classSortOrder[i].isAssignableFrom(jClass)) {
					return i;
				}
			}
			return -1;
		}
	}

	protected final @NonNull EnvironmentFactory environmentFactory;
	protected final @NonNull Orphanage mergedOrphanage;
	protected final @NonNull CompleteModel completeModel;
	protected final @NonNull ESuperClassHelper eSuperClassHelper;
	protected final @NonNull EAssociationHelper eAssociationHelper;
	protected final @NonNull MergerResolveVisitor resolveVisitor;

	/**
	 * The outgoing merged element created for each incoming partial element.
	 */
	private @NonNull Map<@NonNull Element, @NonNull Element> partialElement2mergedElement = new HashMap<>();

	/**
	 * The incoming partial elements from which each outgoing merged element was created
	 */
//	private @NonNull Map<@NonNull Element, @NonNull List<@NonNull ? extends Element>> mergedElement2partialElements = new HashMap<>();
	private @NonNull Map<@NonNull Element, @NonNull MergeNode> element2mergeNode = new HashMap<>();

	/**
	 * List of the per-merged-implicit-opposite list-of-partial-properties to be merged as an implicit opposite.
	 * Populated by gatherImplicitOppositeMerges during the containment tree pass.
	 */
	private @NonNull List<@NonNull List<@NonNull Property>> implicitOppositeMerges = new ArrayList<>();

	/**
	 * The detected problems.
	 */
	private @Nullable List<@NonNull String> problemMessages = null;

	public Merger(@NonNull EnvironmentFactory environmentFactory, @NonNull Orphanage mergedOrphanage) {
		this.environmentFactory = environmentFactory;
		this.mergedOrphanage = mergedOrphanage;
		this.completeModel = environmentFactory.getCompleteModel();
		this.eSuperClassHelper = new ESuperClassHelper();
		this.eAssociationHelper = new EAssociationHelper();
		this.resolveVisitor = createResolveVisitor();
	//	System.out.println("mergedOrphanage " + NameUtil.debugSimpleName(mergedOrphanage));
	}

	private void addMerge(int depth, @NonNull MergeNode mergeNode) {
		@NonNull Element mergedParent = mergeNode.getMergedElement();
		@NonNull List<@NonNull ? extends Element> partialParents = mergeNode.getPartialElements();
		if (MERGER_ADD.isActive()) {
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < depth; i++) {
				s.append("\t");
			}
			s.append("\taddMerge " + NameUtil.debugSimpleName(mergedParent));
			for (@NonNull Element partialParent : partialParents) {
				s.append("\n");
				for (int i = 0; i < depth; i++) {
					s.append("\t");
				}
				s.append(NameUtil.debugSimpleName(partialParent) + " : " + partialParent);
			}
			MERGER_ADD.println(s.toString());
		}
		if ("OclAny::oclAsType(TT)(oclAsType.TT[1]) : oclAsType.TT[1]".equals(partialParents.get(0).toString())) {
			getClass();		// XXX
		}
		if ((partialParents.get(0) instanceof Nameable) && "isComputable".equals(((Nameable)partialParents.get(0)).getName())) {
			getClass();		// XXX
		}
		if (mergedParent instanceof org.eclipse.ocl.pivot.Class) {
			CompleteClass completeClass = completeModel.getCompleteClass((org.eclipse.ocl.pivot.Class)partialParents.get(0));
			completeClass.getPartialClasses().add((org.eclipse.ocl.pivot.Class)mergedParent);
		}
		MergeNode old = element2mergeNode.put(mergedParent, mergeNode);
		assert old == null;
		for (@NonNull Element partialParent : partialParents) {
			partialElement2mergedElement.put(partialParent, mergedParent);
		}
	}

	protected void addProblem(@NonNull String message) {
		System.err.println(message);
		List<@NonNull String> problemMessages2 = problemMessages;
		if (problemMessages2 == null) {
			problemMessages = problemMessages2 = new ArrayList<>();
		}
		problemMessages2.add(message);
	}

	protected @NonNull MergeDescriptor createMergeDescriptor(int depth, @NonNull EClass eClass, @NonNull List<@NonNull List<@NonNull Element>> manyPartialValues) {
		if (PivotPackage.Literals.COMMENT.isSuperTypeOf(eClass)) {
			return new CommentMergeDescriptor(depth, (List<@NonNull List<@NonNull Comment>>)(Object)manyPartialValues);
		}
		else if (PivotPackage.Literals.CONSTRAINT.isSuperTypeOf(eClass)) {
			return new ConstraintMergeDescriptor(depth, (List<@NonNull List<@NonNull Constraint>>)(Object)manyPartialValues);
		}
		else if (PivotPackage.Literals.OCL_EXPRESSION.isSuperTypeOf(eClass)) {
			return new OCLExpressionMergeDescriptor(depth, (List<@NonNull List<org.eclipse.ocl.pivot.@NonNull OCLExpression>>)(Object)manyPartialValues);
		}
		else if (PivotPackage.Literals.OPERATION.isSuperTypeOf(eClass)) {
			return new OperationMergeDescriptor(depth, (List<@NonNull List<@NonNull Operation>>)(Object)manyPartialValues);
		}
		else if (PivotPackage.Literals.PACKAGE.isSuperTypeOf(eClass)) {
			return new PackageMergeDescriptor(depth, (List<@NonNull List<org.eclipse.ocl.pivot.@NonNull Package>>)(Object)manyPartialValues);
		}
		else if (PivotPackage.Literals.PROPERTY.isSuperTypeOf(eClass)) {
			return new PropertyMergeDescriptor(depth, (List<@NonNull List<@NonNull Property>>)(Object)manyPartialValues);
		}
		else if (PivotPackage.Literals.NAMED_ELEMENT.isSuperTypeOf(eClass)) {
			return new NamedElementMergeDescriptor(depth, (List<@NonNull List<@NonNull NamedElement>>)(Object)manyPartialValues);
		}
		else if (PivotPackage.Literals.ELEMENT.isSuperTypeOf(eClass)) {
			return new ElementMergeDescriptor(depth, manyPartialValues);
		}
		else {
			throw new UnsupportedOperationException("Unsupported getPartitioner for " + eClass.getName());
		}
	}

	protected <E extends Element> @NonNull MergeNode createMergeNode(@NonNull MergeDescriptor mergeDescriptor, @NonNull List<@NonNull E> partialElements) {
		EClass eClass = mergeDescriptor.getCompatibleEClass(eSuperClassHelper, partialElements);
	//	return mergeDescriptor.createMergeNode(eClass, partialElements);
		Element mergedChild = (Element)PivotFactory.eINSTANCE.create(eClass);
		return new MergeNode(mergedChild, partialElements);
	}

	protected @NonNull MergerResolveVisitor createResolveVisitor() {
		return new MergerResolveVisitor(this, mergedOrphanage);
	}

	protected void gatherImplicitOppositeMerges(@NonNull List<@NonNull Property> partialProperties) {
		//	StringBuilder so = null;
		for (@NonNull Property partialProperty : partialProperties) {
			assert !partialProperty.isIsImplicit();
			if ("OclElement".equals(partialProperty.getName())) {
				getClass();		// XXX
			}
			Property partialOpposite = partialProperty.getOpposite();
			if ((partialOpposite != null) && (partialOpposite.getESObject() == null)) {
			//	if (so == null) {
			//		so = new StringBuilder();
			//		so.append(indent + "  implicit-opposite");
			//	}
			//	so.append("\n\t" + indent + NameUtil.debugSimpleName(partialOpposite) + " : " + partialOpposite);
				implicitOppositeMerges.add(partialProperties);
				break;
			}
		}
		//	if (so != null) {
		//		implicitOppositeMerges.add(partialProperties);
		//		System.out.println(so.toString());
		//	}
	}

	private @NonNull List<@NonNull List<@NonNull Element>> gatherManyPartialElements(@NonNull List<@NonNull ? extends Element> partialParents, @NonNull EReference eReference) {
		EClass eContainingClass = eReference.getEContainingClass();
		assert eContainingClass != null;
		List<@NonNull List<@NonNull Element>> manyPartialElements = new ArrayList<>();
		for (@NonNull Element partialParent : partialParents) {
			EClass partialParentEClass = partialParent.eClass();
			assert partialParentEClass != null;
			if (eSuperClassHelper.isSubEClass(partialParentEClass, eContainingClass)) {
				@SuppressWarnings("unchecked")
				List<@NonNull Element> partialChildren = (@NonNull List<@NonNull Element>)partialParent.eGet(eReference);
				if (!partialChildren.isEmpty()) {
					manyPartialElements.add(partialChildren);
				}
			}
		}
		return manyPartialElements;
	}

	private @NonNull List<@NonNull Element> gatherSinglePartialElement(@NonNull List<@NonNull ? extends Element> partialParents, @NonNull EReference eReference) {
		EClass eContainingClass = eReference.getEContainingClass();
		assert eContainingClass != null;
		List<@NonNull Element> partialElements = new ArrayList<>();
		for (@NonNull Element partialParent : partialParents) {
			EClass partialParentEClass = partialParent.eClass();
			assert partialParentEClass != null;
			if (eSuperClassHelper.isSubEClass(partialParentEClass, eContainingClass)) {
				Element partialElement = (Element)partialParent.eGet(eReference);
				if (partialElement != null) {
					partialElements.add(partialElement);
				}
				else {
					assert !eReference.isRequired();
				}
			}
		}
		return partialElements;
	}

	public @Nullable Element getMergedElement(@NonNull Element partialElement) {
		return partialElement2mergedElement.get(partialElement);
	}

	public <E extends Element> @NonNull Iterable<@NonNull E> getPartialElements(@NonNull E mergedElement) {
		@SuppressWarnings("unchecked")
		MergeNode mergeNode = element2mergeNode.get(mergedElement);
		assert mergeNode != null;
		Iterable<@NonNull E> partialElements = (Iterable<@NonNull E>)mergeNode.getPartialElements();
		return partialElements;
	}

	public @Nullable List<@NonNull String> getProblemMessages() {
		return problemMessages;
	}

	/**
	 * Create and return a deep merge of partialElements returning the merged element and installing it promptly
	 * within mergedParentElements thereby ensuring that the each merged element has a valid containment ancestry.
	 */
	public <E extends org.eclipse.ocl.pivot.Package> /*@NonNull E*/void merge(@NonNull List<@NonNull ? super E> mergedParentElements, @NonNull List<@NonNull E> partialElements) {
		// Create and install root merged element
		/*@SuppressWarnings("unchecked")
		E mergedElement =*/
		EClass eClass = eSuperClassHelper.getCompatibleEClass(partialElements);
		MergeDescriptor mergeDescriptor = createMergeDescriptor(0, eClass, Collections.singletonList((List<@NonNull Element>)partialElements));
		MergeNode mergeNode = mergeOneOfMany(mergeDescriptor, mergedParentElements, partialElements);
	//	xxx mergeSuperClasses
	//	assert mergedOrphanage.assertConsistent();
		resolveImplicitOpposites();
		// Resolve references
	//	assert mergedOrphanage.assertConsistent();
		resolveAttributeSlots();
	//	assert mergedOrphanage.assertConsistent();
	//	resolveSuperClassesSlots(mergedElement);
	//	assert mergedOrphanage.assertConsistent();
		resolveReferenceSlots();
	//	assert mergedOrphanage.assertConsistent();
		resolvePossibleBidirectionalSlots();
	//	assert mergedOrphanage.assertConsistent();
	//	return mergedElement;
	}

	/**
	 * Traverse the partial containment trees to create a merged containment tree.
	 * Only the containment slots are resolved in this pass.
	 */
	private void mergeContainmentHierarchy(int depth, @NonNull MergeNode mergeNode) {
		@NonNull Element mergedParent = mergeNode.getMergedElement();
		@NonNull List<@NonNull ? extends Element> partialParents = mergeNode.getPartialElements();
		assert partialParents.size() >= 0;
		addMerge(depth, mergeNode);
		EClass parentEClass = mergedParent.eClass();
	//	assert parentEClass == eSuperClassHelper.getCompatibleEClass(partialParents);
		if (parentEClass == PivotPackage.Literals.PROPERTY) {
			@SuppressWarnings("unchecked") List<@NonNull Property> partialProperties = (List<@NonNull Property>)partialParents;
			gatherImplicitOppositeMerges(partialProperties);
		}
		for (EStructuralFeature eStructuralFeature : parentEClass.getEAllStructuralFeatures()) {
		//	System.out.println(indent + "  " + eStructuralFeature.getEContainingClass().getName() + "::" + eStructuralFeature.getName());
			if (eStructuralFeature instanceof EReference) {
				EReference eReference = (EReference)eStructuralFeature;
				eAssociationHelper.add(eReference);
				if ("oclContainer".equals(eReference.getName())) {
					getClass();		// XXX
				}
				if (eReference.isContainment() && !eReference.isDerived() && !eReference.isTransient() && !eReference.isVolatile()) {
					mergeElements(depth, mergedParent, partialParents, eReference);
				}
			}
		}
	}

	protected void mergeElements(int depth, @NonNull Element mergedParent, @NonNull List<@NonNull ? extends Element> partialParents, @NonNull EReference eReference) {
		if (eReference.isMany()) {
			List<@NonNull List<@NonNull Element>> manyPartialElements = gatherManyPartialElements(partialParents, eReference);
			if (Iterables.size(manyPartialElements) <= 0) {
				return;
			}
			Iterable<@NonNull Element> allPartialElements = Iterables.concat(manyPartialElements);
			assert allPartialElements != null;
			if (Iterables.size(allPartialElements) <= 0) {
				return;
			}
			EClass childrenEClass = eSuperClassHelper.getCommonEClass(allPartialElements);
			MergeDescriptor mergeDescriptor = createMergeDescriptor(depth, childrenEClass, manyPartialElements);
			List<@NonNull Element> mergedChildren = (List<@NonNull Element>)mergedParent.eGet(eReference);
			assert mergedChildren != null;
			mergeMany(mergeDescriptor, mergedChildren);
		}
		else {
			List<@NonNull Element> partialElements = gatherSinglePartialElement(partialParents, eReference);
			if (partialElements.size() > 0) {
				EClass childrenEClass = eSuperClassHelper.getCommonEClass(partialElements);
				MergeDescriptor mergeDescriptor = createMergeDescriptor(depth, childrenEClass, Collections.singletonList(partialElements));
				mergeSingle(mergeDescriptor, mergedParent, eReference, partialElements);
			}
		}
	}

	private <E extends Element> void mergeMany(@NonNull MergeDescriptor mergeDescriptor, @NonNull List<@NonNull Element> mergedChildren) {
		List<@NonNull List<@NonNull Element>> manyListsOfPartialElements = mergeDescriptor.partition();
		for (@NonNull List<@NonNull Element> partialElements : manyListsOfPartialElements) {
			mergeOneOfMany(mergeDescriptor, mergedChildren, partialElements);
		}
	}

	private <E extends Element> @NonNull MergeNode mergeOneOfMany(@NonNull MergeDescriptor mergeDescriptor, @NonNull List<@NonNull ? super E> mergedChildren, @NonNull List<@NonNull E> partialElements) {
		MergeNode mergeNode = createMergeNode(mergeDescriptor, partialElements);
		mergeContainmentHierarchy(mergeDescriptor.getDepth()+1, mergeNode);
		mergedChildren.add((E)mergeNode.getMergedElement());
		return mergeNode;
	}

	private void mergeSingle(@NonNull MergeDescriptor mergeDescriptor, @NonNull Element mergedParent, @NonNull EReference eReference, @NonNull List<@NonNull Element> partialElements) {
		if (!partialElements.isEmpty()) {
			MergeNode mergeNode = createMergeNode(mergeDescriptor, partialElements);
			mergeContainmentHierarchy(mergeDescriptor.getDepth()+1, mergeNode);
			mergedParent.eSet(eReference, mergeNode.getMergedElement());
		}
		else {
			mergedParent.eSet(eReference, null);
		}
	}

	//	private void mergeModels(@NonNull Model mergedModel, @NonNull Iterable<@NonNull Model> partialModels) {
	//		mergeElements(mergedModel, partialModels);
	//	}

	/**
	 * Traverse the merged containment tree to resolve all attribute slots.
	 */
	private <E extends Element> void resolveAttributeSlots(/*@NonNull E mergedParent, @NonNull Iterable<@NonNull E> partialParents*/) {
		List<@NonNull Element> keyList = new ArrayList<>(element2mergeNode.keySet());
		Collections.sort(keyList, NameUtil.TO_STRING_COMPARATOR);			// Make execution predictable
		for (@NonNull Element mergedParent : keyList) { //mergedElement2partialElements.keySet()) {
			MergeNode mergeNode = element2mergeNode.get(mergedParent);
			assert mergeNode != null;
			List<@NonNull ? extends Element> partialParents = mergeNode.getPartialElements();
			assert partialParents != null;
			EClass eClass = mergedParent.eClass();
			assert eClass != null;
			for (EStructuralFeature eStructuralFeature : eClass.getEAllStructuralFeatures()) {
				assert eStructuralFeature != null;
				if ((eStructuralFeature instanceof EAttribute) && !eStructuralFeature.isDerived() && !eStructuralFeature.isTransient() && !eStructuralFeature.isVolatile()) {
					EAttribute eAttribute = (EAttribute)eStructuralFeature;
					if (eStructuralFeature.isMany()) {
						resolveManyAttributesSlot(mergedParent, eAttribute, partialParents);
					}
					else {
						resolveSingleAttributeSlot(mergedParent, eAttribute, partialParents);
					}
				}
			}
		}
	}

	private @Nullable Boolean resolveBooleanValue(@NonNull Element mergedParent, @NonNull EAttribute eAttribute, @NonNull List<@Nullable Object> partialValues) {
		boolean isRequired = eAttribute.isRequired();
		Boolean mergedValue = null;
		for (@Nullable Object partialValue : partialValues) {
			if (partialValue == Boolean.TRUE) {
				mergedValue = Boolean.TRUE;
			}
			else if ((partialValue == Boolean.FALSE) && (mergedValue == null)) {
				mergedValue = Boolean.FALSE;
			}
		}
		if (isRequired && (mergedValue == null)) {
			mergedValue = eAttribute.getDefaultValue() == Boolean.TRUE;
		}
		return mergedValue;
	}

	private void resolveImplicitOpposites() {
		for (@NonNull List<@NonNull Property> partialPropertiesWithImplicitOpposite : implicitOppositeMerges) {
			if ("pivotas::State::ownedDoActivity".equals(partialPropertiesWithImplicitOpposite.get(0).getOpposite().toString())) {
				getClass();		// XXX
			}
			org.eclipse.ocl.pivot.Class mergedOppositeClass = null;
			List<@NonNull Property> partialOpposites = new ArrayList<>();
			Property mergedChild = PivotFactory.eINSTANCE.createProperty();
		//	StringBuilder s = new StringBuilder();
		//	s.append("resolveImplicitOpposites " + NameUtil.debugSimpleName(mergedChild)); // + " for " + eReference.getEContainingClass().getName() + "::" + eReference.getName());
			for (@NonNull Property partialPropertyWithImplicitOpposite : partialPropertiesWithImplicitOpposite) {
				Property partialOpposite = partialPropertyWithImplicitOpposite.getOpposite();
				assert partialOpposite != null;
				partialOpposites.add(partialOpposite);
			//	s.append("\n\t" + NameUtil.debugSimpleName(partialOpposite) + " : " + partialOpposite);
			//	s.append("\n\t\t~" + NameUtil.debugSimpleName(partialPropertyWithImplicitOpposite) + " : " + partialPropertyWithImplicitOpposite);
			//	assert containmentProperty.isIsComposite();
			//	assert !containerProperty.isIsComposite();
				org.eclipse.ocl.pivot.Class oppositeClass = PivotUtil.getOwningClass(partialOpposite);
				Element mergedOppositeElement = partialElement2mergedElement.get(oppositeClass);
				assert mergedOppositeElement instanceof org.eclipse.ocl.pivot.Class;
				if (mergedOppositeClass == null) {
					mergedOppositeClass = (org.eclipse.ocl.pivot.Class)mergedOppositeElement;
				}
				else {
					assert mergedOppositeClass == mergedOppositeElement;
				}
			}
		//	System.out.println(s.toString());
			assert mergedOppositeClass != null;
			mergedOppositeClass.getOwnedProperties().add(mergedChild);
			addMerge(1, new MergeNode(mergedChild, partialOpposites));
		}
	}

	private void resolveManyAttributesSlot(@NonNull Element mergedParent, @NonNull EAttribute eAttribute, @NonNull List<@NonNull ? extends Element> partialParents) {
	//	System.out.println("resolveManyAttributesSlot " + NameUtil.debugSimpleName(mergedParent) + "." + eAttribute.getName() + " : " + partialParents);
		assert eAttribute.isMany();
		@SuppressWarnings("unchecked")
		List<@Nullable Object> mergedValues = (List<@Nullable Object>)mergedParent.eGet(eAttribute);
		if (partialParents.size() == 1) {
			Element partialParent = partialParents.get(0);
			@SuppressWarnings("unchecked")
			List<@Nullable Object> partialValues = (@NonNull List<@Nullable Object>)partialParent.eGet(eAttribute);
			mergedValues.addAll(partialValues);
		}
		else {
			for (@NonNull Element partialParent : partialParents) {
				@SuppressWarnings("unchecked")
				List<@Nullable Object> partialValues = (@NonNull List<@Nullable Object>)partialParent.eGet(eAttribute);
				for (@Nullable Object partialValue : partialValues) {
					if (!mergedValues.contains(partialValue)) {
						mergedValues.add(partialValue);
					}
				}
			}
		}
	}

	private void resolveManyReferencesSlot(@NonNull Element mergedParent, @NonNull EReference eReference, @NonNull List<@NonNull ? extends Element> partialParents) {
	//	System.out.println("resolveManyReferencesSlot " + NameUtil.debugSimpleName(mergedParent) + "." + eReference.getName() + " : " + partialParents);
	//	if (eReference == PivotPackage.Literals.CLASS__SUPER_CLASSES) {
	//		System.out.println("resolveManyReferencesSlot " + NameUtil.debugSimpleName(mergedParent) + "" + mergedParent + "." + eReference.getName() + " : " + partialParents);
	//	}
		assert !eReference.isContainment();
		assert eReference.isMany();
		@SuppressWarnings("unchecked")
		List<@NonNull Element> mergedChildren = (List<@NonNull Element>)mergedParent.eGet(eReference);
		for (@NonNull Element partialParent : partialParents) {
			@SuppressWarnings("unchecked")
			List<@NonNull Element> partialChildren = (@NonNull List<@NonNull Element>)partialParent.eGet(eReference);
			if (!partialChildren.isEmpty()) {
				for (@NonNull Element partialChild : partialChildren) {
					Element resolvedChild = partialChild.accept(resolveVisitor);
					if ((resolvedChild != null) && !mergedChildren.contains(resolvedChild)) {
						mergedChildren.add(resolvedChild);
					//	if (eReference == PivotPackage.Literals.CLASS__SUPER_CLASSES) {
					//		System.out.println("\t" + NameUtil.debugSimpleName(resolvedChild) +" : " + resolvedChild);
					//	}
					}
				}
			}
		}
	}

	private void resolvePossibleBidirectionalSlot(@NonNull Element mergedSource, @NonNull EReference eReference, @NonNull List<@NonNull ? extends Element> partialParents) {
	/*	StringBuilder s = new StringBuilder();
		s.append("resolvePossibleBidirectionalSlot " + NameUtil.debugSimpleName(mergedSource) + "." + eReference.getName());
		for (@NonNull Element partialSource : partialParents) {
			Element partialTarget = (Element)partialSource.eGet(eReference);
			s.append("\n\t" + NameUtil.debugSimpleName(partialSource) + " : " + partialSource + " => " + NameUtil.debugSimpleName(partialTarget) + " : " + partialTarget);
		}
		System.out.println(s.toString()); */
		assert !eReference.isContainment();
		assert !eReference.isMany();
		assert eReference.getEOpposite() == null;
		assert eReference.getEType() == eReference.getEContainingClass();
		@Nullable Element mergedTarget = null;
		if (partialParents.size() == 1) {
			@NonNull Element partialSource = partialParents.get(0);
			Element partialTarget = (Element)partialSource.eGet(eReference);
			if (partialTarget != null) {
				mergedTarget = partialTarget.accept(resolveVisitor);
			//	if (mergedTarget == null) {
			//		mergedTarget = partialTarget;
			//	}
			}
		}
		else {
			if (partialParents.toString().contains("OclAny::oclAsType")) {
				getClass();		// XXX
			}
			for (@NonNull Element partialSource : partialParents) {
				Element partialTarget = (Element)partialSource.eGet(eReference);
				Element resolvedSource = partialSource.accept(resolveVisitor);
			//	if (resolvedSource == null) {
			//		resolvedSource = partialSource;
			//	}
				Element resolvedTarget = partialTarget != null ? (Element)partialTarget.accept(resolveVisitor) : null;
				if (resolvedTarget == null) {
					resolvedTarget = partialTarget;
				}
			//	if (resolvedTarget != null) {
					if (mergedTarget == null) {
						mergedTarget = resolvedTarget;
					}
					else if (mergedTarget != resolvedTarget) {
					/*	if (eReference.getEOpposite() == null)	{		// Pseudo-Association where Ecore prohibits EReference cycle
							resolvedTarget = (Element)partialSource.accept(resolveVisitor);
							if (resolvedTarget == null) {
								resolvedTarget = partialSource;
							}
						} */
					//	throw new IllegalStateException("Merger:populate inconsistent \"" + resolvedTarget + "\" and \"" + mergedTarget + "\"");
						addProblem("Merger:resolveSingleReferenceSlot inconsistent \"" + resolvedTarget + "\" and \"" + mergedTarget + "\"");
					}
			//	}
			}
		}
		mergedSource.eSet(eReference, mergedTarget);
	}

	/**
	 * Traverse the merged containment tree to resolve all reference slots (other than containments).
	 */
	private <E extends Element> void resolvePossibleBidirectionalSlots(/*@NonNull E mergedParent, @NonNull Iterable<@NonNull E> partialParents*/) {
		for (@NonNull Element mergedParent : element2mergeNode.keySet()) {
			MergeNode mergeNode = element2mergeNode.get(mergedParent);
			assert mergeNode != null;
			List<@NonNull ? extends Element> partialParents = mergeNode.getPartialElements();
			assert partialParents != null;
			EClass eClass = mergedParent.eClass();
			assert eClass != null;
			for (EStructuralFeature eStructuralFeature : eClass.getEAllStructuralFeatures()) {
				assert eStructuralFeature != null;
				if ((eStructuralFeature instanceof EReference) && !eStructuralFeature.isDerived() && !eStructuralFeature.isTransient() && !eStructuralFeature.isVolatile()) {
					EReference eReference = (EReference)eStructuralFeature;
					if (!eReference.isContainment() && !eReference.isContainer() && (eReference.getEType() == eReference.getEContainingClass())) {
						EAssociation eAssociation = eAssociationHelper.get(eReference);
						if ((eAssociation != null) && (eReference.getEOpposite() == null)) {
							assert !eStructuralFeature.isMany();
							resolvePossibleBidirectionalSlot(mergedParent, eReference,  partialParents);
						}
					}
				}
			}
		}
	}

	/**
	 * Traverse the merged containment tree to resolve all reference slots (other than containments).
	 */
	private <E extends Element> void resolveReferenceSlots(/*@NonNull E mergedParent, @NonNull Iterable<@NonNull E> partialParents*/) {
		List<@NonNull Element> mergedElements = new ArrayList<>(element2mergeNode.keySet());
		MergedElementComparator comparator = new MergedElementComparator();
		SuperClassHelper superClassHelper = new SuperClassHelper();
	//	assert ClassUtil.validateGeneralContract(mergedElements, comparator);	// XXX
		Collections.sort(mergedElements, comparator);	// Must have shallowest inheritance first, determinism is good // XXX intermittent bad sort crash
		for (@NonNull Element mergedParent : mergedElements) {
			MergeNode mergeNode = element2mergeNode.get(mergedParent);
			assert mergeNode != null;
			List<@NonNull ? extends Element> partialParents = mergeNode.getPartialElements();
			assert partialParents != null;
			if ("OclAny::oclAsType(TT)(oclAsType.TT[1]) : oclAsType.TT[1]".equals(partialParents.get(0).toString())) {
				getClass();		// XXX
			}
			EClass eClass = mergedParent.eClass();
			assert eClass != null;
			for (EStructuralFeature eStructuralFeature : eClass.getEAllStructuralFeatures()) {
				assert eStructuralFeature != null;
				if ((eStructuralFeature instanceof EReference) && !eStructuralFeature.isDerived() && !eStructuralFeature.isTransient() && !eStructuralFeature.isVolatile()) {
					EReference eReference = (EReference)eStructuralFeature;
					if (!eReference.isContainment() && !eReference.isContainer()) {
						EAssociation eAssociation = eAssociationHelper.get(eReference);
						if ((eAssociation == null) || eAssociation.isFirst(eReference)) {
							if (eStructuralFeature.isMany()) {
								resolveManyReferencesSlot(mergedParent, eReference, partialParents);
							}
							else {
								resolveSingleReferenceSlot(mergedParent, eReference, partialParents);
							}
						}
					}
					if (eStructuralFeature == PivotPackage.Literals.CLASS__SUPER_CLASSES) {
						resolveSuperClasses(superClassHelper, (org.eclipse.ocl.pivot.Class)mergedParent);
					}
				}
			}
		}
	}

	private void resolveSingleAttributeSlot(@NonNull Element mergedParent, @NonNull EAttribute eAttribute, @NonNull List<@NonNull ? extends Element> partialParents) {
	//	System.out.println("resolveSingleAttributeSlot " + NameUtil.debugSimpleName(mergedParent) + "." + eAttribute.getName() + " : " + partialParents);
		assert !eAttribute.isMany();
		if (partialParents.size() == 1) {
			Element partialParent = partialParents.get(0);
			if (partialParent.eIsSet(eAttribute)) {
				Object mergedValue = partialParent.eGet(eAttribute);
				mergedParent.eSet(eAttribute, mergedValue);
			}
		}
		else {
			List<@Nullable Object> partialValues = new ArrayList<>();
			for (@NonNull Element partialParent : partialParents) {
				if (partialParent.eIsSet(eAttribute)) {
					@Nullable Object partialValue = partialParent.eGet(eAttribute);
					partialValues.add(partialValue);
				}
			}
			if (partialValues.size() > 0) {
				@Nullable Object mergedValue;
				if (partialValues.size() == 1) {
					mergedValue = partialValues.get(0);
				}
				else {
					mergedValue = resolveValue(mergedParent, eAttribute, partialValues);
				}
				mergedParent.eSet(eAttribute, mergedValue);
			}
		}
	}

	private void resolveSingleReferenceSlot(@NonNull Element mergedParent, @NonNull EReference eReference, @NonNull List<@NonNull ? extends Element> partialParents) {
	/*	StringBuilder s = new StringBuilder();
		s.append("resolveSingleReferenceSlot " + NameUtil.debugSimpleName(mergedParent) + "." + eReference.getName());
		for (@NonNull Element partialSource : partialParents) {
			Element partialTarget = (Element)partialSource.eGet(eReference);
			s.append("\n\t" + NameUtil.debugSimpleName(partialSource) + " : " + partialSource + " => " + NameUtil.debugSimpleName(partialTarget) + " : " + partialTarget);
		}
		System.out.println(s.toString()); */
		assert !eReference.isContainment();
		assert !eReference.isMany();
		if ("opposite".equals(eReference.getName()) && "[MapType::keyType, pivot::MapType::keyType]".equals(partialParents.toString())) {
			getClass();		// XXX
		}
		if (partialParents.size() == 1) {
			@NonNull Element partialParent = partialParents.get(0);
			Element partialElement = (Element)partialParent.eGet(eReference);
			if (partialElement != null) {
				Element mergedElement = partialElement.accept(resolveVisitor);
			//	if (mergedElement == null) {
			//		mergedElement = partialElement;
			//	}
				mergedParent.eSet(eReference, mergedElement);
			}
		}
		else {
			@Nullable Element mergedTarget = null;
			for (@NonNull Element partialSource : partialParents) {
				Element resolvedTarget = null;
				Element partialTarget = (Element)partialSource.eGet(eReference);
				if (partialTarget != null) {
					resolvedTarget = partialTarget.accept(resolveVisitor);
				//	if (resolvedTarget == null) {
				//		resolvedTarget = partialTarget;
				//	}
				}
				if (resolvedTarget != null) {
					if (mergedTarget == null) {
						mergedTarget = resolvedTarget;
					}
					else if (mergedTarget != resolvedTarget) {
						if (resolvedTarget instanceof DataType) {
							org.eclipse.ocl.pivot.Class behavioralClass = ((DataType)resolvedTarget).getBehavioralClass();
							if (behavioralClass != null) {
								resolvedTarget = behavioralClass;
							}
						}
						if (mergedTarget != resolvedTarget) {
							if (mergedTarget instanceof DataType) {
								org.eclipse.ocl.pivot.Class behavioralClass = ((DataType)mergedTarget).getBehavioralClass();
								if (behavioralClass != null) {
									mergedTarget = ((DataType)mergedTarget).getBehavioralClass();
								}
							}
							if (mergedTarget != resolvedTarget) {
							//	throw new IllegalStateException("Merger:populate inconsistent \"" + resolvedTarget + "\" and \"" + mergedTarget + "\"");
								addProblem("Merger:resolveSingleReferenceSlot inconsistent \"" + resolvedTarget + "\" and \"" + mergedTarget + "\" for " + mergedParent + "." + eReference.getName());
							}
						}
					}
				}
			}
			mergedParent.eSet(eReference, mergedTarget);
		}
	}



	//	private void mergeModels(@NonNull Model mergedModel, @NonNull Iterable<@NonNull Model> partialModels) {
	//		mergeElements(mergedModel, partialModels);
	//	}

	private @Nullable String resolveStringValue(@NonNull Element mergedParent, @NonNull EAttribute eAttribute, @NonNull List<@Nullable Object> partialValues) {
		boolean isRequired = eAttribute.isRequired();
		String mergedValue = null;
		for (@Nullable Object partialValue : partialValues) {
			if (partialValue != null) {
				if (mergedValue == null) {
					mergedValue = (String)partialValue;
				}
				else if (partialValue.equals(mergedValue)) {
					;
				}
				else {
				//	throw new IllegalStateException("Merger:resolveStringValue mismatching \"" + partialValue + "\" and \"" + mergedValue + "\"");
					MergeNode mergeNode = element2mergeNode.get(mergedParent);
					addProblem("Merger:resolveStringValue mismatching " + eAttribute.getEContainingClass().getName() + "." + eAttribute.getName() + " \"" + partialValue + "\" and \"" + mergedValue + "\"");
				}
			}
		}
		if (isRequired && (mergedValue == null)) {
			mergedValue = eAttribute.getDefaultValueLiteral();
		}
		return mergedValue;
	}

	/**
	 * Traverse the merged containment tree to resolve all reference slots (other than containments).
	 *
	private <E extends Element> void resolveSuperClassesSlots(org.eclipse.ocl.pivot.@NonNull Package mergedPackage) {
		for (org.eclipse.ocl.pivot.@NonNull Class mergedClass : PivotUtil.getOwnedClasses(mergedPackage)) {
			List<org.eclipse.ocl.pivot.@NonNull Class> partialClasses = (List<org.eclipse.ocl.pivot.@NonNull Class>) mergedElement2partialElements.get(mergedClass);
			assert partialClasses != null;
			EClass eClass = mergedClass.eClass();
			assert eClass != null;
			resolveManyReferencesSlot(mergedClass, PivotPackage.Literals.CLASS__SUPER_CLASSES, partialClasses);
		}
	} */

	private void resolveSuperClasses(@NonNull SuperClassHelper superClassHelper, org.eclipse.ocl.pivot.@NonNull Class mergedClass) {
		EList<org.eclipse.ocl.pivot.@NonNull Class> mergedSuperClasses = (EList<org.eclipse.ocl.pivot.@NonNull Class>)mergedClass.getSuperClasses();
		if (mergedSuperClasses.size() > 1) {
			List<org.eclipse.ocl.pivot.@NonNull Class> superConcretes = new ArrayList<>();
			List<org.eclipse.ocl.pivot.@NonNull Class> superAbstracts = new ArrayList<>();
			List<org.eclipse.ocl.pivot.@NonNull Class> superInterfaces = new ArrayList<>();
			for (org.eclipse.ocl.pivot.@NonNull Class candidateSuperClass : mergedSuperClasses) {
				boolean isSuperClass = false;
				for (org.eclipse.ocl.pivot.@NonNull Class mergedSuperClass : mergedSuperClasses) {
					if ((mergedSuperClass != candidateSuperClass) && superClassHelper.isSubClass(mergedSuperClass, candidateSuperClass)) {
						isSuperClass = true;
						break;
					}
				}
				if (isSuperClass) {
					// redundant super class
				}
				else if (candidateSuperClass.isIsInterface()) {
					superInterfaces.add(candidateSuperClass);
				}
				else if (candidateSuperClass.isIsAbstract()) {
					superAbstracts.add(candidateSuperClass);
				}
				else {
					superConcretes.add(candidateSuperClass);
				}
			}
			List<org.eclipse.ocl.pivot.@NonNull Class> sortedSuperClasses = new ArrayList<>();
			sortedSuperClasses.addAll(superConcretes);		// sort by depth ??
			sortedSuperClasses.addAll(superAbstracts);
			sortedSuperClasses.addAll(superInterfaces);
			int index = 0;
			for (org.eclipse.ocl.pivot.@NonNull Class sortedSuperClass : sortedSuperClasses) {
				mergedSuperClasses.move(index++, sortedSuperClass);
			}
			while (index < mergedSuperClasses.size()) {
				mergedSuperClasses.remove(index);
			}
		}
	}

	private @Nullable Object resolveValue(@NonNull Element mergedParent, @NonNull EAttribute eAttribute, @NonNull List<@Nullable Object> partialValues) {
		EDataType eType = eAttribute.getEAttributeType();
		if ((eType == PivotPackage.Literals.BOOLEAN) /*|| (eType == PivotPackage.Literals.EBOOLEAN)*/ || (eType == EcorePackage.Literals.EBOOLEAN) || (eType == EcorePackage.Literals.EBOOLEAN_OBJECT)) {
			return resolveBooleanValue(mergedParent, eAttribute, partialValues);
		}
		else if ((eType == PivotPackage.Literals.STRING) || (eType == EcorePackage.Literals.ESTRING)) {
			return resolveStringValue(mergedParent, eAttribute, partialValues);
		}
		else {
			throw new UnsupportedOperationException(eType.getName());

		}
	}
}
