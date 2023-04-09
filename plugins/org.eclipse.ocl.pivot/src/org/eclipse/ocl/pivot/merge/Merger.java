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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorStandardLibrary;
import org.eclipse.ocl.pivot.internal.manager.PivotIdResolver;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.merge.EAssociationHelper.EAssociation;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

import com.google.common.collect.Iterables;

/**
 * The Merger supports the creation of a merged model from one or more partial models.
 * <br>
 * The merging uses the Ecore metamodel to merge the actual models.
 */
public class Merger
{
	protected final @NonNull EnvironmentFactory environmentFactory;
	protected final @NonNull ESuperClassHelper eSuperClassHelper = new ESuperClassHelper();
	protected final @NonNull EAssociationHelper eAssociationHelper = new EAssociationHelper();
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
	private @NonNull Map<@NonNull Element, @NonNull List<@NonNull Element>> mergedElement2partialElements = new HashMap<>();

	@Deprecated /* @deprecated pragmatic re-use pending a rationalized Orphanage */
	private @NonNull ExecutorStandardLibrary standardLibrary = new ExecutorStandardLibrary();

	public Merger(@NonNull EnvironmentFactory environmentFactory) {
		this.environmentFactory = environmentFactory;
		this.groupVisitor = createGroupVisitor();
		this.resolveVisitor = createResolveVisitorVisitor();
	}

	private void addPartialElements(@NonNull Element mergedChild, @NonNull List<@NonNull Element> partialChildren) {
		mergedElement2partialElements.put(mergedChild, partialChildren);
		for (@NonNull Element partialChild : partialChildren) {
			partialElement2mergedElement.put(partialChild, mergedChild);
		}
		resolveInstances(mergedChild, partialChildren);
	}

	protected @NonNull MergerGroupVisitor createGroupVisitor() {
		return new MergerGroupVisitor(this);
	}

	protected @NonNull MergerResolveVisitor createResolveVisitorVisitor() {
		return new MergerResolveVisitor(this);
	}

	public org.eclipse.ocl.pivot.@NonNull Class getCollectionType(@NonNull CollectionType genericType, @NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return standardLibrary.getCollectionType(genericType, elementType, isNullFree, lower, upper);
	}

	public org.eclipse.ocl.pivot.@NonNull Class getMapType(@NonNull Type keyType, boolean keyValuesAreNullFree, @NonNull Type valueType, boolean valuesAreNullFree) {
		return standardLibrary.getMapType(keyType, keyValuesAreNullFree, valueType, valuesAreNullFree);
	}

//	public @NonNull TupleType getTupleType(@NonNull String tupleName, @NonNull Map<@NonNull String, @NonNull ? extends Type> parts) {
	//	CompleteModelInternal completeModel = (CompleteModelInternal)environmentFactory.getCompleteModel();
//		return standardLibrary.getTupleType(tupleName, parts);
//	}

	public @Nullable EObject getTupleType(@NonNull TupleTypeId tupleTypeId) {
		PivotIdResolver idResolver = new PivotIdResolver((EnvironmentFactoryInternal) environmentFactory) {};	// XXX
		return standardLibrary.getTupleType(idResolver, tupleTypeId);
	}

