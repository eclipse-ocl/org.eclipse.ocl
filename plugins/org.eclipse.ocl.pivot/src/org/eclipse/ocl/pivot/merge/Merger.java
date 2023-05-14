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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.utilities.Orphanage;
import org.eclipse.ocl.pivot.merge.EAssociationHelper.EAssociation;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

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
	protected final @NonNull EnvironmentFactory environmentFactory;
	protected final @NonNull Orphanage mergedOrphanage;
	protected final @NonNull ESuperClassHelper eSuperClassHelper;
	protected final @NonNull EAssociationHelper eAssociationHelper;
	protected final @NonNull MergerGroupVisitor groupVisitor;
	protected final @NonNull MergerResolveVisitor resolveVisitor;

	/**
	 * The lists of incoming partial elements to  be grouped to support a many-child.
	 */
	private @NonNull Map<@NonNull Element, @NonNull ListOfList<@NonNull Element>> protoElement2partialElements = new HashMap<>();

	/**
	 * The outgoing merged element created for each incoming partial element.
	 */
	private @NonNull Map<@NonNull Element, @NonNull Element> partialElement2mergedElement = new HashMap<>();

	/**
	 * The incoming partial elements from which each outgoing merged element was created
	 */
	private @NonNull Map<@NonNull Element, @NonNull List<@NonNull ? extends Element>> mergedElement2partialElements = new HashMap<>();

	/**
	 * The implicit containment opposites to be merged to complete the containment hierarchy.
	 */
	private @NonNull List<@NonNull List<@NonNull Property>> implicitOppositeMerges = new ArrayList<>();

	public Merger(@NonNull EnvironmentFactory environmentFactory, @NonNull Orphanage mergedOrphanage) {
		this.environmentFactory = environmentFactory;
		this.mergedOrphanage = mergedOrphanage;
		this.eSuperClassHelper = new ESuperClassHelper();
		this.eAssociationHelper = new EAssociationHelper();
		this.groupVisitor = createGroupVisitor();
		this.resolveVisitor = createResolveVisitorVisitor();
	}

	private void addMerge(@NonNull String indent, @NonNull Element mergedParent, @NonNull List<@NonNull ? extends Element> partialParents) {
		StringBuilder s = new StringBuilder();
		s.append(indent + "addMerge " + NameUtil.debugSimpleName(mergedParent));
		for (@NonNull Element partialParent : partialParents) {
			s.append("\n\t" + indent + NameUtil.debugSimpleName(partialParent) + " : " + partialParent);
		}
		System.out.println(s.toString());
		if ((partialParents.get(0) instanceof Nameable) && "ownedExit".equals(((Nameable)partialParents.get(0)).getName())) {
			getClass();		// XXX
		}
		mergedElement2partialElements.put(mergedParent, partialParents);
		for (@NonNull Element partialParent : partialParents) {
			partialElement2mergedElement.put(partialParent, mergedParent);
		}
	}

	protected @NonNull MergerGroupVisitor createGroupVisitor() {
		return new MergerGroupVisitor(this);
	}

	protected @NonNull MergerResolveVisitor createResolveVisitorVisitor() {
		return new MergerResolveVisitor(this, mergedOrphanage);
	}

	public @Nullable Element getMergedElement(@NonNull Element partialElement) {
		return partialElement2mergedElement.get(partialElement);
	}

	public <E extends Element> @NonNull Iterable<@NonNull E> getPartialElements(@NonNull E mergedElement) {
		@SuppressWarnings("unchecked")
		Iterable<@NonNull E> partialElements = (Iterable<@NonNull E>)mergedElement2partialElements.get(mergedElement);
		assert partialElements != null;
		return partialElements;
	}

	public <E extends Element> @NonNull ListOfList<@NonNull E> getUngroupedPartialElements(@NonNull E protoElement) {
		@SuppressWarnings("unchecked")
		ListOfList<@NonNull E> ungroupedPartialElements = (ListOfList<@NonNull E>)protoElement2partialElements.get(protoElement);
		assert ungroupedPartialElements != null;
		return ungroupedPartialElements;
	}

	/**
	 * Create and return a deep merge of partialElements returning the merged element and installing it promptly
	 * within mergedParentElements thereby ensuring that the each merged element has a valid containment ancestry.
	 */
	public <E extends Element> @NonNull E merge(@NonNull List<@NonNull E> mergedParentElements, @NonNull List<@NonNull E> partialElements) {
		// Create and install root merged element
		EClass eClass = eSuperClassHelper.getCompatibleEClass(partialElements);
		@SuppressWarnings("unchecked")
		E mergedElement = (E)PivotFactory.eINSTANCE.create(eClass);
		mergedParentElements.add(mergedElement);
		// Create and install descendant merged elements
		mergeContainmentHierarchy("", mergedElement, partialElements);
		resolveImplicitOpposites();
		// Resolve references
		resolveAttributeSlots();
		resolveReferenceSlots();
		resolvePossibleBidirectionalSlots();
		return mergedElement;
	}

	/**
	 * Traverse the partial containment trees to create a merged containment tree.
	 * Only the containment slots are resolved in this pass.
	 */
	private void mergeContainmentHierarchy(@NonNull String indent, @NonNull Element mergedParent, @NonNull List<@NonNull ? extends Element> partialParents) {
		addMerge(indent, mergedParent, partialParents);
		EClass parentEClass = mergedParent.eClass();
		assert parentEClass == eSuperClassHelper.getCompatibleEClass(partialParents);
		if (parentEClass == PivotPackage.Literals.PROPERTY) {
			StringBuilder so = null;
			@SuppressWarnings("unchecked")
			List<@NonNull Property> partialProperties = (List<@NonNull Property>)partialParents;
			for (@NonNull Property partialProperty : partialProperties) {
				Property partialOpposite = partialProperty.getOpposite();
				if ((partialOpposite != null) && (partialOpposite.getESObject() == null)) {
					if (so == null) {
						so = new StringBuilder();
						so.append(indent + "  implicit-opposite");
					}
					so.append("\n\t" + indent + NameUtil.debugSimpleName(partialOpposite) + " : " + partialOpposite);
					break;
				}
			}
			if (so != null) {
				implicitOppositeMerges.add(partialProperties);
				System.out.println(so.toString());
			}
		}
		for (EStructuralFeature eStructuralFeature : parentEClass.getEAllStructuralFeatures()) {
			System.out.println(indent + "  " + eStructuralFeature.getEContainingClass().getName() + "::" + eStructuralFeature.getName());
			EClass eContainingClass = eStructuralFeature.getEContainingClass();
			assert eContainingClass != null;
			if (eStructuralFeature instanceof EReference) {
				EReference eReference = (EReference)eStructuralFeature;
				eAssociationHelper.add(eReference);
				if (eReference.isContainment() && !eReference.isDerived() && !eReference.isTransient() && !eReference.isVolatile()) {
					if (eReference.isMany()) {
						@SuppressWarnings("unchecked")
						List<@NonNull Element> mergedChildren = (List<@NonNull Element>)mergedParent.eGet(eReference);
						ListOfList<@NonNull Element> ungroupedPartialChildren = new ListOfList<>();
						for (@NonNull Element partialParent : partialParents) {
							EClass partialParentEClass = partialParent.eClass();
							assert partialParentEClass != null;
							if (eSuperClassHelper.isSubEClass(partialParentEClass, eContainingClass)) {
								@SuppressWarnings("unchecked")
								List<@NonNull Element> partialChildren = (@NonNull List<@NonNull Element>)partialParent.eGet(eReference);
								if (!partialChildren.isEmpty()) {
									ungroupedPartialChildren.add(partialChildren);
								}
							}
						}
						Iterable<@NonNull Element> allPartialChildren = ungroupedPartialChildren.getInnerIterable();
						if (!Iterables.isEmpty(allPartialChildren)) {
							if ("ownedComments".equals(eReference.getName())) {
								getClass();		// XXX
							}
							EClass protoChildEClass = eSuperClassHelper.getCommonEClass(allPartialChildren);
							Element protoChildElement = (Element)PivotFactory.eINSTANCE.create(protoChildEClass);
							protoElement2partialElements.put(protoChildElement, ungroupedPartialChildren);
							ListOfList<@NonNull Element> groupedPartialChildren = protoChildElement.accept(groupVisitor);
							for (@NonNull List<@NonNull Element> partialChildren : groupedPartialChildren.getOuterIterable()) {
								EClass childEClass = eSuperClassHelper.getCompatibleEClass(partialChildren);
								Element mergedChild = (Element)PivotFactory.eINSTANCE.create(childEClass);
								mergedChildren.add(mergedChild);
								mergeContainmentHierarchy(indent + "\t", mergedChild, partialChildren);
							}
						}
					}
					else {
						List<@Nullable Element> partialChildren = new ArrayList<>();
						for (@Nullable Element partialParent : partialParents) {
							EClass partialParentEClass = partialParent.eClass();
							assert partialParentEClass != null;
							if (eSuperClassHelper.isSubEClass(partialParentEClass, eContainingClass)) {
								Element partialChild = (Element)partialParent.eGet(eReference);
								if (partialChild != null) {
									partialChildren.add(partialChild);
								}
								else {
									assert !eReference.isRequired();
								}
							}
						}
						if (!partialChildren.isEmpty()) {
							EClass childEClass = eSuperClassHelper.getCompatibleEClass(partialChildren);
							Element mergedChild = (Element)PivotFactory.eINSTANCE.create(childEClass);
							mergedParent.eSet(eReference, mergedChild);
							mergeContainmentHierarchy(indent + "\t", mergedChild, partialChildren);
						}
					}
				}
			}
		}
	}





	//	private void mergeModels(@NonNull Model mergedModel, @NonNull Iterable<@NonNull Model> partialModels) {
	//		mergeElements(mergedModel, partialModels);
	//	}



