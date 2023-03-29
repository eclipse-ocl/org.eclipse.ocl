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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;

/**
 * The Merger supports the creation of a merged model from one or more partial models.
 */
public class Merger
{
	protected final @NonNull EnvironmentFactory environmentFactory;
	protected final @NonNull ESuperClassHelper eSuperClassHelper = new ESuperClassHelper();
	protected final @NonNull MergerGroupVisitor groupVisitor;
	protected final @NonNull MergerUpdateVisitor updateVisitor;

	/**
	 * The lists of incoming partial elements to  be grouped to support a many-child.
	 */
	private @NonNull Map<@NonNull Element, @NonNull IterableOfIterable<@NonNull Element>> protoElement2partialElements = new HashMap<>();

	/**
	 * The outgoing merged element created for each incoming partial element.
	 */
	private @NonNull Map<@NonNull Element, @NonNull Element> partialElement2mergedElement = new HashMap<>();

	/**
	 * The incoming partial elements from which each outgoing merged element was created
	 */
	private @NonNull Map<@NonNull Element, @NonNull Iterable<@NonNull Element>> mergedElement2partialElements = new HashMap<>();

	public Merger(@NonNull EnvironmentFactory environmentFactory) {
		this.environmentFactory = environmentFactory;
		this.groupVisitor = createGroupVisitor();
		this.updateVisitor = createUpdateVisitor();
	}

	private void addPartialElements(@NonNull Element mergedChild, @NonNull Iterable<@NonNull Element> partialChildren) {
		mergedElement2partialElements.put(mergedChild, partialChildren);
		for (@NonNull Element partialChild : partialChildren) {
			partialElement2mergedElement.put(partialChild, mergedChild);
		}
	}

	protected @NonNull MergerGroupVisitor createGroupVisitor() {
		return new MergerGroupVisitor(this);
	}

	protected @NonNull MergerUpdateVisitor createUpdateVisitor() {
		return new MergerUpdateVisitor(this);
	}

	public <E extends Element> @NonNull Iterable<@NonNull E> getPartialElements(@NonNull E mergedElement) {
		@SuppressWarnings("unchecked")
		Iterable<@NonNull E> partialElements = (Iterable<@NonNull E>)mergedElement2partialElements.get(mergedElement);
		assert partialElements != null;
		return partialElements;
	}

	public <E extends Element> @NonNull IterableOfIterable<@NonNull E> getUngroupedPartialElements(@NonNull E protoElement) {
		@SuppressWarnings("unchecked")
		IterableOfIterable<@NonNull E> ungroupedPartialElements = (IterableOfIterable<@NonNull E>)protoElement2partialElements.get(protoElement);
		assert ungroupedPartialElements != null;
		return ungroupedPartialElements;
	}

	public <E extends Element> @NonNull E merge(@NonNull Iterable<@NonNull E> partialElements) {
		EClass eClass = eSuperClassHelper.getCommonEClass(partialElements);
		@SuppressWarnings("unchecked")
		E mergedElement = (E)PivotFactory.eINSTANCE.create(eClass);
		mergeElements(mergedElement, partialElements);
		return mergedElement;
	}

	private <E extends Element> void mergeElements(@NonNull E mergedParent, @NonNull Iterable<@NonNull E> partialParents) {
	//	Iterable<@NonNull Element> partialParents = context.getPartialElements(mergedParent);
		EClass parentEClass = eSuperClassHelper.getCommonEClass(partialParents);
		assert parentEClass == mergedParent.eClass();
		for (EReference eContainment : parentEClass.getEAllContainments()) {
			assert eContainment != null;
			if (eContainment.isMany()) {
				@SuppressWarnings("unchecked")
				List<@NonNull Element> mergedChildren = (List<@NonNull Element>)mergedParent.eGet(eContainment);
				IterableOfIterable<@NonNull Element> ungroupedPartialChildren = new IterableOfIterable<>();
				for (@NonNull E partialParent : partialParents) {
					@SuppressWarnings("unchecked")
					List<@NonNull Element> partialChildren = (@NonNull List<@NonNull Element>)partialParent.eGet(eContainment);
					ungroupedPartialChildren.add(partialChildren);
				}
				EClass childEClass = eSuperClassHelper.getCommonEClass(ungroupedPartialChildren.getInnerIterable());
				Element protoChildElement = (Element)PivotFactory.eINSTANCE.create(childEClass);
				protoElement2partialElements.put(protoChildElement, ungroupedPartialChildren);
				IterableOfIterable<@NonNull Element> groupedPartialChildren = protoChildElement.accept(groupVisitor);
				for (@NonNull Iterable<@NonNull Element> partialChildren : groupedPartialChildren.getOuterIterable()) {
					Element mergedChild = (Element)PivotFactory.eINSTANCE.create(childEClass);
					mergedChildren.add(mergedChild);
					addPartialElements(mergedChild, partialChildren);
				}

			}
			else {
				List<@Nullable Element> partialChildren = new ArrayList<>();
				for (@Nullable Element partialParent : partialParents) {
					Element partialChild = (Element)partialParent.eGet(eContainment);
					assert partialChild != null;
					partialChildren.add(partialChild);
				}
				EClass childEClass = eSuperClassHelper.getCommonEClass(partialChildren);
				Element mergedChild = (Element)PivotFactory.eINSTANCE.create(childEClass);
				mergedParent.eSet(eContainment, mergedChild);
				addPartialElements(mergedChild, partialChildren);
			}
		}
	//	List<?> owns = cgElement instanceof CGValuedElement ? ((CGValuedElement)cgElement).getOwns() : null;
	//	for (CGElement cgChild : cgElement.getChildren()) {
	//		if ((owns == null) || !owns.contains(cgChild)) {
	//			cgChild.accept(this);
	//		}
	//	}
	//	return null;
	}

