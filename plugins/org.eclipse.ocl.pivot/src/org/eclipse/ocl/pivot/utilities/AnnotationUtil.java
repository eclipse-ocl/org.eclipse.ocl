/*******************************************************************************
 * Copyright (c) 2010, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 * E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import java.util.Comparator;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Annotation;
import org.eclipse.ocl.pivot.Detail;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.util.DerivedConstants;

public class AnnotationUtil
{
	/**
	 * EClassifier annotation qualifying a Classifier.
	 */
	public static final @NonNull String DATA_TYPE_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL-DataType";
	/**
	 * 	 The name for the full-featured EClass to accompany the feature-free EDataType realizing a DataType in Ecore.
	 */
	public static final @NonNull String DATA_TYPE_ECLASS_NAME = "EClassName";

	/**
	 * EClassifier annotation qualifying a Classifier.
	 */
	public static final @NonNull String ECLASSIFIER_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL-EClassifier";
	/**
	 * 	 The classifier role may be "Entry" or "Lambda" or "Tuple" or blank.
	 */
	public static final @NonNull String ECLASSIFIER_ROLE = "role";
	/**
	 * 	 A DataType class is a DataType mapped to an EClass, rather than EDataType, to support features
	 */
	public static final @NonNull String ECLASSIFIER_ROLE_DATA_TYPE = "DataType";
	/**
	 * 	 An EDataType class is the value aspect of an EClass referenced by the EAnnotation. The EClass supports the features of the DataType.
	 */
	public static final @NonNull String ECLASSIFIER_ROLE_DATA_TYPE_VALUE = "DataTypeValue";
	/**
	 * 	 An EntryXXXX<K,V> class types a Map Entry with Class/DataType and Required/Optional types.
	 */
	public static final @NonNull String ECLASSIFIER_ROLE_ENTRY = "Entry";
	/**
	 * 	 A LambdaXXXX<C, P..., R> types a Lambda with Context, Parameter(s) and Result.
	 */
	public static final @NonNull String ECLASSIFIER_ROLE_LAMBDA = "Lambda";
	/**
	 * 	 A TupleXXXX<P...> types a Tuple with parts.
	 */
	public static final @NonNull String ECLASSIFIER_ROLE_TUPLE = "Tuple";

	/**
	 * ETypedElement annotation qualification.
	 */
	public static final @NonNull String COLLECTION_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL-Collection";
	public static final @NonNull String legacy_COLLECTION_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL/Collection";
	public static final @NonNull String COLLECTION_ANNOTATION_SOURCE2 = "http://www.eclipse.org/OCL-Collection";
	/**
	 * ETypedElement annotation identifying that a collection is null-free.
	 */
	public static final @NonNull String COLLECTION_IS_NULL_FREE = "nullFree";
	/**
	 * ETypedElement annotation identifying the name of the abstract Pivot collection class when other than
	 * the concrete Bag/OrderedSet/Sequence/Set deduced from ordered/unique attributes.
	 */
	public static final @NonNull String COLLECTION_KIND = "kind";

	/**
	 * EOperation annotation qualifying that an operation is transient (not-cached).
	 * @since 1.3
	 */
	public static final @NonNull String EOPERATION_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL-EOperation";
	/**
	 * 	 The operation is a coercion: true - coercion, blank/false - regular.
	 */
	public static final @NonNull String EOPERATION_IS_COERCION = "isCoercion";
	/**
	 * 	 The operation is a constraint validator: true - transient (not-cached), blank/false - regular.
	 */
	public static final @NonNull String EOPERATION_IS_CONSTRAINT = "isConstraint";		// XXX replace originalName
	/**
	 * 	 The operation is: true - invalidating (may source an invalid), blank/false - regular.
	 */
	public static final @NonNull String EOPERATION_IS_INVALIDATING = "isInvalidating";
	/**
	 * EOperation annotation identifying that an OPeration is static.
	 */
	public static final @NonNull String EOPERATION_IS_STATIC = "isStatic";
	/**
	 * 	 The operation is: true - transient (not-cached), blank/false - regular.
	 */
	public static final @NonNull String EOPERATION_IS_TRANSIENT = "isTransient";
	/**
	 * 	 The EOperation return is: true - a type, blank/false - regular.
	 */
	public static final @NonNull String EOPERATION_IS_TYPE_OF = "isTypeOf";
	/**
	 * 	 The operation is: true - validating (may absorb an invalid), blank/false - regular.
	 */
	public static final @NonNull String EOPERATION_IS_VALIDATING = "isValidating";
	/**
	 * 	 The operation is: fully qualified implementation class name.
	 */
	public static final @NonNull String EOPERATION_IMPLEMENTATION = "implementation";
	/**
	 * 	 The operation is an iteration with a given iterator count.
	 */
	public static final @NonNull String EOPERATION_ITERATORS = "iterators";
	/**
	 * 	 The operation is an iterate iteration with a given accumulator count.
	 */
	public static final @NonNull String EOPERATION_ACCUMULATORS = "accumulators";
	/**
	 * 	 The operation has a given named precedence.
	 */
	public static final @NonNull String EOPERATION_PRECEDENCE = "precedence";

	/**
	 * EPackage annotation indicating that the EPackage is an Ecore serialisation of an OCL AS Library.
	 * No details are defined for this EAnnotation.
	 * <p>
	 * This annotation is used by /org.eclipse.ocl.pivot/model/oclstdlib.ecore. It is not
	 * intended to be used by client code.
	 */
	public static final @NonNull String EPACKAGE_AS_LIBRARY_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL-EPackage-ASLibrary";
	public static final @NonNull String legacy_AS_LIBRARY_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL/ASLibrary";

	/**
	 * EPackage annotation indicating that the EPackage is an Ecore serialisation of an OCL AS Metamodel.
	 * No details are defined for this EAnnotation.
	 * <p>
	 * This annotation is used by /org.eclipse.ocl.pivot/model/Pivot.ecore. It is not
	 * intended to be used by client code.
	 */
	public static final @NonNull String EPACKAGE_AS_METAMODEL_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL-EPackage-ASMetamodel";
	public static final @NonNull String legacy_AS_METAMODEL_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL/ASMetamodel";

	/**
	 * EPackage annotation identifying models that must be imported to enable the OCL embedded as
	 * feature bodies gto be successfully parsed. MOdels that are directly referenced from the
	 * structural Ecore may be omitted.
	 * Each detail is an alias-name, import uri pair.
	 */
	public static final @NonNull String legacy_IMPORT_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL/Import";
	public static final @NonNull String EPACKAGE_IMPORT_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL-EPackage-Import";

	/**
	 * EPackage EAnnotation qualification. The Boolean/Integer/Real/String/UnlimitedNatural sub-EAnnotations
	 * reference the EGenericType elements that should be treated as the corresponding PrimitiveType.
	 * We would like to annotate the EGenericType.eClassifier with the corresponding Pivot DataType/PrimitiveType,
	 * but EGenericType does not support EAnnotations so the EAnnotation is placed on the ancestral EPackage
	 * with the source-suffix identifying the PrimitiveType and references to each EGenericType. These references
	 * look like redundant duplicates in the Sample Ecore Editor; they aren't.
	 */
	public static final @NonNull String EPACKAGE_ORIGINAL_TYPE_ANNOTATION_SOURCE_PREFIX = "http://www.eclipse.org/OCL-EGenericType-";

	/**
	 * 	 The package role may be "Orphanage" or "Synthetics" or blank.
	 */