//	public <E extends Element> void putPartialElements(@NonNull E mergedElement, @NonNull List<@NonNull E> partialElements) {
//		@SuppressWarnings("unchecked")
//		List<@NonNull Element> castPartialElements = (List<@NonNull Element>)partialElements;
//		mergedElement2partialElements.put(mergedElement, castPartialElements);
//	}

	/**
	 * Traverse the merged containment tree to resolve all attribute slots.
	 */
	private <E extends Element> void resolveAttributeSlots(/*@NonNull E mergedParent, @NonNull Iterable<@NonNull E> partialParents*/) {
	//	List<@NonNull Element> keyList = new ArrayList<>(mergedElement2partialElements.keySet());
	//	Collections.sort(keyList, NameUtil.TO_STRING_COMPARATOR);			// Make execution predictable
		for (@NonNull Element mergedParent : mergedElement2partialElements.keySet()) {
			List<@NonNull ? extends Element> partialParents = mergedElement2partialElements.get(mergedParent);
			assert partialParents != null;
			EClass eClass = mergedParent.eClass();
			assert eClass != null;
			for (EStructuralFeature eStructuralFeature : eClass.getEAllStructuralFeatures()) {
				assert eStructuralFeature != null;
				if ((eStructuralFeature instanceof EAttribute) && !eStructuralFeature.isDerived() && !eStructuralFeature.isTransient() && !eStructuralFeature.isVolatile()) {
					EAttribute eAttribute = (EAttribute)eStructuralFeature;
					if (eStructuralFeature.isMany()) {
						resolveManyAttributesSlot(mergedParent, eAttribute,  partialParents);
					}
					else {
						resolveSingleAttributeSlot(mergedParent, eAttribute,  partialParents);
					}
				}
			}
		}
	}

	private @Nullable Boolean resolveBooleanValue(@NonNull EAttribute eAttribute, @NonNull List<@Nullable Object> partialValues) {
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
			StringBuilder s = new StringBuilder();
			s.append("resolveImplicitOpposites " + NameUtil.debugSimpleName(mergedChild)); // + " for " + eReference.getEContainingClass().getName() + "::" + eReference.getName());
			for (@NonNull Property partialPropertyWithImplicitOpposite : partialPropertiesWithImplicitOpposite) {
				Property partialOpposite = partialPropertyWithImplicitOpposite.getOpposite();
				assert partialOpposite != null;
				partialOpposites.add(partialOpposite);
				s.append("\n\t" + NameUtil.debugSimpleName(partialOpposite) + " : " + partialOpposite);
				s.append("\n\t\t~" + NameUtil.debugSimpleName(partialPropertyWithImplicitOpposite) + " : " + partialPropertyWithImplicitOpposite);
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
			System.out.println(s.toString());
			assert mergedOppositeClass != null;
			mergedOppositeClass.getOwnedProperties().add(mergedChild);
			addMerge("\t", mergedChild, partialOpposites);
		//	mergedParent.eSet(eReference, mergedChild);
		//	mergeContainmentHierarchy(indent + "\t", mergedChild, oppositeProperties, false);

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
			ListOfList<@Nullable Object> ungroupedPartialValues = new ListOfList<>();
			for (@NonNull Element partialParent : partialParents) {
				@SuppressWarnings("unchecked")
				List<@Nullable Object> partialValues = (@NonNull List<@Nullable Object>)partialParent.eGet(eAttribute);
				if (!partialValues.isEmpty()) {
					ungroupedPartialValues.add(partialValues);
				}
			}
		//	Iterable<@Nullable Object> allPartialValues = ungroupedPartialValues.getInnerIterable();
			// pick all first group and any novel further group elements (?? Precedence needs insertion ??)
		/*	if (!Iterables.isEmpty(allPartialValues)) {
				EClass protoChildEClass = eSuperClassHelper.getCommonEClass(allPartialChildren);
				Element protoChildElement = (Element)PivotFactory.eINSTANCE.create(protoChildEClass);
				protoElement2partialElements.put(protoChildElement, ungroupedPartialChildren);
				ListOfList<@NonNull Element> groupedPartialChildren = protoChildElement.accept(groupVisitor);
				for (@NonNull Iterable<@NonNull Element> partialChildren : groupedPartialChildren.getOuterIterable()) {
					EClass childEClass = eSuperClassHelper.getCommonEClass(partialChildren);
					Element mergedChild = (Element)PivotFactory.eINSTANCE.create(childEClass);
					mergedChildren.add(mergedChild);
					addPartialElements(mergedChild, partialChildren);
				}
			}
			mergedChildren.add(partialValues); */
			throw new UnsupportedOperationException();		// XXX
		}
	}

	private void resolveManyReferencesSlot(@NonNull Element mergedParent, @NonNull EReference eReference, @NonNull List<@NonNull ? extends Element> partialParents) {
	//	System.out.println("resolveManyReferencesSlot " + NameUtil.debugSimpleName(mergedParent) + "." + eReference.getName() + " : " + partialParents);
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
		if (partialParents.size() == 1) {
			@NonNull Element partialSource = partialParents.get(0);
			Element partialTarget = (Element)partialSource.eGet(eReference);
			if (partialTarget != null) {
				Element mergedTarget = partialTarget.accept(resolveVisitor);
				if (mergedTarget == null) {
					mergedTarget = partialTarget;
				}
				mergedSource.eSet(eReference, mergedTarget);
			}
		}
		else {
			@Nullable Element mergedTarget = null;
			if (partialParents.toString().contains("CollectionType::elementType")) {
				getClass();		// XXX
			}
			for (@NonNull Element partialSource : partialParents) {
				Element partialTarget = (Element)partialSource.eGet(eReference);
				Element resolvedTarget = partialSource.accept(resolveVisitor);
				Element resolvedTarget2 = partialTarget != null ? (Element)partialTarget.accept(resolveVisitor) : null;
				if (resolvedTarget == null) {
					resolvedTarget = partialSource;
				}
				if (partialTarget != null) {
					resolvedTarget = partialTarget.accept(resolveVisitor);
					if (resolvedTarget == null) {
						resolvedTarget = partialTarget;
					}
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
					//	throw new IllegalStateException("Merge:populate inconsistent \"" + resolvedTarget + "\" and \"" + mergedTarget + "\"");
						System.err.println("Merge:resolveSingleReferenceSlot inconsistent \"" + resolvedTarget + "\" and \"" + mergedTarget + "\"");
					}
			//	}
			}
			mergedSource.eSet(eReference, mergedTarget);
		}
	}

	/**
	 * Traverse the merged containment tree to resolve all reference slots (other than containments).
	 */
	private <E extends Element> void resolvePossibleBidirectionalSlots(/*@NonNull E mergedParent, @NonNull Iterable<@NonNull E> partialParents*/) {
		for (@NonNull Element mergedParent : mergedElement2partialElements.keySet()) {
			List<@NonNull ? extends Element> partialParents = mergedElement2partialElements.get(mergedParent);
			assert partialParents != null;
			EClass eClass = mergedParent.eClass();
			assert eClass != null;
			for (EStructuralFeature eStructuralFeature : eClass.getEAllStructuralFeatures()) {
				assert eStructuralFeature != null;
				if ((eStructuralFeature instanceof EReference) && !eStructuralFeature.isDerived() && !eStructuralFeature.isTransient() && !eStructuralFeature.isVolatile()) {
					EReference eReference = (EReference)eStructuralFeature;
					if (!eReference.isContainment() && !eReference.isContainer()) {
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
		for (@NonNull Element mergedParent : mergedElement2partialElements.keySet()) {
			List<@NonNull ? extends Element> partialParents = mergedElement2partialElements.get(mergedParent);
			assert partialParents != null;
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
								resolveManyReferencesSlot(mergedParent, eReference,  partialParents);
							}
							else {
								resolveSingleReferenceSlot(mergedParent, eReference,  partialParents);
							}
						}
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
					mergedValue = resolveValue(eAttribute, partialValues);
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
				if (mergedElement == null) {
					mergedElement = partialElement;
				}
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
					if (resolvedTarget == null) {
						resolvedTarget = partialTarget;
					}
				}
				if (resolvedTarget != null) {
					if (mergedTarget == null) {
						mergedTarget = resolvedTarget;
					}
					else if (mergedTarget != resolvedTarget) {
					//	throw new IllegalStateException("Merge:populate inconsistent \"" + resolvedTarget + "\" and \"" + mergedTarget + "\"");
						System.err.println("Merge:resolveSingleReferenceSlot inconsistent \"" + resolvedTarget + "\" and \"" + mergedTarget + "\"");
					}
				}
			}
			mergedParent.eSet(eReference, mergedTarget);
		}
	}



	//	private void mergeModels(@NonNull Model mergedModel, @NonNull Iterable<@NonNull Model> partialModels) {
	//		mergeElements(mergedModel, partialModels);
	//	}

	private @Nullable String resolveStringValue(@NonNull EAttribute eAttribute, @NonNull List<@Nullable Object> partialValues) {
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
				//	throw new IllegalStateException("Merger:mergeString mismatching \"" + partialValue + "\" and \"" + mergedValue + "\"");
					System.err.println("Merger:mergeString mismatching " + eAttribute.getEContainingClass().getName() + "." + eAttribute.getName() + " \"" + partialValue + "\" and \"" + mergedValue + "\"");
				}
			}
		}
		if (isRequired && (mergedValue == null)) {
			mergedValue = eAttribute.getDefaultValueLiteral();
		}
		return mergedValue;
	}

	private @Nullable Object resolveValue(@NonNull EAttribute eAttribute, @NonNull List<@Nullable Object> partialValues) {
		EDataType eType = eAttribute.getEAttributeType();
		if ((eType == PivotPackage.Literals.BOOLEAN) || (eType == EcorePackage.Literals.EBOOLEAN) || (eType == EcorePackage.Literals.EBOOLEAN_OBJECT)) {
			return resolveBooleanValue(eAttribute, partialValues);
		}
		else if ((eType == PivotPackage.Literals.STRING) || (eType == EcorePackage.Literals.ESTRING)) {
			return resolveStringValue(eAttribute, partialValues);
		}
		else {
			throw new UnsupportedOperationException(eType.getName());

		}
	}
}
