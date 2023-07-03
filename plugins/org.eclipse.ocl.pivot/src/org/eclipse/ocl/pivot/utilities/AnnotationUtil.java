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
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.util.DerivedConstants;

public class AnnotationUtil
{
	/**
	 * EClassifier annotation qualifying a Classifier.
	 */
	public static final @NonNull String CLASSIFIER_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL/Pivot/Classifier";
	/**
	 * 	 The classifier role may be "Entry" or "Lambda" or blank.
	 */
	public static final @NonNull String CLASSIFIER_ROLE = "role";
	/**
	 * 	 An EntryXXXX<K,V> class types a Map Entry with Class/DataType and Required/Optional types.
	 */
	public static final @NonNull String CLASSIFIER_ROLE_ENTRY = "Entry";
	/**
	 * 	 A LambdaXXXX<C, P..., R> types a Lambda wth Context, Paramter(s) and Result.
	 */
	public static final @NonNull String CLASSIFIER_ROLE_LAMBDA = "Lambda";

	/**
	 * ETypedElement annotation qualification.
	 */
	public static final @NonNull String COLLECTION_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL/Collection";
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
	public static final @NonNull String OPERATION_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL/Pivot/Operation";
	/**
	 * 	 The operation is a constraint validator: true - transient (not-cached), blank/false - regular.
	 */
	public static final @NonNull String OPERATION_IS_CONSTRAINT = "isConstraint";		// XXX replace originalName
	/**
	 * 	 The operation is: true - invalidating (may source an invalid), blank/false - regular.
	 */
	public static final @NonNull String OPERATION_IS_INVALIDATING = "isInvalidating";
	/**
	 * 	 The operation is: true - transient (not-cached), blank/false - regular.
	 */
	public static final @NonNull String OPERATION_IS_TRANSIENT = "isTransient";
	/**
	 * 	 The operation is: true - validating (may absorb an invalid), blank/false - regular.
	 */
	public static final @NonNull String OPERATION_IS_VALIDATING = "isValidating";
	/**
	 * 	 The operation is: fully qualified implementation class name.
	 */
	public static final @NonNull String OPERATION_IMPLEMENTATION = "implementation";
	/**
	 * 	 The operation is an iteration with a given iterator count.
	 */
	public static final @NonNull String OPERATION_ITERATORS = "iterators";
	/**
	 * 	 The operation is an iterate iteration with a given accumulator count.
	 */
	public static final @NonNull String OPERATION_ACCUMULATORS = "accumulators";
	/**
	 * 	 The operation has a given named precedence.
	 */
	public static final @NonNull String OPERATION_PRECEDENCE = "precedence";

	/**
	 * EPackage annotation qualification.
	 */
	public static final @NonNull String PACKAGE_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL/Pivot/Package";
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
	 * EParameter annotation qualification.
	 */
	public static final @NonNull String PARAMETER_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL/Pivot/Parameter";
	/**
	 * 	 The EParameter is: true - a type, blank/false - regular.
	 */
	public static final @NonNull String PARAMETER_IS_TYPE_OF = "isTypeOf";

	/**
	 * EPackage annotation declaring one of precedence-ordered list of named precedences.
	 */
	public static final @NonNull String PRECEDENCE_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL/Pivot/Precedence";
	/**
	 * 	 The name of the precedence.
	 */
	public static final @NonNull String PRECEDENCE_NAME = "name";
	/**
	 * 	 The AssociativityKind of the precedence.
	 */
	public static final @NonNull String PRECEDENCE_ASSOCIATIVITY = "associativity";
//	public static final @NonNull String PRECEDENCE_ORDER = "order";

	/**
	 * EReference annotation qualification.
	 */
	public static final @NonNull String PROPERTY_ANNOTATION_SOURCE = "http://www.eclipse.org/OCL/Property";
	/**
	 * EReference annotation identifying that a Property is cyclic.
	 */
	public static final @NonNull String PROPERTY_SELF = "self";

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

	public static @NonNull EAnnotation getEAnnotation(@NonNull EModelElement eModelElement, @NonNull String source) {
		EAnnotation eAnnotation = eModelElement.getEAnnotation(source);
		if (eAnnotation == null) {
			eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
			eAnnotation.setSource(source);
			eModelElement.getEAnnotations().add(eAnnotation);
		}
		return eAnnotation;
	}

	/**
	 * Return the value of the key of the source annotation of the eModelElement.
	 * Returns null if not available.
	 */
	public static @Nullable String getEAnnotationValue(@Nullable EModelElement eModelElement, @NonNull String source, @NonNull String key) {
		if (eModelElement == null) {
			return null;
		}
		EAnnotation eAnnotation = eModelElement.getEAnnotation(source);
		if (eAnnotation == null) {
			return null;
		}
		return eAnnotation.getDetails().get(key);
	}

/*	public static boolean hasDocumentationKey(@Nullable String source, @NonNull EMap<String, String> details) {
		return (details.size() == 1) && PivotConstantsInternal.DOCUMENTATION_ANNOTATION_SOURCE.equals(source)
				&& details.containsKey(PivotConstantsInternal.DOCUMENTATION_ANNOTATION_KEY);
	} */

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