//	public static final @NonNull String PACKAGE_ROLE = "role";
	/**
	 * 	 A local Orphanage package hosts the actual synthetic types to satisfy XMI serializationwithout elaborate proxies.
	 */
//	public static final @NonNull String PACKAGE_ROLE_ORPHANAGE = "Orphanage";	// never used
	/**
	 * 	 The Synthetics package hosts the interface synthetic types to support Map entries and Lambdas.
	 */
//	public static final @NonNull String PACKAGE_ROLE_SYNTHETICS = "Synthetics";

	/**
	 * EPackage annotation declaring one of precedence-ordered list of named precedences.
	 */
	public static final @NonNull String EPACKAGE_PRECEDENCE_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL-EPackage-Precedence";
	/**
	 * 	 The name of the precedence.
	 */
	public static final @NonNull String EPACKAGE_PRECEDENCE_NAME = "name";
	/**
	 * 	 The AssociativityKind of the precedence.
	 */
	public static final @NonNull String EPACKAGE_PRECEDENCE_ASSOCIATIVITY = "associativity";
//	public static final @NonNull String PRECEDENCE_ORDER = "order";

	/**
	 * EParameter EAnnotation qualification.
	 */
	public static final @NonNull String EPARAMETER_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL-EParameter";
	/**
	 * 	 The EParameter is: true - a type, blank/false - regular.
	 */
	public static final @NonNull String EPARAMETER_IS_TYPE_OF = "isTypeOf";

	/**
	 * EReference annotation qualification.
	 */
	public static final @NonNull String ESTRUCTURAL_FEATURE_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL-EStructuralFeature";
