/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *   E.D.Willink (CEA List) - Bug 424057 - UML 2.5 CG *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ecore.es2as;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Annotation;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Detail;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.internal.utilities.AbstractConversion;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.External2AS;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.util.DerivedConstants;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

public abstract class AbstractExternal2AS extends AbstractConversion implements External2AS, PivotConstantsInternal
{
	public static @Nullable External2AS findAdapter(@NonNull Resource resource, @NonNull EnvironmentFactoryInternal environmentFactory) {
		External2AS es2as = environmentFactory.getMetamodelManager().getES2AS(resource);
		return es2as;
	}

	private static final @NonNull Map<@NonNull String, @NonNull EAnnotationConverter> source2eAnnotationConverter = new HashMap<>();

	public static void addHandler(@NonNull String source, @NonNull EAnnotationConverter eAnnotationConverter) {
		EAnnotationConverter old = source2eAnnotationConverter.put(source, eAnnotationConverter);
		assert (old == null) || (old == eAnnotationConverter);
	}

	static {
		addHandler(EcorePackage.eNS_URI, EcoreEAnnotationConverter.INSTANCE);
		addHandler(PivotConstantsInternal.DOCUMENTATION_ANNOTATION_SOURCE, GenModelEAnnotationConverter.INSTANCE);
		addHandler(PivotConstants.EXTENDED_META_DATA_ANNOTATION_SOURCE, DefaultEAnnotationConverter.ALL_KEYS_KNOWN);
		addHandler("http://www.eclipse.org/emf/CDO", new DefaultEAnnotationConverter("filter", "persistent"));
		addHandler(DerivedConstants.UML2_UML_PACKAGE_2_0_NS_URI, UMLEAnnotationConverter.INSTANCE);
		addHandler(PivotConstants.IMPORT_ANNOTATION_SOURCE, NullEAnnotationConverter.INSTANCE);
		addHandler(PivotConstants.COLLECTION_ANNOTATION_SOURCE, CollectionEAnnotationConverter.INSTANCE);
	}

	private static interface EAnnotationConverter
	{
		@Nullable List<@NonNull Detail> convert(@NonNull AbstractExternal2AS external2AS, @NonNull EAnnotation eAnnotation);
	}

	private static class DefaultEAnnotationConverter implements EAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter NO_KEYS_KNOWN = new DefaultEAnnotationConverter();
		public static final @NonNull EAnnotationConverter ALL_KEYS_KNOWN = new DefaultEAnnotationConverter((@NonNull String[])null);

		protected @NonNull String @Nullable [] knownKeys;			// explicitly known keys, empty for none, null for all

		protected DefaultEAnnotationConverter(@NonNull String ... knownKeys) {
			this.knownKeys = knownKeys;
		}

		@Override
		public @Nullable List<@NonNull Detail> convert(@NonNull AbstractExternal2AS external2AS, @NonNull EAnnotation eAnnotation) {
			List<@NonNull Detail> asDetails = null;
			for (Map.Entry<String, String> eDetail : eAnnotation.getDetails()) {
				String key = eDetail.getKey();
				String value = eDetail.getValue();
				Detail asDetail = convert(external2AS, eAnnotation, key, value);
				if (asDetail != null) {
					if (asDetails == null) {
						asDetails = new ArrayList<>();
					}
					asDetails.add(asDetail);
				}
			}
			return asDetails;
		}

		protected @Nullable Detail convert(@NonNull AbstractExternal2AS external2AS, @NonNull EAnnotation eAnnotation, String key, String value) {
			if (Ecore2AS.UNKNOWN_EANNOTATIONS.isActive()) {
				@NonNull String[] knownKeys2 = knownKeys;
				if (knownKeys2 != null) {
					boolean isKnown = false;
					for (@NonNull String knownKey : knownKeys2) {
						if (knownKey.equals(key)) {
							isKnown = true;
							break;
						}
					}
					if (!isKnown) {
						Ecore2AS.UNKNOWN_EANNOTATIONS.println(eAnnotation.getSource() + "::" + key + " : " + value);
					}
				}
			}
			Detail asDetail = PivotFactory.eINSTANCE.createDetail();
			asDetail.setName(key);
			asDetail.getValues().add(value);
			return asDetail;
		}