	public @Nullable EObject getTupleType(@NonNull String string, @NonNull Map<@NonNull String, @NonNull Type> tupleParts) {
		return standardLibrary.getTupleType(string, tupleParts);
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

	public <E extends Element> @NonNull E merge(@NonNull List<@NonNull E> mergedParentElements, @NonNull List<@NonNull E> partialElements) {
		EClass eClass = eSuperClassHelper.getCommonEClass(partialElements);
		@SuppressWarnings("unchecked")
		E mergedElement = (E)PivotFactory.eINSTANCE.create(eClass);
		mergedParentElements.add(mergedElement);
	//	resolveInstances(mergedElement, partialElements);
		addPartialElements(mergedElement, (@NonNull List<@NonNull Element>)partialElements);
		resolveAttributeSlots();
		resolveReferenceSlots();
		resolvePossibleBidirectionalSlots();
		return mergedElement;
	}

	private @Nullable Boolean mergeBoolean(@NonNull EAttribute eAttribute, @NonNull List<@Nullable Object> partialValues) {
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

	private @Nullable String mergeString(@NonNull EAttribute eAttribute, @NonNull List<@Nullable Object> partialValues) {
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

	//	private void mergeModels(@NonNull Model mergedModel, @NonNull Iterable<@NonNull Model> partialModels) {
	//		mergeElements(mergedModel, partialModels);
	//	}

	private @Nullable Object mergeValue(@NonNull EAttribute eAttribute, @NonNull List<@Nullable Object> partialValues) {
		EDataType eType = eAttribute.getEAttributeType();
		if ((eType == PivotPackage.Literals.BOOLEAN) || (eType == EcorePackage.Literals.EBOOLEAN) || (eType == EcorePackage.Literals.EBOOLEAN_OBJECT)) {
			return mergeBoolean(eAttribute, partialValues);
		}
		else if ((eType == PivotPackage.Literals.STRING) || (eType == EcorePackage.Literals.ESTRING)) {
			return mergeString(eAttribute, partialValues);
		}
		else {
			throw new UnsupportedOperationException(eType.getName());

		}
	}

	public <E extends Element> void putPartialElements(@NonNull E mergedElement, @NonNull List<@NonNull E> partialElements) {
		@SuppressWarnings("unchecked")
		List<@NonNull Element> castPartialElements = (List<@NonNull Element>)partialElements;
		mergedElement2partialElements.put(mergedElement, castPartialElements);
	}

	/**
	 * Traverse the merged containment tree to resolve all attribute slots.
	 */
	private <E extends Element> void resolveAttributeSlots(/*@NonNull E mergedParent, @NonNull Iterable<@NonNull E> partialParents*/) {
	//	List<@NonNull Element> keyList = new ArrayList<>(mergedElement2partialElements.keySet());
	//	Collections.sort(keyList, NameUtil.TO_STRING_COMPARATOR);			// Make execution predictable
		for (@NonNull Element mergedParent : mergedElement2partialElements.keySet()) {
			List<@NonNull Element> partialParents = mergedElement2partialElements.get(mergedParent);
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

	/**
	 * Traverse the partial containment trees to create a merged containment tree.
	 * Only the containment slots are resolved in this pass.
	 */
	private <E extends Element> void resolveInstances(@NonNull E mergedParent, @NonNull Iterable<@NonNull E> partialParents) {
		EClass parentEClass = mergedParent.eClass();
	//	System.out.println("Merger:resolveInstances " + NameUtil.debugSimpleName(mergedParent) + " : " + partialParents);
		assert parentEClass == eSuperClassHelper.getCommonEClass(partialParents);
		for (EStructuralFeature eStructuralFeature : parentEClass.getEAllStructuralFeatures()) {
			if (eStructuralFeature instanceof EReference) {
				EReference eReference = (EReference)eStructuralFeature;
				eAssociationHelper.add(eReference);
				if (eReference.isContainment() && !eReference.isDerived() && !eReference.isTransient() && !eReference.isVolatile()) {
					if (eReference.isMany()) {
						if ("Property".equals(eReference.getName())) {
							getClass();		// XXX
						}
						@SuppressWarnings("unchecked")
						List<@NonNull Element> mergedChildren = (List<@NonNull Element>)mergedParent.eGet(eReference);
						ListOfList<@NonNull Element> ungroupedPartialChildren = new ListOfList<>();
						for (@NonNull E partialParent : partialParents) {
							@SuppressWarnings("unchecked")
							List<@NonNull Element> partialChildren = (@NonNull List<@NonNull Element>)partialParent.eGet(eReference);
							if (!partialChildren.isEmpty()) {
								ungroupedPartialChildren.add(partialChildren);
							}
						}
						Iterable<@NonNull Element> allPartialChildren = ungroupedPartialChildren.getInnerIterable();
						if (!Iterables.isEmpty(allPartialChildren)) {
							EClass protoChildEClass = eSuperClassHelper.getCommonEClass(allPartialChildren);
							Element protoChildElement = (Element)PivotFactory.eINSTANCE.create(protoChildEClass);
							protoElement2partialElements.put(protoChildElement, ungroupedPartialChildren);
							ListOfList<@NonNull Element> groupedPartialChildren = protoChildElement.accept(groupVisitor);
							for (@NonNull List<@NonNull Element> partialChildren : groupedPartialChildren.getOuterIterable()) {
								EClass childEClass = eSuperClassHelper.getCommonEClass(partialChildren);
								Element mergedChild = (Element)PivotFactory.eINSTANCE.create(childEClass);
								mergedChildren.add(mergedChild);
								addPartialElements(mergedChild, partialChildren);
							}
						}
					}
					else {
						List<@Nullable Element> partialChildren = new ArrayList<>();
						for (@Nullable Element partialParent : partialParents) {
							Element partialChild = (Element)partialParent.eGet(eReference);
							if (partialChild != null) {
								partialChildren.add(partialChild);
							}
							else {
								assert !eReference.isRequired();
							}
						}
						if (!partialChildren.isEmpty()) {
							EClass childEClass = eSuperClassHelper.getCommonEClass(partialChildren);
							Element mergedChild = (Element)PivotFactory.eINSTANCE.create(childEClass);
							mergedParent.eSet(eReference, mergedChild);
							addPartialElements(mergedChild, partialChildren);
						}
					}
				}
			}
		}
	}

	private void resolveManyAttributesSlot(@NonNull Element mergedParent, @NonNull EAttribute eAttribute, @NonNull List<@NonNull Element> partialParents) {
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
			Iterable<@Nullable Object> allPartialValues = ungroupedPartialValues.getInnerIterable();
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

	private void resolveManyReferencesSlot(@NonNull Element mergedParent, @NonNull EReference eReference, @NonNull List<@NonNull Element> partialParents) {
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
					Element resolvedChild = (Element)partialChild.accept(resolveVisitor);
					if ((resolvedChild != null) && !mergedChildren.contains(resolvedChild)) {
						mergedChildren.add(resolvedChild);
					}
				}
			}
		}
	}

	private void resolvePossibleBidirectionalSlot(@NonNull Element mergedSource, @NonNull EReference eReference, @NonNull List<@NonNull Element> partialParents) {
		System.out.println("resolvePossibleBidirectionalSlot " + NameUtil.debugSimpleName(mergedSource) + "." + eReference.getName() + " : " + partialParents);
		assert !eReference.isContainment();
		assert !eReference.isMany();
		assert eReference.getEOpposite() == null;
		assert eReference.getEType() == eReference.getEContainingClass();
		if (partialParents.size() == 1) {
			@NonNull Element partialSource = partialParents.get(0);
			Element partialTarget = (Element)partialSource.eGet(eReference);
			if (partialTarget != null) {
				Element mergedTarget = (Element)partialTarget.accept(resolveVisitor);
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
				Element resolvedTarget = (Element)partialSource.accept(resolveVisitor);
				Element resolvedTarget2 = (Element)partialTarget.accept(resolveVisitor);
				if (resolvedTarget == null) {
					resolvedTarget = partialSource;
				}
				if (partialTarget != null) {
					resolvedTarget = (Element)partialTarget.accept(resolveVisitor);
					if (resolvedTarget == null) {
						resolvedTarget = partialTarget;
					}
				}
				if (resolvedTarget != null) {
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
				}
			}
			mergedSource.eSet(eReference, mergedTarget);
		}
	}

	private void resolveSingleAttributeSlot(@NonNull Element mergedParent, @NonNull EAttribute eAttribute, @NonNull List<@NonNull Element> partialParents) {
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
					mergedValue = mergeValue(eAttribute, partialValues);
				}
				mergedParent.eSet(eAttribute, mergedValue);
			}
		}
	}