//	public static final @NonNull String PROPERTY_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL-Property";
	/**
	 * 	 The property is: fully qualified implementation class name.
	 */
	public static final @NonNull String ESTRUCTURAL_FEATURE_IMPLEMENTATION = "implementation";
	/**
	 * EStructuralFeature annotation identifying that a Property is static.
	 */
	public static final @NonNull String ESTRUCTURAL_FEATURE_IS_STATIC = "isStatic";
	/**
	 * EReference annotation identifying that a Property is cyclic.
	 */
	public static final @NonNull String ESTRUCTURAL_FEATURE_SELF = "self";

	/**
	 * ETypedElement annotation qualification.
	 */
	public static final @NonNull String ETYPED_ELEMENT_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL-ETypedElement";
	/**
	 * ETypedElement annotation identifying the OCL type such as String that was replaced by an Ecore type such as EString.
	 */
	public static final @NonNull String ETYPED_ELEMENT_ORIGINAL_TYPE = "originalType";

	/**
	 * Package annotation indicating that the Package is the AS Library part of an AS Metamodel.
	 * No details are defined for this Annotation.
	 * <p>
	 * This annotation is used to indicate that the transient packageId should $metamodel$. It is not
	 * intended to be used by client code.
	 */
	public static final @NonNull String PACKAGE_AS_LIBRARY_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL-Package-ASLibrary";

	/**
	 * Package annotation indicating that the Package is part of the OCL AS Metamodel.
	 * No details are defined for this Annotation.
	 * <p>
	 * This annotation is used to indicate that the transient packageId should $metamodel$. It is not
	 * intended to be used by client code.
	 */
	public static final @NonNull String PACKAGE_AS_METAMODEL_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL-Package-ASMetamodel";

	public static final class EAnnotationComparator implements Comparator<EAnnotation>
	{
		public static final @NonNull EAnnotationComparator INSTANCE = new EAnnotationComparator();

		@Override
		public int compare(EAnnotation o1, EAnnotation o2) {
			String n1 = o1.getSource();
			String n2 = o2.getSource();
			return ClassUtil.safeCompareTo(n1, n2);
		}
	}

	public static @Nullable Annotation basicGetAnnotation(@NonNull Element asElement, @NonNull String annotationSource) {
		for (Element asElementAnnotation : asElement.getOwnedAnnotations()) {
			if (asElementAnnotation instanceof Annotation) {
				Annotation asAnnotation = (Annotation)asElementAnnotation;
				if (annotationSource.equals(asAnnotation.getName())) {
					return asAnnotation;
				}
			}
		}
		return null;
	}

	public static @Nullable String basicGetAnnotationValue(@NonNull Element asElement, @NonNull String source, @NonNull String key) {
		Annotation asAnnotation = basicGetAnnotation(asElement, source);
		if (asAnnotation == null) {
			return null;
		}
		Detail asDetail = NameUtil.getNameable(asAnnotation.getOwnedDetails(), key);
		return asDetail != null ? StringUtil.splice(asDetail.getValues(), " ") : null;
	}

	/**
	 * Return the value of the key of the source annotation of the eModelElement.
	 * Returns null if not available.
	 */
	public static @Nullable String basicGetEAnnotationValue(@Nullable EModelElement eModelElement, @NonNull String source, @NonNull String key) {
		if (eModelElement == null) {
			return null;
		}
		EAnnotation eAnnotation = eModelElement.getEAnnotation(source);
		if (eAnnotation == null) {
			return null;
		}
		return eAnnotation.getDetails().get(key);
	}

	public static @NonNull Annotation getAnnotation(@NonNull Element asElement, @NonNull String annotationSource) {
		Annotation asAnnotation = basicGetAnnotation(asElement, annotationSource);
		if (asAnnotation == null) {
			asAnnotation = PivotFactory.eINSTANCE.createAnnotation();
			asAnnotation.setName(annotationSource);
			asElement.getOwnedAnnotations().add(asAnnotation);
		}
		return asAnnotation;
	}

	public static @NonNull EAnnotation getEAnnotation(@NonNull EModelElement eModelElement, @NonNull String source) {
		EAnnotation eAnnotation = eModelElement.getEAnnotation(source);
		if (eAnnotation == null) {
			eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
			eAnnotation.setSource(source);
			eModelElement.getEAnnotations().add(eAnnotation);
		}
		return eAnnotation;
	}