	/*	protected void mergeClass(@NonNull PivotHelper helper, @NonNull CompleteClass completeClass,
			@NonNull Map<@NonNull NamedElement, @NonNull Type> completeType2type) {
		org.eclipse.ocl.pivot.Class asClass = (org.eclipse.ocl.pivot.Class) completeType2type.get(completeClass);
		assert asClass != null;
		//
		//	Gather the partial aggregates.
		//
		boolean isAbstract = false;
		boolean isInterface = false;
		String instanceClassName = null;
		List<org.eclipse.ocl.pivot.@NonNull Class> superClasses = null;
		for (org.eclipse.ocl.pivot.@NonNull Class partialClass : PivotUtil.getPartialClasses(completeClass)) {
			if (partialClass.isIsAbstract()) {
				isAbstract = true;
			}
			if (partialClass.isIsInterface()) {
				isInterface = true;
			}
			if (instanceClassName == null) {
				instanceClassName = partialClass.getInstanceClassName();
			}
			for (org.eclipse.ocl.pivot.@NonNull Class partialSuperClass : PivotUtil.getSuperClasses(partialClass)) {
				if (superClasses == null) {
					superClasses = new ArrayList<>();
				}
				if (!superClasses.contains(partialSuperClass)) {
					superClasses.add(partialSuperClass);
				}
			}
		}
		//
		//	Populate from the aggregates.
		//
		asClass.setIsAbstract(isAbstract);
		asClass.setIsInterface(isInterface);
		if (instanceClassName != null) {
			asClass.setInstanceClassName(instanceClassName);
		}
		if (superClasses != null) {
			List<org.eclipse.ocl.pivot.@NonNull Class> asSuperClasses = PivotUtilInternal.getSuperClassesList(asClass);
			for (org.eclipse.ocl.pivot.@NonNull Class superClass : superClasses) {
			//	CompleteClassInternal superCompleteClass = environmentFactory.getCompleteModel().getCompleteClass(superClass);
				org.eclipse.ocl.pivot.Class unspecializedSuperClass = PivotUtil.getUnspecializedTemplateableElement(superClass);
				org.eclipse.ocl.pivot.Class asSuperClass = (org.eclipse.ocl.pivot.Class) completeType2type.get(unspecializedSuperClass);
				if (unspecializedSuperClass != superClass) {
					if (superClass instanceof CollectionType) {
						CollectionType superCollectionType = (CollectionType)superClass;
						Type elementType = superCollectionType.getElementType();
						Type asElementType = completeType2type.get(elementType);
						asSuperClass = environmentFactory.getCompleteEnvironment().getCollectionType(asSuperClass, asElementType, superCollectionType.isIsNullFree(), superCollectionType.getLowerValue(), superCollectionType.getUpperValue());
					}
					else if (superClass instanceof MapType) {
						MapType superMapType = (MapType)superClass;
						Type keyType = superMapType.getKeyType();
						Type valueType = superMapType.getValueType();
						Type asKeyType = completeType2type.get(keyType);
						Type asValueType = completeType2type.get(valueType);
						asSuperClass = environmentFactory.getCompleteEnvironment().getMapType(asSuperClass, asKeyType, superMapType.isKeysAreNullFree(), asValueType, superMapType.isValuesAreNullFree());
					}
					else {
						throw new UnsupportedOperationException();
					}
				}
				if (asSuperClass == null) {
					asSuperClass = superClass;			// A specialization
				}
				if (!asSuperClasses.contains(asSuperClass)) {
					asSuperClasses.add(asSuperClass);
				}
			}
		}
	} */

/*	protected void mergePackage(@NonNull List<org.eclipse.ocl.pivot.Package> asPackages, @NonNull PivotHelper helper, @NonNull CompletePackage completePackage) {
		org.eclipse.ocl.pivot.Package asPackage = helper.createPackage(NameUtil.getName(completePackage), completePackage.getNsPrefix(), uri);
		asPackages.add(asPackage);
		List<@NonNull TemplateSignature> templateSignatures = new ArrayList<>();
		Map<@NonNull NamedElement, @NonNull Type> completeType2type = new HashMap<>();
		//
		//	Create the empty classes and discover the template signatures.
		//
		List<org.eclipse.ocl.pivot.@NonNull Class> asClasses = new ArrayList<>();
		Iterable<@NonNull CompleteClass> completeClasses = PivotUtil.getOwnedCompleteClasses(completePackage);
		for (@NonNull CompleteClass completeClass : completeClasses) {
			EClass eClass = completeClass.getPrimaryClass().eClass();
			org.eclipse.ocl.pivot.Class asClass = (org.eclipse.ocl.pivot.Class)PivotFactory.eINSTANCE.create(eClass);
			asClass.setName(NameUtil.getName(completeClass));
			asClasses.add(asClass);
			completeType2type.put(completeClass, asClass);
			for (org.eclipse.ocl.pivot.@NonNull Class partialClass : PivotUtil.getPartialClasses(completeClass)) {
				completeType2type.put(partialClass, asClass);
				TemplateSignature templateSignature = partialClass.getOwnedSignature();
				if (templateSignature != null) {
					List<@NonNull TemplateParameter> templateParameters = PivotUtil.getTemplateParameters(templateSignature);
					if (templateParameters != null) {
						templateSignatures.add(templateSignature);
					}
				}
				// XXX Operation TemplateSignature
			}
		}
		Collections.sort(asClasses, NameUtil.NAMEABLE_COMPARATOR);
		asPackage.getOwnedClasses().addAll(asClasses);
		//
		//	Parameterize the classes.
		//
		for (@NonNull TemplateSignature templateSignature : templateSignatures) {
			TemplateableElement templateableElement = templateSignature.getOwningElement();
			TemplateableElement asTemplateableElement = (TemplateableElement)completeType2type.get(templateableElement);
			if (asTemplateableElement != null) {
				List<@NonNull TemplateParameter> templateParameters = PivotUtil.getTemplateParameters(templateSignature);
				assert templateParameters != null;
				@NonNull TemplateParameter[] asTemplateParameters = new @NonNull TemplateParameter[templateParameters.size()];
				for (int i = 0; i < templateParameters.size(); i++) {
					@NonNull TemplateParameter templateParameter = templateParameters.get(i);
					List<org.eclipse.ocl.pivot.@NonNull Class> constrainingClasses = templateParameter.getConstrainingClasses();
					org.eclipse.ocl.pivot.@NonNull Class [] asConstrainingClasses = new org.eclipse.ocl.pivot.@NonNull Class[constrainingClasses.size()];
					for (int j = 0; j < constrainingClasses.size(); j++) {
						org.eclipse.ocl.pivot.@NonNull Class constrainingClass = constrainingClasses.get(i);
						org.eclipse.ocl.pivot.Class asConstrainingClass = (org.eclipse.ocl.pivot.Class)completeType2type.get(constrainingClass);
						asConstrainingClasses[i] = asConstrainingClass != null ? asConstrainingClass : constrainingClass;
					}
					TemplateParameter asTemplateParameter = PivotUtil.createTemplateParameter(NameUtil.getName(templateParameter), asConstrainingClasses);
					asTemplateParameters[i] = asTemplateParameter;
					completeType2type.put(templateParameter, asTemplateParameter);
				}
				/*TemplateSignature asTemplateSignature =* / PivotUtil.createTemplateSignature(asTemplateableElement, asTemplateParameters);
			}
		}
		//
		//	Populate the classes.
		//
	//	List<@NonNull CompleteClass> sortedCompleteClasses = Lists.newArrayList(completeClasses);
	//	Collections.sort(sortedCompleteClasses, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull CompleteClass completeClass : completeClasses) {
			mergeClass(helper, completeClass, completeType2type);
		}
	} */


/*	protected @Nullable ASResource  mergeResources(@NonNull URI mergedURI, @NonNull ASResource asResource1, @NonNull ASResource asResource2) {
		PivotHelper helper = new PivotHelper(environmentFactory);
		ResourceSet asResourceSet = metamodelManager.getASResourceSet();
		ASResource asResource = (ASResource) asResourceSet.createResource(mergedURI);
	//	asResourceSet.getResources().remove(asResource);
	//	ResourceSet resourceSet = new ResourceSetImpl();
	//	resourceSet.getResources().add(asResource);
		Model asModel = PivotUtil.createModel(mergedURI.toString());
		asResource.getContents().add(asModel);
		CompleteModelInternal completeModel = environmentFactory.getCompleteModel();
		for (CompletePackage completePackage : Lists.newArrayList(completeModel.getAllCompletePackages())) {	// Avoid CME from 'merged' CompletePackage
			if (PivotConstants.METAMODEL_NAME.equals(completePackage.getURI())) {
				mergePackage(asModel.getOwnedPackages(), helper, completePackage);
			}
		}
		return asResource;
	} */

//	private void mergeModels(@NonNull Model mergedModel, @NonNull Iterable<@NonNull Model> partialModels) {
//		mergeElements(mergedModel, partialModels);
//	}

	public <E extends Element> void putPartialElements(@NonNull E mergedElement, @NonNull Iterable<@NonNull E> partialElements) {
		@SuppressWarnings("unchecked")
		Iterable<@NonNull Element> castPartialElements = (Iterable<@NonNull Element>)partialElements;
		mergedElement2partialElements.put(mergedElement, castPartialElements);
	}
}