	private void resolveSingleReferenceSlot(@NonNull Element mergedParent, @NonNull EReference eReference, @NonNull List<@NonNull Element> partialParents) {
	//	System.out.println("resolveSingleReferenceSlot " + NameUtil.debugSimpleName(mergedParent) + "." + eReference.getName() + " : " + partialParents);
		assert !eReference.isContainment();
		assert !eReference.isMany();
		if (partialParents.size() == 1) {
			@NonNull Element partialParent = partialParents.get(0);
			Element partialElement = (Element)partialParent.eGet(eReference);
			if (partialElement != null) {
				Element mergedElement = (Element)partialElement.accept(resolveVisitor);
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
					resolvedTarget = (Element)partialTarget.accept(resolveVisitor);
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

	/**
	 * Traverse the merged containment tree to resolve all reference slots (other than containments).
	 */
	private <E extends Element> void resolvePossibleBidirectionalSlots(/*@NonNull E mergedParent, @NonNull Iterable<@NonNull E> partialParents*/) {
		for (@NonNull Element mergedParent : mergedElement2partialElements.keySet()) {
			List<@NonNull Element> partialParents = mergedElement2partialElements.get(mergedParent);
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
			List<@NonNull Element> partialParents = mergedElement2partialElements.get(mergedParent);
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
}