		protected @NonNull Element getASContainer(@NonNull AbstractExternal2AS external2AS, @NonNull EAnnotation eAnnotation) {
			EObject eContainer = eAnnotation.eContainer();
			assert eContainer != null;
			Element asContainer = external2AS.getCreated(Element.class, eContainer);
			assert asContainer != null;
			return asContainer;
		}
	}

	private static class NullEAnnotationConverter implements EAnnotationConverter
	{
		public static @NonNull EAnnotationConverter INSTANCE = new NullEAnnotationConverter();

		@Override
		public @Nullable List<@NonNull Detail> convert(@NonNull AbstractExternal2AS external2AS, @NonNull EAnnotation eAnnotation) {
			return null;
		}
	}

	private static class CollectionEAnnotationConverter extends DefaultEAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new CollectionEAnnotationConverter();

		private CollectionEAnnotationConverter() {
			super(PivotConstants.COLLECTION_IS_NULL_FREE);
		}

		@Override
		protected @Nullable Detail convert(@NonNull AbstractExternal2AS external2AS, @NonNull EAnnotation eAnnotation, String key, String value) {
			if (PivotConstants.COLLECTION_IS_NULL_FREE.equals(key)) {
				EObject eContainer = eAnnotation.eContainer();
				assert eContainer != null;
				Element asContainer = external2AS.getCreated(Element.class, eContainer);
				assert asContainer != null;
				if (!(asContainer instanceof org.eclipse.ocl.pivot.Package)) {
					return null;	// Suppress redundant annotation
				}
			}
			return super.convert(external2AS, eAnnotation, key, value);
		}
	}

	private static class EcoreEAnnotationConverter extends DefaultEAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new EcoreEAnnotationConverter();

		@Override
		protected @Nullable Detail convert(@NonNull AbstractExternal2AS external2AS, @NonNull EAnnotation eAnnotation, String key, String value) {
			if ("constraints".equals(key)) {
				return null;	// Suppress redundant annotation
			}
			else {
				return super.convert(external2AS, eAnnotation, key, value);
			}
		}
	}

	private static class GenModelEAnnotationConverter extends DefaultEAnnotationConverter
	{
		public static final @NonNull EAnnotationConverter INSTANCE = new GenModelEAnnotationConverter();

		private GenModelEAnnotationConverter() {
			super("body", "suppressedGetVisibility", "suppressedIsSetVisibility", "suppressedSetVisibility", "suppressedUnsetVisibility", "suppressedVisibility");
		}

		@Override
		protected @Nullable Detail convert(@NonNull AbstractExternal2AS external2AS, @NonNull EAnnotation eAnnotation, String key, String value) {
			if (PivotConstantsInternal.DOCUMENTATION_ANNOTATION_KEY.equals(key)) {
				Element asContainer = getASContainer(external2AS, eAnnotation);
				Comment asComment = PivotFactory.eINSTANCE.createComment();
				asComment.setBody(value);
				asContainer.getOwnedComments().add(asComment);
				return null;	// Suppress redundant annotation
			}
			else {
				return super.convert(external2AS, eAnnotation, key, value);
			}
		}
	}

	private static class UMLEAnnotationConverter extends DefaultEAnnotationConverter
	{
		public static @NonNull EAnnotationConverter INSTANCE = new UMLEAnnotationConverter();

		@Override
		protected @Nullable Detail convert(@NonNull AbstractExternal2AS external2AS, @NonNull EAnnotation eAnnotation, String key, String value) {
			if (DerivedConstants.ANNOTATION_DETAIL__ORIGINAL_NAME.equals(key)) {
				Element asContainer = getASContainer(external2AS, eAnnotation);
				assert ClassUtil.safeEquals(((NamedElement)asContainer).getName(), value);
				return null;	// Suppress redundant annotation
			}
			else {
				return super.convert(external2AS, eAnnotation, key, value);
			}
		}
	}

	protected AbstractExternal2AS(@NonNull EnvironmentFactoryInternal environmentFactory) {
		super(environmentFactory);
	}

	public abstract void addGenericType(@NonNull EGenericType eObject);

	public abstract void addMapping(@NonNull EObject eObject, @NonNull Element pivotElement);

	//
	//	Duplicate (same-named) Annotations are a useful detection of bad conversions, but not actually illegal.
	//	The SysML QUDV exampple has a gratuitous duplicate EAnnotation source. Therefore assert that asAnnotation
	//	is not present in asAnnotations unless eContainer has duplicates.
	//
	private boolean assertConsistentDuplication(@NonNull Annotation asAnnotation, @NonNull List<Element> asAnnotations, EObject eContainer) {
	 	String name = asAnnotation.getName();
	 	int nameMatches = 0;
		for (Element asElement : asAnnotations) {
			if (asElement instanceof Annotation) {
				if (((Annotation)asElement).getName().equals(name)) {
					nameMatches++;
					break;
			 	}
			}
		}
	 	if (nameMatches <= 0) {
	 		return true;
	 	}
		EModelElement eObject = (EModelElement)eContainer;
		int sourceMatches = 0;
		for (EAnnotation eAnnotation : eObject.getEAnnotations()) {
			if (name.equals(eAnnotation.getSource())) {
				if (++sourceMatches >= 2) {
					return true;
				}
			}
		}
		assert false : "Duplicate EAnnotation source : " + name + " in " + eContainer;
	 	return false;
	}

	protected abstract Model basicGetPivotModel();

	/**
	 * @since 1.3
	 */
	public boolean cannotBeOptional(@NonNull ETypedElement eTypedElement) {	// Fixes Bug 510180, Ecore does not prohibit optional primitive types
		EClassifier eType = eTypedElement.getEType();
		if (eType != null) {
			Class<?> instanceClass = eType.getInstanceClass();
			if ((instanceClass != null) && ((instanceClass == boolean.class) || (instanceClass == byte.class)
					|| (instanceClass == double.class) || (instanceClass == float.class)
					|| (instanceClass == int.class) || (instanceClass == long.class) || (instanceClass == short.class))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void dispose() {
		Model pivotModel2 = basicGetPivotModel();
		if (pivotModel2 != null) {
			Resource asResource = pivotModel2.eResource();
			if (asResource != null) {
				asResource.unload();
			}
			environmentFactory.getCompleteModel().getPartialModels().remove(pivotModel2);
			metamodelManager.getASResourceSet().getResources().remove(asResource);
		}
		metamodelManager.removeExternalResource(this);
	}

	public abstract void error(@NonNull String message);

	/**
	 * Return true if eClassifier is annotated to indicate that it is a Map Entry class that is required in
	 * Ecore but is not required by the Pivot; i.e. it is only ever used to represent the Entry(K,V) underlying
	 * a Map(K,V).
	 *
	 * @since 1.7
	 */
	public boolean isEcoreOnlyEntryClass(@Nullable EClassifier eClassifier) {
//		return (eClassifier != null) && (eClassifier.getEAnnotation(PivotConstants.ENTRY_CLASS_ANNOTATION_SOURCE) != null);
		if (eClassifier == null) {
			return false;
		}
		String role = EcoreUtil.getAnnotation(eClassifier, PivotConstantsInternal.CLASSIFIER_ANNOTATION_SOURCE, PivotConstantsInternal.CLASSIFIER_ROLE);
		if (PivotConstantsInternal.CLASSIFIER_ROLE_ENTRY.equals(role)) {
			return true;				// XXX Boolean
		}
		else if (PivotConstantsInternal.CLASSIFIER_ROLE_LAMBDA.equals(role)) {
			return false;				// XXX Boolean
		}
		return false;
	}

	/**
	 * Return true if eClassifier is an EClass whose instanceClass is set to java.util.Map.Entry.class
	 * identifying its use as the Entry(K,V) for the Ecore Collection(Entry(K,V)) == Map(K,V) idiom.
	 *
	 * @since 1.7
	 */
	public boolean isEntryClass(@Nullable EClassifier eClassifier) {
		return (eClassifier instanceof EClass) && (eClassifier.getInstanceClass() == java.util.Map.Entry.class);
	}

	/**
	 * Return true if eOperation can be handled as an OCL invariant. In addition to the EcoreUtil.isInvariant()
	 * checks we also require either no GenModel documentation or a GenModel documentation with no additional
	 * content such as a Java body.
	 */
	public boolean isInvariant(@NonNull EOperation eOperation) {
		if (!EcoreUtil.isInvariant(eOperation)) {
			return false;
		}
		EAnnotation eAnnotation = eOperation.getEAnnotation(PivotConstantsInternal.DOCUMENTATION_ANNOTATION_SOURCE);
		if (eAnnotation != null) {
			@SuppressWarnings("null")@NonNull EMap<String, String> details = eAnnotation.getDetails();
			if ((details.size() != 1) ||  details.containsKey(PivotConstantsInternal.DOCUMENTATION_ANNOTATION_KEY)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @since 1.17
	 */
	public boolean isLibrary(@NonNull EPackage ePackage) {
		EAnnotation asLibraryAnnotation = ePackage.getEAnnotation(PivotConstants.AS_LIBRARY_ANNOTATION_SOURCE);
		return asLibraryAnnotation != null;
	}

	public boolean isRequired(@NonNull EPackage ePackage) {
		for (EClassifier eClassifier : ePackage.getEClassifiers()) {
			String role = EcoreUtil.getAnnotation(eClassifier, PivotConstantsInternal.CLASSIFIER_ANNOTATION_SOURCE, PivotConstantsInternal.CLASSIFIER_ROLE);
			if (role == null) {
				return true;
			}
		}
		for (EPackage eSubPackage : ePackage.getESubpackages()) {
			if (isRequired(eSubPackage)) {
				return true;
			}
		}
		return false;
	}

	public abstract void queueEAnnotation(@NonNull EAnnotation eAnnotation);

	public abstract void queueReference(@NonNull EObject eObject);

	public <@NonNull T extends NamedElement> T refreshElement(@NonNull Class<T> pivotClass, /*@NonNull*/ EClass pivotEClass, @NonNull EModelElement eModelElement) {
		assert pivotEClass != null;
		EFactory eFactoryInstance = pivotEClass.getEPackage().getEFactoryInstance();
		EObject pivotElement = eFactoryInstance.create(pivotEClass);
		if (!pivotClass.isAssignableFrom(pivotElement.getClass())) {
			throw new ClassCastException();
		}
		@SuppressWarnings("unchecked")
		T castElement = (T) pivotElement;
		return castElement;
	}

	public <@NonNull T extends NamedElement> T refreshNamedElement(@NonNull Class<T> pivotClass, /*@NonNull*/ EClass pivotEClass, @NonNull ENamedElement eNamedElement) {
		T castElement = refreshElement(pivotClass, pivotEClass, eNamedElement);
		castElement.setName(environmentFactory.getTechnology().getOriginalName(eNamedElement));
		return castElement;
	}

	protected void resolveEAnnotation(@NonNull EAnnotation eAnnotation) {
		String source = eAnnotation.getSource();
		boolean hasReferences = !eAnnotation.getReferences().isEmpty();
		EAnnotationConverter eAnnotationConverter = source2eAnnotationConverter.get(source);
		if (eAnnotationConverter == null) {
			boolean isKnown = (Ecore2AS.knownEAnnotationSources != null) && Ecore2AS.knownEAnnotationSources.contains(source);
			if (isKnown) {
				eAnnotationConverter = DefaultEAnnotationConverter.ALL_KEYS_KNOWN;
			}
			else {
				eAnnotationConverter = DefaultEAnnotationConverter.NO_KEYS_KNOWN;
			}
		}
		List<@NonNull Detail> asDetails = eAnnotationConverter.convert(this, eAnnotation);
		Annotation asAnnotation = null;
		if (hasReferences || (asDetails != null)) {			// XXX contents ???
			asAnnotation = PivotFactory.eINSTANCE.createAnnotation();
			asAnnotation.setName(source);
		}
		if (asDetails != null) {
			assert asAnnotation != null;
			asAnnotation.getOwnedDetails().addAll(asDetails);
		}
		if (asAnnotation != null) {
			EObject eContainer = eAnnotation.eContainer();
			assert eContainer != null;
			Element asContainer = getCreated(Element.class, eContainer);
			assert asContainer != null;
			List<Element> asAnnotations = asContainer.getOwnedAnnotations();
			assert assertConsistentDuplication(asAnnotation, asAnnotations, eContainer);
			asAnnotations.add(asAnnotation);
			addMapping(eAnnotation, asAnnotation);
		}
		if (hasReferences) {
			queueReference(eAnnotation);
		}
	}
}