/*	public static boolean hasDocumentationKey(@Nullable String source, @NonNull EMap<String, String> details) {
		return (details.size() == 1) && PivotConstantsInternal.DOCUMENTATION_ANNOTATION_SOURCE.equals(source)
				&& details.containsKey(PivotConstantsInternal.DOCUMENTATION_ANNOTATION_KEY);
	} */

	public static boolean hasSyntheticRole(@NonNull EClassifier eClassifier) {
		String role = basicGetEAnnotationValue(eClassifier, ECLASSIFIER_ANNOTATION_SOURCE, ECLASSIFIER_ROLE);
	//	return (role != null) && !CLASSIFIER_ROLE_DATA_TYPE.equals(role);
		return isSyntheticRole(role);
	}

	public static boolean isASLibrary(@NonNull EPackage ePackage) {
		return (ePackage.getEAnnotation(AnnotationUtil.EPACKAGE_AS_LIBRARY_ANNOTATION_SOURCE) != null)
		||  (ePackage.getEAnnotation(AnnotationUtil.legacy_AS_LIBRARY_ANNOTATION_SOURCE) != null);
	}

	public static boolean isASLibrary(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		return (AnnotationUtil.basicGetAnnotation(asPackage, AnnotationUtil.PACKAGE_AS_LIBRARY_ANNOTATION_SOURCE) != null)
		||  (AnnotationUtil.basicGetAnnotation(asPackage, AnnotationUtil.legacy_AS_LIBRARY_ANNOTATION_SOURCE) != null);
	}

	public static boolean isASMetamodel(@NonNull EPackage ePackage) {
		return (ePackage.getEAnnotation(AnnotationUtil.EPACKAGE_AS_METAMODEL_ANNOTATION_SOURCE) != null)
		|| (ePackage.getEAnnotation(AnnotationUtil.legacy_AS_METAMODEL_ANNOTATION_SOURCE) != null);
	}

	public static boolean isASMetamodel(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		return (AnnotationUtil.basicGetAnnotation(asPackage, AnnotationUtil.PACKAGE_AS_METAMODEL_ANNOTATION_SOURCE) != null)
		|| (AnnotationUtil.basicGetAnnotation(asPackage, AnnotationUtil.legacy_AS_METAMODEL_ANNOTATION_SOURCE) != null);
	}

	public static @Nullable EAnnotation isDataType(@NonNull EModelElement eModelElement) {
		EAnnotation eAnnotation = eModelElement.getEAnnotation(ECLASSIFIER_ANNOTATION_SOURCE);
		if (eAnnotation == null) {
			return null;
		}
		if (!eAnnotation.getDetails().get(ECLASSIFIER_ROLE).equals(ECLASSIFIER_ROLE_DATA_TYPE)) {
			return null;
		}
		assert eAnnotation.getReferences().size() == 1;
		assert eAnnotation.getReferences().get(0) instanceof EDataType;
		assert eModelElement instanceof EClass;
		return eAnnotation;
	}

	public static boolean isDocumentationKey(@Nullable String source, @Nullable String key) {
		return PivotConstantsInternal.DOCUMENTATION_ANNOTATION_SOURCE.equals(source)
				&& PivotConstantsInternal.DOCUMENTATION_ANNOTATION_KEY.equals(key);
	}

	public static boolean isGenModelSource(@Nullable String source) {
		return PivotConstantsInternal.DOCUMENTATION_ANNOTATION_SOURCE.equals(source);
	}

//	public static boolean isOCLImportSource(@Nullable String source) {
//		return PivotConstants.IMPORT_ANNOTATION_SOURCE.equals(source);
//	}

	public static boolean isOriginalNameKey(@Nullable String source, @Nullable String key) {
		return DerivedConstants.UML2_UML_PACKAGE_2_0_NS_URI.equals(source)
				&& DerivedConstants.ANNOTATION_DETAIL__ORIGINAL_NAME.equals(key);
	}

	public static boolean isSyntheticRole(@Nullable String role) {
		if (role == null) {
			return false;
		}
		if (ECLASSIFIER_ROLE_DATA_TYPE.equals(role)) {
			return false;		// DataType be synthesized as a Class
		}
		if (ECLASSIFIER_ROLE_DATA_TYPE_VALUE.equals(role)) {
			return true;		// DataType Value should be synthesized by its corresponding DataType Class
		}
		if (ECLASSIFIER_ROLE_ENTRY.equals(role)) {
			return true;		// Entry Class should be synthesized as part of a Map
		}
		if (ECLASSIFIER_ROLE_LAMBDA.equals(role)) {
			return true;		// Lambda should be synthesized as an orphan
		}
		if (ECLASSIFIER_ROLE_TUPLE.equals(role)) {
			return true;		// Tuple should be synthesized as an orphan
		}
		throw new UnsupportedOperationException(role);
	}

	public static @Nullable String removeDetail(@NonNull EModelElement eModelElement, @NonNull String source, @NonNull String key) {
		EAnnotation eAnnotation = eModelElement.getEAnnotation(source);
		if (eAnnotation == null) {
			return null;
		}
		EMap<String, String> details = eAnnotation.getDetails();
		return details.removeKey(key);
	}

	public static @Nullable String setDetail(@NonNull EModelElement eModelElement, @NonNull String source, @NonNull String key, @Nullable String value) {
		EAnnotation eAnnotation = getEAnnotation(eModelElement, source);
		EMap<String, String> details = eAnnotation.getDetails();
		return details.put(key, value);
	}
}
